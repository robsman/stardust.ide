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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;


public class MoveSymbolCommandUtils
{
   private static final Comparator X_AXIS_RECT_COMPARATOR = new XAxisRectangleComparator();

   public static boolean isSymbolCollision(List changedParts, EditPart host,
         EditPart target, Object constraint)
   {
      boolean noCollision = true;
      Set changedFigures = new HashSet(changedParts.size());
      for (Iterator i = changedParts.iterator(); i.hasNext();)
      {
         Object part = i.next();
         if (part instanceof GraphicalEditPart)
         {
            changedFigures.add(((GraphicalEditPart) part).getFigure());
         }
      }

      if (target instanceof AbstractNodeSymbolEditPart)
      {
         if (host instanceof AbstractGraphicalEditPart)
         {
            // find areas occupied by sibling figures
            IFigure hostFigure = ((AbstractGraphicalEditPart) host).getFigure();

            List xDropAreas = new ArrayList(hostFigure.getChildren().size());
            for (Iterator i = hostFigure.getChildren().iterator(); i.hasNext();)
            {
               IFigure childFigure = (IFigure) i.next();
               if (!changedFigures.contains(childFigure))
               {
                  xDropAreas.add(childFigure.getBounds());
               }
            }

            Collections.sort(xDropAreas, X_AXIS_RECT_COMPARATOR);

            IFigure targetFigure = ((GraphicalEditPart) target).getFigure();
            Rectangle bounds = ((Rectangle) constraint).getCopy();
            org.eclipse.draw2d.geometry.Dimension min = targetFigure.getMinimumSize();
            org.eclipse.draw2d.geometry.Dimension max = targetFigure.getMaximumSize();
            if (-1 == bounds.width)
            {
               bounds.width = Math.min(max.width, Math.max(
                     targetFigure.getBounds().width, min.width));
            }
            if (-1 == bounds.height)
            {
               bounds.height = Math.min(max.height, Math.max(
                     targetFigure.getBounds().height, min.height));
            }
            // todo: keep center!

            /*
            if (hostFigure instanceof AbstractSwimlaneFigure)
            {
               Rectangle hostArea = hostFigure.getBounds().getCopy();
               hostArea.crop(hostFigure.getBorder().getInsets(hostFigure));
               hostFigure.translateFromParent(hostArea);
               if (target instanceof AbstractSwimlaneEditPart)
               {
                  OrientationType orientation = ((AbstractSwimlaneEditPart) target)
                        .getSwimlaneModel().getOrientation();
                  if (OrientationType.HORIZONTAL_LITERAL.equals(orientation))
                  {
                     noCollision &= (hostArea.y <= bounds.y)
                           && (hostArea.bottom() >= bounds.bottom());
                  }
                  else
                  {
                     noCollision &= (hostArea.x <= bounds.x)
                           && (hostArea.right() >= bounds.right());
                  }
               }
               else
               {
                  noCollision &= hostArea.contains(bounds);
               }
            }
            */

            for (Iterator i = xDropAreas.iterator(); i.hasNext();)
            {
               Rectangle area = (Rectangle) i.next();
               if (area.right() < bounds.x)
               {
                  continue;
               }
               else if (bounds.right() < area.x)
               {
                  break;
               }
               noCollision &= !bounds.intersects(area);
            }

            // original code, in case herbert changes his mind
            /*
             * if (noCollision) { MoveNodeSymbolCommand cmd = new MoveNodeSymbolCommand();
             * cmd.setPart((INodeSymbol) target.getModel()); if
             * (REQ_MOVE_CHILDREN.equals(type) || REQ_MOVE.equals(type)) {
             * cmd.setLocation(bounds.getLocation()); } else { cmd.setBounds((bounds)); }
             * result = cmd; } else { result = UnexecutableCommand.INSTANCE; }
             */

         }
      }
      return !noCollision;
   }

   private static class XAxisRectangleComparator implements Comparator
   {
      public int compare(Object o1, Object o2)
      {
         if (!(o1 instanceof Rectangle) || !(o2 instanceof Rectangle))
         {
            throw new ClassCastException(
                  Diagram_Messages.EX_CLASSCAST_ExpectionCompareRectangles);
         }
         Rectangle lhs = (Rectangle) o1;
         Rectangle rhs = (Rectangle) o2;
         if (lhs.x != rhs.x)
         {
            return lhs.x < rhs.x ? -1 : 1;
         }
         else if (lhs.width != rhs.width)
         {
            return lhs.width < rhs.width ? -1 : 1;
         }
         else
         {
            return 0;
         }
      }
   }
}
