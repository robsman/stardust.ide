package org.eclipse.stardust.ui.web.modeler.bpmn2.utils;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

public class Bpmn2DatatypeUtil {

   private static final String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";

	public static boolean refersToPrimitiveType(ItemAwareElement data) {
		URI typeUri = getDataStructureURI(data);
		if (typeUri == null) return false;
		return isXsdType(typeUri);
	}

	protected static boolean isXsdType(URI typeUri) {
		if (typeUri == null) return false;
		URI baseUri = typeUri.trimFragment();
		return baseUri.toString().equals(XML_SCHEMA_URI);
	}

	public static URI getDataStructureURI(ItemAwareElement data) {
		if (data == null) return null;
		ItemDefinition itemDef = data.getItemSubjectRef();
		Definitions defs = ModelInfo.getDefinitions(data);
		return getDataStructureURI(itemDef, defs);
	}

	protected static URI getDataStructureURI(ItemDefinition itemDef, Definitions defs) {
		if (itemDef == null) return null;
		if (itemDef.eIsProxy()) {
			itemDef = Bpmn2ProxyResolver.resolveItemDefinition(itemDef, defs);
		}
		if (null == itemDef) return null;
		EObject structureRef = (EObject)itemDef.getStructureRef();
		if (structureRef == null) return null;

		return ((InternalEObject)structureRef).eProxyURI();
	}


}
