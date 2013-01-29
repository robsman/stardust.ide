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
package org.eclipse.stardust.model.xpdl.builder.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ubirkemeyer
 * @version $Revision: 50715 $
 */
public class DateUtils
{
   // @todo (france, ub): unify with NONINTERACTIVE_DATE_FORMAT
   private static final DateFormat dateFormat = DateFormat.getDateInstance();
   private static final DateFormat timeFormat = DateFormat.getTimeInstance();
   static private final String DEFAULT_HOUR_SIGN = " h"; //$NON-NLS-1$
   static private final char LEADING_NULL_SIGN = '0';
   static private final char DEFAULT_DURATION_SEPARATOR = ':';

   public static SimpleDateFormat getNonInteractiveDateFormat()
   {
      return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS"); //$NON-NLS-1$
   }

   public static SimpleDateFormat getInteractiveDateFormat()
   {
      return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //$NON-NLS-1$
   }

   public static long getTimestamp(Date date, long def)
   {
      return date == null ? def : date.getTime();
   }

   public static String formatDateTime(Date date)
   {
      return date == null ? "" : dateFormat.format(date) + " " + timeFormat.format(date); //$NON-NLS-1$ //$NON-NLS-2$
   }

   public static String formatDate(Date date)
   {
      return date == null ? "" : dateFormat.format(date); //$NON-NLS-1$
   }

   public static String formatTime(Date date)
   {
      return date == null ? "" : timeFormat.format(date); //$NON-NLS-1$
   }

   /**
    * Returns the duration as formatted string 
    * Format is: <hours>:<minutes>:<seconds> h
    * Example : 1:05:20 h
    */
   public static String formatDurationAsString(double durationInHours)
   {
      StringBuffer _durationString = new StringBuffer(15);

      Double _durationInSec = new Double(durationInHours * 3600);

      int _rest = _durationInSec.intValue();

      // append hours
      _durationString.append(_rest / 3600);
      _rest = _rest % 3600;

      // append minutes
      _durationString.append(DEFAULT_DURATION_SEPARATOR);
      if ((_rest / 60) < 10)
      {
         _durationString.append(LEADING_NULL_SIGN);
      }
      _durationString.append(_rest / 60);
      _rest = _rest % 60;

      // append seconds
      _durationString.append(DEFAULT_DURATION_SEPARATOR);
      if (_rest < 10)
      {
         _durationString.append(LEADING_NULL_SIGN);
      }
      _durationString.append(_rest);

      // append hour sign
      _durationString.append(DEFAULT_HOUR_SIGN);

      return _durationString.toString();
   }
}
