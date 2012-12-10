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

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newBpmModel;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newOrganization;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InputOutputBinding;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.extensions.triggers.timer.TimerTriggerValidator;
import org.eclipse.stardust.engine.extensions.jms.trigger.JMSTriggerValidator;
import org.eclipse.stardust.engine.extensions.mail.trigger.MailTriggerValidator;
import org.eclipse.stardust.model.bpmn2.transform.Transformator;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.ServiceTask2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.UserTask2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.Resource2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.Gateway2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.ProcessStartConfigurator;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.RoutingSequenceFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.SequenceFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.Data2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.IntermediateAndEndEventDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.StartEventDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.TaskDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.EndEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.IntermediateEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.StartEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.process.Process2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.process.Subprocess2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.service.Interface2StardustApplication;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.defaults.DefaultTypesInitializer;
import org.eclipse.stardust.model.xpdl.builder.model.BpmPackageBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.ImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
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

    public void createTargetModel(Definitions definitions) {
    	logger.info("createTargetModel " + definitions.getName());
        carnotModel = newBpmModel()
                .withIdAndName(definitions.getId(), definitions.getName())
                .build();
        Bpmn2StardustXPDLExtension.addModelExtensions(definitions, carnotModel);
        Bpmn2StardustXPDLExtension.addModelExtensionDefaults(definitions, carnotModel);
        query = new CarnotModelQuery(carnotModel);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////TODO REMOVE IF DEFAULTTYPESINITIALIZER IS COMPLETE (OR WHAT IS ABOUT THESE TYPES?)!!! ////////////////////
        DefaultTypesInitializer initializer = new DefaultTypesInitializer();
//FIXME (?) DefaultTypesInitializer.initializeTriggerType checks for already existing APPLICATION-TYPE by name - JMS_TRIGGER has the same value (name) as jms application...
//        initializer.initializeTriggerType(carnotModel, PredefinedConstants.JMS_TRIGGER, "JMS Trigger",
//        false, JMSTriggerValidator.class);
//        model.getTriggerType().add(typeDef);
        initializer.initializeTriggerType(carnotModel, PredefinedConstants.MAIL_TRIGGER, "Mail Trigger",
        false, MailTriggerValidator.class);
        initializer.initializeTriggerType(carnotModel, PredefinedConstants.TIMER_TRIGGER, "Timer Trigger",
        false, TimerTriggerValidator.class);
        initializer.initializeInteractionContextTypes(carnotModel);

        if (null == XpdlModelUtils.findElementById(carnotModel.getTriggerType(), PredefinedConstants.JMS_TRIGGER)) {
	        TriggerTypeType typeDef = BpmPackageBuilder.F_CWM.createTriggerTypeType();
	        typeDef.setId(PredefinedConstants.JMS_TRIGGER);
	        typeDef.setName("JMS Trigger");
	        typeDef.setPullTrigger(false);
	        typeDef.setIsPredefined(true);
	        AttributeUtil.setAttribute(typeDef, PredefinedConstants.VALIDATOR_CLASS_ATT, JMSTriggerValidator.class.getName());
	        carnotModel.getTriggerType().add(typeDef);
    	}

        if (null == XpdlModelUtils.findElementById(carnotModel.getApplicationContextType(), PredefinedConstants.APPLICATION_CONTEXT))
        {
           ApplicationContextTypeType typeDef = BpmPackageBuilder.F_CWM.createApplicationContextTypeType();
           typeDef.setId(PredefinedConstants.APPLICATION_CONTEXT);
           typeDef.setName("Noninteractive Application Context");
           typeDef.setIsPredefined(true);

           typeDef.setHasApplicationPath(true);
           typeDef.setHasMappingId(false);
           carnotModel.getApplicationContextType().add(typeDef);
        }
        if (null == XpdlModelUtils.findElementById(carnotModel.getApplicationContextType(), PredefinedConstants.JSF_CONTEXT))
        {
           ApplicationContextTypeType typeDef = BpmPackageBuilder.F_CWM.createApplicationContextTypeType();
           typeDef.setId(PredefinedConstants.JSF_CONTEXT);
           typeDef.setName("JSF Application");
           typeDef.setIsPredefined(true);
           typeDef.setHasApplicationPath(true);
           typeDef.setHasMappingId(false);
           carnotModel.getApplicationContextType().add(typeDef);
        }

        EventActionTypeType scheduleActionType = CarnotWorkflowModelFactory.eINSTANCE.createEventActionTypeType();
        scheduleActionType.setId(PredefinedConstants.SCHEDULE_ACTIVITY_ACTION);
        scheduleActionType.setIsPredefined(true);
        scheduleActionType.setActivityAction(true);

        EventActionTypeType completeActionType = CarnotWorkflowModelFactory.eINSTANCE.createEventActionTypeType();
        completeActionType.setId(PredefinedConstants.COMPLETE_ACTIVITY_ACTION);
        completeActionType.setIsPredefined(true);
        completeActionType.setActivityAction(true);

        EventActionTypeType abortActionType = CarnotWorkflowModelFactory.eINSTANCE.createEventActionTypeType();
        abortActionType.setId(PredefinedConstants.ABORT_ACTIVITY_ACTION);
        abortActionType.setIsPredefined(true);
        abortActionType.setActivityAction(true);

        EventActionTypeType setDataActionType = CarnotWorkflowModelFactory.eINSTANCE.createEventActionTypeType();
        setDataActionType.setId(PredefinedConstants.SET_DATA_ACTION);
        setDataActionType.setIsPredefined(true);
        setDataActionType.setActivityAction(true);

        EventConditionTypeType timerEventHandlerType = CarnotWorkflowModelFactory.eINSTANCE.createEventConditionTypeType();
        timerEventHandlerType.setId(PredefinedConstants.TIMER_CONDITION);
        timerEventHandlerType.setIsPredefined(true);
        timerEventHandlerType.setActivityCondition(true);
        timerEventHandlerType.setImplementation(ImplementationType.PULL_LITERAL);

        EventConditionTypeType exceptionEventHandlerType = CarnotWorkflowModelFactory.eINSTANCE.createEventConditionTypeType();
        exceptionEventHandlerType.setId(PredefinedConstants.EXCEPTION_CONDITION);
        exceptionEventHandlerType.setIsPredefined(true);
        exceptionEventHandlerType.setActivityCondition(true);
        exceptionEventHandlerType.setImplementation(ImplementationType.ENGINE_LITERAL);

        carnotModel.getEventActionType().add(scheduleActionType);
        carnotModel.getEventActionType().add(completeActionType);
        carnotModel.getEventActionType().add(abortActionType);
        carnotModel.getEventActionType().add(setDataActionType);

        carnotModel.getEventConditionType().add(timerEventHandlerType);
        carnotModel.getEventConditionType().add(exceptionEventHandlerType);

        ///**************************************************************************************************************///
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public ModelType getTargetModel() {
        BpmModelBuilder.assignMissingElementOids(carnotModel);
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
    	logger.debug("addStartEvent" + event.getId() + " " + event.getName() + " in " + container);
    	new StartEvent2Stardust(carnotModel, failures).addStartEvent(event, container);
    }

	@Override
	public void addIntermediateCatchEvent(IntermediateCatchEvent event, FlowElementsContainer container) {
		logger.info("addIntermediateCatchEvent " + event);
		new IntermediateEvent2Stardust(carnotModel, failures).addIntermediateCatchEvent(event, container);
	}

	@Override
	public void addIntermediateThrowEvent(IntermediateThrowEvent event, FlowElementsContainer container) {
		logger.info("addIntermediateThrowEvent " + event);
		new IntermediateEvent2Stardust(carnotModel, failures).addIntermediateThrowEvent(event, container);
	}

    public void addEndEvent(EndEvent event, FlowElementsContainer container) {
    	new EndEvent2Stardust(carnotModel, failures).addEndEvent(event, container);
    }

    public void addExclusiveGateway(ExclusiveGateway gateway, FlowElementsContainer container) {
        logger.debug("addExclusiveGateway" + gateway.getId() + " " + gateway.getName());
        new Gateway2Stardust(carnotModel,failures).addExclusiveGateway(gateway, container);
    }

    public void addParallelGateway(ParallelGateway gateway, FlowElementsContainer container) {
        logger.debug("addParallelGateway" + gateway.getId() + " " + gateway.getName());
        new Gateway2Stardust(carnotModel,failures).addParallelGateway(gateway, container);
    }

    public void addPartnerEntity(PartnerEntity entity) {
    	logger.debug("addPartnerEntity " + entity);
        newOrganization(carnotModel)
            .withIdAndName(entity.getId(), entity.getName())
            .withDescription(DocumentationTool.getDescriptionFromDocumentation(entity.getDocumentation()))
            .build();
    }

    public void addProcess(Process process) {
        logger.debug("addProcess " + process);
        new Process2Stardust(carnotModel, failures).addProcess(process);
    }

    public void addSequenceFlow(SequenceFlow seq, FlowElementsContainer container) {
        logger.debug("addSequenceFlow " + seq);
        new SequenceFlow2Stardust(carnotModel, failures).addSequenceFlow(seq, container);
    }

    public void addSubProcess(SubProcess subprocess, FlowElementsContainer container) {
        logger.debug("addSubProcess" + subprocess.getId() + " " + subprocess.getName());
        new Subprocess2Stardust(carnotModel, failures).addSubprocess(subprocess, container);
    }

    public void addAbstractTask(Task task, FlowElementsContainer container) {
    	logger.debug("addAbstractTask " + task + " in " + container);
        ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
        if (processDef == null) return;
        String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());
        newRouteActivity(processDef)
                .withIdAndName(task.getId(), task.getName())
                .withDescription(descr)
                .build();
    }

    public void addUserTask(UserTask task, FlowElementsContainer container) {
    	new UserTask2Stardust(carnotModel, failures).addUserTask(task, container);
    }

    public void addServiceTask(ServiceTask task, FlowElementsContainer container) {
    	logger.debug("addServiceTask " + task + " in " + container);
    	new ServiceTask2Stardust(carnotModel, failures).addServiceTask(task, container);
    }

    public void addItemDefinition(ItemDefinition itemdef, List<Import> bpmnImports) {
    	logger.debug("addItemDefinition " + itemdef);
        new Data2Stardust(carnotModel, failures).addItemDefinition(itemdef);
    }

    public void addParticipant(Participant participant, Process process) {
    }

    public void addLane(Lane lane, LaneSet laneset, Lane parentLane, FlowElementsContainer container) {

    }

    public void addIOBinding(List<InputOutputBinding> ioBinding, FlowElementsContainer container) {

    }

    public void addDataObject(DataObject dataObject, FlowElementsContainer container) {
    	logger.debug("addDataObject " + dataObject.getId() + " " + dataObject.getName() + " in " + container);
        new Data2Stardust(carnotModel, failures).addDataObject(dataObject);
    }

    public void addDataObjectReference(DataObjectReference flowElement, FlowElementsContainer container) {

    }

    public void addDataStoreReference(DataStoreReference flowElement, FlowElementsContainer container) {

    }

    public void addTaskDataFlows(Activity activity, FlowElementsContainer container) {
        logger.debug("addTaskDataFlows " + activity.getId() + " " + activity.getName());
        new TaskDataFlow2Stardust(carnotModel,failures).addDataFlows(activity, container);
    }

	public void addInterface(Interface bpmnInterface) {
		new Interface2StardustApplication(carnotModel, failures).addIInterface(bpmnInterface);
	}

    @SuppressWarnings("unused")
    private static DescriptionType getDescription(String description) {
        DescriptionType descriptor = AbstractElementBuilder.F_CWM.createDescriptionType();
        XpdlModelUtils.setCDataString(descriptor.getMixed(), description, true);
        return descriptor;
    }

	@Override
	public void addRoutingSequenceFlows(FlowNode node, FlowElementsContainer process) {
		logger.debug("Process control flows not using gateways (uncontrolled or conditional sequences) in container: " + process);
		new RoutingSequenceFlow2Stardust(carnotModel, failures).processRoutingNode(node, process);
	}

	@Override
	public void addResource(Resource resource) {
		logger.debug("addResource " + resource);
		new Resource2Stardust(carnotModel, failures).addResource(resource);
	}

	@Override
	public void finalizeTransformation(Definitions defs) {
		logger.info("Finalize transformation.");
		new Resource2Stardust(carnotModel, failures).setConditionalPerformerData(defs);
	}

	@Override
	public void addEventDataFlows(CatchEvent event, FlowElementsContainer container) {
		logger.debug("addEventDataFlows for catch event (" + event + ").");
		if (event instanceof StartEvent) {
			new StartEventDataFlow2Stardust(carnotModel, failures).addDataFlows((StartEvent)event, container);
		} else {
			new IntermediateAndEndEventDataFlow2Stardust(carnotModel, failures).addDataFlows(event, container);
		}
	}

	@Override
	public void addEventDataFlows(ThrowEvent event, FlowElementsContainer container) {
		logger.debug("addEventDataFlows for throw event (" + event + ").");
		new IntermediateAndEndEventDataFlow2Stardust(carnotModel, failures).addDataFlows(event, container);
	}

	@Override
	public void postTransformProcessStarts(Map<FlowElementsContainer, List<StartEvent>> startEventsPerContainer,
			Map<FlowElementsContainer, List<FlowNode>> potentialStartNodesPerContainer) {
		new ProcessStartConfigurator(carnotModel, failures).configureProcessStarts(startEventsPerContainer, potentialStartNodesPerContainer);
	}

	@Override
	public void addBoundaryEvent(BoundaryEvent event, FlowElementsContainer container) {
		new BoundaryEvent2Stardust(carnotModel, failures).addBoundaryEvent(event, container);
	}


}
