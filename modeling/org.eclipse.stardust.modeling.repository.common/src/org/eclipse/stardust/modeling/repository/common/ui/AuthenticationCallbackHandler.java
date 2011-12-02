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
package org.eclipse.stardust.modeling.repository.common.ui;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.ChoiceCallback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.window.SameShellProvider;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.ui.dialogs.LoginDialog;
import org.eclipse.swt.widgets.Display;


public class AuthenticationCallbackHandler implements CallbackHandler
{
   private IShellProvider provider = null;
   private String title;
   
   public AuthenticationCallbackHandler()
   {
      this.provider = new SameShellProvider(Display.getCurrent().getActiveShell());;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException
   {
      if (callbacks == null)
      {
         return;
      }
      
      for (int i = 0; i < callbacks.length; i++)
      {
         if (!(callbacks[i] instanceof NameCallback
               || callbacks[i] instanceof PasswordCallback
               || callbacks[i] instanceof ChoiceCallback))
         {
            throw new UnsupportedCallbackException(callbacks[i]);
         }
      }
      
      LoginDialog dialog = new LoginDialog(provider.getShell(),
            title == null ? Repository_Messages.DIA_LOGIN : title, callbacks);
      dialog.open();
   }
}
