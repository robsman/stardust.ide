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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveNodeSymbolCommand;
import org.eclipse.ui.IWorkbenchPart;


public class AlignmentSnapToGridAction extends SelectionAction
{
   private AlignmentAction alignmentAction;

   private final ISelectionProvider selectionProvider = new ISelectionProvider()
   {
      private ISelection selection;

      public void addSelectionChangedListener(ISelectionChangedListener listener)
      {}

      public ISelection getSelection()
      {
         return selection;
      }

      public void removeSelectionChangedListener(ISelectionChangedListener listener)
      {}

      public void setSelection(ISelection selection)
      {
         this.selection = selection;
      }
   };

   private final int alignment;

   public AlignmentSnapToGridAction(IWorkbenchPart part, AlignmentAction alignmentAction,
         int alignment)
   {
      super(part);
      this.alignment = alignment;
      setId(alignmentAction.getId());
      setText(alignmentAction.getText());
      alignmentAction.setSelectionProvider(selectionProvider);
      this.alignmentAction = alignmentAction;
   }

   public boolean isEnabled()
   {
      selectionProvider.setSelection(getSelection());
      alignmentAction.update();
      return alignmentAction.isEnabled();
   }

   public void run()
   {
      final boolean isSnapToGrid = isSnapToGridEnabled();
      Command alignCmd = new Command()
      {
         public void execute()
         {
            alignmentAction.run();
            if (isSnapToGrid)
            {
               getCommandStack().execute(createSnapToGridCmd());
            }
         }

         public void undo()
         {
            getCommandStack().undo();
            if (isSnapToGrid)
            {
               getCommandStack().undo();
            }
         }

         public void redo()
         {
            getCommandStack().redo();
            if (isSnapToGrid)
            {
               getCommandStack().redo();
            }
         }
      };
      getCommandStack().execute(alignCmd);
   }

   protected boolean calculateEnabled()
   {
      selectionProvider.setSelection(getSelection());
      alignmentAction.update();
      return alignmentAction.isEnabled();
   }

   private boolean isSnapToGridEnabled()
   {
      return Boolean.TRUE.equals(((EditPart) getSelectedObjects().get(0)).getViewer()
            .getProperty(SnapToGrid.PROPERTY_GRID_ENABLED))
            && (Boolean.FALSE.equals(((EditPart) getSelectedObjects().get(0)).getViewer()
                  .getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED)) || ((EditPart) getSelectedObjects()
                  .get(0)).getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED) == null);
   }

   private CompoundCommand createSnapToGridCmd()
   {
      CompoundCommand command = new CompoundCommand();
      List editParts = getSelectedObjects();
      // PrecisionPoint snapGridPoint = computeClosestGridPoint(editParts);
      addSnapToClosestGridPointCmd(command, editParts /* , snapGridPoint */);
      return command;
   }

   private void addSnapToClosestGridPointCmd(CompoundCommand command, List editParts)
   {
      // if (snapGridPoint != null)
      // {
      // snapGridPoint = PositionConstants.MIDDLE == alignment ? new PrecisionPoint(
      // snapGridPoint.x, 0) : PositionConstants.CENTER == alignment
      // ? new PrecisionPoint(0, snapGridPoint.y)
      // : snapGridPoint;
      // // align to closest grid point x or y
      // for (Iterator iter = editParts.iterator(); iter.hasNext();)
      // {
      // AbstractNodeSymbolEditPart part = (AbstractNodeSymbolEditPart) iter.next();
      // INodeSymbol symbol = (INodeSymbol) part.getModel();
      // addMoveNodeSymbolCmd(command, snapGridPoint, symbol);
      // }
      // }
      // all symbols snap to the grid if alignment is middle or center
      if (PositionConstants.MIDDLE == alignment || PositionConstants.CENTER == alignment)
      {
         for (Iterator iter = editParts.iterator(); iter.hasNext();)
         {
            AbstractNodeSymbolEditPart part = (AbstractNodeSymbolEditPart) iter.next();

            INodeSymbol symbol = (INodeSymbol) part.getModel();
            Rectangle rect = new Rectangle(new Long(symbol.getXPos()).intValue(),
                  new Long(symbol.getYPos()).intValue(), symbol.getWidth(), symbol
                        .getHeight());

            PrecisionRectangle baseRect = new PrecisionRectangle(rect);
            PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);

            SnapToHelper snapToHelper = (SnapToHelper) part.getParent().getAdapter(
                  SnapToHelper.class);
            if (snapToHelper != null)
            {
               snapToHelper.snapPoint(new AlignmentRequest(),
                     PositionConstants.HORIZONTAL | PositionConstants.VERTICAL,
                     new PrecisionRectangle[] {baseRect}, preciseDelta);

               addMoveNodeSymbolCmd(command, preciseDelta, symbol);
            }
         }
      }
   }

   // private PrecisionPoint computeClosestGridPoint(List editParts)
   // {
   // PrecisionPoint snapGridPoint = null;
   // for (Iterator iter = editParts.iterator(); iter.hasNext();)
   // {
   // AbstractNodeSymbolEditPart part = (AbstractNodeSymbolEditPart) iter.next();
   //
   // INodeSymbol symbol = (INodeSymbol) part.getModel();
   // Rectangle rect = new Rectangle(new Long(symbol.getXPos()).intValue(), new Long(
   // symbol.getYPos()).intValue(), symbol.getWidth(), symbol.getHeight());
   //
   // PrecisionRectangle baseRect = new PrecisionRectangle(rect);
   // PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);
   //
   // SnapToHelper snapToHelper = (SnapToHelper) part.getParent().getAdapter(
   // SnapToHelper.class);
   // if (snapToHelper != null)
   // {
   // snapToHelper.snapPoint(new AlignmentRequest(), PositionConstants.HORIZONTAL
   // | PositionConstants.VERTICAL, new PrecisionRectangle[] {baseRect},
   // preciseDelta);
   //
   // if (snapGridPoint == null)
   // {
   // snapGridPoint = preciseDelta;
   // }
   // else if (preciseDelta.preciseX < snapGridPoint.preciseX
   // || preciseDelta.preciseY < snapGridPoint.preciseY)
   // {
   // snapGridPoint = preciseDelta;
   // }
   // }
   // }
   // return snapGridPoint;
   // }

   private void addMoveNodeSymbolCmd(CompoundCommand command, PrecisionPoint delta,
         INodeSymbol symbol)
   {
      MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
      moveCommand.setPart(symbol);
      moveCommand.setLocation(delta.getTranslated(new PrecisionRectangle(new Rectangle(
            new Long(symbol.getXPos()).intValue(), new Long(symbol.getYPos()).intValue(),
            symbol.getWidth(), symbol.getHeight())).getLocation()));
      command.add(moveCommand);
   }
}
