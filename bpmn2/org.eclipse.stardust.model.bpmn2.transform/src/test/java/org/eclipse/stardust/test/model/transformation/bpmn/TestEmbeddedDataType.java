package org.eclipse.stardust.test.model.transformation.bpmn;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.junit.Test;

public class TestEmbeddedDataType extends Bpmn2StardustTestSuite {
	
	@Test
	public void testEmbeddedType() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "EmbeddedSchemaDataObject.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "EmbeddedSchemaDataObject.bpmn.xpdl";

        ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        
//        // TASK
//        ActivityType activity = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
//        assertNotNull(activity);
//
//        // VARIABLES - not predefined primitive string / int
//        DataType integerData = CarnotModelQuery.findVariable(result, DATA_OBJECT_INT_INPUT_ID);
//        DataType stringData = CarnotModelQuery.findVariable(result, DATA_OBJECT_STRING_OUTPUT_ID);
        

        
	}

}
