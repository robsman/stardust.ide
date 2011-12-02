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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Text;

import ag.carnot.base.StringUtils;

public class OverdueThresholdVerifier implements Verifier
{
   private static final String PATTERN = "###%"; //$NON-NLS-1$

   private static final String DEFAULT_TEXT = StringUtils.replace(PATTERN, "#", " "); //$NON-NLS-1$ //$NON-NLS-2$

   private final Text text;

   private boolean ignore;

   public OverdueThresholdVerifier(Text text)
   {
      this.text = text;
      text.setText(DEFAULT_TEXT);
      updateCaretPosition(0, false);
      ignore = true;
      text.setText(DEFAULT_TEXT);
      ignore = false;
   }

   public String getExternalValue(String value)
   {
      if (value == null)
      {
         return "  0%"; //$NON-NLS-1$
      }
      int c = 0;
      StringBuffer externalValue = new StringBuffer();
      for (int i = 0; i < PATTERN.length(); i++)
      {
         char ch = PATTERN.charAt(i);
         if (c < value.length() && (ch == '#') && (c != '%'))
         {
            externalValue.append(value.charAt(c));
            c++;
         }
         else
         {
            if (ch != '#')
            {
               externalValue.append(ch);
            }
         }
      }
      while (externalValue.length() < PATTERN.length())
      {
         externalValue.insert(0, ' ');
      }
      return externalValue.toString();
   }

   public String getInternalValue(String val)
   {
      StringBuffer internalValue = new StringBuffer();
      for (int i = 0; i < PATTERN.length(); i++)
      {
         if (PATTERN.charAt(i) == '#' && val.length() > i && (val.charAt(i) != '%')
               && (!Character.isWhitespace(val.charAt(i))))
         {
            internalValue.append(val.charAt(i));
         }
      }
      String value = internalValue.toString().trim();
      String result = StringUtils.isEmpty(value) ? "0" : value; //$NON-NLS-1$
      return result;
   }

   public int getType()
   {
      return Verifier.OVERDUE_THRESHOLD;
   }

   private void updateCaretPosition(int caretPosition, boolean backwards)
   {
      if (caretPosition < 0)
      {
         caretPosition = 0;
      }
      if (caretPosition > PATTERN.length())
      {
         caretPosition = PATTERN.length();
      }
      if (backwards)
      {
         while (caretPosition > 0 && PATTERN.charAt(caretPosition) != '#'
               && PATTERN.charAt(caretPosition - 1) != '#')
         {
            caretPosition--;
         }
         if (caretPosition == 0)
         {
            while (caretPosition < PATTERN.length()
                  && PATTERN.charAt(caretPosition) != '#')
            {
               caretPosition++;
            }
         }
      }
      else
      {
         while (caretPosition < PATTERN.length() && PATTERN.charAt(caretPosition) != '#')
         {
            caretPosition++;
         }
      }
      this.text.setSelection(caretPosition);
   }

   public void verifyText(VerifyEvent e)
   {
      if (ignore)
      {
         return;
      }
      e.doit = false;
      if (e.keyCode == SWT.BS || e.keyCode == SWT.DEL)
      {
         clearText(e.start, e.end);
         updateCaretPosition(e.start, e.keyCode == SWT.BS);
      }
      else
      {
         if (e.start >= PATTERN.length())
         {
            return;
         }

         String editValue = text.getText();
         StringBuffer sb = new StringBuffer();
         sb.append(editValue);
         for (int i = 0, pos = e.start; i < e.text.length() && pos < PATTERN.length(); i++, pos++)
         {
            char c = e.text.charAt(i);
            if ('#' == PATTERN.charAt(pos))
            {
               if (!Character.isDigit(c) && !Character.isWhitespace(c))
               {
                  return;
               }
               sb.setCharAt(pos, c);
            }
         }
         String value = sb.toString();
         int overdueThreshold = Integer.parseInt(getInternalValue(value));
         if (overdueThreshold < 1000)
         {
            ignore = true;
            this.text.setText(value);
            ignore = false;
            updateCaretPosition(e.start + e.text.length(), false);
         }
      }
   }

   private void clearText(int start, int end)
   {
      ignore = true;
      StringBuffer sb = new StringBuffer();
      sb.append(text.getText());
      for (int pos = start; pos < end && pos < PATTERN.length(); pos++)
      {
         char c = DEFAULT_TEXT.charAt(pos);
         sb.setCharAt(pos, c);
      }
      text.setText(sb.toString());
      ignore = false;
   }

}
