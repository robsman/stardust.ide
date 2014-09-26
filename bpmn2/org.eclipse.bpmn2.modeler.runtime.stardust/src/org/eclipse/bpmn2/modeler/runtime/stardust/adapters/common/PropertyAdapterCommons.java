package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;

/**
 * @author Simon Nikles
 *
 */
public class PropertyAdapterCommons {

	public static AttributeType createAttributeType(String name, String value, String type) {
		AttributeType at = CarnotWorkflowModelFactory.eINSTANCE.createAttributeType();
		at.setName(name);
		at.setValue(value);
		if (type != null && !type.isEmpty())
			at.setType(type);
		return at;
	}

	public static AttributeType findAttributeType(IExtensibleElement element, String name) {
		for (AttributeType at : element.getAttribute()) {
			if (at.getName().equals(name))
				return at;
		}
		return null;
	}

}
