package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.camel;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointChangeListener;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointListComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint.AccessPointTypeDetailComposite;
import org.eclipse.swt.widgets.Composite;

public class CamelAccessPointListComposite extends AccessPointListComposite {

	public CamelAccessPointListComposite(Composite parent, boolean isInput, AccessPointChangeListener listener) {
		super(parent, isInput, listener);
	}
	
	@Override
	public AbstractDetailComposite createDetailComposite(@SuppressWarnings("rawtypes") Class eClass, Composite parent, int style) {
		AbstractDetailComposite composite = new AccessPointTypeDetailComposite(parent, (AccessPointChangeListener)this);
		return composite;
	}

}
