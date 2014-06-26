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
package org.eclipse.stardust.test.model.bpmn2.input;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.bpmn2.util.Bpmn2Resource;
import org.eclipse.stardust.model.bpmn2.input.BPMNModelImporter;
import org.junit.Assert;


public class BPMNModelImporterTest extends TestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite(BPMNModelImporterTest.class);
        return suite;
    }

    public void testModelImport() {
        final String filePath = this.getClass().getClassLoader().getResource("models/simple_import_test.bpmn").getPath();
        try {
            Bpmn2Resource modelResource = BPMNModelImporter.importModel(filePath);
            assertNotNull(modelResource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    public void testModelImport2() {
        final String filePath = this.getClass().getClassLoader().getResource("models/StartEventTimer.bpmn").getPath();
        try {
            Bpmn2Resource modelResource = BPMNModelImporter.importModel(filePath);
            assertNotNull(modelResource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }


}
