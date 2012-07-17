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
package org.eclipse.stardust.test.model.transformation.bpmn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.transform.TransformationControl;
import org.eclipse.stardust.model.bpmn2.transform.carnot.DialectCarnotXPDL;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;


public class Bpmn2CarnotXPDLTest extends TestCase {

	private static final String BPMN_MODEL_DIR = "models/bpmn/"; 
	private static final String OUTPUT_DIR = "models/output/";
	private static final String TEST_ID_START_EVENT = "TestModelStartEventId";
	private static final String TEST_ID_POOL_P1 = "TestModelPool1";
	private static final String TEST_ID_POOL_P2 = "TestModelPool2";
	private static final String TEST_ID_LANE_L1 = "TestModelLane1";
	private static final String TEST_ID_LANE_L2 = "TestModelLane2";	
	private static final String TEST_ID_TASK_A = "TestModelTaskA";
	private static final String TEST_ID_TASK_B = "TestModelTaskB";
	private static final String TEST_ID_TASK_C = "TestModelTaskC";
	private static final String TEST_ID_TASK_D = "TestModelTaskD";
	private static final String TEST_ID_TASK_E = "TestModelTaskE";
	private static final String TEST_ID_SEQUENCE_A_TO_B = "TestModelSequenceAtoB";
	private static final String TEST_ID_CONDITIONAL_SEQUENCE = "TestModelConditionalSequenceFlow1";
	private static final String TEST_ID_DEFAULT_SEQUENCE = "TestModelDefaultSequenceFlow";
	private static final String TEST_ID_XOR_SPLIT_GATEWAY = "TestModelXORSplitGateway";
	private static final String TEST_ID_XOR_JOIN_GATEWAY = "TestModelXORJoinGateway";
	private static final String TEST_ID_SUBPROCESS = "TestModelSubProcess";
	private static final String TEST_ID_MAIN_PROCESS = "TestModelMainProcess";
	private static final String TEST_ID_PARTNER_ENTITY_ORG_A = "TestOrganisationA";
	private static final String TEST_ID_RESOURCE_ROLE_A = "TestPerformerRoleA";
	
	public static Test suite() {
		TestSuite suite = new TestSuite(Bpmn2CarnotXPDLTest.class);
		createOutputDir();
		return suite;
	}
	

	public void testStartEventNone() {
		final String modelFile = BPMN_MODEL_DIR + "StartEventNone.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testStartEventNone.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);
		
		assertNotNull(process);
		assertNotNull(result);		
		TriggerType trigger = findTrigger(process, TEST_ID_START_EVENT);
		assertNotNull(trigger);
		assertEquals(PredefinedConstants.MANUAL_TRIGGER, trigger.getType().getId());
	}

	public void testStartEventMessage() {
		// TODO JMS_TRIGGER type is not available
		/*
		final String modelFile = BPMN_MODEL_DIR + "StartEventMessage.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testStartEventMessage.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);
		
		assertNotNull(process);
		assertNotNull(result);		
		TriggerType trigger = findTrigger(process, TEST_ID_START_EVENT);
		assertNotNull(trigger);
		assertEquals(PredefinedConstants.JMS_TRIGGER, trigger.getType().getId());
		*/		
	}
	
	public void testStartEventTimer() {
		final String modelFile = BPMN_MODEL_DIR + "StartEventTimer.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testStartEventTimer.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);
		
		assertNotNull(process);
		assertNotNull(result);		
		TriggerType trigger = findTrigger(process, TEST_ID_START_EVENT);
		assertNotNull(trigger);
		assertEquals(PredefinedConstants.TIMER_TRIGGER, trigger.getType().getId());
	}
	
	public void testUserTask() {
		final String modelFile = BPMN_MODEL_DIR + "UserTask.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testUserTask.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);
		
		assertNotNull(process);
		assertNotNull(result);		
		ActivityType activity = findActivity(process, TEST_ID_TASK_A);
		assertNotNull(activity);
		assertEquals(ActivityImplementationType.MANUAL_LITERAL, activity.getImplementation());		
	}

	public void testTaskPerformerAndOrganisation() {
		final String modelFile = BPMN_MODEL_DIR + "PerformerAndPartnerEntityOrg.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "PerformerAndPartnerEntityOrg.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);
		assertNotNull(process);
		assertNotNull(result);		

		IModelParticipant orgParticipant = findParticipant(result, TEST_ID_PARTNER_ENTITY_ORG_A);
		IModelParticipant resourceRole = findParticipant(result, TEST_ID_RESOURCE_ROLE_A);
		ActivityType taskA = findActivity(process, TEST_ID_TASK_A);
		assertNotNull(orgParticipant);
		assertNotNull(resourceRole);		
		assertNotNull(taskA);
		assertTrue(orgParticipant instanceof OrganizationType);
		assertTrue(resourceRole instanceof RoleType);
		
