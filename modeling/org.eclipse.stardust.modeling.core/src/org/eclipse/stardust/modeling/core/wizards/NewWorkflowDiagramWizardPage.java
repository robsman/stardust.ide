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
package org.eclipse.stardust.modeling.core.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well as the file
 * name. The page will only accept file name without the extension OR with the extension
 * that matches the expected one (cwm).
 */
public class NewWorkflowDiagramWizardPage extends WizardPage
{
   private String modelId = ""; //$NON-NLS-1$
   private String modelName = ""; //$NON-NLS-1$
   private String modelAuthor = ""; //$NON-NLS-1$
   private String modelDescription = ""; //$NON-NLS-1$
   private String containerName = ""; //$NON-NLS-1$
   private String fileName = ""; //$NON-NLS-1$

   private ISelection selection;

   // enable finish button
   private boolean completePage = true;
   // is there an open project
   private boolean currentProject = true; 

   /**
    * Constructor for SampleNewWizardPage.
    * 
    * @param selection
    */
   public NewWorkflowDiagramWizardPage(ISelection selection)
   {
      super("newModelPage"); //$NON-NLS-1$
      setTitle(Diagram_Messages.TITLE_NewCarnotWorkflowModel);
      setDescription(Diagram_Messages.DESC_CarnotWizard);
      this.selection = selection;
      
      // no current projekt
      if (ResourcesPlugin.getWorkspace().getRoot().getProjects().length == 0)
      {
         currentProject = false; 
      }   
   }

   /**
    * @see IDialogPage#createControl(Composite)
    */
   public void createControl(Composite parent)
   {      
      Composite container = new Composite(parent, SWT.NULL);
      GridLayout layout = new GridLayout();
      container.setLayout(layout);
      layout.numColumns = 3;
      layout.verticalSpacing = 9;

/*      
      final Label lblModelId = new Label(container, SWT.NULL);
      lblModelId.setText(Diagram_Messages.LB_ModelAndId);

      final Text txtModelId = new Text(container, SWT.BORDER | SWT.SINGLE);
      txtModelId.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      txtModelId.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            modelId = ((Text) e.widget).getText();
            dialogChanged();
         }
      });
      txtModelId.setText("New_Workflow_Model"); //$NON-NLS-1$
      // ending line with blind label
      new Label(container, SWT.NULL);
*/      

      final Label lblModelName = new Label(container, SWT.NULL);
      lblModelName.setText(Diagram_Messages.LB_ModelAndName);

      final Text txtModelName = new Text(container, SWT.BORDER | SWT.SINGLE);
      txtModelName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
/*      
      txtModelName.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            modelName = ((Text) e.widget).getText();
            txtModelId.setText(ModelUtils.computeId(modelName));
            dialogChanged();
         }
      });
      txtModelName.setText(Diagram_Messages.LB_NewWorkflowModel);
*/      
      // ending line with blind label
      new Label(container, SWT.NULL);

      final Label lblDescription = new Label(container, SWT.NULL);
      lblDescription.setText(Diagram_Messages.LB_DESC_Description);

      Text txtDescription = new Text(container, SWT.BORDER | SWT.MULTI | SWT.WRAP);
      txtDescription.setLayoutData(new GridData(GridData.FILL_BOTH));
      txtDescription.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            modelDescription = ((Text) e.widget).getText();
         }
      });
      // ending line with blind label
      new Label(container, SWT.NULL);

      final Label lblFolder = new Label(container, SWT.NULL);
      lblFolder.setText(Diagram_Messages.LB_Folder);

      final Text txtFolder = new Text(container, SWT.BORDER | SWT.SINGLE);
      GridData gd = new GridData(GridData.FILL_HORIZONTAL);
      txtFolder.setLayoutData(gd);
