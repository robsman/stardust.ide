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

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.modeling.core.DiagramPlugin;


public abstract class AbstractCompartmentFigure extends Figure
{
   private final GraphicalEditPart part;

   public AbstractCompartmentFigure(GraphicalEditPart part)
   {
      this.part = part;

      setLayoutManager(new CompartmentLayout());
   }

   public GraphicalEditPart getEditPart()
   {
      return part;
   }

   public Dimension getMinimumSize(int wHint, int hHint)
   {
      // TODO make configurable
      return isVerticalModelling() ? new Dimension(250, 500) : new Dimension(500, 250);
   }

   boolean isVerticalModelling()
   {
      return DiagramPlugin.isVerticalModelling((IGraphicalObject) part.getModel());
   }
}