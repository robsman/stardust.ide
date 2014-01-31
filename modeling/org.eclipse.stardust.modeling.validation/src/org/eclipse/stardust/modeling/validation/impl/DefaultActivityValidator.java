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
import java.util.*;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.model.beans.TransitionBean;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.util.XpdlUtil;
import org.eclipse.stardust.modeling.validation.*;

public class DefaultActivityValidator implements IModelElementValidator
{
   private static final int JOIN = 0;
   private static final int SPLIT = 1;

   private Set<ActivityType> checkedActivities;

   protected boolean performFullCheck()
   {
      return true;
   }

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      ActivityType activity = (ActivityType) element;

      if (findDuplicateId(activity))
      {
         result.add(Issue.error(activity, Validation_Messages.ERR_ACTIVITY_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      if (performFullCheck())
      {
         checkPerformer(result, activity);
         checkSubprocessActivity(result, activity);
         checkApplicationActivity(result, activity);
      }

      if (activity.getJoin() == null
            || activity.getJoin().getValue() == JoinSplitType.NONE)
      {
         if (hasMultipleTransitions(activity, JOIN))
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_MultipleIncomingTransitions,
                  ValidationService.PKG_CWM.getTransitionType_To()));
         }
      }

      if (activity.getSplit() == null
            || activity.getSplit().getValue() == JoinSplitType.NONE)
      {
         if (hasMultipleTransitions(activity, SPLIT))
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_MultipleOutgoingTransitions,
                  ValidationService.PKG_CWM.getTransitionType_From()));
         }
      }

      validateLoop(result, activity, activity.getLoop());

      Map<ActivityType, Boolean> targetActivities = new HashMap<ActivityType, Boolean>();
      for (TransitionType transition : activity.getOutTransitions())
      {
         if (transition.getTo() != null)
         {
            if (targetActivities.containsKey(transition.getTo()))
            {
               if (Boolean.FALSE.equals(targetActivities.get(transition.getTo())))
               {
                  // issue warning only once
                  result.add(Issue.warning(activity, MessageFormat.format(
                        Validation_Messages.ERR_ACTIVITY_MultipleTransitions,
                        new Object[] {activity.getId(), transition.getTo().getId()}),
                        ValidationService.PKG_CWM.getActivityType_LoopCondition()));

                  targetActivities.put(transition.getTo(), Boolean.TRUE);
               }
            }
            else
            {
               targetActivities.put(transition.getTo(), Boolean.FALSE);
            }
         }
      }

      checkedActivities = new HashSet<ActivityType>();
      ActivityType blockingActivity = checkXORANDBlock(activity, activity);

      if (blockingActivity != null)
      {
         result.add(Issue.warning(activity, MessageFormat.format(
               Validation_Messages.Msg_XORSplitANDJoinBlock,
                  activity.getName(), blockingActivity.getName()),
               ValidationService.PKG_CWM.getActivityType()));
      }

      ValidationService vs = ValidationService.getInstance();
      result.addAll(Arrays.asList(vs.validateModelElements(activity.getDataMapping())));
      result.addAll(Arrays.asList(vs.validateModelElements(activity.getEventHandler())));

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private void validateLoop(List<Issue> result, ActivityType activity,
         org.eclipse.stardust.model.xpdl.xpdl2.LoopType loop)
   {
      if (loop == null)
      {
         validateOldStyleLoop(result, activity);
      }
      else
      {
         switch (loop.getLoopType())
         {
         case STANDARD:
            validateLoopStandard(result, activity, loop.getLoopStandard());
            break;
         case MULTI_INSTANCE:
            // TODO (fh)
            break;
         default:
            // TODO (fh) add error
            break;
         }
      }
   }

   private void validateLoopStandard(List<Issue> result, ActivityType activity,
         LoopStandardType loopStandard)
   {
      if (loopStandard == null)
      {
         // TODO (fh) add error
      }
      else if (loopStandard.getTestTime() == null)
      {
         // TODO (fh) add error
      }
      else
      {
         String condition = XpdlUtil.getLoopStandardCondition(loopStandard);
         if (condition == null
               || condition.trim().length() == 0)
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_NoLoopCondition,
                  XpdlPackage.eINSTANCE.getLoopStandardType_LoopCondition()));
         }
         else if (!isValidLoopCondition(condition))
         {
            result.add(Issue.warning(activity,
                  Validation_Messages.ERR_ACTIVITY_InvalidLoopCondition,
                  XpdlPackage.eINSTANCE.getLoopStandardType_LoopCondition()));
         }
      }
   }

   private void validateOldStyleLoop(List<Issue> result, ActivityType activity)
   {
      /*if (activity.getLoopType() != null
            && (activity.getLoopType().getValue() == LoopType.WHILE || activity
                  .getLoopType().getValue() == LoopType.REPEAT))
      {
         if (activity.getLoopCondition() == null
               || activity.getLoopCondition().trim().length() == 0)
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_NoLoopCondition,
                  ValidationService.PKG_CWM.getActivityType_LoopCondition()));
         }
         else if (!isValidLoopCondition(activity.getLoopCondition()))
         {
            result.add(Issue.warning(activity,
                  Validation_Messages.ERR_ACTIVITY_InvalidLoopCondition,
                  ValidationService.PKG_CWM.getActivityType_LoopCondition()));
         }
      }*/
   }

   private ActivityType checkXORANDBlock(ActivityType startActivity,
         ActivityType currentActivity)
   {

      for (TransitionType outTransition : currentActivity.getOutTransitions())
      {
         currentActivity = outTransition.getTo();
         if (JoinSplitType.AND_LITERAL.equals(currentActivity.getJoin())
               && checkBackXORANDBlock(startActivity, currentActivity, outTransition))
         {
            return currentActivity;
         }
         if (!checkedActivities.contains(currentActivity))
         {
            checkedActivities.add(currentActivity);
            return checkXORANDBlock(startActivity, currentActivity);
         }
      }
      return null;
   }

   private boolean checkBackXORANDBlock(ActivityType startActivity,
         ActivityType currentActivity, TransitionType outTransition)
   {
      for (TransitionType inTransition : currentActivity.getInTransitions())
      {
         if (outTransition != null && outTransition.equals(inTransition))
         {
            outTransition = null;
         }
         else
         {
            currentActivity = inTransition.getFrom();
            if (JoinSplitType.AND_LITERAL.equals(currentActivity.getSplit()))
            {
               return false;
            }
            if (currentActivity.equals(startActivity))
            {
               return true;
            }
            if (!checkedActivities.contains(currentActivity))
            {
               checkedActivities.add(currentActivity);
               return checkBackXORANDBlock(startActivity, currentActivity, outTransition);
            }
         }
      }
      return false;
   }

   private void checkApplicationActivity(List<Issue> result, ActivityType activity)
   {
      if (ActivityUtil.isApplicationActivity(activity))
      {
         if (activity.getApplication() == null)
         {
            result.add(Issue.error(activity, MessageFormat.format(
                  Validation_Messages.ERR_ACTIVITYNoApplication, activity.getName()),
                  ValidationService.PKG_CWM.getActivityType_Application()));
         }
      }
   }

   private void checkSubprocessActivity(List<Issue> result, ActivityType activity)
   {
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
            result.add(Issue.warning(activity, MessageFormat.format(
                  Validation_Messages.ERR_ACTIVITY_SubProcessMode, activity.getName()),
                  ValidationService.PKG_CWM.getActivityType_SubProcessMode()));
         }
      }
   }

   private void checkPerformer(List<Issue> result, ActivityType activity)
   {
      if (ActivityUtil.isInteractive(activity))
      {
         if (null == activity.getPerformer())
         {
            result.add(Issue.error(activity,
                  Validation_Messages.ERR_ACTIVITY_NoPerformerSet,
                  ValidationService.PKG_CWM.getActivityType_Performer()));
         }

         boolean isQualityAssurance = AttributeUtil.getBooleanValue((IExtensibleElement) activity, PredefinedConstants.ACTIVITY_IS_QUALITY_ASSURANCE_ATT);

         if (isQualityAssurance)
         {
            IModelParticipant performer = activity.getPerformer();
            if (performer != null)
            {
               if (performer instanceof ConditionalPerformerType)
               {
                  result.add(Issue.error(activity,
                        Validation_Messages.ERR_ACTIVITY_QualityAssurancePerformer,
                        ValidationService.PKG_CWM.getActivityType_Performer()));
               }
            }

            IModelParticipant qualityControlPerformer = activity.getQualityControlPerformer();
            if (qualityControlPerformer == null)
            {

            }
            else
            {
               if (qualityControlPerformer instanceof ConditionalPerformerType)
               {
                  result.add(Issue.error(activity,
                        Validation_Messages.ERR_ACTIVITY_QualityAssurancePerformer,
                        ValidationService.PKG_CWM.getActivityType_QualityControlPerformer()));
               }
            }
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
   }

   private boolean isValidLoopCondition(String condition)
   {
      // TODO: (fh) syntactic check?
      return true;
   }

   private boolean hasMultipleTransitions(ActivityType activity, int type)
   {
      ProcessDefinitionType process = (ProcessDefinitionType) activity.eContainer();
      int count = 0;

      List<TransitionType> removeTransitions = new ArrayList<TransitionType>();
      List<TransitionType> transitions = new ArrayList<TransitionType>();
      transitions.addAll(process.getTransition());
      for(EventHandlerType eventHandler : activity.getEventHandler())
      {
         TransitionType exceptionTransition = getExceptionTransition(activity.getOutTransitions(), eventHandler.getId());
         if(exceptionTransition != null)
         {
            removeTransitions.add(exceptionTransition);
         }
      }
      transitions.removeAll(removeTransitions);

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
      for (ActivityType otherActivity : ModelUtils.findContainingProcess(activity).getActivity())
      {
         if ((otherActivity.getId().equals(activity.getId()))
               && (!activity.equals(otherActivity)))
         {
            return true;
         }
      }
      return false;
   }

   private TransitionType getExceptionTransition(List<TransitionType> outTransitions, String eventHandlerId)
   {
      if (outTransitions == null || StringUtils.isEmpty(eventHandlerId))
      {
         return null;
      }

      String condition = TransitionBean.ON_BOUNDARY_EVENT_PREDICATE + "(" + eventHandlerId + ")"; //$NON-NLS-1$ //$NON-NLS-2$
      for (TransitionType t : outTransitions)
      {
         String expression = getExpression(t);
         if (expression != null && condition.equals(expression))
         {
            return t;
         }
      }
      return null;
   }

   private String getExpression(TransitionType transition)
   {
      XmlTextNode type = transition.getExpression();
      String expression = type == null ? null : ModelUtils.getCDataString(transition.getExpression().getMixed());
      return expression;
   }
}