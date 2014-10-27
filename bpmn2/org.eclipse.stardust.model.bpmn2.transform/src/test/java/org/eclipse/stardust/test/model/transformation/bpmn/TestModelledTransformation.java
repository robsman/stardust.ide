package org.eclipse.stardust.test.model.transformation.bpmn;

import static org.junit.Assert.assertNotNull;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.junit.Test;

public class TestModelledTransformation extends Bpmn2StardustTestSuite {
	
    @Test
    public void transformP3() {
    	final String modelFile = TEST_BPMN_MODEL_DIR + "modelled/process_3.bpmn";
    	final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "process_3.bpmn.xpdl";
    	ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
    	assertNotNull(result);
    }
    
    @Test
    public void transformP1() {
    	final String modelFile = TEST_BPMN_MODEL_DIR + "modelled/process_1.bpmn";
    	final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "process_1.bpmn.xpdl";
    	ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
    	assertNotNull(result);
    }

    @Test
    public void testServiceTaskModel() {
    	final String modelFile = TEST_BPMN_MODEL_DIR + "bpmn2_stardust_runtime_models/ServiceTaskWebServiceApp.bpmn";
    	final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "ModelledServiceTaskWebServiceApp.bpmn.xpdl";
    	ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
    	assertNotNull(result);
    }

    @Test
    public void testTimerStartModel() {
    	final String modelFile = TEST_BPMN_MODEL_DIR + "bpmn2_stardust_runtime_models/StartEventTimer.bpmn";
    	final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "ModelledStartEventTimer.bpmn.xpdl";
    	ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
    	assertNotNull(result);
    }

}
