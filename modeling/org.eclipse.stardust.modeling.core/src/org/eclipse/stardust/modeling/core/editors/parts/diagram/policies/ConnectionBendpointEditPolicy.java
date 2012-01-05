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

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.handles.BendpointCreationHandle;
import org.eclipse.gef.handles.BendpointHandle;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.stardust.modeling.core.editors.figures.BendpointSelectionMoveHandle;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.ConnectionBendpointsCommand;


/**
 * @author rsauer
 * @version $Revision$
 */
// this editpolicy is responsible for what can be done with bendpoints  
public class ConnectionBendpointEditPolicy extends /* My */BendpointEditPolicy
{
   int handleSize = 0;
   // a handle for each bendpoint
   private List bendpointHandles = new ArrayList();
   // DragEditPartsTrackerWithExplicitBendpoints uses this (REQ_MOVE_BENDPOINT)
   // request to alter a bendpoint
   public static class DummyBendpointRequest extends BendpointRequest
   {

   }   

   // the commands (created from request, called by framework)   
   // create bendpoints (when creating a new bendpoint from an existing point)
   protected Command getCreateBendpointCommand(BendpointRequest request)
   {
      // default (current) handles on creation 
      handleSize = handleSize == 0 ? handles.size() : handleSize;

      Command result = UnexecutableCommand.INSTANCE;
      if (request.getSource() instanceof AbstractConnectionSymbolEditPart)
      {
         AbstractConnectionSymbolEditPart connection = 
            (AbstractConnectionSymbolEditPart) request.getSource();
         if (handleSize + 1 < handles.size()) {
            // add new BendpointSelectionMoveHandle (extends BendpointMoveHandle)
            bendpointHandles.add(request.getIndex(), new BendpointSelectionMoveHandle(
                  (ConnectionEditPart) getHost(), request.getIndex(), request
                        .getIndex() + 1));
         }
         if (handleSize < handles.size())
         {
            handleSize++;
         }
         result = ConnectionBendpointsCommand.insertBendpoint(connection
               .getConnectionSymbolModel(), computeLocation(request), request
               .getIndex());
      }
      return result;
   }

   // delete a bendpoint from the existing bendpoints
   protected Command getDeleteBendpointCommand(BendpointRequest request)
   {
      Command result = UnexecutableCommand.INSTANCE;
      if (request.getSource() instanceof AbstractConnectionSymbolEditPart)
      {
         AbstractConnectionSymbolEditPart connection = 
            (AbstractConnectionSymbolEditPart) request.getSource();
         result = ConnectionBendpointsCommand.removeBendpoint(connection
               .getConnectionSymbolModel(), request.getIndex());
         // remove from list
         if(request.getIndex() < bendpointHandles.size())
         {
            bendpointHandles.remove(request.getIndex());
            handleSize--;
         } 
      }
      return result;
   }

   // when moving an existing bendpoint to a new location
   protected Command getMoveBendpointCommand(BendpointRequest request)
   {
     Command result = UnexecutableCommand.INSTANCE;
     
     if (request.getSource() instanceof AbstractConnectionSymbolEditPart) {
         AbstractConnectionSymbolEditPart connection = 
            (AbstractConnectionSymbolEditPart) request.getSource();
         result = ConnectionBendpointsCommand.moveBendpoint(connection
               .getConnectionSymbolModel(), computeLocation(request), request.getIndex());
      }
      return result;
   }

   // location of request
   private Point computeLocation(BendpointRequest request)
   {
      // the real location
      Point location = request.getLocation().getCopy();
      // ???
      if (!(request instanceof DummyBendpointRequest)) {
         // for Zooming
         ScalableFreeformRootEditPart root = 
            (ScalableFreeformRootEditPart) request.getSource().getRoot();
         ZoomManager manager = root.getZoomManager();
         // window
         Viewport viewport = (Viewport) root.getFigure();
         Point view = viewport.getViewLocation();
         if(!isSelected(request)) {
            // location + view.x, location + view.y
            location.performTranslate(view.x, view.y);            
         }
         location.performScale(1 / manager.getZoom());
      }
      return location;
   }

