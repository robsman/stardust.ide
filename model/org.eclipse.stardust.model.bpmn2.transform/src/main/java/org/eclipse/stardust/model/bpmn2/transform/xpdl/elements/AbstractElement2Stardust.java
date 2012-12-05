/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ITpearls AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

/**
 * @author Simon Nikles
 *
 */
public class AbstractElement2Stardust {

    protected final Logger logger = Logger.getLogger(this.getClass());
    protected final CarnotModelQuery query;
    protected final ModelType carnotModel;
    protected final List<String> failures;

    public AbstractElement2Stardust(ModelType carnotModel, List<String> failures) {
        this.carnotModel = carnotModel;
        this.query = new CarnotModelQuery(carnotModel);
        this.failures = failures;
    }

    protected ProcessDefinitionType getProcessAndReportFailure(FlowElement element, FlowElementsContainer container) {
    	ProcessDefinitionType process = query.findProcessDefinition(container.getId());
    	if (process == null) {
    		failures.add(Bpmn2StardustXPDL.FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + " for element " + element.getId() + ", " + element.getName() + ")");
    	}
    	return process;
    }

    protected String getNonEmptyName(String name, String id, Object obj) {
        if (name != null && !name.isEmpty()) {
            return name;
        }
        if (id != null && !id.isEmpty()) {
            return id;
        }
        return String.valueOf(obj.hashCode());
    }

}
