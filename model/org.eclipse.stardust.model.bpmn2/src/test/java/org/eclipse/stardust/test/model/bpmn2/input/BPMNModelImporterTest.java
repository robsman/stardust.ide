package org.eclipse.stardust.test.model.bpmn2.input;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.junit.Assert;


public class BPMNModelImporterTest extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(BPMNModelImporterTest.class);
		return suite;
	}

	public void testModelImport() {
		//final String filePath = "src/test/resources/test.bpmn"; 
		final String filePath = this.getClass().getClassLoader().getResource("models/simple_import_test.bpmn").getPath();

		try {
			Bpmn2Resource modelResource = BPMNModelImporter.importModel(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
	}
	

}
