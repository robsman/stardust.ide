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
package org.eclipse.stardust.modeling.core.editors.tools;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;

public class ViewportScrollHelper
{

   private GraphicalEditPart part;

   private ScrollingGraphicalViewer viewer;

   public ViewportScrollHelper(GraphicalEditPart part, ScrollingGraphicalViewer viewer)
   {
      this.part = part;
      this.viewer = viewer;
   }

   public void updateViewport(Point location)
   {
      Viewport viewport = findViewport();
      Point viewPortLoc = viewport.getLocation();

      Rectangle rect = viewport.getClientArea();
      Rectangle smallRect = new Rectangle(viewPortLoc.x + 30, viewPortLoc.y + 30,
            rect.width - 60, rect.height - 60);
 
      viewPortLoc.x = rect.x;
      viewPortLoc.y = rect.y;

      if (!smallRect.contains(location))
      {
         int scrollX = viewPortLoc.x;
         int scrollY = viewPortLoc.y;
         int region = smallRect.getPosition(location);
         int scrollOffset = 10;
         if (region == PositionConstants.NORTH)
         {
            scrollY = viewPortLoc.y - scrollOffset;
         }
         else if (region == PositionConstants.SOUTH)
         {
            scrollY = viewPortLoc.y + scrollOffset;
         }
         if (region == PositionConstants.WEST)
         {
            scrollX = viewPortLoc.x - scrollOffset;
         }
         else if (region == PositionConstants.EAST)
         {
            scrollX = viewPortLoc.x + scrollOffset;
         }
         ((FigureCanvas) viewer.getControl()).scrollSmoothTo(scrollX, scrollY);
      }
   }

   protected Viewport findViewport()
   {
      IFigure figure = null;
      Viewport port = null;
      do
      {
         if (figure == null)
            figure = part.getContentPane();
         else
            figure = figure.getParent();
         if (figure instanceof Viewport)
         {
            port = (Viewport) figure;
            break;
         }
      }
      while (figure != part.getFigure() && figure != null);
      return port;
   }
}
