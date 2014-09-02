package org.eclipse.stardust.model.bpmn2.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.stardust.model.bpmn2.extension.AccessPointSchemaWrapper.AccessPointSchemaElement;
import org.eclipse.stardust.model.bpmn2.extension.AccessPointSchemaWrapper.Direction;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Simon Nikles
 *
 */
public enum ExtensionHelper2 {

	INSTANCE;

	public static final String STARDUST_EXTENSION_NAMESPACE = ExtensionHelper.NS_URI_STARDUST; //"http://www.eclipse.org/stardust/model/bpmn2/sdbpmn";
	public static final String STARDUST_EXTENSION_PREFIX = "sdbpmn";
	public static final String STARDUST_ACCESSPOINT_ID = "sdbpmn:accesspoint";
	public static final String STARDUST_ACCESSPOINT_TYPE_CLASSNAME = "sdbpmn:typeClass";
	public static final String STARDUST_ACCESSPOINT_DISPLAY_NAME = "sdbpmn:displayName";		
	public static final String STARDUST_SYNTHETIC_ITEMDEF = "syntheticItemDefinition";
	public static final String STARDUST_ACCESSPOINT_SCHEMA_TYPE_POSTFIX = "Type";
	public static final String STARDUST_ACCESSPOINT_SCHEMA_ELEMENT_POSTFIX = "Element";

	public XSDSchema getEmbeddedSchemaExtension(ItemDefinition itemdef) {
		final String featureName = XSDPackage.Literals.XSD_CONCRETE_COMPONENT__SCHEMA.getName();
		final String xmlschema2001 = XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001;
		final String xmlschema1999 = XSDConstants.SCHEMA_FOR_SCHEMA_URI_1999;
		final String xmlschema200010 = XSDConstants.SCHEMA_FOR_SCHEMA_URI_2000_10;
		final String xmlnsUri = XSDConstants.XMLNS_URI_2000;

		final List<String> consideredNamespaces = Arrays.asList(xmlschema2001, xmlschema1999, xmlschema200010, xmlnsUri);

		for (ExtensionAttributeValue extensionAttributeValue : itemdef.getExtensionValues()) {
			FeatureMap extensionElements = extensionAttributeValue.getValue(); 
			for (Entry e : extensionElements) {
				EStructuralFeature feature = e.getEStructuralFeature();
				if (null != feature
						&& featureName.equals(feature.getName())
						&& consideredNamespaces.contains(ExtendedMetaData.INSTANCE.getNamespace(feature))) {
					if (e.getValue() instanceof XSDSchema) {
						return (XSDSchema)e.getValue();
					}
				}
			}
		}
		return null;
	}

