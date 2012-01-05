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
package org.eclipse.stardust.modeling.debug.launching.ui;

import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.Assert;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.CwmFileSelectionDialog;
import org.eclipse.stardust.modeling.debug.DebugPlugin;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.ModelElementSelectionDialog;
import org.eclipse.stardust.modeling.debug.debugger.UiAccessor;
import org.eclipse.stardust.modeling.debug.launching.LaunchConfigUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ResourceListSelectionDialog;

public class MainTab extends AbstractLaunchConfigurationTab
{
   private Text projectText;
   private Button projectButton;
   private Text modelText;
   private Button modelButton;
   private Text processDefinitionText;
   private Button processDefinitionButton;

   public void createControl(Composite parent)
   {
      Font font = parent.getFont();

      Composite comp = new Composite(parent, SWT.NONE);
      setControl(comp);
      GridLayout topLayout = new GridLayout();
      topLayout.verticalSpacing = 0;
      topLayout.numColumns = 3;
      comp.setLayout(topLayout);
      comp.setFont(font);

      createVerticalSpacer(comp, 3);

      Label programLabel = new Label(comp, SWT.NONE);
      programLabel.setText(Debug_Messages.LB_Project);
      GridData gd = new GridData(GridData.BEGINNING);
      programLabel.setLayoutData(gd);
      programLabel.setFont(font);

      projectText = new Text(comp, SWT.SINGLE | SWT.BORDER);
      gd = new GridData(GridData.FILL_HORIZONTAL);
      projectText.setLayoutData(gd);
      projectText.setFont(font);
      projectText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateLaunchConfigurationDialog();
         }
      });

      projectButton = createPushButton(comp, Debug_Messages.LB_Browse, null);
      projectButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            browseProjects();
         }
      });

      createVerticalSpacer(comp, 3);

      programLabel = new Label(comp, SWT.NONE);
      programLabel.setText(Debug_Messages.LB_Model);
      gd = new GridData(GridData.BEGINNING);
      programLabel.setLayoutData(gd);
      programLabel.setFont(font);

      modelText = new Text(comp, SWT.SINGLE | SWT.BORDER);
      gd = new GridData(GridData.FILL_HORIZONTAL);
      modelText.setLayoutData(gd);
      modelText.setFont(font);
      modelText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateLaunchConfigurationDialog();
         }
      });

      modelButton = createPushButton(comp, Debug_Messages.LB_Browse, null);
      modelButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            browseModelFiles();
         }
      });

      createVerticalSpacer(comp, 3);

      Label pdLabel = new Label(comp, SWT.NONE);
      pdLabel.setText(Debug_Messages.LB_ProcessDefinition);
      gd = new GridData(GridData.BEGINNING);
      pdLabel.setLayoutData(gd);
      pdLabel.setFont(font);

      processDefinitionText = new Text(comp, SWT.SINGLE | SWT.BORDER);
      gd = new GridData(GridData.FILL_HORIZONTAL);
      processDefinitionText.setLayoutData(gd);
      processDefinitionText.setFont(font);
      processDefinitionText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateLaunchConfigurationDialog();
         }
      });

      processDefinitionButton = createPushButton(comp, Debug_Messages.LB_Browse, null);
      processDefinitionButton.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            browseProcessDefinitions();
         }
      });
   }

   public void setDefaults(ILaunchConfigurationWorkingCopy configuration)
   {
      // These settings are normally done by {@link JavaMainTab}
      IJavaElement javaElement = getContext();
      if (javaElement != null)
      {
         initializeJavaProject(javaElement, configuration);
      }
      else
      {
         // We set empty attributes for project & main type so that when one config is
         // compared to another, the existence of empty attributes doesn't cause an
         // incorrect result (the performApply() method can result in empty values
         // for these attributes being set on a config if there is nothing in the
         // corresponding text boxes)
         LaunchConfigUtils.setProjectName(configuration, Constants.EMPTY);
      }
      LaunchConfigUtils.setDefaultAttributes(configuration);
   }

   public void initializeFrom(ILaunchConfiguration configuration)
   {
      try
      {
         String project = LaunchConfigUtils.getProjectName(configuration);
         if (project != null)
         {
            projectText.setText(project);
         }

         String model = LaunchConfigUtils.getModelFileName(configuration);
         if (model != null)
         {
            modelText.setText(model);
         }

         String procDef = LaunchConfigUtils.getProcessDefinitionId(configuration);
         if (procDef != null)
         {
            processDefinitionText.setText(procDef);
         }
      }
      catch (CoreException e)
      {
         setErrorMessage(e.getMessage());
      }
   }

   public void performApply(ILaunchConfigurationWorkingCopy configuration)
   {
      LaunchConfigUtils.setProjectName(configuration, projectText.getText());
      LaunchConfigUtils.setModelFileName(configuration, modelText.getText());
      LaunchConfigUtils.setProcessDefinitionId(configuration, processDefinitionText.getText());
      LaunchConfigUtils.setProgramArguments(configuration, projectText.getText(),
            modelText.getText(), processDefinitionText.getText());
//      LaunchConfigUtils.setDefaultAttributes(configuration);
   }

   public String getName()
   {
      return "Main"; //$NON-NLS-1$
   }

   public boolean isValid(ILaunchConfiguration launchConfig)
   {
      setErrorMessage(null);

      String projectName = projectText.getText().trim();
      if (StringUtils.isEmpty(projectName))
      {
         setErrorMessage(Debug_Messages.ERR_SpecifyProject);
         return false;
      }

      IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
      if (null == project)
      {
         setErrorMessage(Debug_Messages.ERR_SpecifiedProjectDoesNotExist);
         return false;
      }

      String model = modelText.getText().trim();
      if (StringUtils.isEmpty(model))
      {
         setErrorMessage(Debug_Messages.ERR_SpecifyModelFile);
         return false;
      }

      IResource resource = project.findMember(model);
      if (null == resource)
      {
         setErrorMessage(Debug_Messages.ERR_SpecifiedModelFileDoesNotExist);
         return false;
      }

      String pd = processDefinitionText.getText().trim();
      if (StringUtils.isEmpty(pd))
      {
         setErrorMessage(Debug_Messages.ERR_SpecifyProcessDefinitionID);
         return false;
      }

      // TODO (sb): process definition validation (does it exist in the given model?)

      return super.isValid(launchConfig);
   }

   public Image getImage()
   {
      return DebugPlugin.getImage("icons/full/obj16/process.gif"); //$NON-NLS-1$
   }

   /**
    * Adapted from {@link org.eclipse.jdt.debug.ui.launchConfigurations.JavaLaunchTab#getContext()}
    *
    * Returns the current Java element context from which to initialize
    * default settings, or <code>null</code> if none.
    *
    * @return Java element context.
    */
   private IJavaElement getContext()
   {
      IWorkbenchWindow workbench = UiAccessor.getActiveWorkbenchWindow();

      if (null == workbench)
      {
         return null;
      }

      IWorkbenchPage page = workbench.getActivePage();
      if (page != null)
      {
         ISelection selection = page.getSelection();
         if (selection instanceof IStructuredSelection)
         {
            IStructuredSelection ss = (IStructuredSelection) selection;
            if ( !ss.isEmpty())
            {
               Object obj = ss.getFirstElement();
               if (obj instanceof IJavaElement)
               {
                  return (IJavaElement) obj;
               }
               if (obj instanceof IResource)
               {
                  IJavaElement je = JavaCore.create((IResource) obj);
                  if (je == null)
                  {
                     IProject pro = ((IResource) obj).getProject();
                     je = JavaCore.create(pro);
                  }
                  if (je != null)
                  {
                     return je;
                  }
               }
            }
         }

         IEditorPart part = page.getActiveEditor();
         if (part != null)
         {
            if (part instanceof WorkflowModelEditor)
            {
               IEditorInput input = part.getEditorInput();
               IFile file = (IFile) input.getAdapter(IFile.class);
               if (null != file)
               {
                  IProject project = file.getProject();
                  IJavaElement je = JavaCore.create(project);
                  if (null != je)
                  {
                     return je;
                  }
               }
            }
            else
            {
               IEditorInput input = part.getEditorInput();
               return (IJavaElement) input.getAdapter(IJavaElement.class);
            }
         }
      }
      return null;
   }

   /**
    * Set the java project attribute based on the IJavaElement.
    */
   private void initializeJavaProject(IJavaElement javaElement,
         ILaunchConfigurationWorkingCopy config)
   {
      IJavaProject javaProject = javaElement.getJavaProject();
      String name = null;
      if (javaProject != null && javaProject.exists())
      {
         name = javaProject.getElementName();
      }

      LaunchConfigUtils.setProjectName(config, name);
   }

   private void browseProjects()
   {
      IJavaProject[] projects;
      try
      {
         projects = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot())
               .getJavaProjects();
      }
      catch (JavaModelException e)
      {
         projects = new IJavaProject[0];
      }

      ILabelProvider labelProvider = new JavaElementLabelProvider(
            JavaElementLabelProvider.SHOW_DEFAULT);
      ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(),
            labelProvider);
      dialog.setTitle(Debug_Messages.TITLE_CarnotWorkflowModel);
      dialog.setMessage(Debug_Messages.TESTCHANGEKEY);
      dialog.setElements(projects);

      String projectName = projectText.getText();
      if ( !StringUtils.isEmpty(projectName))
      {
         IJavaProject javaProject = JavaCore.create(ResourcesPlugin.getWorkspace()
               .getRoot().getProject(projectName));
         if (javaProject != null)
         {
            dialog.setInitialSelections(new Object[] { javaProject });
         }
      }

      if (dialog.open() == Window.OK)
      {
         IJavaProject javaProject = (IJavaProject) dialog.getFirstResult();
         if (null != javaProject)
         {
            projectText.setText(javaProject.getElementName());
         }
         else
         {
            projectText.setText(Constants.EMPTY);
         }
      }
   }

   /**
    * Open a resource chooser to select a model file
    */
   private void browseModelFiles()
   {
      String projectName = projectText.getText();
      Assert.isNotEmpty(projectName, Debug_Messages.TXT_ProjectHasToBeSpecified);

      ResourceListSelectionDialog dialog = new CwmFileSelectionDialog(getShell(),
            ResourcesPlugin.getWorkspace().getRoot().getProject(projectName),
            IResource.FILE);
      dialog.setTitle(Debug_Messages.TITLE_CarnotWorkflowModel);
      dialog.setMessage(MessageFormat.format(Debug_Messages.MSG_SelectWorkflowModelInProject,
            new String[] { projectName }));

      // TODO: single select
      if (dialog.open() == Window.OK)
      {
         Object[] files = dialog.getResult();
         IFile file = (IFile) files[0];
         String fullPath = file.getFullPath().toString();
         modelText.setText(fullPath.substring(projectName.length() + 2));
      }
   }

   /**
    * Open a process definition chooser
    */
   private void browseProcessDefinitions()
   {
      String projectName = projectText.getText();
      Assert.isNotEmpty(projectName, Debug_Messages.TXT_ProjectHasToBeSpecified);

      String modelFileName = modelText.getText();
      Assert.isNotEmpty(modelFileName, Debug_Messages.TXT_ModelFileHasToBeSpecified);

      ModelElementSelectionDialog dialog = new ModelElementSelectionDialog(getShell(),
            ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getFile(modelFileName),
            new Class[]{ProcessDefinitionType.class});
      dialog.setTitle(Debug_Messages.TITLE_CarnotWorkflowModel);
      dialog.setMessage(MessageFormat.format(Debug_Messages.MSG_SelectProcessDefinitionFromModelFile,
            new String[] { modelFileName }));

      // TODO: single select
      if (dialog.open() == Window.OK)
      {
         Object[] modelElements = dialog.getResult();
         ProcessDefinitionType processDefinition = (ProcessDefinitionType) modelElements[0];
         processDefinitionText.setText(processDefinition.getId());
      }
   }
}
