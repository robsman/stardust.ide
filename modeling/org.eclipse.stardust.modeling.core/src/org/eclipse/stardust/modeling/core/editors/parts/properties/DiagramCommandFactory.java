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
package org.eclipse.stardust.modeling.core.editors.parts.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;


public class DiagramCommandFactory extends DefaultPropSheetCmdFactory
{
   public static final DiagramCommandFactory INSTANCE = new DiagramCommandFactory();

   public void addDeleteConnectionCommands(CompoundCommand command, IModelElement first,
         EStructuralFeature connectionType)
   {
      addDeleteConnectionCommands(command, first, null, connectionType);
   }

   public void addDeleteConnectionCommands(CompoundCommand command, IModelElement first,
         IModelElement second, EStructuralFeature connectionType)
   {
      ProcessDefinitionType process = ModelUtils.findContainingProcess(first);
      List diagrams = process.getDiagram();
      for (int i = 0; i < diagrams.size(); i++)
      {
         DiagramType diagram = (DiagramType) diagrams.get(i);
         addDeleteConnectionCommands(command, diagram, first, second, connectionType);
      }
   }

   private void addDeleteConnectionCommands(CompoundCommand command, DiagramType diagram,
         IModelElement first, IModelElement second, EStructuralFeature connectionType)
   {
      List connections = new ArrayList();
      connections.addAll((List) diagram.eGet(connectionType));
      for (Iterator iter = diagram.getPoolSymbols().iterator(); iter.hasNext();)
      {
         PoolSymbol pool = (PoolSymbol) iter.next();
         connections.addAll((List) pool.eGet(connectionType));
         for (Iterator iterator = pool.getLanes().iterator(); iterator.hasNext();)
         {
            LaneSymbol lane = (LaneSymbol) iterator.next();
            connections.addAll((List) lane.eGet(connectionType));
         }
      }

      for (int i = 0; i < connections.size(); i++)
      {
         IConnectionSymbol symbol = (IConnectionSymbol) connections.get(i);
         INodeSymbol source = symbol.getSourceNode();
         INodeSymbol target = symbol.getTargetNode();
         if (source instanceof IModelElementNodeSymbol
               && ((IModelElementNodeSymbol) source).getModelElement() == first
               && (second == null || (target instanceof IModelElementNodeSymbol)
                     && ((IModelElementNodeSymbol) target).getModelElement() == second)
               || target instanceof IModelElementNodeSymbol
               && ((IModelElementNodeSymbol) target).getModelElement() == first
               && (second == null || (source instanceof IModelElementNodeSymbol)
                     && ((IModelElementNodeSymbol) source).getModelElement() == second))
         {
            command.add(new DeleteConnectionSymbolCmd(symbol));
         }
      }
   }
}
