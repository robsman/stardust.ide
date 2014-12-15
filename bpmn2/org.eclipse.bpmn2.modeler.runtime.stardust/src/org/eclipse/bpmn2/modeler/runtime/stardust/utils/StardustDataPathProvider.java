package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.util.List;

import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.stardust.model.bpmn2.extension.DataMappingPathHelper;

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
