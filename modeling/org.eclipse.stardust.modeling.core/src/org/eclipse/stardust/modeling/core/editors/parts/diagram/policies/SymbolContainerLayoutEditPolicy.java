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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.NodeCreationFactory;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.ActivitySymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SymbolGroupEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.ChangeConstraintCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CompoundDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateModelElementCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.IDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveNodeSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveSymbolCommandUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetSymbolContainerCommand;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.stardust.modeling.core.utils.SnapGridUtils;
import org.eclipse.ui.PlatformUI;


/**
 * @author rsauer
 * @version $Revision$
 */
public class SymbolContainerLayoutEditPolicy extends XYLayoutEditPolicy
{
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;   
   private Request request = null;
   
   // we need the request for the mouse location
   protected Command getAddCommand(Request generic) 
   {
      request = generic;
      Command command = super.getAddCommand(generic);
      request = null;
      return command;
   }

   // we need the request for the mouse location
   private Request getRequest()
   {
      return request;
   }
   
   protected Command createAddCommand(EditPart child, Object constraint)
   {
      return createChangeConstraintCommand(child, constraint);
   }

   protected Command createChangeConstraintCommand(ChangeBoundsRequest request,
         EditPart child, Object constraint)
   {
      List changedParts = (request instanceof ProxyChangeBoundsRequest)
            ? ((ProxyChangeBoundsRequest) request).getOther().getEditParts()
            : request.getEditParts();
      return doCreateChangeConstraintCommand(request, child, changedParts,
            constraint);
   }

   protected Command createChangeConstraintCommand(EditPart target, Object constraint)
   {
      return doCreateChangeConstraintCommand((ChangeBoundsRequest) getRequest(), target, Collections
            .singletonList(target), constraint);
   }

   protected EditPolicy createChildEditPolicy(EditPart child)
   {
      return (child instanceof AbstractNodeSymbolEditPart)
            ? new NodeSymbolResizableEditPolicy()
            : super.createChildEditPolicy(child);
   }

