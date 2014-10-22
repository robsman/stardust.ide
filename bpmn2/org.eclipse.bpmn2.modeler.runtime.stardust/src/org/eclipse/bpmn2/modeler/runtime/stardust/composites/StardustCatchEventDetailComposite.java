package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

public class StardustCatchEventDetailComposite extends AbstractDetailComposite {

	public StardustCatchEventDetailComposite(
			AbstractBpmn2PropertySection section) {
		super(section);
	}

	public StardustCatchEventDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void createBindings(EObject be) {
		bindList(be,"dataOutputs"); //$NON-NLS-1$
	}

}