/*      
      txtFolder.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            containerName = ((Text) e.widget).getText();
            dialogChanged();
         }
      });
*/      

      Button btnBrowsFolder = new Button(container, SWT.PUSH);
      btnBrowsFolder.setText(Diagram_Messages.TXT_Browse);
      btnBrowsFolder.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            handleBrowse(txtFolder);
         }
      });

      // no current projekt, disable button
      if (currentProject == false)
      {
         btnBrowsFolder.setEnabled(false);
      }
      
      final Button btnAdvanced = new Button(container, SWT.PUSH);
      btnAdvanced.setText(Diagram_Messages.TXT_AdvancedRight);
      // ending line with blind label
      new Label(container, SWT.NULL).setLayoutData(new GridData(SWT.CENTER, SWT.CENTER,
            false, false, 2, 1));

      // advanced panel
      final Composite pnlAdvanced = new Composite(container, SWT.NULL);
      pnlAdvanced.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
      final GridLayout layoutPnlAdvanced = new GridLayout();
      pnlAdvanced.setLayout(layoutPnlAdvanced);
      layoutPnlAdvanced.numColumns = 2;
      layoutPnlAdvanced.verticalSpacing = 9;

//      
      
      final Label lblModelId = new Label(pnlAdvanced, SWT.NULL);
      lblModelId.setText(Diagram_Messages.LB_ModelAndId);

      final Text txtModelId = new Text(pnlAdvanced, SWT.BORDER | SWT.SINGLE);
      txtModelId.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      txtModelId.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            modelId = ((Text) e.widget).getText();
            dialogChanged();
         }
      });
      txtModelId.setText("New_Workflow_Model"); //$NON-NLS-1$

      txtFolder.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            containerName = ((Text) e.widget).getText();
            dialogChanged();
         }
      });
      
      txtModelName.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            modelName = ((Text) e.widget).getText();
            txtModelId.setText(ModelUtils.computeId(modelName));
            dialogChanged();
         }
      });
      txtModelName.setText(Diagram_Messages.LB_NewWorkflowModel);

      // no current project
      if (currentProject == false)
      {         
         txtFolder.setEnabled(false);
      }
      else
      {
         initializeSelection(txtFolder);
      }
      
      
