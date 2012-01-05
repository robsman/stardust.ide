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
package org.eclipse.stardust.modeling.repository.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.TreeEditPart;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.BindingManager;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.editors.ui.ModelElementPropertyDialog;
import org.eclipse.stardust.modeling.core.properties.AbstractConnectionPropertyPage;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;
import org.eclipse.stardust.modeling.repository.common.RepositoryPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;

/*
 * TODO: Layout
 * validation for duplicates
 * error messages 
 * 
 */
public class FileConnectionPropertyPage extends AbstractConnectionPropertyPage
{
   private RepositoryPackage PKG = RepositoryPackage.eINSTANCE;
   private BindingManager bndMgr = new BindingManager();
   
   private static final String FILENAME = "filename"; //$NON-NLS-1$
   private LabeledText text;
   private String filename;

   protected LabeledText txtId;
   protected LabeledText txtName;
   private Button byReferenceCheckbox;
      
   public void dispose()
   {
      Connection connection = getConnection();
      bndMgr.unbind((EObject) connection, txtId.getText());
      bndMgr.unbind((EObject) connection, txtName.getText());
   }
   
   public Control createBody(Composite parent)
   {
      // id, name, browse
      Composite composite = FormBuilder.createComposite(parent, 1);
      
      Composite composite1 = FormBuilder.createComposite(composite, 2);
      txtId = FormBuilder.createLabeledText(composite1, File_Messages.LBL_ID);
      txtName = FormBuilder.createLabeledText(composite1, File_Messages.LBL_TXT_NAME);
      FormBuilder.createLabel(composite1, ""); //$NON-NLS-1$
      byReferenceCheckbox = FormBuilder.createCheckBox(composite1, File_Messages.BOX_IMPORT_BY_REFERENCE);      
           
      FormBuilder.createHorizontalSeparator(composite, 1);
      
      Composite composite2 = FormBuilder.createComposite(composite, 3);
      
      text = FormBuilder.createLabeledText(composite2, File_Messages.LBL_TXT);
      FormBuilder.createButton(composite2, File_Messages.BUT_BW, new SelectionListener()
      {         
         public void widgetSelected(SelectionEvent e)
         {
            FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
            dialog.setFilterExtensions(new String[] {"*.xpdl"});  //$NON-NLS-1$ 
            
            String fileName = text.getText().getText();
            if (!StringUtils.isEmpty(fileName))
            {
               File file = FileConnectionHandler.resolve(getConnection(), fileName);
               dialog.setFilterPath(file.getParent());
               dialog.setFileName(file.getName());
            }
            fileName = dialog.open();
            if (fileName != null)
            {
               URI fileURI = new File(fileName).toURI();
               URI platformRelative = Platform.getLocation().toFile().toURI().relativize(fileURI);
               if (platformRelative != fileURI)
               {
                  Connection connection = getConnection();
                  ConnectionManager manager = (ConnectionManager) connection.getProperty(IConnectionManager.CONNECTION_MANAGER);
                  ModelType model = manager.getModel();
                  org.eclipse.emf.common.util.URI modelURI = model.eResource().getURI();
                  if (modelURI.isPlatformResource())
                  {
                     URI projectURI = URI.create(modelURI.segment(1));
                     URI projectRelative = projectURI.relativize(platformRelative);
                     if (projectRelative != platformRelative)
                     {
                        text.getText().setText("project:/" + projectRelative);
                        return;
                     }
                  }
                  text.getText().setText("platform:/" + platformRelative);
               }
               else
               {
                  text.getText().setText(fileName);
               }
            }
         }

         public void widgetDefaultSelected(SelectionEvent e)
         {}
      });

      // load from file
      Connection connection = getConnection();
      bndMgr.bind((EObject) connection, PKG.getConnection_Id(), txtId.getText());
      bndMgr.bind((EObject) connection, PKG.getConnection_Name(), txtName.getText());
      
      String byReference = getAttribute(IConnectionManager.BY_REFERENCE);
      if ("true".equals(byReference)) //$NON-NLS-1$
      {
         byReferenceCheckbox.setSelection(true);
      }

      // load the attribute for the file
      filename = getAttribute(FILENAME);
      text.getText().setText(filename == null ? "" : filename);       //$NON-NLS-1$
      return composite;
   }

   public boolean performOk()
   {  
      String fileName = text.getText().getText();
      File file = FileConnectionHandler.resolve(getConnection(), fileName);
      if (!file.exists() || file.isDirectory())
      {
          MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(), SWT.ICON_WARNING | SWT.CANCEL);
            messageBox.setText(File_Messages.TXT_WR);
            messageBox.setMessage(File_Messages.MSG_PLEASE_PROVIDE_A_VALID_FILENAME);
            messageBox.open();    	  
            return false;
      }
      if (byReferenceCheckbox.getSelection() && checkForCircularReference(file)) {
         return false;
      }
      setAttribute(IConnectionManager.BY_REFERENCE, byReferenceCheckbox.getSelection() ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
	  setAttribute(FILENAME, fileName);
      connectionPropertiesChanged();
      if (getElement() != null)
      {
         ((TreeEditPart) getElement()).refresh();
      }
      return true;
   }

   private boolean checkForCircularReference(File file)
   {
      boolean result = false;
      ModelElementPropertyDialog med = (ModelElementPropertyDialog) this.getContainer();
      String modelID = med.getEditor().getWorkflowModel().getId();
      try
      {
         ModelType referencedModel = ExtendedModelManager.loadModel(file);
         ConnectionManager manager = new ConnectionManager(referencedModel);
         manager.resolve();
         referencedModel.setConnectionManager(manager);
         result =  ModelUtils.hasCircularDependency(modelID, referencedModel);
         
         if (result) {
             MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
                   SWT.ICON_WARNING | SWT.CANCEL);
             messageBox.setText(File_Messages.TXT_CIRCULAR_DEPENDENCY_DETECTED);
             String message = MessageFormat.format(File_Messages.MSG_IS_DIRECTLY_OR_INDIRECTLY_REFERENCED_BY_MD, new Object[]{modelID, referencedModel.getId()});
             message = message + "\n\n" + File_Messages.MSG_CIRCULAR_REFERENCES_BETWEEN_MD_FILES_ARE_NOT_ALLOWED;
             messageBox.setMessage(message);
             messageBox.open();
             return result;
          }
         
        
         /*if (result) {
            MessageBox messageBox = new MessageBox(Display.getDefault().getActiveShell(),
                  SWT.ICON_WARNING | SWT.CANCEL);
            
            
            
            
            String message = File_messages.MSG_IS_DIRECTLY_OR_INDIRECTLY_REFERENCED_BY_MD;
            String message1 = File_messages.MSG_CIRCULAR_REFERENCES_BETWEEN_MD_FILES_ARE_NOT_ALLOWED;
            
            messageBox.setMessage(MessageFormat.format(message,new Object[]{referencedModel.getId()}+message1));
            
            messageBox.setMessage("'" + modelID //$NON-NLS-1$
                  + File_messages.MSG_IS_DIRECTLY_OR_INDIRECTLY_REFERENCED_BY_MD
                  + referencedModel.getId() + "'.\n\n" //$NON-NLS-1$
                  + File_messages.MSG_CIRCULAR_REFERENCES_BETWEEN_MD_FILES_ARE_NOT_ALLOWED);
            messageBox.open();
            
            return result;
         }*/
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return false;
   }
}