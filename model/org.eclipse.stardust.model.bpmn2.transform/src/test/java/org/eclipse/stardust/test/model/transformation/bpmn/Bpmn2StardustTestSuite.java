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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.transform.TransformationControl;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.DialectStardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control.TransitionUtil;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.junit.BeforeClass;

/**
 * @author Simon Nikles
 *
 */
//@RunWith(Suite.class)
//@Suite.SuiteClasses(
//        {TestUserTaskDataFlow2Stardust.class,
//         TestGateways2Stardust.class,
//         TestSequences2Stardust.class,
//         TestStartEvents2Stardust.class,
//         TestSubprocesses2Stardust.class,
//         TestSwimlanes2Stardust.class,
//         TestTasks2Stardust.class,
//         TestWebServiceTask2Stardust.class,
//         TestUserTaskWebApp2Stardust.class,
//         TestSimpleTypeDataFlow2Stardust.class,
//         TestSequencesUncontrolledFlow2Stardust.class,
//         TestSequencesConditionalWithDefault2Stardust.class,
//         TestSequencesConditionalWithDefaultAndUnconditional2Stardust.class,
//         TestSequencesConditionalPlusGateways2Stardust.class,
//         TestConvergingGatewaySequenceWithConditionalSequences2Stardust.class,
//         TestMessageEvents2Stardust.class,
//         TestProcessStart2Stardust.class,
//         TestIntermediateTimerEvent2Stardust.class,
//         TestBoundaryEvents2Stardust.class,
//         TestDataStoreDataFlow2Stardust.class,
//         TestCallActivities2Stardust.class,
//         TestSendReceiveTasks2Stardust.class,
//
//        })
public class Bpmn2StardustTestSuite {

    public static final String TEST_BPMN_MODEL_DIR = "models/bpmn/";
    public static final String TEST_MODEL_OUTPUT_DIR = "models/output/";
    public static final String TEST_ID_START_EVENT = "TestModelStartEventId";
    public static final String TEST_ID_START_EVENT_TIMER_DATE = "TestModelTimerStartEventTime";
    public static final String TEST_ID_START_EVENT_TIMER_CYCLE_STOP = "TestModelStartEventCycleStop";

    public static final String TEST_ID_TASK_A = "TestModelTaskA";
    public static final String TEST_ID_TASK_B = "TestModelTaskB";
    public static final String TEST_ID_TASK_C = "TestModelTaskC";
    public static final String TEST_ID_TASK_D = "TestModelTaskD";
    public static final String TEST_ID_TASK_E = "TestModelTaskE";
    public static final String TEST_ID_SEQUENCE_A_TO_B = "TestModelSequenceAtoB";
    public static final String TEST_ID_CONDITIONAL_SEQUENCE = "TestModelConditionalSequenceFlow1";
    public static final String TEST_ID_DEFAULT_SEQUENCE = "TestModelDefaultSequenceFlow";

    public static final String TEST_ID_XOR_MIXED_GATEWAY = "TestModelXORMixedGateway";
    public static final String TEST_ID_SEQUENCE_B2GATE = "SeqB2Gate";
    public static final String TEST_ID_SEQUENCE_C2GATE = "SeqC2Gate";
    public static final String TEST_ID_SEQUENCE_GATE2D = "SeqGate2D";
    public static final String TEST_ID_SEQUENCE_GATE2E = "SeqGate2E";

    public static final String TEST_ID_SUBPROCESS = "TestModelSubProcess";
    public static final String TEST_ID_MAIN_PROCESS = "TestModelMainProcess";
    public static final String TEST_ID_PARTNER_ENTITY_ORG_A = "TestOrganisationA";
    public static final String TEST_ID_RESOURCE_ROLE_A = "TestPerformerRoleA";

    public Bpmn2StardustTestSuite() {
    }

    @BeforeClass
    public static void before() {
        createOutputDir();
    }

    private static void createOutputDir() {
        String path = TestSequences2Stardust.class.getClassLoader().getResource("").getPath();
        path += "/" + TEST_MODEL_OUTPUT_DIR;
        File f = new File(path);
        if (!f.exists()) f.mkdir();
    }


    public static ModelType transformModel(Definitions definitions, String fileOutput) {
        TransformationControl transf = TransformationControl.getInstance(new DialectStardustXPDL());
        try {
            FileOutputStream targetFile = new FileOutputStream(fileOutput);
            try {
                transf.transformToTarget(definitions, targetFile);
            } finally {
                targetFile.close();
            }
        } catch (IOException ioe) {
            throw new RuntimeException("Failed transforming model.", ioe);
        }
        return (ModelType)transf.getTargetModel();
    }

    public static Definitions loadBpmnModel(String bpmnFile) {
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

    public static Definitions loadBpmnModelWithXsd(String bpmnFile) throws FileNotFoundException, IOException {
        String path = getResourceFilePath(bpmnFile);
        Definitions definitions = null;
        Bpmn2Resource bpmnModel = BPMNModelImporter.importModel(path);
		definitions = BPMNModelImporter.getDefinitions(bpmnModel);

        return definitions;
    }

    public static String getResourceFilePath(String relativePath) {
        URL fileUri = Bpmn2StardustTestSuite.class.getClassLoader().getResource(relativePath);
        return fileUri.getPath();
    }


    public static boolean transitionExistsBetween(ActivityType source, ActivityType target) {
    	for (TransitionType trans : source.getOutTransitions()) {
    		if (trans.getTo() == target) return true;
    	}
    	return false;
    }

    public static String transitionConditionBetween(ActivityType source, ActivityType target) {
    	String condition = "";
    	for (TransitionType trans : source.getOutTransitions()) {
    		if (trans.getTo() == target) {
    			condition = TransitionUtil.getTransitionExpression(trans);
    			break;
    		}
    	}
    	return condition;
    }

    public static boolean otherwiseConditionBetween(ActivityType source, ActivityType target) {
    	for (TransitionType trans : source.getOutTransitions()) {
    		if (trans.getTo() == target) {
    			return TransitionUtil.hasOtherwiseCondition(trans);
    		}
    	}
    	return false;
    }

    public static boolean noneIsNull(IModelElement ... elements) {
    	for (IModelElement element : elements) {
    		if (null == element) {
    			return false;
    		}
    	}
    	return true;
    }

    public static EventActionType getFirstEventActionOfType(EventHandlerType handler, EventActionTypeType actionType) {
    	for (EventActionType eventAction : handler.getEventAction()) {
    		if (eventAction.getType().equals(actionType)) {
    			return eventAction;
    		}
    	}
    	return null;
    }

    public static boolean eventHasActionType(EventHandlerType handler, EventActionTypeType actionType) {
    	for (EventActionType eventAction : handler.getEventAction()) {
    		if (eventAction.getType().equals(actionType)) {
    			return true;
    		}
    	}
    	return false;
    }

}
