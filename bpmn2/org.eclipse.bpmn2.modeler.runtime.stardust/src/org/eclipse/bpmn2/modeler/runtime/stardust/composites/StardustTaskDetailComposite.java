package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.swt.widgets.Composite;

public class StardustTaskDetailComposite extends StardustActivityDetailComposite {

	public StardustTaskDetailComposite(Composite parent, int style) {
		super(parent, style);
	}
	
	public StardustTaskDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

}
