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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventhandler;

import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.eventhandler.AbstractEventHandlerBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class BpmActivityTimerEventHandlerBuilder extends AbstractEventHandlerBuilder<BpmActivityTimerEventHandlerBuilder>
{
   public BpmActivityTimerEventHandlerBuilder()
   {
   }

   @Override
   protected EventHandlerType finalizeElement()
   {
	  forConditionType(PredefinedConstants.TIMER_CONDITION);
      return super.finalizeElement();
   }

   public static BpmActivityTimerEventHandlerBuilder newActivityTimerEventHandler()
   {
      return new BpmActivityTimerEventHandlerBuilder();
   }

   public static BpmActivityTimerEventHandlerBuilder newActivityTimerEventHandler(ActivityType activity)
   {
      return newActivityTimerEventHandler().forActivity(activity);
   }

   public BpmActivityTimerEventHandlerBuilder withAutoBinding() {
	   element.setAutoBind(true);
	   return this;
   }

   public BpmActivityTimerEventHandlerBuilder withConstantPeriod(Period period) {
	   if (null == period) return this;
	   AttributeUtil.setBooleanAttribute(element, PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT, false);
	   AttributeUtil.setAttribute(element, PredefinedConstants.TIMER_PERIOD_ATT, "Period", period.toString());
	   return this;
   }

   public BpmActivityTimerEventHandlerBuilder consumedOnMatch() {
	   element.setConsumeOnMatch(true);
	   return this;
   }

}
