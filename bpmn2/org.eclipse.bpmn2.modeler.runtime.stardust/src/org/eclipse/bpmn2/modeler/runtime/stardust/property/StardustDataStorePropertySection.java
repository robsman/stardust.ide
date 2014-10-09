package org.eclipse.bpmn2.modeler.runtime.stardust.property;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.data.StardustDataStoreDetailComposite;
import org.eclipse.bpmn2.modeler.ui.property.data.DataStorePropertySection;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustDataStorePropertySection extends DataStorePropertySection {

	@Override
	protected AbstractDetailComposite createSectionRoot() {
		return new StardustDataStoreDetailComposite(this);
	}

	@Override
	public AbstractDetailComposite createSectionRoot(Composite parent, int style) {
		return new StardustDataStoreDetailComposite(parent,style);
	}

}
