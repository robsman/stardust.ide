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

import java.util.Iterator;

import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.stardust.modeling.core.DiagramPlugin;


/**
 * Basically this class defines a layout strategy on its own and could be implemented as a
 * specialization of AbstractLayout, but deriving from FlowLayout allows for
 * {@link org.eclipse.gef.editpolicies.FlowLayoutEditPolicy}use for the container.
 * 
 * @author rsauer
 * @version $Revision$
 */
public class SymbolContainerLayout extends FreeformLayout
{
   private int compartmentSpacing;

   public SymbolContainerLayout()
   {
      // setHorizontal(true);
      setCompartmentSpacing(3);
   }

   public int getCompartmentSpacing()
   {
      return compartmentSpacing;
   }

   public void setCompartmentSpacing(int compartmentSpacing)
   {
      this.compartmentSpacing = compartmentSpacing;
   }

   public void layout(IFigure parent)
   {
      // apply location constraints
      super.layout(parent);

      // size pools and lanes to maximum extent
      Rectangle drawingArea = null;
      if (parent instanceof AbstractSwimlaneFigure)
      {
         drawingArea = parent.getBounds().getCopy();
         drawingArea.crop(parent.getBorder().getInsets(parent));
         parent.translateFromParent(drawingArea);
      }
      else
      {
         for (Iterator i = parent.getChildren().iterator(); i.hasNext();)
         {
            IFigure child = (IFigure) i.next();
            if (null != drawingArea)
            {
               drawingArea.union(child.getBounds());
            }
            else
            {
               drawingArea = child.getBounds().getCopy();
            }
         }
      }

      for (Iterator i = parent.getChildren().iterator(); i.hasNext();)
      {
         IFigure child = (IFigure) i.next();

         if (child instanceof PoolFigure)
         {
            /*
             * final OrientationType orientation = ((AbstractSwimlaneFigure)
             * child).getSwimlaneEditPart() .getSwimlaneModel() .getOrientation();
             */

            Rectangle bounds = child.getBounds().getCopy();
            // if (OrientationType.HORIZONTAL_LITERAL.equals(orientation))
            if (!DiagramPlugin.isVerticalModelling(((AbstractSwimlaneFigure) child)
                  .getSwimlaneEditPart().getSwimlaneModel()))
            {
               bounds.x = Math.min(0, drawingArea.x);
               bounds.width = Math.max(bounds.width, drawingArea.width);
            }
            else
            {
               bounds.y = Math.min(0, drawingArea.y);
               bounds.height = Math.max(bounds.height, drawingArea.height);
            }
            child.setBounds(bounds);
         }
      }
   }
}