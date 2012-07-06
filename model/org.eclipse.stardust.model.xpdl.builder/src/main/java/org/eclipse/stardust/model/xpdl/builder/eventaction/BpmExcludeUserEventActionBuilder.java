package org.eclipse.stardust.model.xpdl.builder.eventaction;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.builder.utils.XpdlModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class BpmExcludeUserEventActionBuilder
      extends AbstractEventActionBuilder<EventActionType, BpmExcludeUserEventActionBuilder>
{
   public BpmExcludeUserEventActionBuilder(EventHandlerType handler)
   {
      super(handler, F_CWM.createEventActionType());

      forActionType(PredefinedConstants.EXCLUDE_USER_ACTION);
   }

   @Override
   protected EventActionType finalizeElement()
   {
      return super.finalizeElement();
   }

   public static BpmExcludeUserEventActionBuilder newExcludeUserAction(
         EventHandlerType handler)
   {
      return new BpmExcludeUserEventActionBuilder(handler);
   }

   public BpmExcludeUserEventActionBuilder basedOnVariable(String dataId)
   {
      return basedOnVariable(XpdlModelUtils.findElementById(model.getData(), dataId));
   }

   public BpmExcludeUserEventActionBuilder basedOnVariable(DataType data)
   {
      AttributeUtil.setAttribute(element, PredefinedConstants.EXCLUDED_PERFORMER_DATA,
            data.getId());

      return this;
   }

}
