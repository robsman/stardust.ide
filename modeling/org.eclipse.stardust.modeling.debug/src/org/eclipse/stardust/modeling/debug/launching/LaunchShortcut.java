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
package org.eclipse.stardust.modeling.debug.launching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.modeling.common.platform.utils.WorkspaceUtils;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.debug.Constants;
import org.eclipse.stardust.modeling.debug.Internal_Debugger_Messages;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public class LaunchShortcut implements ILaunchShortcut
{
   public void launch(ISelection selection, String mode)
   {
      if (selection instanceof IStructuredSelection)
      {
         IStructuredSelection sel = (IStructuredSelection) selection;
         if (sel.size() == 1)
         {
            Object object = sel.getFirstElement();
            if (object instanceof IAdaptable)
            {
               IAdaptable adaptable = (IAdaptable) object;
               IModelElement element = (IModelElement) adaptable.getAdapter(
                     IModelElement.class);
               if (element != null)
               {
                  ProcessDefinitionType type = ModelUtils.findContainingProcess(element);
                  if (type != null)
                  {
                     launch(type, mode);
                  }
               }
            }
         }
      }
   }

   public void launch(IEditorPart editor, String mode)
   {
      if (editor instanceof WorkflowModelEditor)
      {
         WorkflowModelEditor wfmEditor = (WorkflowModelEditor) editor;
         DiagramType diagram = wfmEditor.getActiveDiagram();
         if (diagram != null)
         {
            launch(diagram, mode);
         }
      }
      else if (editor instanceof DiagramEditorPage)
      {
         DiagramEditorPage page = (DiagramEditorPage) editor; 
         launch(page.getDiagram(), mode);
      }
   }

   protected void launch(DiagramType diagram, String mode)
   {
      EObject container = diagram.eContainer();
      if (container instanceof ProcessDefinitionType)
      {
         launch((ProcessDefinitionType) container, mode);
      }
   }

   /**
    * Launches a configuration for the given type
    */
   protected void launch(ProcessDefinitionType type, String mode)
   {
      ILaunchConfiguration config = findLaunchConfiguration(type, getConfigurationType());
      if (config != null)
      {
         try
         {
            ILaunchConfigurationWorkingCopy wc = config.getWorkingCopy();
            LaunchConfigUtils.setDependencies(wc, getDependencies(type));
            DebugUITools.launch(wc, mode);
         }
         catch (CoreException e)
         {
            DebugPlugin.log(e);
         }
      }
   }

   private List<String> getDependencies(ProcessDefinitionType process)
   {
      List<String> deps = CollectionUtils.newList();
      ModelType model = ModelUtils.findContainingModel(process);
      if (model != null)
      {
         Set<ModelType> visited = CollectionUtils.newSet();
         collectDependencies(visited, model, deps);
      }
      return deps.isEmpty() ? null : deps;
   }

   private void collectDependencies(Set<ModelType> visited, ModelType model, List<String> deps)
   {
      visited.add(model);
      ExternalPackages externals = model.getExternalPackages();
      if (externals != null)
      {
         for (ExternalPackage pack : externals.getExternalPackage())
         {
            ModelType externalModel = ModelUtils.getExternalModel(pack);
            if (externalModel != null && !visited.contains(externalModel))
            {
               String location = WorkspaceUtils.getLocation((ModelType) externalModel);
               if (location != null)
               {
                  deps.add(location);
               }
               collectDependencies(visited, (ModelType) externalModel, deps);
            }
         }
      }
   }

   /**
    * Returns the type of configuration this shortcut is applicable to.
    * 
    * @return the type of configuration this shortcut is applicable to
    */
   protected ILaunchConfigurationType getConfigurationType()
   {
      ILaunchManager lm = DebugPlugin.getDefault().getLaunchManager();
      return lm.getLaunchConfigurationType(Constants.ID_CWM_LAUNCH);
   }

   /**
    * Locate a configuration to relaunch for the given type. If one cannot be found,
    * create one.
    * 
    * @return a re-useable config or <code>null</code> if none
    */
   protected ILaunchConfiguration findLaunchConfiguration(ProcessDefinitionType type,
         ILaunchConfigurationType configType)
   {
      String projectName = null;
      String modelFilePath = null;
      Resource eResource = type.eResource();
      if (eResource != null)
      {
         URI eUri = eResource.getURI();
         URI projectUri = eUri.trimSegments(eUri.segmentCount() - 2);
         URI modelUri = eUri.deresolve(projectUri);
         IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(
            eUri.segment(1));
         IProject project = null;
         if (resource instanceof IProject)
         {
            project = (IProject) resource;
         }
         else if (resource != null)
         {
            project = resource.getProject();
         }
         if (project != null)
         {
            projectName = project.getName();
            modelFilePath = modelUri.toString();
            if (modelFilePath.startsWith(projectName + "/")) //$NON-NLS-1$
            {
               modelFilePath = modelFilePath.substring(projectName.length() + 1);
            }
         }
      }
      if (modelFilePath == null || projectName == null)
      {
         return null;
      }
      List candidateConfigs = Collections.EMPTY_LIST;
      try
      {
         ILaunchConfiguration[] configs = DebugPlugin.getDefault().getLaunchManager()
               .getLaunchConfigurations(configType);
         candidateConfigs = new ArrayList(configs.length);
         for (int i = 0; i < configs.length; i++)
         {
            ILaunchConfiguration config = configs[i];
            if (projectName.equals(LaunchConfigUtils.getProjectName(config))) //$NON-NLS-1$
            {
               if (modelFilePath.equals(LaunchConfigUtils.getModelFileName(config))) //$NON-NLS-1$
               {
                  if (type.getId().equals(LaunchConfigUtils.getProcessDefinitionId(config))) //$NON-NLS-1$
                  {
                     candidateConfigs.add(config);
                  }
               }
            }
         }
      }
      catch (CoreException e)
      {
         DebugPlugin.log(e);
      }

      // If there are no existing configs associated with the IType, create one.
      // If there is exactly one config associated with the IType, return it.
      // Otherwise, if there is more than one config associated with the IType, prompt the
      // user to choose one.
      int candidateCount = candidateConfigs.size();
      if (candidateCount < 1)
      {
         return createConfiguration(type, modelFilePath, projectName);
      }
      else if (candidateCount == 1)
      {
         return (ILaunchConfiguration) candidateConfigs.get(0);
      }
      else
      {
         // Prompt the user to choose a config. A null result means the user
         // cancelled the dialog, in which case this method returns null,
         // since cancelling the dialog should also cancel launching anything.
         ILaunchConfiguration config = chooseConfiguration(candidateConfigs);
         if (config != null)
         {
            return config;
         }
      }
      return null;
   }

   protected ILaunchConfiguration createConfiguration(ProcessDefinitionType type,
         String modelFilePath, String projectName)
   {
      ILaunchConfiguration config = null;
      ILaunchConfigurationWorkingCopy wc = null;
      try
      {
         String prefix = type.getId();
         ILaunchConfigurationType configType = getConfigurationType();
         wc = configType.newInstance(null, getLaunchManager()
               .generateUniqueLaunchConfigurationNameFrom(prefix));
         
         LaunchConfigUtils.setProjectName(wc, projectName);
         LaunchConfigUtils.setModelFileName(wc, modelFilePath);
         LaunchConfigUtils.setProcessDefinitionId(wc, type.getId());
         LaunchConfigUtils.setProgramArguments(wc, projectName, modelFilePath, type.getId());
         LaunchConfigUtils.setDefaultAttributes(wc);

         config = wc.doSave();
      }
      catch (CoreException exception)
      {
         reportError(exception);
      }
      return config;
   }

   /**
    * Show a selection dialog that allows the user to choose one of the specified launch
    * configurations. Return the chosen config, or <code>null</code> if the user
    * cancelled the dialog.
    */
   protected ILaunchConfiguration chooseConfiguration(List configList)
   {
      IDebugModelPresentation labelProvider = DebugUITools.newDebugModelPresentation();
      ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(),
            labelProvider);
      dialog.setElements(configList.toArray());
      dialog.setTitle(Internal_Debugger_Messages.getString("Launching_DialogTitle")); //$NON-NLS-1$
      dialog.setMessage(Internal_Debugger_Messages.getString("Launching_DialogMessage")); //$NON-NLS-1$
      dialog.setMultipleSelection(false);
      int result = dialog.open();
      labelProvider.dispose();
      if (result == Window.OK)
      {
         return (ILaunchConfiguration) dialog.getFirstResult();
      }
      return null;
   }

   /**
    * Opens an error dialog on the given excpetion.
    * 
    * @param exception
    */
   protected void reportError(CoreException exception)
   {
      MessageDialog.openError(getShell(), Internal_Debugger_Messages.getString("Launching_Error"), //$NON-NLS-1$
            exception.getStatus().getMessage());
   }

   /**
    * Convenience method to get the window that owns this action's Shell.
    */
   protected Shell getShell()
   {
      return org.eclipse.stardust.modeling.debug.DebugPlugin.getActiveWorkbenchShell();
   }

   /**
    * Convenience method to get the launch manager.
    */
   protected ILaunchManager getLaunchManager()
   {
      return DebugPlugin.getDefault().getLaunchManager();
   }
}
