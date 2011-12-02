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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

public abstract class AbstractIconFigure extends Shape
{
   private Image iconImage;

   protected abstract Image doLoadIcon();

   protected void fillShape(Graphics graphics)
   {
      final Rectangle bounds = getBounds().getCropped(getInsets());

      final Image image = getCachedIcon();
      if (image != null)
      {
         Rectangle imageBounds = new Rectangle(image.getBounds());
         graphics.drawImage(image, imageBounds, bounds);
      }

   }

   protected void outlineShape(Graphics graphics)
   {
   // nothing to do
   }

   public Dimension getPreferredSize(int wHint, int hHint)
   {
      if (getCachedIcon() != null)
      {
         org.eclipse.swt.graphics.Rectangle iconBounds = getCachedIcon().getBounds();
         return new Dimension(iconBounds.width, iconBounds.height);
      }
      return super.getPreferredSize(wHint, hHint);
   }
   
   protected void resetIcon()
   {
      this.iconImage = null;
   }

   private Image getCachedIcon()
   {
      if (null == iconImage)
      {
         iconImage = doLoadIcon();
      }
      return iconImage;
   }
}
