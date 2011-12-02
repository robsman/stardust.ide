/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.validation.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ActionTypeUtil;
import org.eclipse.stardust.modeling.validation.*;


public class ActionValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      if (element instanceof AbstractEventAction)
      {
         AbstractEventAction action = (AbstractEventAction) element;
         if (findDuplicateId(action))
         {
            result.add(Issue.error(action,
                  Validation_Messages.ERR_ACTIONTYPE_DuplicateId,
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }
         if (null == ActionTypeUtil.getActionType(action))
         {
            result.add(Issue.error(action, Validation_Messages.ERR_ActionHasNoType,
                  ValidationService.PKG_CWM.getIIdentifiableElement_Name()));
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean findDuplicateId(AbstractEventAction eventAction)
   {
      EventHandlerType eventHandler = ((EventHandlerType) eventAction.eContainer());

      List abstractEventActions = eventAction instanceof EventActionType ? eventHandler
            .getEventAction() : eventAction instanceof BindActionType ? eventHandler
            .getBindAction() : eventHandler.getUnbindAction();

      return findDuplicateId(abstractEventActions, eventAction);
   }

   private boolean findDuplicateId(List abstractEventActions,
         AbstractEventAction eventAction)
   {
      for (Iterator iter = abstractEventActions.iterator(); iter.hasNext();)
      {
         AbstractEventAction nextEventAction = (AbstractEventAction) iter.next();
         if ((nextEventAction.getId().equals(eventAction.getId()))
               && (!eventAction.equals(nextEventAction)))
         {
            return true;
         }
      }
      return false;
   }
}
