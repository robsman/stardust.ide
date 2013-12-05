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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.Operation;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
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

	public static Definitions getDefinitions(FlowElementsContainer container) {
		if (null == container) return null;
		return findDefinitions(container);
	}


	private static Definitions findDefinitions(FlowElementsContainer container) {
		EObject parent = container.eContainer();
		if (!(parent instanceof Definitions)) return findDefinitions(container);
		return (Definitions)parent;
	}

	public static boolean hasNoIncomingSequence(FlowNode flowNode) {
		List<SequenceFlow> incoming = flowNode.getIncoming();
		return (null == incoming || 0 == incoming.size());
	}

	public static int getNumberOfSequenceSuccessorNodesOf(FlowNode node) {
		if (null == node || null == node.getOutgoing()) return 0;
		return node.getOutgoing().size();
	}

	public static int getNumberOfSequencePredecessorNodesOf(FlowNode node) {
		if (null == node || null == node.getIncoming()) return 0;
		return node.getIncoming().size();
	}

	public static List<FlowNode> getSequenceSuccessorNodesOf(FlowNode node) {
		List<FlowNode> successors = new ArrayList<FlowNode>();
		if (null == node) return successors;
		for (SequenceFlow seq : node.getOutgoing()) {
			successors.add(seq.getTargetRef());
		}
		return successors;
	}

	public static List<FlowNode> getSequencePredecessorNodesOf(FlowNode node) {
		List<FlowNode> predecessors = new ArrayList<FlowNode>();
		if (null == node) return predecessors;
		for (SequenceFlow seq : node.getIncoming()) {
			predecessors.add(seq.getSourceRef());
		}
		return predecessors;
	}

	public static boolean isPotentialStartNode(FlowElement flowElement) {
		if (!(flowElement instanceof FlowNode)) return false;
		if (flowElement instanceof BoundaryEvent) return false;
		if (flowElement instanceof SubProcess && ((SubProcess)flowElement).isTriggeredByEvent()) return false;
		return hasNoIncomingSequence((FlowNode)flowElement);
	}

	public static boolean hasBoundaryEventAttached(FlowNode node) {
        if (node instanceof Activity) {
        	List<BoundaryEvent> boundaryEventRefs = ((Activity)node).getBoundaryEventRefs();
        	if (boundaryEventRefs != null && boundaryEventRefs.size() > 0) return true;
        }
		return false;
	}

	public static Definitions getModelDefinitions(BaseElement element) {
		EObject object = element;
		while (null != object.eContainer() && !(object instanceof Definitions) && !(object == object.eContainer())) {
			object = object.eContainer();
			if (object instanceof Definitions) return (Definitions)object;
		}
		return null;
	}

	public static DataInputAssociation findDataInputAssociationTo(DataInput callerInput, CallActivity caller) {
		for (DataInputAssociation assoc : caller.getDataInputAssociations()) {
			if (assoc.getTargetRef() != null && assoc.getTargetRef().equals(callerInput)) {
				return assoc;
			}
		}
		return null;
	}

	public static DataOutputAssociation findDataOutputAssociationFrom(DataOutput callerOutput, CallActivity caller) {
		for (DataOutputAssociation assoc : caller.getDataOutputAssociations()) {
			if (assoc.getSourceRef() != null) {
				for (ItemAwareElement out : assoc.getSourceRef()) {
					if (out != null && out.equals(callerOutput)) {
						return assoc;
					}
				}
			}
		}
		return null;
	}

	public Interface findInterfaceById(Definitions defs, String id) {
		if (null == id) return null;
		for (RootElement root : defs.getRootElements()) {
			if (root instanceof Interface) {
				if (id.equals(root.getId())) return (Interface) root;
			}
		}
		return null;
	}

	public Resource findResourceByUUID(Definitions defs, String searchUUID) {
		for (RootElement root : defs.getRootElements()) {
			if (root instanceof Resource) {
				String uuid = getUUID(root.getAnyAttribute());
				if (null != uuid && uuid.equals(searchUUID)) return (Resource)root;
			}
		}
		return null;
	}

	private String getUUID(FeatureMap anyAttribute) {
		final String uuid = "uuid";
		Iterator<Entry> iterator = anyAttribute.iterator();
		while (iterator.hasNext()) {
			Entry entry = iterator.next();
			if (uuid.equals(entry.getEStructuralFeature().getName())) return entry.getValue().toString();
		}
		return null;
	}


}
