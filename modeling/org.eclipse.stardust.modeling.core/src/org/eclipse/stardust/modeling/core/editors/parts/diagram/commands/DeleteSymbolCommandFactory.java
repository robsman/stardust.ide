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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.PublicInterfaceSymbol;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class DeleteSymbolCommandFactory
{
   public static Command createDeleteSymbolCommand(INodeSymbol symbol)
   {
      CompoundCommand cmd = new CompoundCommand();

      if (symbol instanceof PublicInterfaceSymbol) {
    	  ProcessDefinitionType pdt = ModelUtils.findContainingProcess(symbol);
    	  SetValueCmd command = new SetValueCmd(pdt,  CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_FormalParameters(), null);
    	  cmd.add(command);
    	  command = new SetValueCmd(pdt,  CarnotWorkflowModelPackage.eINSTANCE.getProcessDefinitionType_FormalParameterMappings(), null);
    	  cmd.add(command);    	  
      }
      if (symbol instanceof StartEventSymbol)
      {
         AbstractEventSymbol eventSymbol = (AbstractEventSymbol) symbol;
         if (eventSymbol.getModelElement() == null)
         {
            removeEventAttr(cmd, CarnotConstants.START_ACTIVITY_ATTR, eventSymbol
                  .getOutTransitions().iterator());
         }
      }
      if (symbol instanceof EndEventSymbol)
      {
         AbstractEventSymbol eventSymbol = (AbstractEventSymbol) symbol;
         removeEventAttr(cmd, CarnotConstants.END_ACTIVITY_ATTR, eventSymbol
               .getInTransitions().iterator());
      }
      if (symbol instanceof ActivitySymbolType)
      {
         ActivitySymbolType activitySymbol = (ActivitySymbolType) symbol;
         if (activitySymbol.getActivity() != null)
         {
            if (activitySymbol.getActivity().getJoin().getValue() != JoinSplitType.NONE)
            {
               for (Iterator iter = activitySymbol.getInTransitions().iterator(); iter
                     .hasNext();)
               {
                  TransitionConnectionType transitionConnection = (TransitionConnectionType) iter
                        .next();
                  if (transitionConnection.getSourceNode() instanceof GatewaySymbol)
                  {
                     cmd
                           .add(new DeleteNodeSymbolCmd(transitionConnection
                                 .getSourceNode()));
                  }
               }
            }
            if (activitySymbol.getActivity().getSplit().getValue() != JoinSplitType.NONE)
            {
               for (Iterator iter = activitySymbol.getOutTransitions().iterator(); iter
                     .hasNext();)
               {
                  TransitionConnectionType transitionConnection = (TransitionConnectionType) iter
                        .next();
                  if (transitionConnection.getTargetNode() instanceof GatewaySymbol)
                  {
                     cmd
                           .add(new DeleteNodeSymbolCmd(transitionConnection
                                 .getTargetNode()));
                  }
               }
            }
         }
      }
      else if (symbol instanceof GroupSymbolType)
      {
         GroupSymbolType groupSymbol = (GroupSymbolType) symbol;

         for (Iterator iter = groupSymbol.getNodes().valueListIterator(); iter.hasNext();)
         {
            Object obj = iter.next();
            List connectionFeatures = new ArrayList();
            connectionFeatures.addAll(((INodeSymbol) obj).getInConnectionFeatures());
            connectionFeatures.addAll(((INodeSymbol) obj).getOutConnectionFeatures());
            for (Iterator iterator = connectionFeatures.iterator(); iterator.hasNext();)
            {
               EStructuralFeature feature = (EStructuralFeature) iterator.next();
               Object object = ((INodeSymbol) obj).eGet(feature);
               if(object != null)
               {
                  if(feature.isMany())
                  {
                     Iterator iter2 = ((List) ((IGraphicalObject) obj).eGet(feature)).iterator(); 
                     while(iter2.hasNext())
                     {
                        cmd
                           .add(new DeleteConnectionSymbolCmd((IConnectionSymbol) iter2.next()));
                     }                      
                  }
                  else
                  {
                     if(((IGraphicalObject) obj).eGet(feature) instanceof IConnectionSymbol)
                     {
                        cmd
                           .add(new DeleteConnectionSymbolCmd((IConnectionSymbol) ((IGraphicalObject) obj).eGet(feature)));                     
                     }
                  }
               }
            }
            cmd.add(new DeleteNodeSymbolCmd((INodeSymbol) obj));
         }
      }
      else if ((symbol instanceof LaneSymbol) || (symbol instanceof PoolSymbol))
      {
         moveSymbolsToParentContainerCmd(symbol, cmd);
      }
      cmd.add(new DeleteNodeSymbolCmd(symbol));

      return cmd;
   }

   private static void moveSymbolsToParentContainerCmd(INodeSymbol symbol,
         CompoundCommand cmd)
   {
      if ((symbol instanceof ISymbolContainer)
            && (symbol.eContainer() instanceof ISymbolContainer))
      {
         CompoundCommand cmdAddToDiagram = new CompoundCommand();

         for (Iterator i = ((ISymbolContainer) symbol).getNodes().valueListIterator(); i
               .hasNext();)
         {
            INodeSymbol node = (INodeSymbol) i.next();

            if (null != node.eContainmentFeature())
            {
               cmd.add(new DeleteValueCmd(symbol, node, node.eContainmentFeature()));

               cmdAddToDiagram.add(new SetValueCmd(node,
                     CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_XPos(),
                     new Long(node.getXPos() + symbol.getXPos())));
               cmdAddToDiagram.add(new SetValueCmd(node,
                     CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_YPos(),
                     new Long(node.getYPos() + symbol.getYPos() / 2)));

               cmdAddToDiagram.add(new SetValueCmd(symbol.eContainer(), node
                     .eContainmentFeature(), node));
            }
         }

         for (Iterator i = ((ISymbolContainer) symbol).getConnections()
               .valueListIterator(); i.hasNext();)
         {
            IConnectionSymbol connection = (IConnectionSymbol) i.next();

            if (null != connection.eContainmentFeature())
            {
               cmd.add(new DeleteValueCmd(symbol, connection, connection
                     .eContainmentFeature()));

               cmd.add(new SetValueCmd(symbol.eContainer(), connection
                     .eContainmentFeature(), connection));
            }
            else
            {
               cmd.add(UnexecutableCommand.INSTANCE);
            }
         }
         cmd.add(cmdAddToDiagram);
      }
   }

   private static void removeEventAttr(CompoundCommand cmd, String attrName,
         Iterator iterator)
   {
      for (Iterator iter = iterator; iter.hasNext();)
      {
         TransitionConnectionType transition = (TransitionConnectionType) iter.next();
         AttributeType attribute = AttributeUtil
               .getAttribute(
                     ((ActivitySymbolType) ((transition.getSourceActivitySymbol() instanceof ActivitySymbolType)
                           ? transition.getSourceActivitySymbol()
                           : transition.getTargetActivitySymbol())).getModelElement(),
                     attrName);
         if (attribute != null)
         {
            cmd.add(new DeleteValueCmd(attribute.eContainer(), attribute, attribute
                  .eContainingFeature()));
         }
      }
   }
}