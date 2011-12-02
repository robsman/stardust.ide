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

import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.*;


public class DefaultProcessDefinitionValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

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

         for (Iterator iter = proc.getActivity().iterator(); iter.hasNext();)
         {
            ActivityType activity = (ActivityType) iter.next();
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
            Set allActivities = new HashSet(proc.getActivity());
            
            List reachedActivities = new LinkedList();
            Set visitedActivities = new HashSet();
            reachedActivities.add(startActivity);
            
            // span activity graph reachable from starting activity
            while ( !reachedActivities.isEmpty())
            {
               ActivityType activity = (ActivityType) reachedActivities.remove(0);
               if ( !visitedActivities.contains(activity))
               {
                  visitedActivities.add(activity);
                  
                  for (Iterator i = activity.getOutTransitions().iterator(); i.hasNext();)
                  {
                     // add all reachable, unvisited activities
                     TransitionType transition = (TransitionType) i.next();
                     if ( !visitedActivities.contains(transition.getTo()))
                     {
                        reachedActivities.add(transition.getTo());
                     }
                  }
               }
            }

            allActivities.removeAll(visitedActivities);
            if ( !allActivities.isEmpty())
            {
               result.add(Issue.error(proc, MessageFormat.format(
                     Validation_Messages.MSG_PROCDEF_DisconnectedActivityGraph,
                     new Object[] {new Integer(allActivities.size())})));
            }
         }

         ValidationService vs = ValidationPlugin.getDefault().getValidationService();

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

   private boolean findDuplicateId(ProcessDefinitionType proc)
   {
      for (Iterator iter = ModelUtils.findContainingModel(proc).getProcessDefinition()
            .iterator(); iter.hasNext();)
      {
         ProcessDefinitionType otherProc = (ProcessDefinitionType) iter.next();
         if ((otherProc.getId().equals(proc.getId()))
               && (!proc.equals(otherProc)))
         {
            return true;
         }
      }
      return false;
   }
}
