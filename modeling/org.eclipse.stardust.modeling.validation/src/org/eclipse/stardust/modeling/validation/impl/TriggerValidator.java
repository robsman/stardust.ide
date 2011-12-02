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

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.*;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

public class TriggerValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      TriggerType trigger = (TriggerType) element;

      List<Issue> result = CollectionUtils.newList();

      if (findDuplicateId(trigger))
      {
         result.add(Issue.error(trigger, Validation_Messages.MSG_TRIGGER_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }

      // TODO check type, attributes
      if (trigger.getType() == null)
      {
         result.add(Issue.error(trigger, Validation_Messages.MSG_TRIGGER_NoTypeSet,
               ValidationService.PKG_CWM.getIIdentifiableElement_Name()));
      }

      for (ParameterMappingType parameterMapping : trigger.getParameterMapping())
      {
         if (parameterMapping.getData() == null)
         {
            result.add(Issue.warning(trigger,
                  Validation_Messages.MSG_NoDataSpecifiedByParameterMapping, ValidationService.PKG_CWM
                        .getParameterMappingType_Data()));
         }
         else
         {
            String parameterId = parameterMapping.getParameter();
            if (StringUtils.isEmpty(parameterId))
            {
               result.add(Issue.warning(trigger,
                     Validation_Messages.MSG_NoParameterSpecifiedByParameterMapping,
                     ValidationService.PKG_CWM.getParameterMappingType_Parameter()));
            }
            else
            {
               AccessPointType accessPoint = findAccessPoint(trigger, parameterId);
               if (accessPoint == null)
               {
                  result.add(Issue.warning(trigger,
                        Validation_Messages.MSG_NoParameterSpecifiedByParameterMapping,
                        ValidationService.PKG_CWM.getParameterMappingType_Parameter()));
               }
               else
               {
                  if (!ModelUtils.isValidId(accessPoint.getId()))
                  {
                     result.add(Issue.warning(accessPoint,
                           MessageFormat.format(Validation_Messages.ERR_ELEMENT_InvalidId,
                                 new Object[] {accessPoint.getId()}),      
                                 ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
                  }
                  
                  try
                  {
                     BridgeObject.checkMapping(parameterMapping.getData(), parameterMapping.getDataPath(),
                        accessPoint, parameterMapping.getParameterPath());
                  }
                  catch (ValidationException e)
                  {
                     result.add(Issue.warning(trigger,
                        Validation_Messages.MSG_ParameterMappingInvalidTypeConversion,
                        ValidationService.PKG_CWM.getParameterMappingType_ParameterPath()));
                  }
               }
            }
         }
      }

      return result.isEmpty() ? Issue.ISSUE_ARRAY : result.toArray(Issue.ISSUE_ARRAY);
   }

   private AccessPointType findAccessPoint(TriggerType trigger, String parameter)
   {
      return ModelUtils.findElementById(trigger.getAccessPoint(), parameter);
   }

   private boolean findDuplicateId(TriggerType trigger)
   {
      ProcessDefinitionType process = (ProcessDefinitionType) trigger.eContainer();
      TriggerType other = ModelUtils.findElementById(process.getTrigger(), trigger.getId());
      return other != null && other != trigger;
   }
}