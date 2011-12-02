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

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;


public abstract class AbstractSwimlaneFigure extends AbstractCompartmentFigure
      implements IIdentifiableElementFigure
{
   private String name;

   public AbstractSwimlaneFigure(AbstractSwimlaneEditPart part)
   {
      super(part);

      setLayoutManager(new SymbolContainerLayout());

      setOpaque(false);

      setBorder(new SwimlaneBorder(part.getSwimlaneModel()));
   }

   public Insets getAbstractSwimlaneFigureInsets()
   {
      CompoundBorder border = (CompoundBorder) getBorder();
      SwimlaneBorder outerBorder = (SwimlaneBorder) border.getOuterBorder();
      MarginBorder innerBorder = (MarginBorder) border.getInnerBorder();
      Insets insets = outerBorder.getSwimlaneBorderInsets();
      insets.add(innerBorder.getInsets(null));
      
      return insets;
   }
   
   public AbstractSwimlaneEditPart getSwimlaneEditPart()
   {
      return (AbstractSwimlaneEditPart) getEditPart();
   }

   public void setId(String id)
   {
   // TODO Auto-generated method stub
   }

   public void setName(String name)
   {
      this.name = name;
      refresh();
   }

   public void refresh()
   {
      SwimlaneBorder swimlaneBorder = getSwimlaneBorder();
      if (swimlaneBorder != null)
      {
         AbstractSwimlaneEditPart part = (AbstractSwimlaneEditPart) getEditPart();
         IModelParticipant participant = part.getSwimlaneModel().getParticipant();
         swimlaneBorder.setLabel(participant == null ? fixName() : fixName()
               + " (" + //$NON-NLS-1$
               (participant.getName() == null || participant.getName().length() == 0
                     ? participant.getId() == null ? "?" : participant.getId() //$NON-NLS-1$
                     : participant.getName()) + ")"); //$NON-NLS-1$
         if(participant != null)
         {
            swimlaneBorder.setHasParticipant(true);
         }
         else
         {
            swimlaneBorder.setHasParticipant(false);            
         }         
         repaint();
      }
   }

   private SwimlaneBorder getSwimlaneBorder()
   {
      Border border = getBorder();
      if (border instanceof CompoundBorder)
      {
         border = ((CompoundBorder) border).getOuterBorder();
      }
      return border instanceof SwimlaneBorder ? (SwimlaneBorder) border : null;
   }

   private String fixName()
   {
      return name == null ? "" : name; //$NON-NLS-1$
   }

   public void setOrientation(int orientation)
   {
      SwimlaneBorder swimlaneBorder = getSwimlaneBorder();
      if (swimlaneBorder != null)
      {
         swimlaneBorder.setOrientation(orientation);
         // TODO restrict to border area
         repaint();
      }
   }

   public Dimension getPreferredSize(int wHint, int hHint)
   {
      Dimension prefSize = super.getPreferredSize(-1, -1);
      prefSize.union(getMinimumSize());
      IFigure parent = getParent();
      if (parent instanceof DiagramLayer)
      {
         Rectangle bounds = null;
         if (getEditPart() instanceof ScalableRootEditPart)
         {
            ScalableRootEditPart root = (ScalableRootEditPart) getEditPart().getRoot();
            bounds = root.getFigure().getClientArea();
         }
         else if (getEditPart() instanceof ScalableFreeformRootEditPart)
         {
            ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) getEditPart()
                  .getRoot();
            bounds = root.getFigure().getClientArea();
         }
         else
         {
            AbstractGraphicalEditPart root = (AbstractGraphicalEditPart) getEditPart()
                  .getRoot();
            bounds = root.getFigure().getClientArea();
         }

         if (isVerticalModelling())
         {
            prefSize.height = Math.max(prefSize.height, bounds.height);
         }
         else
         {
            prefSize.width = Math.max(prefSize.width, bounds.width);
         }
      }
      return prefSize;
   }

   protected boolean useLocalCoordinates()
   {
      return true;
   }
}