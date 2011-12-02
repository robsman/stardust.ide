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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.tools.ConnectionBendpointTracker;
import org.eclipse.stardust.modeling.core.editors.figures.BendpointSelectionMoveHandle;


// @see ConnectionBendpointTracker

// A tracker for creating new bendpoints or dragging existing ones. 
// The Connection bendpoint tracker is returned by connection bendpoint handles.
// This tracker will send a BendpointRequest to the connection editpart which originated the tracker.
// The bendpoint request may be either a request to move an existing bendpoint, or a request to create a new bendpoint. 
public class DiagramConnectionBendpointTracker extends ConnectionBendpointTracker
{
   private List sourceRequests;
   private ArrayList locations;

   private final List selectedBendpoints;

   protected final BendpointSelectionMoveHandle handle;

   public DiagramConnectionBendpointTracker(ConnectionEditPart editpart,
         List selectedHandles, BendpointSelectionMoveHandle handle)
   {
      this.selectedBendpoints = selectedHandles;
      this.handle = handle;
      setConnectionEditPart(editpart);
      setIndex(handle.getIndex());
   }

   protected Command getCommand()
   {
      CompoundCommand command = new CompoundCommand();
      // here no bendpoint is selected
      if (selectedBendpoints.size() == 0)
      {
         command.add(getConnectionEditPart().getCommand(super.getSourceRequest()));
      }
      else
      {
         for (Iterator iter = getSourceRequests().iterator(); iter.hasNext();)
         {
            Request request = (Request) iter.next();
            command.add(getConnectionEditPart().getCommand(request));
         }
      }
      return command;
   }

   protected List getSourceRequests()
   {
      if (sourceRequests == null)
         sourceRequests = createSourceRequests();
      return sourceRequests;
   }

   protected List createSourceRequests()
   {
      // List constraints = (List) getConnection().getRoutingConstraint();
      sourceRequests = new ArrayList();
      locations = new ArrayList();
      for (Iterator iter = selectedBendpoints.iterator(); iter.hasNext();)
      {
         BendpointSelectionMoveHandle bendpointHandle = ((BendpointSelectionMoveHandle) iter
               .next());
         int index = bendpointHandle.getIndex();
         // request to alter a Bendpoint
         BendpointRequest request = new BendpointRequest();
         request.setType(getType());
         request.setIndex(index);
         request.setSource(getConnectionEditPart());
         // locations.add(((Bendpoint) constraints.get(i)).getLocation());
         locations.add(bendpointHandle.getLocation());
         sourceRequests.add(request);
      }
      return sourceRequests;
   }

   // what is this (shows moving of the stuff?)
   protected void showSourceFeedback()
   {
      if (selectedBendpoints.size() == 0)
      {
         super.showSourceFeedback();
      }
      else 
      {
         List editParts = getOperationSet();
         for (int i = 0; i < editParts.size(); i++)
         {
            EditPart editPart = (EditPart) editParts.get(i);
            for (Iterator iter = getSourceRequests().iterator(); iter.hasNext();)
            {
               Request request = (Request) iter.next();
               editPart.showSourceFeedback(request);
            }
         }
         setFlag(MAX_FLAG, true);
      }     
   }

   // called when releasing the mouse
   protected void eraseSourceFeedback()
   {
      // checks for the Flag
      if (!isShowingFeedback()) {
         return;
      }
      if (selectedBendpoints.size() == 0)
      {
         super.eraseSourceFeedback();
      }
      else 
      {  
         setFlag(MAX_FLAG, false);
         List editParts = getOperationSet();
         for (int i = 0; i < editParts.size(); i++)
         {
            EditPart editPart = (EditPart) editParts.get(i);
            for (Iterator iter = getSourceRequests().iterator(); iter.hasNext();)
            {
               Request request = (Request) iter.next();
               editPart.eraseSourceFeedback(request);
            }
         }
      }
   }

   // first update source request, then show source feedback, then erase 
   protected void updateSourceRequest()
   {
      if (selectedBendpoints.size() == 0)
      {
         super.updateSourceRequest();
      }
      else 
      {      
         for (int i = 0; i < getSourceRequests().size(); i++)
         {
            BendpointRequest bendpointRequest = (BendpointRequest) getSourceRequests()
                  .get(i);
            // Point location = ((Bendpoint) constraints.get(i)).getLocation();
            Point newLocation = new Point(((Point) locations.get(i)).x
                  + getDragMoveDelta().width, ((Point) locations.get(i)).y
                  + getDragMoveDelta().height);
            bendpointRequest.setLocation(newLocation);
         }   
      }
   }
}