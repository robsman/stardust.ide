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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.api.runtime.ActivityInstanceState;
import org.eclipse.stardust.model.xpdl.builder.eventaction.AbstractEventActionBuilder;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class BpmCompleteActivityEventActionBuilder
      extends AbstractEventActionBuilder<EventActionType, BpmCompleteActivityEventActionBuilder>
{
   public BpmCompleteActivityEventActionBuilder(EventHandlerType handler)
   {
      super(handler, F_CWM.createEventActionType());
      forActionType(PredefinedConstants.COMPLETE_ACTIVITY_ACTION);
      AttributeUtil.setAttribute(element, PredefinedConstants.TARGET_STATE_ATT, ActivityInstanceState.class.toString(), String.valueOf(ActivityInstanceState.COMPLETED));
   }

   @Override
   protected EventActionType finalizeElement()
   {
      return super.finalizeElement();
   }

   public static BpmCompleteActivityEventActionBuilder newScheduleActivityAction(EventHandlerType handler)
   {
      return new BpmCompleteActivityEventActionBuilder(handler);
   }


}
