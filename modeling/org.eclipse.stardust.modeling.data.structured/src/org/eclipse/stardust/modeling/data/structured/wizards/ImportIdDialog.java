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
package org.eclipse.stardust.modeling.data.structured.wizards;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author rsauer
 * @version $Revision: 27953 $
 */
public class ImportIdDialog extends Dialog
{
   private List<String> idCache;
   private LabeledText idEntry;
   private String id;
   private final String idPrefix = "CopyOf"; //$NON-NLS-1$
   
   private ModifyListener idListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         // validate
         if (idEntry != null)
         {
            boolean enabled = false;
            enabled = validateUserInput();
            // if validation failed, disable the Button
            Button okButton = getButton(IDialogConstants.OK_ID);
            if (okButton != null)
            {
               okButton.setEnabled(enabled);               
            }
         }
      }
   };

   public ImportIdDialog(Shell parent, String id, List<String> idCache)
   {
      super(parent);      
      this.idCache = idCache;
      this.id = id;
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText(Structured_Messages.ImportIdDialog_Label);
   }

   public String getId()
   {
      return id;
   }

   public List<String> getIdCache()
   {
      return idCache;
   }      
   
   protected Control createDialogArea(Composite parent)
   {
      Composite panel = (Composite) super.createDialogArea(parent);

      ((GridLayout) panel.getLayout()).numColumns = 2;

      this.idEntry = FormBuilder.createLabeledText(panel, Structured_Messages.ImportIdDialog_Id);
            
      idEntry.getText().addModifyListener(idListener);
      String prefixId = generatePrefixString(false);
      idEntry.getText().setText(prefixId);
      
      return panel;
   }

   // find a String not already in Cache for initial setting
   private String generatePrefixString(boolean isName)
   {
      StringBuffer generatedString = null;
      generatedString = new StringBuffer(idPrefix);  
      
      generatedString.append(id);
      while (idCache.contains(generatedString.toString()))            
      {
         generatedString.insert(0, idPrefix);      
      }         
      return generatedString.toString();
   }
   
   // not empty, not the same as before
   private boolean validateUserInput()
   {
      idEntry.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      
      if (StringUtils.isEmpty(idEntry.getText().getText()))
      {
         idEntry.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         idEntry.getLabel().setToolTipText(Structured_Messages.ImportIdDialog_EmptyId);
         return false;
      }
      else if (idCache.contains(idEntry.getText().getText()))
      {
         idEntry.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
         idEntry.getLabel().setToolTipText(Structured_Messages.ImportIdDialog_DuplicateId);
         return false;
      }

      return true;
   }
   
   /**
    * validate on creation
    */
   public void create()
   {
      super.create();
      boolean enabled = false;
      enabled = validateUserInput();
      // if validation failed, disable the Button
      Button okButton = getButton(IDialogConstants.OK_ID);
      if (okButton != null)
      {
         okButton.setEnabled(enabled);               
      }
   }
   
   protected void okPressed()
   {
      this.id = idEntry.getText().getText();
      idCache.add(id);         
      
      super.okPressed();
   }
}