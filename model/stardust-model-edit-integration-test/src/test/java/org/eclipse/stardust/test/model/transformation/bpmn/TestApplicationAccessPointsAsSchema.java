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

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.xsd.XSDComplexTypeContent;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.junit.Assert;
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

	private final String modelFile = "_Refactor_ServiceTaskWebServiceApp.bpmn";

	private Definitions definitions = null;
	private ItemDefinition inputAccessPointDef = null;
	private ItemDefinition outputAccessPointDef = null;

    @Test
    public void testStructureRefEval() {
// TODO TEST EMBEDDED SCHEMA SUPPORT
//        XSDSchema inputSchema = ExtensionHelper2.getInstance().getEmbeddedSchemaExtension(inputAccessPointDef);
//        AnyType inputStructureRef = (AnyType)inputAccessPointDef.getStructureRef();
//        URI inputProxyURI = ((InternalEObject) inputStructureRef).eProxyURI();
//        XSDElementDeclaration referencedElement = inputSchema.resolveElementDeclarationURI(inputProxyURI.toString());
//        XSDComplexTypeDefinition refType = (XSDComplexTypeDefinition)referencedElement.getType();
//        XSDComplexTypeContent content = refType.getContent();
//        if ("sequence".equals(content.getElement().getLocalName())) {
//        	NodeList sequenceElements = content.getElement().getChildNodes();
//            for (int i = 0; i < sequenceElements.getLength(); i++) {
//            	Node item = sequenceElements.item(i);
//            	System.out.println(item.getNodeName() + " " + item.getLocalName() + " " + item.getAttributes().getNamedItem("name"));
//            }
//        }
    	Assert.fail("TODO - ACCESS-POINTS/EMBEDDED SCHEMA");
    }

    @Test
    @Ignore
    public void checkNestedSchemaInfos() {
// TODO TEST EMBEDDED SCHEMA SUPPORT
//        XSDSchema inputSchema = ExtensionHelper2.getInstance().getEmbeddedSchemaExtension(inputAccessPointDef);
//        XSDSchema outputSchema = ExtensionHelper2.getInstance().getEmbeddedSchemaExtension(outputAccessPointDef);
//
//        AnyType inputStructureRef = (AnyType)inputAccessPointDef.getStructureRef();
//        AnyType outputStructureRef = (AnyType)outputAccessPointDef.getStructureRef();
//        System.out.println("inputStructureRef: " + inputStructureRef);
//        System.out.println("outputStructureRef: " + outputStructureRef);
//
//        URI inputProxyURI = ((InternalEObject) inputStructureRef).eProxyURI();
//        URI proxyNs = inputProxyURI.trimFragment();
//        String proxyFragment = inputProxyURI.fragment();
//
//        XSDElementDeclaration resolveElementDeclarationURI = inputSchema.resolveElementDeclarationURI(inputProxyURI.toString());
//        System.out.println(resolveElementDeclarationURI);
//
//        EList<XSDElementDeclaration> inElementDeclarations = inputSchema.getElementDeclarations();
//        for (XSDElementDeclaration inputElement : inElementDeclarations) {
//        	String qName = inputElement.getQName();
//        	System.out.println("Input Element QName: " + qName);
//        	System.out.println("Input Element Type QName: " + inputElement.getType().getQName());
//        }
//
//        Element element = inputSchema.getElement();
//        NodeList matches = element.getElementsByTagNameNS(proxyNs.toString(), proxyFragment);
//        System.out.println(element);
//        System.out.println("matches: " + matches.getLength());
//
//        EList<XSDTypeDefinition> inputTypeDefinitions = inputSchema.getTypeDefinitions();
//        for (XSDTypeDefinition inputAp : inputTypeDefinitions) {
//        	String qName = inputAp.getQName();
//        	System.out.println("TypeDefinition QName: " + qName);
//        }
    	Assert.fail("TODO - ACCESS-POINTS/EMBEDDED SCHEMA");
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
