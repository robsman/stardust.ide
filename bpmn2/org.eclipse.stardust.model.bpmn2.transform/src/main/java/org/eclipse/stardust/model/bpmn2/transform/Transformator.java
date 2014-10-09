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

/**
 * @author Simon Nikles
 *
 */
public interface Transformator {

    public void createTargetModel(Definitions definitions) throws Exception;

    public Object getTargetModel();

    public void addProcess(Process process);

    public void addAbstractTask(Task task, FlowElementsContainer container);

    public void addUserTask(UserTask task, FlowElementsContainer container);

    public void addStartEvent(StartEvent event, FlowElementsContainer container);

    public void addEndEvent(EndEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId);

    public void serializeTargetModel(OutputStream target);

    public void addIOBinding(List<InputOutputBinding> ioBinding, FlowElementsContainer container);

    public void addDataObject(DataObject flowElement, FlowElementsContainer container);

    public void addDataObjectReference(DataObjectReference flowElement, FlowElementsContainer container);

    public void addDataStoreReference(DataStoreReference flowElement, FlowElementsContainer container);

    public void addSubProcess(SubProcess activity, FlowElementsContainer container);

    public List<String> getTransformationMessages();

    public void addSequenceFlow(SequenceFlow seq, FlowElementsContainer container);

    public void addParticipant(Participant participant, Process proc);

    public void addLane(Lane lane, LaneSet laneset, Lane parentLane, FlowElementsContainer container);

    public void addExclusiveGateway(ExclusiveGateway gateway, FlowElementsContainer container);

    public void addParallelGateway(ParallelGateway gateway, FlowElementsContainer container);

    public void addPartnerEntity(PartnerEntity entity);

    public void addServiceTask(ServiceTask activity, FlowElementsContainer container);

    public void addItemDefinition(ItemDefinition itemdef, List<Import> bpmnImports);

    public void addTaskDataFlows(Activity activity, FlowElementsContainer container, Map<String, String> predefinedDataForId);

	public void addInterface(Interface bpmnInterface);

	public void addRoutingSequenceFlows(FlowNode node, FlowElementsContainer process);

	public void addResource(Resource resource);

	public void finalizeTransformation(Definitions defs);

	public void addIntermediateCatchEvent(IntermediateCatchEvent event, FlowElementsContainer container);

	public void addIntermediateThrowEvent(IntermediateThrowEvent event, FlowElementsContainer container);

	public void addEventDataFlows(CatchEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId);

	public void addEventDataFlows(ThrowEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId);

	public void postTransformProcessStarts(Map<FlowElementsContainer, List<StartEvent>> startEventsPerContainer, Map<FlowElementsContainer, List<FlowNode>> potentialStartNodesPerContainer);

	public void addBoundaryEvent(BoundaryEvent event, FlowElementsContainer container);

	public void addGlobalTask(GlobalTask global);

	public void addCallActivity(CallActivity activity, FlowElementsContainer container);

	public Definitions getImportDefinitions(Import imp);

	public void addInclusiveGateway(InclusiveGateway gateway, FlowElementsContainer container);

	public void addGlobalCall(CallActivity caller, FlowElementsContainer container);

	void addProperty(Property property, Map<String, String> predefinedDataForId);

	public void addDataStore(DataStore data);

}
