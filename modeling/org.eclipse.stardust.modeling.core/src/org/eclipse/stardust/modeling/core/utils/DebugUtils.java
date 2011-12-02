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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DebugUtils
{
   private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.GERMANY); //$NON-NLS-1$
   
   public static void showTime(String hint)
   {
      Date date = new Date();
      System.err.println(hint + " / " + dateFormat.format(date) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
   }
}