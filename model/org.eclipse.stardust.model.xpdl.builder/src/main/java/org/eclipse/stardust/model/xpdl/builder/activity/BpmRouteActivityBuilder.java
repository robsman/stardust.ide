package org.eclipse.stardust.model.xpdl.builder.activity;

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;


public class BpmRouteActivityBuilder
      extends AbstractActivityBuilder<BpmRouteActivityBuilder>
{

   public BpmRouteActivityBuilder()
   {
      element.setImplementation(ActivityImplementationType.ROUTE_LITERAL);
   }

   @Override
   protected ActivityType finalizeElement()
   {
      // TODO more specific handling?
      
      return super.finalizeElement();
   }

}
