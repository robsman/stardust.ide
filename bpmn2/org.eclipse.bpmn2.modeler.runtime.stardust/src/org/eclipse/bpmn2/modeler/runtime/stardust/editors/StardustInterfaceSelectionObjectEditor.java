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

import java.net.MalformedURLException;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.ItemKind;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.IntrinsicJavaAccesspointInfo;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustInterfaceSelectionDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

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
		final IMethod[] selectedMethods = dialog.getIMethods();
		if (selectedMethods.length <= 0) return;
		for (int i=0; i<dialog.getIMethods().length; ++i)
			System.out.println("selectedMethod " + i + ": " + selectedMethods[i].toString() );
				
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object.eResource());
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						Definitions definitions = ModelUtil.getDefinitions(object.eResource());
						ItemDefinition itemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
						itemDef.setItemKind(ItemKind.INFORMATION);
						String generateID = ModelUtil.generateID(itemDef, object.eResource(), object.eClass().getName());
						itemDef.setId(generateID);
						try {
							IntrinsicJavaAccesspointInfo.addAccessPointItemDefinitionSchema(selectedMethods[0], itemDef);
						} catch (ClassNotFoundException | NoSuchMethodException
								| SecurityException | MalformedURLException
								| CoreException e) {
							e.printStackTrace();
						}
						definitions.getRootElements().add(itemDef);
					}
				});
			}
		});
	}
}
