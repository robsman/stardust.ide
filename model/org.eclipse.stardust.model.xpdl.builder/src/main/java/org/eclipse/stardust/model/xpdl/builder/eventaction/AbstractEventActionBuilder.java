package org.eclipse.stardust.model.xpdl.builder.eventaction;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction;
import org.eclipse.stardust.model.xpdl.carnot.BindActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.UnbindActionType;



public abstract class AbstractEventActionBuilder<T extends AbstractEventAction, B extends AbstractEventActionBuilder<T, B>>
      extends AbstractModelElementBuilder<T, B>
{
   protected final EventHandlerType handler;

   public AbstractEventActionBuilder(EventHandlerType handler, T action)
   {
      super(action);

      forModel(XpdlModelUtils.findContainingModel(handler));

      this.handler = handler;
   }

   @Override
   protected T finalizeElement()
   {
      if (element instanceof EventActionType)
      {
         handler.getEventAction().add((EventActionType) element);
      }
      else if (element instanceof BindActionType)
      {
         handler.getBindAction().add((BindActionType) element);
      }
      else if (element instanceof UnbindActionType)
      {
         handler.getUnbindAction().add((UnbindActionType) element);
      }

      return element;
   }

   @Override
   protected String getDefaultElementIdPrefix()
   {
      return "EventAction";
   }

   protected B forActionType(String actionTypeId)
   {
      return forActionType(XpdlModelUtils.findElementById(model.getEventActionType(),
            actionTypeId));
   }

   @SuppressWarnings("unchecked")
   protected B forActionType(EventActionTypeType actionType)
   {
      if (null != actionType)
      {
         element.setType(actionType);
      }
      else
      {
         element.eUnset(PKG_CWM.getAbstractEventAction_Type());
      }

      return (B) this;
   }

}
