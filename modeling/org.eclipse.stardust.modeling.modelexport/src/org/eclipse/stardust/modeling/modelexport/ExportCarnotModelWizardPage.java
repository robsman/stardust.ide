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
package org.eclipse.stardust.modeling.modelexport;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.modeling.repository.common.ExtendedModelManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.dialogs.WizardExportResourcesPage;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchPartLabelProvider;

import ag.carnot.base.CollectionUtils;
import ag.carnot.base.StringUtils;

public class ExportCarnotModelWizardPage extends WizardExportResourcesPage
{
   private static final String DIRECTORY_LABEL = ExportMessages.LB_ToDir;
   private static final String CONTAINER_BROWSE_BUTTON_LABEL = ExportMessages.Btn_Browse;

   private Composite parent;
   private Text fileNameField;

   public ExportCarnotModelWizardPage(String pageId, IStructuredSelection selection)
   {
      super(pageId, selection);
   }

   public boolean performFinish()
   {
      checkUnsavedModels();

      for (Iterator<?> _iterator = getSelectedResources().iterator(); _iterator.hasNext();)
      {
         IResource resource = (IResource) _iterator.next();
         String modelName = "";

         try
         {
            modelName = resource.getName().substring(0,
                  resource.getName().lastIndexOf("."));
         }
         catch (Throwable t)
         {
         }

         if ("cwm".equals(resource.getFileExtension()) || ("xpdl").equals(resource.getFileExtension())) //$NON-NLS-1$ //$NON-NLS-2$
         {            
            StringBuffer pathBuf = new StringBuffer();
            pathBuf.append(fileNameField.getText());
            pathBuf.append(IPath.SEPARATOR);
            pathBuf.append(modelName);
            pathBuf.append("cwm".equals(resource.getFileExtension()) ? ".xml" : ".xpdl"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

            File targetFile = new File(pathBuf.toString());
            if (targetFile.exists()) 
            {
               //WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
               //Shell shell = editor.getSite().getShell();
               // skip file, when user does not want to overwrite file
               String message = ExportMessages.ExportCarnotModelWizardPage_OverwriteQuestionString + pathBuf.toString() + "?"; //$NON-NLS-2$
               boolean answer = MessageDialog.openQuestion(null, ExportMessages.ExportCarnotModelWizardPage_OverwriteQuestionTitle, message);
               if (!answer)
               {
                  continue;
               }
            }
            
            ExtendedModelManager emm = new ExtendedModelManager();
            try
            {
               emm.load(URI.createFileURI(resource.getLocation().toString()));
               emm.save(URI.createFileURI(pathBuf.toString()));
            }
            catch (IOException ex)
            {
               ex.printStackTrace();
            }
         }
      }
      return true;
   }

   private void checkUnsavedModels()
   {
      IEditorPart[] dirtyEditors = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
            .getActivePage().getDirtyEditors();
      List<IEditorPart> saveEditors = CollectionUtils.newList();
      for (int i = 0; i < dirtyEditors.length; i++)
      {
         IEditorInput editorInput = dirtyEditors[i].getEditorInput();
         if (editorInput instanceof IFileEditorInput)
         {
            IFile file = ((IFileEditorInput) editorInput).getFile();
            String extension = file.getFileExtension();
            if ((("cwm").equalsIgnoreCase(extension) || ("xpdl") //$NON-NLS-1$ //$NON-NLS-2$
                  .equalsIgnoreCase(extension))
                  && (getSelectedResources().contains(file)))
            {
               saveEditors.add(dirtyEditors[i]);
            }
         }
      }
      if (!saveEditors.isEmpty())
      {
         ListSelectionDialog modelsToSaveDialog = new ListSelectionDialog(getShell(),
               new AdaptableList(saveEditors), new WorkbenchContentProvider(),
               new WorkbenchPartLabelProvider(),
               ExportMessages.MSG_SaveModelsBeforeExporting);
         modelsToSaveDialog.setInitialSelections(saveEditors.toArray());
         modelsToSaveDialog.setTitle(ExportMessages.TITLE_SaveModels);
         int result = modelsToSaveDialog.open();
         if (result == IDialogConstants.OK_ID)
         {
            final Object[] editorsToSave = modelsToSaveDialog.getResult();
            BusyIndicator.showWhile(getShell().getDisplay(), new Runnable()
            {
               public void run()
               {
                  for (int i = 0; i < editorsToSave.length; i++)
                  {
                     IEditorPart editor = (IEditorPart) editorsToSave[i];
                     editor.doSave(new NullProgressMonitor());
                  }
               }
            });
         }
      }
   }

   protected void createDestinationGroup(Composite parent)
   {
      this.parent = parent;

      Composite containerGroup = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.numColumns = 3;
      containerGroup.setLayout(layout);
      containerGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL
            | GridData.GRAB_HORIZONTAL));
      containerGroup.setFont(parent.getFont());

