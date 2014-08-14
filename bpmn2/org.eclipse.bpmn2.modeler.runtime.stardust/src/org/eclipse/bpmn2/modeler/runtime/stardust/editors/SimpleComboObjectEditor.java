/*******************************************************************************
 * Copyright (c) 2011, 2012, 2013, 2014 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.runtime.stardust.editors;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.ComboObjectEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SimpleComboObjectEditor extends ComboObjectEditor {
	private Hashtable<String, Object> choices = new Hashtable<String, Object>();

	public SimpleComboObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature,
			String choices[]) {
		super(parent, object, feature);
		for (String s : choices) {
			this.choices.put(s, s);
		}
	}

	@Override
	protected Hashtable<String, Object> getChoiceOfValues(EObject object, EStructuralFeature feature) {
		return choices;
	}
}