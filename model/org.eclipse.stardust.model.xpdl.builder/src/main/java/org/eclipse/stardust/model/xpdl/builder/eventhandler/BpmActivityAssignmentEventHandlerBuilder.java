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
