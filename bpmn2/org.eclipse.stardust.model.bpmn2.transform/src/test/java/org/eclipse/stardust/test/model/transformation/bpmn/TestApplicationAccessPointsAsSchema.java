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

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.namespace.QName;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.model.bpmn2.extension.AccessPointSchemaWrapper;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.output.BPMNModelExporter;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.XSDType2Stardust;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.impl.type.XSDQNameType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * @author Simon Nikles
 *
 */
public class TestApplicationAccessPointsAsSchema extends Bpmn2StardustTestSuite {

	private final String modelFile = TEST_BPMN_MODEL_DIR + "_Refactor_ServiceTaskWebServiceApp.bpmn";

	private Definitions definitions = null;
	private ItemDefinition inputAccessPointDef = null; // see @before
	private ItemDefinition outputAccessPointDef = null;

    @Test
    public void testStructureRefEval() throws FileNotFoundException, IOException {
    	
    	System.out.println(inputAccessPointDef);
        XSDSchema inputSchema = ExtensionHelper2.INSTANCE.getEmbeddedSchemaExtension(inputAccessPointDef);
        
        AnyType inputStructureRef = (AnyType)inputAccessPointDef.getStructureRef();
        URI inputProxyURI = ((InternalEObject) inputStructureRef).eProxyURI();
        XSDElementDeclaration referencedElement = inputSchema.resolveElementDeclarationURI(inputProxyURI.toString());
        
//        XSDComplexTypeDefinition refType = (XSDComplexTypeDefinition)referencedElement.getType();
//        XSDComplexTypeContent content = refType.getContent();
        
        System.out.println("################################################");
        System.out.println("IS SYNTHETIC? " + ExtensionHelper2.INSTANCE.isSynthetic(inputAccessPointDef));
        System.out.println("Search Value for carnot:engine:endpointAddress: " + ExtensionHelper2.INSTANCE.getSchemaElementAttributeValue(referencedElement, "carnot:engine:endpointAddress", "http://www.eclipse.org/stardust", "browsable"));
        System.out.println("################################################");
        
        
        BPMNModelExporter.exportModel((Bpmn2Resource)definitions.eResource(), "c:/temp/my_out.xml.bpmn");
        
//        if ("sequence".equals(content.getElement().getLocalName())) {
//        	NodeList sequenceElements = content.getElement().getChildNodes();
//            for (int i = 0; i < sequenceElements.getLength(); i++) {
//            	Node item = sequenceElements.item(i);
//            	System.out.println(item.getNodeName() + " " + item.getLocalName() + " " + item.getAttributes().getNamedItem("name"));
//            	NamedNodeMap attributes = item.getAttributes();
//            	for (int at=0; at < attributes.getLength(); at++) {
//            		Node attribute = attributes.item(at);
//            		System.out.println("attribute.getBaseURI(): " + attribute.getBaseURI() 
//            						 + " attribute.getLocalName(): " + attribute.getLocalName()
//            						 + " attribute.getNamespaceURI(): " + attribute.getNamespaceURI() 
//            						 + " attribute.getNodeType(): " +  attribute.getNodeType()
//            						 + " attribute.getNodeName(): " + attribute.getNodeName()
//            						 + " attribute.getPrefix(): " + attribute.getPrefix()
//            						 + " attribute.getTextContent(): " + attribute.getTextContent()
//            				);
//            		
//            	}
//            }
//        }
    }

    @Test
    @Ignore
    public void checkNestedSchemaInfos() {
        XSDSchema inputSchema = ExtensionHelper2.INSTANCE.getEmbeddedSchemaExtension(inputAccessPointDef);
        XSDSchema outputSchema = ExtensionHelper2.INSTANCE.getEmbeddedSchemaExtension(outputAccessPointDef);

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

    @Test
    public void testModelExportAccessPointItemDefinition() {
    	final String filePath = this.getClass().getClassLoader().getResource(modelFile).getPath();
        final String targetFilePath = this.getClass().getClassLoader().getResource("models/").getPath().concat("test_export_accessPoint.bpmn");
        try {
            Bpmn2Resource modelResource = BPMNModelImporter.importModel(filePath);
            Definitions defs = BPMNModelImporter.getDefinitions(modelResource);
            
            ItemDefinition itemDef = Bpmn2Factory.eINSTANCE.createItemDefinition();
            itemDef.setId("StardustSyntheticItemDef_500");
            ExtensionHelper2.INSTANCE.createAccessPointItemDefinition(createDummyAccessPointInfo(), itemDef);
            defs.getRootElements().add(itemDef);
                        
            System.out.println("BPMNModelExporterTest.testModelExport() " + modelResource.getClass().toString());
            BPMNModelExporter.exportModel(modelResource, targetFilePath);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

	private AccessPointSchemaWrapper createDummyAccessPointInfo() {
		AccessPointSchemaWrapper schemaInfo = new AccessPointSchemaWrapper();
		XSDSimpleTypeDefinition simpleString = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
		QName xsdQname = XSDType2Stardust.STRING.getXSDQname();
		simpleString.setTargetNamespace(xsdQname.getNamespaceURI());
		simpleString.setName(xsdQname.getLocalPart());
		schemaInfo.addElement("myElementName", "sParam1", simpleString, "Anzeigename", null); 
		return schemaInfo;
	}
    
    
}
