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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

/**
 * @author fherinean
 * @version $Revision$
 */
public class CompoundDiagramCommand extends CompoundCommand implements IDiagramCommand
{
   public CompoundDiagramCommand()
   {
      super();
   }

   public CompoundDiagramCommand(String label)
   {
      super(label);
   }

   public IContainedElementCommand duplicate()
   {
      CompoundDiagramCommand replica = new CompoundDiagramCommand();
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

   public void setLocation(Rectangle location)
   {
      List commands = getCommands();
      for (Iterator i = commands.iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IDiagramCommand)
         {
            ((IDiagramCommand) command).setLocation(location);
         }
      }
   }

   public void setParent(EObject parent)
   {
      List commands = getCommands();
      for (Iterator i = commands.iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IContainedElementCommand)
         {
            ((IContainedElementCommand) command).setParent(parent);
         }
      }
   }

   public Rectangle getLocation()
   {
      List commands = getCommands();
      for (Iterator i = commands.iterator(); i.hasNext();)
      {
         Command command = (Command) i.next();
         if (command instanceof IDiagramCommand)
         {
            return ((IDiagramCommand) command).getLocation();
         }
      }
      return null;
   }
}
