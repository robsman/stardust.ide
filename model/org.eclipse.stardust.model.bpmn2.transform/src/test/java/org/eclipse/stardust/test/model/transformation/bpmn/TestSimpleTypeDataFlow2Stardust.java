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

import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_BPMN_MODEL_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_ID_TASK_A;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.TEST_MODEL_OUTPUT_DIR;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.getResourceFilePath;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.loadBpmnModel;
import static org.eclipse.stardust.test.model.transformation.bpmn.Bpmn2StardustTestSuite.transformModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestSimpleTypeDataFlow2Stardust {

    private final String TEST_PROCESS_ID = "TestModelProcessPrimitiveData";

    private final String DATA_OBJECT_INT_INPUT_ID = "TestModelNumberDataInputObject";
    private final String DATA_OBJECT_STRING_OUTPUT_ID = "TestModelStringDataOutputObject";
	private final String DATA_INPUT_ASSOCIATION_ID = "TestModelNumberDataInputAssociationTaskA";
	private final String DATA_OUTPUT_ASSOCIATION_ID = "TestModelStringDataOutputAssociationTaskA";

    private final String modelFile = TEST_BPMN_MODEL_DIR + "SimpleTypeDataFlow.bpmn";
    private final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testSimpleTypeDataFlow.xpdl";

    @Test
    public void testPrimitiveDataFlow() {
    	// MODEL
    	ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
    	assertNotNull(result);

    	// PROCESS
        ProcessDefinitionType process = CarnotModelQuery.findProcessDefinition(result, TEST_PROCESS_ID);
        assertNotNull(process);

        // TASK
        ActivityType activity = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        assertNotNull(activity);

        // VARIABLES - not predefined primitive string / int
        DataType integerData = CarnotModelQuery.findVariable(result, DATA_OBJECT_INT_INPUT_ID);
        DataType stringData = CarnotModelQuery.findVariable(result, DATA_OBJECT_STRING_OUTPUT_ID);

        assertNotNull(integerData);
        assertNotNull(stringData);

        String integerDataType = AttributeUtil.getAttributeValue(integerData, PredefinedConstants.TYPE_ATT);
        String stringDataType = AttributeUtil.getAttributeValue(stringData, PredefinedConstants.TYPE_ATT);

        assertFalse(integerData.isPredefined());
        assertFalse(integerData.isPredefined());

        assertEquals(Type.Integer.getId(), integerDataType);
        assertEquals(Type.String.getId(), stringDataType);

        assertEquals(PredefinedConstants.PRIMITIVE_DATA, integerData.getType().getId());
        assertEquals(PredefinedConstants.PRIMITIVE_DATA, stringData.getType().getId());

        // TASK INPUT/OUTPUT
        DataMappingType intInputMapping = CarnotModelQuery.getDataMapping(activity, DATA_INPUT_ASSOCIATION_ID);
        DataMappingType strOutputMapping = CarnotModelQuery.getDataMapping(activity, DATA_OUTPUT_ASSOCIATION_ID);

        assertNotNull(intInputMapping);
        assertNotNull(strOutputMapping);

        assertEquals(integerData, intInputMapping.getData());
        assertEquals(stringData, strOutputMapping.getData());

        // TODO VISIBILITY?
    }



}
