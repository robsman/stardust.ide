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

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;


/**
 * @author fherinean
 * @version $Revision$
 */
public class CompoundConnectionCommand extends CompoundDiagramCommand implements IConnectionCommand
{
   public IContainedElementCommand duplicate()
   {
      CompoundConnectionCommand replica = new CompoundConnectionCommand();
      for (Iterator i = getCommands().iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IContainedElementCommand)
         {
            command = (Command) ((IContainedElementCommand) command).duplicate();
         }
         replica.add(command);
      }
      return replica;
   }

   public void setTargetSymbol(INodeSymbol symbol)
   {
      List commands = getCommands();
      for (Iterator i = commands.iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IConnectionCommand)
         {
            ((IConnectionCommand) command).setTargetSymbol(symbol);
         }
      }
   }

   public void setSourceSymbol(INodeSymbol symbol)
   {
      List commands = getCommands();
      for (Iterator i = commands.iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IConnectionCommand)
         {
            ((IConnectionCommand) command).setSourceSymbol(symbol);
         }
      }
   }

   public void setTargetAnchorType(String anchorType)
   {
      List commands = getCommands();
      for (Iterator i = commands.iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IConnectionCommand)
         {
            ((IConnectionCommand) command).setTargetAnchorType(anchorType);
         }
      }
   }

   public void setSourceAnchorType(String anchorType)
   {
      List commands = getCommands();
      for (Iterator i = commands.iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IConnectionCommand)
         {
            ((IConnectionCommand) command).setSourceAnchorType(anchorType);
         }
      }
   }
}
