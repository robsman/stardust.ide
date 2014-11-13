package org.eclipse.bpmn2.modeler.runtime.stardust.property;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.StardustDefinitionsPropertyComposite;
import org.eclipse.bpmn2.modeler.ui.property.diagrams.DefinitionsPropertySection;
import org.eclipse.swt.widgets.Composite;

public class StardustDefinitionsPropertySection extends DefinitionsPropertySection {

	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustDefinitionsPropertyComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustDefinitionsPropertyComposite(parent,style);
	}

}
