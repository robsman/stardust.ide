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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DistributeAction extends SelectionAction
{
   public static final String REQ_DISTRIBUTE = Diagram_Messages.REQ_DISTRIBUTE;

   public static final String DISTRIBUTE_HORIZONTAL_LABEL = Diagram_Messages.DISTRIBUTE_HORIZONTAL_LABEL;

   public static final String DISTRIBUTE_VERTICAL_LABEL = Diagram_Messages.DISTRIBUTE_VERTICAL_LABEL;

   private int direction;

   private List operationSet;

   public DistributeAction(IWorkbenchPart part, int direction)
   {
      super(part);
      this.direction = direction;
      initUI();
   }

   protected boolean calculateEnabled()
   {
      operationSet = null;
      Command cmd = createDistributeCommand(false, false);
      if (cmd == null)
      {
         return false;
      }
      return true;
   }

   public void dispose()
   {
      operationSet = Collections.EMPTY_LIST;
      super.dispose();
   }

   public void run()
   {
      operationSet = null;
      boolean isSnapToGrid = isSnapToGridEnabled();
      boolean oneSymbolGrid = false;
      if (isSnapToGrid)
      {
         DistributionGridDialog dialog = new DistributionGridDialog();
         if (PlatformUI.getPreferenceStore().getBoolean(
               BpmProjectNature.PREFERENCE_DISTRIBUTE_PROMPT_GRID))
         {
            dialog.open();
            if (IDialogConstants.OK_ID == dialog.getReturnCode()
                  && dialog.getToggleState())
            {
               PlatformUI.getPreferenceStore().setValue(
                     BpmProjectNature.PREFERENCE_DISTRIBUTE_PROMPT_GRID, false);
               PlatformUI.getPreferenceStore().setValue(
                     BpmProjectNature.PREFERENCE_DISTRIBUTE_ALL_SYMBOLS_GRID,
                     !dialog.isOneSymbolSnapToGrid());
               PlatformUI.getPreferenceStore().setValue(
                     BpmProjectNature.PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID,
                     dialog.isOneSymbolSnapToGrid());
            }
            else if (IDialogConstants.OK_ID != dialog.getReturnCode())
            {
               return;
            }
         }
         oneSymbolGrid = PlatformUI.getPreferenceStore().getBoolean(
               BpmProjectNature.PREFERENCE_DISTRIBUTE_PROMPT_GRID) ? dialog
               .isOneSymbolSnapToGrid() : PlatformUI.getPreferenceStore().getBoolean(
               BpmProjectNature.PREFERENCE_DISTRIBUTE_ONE_SYMBOL_GRID);
      }
      execute(createDistributeCommand(oneSymbolGrid, isSnapToGrid));
   }

   private Command createDistributeCommand(boolean oneSymbolGrid, boolean isSnapToGrid)
   {
      DistributeRequest request = new DistributeRequest();
      List editparts = getOperationSet(request);
      if (editparts.size() < 3)
      {
         return null;
      }
      PrecisionRectangle distRect = calculateDistributionRectangle(editparts);
      Dimension existing = calculateTotalDimension(editparts);
      double deltaX = (distRect.preciseWidth - existing.width) / (editparts.size() - 1.0);
      double deltaY = (distRect.preciseHeight - existing.height)
            / (editparts.size() - 1.0);

      GraphicalEditPart part = (GraphicalEditPart) editparts.get(0);
      PrecisionRectangle rect = new PrecisionRectangle(part.getFigure().getBounds());
      part.getFigure().translateToAbsolute(rect);

      double px = rect.preciseRight() + deltaX;
      double py = rect.preciseBottom() + deltaY;

      CompoundCommand command = new CompoundCommand();
      command.setDebugLabel(getText());

      PrecisionPoint snapDelta = new PrecisionPoint(0, 0);

      if (isSnapToGrid && oneSymbolGrid)
      {
         EditPart lastSelectedPart = (EditPart) editparts.get(editparts.size() - 1);
         SnapToHelper snapToHelper = (SnapToHelper) lastSelectedPart.getParent()
               .getAdapter(SnapToHelper.class);

         request = new DistributeRequest();
         PrecisionRectangle baseRect = new PrecisionRectangle(
               ((GraphicalEditPart) lastSelectedPart).getFigure().getBounds());
         snapToHelper.snapPoint(request, PositionConstants.HORIZONTAL
               | PositionConstants.VERTICAL, new PrecisionRectangle[] {baseRect},
               snapDelta);
         request.setMoveDelta(snapDelta);
         command.add(lastSelectedPart.getCommand(request));

         request = new DistributeRequest();
         request.setMoveDelta(snapDelta);
         command.add(((EditPart) editparts.get(0)).getCommand(request));
      }

      for (int i = 1; i < editparts.size() - 1; i++)
      {
         part = (GraphicalEditPart) editparts.get(i);
         request = new DistributeRequest();
         rect = new PrecisionRectangle(part.getFigure().getBounds());
         part.getFigure().translateToAbsolute(rect);
         PrecisionPoint delta = direction == PositionConstants.HORIZONTAL
               ? new PrecisionPoint(px - rect.preciseX, 0)
               : new PrecisionPoint(0, py - rect.preciseY);

         if (isSnapToGrid && !oneSymbolGrid)
         {
            snapDelta = new PrecisionPoint(0, 0);
            SnapToHelper snapToHelper = (SnapToHelper) part.getParent().getAdapter(
                  SnapToHelper.class);
            Rectangle movedRect = new Rectangle(part.getFigure().getBounds())
                  .getTranslated(delta);
            if (snapToHelper != null)
            {
               snapToHelper.snapPoint(request, PositionConstants.HORIZONTAL
                     | PositionConstants.VERTICAL,
                     new PrecisionRectangle[] {new PrecisionRectangle(movedRect)},
                     snapDelta);
            }
         }

         PrecisionPoint snapDistDelta = new PrecisionPoint(delta.x + snapDelta.x, delta.y
               + snapDelta.y);
         request.setMoveDelta(snapDistDelta);
         px += rect.preciseWidth + deltaX;
         py += rect.preciseHeight + deltaY;
         command.add(part.getCommand(request));
      }

      return command;
   }

   private boolean isSnapToGridEnabled()
   {
      return Boolean.TRUE.equals(((EditPart) getSelectedObjects().get(0)).getViewer()
            .getProperty(SnapToGrid.PROPERTY_GRID_ENABLED))
            && (Boolean.FALSE.equals(((EditPart) getSelectedObjects().get(0)).getViewer()
                  .getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED)) || ((EditPart) getSelectedObjects()
                  .get(0)).getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED) == null);
   }

   private Dimension calculateTotalDimension(List editparts)
   {
      GraphicalEditPart part = (GraphicalEditPart) editparts.get(0);
      Dimension dim = new Dimension(part.getFigure().getBounds().getSize());
      for (int i = 1; i < editparts.size(); i++)
      {
         part = (GraphicalEditPart) editparts.get(i);
         Dimension dim2 = new Dimension(part.getFigure().getBounds().getSize());
         dim.expand(dim2);
      }
      return dim;
   }

   private PrecisionRectangle calculateDistributionRectangle(List editparts)
   {
      GraphicalEditPart part = (GraphicalEditPart) editparts.get(0);
      PrecisionRectangle rect = new PrecisionRectangle(part.getFigure().getBounds());
      part.getFigure().translateToAbsolute(rect);
      for (int i = 1; i < editparts.size(); i++)
      {
         part = (GraphicalEditPart) editparts.get(i);
         PrecisionRectangle rect2 = new PrecisionRectangle(part.getFigure().getBounds());
         part.getFigure().translateToAbsolute(rect2);
         rect.union(rect2);
      }
      return rect;
   }

   protected List getOperationSet(Request request)
   {
      if (operationSet != null)
      {
         return operationSet;
      }
      List editparts = new ArrayList(getSelectedObjects());
      if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart))
      {
         return Collections.EMPTY_LIST;
      }
      editparts = ToolUtilities.getSelectionWithoutDependants(editparts);
      ToolUtilities.filterEditPartsUnderstanding(editparts, request);
      if (editparts.size() < 3)
      {
         return Collections.EMPTY_LIST;
      }
      EditPart parent = ((EditPart) editparts.get(0)).getParent();
      for (int i = 1; i < editparts.size(); i++)
      {
         EditPart part = (EditPart) editparts.get(i);
         if (part.getParent() != parent)
         {
            return Collections.EMPTY_LIST;
         }
      }
      Collections.sort(editparts, new Comparator()
      {
         public int compare(Object o1, Object o2)
         {
            Point p1 = getCenter((GraphicalEditPart) o1);
            Point p2 = getCenter((GraphicalEditPart) o2);
            return direction == PositionConstants.HORIZONTAL ? p1.x - p2.x : p1.y - p2.y;
         }
      });
      return editparts;
   }

   private Point getCenter(GraphicalEditPart part1)
   {
      Rectangle rect1 = new Rectangle(part1.getFigure().getBounds());
      part1.getFigure().translateToAbsolute(rect1);
      return rect1.getCenter();
   }

   protected void initUI()
   {
      switch (direction)
      {
      case PositionConstants.HORIZONTAL:
         setId(DiagramActionConstants.DISTRIBUTE_HORIZONTAL);
         setText(DISTRIBUTE_HORIZONTAL_LABEL);
         // setToolTipText(GEFMessages.AlignLeftAction_Tooltip);
         // setImageDescriptor(InternalImages.DESC_HORZ_ALIGN_LEFT);
         // setDisabledImageDescriptor(InternalImages.DESC_HORZ_ALIGN_LEFT_DIS);
         break;

      case PositionConstants.VERTICAL:
         setId(DiagramActionConstants.DISTRIBUTE_VERTICAL);
         setText(DISTRIBUTE_VERTICAL_LABEL);
         // setToolTipText(GEFMessages.AlignRightAction_Tooltip);
         // setImageDescriptor(InternalImages.DESC_HORZ_ALIGN_RIGHT);
         // setDisabledImageDescriptor(InternalImages.DESC_HORZ_ALIGN_RIGHT_DIS);
         break;
      }
   }

   private class DistributionGridDialog extends MessageDialogWithToggle
   {

      private Button oneSymbolRadio;

      private boolean isOneSymbolSnapToGrid;

      public DistributionGridDialog()
      {
         super(null, Diagram_Messages.TITLE_SnapToGrid, null,
               "", MessageDialog.QUESTION, new String[] { //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
               IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0,
               Diagram_Messages.LB_RememberDecision, false);
      }

      protected Control createMessageArea(Composite composite)
      {
         Image image = getImage();
         if (image != null)
         {
            imageLabel = new Label(composite, SWT.NULL);
            image.setBackground(imageLabel.getBackground());
            imageLabel.setImage(image);
            imageLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER
                  | GridData.VERTICAL_ALIGN_BEGINNING));
         }
         Group group = FormBuilder.createGroup(composite, Diagram_Messages.LB_SnapToGrid,
               2);
         ((GridLayout) composite.getLayout()).horizontalSpacing = 15;
         oneSymbolRadio = FormBuilder.createRadioButton(group,
               Diagram_Messages.LB_LastSelectedSymbol);
         oneSymbolRadio.setSelection(true);
         FormBuilder.createRadioButton(group, Diagram_Messages.LB_AllMovedSymbols);
         return composite;
      }

      protected void okPressed()
      {
         setReturnCode(OK);
         isOneSymbolSnapToGrid = oneSymbolRadio.getSelection();
         close();
      }

      public boolean isOneSymbolSnapToGrid()
      {
         return isOneSymbolSnapToGrid;
      }
   }
}
