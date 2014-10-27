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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.GlobalTask;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InclusiveGateway;
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
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.stardust.common.log.LogManager;
import org.eclipse.stardust.common.log.Logger;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.extensions.actions.abort.AbortActivityEventAction;
import org.eclipse.stardust.engine.core.extensions.actions.complete.CompleteActivityEventAction;
import org.eclipse.stardust.engine.core.extensions.conditions.exception.ExceptionCondition;
import org.eclipse.stardust.engine.core.extensions.conditions.exception.ExceptionConditionAccessPointProvider;
import org.eclipse.stardust.engine.core.extensions.conditions.exception.ExceptionConditionValidator;
import org.eclipse.stardust.engine.core.extensions.conditions.timer.TimeStampBinder;
import org.eclipse.stardust.engine.core.extensions.conditions.timer.TimeStampCondition;
import org.eclipse.stardust.engine.core.extensions.conditions.timer.TimeStampEmitter;
import org.eclipse.stardust.engine.core.extensions.conditions.timer.TimerAccessPointProvider;
import org.eclipse.stardust.engine.core.extensions.conditions.timer.TimerValidator;
import org.eclipse.stardust.engine.core.extensions.triggers.timer.TimerTriggerValidator;
import org.eclipse.stardust.engine.extensions.camel.app.CamelProducerSpringBeanApplicationInstance;
import org.eclipse.stardust.engine.extensions.camel.app.CamelProducerSpringBeanValidator;
import org.eclipse.stardust.engine.extensions.camel.trigger.validation.CamelTriggerValidator;
import org.eclipse.stardust.engine.extensions.jms.trigger.JMSTriggerValidator;
import org.eclipse.stardust.engine.extensions.mail.trigger.MailTriggerValidator;
import org.eclipse.stardust.engine.spring.extensions.app.SpringBeanAccessPointProvider;
import org.eclipse.stardust.engine.spring.extensions.app.SpringBeanApplicationInstance;
import org.eclipse.stardust.engine.spring.extensions.app.SpringBeanValidator;
import org.eclipse.stardust.model.bpmn2.input.serialization.Bpmn2PersistenceHandler;
import org.eclipse.stardust.model.bpmn2.transform.Transformator;
import org.eclipse.stardust.model.bpmn2.transform.util.PredefinedDataInfo;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.CallActivity2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.Subprocess2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.task.ServiceTask2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.task.UserTask2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.callable.GlobalTask2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.callable.process.Process2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common.Resource2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.Gateway2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.ProcessStartConfigurator;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.RoutingSequenceFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.SequenceFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.Data2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.IntermediateAndEndEventDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.Property2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.StartEventDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.TaskDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.EndEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.NativeBoundaryEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.NativeIntermediateEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.StartEvent2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.service.Interface2StardustApplication;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.defaults.DefaultTypesInitializer;
import org.eclipse.stardust.model.xpdl.builder.model.BpmPackageBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelBuilderFacade;
import org.eclipse.stardust.model.xpdl.builder.utils.ModelerConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelIoUtils;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
/**
 * @author Simon Nikles
 *
 */
public class Bpmn2StardustXPDL implements Transformator {

    public static final String FAIL_NO_PROCESS_DEF = "Stardust Process definition not found";
    public static final String FAIL_ELEMENT_CREATION = "Could not create Stardust element";
    public static final String FAIL_ELEMENT_UNSUPPORTED_FEATURE = "Usupported feature: ";

    protected ModelType carnotModel = null;
    private Map<String, ModelType> transformedRelatedModelsByDefinitionsId;

    private List<String> failures = new ArrayList<String>();

    private final Logger logger = LogManager.getLogger(this.getClass());

    private CarnotModelQuery query;

