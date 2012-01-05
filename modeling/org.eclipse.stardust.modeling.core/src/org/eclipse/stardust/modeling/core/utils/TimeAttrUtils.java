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
package org.eclipse.stardust.modeling.core.utils;

import java.text.NumberFormat;
import java.text.ParseException;

import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.swt.widgets.Text;

public class TimeAttrUtils
{
   private static final short ZERO = 00;
   
   private static final short ZERO_YEAR = 000;

   private static final short ZERO_DAY = 000;

   private static final int[] FIELD_KIND = new int[] {
         Period.YEARS, Period.MONTHS, Period.DAYS, Period.HOURS, Period.MINUTES,
         Period.SECONDS};

   public static void initPeriod(Text[] txtPeriodParts, AttributeType attr)
   {
      Period period = (null != attr) //
            ? new Period(attr.getValue())
            : new Period(ZERO, ZERO, ZERO, ZERO, ZERO, ZERO);

      for (int i = 0; i < FIELD_KIND.length; i++ )
      {
         if (i < txtPeriodParts.length)
         {
            txtPeriodParts[i].setText(Short.toString(period.get(FIELD_KIND[i])));
         }
      }
   }
   
   public static String initPeriod(AttributeType attr)
   {
      String periodPart;
      int periodPartSize;
      Period period = (null != attr) ? new Period(attr.getValue()) : new Period(
            ZERO_YEAR, ZERO, ZERO_DAY, ZERO, ZERO, ZERO);
      StringBuffer buffer = new StringBuffer();
      for (int i = 0; i < FIELD_KIND.length; i++ )
      {
         periodPartSize = ((FIELD_KIND[i] == Period.YEARS) || (FIELD_KIND[i] == Period.DAYS))
               ? 3
               : 2;

         String periodField = Short.toString(period.get(FIELD_KIND[i]));
         for (int j = 0; j < (periodPartSize - periodField.length()); j++)
         {
            buffer.append(String.valueOf(0));
         }
         buffer.append(periodField);
         if (i < FIELD_KIND.length - 1)
         {
            buffer.append(": "); //$NON-NLS-1$
      }
   }
      periodPart = buffer.toString();
      return periodPart;
   }
   
   public static void updatePeriodAttr(IExtensibleElement element, String attrName, Text[] txtPeriodParts)
   {
      Period period = null;

      if (null != txtPeriodParts)
      {
         short[] fields = new short[FIELD_KIND.length];
         for (int i = 0; i < fields.length; i++ )
         {
            if (i < txtPeriodParts.length)
            {
               fields[i] = parseShort(txtPeriodParts[i].getText());
            }
         }
         
         period = new Period(fields[0], fields[1], fields[2], fields[3], fields[4],
               fields[5]);
      }
      
      AttributeUtil.setAttribute(element, attrName,
            Reflect.getAbbreviatedName(Period.class), (null != period)
                  ? period.toString()
                  : ""); //$NON-NLS-1$
   }
   
   public static void updatePeriodAttr(IExtensibleElement element, String attrName,
         String[] periodParts)
   {
      Period period = null;

      if (null != periodParts)
      {
         short[] fields = new short[FIELD_KIND.length];
         for (int i = 0; i < fields.length; i++ )
         {
            if (i < periodParts.length)
            {
               fields[i] = parseShort(periodParts[i].trim());
            }
         }
         
         period = new Period(fields[0], fields[1], fields[2], fields[3], fields[4],
               fields[5]);
      }
      AttributeUtil.setAttribute(element, attrName, Reflect
            .getAbbreviatedName(Period.class), (null != period) ? period.toString() : ""); //$NON-NLS-1$
   }
   
   public static short parseShort(String value)
   {
      try
      {
         Number result = NumberFormat.getIntegerInstance().parse(value);
         return (null != result) ? result.shortValue() : 0;
      }
      catch (ParseException e)
      {
         // TODO Auto-generated catch block
         return 0;
      }
   }
}
