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

import java.util.List;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;

/**
 * @author Simon Nikles
 *
 */
public class SequenceFlow2Stardust extends AbstractElement2Stardust {

	public SequenceFlow2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addSequenceFlow(SequenceFlow seq, FlowElementsContainer container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(seq, container);
		if (processDef == null) return;

		if (CarnotModelQuery.findTransition(processDef, seq.getId()) != null) return;

		FlowNode sourceNode = seq.getSourceRef();
		FlowNode targetNode = seq.getTargetRef();

		if (sourceNode instanceof Gateway || targetNode instanceof Gateway)
			return;

		if (sourceNode instanceof StartEvent)
			return;

		if (targetNode instanceof EndEvent) {
			if (!endEventTransformedToActivity(targetNode, container)) return;
		}

		//if (sourceNode instanceof Activity && targetNode instanceof Activity) {
			addActivityToActivityTransition(seq, sourceNode, targetNode, container, processDef);
		//}
	}

	private boolean endEventTransformedToActivity(FlowNode targetNode, FlowElementsContainer container) {
		ActivityType targetActivity = query.findActivity(targetNode, container);
		return targetActivity != null;
	}

	private void addActivityToActivityTransition(SequenceFlow seq, FlowNode sourceNode, FlowNode targetNode, FlowElementsContainer container, ProcessDefinitionType processDef) {
		ActivityType sourceActivity = query.findActivity(sourceNode, container);
		ActivityType targetActivity = query.findActivity(targetNode, container);
		if (sourceActivity != null && targetActivity != null) {
			String documentation = DocumentationTool.getDescriptionFromDocumentation(seq.getDocumentation());
			String name = getNonEmptyName(seq.getName(), seq.getId(), seq);
			TransitionType transition = TransitionUtil.createTransition(seq.getId(), name, documentation,
					processDef, sourceActivity, targetActivity);

			TransitionUtil.setSequenceExpressionConditionOrTrue(transition, seq.getConditionExpression(), logger, failures);

			// TODO transition.setForkOnTraversal()
			processDef.getTransition().add(transition);
		} else {
			failures.add("No valid source and target for sequence flow: " + seq.getId() + " sourceRef "
					+ seq.getSourceRef() + " targetRef " + seq.getTargetRef());
		}
	}

//	private void addActivityToActivityTransition(SequenceFlow seq, FlowNode sourceNode, FlowNode targetNode, FlowElementsContainer container, ProcessDefinitionType processDef) {
//		ActivityType sourceActivity = query.findActivity(sourceNode, container);
//		ActivityType targetActivity = query.findActivity(targetNode, container);
//		if (sourceActivity != null && targetActivity != null) {
//			String documentation = DocumentationTool.getDescriptionFromDocumentation(seq.getDocumentation());
//			TransitionType transition = TransitionUtil.createTransition(seq.getId(), seq.getName(), documentation,
//					processDef, sourceActivity, targetActivity);
//			if (seq.getConditionExpression() != null) {
//				if (seq.getConditionExpression() instanceof FormalExpression) {
//					TransitionUtil.setSequenceFormalCondition(transition, (FormalExpression) seq.getConditionExpression(), failures);
//				} else if (seq.getConditionExpression().getDocumentation() != null
//						&& seq.getConditionExpression().getDocumentation().get(0) != null
//						&& !seq.getConditionExpression().getDocumentation().get(0).getText().equals("")) {
//					TransitionUtil.setSequenceInformalCondition(transition, seq.getConditionExpression().getDocumentation().get(0).getText());
//				} else {
//					TransitionUtil.setSequenceTrueCondition(transition);
//				}
//			}
//			// TODO transition.setForkOnTraversal()
//			processDef.getTransition().add(transition);
//		} else {
//			failures.add("No valid source and target for sequence flow: " + seq.getId() + " sourceRef "
//					+ seq.getSourceRef() + " targetRef " + seq.getTargetRef());
//		}
//	}
}
