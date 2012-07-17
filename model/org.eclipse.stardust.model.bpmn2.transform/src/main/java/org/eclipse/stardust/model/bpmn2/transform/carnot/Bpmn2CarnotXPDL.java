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
package org.eclipse.stardust.model.bpmn2.transform.carnot;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualTrigger;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOrganization;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRole;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.CatchEvent;
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
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.extensions.triggers.timer.TimerTriggerValidator;
import org.eclipse.stardust.engine.extensions.jms.trigger.JMSTriggerValidator;
import org.eclipse.stardust.engine.extensions.mail.trigger.MailTriggerValidator;
import org.eclipse.stardust.model.bpmn2.transform.Transformator;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.defaults.DefaultTypesInitializer;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
/**
 * @author Simon Nikles
 *
 */
public class Bpmn2CarnotXPDL implements Transformator {
	
	private static final String FAIL_NO_PROCESS_DEF = "Stardust Process definition not found"; 
	private static final String FAIL_ELEMENT_CREATION = "Could not create Stardust element";
	private static final String FAIL_ELEMENT_UNSUPPORTED_FEATURE = "Usupported feature: ";
	
	private static final String CONDITION_KEY = "CONDITION";
	private static final String OTHERWISE_KEY = "OTHERWISE";
	
	private static final String EXPRESSION_LANGUAGE_JAVA = "http://www.sun.com/java";


	private ModelType carnotModel = null;

