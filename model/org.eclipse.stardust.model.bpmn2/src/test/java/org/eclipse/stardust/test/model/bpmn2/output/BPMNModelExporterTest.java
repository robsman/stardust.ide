package org.eclipse.stardust.test.model.bpmn2.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.eclipse.stardust.model.bpmn2.output.BPMNModelExporter;
import org.junit.Assert;


public class BPMNModelExporterTest extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(BPMNModelExporterTest.class);
		return suite;
	}
	
	public void testModelExport() {
		//final String filePath = "c:/temp/test.bpmn";
		final String filePath = "c:/temp/test.bpmn";
		final String targetFilePath = "c:/temp/test_export.bpmn";
		try {
			//Definitions definitions = BPMNModelImporter.importModel(filePath);
			Bpmn2Resource modelResource = BPMNModelImporter.importModel(filePath);
			System.out.println("BPMNModelExporterTest.testModelExport() " + modelResource.getClass().toString());
			//BPMNModelExporter.exportModel(definitions, getNonExistingFilePath(targetFilePath));
			BPMNModelExporter.exportModel(modelResource, getNonExistingFilePath(targetFilePath));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
	}	
	
	public static String getNonExistingFilePath(String initialPath) {
		String path = initialPath;
		File f = new File(path); 
		int nr = 1;
		while(f.exists()) {
			int extStart = initialPath.lastIndexOf(".");
			path = initialPath.substring(0, extStart) + nr + initialPath.substring(extStart);
			f = new File(path);
			nr++;
		}
		return  path;
	}
}
