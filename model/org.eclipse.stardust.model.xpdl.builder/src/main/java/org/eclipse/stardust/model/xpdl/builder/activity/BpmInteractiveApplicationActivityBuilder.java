package org.eclipse.stardust.model.xpdl.builder.activity;

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;

public class BpmInteractiveApplicationActivityBuilder
      extends
      AbstractInteractiveActivityBuilder<BpmInteractiveApplicationActivityBuilder>
{

   public BpmInteractiveApplicationActivityBuilder()
   {
      element.setImplementation(ActivityImplementationType.ROUTE_LITERAL);
   }

   @Override
   protected ActivityType finalizeElement()
   {
      // TODO more specific handling?

      return super.finalizeElement();
   }

   public BpmInteractiveApplicationActivityBuilder usingApplication(
         ApplicationType application)
   {
      element.setApplication(application);

      return this;
   }

}
