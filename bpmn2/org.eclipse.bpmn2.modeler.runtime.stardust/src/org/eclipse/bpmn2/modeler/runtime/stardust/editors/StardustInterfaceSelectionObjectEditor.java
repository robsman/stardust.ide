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


import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.ItemKind;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractDetailComposite;
import org.eclipse.bpmn2.modeler.core.merrimac.dialogs.TextAndButtonObjectEditor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.IntrinsicJavaAccesspointInfo;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustInterfaceSelectionDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class StardustInterfaceSelectionObjectEditor extends TextAndButtonObjectEditor {

	private AttributeTypeTextEditor methodAttribute = null;
	private StardustInterfaceType sdInterface = null;
	
	public StardustInterfaceSelectionObjectEditor(AbstractDetailComposite parent, StardustInterfaceType sdInterface, AttributeType object, AttributeTypeTextEditor methodAttribute, EStructuralFeature feature) {
		super(parent, object, feature);
		this.methodAttribute = methodAttribute;
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
    	final StardustInterfaceSelectionDialog dialog = new StardustInterfaceSelectionDialog();
    	dialog.open();
        final IType selectedType = dialog.getIType();
        System.out.println("Selected Type:" + selectedType.getElementName());
        System.out.println("Selected Type Full Qulified Name:" + selectedType.getFullyQualifiedName().toString());
        System.out.println("Selected IType:" + dialog.getIType().toString());
        System.out.println("Selected Methods:" + dialog.getIMethods().toString());
		final IMethod[] selectedMethods = dialog.getIMethods();
		String oldClsName = null != super.getText() ? super.getText() : "";
		String oldMethod = null != methodAttribute.getValue() ? methodAttribute.getValue().toString() : "";
		
		if (selectedMethods.length <= 0) {
			super.setValue("");
			methodAttribute.setValue("");
			resetExistingApp();
			return;
		}
		
		try {
			boolean valuesChanged = false;
			String newClsName = selectedType.getFullyQualifiedName();
			String newMethod = IntrinsicJavaAccesspointInfo.encodeMethod(selectedMethods[0]);
			if (!oldClsName.equals(newClsName)) valuesChanged = true;
			if (!oldMethod.equals(newMethod)) valuesChanged = true;
			if (valuesChanged) resetExistingApp();
			
			super.setValue(newClsName);
			methodAttribute.setValue(newMethod);
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | MalformedURLException | CoreException e1) {
			e1.printStackTrace();
		}
		
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
	
	private void resetExistingApp() {
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(sdInterface.eResource());
				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
					@Override
					protected void doExecute() {
						performResetExistingApp();
					}
				});
			}
		});		
	}
	
	private void performResetExistingApp() {
		Interface bpmnInterface = (Interface)(sdInterface.eContainer()).eContainer();
		Definitions definitions = ModelUtil.getDefinitions(bpmnInterface);
		List<Operation> operations = bpmnInterface.getOperations();
		Message inMessage = null;
		Message outMessage = null;

		for (Operation op : operations) {
			List<BaseElement> referencingElements = findReferences(definitions, op);
			resetReferencingActivities(referencingElements);
			inMessage = op.getInMessageRef();
			outMessage = op.getOutMessageRef();
			if (null != inMessage) {
				ItemDefinition itemRef = inMessage.getItemRef();
				if (null != itemRef) {
					// delete item definition
					//definitions.getRootElements().remove(itemRef);
					EcoreUtil.delete(itemRef);
				}
			}
			if (null != outMessage) {
				ItemDefinition itemRef = outMessage.getItemRef();
				if (null != itemRef) {
					// delete item definition
					//definitions.getRootElements().remove(itemRef);
					EcoreUtil.delete(itemRef);
				}
			}
		}
		bpmnInterface.getOperations().clear();
//		definitions.getRootElements().remove(inMessage);
//		definitions.getRootElements().remove(outMessage);
		
		if (null != inMessage) EcoreUtil.delete(inMessage);
		if (null != outMessage) EcoreUtil.delete(outMessage);
	}	
	
	@SuppressWarnings("unchecked")
	private void resetReferencingActivities(List<BaseElement> referencingActivities) {
		for (BaseElement element : referencingActivities) {
			List<DataInput> dataInputs = new ArrayList<DataInput>();
			List<DataOutput> dataOutputs = new ArrayList<DataOutput>();

			if (element instanceof Activity) {
				InputOutputSpecification ioSpecification = (InputOutputSpecification)((Activity)element).getIoSpecification();
				dataInputs.addAll(ioSpecification.getDataInputs());
				dataOutputs.addAll(ioSpecification.getDataOutputs());				
			}
			if (element instanceof EventDefinition) {
				Event event = (Event)((EventDefinition)element).eContainer();
				if (null != event) {
					try {
						Method getter = event.getClass().getMethod("getDataInputs");
						if (null != getter) {
							dataInputs.addAll((List<DataInput>)getter.invoke(event));
						}
					} catch (Exception e) {}				
					try {
						Method getter = event.getClass().getMethod("getDataOutputs");
						if (null != getter) {
							dataOutputs.addAll((List<DataOutput>)getter.invoke(event));
						}
					} catch (Exception e) {}
				}
			}
			
			// we expect implementing tasks/events to have only one datainput / one dataoutput (or even if there are more) with the same type as the operation-message
			for (DataInput in : dataInputs) {
				in.setItemSubjectRef(null);
			}
			
			for (DataOutput out : dataOutputs) {
				out.setItemSubjectRef(null);
			}			
			try {
				Method getter = element.getClass().getMethod("getOperationRef");
				if (null != getter) {
					Method setter = element.getClass().getMethod("setOperationRef", Operation.class);
					Operation newOp = null;
					setter.invoke(element, newOp);
				}
			} catch (Exception e) {}
		}
	}

	private List<BaseElement> findReferences(Definitions definitions, Operation operation) {
		List<BaseElement> referencingActivities = new ArrayList<BaseElement>();
		TreeIterator<EObject> allContents = definitions.eAllContents();
		while (allContents.hasNext()) {
			EObject o = allContents.next();
			for (EReference reference : o.eClass().getEAllReferences()) {
				if (!reference.isContainment() && !(o instanceof DiagramElement)) {
					if (reference.isMany()) {
						@SuppressWarnings("rawtypes")
						List refList = (List)o.eGet(reference);
						if (refList.contains(operation)) {
							if (o instanceof BaseElement) {
								referencingActivities.add((BaseElement)o);
							}
						}
					}
					else {
						Object referencedObject = o.eGet(reference);
						if (operation.equals(referencedObject)) referencingActivities.add((BaseElement)o);
					}
				}
			}
		}
		return referencingActivities;
	}
}