		assertNotNull(taskA.getPerformer());
		assertTrue(taskA.getPerformer().equals(resourceRole));
		
	}
	
	public void testPoolToOrganization() {
	}

	public void testLaneToRole() {
		/*final String modelFile = BPMN_MODEL_DIR + "Lane.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testLane.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);

		assertNotNull(result);		
		IModelParticipant participant = findParticipant(result, TEST_ID_LANE_L1);
		assertNotNull(participant);
		assertTrue(participant instanceof RoleType); */
	}
	
	public void testPerformerDerivedFromPoolAndLane() {
	}

	public void testSequenceActivityToActivity(ActivityType taskA, ActivityType taskB, ProcessDefinitionType processDef) {
		TransitionType sequenceFlow = findTransition(processDef, TEST_ID_SEQUENCE_A_TO_B);
		assertNotNull(sequenceFlow);
		assertNotNull(sequenceFlow.getFrom());
		assertNotNull(sequenceFlow.getTo());
		assertEquals(taskA, sequenceFlow.getFrom());
		assertEquals(taskB, sequenceFlow.getTo());
	}
	
	public void testXORGatewayOneSplitOneMerge() {
		final String modelFile = BPMN_MODEL_DIR + "XORGatewaysSingle.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testXORGatewaysSingle.xpdl";		
		final String testCondition = "test='Condition to B'";
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);		
		
		// A -> B, C or D -> E
		// A has condition, D is default path
		ActivityType taskA = findActivity(process, TEST_ID_TASK_A);
		ActivityType taskB = findActivity(process, TEST_ID_TASK_B);
		ActivityType taskC = findActivity(process, TEST_ID_TASK_C);
		ActivityType taskD = findActivity(process, TEST_ID_TASK_D);
		ActivityType taskE = findActivity(process, TEST_ID_TASK_E);		
		
		TransitionType transitionAB = findTransition(process, TEST_ID_CONDITIONAL_SEQUENCE);
		TransitionType defaultTransitionAD = findTransition(process, TEST_ID_DEFAULT_SEQUENCE);

		assertNotNull(taskA);
		assertNotNull(taskB);
		assertNotNull(taskC);
		assertNotNull(taskD);
		assertNotNull(taskE);
		
		assertNotNull(transitionAB);
		assertNotNull(defaultTransitionAD);
		
		assertTrue(taskA.getOutTransitions().size()==3);
		assertTrue(taskE.getInTransitions().size()==3);
		
		assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
		assertEquals(JoinSplitType.XOR_LITERAL, taskE.getJoin());
		System.out.println("sequence condition AB " + transitionAB.getCondition());
		System.out.println("sequence condition AD " + defaultTransitionAD.getCondition());
		
		assertEquals(testCondition, transitionAB.getCondition());
		assertEquals(XMLConstants.CONDITION_OTHERWISE_VALUE, defaultTransitionAD.getCondition());
		
	}
	
	public void testCollapsedSubprocess() {
		final String modelFile = BPMN_MODEL_DIR + "CollapsedSubprocess.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testCollapsedSubprocess.xpdl";		

		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType mainprocess = findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
		ProcessDefinitionType subprocess = findProcessDefinition(result, TEST_ID_SUBPROCESS);		

		ActivityType taskA = findActivity(subprocess, TEST_ID_TASK_A);
		ActivityType subprocessActivity = findActivity(mainprocess, TEST_ID_SUBPROCESS);
		
		assertNotNull(mainprocess);
		assertNotNull(subprocessActivity);		
		assertNotNull(subprocess);
		assertNotNull(taskA);
				
		assertEquals(ActivityImplementationType.SUBPROCESS_LITERAL, subprocessActivity.getImplementation());
		assertEquals(subprocess, subprocessActivity.getImplementationProcess());

	}
	
	private ModelType transformModel(Definitions definitions, String fileOutput) {
		TransformationControl transf = TransformationControl.getInstance(new DialectCarnotXPDL());
		transf.transformToTarget(definitions, fileOutput);
		return (ModelType)transf.getTargetModel();
	}
	
	private Definitions loadBpmnModel(String bpmnFile) {
		String path = getResourceFilePath(bpmnFile);
		Definitions definitions = null;
		try {
			Bpmn2Resource bpmnModel = BPMNModelImporter.importModel(path);
			definitions = BPMNModelImporter.getDefinitions(bpmnModel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return definitions;
	}
	
	private String getResourceFilePath(String relativePath) {
		URL fileUri = getClass().getClassLoader().getResource(relativePath);
		return fileUri.getPath();
	}

	private static void createOutputDir() {
		String path = Bpmn2CarnotXPDLTest.class.getClassLoader().getResource("").getPath();
		path += "/" + OUTPUT_DIR;
		File f = new File(path);
		if (!f.exists()) f.mkdir();
	}
	
	private static ProcessDefinitionType findProcessInModel(ModelType model, String processId) {
		for (ProcessDefinitionType processDef : model.getProcessDefinition()) {
			if (processDef.getId().equals(processId)) return processDef;
		}
		return null;
	}

	private ProcessDefinitionType findProcessDefinition(ModelType model, String id) {
		for (ProcessDefinitionType processDef : model.getProcessDefinition()) {
			if (processDef.getId().equals(id)) return processDef;
		}
		return null;
	}

	private ActivityType findActivity(ProcessDefinitionType processDef, String id) {
		for (ActivityType activity : processDef.getActivity()) {
			if (activity.getId().equals(id)) return activity; 
		}
		return null;
	}

	private TransitionType findTransition(ProcessDefinitionType processDef, String id) {
		for (TransitionType transition : processDef.getTransition()) {
			if (transition.getId().equals(id)) return transition; 
		}
		return null;
	}

	private TriggerType findTrigger(ProcessDefinitionType processDef, String id) {
		for (TriggerType trigger : processDef.getTrigger()) {
			if (trigger.getId().equals(id)) return trigger;
		}
		return null;
	}

	private IModelParticipant findParticipant(ModelType model, String id) {
		for (RoleType role : model.getRole()) {
			if (role.getId().equals(id)) return role;
		}
		for (OrganizationType org : model.getOrganization()) {
			if (org.getId().equals(id)) return org;
		}
		return null;
	}

}
