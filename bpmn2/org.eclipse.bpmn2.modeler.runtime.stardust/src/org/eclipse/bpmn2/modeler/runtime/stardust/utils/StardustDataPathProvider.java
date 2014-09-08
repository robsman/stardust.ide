package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.bpmn2.extension.DataMappingPathHelper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Generates possible paths based on a schema definition. These paths may be offered as selection for data mappings. 
 * 
 * @author Simon Nikles
 *
 */
public enum StardustDataPathProvider {

	INSTANCE;
	
	public List<String> getDataPaths(ItemAwareElement dataItem) {
		return DataMappingPathHelper.INSTANCE.getDataPaths(dataItem);
	}

	public List<String> getDataPaths(ItemDefinition itemDef) {
		return DataMappingPathHelper.INSTANCE.getDataPaths(itemDef);
	}

}
