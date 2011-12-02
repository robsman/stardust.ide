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
package org.eclipse.stardust.modeling.core.editors.figures.anchors;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.stardust.modeling.core.editors.figures.GatewayFigure;


/**
 * @author rsauer
 * @version $Revision$
 */
public class TransitionConnectionAnchor extends ChopboxAnchor
{
   public static final String LEFT = "left"; //$NON-NLS-1$
   public static final String CENTER = "center"; //$NON-NLS-1$
   public static final String RIGHT = "right"; //$NON-NLS-1$
   public static final String TOP = "top"; //$NON-NLS-1$
   public static final String BOTTOM = "bottom"; //$NON-NLS-1$

   private String type;

   public TransitionConnectionAnchor(IFigure owner, Point location)
   {
      this(owner, computeType(owner, location));
   }

   public TransitionConnectionAnchor(IFigure owner, String anchor)
   {
      super(owner);
      type = anchor;
   }

   public Point getLocation(Point reference)
   {
      IFigure owner = getOwner();
      Rectangle bounds = owner.getBounds();
      
      if (owner instanceof GatewayFigure)
      {
         // adjusting to outline being forced to be of uneven width/height to get smooth
         // edges (see {@link GatewayFigure#setBounds(Rectangle)})
         bounds = new Rectangle(bounds);
         bounds.width = 2 * ((bounds.width + 1) / 2) - 1; 
         bounds.height = 2 * ((bounds.height + 1) / 2) - 1; 
      }

      Point center = bounds.getCenter();
      owner.translateToAbsolute(center);
      Point left = bounds.getLeft();
      owner.translateToAbsolute(left);
      Point right = bounds.getRight();
      owner.translateToAbsolute(right);
      Point top = bounds.getTop();
      owner.translateToAbsolute(top);
      Point bottom = bounds.getBottom();
      owner.translateToAbsolute(bottom);

      if (LEFT.equals(getType()))
      {
         return left;
      }
      if (RIGHT.equals(getType()))
      {
         return right;
      }
      if (TOP.equals(getType()))
      {
         return top;
      }
      if (BOTTOM.equals(getType()))
      {
         return bottom;
      }
      boolean bx = reference.x < center.x;
      boolean by = reference.y < center.y;
      int dx = Math.abs(reference.x - center.x);
      int dy = Math.abs(reference.y - center.y);

      if (dy > dx)
      {
         return by ? top : bottom;
      }
      else
      {
         return bx ? left : right;
      }
   }

   public String getType()
   {
      return type;
   }

   private static String computeType(IFigure owner, Point location)
   {
      Rectangle bounds = owner.getBounds();

      Point center = bounds.getCenter();
      owner.translateToAbsolute(center);
      Point left = bounds.getLeft();
      owner.translateToAbsolute(left);
      Point right = bounds.getRight();
      owner.translateToAbsolute(right);
      Point top = bounds.getTop();
      owner.translateToAbsolute(top);
      Point bottom = bounds.getBottom();
      owner.translateToAbsolute(bottom);

      if (location.y >= top.y && location.y <= bottom.y)
      {
         int lx = (center.x + left.x) / 2;
         if (lx - left.x > 20)
         {
            lx = left.x + 20;
         }
         if (location.x < lx)
         {
            return LEFT;
         }

         lx = (center.x + right.x) / 2;
         if (right.x - lx > 20)
         {
            lx = right.x - 20;
         }
         if (location.x > lx)
         {
            return RIGHT;
         }
      }
      if (location.x >= left.x && location.x <= right.x)
      {
         int ly = (center.y + top.y) / 2;
         if (ly - top.y > 20)
         {
            ly = top.y + 20;
         }
         if (location.y < ly)
         {
            return TOP;
         }

         ly = (center.y + bottom.y) / 2;
         if (bottom.y - ly > 20)
         {
            ly = bottom.y - 20;
         }
         if (location.y > ly)
         {
            return BOTTOM;
         }
      }
      return CENTER;
   }
}