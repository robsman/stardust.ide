/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newApplicationActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualTrigger;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newModelDiagram;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOrganization;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRole;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.InputOutputBinding;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.extensions.triggers.timer.TimerTriggerValidator;
import org.eclipse.stardust.engine.extensions.jms.trigger.JMSTriggerValidator;
import org.eclipse.stardust.engine.extensions.mail.trigger.MailTriggerValidator;
import org.eclipse.stardust.model.bpmn2.transform.Transformator;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.Gateway2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.Sequence2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.defaults.DefaultTypesInitializer;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
/**
 * @author Simon Nikles
 *
 */
public class Bpmn2StardustXPDL implements Transformator {
	
	public static final String FAIL_NO_PROCESS_DEF = "Stardust Process definition not found"; 
	public static final String FAIL_ELEMENT_CREATION = "Could not create Stardust element";
	public static final String FAIL_ELEMENT_UNSUPPORTED_FEATURE = "Usupported feature: ";
	
	public ModelType carnotModel = null;

	private List<String> failures = new ArrayList<String>(); 
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private CarnotModelQuery query;
	private BpmnModelQuery bpmnquery;
	

	public void createTargetModel(Definitions definitions) {
		carnotModel = newBpmModel()
				.withIdAndName(definitions.getId(), definitions.getName())
				.build();		
		Bpmn2StardustXPDLExtension.addModelExtensions(definitions, carnotModel);
		Bpmn2StardustXPDLExtension.addModelExtensionDefaults(definitions, carnotModel);
		
		query = new CarnotModelQuery(carnotModel);
		bpmnquery = new BpmnModelQuery();

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////TODO REMOVE IF DEFAULTTYPESINITIALIZER IS COMPLETE (OR WHAT IS ABOUT THESE TYPES?)!!! ////////////////////
		DefaultTypesInitializer initializer = new DefaultTypesInitializer();
		initializer.initializeTriggerType(carnotModel, PredefinedConstants.JMS_TRIGGER, "JMS Trigger",
		false, JMSTriggerValidator.class);
		initializer.initializeTriggerType(carnotModel, PredefinedConstants.MAIL_TRIGGER, "Mail Trigger",
		false, MailTriggerValidator.class);
		initializer.initializeTriggerType(carnotModel, PredefinedConstants.TIMER_TRIGGER, "Timer Trigger",
		false, TimerTriggerValidator.class);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	}
	
	public ModelType getTargetModel() {
		return carnotModel;
	}

	public List<String> getTransformationMessages() {
		return failures;
	}

	public void serializeTargetModel(String outputPath) {
		logger.debug("serializeTargetModel " + outputPath);
		FileOutputStream fis;
		try {
			fis = new FileOutputStream(new File(outputPath));
			fis.write(XpdlModelIoUtils.saveModel(carnotModel));
			fis.close();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void addStartEvent(StartEvent event, FlowElementsContainer container) {
		logger.debug("addStartEvent " + event);
		int eventDefCount = bpmnquery.countEventDefinitions(event);
		EventDefinition def = bpmnquery.getFirstEventDefinition(event);
		if (eventDefCount > 1) {
			failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "StartEvent - Multiple Event definitions " + event.getId());
			return;
		}
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		if (processDef != null) { 
			if (def == null) {
				failures.add("StartEvent - no event definition available (" + event.getId() + "). Manual Start is assumed.");
				addManualTrigger(event, container, processDef);				
			} else if (def instanceof MessageEventDefinition) {
				//def = event.getEventDefinitions().get(0);
				addMessageTrigger(event, (MessageEventDefinition)def, container, processDef);
			} else if (def instanceof TimerEventDefinition) {
				//def = event.getEventDefinitions().get(0);
				addTimerTrigger(event, (TimerEventDefinition)def, container, processDef);
			} else {
				failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "StartEvent " + event.getId() + " EventDefinition " + def.getClass().getName());
				return;
			}

		} else {
			failures.add(FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");
		}
	}
	
	public void addExclusiveGateway(ExclusiveGateway gateway, FlowElementsContainer container) {
		logger.info("addExclusiveGateway" + gateway.getId() + " " + gateway.getName());
		new Gateway2Stardust(carnotModel,failures).addExclusiveGateway(gateway, container);
	}

	public void addParallelGateway(ParallelGateway gateway, FlowElementsContainer container) {
		logger.info("addParallelGateway" + gateway.getId() + " " + gateway.getName());
		new Gateway2Stardust(carnotModel,failures).addParallelGateway(gateway, container);		
	}
	
	public void addPartnerEntity(PartnerEntity entity) {
		newOrganization(carnotModel)
			.withIdAndName(entity.getId(), entity.getName())
			.withDescription(DocumentationTool.getDescriptionFromDocumentation(entity.getDocumentation()))
			.build();
	}
	
	public void addProcess(Process process) {
		logger.debug("addProcess " + process);
		List<Documentation> docs = process.getDocumentation();
		String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);		

		ProcessDefinitionType def =
			newProcessDefinition(carnotModel)
				.withIdAndName(process.getId(), process.getName())
				.withDescription(processDescription)
				.build();

			newModelDiagram(carnotModel).forProcess(def).build();
	}

