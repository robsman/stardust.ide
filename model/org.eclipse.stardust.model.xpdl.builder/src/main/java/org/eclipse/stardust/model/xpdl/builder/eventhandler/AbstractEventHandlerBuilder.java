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
