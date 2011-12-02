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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class InvalidIdentifierDialog extends Dialog
{
   private Text idText;
   private Button performCheckBox;
   private Text characterText;
   private Button autoPerformCheckBox;
   
   private String id;
   private String iconPath;
   private String type;
   
   private boolean perform = false;
   private char character = '_';
   private boolean autoPerform = false;
   
   private boolean hasInvalidCharacters;
   private boolean canAutoPerform;

   protected InvalidIdentifierDialog(Shell parentShell, boolean canAutoPerform, boolean hasInvalidCharacters)
   {
      super(parentShell);
      this.hasInvalidCharacters = hasInvalidCharacters;
      this.canAutoPerform = canAutoPerform;
      perform = !canAutoPerform;
   }

   protected Control createDialogArea(Composite parent)
   {
      getShell().setText(Diagram_Messages.TXT_WR);
      Composite composite = (Composite) super.createDialogArea(parent);
      GridLayout layout = (GridLayout) composite.getLayout();
      layout.numColumns = 3;
      Image image = DiagramPlugin.getImage(iconPath);
      Label iconLabel = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      iconLabel.setImage(image);
      FormBuilder.createLabel(composite, id, 2);
      FormBuilder.createLabel(composite, getMessageText(), 3);
      FormBuilder.createLabel(composite, Diagram_Messages.LBL_ID);
      idText = FormBuilder.createText(composite, 2);
      idText.setText(id);
      idText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            id = idText.getText();
         }
      });
      performCheckBox = FormBuilder.createCheckBox(composite, getPerformCheckBoxText(), 2);
      performCheckBox.setSelection(perform);
      performCheckBox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {/* (fh) ignore */}

         public void widgetSelected(SelectionEvent e)
         {
            perform = performCheckBox.getSelection();
            autoPerformCheckBox.setSelection(false);
            autoPerformCheckBox.setVisible(perform && canAutoPerform);
            updateId();
         }
      });
      characterText = new Text(composite, SWT.BORDER);
      characterText.setTextLimit(1);
      characterText.setText(Character.toString(character));
      characterText.addVerifyListener(new VerifyListener()
      {
         private boolean changing = false;
         
         public void verifyText(VerifyEvent e)
         {
            if (!changing)
            {
               if (!Character.isISOControl(e.character))
               {
                  if (Character.isJavaIdentifierPart(e.character))
                  {
                     changing = true;
                     characterText.setText(Character.toString(e.character));
                     changing = false;
                  }
                  e.doit = false;
               }
            }
         }
      });
      GridData layoutData = new GridData();
      layoutData.widthHint = convertWidthInCharsToPixels(2);
      layoutData.minimumWidth = layoutData.widthHint;
      characterText.setLayoutData(layoutData);
      characterText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String replaceString = characterText.getText();
            character = replaceString.length() == 0 ? 0 : replaceString.charAt(0);
            updateId();
         }
      });
      autoPerformCheckBox = FormBuilder.createCheckBox(composite, getAutoPerformCheckBoxText(), 3);
      autoPerformCheckBox.setSelection(autoPerform);
      autoPerformCheckBox.setVisible(perform && canAutoPerform);
      autoPerformCheckBox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {/* (fh) ignore */}

         public void widgetSelected(SelectionEvent e)
         {
            autoPerform = autoPerformCheckBox.getSelection();
         }
      });
      updateId();
      return composite;
   }

   private String getAutoPerformCheckBoxText()
   {
      return canAutoPerform ? Diagram_Messages.BOX_TXT_APPLY_TO_ALL_INVALID_ID_S : Diagram_Messages.BOX_TXT_APPLY_TO_ALL_SIMILAR_ISSUES_IN_THIS_MD;
   }

   public void updateId()
   {
      if (perform)
      {
         if (hasInvalidCharacters)
         {
            StringBuffer sb = new StringBuffer(id);
            for (int i = sb.length() - 1; i >= 0; i--)
            {
               if (!Character.isJavaIdentifierPart(sb.charAt(i)))
               {
                  if (character == 0)
                  {
                     sb.deleteCharAt(i);
                  }
                  else
                  {
                     sb.setCharAt(i, character);
                  }
               }
            }
            id = sb.toString();
         }
         else if (character != 0)
         {
            id = Character.toString(character) + id;
         }
         if (!idText.isDisposed())
         {
            idText.setText(id);
         }
      }
   }

   public void initialize(String id, String iconPath, String type, boolean autoReplace)
   {
      this.id = id;
      this.iconPath = iconPath;
      this.type = type;
      this.autoPerform = autoReplace;
   }

   private String getMessageText()
   {
      return type + (hasInvalidCharacters
         ? Diagram_Messages.MSG_TXT_ID_CONTAINS_INVALID_CHARACTERS
         : Diagram_Messages.MSG_TXT_ID_IS_NOT_VALID);
   }

   private String getPerformCheckBoxText()
   {
      return hasInvalidCharacters
            ? Diagram_Messages.BOX_TXT_REPLACE_INVALID_CHARACTERS_WITH
            : Diagram_Messages.BOX_TXT_PREFIX_ID_WITH;
   }

   public String getId()
   {
      return id;
   }

   public boolean getAutoPerform()
   {
      return autoPerform;
   }
}
