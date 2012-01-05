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
package org.eclipse.stardust.modeling.common.ui.jface.utils;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public class NumericFieldVerifier implements VerifyListener
{
   private final int minValue;
   private final int maxValue;
   
   public NumericFieldVerifier(int minValue, int maxValue)
   {
      this.minValue = minValue;
      this.maxValue = maxValue;
   }
   
   public void verifyText(VerifyEvent e)
   {
      e.doit = false;
      if (Character.isDigit(e.character) && (e.getSource() instanceof Text))
      {
         Text field = (Text) e.getSource();
         
         StringBuffer buffer = new StringBuffer();
         buffer.append(field.getText());
         buffer.replace(e.start, e.end, e.text);
         
         int intValue;
         try
         {
            intValue = !StringUtils.isEmpty(buffer.toString())
                  ? Integer.parseInt(buffer.toString())
                  : 0;
         }
         catch (NumberFormatException ex)
         {
            intValue = 0;
         }

         if (0 < field.getSelectionCount())
         {
            if (field.getSelectionText().length() == field.getText().length())
            {
               e.doit = true;
            }
         }
         if ((minValue <= intValue) && (intValue <= maxValue))
         {
            e.doit = true;
         }
      }
      else if (e.character == '\b')
      {
         e.doit = true;
      }
      else
      {
         try
         {
            int intValue = Integer.parseInt(e.text);
            if ((minValue <= intValue) && (intValue <= maxValue))
            {
               e.doit = true;
            }
         }
         catch (NumberFormatException e1)
         {
            e.doit = false;
         }
      }
   }
}