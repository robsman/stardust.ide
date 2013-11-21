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

import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ABORT_ACTION_SCOPE_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.ABORT_ACTIVITY_ACTION;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.EXCEPTION_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.SET_DATA_ACTION;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_NAME_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.SET_DATA_ACTION_ATTRIBUTE_PATH_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.SET_DATA_ACTION_DATA_ID_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.TARGET_TIMESTAMP_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.TIMER_PERIOD_ATT;
import static org.eclipse.stardust.engine.api.model.PredefinedConstants.EXCEPTION_CLASS_ATT;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_EVENT_FIRED_CONDITION;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_EVENT_NOT_FIRED_CONDITION;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX;
import static org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event.BoundaryEvent2Stardust.CONTROL_FLOW_VAR_SUFFIX;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.engine.core.runtime.beans.AbortScope;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author Simon Nikles
 *
 */
public class TestApplicationAccessPointsAsSchema extends Bpmn2StardustTestSuite {

	private final String modelFile = TEST_BPMN_MODEL_DIR + "_Refactor_ServiceTaskWebServiceApp.bpmn";

	private Definitions definitions = null;
	private ItemDefinition inputAccessPointDef = null;
	private ItemDefinition outputAccessPointDef = null;

    @Test
    public void testStructureRefEval() {
        XSDSchema inputSchema = ExtensionHelper2.getInstance().getEmbeddedSchemaExtension(inputAccessPointDef);
        AnyType inputStructureRef = (AnyType)inputAccessPointDef.getStructureRef();
        URI inputProxyURI = ((InternalEObject) inputStructureRef).eProxyURI();
        XSDElementDeclaration referencedElement = inputSchema.resolveElementDeclarationURI(inputProxyURI.toString());
        XSDComplexTypeDefinition refType = (XSDComplexTypeDefinition)referencedElement.getType();
        XSDComplexTypeContent content = refType.getContent();
        if ("sequence".equals(content.getElement().getLocalName())) {
        	NodeList sequenceElements = content.getElement().getChildNodes();
            for (int i = 0; i < sequenceElements.getLength(); i++) {
            	Node item = sequenceElements.item(i);
            	System.out.println(item.getNodeName() + " " + item.getLocalName() + " " + item.getAttributes().getNamedItem("name"));
            }
        }
    }

    @Test
    @Ignore
    public void checkNestedSchemaInfos() {
        XSDSchema inputSchema = ExtensionHelper2.getInstance().getEmbeddedSchemaExtension(inputAccessPointDef);
        XSDSchema outputSchema = ExtensionHelper2.getInstance().getEmbeddedSchemaExtension(outputAccessPointDef);

        AnyType inputStructureRef = (AnyType)inputAccessPointDef.getStructureRef();
        AnyType outputStructureRef = (AnyType)outputAccessPointDef.getStructureRef();
        System.out.println("inputStructureRef: " + inputStructureRef);
        System.out.println("outputStructureRef: " + outputStructureRef);

        URI inputProxyURI = ((InternalEObject) inputStructureRef).eProxyURI();
        URI proxyNs = inputProxyURI.trimFragment();
        String proxyFragment = inputProxyURI.fragment();

        XSDElementDeclaration resolveElementDeclarationURI = inputSchema.resolveElementDeclarationURI(inputProxyURI.toString());
        System.out.println(resolveElementDeclarationURI);

        EList<XSDElementDeclaration> inElementDeclarations = inputSchema.getElementDeclarations();
        for (XSDElementDeclaration inputElement : inElementDeclarations) {
        	String qName = inputElement.getQName();
        	System.out.println("Input Element QName: " + qName);
        	System.out.println("Input Element Type QName: " + inputElement.getType().getQName());
        }

        Element element = inputSchema.getElement();
        NodeList matches = element.getElementsByTagNameNS(proxyNs.toString(), proxyFragment);
        System.out.println(element);
        System.out.println("matches: " + matches.getLength());

        EList<XSDTypeDefinition> inputTypeDefinitions = inputSchema.getTypeDefinitions();
        for (XSDTypeDefinition inputAp : inputTypeDefinitions) {
        	String qName = inputAp.getQName();
        	System.out.println("TypeDefinition QName: " + qName);
        }

    }

    @Before
	public void initAccessPointDefinitions() {
        definitions = loadBpmnModel(modelFile);
        for (RootElement root : definitions.getRootElements()) {
        	if (root instanceof ItemDefinition) {
        		if (root.getId().equals("apInItem")) {
        			inputAccessPointDef = (ItemDefinition)root;
        		} else if (root.getId().equals("apOutItem")) {
        			outputAccessPointDef = (ItemDefinition)root;
        		}
        	}
        }
	}

}
