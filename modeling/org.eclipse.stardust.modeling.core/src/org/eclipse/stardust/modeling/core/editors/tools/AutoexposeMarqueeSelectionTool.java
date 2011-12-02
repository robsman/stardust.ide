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
package org.eclipse.stardust.modeling.core.editors.tools;

import java.util.*;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.*;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Display;


public class AutoexposeMarqueeSelectionTool extends AutoexposeTool
{
   public static final Object PROPERTY_MARQUEE_BEHAVIOR = "marqueeBehavior"; //$NON-NLS-1$

   public static final int BEHAVIOR_NODES_CONTAINED = new Integer(1).intValue();

   public static final int BEHAVIOR_CONNECTIONS_TOUCHED = new Integer(2).intValue();

   public static final int BEHAVIOR_NODES_AND_CONNECTIONS = new Integer(3).intValue();

   static final int DEFAULT_MODE = 0;

   static final int TOGGLE_MODE = 1;

   static final int APPEND_MODE = 2;

   private Figure marqueeRectangleFigure;

   private Set allChildren = new HashSet();

   private Collection selectedEditParts;

   private Request targetRequest;

   private int marqueeBehavior = BEHAVIOR_NODES_CONTAINED;

   private int mode;

   private static final Request MARQUEE_REQUEST = new Request(
         RequestConstants.REQ_SELECTION);

   private Point startLocation;

   private Rectangle marqueeRectAbsolute = new Rectangle();
   public AutoexposeMarqueeSelectionTool()
   {
      setDefaultCursor(Cursors.CROSS);
      setUnloadWhenFinished(false);
   }

   protected void applyProperty(Object key, Object value)
   {
      if (PROPERTY_MARQUEE_BEHAVIOR.equals(key))
      {
         if (value instanceof Integer)
            setMarqueeBehavior(((Integer) value).intValue());
         return;
      }
      super.applyProperty(key, value);
   }

   private void calculateConnections(Collection newSelections, Collection deselections)
   {
      // determine the currently selected nodes minus the ones that are to be deselected
      Collection currentNodes = new HashSet();
      if (getSelectionMode() != DEFAULT_MODE)
      { // everything is deselected in default mode
         Iterator iter = getCurrentViewer().getSelectedEditParts().iterator();
         while (iter.hasNext())
         {
            EditPart selected = (EditPart) iter.next();
            if (!(selected instanceof ConnectionEditPart)
                  && !deselections.contains(selected))
               currentNodes.add(selected);
         }
      }
      // add new connections to be selected to newSelections
      Collection connections = new ArrayList();
      for (Iterator nodes = newSelections.iterator(); nodes.hasNext();)
      {
         GraphicalEditPart node = (GraphicalEditPart) nodes.next();
         for (Iterator itr = node.getSourceConnections().iterator(); itr.hasNext();)
         {
            ConnectionEditPart sourceConn = (ConnectionEditPart) itr.next();
            if (sourceConn.getSelected() == EditPart.SELECTED_NONE
                  && (newSelections.contains(sourceConn.getTarget()) || currentNodes
                        .contains(sourceConn.getTarget())))
               connections.add(sourceConn);
         }
         for (Iterator itr = node.getTargetConnections().iterator(); itr.hasNext();)
         {
            ConnectionEditPart targetConn = (ConnectionEditPart) itr.next();
            if (targetConn.getSelected() == EditPart.SELECTED_NONE
                  && (newSelections.contains(targetConn.getSource()) || currentNodes
                        .contains(targetConn.getSource())))
               connections.add(targetConn);
         }
      }
      newSelections.addAll(connections);
      // add currently selected connections that are to be deselected to deselections
      connections = new HashSet();
      for (Iterator nodes = deselections.iterator(); nodes.hasNext();)
      {
         GraphicalEditPart node = (GraphicalEditPart) nodes.next();
         for (Iterator itr = node.getSourceConnections().iterator(); itr.hasNext();)
         {
            ConnectionEditPart sourceConn = (ConnectionEditPart) itr.next();
            if (sourceConn.getSelected() != EditPart.SELECTED_NONE)
               connections.add(sourceConn);
         }
         for (Iterator itr = node.getTargetConnections().iterator(); itr.hasNext();)
         {
            ConnectionEditPart targetConn = (ConnectionEditPart) itr.next();
            if (targetConn.getSelected() != EditPart.SELECTED_NONE)
               connections.add(targetConn);
         }
      }
      deselections.addAll(connections);
   }

