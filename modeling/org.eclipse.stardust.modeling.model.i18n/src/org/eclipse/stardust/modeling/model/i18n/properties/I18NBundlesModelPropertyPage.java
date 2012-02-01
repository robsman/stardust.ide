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
package org.eclipse.stardust.modeling.model.i18n.properties;

import java.text.MessageFormat;
import java.util.Locale;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.model.i18n.I18N_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;


public class I18NBundlesModelPropertyPage extends AbstractModelElementPropertyPage
{
   private Text baseNameText;

   private PropertyModel model;

   private LocalesList localesList;

   private Button deleteButton;

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      FormBuilder.createLabel(composite, I18N_Messages.I18NBundlesPropertyPage_Bundle_Basename_Label);
      if (getModelElement() instanceof ModelType)
      {
         baseNameText = FormBuilder.createText(composite);
         baseNameText.addFocusListener(new FocusListener()
         {
            public void focusGained(FocusEvent e)
            {
            }

            public void focusLost(FocusEvent e)
            {
               model.setBasename(baseNameText.getText());
            }
         });
         baseNameText.addKeyListener(new KeyListener()
         {
            public void keyPressed(KeyEvent e)
            {
            }

            public void keyReleased(KeyEvent e)
            {
               if (e.keyCode == SWT.CR)
               {
                  model.setBasename(baseNameText.getText());
               }
               if (e.keyCode == SWT.ESC)
               {
                  baseNameText.setText(model.getBasename());
               }
            }
         });
      }

      localesList = new LocalesList();
      localesList.createBody(composite, 2);
      localesList.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            deleteButton.setEnabled(localesList.getSelectedNLS() != null);
         }
      });
      
      return composite;
   }

   public void contributeButtons(Composite parent)
   {
      ((GridLayout) parent.getLayout()).numColumns++;
      deleteButton = FormBuilder.createButton(parent, I18N_Messages.I18NBundlesModelPropertyPage_DeleteButtonLabel, new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            Locale locale = localesList.getSelectedNLS();
            if (MessageDialog.openConfirm(getShell(),
                  I18N_Messages.I18NBundlesModelPropertyPage_DeleteConfirmationDialogTitle,
                  MessageFormat.format(
                        I18N_Messages.I18NBundlesModelPropertyPage_DeleteConfirmationMessage,
                        new Object[] {locale.getDisplayName()})))
            {
               model.deleteNls(localesList.getSelectedNLS());
            }
         }
      });
      deleteButton.setEnabled(false);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      if (model == null)
      {
         model = PropertyModel.get((ModelType) element);
      }
      baseNameText.setText(model.getBasename());
      localesList.setModel(model);
      
      if (!model.hasSourceFolders())
      {
         setErrorMessage(I18N_Messages.I18NBundlesModelPropertyPage_NoSourceFolderMessage);
      }
   }

   public void dispose()
   {
      if (model != null)
      {
         model.dispose();
      }
      super.dispose();
   }
}
