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

import static org.junit.Assert.*;

import org.eclipse.stardust.engine.extensions.dms.data.DmsConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestDataStoreDataFlow2Stardust extends Bpmn2StardustTestSuite {

    private final String DATA_OBJECT_INT_INPUT_ID = "TestModelNumberDataInputObject";
    private final String DATA_OBJECT_STRING_OUTPUT_ID = "TestModelStringDataOutputObject";
	private final String DATA_INPUT_ASSOCIATION_ID = "TestModelNumberDataInputAssociationTaskA";
	private final String DATA_OUTPUT_ASSOCIATION_ID = "TestModelStringDataOutputAssociationTaskA";

	private final String OUTPUT_TASK_A = "DataOutputTaskA";
	private final String OUTPUT_ASSOC_TASK_A = "DataOutputAssocTaskA";
	private final String INPUT_TASK_B = "DataInputTaskB";
	private final String INPUT_ASSOC_TASK_B = "DataInputAssocTaskB";
	private final String OUTPUT_TASK_C = "DataOutputTaskC";
	private final String OUTPUT_ASSOC_TASK_C = "DataOutputAssocTaskC";
	private final String INPUT_TASK_D = "DataInputTaskD" ;
	private final String INPUT_ASSOC_TASK_D = "DataInputAssocTaskD";

	private final String DS_SIMPLE = "DmsDataStoreSimple";
	private final String DS_TYPED = "TypedDmsDocDatastore";

	private final String ITEM_DEFINITION_TYPED_DMS = "TypedDmsDocItemDefinition";
	private final String EMBEDDED_XSD_COMPLEX_TYPE = "EmbeddedXSDType";
	private final String EMBEDDED_XSD_ELEMENT = "XSDDataStructure1";
	private final String EMBEDDED_TYPE_ITEM_DFINITION_ID = "TypedDmsDocItemDefinition";

    private final String modelFile = TEST_BPMN_MODEL_DIR + "DataStoreEmbeddedType.bpmn";
    private final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testDataStoreEmbeddedType.xpdl";

    @Test
    public void testDataStore() {
    	// MODEL
    	ModelType result = transformModel(loadBpmnModel(modelFile), fileOutput);
    	assertNotNull(result);

    	// PROCESS
        ProcessDefinitionType process = CarnotModelQuery.findProcessDefinition(result, TEST_ID_MAIN_PROCESS);
        assertNotNull(process);

        // TASK
        ActivityType activityA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType activityB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType activityC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);
        ActivityType activityD = CarnotModelQuery.findActivity(process, TEST_ID_TASK_D);
        assertNotNull(activityA);
        assertNotNull(activityB);
        assertNotNull(activityC);
        assertNotNull(activityD);

        // TASK INPUT/OUTPUT
        DataMappingType outMappingTaskA = CarnotModelQuery.getDataMapping(activityA, OUTPUT_ASSOC_TASK_A);
        DataMappingType inMappingTaskB = CarnotModelQuery.getDataMapping(activityB, INPUT_ASSOC_TASK_B);
        DataMappingType outMappingTaskC = CarnotModelQuery.getDataMapping(activityC, OUTPUT_ASSOC_TASK_C);
        DataMappingType inMappingTaskD = CarnotModelQuery.getDataMapping(activityD, INPUT_ASSOC_TASK_D);

        assertNotNull(outMappingTaskA);
        assertNotNull(inMappingTaskB);
        assertNotNull(outMappingTaskC);
        assertNotNull(inMappingTaskD);

        // DATA(STORES)
        DataType simpleDmsDoc = CarnotModelQuery.findVariable(result, DS_SIMPLE);
        DataType typedDmsDoc = CarnotModelQuery.findVariable(result, DS_TYPED);
        assertNotNull(simpleDmsDoc);
        assertNotNull(typedDmsDoc);

        // Datamapping source / target
        assertTrue(mappingHasTarget(outMappingTaskA, simpleDmsDoc));
        assertTrue(mappingHasSource(inMappingTaskB, simpleDmsDoc));
        assertTrue(mappingHasTarget(outMappingTaskC, typedDmsDoc));
        assertTrue(mappingHasSource(inMappingTaskD, typedDmsDoc));

        // Embedded schema
        TypeDeclarationType typeDeclaration = CarnotModelQuery.findTypeDeclaration(result, ITEM_DEFINITION_TYPED_DMS);
        XSDSchema schema = typeDeclaration.getSchema();
        assertNotNull(schema);
        assertTrue(schemaContainsType(schema, EMBEDDED_XSD_COMPLEX_TYPE));
        assertTrue(schemaContainsElement(schema, EMBEDDED_XSD_ELEMENT));

        AttributeType typeValue = AttributeUtil.getAttribute(typedDmsDoc, DmsConstants.RESOURCE_METADATA_SCHEMA_ATT);
        assertEquals(EMBEDDED_TYPE_ITEM_DFINITION_ID, typeValue.getValue());

        // TODO ADD AND CHECK DATASTORE-ATTRIBUTES
    }

	private boolean mappingHasTarget(DataMappingType dataMapping, DataType data) {
		return data.equals(dataMapping.getData());
	}

	private boolean mappingHasSource(DataMappingType dataMapping, DataType data) {
		return data.equals(dataMapping.getData());
	}

	private boolean schemaContainsType(XSDSchema schema, String typeName) {
		for (XSDTypeDefinition type : schema.getTypeDefinitions()) {
			if (typeName.equals(type.getName())) return true;
		}
		return false;
	}

	private boolean schemaContainsElement(XSDSchema schema, String elementName) {
		for (XSDElementDeclaration element : schema.getElementDeclarations()) {
			if (elementName.equals(element.getName())) return true;
		}
		return false;
	}

}
