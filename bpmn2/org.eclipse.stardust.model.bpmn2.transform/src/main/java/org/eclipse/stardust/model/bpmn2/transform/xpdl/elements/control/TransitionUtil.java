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

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newTransition;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.Expression;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.bpmn2.SequenceFlow;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.XmlTextNode;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

/**
 * @author Simon Nikles
 *
 */
public class TransitionUtil {

    public static final String CONDITION_KEY = "CONDITION";
    public static final String OTHERWISE_KEY = "OTHERWISE";
    // TODO Change to JavaScript: e.g. http://ecma-international.org/ecma-262 (?)
    public static final String EXPRESSION_LANGUAGE_JAVA = "http://www.java.com/java"; //"http://www.sun.com/java";

    public static TransitionType createTransition(String id, String name, String documentation, ProcessDefinitionType process, ActivityType sourceActivity, ActivityType targetActivity) {
        TransitionType transition =
                newTransition()
                .forProcess(process)
                .betweenActivities(sourceActivity, targetActivity)
                .withIdAndName(id, name)
                .withDescription(documentation)
                .build();
        return transition;
    }

    public static void setSequenceExpressionConditionOrTrue(TransitionType transition, Expression expression, Logger logger, List<String> failures) {

        String expressionVal = getInformalExpressionValueOrBlank(expression);
        FormalExpression formal = getFormalExpressionOrNull(expression);
        if (formal != null) {
            logger.debug("transition: " + transition.getId() + " setSequenceFormalCondition ");
            setSequenceFormalCondition(transition, formal, failures);
        } else if (!expressionVal.trim().equals("")) {
            logger.debug("transition: " + transition.getId() + " setSequenceInformalCondition: " + expressionVal);
            setSequenceInformalCondition(transition, expressionVal);
        } else {
            logger.debug("transition: " + transition.getId() + " setSequenceTrueCondition");
            setSequenceTrueCondition(transition);
        }
    }

    public static void setTransitionExpression(TransitionType transition, String expression) {
        transition.setCondition(CONDITION_KEY);
        XmlTextNode expressionNode = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
        ModelUtils.setCDataString(expressionNode.getMixed(), expression, true);
        transition.setExpression(expressionNode);
    }

    public static String getTransitionExpression(TransitionType transition) {
    	String expression = "";
    	XmlTextNode node = transition.getExpression();
    	if (null == node) return "";
    	expression = ModelUtils.getCDataString(node.getMixed());
    	if (null == expression) expression = "";
    	return expression;
    }

    public static void setSequenceOtherwiseCondition(TransitionType transition) {
        transition.setCondition(OTHERWISE_KEY);
    }

    public static void setSequenceTrueCondition(TransitionType transition) {
        transition.setCondition(CONDITION_KEY);
        XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
        ModelUtils.setCDataString(expression.getMixed(), "true", true);
        transition.setExpression(expression);
    }

    public static void setSequenceInformalCondition(TransitionType transition, String string) {
//		DescriptionType descrType = transition.getDescription();
//		String val = descrType.getMixed().getValue(CarnotWorkflowModelPackage.DESCRIPTION_TYPE__MIXED).toString();
//		descrType.getMixed().setValue(CarnotWorkflowModelPackage.DESCRIPTION_TYPE__MIXED, val + " " + string);
//		transition.setDescription(descrType);
        transition.setCondition(CONDITION_KEY);
        XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
        ModelUtils.setCDataString(expression.getMixed(), string, true);
        transition.setExpression(expression);
    }

    public static void setSequenceFormalCondition(TransitionType transition, FormalExpression formalExpression, List<String> failures) {
        if (formalExpression.getLanguage().equals(EXPRESSION_LANGUAGE_JAVA)) {
                transition.setCondition(CONDITION_KEY);
                XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
                ModelUtils.setCDataString(expression.getMixed(), formalExpression.getBody(), true);
                transition.setExpression(expression);
        } else {
            String expr = formalExpression.getLanguage()
                    + " \n" + formalExpression.getBody();
            setSequenceInformalCondition(transition, expr);
            failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "(Sequence - " + transition.getId() + " - Expression language (" + formalExpression.getLanguage() + ") not supported.");
        }
    }

    public static String getInformalExpressionValueOrBlank(Expression expression) {
    	return DocumentationTool.getInformalExpressionValue(expression);
    }

    public static FormalExpression getFormalExpressionOrNull(Expression expression) {
        if (expression != null) {
        	if (expression instanceof FormalExpression) {
        		return (FormalExpression) expression;
        	}
        }
        return null;
    }

    public static boolean hasOtherwiseCondition(TransitionType transition) {
    	return transition.getCondition().equals(OTHERWISE_KEY);
    }

	public static void setStardustBoundaryOutgoingCondition(TransitionType transition, BoundaryEvent sourceNode, SequenceFlow seq) {
		transition.setCondition(CONDITION_KEY);
        XmlTextNode expression = CarnotWorkflowModelFactory.eINSTANCE.createXmlTextNode();
        String condition = "ON_BOUNDARY_EVENT("+sourceNode.getId()+");";
        ModelUtils.setCDataString(expression.getMixed(), condition, true);
        transition.setExpression(expression);
		//<Condition Type="CONDITION">ON_BOUNDARY_EVENT(BoundaryTimer)</Condition>		
	}
}
