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

import java.util.List;

import org.eclipse.bpmn2.DataObject;
import org.eclipse.bpmn2.DataObjectReference;
import org.eclipse.bpmn2.DataStoreReference;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.InputOutputBinding;
import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.UserTask;

/**
 * @author Simon Nikles
 *
 */
public interface Transformator {

	public void createTargetModel(Definitions definitions);
	
	public Object getTargetModel();
	
	public void addProcess(Process process);

	public void addUserTask(UserTask task, FlowElementsContainer container);
	
	public void addStartEvent(StartEvent event, FlowElementsContainer container);
	
	public void addEndEvent(EndEvent event, FlowElementsContainer container);
	
	public void serializeTargetModel(String outputFile);

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


}
