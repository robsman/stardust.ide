package org.eclipse.bpmn2.modeler.runtime.stardust.property;

import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultPropertySection;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustServiceTaskDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustServiceTaskPropertySection extends DefaultPropertySection {
	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustServiceTaskDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustServiceTaskDetailComposite(parent,style);
	}
	
	@Override
	public boolean appliesTo(EObject element) {
		return element instanceof ServiceTask;
	}

}
