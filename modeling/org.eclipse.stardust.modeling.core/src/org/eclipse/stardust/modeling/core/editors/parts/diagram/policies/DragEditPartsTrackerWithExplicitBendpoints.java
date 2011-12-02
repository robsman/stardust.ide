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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.policies;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractConnectionSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;


/**
 * @author fherinean
 * @version $Revision$
 */
public class DragEditPartsTrackerWithExplicitBendpoints extends DragEditPartsTracker
{
   public DragEditPartsTrackerWithExplicitBendpoints(EditPart editPart)
   {
      super(editPart);
   }

   protected Command getCommand()
   {
      if (isMove() && getTargetRequest() instanceof ChangeBoundsRequest)
      {
         CompoundCommand command = new CompoundCommand();

         Iterator iter = getOperationSet().iterator();

         ChangeBoundsRequest request = (ChangeBoundsRequest) getTargetRequest();

         while (iter.hasNext())
         {
            EditPart editPart = (EditPart) iter.next();
            command.add(editPart.getCommand(request));
         }

         iter = getBendpointConnectionEditParts(getOperationSet()).iterator();
         while (iter.hasNext())
         {
            AbstractConnectionSymbolEditPart conn = (AbstractConnectionSymbolEditPart) iter
                  .next();
            AbstractConnectionSymbolFigure figure = (AbstractConnectionSymbolFigure) conn
                  .getFigure();

            // iterate over all polyline points but both the starting and ending
            final int bendpointOffset = 2;
            for (int pointNumber = bendpointOffset; pointNumber <= figure.getPoints()
                  .size() - 1; ++pointNumber)
            {
               int bendpointIdx = pointNumber - bendpointOffset;
               BendpointRequest bpRequest = new ConnectionBendpointEditPolicy.DummyBendpointRequest();
               bpRequest.setType(REQ_MOVE_BENDPOINT);
               bpRequest.setSource(conn);
               bpRequest.setIndex(bendpointIdx);

               Point location = figure.getPoints().getPoint(pointNumber - 1);
               ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) conn
                     .getRoot();
               ZoomManager manager = root.getZoomManager();
               location.performScale(manager.getZoom());
               Point delta = request.getMoveDelta();
               location.performTranslate(delta.x, delta.y);
               location.performScale(1 / manager.getZoom());
               bpRequest.setLocation(location);

               command.add(conn.getCommand(bpRequest));
            }
         }
         return command;
      }
      return super.getCommand();
   }

   private List getBendpointConnectionEditParts(List editParts)
   {
      List result = new ArrayList();

      for (Iterator iter = editParts.iterator(); iter.hasNext();)
      {
         Object rawEditPart = iter.next();
         if (rawEditPart instanceof AbstractNodeSymbolEditPart)
         {
            AbstractNodeSymbolEditPart nodeSymbolEditPart = (AbstractNodeSymbolEditPart) rawEditPart;

            for (Iterator connIter = nodeSymbolEditPart.getSourceConnections().iterator(); connIter
                  .hasNext();)
            {
               AbstractConnectionSymbolEditPart conn = (AbstractConnectionSymbolEditPart) connIter
                     .next();
               AbstractGraphicalEditPart node = (AbstractGraphicalEditPart) conn
                     .getTarget();
               addBendpointEditPart(result, editParts, conn, node);
            }

            for (Iterator connIter = nodeSymbolEditPart.getTargetConnections().iterator(); connIter
                  .hasNext();)
            {
               AbstractConnectionSymbolEditPart conn = (AbstractConnectionSymbolEditPart) connIter
                     .next();
               AbstractGraphicalEditPart node = (AbstractGraphicalEditPart) conn
                     .getSource();
               addBendpointEditPart(result, editParts, conn, node);
            }
         }
      }

      return result;
   }

   private void addBendpointEditPart(List result, List editParts,
         AbstractConnectionSymbolEditPart conn, AbstractGraphicalEditPart node)
   {
      AbstractConnectionSymbolFigure figure = (AbstractConnectionSymbolFigure) conn
            .getFigure();

      if (editParts.contains(node) && !result.contains(conn)
            && figure.getConnectionRouter() instanceof BendpointConnectionRouter)
      {
         result.add(conn);
      }
   }

   protected boolean handleDragInProgress()
   {
      updateAutoexposeHelper();
      return super.handleDragInProgress();
   }

   // it is necessary to catch a NPE
   protected void handleAutoexpose()
   {
      try
      {
         super.handleAutoexpose();
      }
      catch (NullPointerException e)
      {         
      }
   }
}