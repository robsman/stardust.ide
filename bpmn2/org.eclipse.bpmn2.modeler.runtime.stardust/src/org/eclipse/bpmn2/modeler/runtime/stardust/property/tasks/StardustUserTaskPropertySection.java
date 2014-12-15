package org.eclipse.bpmn2.modeler.runtime.stardust.property.tasks;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultPropertySection;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.task.StardustUserTaskDetailComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustUserTaskPropertySection extends DefaultPropertySection {

	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustUserTaskDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustUserTaskDetailComposite(parent,style);
	}

}
