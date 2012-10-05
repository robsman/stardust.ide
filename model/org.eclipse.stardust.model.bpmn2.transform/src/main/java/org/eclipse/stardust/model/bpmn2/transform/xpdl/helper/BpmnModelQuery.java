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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.helper;

import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;

/**
 * @author Simon Nikles
 *
 */
public class BpmnModelQuery {

    public BpmnModelQuery() {

    }

    public int countEventDefinitions(CatchEvent event) {
        if (event.getEventDefinitions() != null) return event.getEventDefinitions().size();
        return 0;
    }

    public EventDefinition getFirstEventDefinition(CatchEvent event) {
        if (countEventDefinitions(event) > 0) {
            return event.getEventDefinitions().get(0);
        }
        return null;
    }

    public static boolean isRoutingFlowNode(FlowNode flowNode) {
    	// more than one incoming or more than one outgoing sequences
    	boolean isRouting =
    				(flowNode.getIncoming() != null
    				&& flowNode.getIncoming().size() > 1)
    			||
    				(flowNode.getOutgoing() != null
    				&& flowNode.getOutgoing().size() > 1);

		return isRouting;
	}

    public static boolean isMergingNonGateway(FlowNode flowNode) {
    	// more than one incoming sequences
    	if (flowNode instanceof Gateway) return false;
    	return (flowNode.getIncoming() != null
    			&& flowNode.getIncoming().size() > 1);
	}

    public static boolean isForkingNonGateway(FlowNode flowNode) {
    	// more than one outgoing sequences
    	if (flowNode instanceof Gateway) return false;
    	return (flowNode.getOutgoing() != null
    			&& flowNode.getOutgoing().size() > 1);
	}

    public static Resource findResource(Definitions defs, String id) {
    	for (RootElement root : defs.getRootElements()) {
    		if (root instanceof Resource && root.getId().equals(id)) return (Resource)root;
    	}
    	return null;
    }

}
