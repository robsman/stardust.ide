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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.AddNodeSymbolCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteValueCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.HideConnectionSymbolsCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.SetValueCmd;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @author fherinean
 * @version $Revision$
 */
public class GroupSymbolsAction extends SelectionAction
{
   public GroupSymbolsAction(IWorkbenchPart part)
   {
      super(part);
      
      setId(DiagramActionConstants.GROUP_SYMBOLS);
      setText(Diagram_Messages.GROUP_SYMBOLS_LABEL);
   }
   
   protected boolean calculateEnabled()
   {
      Command cmd = createGroupSymbolsCommand();
      return (null != cmd) ? cmd.canExecute() : false;
   }

   public void run()
   {
      execute(createGroupSymbolsCommand());
   }

   private Command createGroupSymbolsCommand()
   {
      CompoundCommand result = new CompoundCommand();
      
      Set parents = new HashSet();
      Set nodeEditParts = new HashSet();
      Set connections = new HashSet();
      
      Rectangle bounds = null;
      
      for (Iterator i = getSelectedObjects().iterator(); i.hasNext();)
      {
         Object element = i.next();
         if (element instanceof AbstractSwimlaneEditPart)
         {
            return UnexecutableCommand.INSTANCE;
         }
         else if (element instanceof AbstractNodeSymbolEditPart)
         {
            INodeSymbol node = (INodeSymbol) ((AbstractNodeSymbolEditPart) element).getModel();
            EObject container = ModelUtils.findContainingProcess(node);
            if (container == null)
            {
               container = ModelUtils.findContainingDiagram(node);
            }
            Boolean lockedByCurrentUser = ModelServerUtils.isLockedByCurrentUser(container);
            if (lockedByCurrentUser != null && lockedByCurrentUser.equals(Boolean.FALSE))
            {
               return UnexecutableCommand.INSTANCE;                  
            }
            
            if (node.eContainer() instanceof ISymbolContainer)
            {
               parents.add(node.eContainer());
            }
            else
            {
               result.add(UnexecutableCommand.INSTANCE);
            }
            
            nodeEditParts.add(element);
            
            // TODO rsauer robust enough?
            Rectangle nodeBounds = new Rectangle((int) node.getXPos(),
                  (int) node.getYPos(), Math.max(0, node.getWidth()), Math.max(0,
                        node.getHeight()));
            bounds = (null == bounds) ? nodeBounds : bounds.union(nodeBounds);
         }
         else if (element instanceof AbstractConnectionSymbolEditPart)
         {
            result.add(UnexecutableCommand.INSTANCE);
/*            
            IConnectionSymbol connection = (IConnectionSymbol) ((AbstractConnectionSymbolEditPart) element).getModel();
            if (connection.eContainer() instanceof ISymbolContainer)
            {
               parents.add(connection.eContainer());
            }
            else
            {
               result.add(UnexecutableCommand.INSTANCE);
            }            
            connections.add(connection);
*/            
         }
      }
      
      if (1 >= (nodeEditParts.size() + connections.size()))
      {
         result.add(UnexecutableCommand.INSTANCE);
      }
      
      if (1 == parents.size())
      {
         if (null == bounds)
         {
            bounds = new Rectangle(0, 0, 0, 0);
         }
         
         // move all nodes and connections to group
         CompoundCommand cmdAddToGroup = new CompoundCommand();
         
         GroupSymbolType group = CarnotWorkflowModelFactory.eINSTANCE.createGroupSymbolType();
         group.setXPos(bounds.x);
         group.setYPos(bounds.y);

         Set nodes = new HashSet();
         for (Iterator i = nodeEditParts.iterator(); i.hasNext();)
         {
            AbstractNodeSymbolEditPart nodeEditPart = (AbstractNodeSymbolEditPart) i.next();
            INodeSymbol node = (INodeSymbol) nodeEditPart.getModel();
            
            nodes.add(node);
            
            if (null != node.eContainmentFeature())
            {
               // remove from parent
               result.add(new HideConnectionSymbolsCmd(nodeEditPart));
               result.add(new DeleteValueCmd(node.eContainer(), node, node.eContainmentFeature()));
               
               // adjust coordinates
               cmdAddToGroup.add(new SetValueCmd(node,
                     CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_XPos(),
                     new Long(node.getXPos() - bounds.x)));
               cmdAddToGroup.add(new SetValueCmd(node,
                     CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_YPos(),
                     new Long(node.getYPos() - bounds.y)));
               
               // add to group
               cmdAddToGroup.add(new SetValueCmd(group, node.eContainmentFeature(), node));
            }
            else
            {
               result.add(UnexecutableCommand.INSTANCE);
            }
         }
         for (Iterator i = connections.iterator(); i.hasNext();)
         {
            IConnectionSymbol connection = (IConnectionSymbol) i.next();
            if (nodes.contains(connection.getSourceNode()) && nodes.contains(connection.getTargetNode()))
            {
               if (null != connection.eContainmentFeature())
               {
                  // remove from parent
                  result.add(new DeleteValueCmd(connection.eContainer(), connection,
                        connection.eContainmentFeature()));
                  
                  // add to group
                  result.add(new SetValueCmd(group, connection.eContainmentFeature(), connection));
               }
               else
               {
                  result.add(UnexecutableCommand.INSTANCE);
               }
            }
            else
            {
               i.remove();
            }
         }

         if ( !(1 < (nodes.size() + connections.size())))
         {
            result.add(UnexecutableCommand.INSTANCE);
         }

         // finally add group
         AddNodeSymbolCommand cmdAddGroup = new AddNodeSymbolCommand();
         cmdAddGroup.setDiagram((ISymbolContainer) parents.iterator().next());
         cmdAddGroup.setNodeSymbol(group,
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GroupSymbol());
         result.add(cmdAddGroup);
         
         result.add(cmdAddToGroup);
      }
      else
      {
         result.add(UnexecutableCommand.INSTANCE);
      }

      return result.unwrap();
   }
}