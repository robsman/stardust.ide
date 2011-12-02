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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractSwimlaneFigure;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;
import org.eclipse.stardust.modeling.core.editors.figures.PoolFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.MoveNodeSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.tools.SnapCenterToGrid;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


public class PoolLaneHelper
{
   // flags for resize operations
   public static boolean resizeLeft = false;
   public static boolean resizeTop = false;
   
   public static Comparator RECT_COMPARATOR = new RectangleComparator();   
   // default space if snap2Grid is disabled
   public static int space = 10;
   
   // constants to define what shall happens when all lanes inside a container will be reordered
   // shall the size als change?
   public static final int CHILD_LANES_MINSIZE = 1;   
   public static final int CHILD_LANES_MAXSIZE = 2;   
   public static final int CHILD_LANES_CONTAINER_SIZE = 3;   
   public static final int CHILD_LANES_SAME_SIZE = 4;      

   // size between Lanes in BPMN Mode
   public static int getSpace()
   {
      int space = PoolLaneUtils.space;
      DiagramEditorPage diagramEditorPage = (DiagramEditorPage) ((WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getCurrentPage();      
      EditPart diagramEP = diagramEditorPage.findEditPart(diagramEditorPage.getDiagram());
      if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) diagramEP) != null)
      {
         space = SnapGridUtils.getNextSnapSize(space);
      }
      return space;
   }   
   
   // enable shrink to fit only if one of the container has symbols
   public static boolean containsSymbols(EditPart editPart)
   {
      if(PoolLaneUtils.containsLanes(editPart))
      {
         List children = editPart.getChildren();
         for( int i = 0; i < children.size(); i++)
         {
            EditPart childEP = (EditPart) children.get(i);
            if(childEP instanceof AbstractSwimlaneEditPart && containsSymbols(childEP))
            {
               return true;
            }
         }
      } 
      else if(PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) editPart))
      {
         return true;
      }
      return false;
   }   
   
   // check all children for available space
   public static void checkChildrenForShrinkToFit(int[] currentSpace, 
         AbstractSwimlaneEditPart swimLaneEP, List siblings, boolean childSibling,
         boolean[] fixGrid, Dimension dimension)
   {
      // sibling or child of sibling
      boolean isSibling = false;
      if(!siblings.isEmpty() && siblings.contains(swimLaneEP))
      {
         isSibling = true;         
      }      
      if(childSibling)
      {
         isSibling = true;         
      }
      // check size 
      if(!containsLanes(swimLaneEP) && !containsOthers(swimLaneEP))
      {
         Rectangle rect = GenericUtils.getSymbolRectangle(swimLaneEP);
         if(rect.width < dimension.width)
         {
            dimension.width = rect.width;
         }
         if(rect.height < dimension.height)
         {
            dimension.height = rect.height;
         }            
      }
      // here we must check symbol bounds
      if(containsOthers(swimLaneEP))
      {
         getSpaceForShrinkToFit(currentSpace, swimLaneEP, isSibling, fixGrid);         
      }
      else if(containsLanes(swimLaneEP))         
      {
         List children = swimLaneEP.getChildren();
         for(int i = 0; i < children.size(); i++)
         {
            LaneEditPart childEP = (LaneEditPart) children.get(i);
            checkChildrenForShrinkToFit(currentSpace, childEP, siblings, 
                  isSibling, fixGrid, dimension);
         }         
      }
   }
   
   // check all container if the target can be shrinked
   public static boolean canShrink(AbstractSwimlaneEditPart editPart, int[] currentSpace)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) editPart.getModel());
      OrientationType direction = diagram.getOrientation();      
      
      // collect all siblings
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
      // now start from pool and check space for shrink
      boolean[] fixGrid = new boolean[] {false, false};
      AbstractSwimlaneEditPart poolEP = (AbstractSwimlaneEditPart) getPoolEditPart(editPart);
      PoolFigure poolFigure = (PoolFigure) poolEP.getFigure();
      Insets insets = poolFigure.getInsets();
      Dimension dimension = new Dimension(GenericUtils.getSymbolRectangle(poolEP).getSize());
      checkChildrenForShrinkToFit(currentSpace, poolEP, siblings, false, fixGrid, dimension);  
      
      if(SnapGridUtils.getSnapToHelper(editPart) != null)
      {
         if(fixGrid[0])
         {
            currentSpace[0] -= SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;
         }
         if(fixGrid[1])
         {
            currentSpace[1] -= SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;            
         }                  
      }

      // depends on direction, resize not smaller that lanes will vanish
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {                
         if(currentSpace[1] >= currentSpace[3])
         {
            if(currentSpace[1] >= dimension.height)
            {
               currentSpace[1] = dimension.height - insets.getHeight();
               currentSpace[3] = -1;
            }
            else
            {
               dimension.height -= currentSpace[1];
               if(currentSpace[3] >= dimension.height)
               {
                  currentSpace[3] = dimension.height - insets.getHeight();
               }               
            }
         }
         else
         {
            if(currentSpace[3] >= dimension.height)
            {
               currentSpace[3] = dimension.height - insets.getHeight();
               currentSpace[1] = -1;
            }
            else
            {
               dimension.height -= currentSpace[3];
               if(currentSpace[1] >= dimension.height)
               {
                  currentSpace[1] = dimension.height - insets.getHeight();
               }               
            }            
         }
      }
      else
      {
         if(currentSpace[0] >= currentSpace[2])
         {
            if(currentSpace[0] >= dimension.width)
            {
               currentSpace[0] = dimension.width - insets.getWidth();
               currentSpace[2] = -1;
            }
            else
            {
               dimension.width -= currentSpace[0];
               if(currentSpace[2] >= dimension.width)
               {
                  currentSpace[2] = dimension.width - insets.getWidth();
               }               
            }
         }
         else
         {
            if(currentSpace[2] >= dimension.width)
            {
               currentSpace[2] = dimension.width - insets.getWidth();
               currentSpace[0] = -1;
            }
            else
            {
               dimension.width -= currentSpace[2];
               if(currentSpace[0] >= dimension.width)
               {
                  currentSpace[0] = dimension.width - insets.getWidth();
               }
            }         
         }
      }
      
      if(SnapGridUtils.getSnapToHelper(editPart) != null)
      {
         if(currentSpace[0] > SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE 
               || currentSpace[1] > SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE
               || currentSpace[2] > SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE 
               || currentSpace[3] > SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE)
         {
            return true;            
         }         
      }
      else if(currentSpace[0] > 0 || currentSpace[1] > 0
            || currentSpace[2] > 0 || currentSpace[3] > 0)
      {
         return true;
      }
      return false;
   }
   
   // check space for shrink to fit (checks only lanes with symbols)
   // check only childlanes in all directions
   // leave some space 
   public static void getSpaceForShrinkToFit(int[] currentSpace, EditPart laneEP, boolean isSibling, 
         boolean[] fixGrid)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) laneEP.getModel());
      // occurs when deleting more than one lane a time (multiple selection)
      if(diagram == null)
      {
         return;
      }
      OrientationType direction = diagram.getOrientation();
      
      Rectangle drawingArea = null;
      List<AbstractGraphicalEditPart> children = laneEP.getChildren();
      Rectangle parentBounds = GenericUtils.getSymbolRectangle(laneEP);
      Insets insets = ((AbstractSwimlaneFigure) ((GraphicalEditPart) laneEP).getFigure()).getAbstractSwimlaneFigureInsets();         
              
      for(int i = 0; i < children.size(); i++)
      {
         Figure childFigure = (Figure) children.get(i).getFigure();
         if (null != drawingArea)
         {
            drawingArea.union(childFigure.getBounds());
         }
         else
         {
            drawingArea = childFigure.getBounds().getCopy();
         }
      }
      // space to have some margins
      int left = drawingArea.x;
      int top = drawingArea.y - insets.bottom * 2;
      int right = parentBounds.width - (drawingArea.width + drawingArea.x) - insets.getWidth();
      int bottom = parentBounds.height - (drawingArea.height + drawingArea.y) - insets.getHeight() - insets.bottom;
      if(left < 0)
      {
         left = 0;
      }
      if(top < 0)
      {
         top = 0;
      }
      if(right < 0)
      {
         right = 0;
      }
      if(bottom < 0)
      {
         bottom = 0;      
      }
      
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {               
         if((left < currentSpace[0] || currentSpace[0] == -1) && !isSibling)
         {
            currentSpace[0] = left;
         }
         if(top < currentSpace[1] || currentSpace[1] == -1)
         {
            currentSpace[1] = top;
         }
         if((right < currentSpace[2] || currentSpace[2] == -1) && !isSibling)
         {
            currentSpace[2] = right;
         }
         if(bottom < currentSpace[3] || currentSpace[3] == -1)
         {
            currentSpace[3] = bottom;
         }
      }
      else
      {
         if(left < currentSpace[0] || currentSpace[0] == -1)
         {
            currentSpace[0] = left;
         }
         if((top < currentSpace[1] || currentSpace[1] == -1) && !isSibling)
         {
            currentSpace[1] = top;
         }
         if(right < currentSpace[2] || currentSpace[2] == -1)
         {
            currentSpace[2] = right;
         }
         if((bottom < currentSpace[3] || currentSpace[3] == -1) && !isSibling)
         {
            currentSpace[3] = bottom;
         }         
      }
      
      // if snap2grid is enabled, check if move children would snap to a negative value
      if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) laneEP) != null)
      {
         checkAllChildrenForMove(laneEP, currentSpace, fixGrid);
      }      
   }   
   
   // method to set the flags for resize directions
   // direction indicates what selection handle is selected
   public static void setResizeFlags(int directions)
   {
      if(directions == -1)
      {
         resizeLeft = false;
         resizeTop = false;
         return;
      }      
      if (directions == PositionConstants.SOUTH_WEST)
      {
         resizeLeft = true;
         resizeTop = false;           
      } 
      else if (directions == PositionConstants.WEST)
      {
         resizeLeft = true;
         resizeTop = false;
      } 
      else if (directions == PositionConstants.NORTH_WEST)
      {
         resizeLeft = true;
         resizeTop = true;
      } 
      else if (directions == PositionConstants.NORTH)
      {
         resizeLeft = false;
         resizeTop = true;           
      } 
      else if (directions == PositionConstants.NORTH_EAST)
      {
         resizeLeft = false;
         resizeTop = true;           
      }  
      else
      {
         resizeLeft = false;
         resizeTop = false;         
      }
   }
   
   public static Rectangle checkPoolSize(DiagramEditPart part)
   {
      Rectangle drawingArea = null;
      Rectangle newSize = null;
      PoolEditPart poolEP = part.getPoolDelegate();
      if(poolEP == null)
      {
         return newSize;
      }
      
      INodeSymbol poolSymbol = (INodeSymbol) poolEP.getModel();
      Rectangle poolRectangle = new Rectangle(0, 0, 
            poolSymbol.getWidth(), poolSymbol.getHeight());
      if(poolRectangle.width == -1 || poolRectangle.height == -1)
      {
         DiagramType diagram = (DiagramType) part.getModel();
         OrientationType direction = diagram.getOrientation();
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {                                       
            poolRectangle.setSize(PoolFigure.getDimension(true));
         }
         else
         {
            poolRectangle.setSize(PoolFigure.getDimension(false));            
         }
      }
      
      List children = poolEP.getChildren();
      if(children.size() == 0)
      {
         return newSize;
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
      drawingArea.width += drawingArea.x;
      drawingArea.height += drawingArea.y;
      drawingArea.x = 0;
      drawingArea.y = 0;
      // now we have the size of the container children bounds 

      // add insets
      Insets insets = ((AbstractSwimlaneFigure) poolEP.getFigure()).getAbstractSwimlaneFigureInsets();         
      drawingArea.width += insets.getWidth();
      drawingArea.height += insets.getHeight();
      if(poolRectangle.width < drawingArea.width
            || poolRectangle.height < drawingArea.height)
      {
         // should not become smaller
         if(drawingArea.width < poolRectangle.width)
         {
            drawingArea.width = poolRectangle.width;
         }
         if(drawingArea.height < poolRectangle.height)
         {
            drawingArea.height = poolRectangle.height;                  
         }               
      }
      newSize = new Rectangle(drawingArea);
      return newSize;
   }
   
   // get viewport 
   public static Point findViewportPoint(GraphicalEditPart part)
   {
      IFigure figure = part.getFigure();
      Viewport port = null;
      while(figure != null)
      {
         if (figure instanceof Viewport)
         {
            port = (Viewport) figure;
            break;
         }
         figure = figure.getParent();
      }
      Point view = null;
      if(port != null)
      {
         view = port.getViewLocation();
      }
      return view;      
   }
   
   // get pool editpart
   public static EditPart getPoolEditPart(EditPart targetEP)
   {
      EditPart poolEP = targetEP;
      if(poolEP instanceof PoolEditPart)
      {
         return poolEP;
      }      
      if(poolEP instanceof DiagramEditPart)
      {
         return ((DiagramEditPart) poolEP).getPoolDelegate();
      }      
      while(!(poolEP instanceof PoolEditPart)
            || !(poolEP instanceof DiagramEditPart))
      {         
         poolEP = poolEP.getParent();
         if(poolEP instanceof PoolEditPart)
         {
            return poolEP;
         }      
         if(poolEP instanceof DiagramEditPart)
         {
            return ((DiagramEditPart) poolEP).getPoolDelegate();
         }               
      }        
      return null;
   }
   
   // find the real target EditPart from the given mouse location
   public static EditPart findTargetEditPart(WorkflowModelEditor editor)
   {
      IEditorPart part = editor.getCurrentPage();
      if(!(part instanceof DiagramEditorPage))
      {
         return null;
      }
      DiagramEditorPage diagramEditorPage = (DiagramEditorPage) part;      
      if(diagramEditorPage != null && diagramEditorPage.getMouseLocation() != null)
      {
         Point location = diagramEditorPage.getMouseLocation().getCopy();         
         EditPartViewer editPartViewer = diagramEditorPage.getGraphicalViewer();
         EditPart editPart = editPartViewer.findObjectAt(location);   
         // we need the DiagramEP
         if(editPart instanceof DiagramRootEditPart)
         {
            editPart = (EditPart) ((DiagramRootEditPart) editPart).getChildren().get(0);
         }
         return editPart;
      }
      return null;
   }
   
   // get siblings, we must reorder the children of the siblings
   public static List getSiblings(EditPart parent, EditPart changedEP)
   {
      List siblings = new ArrayList();
      List children = parent.getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart childEP = (EditPart) children.get(i);
         if(!(childEP.getModel().equals(changedEP.getModel())) && childEP instanceof LaneEditPart)
         {
            siblings.add(childEP);      
         }
      }      
      return siblings;
   }   

   // does the lane contains symbols other than lanes
   public static boolean containsOthers(AbstractSwimlaneEditPart containerEP)
   {
      List children = ((AbstractSwimlaneEditPart) containerEP).getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         if(!(editPart instanceof LaneEditPart))
         {
            return true;
         }
      }
      return false;
   }   
   
   // does the container contains lanes
   public static boolean containsLanes(EditPart containerEP)
   {
      if(containerEP instanceof DiagramEditPart && ModelUtils.findContainingProcess((EObject) containerEP.getModel()) != null)
      {
         PoolSymbol pool = DiagramUtil.getDefaultPool((DiagramType) containerEP.getModel());
         if(pool.getChildLanes().size() > 0)
         {
            return true;                              
         }
         else
         {
            return false;
         }
      }      
      List children = ((AbstractSwimlaneEditPart) containerEP).getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         if(editPart instanceof LaneEditPart)
         {
            return true;
         }
      }
      return false;
   }

   // check for parent organizations
   public static boolean canMove(LaneEditPart hostEP, LaneEditPart laneEP)
   {
      IModelParticipant laneParticipant = ((ISwimlaneSymbol) laneEP.getModel()).getParticipant();
      List childParticipants = null;
      if(laneParticipant == null)
      {
         childParticipants = HierarchyUtils.getTopLevelChildParticipants((LaneSymbol) laneEP.getModel());
         if(childParticipants == null)
         {
            return true;            
         }
      }
      ModelType model = (ModelType) hostEP.getEditor().getModel();      

      IModelParticipant hostParticipant = null;         
      Map organizationTree = HierarchyUtils.createHierarchy(model); 
      AbstractSwimlaneEditPart parentEP = (AbstractSwimlaneEditPart) hostEP;
      IModelParticipant parentParticipant = null;   
      while(parentEP != null && !(parentEP instanceof PoolEditPart))
      {
         parentParticipant = ((ISwimlaneSymbol) parentEP.getModel()).getParticipant();
         if(parentParticipant != null)
         {
            hostParticipant = parentParticipant;   
            break;
         }
         parentEP = (AbstractSwimlaneEditPart) parentEP.getParent();
      }    

      // no parent participant
      if(hostParticipant == null)
      {
         return true;
      }    
      // a role can have no children
      if(!(hostParticipant instanceof OrganizationType))
      {
         return false;
      }         
      
      List organizationChildren = HierarchyUtils.getChildHierarchy(organizationTree, (OrganizationType) hostParticipant);
      List participants = new ArrayList();
      if(organizationChildren != null)
      {
         if(HierarchyUtils.getParticipants(organizationChildren) != null)
         {
            participants = HierarchyUtils.getParticipants(organizationChildren);               
         }
      }
      if(laneParticipant != null)
      {
         if(participants.contains(laneParticipant))
         {
            return true;
         }         
      }
      else
      {
         if(participants.containsAll(childParticipants))
         {
            return true;
         }                  
      }
      return false;
   }   
   
   // pool must be always at 0, 0
   public static Command moveDefaultPool(PoolEditPart target)
   {
      MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
      moveCommand.setPart((INodeSymbol) target.getModel());            
      moveCommand.setLocation(new Point(0, 0));
      
      return moveCommand;
   }   
   
   // we must check that after snap2grid no symbol has a position below 0
   public static void getSnapGridResizeValue(EditPart parentEP, int[] resizeValue, boolean[] addFixValue)
   {
      List children = parentEP.getChildren();
      int correctionX = 0;
      int correctionY = 0;      
      for(int i = 0; i < children.size(); i++)
      {
         EditPart childEP = (EditPart) children.get(i);
         INodeSymbol childSymbol = (INodeSymbol) childEP.getModel();
         Point location = new Point(new Long(childSymbol.getXPos()).intValue() + resizeValue[0],
               new Long(childSymbol.getYPos()).intValue() + resizeValue[1]);
         // symbols must snap2grid
         Point newLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) parentEP, (AbstractNodeSymbolEditPart) childEP, null, null, location);
         if(newLocation.x < 0 && newLocation.x < correctionX)
         {
            correctionX = newLocation.x;
         }
         if(newLocation.y < 0 && newLocation.y < correctionY)
         {
            correctionY = newLocation.y;
         }         
      }   
      // if a symbol has a position below 0, we must fix the value by finding the next grid
      if(SnapGridUtils.getSnapToHelper((AbstractGraphicalEditPart) parentEP) != null)
      {
         correctionX = - correctionX;
         correctionY = - correctionY;
         if(correctionX != 0 && correctionX < SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2)
         {
            correctionX += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;
         }
         if(correctionY != 0 && correctionY < SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2)
         {
            correctionY += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;
         }         
      }    
      resizeValue[0] += correctionX;
      resizeValue[1] += correctionY;  
      // if one symbol is out of container, we must also make the container bigger, so we must add a value      
      Rectangle parentBounds = GenericUtils.getSymbolRectangle(parentEP);
      for(int i = 0; i < children.size(); i++)
      {
         EditPart childEP = (EditPart) children.get(i);
         INodeSymbol childSymbol = (INodeSymbol) childEP.getModel();
         Point location = new Point(new Long(childSymbol.getXPos()).intValue() + resizeValue[0],
               new Long(childSymbol.getYPos()).intValue() + resizeValue[1]);
         // symbols must snap2grid
         Point newLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) parentEP, (AbstractNodeSymbolEditPart) childEP, null, null, location);
         // here we check for a fix value if container is too small to keep all symbols after snap2grid
         if(newLocation.x + childSymbol.getWidth() > parentBounds.width && addFixValue[0] == false)
         {
            addFixValue[0] = true;
         }
         if(newLocation.y + childSymbol.getHeight() > parentBounds.height && addFixValue[1] == false)
         {
            addFixValue[1] = true;
         }
      }
   }
   
   // when snap2grid and we move symbols inside a container it may be that 
   // a symbol snaps to have a negative value, must be fixed by adding some space
   public static void checkAllChildrenForMove(EditPart parentEP, int[] resizeValue, 
         boolean[] result)
   {
      List children = parentEP.getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart childEP = (EditPart) children.get(i);
         INodeSymbol childSymbol = (INodeSymbol) childEP.getModel();
         Point location = new Point(new Long(childSymbol.getXPos()).intValue() - resizeValue[0],
               new Long(childSymbol.getYPos()).intValue() - resizeValue[1]);
         Point newLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) parentEP, (AbstractNodeSymbolEditPart) childEP, null, null, location);
         // adding grid/2 to the container
         if(newLocation.x <= 0 || newLocation.y <= 0)
         {
            if(newLocation.x <= 0)
            {
               result[0] = true;
            }
            if(newLocation.y <= 0)
            {
               result[1] = true;
            }
         }
      }      
   }   
   
   // check if we must move the child symbols
   public static void checkForNegativePositions(PoolSymbol pool, int[] moveValue)
   {
      for (Iterator iter = pool.getNodes().valueListIterator(); iter.hasNext();)
      {
         INodeSymbol symbol = (INodeSymbol) iter.next();
         int x = new Long(symbol.getXPos()).intValue();
         int y = new Long(symbol.getYPos()).intValue();
         if(x < 0)
         {
            if(moveValue[0] == 0 || moveValue[0] > x)
            {
               moveValue[0] = x;
            }
         }
         if(y < 0)
         {
            if(moveValue[1] == 0 || moveValue[1] > y)
            {
               moveValue[1] = y;
            }            
         }         
      }
   }
   
   // move all children by the given value
   public static CompoundCommand moveAllChildren(EditPart parentEP, int[] resizeValue)
   {
      CompoundCommand cmd = new CompoundCommand();
      List children = parentEP.getChildren();
      for(int i = 0; i < children.size(); i++)
      {
         EditPart childEP = (EditPart) children.get(i);
         INodeSymbol childSymbol = (INodeSymbol) childEP.getModel();
         Point location = new Point(new Long(childSymbol.getXPos()).intValue() + resizeValue[0],
               new Long(childSymbol.getYPos()).intValue() + resizeValue[1]);
         Point newLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) parentEP, (AbstractNodeSymbolEditPart) childEP, null, null, location);
         /*
         if(newLocation.x <= 0 || newLocation.y <= 0)
         {
            if(newLocation.x <= 0)
            {
               location.x += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;
            }
            if(newLocation.y <= 0)
            {
               location.y += SnapCenterToGrid.CARNOT_DEFAULT_GRID_SIZE/2;               
            }
            newLocation = SnapGridUtils.getSnapLocation((AbstractGraphicalEditPart) parentEP, (AbstractNodeSymbolEditPart) childEP, null, null, location);
         }
         */
         if(newLocation.x >= 0 && newLocation.y >= 0)
         {
            MoveNodeSymbolCommand moveCommand = new MoveNodeSymbolCommand();
            moveCommand.setPart(childSymbol);
            moveCommand.setLocation(newLocation);
            cmd.add(moveCommand);
         }
         else
         {
            cmd.add(UnexecutableCommand.INSTANCE);
         }
      }      
      return cmd;
   }   
   
   // get one percent of the whole size of all expanded children
   // get the value to fix the last editpart
   public static double getOnePercent(AbstractSwimlaneEditPart containerEP, int[] fixValue, 
         AbstractSwimlaneEditPart laneEP)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) containerEP.getModel());
      OrientationType direction = diagram.getOrientation();      
      
      double onePercent = 0;
      int wholeSize = 0;
      List children = containerEP.getChildren();      
      // collapsed lanes should be resized only in one direction
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         boolean checkLane = true;
         // for collision and resize checks
         if(laneEP != null)
         {
            checkLane = checkContainerForResize(editPart, laneEP);
         }         
         INodeSymbol symbol = (INodeSymbol) editPart.getModel();
         if(canChange(editPart) && checkLane)
         {
            if (OrientationType.VERTICAL_LITERAL.equals(direction))
            {                                 
               wholeSize += symbol.getWidth();
            }
            else
            {
               wholeSize += symbol.getHeight();            
            }         
         }
      }      
      onePercent = wholeSize/(double) 100;
      // check for fix value 
      // (because of arithmetic with double values the sum is not always 100% of wholesize)
      int checkWholeSize = 0;
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         INodeSymbol symbol = (INodeSymbol) editPart.getModel();
         double factor;
         if(canChange(editPart))
         {
            if (OrientationType.VERTICAL_LITERAL.equals(direction))
            {                                 
               factor = (symbol.getWidth()/onePercent/100);
               checkWholeSize += wholeSize * factor;
            }
            else
            {
               factor = (symbol.getHeight()/onePercent/100);         
               checkWholeSize += wholeSize * factor;            
            }         
         }
      }      
      fixValue[0] = wholeSize - checkWholeSize;
      return onePercent;
   }

   // check if all children are collapsed or in an expanded child all childs are collapsed
   public static boolean canChange(EditPart parentEP)
   {
      List children = parentEP.getChildren(); 
      if(!containsLanes(parentEP))
      {
         if(parentEP instanceof LaneEditPart)
         {
            if(((LaneEditPart) parentEP).getLaneFigure().isCollapsed())
            {
               return false;
            }            
         }
         return true;
      }      
      // collapsed lanes should be resized only in one direction
      boolean containsExpanded = false;
      for(int i = 0; i < children.size(); i++)
      {
         EditPart editPart = (EditPart) children.get(i);
         if(!(((LaneEditPart) editPart).getLaneFigure().isCollapsed()))
         {
            containsExpanded = canChange(editPart); 
            if(containsExpanded)
            {
               break;
            }
         }            
      }
      if(!containsExpanded)
      {
         return false;
      }
      return true;
   }
   
   // get current size of lane for validations
   public static int[] getNewLaneSizeForValidation(EditPart targetEP, int[] newSpace, boolean resize)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) targetEP.getModel());
      OrientationType direction = diagram.getOrientation();
      
      INodeSymbol symbol = (INodeSymbol) targetEP.getModel();
      Insets insets = ((AbstractSwimlaneFigure) ((GraphicalEditPart) targetEP).getFigure()).getAbstractSwimlaneFigureInsets();         
      int newInnerWidth = 0;
      int newInnerHeight = 0;  
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {                                 
         if(canChange(targetEP) && resize)
         {
            newInnerWidth = symbol.getWidth() + newSpace[0];                  
         }
         else
         {
            newInnerWidth = symbol.getWidth();
         }
         newInnerHeight = symbol.getHeight() + newSpace[1];                  
      }
      else
      {
         newInnerWidth = symbol.getWidth() + newSpace[0];                  
         if(canChange(targetEP) && resize)
         {               
            newInnerHeight = symbol.getHeight() + newSpace[1];                                 
         }
         else
         {
            newInnerHeight = symbol.getHeight();
         }
      }
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {
         newInnerWidth -= insets.getWidth()/2;
         newInnerHeight -= insets.top;
      }
      else
      {
         newInnerWidth -= insets.left;
         newInnerHeight -= insets.getHeight()/2;
      } 
      int[] innerSpace = new int[] {newInnerWidth, newInnerHeight};
      return innerSpace;
   }

   // get new size of child lanes when resizing (used by checks)
   public static int[] getNewChildLaneSize(EditPart childEP, int[] newSpace, double onePercent)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) childEP.getModel());
      OrientationType direction = diagram.getOrientation();

      INodeSymbol symbol = (INodeSymbol) childEP.getModel();
      double factor;
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {                                 
         factor = (symbol.getWidth()/onePercent/100);
      }
      else
      {
         factor = (symbol.getHeight()/onePercent/100);         
      }
      
      int[] newInnerSpace = new int[] {0, 0};    
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {                                 
         newInnerSpace = new int[] {(int) (newSpace[0] * factor), newSpace[1]};
         if(!canChange(childEP))
         {
            newInnerSpace = new int[] {0, newSpace[1]};                  
         }
      }
      else
      {
         newInnerSpace = new int[] {newSpace[0], (int) (newSpace[1] * factor)};            
         if(!canChange(childEP))
         {
            newInnerSpace = new int[] {newSpace[0], 0};            
         }               
      }
      return newInnerSpace;
   }   

   // if checkEP is changedEP or child of changedEP return true
   public static boolean checkContainerForResize(EditPart checkEP, EditPart changedEP)
   {
      if(changedEP instanceof PoolEditPart)
      {
         return true;
      }
      if(checkEP.equals(changedEP))
      {
         return true;
      }
      if(containsLanes(changedEP))
      {
         List children = changedEP.getChildren();
         for(int i = 0; i < children.size(); i++)
         {
            EditPart childEP = (EditPart) children.get(i);
            if(checkContainerForResize(checkEP, childEP))
            {
               return true;
            }
         }
      }
      return false;
   }
   
   // do we have a Collision in any Lane (with a symbol that is not a Lane)
   // newSpace -> newWidthSpace, newHeightSpace
   public static boolean hasCollision(EditPart targetEP, AbstractSwimlaneEditPart laneEP, int[] newSpace)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) targetEP.getModel());
      OrientationType direction = diagram.getOrientation();
      
      if(!PoolLaneUtils.containsLanes(targetEP)
            && targetEP.getChildren().size() != 0)
      {
         int[] innerSpace = getNewLaneSizeForValidation(targetEP, newSpace, checkContainerForResize(targetEP, laneEP));
         int newInnerWidth = innerSpace[0];
         int newInnerHeight = innerSpace[1];
         
         Rectangle drawingArea = null;
         List children = targetEP.getChildren();
         Rectangle parentBounds = GenericUtils.getSymbolRectangle(targetEP);
         Insets insets = ((AbstractSwimlaneFigure) ((GraphicalEditPart) targetEP).getFigure()).getAbstractSwimlaneFigureInsets();         
         
         for(int i = 0; i < children.size(); i++)
         {
            Figure childFigure = (Figure) ((AbstractGraphicalEditPart) children.get(i)).getFigure();
            if (null != drawingArea)
            {
               drawingArea.union(childFigure.getBounds());
            }
            else
            {
               drawingArea = childFigure.getBounds().getCopy();
            }
         }
         if(drawingArea != null)
         {
            if(!resizeLeft)
            {
               drawingArea.width += drawingArea.x;
               drawingArea.width += insets.right;
            }
            else
            {
               drawingArea.width += (parentBounds.width - (drawingArea.width + drawingArea.x));
               drawingArea.width -= insets.left;
            }
            if(!resizeTop)
            {
               drawingArea.height += drawingArea.y;
               drawingArea.height += insets.bottom * 2;
            }
            else
            {
               drawingArea.height += (parentBounds.height - (drawingArea.height + drawingArea.y));
               drawingArea.height -= insets.top;
               drawingArea.height += insets.bottom * 2;
            }
            if(newInnerHeight < drawingArea.height || newInnerWidth < drawingArea.width)
            {
               return true;
            }
         }
      }      
      else if(PoolLaneUtils.containsLanes(targetEP))
      {
         // check children 
         List children = targetEP.getChildren();
         // collapsed lanes should be resized only in one direction    
         int[] fixValue = new int[] {0};
         double onePercent = getOnePercent((AbstractSwimlaneEditPart) targetEP, fixValue, laneEP);
         for(int i = 0; i < children.size(); i++)
         {
            EditPart childEP = (EditPart) children.get(i);
            int[] newInnerSpace = getNewChildLaneSize(childEP, newSpace, onePercent);            
            // if this child will not change by our resize, we keep the old value
            if(!checkContainerForResize(childEP, laneEP))
            {
               if (OrientationType.VERTICAL_LITERAL.equals(direction))
               {                            
                  newInnerSpace[0] = newSpace[0];
               }
               else
               {
                  newInnerSpace[1] = newSpace[1];                  
               }             
            }
            if(hasCollision(childEP, laneEP, newInnerSpace))
            {
               return true;
            } 
         }   
      }
      return false;
   }   
   
   // every lane must have a minimum size
   public static boolean laneTooSmall(EditPart targetEP, AbstractSwimlaneEditPart laneEP, int[] newSpace)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) targetEP.getModel());
      OrientationType direction = diagram.getOrientation();
      
      if(targetEP instanceof LaneEditPart)
      {         
         int[] innerSpace = getNewLaneSizeForValidation(targetEP, newSpace, checkContainerForResize(targetEP, laneEP));
         int newInnerWidth = innerSpace[0];
         int newInnerHeight = innerSpace[1];
         
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {                            
            if(newInnerHeight < LaneFigure.getCollapsedSize(laneEP)/2 || newInnerWidth < LaneFigure.getCollapsedSize(laneEP))
            {
               return true;
            }                           
         }
         else
         {
            if(newInnerWidth < LaneFigure.getCollapsedSize(laneEP)/2 || newInnerHeight < LaneFigure.getCollapsedSize(laneEP))
            {
               return true;
            }               
         }         
      }      
      if(targetEP.getChildren().size() != 0 && PoolLaneUtils.containsLanes(targetEP))
      {
         // check children 
         List children = targetEP.getChildren(); 
         int[] fixValue = new int[] {0};
         double onePercent = getOnePercent((AbstractSwimlaneEditPart) targetEP, fixValue, laneEP);         
         for(int i = 0; i < children.size(); i++)
         {
            EditPart childEP = (EditPart) children.get(i);
            int[] newInnerSpace = getNewChildLaneSize(childEP, newSpace, onePercent);
            // if this child will not change by our resize, we keep the old value
            if(!checkContainerForResize(childEP, laneEP))
            {
               if (OrientationType.VERTICAL_LITERAL.equals(direction))
               {                            
                  newInnerSpace[0] = newSpace[0];
               }
               else
               {
                  newInnerSpace[1] = newSpace[1];                  
               }               
            }
            if(laneTooSmall(childEP, laneEP, newInnerSpace))
            {
               return true;
            } 
         }   
      }
      return false;
   }         

   // check starts from 0
   public static boolean isSensitiveArea(AbstractSwimlaneEditPart containerEP, Point location)
   {
      DiagramType diagram = ModelUtils.findContainingDiagram((IGraphicalObject) containerEP.getModel());
      OrientationType direction = diagram.getOrientation();
      // so we can use also the header of the lanes
      if (OrientationType.VERTICAL_LITERAL.equals(direction))
      {            
         if(location.x < 0)
         {
            return false;
         }
      }
      else
      {
         if(location.y < 0)
         {
            return false;
         }         
      }
      
      Rectangle containerBounds = containerEP.getFigure().getBounds().getCopy();      
      int lastPosition = 0;      
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
      
      if(lanes.size() > 0)
      {      
         for(int i = 0; i < lanes.size(); i++)
         {
            LaneEditPart laneEP = (LaneEditPart) lanes.get(i);
            LaneFigure figure = laneEP.getLaneFigure();
            Rectangle figureRectangle = new Rectangle(figure.getBounds().getCopy());
            areas.add(figureRectangle);
         }
      }
      else
      {
         return true;
      }
      
      ((RectangleComparator) RECT_COMPARATOR).setOrientation(direction);
      Collections.sort(areas, RECT_COMPARATOR);
      if(areas.size() > 0)
      {      
         for(int i = 0; i < areas.size(); i++)
         {
            Rectangle area = ((Rectangle) areas.get(i)).getCopy();   
            if (OrientationType.VERTICAL_LITERAL.equals(direction))
            {            
               if(location.x > lastPosition && location.x < area.x)
               {
                  return true;
               }
               lastPosition = area.x + area.width;                  
            }
            else
            {
               if(location.y > lastPosition && location.y < area.y)
               {
                  return true;
               }
               lastPosition = area.y + area.height;                                 
            }
         }
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {                     
            if(location.x > lastPosition && location.x < containerBounds.width)
            {
               return true;
            }         
         }
         else
         {
            if(location.y > lastPosition && location.y < containerBounds.height)
            {
               return true;
            }                     
         }
      }      
      return false;
   }   
   
   ////////// locations
   
   public static Point getLocationDelta(IFigure parentFigure, IFigure hostFigure,
         DiagramType diagram)
   {
      Point newLocation = new Point(0, 0);
      OrientationType direction = diagram.getOrientation();
      while(parentFigure != null && parentFigure instanceof AbstractSwimlaneFigure)
      {
         Point parentFigureLocation = parentFigure.getBounds().getLocation().getCopy();
         Insets insets = ((AbstractSwimlaneFigure) parentFigure).getAbstractSwimlaneFigureInsets();         
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {
            newLocation.x += insets.getWidth()/2;
            newLocation.y += insets.top;
         }
         else
         {
            newLocation.x += insets.left;
            newLocation.y += insets.getHeight()/2;
         } 
         newLocation.x += parentFigureLocation.x;
         newLocation.y += parentFigureLocation.y;
         parentFigure = parentFigure.getParent();
      }
      while(hostFigure != null && hostFigure instanceof AbstractSwimlaneFigure)
      {
         Point hostFigureLocation = hostFigure.getBounds().getLocation().getCopy();         
         Insets insets = ((AbstractSwimlaneFigure) hostFigure).getAbstractSwimlaneFigureInsets(); 
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {
            newLocation.x -= insets.getWidth()/2;
            newLocation.y -= insets.top;
         }
         else
         {
            newLocation.x -= insets.left;            
            newLocation.y -= insets.getHeight()/2;
         } 
         newLocation.x -= hostFigureLocation.x;
         newLocation.y -= hostFigureLocation.y;
         hostFigure = hostFigure.getParent();
      }
      return newLocation;      
   }   
   
   // absolute position needed for Copy & Paste
   public static Point getAbsoluteLocation(EditPart hostEP, Point location, DiagramType diagram)
   {
      OrientationType direction = diagram.getOrientation();      
      Point newLocation = new Point(location);
      IFigure figure = ((GraphicalEditPart) hostEP).getFigure();
      while(figure != null && !(figure instanceof FreeformLayer))
      {
         Point parentFigureLocation = figure.getBounds().getLocation().getCopy();
         newLocation.x += parentFigureLocation.x;
         newLocation.y += parentFigureLocation.y;
         
         Insets insets = ((AbstractSwimlaneFigure) figure).getAbstractSwimlaneFigureInsets();         
         if (OrientationType.VERTICAL_LITERAL.equals(direction))
         {
            newLocation.x += insets.getWidth()/2;
            newLocation.y += insets.top;
         }
         else
         {
            newLocation.x += insets.left;
            newLocation.y += insets.getHeight()/2;
         } 
         figure = figure.getParent();
      }
      return newLocation;
   }   
   
   // get absolute location 
   public static Point getLocation(GraphicalEditPart part, IFigure hostFigure, 
         Point location, boolean createLocation)
   {
      Point newLocation = null;
      DiagramType diagram = null;
      if(part.getModel() instanceof DiagramType)
      {
         diagram = (DiagramType) part.getModel();
      }
      else
      {         
         diagram = ModelUtils.findContainingDiagram((IGraphicalObject) part.getModel());         
      }
      IFigure partFigure = ((GraphicalEditPart) part).getFigure();
      IFigure parentFigure = partFigure.getParent();
      if(location != null)
      {
         newLocation = location.getCopy();
      }
      else
      {
         newLocation = ((GraphicalEditPart) part).getFigure().getBounds().getLocation().getCopy();               
      }      
      // if host equals part, we stay in the same container
      if(partFigure.equals(hostFigure))
      {
         parentFigure = null;
      }
      
      if(createLocation)
      {
         parentFigure = null;
         Point viewPortPoint = findViewportPoint(part); 
         newLocation.performTranslate(viewPortPoint.x, viewPortPoint.y);
      }
      
      DiagramEditorPage diagramEditorPage = (DiagramEditorPage) ((WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getCurrentPage();      
      ScalableFreeformRootEditPart rootEditPart = (ScalableFreeformRootEditPart) diagramEditorPage.getGraphicalViewer().getRootEditPart();
      ZoomManager zoomManager = rootEditPart.getZoomManager();
      newLocation.performScale(1 / zoomManager.getZoom());

      Point locationDelta = PoolLaneUtils.getLocationDelta(parentFigure, hostFigure, diagram);
      newLocation.x += locationDelta.x;
      newLocation.y += locationDelta.y;
      
      return newLocation;
   }
      
   // depends on direction
   public static class RectangleComparator implements Comparator
   {
      OrientationType direction = null;
      public void setOrientation(OrientationType direction)
      {
         this.direction = direction;
      }

      public int compare(Object o1, Object o2)
      {
         long lx = ((Rectangle) o1).x;
         long ly = ((Rectangle) o1).y;

         long rx = ((Rectangle) o2).x;
         long ry = ((Rectangle) o2).y;
         
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