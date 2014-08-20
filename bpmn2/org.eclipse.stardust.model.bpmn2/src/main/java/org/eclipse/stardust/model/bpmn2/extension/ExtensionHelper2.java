package org.eclipse.stardust.model.bpmn2.extension;

import java.util.Arrays;
import java.util.List;

import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;

public enum ExtensionHelper2 {

	INSTANCE;
		
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
							
//							&& xmlschema2001.equals(ExtendedMetaData.INSTANCE.getNamespace(feature)) ExtendedMetaData.INSTANCE.get
//							&& featureName.equals(feature.getName())) {
						if (e.getValue() instanceof XSDSchema) {
							return (XSDSchema)e.getValue();
						}
					}

				}
			}
			return null;
		}
		
}
