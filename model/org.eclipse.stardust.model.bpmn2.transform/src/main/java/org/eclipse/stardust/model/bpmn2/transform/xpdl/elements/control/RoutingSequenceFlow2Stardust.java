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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;

/**
 * RoutingSequenceFlow handles bpmn flow-nodes with more than one outgoing/incoming sequence-flows.
 * The transformation differs based on whether the sequence-flows have conditions or not and
 * on whether they have a default path.
 *
 * @author Simon Nikles
 */
public class RoutingSequenceFlow2Stardust extends AbstractElement2Stardust {

	private ProcessDefinitionType processDef = null;
	private int numSplittingStartEvents = 0;

	private enum RoutingType {
		UNCONTROLLED_FLOW, // unconditional split or join
		SIMPLE_CONDITIONAL_FLOW, // conditional split/join: conditional (plus EITHER unconditional OR default flows)
		COMPLEX_CONDITIONAL_FLOW // conditional split/join: with both, unconditional and default flows (interpreted as more than one gateway)
	}

	public RoutingSequenceFlow2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void processRoutingNode(FlowNode node, FlowElementsContainer container) {
		processDef = query.findProcessDefinition(container.getId());
		numSplittingStartEvents = 0;
		List<SequenceFlow> incomings = node.getIncoming();
		List<SequenceFlow> outgoings = node.getOutgoing();

		if (outgoings != null && outgoings.size() > 1) {
			createSplit(node, outgoings, container);
		}

		if (incomings != null && incomings.size() > 1) {
			createJoin(node, incomings, container);
		}
	}

	private void createJoin(FlowNode node, List<SequenceFlow> incomings, FlowElementsContainer container) {
		ActivityType joiningActivity = query.findActivity(node, container);

		if (joiningActivity == null) return;

		joiningActivity.setJoin(JoinSplitType.XOR_LITERAL);

		for (SequenceFlow in : incomings) {
			ActivityType sourceActivity = query.findActivity(in.getSourceRef(), container);
			if (sourceActivity == null) continue;
			TransitionType transition = TransitionUtil.createTransition(in.getId(), in.getName(), getDescr(in), processDef, sourceActivity, joiningActivity);

			SequenceFlow defaultSequence = getDefault(in.getSourceRef());

			if (in == defaultSequence && defaultSequence != null) {
				TransitionUtil.setSequenceOtherwiseCondition(transition);
			} else {
				TransitionUtil.setSequenceExpressionConditionOrTrue(transition, in.getConditionExpression(), logger, failures);
			}
		}
	}

	private void createSplit(FlowNode node, List<SequenceFlow> outgoings, FlowElementsContainer container) {
		RoutingType splitType = getRoutingSplitType(node, outgoings);
		switch(splitType) {
			case UNCONTROLLED_FLOW:
			case SIMPLE_CONDITIONAL_FLOW:
				createSimpleSplit(node, outgoings, splitType, container);
				break;
			case COMPLEX_CONDITIONAL_FLOW:
				createComplexSplit(node, outgoings, container);
		}
	}

	private void createSimpleSplit(FlowNode node, List<SequenceFlow> outgoings, RoutingType splitType, FlowElementsContainer container) {
		ActivityType splittingActivity = query.findSequenceSourceActivityForNode(node, container); //query.findActivity(node, container);
		if (null == splittingActivity && node instanceof StartEvent) {
			splittingActivity = new ProcessStartConfigurator(carnotModel, failures).createRouteActivity(container, node, numSplittingStartEvents);
			numSplittingStartEvents++;
		}
		splittingActivity.setSplit(JoinSplitType.AND_LITERAL);
		//SequenceFlow defaultSequence = getDefaultSequence(node, outgoings);
		SequenceFlow defaultSequence = getDefault(node);

		for (SequenceFlow out : outgoings) {
			ActivityType targetActivity = query.findActivity(out.getTargetRef(), container);
			TransitionType transition = TransitionUtil.createTransition(out.getId(), out.getName(), getDescr(out), processDef, splittingActivity, targetActivity);
			if (splitType.equals(RoutingType.UNCONTROLLED_FLOW)) {
				TransitionUtil.setSequenceTrueCondition(transition);
			} else {
				if (out == defaultSequence && defaultSequence != null) {
					TransitionUtil.setSequenceOtherwiseCondition(transition);
				} else {
					TransitionUtil.setSequenceExpressionConditionOrTrue(transition, out.getConditionExpression(), logger, failures);
				}
			}
		}
	}

