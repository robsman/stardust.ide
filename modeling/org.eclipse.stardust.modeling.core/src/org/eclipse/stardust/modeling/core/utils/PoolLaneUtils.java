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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractSwimlaneFigure;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.ChangeConstraintCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveNodeSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.ui.PlatformUI;


public class PoolLaneUtils extends PoolLaneHelper
{  
   public static Comparator LANESYMBOL_COMPARATOR = new LaneSymbolComparator();      
   
   // recursive command to resize all lanes
   public static CompoundCommand shrinkToFitAllContainer(AbstractSwimlaneEditPart editPart, 
         int[] currentSpace, List siblings, boolean childSibling, List changedContainer)
   {     
      // sibling or child of sibling
      boolean isSibling = false;
      if(!siblings.isEmpty() && siblings.contains(editPart))
      {
         isSibling = true;
      }      
      if(childSibling)
      {
         isSibling = true;         
      }
      CompoundCommand cmd = new CompoundCommand();
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) editPart.getModel());
      OrientationType direction = diagram.getOrientation();
      ISwimlaneSymbol symbol = editPart.getSwimlaneModel();
      Rectangle symbolBounds = GenericUtils.getSymbolRectangle(editPart);
      // useSpace needed for children
      int[] useSpace = new int[] {currentSpace[0], currentSpace[1], currentSpace[2], currentSpace[3]};
      if(isSibling || !containsSymbols(editPart))
      {
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {                                       
            useSpace[0] = 0;
            useSpace[2] = 0;            
         }
         else
         {
            useSpace[1] = 0;
            useSpace[3] = 0;                        
         }         
      }
      symbolBounds.width -= (useSpace[0] + useSpace[2]);
      symbolBounds.height -= (useSpace[1] + useSpace[3]);      

      MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
      moveCommand.setPart(symbol);
      // must get snap coordinaten here, otherwise the layout later is not correct
      symbolBounds = SnapGridUtils.getSnapRectangle(editPart.getParent(), editPart, symbolBounds.getSize(), symbolBounds.getLocation());
      
      moveCommand.setBounds(symbolBounds);
      cmd.add(moveCommand);
      
      // move child symbols
      if(containsOthers(editPart))
      {
         if(useSpace[0] > 0 || useSpace[1] > 0)
         {
            int[] resizeValue = new int[] {0, 0};
            if(useSpace[0] > 0 && useSpace[1] > 0)
            {
               resizeValue = new int[] {- useSpace[0], - useSpace[1]};                  
            }         
            else if(useSpace[0] > 0)
            {
               resizeValue = new int[] {- useSpace[0], 0};
            }                  
            else if(useSpace[1] > 0)
            {
               resizeValue = new int[] {0, - useSpace[1]};                  
            }
            cmd.add(PoolLaneUtils.moveAllChildren(editPart, resizeValue));
         }         
         changedContainer.add(editPart);
      }
      else if(containsLanes(editPart))
      {
         // iterate child lanes
         List children = editPart.getChildren();
         for(int i = 0; i < children.size(); i++)
         {
            LaneEditPart childEP = (LaneEditPart) children.get(i);
            cmd.add(shrinkToFitAllContainer(childEP, useSpace, siblings, isSibling, changedContainer));
         }
      }
      return cmd;
   }
   
   // shrink to fit command for shrink to fit action
   public static CompoundCommand shrinkToFit(final AbstractSwimlaneEditPart editPart)
   {
      EditPart poolEP = getPoolEditPart(editPart);
      CompoundCommand cmd = new CompoundCommand();
      List siblings = new ArrayList();
      if(!(editPart instanceof PoolEditPart))
      {
         EditPart parentEP = editPart.getParent();
         EditPart checkEP = editPart;
         while(!(parentEP instanceof DiagramEditPart))
         {
            List checkSiblings = PoolLaneUtils.getSiblings(parentEP, checkEP);
            if(!checkSiblings.isEmpty())
            {
               siblings.addAll(checkSiblings);                  
            }               
            checkEP = parentEP;
            parentEP = parentEP.getParent();
         }
      }      
      List changedContainer = new ArrayList();
      int[] currentSpace = new int[] {-1, -1, -1, -1};
      PoolLaneUtils.canShrink(editPart, currentSpace);
      cmd.add(shrinkToFitAllContainer((AbstractSwimlaneEditPart) poolEP, currentSpace, siblings, false, changedContainer));
      // reorder lanes
      if(!changedContainer.isEmpty())
      {
         for(int i = 0; i < changedContainer.size(); i++)
         {
            EditPart container = (EditPart) changedContainer.get(i);
            if(!(container instanceof PoolEditPart))
            {
               PoolLaneUtils.setResizeFlags(-1);
               Command changeContainer = PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) container.getParent(), new Integer(PoolLaneUtils.CHILD_LANES_SAME_SIZE));
               cmd.add(changeContainer);
            }
         }
      }
      return cmd;
   }   
   
   // reorder siblings
   public static CompoundCommand reorderSiblings(EditPart changedEP, EditPart useParentEP)
   {
      if(changedEP.getParent() == null && useParentEP == null)
      {
         return null;
      }
      CompoundCommand cmd = new CompoundCommand();
      // collect all siblings
      List siblings = new ArrayList();
      if(!(changedEP instanceof PoolEditPart))
      {
         EditPart parentEP;
         if(useParentEP == null)
         {
            parentEP = changedEP.getParent();
            
         }
         else
         {
            parentEP = useParentEP;            
         }
         EditPart checkEP = changedEP;
         while(!(parentEP instanceof DiagramEditPart))
         {
            List checkSiblings = PoolLaneUtils.getSiblings(parentEP, checkEP);
            if(!checkSiblings.isEmpty())
            {
               siblings.addAll(checkSiblings);                  
            }               
            checkEP = parentEP;
            parentEP = parentEP.getParent();
         }
      }
      if(siblings.size() > 0)
      {
         for(int i = 0; i < siblings.size(); i++)
         {
            final EditPart sibling = (EditPart) siblings.get(i);
            
            INodeSymbol symbol = (INodeSymbol) sibling.getModel();
            final int[] oldSize = new int[] {symbol.getWidth(), 
                  symbol.getHeight()};    
            if(containsLanes(sibling))
            {
               cmd.add(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.resizeChildLanes(((AbstractSwimlaneEditPart) sibling), oldSize);
                  }
               });
               cmd.add(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return PoolLaneUtils.reorderChildLanes((AbstractSwimlaneEditPart) sibling, new Integer(PoolLaneUtils.CHILD_LANES_CONTAINER_SIZE));
                  }               
               });
            }  
         }
      }      
      return cmd;
   }   

   // when resizing from top or left all children must be moved
   public static CompoundCommand checkMoveChildren(EditPart editPart, int[] newInnerSpace)
   {
      CompoundCommand cmd = new CompoundCommand();
      if((resizeLeft || resizeTop) && (newInnerSpace[0] != 0 || newInnerSpace[1] != 0))
      {
         final int[] resizeValue;
         if(resizeLeft && resizeTop)
         {
            resizeValue = new int[] {newInnerSpace[0], newInnerSpace[1]};                  
         }         
         else if(resizeLeft)
         {
            resizeValue = new int[] {newInnerSpace[0], 0};
         }                  
         else if(resizeTop)
         {
            resizeValue = new int[] {0, newInnerSpace[1]};                  
         }
         else
         {
            return null;
         }
         cmd.add(PoolLaneUtils.moveAllChildren(editPart, resizeValue));
      }
      return cmd;
   }   
   
   // all child lanes must be resized if a parent container was changed
   // the container has already the new size, we can compare with the old size
   // child symbols may be moved
   public static CompoundCommand resizeChildLanes(final AbstractSwimlaneEditPart containerEP, 
         int[] oldSize)
   {
      CompoundCommand cmd = new CompoundCommand();
      INodeSymbol containerSymbol = (INodeSymbol) containerEP.getModel();
      int newWidthSpace = containerSymbol.getWidth() - oldSize[0];
      int newHeightSpace = containerSymbol.getHeight() - oldSize[1];
      
      
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) containerEP.getModel());
      OrientationType direction = diagram.getOrientation();      
      if(!containsLanes(containerEP))
      {
         return null;
      }
      
      int[] fixValue = new int[] {0};
      double onePercent = getOnePercent(containerEP, fixValue, null);
      List children = containerEP.getChildren();      
      // because of arithmetic with double values the sum is not always 100% of wholesize
      // we must fix this by fixing one lane in this iteration
      EditPart laneToFix = null;
      // if snap2grid is enabled and container is resized by only one grid (example)
      // it is possible that no child gets a new size by calculation
      // so resize children must be done in another way
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         if(editPart instanceof LaneEditPart && canChange(editPart))
         {
            laneToFix = editPart;
         }
      }
      
      if(newWidthSpace != 0 || newHeightSpace != 0)
      {
         int changedSum = 0;
         int snapGridLeftValue = 0;
         if(SnapGridUtils.getSnapToHelper(containerEP) != null)
         {
            for(int i = 0; i < children.size(); i++)
            {
               final EditPart editPart = (EditPart) children.get(i);
               if(editPart instanceof LaneEditPart)
               {
                  INodeSymbol symbol = (INodeSymbol) editPart.getModel();            
                  int[] newInnerSpace = new int[] {0, 0};
                  // add value to fix the calculation
                  if(editPart.equals(laneToFix) && fixValue[0] != 0)
                  {
                     if (OrientationType.VERTICAL_LITERAL.equals(direction))
                     {                                             
                        newInnerSpace = getNewChildLaneSize(editPart, 
                              new int[] {newWidthSpace + fixValue[0], newHeightSpace}, onePercent);
                     }
                     else
                     {
                        newInnerSpace = getNewChildLaneSize(editPart, 
                              new int[] {newWidthSpace, newHeightSpace + fixValue[0]}, onePercent);
                     }                        
                  }
                  else
                  {
                     newInnerSpace = getNewChildLaneSize(editPart, 
                           new int[] {newWidthSpace, newHeightSpace}, onePercent);               
                  }
                  Rectangle symbolRectangle = new Rectangle(new Long(symbol.getXPos()).intValue(), 
                        new Long(symbol.getYPos()).intValue(), 
                        newInnerSpace[0] + symbol.getWidth(), newInnerSpace[1] + symbol.getHeight());
                  Rectangle snapRectangle = SnapGridUtils.getSnapRectangle(containerEP, editPart, symbolRectangle.getSize(), symbolRectangle.getLocation());   
                  if (OrientationType.VERTICAL_LITERAL.equals(direction))
                  {                                             
                     changedSum += snapRectangle.width - symbol.getWidth();
                  }
                  else
                  {
                     changedSum += snapRectangle.height - symbol.getHeight();
                  }                        
               }
            }
            
            if (OrientationType.VERTICAL_LITERAL.equals(direction))
            {                                             
               if(changedSum != newWidthSpace)
               {
                  snapGridLeftValue = newWidthSpace - changedSum;
               }
            }
            else
            {
               if(changedSum != newHeightSpace)               
               {
                  snapGridLeftValue = newHeightSpace - changedSum;
               }
            }                        
         }
         
         for(int i = 0; i < children.size(); i++)
         {
            final EditPart editPart = (EditPart) children.get(i);
            if(editPart instanceof LaneEditPart)
            {
               final INodeSymbol symbol = (INodeSymbol) editPart.getModel();            
               final int[] newInnerSpace;
               // add value to fix the calculation
               if(editPart.equals(laneToFix) && (fixValue[0] != 0 || snapGridLeftValue != 0))
               {
                  if (OrientationType.VERTICAL_LITERAL.equals(direction))
                  {                                             
                     newInnerSpace = getNewChildLaneSize(editPart, 
                           new int[] {newWidthSpace + fixValue[0], newHeightSpace}, onePercent);
                     newInnerSpace[0] += snapGridLeftValue;
                  }
                  else
                  {
                     newInnerSpace = getNewChildLaneSize(editPart, 
                           new int[] {newWidthSpace, newHeightSpace + fixValue[0]}, onePercent);
                     newInnerSpace[1] += snapGridLeftValue;
                  }                        
               }
               else
               {
                  newInnerSpace = getNewChildLaneSize(editPart, 
                        new int[] {newWidthSpace, newHeightSpace}, onePercent);               
               }
               
               Rectangle symbolRectangle = new Rectangle(new Long(symbol.getXPos()).intValue(), 
                     new Long(symbol.getYPos()).intValue(), 
                     newInnerSpace[0] + symbol.getWidth(), newInnerSpace[1] + symbol.getHeight());
               final Rectangle snapRectangle = SnapGridUtils.getSnapRectangle(containerEP, editPart, symbolRectangle.getSize(), symbolRectangle.getLocation());   
               
               cmd.add(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {            
                     MoveNodeSymbolCommand changeCmd = new MoveNodeSymbolCommand();
                     changeCmd.setPart((INodeSymbol) symbol);
                     changeCmd.setBounds(snapRectangle);
                     return changeCmd;
                  }
               });
               cmd.add(new DelegatingCommand()
               {
                  public Command createDelegate()
                  {
                     return checkMoveChildren(editPart, newInnerSpace);
                  }
               });
            }
         }
      }
      final int[] newSize = new int[] {0, 0}; 
      cmd.add(new DelegatingCommand()
      {
         public Command createDelegate()
         {
            return PoolLaneUtils.changeContainerLanes((AbstractSwimlaneEditPart) containerEP, newSize, new Integer(CHILD_LANES_CONTAINER_SIZE), false);
         }
      });
      // next children
      for(int i = 0; i < children.size(); i++)
      {
         final EditPart editPart = (EditPart) children.get(i);
         if(editPart instanceof LaneEditPart)
         {
            INodeSymbol symbol = (INodeSymbol) editPart.getModel();
            final int[] innerOldSize = new int[] {symbol.getWidth(), symbol.getHeight()}; 
            cmd.add(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  return PoolLaneUtils.resizeChildLanes(((AbstractSwimlaneEditPart) editPart), innerOldSize);
               }               
            });
         }
      }
      return cmd;
   }
   
   // resize single lane and reorder children (command is called when symbols in lane change)
   public static CompoundCommand resizeLane(final AbstractSwimlaneEditPart containerEP)
   {
      boolean mustResizeContainer = false;
      CompoundCommand cmd = new CompoundCommand();
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) containerEP.getModel());
      OrientationType direction = diagram.getOrientation();      
      
      List children = containerEP.getChildren();
      final INodeSymbol containerSymbol = (INodeSymbol) containerEP.getModel();
      Insets insets = ((AbstractSwimlaneFigure) containerEP.getFigure()).getAbstractSwimlaneFigureInsets();         
      Rectangle drawingArea = null;
      Rectangle defaultDrawingArea = containerEP.getFigure().getBounds().getCopy();
      
      int[] resizeValue = new int[] {0, 0};    
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         INodeSymbol symbol = (INodeSymbol) editPart.getModel();
         if(symbol.getXPos() < 0 || symbol.getYPos() < 0)
         {
            if(symbol.getXPos() < 0)
            {
               resizeValue[0] = - new Long(symbol.getXPos()).intValue();
            }
            if(symbol.getYPos() < 0)
            {
               resizeValue[1] = - new Long(symbol.getYPos()).intValue();
               if (OrientationType.VERTICAL_LITERAL.equals(direction))
               {
                  resizeValue[1] += insets.bottom * 2;
               }
               else
               {
                  resizeValue[1] += insets.getHeight();
               }                        
            }
            break;
         }
      }
      if(resizeValue[0] != 0 || resizeValue[1] != 0)
      {
         boolean[] addFixValue = new boolean[] {false, false};
         if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) containerEP) != null)
         {
            getSnapGridResizeValue(containerEP, resizeValue, addFixValue);
         }
         final int[] innerResizeValue = new int[] {resizeValue[0], resizeValue[1]};   
         mustResizeContainer = true;
         cmd.add(new DelegatingCommand()
         {
            public Command createDelegate()
            {
               return PoolLaneUtils.moveAllChildren(containerEP, innerResizeValue);
            }
         });
         // new size of container, if addFixValue is set, the container may be too small after snap2grid to keep all symbols
         defaultDrawingArea.width += resizeValue[0] + (addFixValue[0] ? SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2 : 0);
         defaultDrawingArea.height += resizeValue[1] + (addFixValue[1] ? SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2 : 0);
         
         
         // the resized lane must get the correct size for snap2grid already
         Dimension currentDimension = new Dimension(defaultDrawingArea.width, defaultDrawingArea.height);
         Point currentLocation = new Point(defaultDrawingArea.x, defaultDrawingArea.y);
         defaultDrawingArea = SnapGridUtils.getSnapRectangle(containerEP.getParent(), containerEP, 
               currentDimension, currentLocation);
         
         final Rectangle newBounds = new Rectangle(defaultDrawingArea);      
         cmd.add(new DelegatingCommand()
         {
            public Command createDelegate()
            {
               MoveNodeSymbolCommand cmd = new MoveNodeSymbolCommand();
               cmd.setPart(containerSymbol);
               cmd.setBounds(newBounds);               
               return cmd;
            }
         }); 
      }      
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         Rectangle symbolRectangle = GenericUtils.getSymbolRectangle(editPart);
         if (null != drawingArea)
         {
            drawingArea.union(symbolRectangle);
         }
         else
         {
            drawingArea = symbolRectangle;
         }
      }   
      if(drawingArea != null)
      {            
         if(resizeValue[0] != 0 || resizeValue[1] != 0)
         {
            drawingArea.width += resizeValue[0];
            drawingArea.height += resizeValue[1];
         }      
         
         drawingArea.width += drawingArea.x;
         drawingArea.height += drawingArea.y;
         drawingArea.x = defaultDrawingArea.x;
         drawingArea.y = defaultDrawingArea.y;
         // now we have the size of the container children bounds 
   
         // add insets
         drawingArea.width += insets.getWidth();
         drawingArea.height += insets.getHeight() + insets.bottom;
         
         if(defaultDrawingArea.width < drawingArea.width
               || defaultDrawingArea.height < drawingArea.height)
         {
            mustResizeContainer = true;
            // should not become smaller
            if(drawingArea.width < defaultDrawingArea.width)
            {
               drawingArea.width = defaultDrawingArea.width;
            }
            if(drawingArea.height < defaultDrawingArea.height)
            {
               drawingArea.height = defaultDrawingArea.height;                  
            }         
            // the resized lane must get the correct size for snap2grid already
            Dimension currentDimension = new Dimension(drawingArea.width, drawingArea.height);
            Point currentLocation = new Point(drawingArea.x, drawingArea.y);
            Rectangle snapRectangle = SnapGridUtils.getSnapRectangle(containerEP.getParent(), containerEP, 
                  currentDimension, currentLocation);
   
            // resize container
            final Rectangle newBounds = new Rectangle(snapRectangle);      
            cmd.add(new DelegatingCommand()
            {
               public Command createDelegate()
               {
                  MoveNodeSymbolCommand cmd = new MoveNodeSymbolCommand();
                  cmd.setPart(containerSymbol);
                  cmd.setBounds(newBounds);
                  
                  return cmd;
               }
            }); 
         }
      }
      if(mustResizeContainer)
      {
         if(containerEP.getParent() instanceof AbstractSwimlaneEditPart)
         {
            cmd.add(reorderLanes((AbstractSwimlaneEditPart) containerEP.getParent(), new Integer(CHILD_LANES_MAXSIZE)));
         }         
      }
      return cmd;
   }
   
   // reorder all children
   public static CompoundCommand reorderChildLanes(final AbstractSwimlaneEditPart containerEP, final Integer changeChildLanes)
   {
      CompoundCommand cmd = new CompoundCommand();      
      if(!containsLanes(containerEP))
      {
         return null;
      }
      cmd.add(new DelegatingCommand()
      {
         public Command createDelegate()
         {
            return PoolLaneUtils.changeContainerLanes((AbstractSwimlaneEditPart) containerEP, new int[] {0, 0}, changeChildLanes, true);
         }
      });
      
      List children = containerEP.getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         if(containsLanes(editPart))
         {
            cmd.add(reorderChildLanes((AbstractSwimlaneEditPart) editPart, changeChildLanes));
         }         
      } 
      return cmd;
   }
   
   // reorder 1st children, then resize parent, and next the same with the next parent
   public static CompoundCommand reorderLanes(AbstractSwimlaneEditPart containerEP, final Integer changeChildLanes)
   {
      CompoundCommand cmd = new CompoundCommand();
      // container of the changed lane
      EditPart parent = containerEP;
      while(parent != null && parent instanceof AbstractSwimlaneEditPart)
      {
         final int[] newSize = new int[] {0, 0};
         final EditPart useParent = parent;
         cmd.add((new DelegatingCommand()
         {
            public Command createDelegate()
            {
               CompoundCommand command = new CompoundCommand();
               // reorder children
               command.add(PoolLaneUtils.changeContainerLanes((AbstractSwimlaneEditPart) useParent, newSize, changeChildLanes, false));
                              
               final int[] newInnerSize = new int[] {newSize[0], newSize[1]};
               final EditPart useInnerParent = useParent;
               if(useInnerParent instanceof AbstractSwimlaneEditPart && (newInnerSize[0] != 0 || newInnerSize[1] != 0))
               {     
                  command.add((new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        // reorder parent container
                        return changeParentLane((AbstractSwimlaneEditPart) useInnerParent, newInnerSize);                        
                     }
                  }));
               }
               return command;
            }
         }));
         parent = parent.getParent();
      }
      return cmd;
   }
   
   // all lanes in a container must be reordered and the size may have changed
   public static CompoundCommand changeContainerLanes(AbstractSwimlaneEditPart containerEP, int[] newSize, 
         Integer changeChildLanes, boolean keepPosition)
   {
      CompoundCommand cmd = new CompoundCommand();
      
      Insets insets = ((AbstractSwimlaneFigure) containerEP.getFigure()).getAbstractSwimlaneFigureInsets();      
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) containerEP.getModel());
      if(diagram == null)
      {
         cmd.add(UnexecutableCommand.INSTANCE);
         return cmd;
      }
      
      OrientationType direction = diagram.getOrientation();
      
      INodeSymbol containerSymbol = (ISwimlaneSymbol) containerEP.getModel();
      Rectangle containerBounds = new Rectangle(new Long(containerSymbol.getXPos()).intValue(), 
               new Long(containerSymbol.getYPos()).intValue(), 
               containerSymbol.getWidth(), containerSymbol.getHeight());         
      Map areaMap = new HashMap();
      List areas = new ArrayList();
      
      // we must order the list
      List children = containerEP.getChildren();
      List lanes = new ArrayList();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         if(editPart instanceof LaneEditPart)
         {
            lanes.add(editPart);
         }
      }
      
      int maxSize = 0;      
      if(lanes.size() > 0)
      {      
         for(int i = 0; i < lanes.size(); i++)
         {
            LaneEditPart laneEP = (LaneEditPart) lanes.get(i);
            LaneFigure figure = laneEP.getLaneFigure();
            Rectangle symbolRectangle = GenericUtils.getSymbolRectangle(laneEP);
            if (OrientationType.VERTICAL_LITERAL.equals(direction))
            {
               if(maxSize == 0)
               {
                  maxSize = symbolRectangle.height;
               }
               else
               {
                  if(changeChildLanes.intValue() == CHILD_LANES_MINSIZE)
                  {
                     if(symbolRectangle.height < maxSize)
                     {
                        maxSize = symbolRectangle.height;
                     }                                          
                  }
                  else if(changeChildLanes.intValue() == CHILD_LANES_MAXSIZE)
                  {
                     if(symbolRectangle.height > maxSize)
                     {
                        maxSize = symbolRectangle.height;
                     }                     
                  }
               }
            }
            else
            {                  
               if(maxSize == 0)
               {
                  maxSize = symbolRectangle.width;
               }
               else
               {
                  if(changeChildLanes.intValue() == CHILD_LANES_MINSIZE)                     
                  {
                     if(symbolRectangle.width < maxSize)
                     {
                        maxSize = symbolRectangle.width;
                     }                     
                  }
                  else if(changeChildLanes.intValue() == CHILD_LANES_MAXSIZE)
                  {
                     if(symbolRectangle.width > maxSize)
                     {
                        maxSize = symbolRectangle.width;
                     }                     
                  }
               }
            }
            
            if(figure.isCollapsed())
            {
               if (OrientationType.VERTICAL_LITERAL.equals(direction))
               {
                  symbolRectangle.width = LaneFigure.getCollapsedSize(laneEP);
               }
               else
               {                  
                  symbolRectangle.height = LaneFigure.getCollapsedSize(laneEP);       
               }
            }            
            areaMap.put(symbolRectangle, laneEP);
            areas.add(symbolRectangle);
         }
      }
      else
      {
         newSize[0] = 0;            
         return null;
      }
            
      if(changeChildLanes.intValue() == CHILD_LANES_CONTAINER_SIZE)
      {
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {
            if(maxSize < containerBounds.height - 2 * getSpace() - insets.top)
            {
               maxSize = containerBounds.height - 2 * getSpace() - insets.top;
            }
            else if(maxSize > containerBounds.height - 2 * getSpace() - insets.top)
            {
               maxSize = containerBounds.height - 2 * getSpace() - insets.top;
            }            
            // fix value for correction
            if(SnapGridUtils.getSnapToHelper(containerEP) == null)
            {
               maxSize += 1;
            }         
         }
         else
         {
            if(maxSize < containerBounds.width - 2 * getSpace() - insets.left)
            {
               maxSize = containerBounds.width - 2 * getSpace() - insets.left;
            }
            else if(maxSize > containerBounds.width - 2 * getSpace() - insets.left)
            {
               maxSize = containerBounds.width - 2 * getSpace() - insets.left;
            }
            // fix value for correction
            if(SnapGridUtils.getSnapToHelper(containerEP) == null)
            {
               maxSize += 4;
            }         
         }
      }      
      // must fix value because of ration space vs insets
      if(getSpace() > 20)
      {
         maxSize = SnapGridUtils.getRoundSnapValue(maxSize, containerEP);                  
      }
      
      // depends on orientation
      int lastPosition = getSpace();  
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {
         lastPosition -= insets.getWidth()/2;
      }
      else
      {
         lastPosition -= insets.getHeight()/2;         
      }               
      ((RectangleComparator) RECT_COMPARATOR).setOrientation(direction);
      Collections.sort(areas, RECT_COMPARATOR);
      if(areas.size() > 0)
      {      
         for(int i = 0; i < areas.size(); i++)
         {
            Rectangle area = (Rectangle) areas.get(i);
            
            LaneEditPart laneEP = (LaneEditPart) areaMap.get(area);
            LaneFigure figure = laneEP.getLaneFigure();            
            LaneSymbol symbol = laneEP.getLaneModel();
            Rectangle symbolRectangle = area.getCopy();
            Rectangle oldSize = area.getCopy();
            if (OrientationType.VERTICAL_LITERAL.equals(direction))
            {            
               symbolRectangle.x = lastPosition;
               symbolRectangle.y = getSpace();
               if(getSpace() <= 15)
               {
                  symbolRectangle.y -= insets.bottom;
               }
               symbolRectangle.height = maxSize;
            }
            else
            {
               symbolRectangle.y = lastPosition;
               symbolRectangle.x = getSpace();
               if(getSpace() <= 15)
               {
                  symbolRectangle.x -= insets.right;
               }
               symbolRectangle.width = maxSize;
            }                          
            ChangeConstraintCommand changeCmd = new ChangeConstraintCommand();
            changeCmd.setSnapToGrid(false);            
            changeCmd.setTarget(laneEP);
            changeCmd.setHost(containerEP);
            changeCmd.setPart((INodeSymbol) symbol);
            
            if (OrientationType.VERTICAL_LITERAL.equals(direction))
            {            
               lastPosition += (symbolRectangle.width + getSpace());            
            }
            else
            {
               lastPosition += (symbolRectangle.height + getSpace());                           
            }
            if(figure.isCollapsed())
            {
               if (OrientationType.VERTICAL_LITERAL.equals(direction))
               {            
                  symbolRectangle.width = symbol.getWidth();
               }
               else
               {
                  symbolRectangle.height = symbol.getHeight();                  
               }               
            }
            // get values to snaptogrid
            Dimension currentDimension = new Dimension(symbolRectangle.width, symbolRectangle.height);
            Point currentLocation = new Point(symbolRectangle.x, symbolRectangle.y);
            symbolRectangle = SnapGridUtils.getSnapRectangle(containerEP, laneEP, currentDimension, currentLocation);
            
            // needed because there seems to be a strange bug somewhere resulting in wrong coordinates
            if(keepPosition)
            {
               symbolRectangle.setLocation(area.x, area.y);
            } 
            changeCmd.setBounds(symbolRectangle);
            cmd.add(changeCmd);                
            // move symbols if necessary
            int[] newSpace = new int[] {symbolRectangle.width - oldSize.width, 
                  symbolRectangle.height - oldSize.height};            
            if(newSpace[0] < 0 || newSpace[1] < 0 && PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) laneEP))
            {            
               cmd.add(PoolLaneUtils.checkMoveChildren(((AbstractSwimlaneEditPart) laneEP), newSpace));
            }
         }
      } 
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {
         lastPosition += insets.getWidth()/2;
      }
      else
      {
         lastPosition += insets.getHeight()/2;
      } 
      // if vertical - this is width for parent
      newSize[0] = lastPosition;
      // if vertical - this is height for parent
      newSize[1] = maxSize + 3 * getSpace();     
      if(getSpace() <= 10)
      {
         if(OrientationType.VERTICAL_LITERAL.equals(direction))
         {
            newSize[1] += getSpace();
         }
         else
         {
            newSize[1] += getSpace();
         }          
      }
      return cmd;
   }

   public static Command changeParentLane(AbstractSwimlaneEditPart part, int[] newSize)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) part.getModel());
      OrientationType direction = diagram.getOrientation();
      
      ISwimlaneSymbol symbol = (ISwimlaneSymbol) part.getModel();
      Rectangle symbolRectangle = new Rectangle(new Long(symbol.getXPos()).intValue(), 
            new Long(symbol.getYPos()).intValue(), 
            symbol.getWidth(), symbol.getHeight());
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {
         symbolRectangle.width = newSize[0];
         symbolRectangle.height = newSize[1];// + getSpace();                  
      }
      else
      {
         symbolRectangle.height = newSize[0];                  
         symbolRectangle.width = newSize[1];// + getSpace();
      }
      if(symbol instanceof PoolSymbol)
      {
         symbolRectangle.x = 0;
         symbolRectangle.y = 0;         
      }
      // seems to be correct already
      Rectangle snapRectangle = SnapGridUtils.getSnapRectangle(part.getParent(), part, 
            symbolRectangle.getSize(), symbolRectangle.getLocation());
      ChangeConstraintCommand changeCmd = new ChangeConstraintCommand();
      changeCmd.setSnapToGrid(false);
      changeCmd.setTarget(part);
      changeCmd.setHost(part.getParent());
      changeCmd.setPart((INodeSymbol) symbol);
      changeCmd.setBounds(snapRectangle);

      return changeCmd;
   }
   
   // UpdateProcessDiagram   
   public static Command updateProcessDiagram(ISwimlaneSymbol container)
   {
      CompoundCommand cmd = new CompoundCommand();
      
      ISymbolContainer diagram = (ISymbolContainer) container;
      while (!(diagram instanceof DiagramType)
            && diagram.eContainer() instanceof ISymbolContainer)
      {
         diagram = (ISymbolContainer) diagram.eContainer();
      }
      OrientationType direction = ((DiagramType) diagram).getOrientation();      
      
      int lastPosition = space;            
      // we must order the list
      List symbols = container.getChildLanes();
      List lanes = new ArrayList();
      for(int i = 0; i < symbols.size(); i++)
      {
         LaneSymbol lane = (LaneSymbol) symbols.get(i);   
         lanes.add(lane);
      }      
      
      ((LaneSymbolComparator) LANESYMBOL_COMPARATOR).setOrientation(direction);
      Collections.sort(lanes, LANESYMBOL_COMPARATOR);
      for(int i = 0; i < lanes.size(); i++)
      {
         LaneSymbol lane = (LaneSymbol) lanes.get(i);   
         Point location;
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {            
            location = new Point(lastPosition, space);
            lastPosition += (lane.getWidth() + space);                  
         }
         else
         {
            location = new Point(space, lastPosition);
            lastPosition += (lane.getHeight() + space);                  
         }
         MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
         moveCommand.setPart(lane);
         moveCommand.setLocation(location);
         cmd.add(moveCommand);         
         
         for (Iterator iter = lane.getNodes().valueListIterator(); iter.hasNext();)
         {
            Object node = iter.next();
            if(node instanceof INodeSymbol)
            {
               boolean execute = false;
               Point position = new Point(((INodeSymbol) node).getXPos(), ((INodeSymbol) node).getYPos());                  
               if(((INodeSymbol) node).getXPos() <= space)
               {
                  execute = true;
                  position.x = space;
               }
               if(((INodeSymbol) node).getYPos() <= space)
               {
                  execute = true;
                  position.y = space;
               }
               if(execute)
               {
                  MoveNodeSymbolCommand moveNodeCommand = new MoveNodeSymbolCommand();
                  moveNodeCommand.setPart((INodeSymbol) node);
                  moveNodeCommand.setLocation(position);
                  cmd.add(moveNodeCommand);                           
               }
            }            
         }
      }
      return cmd;
   }   
   
   // we may need to refresh the connections
   public static void refreshLaneContent()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      if(editor == null)
      {
         return;
      }
      DiagramType diagram = editor.getActiveDiagram();
      DiagramModeType actualMode = diagram.getMode();
      if(actualMode.equals(DiagramModeType.MODE_450_LITERAL))
      {
         PoolSymbol pool = DiagramUtil.getDefaultPool(diagram);
         if(pool != null)
         {
            List lanes = pool.getLanes();
            for(int i = 0; i < lanes.size(); i++)
            {
               LaneSymbol lane = (LaneSymbol) lanes.get(i);   
               LaneEditPart editPart = (LaneEditPart) editor.findEditPart(lane);
               editPart.refreshContent();
            }            
         }
      }
   }
   
   // depends on direction
   public static class LaneSymbolComparator implements Comparator
   {
      OrientationType direction = null;
      public void setOrientation(OrientationType direction)
      {
         this.direction = direction;
      }

      public int compare(Object o1, Object o2)
      {
         long lx = ((LaneSymbol) o1).getXPos();
         long ly = ((LaneSymbol) o1).getYPos();

         long rx = ((LaneSymbol) o2).getXPos();
         long ry = ((LaneSymbol) o2).getYPos();
         
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {
            if (lx != rx)
            {
               return lx < rx ? -1 : 1;
            }
         }
         else
         {
            if (ly != ry)
            {
               return ly < ry ? -1 : 1;
            }            
         }
         return 0;
      }
   }
}