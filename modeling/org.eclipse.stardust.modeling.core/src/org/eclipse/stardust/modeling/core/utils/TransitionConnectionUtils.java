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
package org.eclipse.stardust.modeling.core.utils;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.ActivitySymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.DiagramLayer;
import org.eclipse.stardust.modeling.core.editors.figures.GatewayFigure;
import org.eclipse.stardust.modeling.core.editors.figures.TransitionConnectionFigure;
import org.eclipse.stardust.modeling.core.editors.figures.anchors.TransitionConnectionAnchor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.ActivitySymbolNodeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.GatewaySymbolEditPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


public class TransitionConnectionUtils
{
   public static final int ADD_FIX_VALUE = 10;
   
   // check if we have a "loop" with gateways and return value to add
   synchronized public static Integer isTransitionWithGateways(Connection conn, 
         List savePositions, Point startPoint, Point endPoint, boolean[] isVertical, 
         boolean[] gatewaysFlag)
   {
      Point startPoint_ = startPoint;
      Point endPoint_ = endPoint;
      
      int gateways = 0;
      Integer newPosition = null;
      boolean isLoop = false;
      
      ConnectionAnchor startAnchor = conn.getSourceAnchor();   
      String startAnchorType = null;
      if(startAnchor instanceof TransitionConnectionAnchor)
      {
         startAnchorType = ((TransitionConnectionAnchor) startAnchor).getType();
      }         
      
      ConnectionAnchor endAnchor = conn.getTargetAnchor();
      String endAnchorType = null;
      if(endAnchor instanceof TransitionConnectionAnchor)
      {
         endAnchorType = ((TransitionConnectionAnchor) endAnchor).getType();
      }     
            
      if(startAnchor.getOwner() instanceof GatewayFigure 
            || endAnchor.getOwner() instanceof GatewayFigure)
      {
         if(startAnchor.getOwner() instanceof GatewayFigure)
         {
            gateways++;
            startPoint_ = getConnectionPoint(conn, startAnchor, true);
         }
         else
         {
            startAnchorType = null;
         }
         if(endAnchor.getOwner() instanceof GatewayFigure)
         {
            gateways++;
            endPoint_ = getConnectionPoint(conn, endAnchor, false);
         }
         else
         {
            endAnchorType = null;
         }   
         if(startPoint_ == null || endPoint_ == null)
         {
            return null;
         }
         
         isLoop = orderPoints(isVertical, startPoint_, endPoint_);
         if(isLoop)
         {
            String anchorType = null;
            if(gateways == 1)
            {
               if(startAnchorType != null)
               {
                  anchorType = startAnchorType;
                  gatewaysFlag[0] = true;
               }
               else
               {
                  anchorType = endAnchorType;
               }
            }
            else if(gateways == 2)
            {
               String compareAnchorType = null;
               if(!isVertical[0])
               {
                  if(startAnchorType.equals(TransitionConnectionAnchor.TOP)
                        || startAnchorType.equals(TransitionConnectionAnchor.BOTTOM))
                  {
                     anchorType = startAnchorType;
                     gatewaysFlag[0] = true;
                  }
                  if(endAnchorType.equals(TransitionConnectionAnchor.TOP)
                        || endAnchorType.equals(TransitionConnectionAnchor.BOTTOM))
                  {
                     compareAnchorType = endAnchorType;                     
                  }                                    
               }
               else
               {
                  if(startAnchorType.equals(TransitionConnectionAnchor.LEFT)
                        || startAnchorType.equals(TransitionConnectionAnchor.RIGHT))
                  {
                     anchorType = startAnchorType;
                     gatewaysFlag[0] = true;
                  }
                  if(endAnchorType.equals(TransitionConnectionAnchor.LEFT)
                        || endAnchorType.equals(TransitionConnectionAnchor.RIGHT))
                  {
                     compareAnchorType = endAnchorType;                     
                  }                  
               }
               if(anchorType != null || compareAnchorType != null)
               {
                  if(anchorType != null && compareAnchorType != null)
                  {
                     if(!anchorType.equals(compareAnchorType))
                     {
                        anchorType = null;
                     }                     
                  }
                  else
                  {
                     if(anchorType == null)
                     {
                        anchorType = compareAnchorType;
                     }
                  }                     
               }
               else
               {
                  if(compareAnchorType != null)
                  {
                     anchorType = compareAnchorType;
                  }
               }
            }
            if(anchorType != null)
            {
               boolean increase = true;
               if(isVertical[0])
               {
                  if(anchorType.equals(TransitionConnectionAnchor.LEFT))
                  {
                     increase = false;
                  }                    
               }
               else
               {
                  if(anchorType.equals(TransitionConnectionAnchor.TOP))
                  {
                     increase = false;
                  }                    
               }
               newPosition = TransitionConnectionUtils.getNewPosition(conn, 
                     isVertical, increase, startPoint_, endPoint_);
               if(newPosition != null)
               {
                  if(increase)
                  {
                     return new Integer(newPosition.intValue() + TransitionConnectionUtils.ADD_FIX_VALUE);
                     
                  }
                  else
                  {
                     return new Integer(newPosition.intValue() - TransitionConnectionUtils.ADD_FIX_VALUE);                     
                  }
               }
            }
         }
      }
      return newPosition;
   }

