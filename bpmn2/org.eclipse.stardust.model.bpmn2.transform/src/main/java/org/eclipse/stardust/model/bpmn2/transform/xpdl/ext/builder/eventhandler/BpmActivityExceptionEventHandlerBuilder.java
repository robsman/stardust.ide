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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.eventhandler.AbstractEventHandlerBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class BpmActivityExceptionEventHandlerBuilder extends AbstractEventHandlerBuilder<BpmActivityExceptionEventHandlerBuilder>
{
   public BpmActivityExceptionEventHandlerBuilder()
   {
   }

   @Override
   protected EventHandlerType finalizeElement()
   {
	  forConditionType(PredefinedConstants.EXCEPTION_CONDITION);
      return super.finalizeElement();
   }

   public static BpmActivityExceptionEventHandlerBuilder newActivityExceptionEventHandler()
   {
      return new BpmActivityExceptionEventHandlerBuilder();
   }

   public static BpmActivityExceptionEventHandlerBuilder newActivityExceptionEventHandler(ActivityType activity)
   {
      return newActivityExceptionEventHandler().forActivity(activity);
   }

   public BpmActivityExceptionEventHandlerBuilder withAutoBinding() {
	   element.setAutoBind(true);
	   return this;
   }

   public BpmActivityExceptionEventHandlerBuilder forExceptionClass(String exceptionClass) {
	   AttributeUtil.setAttribute(element, PredefinedConstants.EXCEPTION_CLASS_ATT, exceptionClass);
	   return this;
   }

   public BpmActivityExceptionEventHandlerBuilder consumedOnMatch() {
	   element.setConsumeOnMatch(true);
	   return this;
   }

   public BpmActivityExceptionEventHandlerBuilder consumedOnMatch(boolean consume) {
	   element.setConsumeOnMatch(consume);
	   return this;
   }
}
