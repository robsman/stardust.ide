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

import org.eclipse.bpmn2.FormalExpression;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
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
    public static final String EXPRESSION_LANGUAGE_JAVA = "http://www.sun.com/java";

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

}
