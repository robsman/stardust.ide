package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.swt.widgets.Composite;

/**
 * Just to hide io infos. Extensive type hierarchy for future needs (serviceTask->task->activity).   
 * 
 * @author Simon Nikles
 *
 */
public class StardustServiceTaskDetailComposite extends StardustTaskDetailComposite {

	public StardustServiceTaskDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustServiceTaskDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}


}
