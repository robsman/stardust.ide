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
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.beans.XMLConstants;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.transform.TransformationControl;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.DialectStardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
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
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


/**
 * @author Simon Nikles
 *
 */
public class Bpmn2CarnotXPDLTest extends TestCase {

	private static final String BPMN_MODEL_DIR = "models/bpmn/"; 
	private static final String OUTPUT_DIR = "models/output/";
	private static final String TEST_ID_START_EVENT = "TestModelStartEventId";
	private static final String TEST_ID_START_EVENT_TIMER_DATE = "TestModelTimerStartEventTime";
	private static final String TEST_ID_START_EVENT_TIMER_CYCLE_STOP = "TestModelStartEventCycleStop";	
	
	private static final String TEST_ID_TASK_A = "TestModelTaskA";
	private static final String TEST_ID_TASK_B = "TestModelTaskB";
	private static final String TEST_ID_TASK_C = "TestModelTaskC";
	private static final String TEST_ID_TASK_D = "TestModelTaskD";
	private static final String TEST_ID_TASK_E = "TestModelTaskE";
	private static final String TEST_ID_SEQUENCE_A_TO_B = "TestModelSequenceAtoB";
	private static final String TEST_ID_CONDITIONAL_SEQUENCE = "TestModelConditionalSequenceFlow1";
	private static final String TEST_ID_DEFAULT_SEQUENCE = "TestModelDefaultSequenceFlow";
		
	private static final String TEST_ID_XOR_MIXED_GATEWAY = "TestModelXORMixedGateway";
	private static final String TEST_ID_SEQUENCE_B2GATE = "SeqB2Gate";
	private static final String TEST_ID_SEQUENCE_C2GATE = "SeqC2Gate";
	private static final String TEST_ID_SEQUENCE_GATE2D = "SeqGate2D";
	private static final String TEST_ID_SEQUENCE_GATE2E = "SeqGate2E";	
	
	private static final String TEST_ID_SUBPROCESS = "TestModelSubProcess";
	private static final String TEST_ID_MAIN_PROCESS = "TestModelMainProcess";
	private static final String TEST_ID_PARTNER_ENTITY_ORG_A = "TestOrganisationA";
	private static final String TEST_ID_RESOURCE_ROLE_A = "TestPerformerRoleA";
	
	public static Test suite() {
		TestSuite suite = new TestSuite(Bpmn2CarnotXPDLTest.class);
		createOutputDir();
		return suite;
	}
	
	public void testSimpleSequence() throws FileNotFoundException, IOException {
		final String modelFile = "c:/temp/simple-sequence.bpmn";
		final String fileOutput = "c:/temp/simple-sequence.xpdl";
		Bpmn2Resource bpmnModel = BPMNModelImporter.importModel(modelFile);
		Definitions definitions = BPMNModelImporter.getDefinitions(bpmnModel);

		ModelType result = transformModel(definitions, fileOutput);
	}
	
