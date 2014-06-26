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
package org.eclipse.stardust.test.model.bpmn2.sdbpmn;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.ExtensionAttributeValue;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.ProcessType;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.output.BPMNModelExporter;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAttributesType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class BpmnExtensionTest extends TestCase {

    private static final String USER_TASK_ID = "userTaskA";
    private static final String OID = "5000";
    private static final String START_EVENT_ID = "startEventA";

    private Bpmn2Factory fac = Bpmn2Factory.eINSTANCE;
    private SdbpmnFactory efac = SdbpmnFactory.eINSTANCE;
    private final String testModelPath = getResourceFilePath("models/") + "testModel.xml";
    private Definitions defs = null;

    public static Test suite() {
        TestSuite suite = new TestSuite(BpmnExtensionTest.class);
        return suite;
    }

    public void testExportAndReload() {
        try {
            initTestModel();
            populateTestModel();
            exportModel();
            reimportModel();
            readImportedExtensions();
        } catch(Exception e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

//	 public void testItemDefinition() throws Exception {
//			Bpmn2ResourceFactoryImpl rf = new Bpmn2ResourceFactoryImpl();
//			defs = rf.createAndInitResource(URI.createURI("http://www.itptest.ch/sdbpmn"));
//			defs.setTargetNamespace("http://www.itptest.ch/sdbpmn");
//
//	        Import imp = Bpmn2Factory.eINSTANCE.createImport();
//	        imp.setNamespace("http://stardust.eclipse.org/Customer");
//	        imp.setLocation("c:/temp/Customer.xsd");
//	        defs.getImports().add(imp);
//
//	        ItemDefinition item = Bpmn2Factory.eINSTANCE.createItemDefinition();
//	        item.setId("itemID");
//	        InternalEObject value = new DynamicEObjectImpl();
//	        //final URI uri = URI.createURI("c:/temp/Customer.xsd#Customer");
//	        final URI uri = URI.createURI("Customer.xsd#Customer");
//	        value.eSetProxyURI(uri);
//	        item.setStructureRef(value);
//	        defs.getRootElements().add(item);
//	        exportModel();
//	        reimportModel();
//	        Object t = ((ItemDefinition)defs.getRootElements().get(0)).getStructureRef();
//	        System.out.println("BpmnExtensionTest.testItemDefinition() " + ((InternalEObject)((ItemDefinition)defs.getRootElements().get(0)).getStructureRef()).eProxyURI());
//	        System.out.println("BpmnExtensionTest.testItemDefinition() " + ((InternalEObject)((ItemDefinition)defs.getRootElements().get(0)).getStructureRef()).eProxyURI().fragment());
//	        System.out.println("BpmnExtensionTest.testItemDefinition() "  + t);
//	    }


    private void initTestModel() {

        Bpmn2ResourceFactoryImpl rf = new Bpmn2ResourceFactoryImpl();
        defs = rf.createAndInitResource(URI.createURI("http://www.itptest.ch/sdbpmn"));
        defs.setTargetNamespace("http://www.itptest.ch/sdbpmn");
    }

    private void populateTestModel() {
        Process proc = fac.createProcess();
        defs.getRootElements().add(proc);

        StartEvent event = createTestStartEvent();
        UserTask utask = createTestUserTask();

        proc.setProcessType(ProcessType.PRIVATE);
        proc.getFlowElements().add(utask);
        proc.getFlowElements().add(event);
    }

    private UserTask createTestUserTask() {
        UserTask utask = fac.createUserTask();
        utask.setId(USER_TASK_ID);
        ExtensionHelper.getInstance().setUserTaskExtension(utask, createUserTaskExtension());
        return utask;
    }

    private StartEvent createTestStartEvent() {
        StartEvent event = fac.createStartEvent();
        event.setId(START_EVENT_ID);
        TimerEventDefinition timer = fac.createTimerEventDefinition();
        timer.setId("StartTimerDef");
        FormalExpression expr = fac.createFormalExpression();
        expr.setBody("2012-09-01T11:00:00/P1Y2M1DT20H30M");
        timer.setTimeCycle(expr);
        event.getEventDefinitions().add(timer);
        ExtensionHelper.getInstance().setTimerStartEventExtension(event, createTimerStartExtension());
        return event;
    }

	private StardustUserTaskType createUserTaskExtension() {
        StardustUserTaskType userTaskExtension = efac.createStardustUserTaskType();
        userTaskExtension.setAllowsAbortByPerformer(false);
        userTaskExtension.setElementOid(OID);
        userTaskExtension.setHibernateOnCreation(true);
        return userTaskExtension;
    }

    private StardustTimerStartEventType createTimerStartExtension() {
        StardustTimerStartEventType event = efac.createStardustTimerStartEventType();
        StardustAttributesType attr = efac.createStardustAttributesType();
        event.setStardustAttributes(attr);
        AttributeType attrType = AttributeUtil.createAttribute("carnot:engine:stopTime");
        attrType.setAttributeValue("long", "1347919200000");
        attr.getAttributeType().add(attrType);
        return event;
    }

    private void exportModel() throws IOException {
        BPMNModelExporter.exportModel((Bpmn2Resource) defs.eResource(), testModelPath);
    }

    private String getResourceFilePath(String relativePath) {
        URL fileUri = getClass().getClassLoader().getResource(relativePath);
        System.out.println("BpmnExtensionTest.getResourceFilePath() " + relativePath + " " + fileUri);
        return fileUri.getPath();
    }

    private void reimportModel() throws FileNotFoundException, IOException {
        Bpmn2Resource modelResource = BPMNModelImporter.importModel(testModelPath);
        this.defs = BPMNModelImporter.getDefinitions(modelResource);
    }

    private void readImportedExtensions() {

        UserTask task = (UserTask)findFlowElement(defs, USER_TASK_ID);
        assertNotNull(task);

        ExtensionAttributeValue extensionAttributeValue = task.getExtensionValues().get(0);
        assertNotNull(extensionAttributeValue);

        FeatureMap extensionElements = extensionAttributeValue.getValue();
        assertNotNull(extensionElements);

        StardustUserTaskType taskExtension = ExtensionHelper.getInstance().getUserTaskExtension(task);
        assertNotNull(taskExtension);
        assertEquals(false, taskExtension.isAllowsAbortByPerformer());
        assertEquals(OID, taskExtension.getElementOid());
        assertEquals(true, taskExtension.isHibernateOnCreation());

        StartEvent event = (StartEvent)findFlowElement(defs, START_EVENT_ID);
        StardustTimerStartEventType ext = ExtensionHelper.getInstance().getTimerStartEventExtension(event);
        AttributeType attr = ext.getStardustAttributes().getAttributeType().get(0);
        assertEquals("carnot:engine:stopTime", attr.getName());
        assertEquals("1347919200000", attr.getValue());

    }


    private FlowElement findFlowElement(Definitions definitions, String id) {
        List<RootElement> roots = definitions.getRootElements();
        for (RootElement root : roots) {
            if (root instanceof FlowElementsContainer) {
                for (FlowElement fe : ((FlowElementsContainer) root).getFlowElements()) {
                    if (fe.getId().equals(id)) return fe;
                }
            }
        }
        return null;
    }

}
