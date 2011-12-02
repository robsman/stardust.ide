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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.tools.PanningSelectionTool;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractSwimlaneFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;


public class CarnotSelectionTool extends PanningSelectionTool
{
   protected EditPartViewer.Conditional getTargetingConditional()
   {
      return new EditPartViewer.Conditional()
      {
         public boolean evaluate(EditPart editPart)
         {
            if (editPart instanceof AbstractSwimlaneEditPart)
            {
               AbstractSwimlaneFigure figure = ((AbstractSwimlaneEditPart) editPart).getSwimlaneFigure();
               Rectangle clientArea = new Rectangle();
               clientArea.setBounds(figure.getBounds());
               clientArea.crop(figure.getInsets());
               while (figure.getParent() instanceof AbstractSwimlaneFigure)
               {
                  figure.getParent().translateToParent(clientArea);
                  figure = (AbstractSwimlaneFigure) figure.getParent();
               }
               ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) editPart.getRoot();
               Point viewLocation = root.getZoomManager().getViewport().getViewLocation();
               clientArea.scale(root.getZoomManager().getZoom());
               clientArea.translate(-viewLocation.x, -viewLocation.y);
               Point location = getLocation();
               if (clientArea.contains(location))
               {
                  return false;
               }
            }
            return editPart.isSelectable();
         }
      };
   }
}
