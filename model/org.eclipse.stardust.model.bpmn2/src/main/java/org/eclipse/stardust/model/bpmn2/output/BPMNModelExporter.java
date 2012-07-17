package org.eclipse.stardust.model.bpmn2.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * @author Simon Nikles
 *
 */
public class BPMNModelExporter {

	public BPMNModelExporter() {
	
	}

	public static void exportModel(Bpmn2Resource resource, String targetFilePath) throws FileNotFoundException, IOException {
		Logger.getLogger(BPMNModelExporter.class).debug("BPMNModelExporter.exportModel() " + targetFilePath);

		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);		
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE); 
		options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, Boolean.TRUE);
		FileOutputStream fos = new FileOutputStream(targetFilePath);

		resource.save(fos, options);
		fos.close();
	}

}
