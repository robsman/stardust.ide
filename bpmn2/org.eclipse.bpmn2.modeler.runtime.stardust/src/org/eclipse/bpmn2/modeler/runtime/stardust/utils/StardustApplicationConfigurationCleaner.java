package org.eclipse.bpmn2.modeler.runtime.stardust.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * Removes attributes / references generated through {@link StardustApplicationType} config (e.g. item definitions, operations, messages, implementationRef).
 *  
 * @author Simon Nikles
 *
 */
public class StardustApplicationConfigurationCleaner {

	public void performResetExistingApp(StardustInterfaceType sdInterface) {
		
		Interface bpmnInterface = (Interface)(sdInterface.eContainer()).eContainer();
		bpmnInterface.setImplementationRef(null);
		sdInterface.setStardustTrigger(null);
		
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
					EcoreUtil.delete(itemRef);
				}
			}
			if (null != outMessage) {
				ItemDefinition itemRef = outMessage.getItemRef();
				if (null != itemRef) {
					// delete item definition
					EcoreUtil.delete(itemRef);
				}
			}
		}
		bpmnInterface.getOperations().clear();
		
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
