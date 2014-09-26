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
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustDataPathProvider;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustInterfaceSelectionDialog;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class StardustInterfaceSelectionObjectEditor extends TextAndButtonObjectEditor {

	private StardustInterfaceType sdInterface = null;

	public StardustInterfaceSelectionObjectEditor(AbstractDetailComposite parent, StardustInterfaceType sdInterface, AttributeType object, EStructuralFeature feature) {
		super(parent, object, feature);
		this.sdInterface = sdInterface;
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
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object.eResource());
		BasicCommandStack commandStack = (BasicCommandStack) editingDomain.getCommandStack();
		commandStack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				final StardustInterfaceSelectionDialog dialog = new StardustInterfaceSelectionDialog();
				if (StardustInterfaceSelectionDialog.CANCEL == dialog.open()) return;
				final IType selectedType = dialog.getIType();
				String oldClsName = null != getText() ? getText() : "";
				String newClsName = selectedType.getFullyQualifiedName();

				boolean valuesChanged = false;
				if (!oldClsName.equals(newClsName))
					valuesChanged = true;
				if (valuesChanged) {
					if (null != sdInterface.getStardustApplication()) {
						AttributeType methodAttribute = PropertyAdapterCommons.findAttributeType(sdInterface.getStardustApplication(), "carnot:engine:methodName");
						if (null != methodAttribute) methodAttribute.setValue("");
						AttributeType constructorAttribute = PropertyAdapterCommons.findAttributeType(sdInterface.getStardustApplication(), "carnot:engine:constructorName");
						if (null != constructorAttribute) constructorAttribute.setValue("");
					}
					StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdInterface);
					parent.refresh();
				}
				setValue(newClsName);
			}
		});
	}
		
}