   synchronized public static boolean orderPoints(boolean[] isVertical, 
         Point startPoint_, Point endPoint_)
   {
      boolean isLoop = false;
      // reorder points if necessary
      if(startPoint_.x == endPoint_.x)
      {
         isLoop = true;
         isVertical[0] = true;
         if(startPoint_.y > endPoint_.y)
         {
            Point tmpPoint = new Point(startPoint_);
            startPoint_.setLocation(endPoint_.x, endPoint_.y);
            endPoint_.setLocation(tmpPoint);
         }            
      }
      else if(startPoint_.y == endPoint_.y)
      {
         isLoop = true;
         if(startPoint_.x > endPoint_.x)
         {
            Point tmpPoint = new Point(startPoint_);
            startPoint_.setLocation(endPoint_.x, endPoint_.y);
            endPoint_.setLocation(tmpPoint);
         }            
      }
      return isLoop;
   }
   
   // check all figures that are crossed by this connection and get new position
   synchronized public static Integer getNewPosition(Connection conn, 
         boolean[] isVertical, boolean increase, Point startPoint_, Point endPoint_)
   {
      Point connectionStartPoint = new Point(startPoint_);
      Point connectionEndPoint = new Point(endPoint_);
      // a fix value that we must add to our connection      
      boolean hasChanged = false;      
      ConnectionAnchor startAnchor = conn.getSourceAnchor();   
      IFigure startFigure = startAnchor.getOwner();
      IFigure parent = startFigure.getParent();
      if(parent instanceof DiagramLayer)
      {
         List children = ((DiagramLayer) parent).getChildren();
         for(int i = 0; i < children.size(); i++)
         {
            IFigure figure = (IFigure) children.get(i);   
            // check if figure crosses line
            if(isCrossing(figure, connectionStartPoint, connectionEndPoint, isVertical))
            {
               hasChanged = true;
               // bounds: x, y, width, height
               Rectangle figureBounds = figure.getBounds(); 
               // change x value
               if(isVertical[0])
               {
                  if(increase)
                  {
                     connectionStartPoint.x = figureBounds.x + figureBounds.width;
                     connectionEndPoint.x = figureBounds.x + figureBounds.width;
                  }
                  else
                  {
                     connectionStartPoint.x = figureBounds.x;
                     connectionEndPoint.x = figureBounds.x;                     
                  }                  
               }
               else
               {
                  if(increase)
                  {
                     connectionStartPoint.y = figureBounds.y + figureBounds.height;
                     connectionEndPoint.y = figureBounds.y + figureBounds.height;
                  }
                  else
                  {
                     connectionStartPoint.y = figureBounds.y;
                     connectionEndPoint.y = figureBounds.y;                     
                  }                  
               }
            }            
         }                     
      }
      
      if(hasChanged)
      {
         // change x value
         if(isVertical[0])
         {
            return new Integer(connectionStartPoint.x);
         }
         else
         {
            return new Integer(connectionStartPoint.y);
         }
      }
      return null;
   }

