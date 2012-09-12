//package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements;
//
//import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRouteActivity;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.eclipse.bpmn2.Activity;
//import org.eclipse.bpmn2.ExclusiveGateway;
//import org.eclipse.bpmn2.Expression;
//import org.eclipse.bpmn2.FlowElementsContainer;
//import org.eclipse.bpmn2.FlowNode;
//import org.eclipse.bpmn2.FormalExpression;
//import org.eclipse.bpmn2.Gateway;
//import org.eclipse.bpmn2.ParallelGateway;
//import org.eclipse.bpmn2.SequenceFlow;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
//import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
//import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
//import org.eclipse.stardust.model.xpdl.carnot.ModelType;
//import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
//import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
//
//public class Gateway2Stardust_Bck {
//
//	private enum Gate {
//		EXCLUSIVE,
//		INCLUSIVE,
//		PARALLEL,
//		EVENT
//	}
//	private enum GateDirection {
//		CONVERGE,
//		DIVERGE,
//		MIXED
//	}
//	
//	private final Logger logger = Logger.getLogger(this.getClass());
//
//	private final CarnotModelQuery query;
//	private List<String> failures;
//
//	public Gateway2Stardust_Bck(ModelType carnotModel, List<String> failures) {
//		this.query = new CarnotModelQuery(carnotModel);
//		this.failures = failures;
//	}
//
//	public void addExclusiveGateway(ExclusiveGateway gateway, FlowElementsContainer container) {
//		if (!validateContainer(container)) return;
//		//if (isMixed(gateway)) return;
//		if (!validateGate(gateway)) return;
//		
//		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
//		List<SequenceFlow> incomings = gateway.getIncoming();
//		List<SequenceFlow> outgoings = gateway.getOutgoing();
//		boolean isFork = isFork(gateway);
//		boolean isJoin = isJoin(gateway);		
//		boolean isMixed = isMixed(gateway);
//		boolean hasGateSource = hasBpmnGateSource(gateway, incomings);
//		
//		if (isMixed || hasGateSource) {
//			GateDirection direction = isMixed ? GateDirection.MIXED : (isFork ? GateDirection.DIVERGE : GateDirection.CONVERGE);
//			ActivityType route = addRoute(Gate.EXCLUSIVE, direction, gateway, processDef, container, incomings, outgoings);
//			createRouteTransitions(route, processDef, Gate.EXCLUSIVE, direction, gateway, container, incomings, outgoings);
//		} else if (isFork) {
//			addFork(Gate.EXCLUSIVE, JoinSplitType.XOR_LITERAL, gateway, processDef, container, incomings, outgoings);
//		} else if (isJoin) {
//			addJoin(Gate.EXCLUSIVE, JoinSplitType.XOR_LITERAL, gateway, processDef, container, incomings, outgoings);
//		}		
//	}
//
//	public void addParallelGateway(ParallelGateway gateway, FlowElementsContainer container) {
//		if (!validateContainer(container)) return;
//		//if (isMixed(gateway)) return;
//		if (!validateGate(gateway)) return;
//		
//		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
//		List<SequenceFlow> incomings = gateway.getIncoming();
//		List<SequenceFlow> outgoings = gateway.getOutgoing();
//		boolean isFork = isFork(gateway);
//		boolean isJoin = isJoin(gateway);		
//		boolean isMixed = isMixed(gateway);
//		boolean hasGateSource = hasBpmnGateSource(gateway, incomings);
//		
//		if (isMixed || hasGateSource) {
//			GateDirection direction = isMixed ? GateDirection.MIXED : (isFork ? GateDirection.DIVERGE : GateDirection.CONVERGE);
//			ActivityType route = addRoute(Gate.PARALLEL, direction, gateway, processDef, container, incomings, outgoings);
//			createRouteTransitions(route, processDef, Gate.PARALLEL, direction, gateway, container, incomings, outgoings);
//		} else if (isFork) {
//			addFork(Gate.PARALLEL, JoinSplitType.AND_LITERAL, gateway, processDef, container, incomings, outgoings);
//		} else if (isJoin) {
//			addJoin(Gate.PARALLEL, JoinSplitType.AND_LITERAL, gateway, processDef, container, incomings, outgoings);
//		}		
//	}	
//		
//	private ActivityType addRoute(Gate type, GateDirection direction, Gateway gateway,
//			ProcessDefinitionType processDef, FlowElementsContainer container, List<SequenceFlow> incomings, List<SequenceFlow> outgoings) {
//		
//		ActivityType route = 
//				newRouteActivity(processDef)
//				.withIdAndName(gateway.getId(), gateway.getName())
//				.withDescription(DocumentationTool.getDescriptionFromDocumentation(gateway.getDocumentation()))
//				.build();
//		
//		JoinSplitType routeType = ( type.equals(Gate.EXCLUSIVE) ? JoinSplitType.XOR_LITERAL : JoinSplitType.AND_LITERAL);
//		if (direction.equals(GateDirection.DIVERGE) || direction.equals(GateDirection.MIXED)) {			
//			route.setSplit(routeType);
//		}
//		if (direction.equals(GateDirection.CONVERGE) || direction.equals(GateDirection.MIXED)) {
//			route.setJoin(routeType);
//		}
//		
//		return route;		
//	}
//
//	private void createRouteTransitions(ActivityType route, ProcessDefinitionType processDef, Gate type, GateDirection direction, Gateway gateway, FlowElementsContainer container, List<SequenceFlow> incomings, List<SequenceFlow> outgoings) {
//		createIncomingRouteTransitions(route, processDef, type, direction, gateway, container, incomings, outgoings);
//		createOutgoingRouteTransitions(route, processDef, type, direction, gateway, container, incomings, outgoings);
//	}
//
//	private void createOutgoingRouteTransitions(ActivityType route, ProcessDefinitionType processDef, Gate type,
//			GateDirection direction, Gateway gateway, FlowElementsContainer container, List<SequenceFlow> incomings,
//			List<SequenceFlow> outgoings) {
//		for (SequenceFlow out : outgoings) {
//			ActivityType target = findSequenceTargetActivity(out, container);
//			if (target != null) {
//				TransitionType transition = Sequence2Stardust.createTransition(out.getId(), out.getName(), DocumentationTool.getDescriptionFromDocumentation(out.getDocumentation()), processDef, route, target);
//				setGatewayConditions(type, gateway, out.getConditionExpression(), transition, route, target);
//			}			
//		}	
//	}
//
//	private void createIncomingRouteTransitions(ActivityType route, ProcessDefinitionType processDef, Gate type, GateDirection direction, Gateway gateway, FlowElementsContainer container, List<SequenceFlow> incomings, List<SequenceFlow> outgoings) {
//		
//		for (SequenceFlow in : incomings) {
//			ActivityType source = findSequenceSourceActivity(in, container);
//			if (source == null) {
//				FlowNode sourceNode = in.getSourceRef();
//				if (sourceNode instanceof Gateway) {
//					Gateway sourceGate = (Gateway)sourceNode;
//					if (!isGatewayTransformedToRoute(sourceGate)) {
//						backwardCreateMissingTransitions(type, sourceGate, route, processDef, in, container);
//					}
//				}
//			} else {
//				TransitionType transition = Sequence2Stardust.createTransition(in.getId(), in.getName(), DocumentationTool.getDescriptionFromDocumentation(in.getDocumentation()), processDef, source, route);
//				setGatewayConditions(type, gateway, in.getConditionExpression(), transition, source, route);
//			}
//		}		
//	}
//	
//	
//	private void backwardCreateMissingTransitions(Gate type, Gateway gateway, ActivityType target, ProcessDefinitionType processDef, SequenceFlow flowToTarget, FlowElementsContainer container) {
//		
//		if (isMixed(gateway)) return;
//		
//		if (isFork(gateway)) {
//			if (null != query.findTransition(flowToTarget.getId(), container)) return;
//			FlowNode sourceNode = getFirstIncomingSource(gateway);
//			if (sourceNode != null && sourceNode instanceof Activity) {
//				ActivityType source = query.findActivity(sourceNode, container);
//				if (source == null) return;
//				TransitionType transition = Sequence2Stardust.createTransition(flowToTarget.getId(), flowToTarget.getName(), DocumentationTool.getDescriptionFromDocumentation(flowToTarget.getDocumentation()), processDef, source, target);
//				setGatewayConditions(type, gateway, flowToTarget.getConditionExpression(), transition, source, target);
//			}
//		} else {
//			for (SequenceFlow in : gateway.getIncoming()) {			
//				if (null == query.findTransition(in.getId(), container)) {
//					FlowNode sourceNode = in.getSourceRef();
//					if (sourceNode != null && sourceNode instanceof Activity) {
//						ActivityType source = query.findActivity(sourceNode, container);
//						if (source == null) continue;
//						TransitionType transition = Sequence2Stardust.createTransition(in.getId(), flowToTarget.getName(), DocumentationTool.getDescriptionFromDocumentation(in.getDocumentation()), processDef, source, target);
//						setGatewayConditions(type, gateway, in.getConditionExpression(), transition, source, target);
//					}
//					
//				}
//			}		
//		}
//	}
//
//	private FlowNode getFirstIncomingSource(Gateway gateway) {
//		for (SequenceFlow in : gateway.getIncoming()) {
//			if (in != null) return in.getSourceRef();
//		}
//		return null;
//	}
//
//	private boolean isGatewayTransformedToRoute(Gateway sourceGate) {
//		if (isMixed(sourceGate)) return true;
//		if (hasBpmnGateSource(sourceGate, sourceGate.getIncoming())) return true;
//		return false;
//	}
//
//	private ActivityType findSequenceTargetActivity(SequenceFlow flow, FlowElementsContainer container) {
//		FlowNode targetNode = flow.getTargetRef();
//		return query.findActivity(targetNode, container);	
//	}
//
//	private ActivityType findSequenceSourceActivity(SequenceFlow flow, FlowElementsContainer container) {
//		FlowNode sourceNode = flow.getSourceRef();
//		return query.findActivity(sourceNode, container);
//	}
//
//	private boolean isFork(Gateway gateway) {
//		return gateway.getOutgoing() != null & gateway.getOutgoing().size() > 1;
//	}
//
//	private boolean isJoin(Gateway gateway) {
//		return gateway.getIncoming() != null && gateway.getIncoming().size() > 1;
//	}
//	
//	private boolean isMixed(Gateway gateway) {		
//		if (isJoin(gateway) && isFork(gateway)) {
////			failures.add(Bpmn2CarnotXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + " n:n exclusive gateway - target model incomplete.");
//			return true;			
//		}		
//		return false;
//	}
//
//	private boolean hasBpmnGateSource(Gateway gateway, List<SequenceFlow> incomings) {
//		for (SequenceFlow in : incomings) {
//			if (in.getSourceRef() instanceof Gateway) return true;
//		}
//		return false;
//	}
//
//	private boolean validateGate(Gateway gateway) {		
//		List<SequenceFlow> incomings = gateway.getIncoming();
//		List<SequenceFlow> outgoings = gateway.getOutgoing();
//		
//		if (incomings == null || outgoings == null) return false;
//		if (incomings.size() == 0 || outgoings.size() == 0) return false;
//		
//		return true;
//	}
//	
//	private boolean validateContainer(FlowElementsContainer container) {
//		ProcessDefinitionType processDef = query.findProcessDefinition(container.getId());
//		if (processDef ==null) {
//			failures.add(Bpmn2StardustXPDL.FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");
//			return false;
//		}
//
//		return true;
//	}
//	
//	private void addJoin(Gate gate, JoinSplitType joinsplit, Gateway gateway,
//			ProcessDefinitionType processDef, FlowElementsContainer container, List<SequenceFlow> incomings,
//			List<SequenceFlow> outgoings) {
//
//		ActivityType targetActivity = query.findActivity(outgoings.get(0).getTargetRef(), container);
//		if (targetActivity != null) targetActivity.setJoin(joinsplit);
//		for (SequenceFlow incoming : incomings) {
//			if (incoming.getSourceRef() instanceof Activity || incoming.getSourceRef() instanceof Gateway) {
//				ActivityType sourceActivity = query.findActivity(incoming.getSourceRef(), container);
//				if (sourceActivity == null) continue;					
//				addGatewayTransition(gate, gateway, incoming.getConditionExpression(), incoming.getId(), incoming.getId(), DocumentationTool.getDescriptionFromDocumentation(gateway.getDocumentation()), sourceActivity, targetActivity, container, processDef);
//			} else { 
//				failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + " exclusive gateway source other than activity - target model incomplete.");
//				continue;
//			}			
//		}
//	}
//	
//	private void addFork(Gate gate, JoinSplitType splitType, Gateway gateway, ProcessDefinitionType processDef, FlowElementsContainer container, List<SequenceFlow> incomings, List<SequenceFlow> outgoings) {
//		ActivityType sourceActivity = query.findActivity(incomings.get(0).getSourceRef(), container);
//		if (sourceActivity != null) sourceActivity.setSplit(splitType);
//		for (SequenceFlow outgoing : outgoings) {
//			if (outgoing.getTargetRef() instanceof Activity || outgoing.getTargetRef() instanceof Gateway) {
//				ActivityType targetActivity = query.findActivity(outgoing.getTargetRef(), container);
//				if (targetActivity == null) continue;
//				String gatewayDoc = DocumentationTool.getDescriptionFromDocumentation(gateway.getDocumentation());
//				addGatewayTransition(gate, gateway, outgoing.getConditionExpression(), outgoing.getName(), outgoing.getId(), gatewayDoc, sourceActivity, targetActivity, container, processDef);
//			} else {
//				failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + " exclusive gateway target other than activity or gateway - target model incomplete.");
//				continue;
//			}
//		}		
//	}
//
//	private void addGatewayTransition(Gate gate, Gateway gateway, Expression expression, String name, String Id, String documentation, ActivityType sourceActivity, ActivityType targetActivity, FlowElementsContainer container, ProcessDefinitionType processDef) {
//		logger.info("addGatewayTransition from " + sourceActivity.getName() + " to " + targetActivity.getName()); 
//		if (processDef != null) {			
//			if (sourceActivity != null && targetActivity != null) {
//				TransitionType transition = Sequence2Stardust.createTransition(Id, name, documentation, processDef, sourceActivity, targetActivity);
//				setGatewayConditions(gate, gateway, expression, transition, sourceActivity, targetActivity);
//				processDef.getTransition().add(transition);
//			} else {
//				failures.add("No valid source and target for gateway sequence: " + Id);
//			}
//		} else {
//			failures.add(Bpmn2StardustXPDL.FAIL_NO_PROCESS_DEF + "(Id: " + container.getId() + ")");			
//		}		
//	}
//
//	private void setGatewayConditions(Gate gate, Gateway gateway, Expression expression, TransitionType transition, ActivityType sourceActivity, ActivityType targetActivity) {
//		String expressionVal = "";
//		FormalExpression formal = null;
//		if (expression != null)
//		if (expression instanceof FormalExpression) {
//			formal = (FormalExpression) expression;
//		} else {
//			expressionVal = DocumentationTool.getInformalExpressionValue(expression);
//		}
//		
//		if (isGatewayDefaultSequence(gate, gateway, targetActivity)) {
//			logger.debug("transition: " + transition.getId() + " setSequenceOtherwiseCondition");
//			Sequence2Stardust.setSequenceOtherwiseCondition(transition);
//		} else if (gate.equals(Gate.PARALLEL)) {
//			logger.debug("transition: " + transition.getId() + " setSequenceTrueCondition");
//			Sequence2Stardust.setSequenceTrueCondition(transition);
//		} else if (!expressionVal.equals("")) {
//			logger.debug("transition: " + transition.getId() + " setSequenceInformalCondition: " + expressionVal);
//			Sequence2Stardust.setSequenceInformalCondition(transition, expressionVal);
//		} else if (formal != null) {
//			logger.debug("transition: " + transition.getId() + " setSequenceFormalCondition ");			
//			Sequence2Stardust.setSequenceFormalCondition(transition, formal, failures);
//		} else {
//			logger.debug("transition: " + transition.getId() + " setSequenceTrueCondition");
//			Sequence2Stardust.setSequenceTrueCondition(transition);
//		}
//		
//	}
//
//	private boolean isGatewayDefaultSequence(Gate gate, Gateway gateway, ActivityType targetActivity) {
//		return gate.equals(Gate.EXCLUSIVE) 
//				&& ((ExclusiveGateway)gateway).getDefault() != null 
//				&& ((ExclusiveGateway)gateway).getDefault().getTargetRef().getId().equals(targetActivity.getId());
//	}	
//	
//}
