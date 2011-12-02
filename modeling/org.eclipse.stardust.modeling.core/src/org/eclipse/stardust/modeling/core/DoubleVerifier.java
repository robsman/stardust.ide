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
public class DoubleVerifier implements Verifier
{
   private int type;
   private double min;
   private double max;

   private static final String DEFAULT_CURRENCY = Diagram_Messages.DEFAULT_CURRENCY;

   public DoubleVerifier(int format, double minValue, double maxValue)
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
         sb.replace(e.start, e.end, e.text);
      }
      else if (!Character.isISOControl(e.character))
      {
         sb.replace(e.start, e.end, e.text);
      }

      char last = 0;
      for (int i = 0; i < sb.length(); i++)
      {
         char current = sb.charAt(i);
         if (current == symbols.getGroupingSeparator())
         {
            sb.setCharAt(i, INTERNAL_GROUPING_SEPARATOR);
         }
         else if (current == symbols.getDecimalSeparator())
         {
            sb.setCharAt(i, INTERNAL_DECIMAL_SEPARATOR);
         }
         else if (current == symbols.getMinusSign())
         {
            sb.setCharAt(i, INTERNAL_MINUS_SIGN);
         }
         else if (current == ALTERNATE_EXPONENTIAL_SIGN)
         {
            sb.setCharAt(i, INTERNAL_EXPONENTIAL_SIGN);
         }
         last = sb.charAt(i);
      }

      // code complete :p to allow continuation of typing
      if (last == 0
              || last == INTERNAL_MINUS_SIGN
              || last == INTERNAL_DECIMAL_SEPARATOR
              || last == INTERNAL_EXPONENTIAL_SIGN)
      {
         sb.append(symbols.getZeroDigit());
      }

      double value = 0;
      try
      {
         switch (type)
         {
            case Verifier.FLOAT:
               value = Float.parseFloat(sb.toString());
               break;
            case Verifier.DOUBLE:
               value = Double.parseDouble(sb.toString());
               break;
         }
         e.doit = value >= min && value <= max;
      }
      catch (NumberFormatException nfe)
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
      if (value == null)
      {
         return value;
      }

      // cut-out currency
      int ix = value.indexOf(' ');
      StringBuffer sb = new StringBuffer(ix < 0 ? value : value.substring(0, ix));

      for (int i = 0; i < sb.length(); i++)
      {
         char current = sb.charAt(i);
         if (current == INTERNAL_GROUPING_SEPARATOR)
         {
            sb.setCharAt(i, symbols.getGroupingSeparator());
         }
         else if (current == INTERNAL_DECIMAL_SEPARATOR)
         {
            sb.setCharAt(i, symbols.getDecimalSeparator());
         }
         else if (current == INTERNAL_MINUS_SIGN)
         {
            sb.setCharAt(i, symbols.getMinusSign());
         }
         else if (current == ALTERNATE_EXPONENTIAL_SIGN)
         {
            sb.setCharAt(i, INTERNAL_EXPONENTIAL_SIGN);
         }
      }

      return sb.toString();
   }

   public String getInternalValue(String value)
   {
      if (value == null)
      {
         return value;
      }

      StringBuffer sb = new StringBuffer(value);

      for (int i = 0; i < sb.length(); i++)
      {
         char current = sb.charAt(i);
         if (current == symbols.getGroupingSeparator())
         {
            sb.setCharAt(i, INTERNAL_GROUPING_SEPARATOR);
         }
         else if (current == symbols.getDecimalSeparator())
         {
            sb.setCharAt(i, INTERNAL_DECIMAL_SEPARATOR);
         }
         else if (current == symbols.getMinusSign())
         {
            sb.setCharAt(i, INTERNAL_MINUS_SIGN);
         }
         else if (current == ALTERNATE_EXPONENTIAL_SIGN)
         {
            sb.setCharAt(i, INTERNAL_EXPONENTIAL_SIGN);
         }
      }

      if (Verifier.MONEY == type)
      {
         sb.append(' ');
         sb.append(DEFAULT_CURRENCY);
      }

      return sb.toString();
   }
}
