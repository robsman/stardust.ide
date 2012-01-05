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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Handle;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.NonResizableHandleKit;
import org.eclipse.gef.handles.ResizableHandleKit;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.modeling.core.editors.figures.IFeedbackFigureFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DistributeAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DistributeRequest;


public class NodeSymbolResizableEditPolicy extends ResizableEditPolicy
{
   public EditPart getTargetEditPart(Request request)
   {
      // TODO Auto-generated method stub
      EditPart result = null;

      if (getHost().getModel() instanceof INodeSymbol)
      {
         IGraphicalObject symbol = (INodeSymbol) getHost().getModel();
         while (symbol.eContainer() instanceof GroupSymbolType)
         {
            symbol = (IGraphicalObject) symbol.eContainer();
         }

         if (getHost().getModel() != symbol)
         {
            result = (EditPart) getHost().getRoot()
               .getViewer()
               .getEditPartRegistry()
               .get(symbol);
         }
      }

      return (null != result) ? result : super.getTargetEditPart(request);
   }

   public static class MyMoveHandle extends MoveHandle
   {
      public MyMoveHandle(GraphicalEditPart owner)
      {
         super(owner);
      }

      protected DragTracker createDragTracker()
      {
         DragEditPartsTracker tracker = new DragEditPartsTrackerWithExplicitBendpoints(getOwner());
         tracker.setDefaultCursor(getCursor());
         return tracker;
      }
   }

   public int getResizeDirections()
   {
      Object model = getHost().getModel();
      if (model instanceof GatewaySymbol || model instanceof StartEventSymbol
         || model instanceof EndEventSymbol)
      {
         return 0;
      }
      return super.getResizeDirections();
   }

   protected List createSelectionHandles()
   {
      List list = new ArrayList();

      int directions = getResizeDirections();
      if (directions == 0)
         NonResizableHandleKit.addHandles((GraphicalEditPart) getHost(), list);
      else if (directions != -1)
      {
//         ResizableHandleKit.addMoveHandle((GraphicalEditPart)getHost(), list);
         addMoveHandle((GraphicalEditPart) getHost(), list);
         if ((directions & PositionConstants.EAST) != 0)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.EAST);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.EAST);
         if ((directions & PositionConstants.SOUTH_EAST) == PositionConstants.SOUTH_EAST)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.SOUTH_EAST);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.SOUTH_EAST);
         if ((directions & PositionConstants.SOUTH) != 0)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.SOUTH);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.SOUTH);
         if ((directions & PositionConstants.SOUTH_WEST) == PositionConstants.SOUTH_WEST)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.SOUTH_WEST);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.SOUTH_WEST);
         if ((directions & PositionConstants.WEST) != 0)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.WEST);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.WEST);
         if ((directions & PositionConstants.NORTH_WEST) == PositionConstants.NORTH_WEST)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.NORTH_WEST);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.NORTH_WEST);
         if ((directions & PositionConstants.NORTH) != 0)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.NORTH);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.NORTH);
         if ((directions & PositionConstants.NORTH_EAST) == PositionConstants.NORTH_EAST)
            ResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.NORTH_EAST);
         else
            NonResizableHandleKit.addHandle((GraphicalEditPart) getHost(), list,
               PositionConstants.NORTH_EAST);
      }
      else
      {
//         ResizableHandleKit.addHandles((GraphicalEditPart)getHost(), list);
         addHandles((GraphicalEditPart) getHost(), list);
      }
      return list;
   }

   static void addMoveHandle(GraphicalEditPart part, List list)
   {
      list.add(new MyMoveHandle(part));
   }

   static void addHandles(GraphicalEditPart part, List handles)
   {
      addMoveHandle(part, handles);
      handles.add(createHandle(part, PositionConstants.EAST));
      handles.add(createHandle(part, PositionConstants.SOUTH_EAST));
      handles.add(createHandle(part, PositionConstants.SOUTH));
      handles.add(createHandle(part, PositionConstants.SOUTH_WEST));
      handles.add(createHandle(part, PositionConstants.WEST));
      handles.add(createHandle(part, PositionConstants.NORTH_WEST));
      handles.add(createHandle(part, PositionConstants.NORTH));
      handles.add(createHandle(part, PositionConstants.NORTH_EAST));
   }

   static Handle createHandle(GraphicalEditPart owner, int direction)
   {
      ResizeHandle handle = new ResizeHandle(owner, direction);
      //	handle.setDragTracker(new ResizeTracker(direction));
      return handle;
   }

   protected Command getMoveCommand(ChangeBoundsRequest request)
   {
      // including original request to be able to correctly perform collision test later
      ChangeBoundsRequest req = new ProxyChangeBoundsRequest(REQ_MOVE_CHILDREN, request);
      req.setEditParts(getHost());

      req.setMoveDelta(request.getMoveDelta());
      req.setSizeDelta(request.getSizeDelta());
      req.setLocation(request.getLocation());
      req.setExtendedData(request.getExtendedData());
      return getHost().getParent().getCommand(req);
   }

   public boolean understandsRequest(Request request)
   {
      if (DistributeAction.REQ_DISTRIBUTE.equals(request.getType()))
      {
         return true;
      }
      return super.understandsRequest(request);
   }


   public Command getCommand(Request request)
   {
      if (DistributeAction.REQ_DISTRIBUTE.equals(request.getType()))
      {
         return getDistributeChildrenCommand((DistributeRequest) request);
      }
      return super.getCommand(request);
   }

   private Command getDistributeChildrenCommand(DistributeRequest request)
   {
      return getResizeCommand(request);
   }

   protected IFigure createDragSourceFeedbackFigure()
   {
      IFigure figure = createFigure((GraphicalEditPart) getHost(), null);
      if (null != figure)
      {
         figure.setBounds(getInitialFeedbackBounds());
         addFeedback(figure);
      }
      else
      {
         figure = super.createDragSourceFeedbackFigure();
      }
      return figure;
   }

   protected IFigure createFigure(GraphicalEditPart part, IFigure parent)
   {
      IFigure child = null;

      if (part instanceof IFeedbackFigureFactory)
      {
         child = ((IFeedbackFigureFactory) part).createFeedbackFigure();

         if (null != child)
         {
            if (null != parent)
            {
               parent.add(child);
            }
            
            Rectangle childBounds = part.getFigure().getBounds().getCopy();
            
            IFigure walker = part.getFigure().getParent();
            while (walker != ((GraphicalEditPart) part.getParent()).getFigure())
            {
               walker.translateToParent(childBounds);
               walker = walker.getParent();
            }
            
            child.setBounds(childBounds);
            
            for (Iterator i = part.getChildren().iterator(); i.hasNext();)
            {
               createFigure((GraphicalEditPart) i.next(), child);
            }
         }
      }

      return child;
   }
}
