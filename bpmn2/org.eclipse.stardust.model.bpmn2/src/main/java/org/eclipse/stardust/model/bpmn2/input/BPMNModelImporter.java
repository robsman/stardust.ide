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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.model.bpmn2.ModelConstants;
import org.eclipse.stardust.model.bpmn2.input.serialization.Bpmn2PersistenceHandler;

/**
 * @author Simon Nikles
 */
public class BPMNModelImporter {

    private static Logger log = Logger.getLogger(BPMNModelImporter.class);

    public BPMNModelImporter() {

    }

    public static Bpmn2Resource importModel(String filePath) throws FileNotFoundException, IOException {
        log.info("importModel " + filePath);
        File f = new File(filePath); 
        return importModel(f.toURI());
        
//        FileInputStream fis = new FileInputStream(f);
//        Bpmn2PersistenceHandler handler = new Bpmn2PersistenceHandler();
//        String name = f.getName();
//        name = name.replaceAll(" ", "_");
//        Bpmn2Resource loaded = handler.loadModel(name, fis);
//        fis.close();
//        try {
//			loadImports(getDefinitions(loaded), filePath);
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//        return loaded;
        /*
        Bpmn2ResourceFactoryImpl factory = new Bpmn2ResourceFactoryImpl();

        Bpmn2Resource loadedResource = (Bpmn2Resource) factory.createResource(URI.createFileURI(filePath));

        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
        options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
        loadedResource.load(options);
        
        loadImports(loadedResource);
        //debugPrint(loadedResource);

        return loadedResource;*/
    }

    public static Bpmn2Resource importModel(java.net.URI fileUri) throws FileNotFoundException, IOException {
        log.info("importModel " + fileUri);
        File f = new File(fileUri); 
        FileInputStream fis = new FileInputStream(f);
        Bpmn2PersistenceHandler handler = new Bpmn2PersistenceHandler();
        String name = f.getName();
        name = name.replaceAll(" ", "_");
        Bpmn2Resource loaded = handler.loadModel(name, fis);
        fis.close();
        try {
			List<Bpmn2Resource> importedResources = loadImports(getDefinitions(loaded), fileUri);
			loaded.getResourceSet().getResources().addAll(importedResources);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
        return loaded;
    }
    
    private static List<Bpmn2Resource> loadImports(Definitions loaded, java.net.URI loadedUri) throws IOException, URISyntaxException {
//    	File f = new File(filePath);
//    	java.net.URI loadedUri = f.toURI();
    	//java.net.URI loadedUri = new java.net.URI(filePath);
    	
    	//Definitions defs = getDefinitions(loadedResource);
    	List<Bpmn2Resource> loadedImports = new ArrayList<Bpmn2Resource>();
    	List<Import> imports = loaded.getImports();
    	if (null == imports) return loadedImports;
    	for (Import imp : imports) {
    		if (null == imp.getImportType() || !ModelConstants.BPMN_IMPORT_TYPE_MODEL.equals(imp.getImportType())) continue;
    		java.net.URI importPath = loadedUri.resolve(imp.getLocation());
    		loadedImports.add(importModel(importPath));
//            Bpmn2ResourceFactoryImpl factory = new Bpmn2ResourceFactoryImpl();
//            Bpmn2Resource loadImp = (Bpmn2Resource) factory.createResource(URI.createFileURI(imp.getLocation()));
//            Map<Object, Object> options = new HashMap<Object, Object>();
//            options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
//            options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
//            options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
//            options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
//            loadImp.load(options);
    	}
    	return loadedImports;
	}

//    private static void loadImports(Bpmn2Resource loadedResource) throws IOException {
//    	ResourceSet resourceSet = loadedResource.getResourceSet();
//    	Definitions defs = getDefinitions(loadedResource);
//    	for (Import imp : defs.getImports()) {
//            Bpmn2ResourceFactoryImpl factory = new Bpmn2ResourceFactoryImpl();
//            Bpmn2Resource loadImp = (Bpmn2Resource) factory.createResource(URI.createFileURI(imp.getLocation()));
//            Map<Object, Object> options = new HashMap<Object, Object>();
//            options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
//            options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
//            options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
//            options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
//            loadImp.load(options);
//    	}
//	}

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
