/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.builder.transition;

import static org.eclipse.stardust.model.xpdl.builder.common.PropertySetter.elementById;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractProcessElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.PropertySetter;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;


public abstract class AbstractTransitionBuilder<B extends AbstractTransitionBuilder<B>>
      extends AbstractProcessElementBuilder<TransitionType, B>
{
   public AbstractTransitionBuilder()
   {
      super(F_CWM.createTransitionType());
   }

   @Override
   protected TransitionType finalizeElement()
   {
      super.finalizeElement();
      
      return element;
   }

   @Override
   protected EList<? super TransitionType> getElementContainer()
   {
      return process.getTransition();
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "Transition";
   }

   public static BpmConditionalTransitionBuilder newTransition()
   {
      return new BpmConditionalTransitionBuilder();
   }

   public static BpmConditionalTransitionBuilder newTransition(
         ProcessDefinitionType process)
   {
      return newTransition().inProcess(process);
   }

   public static BpmOtherwiseTransitionBuilder newOtherwiseTransition()
   {
      return new BpmOtherwiseTransitionBuilder();
   }

   public static BpmOtherwiseTransitionBuilder newOtherwiseTransition(
         ProcessDefinitionType process)
   {
      return newOtherwiseTransition().inProcess(process);
   }

   public static BpmActivitySequenceBuilder newActivitySequence()
   {
      return new BpmActivitySequenceBuilder();
   }

   @SuppressWarnings("unchecked")
   public B doForkOnTraversal()
   {
      element.setForkOnTraversal(true);

      return (B) this;
   }

   @SuppressWarnings("unchecked")
   public B betweenActivities(String fromActivityId, String toActivityId)
   {
      fromActivity(fromActivityId);
      toActivity(toActivityId);

      return (B) this;
   }

   @SuppressWarnings("unchecked")
   public B betweenActivities(ActivityType from, ActivityType to)
   {
      fromActivity(from);
      toActivity(to);

      return (B) this;
   }

   public B from(String fromActivityId)
   {
      return fromActivity(fromActivityId);
   }

   public B fromActivity(String activityId)
   {
      setters.add(elementById(PKG_CWM.getTransitionType_From(),
            PKG_CWM.getProcessDefinitionType_Activity(), activityId));

      return self();
   }

   public B from(ActivityType from)
   {
      return fromActivity(from);
   }

   public B fromActivity(ActivityType from)
   {
      setters.add(PropertySetter.directValue(PKG_CWM.getTransitionType_From(), from));
      
      return self();
   }

   public B to(String toActivityId)
   {
      return toActivity(toActivityId);
   }

   public B toActivity(String activityId)
   {
      setters.add(elementById(PKG_CWM.getTransitionType_To(),
            PKG_CWM.getProcessDefinitionType_Activity(), activityId));

      return self();
   }

   public B to(ActivityType to)
   {
      return toActivity(to);
   }

   public B toActivity(ActivityType to)
   {
      setters.add(PropertySetter.directValue(PKG_CWM.getTransitionType_To(), to));
      
      return self();
   }

}
