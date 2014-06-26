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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.bindaction;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.ActivityInstanceState;
import org.eclipse.stardust.model.xpdl.builder.eventaction.AbstractEventActionBuilder;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class BpmScheduleActivityBindActionBuilder
      extends AbstractEventActionBuilder<BindActionType, BpmScheduleActivityBindActionBuilder>
{
   public BpmScheduleActivityBindActionBuilder(EventHandlerType handler)
   {
      super(handler, F_CWM.createBindActionType());
      forActionType(PredefinedConstants.SCHEDULE_ACTIVITY_ACTION);
   }

   @Override
   protected BindActionType finalizeElement()
   {
      return super.finalizeElement();
   }

   public static BpmScheduleActivityBindActionBuilder newScheduleActivityAction(EventHandlerType handler)
   {
      return new BpmScheduleActivityBindActionBuilder(handler);
   }

   public BpmScheduleActivityBindActionBuilder withTargetState(ActivityInstanceState state)
   {
      AttributeUtil.setAttribute(element, PredefinedConstants.TARGET_STATE_ATT, ActivityInstanceState.class.toString(), String.valueOf(state.getValue()));
      return this;
   }

   public BpmScheduleActivityBindActionBuilder withTargetStateSuspended()
   {
      AttributeUtil.setAttribute(element, PredefinedConstants.TARGET_STATE_ATT, ActivityInstanceState.class.toString(), String.valueOf(ActivityInstanceState.SUSPENDED));
      return this;
   }

   public BpmScheduleActivityBindActionBuilder withTargetStateHibernated()
   {
      AttributeUtil.setAttribute(element, PredefinedConstants.TARGET_STATE_ATT, ActivityInstanceState.class.toString(), String.valueOf(ActivityInstanceState.HIBERNATED));
      return this;
   }

}
