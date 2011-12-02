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
package org.eclipse.stardust.modeling.core;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Text;

/**
 * @author fherinean
 * @version $Revision$
 */
public class LongVerifier implements Verifier
{
   private int type;
   private long min;
   private long max;

   public LongVerifier(int format, long minValue, long maxValue)
   {
      type = format;
      min = minValue;
      max = maxValue;
   }

   public void verifyText(VerifyEvent e)
   {
      StringBuffer sb = new StringBuffer(((Text) e.widget).getText());
      if (e.character == 0)
      {
         sb.insert(e.start, e.text);
      }
      else if (!Character.isISOControl(e.character))
      {
         sb.insert(e.start, e.text);
      }

      char last = 0;
      for (int i = 0; i < sb.length(); i++)
      {
         char current = sb.charAt(i);
         if (current == symbols.getGroupingSeparator())
         {
            sb.setCharAt(i, INTERNAL_GROUPING_SEPARATOR);
         }
         else if (current == symbols.getMinusSign())
         {
            sb.setCharAt(i, INTERNAL_MINUS_SIGN);
         }
         last = sb.charAt(i);
      }

      // code complete :p to allow continuation of typing
      if (last == 0 || last == INTERNAL_MINUS_SIGN)
      {
         sb.append(symbols.getZeroDigit());
      }

      long value = 0;
      try
      {
         switch (type)
         {
            case BYTE:
               value = Byte.parseByte(sb.toString());
               break;
            case SHORT:
               value = Short.parseShort(sb.toString());
               break;
            case INT:
               value = Integer.parseInt(sb.toString());
               break;
            case LONG:
               value = Long.parseLong(sb.toString());
               break;
         }
         e.doit = value >= min && value <= max;
      }
      catch (NumberFormatException ex)
      {
         e.doit = false;
      }
   }

   public int getType()
   {
      return type;
   }

   public String getExternalValue(String value)
   {
      return value;
   }

   public String getInternalValue(String value)
   {
      return value;
   }
}
