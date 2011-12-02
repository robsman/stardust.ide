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

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class DataSymbolFigure extends AbstractLabeledFigure implements IIconFigure
{
   public DataSymbolFigure(String icon)
   {
      super(new DataPictogram(icon));

      setOutline(false);
   }

   public void setIconPath(String iconPath)
   {
      ((IIconFigure) getShape()).setIconPath(iconPath);
   }

   private static class DataPictogram extends Figure implements IIconFigure
   {
      private Dimension cornerDimension = new Dimension(8, 8);

      private IconFigure icon;

      public DataPictogram(String iconPath)
      {
         this.icon = new IconFigure(iconPath);

         setLayoutManager(new DataPictogramLayout());

         add(icon);
      }

      public void setIconPath(String iconPath)
      {
         icon.setIconPath(iconPath);
      }

      public Dimension getMinimumSize(int wHint, int hHint)
      {
         Dimension iconSize = icon.getMinimumSize(wHint, hHint);
         Dimension szPictogram = iconSize.getExpanded(0, iconSize.height);
         szPictogram.expand(10, 10);
         return szPictogram;
      }

      protected void paintFigure(Graphics graphics)
      {
         Rectangle bounds = getBounds();
         
         int lineWidth = 1;

         graphics.pushState();
         try
         {
            int[] p = new int[10];
            p[0] = bounds.x + bounds.width - cornerDimension.width;
            p[1] = bounds.y;

            p[2] = bounds.x;
            p[3] = bounds.y;

            p[4] = bounds.x;
            p[5] = bounds.y + bounds.height - lineWidth;

            p[6] = bounds.x + bounds.width - lineWidth;
            p[7] = bounds.y + bounds.height - lineWidth;

            p[8] = bounds.x + bounds.width - lineWidth;
            p[9] = bounds.y + cornerDimension.height;

            graphics.setBackgroundColor(ColorConstants.white);
            graphics.fillPolygon(p);

            p = new int[16];
            p[0] = bounds.x + bounds.width - lineWidth - cornerDimension.width;
            p[1] = bounds.y;

            p[2] = bounds.x;
            p[3] = bounds.y;

            p[4] = bounds.x;
            p[5] = bounds.y + bounds.height - lineWidth;

            p[6] = bounds.x + bounds.width - lineWidth;
            p[7] = bounds.y + bounds.height - lineWidth;

            p[8] = bounds.x + bounds.width - lineWidth;
            p[9] = bounds.y + cornerDimension.height;

            p[10] = bounds.x + bounds.width - lineWidth - cornerDimension.width;
            p[11] = bounds.y + cornerDimension.height;

            p[12] = bounds.x + bounds.width - lineWidth - cornerDimension.width;
            p[13] = bounds.y;

            p[14] = bounds.x + bounds.width - lineWidth;
            p[15] = bounds.y + cornerDimension.height;

            graphics.setForegroundColor(ColorConstants.lightGray);
            graphics.drawPolyline(p);
         }
         finally
         {
            graphics.popState();
         }
      }

      private class DataPictogramLayout extends AbstractLayout
      {
         protected Dimension calculatePreferredSize(IFigure container, int wHint,
               int hHint)
         {
            // TODO Auto-generated method stub
            if (container instanceof DataPictogram)
            {
               DataPictogram dp = (DataPictogram) container;
               
               Dimension iconSize = dp.icon.getMinimumSize(wHint, hHint);
               Dimension szPictogram = iconSize.getExpanded(0, iconSize.height);
               szPictogram.expand(10, 10);
               return szPictogram;
            }
            return null;
         }

         public void layout(IFigure container)
         {
            if (container instanceof DataPictogram)
            {
               DataPictogram dp = (DataPictogram) container;

               Rectangle rectIcon = dp.getBounds().getCopy();
               rectIcon.y += dp.cornerDimension.height;
               rectIcon.shrink(2, 2);

               Dimension szIcon = dp.icon.getPreferredSize();
               rectIcon.shrink((rectIcon.width - szIcon.width) / 2,
                     (rectIcon.height - szIcon.height) / 2);
               dp.icon.setBounds(rectIcon);
            }
         }
      }
   }
}
