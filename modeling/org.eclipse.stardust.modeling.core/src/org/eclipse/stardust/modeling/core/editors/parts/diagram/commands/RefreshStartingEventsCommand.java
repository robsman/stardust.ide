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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.core.Diagram_Messages;


/**
 * @author fherinean
 * @version $Revision$
 */
public class RefreshStartingEventsCommand extends DiagramCommand
{
   private static final String IS_EVENT = "1"; //$NON-NLS-1$

   private static final String START_ACTIVITY_ATTR = CarnotConstants.ENGINE_SCOPE
         + "controlflow.startActivity"; //$NON-NLS-1$

   private static final String END_ACTIVITY_ATTR = CarnotConstants.ENGINE_SCOPE
         + "controlflow.endActivity"; //$NON-NLS-1$

   private CompoundCommand operations = new CompoundCommand();

   public RefreshStartingEventsCommand()
   {
      super(DIAGRAM, null, null);
   }

   public void execute()
   {
      List startingEvents = CommandUtils.getSymbols((ISymbolContainer) getContainer(),
            CarnotWorkflowModelPackage.eINSTANCE.getStartEventSymbol());
      List endingEvents = CommandUtils.getSymbols((ISymbolContainer) getContainer(),
            CarnotWorkflowModelPackage.eINSTANCE.getEndEventSymbol());
      ProcessDefinitionType process = getProcess();
      if (process != null && (!startingEvents.isEmpty() || !endingEvents.isEmpty()))
      {
         // find starting/ending activities
         ArrayList startingIds = new ArrayList(process.getActivity().size());
         for (Iterator i = process.getActivity().iterator(); i.hasNext();)
         {
            ActivityType activity = (ActivityType) i.next();
            startingIds.add(activity.getId());
         }
         ArrayList endingIds = new ArrayList(process.getActivity().size());
         endingIds.addAll(startingIds);
         Collections.reverse(endingIds);
         for (Iterator i = process.getTransition().iterator(); i.hasNext();)
         {
            TransitionType transition = (TransitionType) i.next();
            if (null != transition.getTo())
            {
               startingIds.remove(transition.getTo().getId());
            }            
            if (null != transition.getFrom())
            {
               endingIds.remove(transition.getFrom().getId());
            }            
         }
         // find starting/ending activity symbol
         ActivitySymbolType startingActivitySymbol = null;
         ActivitySymbolType endingActivitySymbol = null;
         List symbols = CommandUtils.getSymbols((ISymbolContainer) getContainer(),
               CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType());
         for (Iterator i = symbols.iterator(); i.hasNext();)
         {
            ActivitySymbolType symbol = (ActivitySymbolType) i.next();
            if (startingIds.contains(symbol.getModelElement().getId())
                  && startingActivitySymbol == null)
            {
               startingActivitySymbol = symbol;
            }
         }
         Collections.reverse(symbols);
         for (Iterator i = symbols.iterator(); i.hasNext();)
         {
            ActivitySymbolType symbol = (ActivitySymbolType) i.next();
            if (endingIds.contains(symbol.getModelElement().getId())
                  && endingActivitySymbol == null)
            {
               endingActivitySymbol = symbol;
            }
         }
         // updateVisuals starting connections
         for (Iterator i = startingEvents.iterator(); i.hasNext();)
         {
            StartEventSymbol eventSymbol = (StartEventSymbol) i.next();
            if (startingActivitySymbol == null)
            {
               // delete existing connections
               for (Iterator connItr = eventSymbol.getOutTransitions().iterator(); connItr
                     .hasNext();)
               {
                  TransitionConnectionType connection = (TransitionConnectionType) connItr
                        .next();
                  operations.add(new DeleteConnectionSymbolCmd(connection));
               }
            }
            else
            {
               Iterator connItr = eventSymbol.getOutTransitions().iterator();
               if (connItr.hasNext())
               {
                  // modify existing connections
                  while (connItr.hasNext())
                  {
                     TransitionConnectionType connection = (TransitionConnectionType) connItr
                           .next();
                     if (!eventSymbol.equals(connection.getSourceActivitySymbol())
                           || !startingActivitySymbol.equals(connection
                                 .getTargetActivitySymbol()))
                     {
                        ModifyConnectionSymbolCommand modify = new ModifyConnectionSymbolCommand();
                        modify.setConnection(connection);
                        modify.setSource(eventSymbol);
                        modify.setTarget(startingActivitySymbol);
                        operations.add(modify);
                     }
                  }
               }
               else
               {
                  // create connection
                  CreateConnectionSymbolCommand create = new CreateConnectionSymbolCommand(
                        new IdFactory("Transition", Diagram_Messages.BASENAME_Transition), //$NON-NLS-1$
                        CarnotWorkflowModelPackage.eINSTANCE
                              .getTransitionConnectionType());
                  create.setSourceSymbol(eventSymbol);
                  AttributeUtil.setAttribute(startingActivitySymbol.getModelElement(),
                        START_ACTIVITY_ATTR, IS_EVENT);
                  create.setTargetSymbol(startingActivitySymbol);
                  create.setParent(getDiagram());
                  create.setLocation(location);
                  operations.add(create);
               }
            }
         }
         // updateVisuals ending connections
         for (Iterator i = endingEvents.iterator(); i.hasNext();)
         {
            EndEventSymbol eventSymbol = (EndEventSymbol) i.next();
            if (endingActivitySymbol == null)
            {
               // delete existing connections
               for (Iterator connItr = eventSymbol.getInTransitions().iterator(); connItr
                     .hasNext();)
               {
                  TransitionConnectionType connection = (TransitionConnectionType) connItr
                        .next();
                  operations.add(new DeleteConnectionSymbolCmd(connection));
               }
            }
            else
            {
               Iterator connItr = eventSymbol.getInTransitions().iterator();
               if (connItr.hasNext())
               {
                  // modify existing connections
                  while (connItr.hasNext())
                  {
                     TransitionConnectionType connection = (TransitionConnectionType) connItr
                           .next();
                     if (!eventSymbol.equals(connection.getTargetActivitySymbol())
                           || !endingActivitySymbol.equals(connection
                                 .getSourceActivitySymbol()))
                     {
                        ModifyConnectionSymbolCommand modify = new ModifyConnectionSymbolCommand();
                        modify.setConnection(connection);
                        modify.setSource(endingActivitySymbol);
                        modify.setTarget(eventSymbol);
                        operations.add(modify);
                     }
                  }
               }
               else
               {
                  // create connection
                  CreateConnectionSymbolCommand create = new CreateConnectionSymbolCommand(
                        new IdFactory("Transition", Diagram_Messages.BASENAME_Transition), //$NON-NLS-1$
                        CarnotWorkflowModelPackage.eINSTANCE
                              .getTransitionConnectionType());
                  AttributeUtil.setAttribute(endingActivitySymbol.getModelElement(),
                        END_ACTIVITY_ATTR, IS_EVENT);
                  create.setSourceSymbol(endingActivitySymbol);
                  create.setTargetSymbol(eventSymbol);
                  create.setParent(getDiagram());
                  create.setLocation(location);
                  operations.add(create);
               }
            }
         }
      }
      operations.execute();
   }

   public boolean canExecute()
   {
      return true;
   }

   public void redo()
   {
      operations.redo();
   }

   public void undo()
   {
      operations.undo();
   }

   public void dispose()
   {
      operations.dispose();
      operations = null;
      super.dispose();
   }
}
