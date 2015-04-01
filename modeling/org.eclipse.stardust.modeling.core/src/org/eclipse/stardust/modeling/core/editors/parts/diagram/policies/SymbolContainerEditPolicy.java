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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;

import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.figures.LaneFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.*;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.*;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;


public class SymbolContainerEditPolicy extends ContainerEditPolicy
{
   private Rectangle bounds = new Rectangle(0, 0, 0, 0);
   private static CarnotWorkflowModelPackage PKG = CarnotWorkflowModelPackage.eINSTANCE;

   protected Command getAddCommand(final GroupRequest request)
   {
      Command result = UnexecutableCommand.INSTANCE;

      if (getHost().getModel() instanceof ISymbolContainer)
      {
         EditPart hostEP = getHost();
         ISymbolContainer symbolContainer = (ISymbolContainer) hostEP.getModel();

         DiagramType diagram = null;
         if(symbolContainer instanceof DiagramType)
         {
            diagram = (DiagramType) symbolContainer;
         }
         else
         {
            diagram = ModelUtils.findContainingDiagram((IGraphicalObject) symbolContainer);
         }

         // absolute mouse location
         Point mouseLocation = ((ChangeBoundsRequest) request).getLocation().getCopy();
         ISymbolContainer container = (ISymbolContainer) (getHost()).getModel();
         final IFigure hostFigure = ((AbstractGraphicalEditPart) getHost()).getFigure();

         // needed for calculation of new coordinates
         final Point moveDelta = ((ChangeBoundsRequest) request).getMoveDelta();

         List parts = request.getEditParts();
         CompoundCommand addCmds = new CompoundCommand();
         for (int i = 0; i < parts.size(); i++)
         {
            final Object part = parts.get(i);
            if(getHost() instanceof AbstractSwimlaneEditPart
                  || getHost() instanceof DiagramEditPart)
            {
               // BPMN Mode on Process Diagram
               if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
                     && DiagramUtil.getDefaultPool(diagram) != null)
               {
                  // a lane cannot be placed on diagram
                  if(part instanceof LaneEditPart)
                  {
                     if(getHost() instanceof DiagramEditPart)
                     {
                        return result;
                     }
                  }
                  else
                  {
                     // if host contains lanes no other symbols can be placed on host then lanes
                     if(getHost() instanceof AbstractSwimlaneEditPart && PoolLaneUtils.containsLanes(getHost()))
                     {
                        return result;
                     }
                  }
               }
            }
            // for old models - a pool should not be moved into pool
            if(getHost() instanceof PoolEditPart && part instanceof PoolEditPart)
            {
               return result;
            }
            // not on a collapsed lane
            if(getHost() instanceof LaneEditPart
                  && ((LaneEditPart) getHost()).getLaneFigure().isCollapsed())
            {
               return result;
            }

            if (part instanceof LaneEditPart)
            {
               if (getRealContainer(container) instanceof ISwimlaneSymbol)
               {
                  MoveLaneCommand cmd = new MoveLaneCommand();
                  cmd.setTarget(((LaneEditPart) part).getLaneModel());
                  cmd.setContainer((ISwimlaneSymbol) getRealContainer(container));
                  addCmds.add(cmd);

                  // BPMN Mode on Process Diagram
                  if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
                        && DiagramUtil.getDefaultPool(diagram) != null)
                  {
                     // check for organizations
                     if(getHost() instanceof LaneEditPart &&
                           !PoolLaneUtils.canMove((LaneEditPart) getHost(), (LaneEditPart) part))
                     {
                        return UnexecutableCommand.INSTANCE;
                     }
                     if(PoolLaneUtils.containsLanes(getHost()))
                     {
                        mouseLocation = PoolLaneUtils.getLocation((GraphicalEditPart) getHost(),
                              ((AbstractSwimlaneEditPart) getHost()).getSwimlaneFigure(), mouseLocation, true);
                        // location is absolute mouse position
                        if(!PoolLaneUtils.isSensitiveArea((AbstractSwimlaneEditPart) getHost(), mouseLocation))
                        {
                           return UnexecutableCommand.INSTANCE;
                        }
                     }
                     else if(PoolLaneUtils.containsOthers((AbstractSwimlaneEditPart) getHost()))
                     {
                        // return UnexecutableCommand.INSTANCE;
                     }
                  }
                  MoveNodeSymbolCommand moveCmd;
                  moveCmd = new MoveNodeSymbolCommand();
                  moveCmd.setPart((INodeSymbol) ((AbstractNodeSymbolEditPart) part)
                        .getModel());
                  moveCmd.setLocation(mouseLocation);
                  addCmds.add(moveCmd);
               }
               else
               {
                  addCmds.add(UnexecutableCommand.INSTANCE);
               }
            }
            else if (part instanceof AbstractNodeSymbolEditPart)
            {
               boolean isValid = true;
               // no move if we have an collision
               if (checkMoveCollision((ChangeBoundsRequest) request))
               {
                  isValid = false;
               }
               if(isValid)
               {
                  SetSymbolContainerCommand addCmd = new SetSymbolContainerCommand()
                  {
                     private MoveNodeSymbolCommand cmd;

                     public void execute()
                     {
                        // newLocation after moving
                        Point oldLocation = ((GraphicalEditPart) part).getFigure().getBounds().getLocation().getCopy();
                        Point newLocation = moveDelta.getCopy();
                        newLocation = PoolLaneUtils.getLocation((GraphicalEditPart) part,
                              hostFigure, newLocation, false);
                        newLocation.x += oldLocation.x;
                        newLocation.y += oldLocation.y;

                        cmd = new MoveNodeSymbolCommand();
                        cmd.setPart((INodeSymbol) ((AbstractNodeSymbolEditPart) part)
                              .getModel());
                        cmd.setLocation(newLocation);
                        getHost().getViewer().getEditDomain().getCommandStack().execute(
                              cmd);
                        super.execute();
                     }

                     public void undo()
                     {
                        cmd.undo();
                        super.undo();
                     }

                     public void redo()
                     {
                        cmd.redo();
                        super.redo();
                     }
                  };
                  addCmd.setContainer(getRealContainer(container), getHost());
                  addCmd.setSymbol((IGraphicalObject) ((AbstractNodeSymbolEditPart) part)
                        .getModel());
                  // orphan.setLabel();
                  addCmds.add(addCmd);
               }
               else
               {
                  addCmds.add(UnexecutableCommand.INSTANCE);
               }
            }
            else
            {
               addCmds.add(UnexecutableCommand.INSTANCE);
            }
         }
         result = addCmds.unwrap();
      }

      return result;
   }

   private ISymbolContainer getRealContainer(ISymbolContainer container)
   {
      if (container instanceof DiagramType)
      {
         PoolSymbol defaultPool = DiagramUtil.getDefaultPool((DiagramType) container);
         if (defaultPool != null)
         {
            container = defaultPool;
         }
      }
      return container;
   }

   protected Command getCreateCommand(CreateRequest request)
   {
      Command cmd = UnexecutableCommand.INSTANCE;

      if (getHost().getModel() instanceof ISymbolContainer)
      {
         EditPart hostEP = getHost();
         ISymbolContainer symbolContainer = (ISymbolContainer) hostEP.getModel();

         DiagramType diagram = null;
         if(symbolContainer instanceof DiagramType)
         {
            diagram = (DiagramType) symbolContainer;
         }
         else
         {
            diagram = ModelUtils.findContainingDiagram((IGraphicalObject) symbolContainer);
         }

         Object part = request.getNewObject();
         if (part instanceof IDiagramCommand)
         {
            ((IDiagramCommand) part).setParent(getRealContainer(symbolContainer));
            // (fh) moved the actual returning of the command to LayoutEditPolicy,
            // here we only check for the parent validity
            cmd = null; // (Command) part;
         }
         // no creation on collapsed lane
         if(hostEP instanceof LaneEditPart)
         {
            if(((LaneFigure) ((LaneEditPart) hostEP).getLaneFigure()).isCollapsed())
            {
               return UnexecutableCommand.INSTANCE;
            }
         }

         if(hostEP instanceof AbstractSwimlaneEditPart
               || hostEP instanceof DiagramEditPart)
         {
            if(request instanceof CreateRequest)
            {
               CreateSymbolCommand createSymbolCommand = null;
               Command command = (Command) request.getNewObject();
               if(command instanceof CompoundCommand)
               {
                  List commands = ((CompoundCommand) command).getCommands();
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
               else if(command instanceof CreateSymbolCommand)
               {
                  createSymbolCommand = (CreateSymbolCommand) command;
               }
               // BPMN Mode on Process Diagram
               if(diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
                     && DiagramUtil.getDefaultPool(diagram) != null)
               {
                  boolean isLane = false;
                  if (null == createSymbolCommand)
                  {
                     return UnexecutableCommand.INSTANCE;
                  }
                  EClass eClass = createSymbolCommand.getEClass();
                  if(eClass.equals(PKG.getLaneSymbol()))
                  {
                     isLane = true;
                  }

                  if(isLane)
                  {
                     if(!(hostEP instanceof DiagramEditPart))
                     {
                        if(PoolLaneUtils.containsLanes(getHost()))
                        {
                           Point location = request.getLocation();
                           location = PoolLaneUtils.getLocation((GraphicalEditPart) hostEP,
                                 ((AbstractSwimlaneEditPart) hostEP).getSwimlaneFigure(), location, true);
                           // location is absolute
                           if(!PoolLaneUtils.isSensitiveArea((AbstractSwimlaneEditPart) hostEP, location))
                           {
                              return UnexecutableCommand.INSTANCE;
                           }
                        }
                     }
                     else
                     {
                        return UnexecutableCommand.INSTANCE;
                     }
                  }
                  else
                  {
                     if(hostEP instanceof DiagramEditPart)
                     {
                        return UnexecutableCommand.INSTANCE;
                     }
                     else
                     {
                        // symbols can be placed only on leaf nodes (lanes)
                        if(PoolLaneUtils.containsLanes((AbstractSwimlaneEditPart) getHost()))
                        {
                           return UnexecutableCommand.INSTANCE;
                        }
                     }
                  }
               }
            }
         }
      }
      return cmd;
   }

   public Command getCommand(Request request)
   {
      if (RequestConstants.REQ_MOVE_CHILDREN.equals(request.getType()))
      {
         checkMoveCollision((ChangeBoundsRequest) request);
      }
      return super.getCommand(request);
   }

   private boolean checkMoveCollision(ChangeBoundsRequest request)
   {
      boolean isCollision = false;
      for (Iterator iter = request.getEditParts().iterator(); iter.hasNext();)
      {
         AbstractNodeSymbolEditPart part = (AbstractNodeSymbolEditPart) iter.next();
         bounds = part.getFigure().getBounds().getCopy();
         part.getFigure().translateToAbsolute(bounds);
         bounds = request.getTransformedRectangle(bounds);
         ((GraphicalEditPart) getHost()).getContentPane().translateToRelative(bounds);
         ((GraphicalEditPart) getHost()).getContentPane().translateFromParent(bounds);
         bounds.translate(((GraphicalEditPart) getHost()).getContentPane()
               .getClientArea().getLocation().getNegated());
         isCollision = MoveSymbolCommandUtils.isSymbolCollision(Collections
               .singletonList(part), getHost(), part, bounds);
      }
      return isCollision;
   }
}