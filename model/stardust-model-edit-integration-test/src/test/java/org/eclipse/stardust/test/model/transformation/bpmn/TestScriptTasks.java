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
package org.eclipse.stardust.test.model.transformation.bpmn;

import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Simon Nikles
 *
 */
public class TestScriptTasks extends Bpmn2StardustTestSuite {

	public TestScriptTasks() {
	}

    @Test
    public void testScriptTask() {
        final String modelFile = "ScriptTask.bpmn";

        ModelType result = transformModel(loadBpmnModel(modelFile));
        ProcessDefinitionType processDef = result.getProcessDefinition().get(0);

        // TODO check target model
        Assert.fail("NO ASSERTIONS IMPLEMENTED");
    }
}