//
      
      final Label lblAuthor = new Label(pnlAdvanced, SWT.NULL);
      lblAuthor.setText(Diagram_Messages.TXT_Author);

      final Text txtAuthor = new Text(pnlAdvanced, SWT.BORDER | SWT.SINGLE);
      txtAuthor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      txtAuthor.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            modelAuthor = ((Text) e.widget).getText();
         }
      });
      txtAuthor.setText(System.getProperty("user.name")); //$NON-NLS-1$

      final Label lblFileName = new Label(pnlAdvanced, SWT.NULL);
      lblFileName.setText(Diagram_Messages.TXT_FileName);

      final Text txtFileName = new Text(pnlAdvanced, SWT.BORDER | SWT.SINGLE);
      txtFileName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
      txtFileName.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            fileName = ((Text) e.widget).getText();
            dialogChanged();
         }
      });
      pnlAdvanced.setVisible(false);

      btnAdvanced.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            pnlAdvanced.setVisible(!pnlAdvanced.isVisible());
            btnAdvanced.setText(pnlAdvanced.isVisible()
                  ? Diagram_Messages.TXT_AdvancedLeft
                  : Diagram_Messages.TXT_AdvancedRight);

            if (!pnlAdvanced.isVisible())
            {
               txtAuthor.setText(System.getProperty("user.name")); //$NON-NLS-1$
               modelAuthor = txtAuthor.getText();
               txtFileName.setText(""); //$NON-NLS-1$
               fileName = txtFileName.getText();
            }
            else
            {
               txtFileName.setText(txtModelId.getText() + ".xpdl"); //$NON-NLS-1$
               fileName = txtFileName.getText();
            }
         }
      });

      if(!completePage)
      {   
         setPageComplete(false);         
      }
      setControl(container);
   }

   private void initializeSelection(Text containerText)
   {
      if (selection != null && !selection.isEmpty()
            && selection instanceof IStructuredSelection)
      {
         IStructuredSelection ssel = (IStructuredSelection) selection;
         Object obj = ssel.getFirstElement();
         if (obj instanceof IResource)
         {
            setContainerText((IResource) obj, containerText);
         }
         else if (obj instanceof IAdaptable)
         {
            IResource resource = (IResource) ((IAdaptable) obj)
                  .getAdapter(IResource.class);
            setContainerText(resource, containerText);
         }
      }
      else
      {
         IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
         if (root.getProjects().length == 1)
         {
            setContainerText(root.getProjects()[0], containerText);
         }
      }
   }

   private void setContainerText(IResource resource, Text containerText)
   {
      if (resource != null)
      {
         IContainer resourceContainer = resource instanceof IContainer
               ? (IContainer) resource
               : resource.getParent();
         containerText.setText(resourceContainer.getFullPath().toString());
      }
      else
      {
         IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
         if (root.getProjects().length == 1)
         {
            setContainerText(root.getProjects()[0], containerText);
         }
      }
   }

   /**
    * Uses the standard container selection dialog to choose the new value for the
    * container field.
    */

   private void handleBrowse(Text containerText)
   {
      ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(),
            ResourcesPlugin.getWorkspace().getRoot(), false,
            Diagram_Messages.MSG_SelectNewFileContainer);
      if (dialog.open() == Window.OK)
      {
         Object[] result = dialog.getResult();
         if (result.length == 1)
         {
            containerText.setText(((Path) result[0]).toString());
         }
      }
   }

   /**
    * Ensures that both text fields are set.
    */

   private void dialogChanged()
   {
      IResource container = ResourcesPlugin.getWorkspace().getRoot().findMember(
            new Path(getContainerName()));
      String message = null;
      // root is selected
      
      boolean isRoot = container == null || container.getProject() == null;      
      
      // there is a current project open but no container name typed in
      if(containerName.length() == 0 && currentProject)
      {
         // only in developer mode
         if(!DiagramPlugin.isBusinessPerspective())
         {  
            message = Diagram_Messages.NewWorkflowDiagramWizardPage_MSG_FolderMustNotBeEmpty;            
         }
      }
      // there is a current project open but no valid container selected
      else if (currentProject && (container == null || !(container instanceof IContainer) 
            || isRoot))
      {
         message = Diagram_Messages.MSG_FolderMustExist;         
      }      
      // if (containerName.length() == 0)
      // {
      // message = Diagram_Messages.MSG_FolderForNewModelMustBeSpecified;
      // }
      // else
      // if (container == null
      // || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0)
      // {
      // message = Diagram_Messages.MSG_FolderMustExist;
      // }
      // else if ( !container.isAccessible())
      // {
      // message = Diagram_Messages.MSG_FolderMustBeWritable;
      // }
      // else
      if (StringUtils.isEmpty(modelId))
      {
         message = Diagram_Messages.MSG_ValidModelIDMustBeSpecified;
      }
      else if (StringUtils.isEmpty(modelName))
      {
         message = Diagram_Messages.MSG_ValidModelNameMustBeSpecified;
      }
      else if (!StringUtils.isEmpty(fileName))
      {
         if (fileName.replace('\\', '/').indexOf('/', 1) > 0)
         {
            message = Diagram_Messages.MSG_ModelFileNameMustBeValid;
         }
         else
         {
            int dotLoc = fileName.lastIndexOf('.');
            if (dotLoc != -1)
            {
               String ext = fileName.substring(dotLoc + 1);
               if (!(ext.equalsIgnoreCase("xpdl") || ext.equalsIgnoreCase("cwm"))) //$NON-NLS-1$ //$NON-NLS-2$
               {
                  message = Diagram_Messages.MSG_WrongFileExtension;
               }
            }
         }
      }
      setErrorMessage(message);
      if(completePage == true)
      {
         setPageComplete(message == null);         
      }
      else
      {
         setPageComplete(false);         
      }
   }

   public String getModelId()
   {
      return modelId;
   }

   public String getModelName()
   {
      return modelName;
   }

   public String getModelAuthor()
   {
      return modelAuthor;
   }

   public String getModelDescription()
   {
      return modelDescription;
   }

   public String getContainerName()
   {
      return containerName;
   }

   public String getFileName()
   {
      return StringUtils.isEmpty(fileName) ? getModelId() + ".xpdl" : fileName; //$NON-NLS-1$
   }
}