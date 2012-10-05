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

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Activity;
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
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.RoutingSequenceFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.SequenceFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.Data2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.TaskDataFlow2Stardust;
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
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
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
        carnotModel = newBpmModel()
                .withIdAndName(definitions.getId(), definitions.getName())
                .build();
        Bpmn2StardustXPDLExtension.addModelExtensions(definitions, carnotModel);
        Bpmn2StardustXPDLExtension.addModelExtensionDefaults(definitions, carnotModel);
        query = new CarnotModelQuery(carnotModel);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////TODO REMOVE IF DEFAULTTYPESINITIALIZER IS COMPLETE (OR WHAT IS ABOUT THESE TYPES?)!!! ////////////////////
        DefaultTypesInitializer initializer = new DefaultTypesInitializer();
        initializer.initializeTriggerType(carnotModel, PredefinedConstants.JMS_TRIGGER, "JMS Trigger",
        false, JMSTriggerValidator.class);
        initializer.initializeTriggerType(carnotModel, PredefinedConstants.MAIL_TRIGGER, "Mail Trigger",
        false, MailTriggerValidator.class);
        initializer.initializeTriggerType(carnotModel, PredefinedConstants.TIMER_TRIGGER, "Timer Trigger",
        false, TimerTriggerValidator.class);
        initializer.initializeInteractionContextTypes(carnotModel);
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
    	new StartEvent2Stardust(carnotModel, failures).addStartEvent(event, container);
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
        new Process2Stardust(carnotModel, failures).addProcess(process);
    }

    public void addSequenceFlow(SequenceFlow seq, FlowElementsContainer container) {
        logger.debug("addSequenceFlow " + seq);
        new SequenceFlow2Stardust(carnotModel, failures).addSequenceFlow(seq, container);
    }

    public void addSubProcess(SubProcess subprocess, FlowElementsContainer container) {
        logger.info("addSubProcess" + subprocess.getId() + " " + subprocess.getName());
        new Subprocess2Stardust(carnotModel, failures).addSubprocess(subprocess, container);
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
    	new UserTask2Stardust(carnotModel, failures).addUserTask(task, container);
    }

    public void addServiceTask(ServiceTask task, FlowElementsContainer container) {
    	new ServiceTask2Stardust(carnotModel, failures).addServiceTask(task, container);
    }

    public void addItemDefinition(ItemDefinition itemdef, List<Import> bpmnImports) {
        new Data2Stardust(carnotModel, failures).addItemDefinition(itemdef);
    }

    public void addParticipant(Participant participant, Process process) {
    }

    public void addLane(Lane lane, LaneSet laneset, Lane parentLane, FlowElementsContainer container) {

    }

    public void addIOBinding(List<InputOutputBinding> ioBinding, FlowElementsContainer container) {

    }

    public void addDataObject(DataObject dataObject, FlowElementsContainer container) {
        new Data2Stardust(carnotModel, failures).addDataObject(dataObject);
    }

    public void addDataObjectReference(DataObjectReference flowElement, FlowElementsContainer container) {

    }

    public void addDataStoreReference(DataStoreReference flowElement, FlowElementsContainer container) {

    }

    public void addEndEvent(EndEvent event, FlowElementsContainer container) {

    }

    public void addTaskDataFlows(Activity activity, FlowElementsContainer container) {
        logger.info("addTaskDataFlows " + activity.getId() + " " + activity.getName());
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
		new RoutingSequenceFlow2Stardust(carnotModel, failures).processRoutingNode(node, process);
	}

	@Override
	public void addResource(Resource resource) {
		new Resource2Stardust(carnotModel, failures).addResource(resource);
	}

	@Override
	public void finalizeTransformation(Definitions defs) {
		new Resource2Stardust(carnotModel, failures).setConditionalPerformerData(defs);
	}

}
