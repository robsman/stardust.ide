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
package org.eclipse.stardust.modeling.core.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * @author rsauer
 * @version $Revision$
 */
public class StringListCellEditor extends TextCellEditor
{

   public StringListCellEditor()
   {
   }

   public StringListCellEditor(Composite parent)
   {
      super(parent);
   }

   public StringListCellEditor(Composite parent, int style)
   {
      super(parent, style);
   }

   protected Object doGetValue()
   {
      String stringList = (String) super.doGetValue();

      List strings = new ArrayList();

      // TODO support quoting

      StringTokenizer parser = new StringTokenizer(stringList, ","); //$NON-NLS-1$
      while (parser.hasMoreTokens())
      {
         strings.add(parser.nextToken().trim());
      }

      return strings;
   }

   protected void doSetValue(Object value)
   {
      if (value instanceof List)
      {
         StringBuffer buffer = new StringBuffer();

         String joinToken = ""; //$NON-NLS-1$
         for (Iterator i = ((List) value).iterator(); i.hasNext();)
         {
            String element = String.valueOf(i.next());
            if ( -1 != element.indexOf(','))
            {
               buffer.append(joinToken).append(element);
            }
            else
            {
               buffer.append(joinToken).append("\"").append(element).append("\""); //$NON-NLS-1$ //$NON-NLS-2$
            }
            joinToken = ", "; //$NON-NLS-1$
         }

         super.doSetValue(buffer.toString());
      }
      else
      {
         super.setValue(value);
      }
   }
}