	public void testStartEventNone() {
		final String modelFile = BPMN_MODEL_DIR + "StartEventNone.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testStartEventNone.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);
		
		assertNotNull(process);
		assertNotNull(result);		
		TriggerType trigger = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT);
		assertNotNull(trigger);
		assertEquals(PredefinedConstants.MANUAL_TRIGGER, trigger.getType().getId());
	}

	public void testStartEventMessage() {
		// TODO JMS_TRIGGER type is not available

//		final String modelFile = BPMN_MODEL_DIR + "StartEventMessage.bpmn";
//		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testStartEventMessage.xpdl";		
//		
//		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
//		ProcessDefinitionType process = result.getProcessDefinition().get(0);
//		
//		assertNotNull(process);
//		assertNotNull(result);		
//		TriggerType trigger =  CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT);
//		assertNotNull(trigger);
//		assertEquals(PredefinedConstants.JMS_TRIGGER, trigger.getType().getId());

	}
	
	public void testStartEventTimer() {
		final String modelFile = BPMN_MODEL_DIR + "StartEventTimer.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testStartEventTimer.xpdl";		
		
		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);
		
		assertNotNull(process);
		assertNotNull(result);		
		TriggerType trigger = CarnotModelQuery.findTrigger(process, TEST_ID_START_EVENT);
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
		ActivityType activity = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
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

		IModelParticipant orgParticipant = CarnotModelQuery.findParticipant(result, TEST_ID_PARTNER_ENTITY_ORG_A);
		IModelParticipant resourceRole = CarnotModelQuery.findParticipant(result, TEST_ID_RESOURCE_ROLE_A);
		ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
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
		TransitionType sequenceFlow = CarnotModelQuery.findTransition(processDef, TEST_ID_SEQUENCE_A_TO_B);
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
		ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
		ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
		ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
		ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
		ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);		
		
		TransitionType transitionAB = CarnotModelQuery.findTransition(process, TEST_ID_CONDITIONAL_SEQUENCE);
		TransitionType defaultTransitionAD = CarnotModelQuery.findTransition(process, TEST_ID_DEFAULT_SEQUENCE);

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
		
		//assertEquals(testCondition, transitionAB.getCondition());
		assertEquals(XMLConstants.CONDITION_VALUE, transitionAB.getCondition());
		assertEquals(testCondition, transitionAB.getExpression().getMixed().getValue(0));
		assertEquals(XMLConstants.CONDITION_OTHERWISE_VALUE, defaultTransitionAD.getCondition());
		
	}
	
	public void testParallelGatewayOneSplitOneMerge() {
		final String modelFile = BPMN_MODEL_DIR + "ParallelGatewaysSingle.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testParallelGatewaysSingle.xpdl";		

		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);		
		
		// A -> B, C and D -> E
		ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
		ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
		ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
		ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
		ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);		
		
		assertNotNull(taskA);
		assertNotNull(taskB);
		assertNotNull(taskC);
		assertNotNull(taskD);
		assertNotNull(taskE);
		
		assertTrue(taskA.getOutTransitions().size()==3);
		assertTrue(taskE.getInTransitions().size()==3);
		
		assertEquals(JoinSplitType.AND_LITERAL, taskA.getSplit());
		assertEquals(JoinSplitType.AND_LITERAL, taskE.getJoin());

		for (TransitionType trans : taskA.getOutTransitions()) {
			assertEquals("CONDITION", trans.getCondition());
			assertEquals("true", trans.getExpression().getMixed().getValue(0));
		}
	}
	
	public void testMixedGateway() {
		// Gateway with two incoming and two outgoing sequence-flows
		// expected in stardust: 
		// - two 'gateways'
		// - one Route Activity (representing the Gateway)
		// -- having XOR Join and XOR Split 
		// - four transitions
		// -- b->Route, c->Route, Route->d (with condition), Route->e (with condition)
		
		final String modelFile = BPMN_MODEL_DIR + "MixedGateway.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testMixedGateway.xpdl";		

		final String expr4D = "expr4D";
		final String expr4E = "expr4E";

		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);		

		ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
		ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
		ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
		ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
		ActivityType taskE = CarnotModelQuery.findActivity(process, TEST_ID_TASK_E);		
		ActivityType route = CarnotModelQuery.findActivity(process, TEST_ID_XOR_MIXED_GATEWAY);

		assertNotNull(taskA);
		assertNotNull(taskB);
		assertNotNull(taskC);
		assertNotNull(taskD);
		assertNotNull(taskE);
		assertNotNull(route);

		assertEquals(JoinSplitType.NONE_LITERAL, taskB.getSplit());
		assertEquals(JoinSplitType.NONE_LITERAL, taskC.getSplit());
		assertEquals(JoinSplitType.NONE_LITERAL, taskD.getJoin());
		assertEquals(JoinSplitType.NONE_LITERAL, taskE.getJoin());
		
		assertEquals(JoinSplitType.XOR_LITERAL, route.getJoin());
		assertEquals(JoinSplitType.XOR_LITERAL, route.getSplit());
		
		TransitionType transitionB2G = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_B2GATE);
		TransitionType transitionC2G = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_C2GATE);
		TransitionType transitionG2D = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATE2D);
		TransitionType transitionG2E = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GATE2E);

		assertNotNull(transitionB2G);
		assertNotNull(transitionC2G);
		assertNotNull(transitionG2D);
		assertNotNull(transitionG2E);
				
		assertEquals(taskB, transitionB2G.getFrom());
		assertEquals(taskC, transitionC2G.getFrom());
		assertEquals(route, transitionB2G.getTo());
		assertEquals(route, transitionC2G.getTo());		
		assertEquals(route, transitionG2D.getFrom());
		assertEquals(route, transitionG2E.getFrom());
		assertEquals(taskD, transitionG2D.getTo());
		assertEquals(taskE, transitionG2E.getTo());
		
		assertEquals(expr4D, transitionG2D.getExpression().getMixed().getValue(0));
		assertEquals(expr4E, transitionG2E.getExpression().getMixed().getValue(0));
				
	}
	
	public void testSequentialWithMixedGateway() {
		// Sequence of Gateways (two diverging and one mixed)
		// expected in stardust:
		// - first gate as split on task a
		// - second gate as route with split
		// - third gate as route with join and split

		final String modelFile = BPMN_MODEL_DIR + "SequentialMixedGateway.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testSequentialMixedGateway.xpdl";		
		// ids
		final String TEST_ID_GATE_B = "TestModelGateB";
		final String TEST_ID_SEQUENCE_GA2GB = "TestModelSequenceGA2GB";		
		final String TEST_ID_SEQUENCE_GA2MIXED = "TestModelSequenceGA2MixedGate";
		final String TEST_ID_SEQUENCE_GB2B = "TestModelSequenceGB2B";				
		final String TEST_ID_SEQUENCE_GB2MIXED = "TestModelSequenceGB2MixedGate";		
		final String TEST_ID_SEQUENCE_MIXED2C = "TestModelSequenceMixedGate2C";		
		final String TEST_ID_SEQUENCE_MIXED2D = "TestModelSequenceMixedGate2D";
		
		final String x1 = "test='X=1'";
		final String x2 = "test='X=2'";
		final String y1 = "test='Y=1'";
		final String y2 = "test='Y=2'";
		final String z1 = "test='Z=1'";
		final String z2 = "test='Z=2'";

		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType process = result.getProcessDefinition().get(0);		

		ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
		ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
		ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
		ActivityType taskD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
		
		ActivityType routeA = CarnotModelQuery.findActivity(process, TEST_ID_GATE_B);
		ActivityType routeB = CarnotModelQuery.findActivity(process, TEST_ID_XOR_MIXED_GATEWAY);
		
		TransitionType transitionA2RouteA = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GA2GB); //x1
		TransitionType transitionA2RouteB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GA2MIXED); //x2
		TransitionType transitionRouteA2B = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GB2B); //y1
		TransitionType transitionRouteA2RouteB = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_GB2MIXED); //y2
		TransitionType transitionRouteB2C = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_MIXED2C); //z1
		TransitionType transitionRouteB2D = CarnotModelQuery.findTransition(process, TEST_ID_SEQUENCE_MIXED2D); //z2

		// Elements found?
		assertNotNull(taskA);
		assertNotNull(taskB);
		assertNotNull(taskC);
		assertNotNull(taskD);
		assertNotNull(routeA);
		assertNotNull(routeB);
		assertNotNull(transitionA2RouteA);
		assertNotNull(transitionA2RouteB);
		assertNotNull(transitionRouteA2B);
		assertNotNull(transitionRouteA2RouteB);
		assertNotNull(transitionRouteB2C);
		assertNotNull(transitionRouteB2D);
		
		// Join- and Split config
		assertEquals(JoinSplitType.XOR_LITERAL, taskA.getSplit());
		assertEquals(JoinSplitType.NONE_LITERAL, taskA.getJoin());
		assertEquals(JoinSplitType.XOR_LITERAL, routeA.getSplit());
		assertEquals(JoinSplitType.NONE_LITERAL, routeA.getJoin());
		assertEquals(JoinSplitType.XOR_LITERAL, routeB.getSplit());
		assertEquals(JoinSplitType.XOR_LITERAL, routeB.getJoin());		
		
		// Transition source/target
		assertEquals(taskA, transitionA2RouteA.getFrom());
		assertEquals(taskA, transitionA2RouteB.getFrom());
		assertEquals(routeA, transitionRouteA2B.getFrom());
		assertEquals(routeA, transitionRouteA2RouteB.getFrom());
		assertEquals(routeB, transitionRouteB2C.getFrom());
		assertEquals(routeB, transitionRouteB2D.getFrom());

		assertEquals(routeA, transitionA2RouteA.getTo());
		assertEquals(routeB, transitionA2RouteB.getTo());
		assertEquals(taskB, transitionRouteA2B.getTo());
		assertEquals(routeB, transitionRouteA2RouteB.getTo());
		assertEquals(taskC, transitionRouteB2C.getTo());
		assertEquals(taskD, transitionRouteB2D.getTo());
		
		// Transition conditions
		assertEquals(x1, transitionA2RouteA.getExpression().getMixed().getValue(0));
		assertEquals(x2, transitionA2RouteB.getExpression().getMixed().getValue(0));
		assertEquals(y1, transitionRouteA2B.getExpression().getMixed().getValue(0));
		assertEquals(y2, transitionRouteA2RouteB.getExpression().getMixed().getValue(0));
		assertEquals(z1, transitionRouteB2C.getExpression().getMixed().getValue(0));
		assertEquals(z2, transitionRouteB2D.getExpression().getMixed().getValue(0));
						
	}

	public void testCollapsedSubprocess() {
		final String modelFile = BPMN_MODEL_DIR + "CollapsedSubprocess.bpmn";
		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testCollapsedSubprocess.xpdl";		

		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
		ProcessDefinitionType mainprocess = CarnotModelQuery.findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
		ProcessDefinitionType subprocess = CarnotModelQuery.findProcessDefinition(result, TEST_ID_SUBPROCESS);		

		ActivityType taskA = CarnotModelQuery.findActivity(subprocess, TEST_ID_TASK_A);
		ActivityType subprocessActivity = CarnotModelQuery.findActivity(mainprocess, TEST_ID_SUBPROCESS);
		
		assertNotNull(mainprocess);
		assertNotNull(subprocessActivity);		
		assertNotNull(subprocess);
		assertNotNull(taskA);
				
		assertEquals(ActivityImplementationType.SUBPROCESS_LITERAL, subprocessActivity.getImplementation());
		assertEquals(subprocess, subprocessActivity.getImplementationProcess());

	}
	
	private ModelType transformModel(Definitions definitions, String fileOutput) {
		TransformationControl transf = TransformationControl.getInstance(new DialectStardustXPDL());
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

//	public void testMixedGateway() {
//		// Gateway with two incoming and two outgoing sequence-flows
//		// Version without additional Route (=>multiplied conditions, difficult to read) 
//		
//		final String modelFile = BPMN_MODEL_DIR + "MixedGateway.bpmn";
//		final String fileOutput = getResourceFilePath(OUTPUT_DIR) + "testMixedGateway.xpdl";		
//
//		final String exprBD = "expr4D";
//		final String exprBE = "expr4E";
//		final String exprCD = "expr4D";
//		final String exprCE = "expr4E";
//
//		ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
//		ProcessDefinitionType process = result.getProcessDefinition().get(0);		
//
//		ActivityType taskA = findActivity(process, TEST_ID_TASK_A);
//		ActivityType taskB = findActivity(process, TEST_ID_TASK_B);
//		ActivityType taskC = findActivity(process, TEST_ID_TASK_C);
//		ActivityType taskD = findActivity(process, TEST_ID_TASK_D);
//		ActivityType taskE = findActivity(process, TEST_ID_TASK_E);		
//
//		assertNotNull(taskA);
//		assertNotNull(taskB);
//		assertNotNull(taskC);
//		assertNotNull(taskD);
//		assertNotNull(taskE);
//		
//		assertEquals(JoinSplitType.XOR, taskB.getSplit());
//		assertEquals(JoinSplitType.XOR, taskC.getSplit());
//		
//		assertEquals(JoinSplitType.XOR, taskD.getJoin());
//		assertEquals(JoinSplitType.XOR, taskE.getJoin());
//		
//		EList<TransitionType> bOut = taskB.getOutTransitions();
//		EList<TransitionType> cOut = taskC.getOutTransitions();
//		
//		for (TransitionType trans : bOut) {
//			ActivityType target = trans.getTo();
//			if (target.equals(taskD)) {
//				assertEquals(exprBD, trans.getExpression().getMixed().getValue(0));
//			} else if (target.equals(taskE)) {
//				assertEquals(exprBE, trans.getExpression().getMixed().getValue(0));
//			} else {
//				fail("Invalid or unexpected Transition Target " + target);
//			}
//		}
//
//		for (TransitionType trans : cOut) {
//			ActivityType target = trans.getTo();
//			if (target.equals(taskD)) {
//				assertEquals(exprCD, trans.getExpression().getMixed().getValue(0));
//			} else if (target.equals(taskE)) {
//				assertEquals(exprCE, trans.getExpression().getMixed().getValue(0));
//			} else {
//				fail("Invalid or unexpected Transition Target " + target);
//			}
//		}
//	}
}