	public boolean isSynthetic(ItemDefinition itemDef) {
		Iterator<Entry> iterator = itemDef.getAnyAttribute().iterator();
		while (iterator.hasNext()) {
			Entry item = iterator.next();
			EStructuralFeature feature = item.getEStructuralFeature();
			String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(feature);
			if (!STARDUST_EXTENSION_NAMESPACE.equals(extensionNs)) continue;
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute)feature;
				if (STARDUST_SYNTHETIC_ITEMDEF.equals(attr.getName())) {
					return "true".equals(item.getValue());
				}
			}
		} return false;
	}

	/**
	 * Returns the value of the attribute named <code>localName</code> in the namespace <code>attributeNamespaceUri</code>
	 * of an element named <code>elementName</code> of the complexType of the given <code>schemaElement</code>.
	 * <br>Note that only complex types consisting of a simple sequence of elements are considered.
	 */
	public String getSchemaElementAttributeValue(XSDElementDeclaration schemaElement, String elementName, String attributeNamespaceUri, String localName) {
		XSDComplexTypeDefinition complexType = (XSDComplexTypeDefinition)schemaElement.getType();
		return getSchemaElementAttributeValue(complexType, elementName, attributeNamespaceUri, localName);	        
	}

	/**
	 * Returns the value of the attribute named <code>localName</code> in the namespace <code>attributeNamespaceUri</code>
	 * of an element named <code>elementName</code> of the <code>complexType</code>.
	 * @param complexType
	 * @param elementName
	 * @param attributeNamespaceUri
	 * @param localName
	 * @return
	 */
	public String getSchemaElementAttributeValue(XSDComplexTypeDefinition complexType, String elementName, String attributeNamespaceUri, String localName) {
		XSDComplexTypeContent content = complexType.getContent();
		NodeList childNodes = content.getElement().getChildNodes();
		return getAttributeValue(childNodes, elementName, attributeNamespaceUri, localName);
	}

	public Node getSchemaTypeElement(NodeList nodes, String elementName) {
		Node node = null;
		for (int i = 0; i < nodes.getLength(); i++) {
			Node item = nodes.item(i);
			if (elementName.equals(item.getAttributes().getNamedItem("name").getTextContent())) {
				return item;
			}
			node = getSchemaTypeElement(item.getChildNodes(), elementName);
			if (null != node) return node;
		}
		return node;
	}

	private String getAttributeValue(NodeList nodes, String elementName, String attributeNamespaceUri, String localName) {
		Node element = getSchemaTypeElement(nodes, elementName);
		if (null == element) return null;
		NamedNodeMap attributes = element.getAttributes();
		Node namedItemNS = attributes.getNamedItemNS(attributeNamespaceUri, localName);
		if (null != namedItemNS) return namedItemNS.getTextContent();
		return null;
	}		

	public ItemDefinition createInputAccessPointItemDefinition(AccessPointSchemaWrapper schemaInfo, ItemDefinition itemDef) {
		return createAccessPointItemDefinition(schemaInfo, itemDef, Direction.IN);
	}

	public ItemDefinition createOutputAccessPointItemDefinition(AccessPointSchemaWrapper schemaInfo, ItemDefinition itemDef) {
		return createAccessPointItemDefinition(schemaInfo, itemDef, Direction.OUT);
	}

	public ItemDefinition createAccessPointItemDefinition(AccessPointSchemaWrapper schemaInfo, ItemDefinition itemDef) {
		return createAccessPointItemDefinition(schemaInfo, itemDef, Direction.BOTH);
	}

	private ItemDefinition createAccessPointItemDefinition(AccessPointSchemaWrapper schemaInfo, ItemDefinition itemDef, Direction direction) {
		ExtensionHelper.getInstance().setAnyAttribute(itemDef, STARDUST_SYNTHETIC_ITEMDEF, "true");
		int seq = 0;
		try {
			System.out.println("substring: " + itemDef.getId().substring(itemDef.getId().lastIndexOf("_")));
			String substring = itemDef.getId().substring(itemDef.getId().lastIndexOf("_")+1);
			seq = Integer.valueOf(substring);
		} catch (Exception e) {
			e.printStackTrace();
		}
		XSDSchema schema = createSchema(schemaInfo, seq, direction);
		EList<XSDElementDeclaration> elementDeclarations = schema.getElementDeclarations();
		if (elementDeclarations.size() > 0) {
			URI uriRef = URI.createURI(elementDeclarations.get(0).getAliasURI());
			itemDef.setStructureRef(getProxyElement(uriRef));
		}
		schema.getDocument().normalizeDocument();
		ExtensionHelper.getInstance().setExtension(itemDef, schema);
		return itemDef;
	}
	
	private EObject getProxyElement(URI uri) {
		DynamicEObjectImpl dyn = new DynamicEObjectImpl();
		dyn.eSetProxyURI(uri);
		return dyn;
	}

	public XSDSchema createSchema(AccessPointSchemaWrapper schemaInfo, int sequence, Direction direction) {

		final XSDFactory factory = XSDFactory.eINSTANCE;
		final String targetNamespace = STARDUST_EXTENSION_NAMESPACE + "/AccessPoints/" + sequence;
		final String targetNamespacePrefix = "AccessPoints_" + sequence;

		XSDSchema schema = factory.createXSDSchema();
		schema.setSchemaForSchemaQNamePrefix("xsd");
		schema.setTargetNamespace(targetNamespace);
		Map<String, String> prefixMap = schema.getQNamePrefixToNamespaceMap();
		prefixMap.put(schema.getSchemaForSchemaQNamePrefix(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);			
		prefixMap.put(STARDUST_EXTENSION_PREFIX, STARDUST_EXTENSION_NAMESPACE);
		prefixMap.put(targetNamespacePrefix, targetNamespace);

		XSDComplexTypeDefinition accessPointsType = factory.createXSDComplexTypeDefinition();
		accessPointsType.setName("AccessPoint"+sequence+STARDUST_ACCESSPOINT_SCHEMA_TYPE_POSTFIX);

		XSDElementDeclaration element = factory.createXSDElementDeclaration();
		element.setName("AccessPoint"+sequence+STARDUST_ACCESSPOINT_SCHEMA_ELEMENT_POSTFIX);
		element.setTypeDefinition(accessPointsType);			
		//schema.getElementDeclarations().add(element);
		schema.getContents().add(element);

		XSDParticle apSeqeuenceParticle = factory.createXSDParticle();
		XSDModelGroup apSeqeuence = factory.createXSDModelGroup();
		apSeqeuence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);

		apSeqeuenceParticle.setContent(apSeqeuence);
		accessPointsType.setContent(apSeqeuenceParticle);
		schema.getContents().add(accessPointsType);

		for (AccessPointSchemaElement typeElement : schemaInfo.getElements()) {
			if (!Direction.BOTH.equals(direction)) {
				if (!direction.equals(typeElement.getDirection())) continue;
			}
			XSDElementDeclaration ap = factory.createXSDElementDeclaration();
			ap.setName(typeElement.getElementName());
			
			String typeName = typeElement.getDataType().getName();
			XSDSimpleTypeDefinition simpleType = schema.getSchemaForSchema().resolveSimpleTypeDefinition(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, typeName);			
			ap.setTypeDefinition(simpleType);
			//schema.getContents().add(typeElement.getDataType());
			
			XSDParticle particle = factory.createXSDParticle();
			particle.setContent(ap);				
			apSeqeuence.getContents().add(particle);

			ap.updateElement(true); // update/create dom and add custom-attributes 
			ap.getElement().setAttributeNS(STARDUST_EXTENSION_NAMESPACE, STARDUST_ACCESSPOINT_ID , typeElement.getAccessPointId());
			ap.getElement().setAttributeNS(STARDUST_EXTENSION_NAMESPACE, STARDUST_ACCESSPOINT_DISPLAY_NAME , typeElement.getDisplayName());
			if (null != typeElement.getTypeClassName()) {
				ap.getElement().setAttributeNS(STARDUST_EXTENSION_NAMESPACE, STARDUST_ACCESSPOINT_TYPE_CLASSNAME , typeElement.getTypeClassName());
			}
		}
		return schema;
	}

}
