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
package org.eclipse.stardust.modeling.core.editors.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

import ag.carnot.base.CompareHelper;

public abstract class AbstractLabeledIconFigure extends AbstractLabeledFigure
      implements IIconFigure
{
   private String iconPath;
   
   public AbstractLabeledIconFigure(final String iconPath)
   {
      this(iconPath, ColorConstants.darkGray, ColorConstants.white);
   }

   public AbstractLabeledIconFigure(final String iconPath, Color borderColor,
         Color fillColor)
   {
      super(new IconFigure(iconPath), borderColor, fillColor);
      
      this.iconPath = iconPath;
   }

   public void setIconPath(String iconPath)
   {
      if ( !CompareHelper.areEqual(this.iconPath, iconPath))
      {
         super.setShape(new IconFigure(iconPath));
         this.iconPath = iconPath;
      }
   }
}