   /**
    * returns if the Bendpoint of the request is selected
    * @param request
    * @return
    */
   private boolean isSelected(BendpointRequest request) {
      int bendPointIndex = ((BendpointRequest) request).getIndex();
      BendpointSelectionMoveHandle mvHandle = bendpointHandles.size() > bendPointIndex
          ? (BendpointSelectionMoveHandle) bendpointHandles.get(bendPointIndex) : null;
      return mvHandle != null ? mvHandle.isSelected() : false;
   }

   private void snapLocation(BendpointRequest request)
   {
      Point location = request.getLocation();
      RootEditPart root = request.getSource().getRoot();

      SnapToHelper snapToHelper = 
         (SnapToHelper) root.getContents().getAdapter(SnapToHelper.class);
      if (snapToHelper != null)
      {
         PrecisionPoint preciseLocation = new PrecisionPoint(location);
         PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);

         snapToHelper.snapPoint(request, PositionConstants.HORIZONTAL
               | PositionConstants.VERTICAL, preciseLocation, preciseDelta);

         location.performTranslate(preciseDelta.x, preciseDelta.y);
      }
   }

   // shows the moving of the Bendpoint(s)
   public void showSourceFeedback(Request request)
   {
      if (request instanceof BendpointRequest)
      {
         // Point point = computeLocation((BendpointRequest) request);
         // ((BendpointRequest) request).setLocation(point);
         snapLocation((BendpointRequest) request);
      }
      // move request
      if(REQ_MOVE_BENDPOINT.equals(request.getType())) {          
         if (request instanceof BendpointRequest) {
            // when a Bendpoint is selected, it should not be deleted (consistency)
            if(isSelected((BendpointRequest) request)) {
               showMyMoveBendpointFeedback((BendpointRequest) request);
               return;
            }
         }
         // default   
         showMoveBendpointFeedback((BendpointRequest) request);
         // create request
      } else if (REQ_CREATE_BENDPOINT.equals(request.getType())) {
         showCreateBendpointFeedback((BendpointRequest) request);         
      }
   }

   // shows the moving of the Bendpoint, but is not deleting anything 
   protected void showMyMoveBendpointFeedback(BendpointRequest request) {
      // for Zooming
      ScalableFreeformRootEditPart root = 
         (ScalableFreeformRootEditPart) request.getSource().getRoot();
      ZoomManager manager = root.getZoomManager();
      Point p = new Point(request.getLocation());
      // necessary
      p.performScale(1 / manager.getZoom());
      // setReferencePoints(request);
      /*
      if(originalConstraint == null)
          saveOriginalConstraint();
      */
      List constraint = (List) getConnection().getRoutingConstraint();
      // is resulting in a Bug
      // getConnection().translateToRelative(p);
      Bendpoint bp = new AbsoluteBendpoint(p);
      constraint.set(request.getIndex(), bp);
      getConnection().setRoutingConstraint(constraint);
  }
   
   /**
    * when the user clicks with the mouse outside the connection
    * all bendpoints should be unselected
    */
   public void unSelectBendpoints() {
      Connection connection = getConnection();
      if(connection != null) {
         List bendPoints = (List) connection.getRoutingConstraint();
         if(bendPoints != null) {
            for (int i = 0; i <= bendPoints.size(); i++)
            {
               if(bendpointHandles.size() >= i + 1) {
                  BendpointSelectionMoveHandle mvHandle = (BendpointSelectionMoveHandle) bendpointHandles.get(i);
                  mvHandle.setSelected(false);
               }
            }
         }
      }
   }
   
   // returns a list of all handles (explizit routing only)   
   // called by framework 
   private List createHandlesForUserBendpoints()
   {
      // collects all handles 
      List list = new ArrayList();
      ConnectionEditPart connEP = (ConnectionEditPart) getHost();
      // get points and bendpoints
      PointList points = getConnection().getPoints();
      // 
      List bendPoints = (List) getConnection().getRoutingConstraint();
      // counter
      int bendPointIndex = 0;
      Point currBendPoint = null;
      if(bendPoints == null)
      {
         bendPoints = new ArrayList();
      }
      
      if (!bendPoints.isEmpty()) {
         // start with 1st entry
         currBendPoint = ((Bendpoint) bendPoints.get(bendPointIndex)).getLocation();
      }
      // loop through all points (some of them may be bendpoints)
      for (int i = 0; i < points.size() - 1; i++)
      {
         // put a create handle on the middle of every segment (could become a bendpoint)
         list.add(new BendpointCreationHandle(connEP, bendPointIndex, i));
         // If the current user bendpoint matches a bend location, show a move handle
         // not all bendpoints are found and this point is a bendpoint
         // 1st term is not necessarry?
         if(bendPointIndex < bendPoints.size() && currBendPoint.equals(points.getPoint(i + 1)))
         {
            // create and add handle 
            BendpointSelectionMoveHandle mvHandle = new BendpointSelectionMoveHandle(
                  connEP, bendPointIndex, i + 1);
            list.add(mvHandle);
            boolean isSelected = false;
            // check the old handle - if one - whether the old one was selected 
            BendpointSelectionMoveHandle oldMvHandle = bendpointHandles.size() > bendPointIndex
                  ? (BendpointSelectionMoveHandle) bendpointHandles.get(bendPointIndex)
                  : null;
            isSelected = oldMvHandle != null ? oldMvHandle.isSelected() : false;
            // here set if the current move handle is selected
            mvHandle.setSelected(isSelected);

            // add to list of bendpointhandles
            if (bendPointIndex < bendpointHandles.size()) {
               bendpointHandles.set(bendPointIndex, mvHandle);
            } else {
               bendpointHandles.add(mvHandle);
            }

            // Go to the next user bendpoint
            bendPointIndex++;
            if (bendPointIndex < bendPoints.size())
               currBendPoint = ((Bendpoint) bendPoints.get(bendPointIndex)).getLocation();
         }
      }
      // returns list of ('selection move' and 'creation') handles
      return list;
   }

   // ***** called by GEF framework xxx times, when selecting a connection (explicit routing only)
   // called by showSelection (SelectionHandlesEditPolicy)
   protected void addSelectionHandles()
   {
      // calls createSelectionHandles
      super.addSelectionHandles();
      List selectedHandles = getSelectedHandles();
      for (Iterator iter = handles.iterator(); iter.hasNext();)
      {
         BendpointHandle handle = (BendpointHandle) iter.next();
         if (handle instanceof BendpointSelectionMoveHandle)
         {
            ((BendpointSelectionMoveHandle) handle).setSelectedHandles(selectedHandles);
         }
      }
   }

   private List getSelectedHandles()
   {
      List selectedHandles = new ArrayList();
      for (Iterator iter = handles.iterator(); iter.hasNext();)
      {
         BendpointHandle handle = (BendpointHandle) iter.next();
         if (handle instanceof BendpointSelectionMoveHandle)
         {
            if (((BendpointSelectionMoveHandle) handle).isSelected())
            {
               selectedHandles.add(handle);
            }
         }
      }
      return selectedHandles;
   }   
   
   /*
   * creates a List of handles (for automatic bendpoints or for user bendpoints)
   */
   protected List createSelectionHandles() {
      List list = new ArrayList();
      if (isAutomaticallyBending())
      {
         // called when switching to other routing and back (then no bendpoints exists)
         list = createHandlesForAutomaticBendpoints();
      } else {
         list = createHandlesForUserBendpoints();
      }
      return list;
   }
   
   private boolean isAutomaticallyBending() {
      // no bendpoints - a straight line
      List constraint = (List) getConnection().getRoutingConstraint();
      return constraint == null || constraint.isEmpty();
   }
   
   private List createHandlesForAutomaticBendpoints() {
      List list = new ArrayList();
      ConnectionEditPart connEP = (ConnectionEditPart) getHost();
      PointList points = getConnection().getPoints();
      for (int i = 0; i < points.size() - 1; i++) {
         list.add(new BendpointCreationHandle(connEP, 0, i));
      }
      return list;
   }
}