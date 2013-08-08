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
package org.eclipse.stardust.modeling.validation.impl.spi.triggerTypes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

public class TimerTriggerValidator implements IModelElementValidator
{
   private static final short ZERO = 0;

   private static final String DEFAULT_PERIOD = new Period(ZERO, ZERO, ZERO, ZERO, ZERO,
         ZERO).toString();

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element,
            PredefinedConstants.TIMER_TRIGGER_START_TIMESTAMP_ATT)))
      {
         result.add(Issue.error(element,
               Validation_Messages.MSG_TIMERTRIGGER_UnspecifiedStart));
      }

      String periodicityAtt = AttributeUtil.getAttributeValue(
            (IExtensibleElement) element,
            PredefinedConstants.TIMER_TRIGGER_PERIODICITY_ATT);
      if ( !StringUtils.isEmpty(periodicityAtt))
      {
         if (DEFAULT_PERIOD.equals(periodicityAtt))
         {
            result.add(Issue.warning(element,
                  Validation_Messages.TimerTrigger_InvalidPeriodicity));
         }
      }

      // todo: (fh) access points

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}
