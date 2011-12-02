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

import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Text;

import ag.carnot.base.Period;
import ag.carnot.base.StringUtils;

public class PeriodVerifier implements Verifier, FocusListener
{
   private static final String PATTERN = Diagram_Messages.PeriodVerifier_PATTERN;
   private static final String DEFAULT_TEXT = StringUtils.replace(PATTERN, "#", "0"); //"Y:000 M:00 D:000 h:00 m:00 s:00"; //$NON-NLS-1$ //$NON-NLS-2$
   
   private Text text;
   private boolean ignore;

   public PeriodVerifier(Text text)
   {
      this.text = text;
      ignore = true;
      text.setText(DEFAULT_TEXT);
      ignore = false;
      updateCaretPosition(0, false);
      text.addFocusListener(this);
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
         while (caretPosition > 0 && PATTERN.charAt(caretPosition) != '#' && PATTERN.charAt(caretPosition - 1) != '#')
         {
            caretPosition--;
         }
         if (caretPosition == 0)
         {
            while (caretPosition < PATTERN.length() && PATTERN.charAt(caretPosition) != '#')
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
            beep();
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
               if (!Character.isDigit(c))
               {
                  beep();
                  return;
               }
               sb.setCharAt(pos, c);
            }
         }
         String text = sb.toString();
         Period period = new Period(getInternalValue(text));
         if (period.get(Period.MONTHS) < 12 && period.get(Period.DAYS) < 366 &&
             period.get(Period.HOURS) < 24 && period.get(Period.MINUTES) < 60 &&
             period.get(Period.SECONDS) < 60)
         {
            updateText(text);
            updateCaretPosition(e.start + e.text.length(), false);
         }
         else
         {
            beep();
         }
      }
   }

   protected void beep()
   {
      Toolkit.getDefaultToolkit().beep();
   }
   
   protected void updateText(String text)
   {
      ignore = true;
      this.text.setText(text);
      ignore = false;
   }

   protected void clearText(int start, int end)
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

   public String getExternalValue(String internalValue)
   {
      if (internalValue == null)
      {
         return DEFAULT_TEXT;
      }
      int c = 0;
      StringBuffer externalValue = new StringBuffer();
      int start = PATTERN.indexOf(':') + 1;
      externalValue.append(PATTERN.substring(0, start));
      for (int i = start; i < PATTERN.length(); i++)
      {
         char ch = PATTERN.charAt(i);
         if (c < internalValue.length() && (ch == '#' || ch == ':'))
         {
            externalValue.append(internalValue.charAt(c));
            c++;
         }
         else
         {
            externalValue.append(ch);
         }
      }
      return externalValue.toString();
   }

   public String getInternalValue(String externalValue)
   {
      StringBuffer internalValue = new StringBuffer();
      int start = PATTERN.indexOf(':') + 1;
      for (int i = start; i < PATTERN.length(); i++)
      {
         if (PATTERN.charAt(i) == '#' || PATTERN.charAt(i) == ':')
         {
            internalValue.append(externalValue.charAt(i));
         }
      }
      return internalValue.toString();
   }

   public int getType()
   {
      return Verifier.PERIOD;
   }

   public void focusGained(FocusEvent e)
   {
//      updateCaretPosition(text.getCaretPosition(), false);
   }

   public void focusLost(FocusEvent e)
   {
      // ignore
   }
}
