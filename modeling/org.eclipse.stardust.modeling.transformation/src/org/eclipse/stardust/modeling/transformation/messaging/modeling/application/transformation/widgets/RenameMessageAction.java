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
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import java.text.MessageFormat;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.IMessageTransformationApplicationView;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.swt.widgets.Display;


public class RenameMessageAction extends Action
{
   private MessageTransformationController controller;
   private IMessageTransformationApplicationView view;
   private boolean isSource;

   public RenameMessageAction(IMessageTransformationApplicationView view,
         MessageTransformationController controller, boolean isSource)
   {
      this.controller = controller;
      this.view = view;
      this.isSource = isSource;
   }

   public void run()
   {
      super.run();
      
      MessageRenameDialog dialog = new MessageRenameDialog(Display
            .getCurrent().getActiveShell(), controller, isSource); 
      if (dialog.open() == IDialogConstants.OK_ID)
      {
         if(isSource)
         {
            controller.performSourceMessageRemovement();
            controller.addSourceMessageType(dialog.getMessageType(), dialog.getMessageName());
         }
         else
         {
            controller.performTargetMessageRemovement();
            controller.addTargetMessageType(dialog.getMessageType(), dialog.getMessageName());            
         }
         view.refreshModel();
         view.refreshDocument();        
      }
   }

   public String getText()
   {
	  String message = Modeling_Messages.TXT_MODIFY;
	  return MessageFormat.format(message, new Object[]{controller.getNameString()});
   }

   @Override
   public boolean isEnabled()
   {
      if(isSource)
      {
         return controller.isDeleteSourceMessageAvailable();
      }
      return controller.isDeleteTargetMessageAvailable();      
   }
}