	private void createComplexSplit(FlowNode node, List<SequenceFlow> outgoings, FlowElementsContainer container) {
		// two parallel AND Splits (the first without, the second with conditions)
		ActivityType splittingActivity = query.findSequenceSourceActivityForNode(node, container); //query.findActivity(node, container);
		List<SequenceFlow> unconditionals = getUnconditionalNonDefaults(node, outgoings);
		List<SequenceFlow> conditionals = getConditionals(outgoings, unconditionals);
		SequenceFlow defaultSequence = getDefault(node);

		createComplexSplitParallelPart(splittingActivity, unconditionals, container);
		ActivityType route = createComplexSplitConditionalPart(splittingActivity, conditionals, defaultSequence, container);
		createComplexSplitParallelToConditionalConnection(splittingActivity, route);
	}

	private void createComplexSplitParallelPart(ActivityType splittingActivity, List<SequenceFlow> unconditionals, FlowElementsContainer container) {
		splittingActivity.setSplit(JoinSplitType.AND_LITERAL);
		for (SequenceFlow seq : unconditionals) {
			ActivityType targetActivity = query.findActivity(seq.getTargetRef(), container);
			TransitionType transition = TransitionUtil.createTransition(seq.getId(), seq.getName(), getDescr(seq), processDef, splittingActivity, targetActivity);
			TransitionUtil.setSequenceTrueCondition(transition);
		}
	}

	private ActivityType createComplexSplitConditionalPart(ActivityType splittingActivity, List<SequenceFlow> conditionals, SequenceFlow defaultSequence, FlowElementsContainer container) {
        ActivityType route = createComplexSplitConditionalPartRoute(splittingActivity, conditionals);
        createComplexSplitConditionalPartTransitions(route, conditionals, defaultSequence, container);
        return route;
	}

	private ActivityType createComplexSplitConditionalPartRoute(ActivityType splittingActivity, List<SequenceFlow> conditionals) {
		String id = splittingActivity.getId() + "_" + splittingActivity.hashCode();
		String name = JoinSplitType.AND_LITERAL + "_Split_" + getNonEmptyName(splittingActivity.getName(), splittingActivity.getId(), splittingActivity);
        ActivityType route =
                newRouteActivity(processDef)
                .withIdAndName(id, name)
                .usingSplitControlFlow(JoinSplitType.AND_LITERAL)
                .build();
        return route;
	}

	private void createComplexSplitConditionalPartTransitions(ActivityType sourceActivity, List<SequenceFlow> conditionals, SequenceFlow defaultSequence, FlowElementsContainer container) {
		for (SequenceFlow seq : conditionals) {
			ActivityType targetActivity = query.findActivity(seq.getTargetRef(), container);
			TransitionType transition = TransitionUtil.createTransition(seq.getId(), seq.getName(), getDescr(seq), processDef, sourceActivity, targetActivity);
			if (seq == defaultSequence && defaultSequence != null) {
				TransitionUtil.setSequenceOtherwiseCondition(transition);
			} else {
				TransitionUtil.setSequenceExpressionConditionOrTrue(transition, seq.getConditionExpression(), logger, failures);
			}
		}
	}

	private void createComplexSplitParallelToConditionalConnection(ActivityType source, ActivityType target) {
		String id = source.getId() + "_" + target.getId();
		String nameP1 = getNonEmptyName(source.getName(), source.getId(), source);
		String nameP2 = getNonEmptyName(target.getName(), target.getId(), target);
		String name = nameP1 + "_" + nameP2;

		TransitionType transition = TransitionUtil.createTransition(id, name, "", processDef, source, target);
		TransitionUtil.setSequenceTrueCondition(transition);
	}

