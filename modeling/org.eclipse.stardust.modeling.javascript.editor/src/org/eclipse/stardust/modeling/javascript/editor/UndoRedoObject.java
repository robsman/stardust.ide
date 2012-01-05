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
package org.eclipse.stardust.modeling.javascript.editor;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.StyledText;

public class UndoRedoObject
{
   private String text;
   private boolean added;
   private int start;
   private int end;
   
   public UndoRedoObject(ExtendedModifyEvent event)
   {
      super();
      if (event.widget instanceof StyledText)
      {
         StyledText textWidget = (StyledText)event.widget;
         String currentText = textWidget.getText();
         if (!StringUtils.isEmpty(event.replacedText))
         {
            text = event.replacedText;
            start = event.start;
            end = start + text.length();
         } else {
            added = true;
            start = event.start;
            end = start + event.length;
            text = currentText.substring(start, end);
         }
      }

   }
   public String getText()
   {
      return text;
   }
   public boolean isAdded()
   {
      return added;
   }
   public int getStart()
   {
      return start;
   }
   public int getEnd()
   {
      return end;
   }
   public void setAdded(boolean added)
   {
      this.added = added;
   }

}
