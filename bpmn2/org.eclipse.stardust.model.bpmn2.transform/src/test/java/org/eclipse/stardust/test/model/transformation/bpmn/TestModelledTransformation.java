package org.eclipse.stardust.test.model.transformation.bpmn;

import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_BPMN_MODEL_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertNotNull;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.junit.Test;

public class TestModelledTransformation {
	
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
    
}
