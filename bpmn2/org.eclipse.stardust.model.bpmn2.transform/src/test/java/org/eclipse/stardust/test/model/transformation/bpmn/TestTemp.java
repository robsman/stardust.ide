package org.eclipse.stardust.test.model.transformation.bpmn;

import static org.junit.Assert.assertNotNull;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.junit.Test;

public class TestTemp extends Bpmn2StardustTestSuite {

    private final String modelFile = TEST_BPMN_MODEL_DIR + "process_1.bpmn";
    private final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testprocess_1.bpmn.xpdl";

    private ModelType resultModel;
    private Definitions bpmnDefs;

    public TestTemp() {
        transform();
    }

    @Test
    public void testImportedItemDefinition() {
    	assertNotNull(resultModel);
    }

    private void transform() {
        bpmnDefs = loadBpmnModel(modelFile);
        resultModel = transformModel(bpmnDefs, fileOutput);
    }

}
