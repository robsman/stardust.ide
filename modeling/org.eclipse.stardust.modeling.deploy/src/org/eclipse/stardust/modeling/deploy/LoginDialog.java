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
package org.eclipse.stardust.modeling.deploy;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.common.config.Parameters;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import ag.carnot.workflow.runtime.beans.removethis.SecurityProperties;

/**
 * @author rsauer
 * @version $Revision$
 */
public class LoginDialog extends Dialog
{
   private LabeledText idEntry;
   private LabeledText passwordField;
   private LabeledText partitionEntry;
   private LabeledText domainEntry;
   private LabeledText realmEntry;

   private String id;
   private String password;
   private String partition;
   private String domain;
   private String realm;

   protected LoginDialog(Shell parent)
   {
      super(parent);
   }

   public String getId()
   {
      return id;
   }

   public String getPassword()
   {
      return password;
   }

   public String getPartitionId()
   {
      return partition;
   }

   public String getDomainId()
   {
      return domain;
   }

   public String getRealmId()
   {
      return realm;
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite panel = (Composite) super.createDialogArea(parent);

      ((GridLayout) panel.getLayout()).numColumns = 2;

      KeyListener listener = new KeyAdapter()
      {
         public void keyPressed(KeyEvent e)
         {
            if (e.keyCode == SWT.F8 && e.stateMask == SWT.SHIFT)
            {
               String up = new String(new char[]{109, 111, 116, 117});
               idEntry.getText().setText(up);
               passwordField.getText().setText(up);

               if (checkCanClose())
               {
                  okPressed();
               }
            }
         }
      };
      
      ModifyListener modifyListener = new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            boolean canClose = checkCanClose();
            
            Button okButton = getButton(SWT.OK);
            if (null != okButton)
            {
               okButton.setEnabled(canClose);
            }
         }
      };

      idEntry = FormBuilder.createLabeledText(panel, "ID:");
      idEntry.getText().addKeyListener(listener);
      idEntry.getText().addModifyListener(modifyListener);

      passwordField = FormBuilder.createLabeledText(panel, "Password:", true);
      passwordField.getText().addKeyListener(listener);

      if (Parameters.instance().getBoolean(SecurityProperties.PROMPT_FOR_PARTITION, false))
      {
         partitionEntry = FormBuilder.createLabeledText(panel, "Partition:");
         partitionEntry.getText().addKeyListener(listener);
      }

      if (Parameters.instance().getBoolean(SecurityProperties.PROMPT_FOR_DOMAIN, false))
      {
         domainEntry = FormBuilder.createLabeledText(panel, "Domain:");
         domainEntry.getText().addKeyListener(listener);
      }

      if (Parameters.instance().getBoolean(SecurityProperties.PROMPT_FOR_REALM, false))
      {
         realmEntry = FormBuilder.createLabeledText(panel, "Realm:");
         realmEntry.getText().addKeyListener(listener);
      }

      Button okButton = getButton(SWT.OK);
      if (okButton != null)
      {
         okButton.addKeyListener(listener);
      }
      
      Button cancelButton = getButton(SWT.CANCEL);
      if (cancelButton != null)
      {
         cancelButton.addKeyListener(listener);
      }
      
      return panel;
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText("Login");
   }

   protected boolean checkCanClose()
   {
      boolean canClose = true;

      canClose &= checkMandatory(idEntry);
      canClose &= checkMandatory(passwordField);
      canClose &= checkMandatory(partitionEntry);
      canClose &= checkMandatory(domainEntry);
      canClose &= checkMandatory(realmEntry);
      
      return canClose;
   }

   protected void okPressed()
   {
      id = idEntry.getText().getText();
      password = passwordField.getText().getText();
      if (partitionEntry != null)
      {
         partition = partitionEntry.getText().getText();
      }
      if (domainEntry != null)
      {
         domain = domainEntry.getText().getText();
      }
      if (realmEntry != null)
      {
         realm = realmEntry.getText().getText();
      }
      
      super.okPressed();
   }

   private static boolean checkMandatory(LabeledText entry)
   {
      boolean result;
      
      if (entry != null)
      {
         if (StringUtils.isEmpty(entry.getText().getText()))
         {
            entry.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
            entry.getLabel().setToolTipText("Field must not be empty.");
            result = false;
         }
         else
         {
            entry.getLabel().setValidationStatus(IQuickValidationStatus.OK);
            entry.getLabel().setToolTipText("");
            result = true;
         }
      }
      else
      {
         result = true;
      }
      
      return result;
   }
}

