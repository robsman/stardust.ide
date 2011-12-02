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
package org.eclipse.stardust.modeling.debug.engine;

import java.util.*;

import ag.carnot.workflow.runtime.beans.ActivityThread;
import ag.carnot.workflow.runtime.beans.ActivityThreadContextAdapter;
import ag.carnot.workflow.runtime.beans.IActivityInstance;
import ag.carnot.workflow.runtime.beans.TransitionTokenBean;

/**
 * This class is adapted from
 * {@link ag.carnot.workflow.tools.defdesk.debugger.DebugActivityThreadContext}. 
 * 
 * @author sborn
 * @version $Revision$
 */
public class DebugActivityThreadContext extends ActivityThreadContextAdapter
{
   private List listeners = new ArrayList();

   private Set traversedItems = new HashSet();
   private Map activeItems = new HashMap();

   public void addToWorkflowEventListeners(WorkflowEventListener listener)
   {
      listeners.add(listener);
      
      addWorkflowEventListener(listener);
   }

   public boolean isStepMode()
   {
      return false;
   }

   public void suspendActivityThread(ActivityThread activityThread)
   {
      // todo/laokoon implement thread swapping
   }

   public void enteringTransition(TransitionTokenBean transitionToken)
   {
      Set instances = (Set) activeItems.get(transitionToken.getTransition());
      if (null == instances)
      {
         instances = new HashSet();
         activeItems.put(transitionToken.getTransition(), instances);
      }
      instances.add(transitionToken);

      for (Iterator i = listeners.iterator(); i.hasNext();)
      {
         WorkflowEventListener listener = (WorkflowEventListener) i.next();
         listener.performedTransition(transitionToken);
      }
   }

   public void completingTransition(TransitionTokenBean transitionToken)
   {
      traversedItems.add(transitionToken.getTransition());

      Set instances = (Set) activeItems.get(transitionToken.getTransition());
      if (null != instances)
      {
         instances.remove(transitionToken);
         if (instances.isEmpty())
         {
            activeItems.remove(transitionToken.getTransition());
         }
      }

      for (Iterator i = listeners.iterator(); i.hasNext();)
      {
         WorkflowEventListener listener = (WorkflowEventListener) i.next();
         listener.performedTransition(transitionToken);
      }
   }

   public void enteringActivity(IActivityInstance activityInstance)
   {
      Set instances = (Set) activeItems.get(activityInstance.getActivity());
      if (null == instances)
      {
         instances = new HashSet();
         activeItems.put(activityInstance.getActivity(), instances);
      }
      instances.add(activityInstance);

      fireActivityStarted(activityInstance);
   }

   public void completingActivity(IActivityInstance activityInstance)
   {
      traversedItems.add(activityInstance.getActivity());

      Set instances = (Set) activeItems.get(activityInstance.getActivity());
      if (null != instances)
      {
         instances.remove(activityInstance);
         if (instances.isEmpty())
         {
            activeItems.remove(activityInstance.getActivity());
         }
      }

      fireActivityCompleted(activityInstance);
   }

   public void handleWorklistItem(IActivityInstance activityInstance)
   {
      // todo/laokoon
      fireAppendedToWorklist(activityInstance);
   }

   public boolean allowsForceAssignmentToHuman()
   {
      return false;
   }

   public boolean isActiveItem(Object item)
   {
      return activeItems.containsKey(item);
   }

   public boolean isTraversedItem(Object item)
   {
      return traversedItems.contains(item);
   }
   
   protected void fireAppendedToWorklist(IActivityInstance ai)
   {
      if ((null != listeners) && !listeners.isEmpty())
      {
         for (int i = 0; i < listeners.size(); i++ )
         {
            WorkflowEventListener tmp = (WorkflowEventListener) listeners.get(i);
            tmp.appendedToWorklist(ai);
         }
      }
   }
}
