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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.control;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;

import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.ExclusiveGateway;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;

/**
 * @author Simon Nikles
 *
 */
public class Gateway2Stardust extends AbstractElement2Stardust {

    private enum Gate {
        EXCLUSIVE,
        INCLUSIVE,
        PARALLEL,
        EVENT
    }
    private enum GateDirection {
        CONVERGE,
        DIVERGE,
        MIXED
    }

    public Gateway2Stardust(ModelType carnotModel, List<String> failures) {
        super(carnotModel, failures);
    }

    public void addExclusiveGateway(ExclusiveGateway gateway, FlowElementsContainer container) {
        addGateway(Gate.EXCLUSIVE, gateway, container);
    }

    public void addParallelGateway(ParallelGateway gateway, FlowElementsContainer container) {
        addGateway(Gate.PARALLEL, gateway, container);
    }

    private void addGateway(Gate type, Gateway gateway, FlowElementsContainer container) {
        ProcessDefinitionType processDef = getProcessOrReportFailure(gateway, container);
        if (processDef == null) return;
        if (!validateGate(gateway)) return;

        boolean isFork = isFork(gateway);
        boolean isJoin = isJoin(gateway);
        boolean isMixed = isMixed(gateway);

        JoinSplitType joinsplit = getJoinSplitType(type);

        if (isGatewayTransformedToRoute(gateway)) {
            GateDirection direction = getDirection(isMixed, isFork, isJoin);
            ActivityType route = addRoute(type, direction, gateway, processDef, container);
            createRouteTransitions(route, processDef, type, direction, gateway, container);
        } else if (isFork) {
            createForkingTransitions(type, joinsplit, gateway, processDef, container);
        } else if (isJoin) {
            createJoiningTransitions(type, joinsplit, gateway, processDef, container);
        }
    }

    private ActivityType addRoute(Gate type, GateDirection direction, Gateway gateway, ProcessDefinitionType processDef, FlowElementsContainer container) {

        ActivityType route =
                newRouteActivity(processDef)
                .withIdAndName(gateway.getId(), gateway.getName())
                .withDescription(DocumentationTool.getDescriptionFromDocumentation(gateway.getDocumentation()))
                .build();

        JoinSplitType routeType = ( type.equals(Gate.EXCLUSIVE) ? JoinSplitType.XOR_LITERAL : JoinSplitType.AND_LITERAL);
        if (direction.equals(GateDirection.DIVERGE) || direction.equals(GateDirection.MIXED)) {
            route.setSplit(routeType);
        }
        if (direction.equals(GateDirection.CONVERGE) || direction.equals(GateDirection.MIXED)) {
            route.setJoin(routeType);
        }

        return route;
    }

