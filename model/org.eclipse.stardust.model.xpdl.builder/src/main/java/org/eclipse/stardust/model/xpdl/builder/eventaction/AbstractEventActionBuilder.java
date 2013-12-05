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
package org.eclipse.stardust.model.xpdl.builder.eventaction;

import org.eclipse.stardust.model.xpdl.builder.common.AbstractModelElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;



public abstract class AbstractEventActionBuilder<T extends AbstractEventAction, B extends AbstractEventActionBuilder<T, B>>
      extends AbstractModelElementBuilder<T, B>
{
   protected final EventHandlerType handler;

   public AbstractEventActionBuilder(EventHandlerType handler, T action)
   {
      super(action);

      forModel(ModelUtils.findContainingModel(handler));

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
      return forActionType(ModelUtils.findElementById(model.getEventActionType(), actionTypeId));
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
