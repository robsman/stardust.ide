package org.eclipse.stardust.model.bpmn2.extension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Property;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
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
	public static final String STARDUST_SHORT_NAMESPACE = ExtensionHelper.NS_URI_STARDUST_SHORT;
	public static final String STARDUST_EXTENSION_PREFIX = "sdbpmn";
	public static final String STARDUST_ACCESSPOINT_ID = "sdbpmn:accesspoint";
	public static final String STARDUST_ACCESSPOINT_TYPE_CLASSNAME = "sdbpmn:typeClass";
	public static final String STARDUST_ACCESSPOINT_DISPLAY_NAME = "sdbpmn:displayName";
	public static final String STARDUST_SYNTHETIC_ITEMDEF = "syntheticItemDefinition";
	public static final String STARDUST_SYNTHETIC_PROPERTY = "syntheticProperty";
	public static final String STARDUST_ACCESSPOINT_SCHEMA_TYPE_POSTFIX = "Type";
	public static final String STARDUST_ACCESSPOINT_SCHEMA_ELEMENT_POSTFIX = "Element";
	public static final String STARDUST_PROPERTY_ID = "stardustPropertyId";

	public static final String STARDUST_IGNORE_PROPERTY = "stardustIgnore";
	public static final String STARDUST_CARNOT_VERSION_PROPERTY = "targetCarnotVersion";
	public static final String STARDUST_MODEL_VERSION_PROPERTY = "stardustModelVersion";

	private static final String TRUE = Boolean.TRUE.toString();

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

	public boolean isSynthetic(BaseElement element) {
		Iterator<Entry> iterator = element.getAnyAttribute().iterator();
		while (iterator.hasNext()) {
			Entry item = iterator.next();
			EStructuralFeature feature = item.getEStructuralFeature();
			String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(feature);
			if (!STARDUST_EXTENSION_NAMESPACE.equals(extensionNs)) continue;
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute)feature;
				if (STARDUST_SYNTHETIC_PROPERTY.equals(attr.getName()) || STARDUST_SYNTHETIC_ITEMDEF.equals(attr.getName())) {
					return TRUE.equals(item.getValue().toString());
				}
			}
		} return false;
	}

	public boolean hasIgnoreFlag(BaseElement element) {
		Iterator<Entry> iterator = element.getAnyAttribute().iterator();
		while (iterator.hasNext()) {
			Entry item = iterator.next();
			EStructuralFeature feature = item.getEStructuralFeature();
			String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(feature);
			if (!STARDUST_EXTENSION_NAMESPACE.equals(extensionNs)) continue;
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute)feature;
				if (STARDUST_IGNORE_PROPERTY.equals(attr.getName())) {
					return TRUE.equals(item.getValue().toString());
				}
			}
		} return false;
	}

	public String getCarnotVersion(Definitions element) {
		Iterator<Entry> iterator = element.getAnyAttribute().iterator();
		while (iterator.hasNext()) {
			Entry item = iterator.next();
			EStructuralFeature feature = item.getEStructuralFeature();
			String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(feature);
			if (!STARDUST_EXTENSION_NAMESPACE.equals(extensionNs)) continue;
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute)feature;
				if (STARDUST_CARNOT_VERSION_PROPERTY.equals(attr.getName())) {
					return item.getValue().toString();
				}
			}
		} return null;
	}

	public void setIgnoreFlag(BaseElement element) {
		ExtensionHelper.getInstance().setAnyAttribute(element, STARDUST_IGNORE_PROPERTY, TRUE);
	}

	public void setCarnotVersion(String version, Definitions element) {
		ExtensionHelper.getInstance().setAnyAttribute(element, STARDUST_CARNOT_VERSION_PROPERTY, version);
	}

	public void removeIgnoreFlag(BaseElement element) {
		EStructuralFeature featureToRemove = null;
		Iterator<Entry> iterator = element.getAnyAttribute().iterator();
		while (iterator.hasNext()) {
			Entry item = iterator.next();
			EStructuralFeature feature = item.getEStructuralFeature();
			String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(feature);
			if (!STARDUST_EXTENSION_NAMESPACE.equals(extensionNs)) continue;
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute)feature;
				if (STARDUST_PROPERTY_ID.equals(attr.getName())) {
					featureToRemove = feature;
					break;
				}
			}
		}
		if (null != featureToRemove) {
			element.getAnyAttribute().remove(featureToRemove);
		}
	}

	public String getStardustPropertyId(Property prop) {
		Iterator<Entry> iterator = prop.getAnyAttribute().iterator();
		while (iterator.hasNext()) {
			Entry item = iterator.next();
			EStructuralFeature feature = item.getEStructuralFeature();
			String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(feature);
			if (!STARDUST_EXTENSION_NAMESPACE.equals(extensionNs)) continue;
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute)feature;
				if (STARDUST_PROPERTY_ID.equals(attr.getName())) {
					return item.getValue().toString();
				}
			}
		} return null;
	}

	public boolean hasStardustPropertyId(Property prop, String propId) {
		Iterator<Entry> iterator = prop.getAnyAttribute().iterator();
		while (iterator.hasNext()) {
			Entry item = iterator.next();
			EStructuralFeature feature = item.getEStructuralFeature();
			String extensionNs = ExtendedMetaData.INSTANCE.getNamespace(feature);
			if (!STARDUST_EXTENSION_NAMESPACE.equals(extensionNs)) continue;
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute)feature;
				if (STARDUST_PROPERTY_ID.equals(attr.getName())) {
					return propId.equals(item.getValue().toString());
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
		ExtensionHelper.getInstance().setAnyAttribute(itemDef, STARDUST_SYNTHETIC_ITEMDEF, Boolean.TRUE);
		int seq = 0;
		try {
			String substring = itemDef.getId().substring(itemDef.getId().lastIndexOf("_")+1);
			seq = Integer.valueOf(substring);
		} catch (Exception e) { // ignore - first one is not numbered
		}
		XSDSchema schema = createSchema(schemaInfo, seq, direction);
		EList<XSDElementDeclaration> elementDeclarations = schema.getElementDeclarations();
		schema.updateElement(true);
		schema.updateDocument();
		if (null != schema.getDocument()) schema.getDocument().normalizeDocument();
		if (elementDeclarations.size() > 0) {
			URI uriRef = URI.createURI(elementDeclarations.get(0).getAliasURI());
			itemDef.setStructureRef(getProxyElement(uriRef));
		}

		ExtensionHelper.getInstance().setExtension(itemDef, schema);
		return itemDef;
	}

	public EObject getProxyElement(final URI uri) {
		DynamicEObjectImpl dyn = new DynamicEObjectImpl() {
//		dyn.eSet(dyn.eClass().getEStructuralFeature("value"), uri.toString());

			public URI eProxyURI() {
				return uri;
			}
			public boolean eIsProxy() {
				return false;
			}
		};
		return dyn;
	}

	public XSDSchema createSchema(AccessPointSchemaWrapper schemaInfo, int sequence, Direction direction) {

		final XSDFactory factory = XSDFactory.eINSTANCE;
		//final String targetNamespace = STARDUST_EXTENSION_NAMESPACE + "/AccessPoints/" + direction + "/" + sequence;
		final String targetNamespace = STARDUST_SHORT_NAMESPACE + "/AP/" + direction + "/" + sequence;
		final String targetNamespacePrefix = "AccessPoints_" + direction + "_" + sequence;

		XSDSchema schema = factory.createXSDSchema();
		schema.setSchemaForSchemaQNamePrefix("xsd");
		schema.setTargetNamespace(targetNamespace);
		Map<String, String> prefixMap = schema.getQNamePrefixToNamespaceMap();
		prefixMap.put(schema.getSchemaForSchemaQNamePrefix(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
		prefixMap.put(STARDUST_EXTENSION_PREFIX, STARDUST_EXTENSION_NAMESPACE);
		prefixMap.put(targetNamespacePrefix, targetNamespace);

		XSDComplexTypeDefinition accessPointsType = factory.createXSDComplexTypeDefinition();
		String appId = null != schemaInfo.getOwnerApplicationId() ? schemaInfo.getOwnerApplicationId() : "AccessPoint"+sequence;
		String name = null;
		if (null != schemaInfo.getOwnerApplicationName()) {
			name = camelCase(schemaInfo.getOwnerApplicationName());
		} else {
			name = appId;
		}
		accessPointsType.setName(direction + appId + STARDUST_ACCESSPOINT_SCHEMA_TYPE_POSTFIX);

		XSDElementDeclaration element = factory.createXSDElementDeclaration();
		element.setName(name+STARDUST_ACCESSPOINT_SCHEMA_ELEMENT_POSTFIX);
		element.setTypeDefinition(accessPointsType);
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

			String typeName = null != typeElement.getDataType() ? typeElement.getDataType().getName() : "Undefined";
			XSDSimpleTypeDefinition simpleType = schema.getSchemaForSchema().resolveSimpleTypeDefinition(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001, typeName);
			ap.setTypeDefinition(simpleType);
//			schema.getContents().add(typeElement.getDataType());

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

	public static String camelCase(String base) {
		if (null == base) return null;
		String camelCase = "";
		String[] parts = base.split(" ");
		if (parts.length <= 1) return base;

		camelCase = parts[0].trim();
		for (int i = 1; i < parts.length; i++) {
			String part = parts[i];
			if (null == part || part.trim().isEmpty()) {
				continue;
			}
			if (part.length() > 1) {
				camelCase = camelCase.concat(part.substring(0, 1).toUpperCase()).concat(part.substring(1));
			} else {
				camelCase = camelCase.concat(part.toUpperCase());
			}
		}
		return camelCase;
	}

}
