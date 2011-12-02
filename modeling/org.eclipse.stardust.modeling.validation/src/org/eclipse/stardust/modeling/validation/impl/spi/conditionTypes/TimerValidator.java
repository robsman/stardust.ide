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
package org.eclipse.stardust.modeling.validation.impl.spi.conditionTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.BridgeObject;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;

import ag.carnot.base.Period;
import ag.carnot.base.StringUtils;
import ag.carnot.workflow.model.PredefinedConstants;

public class TimerValidator implements IModelElementValidator
{
   private static final BridgeObject DATE_TARGET = new BridgeObject(
         JavaDataTypeUtils.getTypeFromCurrentProject(Date.class.getName()),
         DirectionType.IN_LITERAL);

   private static final BridgeObject LONG_TARGET = new BridgeObject(
         JavaDataTypeUtils.getTypeFromCurrentProject(Long.class.getName()),
         DirectionType.IN_LITERAL);

   private static final BridgeObject PERIOD_TARGET = new BridgeObject(
         JavaDataTypeUtils.getTypeFromCurrentProject(Period.class.getName()),
         DirectionType.IN_LITERAL);

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      boolean useData = AttributeUtil.getBooleanValue((IExtensibleElement) element,
            PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT);
      if (useData)
      {
         String dataId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               PredefinedConstants.TIMER_CONDITION_DATA_ATT);
         if (StringUtils.isEmpty(dataId))
         {
            result.add(Issue.warning(element, Validation_Messages.MSG_NoDataSpecified));
         }
         if (ModelUtils.findContainingProcess(element) != null)
         {
            DataType data = findData(element, dataId);
            if (data != null)
            {
               String dataPath = AttributeUtil.getAttributeValue(
                     (IExtensibleElement) element,
                     PredefinedConstants.TIMER_CONDITION_DATA_PATH_ATT);

               BridgeObject bo = null;
               try
               {
                  bo = BridgeObject.getBridge(data, dataPath, DirectionType.OUT_LITERAL);
                  if ( !DATE_TARGET.acceptAssignmentFrom(bo)
                        && !LONG_TARGET.acceptAssignmentFrom(bo)
                        && !PERIOD_TARGET.acceptAssignmentFrom(bo))
                  {
                     result.add(Issue.warning(element,
                           Validation_Messages.MSG_InvalidDataMapping));
                  }
               }
               catch (ValidationException e)
               {
                  result.add(Issue.warning(element, e.getMessage()));
               }
            }
            else
            {
               result.add(Issue.warning(element,
                     Validation_Messages.MSG_InvalidDataSpecified));
            }
         }
         else
         {
            result.add(Issue.warning(element,
                  Validation_Messages.MSG_TimerConditionHasToHave));
         }
      }
      else
      {
         String period = AttributeUtil.getAttributeValue((IExtensibleElement) element,
               PredefinedConstants.TIMER_PERIOD_ATT);
         if (StringUtils.isEmpty(period))
         {
            result.add(Issue.warning(element, Validation_Messages.MSG_NoPeriodSpecified,
                  PredefinedConstants.TIMER_PERIOD_ATT));
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private DataType findData(IModelElement element, String dataId)
   {
      DataType data = null;

      for (Iterator iter = ModelUtils.findContainingModel(element).getData().iterator(); iter.hasNext();)
      {
         DataType dataType = (DataType) iter.next();
         if (dataType.getId().equals(dataId))
         {
            data = dataType;
         }
      }

      return data;
   }

}
