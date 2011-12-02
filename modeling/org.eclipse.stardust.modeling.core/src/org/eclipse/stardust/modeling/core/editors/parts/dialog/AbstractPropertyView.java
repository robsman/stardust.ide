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
package org.eclipse.stardust.modeling.core.editors.parts.dialog;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStackEvent;
import org.eclipse.gef.commands.CommandStackEventListener;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;


public abstract class AbstractPropertyView implements IPropertyView
{
   protected Composite composite; 

   protected AbstractEditPart editPart;

   protected EObject oldValue;

   protected EObject newValue;
   
   protected Label idLabel = null;

   protected Text idText = null;
   
   public Composite getComposite()
   {
      return composite;
   }

   public CommandStackEventListener createCommandStackListener()
   {
      return new CommandStackEventListener()
      {
         public void stackChanged(CommandStackEvent event)
         {
            if (!getComposite().isDisposed())
            {
               Command aCommand = event.getCommand();
               if (aCommand instanceof ModifyPropertyDialogCommand)
               {
                  EObject currentValue = ((ModifyPropertyDialogCommand) aCommand)
                        .getCurrentValueObject();

                  writeContent(currentValue);
               }
            }
         }
      };
   }
   
   public Listener createApplyButtonListener()
   {
      return new Listener()
      {
         public void handleEvent(Event event)
         {
            NotificationAdapter adapter = new NotificationAdapter(
                  (AbstractNodeSymbolEditPart) editPart);

            readContent(newValue);
            
            ModifyPropertyDialogCommand command = new ModifyPropertyDialogCommand(
                  adapter, oldValue, newValue);

            editPart.getViewer().getEditDomain().getCommandStack().execute(command);
         }
      };
   }
}
