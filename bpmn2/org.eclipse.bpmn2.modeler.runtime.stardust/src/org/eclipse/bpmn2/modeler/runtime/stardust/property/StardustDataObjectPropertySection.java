package org.eclipse.bpmn2.modeler.runtime.stardust.property;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.StardustDataObjectDetailComposite;
import org.eclipse.bpmn2.modeler.ui.property.data.DataObjectPropertySection;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataObjectPropertySection extends DataObjectPropertySection {
	
	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustDataObjectDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustDataObjectDetailComposite(parent,style);
	}

}
