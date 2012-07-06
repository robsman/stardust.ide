package org.eclipse.stardust.model.xpdl.builder.transition;

import org.eclipse.stardust.model.xpdl.carnot.TransitionType;


public class BpmOtherwiseTransitionBuilder
      extends AbstractTransitionBuilder<BpmOtherwiseTransitionBuilder>
{
   public BpmOtherwiseTransitionBuilder()
   {
      element.setCondition("OTHERWISE");
      element.eUnset(PKG_CWM.getTransitionType_Expression());
   }

   @Override
   protected TransitionType finalizeElement()
   {
      return super.finalizeElement();
   }

}
