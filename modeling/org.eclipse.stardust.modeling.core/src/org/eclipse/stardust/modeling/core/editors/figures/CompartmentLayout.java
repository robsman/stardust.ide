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

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Basically this class defines a layout strategy on its own and could be implemented as a
 * specialization of AbstractLayout, but deriving from FlowLayout allows for
 * {@link org.eclipse.gef.editpolicies.FlowLayoutEditPolicy}use for the container.
 * 
 * @author rsauer
 * @version $Revision$
 */
public class CompartmentLayout extends FlowLayout
{
   public CompartmentLayout()
   {
      setHorizontal(true);
      setMinorSpacing(3);
   }

   public void layout(IFigure parent)
   {
      Rectangle bounds = parent.getClientArea();

      for (Iterator i = parent.getChildren().iterator(); i.hasNext();)
      {
         IFigure child = (IFigure) i.next();

         Dimension r = null;
         if ( !(child instanceof FreeformFigure))
         {
            r = child.getPreferredSize();

            bounds.width = r.width;

            child.setBounds(bounds);

            bounds.x += r.width + getMinorSpacing();
         }
      }
   }

   protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint)
   {
      Dimension extent = null;

      for (Iterator i = container.getChildren().iterator(); i.hasNext();)
      {
         IFigure child = (IFigure) i.next();

         Dimension childSize = null;
         if ( !(child instanceof FreeformFigure))
         {
            childSize = child.getPreferredSize();
         }
         if (null != childSize)
         {
            if (null == extent)
            {
               extent = childSize.getCopy();
            }
            else
            {
               extent.width += childSize.width + getMinorSpacing();
               extent.height = Math.max(extent.height, childSize.height);
            }
         }
      }

      if (null != extent)
      {
         extent.union(container.getMinimumSize());
      }
      else
      {
         extent = container.getMinimumSize();
      }

      Insets insets = container.getInsets();
      if (null == extent)
      {
         extent = new Dimension(insets.getWidth(), insets.getHeight());
      }
      else
      {
         // compartment.translateToParent(extent);
         extent.expand(insets.getWidth(), insets.getHeight());
      }

      return extent;
   }
}