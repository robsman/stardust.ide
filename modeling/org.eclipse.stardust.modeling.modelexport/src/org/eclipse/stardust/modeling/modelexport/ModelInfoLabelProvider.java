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
package org.eclipse.stardust.modeling.modelexport;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * @author fherinean
 * @version $Revision$
 */
public class ModelInfoLabelProvider extends LabelProvider
   implements ITableLabelProvider, IColorProvider
{
   public Image getColumnImage(Object element, int columnIndex)
   {
      return columnIndex == 0 ? getImage(element) : null;
   }

   public String getColumnText(Object element, int columnIndex)
   {
      if (element instanceof ModelInfo)
      {
         ModelInfo info = (ModelInfo) element;
         switch (columnIndex)
         {
            case 0:
               return info.getName();
            case 1:
               return info.isActive() ? Long.toString(info.getOID()) + " <active>" //$NON-NLS-1$
                  : Long.toString(info.getOID());
            case 2:
               return info.getValidFromString();
            case 3:
               return info.getValidToString();
         }
      }
      return columnIndex == 0 ? getText(element) : ""; //$NON-NLS-1$
   }

   public Color getForeground(Object element)
   {
      if (element instanceof ModelInfo)
      {
         ModelInfo info = (ModelInfo) element;
         if (info.isActive())
         {
            return ColorConstants.darkGreen;
         }
      }
      return null;
   }

   public Color getBackground(Object element)
   {
      return null;
   }
}