    private void createForkingTransitions(Gate gate, JoinSplitType splitType, Gateway gateway, ProcessDefinitionType processDef, FlowElementsContainer container) {
        List<SequenceFlow> incomings = gateway.getIncoming();
        List<SequenceFlow> outgoings = gateway.getOutgoing();
        ActivityType sourceActivity = query.findActivity(incomings.get(0).getSourceRef(), container);
        if (sourceActivity != null) sourceActivity.setSplit(splitType);
        for (SequenceFlow outgoing : outgoings) {
            if (outgoing.getTargetRef() instanceof Activity || outgoing.getTargetRef() instanceof Gateway) {
                if (isBpmnMergingNonGateway(outgoing.getTargetRef())) continue;
                ActivityType targetActivity = query.findActivity(outgoing.getTargetRef(), container);
                if (targetActivity == null) continue;
                String gatewayDoc = DocumentationTool.getDescriptionFromDocumentation(gateway.getDocumentation());
                addGatewayTransition(gate, gateway, outgoing.getConditionExpression(), outgoing.getName(), outgoing.getId(), gatewayDoc, sourceActivity, targetActivity, processDef);
            } else {
                failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + " exclusive gateway target other than activity or gateway - target model incomplete.");
                continue;
            }
        }
    }

    private void createJoiningTransitions(Gate gate, JoinSplitType joinsplit, Gateway gateway, ProcessDefinitionType processDef, FlowElementsContainer container) {
        List<SequenceFlow> incomings = gateway.getIncoming();
        List<SequenceFlow> outgoings = gateway.getOutgoing();
        ActivityType targetActivity = query.findActivity(outgoings.get(0).getTargetRef(), container);
        if (targetActivity != null) targetActivity.setJoin(joinsplit);
        for (SequenceFlow incoming : incomings) {
            if (incoming.getSourceRef() instanceof Activity || incoming.getSourceRef() instanceof Gateway) {
                if (isBpmnForkingNonGateway(incoming.getSourceRef())) continue;
                ActivityType sourceActivity = query.findActivity(incoming.getSourceRef(), container);
                if (sourceActivity == null) continue;
                addGatewayTransition(gate, gateway, incoming.getConditionExpression(), incoming.getId(), incoming.getId(), DocumentationTool.getDescriptionFromDocumentation(gateway.getDocumentation()), sourceActivity, targetActivity, processDef);
            } else {
                failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + " exclusive gateway source other than activity - target model incomplete.");
                continue;
            }
        }
    }

    private void addGatewayTransition(Gate gate, Gateway gateway, Expression expression, String name, String Id, String documentation, ActivityType sourceActivity, ActivityType targetActivity, ProcessDefinitionType processDef) {
        logger.info("addGatewayTransition from " + sourceActivity.getName() + " to " + targetActivity.getName());
        if (sourceActivity != null && targetActivity != null) {
            TransitionType transition = TransitionUtil.createTransition(Id, name, documentation, processDef, sourceActivity, targetActivity);
            setGatewayConditions(gate, gateway, expression, transition, sourceActivity, targetActivity);
            processDef.getTransition().add(transition);
        } else {
            failures.add("No valid source and target for gateway sequence: " + Id);
        }
    }

    private void createRouteTransitions(ActivityType route, ProcessDefinitionType processDef, Gate type, GateDirection direction, Gateway gateway, FlowElementsContainer container) {
        createIncomingRouteTransitions(route, processDef, type, direction, gateway, container);
        createOutgoingRouteTransitions(route, processDef, type, direction, gateway, container);
    }

    private void createOutgoingRouteTransitions(ActivityType route, ProcessDefinitionType processDef, Gate type, GateDirection direction, Gateway gateway, FlowElementsContainer container) {
        List<SequenceFlow> outgoings = gateway.getOutgoing();
        for (SequenceFlow out : outgoings) {
            ActivityType target = findSequenceTargetActivity(out, container);
            if (target == null && out.getTargetRef() instanceof Gateway && direction.equals(GateDirection.CONVERGE)) {
                Gateway targetGate = (Gateway)out.getTargetRef();
                forwardCreateTransitionToGate(out, route, gateway, type, targetGate, processDef, container);
            } else if (target != null && !isBpmnMergingNonGateway(out.getTargetRef())) {
                TransitionType transition = TransitionUtil.createTransition(out.getId(), out.getName(), DocumentationTool.getDescriptionFromDocumentation(out.getDocumentation()), processDef, route, target);
                setGatewayConditions(type, gateway, out.getConditionExpression(), transition, route, target);
            }
        }
    }

    private void createIncomingRouteTransitions(ActivityType route, ProcessDefinitionType processDef, Gate type, GateDirection direction, Gateway gateway, FlowElementsContainer container) {
        List<SequenceFlow> incomings = gateway.getIncoming();

        for (SequenceFlow in : incomings) {
            ActivityType source = findSequenceSourceActivity(in, container);
            if (source == null) {
                FlowNode sourceNode = in.getSourceRef();
                if (sourceNode instanceof Gateway) {
                    Gateway sourceGate = (Gateway)sourceNode;
                    if (!isGatewayTransformedToRoute(sourceGate)) {
                        backwardCreateMissingTransitions(type, sourceGate, route, processDef, in, container);
                    }
                }
            } else {
                if (!isBpmnForkingNonGateway(in.getSourceRef())) {
                    TransitionType transition = TransitionUtil.createTransition(in.getId(), in.getName(), DocumentationTool.getDescriptionFromDocumentation(in.getDocumentation()), processDef, source, route);
                    setGatewayConditions(type, gateway, in.getConditionExpression(), transition, source, route);
                }
            }
        }
    }

    private void backwardCreateMissingTransitions(Gate type, Gateway gateway, ActivityType target, ProcessDefinitionType processDef, SequenceFlow flowToTarget, FlowElementsContainer container) {

        if (isMixed(gateway)) return;

        if (isFork(gateway)) {
            if (null != query.findTransition(flowToTarget.getId(), container)) return;
            FlowNode sourceNode = getFirstIncomingSource(gateway);
            if (sourceNode != null && sourceNode instanceof Activity) {
                ActivityType source = query.findActivity(sourceNode, container);
                if (source == null) return;
                TransitionType transition = TransitionUtil.createTransition(flowToTarget.getId(), flowToTarget.getName(), DocumentationTool.getDescriptionFromDocumentation(flowToTarget.getDocumentation()), processDef, source, target);
                setGatewayConditions(type, gateway, flowToTarget.getConditionExpression(), transition, source, target);
            }
        } else {
            for (SequenceFlow in : gateway.getIncoming()) {
                if (null == query.findTransition(in.getId(), container)) {
                    FlowNode sourceNode = in.getSourceRef();
                    if (sourceNode != null && sourceNode instanceof Activity) {
                        ActivityType source = query.findActivity(sourceNode, container);
                        if (source == null) continue;
                        TransitionType transition = TransitionUtil.createTransition(in.getId(), flowToTarget.getName(), DocumentationTool.getDescriptionFromDocumentation(in.getDocumentation()), processDef, source, target);
                        setGatewayConditions(type, gateway, in.getConditionExpression(), transition, source, target);
                    }

                }
            }
        }
    }

    private void forwardCreateTransitionToGate(SequenceFlow seq, ActivityType sourceActivity, Gateway gateway, Gate type, Gateway targetGate, ProcessDefinitionType processDef, FlowElementsContainer container) {
        if (isMixed(gateway)) return;
        if (isFork(gateway)) return;
        if (targetGate.getOutgoing() != null && targetGate.getOutgoing().size() == 1) {
            SequenceFlow sequence = targetGate.getOutgoing().get(0);
            FlowNode targetNode = sequence.getTargetRef();
            if (targetNode == null) return;
            if (query.findTransition(seq.getId(), container) != null) return;

            ActivityType target = CarnotModelQuery.findActivity(processDef, targetNode.getId());
            if (target == null) return;
            TransitionType transition = TransitionUtil.createTransition(seq.getId(), seq.getName(), DocumentationTool.getDescriptionFromDocumentation(seq.getDocumentation()), processDef, sourceActivity, target);
            setGatewayConditions(type, gateway, sequence.getConditionExpression(), transition, sourceActivity, target);
        }
    }

    private void setGatewayConditions(Gate gate, Gateway gateway, Expression expression, TransitionType transition, ActivityType sourceActivity, ActivityType targetActivity) {

        if (isGatewayDefaultSequenceTarget(gate, gateway, targetActivity)) {
            logger.debug("transition: " + transition.getId() + " setSequenceOtherwiseCondition");
            TransitionUtil.setSequenceOtherwiseCondition(transition);
        } else if (gate.equals(Gate.PARALLEL)) {
            logger.debug("transition: " + transition.getId() + " setSequenceTrueCondition");
            TransitionUtil.setSequenceTrueCondition(transition);
        } else {
            TransitionUtil.setSequenceExpressionConditionOrTrue(transition, expression, logger, failures);
        }
    }

    private boolean validateGate(Gateway gateway) {
        List<SequenceFlow> incomings = gateway.getIncoming();
        List<SequenceFlow> outgoings = gateway.getOutgoing();

        if (incomings == null || outgoings == null) return false;
        if (incomings.size() == 0 || outgoings.size() == 0) return false;

        return true;
    }

    private boolean isGatewayTransformedToRoute(Gateway gate) {
        if (isMixed(gate)) return true;
        if (hasBpmnGateSource(gate, gate.getIncoming())) return true;
        if (isJoin(gate) && hasMergingGatewayTarget(gate.getOutgoing())) return true;
        if (hasIncomingFromBpmnForkingNonGateway(gate)) return true;
        if (hasOutgoingToBpmnMergingNonGateway(gate)) return true;
        return false;
    }

	private boolean hasIncomingFromBpmnForkingNonGateway(Gateway gate) {
		for (SequenceFlow seq : gate.getIncoming()) {
            FlowNode source = seq.getSourceRef();
            if (BpmnModelQuery.isForkingNonGateway(source)) return true;
		}
		return false;
	}

	private boolean hasOutgoingToBpmnMergingNonGateway(Gateway gate) {
		for (SequenceFlow seq : gate.getOutgoing()) {
            FlowNode target = seq.getTargetRef();
            if (BpmnModelQuery.isMergingNonGateway(target)) return true;
		}
		return false;
	}

	private boolean hasBpmnGateSource(Gateway gateway, List<SequenceFlow> incomings) {
        for (SequenceFlow in : incomings) {
            FlowNode source = in.getSourceRef();
            if (source instanceof Gateway) return true;
            if (BpmnModelQuery.isRoutingFlowNode(source)) return true;
        }
        return false;
    }

	private boolean hasMergingGatewayTarget(List<SequenceFlow> sequences) {
        for (SequenceFlow seq : sequences) {
            FlowNode target = seq.getTargetRef();
            if (target instanceof Gateway && isJoin((Gateway)target)) return true;
        }
        return false;
	}

    private boolean isBpmnForkingNonGateway(FlowNode node) {
    	return BpmnModelQuery.isForkingNonGateway(node);
    }

    private boolean isBpmnMergingNonGateway(FlowNode node) {
    	return BpmnModelQuery.isMergingNonGateway(node);
    }

    private boolean isFork(Gateway gateway) {
        return gateway.getOutgoing() != null & gateway.getOutgoing().size() > 1;
    }

    private boolean isJoin(Gateway gateway) {
        return gateway.getIncoming() != null && gateway.getIncoming().size() > 1;
    }

    private boolean isMixed(Gateway gateway) {
        if (isJoin(gateway) && isFork(gateway)) {
            return true;
        }
        return false;
    }

    private boolean isGatewayDefaultSequenceTarget(Gate gate, Gateway gateway, ActivityType targetActivity) {
        return gate.equals(Gate.EXCLUSIVE)
                && ((ExclusiveGateway)gateway).getDefault() != null
                && ((ExclusiveGateway)gateway).getDefault().getTargetRef().getId().equals(targetActivity.getId());
    }

    private JoinSplitType getJoinSplitType(Gate type) {
        switch(type) {
        case EXCLUSIVE:
            return JoinSplitType.XOR_LITERAL;
        case INCLUSIVE:
            return JoinSplitType.AND_LITERAL;
        case PARALLEL:
            return JoinSplitType.AND_LITERAL;
        }
        return JoinSplitType.XOR_LITERAL;
    }

    private GateDirection getDirection(boolean isMixed, boolean isFork, boolean isJoin) {
        return isMixed ? GateDirection.MIXED : (isFork ? GateDirection.DIVERGE : GateDirection.CONVERGE);
    }

    private FlowNode getFirstIncomingSource(Gateway gateway) {
        for (SequenceFlow in : gateway.getIncoming()) {
            if (in != null) return in.getSourceRef();
        }
        return null;
    }

    private ActivityType findSequenceTargetActivity(SequenceFlow flow, FlowElementsContainer container) {
        FlowNode targetNode = flow.getTargetRef();
        return query.findActivity(targetNode, container);
    }

    private ActivityType findSequenceSourceActivity(SequenceFlow flow, FlowElementsContainer container) {
        FlowNode sourceNode = flow.getSourceRef();
        return query.findActivity(sourceNode, container);
    }

}
