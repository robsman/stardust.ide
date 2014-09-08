package org.eclipse.stardust.model.bpmn2.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Generates and evaluates paths based on a schema definition. 
 * 
 * @author Simon Nikles
 *
 */
public enum DataMappingPathHelper {
	
	INSTANCE;
	
	public AccessPointPathInfo resolveAccessPointPath(String dataPath) {
		String[] segments = getPathSegments(dataPath);
		AccessPointPathInfo info = new AccessPointPathInfo();
		if (0 < segments.length) {
			info.setAccessPointId(segments[0]);
		}
		if (1 < segments.length) {
			getPathAfterFirst(segments);
		}
		return info;
	}

	private String getPathAfterFirst(String[] segments) {
		if (null == segments || segments.length < 2) return "";
		List<String> subList = Arrays.asList(segments).subList(1, segments.length-1);
		StringBuffer buf = new StringBuffer();
		String delim = "";
		for (String sub : subList) {
			buf.append(delim).append(sub);
			delim = "/";
		}
		return buf.toString();
	}

	private String[] getPathSegments(String dataPath) {
		String cleanPath = getCleanPath(dataPath);
		if (cleanPath.isEmpty()) return new String[]{};
		return cleanPath.split("/");
	}

	private String getCleanPath(String dataPath) {
		if (null == dataPath) return "";
		String cleanPath = dataPath.trim().startsWith("/") ? dataPath.trim().substring(1) : dataPath.trim();
		return cleanPath;
	}

	public List<String> getDataPaths(ItemAwareElement dataItem) {
		ItemDefinition itemDef = dataItem.getItemSubjectRef();
		return getDataPaths(itemDef);
	}

	public List<String> getDataPaths(ItemDefinition itemDef) {
		List<String> paths = new ArrayList<String>();
		if (null == itemDef) return paths;
		Object structureRef = itemDef.getStructureRef();
		System.out.println("structureRef " + structureRef);
		
		// use the type of the referenced element declaration
		if (structureRef instanceof XSDElementDeclaration) {
			System.out.println("is element decl");
			return buildPaths(((XSDElementDeclaration)structureRef).getType().getElement(), "", paths);
		}
		// use the referenced type 
		if (structureRef instanceof XSDTypeDefinition) {
			System.out.println("is type definition");
			return buildPaths(((XSDElementDeclaration)structureRef).getType().getElement(), "", paths);
		} 	
		
		// lookup embedded schema
		XSDSchema schema = ExtensionHelper2.INSTANCE.getEmbeddedSchemaExtension(itemDef);
		if (null == schema) return paths;		
		schema.updateDocument();

		// resolve structureRef (as uri from String)
		if (null != structureRef) {
			System.out.println("is anytype");
			System.out.println(structureRef.toString());
			try {
//				URI uri = new URI(structureRef.toString());
//				String ns = uri.getPath();
//				String name = uri.getFragment();
				// try to resolve element reference
				XSDElementDeclaration element = schema.resolveElementDeclarationURI(structureRef.toString());
				if (null != element) {
					System.out.println("resolved structureRef as embedded element definition");
					return buildPaths(element.getType().getElement(), "", paths);
				} 
				// try to resolve as type reference
				XSDComplexTypeDefinition type = schema.resolveComplexTypeDefinitionURI(structureRef.toString());
				if (null != type) {
					System.out.println("resolved structureRef as embedded type definition");
					return buildPaths(type.getElement(), "", paths);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
		
		if (null == schema.getElement()) return paths;

		// use type of the first element declaration in the embedded schema
		EList<XSDElementDeclaration> elementDeclarations = schema.getElementDeclarations();
		if (null != elementDeclarations && elementDeclarations.size() > 0) {
			if (null != elementDeclarations.get(0) && null != elementDeclarations.get(0).getType()) {
				System.out.println("used first element definition");
				return buildPaths(elementDeclarations.get(0).getType().getElement(), "", paths);
			}
		}
		// use first type in the embedded schema
		EList<XSDTypeDefinition> typeDefinitions = schema.getTypeDefinitions();
		if (null != typeDefinitions && typeDefinitions.size() > 0) {
			if (null != typeDefinitions.get(0)) {
				System.out.println("used first type definition");
				return buildPaths(typeDefinitions.get(0).getElement(), "", paths);
			}
		}
		
		return paths; // no results
	}
	
	private List<String> buildPaths(Node element, String parentPath, List<String> paths) {
		if (null == element) return paths;
		if ("element".equals(element.getLocalName())) {
			NamedNodeMap attributes = element.getAttributes();
			if (null != attributes) {
				Node namedItem = attributes.getNamedItem(ExtensionHelper2.STARDUST_ACCESSPOINT_ID);
				if (null == namedItem) namedItem = attributes.getNamedItem("name");
				if (null == namedItem) return paths;
				String textContent = namedItem.getNodeValue();
				if (notNullOrEmpty(textContent)) {
					parentPath = parentPath.concat("/"+textContent);
					paths.add(parentPath);
				}
				paths = buildPaths(attributes.getNamedItemNS(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, "type"), parentPath, paths);
			}
		} else {
			NodeList nodes = element.getChildNodes();
			for (int i=0; i<nodes.getLength(); i++) {
				Node node = nodes.item(i);
				paths = buildPaths(node, parentPath, paths);
			}
		}
		return paths;
	}

	private boolean notNullOrEmpty(String textContent) {
		return null != textContent && !textContent.isEmpty();
	}


	public class AccessPointPathInfo {
		private String accessPointId;
		private String accessPointPath;

		public AccessPointPathInfo() {}
		
		public AccessPointPathInfo(String accessPoint, String accessPointPath) {
			this.accessPointId = accessPoint;
			this.accessPointPath = accessPointPath;
		}

		public String getAccessPointId() {
			return accessPointId;
		}

		public void setAccessPointId(String accessPointId) {
			this.accessPointId = accessPointId;
		}

		public String getAccessPointPath() {
			return accessPointPath;
		}

		public void setAccessPointPath(String accessPointPath) {
			this.accessPointPath = accessPointPath;
		}
	}
}
