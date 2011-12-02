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
package org.eclipse.stardust.modeling.core.editors.tools;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.createUtils.CreationUtils;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CompoundDiagramCommand;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.CreateSymbolCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;


public class CarnotCreationTool extends CreationTool
{
   private Command creationCommand;
   
   public CarnotCreationTool()
   {
      super();
      // by default only apply once per cycle
      setUnloadWhenFinished(true);
   }

   public CarnotCreationTool(CreationFactory factory)
   {
      super(factory);
      // by default only apply once per cycle
      setUnloadWhenFinished(true);
   }
   
   protected void performCreation(int button)
   {
      creationCommand = getCurrentCommand();      
      super.performCreation(button);
   }

   protected boolean updateTargetUnderMouse()
   {
      // check 1st for CurrentViewer, other wise we may get an exception
      if(getCurrentViewer() == null)
      {
         return false;
      }      
      return super.updateTargetUnderMouse();
   }   

   protected void handleFinished()
   {
      if (unloadWhenFinished() && !getCurrentInput().isControlKeyDown())
      {
         getDomain().loadDefaultTool();
      }
      else
      {
         reactivate();
      }
      if(STATE_TERMINAL == getState())
      {
         if(creationCommand != null)
         {
            INodeSymbol symbol = getCreatedSymbol((Command) creationCommand);
            creationCommand = null;
            if(symbol != null)
            {
               CreationUtils.showInDiagramAndEdit(symbol);
            }            
         }    
      }
   }

   protected boolean handleKeyUp(KeyEvent e)
   {
      if (e.keyCode == SWT.CTRL)
      {
         getDomain().loadDefaultTool();
      }
      return super.handleKeyUp(e);
   }
   
   private static INodeSymbol getCreatedSymbol(Command command)
   {      
      INodeSymbol symbol = null;
      if (command instanceof CompoundDiagramCommand)
      {
         for (Iterator i = ((CompoundDiagramCommand) command).getCommands().iterator(); i
               .hasNext();)
         {
            Command child = (Command) i.next();
            if (child instanceof CreateSymbolCommand)
            {
               symbol = (INodeSymbol) ((CreateSymbolCommand) child).getModelElement();
               break;
            }
         }
      }
      else if (command instanceof CompoundCommand)
      {
         for (Iterator i = ((CompoundCommand) command).getCommands().iterator(); i.hasNext();)
         {
            Command child = (Command) i.next();
            if(child instanceof CompoundDiagramCommand)
            {
               return getCreatedSymbol(child);
            }
         }
      }
      return symbol;
   }   
}