   // command for change bounds request
   // will also do all pool lane operations
   protected Command doCreateChangeConstraintCommand(ChangeBoundsRequest request, final EditPart target,
         List changedParts, Object constraint)
   {
      Command result = UnexecutableCommand.INSTANCE;
      Object type = ((ChangeBoundsRequest) request).getType();
      // set resize direction flags
      int resizeDirection;
      if(type.equals(REQ_RESIZE_CHILDREN))
      {
         resizeDirection = request.getResizeDirection();
         PoolLaneUtils.setResizeFlags(resizeDirection);
      }
      else
      {
         PoolLaneUtils.setResizeFlags(-1);         
      }
      // can't resize symbolgroup
      if(type.equals(REQ_RESIZE_CHILDREN) && target instanceof SymbolGroupEditPart)         
      {
         return result;
      }
      
      // absolute mouse location
      Point mouseLocation = new Point(0, 0);
      if(request.getLocation() != null)
      {
         mouseLocation = request.getLocation().getCopy();         
      }
      Point viewPortPoint = PoolLaneUtils.findViewportPoint((GraphicalEditPart) target); 
      mouseLocation.performTranslate(viewPortPoint.x, viewPortPoint.y);
      
      // new code accepting collisions
      boolean isColliding = MoveSymbolCommandUtils.isSymbolCollision(changedParts,
            getHost(), target, constraint);

      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) target.getModel());      
      if(diagram == null)
      {
         return result;
      }
      OrientationType direction = diagram.getOrientation();
      
      // collision: diagram (or process) must be locked, if model is shared
      Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser((EObject) diagram);
      if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
      {
         return result;         
      }      
      
      // BPMN Mode on Process Diagram
      if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
            && DiagramUtil.getDefaultPool(diagram) != null)
      {
         // no colliding when this is a Lane (will be rearranged later)
         if(target instanceof LaneEditPart)
         {
            isColliding = false;
         }
         else
         {
            // when colliding it should not be possible to move a symbol
            if(isColliding)
            {
               return result;
            }
         }         
      }

      // default Pool should not be moved, only resized
      if ((REQ_MOVE_CHILDREN.equals(type) || REQ_MOVE.equals(type)
            || REQ_ALIGN_CHILDREN.equals(type) || REQ_ALIGN.equals(type))
            && target instanceof PoolEditPart
            && DiagramUtil.isDefaultPool(((PoolEditPart) target).getPoolModel()))
      {
         return result;
      }
      
      final Rectangle boundsRect = ((Rectangle) constraint).getCopy();
      if (REQ_RESIZE_CHILDREN.equals(type) && target instanceof LaneEditPart)
      {
         LaneEditPart laneEP = (LaneEditPart) target;
         LaneFigure laneFigure = (LaneFigure) laneEP.getFigure();
         // collapse lanes can't be resized
         if(laneFigure.isCollapsed())
         {
            return result;
         }  
         // in BPMN Mode we can decrease a Lane (we have lanes only in a process diagram)
         if(!diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL))
         {         
            setLaneBoundsToMinSize(boundsRect, (LaneEditPart) target, !isColliding);
         }
      }
      
      // a lane cannot be placed on the diagram
      if(getHost() instanceof DiagramEditPart && target instanceof LaneEditPart)
      {
         return result;
      }
      // when moving a lane we use the mouse location as target location
      if(target instanceof LaneEditPart
            && (!REQ_RESIZE_CHILDREN.equals(type)))
      {   
         mouseLocation = PoolLaneUtils.getLocation((GraphicalEditPart) target.getParent(), 
               ((AbstractSwimlaneEditPart) getHost()).getSwimlaneFigure(), mouseLocation, false);
      }
      if(isColliding)
      {
         return result;
      }
      
      ChangeConstraintCommand cmd = new ChangeConstraintCommand();
      cmd.setTarget(target);
      cmd.setHost(getHost());
      
      cmd.setColliding(isColliding);    
      cmd.setPart((INodeSymbol) target.getModel());
      if (REQ_MOVE_CHILDREN.equals(type) || REQ_MOVE.equals(type)
            || REQ_ALIGN_CHILDREN.equals(type) || REQ_ALIGN.equals(type))
      {
         // BPMN Mode on Process Diagram, use mouse location for lane move
         if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
               && DiagramUtil.getDefaultPool(diagram) != null
               && target instanceof LaneEditPart)
         {
            cmd.setLocation(mouseLocation);         
         }
         else
         {
            cmd.setLocation(boundsRect.getLocation().getCopy());            
         }
      }
      else
      {
         // ADD CHILDREN, RESIZE
         cmd.setBounds((boundsRect));
      }
      // BPMN Mode on Process Diagram
      if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
            && DiagramUtil.getDefaultPool(diagram) != null)
      {
         if(target instanceof AbstractSwimlaneEditPart)
         {      
            // no snap to grid for lanes
            cmd.setSnapToGrid(false);
         }
      }
      result = cmd;
      // if not swimlane, we are finished
      if(!(target instanceof AbstractSwimlaneEditPart))
      {
         // here we may resize the container
         if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
               && DiagramUtil.getDefaultPool(diagram) != null)
         {
            final EditPart host = getHost();            
            // do not move a symbol into the diagram
            if(host instanceof DiagramEditPart)
            {
               return UnexecutableCommand.INSTANCE;
            }            
            if(host instanceof AbstractSwimlaneEditPart)
            {
               result = result.chain(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.resizeLane((AbstractSwimlaneEditPart) host);
                  }
               });               
               // here children of siblings must be ordered (if there are any)    
               result = result.chain(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.reorderSiblings(host, null);
                  }
               });
            }
         }
         return result;
      }   
      // for old models
      if(!diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
            && DiagramUtil.getDefaultPool(diagram) != null)
      {
         return result;
      }
      // size of the current lane
      Rectangle targetRectangle = GenericUtils.getSymbolRectangle(target);
      // decrease a single lane inside a container must decrease all other lanes in this container, 
      // otherwise we take the biggest lane in the container for all other lanes in this container
      // this depends on orientation
      final Integer changeChildLanes;
      // can we resize a container with collapsed lanes
      boolean canChange = true;
      
      // special case if new container has no lanes but symbols
      // the size must be at least the size of the container or bigger if a bigger lane is inserted
      // OR
      // special case if container is empty and bigger than new lane, then the lane must grow
      Rectangle containerBounds = GenericUtils.getSymbolRectangle(getHost());
      if(REQ_ADD.equals(type) 
            && (PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) getHost())
            || !PoolLaneUtils.containsLanes((AbstractSwimlaneEditPart) getHost())))
      {
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {                        
            if(containerBounds.height > boundsRect.height)
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_CONTAINER_SIZE);            
            }                
            else
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE);            
            }
         }
         else
         {
            if(containerBounds.width > boundsRect.width)
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_CONTAINER_SIZE);            
            }         
            else
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE);            
            }
         }  
      }
      else
      {
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {                        
            if(targetRectangle.width != boundsRect.width)
            {
               canChange = false;
            }         
            if(targetRectangle.height > boundsRect.height)
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_MINSIZE);            
            }                
            else
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE);            
            }
         }
         else
         {
            if(targetRectangle.height != boundsRect.height)
            {
               canChange = false;
            }         
            if(targetRectangle.width > boundsRect.width)
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_MINSIZE);            
            }         
            else
            {
               changeChildLanes = new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE);            
            }
         }          
      }
      // BPMN Mode on Process Diagram
      if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
            && DiagramUtil.getDefaultPool(diagram) != null)
      {
         if(REQ_RESIZE_CHILDREN.equals(type))
         {
            // cannot resize container with only collapsed lanes 
            // direction depends on orientation (flag canChange)
            if(!PoolLaneUtils.canChange(target) && !canChange)
            {
               return UnexecutableCommand.INSTANCE;
            }            
         }
         if (REQ_MOVE_CHILDREN.equals(type) || REQ_MOVE.equals(type)
               || REQ_ALIGN_CHILDREN.equals(type) || REQ_ALIGN.equals(type))
         {
            // moving a Lane inside the same container
            if(target instanceof LaneEditPart && PoolLaneUtils.containsLanes(getHost()) 
                  && !PoolLaneUtils.isSensitiveArea((AbstractSwimlaneEditPart) getHost(), mouseLocation))
            {
               return UnexecutableCommand.INSTANCE;               
            }
         }  
         // targetRectangle has the old size
         int newWidthSpace = boundsRect.width - targetRectangle.width;
         int newHeightSpace = boundsRect.height - targetRectangle.height;
         // check for collision if this is an decrease resize
         if(REQ_RESIZE_CHILDREN.equals(type) 
               && (newWidthSpace < 0 || newHeightSpace < 0))
         {
            int[] newSpace = new int[] {newWidthSpace, newHeightSpace};    
            // no collision in any container
            EditPart poolEP = PoolLaneUtils.getPoolEditPart(target);
            if(PoolLaneUtils.hasCollision(poolEP, (AbstractSwimlaneEditPart) target, newSpace))
            {
               return UnexecutableCommand.INSTANCE;
            }
            // the lanes must have a minimum size
            poolEP = PoolLaneUtils.getPoolEditPart(target);
            if(PoolLaneUtils.laneTooSmall(poolEP, (AbstractSwimlaneEditPart) target, newSpace))
            {
               return UnexecutableCommand.INSTANCE;
            }
         }         
         // old size of container
         final int[] oldSize = new int[] {targetRectangle.width, targetRectangle.height};    
         // last step for default Pool (move to 0, 0) after resize
         final EditPart parentEP = target.getParent();
         if(target instanceof PoolEditPart)
         {
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.moveDefaultPool((PoolEditPart) target);
               }
            }); 
            // change all child lanes
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {                  
                  return PoolLaneUtils.resizeChildLanes(((AbstractSwimlaneEditPart) target), oldSize);
               }
            });
            // reorder lanes (if we have collapsed lanes inside)
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) target, changeChildLanes);
               }
            });
            // move children symbols            
            if(PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) target))
            {
               final int[] newSpace = new int[] {newWidthSpace, newHeightSpace};   
               result = result.chain(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.checkMoveChildren(((AbstractSwimlaneEditPart) target), newSpace);
                  }
               });               
            }
            return result;         
         }
         // resize
         if (REQ_RESIZE_CHILDREN.equals(type))
         {   
            // if lane contains symbols, the symbols may be moved
            if(PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) target))
            {            
               final int[] newSpace = new int[] {newWidthSpace, newHeightSpace};    
               result = result.chain(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.checkMoveChildren(((AbstractSwimlaneEditPart) target), newSpace);
                  }
               });
            }                                    
            // change child lanes
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.resizeChildLanes(((AbstractSwimlaneEditPart) target), oldSize);
               }
            });
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) parentEP, changeChildLanes);
               }
            });
            // here children of siblings must be ordered (if there are any) 
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.reorderSiblings(target, null);
               }
            });
         }
         else
         {
            // move lane           
            // move lane into new container and out of old one (ADD CHILDREN)
            Integer childLanesFlag = changeChildLanes;
            if(!parentEP.equals(getHost()))
            {
               // then we create a new lane - must have all current symbols after creation
               // 1st create the new lane, 2nd move the lane (current command)
               if(PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) getHost()))
               {
                  Command oldCommand = result;
                  // new Bounds for the ADD CHILDREN request
                  Rectangle moveBounds = boundsRect.getCopy();
                  // for the creation of the default lane
                  Point createLocation;    
                  // location of both lanes depends on where the mouse is
                  if (OrientationType.VERTICAL_LITERAL.equals(direction))
                  {
                     if(boundsRect.getLocation().x < containerBounds.width/2)
                     {
                        createLocation = new Point(containerBounds.width - 1, 0);
                        moveBounds.x = 0;
                     }
                     else
                     {
                        createLocation = new Point(0, 0);
                        moveBounds.x = containerBounds.width - 1;
                     }
                  }
                  else
                  {
                     if(boundsRect.getLocation().y < containerBounds.height/2)
                     {
                        createLocation = new Point(0, containerBounds.height - 1);
                        moveBounds.y = 0;
                     }
                     else
                     {
                        createLocation = new Point(0, 0);
                        moveBounds.y = containerBounds.height - 1;
                     }
                  }                           
                  if (OrientationType.VERTICAL_LITERAL.equals(direction))
                  {
                     if(containerBounds.height > moveBounds.height) 
                     { 
                        moveBounds.height = containerBounds.height; 
                     }
                  }
                  else
                  {
                     if(containerBounds.width > moveBounds.width)
                     { 
                        moveBounds.width = containerBounds.width; 
                     }
                  }
                  ((ChangeConstraintCommand) oldCommand).setBounds(moveBounds);
                  final Command moveCommand = oldCommand;
                  final CreateRequest creationRequest = new CreateRequest();
                  result = getCreateDefaultLaneCommand(creationRequest, createLocation);

                  result = result.chain(new DelegatingCommand()
                  {
                     public Command createDelegate()
                     { 
                        return moveCommand; 
                     }
                  });
               }
               childLanesFlag = new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE);
            }
            final EditPart host = getHost();
            final Integer useFlag = childLanesFlag;
            // reassign new container, size of biggest lane
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {                   
                  return PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) host, useFlag);
               }
            });             
            // here children of siblings must be ordered (if there are any)    
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.reorderSiblings(target, host);
               }
            });
            // move lane into new container and out of old one
            if(!parentEP.equals(getHost()))
            {
               // reassign old container
               result = result.chain(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) parentEP, new Integer(PoolLaneUtils.CHILD_LANES_SAME_SIZE));
                  }
               });            
            }
            // child lane may have grown
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.reorderChildLanes((AbstractSwimlaneEditPart) target, new Integer(PoolLaneUtils.CHILD_LANES_CONTAINER_SIZE));
               }               
            });
         }
      }      
      return result;
   }

   private void setLaneBoundsToMinSize(Rectangle bounds, LaneEditPart laneEditPart,
         boolean noCollision)
   {
      IFigure laneFigure = laneEditPart.getFigure();
      Rectangle laneBounds = laneFigure.getBounds();
      Insets laneBorder = laneFigure.getBorder().getInsets(laneFigure);

      long laneXPosRight = laneBounds.x + laneBounds.width;
      long lastSymbolXPosRight = laneBounds.x;
      long laneYPosBottom = laneBounds.y + laneBounds.height;
      long lastSymbolYPosBottom = laneBounds.y;

      boolean isXIntersection = false;
      boolean isYIntersection = false;

      boolean isLeftResize = bounds.getLocation().x > laneBounds.x;
      boolean isRightResize = (bounds.getLocation().x <= laneBounds.x)
            && (bounds.width < laneBounds.width);
      boolean isTopResize = bounds.getLocation().y > laneBounds.y;
      boolean isBottomResize = (bounds.getLocation().y <= laneBounds.y)
            && (bounds.height < laneBounds.height);
      Rectangle croppedBounds = bounds.getCropped(laneBorder);
      for (Iterator iter = laneEditPart.getChildren().iterator(); iter.hasNext();)
      {
         AbstractNodeEditPart childPart = (AbstractNodeEditPart) iter.next();
         Rectangle childBounds = childPart.getFigure().getBounds();
         Rectangle translatedRect = childBounds.getCopy().translate(
               croppedBounds.getLocation());

         if (!croppedBounds.contains(translatedRect))
         {
            if ((isLeftResize && ((translatedRect.x + translatedRect.width) > laneXPosRight))
                  || (isRightResize && ((translatedRect.x + translatedRect.width) < laneXPosRight)))
            {
               isXIntersection = true;
            }
            if ((isTopResize && ((translatedRect.y + translatedRect.height) > laneYPosBottom))
                  || (isBottomResize && ((translatedRect.y + translatedRect.height) < laneYPosBottom)))
            {
               isYIntersection = true;
            }

            noCollision = false;

         }
         long boundX = ((INodeSymbol) childPart.getModel()).getXPos() + childBounds.width;
         if (boundX > lastSymbolXPosRight && boundX < laneXPosRight)
         {
            lastSymbolXPosRight = boundX;
         }

         long boundY = ((INodeSymbol) childPart.getModel()).getYPos()
               + childBounds.height;
         if (boundY > lastSymbolYPosBottom && boundY < laneYPosBottom)
         {
            lastSymbolYPosBottom = boundY;
         }
      }

      int diffX = 0;
      int width = laneBounds.width - new Long(lastSymbolXPosRight).intValue()
            - (laneBorder.left + laneBorder.right);
      if (isLeftResize && (diffX == 0 || diffX > width))
      {
         diffX = width;
      }

      int diffY = 0;
      int height = laneBounds.height - new Long(lastSymbolYPosBottom).intValue()
            - (laneBorder.bottom + laneBorder.top);
      if (isTopResize && (diffY == 0 || diffY > height))
      {
         diffY = height;
      }

      if (!noCollision)
      {
         bounds.setLocation(isXIntersection ? (laneBounds.x + diffX) : bounds.x,
               isYIntersection ? (laneBounds.y + diffY) : bounds.y);
         bounds.setSize(isXIntersection ? new Long(lastSymbolXPosRight).intValue()
               + laneBorder.left + laneBorder.right : bounds.width, isYIntersection
               ? new Long(lastSymbolYPosBottom).intValue() + laneBorder.bottom
                     + laneBorder.top
               : bounds.height);
      }

      PrecisionRectangle rect = new PrecisionRectangle(bounds);
      PrecisionRectangle baseRect = rect.getPreciseCopy();
      PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);
      SnapToHelper snapToHelper = (SnapToHelper) getHost().getAdapter(SnapToHelper.class);
      if (snapToHelper != null)
      {
         ChangeBoundsRequest fakeRequest = new ChangeBoundsRequest();
         fakeRequest.setLocation(bounds.getLocation());
         Point moveDelta = new Point(0, 0);
         fakeRequest.setMoveDelta(moveDelta);
         fakeRequest.setType(REQ_RESIZE);
         fakeRequest.setEditParts(new ArrayList());
         snapToHelper.snapPoint(fakeRequest, PositionConstants.HORIZONTAL
               | PositionConstants.VERTICAL, new PrecisionRectangle[] {baseRect},
               preciseDelta);
         bounds.setLocation(preciseDelta.getTranslated(bounds.getLocation()));
      }
   }

   protected Command getCreateCommand(CreateRequest request)
   {
      // set resize flags
      PoolLaneUtils.setResizeFlags(-1);      
      CreateSymbolCommand createSymbolCommand = null;               
      Command requestCommand = (Command) request.getNewObject();
      if(requestCommand instanceof CompoundCommand)
      {
         List commands = ((CompoundCommand) requestCommand).getCommands();
         for(int i = 0; i < commands.size(); i++)
         {
            Command singleCommand = (Command) commands.get(i);
            if(singleCommand instanceof CreateSymbolCommand)
            {
               createSymbolCommand = (CreateSymbolCommand) singleCommand;
               break;                           
            }
         }
      }
      else if(requestCommand instanceof CreateSymbolCommand)
      {
         createSymbolCommand = (CreateSymbolCommand) requestCommand;
      }
      
      Command result = UnexecutableCommand.INSTANCE;
      
      if (createSymbolCommand == null)
      {
         return result;
      }
      
      DiagramType diagram = null;
      if(getHost().getModel() instanceof DiagramType)
      {
         diagram = (DiagramType) getHost().getModel();
      }
      else
      {                  
         diagram = ModelUtils.findContainingDiagram((IGraphicalObject) getHost().getModel());      
      }
      boolean isLane = false;
      EClass eClass = createSymbolCommand.getEClass();
      if(eClass.equals(PKG.getLaneSymbol()))
      {                     
         isLane = true;
      }      
      
      if (request.getNewObject() instanceof IDiagramCommand)
      {
         boolean mustCreatedefaultLane = false;         
         if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL) && isLane)
         {
            if(!(getHost().getModel() instanceof DiagramType))
            {
               // create default lane if container contains no lane but other symbols
               if(!(PoolLaneUtils.containsLanes(getHost())) 
                     && getHost().getChildren().size() > 0)
               {
                  mustCreatedefaultLane = true;            
               }
            }
         }         
         if (mustCreatedefaultLane)
         {
            result = getCreateDefaultLaneCommand(request, null);
         }
         final IDiagramCommand command = (IDiagramCommand) request.getNewObject();
         final Rectangle location = (Rectangle) getConstraintFor(request);
         command.setLocation(location);
         if (mustCreatedefaultLane)
         {
            result = result.chain((Command) command);
         }
         else
         {
            result = (Command) command;
         }
         // to have the middle of the mouse as the center of the new symbol after creation
         result = result.chain(getMoveSymbolToCenterCommand(
               (AbstractGraphicalEditPart) getHost(), command));
         if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL) && 
               getHost() instanceof AbstractSwimlaneEditPart)
         {
            if(isLane)
            {
               result = result.chain(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     // reorder 1st children, then resize parent, and then the same with the next parent
                     return PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) getHost(), new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE));
                  }
               });               
            }
            else
            {
               result = result.chain(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.resizeLane((AbstractSwimlaneEditPart) getHost());
                  }
               });
            }
            // here children of siblings must be ordered (if there are any)   
            result = result.chain(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.reorderSiblings(getHost(), null);
               }
            });
         }
      }
      return result;
   }

   // creates a default lane, the default lane must contain all symbols and connections
   // from the host after creation
   private Command getCreateDefaultLaneCommand(CreateRequest request, Point newLocation)
   {
      CompoundCommand compoundCommand = new CompoundCommand();
      CreationFactory factory = NodeCreationFactory.getLaneFactory();
      final IDiagramCommand command = (IDiagramCommand) factory.getNewObject();
      command.setParent((EObject) getHost().getModel());
      // compute size
      final Rectangle bounds;
      if(newLocation != null)
      {
         bounds = new Rectangle(newLocation, new Dimension(-1, -1));
      }
      else
      {
         bounds = (Rectangle) getConstraintFor(request);

      }
      // container
      final ISwimlaneSymbol container = (ISwimlaneSymbol) getHost().getModel();
      DiagramType diagram = null;
      if(container instanceof DiagramType)
      {
         diagram = (DiagramType) container;
      }
      else
      {                  
         diagram = ModelUtils.findContainingDiagram((IGraphicalObject) container);      
      }      
      OrientationType direction = diagram.getOrientation();
      
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();            
      AbstractSwimlaneEditPart abstractSwimlaneEP = (AbstractSwimlaneEditPart) editor.findEditPart(container);
      Rectangle containerBounds = GenericUtils.getSymbolRectangle(abstractSwimlaneEP);
      
      // if new location is not null we have already the new location
      if(newLocation == null)
      {
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {
            if(bounds.x < containerBounds.width/2)
            {
               bounds.x = 0;
            }
            else
            {
               bounds.x = containerBounds.width - 1;
            }
         }
         else
         {
            if(bounds.y < containerBounds.height/2)
            {
               bounds.y = 0;
            }
            else
            {
               bounds.y = containerBounds.height - 1;
            }
         }         
      }
      // sets only the location
      command.setLocation(bounds);
      compoundCommand.add((Command) command);
      
      // add symbols
      compoundCommand.add(new DelegatingCommand()
      {
         public Command createDelegate()
         {
            INodeSymbol symbol = getCreatedSymbol(command);
            AbstractGraphicalEditPart host = (AbstractGraphicalEditPart) getHost();
            return moveSymbolsToLane(host, getCreatedPart(host, symbol));
         }
      });
      /*
      compoundCommand.add(new DelegatingCommand()
      {
         public Command createDelegate()
         {
            INodeSymbol symbol = getCreatedSymbol(command);

            return moveConnectionsToLane(container, (LaneSymbol) symbol);
         }
      });
      */
      return (Command) compoundCommand;
   }

   private Command moveSymbolsToLane(AbstractGraphicalEditPart host, final AbstractNodeSymbolEditPart part)
   {
      CompoundCommand command = new CompoundCommand();
      List children = host.getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart child = (EditPart) children.get(i);
         if(!(child instanceof AbstractSwimlaneEditPart))
         {
            final INodeSymbol symbol = (INodeSymbol) child.getModel();
            if (null != symbol.eContainmentFeature())
            {
               CompoundCommand moveSymbolsCmd = new CompoundCommand();
               moveSymbolsCmd.add(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     SetSymbolContainerCommand cmd = new SetSymbolContainerCommand()
                     {
                        public boolean changePerformer()
                        {
                           return false;
                        }
                     };
                     cmd.setContainer((ISymbolContainer) part.getModel(), null);
                     cmd.setSymbol(symbol);
                     return cmd;
                  }
               });
               command.add(moveSymbolsCmd);
            }
            
         }
      }
      return command;
   }
   
   private Command getMoveSymbolToCenterCommand(final AbstractGraphicalEditPart host,
         final IDiagramCommand command)
   {
      return new DelegatingCommand()
      {
         public Command createDelegate()
         {
            CompoundCommand cmd = new CompoundCommand();
            INodeSymbol symbol = getCreatedSymbol(command);
            if (!(symbol instanceof ISwimlaneSymbol))
            {
               AbstractNodeSymbolEditPart part = getCreatedPart(host, symbol);
               // get size for new Symbol and set size
               Figure figure = (Figure) part.getFigure();               
               Dimension prefSize = figure.getPreferredSize();               
               Dimension size = SnapGridUtils.getSnapDimension(prefSize, host, 2, true);
               if(!(figure instanceof ActivitySymbolFigure))
               {
                  if(SnapGridUtils.getSnapToHelper(host) != null)
                  {
                     boolean change = false;
                     if(size.height < prefSize.height)
                     {
                        prefSize.height += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
                        change = true;
                     }
                     if(size.width < prefSize.width)
                     {
                        prefSize.width += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE;
                        change = true;
                     }
                     if(change)
                     {
                        size = SnapGridUtils.getSnapDimension(prefSize, host, 2, true);                        
                     }
                  }
               }
               
               Point newLocation = new Point(new Long(symbol.getXPos()).intValue()
                     - size.width / 2, new Long(symbol.getYPos()).intValue()
                     - size.height / 2);               
               // set only if snaptogrid is enabled?               
               if(SnapGridUtils.getSnapToHelper(host) != null)
               {
                  symbol.setWidth(size.width);
                  symbol.setHeight(size.height);                   
               }
               // new location if snaptogrid is enabled
               Point setLocation = SnapGridUtils.getSnapLocation(host, part, null, size, 
                     newLocation);    
               MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
               moveCommand.setPart(symbol);
               moveCommand.setLocation(setLocation);
               cmd.add(moveCommand);
            }
            else
            {
               AbstractNodeSymbolEditPart part = getCreatedPart(host, symbol);
               // get size for new Symbol and set size
               Dimension size = SnapGridUtils.getSnapDimension(
                     ((Rectangle) GenericUtils.getSymbolRectangle(part)).getSize(), 
                     host, 1, true);
               // set only if snaptogrid is enabled?               
               if(SnapGridUtils.getSnapToHelper(host) != null)
               {
                  symbol.setWidth(size.width);
                  symbol.setHeight(size.height);                   
               }
            }
            return cmd;
         }
      };
   }

   public static DelegatingCommand getSnapToGridCommand(
         final AbstractGraphicalEditPart host, final IDiagramCommand command,
         final Point location)
   {
      return new DelegatingCommand()
      {
         public Command createDelegate()
         {
            INodeSymbol symbol = getCreatedSymbol(command);
            AbstractNodeSymbolEditPart part = getCreatedPart(host, symbol);

            return getSnapToGridCommand(host, symbol, location, part);
         }
      };
   }

   public static Command getSnapToGridCommand(final AbstractGraphicalEditPart host,
         final INodeSymbol symbol, final Point location, AbstractNodeSymbolEditPart part)
   {
      IFigure figure = part.getFigure();
      PrecisionRectangle rect = new PrecisionRectangle(new Rectangle(location, figure
            .getPreferredSize()));
      Point oldLocation = rect.getLocation();
      figure.translateToAbsolute(rect);
      PrecisionRectangle baseRect = rect.getPreciseCopy();
      PrecisionPoint preciseDelta = new PrecisionPoint(0, 0);

      SnapToHelper snapToHelper = (SnapToHelper) host.getAdapter(SnapToHelper.class);
      if (snapToHelper != null)
      {
         ChangeBoundsRequest fakeRequest = new ChangeBoundsRequest();
         fakeRequest.setLocation(location);
         Point moveDelta = new Point(0, 0);
         fakeRequest.setMoveDelta(moveDelta);
         fakeRequest.setType(REQ_RESIZE);
         fakeRequest.setEditParts(new ArrayList());
         snapToHelper.snapPoint(fakeRequest, PositionConstants.HORIZONTAL
               | PositionConstants.VERTICAL, new PrecisionRectangle[] {baseRect},
               preciseDelta);
      }

      MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
      moveCommand.setPart(symbol);
      moveCommand.setLocation(preciseDelta.getTranslated(oldLocation));

      return moveCommand;
   }
   
   public static AbstractNodeSymbolEditPart getCreatedPart(
         AbstractGraphicalEditPart host, INodeSymbol symbol)
   {
      AbstractNodeSymbolEditPart symbolPart = null;
      for (Iterator i = host.getChildren().iterator(); i.hasNext();)
      {
         EditPart child = (EditPart) i.next();
         if (symbolPart == null && child instanceof AbstractNodeSymbolEditPart
               && symbol.equals(child.getModel()))
         {
            symbolPart = (AbstractNodeSymbolEditPart) child;
         }
      }
      if(symbolPart == null && host instanceof DiagramEditPart && !host.getChildren().isEmpty())
      {
         AbstractGraphicalEditPart realHost = (AbstractGraphicalEditPart) host.getChildren().get(0);
         symbolPart = null;
         for (Iterator i = realHost.getChildren().iterator(); i.hasNext();)
         {
            EditPart child = (EditPart) i.next();
            if (symbolPart == null && child instanceof AbstractNodeSymbolEditPart
                  && symbol.equals(child.getModel()))
            {
               symbolPart = (AbstractNodeSymbolEditPart) child;
            }
         }         
      }
      return symbolPart;
   }

   private static INodeSymbol getCreatedSymbol(IDiagramCommand command)
   {
      INodeSymbol symbol = null;
      if (command instanceof CreateSymbolCommand)
      {
         symbol = (INodeSymbol) ((CreateSymbolCommand) command).getModelElement();
      }
      else if (command instanceof CreateModelElementCommand)
      {
         IModelElement element = ((CreateModelElementCommand) command).getModelElement();
         if (element instanceof INodeSymbol)
         {
            symbol = (INodeSymbol) element;
         }
      }
      else if (command instanceof CompoundDiagramCommand)
      {
         for (Iterator i = ((CompoundDiagramCommand) command).getCommands().iterator(); i
               .hasNext();)
         {
            Command child = (Command) i.next();
            if (child instanceof CreateSymbolCommand)
            {
               symbol = (INodeSymbol) ((CreateSymbolCommand) child).getModelElement();
               break;
            }
         }
      }
      return symbol;
   }

   protected Command getDeleteDependantCommand(Request request)
   {
      return null;
   }

   protected IFigure createSizeOnDropFeedback(CreateRequest createRequest)
   {
      IFigure result;

      // TODO create custom drop feedback figure

      result = super.createSizeOnDropFeedback(createRequest);

      if (null != result)
      {
         addFeedback(result);
      }

      return result;
   }

   protected IFigure getFeedbackLayer()
   {
      return super.getFeedbackLayer();// getLayer(LayerConstants.SCALED_FEEDBACK_LAYER);
   }
}