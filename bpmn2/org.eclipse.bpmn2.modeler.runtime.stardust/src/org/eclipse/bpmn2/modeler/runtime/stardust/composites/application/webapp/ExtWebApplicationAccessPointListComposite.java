package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.webapp;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
import org.eclipse.swt.widgets.Composite;

public class ExtWebApplicationAccessPointListComposite extends AccessPointListComposite {

	public ExtWebApplicationAccessPointListComposite(Composite parent, boolean isInput, AccessPointChangeListener listener) {
		super(parent, isInput, listener);
	}
	
	@Override
	public AbstractDetailComposite createDetailComposite(@SuppressWarnings("rawtypes") Class eClass, Composite parent, int style) {
		AbstractDetailComposite composite = new ExtWebApplicationAccessPointTypeDetailComposite(parent, (AccessPointChangeListener)this);
		return composite;
	}

}
