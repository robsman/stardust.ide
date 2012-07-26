/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.validation.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.LoopType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;


/**
 * @deprecated Superseeded by {@link DefaultActivityValidator}, please remove.
 */
public class ActivityValidator implements IModelElementValidator
{
   private static final int JOIN = 0;

   private static final int SPLIT = 1;

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      ActivityType activity = (ActivityType) element;

      if (findDuplicateId(activity))
      {
         result.add(Issue.error(activity, Validation_Messages.ERR_ACTIVITY_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      if (ActivityUtil.isInteractive(activity))
      {
         if (null == activity.getPerformer())
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_NoPerformerSet,
                  ValidationService.PKG_CWM.getActivityType_Performer()));
         }
         /*
          * TODO rsauer: obsolete? if ((!StringUtils.isEmpty(activity.getPerformer())) &&
          * (findConditionalPerformer(activity) == null)) { result .add(Issue .error(
          * activity, MessageFormat .format( "The associated performer \"{2}\" set for
          * manual or interactive application activity \"{0}\" doesn't exist in the
          * model.", arguments), ValidationService.PKG_CWM .getActivityType_Performer())); }
          */
      }
      else
      {
         if (null != activity.getPerformer())
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_PerformerWronglySet,
                  ValidationService.PKG_CWM.getActivityType_Performer()));
         }
      }

      if (ActivityUtil.isSubprocessActivity(activity))
      {
         if (null == activity.getImplementationProcess())
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_NoImplementationProcess,
                  ValidationService.PKG_CWM.getActivityType_ImplementationProcess()));
         }
         else if (null == activity.getSubProcessMode())
         {
            result.add(Issue.warning(activity, MessageFormat
                  .format(Validation_Messages.ERR_ACTIVITY_SubProcessMode,
                        new String[] {activity.getName()}), ValidationService.PKG_CWM
                  .getActivityType_SubProcessMode()));
         }
      }

      // Rule: if implementation type is application, an application must be set
      if (ActivityUtil.isApplicationActivity(activity))
      {
         if (activity.getApplication() == null)
         {
            result.add(Issue.error(activity,
                  MessageFormat.format(
                        Validation_Messages.ERR_ACTIVITYNoApplication,
                        new String[] {activity.getName()}),
                  ValidationService.PKG_CWM.getActivityType_Application()));
         }
      }

      if (activity.getJoin() == null
            || activity.getJoin().getValue() == JoinSplitType.NONE)
      {
         if (hasMultipleTransitions(activity, JOIN))
         {
            result.add(Issue.error(activity, Validation_Messages.ERR_ACTIVITY_MultipleIncomingTransitions,
                  ValidationService.PKG_CWM.getTransitionType_To()));
         }
      }

      if (activity.getSplit() == null
            || activity.getSplit().getValue() == JoinSplitType.NONE)
      {
         if (hasMultipleTransitions(activity, SPLIT))
         {
            result.add(Issue.error(activity, Validation_Messages.ERR_ACTIVITY_MultipleOutgoingTransitions,
                  ValidationService.PKG_CWM.getTransitionType_From()));
         }
      }

      if (activity.getLoopType() != null &&
         (activity.getLoopType().getValue() == LoopType.WHILE ||
          activity.getLoopType().getValue() == LoopType.REPEAT))
      {
         if (activity.getLoopCondition() == null ||
            activity.getLoopCondition().trim().length() == 0)
         {
            result.add(Issue.error(activity, Validation_Messages.ERR_ACTIVITY_NoLoopCondition,
                  ValidationService.PKG_CWM.getActivityType_LoopCondition()));
         }
         else if (!isValidLoopCondition(activity.getLoopCondition()))
         {
            result.add(Issue.warning(activity, Validation_Messages.ERR_ACTIVITY_InvalidLoopCondition,
                  ValidationService.PKG_CWM.getActivityType_LoopCondition()));
         }
      }

      ValidationService vs = ValidationService.getInstance();
      result.addAll(Arrays.asList(vs.validateModelElements(activity.getDataMapping())));
      result.addAll(Arrays.asList(vs.validateModelElements(activity.getEventHandler())));

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean isValidLoopCondition(String condition)
   {
      // todo: (fh) syntactic check?
      return true;
   }

   private boolean hasMultipleTransitions(ActivityType activity, int type)
   {
      ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
      int count = 0;
      List transitions = process.getTransition();
      for (int i = 0; i < transitions.size(); i++)
      {
         TransitionType trans = (TransitionType) transitions.get(i);
         switch (type)
         {
         case JOIN:
            if (trans.getTo() == activity)
            {
               count++;
            }
            break;
         case SPLIT:
            if (trans.getFrom() == activity)
            {
               count++;
            }
         }
         if (count > 1)
         {
            return true;
         }
      }
      return false;
   }

   private boolean findDuplicateId(ActivityType activity)
   {
      for (Iterator iter = ModelUtils.findContainingProcess(activity).getActivity()
            .iterator(); iter.hasNext();)
      {
         ActivityType otherActivity = (ActivityType) iter.next();
         if ((otherActivity.getId().equals(activity.getId()))
               && (!activity.equals(otherActivity)))
         {
            return true;
         }
      }
      return false;
   }

}
