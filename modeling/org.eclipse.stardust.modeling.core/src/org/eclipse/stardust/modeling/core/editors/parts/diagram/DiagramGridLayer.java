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
package org.eclipse.stardust.modeling.core.editors.parts.diagram;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.PlatformUI;


/**
 * @author rsauer
 * @version $Revision$
 */
public class DiagramGridLayer extends GridLayer implements IPropertyChangeListener
{
   static Color gridColor  = new Color(null, 220, 220, 220);
   int gridFactor;
   int spaceValue;
   
   public DiagramGridLayer()
   {
      this.spaceValue = SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
      this.gridX = SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
      this.gridY = SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
      this.gridFactor = SnapCenterToGrid.CARNOT_VISIBLE_GRID_FACTOR;
      
      // register to preference store
      IPreferenceStore store = PlatformUI.getPreferenceStore();
      store.addPropertyChangeListener(this);      
      
      setForegroundColor(gridColor);
   }

   public void setSpacing(Dimension spacing)
   {
      if (null == spacing)
      {
         spacing = new Dimension(spaceValue * gridFactor, spaceValue * gridFactor);
      }
      super.setSpacing(spacing);
   }

   protected void paintGrid(Graphics g)
   {
      Rectangle clip = g.getClip(Rectangle.SINGLETON);

      Point upperLeft = new Point();
      Point lowerRight = new Point();

      if (gridX > 0)
      {
         if (origin.x >= clip.x)
         {
            while (origin.x - gridX >= clip.x)
            {
               origin.x -= gridX;
            }
         }
         else
         {
            while (origin.x < clip.x)
            {
               origin.x += gridX;
            }
         }

         upperLeft.x = origin.x;
         lowerRight.x = origin.x;
         for (int i = origin.x; i < clip.x + clip.width; i += gridX)
         {
            lowerRight.x = i;
         }
      }

      if (gridY > 0)
      {
         if (origin.y >= clip.y)
         {
            while (origin.y - gridY >= clip.y)
            {
               origin.y -= gridY;
            }
         }
         else
         {
            while (origin.y < clip.y)
            {
               origin.y += gridY;
            }
         }
         upperLeft.y = origin.y;
         lowerRight.y = origin.y;
         for (int i = origin.y; i < clip.y + clip.height; i += gridY)
         {
            lowerRight.y = i;
         }
      }

      for (int x = upperLeft.x; x <= lowerRight.x; x += gridX)
      {
         for (int y = upperLeft.y; y <= lowerRight.y; y += gridY)
         {
            g.drawLine(x - 2, y, x + 2, y);
            g.drawLine(x, y - 2, x, y + 2);
         }
      }
   }

   public void propertyChange(PropertyChangeEvent event)
   {
      boolean update = false;
      if(event.getProperty().equals(BpmProjectNature.PREFERENCE_SNAP_GRID_PIXEL))
      {
         this.gridX = Integer.parseInt(((String) event.getNewValue()));
         this.gridY = Integer.parseInt(((String) event.getNewValue()));         
         this.spaceValue = Integer.parseInt(((String) event.getNewValue()));         
         update = true;
      }
      if(event.getProperty().equals(BpmProjectNature.PREFERENCE_VISIBLE_GRID_FACTOR))
      {
         this.gridFactor = Integer.parseInt(((String) event.getNewValue()));
         update = true;
      }
      if(update)
      {
         setSpacing(null);            
         repaint();
      }
  }
}