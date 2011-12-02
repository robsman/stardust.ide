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
import java.util.Iterator;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.swt.widgets.Shell;


public class ChangeConstraintCommand extends MoveNodeSymbolCommand
{   
   private CompoundCommand cmd = new CompoundCommand();
   private EditPart target;
   private EditPart host;
   private boolean snapToGrid = true;
   
   private boolean isColliding = false;

   public void execute()
   {
      // same container
      if (getHost().equals(target.getParent()))
      {
         if (isColliding)
         {
            Shell shell = ((EditPart) getHost()).getViewer().getControl().getShell();
            boolean result = MessageDialog
                  .openQuestion(shell, Diagram_Messages.MSG_DIA_MOVE_SYMBOLS,
                        Diagram_Messages.MSG_DIA_THIS_ACTION_WILL_CAUSE_OVERLAPPING_OF_SYMBOLS_DO_YOU_WANT_TO_CONTINUE);
            if (!result)
            {
               return;
            }
         }
         int oldPosX = new Long(((INodeSymbol) target.getModel()).getXPos())
               .intValue();
         int oldPosY = new Long(((INodeSymbol) target.getModel()).getYPos())
               .intValue();

         super.execute();

         if (target instanceof LaneEditPart && snapToGrid)
         {
            createLaneSnapToGridCmd(oldPosX, oldPosY);
         }
      }
   }

   private Object getHost()
   {
      return host;
   }

   public void redo()
   {
      cmd.redo();
      super.redo();
   }

   public void undo()
   {
      cmd.undo();
      super.undo();

   }

   private void createLaneSnapToGridCmd(int oldPosX, int oldPosY)
   {
      int newPosX = new Long(((LaneEditPart) target).getLaneModel().getXPos())
            .intValue();
      int newPosY = new Long(((LaneEditPart) target).getLaneModel().getYPos())
            .intValue();

      for (Iterator iter = target.getChildren().iterator(); iter.hasNext();)
      {
         AbstractNodeSymbolEditPart editPart = (AbstractNodeSymbolEditPart) iter
               .next();
         int symbolX = new Long(((INodeSymbol) editPart.getModel()).getXPos())
               .intValue();
         int symbolY = new Long(((INodeSymbol) editPart.getModel()).getYPos())
               .intValue();

         PrecisionRectangle rect = new PrecisionRectangle(editPart.getFigure()
               .getBounds());
         editPart.getFigure().translateToAbsolute(rect);
         PrecisionRectangle newRect = new PrecisionRectangle(
               new Rectangle(new Point(rect.x - (oldPosX - newPosX), rect.y
                     - (oldPosY - newPosY)), rect.getSize()));
         PrecisionRectangle baseRect = newRect.getPreciseCopy();
         PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);

         SnapToHelper snapToHelper = (SnapToHelper) target
               .getAdapter(SnapToHelper.class);
         Point translated = null;
         if (snapToHelper != null)
         {
            ChangeBoundsRequest fakeRequest = new ChangeBoundsRequest();
            fakeRequest.setLocation(new Point(symbolX, symbolY));
            Point moveDelta = new Point(0, 0);
            fakeRequest.setMoveDelta(moveDelta);
            fakeRequest.setType(RequestConstants.REQ_RESIZE);
            fakeRequest.setEditParts(new ArrayList());
            snapToHelper.snapPoint(fakeRequest, PositionConstants.HORIZONTAL
                  | PositionConstants.VERTICAL,
                  new PrecisionRectangle[] {baseRect}, preciseDelta);
            translated = preciseDelta.getTranslated(new Point(symbolX,
                  symbolY));            
         }
         if(translated != null)
         {
            MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
            moveCommand.setPart((INodeSymbol) editPart.getModel());            
            moveCommand.setLocation(translated);
            cmd.add(moveCommand);            
         }         
      }
      target.getViewer().getEditDomain().getCommandStack().execute(cmd);
   }

   public void setColliding(boolean isColliding)
   {
      this.isColliding = isColliding;
   }

   public void setTarget(EditPart target)
   {
      this.target = target;
   }

   public void setHost(EditPart host)
   {
      this.host = host;
   }  
   
   public void setSnapToGrid(boolean set)
   {
      snapToGrid = set;
   }     
}