   // does the connection crosses this figure
   synchronized public static boolean isCrossing(IFigure figure, 
         Point connectionStartPoint, Point connectionEndPoint, boolean[] isVertical)
   {
      Rectangle figureBounds = figure.getBounds();
      // x == x
      if(isVertical[0])
      {
         if(figureBounds.x <= connectionStartPoint.x
               && (figureBounds.x + figureBounds.width) >= connectionStartPoint.x
               && figureBounds.y >= connectionStartPoint.y 
               && (figureBounds.y + figureBounds.height) <= connectionEndPoint.y)
         {
            return true;
         }
      }
      else
      {
         if(figureBounds.y <= connectionStartPoint.y
               && (figureBounds.y + figureBounds.height) >= connectionStartPoint.y
               && figureBounds.x >= connectionStartPoint.x 
               && (figureBounds.x + figureBounds.width) <= connectionEndPoint.x)
         {
            return true;
         }         
      }      
      return false;
   }
   
   // get the point on the gateway that is connected to the activity
   synchronized public static Point getConnectionPoint(Connection conn, ConnectionAnchor anchor, boolean isStart)
   {
      GatewayFigure gatewayFigure = (GatewayFigure) anchor.getOwner();
      GatewaySymbolEditPart editPart = gatewayFigure.getEditPart();
      WorkflowModelEditor editor = editPart.getEditor();
      
      GatewaySymbol gatewaySymbol = (GatewaySymbol) editPart.getModel();
      List<TransitionConnectionType> inTransitions = gatewaySymbol.getInTransitions();
      List<TransitionConnectionType> outTransitions = gatewaySymbol.getOutTransitions();
      if(isStart)
      {
         if(!inTransitions.isEmpty())
         {
            TransitionConnectionType connection = (TransitionConnectionType) inTransitions.get(0);
            IFlowObjectSymbol activitySymbol = connection.getSourceActivitySymbol();
            if(activitySymbol instanceof ActivitySymbolType)
            {
               ActivitySymbolNodeEditPart activityEditPart = (ActivitySymbolNodeEditPart) editor.findEditPart(activitySymbol);
               if (activityEditPart != null)
               {   
                  ActivitySymbolFigure activityFigure = (ActivitySymbolFigure) activityEditPart.getFigure();
                  return getPoint(conn, activityFigure, isStart);
               }
            }
         }
      }
      else
      {
         if(!outTransitions.isEmpty())
         {
            TransitionConnectionType connection = (TransitionConnectionType) outTransitions.get(0);
            IFlowObjectSymbol activitySymbol = connection.getTargetActivitySymbol();
            if(activitySymbol instanceof ActivitySymbolType)
            {
               ActivitySymbolNodeEditPart activityEditPart = (ActivitySymbolNodeEditPart) editor.findEditPart(activitySymbol);
               if (activityEditPart != null)
               {
                  ActivitySymbolFigure activityFigure = (ActivitySymbolFigure) activityEditPart.getFigure();
                  return getPoint(conn, activityFigure, isStart);
               }
            }
         }
      }      
      return null;
   }
   
