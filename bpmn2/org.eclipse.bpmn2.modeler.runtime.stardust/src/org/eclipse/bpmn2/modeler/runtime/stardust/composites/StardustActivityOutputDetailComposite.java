package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.tasks.ActivityOutputDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

/**
 * Suppress io details.
 * 
 * @author Simon Nikles
 *
 */
public class StardustActivityOutputDetailComposite extends ActivityOutputDetailComposite {

	public StardustActivityOutputDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustActivityOutputDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
	}
}