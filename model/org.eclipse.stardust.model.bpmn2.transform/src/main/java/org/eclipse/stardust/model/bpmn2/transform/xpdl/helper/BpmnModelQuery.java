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

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;

/**
 * @author Simon Nikles
 *
 */
public class BpmnModelQuery {

	private Logger logger;

    public BpmnModelQuery() {
    }

    public BpmnModelQuery(Logger logger) {
    	this.logger = logger;
    }

    public EventDefinition getFirstEventDefinition(Event event) {
        if (countEventDefinitions(event) > 0) {
            return getEventDefinitions(event).get(0);
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

    public int countEventDefinitions(Event event) {
		List<EventDefinition> eventDefinitions = getEventDefinitions(event);
		return eventDefinitions == null ? 0 : eventDefinitions.size();
	}

    public Interface getInterfaceByOperationRef(BaseElement node, EObject container) {
		Class<?> cls = node.getClass();
		try {
			Method m = cls.getMethod("getOperationRef");
			Object op = m.invoke(node);
			if (op != null && op instanceof Operation) {
				Operation operation = (Operation)op;
				if (operation.eIsProxy()) operation = Bpmn2ProxyResolver.resolveOperationProxy(operation, container);
				return (Interface) operation.eContainer();
			}
		} catch (Exception e) {
			if (logger != null) logger.error(e.getMessage());
		}
		return null;
    }

    @SuppressWarnings("unchecked")
	private List<EventDefinition> getEventDefinitions(Event event) {
		Class<?> cls = event.getClass();
		try {
			Method m = cls.getMethod("getEventDefinitions");
			Object defs = m.invoke(event);
			if (defs != null && defs instanceof List<?>) {
				return ((List<EventDefinition>)defs);
			}
		} catch (Exception e) {
			if (logger != null) logger.error(e.getMessage());
		}
		return null;
	}

}