    public void createTargetModel(Definitions definitions) throws Exception {
    	logger.info("createTargetModel " + definitions.getName());
    	String modelId = definitions.getId();
    	if (null == modelId) modelId = definitions.getName();
    	if (null == modelId) {
    		failures.add("No Model Id (Definitions) defined");
    		throw new Exception("No Model Id (Definitions) defined");
    	}
    	carnotModel = newBpmModel()
    			.withIdAndName(modelId, definitions.getName())
    			.build();
    	Bpmn2StardustXPDLExtension.addModelExtensions(definitions, carnotModel);
    	Bpmn2StardustXPDLExtension.addModelExtensionDefaults(definitions, carnotModel);
    	query = new CarnotModelQuery(carnotModel);

        ModelBuilderFacade facade = new ModelBuilderFacade();
        DataType data = facade.createPrimitiveData(carnotModel, PredefinedDataInfo.VAR_START_EVENT_ID, PredefinedDataInfo.LBL_START_EVENT_ID, ModelerConstants.STRING_PRIMITIVE_DATA_TYPE);
        data.setPredefined(true);

    	DefaultTypesInitializer initializer = new DefaultTypesInitializer();
    	initializer.initializeModel(carnotModel);
    	initializer.initializeTriggerType(carnotModel, PredefinedConstants.MAIL_TRIGGER, "Mail Trigger", false, MailTriggerValidator.class);
    	initializer.initializeTriggerType(carnotModel, PredefinedConstants.TIMER_TRIGGER, "Timer Trigger", false, TimerTriggerValidator.class);
    	initializer.initializeTriggerType(carnotModel, "camel", "Camel Trigger", false, CamelTriggerValidator.class);

        EventActionTypeType throwEscalationActionType = CarnotWorkflowModelFactory.eINSTANCE.createEventActionTypeType();
        throwEscalationActionType.setId("throwEscalation");
        throwEscalationActionType.setName("Throw Escalation Action");
        throwEscalationActionType.setIsPredefined(true);
        throwEscalationActionType.setActivityAction(true);
        throwEscalationActionType.setSupportedConditionTypes(PredefinedConstants.ACTIVITY_STATECHANGE_CONDITION + "," + PredefinedConstants.EXCEPTION_CONDITION);
        carnotModel.getEventActionType().add(throwEscalationActionType);

        EventActionTypeType throwErrorActionType = CarnotWorkflowModelFactory.eINSTANCE.createEventActionTypeType();
        throwErrorActionType.setId("throwError");
        throwErrorActionType.setName("Throw Error Action");
        throwErrorActionType.setIsPredefined(true);
        throwErrorActionType.setActivityAction(true);
        throwErrorActionType.setSupportedConditionTypes(PredefinedConstants.ACTIVITY_STATECHANGE_CONDITION);
        carnotModel.getEventActionType().add(throwErrorActionType);

    	initializer.initializeInteractionContextTypes(carnotModel);

    	initializer.initializeApplicationType(carnotModel,
    										 PredefinedConstants.SPRINGBEAN_APPLICATION,
			                				 "Spring Bean Application",
			                				 SpringBeanAccessPointProvider.class,
			                				 SpringBeanApplicationInstance.class,
			                				 SpringBeanValidator.class);

    	initializer.initializeApplicationType(carnotModel,
				 "camelConsumerApplication",
				 "Camel Consumer Application",
				 null,
				 CamelProducerSpringBeanApplicationInstance.class,
				 CamelProducerSpringBeanValidator.class);

    	initializer.initializeApplicationType(carnotModel,
				 "camelSpringProducerApplication",
				 "Camel Consumer Application",
				 null,
				 CamelProducerSpringBeanApplicationInstance.class,
				 CamelProducerSpringBeanValidator.class);

    	if (null == ModelUtils.findElementById(carnotModel.getTriggerType(), PredefinedConstants.JMS_TRIGGER)) {
    		TriggerTypeType typeDef = BpmPackageBuilder.F_CWM.createTriggerTypeType();
    		typeDef.setId(PredefinedConstants.JMS_TRIGGER);
    		typeDef.setName("JMS Trigger");
    		typeDef.setPullTrigger(false);
    		typeDef.setIsPredefined(true);
    		AttributeUtil.setAttribute(typeDef, PredefinedConstants.VALIDATOR_CLASS_ATT, JMSTriggerValidator.class.getName());
    		carnotModel.getTriggerType().add(typeDef);
    	}
    	if (null == ModelUtils.findElementById(carnotModel.getApplicationContextType(), PredefinedConstants.JSF_CONTEXT))
    	{
    		ApplicationContextTypeType typeDef = BpmPackageBuilder.F_CWM.createApplicationContextTypeType();
    		typeDef.setId(PredefinedConstants.JSF_CONTEXT);
    		typeDef.setName("JSF Application");
    		typeDef.setIsPredefined(true);
    		typeDef.setHasApplicationPath(true);
    		typeDef.setHasMappingId(false);
    		carnotModel.getApplicationContextType().add(typeDef);
    	}

    	decodeEventHandlerType("timer", carnotModel);

    	decodeEventActionType("completeActivity", carnotModel);
    	decodeEventActionType("abortActivity", carnotModel);
    }

