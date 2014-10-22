package org.eclipse.bpmn2.modeler.runtime.stardust.property;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultPropertySection;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustCatchEventDetailComposite;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustCatchEventPropertySection extends DefaultPropertySection {

	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustCatchEventDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustCatchEventDetailComposite(parent, style);
	}

}
