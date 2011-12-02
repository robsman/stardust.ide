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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.ViewportAutoexposeHelper;

/**
 * Extends {@link org.eclipse.gef.editparts.ViewportAutoexposeHelper} that performs
 * autoscrolling of a <code>Viewport</code> figure. This helper extends the area which
 * activates scrolling. It stops scrolling if the symbol is dragged in the opposite
 * direction and reactivate scrolling if the symbol is still located in the autoscroll
 * area and is dragged in the scrolling direction again.
 * 
 * @author fuhrmann
 * @version $Revision$
 */
public class DiagramViewportAutoexposeHelper extends ViewportAutoexposeHelper
{
   /** defines the range where autoscroll is active inside a viewer */
   private static final Insets DEFAULT_EXPOSE_THRESHOLD = new Insets(100, 100, 100, 100);

   private Insets threshold;

   /** the last time an auto expose was performed */
   private long lastStepTime = 0;

   private Point lastWhere;

   private boolean isStopped = false;

   public DiagramViewportAutoexposeHelper(GraphicalEditPart owner)
   {
      super(owner, DEFAULT_EXPOSE_THRESHOLD);
      this.threshold = DEFAULT_EXPOSE_THRESHOLD;
   }

   /**
    * @see org.eclipse.gef.editparts.ViewportAutoexposeHelper#detect(org.eclipse.draw2d.geometry.Point)
    */
   public boolean detect(Point where)
   {
      lastStepTime = 0;
      return super.detect(where);
   }

   /**
    * Scrolls the viewport if a symbol is dragged into the area which activates
    * autoscrolling. Scrolling stops if the symbol is moved in the opposite direction and
    * reactivate scrolling if the symbol is still located in the autoscroll area and is
    * dragged in the scrolling direction again.
    * 
    * @see org.eclipse.gef.editparts.ViewportAutoexposeHelper#step(org.eclipse.draw2d.geometry.Point)
    */
   public boolean step(Point where)
   {
      lastWhere = lastWhere == null ? where : lastWhere;
      Viewport port = findViewport(owner);
      Rectangle rect = Rectangle.SINGLETON;

      port.getClientArea(rect);
      port.translateToParent(rect);
      port.translateToAbsolute(rect);

      if (rect.contains(where) && rect.crop(threshold).contains(where))
         return false;

      // set scroll offset (speed factor)
      int scrollOffset = 0;

      // calculate time based scroll offset
      if (lastStepTime == 0)
         lastStepTime = System.currentTimeMillis();

      long difference = System.currentTimeMillis() - lastStepTime;

      if (difference > 0)
      {
         scrollOffset = ((int) difference / 11);
         lastStepTime = System.currentTimeMillis();
      }

      if (scrollOffset == 0)
         return true;

      rect.crop(threshold);

      int region = rect.getPosition(where);
      Point loc = port.getViewLocation();

      // checks if scrolling should continue or stop
      if ((region & PositionConstants.SOUTH) != 0)
      {
         if (lastWhere.y > where.y)
         {
            isStopped = true;
            return true;
         }
         if (!isStopped || lastWhere.y < where.y)
         {
            loc.y += scrollOffset;
            isStopped = false;
         }
      }
      else if ((region & PositionConstants.NORTH) != 0)
      {
         if (lastWhere.y < where.y)
         {
            isStopped = true;
            return true;
         }
         if (!isStopped || lastWhere.y > where.y)
         {
            loc.y -= scrollOffset;
            isStopped = false;
         }
      }

      if ((region & PositionConstants.EAST) != 0)
      {
         if (lastWhere.x > where.x)
         {
            isStopped = true;
            return true;
         }
         if (!isStopped || lastWhere.x < where.x)
         {
            loc.x += scrollOffset;
            isStopped = false;
         }
      }
      else if ((region & PositionConstants.WEST) != 0)
      {
         if (lastWhere.x < where.x)
         {
            isStopped = true;
            return true;
         }
         if (!isStopped || lastWhere.x > where.x)
         {
            loc.x -= scrollOffset;
            isStopped = false;
         }
      }

      port.setViewLocation(loc);
      lastWhere = where;

      return true;
   }
}
