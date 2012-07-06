package org.eclipse.stardust.model.xpdl.builder.transition;

import static org.eclipse.stardust.common.StringUtils.isEmpty;

import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;



public class BpmConditionalTransitionBuilder extends AbstractTransitionBuilder<BpmConditionalTransitionBuilder>
{
   @Override
   protected TransitionType finalizeElement()
   {
      super.finalizeElement();

      if ((null == element.getExpression()) && isEmpty(element.getCondition()))
      {
         onCondition("true");
      }

      return super.finalizeElement();
   }

   public BpmConditionalTransitionBuilder onCondition(String expression)
   {
      // TODO simple expressions into condition field
      element.setCondition("CONDITION");

      element.setExpression(F_CWM.createXmlTextNode());
      XpdlModelUtils.setCDataString(element.getExpression().getMixed(), expression, true);

      return this;
   }

   public BpmConditionalTransitionBuilder unconditionally()
   {
      // TODO simple expressions into condition field
      element.setCondition("CONDITION");

      element.setExpression(F_CWM.createXmlTextNode());
      XpdlModelUtils.setCDataString(element.getExpression().getMixed(), "true", true);

      return this;
   }

}
