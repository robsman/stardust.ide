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
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.engine.core.model.utils.ExclusionComputer;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class DefaultProcessDefinitionValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();

      if (element instanceof ProcessDefinitionType)
      {
         ProcessDefinitionType proc = (ProcessDefinitionType) element;

         if (findDuplicateId(proc))
         {
            result.add(Issue.error(proc, Validation_Messages.MSG_DuplicateProcessDefinitionId,
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }

         ActivityType startActivity = null;
         String otherStartActivities = null;

         for (ActivityType activity : proc.getActivity())
         {
            if (activity.getInTransitions().isEmpty())
            {
               if (startActivity == null)
               {
                  startActivity = activity;
               }
               else if (otherStartActivities == null)
               {
                  otherStartActivities = MessageFormat.format("\"{0}\", \"{1}\"", //$NON-NLS-1$
                        new Object[] {startActivity.getId(), activity.getId()});
               }
               else
               {
                  otherStartActivities = MessageFormat.format(", \"{0}\"", //$NON-NLS-1$
                        new Object[] {activity.getId()});
               }
            }
         }

         if (startActivity == null)
         {
            result.add(Issue.error(proc, Validation_Messages.MSG_NoStartActivity));
         }

         if (otherStartActivities != null)
         {
            result.add(Issue.error(proc, MessageFormat.format(
                  Validation_Messages.MSG_MultipleSartActivities,
                  new Object[] {otherStartActivities})));
         }

         if (proc.getActivity().isEmpty())
         {
            result.add(Issue.error(proc, Validation_Messages.MSG_NoActivity));
         }

         if (null != startActivity)
         {
            Set<ActivityType> allActivities = CollectionUtils.newSet();
            allActivities.addAll(proc.getActivity());

            List<ActivityType> reachedActivities = CollectionUtils.newLinkedList();
            Set<ActivityType> visitedActivities = CollectionUtils.newSet();
            reachedActivities.add(startActivity);

            // span activity graph reachable from starting activity
            while (!reachedActivities.isEmpty())
            {
               ActivityType activity = (ActivityType) reachedActivities.remove(0);
               if (!visitedActivities.contains(activity))
               {
                  visitedActivities.add(activity);
                  for (TransitionType transition : activity.getOutTransitions())
                  {
                     // add all reachable, unvisited activities
                     if ( !visitedActivities.contains(transition.getTo()))
                     {
                        reachedActivities.add(transition.getTo());
                     }
                  }
               }
            }

            allActivities.removeAll(visitedActivities);
            if (!allActivities.isEmpty())
            {
               result.add(Issue.error(proc, MessageFormat.format(
                     Validation_Messages.MSG_PROCDEF_DisconnectedActivityGraph,
                     new Object[] {new Integer(allActivities.size())})));
            }
            else
            {
               checkForDeadlocks(result, proc);
            }
         }

         // validate auditTrailPersistence setting
         AttributeType auditTrailPersistenceAttribute = AttributeUtil.getAttribute(
               (IExtensibleElement) element, "carnot:engine:auditTrailPersistence"); //$NON-NLS-1$

         if (auditTrailPersistenceAttribute != null)
         {
            String auditTrailPersistence = auditTrailPersistenceAttribute.getValue();
            List<String> options = ModelUtils.getPersistenceOptions((ProcessDefinitionType) element);
            if ( !options.contains(auditTrailPersistence))
            {
               result.add(Issue.warning(
                     proc,
                     MessageFormat.format(
                           Validation_Messages.MSG_PERSISTENCE_OPTION_NOT_ALLOWED,
                           new Object[] {ModelUtils.getPersistenceOptionsText(auditTrailPersistence)})));
            }
         }

         ValidationService vs = ValidationService.getInstance();

         result.addAll(Arrays.asList(vs.validateModelElements(proc.getTrigger())));

         result.addAll(Arrays.asList(vs.validateModelElements(proc.getActivity())));

         result.addAll(Arrays.asList(vs.validateModelElements(proc.getTransition())));

         result.addAll(Arrays.asList(vs.validateModelElements(proc.getEventHandler())));

         result.addAll(Arrays.asList(vs.validateModelElements(proc.getDataPath())));

         result.addAll(Arrays.asList(vs.validateModelElements(proc.getDiagram())));

         // TODO handle exception
         // catch (Exception e)
         // {
         // throw new InternalException("Process definition '" + getId() + "' cannot be
         // checked.", e);
         // }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private void checkForDeadlocks(List<Issue> result, ProcessDefinitionType proc)
   {
      ExclusionComputer<ActivityType, TransitionType> computer = new ExclusionComputer<ActivityType, TransitionType>()
      {
         protected ActivityType getFrom(TransitionType transition) {return transition.getFrom();}
         protected ActivityType getTo(TransitionType transition) {return transition.getTo();}
         protected Iterable<TransitionType> getIn(ActivityType activity) {return activity.getInTransitions();}
         protected boolean isInclusiveJoin(ActivityType activity) {return activity.getJoin() == JoinSplitType.AND_LITERAL
               || activity.getJoin() == JoinSplitType.OR_LITERAL;}
      };
      for (ActivityType activity : proc.getActivity())
      {
         ActivityType blockingActivity = computer.getBlockingActivity(activity);
         // we want to show the deadlock only once.
         if (blockingActivity != null && activity.getId().compareTo(blockingActivity.getId()) < 0)
         {
            result.add(Issue.warning(proc, MessageFormat.format(
                  Validation_Messages.Msg_PotentialDeadlock,
                     activity.getName(), blockingActivity.getName()),
                  ValidationService.PKG_CWM.getProcessDefinitionType()));
         }
      }
   }

   private boolean findDuplicateId(ProcessDefinitionType proc)
   {
      ModelType model = ModelUtils.findContainingModel(proc);
      for (ProcessDefinitionType otherProc : model.getProcessDefinition())
      {
         if (otherProc.getId().equals(proc.getId()) && !proc.equals(otherProc))
         {
            return true;
         }
      }
      return false;
   }

}