    public ModelType getTargetModel() {
        // TODO review alternative use of ModelOidUtil
        assignMissingElementOids(carnotModel);
        return carnotModel;
    }

    public List<String> getTransformationMessages() {
        return failures;
    }

    public void serializeTargetModel(OutputStream target) {
        logger.debug("serializeTargetModel");
        try {
            target.write(XpdlModelIoUtils.saveModel(carnotModel));
            target.flush();
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
		new NativeIntermediateEvent2Stardust(carnotModel, failures).addIntermediateCatchEvent(event, container);
	}

	@Override
	public void addIntermediateThrowEvent(IntermediateThrowEvent event, FlowElementsContainer container) {
		logger.info("addIntermediateThrowEvent " + event);
		new NativeIntermediateEvent2Stardust(carnotModel, failures).addIntermediateThrowEvent(event, container);
	}

    public void addEndEvent(EndEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
    	new EndEvent2Stardust(carnotModel, failures).addEndEvent(event, container, predefinedDataForId);
    }

    public void addExclusiveGateway(ExclusiveGateway gateway, FlowElementsContainer container) {
        logger.debug("addExclusiveGateway" + gateway.getId() + " " + gateway.getName());
        new Gateway2Stardust(carnotModel,failures).addExclusiveGateway(gateway, container);
    }

	@Override
	public void addInclusiveGateway(InclusiveGateway gateway, FlowElementsContainer container) {
        logger.debug("addInclusiveGateway" + gateway.getId() + " " + gateway.getName());
        new Gateway2Stardust(carnotModel,failures).addInclusiveGateway(gateway, container);
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

	@Override
	public void addGlobalTask(GlobalTask global) {
        logger.debug("addGlobalTask " + global);
        new GlobalTask2Stardust(carnotModel, failures).addGlobalTask(global);
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
    	// nop
    }

    public void addDataStoreReference(DataStoreReference flowElement, FlowElementsContainer container) {
    	// nop
    }

	@Override
	public void addDataStore(DataStore dataStore) {
    	logger.debug("addDataObject " + dataStore.getId() + " " + dataStore.getName());
        new Data2Stardust(carnotModel, failures).addDataStore(dataStore);
	}

    public void addTaskDataFlows(Activity activity, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
        logger.debug("addTaskDataFlows " + activity.getId() + " " + activity.getName());
        new TaskDataFlow2Stardust(carnotModel,failures).addDataFlows(activity, container, predefinedDataForId);
    }

	public void addInterface(Interface bpmnInterface) {
		new Interface2StardustApplication(carnotModel, failures).addIInterface(bpmnInterface);
	}

    @SuppressWarnings("unused")
    private static DescriptionType getDescription(String description) {
        DescriptionType descriptor = AbstractElementBuilder.F_CWM.createDescriptionType();
        ModelUtils.setCDataString(descriptor.getMixed(), description, true);
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
	public void addEventDataFlows(CatchEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
		logger.debug("addEventDataFlows for catch event (" + event + ").");
		if (event instanceof StartEvent) {
			new StartEventDataFlow2Stardust(carnotModel, failures).addDataFlows((StartEvent)event, container, predefinedDataForId);
		} else {
			new IntermediateAndEndEventDataFlow2Stardust(carnotModel, failures).addDataFlows(event, container, predefinedDataForId);
		}
	}

	@Override
	public void addEventDataFlows(ThrowEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
		logger.debug("addEventDataFlows for throw event (" + event + ").");
		new IntermediateAndEndEventDataFlow2Stardust(carnotModel, failures).addDataFlows(event, container, predefinedDataForId);
	}

	@Override
	public void postTransformProcessStarts(Map<FlowElementsContainer, List<StartEvent>> startEventsPerContainer,
			Map<FlowElementsContainer, List<FlowNode>> potentialStartNodesPerContainer) {
		new ProcessStartConfigurator(carnotModel, failures).configureProcessStarts(startEventsPerContainer, potentialStartNodesPerContainer);
	}

	@Override
	public void addBoundaryEvent(BoundaryEvent event, FlowElementsContainer container) {
		//new BoundaryEvent2Stardust(carnotModel, failures).addBoundaryEvent(event, container);
		new NativeBoundaryEvent2Stardust(carnotModel, failures).addBoundaryEvent(event, container);
	}

    /**
     * @param model the model to be tweaked
     * @deprecated review alternative use of ModelOidUtil
     */
    @Deprecated
    private static void assignMissingElementOids(ModelType model) {
        long maxElementOid = ModelUtils.getMaxUsedOid(model);

        if (!model.isSetOid()) {
            model.setOid(++maxElementOid);
        }

        for (TreeIterator<EObject> modelContents = model.eAllContents(); modelContents.hasNext();) {
            EObject element = modelContents.next();
            if ((element instanceof IModelElement) && !((IModelElement) element).isSetElementOid()) {
            	try {
                ((IModelElement) element).setElementOid(++maxElementOid);
            	} catch (Exception e) {e.printStackTrace();}
            }
        }
    }

	@Override
	public void addCallActivity(CallActivity activity, FlowElementsContainer container) {
		new CallActivity2Stardust(carnotModel, transformedRelatedModelsByDefinitionsId, failures).addCallActivity(activity, container);
	}

	@Override
	public void addGlobalCall(CallActivity caller, FlowElementsContainer container) {
		new CallActivity2Stardust(carnotModel, transformedRelatedModelsByDefinitionsId, failures).addGlobalCall(caller, container);
	}

	@Override
	public Definitions getImportDefinitions(Import imp) {
		Definitions container = (Definitions)imp.eContainer();
		ResourceSet resourceSet = container.eResource().getResourceSet();
		String importName = URI.createFileURI(imp.getLocation()).toFileString();
		URI importUri = Bpmn2PersistenceHandler.getStreamUri(importName);
		org.eclipse.emf.ecore.resource.Resource resource = resourceSet.getResource(importUri, false);
		if (null != resource) {
			try {
				Bpmn2Resource eResource = (Bpmn2Resource)resource;
				EObject importRoot = eResource.getContents().get(0);
				if (importRoot instanceof DocumentRoot) {
					return ((DocumentRoot)importRoot).getDefinitions();
				}

			} catch (Exception e) {}
		}
		return null;
	}

	/**
	 * TODO refactor
	 * TAKEN FROM org.eclipse.stardust.ui.web.modeler.xpdl.marshalling.EventMarshallingUtils to ommit web-modeller dependency
	 */
	   public static EventConditionTypeType decodeEventHandlerType(String conditionTypeId, ModelType model)
	   {
	      if (conditionTypeId != null)
	      {
	         EventConditionTypeType conditionType = ModelUtils.findIdentifiableElement(
	               model.getEventConditionType(), conditionTypeId);
	         if (conditionType == null)
	         {
	            conditionType = injectPredefinedConditionType(model, conditionTypeId);
	         }
	         return conditionType;
	      }
	      return null;
	   }

		/**
		 * TODO refactor
		 * TAKEN FROM org.eclipse.stardust.ui.web.modeler.xpdl.marshalling.EventMarshallingUtils to ommit web-modeller dependency
		 */
	   public static EventActionTypeType decodeEventActionType(String actionTypeId, ModelType model)
	   {
	      if (actionTypeId != null)
	      {
	         EventActionTypeType actionType = ModelUtils.findIdentifiableElement(
	               model.getEventActionType(), actionTypeId);
	         if (actionType == null)
	         {
	            actionType = injectPredefinedActionType(model, actionTypeId);
	         }
	         return actionType;
	      }
	      return null;
	   }

		/**
		 * TODO refactor
		 * TAKEN FROM org.eclipse.stardust.ui.web.modeler.xpdl.marshalling.EventMarshallingUtils to ommit web-modeller dependency
		 */
	   private static EventActionTypeType injectPredefinedActionType(ModelType model, String actionTypeId)
	   {
	      EventActionTypeType actionType = null;
	      if (PredefinedConstants.ABORT_ACTIVITY_ACTION.equals(actionTypeId))
	      {
	         actionType = newActionType(actionTypeId, "Abort Activity", false, true,
	            "timer, exception", "bind, unbind", new String[][] {
	            {"carnot:engine:action", AbortActivityEventAction.class.getName()}
	         });
	      }
	      else if (PredefinedConstants.COMPLETE_ACTIVITY_ACTION.equals(actionTypeId))
	      {
	         actionType = newActionType(actionTypeId, "Complete Activity", false, true,
	            "timer, exception", "bind", new String[][] {
	            {"carnot:engine:action", CompleteActivityEventAction.class.getName()}
	         });
	      }
	      model.getEventActionType().add(actionType);
	      return actionType;
	   }

	   private static EventActionTypeType newActionType(String id, String name, boolean isProcessAction, boolean isActivityAction,
		         String supportedConditionTypes, String unsupportedContexts, String[][] attributes)
		   {
		      EventActionTypeType actionType = BpmPackageBuilder.F_CWM.createEventActionTypeType();
		      actionType.setId(id);
		      actionType.setName(name);
		      actionType.setIsPredefined(true);
		      actionType.setProcessAction(isProcessAction);
		      actionType.setActivityAction(isActivityAction);
		      actionType.setSupportedConditionTypes(supportedConditionTypes);
		      actionType.setUnsupportedContexts(unsupportedContexts);
		      for (String[] attribute : attributes)
		      {
		         AttributeUtil.setAttribute(actionType, attribute[0], attribute[1]);
		      }
		      return actionType;
		   }

	   private static EventConditionTypeType injectPredefinedConditionType(ModelType model, String conditionTypeId)
	   {
	      EventConditionTypeType conditionType = null;
	      if (PredefinedConstants.TIMER_CONDITION.equals(conditionTypeId))
	      {
	         conditionType = newConditionType(conditionTypeId, "Timer", true, true,
	            ImplementationType.PULL_LITERAL, new String[][] {
	            {"carnot:engine:accessPointProvider", TimerAccessPointProvider.class.getName()},
	            {"carnot:engine:binder", TimeStampBinder.class.getName()},
	            {"carnot:engine:condition", TimeStampCondition.class.getName()},
	            {"carnot:engine:pullEventEmitter", TimeStampEmitter.class.getName()},
	            {"carnot:engine:validator", TimerValidator.class.getName()}
	         });
	      }
	      else if (PredefinedConstants.EXCEPTION_CONDITION.equals(conditionTypeId))
	      {
	         conditionType = newConditionType(conditionTypeId, "On Exception", false, true,
	            ImplementationType.ENGINE_LITERAL, new String[][] {
	            {"carnot:engine:accessPointProvider", ExceptionConditionAccessPointProvider.class.getName()},
	            {"carnot:engine:condition", ExceptionCondition.class.getName()},
	            {"carnot:engine:validator", ExceptionConditionValidator.class.getName()}
	         });
	      }
	      model.getEventConditionType().add(conditionType);
	      return conditionType;
	   }

	   private static EventConditionTypeType newConditionType(String id, String name,
		         boolean isProcessCondition, boolean isActivityCondition,
		         ImplementationType implementation, String[][] attributes)
		   {
		      EventConditionTypeType conditionType = BpmPackageBuilder.F_CWM.createEventConditionTypeType();
		      conditionType.setId(id);
		      conditionType.setName(name);
		      conditionType.setIsPredefined(true);
		      conditionType.setProcessCondition(isProcessCondition);
		      conditionType.setActivityCondition(isActivityCondition);
		      conditionType.setImplementation(implementation);
		      for (String[] attribute : attributes)
		      {
		         AttributeUtil.setAttribute(conditionType, attribute[0], attribute[1]);
		      }
		      return conditionType;
		   }

	@Override
	public void addProperty(Property property, Map<String, String> predefinedDataForId) {
		new Property2Stardust(carnotModel, failures).addProperty(property, predefinedDataForId);
	}


}