   // get the point on the gateway that is connected to the activity
   synchronized public static Point getPoint(Connection conn, ActivitySymbolFigure activityFigure, boolean isStart)
   {
      ConnectionLayer layer = (ConnectionLayer) conn.getParent();
      List children = layer.getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         IFigure figure = (IFigure) children.get(i);   
         if(figure instanceof TransitionConnectionFigure
               && !figure.equals(conn))
         {
            ConnectionAnchor startAnchor = ((Connection) figure).getSourceAnchor();   
            ConnectionAnchor endAnchor = ((Connection) figure).getTargetAnchor();
            if(startAnchor != null && startAnchor.getOwner() != null
                  && startAnchor.getOwner().equals(activityFigure) && isStart)
            {
               return ((TransitionConnectionFigure) figure).getEnd();
            }
            else if(endAnchor != null && endAnchor.getOwner() != null
                  && endAnchor.getOwner().equals(activityFigure) && !isStart)
            {
               return ((TransitionConnectionFigure) figure).getStart();               
            }
         }
      }
      return null;      
   } 
   
   public static boolean showForkOnTraversal(TransitionConnectionType transitionConnection)
   {
      boolean showForkOnTraversal = PlatformUI.getPreferenceStore()
                        .getBoolean(BpmProjectNature.PREFERENCE_VIEW_FORK_ON_TRAVERSAL_MODE);
      if(!showForkOnTraversal) return false;   
      TransitionType transition = transitionConnection.getTransition();
      if(transition == null)
      {
         return false;
      }
      boolean transitionForkOnTraversal = transition.isForkOnTraversal();
      boolean viewHint = false;
      
      IFlowObjectSymbol sourceSymbol = transitionConnection.getSourceActivitySymbol();
      IFlowObjectSymbol targetSymbol = transitionConnection.getTargetActivitySymbol();
      if(sourceSymbol instanceof GatewaySymbol  && targetSymbol instanceof ActivitySymbolType)
      {
         FlowControlType type = ((GatewaySymbol) sourceSymbol).getFlowKind();
         ActivitySymbolType activitySymbol = ((GatewaySymbol) sourceSymbol).getActivitySymbol();
         ActivityType activity = activitySymbol.getActivity();         
         if(type == FlowControlType.SPLIT_LITERAL)
         {
            JoinSplitType splitType = activity.getSplit();
            if(splitType.equals(JoinSplitType.XOR_LITERAL))
            {
               viewHint = true;
            }
            else if(splitType.equals(JoinSplitType.AND_LITERAL))
            {
               return checkTransitions((GatewaySymbol) sourceSymbol, transitionConnection);
            }
         }         
      }
      else if(sourceSymbol instanceof ActivitySymbolType && targetSymbol instanceof ActivitySymbolType)
      {
         viewHint = true;
      }

      if(viewHint && transitionForkOnTraversal)
      {
         return true;
      }
      return false;
   }
   
   private static boolean checkTransitions(GatewaySymbol symbol, TransitionConnectionType transitionConnection)
   {
      int forkOnTraversalTrue = 0;
      int forkOnTraversalFalse = 0;      
      List transitions = symbol.getOutTransitions();
      int size = transitions.size();
      TransitionType firstTransition = null;
      for(Iterator iter = transitions.iterator(); iter.hasNext();)
      {
         TransitionConnectionType outTransitionConnection = (TransitionConnectionType) iter.next();
         TransitionType outTransition = outTransitionConnection.getTransition();
         if(outTransition == null)
         {
            return false;
         }         
         boolean transitionForkOnTraversal = outTransition.isForkOnTraversal();
         if(transitionForkOnTraversal)
         {
            forkOnTraversalTrue++;
         }
         else
         {
            forkOnTraversalFalse++;            
            if(firstTransition == null)
            {
               firstTransition = outTransition;
            }
         }         
      }

      IWorkbenchWindow workBenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();      
      IWorkbenchPage page = workBenchWindow.getActivePage();
      if(page != null)
      {
         IEditorPart editor = page.getActiveEditor();
         if(editor instanceof WorkflowModelEditor)
         {
            // now update all the other transition connections
            for(Iterator iter = transitions.iterator(); iter.hasNext();)
            {
               TransitionConnectionType outTransitionConnection = (TransitionConnectionType) iter.next();
               if(!outTransitionConnection.equals(transitionConnection))
               {
                  AbstractConnectionSymbolEditPart editPart = (AbstractConnectionSymbolEditPart) ((WorkflowModelEditor) editor).findEditPart(outTransitionConnection);
                  if(editPart == null)
                  {
                     break;
                  }
                  if(forkOnTraversalTrue == size)
                  {
                     ((TransitionConnectionFigure) editPart.getFigure()).updateForkOnTraversal(Boolean.TRUE);
                  }
                  else if(forkOnTraversalFalse == 1 && firstTransition.equals(outTransitionConnection.getTransition()))
                  {
                     ((TransitionConnectionFigure) editPart.getFigure()).updateForkOnTraversal(Boolean.FALSE);
                  }
                  else if(forkOnTraversalFalse > 1 && firstTransition.equals(outTransitionConnection.getTransition()))
                  {
                     ((TransitionConnectionFigure) editPart.getFigure()).updateForkOnTraversal(Boolean.FALSE);
                  }
                  else
                  {
                     ((TransitionConnectionFigure) editPart.getFigure()).updateForkOnTraversal(Boolean.TRUE);               
                  }
               }
            }
         }
      }
      
      if(forkOnTraversalTrue == size)
      {
         return true;
      }
      if(forkOnTraversalFalse == 1 && firstTransition.equals(transitionConnection.getTransition()))
      {
         return false;
      }
      if(forkOnTraversalFalse > 1 && firstTransition.equals(transitionConnection.getTransition()))
      {
         return false;
      }
      return true;
   }   
}