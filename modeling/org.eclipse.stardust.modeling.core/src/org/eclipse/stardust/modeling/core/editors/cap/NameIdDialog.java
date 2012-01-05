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
package org.eclipse.stardust.modeling.core.editors.cap;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.platform.validation.IQuickValidationStatus;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author rsauer
 * @version $Revision$
 */
public class NameIdDialog extends Dialog
{
   private Map<String, String> nameIdCache;
   private List nameCache;
   private LabeledText idEntry;
   private LabeledText nameEntry;
   private String id;
   private String name;
   private boolean nameOnly = false;
   private String namePrefix = "Copy Of "; //$NON-NLS-1$
   private String idPrefix = CopyPasteUtil.idPrefix;
   private String dialogTitle = null;
   
   protected Button autoIdButton;   
   private SelectionListener autoIdListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {
      }

      public void widgetSelected(SelectionEvent e)
      {
         boolean selection = ((Button) e.widget).getSelection();
         if(selection)
         {
            idEntry.getText().setEditable(false);
            String computedId = ModelUtils.computeId(nameEntry.getText().getText());
            idEntry.getText().setText(computedId);            
         }
         else
         {
            idEntry.getText().setEditable(true);            
         }         
      }
   };            
   
   private ModifyListener idListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         // validate
         if (idEntry != null && nameEntry != null)
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

   private ModifyListener nameListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         // generate id
         if (idEntry != null && nameEntry != null)
         {
            if (id != null)
            {
               if (autoIdButton.getSelection())
               {               
                  String computedId = ModelUtils.computeId(nameEntry.getText().getText());
                  idEntry.getText().setText(computedId);
               }
            }
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

   public NameIdDialog(Shell parent, String id, String name, Map<String, String> nameIdCache)
   {
      super(parent);
      this.nameIdCache = nameIdCache;
      this.id = id;
      this.name = name;
   }

   public NameIdDialog(Shell parent, String name, List nameCache)
   {
      super(parent);
      this.nameCache = nameCache;
      this.name = name;
      nameOnly = true;
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      setDialogTitle();      
      shell.setText(dialogTitle);
   }
   
   public void setNamePrefix(String prefix)
   {
      namePrefix = prefix;
   }

   public void setIdPrefix(String prefix)
   {
      idPrefix = prefix;
   }   

   public String getId()
   {
      return id;
   }

   public String getName()
   {
      return name;
   }

   public Map<String, String> getNameIdCache()
   {
      return nameIdCache;
   }   

   public List getNameCache()
   {
      return nameCache;
   }      
   
   protected Control createDialogArea(Composite parent)
   {
      Composite panel = (Composite) super.createDialogArea(parent);

      ((GridLayout) panel.getLayout()).numColumns = 2;

      nameEntry = FormBuilder.createLabeledText(panel, Diagram_Messages.NameIdDialog_Name);
      idEntry = FormBuilder.createLabeledText(panel, Diagram_Messages.NameIdDialog_ID);
      nameEntry.getText().addModifyListener(nameListener);
      // in place of 'prefix + name' the value could be generated
      String prefixName = generatePrefixString(true);
      
      autoIdButton = FormBuilder.createCheckBox(panel,
            Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = PlatformUI.getPreferenceStore().getBoolean(
            BpmProjectNature.PREFERENCE_AUTO_ID_GENERATION);
      autoIdButton.setSelection(autoIdButtonValue);
      if(autoIdButtonValue)
      {
         idEntry.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);
      
      nameEntry.getText().setText(prefixName);
      if (id != null)
      {
         idEntry.getText().addModifyListener(idListener);
         String prefixId = generatePrefixString(false);
         idEntry.getText().setText(prefixId);
      }
      else
      {
         autoIdButton.setEnabled(false);
         idEntry.getText().setEnabled(false);
      }
      
      return panel;
   }

   // find a String not already in Cache for initial setting
   private String generatePrefixString(boolean isName)
   {
      StringBuffer generatedString = null;
      if(nameOnly || isName)
      {
         generatedString = new StringBuffer(namePrefix);
      }
      else
      {
         generatedString = new StringBuffer(idPrefix);  
      }
      
      if(nameOnly)
      {
         generatedString.append(name);
         while(nameCache.contains(generatedString.toString()))            
         {
            generatedString.insert(0, namePrefix);
         }
         return generatedString.toString();
      }      
      if(isName)
      {
         generatedString.append(name);
         while(nameIdCache.containsValue(generatedString.toString()))            
         {
            generatedString.insert(0, namePrefix);
         }
      }
      else
      {
         generatedString.append(id);
         while(nameIdCache.containsKey(generatedString.toString()))            
         {
            generatedString.insert(0, idPrefix);      
         }         
      }      
      return generatedString.toString();
   }

   private void setDialogTitle()
   {
      boolean isDuplicateId = false;
      boolean isDuplicateName = false;
      if (id != null)
      {
         if (nameIdCache.containsKey(id))
         {
            isDuplicateId = true;
         }
      }
      if ((nameOnly && nameCache.contains(name)) 
            || (!nameOnly && nameIdCache.containsValue(name)))
      {
         isDuplicateName = true;
      }
      if(isDuplicateId && isDuplicateName)
      {
         dialogTitle = new String(Diagram_Messages.NameIdDialog_Title_DuplicateNameID);
      }
      else if(isDuplicateId)
      {
         dialogTitle = new String(Diagram_Messages.NameIdDialog_Title_DuplicateID);         
      }
      else if(isDuplicateName)
      {
         dialogTitle = new String(Diagram_Messages.NameIdDialog_Title_DuplicateName);         
      }
   }
   
   // not empty, not the same as before
   private boolean validateUserInput()
   {
      boolean isEmptyId = false;
      boolean isDuplicateId = false;
      if (id != null)
      {
         idEntry.getLabel().setValidationStatus(IQuickValidationStatus.OK);
         isEmptyId = StringUtils.isEmpty(idEntry.getText().getText());
         isDuplicateId = false;
         if (isEmptyId)
         {
            idEntry.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
            idEntry.getLabel().setToolTipText(Diagram_Messages.NameIdDialog_Warning_IdEmpty);
         }
         else if (nameIdCache.containsKey(idEntry.getText().getText()))
         {
            isDuplicateId = true;
            idEntry.getLabel().setValidationStatus(IQuickValidationStatus.ERRORS);
            idEntry.getLabel().setToolTipText(Diagram_Messages.NameIdDialog_Warning_IdExists);
         }
      }
      nameEntry.getLabel().setValidationStatus(IQuickValidationStatus.OK);
      boolean isEmptyName = StringUtils.isEmpty(nameEntry.getText().getText());
      if (isEmptyName)
      {
         nameEntry.getLabel().setValidationStatus(IQuickValidationStatus.WARNINGS);
         idEntry.getLabel().setToolTipText(Diagram_Messages.NameIdDialog_Warning_NameEmpty);
      }
      else if ((nameOnly && nameCache.contains(nameEntry.getText().getText())) 
            || (!nameOnly && nameIdCache.containsValue(nameEntry.getText().getText())))
      {
         nameEntry.getLabel().setValidationStatus(IQuickValidationStatus.WARNINGS);
         idEntry.getLabel().setToolTipText(Diagram_Messages.NameIdDialog_Warning_NameExists);
      }
      if (isEmptyId || isDuplicateId)
      {
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
      // assign name and id
      this.id = idEntry.getText().getText();
      this.name = nameEntry.getText().getText();
      if(nameOnly)
      {
         nameCache.add(name);         
      }
      else
      {
         nameIdCache.put(id, name);         
      }
      
      super.okPressed();
   }
}