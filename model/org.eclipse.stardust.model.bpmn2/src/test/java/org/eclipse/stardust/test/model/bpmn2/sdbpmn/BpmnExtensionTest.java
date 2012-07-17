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
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.ProcessType;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature.Internal;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl.SimpleFeatureMapEntry;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.output.BPMNModelExporter;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustServiceTaskType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.TStardustCommon;

public class BpmnExtensionTest extends TestCase {
	
	private static final String USER_TASK_ID = "userTaskA";
	private static final String OID = "5000";
	
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

	private void initTestModel() {

		Bpmn2ResourceFactoryImpl rf = new Bpmn2ResourceFactoryImpl();
		defs = rf.createAndInitResource(URI.createURI("http://www.itptest.ch/sdbpmn"));
		defs.setTargetNamespace("http://www.itptest.ch/sdbpmn");
	}
	
	private void populateTestModel() {
		Process proc = fac.createProcess();
		defs.getRootElements().add(proc);
		
		
		UserTask utask = fac.createUserTask();
		utask.setId(USER_TASK_ID);
		
		ExtensionHelper.getInstance().setUserTaskExtension(utask, createUserTaskExtension());
		
		/*
		ExtensionAttributeValue extensionElement = Bpmn2Factory.eINSTANCE.createExtensionAttributeValue();
		FeatureMap.Entry extensionElementEntry = 
				new SimpleFeatureMapEntry((Internal)SdbpmnPackage.Literals.DOCUMENT_ROOT__STARDUST_USER_TASK, userTaskExtension);

		utask.getExtensionValues().add(extensionElement);
		extensionElement.getValue().add(extensionElementEntry);
		*/
		
		proc.setProcessType(ProcessType.PRIVATE);
		proc.getFlowElements().add(utask);
	}
	
	private StardustUserTaskType createUserTaskExtension() {
		StardustUserTaskType userTaskExtension = efac.createStardustUserTaskType();
		userTaskExtension.setAllowsAbortByPerformer(false);
		userTaskExtension.setElementOid(OID);
		userTaskExtension.setHibernateOnCreation(true);		
		return userTaskExtension;
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