	private List<SequenceFlow> getConditionals(List<SequenceFlow> all, List<SequenceFlow> unconditionals) {
		List<SequenceFlow> conditionals = new ArrayList<SequenceFlow>();
		conditionals.addAll(all);
		conditionals.removeAll(unconditionals);
		return conditionals;
	}

	private RoutingType getRoutingSplitType(FlowNode node, List<SequenceFlow> outgoings) {
		boolean hasValidDefault = hasValidDefaultSequence(node, outgoings);
		boolean hasConditionals = hasConditionalSequence(node, outgoings);
		boolean hasUnconditional = hasUnconditionalNonDefaultSequence(node, outgoings, hasValidDefault);

		if (hasValidDefault && hasUnconditional) return RoutingType.COMPLEX_CONDITIONAL_FLOW;
		if (hasConditionals) return RoutingType.SIMPLE_CONDITIONAL_FLOW;
		return RoutingType.UNCONTROLLED_FLOW;
	}

	private boolean hasValidDefaultSequence(FlowNode node, List<SequenceFlow> outgoings) {
		return (node instanceof Activity)
				&& ((Activity)node).getDefault() != null
				&& outgoings.contains(((Activity)node).getDefault());
	}

	private SequenceFlow getDefault(FlowNode node) {
		// 'getDefault' is not inherited - may be "ExclusiveGateway", "ComplexGateway", "Activity"...
		Class<?> cls = node.getClass();
		try {
			Method m = cls.getMethod("getDefault");
			Object deflt = m.invoke(node);
			if (deflt != null && deflt instanceof SequenceFlow) {
				return (SequenceFlow)deflt;
			}
		} catch (SecurityException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			logger.debug(e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.debug(e.getMessage());
		} catch (IllegalAccessException e) {
			logger.debug(e.getMessage());
		} catch (InvocationTargetException e) {
			logger.debug(e.getMessage());
		}
		return null;
	}

	private boolean hasConditionalSequence(FlowNode node, List<SequenceFlow> outgoings) {
		for (SequenceFlow seq : outgoings) {
			Expression expression = seq.getConditionExpression();
			FormalExpression formal = TransitionUtil.getFormalExpressionOrNull(expression);
			String informal = TransitionUtil.getInformalExpressionValueOrBlank(expression);
			if (!informal.trim().isEmpty()) return true;
			if (formal != null && formal.getBody() != null && !formal.getBody().trim().isEmpty()) return true;
		}
		return false;
	}

	private boolean hasUnconditionalNonDefaultSequence(FlowNode node, List<SequenceFlow> outgoings, boolean hasValidDefault) {
		SequenceFlow defaultSequence = (hasValidDefault) ? getDefault(node) : null;
		for (SequenceFlow seq : outgoings) {
			Expression expression = seq.getConditionExpression();
			FormalExpression formal = TransitionUtil.getFormalExpressionOrNull(expression);
			String informal = TransitionUtil.getInformalExpressionValueOrBlank(expression);
			if (   (!hasValidDefault || seq != defaultSequence)
				&& (informal.trim().isEmpty())
				&& (formal == null || formal.getBody() == null || formal.getBody().trim().isEmpty())) return true;
		}
		return false;
	}

	private List<SequenceFlow> getUnconditionalNonDefaults(FlowNode node, List<SequenceFlow> outgoings) {
		List<SequenceFlow> unconditionals = new ArrayList<SequenceFlow>();
		SequenceFlow defaultSequence = getDefault(node);
		for (SequenceFlow seq : outgoings) {
			Expression expression = seq.getConditionExpression();
			FormalExpression formal = TransitionUtil.getFormalExpressionOrNull(expression);
			String informal = TransitionUtil.getInformalExpressionValueOrBlank(expression);
			if (   (seq != defaultSequence)
				&& (informal.trim().isEmpty())
				&& (formal == null || formal.getBody() == null || formal.getBody().trim().isEmpty())) {
				unconditionals.add(seq);
			}
		}
		return unconditionals;
	}

	private String getDescr(BaseElement element) {
		return DocumentationTool.getDescriptionFromDocumentation(element.getDocumentation());
	}

}
