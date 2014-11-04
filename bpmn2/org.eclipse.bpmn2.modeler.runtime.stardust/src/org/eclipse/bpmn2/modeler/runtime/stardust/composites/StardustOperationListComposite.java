/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
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