   private void calculateNewSelection(Collection newSelections, Collection deselections)
   {
      Rectangle marqueeRect = getMarqueeSelectionRectangle();
      for (Iterator itr = getAllChildren().iterator(); itr.hasNext();)
      {
         GraphicalEditPart child = (GraphicalEditPart) itr.next();
         IFigure figure = child.getFigure();
         
         if(child instanceof DiagramEditPart)
            continue;
         
         if (!child.isSelectable() || child.getTargetEditPart(MARQUEE_REQUEST) != child)
            continue;

         INodeSymbol childSymbol = (INodeSymbol) child.getModel();
         EditPart hostEP = null;
         if(child.getParent() instanceof AbstractSwimlaneEditPart)
         {
            hostEP = child.getParent();            
         }
                  
         Rectangle r = figure.getBounds().getCopy();
         IFigure parent = figure.getParent();         
         if (parent != null && hostEP != null)
         {
            // we need the absolute location of the symbol
            DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) childSymbol);
            Point newLocation = PoolLaneUtils.getAbsoluteLocation(hostEP, r.getLocation(), diagram);
            r = new Rectangle(newLocation.x, newLocation.y, r.width, r.height);
         }
         ZoomManager zoomManager = ((ScalableFreeformRootEditPart) getCurrentViewer()
               .getRootEditPart()).getZoomManager();
         r.scale(zoomManager.getZoom());

         boolean included = false;
         if (child instanceof ConnectionEditPart && marqueeRect.intersects(r))
         {
            Rectangle relMarqueeRect = Rectangle.SINGLETON;
            figure.translateToRelative(relMarqueeRect.setBounds(marqueeRect));
            included = ((PolylineConnection) figure).getPoints().intersects(
                  relMarqueeRect);
         }
         else
            included = marqueeRectAbsolute.contains(r);