      Label label = new Label(containerGroup, SWT.None);
      label.setText(DIRECTORY_LABEL);
      label.setFont(parent.getFont());

      fileNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
      GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
            | GridData.GRAB_HORIZONTAL);
      data.widthHint = SIZING_TEXT_FIELD_WIDTH;
      fileNameField.setLayoutData(data);
      fileNameField.setFont(parent.getFont());
      fileNameField.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent event)
         {
            if(event.getSource() instanceof Text)
            {
               Text source = (Text) event.getSource();
               if(StringUtils.isEmpty(source.getText()))
               {
                  setPageComplete(false);            
               }
               else
               {
                  setPageComplete(true);                  
               }
            }            
         }
      });

      Button containerBrowseButton = new Button(containerGroup, SWT.PUSH);
      containerBrowseButton.setText(CONTAINER_BROWSE_BUTTON_LABEL);
      containerBrowseButton.setFont(parent.getFont());
      containerBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
      containerBrowseButton.addSelectionListener(new SelectionListener()
      {
         public void widgetSelected(SelectionEvent event)
         {
            browseForFile();
         }

         public void widgetDefaultSelected(SelectionEvent event)
         {
            browseForFile();
         }

      });
      setTitle(ExportMessages.LB_Select);
      setDescription(ExportMessages.DESC_ModelsToExport); // TODO

      setControl(containerGroup);

      ImageDescriptor imageDescrip = WorkbenchImages
            .getImageDescriptor(IWorkbenchGraphicConstants.IMG_WIZBAN_EXPORT_WIZ);
      setImageDescriptor(imageDescrip);      
   }

   protected void browseForFile()
   {
      DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);

      String directory = dialog.open();
      if (directory != null)
      {
         fileNameField.setText(directory);
      }
   }

   public void createControl(Composite parent)
   {
      super.createControl(parent);
      setPageComplete(false);
   }

   protected void createOptionsGroup(Composite parent)
   {
   }

   public void handleEvent(Event event)
   {
      // TODO Auto-generated method stub
   }

   public String queryOverwrite(String pathString)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean isPageComplete()
   {
      if (StringUtils.isEmpty(fileNameField.getText()))
      {
         setErrorMessage(ExportMessages.MSG_NoDirectory);
         return false;
      }
      File targetFile = new File(fileNameField.getText());
      if (!targetFile.exists())
      {
         setErrorMessage(ExportMessages.MSG_DirectoryDoesNotExist);
         return false;
      }
      if (getSelectedResources().isEmpty())
      {
         setErrorMessage(ExportMessages.MSG_NoModelSelected);
         return false;
      }

      for (Iterator< ? > _iterator = getSelectedResources().iterator(); _iterator
            .hasNext();)
      {
         IResource resource = (IResource) _iterator.next();
         if (!("cwm".equals(resource.getFileExtension()) || ("xpdl").equals(resource
               .getFileExtension())))
         {
            setErrorMessage(ExportMessages.MSG_InvalidResource);
            return false;
         }
      }

      setErrorMessage(null);
      return super.isPageComplete();
   }

   public Control getControl()
   {
      return parent;
   }

   protected List<String> getTypesToExport()
   {
      List<String> list = CollectionUtils.newList();
      list.add("cwm"); //$NON-NLS-1$
      list.add("xpdl"); //$NON-NLS-1$
      return list;
   }
}