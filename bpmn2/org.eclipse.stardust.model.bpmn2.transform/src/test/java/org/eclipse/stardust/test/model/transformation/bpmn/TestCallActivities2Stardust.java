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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestCallActivities2Stardust extends Bpmn2StardustTestSuite {

	private static final String CALLING_PROCESS = "CallingProcessId";
	private static final String CALL_ACTIVITY = "CallingProcessCallActivity";
	private static final String CALL_ACTIVITY_INPUT_ASSOCIATION = "CallingProcessDataObjectParam"; //"CallingProcessCallActivityInputAssociation";
	private static final String CALL_ACTIVITY_OUTPUT_ASSOCIATION = "CallingProcessDataObjectOutput"; //"CallingProcessCallActivityOutputAssociation";

	private static final String CALLING_PROCESS_DATA_OBJECT_PARAM = "CallingProcessDataObjectParam";
	private static final String CALLING_PROCESS_DATA_OBJECT_OUTPUT = "CallingProcessDataObjectOutput";

	private static final String CALLED_PROCESS = "CalledProcessId";

	private static final String CALLED_PROCESS_DATA_INPUT = "CalledProcessDataInput";
	private static final String CALLED_PROCESS_DATA_OUTPUT ="CalledProcessDataOutput";

	private static final String CALLED_PROCESS_TASK_A = "CalledProcessTaskA";
	private static final String CALLED_PROCESS_TASK_A_INPUT_ASSOCIATION = "CalledProcessDataInput"; // "CalledProcessTaskAInputAssociation";

	private static final String CALLED_PROCESS_TASK_B = "CalledProcessTaskB";
	private static final String CALLED_PROCESS_TASK_B_OUTPUT_ASSOCIATION = "CalledProcessDataOutput"; //"CalledProcessTaskBOutputAssociation";

    @Test
    public void testProcessCall() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "CallActivityProcessCall.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testCallActivityProcessCall.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType callingProcess = CarnotModelQuery.findProcessDefinition(result, CALLING_PROCESS);
        ProcessDefinitionType calledProcess = CarnotModelQuery.findProcessDefinition(result, CALLED_PROCESS);
        assertNotNull(result);
        assertNotNull(callingProcess);
        assertNotNull(calledProcess);

        // VARIABLES (two of the calling and two of the caller)
        DataType callerParam = CarnotModelQuery.findVariable(result, CALLING_PROCESS_DATA_OBJECT_PARAM);
        DataType callerResult = CarnotModelQuery.findVariable(result, CALLING_PROCESS_DATA_OBJECT_OUTPUT);
        assertNotNull(callerParam);
        assertNotNull(callerResult);

        DataType calledInput = CarnotModelQuery.findVariable(result, CALLED_PROCESS_DATA_INPUT);
        DataType calledOutput = CarnotModelQuery.findVariable(result, CALLED_PROCESS_DATA_OUTPUT);
        assertNotNull(calledInput);
        assertNotNull(calledOutput);

        // CALLER
        ActivityType callActivity = CarnotModelQuery.findActivity(callingProcess, CALL_ACTIVITY);
        assertNotNull(callActivity);
        assertEquals(SubProcessModeType.SYNC_SEPARATE_LITERAL, callActivity.getSubProcessMode());
        assertEquals(calledProcess, callActivity.getImplementationProcess());
        // Sub-Process input and output are mapped to the variables used as input/output in the called process
        DataMappingType callerInputMapping = CarnotModelQuery.getDataMapping(callActivity, CALL_ACTIVITY_INPUT_ASSOCIATION);
        DataMappingType callerOutputMapping = CarnotModelQuery.getDataMapping(callActivity, CALL_ACTIVITY_OUTPUT_ASSOCIATION);
        assertNotNull(callerInputMapping);
        assertNotNull(callerOutputMapping);
//        assertEquals(PredefinedConstants.DEFAULT_CONTEXT, callerInputMapping.getContext());
//        assertEquals(PredefinedConstants.DEFAULT_CONTEXT, callerOutputMapping.getContext());
        assertEquals(PredefinedConstants.ENGINE_CONTEXT, callerInputMapping.getContext());
        assertEquals(PredefinedConstants.ENGINE_CONTEXT, callerOutputMapping.getContext());
        assertEquals(callerParam, callerInputMapping.getData());
        assertEquals(callerResult, callerOutputMapping.getData());
        assertEquals(calledInput.getId(), callerInputMapping.getApplicationAccessPoint());
        assertEquals(calledOutput.getId(), callerOutputMapping.getApplicationAccessPoint());

        // CALLED PROCESS
        ActivityType taskA = CarnotModelQuery.findActivity(calledProcess, CALLED_PROCESS_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(calledProcess, CALLED_PROCESS_TASK_B);
        assertNotNull(taskA);
        assertNotNull(taskB);

        // Called process has two variables, one as data input for Task A, another as output of Task B
        DataMappingType calledInputMappingA = CarnotModelQuery.getDataMapping(taskA, CALLED_PROCESS_TASK_A_INPUT_ASSOCIATION);
        DataMappingType calledOutputMappingB = CarnotModelQuery.getDataMapping(taskB, CALLED_PROCESS_TASK_B_OUTPUT_ASSOCIATION);
        assertNotNull(calledInputMappingA);
        assertNotNull(calledOutputMappingB);
        assertEquals(PredefinedConstants.DEFAULT_CONTEXT, calledInputMappingA.getContext());
        assertEquals(PredefinedConstants.DEFAULT_CONTEXT, calledOutputMappingB.getContext());
        assertEquals(calledInput, calledInputMappingA.getData());
        assertEquals(calledOutput, calledOutputMappingB.getData());

    }

    @Test
    public void testGlobalTask() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "CallActivityGlobalTask.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testCallActivityGlobalTask.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType callingProcess = CarnotModelQuery.findProcessDefinition(result, CALLING_PROCESS);
        assertNotNull(result);
        assertNotNull(callingProcess);
//        ProcessDefinitionType calledProcess = CarnotModelQuery.findProcessDefinition(result, CALLED_PROCESS);
    }

}