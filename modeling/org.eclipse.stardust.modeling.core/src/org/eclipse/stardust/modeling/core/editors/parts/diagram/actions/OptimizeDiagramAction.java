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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.AbstractConnectionSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteSymbolCommandFactory;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;


/**
 * @author fherinean
 * @version $Revision: 17989 $
 */
public class OptimizeDiagramAction extends SelectionAction implements IActiveAction
{
   public OptimizeDiagramAction(WorkflowModelEditor editor)
   {
      super(editor);
      initUI();
   }

   protected boolean calculateEnabled()
   {
      if (getSelectedObjects().size() == 1)
      {
         DiagramType diagram = getDiagram();
         if (diagram != null)
         {
            WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
            ModelServer server = editor.getModelServer();
            if (server != null && server.requireLock(diagram))
            {
               return false;
            }
            for (Iterator itr = getDiagram().eAllContents(); itr.hasNext();)
            {
               Object item = itr.next();
               if (item instanceof IModelElementNodeSymbol || item instanceof IFlowObjectSymbol)
               {
                  if (hasMultipleConnections((INodeSymbol) item, true)
                        || hasMultipleConnections((INodeSymbol) item, false))
                  {
                     return true;
                  }
               }
            }
         }
      }
      return false;
   }

   public void run()
   {
      Map identifiables = new HashMap();
      Set removedConnections = new HashSet();
      CompoundCommand command = new CompoundCommand();
      for (Iterator contentsIterator = getDiagram().eAllContents(); contentsIterator.hasNext();)
      {
         Object item = contentsIterator.next();
         if (item instanceof IModelElementNodeSymbol || item instanceof IFlowObjectSymbol)
         {
            INodeSymbol symbol = (INodeSymbol) item;
            removeMultipleConnections(command, removedConnections, symbol, true);
            removeMultipleConnections(command, removedConnections, symbol, false);
            if (item instanceof IModelElementNodeSymbol)
            {
               IIdentifiableModelElement identifiable = ((IModelElementNodeSymbol) item).getModelElement();
               Set symbols = (Set) identifiables.get(identifiable);
               if (symbols == null)
               {
                  symbols = new HashSet();
                  identifiables.put(identifiable, symbols);
               }
               symbols.add(symbol);
            }
         }
      }
      for (Iterator itr = identifiables.values().iterator(); itr.hasNext();)
      {
         Set symbols = (Set) itr.next();
         if (symbols.size() > 1)
         {
            for (Iterator symbolsIterator = symbols.iterator(); symbolsIterator.hasNext();)
            {
               INodeSymbol symbol = (INodeSymbol) symbolsIterator.next();
               if (!hasConnections(symbol, removedConnections, true)
                     && !hasConnections(symbol, removedConnections, false)
                     && symbolsIterator.hasNext())
               {
                  command.add(DeleteSymbolCommandFactory.createDeleteSymbolCommand(symbol));
               }
            }
         }
      }
      execute(command);
   }

   private boolean hasConnections(INodeSymbol symbol, Set removedConnections, boolean outgoing)
   {
      List features = outgoing ? symbol.getOutConnectionFeatures() : symbol.getInConnectionFeatures();
      for (int i = 0; i < features.size(); i++)
      {
         EStructuralFeature feature = (EStructuralFeature) features.get(i);
         // (fh) we're interested only in connections that can be multiple
         if (feature.isMany())
         {
            List connections = (List) symbol.eGet(feature);
            if (hasConnections(removedConnections, connections))
            {
               return true;
            }
         }
      }

      // (fh) now deal with GenericLinkConnections because they were not listed above
      List links = outgoing ? symbol.getOutLinks() : symbol.getInLinks();
      if (hasConnections(removedConnections, links))
      {
         return true;
      }

      // (fh) ignore RefersToConnections. In any case it should be empty...
      
      return false;
   }

   private boolean hasConnections(Set removedConnections, List links)
   {
      for (Iterator itr = links.iterator(); itr.hasNext();)
      {
         IConnectionSymbol connection = (IConnectionSymbol) itr.next();
         if (!removedConnections.contains(connection))
         {
            return true;
         }
      }
      return false;
   }

   private void removeMultipleConnections(CompoundCommand command, Set removedConnections, INodeSymbol symbol, boolean outgoing)
   {
      // (fh) check all outgoing connections
      List features = outgoing ? symbol.getOutConnectionFeatures() : symbol.getInConnectionFeatures();
      for (int i = 0; i < features.size(); i++)
      {
         EStructuralFeature feature = (EStructuralFeature) features.get(i);
         // (fh) we're interested only in connections that can be multiple
         if (feature.isMany())
         {
            List connections = (List) symbol.eGet(feature);
            removeConnections(command, removedConnections, connections, symbol, outgoing);
         }
      }

      // (fh) now deal with GenericLinkConnections because they were not listed above
      List links = outgoing ? symbol.getOutLinks() : symbol.getInLinks();
      removeConnections(command, removedConnections, links, symbol, outgoing);

      // (fh) ignore RefersToConnections. In any case it should be empty...
   }

