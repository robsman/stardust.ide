/*******************************************************************************
 * Copyright (c) 2011, 2012 Red Hat, Inc. 
 * All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 *
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.runtime.stardust.editors;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustInterfaceSelectionDialog;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class StardustInterfaceSelectionObjectEditor extends TextAndButtonObjectEditor {

	public StardustInterfaceSelectionObjectEditor(AbstractDetailComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}
	
	@Override
	protected Control createControl(Composite composite, String label, int style) {
		super.createControl(composite, label, style);
		// the Text field should be editable
		text.setEditable(true);
		// and change the "Edit" button to a "Browse" to make it clear that
		// an XML type can be selected from the imports 
		defaultButton.setText(Messages.StardustInterfaceSelectionObjectEditor_Browse);
		return text;
	}

	@Override
	protected void buttonClicked(int buttonId) {
    	final StardustInterfaceSelectionDialog dialog = new StardustInterfaceSelectionDialog();
    	dialog.open();
        final IType selectedType = dialog.getIType();
        System.out.println("Selected Type:" + selectedType.getElementName());
        System.out.println("Selected Type Full Qulified Name:" + selectedType.getFullyQualifiedName().toString());
        System.out.println("Selected IType:" + dialog.getIType().toString());
        System.out.println("Selected Methods:" + dialog.getIMethods().toString());
		IMethod[] selectedMethods = dialog.getIMethods();
		for (int i=0; i<dialog.getIMethods().length; ++i)
			System.out.println("selectedMethod " + i + ": " + selectedMethods[i].toString() );

        
	}
}
