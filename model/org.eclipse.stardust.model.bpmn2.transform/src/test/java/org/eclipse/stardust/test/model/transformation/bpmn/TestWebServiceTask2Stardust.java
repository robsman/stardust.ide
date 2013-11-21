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

import org.eclipse.bpmn2.Definitions;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.struct.StructuredDataConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.junit.Test;


/**
 * @author Simon Nikles
 *
 */
public class TestWebServiceTask2Stardust extends Bpmn2StardustTestSuite {

	private static final String WEBSERVICE_APP_ID = "CROServiceApp";
	private static final String DATA_ASSOC_ID = "TestModelOutputAssociationTaskA";
	private static final String DATA_ASSOC_ASSIGNMENT_ID = "TestAssignmentOutputTaskA";
	private static final String DATA_ASSOC_ASSIGNMENT_EXT_FROM_ACCESS_POINT = "parameters_struct";
	private static final String DATA_ASSOC_ASSIGNMENT_FROM_FORMAL_EXPR = "return";
	private static final String DATA_OBJECT_ID = "TestModelDataObjectA";
	private static final String DATA_OBJECT_TYPE_ID = "getCROResponse";
	private static final String SERVICE_REQUEST_MESSAGE_TYPE_ID = "getCRO";
	private static final Object APPLICATION_NAME = "CROServiceApp";
	private static final Object WSDL_URL = "http://147.86.7.23:8080/ATHENE_WS/services/WSExportRDFService?wsdl";

    @Test
    public void testWebServiceTask() {
        final String modelFile = TEST_BPMN_MODEL_DIR + "ServiceTaskWebServiceApp.bpmn";
        final String fileOutput = getResourceFilePath(TEST_MODEL_OUTPUT_DIR) + "testServiceTaskWebServiceApp.xpdl";

        Definitions defs = loadBpmnModel(modelFile);
        ModelType result = transformModel(defs, fileOutput);

        ProcessDefinitionType process = result.getProcessDefinition().get(0);
        ActivityType activity = CarnotModelQuery.findActivity(process, TEST_ID_TASK_A);

        assertNotNull(activity);
        assertNotNull(process);
        assertNotNull(result);

        serviceTaskAssertions(activity);
        dataFlowAssertions(result, process, activity);
        dataObjectAssertions(result, process);
        dataDefinitionAssertions(result, process);
    }

    private void serviceTaskAssertions(ActivityType activity) {
        ApplicationType application = activity.getApplication();
        assertNotNull(application);

        assertEquals(ActivityImplementationType.APPLICATION_LITERAL, activity.getImplementation());

        assertEquals(WEBSERVICE_APP_ID, application.getId());
    	assertEquals(APPLICATION_NAME, application.getName());
    	assertEquals(10058, application.getElementOid());
    	assertEquals(PredefinedConstants.WS_APPLICATION, application.getType().getId());
    	assertEquals(WSDL_URL, AttributeUtil.getAttribute(application, PredefinedConstants.WS_WSDL_URL_ATT).getValue());

        for (AccessPointType ap : application.getAccessPoint()) {
        	assertNotNull(ap.getType());
        }
    }

    private void dataFlowAssertions(ModelType result, ProcessDefinitionType process, ActivityType activity) {
    	DataMappingType mapping = activity.getDataMapping().get(0);

    	assertNotNull(mapping);

    	assertEquals(DATA_ASSOC_ID + "_" + DATA_ASSOC_ASSIGNMENT_ID, mapping.getId());

    	assertEquals(DATA_ASSOC_ASSIGNMENT_EXT_FROM_ACCESS_POINT, mapping.getApplicationAccessPoint());

    	assertEquals(DATA_ASSOC_ASSIGNMENT_FROM_FORMAL_EXPR, mapping.getApplicationPath());

    	assertEquals(PredefinedConstants.APPLICATION_CONTEXT, mapping.getContext());

    	assertEquals(DATA_OBJECT_ID , mapping.getData().getId());

    }

    private void dataObjectAssertions(ModelType result, ProcessDefinitionType process) {
    	DataType variable = CarnotModelQuery.findVariable(result, DATA_OBJECT_ID);

    	assertNotNull(variable);

    	assertEquals(PredefinedConstants.STRUCTURED_DATA, variable.getType().getId());

    	assertEquals(DATA_OBJECT_TYPE_ID, AttributeUtil.getAttributeValue(variable, StructuredDataConstants.TYPE_DECLARATION_ATT));
    }

    private void dataDefinitionAssertions(ModelType result, ProcessDefinitionType process) {
    	TypeDeclarationType declarationServiceInputType = CarnotModelQuery.findTypeDeclaration(result, SERVICE_REQUEST_MESSAGE_TYPE_ID);
    	TypeDeclarationType declarationServiceOutputType = CarnotModelQuery.findTypeDeclaration(result, DATA_OBJECT_TYPE_ID);

    	assertNotNull(declarationServiceInputType);

    	assertNotNull(declarationServiceOutputType);
    }




}
