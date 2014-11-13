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
package org.eclipse.stardust.model.bpmn2.transform;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.Artifact;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Category;
import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.Collaboration;
import org.eclipse.bpmn2.ComplexGateway;
import org.eclipse.bpmn2.CorrelationProperty;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.DataStore;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.EndPoint;
import org.eclipse.bpmn2.Error;
import org.eclipse.bpmn2.Escalation;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventBasedGateway;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.GlobalTask;
import org.eclipse.bpmn2.ImplicitThrowEvent;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InclusiveGateway;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.Message;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.PartnerRole;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Property;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Signal;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.stardust.model.bpmn2.ModelConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;

/**
 * @author Simon Nikles
 *
 */
public class TransformationControl {

    private static final String NOT_SUPPORTED = ": element transformation not supported\n";

    private final Dialect dialect;
    private Transformator transf;
    private String processingInfo = "";
    private Logger log;
    private Map<FlowElementsContainer, List<Activity>> tasksWithDataflow;
    private Map<FlowElementsContainer, List<ThrowEvent>> throwEventsWithDataflow;
    private Map<FlowElementsContainer, List<CatchEvent>> catchEventsWithDataflow;
    private Map<FlowElementsContainer, List<StartEvent>> startEventsPerContainer;
    /**
     * containers here are 'process' or 'subprocess'
     */
    private Map<FlowElementsContainer, List<FlowNode>> potentialStartNodesPerContainer;
    private Map<FlowElementsContainer, List<CallActivity>> globalCalls;
	private List<String> processedImportDefinitions = new ArrayList<String>();

	private Map<String, String> predefinedDataForId = new HashMap<String, String>();

    public static TransformationControl getInstance(Dialect dialect) {
        return new TransformationControl(dialect);
    }

    private TransformationControl(Dialect dialect) {
        this.dialect = dialect;
        log = Logger.getLogger(this.getClass());
    }

    public String transformToTarget(Definitions definitions, OutputStream target) {
        tasksWithDataflow = new HashMap<FlowElementsContainer, List<Activity>>();
        throwEventsWithDataflow = new HashMap<FlowElementsContainer, List<ThrowEvent>>();
        catchEventsWithDataflow = new HashMap<FlowElementsContainer, List<CatchEvent>>();
        startEventsPerContainer = new HashMap<FlowElementsContainer, List<StartEvent>>();
        potentialStartNodesPerContainer = new HashMap<FlowElementsContainer, List<FlowNode>>();
        globalCalls = new HashMap<FlowElementsContainer, List<CallActivity>>();
        processingInfo = "";
        transf = dialect.getTransformator();
        processBpmn(definitions, transf);
        transf.postTransformProcessStarts(startEventsPerContainer, potentialStartNodesPerContainer);
        transf.finalizeTransformation(definitions);
        transf.serializeTargetModel(target);

        for (String msg : transf.getTransformationMessages()) {
            log.info(msg);
        }
        log.info(processingInfo);

        return processingInfo;
    }

    public Object getTargetModel() {
        return transf.getTargetModel();
    }

