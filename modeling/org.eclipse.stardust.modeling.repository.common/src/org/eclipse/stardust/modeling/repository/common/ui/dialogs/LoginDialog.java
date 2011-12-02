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
package org.eclipse.stardust.modeling.repository.common.ui.dialogs;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.ChoiceCallback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class LoginDialog extends Dialog
{
   private Callback[] callbacks;
   private Control[] controls;
   
   private String title;
   
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         updateButtons();
      }
   };


   public LoginDialog(Shell shell, String title, Callback[] callbacks)
   {
      super(shell);
      this.title = title;
      if (callbacks != null)
      {
         this.callbacks = callbacks;
         this.controls = new Control[callbacks.length];
      }
   }

   /**
    * @see Dialog#createDialogArea(Composite)
    */
   protected Control createDialogArea(Composite parent)
   {
      Composite comp = new Composite(parent, SWT.NULL);
      GridLayout topLayout = new GridLayout();
      topLayout.numColumns = 2;
      topLayout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
      topLayout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
      topLayout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
      topLayout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
      comp.setLayout(topLayout);
      comp.setFont(parent.getFont());
      
      for (int i = 0; i < callbacks.length; i++)
      {
         if (callbacks[i] instanceof NameCallback)
         {
            controls[i] = addNameCallback(comp, (NameCallback) callbacks[i]);
         }
         else if (callbacks[i] instanceof PasswordCallback)
         {
            controls[i] = addPasswordCallback(comp, (PasswordCallback) callbacks[i]);
         }
         else if (callbacks[i] instanceof ChoiceCallback)
         {
            controls[i] = addChoiceCallback(comp, (ChoiceCallback) callbacks[i]);
         }
      }
      
      applyDialogFont(comp);
      return comp;
   }

   private Control addChoiceCallback(Composite comp, ChoiceCallback callback)
   {
      // TODO (fh) add complete support
      createLabel(comp, callback.getPrompt());
      Button checkBox = new Button(comp, SWT.CHECK);
      checkBox.setText(callback.getChoices()[0]);
      checkBox.setFont(comp.getFont());
      return checkBox;
   }

   private Control addPasswordCallback(Composite comp, PasswordCallback password)
   {
      createLabel(comp, password.getPrompt());
      char[] chars = password.getPassword();
      Text passwordText = createText(comp, chars == null ? "" : new String(chars)); //$NON-NLS-1$
      passwordText.setEchoChar('*');
      return passwordText;
   }

   private Text addNameCallback(Composite comp, NameCallback name)
   {
      createLabel(comp, name.getPrompt());
      String text = name.getName();
      if (text == null)
      {
         text = name.getDefaultName();
      }
      Text nameText = createText(comp, text == null ? "" : text); //$NON-NLS-1$
      return nameText;
   }

   private void createLabel(Composite comp, String text)
   {
      Label passwordLabel = new Label(comp, SWT.NONE);
      passwordLabel.setText(text);
      passwordLabel.setFont(comp.getFont());
   }

   private Text createText(Composite comp, String text)
   {
      Text passwordText = new Text(comp, SWT.BORDER | SWT.SINGLE);
      passwordText.setText(text);
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      gd.widthHint = 300;
      passwordText.setLayoutData(gd);
      passwordText.setFont(comp.getFont());
      passwordText.addModifyListener(listener);
      return passwordText;
   }

   protected void buttonPressed(int buttonId)
   {
      if (buttonId == IDialogConstants.OK_ID)
      {
         for (int i = 0; i < callbacks.length; i++)
         {
            if (callbacks[i] instanceof NameCallback)
            {
               ((NameCallback) callbacks[i]).setName(((Text) controls[i]).getText());
            }
            else if (callbacks[i] instanceof PasswordCallback)
            {
               ((PasswordCallback) callbacks[i]).setPassword(
                     ((Text) controls[i]).getText().toCharArray());
            }
            else if (callbacks[i] instanceof ChoiceCallback)
            {
               ((ChoiceCallback) callbacks[i]).setSelectedIndex(
                     ((Button) controls[i]).getSelection() ? 0 : -1);
            }
         }
      }
      super.buttonPressed(buttonId);
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      if (title != null)
      {
         shell.setText(title);
      }
   }

   /**
    * Enable the OK button if valid input
    */
   protected void updateButtons()
   {
      boolean enabled = false;
      for (int i = 0; i < controls.length; i++)
      {
         if (controls[i] != null)
         {
            enabled = isValidInput(controls[i]);
            // if one check failed, set the Button
            if(!enabled) {
               break;
            }
         }
      }
      getButton(IDialogConstants.OK_ID).setEnabled(enabled);
   }

   private boolean isValidInput(Control control)
   {
      if (control instanceof Text)
      {
         String value = ((Text) control).getText();
         return value.length() > 0;
      }
      return true;
   }

   /**
    * Enable the buttons on creation.
    */
   public void create()
   {
      super.create();
      updateButtons();
   }
}