         if (included)
         {
            if (child.getSelected() == EditPart.SELECTED_NONE
                  || getSelectionMode() != TOGGLE_MODE)
            {
               newSelections.add(child);

            }
            else
               deselections.add(child);
         }
      }

      if (marqueeBehavior == BEHAVIOR_NODES_AND_CONNECTIONS)
         calculateConnections(newSelections, deselections);
   }

   private Request createTargetRequest()
   {
      return MARQUEE_REQUEST;
   }

   public void deactivate()
   {
      if (isInState(STATE_DRAG_IN_PROGRESS))
      {
         eraseMarqueeFeedback();
         eraseTargetFeedback();
      }
      setAutoexposeHelper(null);
      super.deactivate();
      allChildren.clear();
      setState(STATE_TERMINAL);
   }

   private void eraseMarqueeFeedback()
   {
      if (marqueeRectangleFigure != null)
      {
         removeFeedback(marqueeRectangleFigure);
         marqueeRectangleFigure = null;
      }
   }

   private void eraseTargetFeedback()
   {
      if (selectedEditParts == null)
         return;
      Iterator oldEditParts = selectedEditParts.iterator();
      while (oldEditParts.hasNext())
      {
         EditPart editPart = (EditPart) oldEditParts.next();
         editPart.eraseTargetFeedback(getTargetRequest());
      }
   }

   private Set getAllChildren()
   {
      if (allChildren.isEmpty())
         getAllChildren(getCurrentViewer().getRootEditPart(), allChildren);
      return allChildren;
   }

   private void getAllChildren(EditPart editPart, Set allChildren)
   {
      List children = editPart.getChildren();
      for (int i = 0; i < children.size(); i++)
      {
         GraphicalEditPart child = (GraphicalEditPart) children.get(i);
         if (marqueeBehavior == BEHAVIOR_NODES_CONTAINED
               || marqueeBehavior == BEHAVIOR_NODES_AND_CONNECTIONS)
            allChildren.add(child);
         if (marqueeBehavior == BEHAVIOR_CONNECTIONS_TOUCHED)
         {
            allChildren.addAll(child.getSourceConnections());
            allChildren.addAll(child.getTargetConnections());
         }
         getAllChildren(child, allChildren);
      }
   }

   protected String getCommandName()
   {
      return REQ_SELECTION;
   }

   protected String getDebugName()
   {
      return "Autoexpose Marquee Tool: " + marqueeBehavior;//$NON-NLS-1$
   }

   private IFigure getMarqueeFeedbackFigure()
   {
      if (marqueeRectangleFigure == null)
      {
         marqueeRectangleFigure = new MarqueeRectangleFigure();
         addFeedback(marqueeRectangleFigure);
      }
      return marqueeRectangleFigure;
   }

   private Rectangle getMarqueeSelectionRectangle()
   {
      return new Rectangle(getStartLocation(), getLocation());
   }

   private int getSelectionMode()
   {
      return mode;
   }

   private Request getTargetRequest()
   {
      if (targetRequest == null)
         targetRequest = createTargetRequest();
      return targetRequest;
   }

   protected boolean handleButtonDown(int button)
   {
      if (!isGraphicalViewer())
         return true;
      if (button != 1)
      {
         setState(STATE_INVALID);
         handleInvalidInput();
      }
      if (stateTransition(STATE_INITIAL, STATE_DRAG_IN_PROGRESS))
      {
         if (getCurrentInput().isModKeyDown(SWT.MOD1))
            setSelectionMode(TOGGLE_MODE);
         else if (getCurrentInput().isShiftKeyDown())
            setSelectionMode(APPEND_MODE);
         else
            setSelectionMode(DEFAULT_MODE);
      }
      return true;
   }

   protected boolean handleButtonUp(int button)
   {
      if (stateTransition(STATE_DRAG_IN_PROGRESS, STATE_TERMINAL))
      {
         eraseTargetFeedback();
         eraseMarqueeFeedback();
         performMarqueeSelect();
      }
      handleFinished();
      return true;
   }

   protected boolean handleDragInProgress()
   {
      if (isInState(STATE_DRAG | STATE_DRAG_IN_PROGRESS))
      {
         showMarqueeFeedback();
         eraseTargetFeedback();
         calculateNewSelection(selectedEditParts = new ArrayList(), new ArrayList());
         showTargetFeedback();
      }
      return true;
   }

   protected boolean handleFocusLost()
   {
      if (isInState(STATE_DRAG | STATE_DRAG_IN_PROGRESS))
      {
         handleFinished();
         return true;
      }
      return false;
   }

   protected boolean handleInvalidInput()
   {
      eraseTargetFeedback();
      eraseMarqueeFeedback();
      return true;
   }

   protected boolean handleKeyDown(KeyEvent e)
   {
      if (super.handleKeyDown(e))
         return true;
      if (getCurrentViewer().getKeyHandler() != null)
         return getCurrentViewer().getKeyHandler().keyPressed(e);
      return false;
   }

   private boolean isGraphicalViewer()
   {
      return getCurrentViewer() instanceof GraphicalViewer;
   }

   protected boolean isViewerImportant(EditPartViewer viewer)
   {
      return viewer instanceof GraphicalViewer;
   }

   private void performMarqueeSelect()
   {
      EditPartViewer viewer = getCurrentViewer();
      Collection newSelections = new LinkedHashSet(), deselections = new HashSet();
      calculateNewSelection(newSelections, deselections);
      if (getSelectionMode() != DEFAULT_MODE)
      {
         newSelections.addAll(viewer.getSelectedEditParts());
         newSelections.removeAll(deselections);
      }
      viewer.setSelection(new StructuredSelection(newSelections.toArray()));
   }

   public void setMarqueeBehavior(int type)
   {
      if (type != BEHAVIOR_CONNECTIONS_TOUCHED && type != BEHAVIOR_NODES_CONTAINED
            && type != BEHAVIOR_NODES_AND_CONNECTIONS)
         throw new IllegalArgumentException("Invalid marquee behaviour specified."); //$NON-NLS-1$
      marqueeBehavior = type;
   }

   private void setSelectionMode(int mode)
   {
      this.mode = mode;
   }

   public void setViewer(EditPartViewer viewer)
   {
      if (viewer == getCurrentViewer())
         return;
      super.setViewer(viewer);
      if (viewer instanceof GraphicalViewer)
         setDefaultCursor(SharedCursors.CROSS);
      else
         setDefaultCursor(SharedCursors.NO);
   }

   public void mouseDown(MouseEvent me, EditPartViewer viewer)
   {
      super.mouseDown(me, viewer);
      startLocation = getStartLocation().getCopy();
      getMarqueeFeedbackFigure().translateToRelative(startLocation);
   }

   private void showMarqueeFeedback()
   {
      Rectangle rect = getMarqueeSelectionRectangle().getCopy();
      getMarqueeFeedbackFigure().translateToRelative(rect);
      Point mouseLocation = getLocation();
      getMarqueeFeedbackFigure().translateToRelative(mouseLocation);
      int width = mouseLocation.x - startLocation.x;
      int height = mouseLocation.y - startLocation.y;
      int x = width < 0 ? mouseLocation.x : startLocation.x;
      int y = height < 0 ? mouseLocation.y : startLocation.y;
      rect.setLocation(new Point(x, y));
      rect.setSize(Math.abs(width), Math.abs(height));
      getMarqueeFeedbackFigure().setBounds(rect);
      marqueeRectAbsolute = rect;
   }

   private void showTargetFeedback()
   {
      for (Iterator itr = selectedEditParts.iterator(); itr.hasNext();)
      {
         EditPart editPart = (EditPart) itr.next();
         editPart.showTargetFeedback(getTargetRequest());
      }
   }

   class MarqueeRectangleFigure extends Figure
   {

      private static final int DELAY = 110; // animation delay in millisecond

      private int offset = 0;

      private boolean schedulePaint = true;

      /**
       * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
       */
      protected void paintFigure(Graphics graphics)
      {
         Rectangle bounds = getBounds().getCopy();
         graphics.translate(getLocation());

         graphics.setXORMode(true);
         graphics.setForegroundColor(ColorConstants.white);
         graphics.setBackgroundColor(ColorConstants.black);

         graphics.setLineStyle(Graphics.LINE_DOT);

         int[] points = new int[6];

         points[0] = 0 + offset;
         points[1] = 0;
         points[2] = bounds.width - 1;
         points[3] = 0;
         points[4] = bounds.width - 1;
         points[5] = bounds.height - 1;

         graphics.drawPolyline(points);

         points[0] = 0;
         points[1] = 0 + offset;
         points[2] = 0;
         points[3] = bounds.height - 1;
         points[4] = bounds.width - 1;
         points[5] = bounds.height - 1;

         graphics.drawPolyline(points);

         graphics.translate(getLocation().getNegated());

         if (schedulePaint)
         {
            Display.getCurrent().timerExec(DELAY, new Runnable()
            {
               public void run()
               {
                  offset++;
                  if (offset > 5)
                     offset = 0;

                  schedulePaint = true;
                  repaint();
               }
            });
         }

         schedulePaint = false;
      }

   }

}
