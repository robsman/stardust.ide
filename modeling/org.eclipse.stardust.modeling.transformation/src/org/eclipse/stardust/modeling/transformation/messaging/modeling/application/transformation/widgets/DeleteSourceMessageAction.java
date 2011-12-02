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
import org.eclipse.stardust.modeling.transformation.messaging.modeling.Modeling_Messages;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.IMessageTransformationApplicationView;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;


public class DeleteSourceMessageAction extends Action
{
   private MessageTransformationController controller;
   private IMessageTransformationApplicationView view;

   public DeleteSourceMessageAction(IMessageTransformationApplicationView view,
         MessageTransformationController controller)
   {
      this.controller = controller;
      this.view = view;
   }

   public void run()
   {
      super.run();
      MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
            SWT.ICON_WARNING | SWT.OK | SWT.CANCEL);
      
      messageBox.setText(Modeling_Messages.TXT_WR_LEER);
      String message = Modeling_Messages.MSG_REALLY_WANT_REMOVE_SEL;
      messageBox.setMessage(MessageFormat.format(message,new Object[]{controller.getNameString()}));      
      
      if (messageBox.open() == SWT.OK)
      {
         controller.performSourceMessageRemovement();
         view.refreshModel();
         view.refreshDocument();
      }
   }

   public String getText()
   {
	  String message = Modeling_Messages.MSG_DELETE_MESSAGE;
	  return MessageFormat.format(message, new Object[]{controller.getNameString()});
   }

   @Override
   public boolean isEnabled()
   {
      return controller.isDeleteSourceMessageAvailable();
   }

}
