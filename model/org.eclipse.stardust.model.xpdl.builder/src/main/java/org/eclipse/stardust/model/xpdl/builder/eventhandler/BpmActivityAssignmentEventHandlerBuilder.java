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
package org.eclipse.stardust.model.xpdl.builder.eventhandler;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;

public class BpmActivityAssignmentEventHandlerBuilder
      extends AbstractEventHandlerBuilder<BpmActivityAssignmentEventHandlerBuilder>
{
   public BpmActivityAssignmentEventHandlerBuilder()
   {
      forConditionType(PredefinedConstants.ACTIVITY_ON_ASSIGNMENT_CONDITION);
   }

   @Override
   protected EventHandlerType finalizeElement()
   {
      return super.finalizeElement();
   }

   public static BpmActivityAssignmentEventHandlerBuilder newActivityAssignmentHandler()
   {
      return new BpmActivityAssignmentEventHandlerBuilder();
   }

   public static BpmActivityAssignmentEventHandlerBuilder newActivityAssignmentHandler(
         ActivityType activity)
   {
      return newActivityAssignmentHandler().forActivity(activity);
   }

}
