package org.eclipse.stardust.model.xpdl.builder.eventhandler;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractActivityElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;



public abstract class AbstractEventHandlerBuilder<B extends AbstractEventHandlerBuilder<B>>
      extends AbstractActivityElementBuilder<EventHandlerType, B>
{
   public AbstractEventHandlerBuilder()
   {
      super(F_CWM.createEventHandlerType());
   }

   @Override
   protected EventHandlerType finalizeElement()
   {
      super.finalizeElement();
      
      activity.getEventHandler().add(element);

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "EventHandler";
   }

   protected B forConditionType(String conditionTypeId)
   {
      return forConditionType(XpdlModelUtils.findElementById(model.getEventConditionType(),
            conditionTypeId));
   }

   @SuppressWarnings("unchecked")
   protected B forConditionType(EventConditionTypeType conditionType)
   {
      if (null != conditionType)
      {
         element.setType(conditionType);
      }
      else
      {
         element.eUnset(PKG_CWM.getEventHandlerType_Type());
      }

      return (B) this;
   }

}
