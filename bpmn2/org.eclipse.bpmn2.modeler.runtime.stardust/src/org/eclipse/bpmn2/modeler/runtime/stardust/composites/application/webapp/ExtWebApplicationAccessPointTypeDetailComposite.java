package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.webapp;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointTypeDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AcessPointDataTypes;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class ExtWebApplicationAccessPointTypeDetailComposite extends AccessPointTypeDetailComposite {

	public ExtWebApplicationAccessPointTypeDetailComposite(Composite parent, AccessPointChangeListener listener) {
		super(parent, listener);
	}
	
	protected Hashtable<String, Object> getDatatypeComboChoice() {
		Hashtable<String, Object> choices = new Hashtable<String, Object>();
		choices.put(AcessPointDataTypes.PRIMITIVE_TYPE.getDisplayName(), AcessPointDataTypes.PRIMITIVE_TYPE.getKey());
		choices.put(AcessPointDataTypes.STRUCT_TYPE.getDisplayName(), AcessPointDataTypes.STRUCT_TYPE.getKey());		
		return choices;
	}

}