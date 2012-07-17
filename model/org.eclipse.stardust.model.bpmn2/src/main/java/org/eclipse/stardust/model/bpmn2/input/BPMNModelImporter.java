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
package org.eclipse.stardust.model.bpmn2.input;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;

/**
 * @author Simon Nikles
 */
public class BPMNModelImporter {

	private static Logger log = Logger.getLogger(BPMNModelImporter.class); 
	
	public BPMNModelImporter() {
	
	}
	
	public static Bpmn2Resource importModel(String filePath) throws FileNotFoundException, IOException {
		log.info("importModel " + filePath);
		Bpmn2ResourceFactoryImpl factory = new Bpmn2ResourceFactoryImpl();

		Bpmn2Resource loadedResource = (Bpmn2Resource) factory.createResource(URI.createFileURI(filePath));
		
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);		
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadedResource.load(options);
		
		//debugPrint(loadedResource);
		
		return loadedResource; 
	}
	

	public static Definitions getDefinitions(Bpmn2Resource loadedResource) {
		
		EList<EObject> list = loadedResource.getContents();
		for (EObject l : list) {
			if (l instanceof DocumentRoot) {
				return ((DocumentRoot) l).getDefinitions();				
			}
		}		
		return null;
	}

	@SuppressWarnings("unused")
	private static void debugPrint(Bpmn2Resource r) {
		
		log.debug("BPMNModelGenerator.debugPrint()");

		EList<EObject> list = r.getContents();
		log.debug("Elist contents: ");
		for (EObject l : list) {
			debugPrintChildren(l, 1);
		}		
	}
	
	private static void debugPrintChildren(EObject parent, int level) {
		TreeIterator<EObject> it = parent.eAllContents(); 
		while(it.hasNext()) {
			EObject obj = it.next();
			String lvl = String.format("%"+level+"s", "").replace(' ', '-');

			if (!(obj instanceof AnyType)) {
				log.debug(lvl + obj.eClass().getName() + " (" + obj.toString() + ")");			
				
				if ("SequenceFlow".equals(obj.eClass().getName())) {
					log.debug("SourceRef: " + ((SequenceFlow)obj).getSourceRef());
					log.debug("TargetRef: " + ((SequenceFlow)obj).getTargetRef());
				}				
			} else {
				for ( Object attr : ((AnyType) obj).getAnyAttribute()) {
					log.debug(" any attr " + attr.toString());
				}
			}
			debugPrintChildren(obj, level+1);
		}
		
	}
}
