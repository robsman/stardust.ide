package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultListComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.widgets.Composite;

/**
 * Construct the list without controls (= readOnly list) and set Title.
 * 
 * @author Simon Nikles
 *
 */
public class StardustOperationListComposite extends DefaultListComposite {

	public StardustOperationListComposite(Composite parent) {
		super(parent, 0);
	}

	public void bindList(final EObject theobject, final EStructuralFeature thefeature) {
		super.bindList(theobject, thefeature);
		setTitle("Stardust Operation");
		table.setLinesVisible(false);
	}
}
