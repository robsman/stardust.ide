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
package org.eclipse.stardust.model.bpmn2.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.stardust.common.log.LogManager;

/**
 * @author Simon Nikles
 *
 */
public class BPMNModelExporter {

    public BPMNModelExporter() {

    }

    public static void exportModel(Bpmn2Resource resource, String targetFilePath) throws FileNotFoundException, IOException {
        LogManager.getLogger(BPMNModelExporter.class).debug("BPMNModelExporter.exportModel() " + targetFilePath);

        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS, Boolean.TRUE);
        options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
        options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, Boolean.TRUE);
        options.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.FALSE);
        FileOutputStream fos = new FileOutputStream(targetFilePath);

        resource.save(fos, options);
        fos.close();
    }

}