   private void removeConnections(CompoundCommand command, Set removedConnections,
         List connections, INodeSymbol symbol, boolean outgoing)
   {
      Set symbols = new HashSet();
      Map connectionsMap = new HashMap();
      for (int j = 0; j < connections.size(); j++)
      {
         IConnectionSymbol connection = (IConnectionSymbol) connections.get(j);
         if (removedConnections.contains(connection))
         {
            continue;
         }
         INodeSymbol target = outgoing ? connection.getTargetNode() : connection.getSourceNode();
         if (target == symbol)
         {
            // (fh) oops, it's a reverse direction connection
            target = outgoing ? connection.getSourceNode() : connection.getTargetNode();
         }
         if (symbols.contains(target))
         {
            // directly remove this connection !
            command.add(new DeleteConnectionSymbolCmd(connection));
            removedConnections.add(connection);
         }
         else if (target instanceof IModelElementNodeSymbol)
         {
            IIdentifiableModelElement identifiable = ((IModelElementNodeSymbol) target).getModelElement();
            IConnectionSymbol otherConnection = (IConnectionSymbol) connectionsMap.get(identifiable);
            if (otherConnection == null)
            {
               connectionsMap.put(identifiable, connection);
            }
            else
            {
               // remove the longest one.
               double length = getLength(connection);
               double otherLength = getLength(otherConnection);
               if (length < otherLength)
               {
                  connectionsMap.put(identifiable, connection);
                  connection = otherConnection;
               }
               command.add(new DeleteConnectionSymbolCmd(connection));
               removedConnections.add(connection);
            }
         }
         symbols.add(target);
      }
   }

   private double getLength(IConnectionSymbol connection)
   {
      double length = 0;
      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
      ConnectionEditPart editPart = (ConnectionEditPart) editor.findEditPart(connection);
      if (editPart != null)
      {
         AbstractConnectionSymbolFigure figure = (AbstractConnectionSymbolFigure) editPart.getFigure();
         length = figure.getStart().getDistance(figure.getEnd());
      }
      return length;
   }

   private boolean hasMultipleConnections(INodeSymbol symbol, boolean outgoing)
   {
      // (fh) check all outgoing connections
      List features = outgoing ? symbol.getOutConnectionFeatures() : symbol.getInConnectionFeatures();
      for (int i = 0; i < features.size(); i++)
      {
         EStructuralFeature feature = (EStructuralFeature) features.get(i);
         // (fh) we're interested only in connections that can be multiple
         if (feature.isMany())
         {
            List connections = (List) symbol.eGet(feature);
            if (isMultiple(symbol, connections, outgoing))
            {
               return true;
            }
         }
      }

      // (fh) now deal with GenericLinkConnections because they were not listed above
      List links = outgoing ? symbol.getOutLinks() : symbol.getInLinks();
      if (isMultiple(symbol, links, outgoing))
      {
         return true;
      }

      // (fh) ignore RefersToConnections. In any case it should be empty...
      return false;
   }

   private boolean isMultiple(INodeSymbol symbol, List connections, boolean outgoing)
   {
      Set identifiables = new HashSet();
      Set symbols = new HashSet();
      for (int j = 0; j < connections.size(); j++)
      {
         IConnectionSymbol connection = (IConnectionSymbol) connections.get(j);
         INodeSymbol target = outgoing ? connection.getTargetNode() : connection.getSourceNode();
         if (target == symbol)
         {
            // (fh) oops, it's a reverse direction connection
            target = outgoing ? connection.getSourceNode() : connection.getTargetNode();
         }
         if (symbols.contains(target))
         {
            return true;
         }
         if (target instanceof IModelElementNodeSymbol)
         {
            IIdentifiableModelElement identifiable = ((IModelElementNodeSymbol) target).getModelElement();
            if (identifiables.contains(identifiable))
            {
               return true;
            }
            identifiables.add(identifiable);
         }
         symbols.add(target);
      }
      return false;
   }

   private DiagramType getDiagram()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof DiagramRootEditPart)
      {
         selection = ((DiagramRootEditPart) selection).getContents();
      }
      if (!(selection instanceof EditPart))
      {
         return null;
      }
      Object element = ((EditPart) selection).getModel();
      if (element instanceof DiagramType)
      {
         return (DiagramType) element;
      }
      return null;
   }

   protected void initUI()
   {
      super.init();
      setId(DiagramActionConstants.DIAGRAM_OPTIMIZE);
      setText(Diagram_Messages.TXT_SimplifyDiagram);
      setLazyEnablementCalculation(false);
   }

   protected void handleSelectionChanged()
   {
      // (fh) do nothing otherwise the calculateEnabled() will be invoked 2 extra times.
   }

   public boolean isActive()
   {
      refresh();
      return isEnabled();
   }
}