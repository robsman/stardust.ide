package org.eclipse.stardust.model.xpdl.builder.activity;

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;

public class BpmManualActivityBuilder
      extends AbstractInteractiveActivityBuilder<BpmManualActivityBuilder>
{
   public BpmManualActivityBuilder()
   {
      element.setImplementation(ActivityImplementationType.MANUAL_LITERAL);
   }

   @Override
   protected ActivityType finalizeElement()
   {
      // TODO more specific handling?
      
      return super.finalizeElement();
   }

}
