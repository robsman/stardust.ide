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

import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;


public class PoolFigure extends AbstractSwimlaneFigure
{
   // A4 dimension for default Pool
   private static final int width = 210 * 3;
   private static final int height = 297 * 3;
   
   public PoolFigure(PoolEditPart part)
   {
      super(part);

      setBorder(new CompoundBorder(new SwimlaneBorder(getPoolEditPart().getPoolModel()),
            new MarginBorder(0, 3, 0, 3)));
   }
   
   public static Dimension getDimension(boolean isVertical)
   {
      if(isVertical)
      {
         return new Dimension(width, height);
      }
      else
      {
         return new Dimension(height, width);
      }
   }   
   
   // default size for default pool      
   public Dimension getMinimumSize(int wHint, int hHint)
   {
      if(DiagramUtil.isDefaultPool(((PoolEditPart) getEditPart()).getPoolModel()))
      {
         Dimension size = isVerticalModelling() ? getDimension(true) : getDimension(false);
         return SnapGridUtils.getSnapDimension(size, getPoolEditPart(), 1, false);
      }
      return isVerticalModelling() ? new Dimension(250, 500) : new Dimension(500, 250);
   }
   
   public PoolEditPart getPoolEditPart()
   {
      return (PoolEditPart) getEditPart();
   }
}