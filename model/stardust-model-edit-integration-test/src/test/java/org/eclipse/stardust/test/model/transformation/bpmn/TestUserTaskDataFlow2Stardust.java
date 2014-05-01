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
import static org.junit.Assert.assertTrue;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.ui.web.modeler.bpmn2.utils.ModelInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestUserTaskDataFlow2Stardust extends Bpmn2StardustTestSuite {

    private final String TEST_PROCESS_ID = "TestProcessDataObjectFlow";
    private final String ITEM_DEFINITION_ID = "TestImportedXmlItemDefinition";
    private final String DATA_OBJECT_ID = "TestModelDataObjectA";
    private final String ASSOCIATION_TRANSFORM_EXPRESSION = "Name";
    private final String IMPORT_NAMESPACE = "http://stardust.eclipse.org/Customer";
    private final String IMPORT_LOCATION = "Customer.xsd";
    private ModelType resultModel;
    private Definitions bpmnDefs;
    private final String modelFile = "DataObjectFlow.bpmn";


//    public TestUserTaskDataFlow2Stardust() {
//      super("models/bpmn/dataFlow");
//    }

    @Test
    public void testImportedItemDefinition() {
        ItemDefinition bpmnItemDef = ModelInfo.getItemDef(bpmnDefs, ITEM_DEFINITION_ID);
        EObject structRef = (EObject)bpmnItemDef.getStructureRef();
        TypeDeclarationType typeDeclaration = CarnotModelQuery.findTypeDeclaration(resultModel, ITEM_DEFINITION_ID);

        assertNotNull(typeDeclaration);
        assertTrue(structRef.eIsProxy());

        String uriFragment = ((InternalEObject)structRef).eProxyURI().fragment();
        // external reference
        assertEquals(IMPORT_NAMESPACE, typeDeclaration.getExternalReference().getNamespace());
        assertEquals(IMPORT_LOCATION, typeDeclaration.getExternalReference().getLocation());
        assertEquals("{" + IMPORT_NAMESPACE + "}" + uriFragment, typeDeclaration.getExternalReference().getXref());
    }

    @Test
    public void testDataObject2DataField() {
        DataType data = CarnotModelQuery.findVariable(resultModel, DATA_OBJECT_ID);
        assertNotNull(data);
        assertEquals(ITEM_DEFINITION_ID, AttributeUtil.getAttributeValue(data, StructuredDataConstants.TYPE_DECLARATION_ATT));
    }

    @Test
    public void testDataAssociation2DataMapping() {
        // TestModelInputAssociationTaskC has a transformation attribute expression (value = ASSOCIATION_TRANSFORM_EXPRESSION)
        // TestModelOutputAssociationTaskC has an Assignment attribute which has a 'to' expression (value = ASSOCIATION_TRANSFORM_EXPRESSION)
        // dataflow is from TaskA (Out) to TaskB (In) to TaskC (In - Out)

        ProcessDefinitionType process = CarnotModelQuery.findProcessDefinition(resultModel, TEST_PROCESS_ID);
        if (process == null) Assert.fail("Process definition not found");
        ActivityType taskA = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);
        ActivityType taskB = CarnotModelQuery.findActivity(process, TEST_ID_TASK_B);
        ActivityType taskC = CarnotModelQuery.findActivity(process, TEST_ID_TASK_C);

        DataMappingType outputTaskA = getOutputMapping(taskA);
        DataMappingType inputTaskB = getInputMapping(taskB);
        DataMappingType inputTaskC = getInputMapping(taskC);
        DataMappingType outputTaskC = getOutputMapping(taskC);

        assertNotNull(outputTaskA);
        assertNotNull(inputTaskB);
        assertNotNull(inputTaskC);
        assertNotNull(outputTaskC);

        assertEquals(ASSOCIATION_TRANSFORM_EXPRESSION, inputTaskC.getDataPath());
        assertEquals(ASSOCIATION_TRANSFORM_EXPRESSION, outputTaskC.getDataPath());

        assertEquals(DATA_OBJECT_ID, outputTaskA.getData().getId());
        assertEquals(DATA_OBJECT_ID, inputTaskB.getData().getId());
        assertEquals(DATA_OBJECT_ID, inputTaskC.getData().getId());
        assertEquals(DATA_OBJECT_ID, outputTaskC.getData().getId());
    }

    private DataMappingType getOutputMapping(ActivityType task) {
        for (DataMappingType map : task.getDataMapping()) {
            if (map.getDirection().equals(DirectionType.OUT_LITERAL)) return map;
        }
        return null;
    }

    private DataMappingType getInputMapping(ActivityType task) {
        for (DataMappingType map : task.getDataMapping()) {
            if (map.getDirection().equals(DirectionType.IN_LITERAL)) return map;
        }
        return null;
    }

    @Before
    public void transform() {
        bpmnDefs = loadBpmnModel(modelFile);
        resultModel = transformModel(bpmnDefs);
    }
}
