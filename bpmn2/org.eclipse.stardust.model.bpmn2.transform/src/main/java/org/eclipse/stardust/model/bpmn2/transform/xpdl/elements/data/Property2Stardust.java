package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.bpmn2.Property;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class Property2Stardust extends Data2Stardust {

	public Property2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addProperty(Property property, Map<String, String> predefinedDataForId) {
		debugPrint(predefinedDataForId);
		if (ExtensionHelper2.INSTANCE.isSynthetic(property)) {
			String predefinedId = ExtensionHelper2.INSTANCE.getStardustPropertyId(property);
			if (null != predefinedId) predefinedDataForId.put(property.getId(), predefinedId);
		} else {
			addVariable(property, property.getName());
		}
	}

	private void debugPrint(Map<String, String> predefinedDataForId) {
		System.out.println("Property2Stardust.debugPrint() ####################################### ");
		for (Entry<String, String> e: predefinedDataForId.entrySet()) {
			System.out.println(e.getKey() + " = " + e.getValue());		
		}
		System.out.println("############################################################# ");
	}

}
