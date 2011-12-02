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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveNodeSymbolCommand;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.ui.IWorkbenchPart;


public class SnapToGridAction extends SelectionAction
{

   private CompoundCommand command;

   public SnapToGridAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.SNAP_TO_GRID);
      setText(Diagram_Messages.LB_SnapToGrid);
   }

   protected boolean calculateEnabled()
   {
      return isEditPartSelected() && isSnapToGridEnabled() && canExecute();
   }

   private boolean canExecute()
   {
      boolean isEnabled;
      command = new CompoundCommand();
      createSnapToGridCmd();
      isEnabled = command.canExecute();
      return isEnabled;
   }

   private boolean isEditPartSelected()
   {
      return getSelectedObjects() != null && getSelectedObjects().size() > 0
            && isNodeSymbolEditPart();
   }

   private boolean isSnapToGridEnabled()
   {
      if (((EditPart) getSelectedObjects().get(0)).getParent() == null) {
         return false;
      }
      return Boolean.TRUE.equals(((EditPart) getSelectedObjects().get(0)).getViewer()
            .getProperty(SnapToGrid.PROPERTY_GRID_ENABLED))
            && (Boolean.FALSE.equals(((EditPart) getSelectedObjects().get(0)).getViewer()
                  .getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED)) || ((EditPart) getSelectedObjects()
                  .get(0)).getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED) == null);
   }

   private boolean isNodeSymbolEditPart()
   {
      for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();)
      {
         Object part = iter.next();
         if (!(part instanceof AbstractNodeSymbolEditPart))
         {
            return false;
         }
      }
      return true;
   }

   public void run()
   {
      execute(command);
   }

   private void createSnapToGridCmd()
   {
      List editParts = getSelectedObjects();
      for (Iterator iter = editParts.iterator(); iter.hasNext();)
      {
         AbstractNodeSymbolEditPart part = (AbstractNodeSymbolEditPart) iter.next();
         INodeSymbol node = (INodeSymbol) part.getModel();
         EObject container = ModelUtils.findContainingProcess(node);
         if (container == null)
         {
            container = ModelUtils.findContainingDiagram(node);
         }
         Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(container);
         if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
         {
            command.add(UnexecutableCommand.INSTANCE);
         }         
         
         IFigure figure = part.getFigure();
         PrecisionRectangle rect = new PrecisionRectangle(figure.getBounds());
         figure.translateToAbsolute(rect);
         PrecisionRectangle baseRect = rect.getPreciseCopy();
         PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);
         SnapToHelper snapToHelper = null;
         if (part.getParent() != null) {
            snapToHelper = (SnapToHelper) part.getParent().getAdapter(
                  SnapToHelper.class);            
         }
         if (snapToHelper != null)
         {
            snapToHelper.snapPoint(new AlignmentRequest(), PositionConstants.HORIZONTAL
                  | PositionConstants.VERTICAL, new PrecisionRectangle[] {baseRect},
                  preciseDelta);
            MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
            moveCommand.setPart((INodeSymbol) part.getModel());
            Point oldLocation = new PrecisionRectangle(new Rectangle(new Long(
                  ((INodeSymbol) part.getModel()).getXPos()).intValue(), new Long(
                  ((INodeSymbol) part.getModel()).getYPos()).intValue(),
                  ((INodeSymbol) part.getModel()).getWidth(), ((INodeSymbol) part
                        .getModel()).getHeight())).getLocation();
            Point newLocation = preciseDelta.getTranslated(oldLocation);
            moveCommand.setLocation(newLocation);
            if (!newLocation.equals(oldLocation))
            {
               command.add(moveCommand);
            }
         }
      }
   }
}