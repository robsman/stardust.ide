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

/**
 * @author rsauer
 * @version $Revision$
 */
public class TopOrBottomAnchor extends ChopboxAnchor
{
   public TopOrBottomAnchor(IFigure owner)
   {
      super(owner);
   }

   public Point getLocation(Point reference)
   {
      Point p = getOwner().getBounds().getCenter();
      getOwner().translateToAbsolute(p);

      if (reference.y < p.y)
      {
         p = getOwner().getBounds().getTop();
      }
      else
      {
         p = getOwner().getBounds().getBottom();
      }

      getOwner().translateToAbsolute(p);
      return p;
   }
}