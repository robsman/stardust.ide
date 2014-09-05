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
import org.eclipse.bpmn2.modeler.core.utils.ImportUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.IntrinsicJavaAccesspointInfo;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.Messages;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustDataPathProvider;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustInterfaceSelectionDialog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
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
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object.eResource());		
		BasicCommandStack commandStack = (BasicCommandStack) editingDomain.getCommandStack();
		commandStack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
		
    	final StardustInterfaceSelectionDialog dialog = new StardustInterfaceSelectionDialog();
    	dialog.open();
        final IType selectedType = dialog.getIType();
//        System.out.println("Selected Type:" + selectedType.getElementName());
//        System.out.println("Selected Type Full Qulified Name:" + selectedType.getFullyQualifiedName().toString());
//        System.out.println("Selected IType:" + dialog.getIType().toString());
//        System.out.println("Selected Methods:" + dialog.getIMethods().toString());
		final IMethod[] selectedMethods = dialog.getIMethods();
		String oldClsName = null != getText() ? getText() : "";
		String oldMethod = null != methodAttribute.getValue() ? methodAttribute.getValue().toString() : "";
		String newClsName = selectedType.getFullyQualifiedName();
		String newMethod = null;
		try {
			newMethod = IntrinsicJavaAccesspointInfo.encodeMethod(selectedMethods[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (selectedMethods.length <= 0) {
			setValue("");			
			methodAttribute.setValue("");
			resetExistingApp();
			return;
		}
		
		boolean valuesChanged = false;
		if (!oldClsName.equals(newClsName)) valuesChanged = true;
		if (!oldMethod.equals(newMethod)) valuesChanged = true;
		if (valuesChanged) resetExistingApp();
		setValue(newClsName);
		methodAttribute.setValue(newMethod);
		
		for (int i=0; i<dialog.getIMethods().length; ++i)
			System.out.println("selectedMethod " + i + ": " + selectedMethods[i].toString() );
				
//		Display.getDefault().asyncExec( new Runnable() {
//			@Override
//			public void run() {
				
//				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object.eResource());
//				commandStack = (BasicCommandStack) editingDomain.getCommandStack();
//				commandStack.execute(new RecordingCommand(editingDomain) {
//					@Override
//					protected void doExecute() {
						Definitions definitions = ModelUtil.getDefinitions(object.eResource());
						ItemDefinition inputItemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
						inputItemDef.setItemKind(ItemKind.INFORMATION);
						String generateID = ModelUtil.generateID(inputItemDef, object.eResource(), object.eClass().getName());
						inputItemDef.setId(generateID);

						ItemDefinition outputItemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
						outputItemDef.setItemKind(ItemKind.INFORMATION);
						String generateOutputID = ModelUtil.generateID(outputItemDef, object.eResource(), object.eClass().getName());
						outputItemDef.setId(generateOutputID);

						try {
							IntrinsicJavaAccesspointInfo.addInputAccessPointItemDefinitionSchema(selectedMethods[0], inputItemDef);
							IntrinsicJavaAccesspointInfo.addOutputAccessPointItemDefinitionSchema(selectedMethods[0], outputItemDef);
							if (null != inputItemDef.getStructureRef()) {
								DynamicEObjectImpl ref = (DynamicEObjectImpl)inputItemDef.getStructureRef();
								URI uri = ref.eProxyURI();
								EObject wrapper = ModelUtil.createStringWrapper(uri.toString());
								inputItemDef.setStructureRef(wrapper);
							}
							if (null != outputItemDef.getStructureRef()) {
								DynamicEObjectImpl ref = (DynamicEObjectImpl)outputItemDef.getStructureRef();
								URI uri = ref.eProxyURI();
								EObject wrapper = ModelUtil.createStringWrapper(uri.toString());
								outputItemDef.setStructureRef(wrapper);
							}
							
						} catch (ClassNotFoundException | NoSuchMethodException
								| SecurityException | MalformedURLException
								| CoreException e) {
							e.printStackTrace();
						}
						definitions.getRootElements().add(inputItemDef);
						definitions.getRootElements().add(outputItemDef);
						// Fill in values for ImplementationRef in the implementRef Property
						populateBPMN2Values(outputItemDef, inputItemDef);
						
					}
				});

			}
		});
	}
	
	
	private void populateBPMN2Values(ItemDefinition outputItemDef, ItemDefinition inputItemDef) {
		if (this.parent.getBusinessObject() instanceof StardustInterfaceType) {
			Interface interf = (Interface) this.parent.getBusinessObject().eContainer().eContainer();
			interf.setImplementationRef(sdInterface.getStardustApplication());
			Definitions definitions = ModelUtil.getDefinitions(interf);
			if (null != interf.getOperations()) {
				// Create new Operation object and add it to the interface
				interf.getOperations().clear();
				Operation op = Bpmn2Factory.eINSTANCE.createOperation();
				String opId = ModelUtil.setID(op);
				//TODO find a better name for the operation
				op.setName(opId);
				op.setImplementationRef(interf.getImplementationRef());
				// Create inMsg and populate it, add it to the operation
				Message inMsg = Bpmn2Factory.eINSTANCE.createMessage();
				definitions.getRootElements().add(inMsg);
				String inMsgId = ModelUtil.setID(inMsg); 
				inMsg.setName(inMsgId);
				inMsg.setItemRef(inputItemDef);	
				op.setInMessageRef(inMsg);
				// Create inMsg and populate it, add it to the operation				
				Message outMsg = Bpmn2Factory.eINSTANCE.createMessage();				
				definitions.getRootElements().add(outMsg);	
				String outMsgId = ModelUtil.setID(outMsg);
				outMsg.setName(outMsgId);
				outMsg.setItemRef(outputItemDef);	
				op.setOutMessageRef(outMsg);
				// Add newly created operation to the Interface
				interf.getOperations().add(op);
			}
		}
	}
	
	private void resetExistingApp() {
		new StardustApplicationConfigurationCleaner().performResetExistingApp(sdInterface);
//		Display.getDefault().asyncExec( new Runnable() {
//			@Override
//			public void run() {
//				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(sdInterface.eResource());
//				editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
//					@Override
//					protected void doExecute() {
//						new StardustApplicationConfigurationCleaner().performResetExistingApp(sdInterface);
//					}
//				});
//			}
//		});		
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
