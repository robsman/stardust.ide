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

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.modeling.common.platform.utils.WorkspaceUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;


/**
 * URL page
 */
public class XSDURLPage extends WizardPage implements SelectionListener
{
   private final ImportFromSchemaWizard importFromSchemaWizard;

   private Text urlField;

   private String saveString;

   protected boolean saveToWorkspace;

   private SelectSingleFolderView folderView;
   
   boolean schemaLoaded = false;

   protected boolean folderSelected;

   private String location;

   public XSDURLPage(ImportFromSchemaWizard importFromSchemaWizard, String location)
   {
      super("URLPage"); //$NON-NLS-1$
      this.importFromSchemaWizard = importFromSchemaWizard;
      setPageComplete(false);
      this.location = location;
   }

   public void setVisible(boolean visible)
   {
      folderView.setVisibleHelper(visible && saveToWorkspace);
      super.setVisible(visible);
   }

   public void createControl(Composite parent)
   {
      Composite client = FormBuilder.createComposite(parent, 2);

      FormBuilder.createLabel(client, Structured_Messages.URLLabel, 2);

      saveString = ImportFromSchemaWizard.HTTP; //$NON-NLS-1$
      urlField = FormBuilder.createText(client);
      if (location == null)
      {
         urlField.setText(saveString);
      }
      else
      {
         urlField.setText(location);
         urlField.setEditable(false);
      }

      FormBuilder.createButton(client, Structured_Messages.LoadButtonLabel, this);
      
      final Button cb = FormBuilder.createCheckBox(client, Structured_Messages.SaveResourceLabel, 2);

      folderView = new SelectSingleFolderView(null, true,
            WorkspaceUtils.getProjectFromEObject(importFromSchemaWizard.getTypeDeclarations()));
      SelectSingleFolderView.Listener listener = new SelectSingleFolderView.Listener()
      {
         public void setControlComplete(boolean isComplete)
         {
            folderSelected = isComplete;
            checkPageComplete();
         }
      };
      folderView.setListener(listener);
      final Control control = folderView.createControl(client);
      control.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData(2));
      control.setVisible(false);
      if (location != null)
      {
         cb.setSelection(true);
         cb.setEnabled(false);
         setMustSave(true);
      }
      cb.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            setMustSave(cb.getSelection());
         }
      });

      setControl(client);
   }

   public String getURL()
   {
      return urlField.getText();
   }

   private boolean openExternalSchema(IProgressMonitor monitor)
   {
      String text = urlField.getText();

      if (text.equals("")) //$NON-NLS-1$
      {
         setErrorMessage(Structured_Messages.SpecifyURLMessage);
         return false;
      }

      if (location == null && !text.startsWith(ImportFromSchemaWizard.HTTP))
      {
         setErrorMessage(Structured_Messages.BadURLPrefixMessage + ImportFromSchemaWizard.HTTP);
         return false;
      }
      
      if (location == null)
      {
         try
         {
            String host = new URL(text).getHost();
            if (host == null || host.trim().length() == 0)
            {
               setErrorMessage(Structured_Messages.NoHost);
               return false;
            }
         }
         catch (MalformedURLException e)
         {
            setErrorMessage(e.getMessage());
            return false;
         }
      }

      setErrorMessage(null);
      String errorMessage = importFromSchemaWizard.doLoadExternalModel(monitor, text, text);
      if (errorMessage != null)
      {
         setErrorMessage(errorMessage);
         return false;
      }
      else
      {
         return true;
      }
   }

   public IRunnableWithProgress getRunnable()
   {
      return new IRunnableWithProgress()
      {
         public void run(IProgressMonitor monitor) throws InvocationTargetException,
               InterruptedException
         {
            if (monitor == null)
            {
               monitor = new NullProgressMonitor();
            }
            monitor.beginTask("", 6); //$NON-NLS-1$

            schemaLoaded = openExternalSchema(monitor);

/*            if (!schemaLoaded)
            {
               throw new InvocationTargetException(new java.lang.Error());
            }*/

            monitor.done();
         }
      };
   }

   public void widgetDefaultSelected(SelectionEvent e)
   {
   }

   public void widgetSelected(SelectionEvent e)
   {
      try
      {
         importFromSchemaWizard.getContainer().run(false, true, getRunnable());
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      checkPageComplete();
   }

   private void checkPageComplete()
   {
      setPageComplete(schemaLoaded && (!saveToWorkspace || folderSelected));
   }

   public IFolder getSaveFolder()
   {
      return folderView.getFolder();
   }

   public String getClasspathResourceName(IFile file)
   {
      return folderView.getClasspathResourceName(file);
   }

   private void setMustSave(boolean mustSave)
   {
      saveToWorkspace = mustSave;
      folderView.setVisibleHelper(saveToWorkspace);
      checkPageComplete();
   }
}