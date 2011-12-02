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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrientationType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CreateSymbolCommand extends DiagramCommand
{
   private SetSymbolContainerCommand symContainerCmd;

   private EditPart targetEditPart;

   private Object transferElement;

   public CreateSymbolCommand(int parentLevel, IdFactory id, EClass eClass)
   {
      super(parentLevel, id, eClass);
   }

   public boolean canExecute()
   {
      if (this.getEClass().getName().equalsIgnoreCase("PublicInterfaceSymbol")) //$NON-NLS-1$
      {
         if (this.getProcess().getFormalParameters() == null)
         {
            return null != getContainingFeature();
         }
         return false;
      }
      return super.canExecute();
   }

   @Override
   protected Object clone() throws CloneNotSupportedException
   {
      // TODO Auto-generated method stub
      return super.clone();
   }

   protected IModelElement createModelElement()
   {
      IGraphicalObject symbol = (IGraphicalObject) super.createModelElement();
      DiagramType diagram = null;
      if (getParent() instanceof DiagramType)
      {
         diagram = (DiagramType) getParent();
      }
      else
      {
         diagram = ModelUtils.findContainingDiagram((IGraphicalObject) getParent());
      }
      OrientationType direction = diagram.getOrientation();

      if (symbol instanceof IModelElementNodeSymbol)
      {
         if (null != getIdFactory().getReferingElement())
         {
            // TODO: should be a command?
            ((IModelElementNodeSymbol) symbol).setModelElement(getIdFactory()
                  .getReferingElement());
         }
      }
      if (symbol instanceof INodeSymbol && location != null)
      {
         Rectangle containerBounds = null;
         EObject parent = getParent();
         boolean createDefaultLane = false;
         AbstractSwimlaneEditPart abstractSwimlaneEP = null;

         if (parent instanceof ISwimlaneSymbol)
         {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage();
            if (page != null)
            {
               IEditorPart editorPart = page.getActiveEditor();
               if (editorPart instanceof WorkflowModelEditor)
               {
                  WorkflowModelEditor editor = (WorkflowModelEditor) editorPart;
                  abstractSwimlaneEP = (AbstractSwimlaneEditPart) editor
                        .findEditPart(parent);
               }

               if (abstractSwimlaneEP != null)
               {
                  containerBounds = GenericUtils.getSymbolRectangle(abstractSwimlaneEP);
               }
            }
         }
         if (symbol instanceof LaneSymbol && containerBounds != null)
         {
            if (!(getParent() instanceof DiagramType))
            {
               if (diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL))
               {
                  // sometimes containsLanes does not return the right value
                  // because more than one editpolicy creates commands
                  // so we do a second check here
                  if (!PoolLaneUtils.containsLanes(abstractSwimlaneEP)
                        || PoolLaneUtils.containsOthers(abstractSwimlaneEP))
                  {
                     createDefaultLane = true;
                  }
               }
            }
         }

         if (diagram.getMode().equals(DiagramModeType.MODE_450_LITERAL)
               && symbol instanceof LaneSymbol)
         {
            ((INodeSymbol) symbol).setXPos(location.x);
            ((INodeSymbol) symbol).setYPos(location.y);
            if (createDefaultLane)
            {
               ((INodeSymbol) symbol).setWidth(containerBounds.width);
               ((INodeSymbol) symbol).setHeight(containerBounds.height);
            }
            else
            {
               // if we have already lanes
               // the new Lane must have same size as other Lanes in Container
               if (abstractSwimlaneEP != null)
               {
                  LaneEditPart firstLaneEP = (LaneEditPart) abstractSwimlaneEP
                        .getChildren().get(0);
                  Rectangle firstLaneBounds = GenericUtils
                        .getSymbolRectangle(firstLaneEP);
                  if (OrientationType.VERTICAL_LITERAL.equals(direction))
                  {
                     ((INodeSymbol) symbol).setHeight(firstLaneBounds.height);
                  }
                  else
                  {
                     ((INodeSymbol) symbol).setWidth(firstLaneBounds.width);
                  }
               }
            }
         }
         else
         {
            ISymbolContainer container = (ISymbolContainer) getContainer();
            boolean vertical = container instanceof DiagramType ? DiagramPlugin
                  .isVerticalModelling((DiagramType) container) : DiagramPlugin
                  .isVerticalModelling((IGraphicalObject) container);
            ((INodeSymbol) symbol).setXPos(symbol instanceof ISwimlaneSymbol && !vertical
                  ? 0
                  : location.x);
            ((INodeSymbol) symbol).setYPos(symbol instanceof ISwimlaneSymbol && vertical
                  ? 0
                  : location.y);

            ((INodeSymbol) symbol).setWidth((symbol instanceof GatewaySymbol)
                  ? location.width
                  : -1);
            ((INodeSymbol) symbol).setHeight((symbol instanceof GatewaySymbol)
                  ? location.height
                  : -1);
         }
      }
      return symbol;
   }

   public void undo()
   {
      if (getModelElement() instanceof LaneSymbol)
      {
         LaneSymbol lane = (LaneSymbol) getModelElement();
         lane.setParentLane(null);
         lane.setParentPool(null);
      }
      symContainerCmd.undo();
      if (transferElement != null && transferElement instanceof IIdentifiableModelElement)
      {
         EList symbols = ((IIdentifiableModelElement) transferElement).getSymbols();
         symbols.remove(getModelElement());
      }
   }

   /**
    * setTargetEditPart needed for redo() below so that a Dialog appears when DND from
    * outline and changing the Performer
    */
   public void setTargetEditPart(EditPart targetEditPart)
   {
      this.targetEditPart = targetEditPart;
   }

   public void setTransferElement(Object transferElement)
   {
      this.transferElement = transferElement;
   }

   public void redo()
   {
      if (symContainerCmd == null)
      {
         symContainerCmd = new SetSymbolContainerCommand();
         symContainerCmd.setContainer((ISymbolContainer) getContainer(), targetEditPart);
         symContainerCmd.setSymbol((IGraphicalObject) getModelElement());
         symContainerCmd.execute();
      }
      else
      {
         symContainerCmd.redo();
      }
      if (getModelElement() instanceof LaneSymbol)
      {
         LaneSymbol lane = (LaneSymbol) getModelElement();
         lane.setParentLane((ISwimlaneSymbol) getParent());
         lane.setParentPool(getPool());
      }
      if (transferElement != null && transferElement instanceof IIdentifiableModelElement)
      {
         EList symbols = ((IIdentifiableModelElement) transferElement).getSymbols();
         if (!symbols.contains(getModelElement()))
         {
            symbols.add(getModelElement());
         }
      }
   }

   protected List getContainingFeatureList()
   {
      return ((ISymbolContainer) getContainer()).getNodeContainingFeatures();

   }
}