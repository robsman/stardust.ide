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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.DocumentRoot;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.bpmn2.util.Bpmn2ResourceFactoryImpl;
import org.eclipse.bpmn2.util.OnlyContainmentTypeInfo;
import org.eclipse.bpmn2.util.XmlExtendedMetadata;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.model.bpmn2.serialization.StardustBpmn2XmlResource;

/**
 * @author Simon Nikles
 */
public class BPMNModelImporter {

    private static Logger log = Logger.getLogger(BPMNModelImporter.class);

    public BPMNModelImporter() {

    }

    public static Bpmn2Resource importModel(String filePath) throws FileNotFoundException, IOException {
    	try
    	{
    		URI resourceStreamUri = URI.createFileURI(filePath);
    		InputStream modelContent = new FileInputStream(filePath);

    		ResourceSet context = new ResourceSetImpl();
    		context.getResourceFactoryRegistry()
    		.getProtocolToFactoryMap()
    		.put(resourceStreamUri.scheme(), new Bpmn2ResourceFactoryImpl()
    		{
    			@Override
    			public Resource createResource(URI uri)
    			{
    				StardustBpmn2XmlResource result = new StardustBpmn2XmlResource(uri);

    				ExtendedMetaData extendedMetadata = new XmlExtendedMetadata();
    				result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetadata);
    				result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetadata);
    				result.getDefaultSaveOptions().put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, new OnlyContainmentTypeInfo());
    				result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    				result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    				result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
    				result.getDefaultSaveOptions().put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(true));
    				result.getDefaultSaveOptions().put(XMLResource.OPTION_ENCODING, "UTF-8");
    				return result;
    			}
    		});

    		Bpmn2Resource bpmnModel = (Bpmn2Resource) context.createResource(resourceStreamUri);

    		// must pass stream directly as load(options) will close the stream internally
    		bpmnModel.load(modelContent, getDefaultXmlLoadOptions());

    		return bpmnModel;
    	}
    	catch (IOException ioe)
    	{
    		log.warn("Failed loading BPMN2 model.", ioe);
    	}
    	return null;
    }

	private static Map<Object, Object> getDefaultXmlLoadOptions() {
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		return options;
	}

	/*
    public static Bpmn2Resource importModel(String filePath) throws FileNotFoundException, IOException {
        log.info("importModel " + filePath);
        Bpmn2ResourceFactoryImpl factory = new Bpmn2ResourceFactoryImpl();

        Bpmn2Resource loadedResource = (Bpmn2Resource) factory.createResource(URI.createFileURI(filePath));

        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
        options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        options.put(XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
        loadedResource.load(options);

        //debugPrint(loadedResource);

        return loadedResource;
    } */

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