    private  void processBpmn(Definitions definitions, Transformator transf) {

    	try {
    		transf.createTargetModel(definitions);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

        List<RootElement> roots = definitions.getRootElements();
        List<Collaboration> collabs = new ArrayList<Collaboration>();
        List<Import> bpmnImports =  definitions.getImports();
        for (Import imp : bpmnImports) {
        	processModelImport(imp);
        }
        // 'globally' used elements
        for (RootElement root : definitions.getRootElements()) {
            if (root instanceof ItemDefinition) {
                processItemDefinition((ItemDefinition)root, bpmnImports);
            } else if (root instanceof Interface) {
                processInterface((Interface)root);
            } else if (root instanceof Resource) {
                processResource((Resource)root);
            }
        }
        for (RootElement root : roots) {
            if (root instanceof CallableElement) {
                if (root instanceof Process) {
                    processProcess((Process)root);
                } else if (root instanceof GlobalTask) {
                    processGlobalTask((GlobalTask)root);
                }
            } else if (root instanceof Category) {
                processCategory((Category)root);
            } else if (root instanceof CorrelationProperty) {
                processCorrelationProperty((CorrelationProperty)root);
            } else if (root instanceof DataStore) {
                processDataStore((DataStore)root);
            } else if (root instanceof EndPoint) {
                processEndPoint((EndPoint)root);
            } else if (root instanceof Error) {
                processError((Error)root);
            } else if (root instanceof Escalation) {
                processEscalation((Escalation)root);
            } else if (root instanceof EventDefinition) {
                processEventDefinition((EventDefinition)root);
            } else if (root instanceof Message) {
                processMessage((Message)root);
            } else if (root instanceof PartnerEntity) {
                processPartnerEntity((PartnerEntity)root);
            } else if (root instanceof PartnerRole) {
                processPartnerRole((PartnerRole)root);
            } else if (root instanceof Signal) {
                processSignal((Signal)root);
            } else if (root instanceof Collaboration) {
                collabs.add((Collaboration)root);
            }
        }
        // process finally, as a transformator may want to set responsibilites (e.g. performing organisation).
        for (Collaboration collab : collabs) {
            processCollaboration(collab);
        }

        for (FlowElementsContainer container : tasksWithDataflow.keySet()) {
            for (Activity activity : tasksWithDataflow.get(container)) {
                processTaskDataFlow(activity, container, predefinedDataForId);
            }
        }
		for (FlowElementsContainer container : throwEventsWithDataflow.keySet()) {
			for (ThrowEvent event : throwEventsWithDataflow.get(container)) {
				processEventDataFlow(event, container, predefinedDataForId);
			}
		}
		for (FlowElementsContainer container : catchEventsWithDataflow.keySet()) {
			for (CatchEvent event : catchEventsWithDataflow.get(container)) {
				processEventDataFlow(event, container, predefinedDataForId);
			}
		}
		for (FlowElementsContainer container : globalCalls.keySet()) {
			for (CallActivity caller : globalCalls.get(container)) {
				processGlobalCall(caller, container);
			}
		}
    }

	private void processModelImport(Import imp) {
		if (null != imp.getImportType() && imp.getImportType().equals(ModelConstants.BPMN_IMPORT_TYPE_MODEL)) {
			Definitions def = transf.getImportDefinitions(imp);
			if (null != def) {
				String defId = null;
				if (null != def.getId()) {
					defId = def.getId();
				} else {
					defId = imp.getLocation();
				}
				if (!processedImportDefinitions.contains(defId)) {
					processedImportDefinitions.add(def.getId());
					processBpmn(def, transf);
				}
			} else {
				processingInfo +=   "Failed to resolve Definitions from BPMN Import element: " + imp + "\n";
				log.error("Failed to resolve Definitions from BPMN Import element: " + imp);
			}
		}
	}

	private  void processProcess(Process process) {
		processProperties(process);
        transf.addProcess(process);
        transf.addIOBinding(process.getIoBinding(), process);
        for (@SuppressWarnings("unused") Artifact artifact : process.getArtifacts()) {
            processingInfo +=   "Artifact" + NOT_SUPPORTED;
        }
        processFlowElementsContainer(process);
    }

    private void processProperties(Process process) {
    	for (Property prop : process.getProperties()) {
    		transf.addProperty(prop, predefinedDataForId);
    	}
	}

    private void processProperties(SubProcess subProcess) {
    	for (Property prop : subProcess.getProperties()) {
    		transf.addProperty(prop, predefinedDataForId);
    	}
	}

	private  void processFlowElementsContainer(FlowElementsContainer container) {
        List<SequenceFlow> sequenceFlows = new ArrayList<SequenceFlow>();
        List<Gateway> gateways = new ArrayList<Gateway>();
        List<FlowNode> routingFlowNodes = new ArrayList<FlowNode>();


        for (LaneSet laneset : container.getLaneSets()) {
            processLaneset(laneset, container);
        }
        for (FlowElement flowElement : container.getFlowElements()) {
            if (flowElement instanceof SequenceFlow) {
                sequenceFlows.add((SequenceFlow)flowElement);
            } else {
                if (flowElement instanceof Gateway) {
                    gateways.add((Gateway)flowElement);
                } else {
                	if (flowElement instanceof FlowNode) {
                		if (BpmnModelQuery.isRoutingFlowNode((FlowNode)flowElement)) {
                			routingFlowNodes.add((FlowNode)flowElement);
                		}
                	}
                    processFlowElement(flowElement, container);
                }
                if (BpmnModelQuery.isPotentialStartNode(flowElement)) { // && BpmnModelQuery.hasNoIncomingSequence((FlowNode)flowElement)) {
                	if (!potentialStartNodesPerContainer.containsKey(container)) {
                		potentialStartNodesPerContainer.put(container, new ArrayList<FlowNode>());
                	}
                	potentialStartNodesPerContainer.get(container).add((FlowNode)flowElement);
                }
            }
        }
        for (Gateway gate : gateways) {
            processFlowElement(gate, container);
        }
        for (FlowNode node : routingFlowNodes) {
        	transf.addRoutingSequenceFlows(node, container);
        }
        for (SequenceFlow flow : sequenceFlows) {
            processSequenceFlow((SequenceFlow)flow, container);
        }

        // process finally, because a transformer may want to set responsibilities (e.g. performing role) of contained elements
        for (LaneSet laneset : container.getLaneSets()) {
            for (Lane lane : laneset.getLanes()) {
                processLane(lane, laneset, null, container);
            }
        }
    }

	private void processLane(Lane lane, LaneSet laneset, Lane parentLane, FlowElementsContainer container) {
        transf.addLane(lane, laneset, parentLane, container);
        if (lane.getChildLaneSet() == null) return;
        for (Lane childLane : lane.getChildLaneSet().getLanes()) {
            processLane(childLane, lane.getChildLaneSet(), lane, container);
        }
    }

    private void processFlowElement(FlowElement flowElement, FlowElementsContainer container) {

        if (flowElement instanceof DataObject) {
            transf.addDataObject((DataObject)flowElement, container);
        } else if (flowElement instanceof DataObjectReference) {
            transf.addDataObjectReference((DataObjectReference)flowElement, container);
        }  else if (flowElement instanceof DataStoreReference) {
            transf.addDataStoreReference((DataStoreReference)flowElement, container);
        } else if (flowElement instanceof FlowNode) {
            if (flowElement instanceof Activity) {
                processActivity((Activity)flowElement, container);
            } else if (flowElement instanceof Event) {
                processEvent((Event)flowElement, container);
            } else if (flowElement instanceof Gateway) {
                processGateway((Gateway)flowElement, container);
            }
            else if (flowElement instanceof ChoreographyActivity) {
                processChoreographyActivity((ChoreographyActivity)flowElement, container);
            }
        }
    }

    private void processActivity(Activity activity, FlowElementsContainer container) {
        if (activity instanceof Task) {

            addToTasksWithDataFlow(activity, container);

            if (activity instanceof UserTask) {
                processUserTask((UserTask)activity, container);
            } else if (activity instanceof ServiceTask) {
                processServiceTask((ServiceTask)activity, container);
            } else if (activity instanceof SendTask) {
                processSendTask((SendTask)activity, container);
            } else if (activity instanceof ScriptTask) {
                processScriptTask((ScriptTask)activity, container);
            } else if (activity instanceof ReceiveTask) {
                processReceiveTask((ReceiveTask)activity, container);
            } else if (activity instanceof ManualTask) {
                processManualTask((ManualTask)activity, container);
            } else if (activity instanceof BusinessRuleTask) {
                processBusinessRuleTask((BusinessRuleTask)activity, container);
            } else {
                processAbstractTask((Task)activity, container);
            }
        } else if (activity instanceof SubProcess) {
            processSubProcess((SubProcess)activity, container);

        } else if (activity instanceof CallActivity) {
            processCallActivity((CallActivity)activity, container);
            if (!globalCalls.containsKey(container)) {
            	globalCalls.put(container, new ArrayList<CallActivity>());
            }
            globalCalls.get(container).add((CallActivity)activity);
        }
    }

    private void addToTasksWithDataFlow(Activity activity, FlowElementsContainer container) {
        InputOutputSpecification ioSpec = activity.getIoSpecification();
        if (ioSpec != null) {
            if ((ioSpec.getDataInputs() != null && ioSpec.getDataInputs().size() > 0)
             || (ioSpec.getDataOutputs() != null && ioSpec.getDataOutputs().size() > 0)
             || (ioSpec.getInputSets() != null && ioSpec.getInputSets().size() > 0)
             || (ioSpec.getOutputSets() != null && ioSpec.getOutputSets().size() > 0)) {
                if (!this.tasksWithDataflow.containsKey(container)) {
                    this.tasksWithDataflow.put(container, new ArrayList<Activity>());
                }
                this.tasksWithDataflow.get(container).add(activity);
            }
        }
    }

    private void addToCatchEventsWithDataFlow(CatchEvent event, FlowElementsContainer container) {
        List<DataOutputAssociation> dataOutputAssociations = event.getDataOutputAssociation();
        if (dataOutputAssociations != null) {
            if (dataOutputAssociations.size() > 0) {
            	if (!this.catchEventsWithDataflow.containsKey(container)) {
            		this.catchEventsWithDataflow.put(container, new ArrayList<CatchEvent>());
            	}
            	this.catchEventsWithDataflow.get(container).add(event);
            }
        }
    }

    private void addToThrowEventsWithDataFlow(ThrowEvent event, FlowElementsContainer container) {
        List<DataInputAssociation> dataInputAssociations = event.getDataInputAssociation();
        if (dataInputAssociations != null) {
            if (dataInputAssociations.size() > 0) {
            	if (!this.throwEventsWithDataflow.containsKey(container)) {
            		this.throwEventsWithDataflow.put(container, new ArrayList<ThrowEvent>());
            	}
            	this.throwEventsWithDataflow.get(container).add(event);
            }
        }
    }

    private void processSubProcess(SubProcess activity, FlowElementsContainer container) {
        if (activity instanceof Transaction) {
            processTransaction((Transaction)activity, container);
        } else if (activity instanceof AdHocSubProcess) {
            processAdHocSubProcess((AdHocSubProcess)activity, container);
        } else {
            processSubProcessDefault(activity, container);
        }
    }

    private void processGateway(Gateway gateway, FlowElementsContainer container) {
        log.debug("ModelTransformator.processGateway() "  + gateway);
        if (gateway instanceof ExclusiveGateway) {
            processExclusiveGateway((ExclusiveGateway)gateway, container);
        } else if (gateway instanceof ParallelGateway) {
            processParallelGateway((ParallelGateway)gateway, container);
        } else if (gateway instanceof InclusiveGateway) {
            processInclusiveGateway((InclusiveGateway)gateway, container);
        } else if (gateway instanceof ComplexGateway) {
            processComplexGateway((ComplexGateway)gateway, container);
        } else if (gateway instanceof EventBasedGateway) {
            processEventBasedGateway((EventBasedGateway)gateway, container);
        }
    }

    private void processEvent(Event event, FlowElementsContainer container) {
        if (event instanceof StartEvent) {
        	if (!startEventsPerContainer.containsKey(container)) {
        		startEventsPerContainer.put(container, new ArrayList<StartEvent>());
        	}
        	startEventsPerContainer.get(container).add((StartEvent)event);
        	addToCatchEventsWithDataFlow((StartEvent)event, container);
            processStartEvent((StartEvent)event, container);
        } else if (event instanceof EndEvent) {
        	addToThrowEventsWithDataFlow((EndEvent)event, container);
            processEndEvent((EndEvent)event, container, predefinedDataForId);
        } else if (event instanceof BoundaryEvent) {
            processBoundaryEvent((BoundaryEvent)event, container);
        } else if (event instanceof IntermediateCatchEvent) {
        	addToCatchEventsWithDataFlow((IntermediateCatchEvent)event, container);
            processIntermediateCatchEvent((IntermediateCatchEvent)event, container);
        } else if (event instanceof IntermediateThrowEvent) {
        	addToThrowEventsWithDataFlow((IntermediateThrowEvent)event, container);
            processIntermediateThrowEvent((IntermediateThrowEvent)event, container);
        } else if (event instanceof ImplicitThrowEvent) {
            processImplicitThrowEvent((ImplicitThrowEvent)event, container);
        }
    }

    private  void processResource(Resource resource) {
        //processingInfo +=   "Resource" + NOT_SUPPORTED;
    	transf.addResource(resource);
    }

    private  void processCollaboration(Collaboration collab) {
        for (Participant participant : collab.getParticipants()) {
            Process proc = participant.getProcessRef();
            if (proc != null && proc.eIsProxy()) {
                URI proxyURI = ((InternalEObject) participant.getProcessRef()).eProxyURI();
                proc = (Process)participant.eResource().getEObject(proxyURI.fragment());
            }
            transf.addParticipant(participant, proc);
        }
    }

    private void processStartEvent(StartEvent event, FlowElementsContainer container) {
        transf.addStartEvent(event, container);
    }

    private void processEndEvent(EndEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
        transf.addEndEvent(event, container, predefinedDataForId);
    }

    private void processExclusiveGateway(ExclusiveGateway gateway, FlowElementsContainer container) {
        transf.addExclusiveGateway(gateway, container);
    }

    private void processInclusiveGateway(InclusiveGateway gateway, FlowElementsContainer container) {
    	transf.addInclusiveGateway(gateway, container);
        //processingInfo +=   "InclusiveGateway" + NOT_SUPPORTED;
    }

    private void processParallelGateway(ParallelGateway gateway, FlowElementsContainer container) {
        transf.addParallelGateway(gateway, container);
    }

    private void processAbstractTask(Task task, FlowElementsContainer container) {
        transf.addAbstractTask(task, container);
    }

    private void processUserTask(UserTask activity, FlowElementsContainer container) {
        transf.addUserTask(activity, container);
    }

    private void processServiceTask(ServiceTask activity, FlowElementsContainer container) {
        //processingInfo +=   "ServiceTask" + NOT_SUPPORTED;
        transf.addServiceTask(activity, container);
    }

    private void processSendTask(SendTask activity, FlowElementsContainer container) {
        //processingInfo +=   "SendTask" + NOT_SUPPORTED;
    	transf.addSendTask(activity, container);
    }

    private void processReceiveTask(ReceiveTask activity, FlowElementsContainer container) {
        //processingInfo +=   "ReceiveTask" + NOT_SUPPORTED;
    	transf.addReceiveTask(activity, container);
    }

    private void processSubProcessDefault(SubProcess activity, FlowElementsContainer container) {
        //processingInfo +=   "SubProcess" + NOT_SUPPORTED;
        processProperties((SubProcess)activity);
        transf.addSubProcess(activity, container);
        processFlowElementsContainer(activity);
    }

    private void processTaskDataFlow(Activity activity, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
        transf.addTaskDataFlows(activity, container, predefinedDataForId);
    }

    private void processEventDataFlow(ThrowEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
    	transf.addEventDataFlows(event, container, predefinedDataForId);
	}

	private void processEventDataFlow(CatchEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
		transf.addEventDataFlows(event, container, predefinedDataForId);
	}

    private void processSequenceFlow(SequenceFlow seq, FlowElementsContainer container) {
        transf.addSequenceFlow(seq, container);
    }

    private  void processPartnerEntity(PartnerEntity entity) {
        transf.addPartnerEntity(entity);
    }


    private void processComplexGateway(ComplexGateway gateway, FlowElementsContainer container) {
        processingInfo +=   "ComplexGateway" + NOT_SUPPORTED;

    }

    private void processEventBasedGateway(EventBasedGateway gateway, FlowElementsContainer container) {
        processingInfo +=   "EventBasedGateway" + NOT_SUPPORTED;

    }

    private void processBoundaryEvent(BoundaryEvent event, FlowElementsContainer container) {
//        processingInfo +=   "BoundaryEvent" + NOT_SUPPORTED;
    	transf.addBoundaryEvent(event, container);

    }

    private void processIntermediateCatchEvent(IntermediateCatchEvent event, FlowElementsContainer container) {
        //processingInfo +=   "IntermediateCatchEvent" + NOT_SUPPORTED;
    	transf.addIntermediateCatchEvent(event, container);

    }

    private void processIntermediateThrowEvent(IntermediateThrowEvent event, FlowElementsContainer container) {
        //processingInfo +=   "IntermediateThrowEvent" + NOT_SUPPORTED;
    	transf.addIntermediateThrowEvent(event, container);
    }

    private void processImplicitThrowEvent(ImplicitThrowEvent event, FlowElementsContainer container) {
        processingInfo +=   "ImplicitThrowEvent" + NOT_SUPPORTED;

    }

    private void processLaneset(LaneSet laneset, FlowElementsContainer container) {
        processingInfo +=   "laneset" + NOT_SUPPORTED;
    }

    private void processScriptTask(ScriptTask activity, FlowElementsContainer container) {
        processingInfo +=   "ScriptTask" + NOT_SUPPORTED;

    }

    private void processManualTask(ManualTask activity, FlowElementsContainer container) {
        //processingInfo +=   "ManualTask" + NOT_SUPPORTED;
    	transf.addManualTask(activity, container);

    }

    private void processBusinessRuleTask(BusinessRuleTask activity, FlowElementsContainer container) {
        processingInfo +=   "BusinessRuleTask" + NOT_SUPPORTED;

    }


    private void processTransaction(Transaction activity, FlowElementsContainer container) {
        processingInfo +=   "Transaction" + NOT_SUPPORTED;

    }

    private void processAdHocSubProcess(AdHocSubProcess activity, FlowElementsContainer container) {
        processingInfo +=   "AdHocSubProcess" + NOT_SUPPORTED;

    }

    private void processCallActivity(CallActivity activity, FlowElementsContainer container) {
        //processingInfo +=   "CallActivity" + NOT_SUPPORTED;
    	transf.addCallActivity(activity, container);
    }

	private void processGlobalCall(CallActivity caller, FlowElementsContainer container) {
		/* handle the call (i.e. data mapping of a call activity) finally, when all callable elements have been transformed */
    	transf.addGlobalCall(caller, container);
	}

    private void processChoreographyActivity(ChoreographyActivity choreo, FlowElementsContainer container) {
        processingInfo +=   "ChoreographyActivity" + NOT_SUPPORTED;

    }

    private  void processGlobalTask(GlobalTask global) {
        //processingInfo +=   "GlobalTask" + NOT_SUPPORTED;
    	transf.addGlobalTask(global);
    }

    private  void processDataStore(DataStore data) {
        //processingInfo +=   "DataStore" + NOT_SUPPORTED;
    	transf.addDataStore(data);
    }

    private  void processPartnerRole(PartnerRole role) {
        processingInfo +=   "PartnerRole" + NOT_SUPPORTED;
    }


    private  void processEndPoint(EndPoint endpoint) {
        processingInfo +=   "EndPoint" + NOT_SUPPORTED;

    }

    private  void processError(Error error) {

        processingInfo +=   "Error" + NOT_SUPPORTED;
    }

    private  void processEscalation(Escalation escal) {
        processingInfo +=   "Escalation" + NOT_SUPPORTED;

    }

    private  void processEventDefinition(EventDefinition eventdef) {
        processingInfo +=   "EventDefinition" + NOT_SUPPORTED;

    }

    private  void processInterface(Interface iface) {
        //processingInfo +=   "Interface" + NOT_SUPPORTED;
    	transf.addInterface(iface);

    }

    private  void processItemDefinition(ItemDefinition itemdef, List<Import> bpmnImports) {
        //processingInfo +=   "ItemDefinition" + NOT_SUPPORTED;
        transf.addItemDefinition(itemdef, bpmnImports);

    }


    private  void processSignal(Signal signal) {
        processingInfo +=   "Signal" + NOT_SUPPORTED;

    }

    private void processMessage(Message root) {
        //processingInfo +=   "Message" + NOT_SUPPORTED;
    }

    private void processCorrelationProperty(CorrelationProperty root) {
        processingInfo +=   "CorrelationProperty" + NOT_SUPPORTED;

    }

    private void processCategory(Category root) {
        processingInfo +=   "Category" + NOT_SUPPORTED;

    }

}
