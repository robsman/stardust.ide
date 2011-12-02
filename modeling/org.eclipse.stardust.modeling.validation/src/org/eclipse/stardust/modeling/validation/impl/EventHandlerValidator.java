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

import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.validation.*;


public class EventHandlerValidator implements IModelElementValidator
{
   private static final String DEFAULT_PERIOD = "000000:000000:000000:000000:000000:000000"; //$NON-NLS-1$

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      EventHandlerType eventHandler = (EventHandlerType) element;

      if (findDuplicateId(eventHandler))
      {
         result.add(Issue.error(eventHandler,
               Validation_Messages.ERR_EVENTHANDLER_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      if (null == eventHandler.getType())
      {
         result.add(Issue.error(eventHandler,
               Validation_Messages.ERR_EVENTHANDLER_NoConditionType,
               ValidationService.PKG_CWM.getIIdentifiableElement_Name()));
      }
      else
      {
         String timerAtt = AttributeUtil.getAttributeValue(eventHandler,
               CarnotConstants.TIMER_PERIOD_ATT);
         if (timerAtt != null)
         {
            if (timerAtt.equals("")) //$NON-NLS-1$
            {
               result.add(Issue.warning(eventHandler, Validation_Messages.EventHandlerValidator_MSG_NO_PERIOD_VALUE));
            }
            else if (timerAtt.equals(DEFAULT_PERIOD))
            {
               result
                     .add(Issue.warning(eventHandler, Validation_Messages.EventHandlerValidator_MSG_DEFAULT_VALUE_PERIOD));
            }
         }
      }

      ValidationService vs = ValidationPlugin.getDefault().getValidationService();
      result.addAll(Arrays.asList(vs.validateModelElements(eventHandler.getBindAction())));
      result.addAll(Arrays.asList(vs.validateModelElements(eventHandler.getUnbindAction())));
      result.addAll(Arrays.asList(vs.validateModelElements(eventHandler.getEventAction())));

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean findDuplicateId(EventHandlerType eventHandler)
   {
      for (Iterator iter = ((List) eventHandler.eContainer().eGet(
            CarnotWorkflowModelPackage.eINSTANCE.getEventHandlerType()
                  .eContainingFeature())).iterator(); iter.hasNext();)
      {
         EventHandlerType otherEventHandler = (EventHandlerType) iter.next();
         if ((otherEventHandler.getId().equals(eventHandler.getId()))
               && (!eventHandler.equals(otherEventHandler)))
         {
            return true;
         }
      }
      return false;
   }
}