	public void addSequenceFlow(SequenceFlow seq, FlowElementsContainer container) {
		logger.debug("addSequenceFlow " + seq);
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		FlowNode sourceNode = seq.getSourceRef();
		FlowNode targetNode = seq.getTargetRef();
		if (processDef != null) {			
			if (sourceNode instanceof StartEvent || targetNode instanceof EndEvent) return;
			if (sourceNode instanceof Activity && targetNode instanceof Activity) {
				addActivityToActivityTransition(seq, sourceNode, targetNode, container, processDef);
			}
		} else {
			failures.add(FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");			
		}
	}

	public void addSubProcess(SubProcess subprocess, FlowElementsContainer container) {
		logger.info("addSubProcess" + subprocess.getId() + " " + subprocess.getName());
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		if (processDef != null) {
			List<Documentation> docs = subprocess.getDocumentation();
			String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);
			ProcessDefinitionType implProcessDef = newProcessDefinition(carnotModel)
					.withIdAndName(subprocess.getId(), subprocess.getName())
					.withDescription(processDescription).build();
			ActivityType activity = newSubProcessActivity(processDef)
					.withIdAndName(subprocess.getId(), subprocess.getName())
					.withDescription(processDescription)
					.build();
			activity.setImplementationProcess(implProcessDef);
		} else {
			failures.add(FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");			
		}
	}

	public void addAbstractTask(Task task, FlowElementsContainer container) {
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());
		newRouteActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr)
				.build();
	}

	public void addUserTask(UserTask task, FlowElementsContainer container) {		
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		if (processDef != null) {
			String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());
			ActivityType activity = newManualActivity(processDef)
					.withIdAndName(task.getId(), task.getName())
					.withDescription(descr)
					.build();		
			
			Bpmn2StardustXPDLExtension.addUserTaskExtensions(task, activity);
			
			List<ResourceRole> resources = task.getResources();
			for (ResourceRole role : resources) {
				if (role instanceof Performer) {
					setTaskPerformer(activity, role, task, container);
				}
			}
		} else {
			failures.add(FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");			
		} 
		
	}
	
	public void addServiceTask(ServiceTask task, FlowElementsContainer container) {
		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
		if (processDef != null) {
			String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());
			ActivityType activity = newApplicationActivity(processDef)
					.withIdAndName(task.getId(), task.getName())
					.withDescription(descr)
					.build();			
			// TODO StardustServiceTaskType (Extensions)
		} else {
			failures.add(FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");			
		} 		
	}

	
	private void addActivityToActivityTransition(SequenceFlow seq, FlowNode sourceNode, FlowNode targetNode, FlowElementsContainer container, ProcessDefinitionType processDef) {
		if (processDef != null) {			
			ActivityType sourceActivity = query.findActivity(sourceNode, container);
			ActivityType targetActivity = query.findActivity(targetNode, container);
			if (sourceActivity != null && targetActivity != null) {
				String documentation = DocumentationTool.getDescriptionFromDocumentation(seq.getDocumentation());
				TransitionType transition = Sequence2Stardust.createTransition(seq.getId(), seq.getName(), documentation, processDef, sourceActivity, targetActivity);
				if (seq.getConditionExpression() != null) {
					if (seq.getConditionExpression() instanceof FormalExpression) {
						Sequence2Stardust.setSequenceFormalCondition(transition, (FormalExpression)seq.getConditionExpression(), failures);
					} else if (seq.getConditionExpression().getDocumentation() != null
							&& seq.getConditionExpression().getDocumentation().get(0) != null
							&& !seq.getConditionExpression().getDocumentation().get(0).getText().equals("")) {
						Sequence2Stardust.setSequenceInformalCondition(transition, seq.getConditionExpression().getDocumentation().get(0).getText());
					} else {
						Sequence2Stardust.setSequenceTrueCondition(transition);
					}
				}
				// TODO transition.setForkOnTraversal()				
				processDef.getTransition().add(transition);
			} else {
				failures.add("No valid source and target for sequence flow: " + seq.getId() + " sourceRef " + seq.getSourceRef() + " targetRef " + seq.getTargetRef());
			}
		} else {
			failures.add(FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");			
		}
	}
	
	private void addManualTrigger(StartEvent event, FlowElementsContainer container, ProcessDefinitionType processDef) {
		logger.debug("addManualTrigger " + event);
		TriggerType trigger = newManualTrigger(processDef)
			.withIdAndName(event.getId(), event.getName())
			.build();		
		Bpmn2StardustXPDLExtension.addStartEventExtensions(event, trigger);
	}

	private void addMessageTrigger(StartEvent event, MessageEventDefinition def, FlowElementsContainer container, ProcessDefinitionType processDef) {
		logger.debug("addMessageTrigger (JMS) " + event);
		TriggerTypeType triggerType = Bpmn2StardustXPDLExtension.getMessageStartEventTriggerType(event, carnotModel);
		if (triggerType != null) {
			TriggerType trigger = AbstractElementBuilder.F_CWM.createTriggerType();
	        trigger.setType(triggerType);
			trigger.setId(event.getId());
			trigger.setName(event.getName());	        
	        Bpmn2StardustXPDLExtension.addMessageStartEventExtensions(event, trigger);
	        processDef.getTrigger().add(trigger);
		} else {
			failures.add(FAIL_ELEMENT_CREATION + "(Start event: " + event.getId() + " - trigger type + " + PredefinedConstants.JMS_TRIGGER + " not found)");
		}
	}
	
	private void addTimerTrigger(StartEvent event, EventDefinition def, FlowElementsContainer container, ProcessDefinitionType processDef) {
		logger.debug("addTimerTrigger " + event);
		TriggerTypeType triggerType = XpdlModelUtils.findElementById(carnotModel.getTriggerType(), PredefinedConstants.TIMER_TRIGGER);
		if (triggerType != null) {
			TriggerType trigger = AbstractElementBuilder.F_CWM.createTriggerType();
	        trigger.setType(triggerType);			
			trigger.setId(event.getId());
			trigger.setName(event.getName());
			Bpmn2StardustXPDLExtension.addTimerStartEventExtensions(event, trigger);
	        processDef.getTrigger().add(trigger);
		} else {
			failures.add(FAIL_ELEMENT_CREATION + "(Start event: " + event.getId() + " - trigger type + " + PredefinedConstants.JMS_TRIGGER + " not found)");
		}
	}
	
	public void addParticipant(Participant participant, Process process) {
	}

	public void addLane(Lane lane, LaneSet laneset, Lane parentLane, FlowElementsContainer container) {

	}
	
	public void addIOBinding(List<InputOutputBinding> ioBinding, FlowElementsContainer container) {

	}
	
	public void addDataObject(DataObject flowElement, FlowElementsContainer container) {

		
	}

	public void addDataObjectReference(DataObjectReference flowElement, FlowElementsContainer container) {

		
	}

	public void addDataStoreReference(DataStoreReference flowElement, FlowElementsContainer container) {

		
	}

	public void addEndEvent(EndEvent event, FlowElementsContainer container) {

		
	}	
	
	@SuppressWarnings("unused")
	private static DescriptionType getDescription(String description) {
        DescriptionType descriptor = AbstractElementBuilder.F_CWM.createDescriptionType();
        XpdlModelUtils.setCDataString(descriptor.getMixed(), description, true);
        return descriptor;
	}

	private void setTaskPerformer(ActivityType activity, ResourceRole role, UserTask task, FlowElementsContainer container) {
		if (role.getResourceAssignmentExpression() != null) failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "(RESOURCE ASSIGNMENT EXPRESSION NOT IMPLEMENTED)");	 
		if (role.getResourceParameterBindings() != null) failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "RESOURCE PARAMETER BINDINGS NOT IMPLEMENTED");
	
		if (role.eIsProxy()) role = Bpmn2ProxyResolver.resolveRoleProxy(role, container);		
		if (role.getResourceRef() != null) {
			Resource resource = role.getResourceRef();
			if (resource.eIsProxy()) resource = Bpmn2ProxyResolver.resolveResourceProxy(resource, container);
			if (resource != null) {
				IModelParticipant resourceType = query.findResourceType(resource.getId());
				if (resourceType==null) {
					String descr = DocumentationTool.getDescriptionFromDocumentation(resource.getDocumentation());
					resourceType = newRole(carnotModel).withIdAndName(resource.getId(), resource.getName()).withDescription(descr).build();
				}
				activity.setPerformer(resourceType);
			}
		}
	}

}