	private List<String> failures = new ArrayList<String>(); 
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	public void createTargetModel(Definitions definitions) {
		carnotModel = newBpmModel()
				.withIdAndName(definitions.getId(), definitions.getName())
				.build();		
		Bpmn2CarnotXPDLExtension.addModelExtensions(definitions, carnotModel);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////TODO REMOVE IF DEFAULTTYPESINITIALIZER IS COMPLETE!!! ////////////////////////////////////////////////////
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
		int eventDefCount = countEventDefinitions(event);
		EventDefinition def = getFirstEventDefinition(event);
		if (eventDefCount > 1) {
			failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "StartEvent - Multiple Event definitions " + event.getId());
			return;
		}
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
		if (processDef != null) { 
			if (def == null) {
				failures.add("StartEvent - no event definition available (" + event.getId() + "). Manual Start is assumed.");
				addManualTrigger(event, container, processDef);				
			} else if (def instanceof MessageEventDefinition) {
				def = event.getEventDefinitions().get(0);
				addMessageTrigger(event, def, container, processDef);
			} else if (def instanceof TimerEventDefinition) {
				def = event.getEventDefinitions().get(0);
				addTimerTrigger(event, def, container, processDef);
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
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
		List<SequenceFlow> incomings = gateway.getIncoming();
		List<SequenceFlow> outgoings = gateway.getOutgoing();
		if (processDef ==null) {
			failures.add(FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");
			return;
		}
		if (incomings == null || outgoings == null) return;
		if (incomings.size() == 0 || outgoings.size() == 0) return;
		
		boolean isFork = outgoings.size() > 1;
		boolean isJoin = incomings.size() > 1;
		
		if (incomings.size() > 1 && outgoings.size() > 1) {
			failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + " n:n exclusive gateway - target model incomplete.");
			return;			
		}		
		if (isFork) {
			ActivityType sourceActivity = findActivity(incomings.get(0).getSourceRef(), container);
			if (sourceActivity != null) sourceActivity.setSplit(JoinSplitType.XOR_LITERAL);
			for (SequenceFlow outgoing : outgoings) {
				if (outgoing.getTargetRef() instanceof Activity) {
					ActivityType targetActivity = findActivity(outgoing.getTargetRef(), container);
					if (targetActivity == null) continue;
					boolean hasCondition = outgoing.getConditionExpression() != null;
					List<Documentation> conditionDoc = hasCondition ? outgoing.getConditionExpression().getDocumentation() : null;
					String condition = hasCondition ? getDescriptionFromDocumentation(conditionDoc) : "";
					String gatewayDoc = getDescriptionFromDocumentation(gateway.getDocumentation());
					addGatewayTransition(gateway, condition, outgoing.getName(), outgoing.getId(), gatewayDoc, sourceActivity, targetActivity, container, processDef);
				} else {
					// TODO 
					failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + " exclusive gateway target other than activity - target model incomplete.");
					continue;
				}
			}
		} else if (isJoin) {
			ActivityType targetActivity = findActivity(outgoings.get(0).getTargetRef(), container);
			if (targetActivity != null) targetActivity.setJoin(JoinSplitType.XOR_LITERAL);
			for (SequenceFlow incoming : incomings) {
				if (incoming.getSourceRef() instanceof Activity) {
					ActivityType sourceActivity = findActivity(incoming.getSourceRef(), container);
					if (sourceActivity == null) continue;					
					addGatewayTransition(gateway, "", incoming.getId(), incoming.getId(), getDescriptionFromDocumentation(gateway.getDocumentation()), sourceActivity, targetActivity, container, processDef);
				} else {
					// TODO 
					failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + " exclusive gateway source other than activity - target model incomplete.");
					continue;
				}			
			}
		}		
	}

	public void addPartnerEntity(PartnerEntity entity) {
		newOrganization(carnotModel)
			.withIdAndName(entity.getId(), entity.getName())
			.withDescription(getDescriptionFromDocumentation(entity.getDocumentation()))
			.build();
	}
	
	public void addProcess(Process process) {
		logger.debug("addProcess " + process);
		List<Documentation> docs = process.getDocumentation();
		String processDescription = getDescriptionFromDocumentation(docs);		
		newProcessDefinition(carnotModel)
			.withIdAndName(process.getId(), process.getName())
			.withDescription(processDescription)
			.build();
	}

	public void addSequenceFlow(SequenceFlow seq, FlowElementsContainer container) {
		logger.debug("addSequenceFlow " + seq);
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
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
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
		if (processDef != null) {
			List<Documentation> docs = subprocess.getDocumentation();
			String processDescription = getDescriptionFromDocumentation(docs);
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

	public void addUserTask(UserTask task, FlowElementsContainer container) {		
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
		if (processDef != null) {
			String descr = getDescriptionFromDocumentation(task.getDocumentation());
			ActivityType activity = newManualActivity(processDef)
					.withIdAndName(task.getId(), task.getName())
					.withDescription(descr)
					.build();			
			Bpmn2CarnotXPDLExtension.addUserTaskExtensions(task, activity);
			
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
	
	private void addActivityToActivityTransition(SequenceFlow seq, FlowNode sourceNode, FlowNode targetNode, FlowElementsContainer container, ProcessDefinitionType processDef) {
		if (processDef != null) {			
			ActivityType sourceActivity = findActivity(sourceNode, container);
			ActivityType targetActivity = findActivity(targetNode, container);
			if (sourceActivity != null && targetActivity != null) {
				String documentation = getDescriptionFromDocumentation(seq.getDocumentation());
				TransitionType transition = createTransition(seq.getId(), seq.getName(), documentation, sourceActivity, targetActivity);
				if (seq.getConditionExpression() != null) {
					if (seq.getConditionExpression() instanceof FormalExpression) {
						setSequenceFormalCondition(transition, (FormalExpression)seq.getConditionExpression());
					} else if (seq.getConditionExpression().getDocumentation() != null
							&& seq.getConditionExpression().getDocumentation().get(0) != null
							&& !seq.getConditionExpression().getDocumentation().get(0).getText().equals("")) {
						setSequenceInformalCondition(transition, seq.getConditionExpression().getDocumentation().get(0).getText());
					} else {
						setSequenceTrueCondition(transition);
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
	
	private void addGatewayTransition(ExclusiveGateway gateway, String condition, String name, String Id, String documentation, ActivityType sourceActivity, ActivityType targetActivity, FlowElementsContainer container, ProcessDefinitionType processDef) {
		logger.info("addGatewayTransition from " + sourceActivity.getName() + " to " + targetActivity.getName()); 
		if (processDef != null) {			
			if (sourceActivity != null && targetActivity != null) {
				TransitionType transition = createTransition(Id, name, documentation, sourceActivity, targetActivity);
				if (gateway.getDefault() != null && gateway.getDefault().getTargetRef().getId().equals(targetActivity.getId())) {
					setSequenceOtherwiseCondition(transition);
				} else if (!condition.equals("")) {
					transition.setCondition(condition);
				}
				processDef.getTransition().add(transition);
			} else {
				failures.add("No valid source and target for gateway sequence: " + Id);
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
		Bpmn2CarnotXPDLExtension.addStartEventExtensions(event, trigger);
	}

	private void addMessageTrigger(StartEvent event, EventDefinition def, FlowElementsContainer container, ProcessDefinitionType processDef) {
		logger.debug("addMessageTrigger (JMS) " + event);
		TriggerTypeType triggerType = Bpmn2CarnotXPDLExtension.getMessageStartEventTriggerType(event, carnotModel);
		if (triggerType != null) {
			TriggerType trigger = AbstractElementBuilder.F_CWM.createTriggerType();
	        trigger.setType(triggerType);
			trigger.setId(event.getId());
			trigger.setName(event.getName());	        
	        Bpmn2CarnotXPDLExtension.addMessageStartEventExtensions(event, trigger);
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
	        Bpmn2CarnotXPDLExtension.addTimerStartEventExtensions(event, trigger);
	        processDef.getTrigger().add(trigger);
		} else {
			failures.add(FAIL_ELEMENT_CREATION + "(Start event: " + event.getId() + " - trigger type + " + PredefinedConstants.JMS_TRIGGER + " not found)");
		}
	}

	public void addParallelGateway(ParallelGateway gateway, FlowElementsContainer container) {
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
	
	private int countEventDefinitions(CatchEvent event) {
		if (event.getEventDefinitions() != null) return event.getEventDefinitions().size();
		return 0;
	}
	
	private TransitionType createTransition(String id, String name, String documentation, ActivityType sourceActivity, ActivityType targetActivity) {
		DescriptionType descrType = getDescription(documentation);
		TransitionType transition = AbstractElementBuilder.F_CWM.createTransitionType();
		transition.setFrom(sourceActivity);				
		transition.setTo(targetActivity);			
		transition.setId(id);
		transition.setName(name);
		transition.setDescription(descrType);
		return transition;
	}
	
	private ActivityType findActivity(FlowNode node, FlowElementsContainer container) {
		String nodeId = node != null ? node.getId() : null;
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
		if (processDef != null && nodeId != null) {
			for (ActivityType activity : processDef.getActivity()) {
				if (activity.getId().equals(nodeId)) return activity; 
			}
		}
		return null;
	}

	private ProcessDefinitionType findProcessDefinition(String id) {
		EList<ProcessDefinitionType> processDefs = carnotModel.getProcessDefinition();
		for (ProcessDefinitionType processDef : processDefs) {
			if (processDef.getId().equals(id)) return processDef;
		}
		return null;
	}

	private IModelParticipant findResourceType(String id) {
		EList<RoleType> roles = carnotModel.getRole();
		EList<OrganizationType> orgs = carnotModel.getOrganization();
		for (RoleType role : roles) {
			if (role.getId().equals(id)) return role;
		}
		for (OrganizationType org : orgs) {
			if (org.getId().equals(id)) return org;
		}
		return null;
	}

	private DescriptionType getDescription(String description) {
        DescriptionType descriptor = AbstractElementBuilder.F_CWM.createDescriptionType();
        XpdlModelUtils.setCDataString(descriptor.getMixed(), description, true);
        return descriptor;
	}

	private String getDescriptionFromDocumentation(List<Documentation> documentation) {
		String description = "";
		for (Documentation doc : documentation) {
			description = description.concat(doc.getText());
		}
		return description;
	}

	private EventDefinition getFirstEventDefinition(CatchEvent event) {
		if (countEventDefinitions(event) > 0) {
			return event.getEventDefinitions().get(0);
		}
		return null;
	}

	private void setSequenceFormalCondition(TransitionType transition, FormalExpression formalExpression) {
		if (formalExpression.getLanguage().equals(EXPRESSION_LANGUAGE_JAVA)) {
				transition.setCondition(CONDITION_KEY);
				XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
				ModelUtils.setCDataString(expression.getMixed(), formalExpression.getBody(), true);
				transition.setExpression(expression);
		} else {
			String expr = formalExpression.getLanguage() 
					+ " \n" + formalExpression.getBody();
			setSequenceInformalCondition(transition, expr);
			failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "(Sequence - " + transition.getId() + " - Expression language (" + formalExpression.getLanguage() + ") not supported.");			
		}		
	}
	
	private void setSequenceInformalCondition(TransitionType transition, String string) {
		DescriptionType descrType = transition.getDescription();
		String val = descrType.getMixed().getValue(CarnotWorkflowModelPackage.DESCRIPTION_TYPE__MIXED).toString();
		descrType.getMixed().setValue(CarnotWorkflowModelPackage.DESCRIPTION_TYPE__MIXED, val + " " + string);
		transition.setDescription(descrType);
	}

	private void setSequenceOtherwiseCondition(TransitionType transition) {
		transition.setCondition(OTHERWISE_KEY);
	}
	
	private void setSequenceTrueCondition(TransitionType transition) {
		transition.setCondition(CONDITION_KEY);
		XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
		ModelUtils.setCDataString(expression.getMixed(), "true", true);
		transition.setExpression(expression);
	}
	
	private void setTaskPerformer(ActivityType activity, ResourceRole role, UserTask task, FlowElementsContainer container) {
		if (role.getResourceAssignmentExpression() != null) failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "(RESOURCE ASSIGNMENT EXPRESSION NOT IMPLEMENTED)");	 
		if (role.getResourceParameterBindings() != null) failures.add(FAIL_ELEMENT_UNSUPPORTED_FEATURE + "RESOURCE PARAMETER BINDINGS NOT IMPLEMENTED");
	
		if (role.eIsProxy()) role = Bpmn2ProxyResolver.resolveRoleProxy(role, container);		
		if (role.getResourceRef() != null) {
			Resource resource = role.getResourceRef();
			if (resource.eIsProxy()) resource = Bpmn2ProxyResolver.resolveResourceProxy(resource, container);
			if (resource != null) {
				IModelParticipant resourceType = findResourceType(resource.getId());
				if (resourceType==null) {
					String descr = getDescriptionFromDocumentation(resource.getDocumentation());
					resourceType = newRole(carnotModel).withIdAndName(resource.getId(), resource.getName()).withDescription(descr).build();
				}
				activity.setPerformer(resourceType);
			}
		}
	}

}
