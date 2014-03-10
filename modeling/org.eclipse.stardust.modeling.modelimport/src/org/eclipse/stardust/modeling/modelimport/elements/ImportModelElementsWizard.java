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
package org.eclipse.stardust.modeling.modelimport.elements;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.modelimport.IImportModelWizardPage;
import org.eclipse.stardust.modeling.modelimport.ImportPlugin;
import org.eclipse.stardust.modeling.modelimport.Import_Messages;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;


public class ImportModelElementsWizard extends Wizard implements IImportWizard
{
   public ImportModelElementsWizard()
   {
      super();
      setWindowTitle(Import_Messages.DESC_CarnotFileImport);
   }

   private ImportModelElementsWizardPage importModelElementsWizardPage;
   private MergeModelElementsWizardPage mergeModelElementsWizardPage;

   public boolean performFinish()
   {
      return ((IImportModelWizardPage) getContainer().getCurrentPage()).performFinish();
   }

   public void init(IWorkbench workbench, IStructuredSelection currentSelection)
   {
      ImportPlugin.resetExtensions();

      @SuppressWarnings("unchecked")
      List<IResource> selectedResources = IDE.computeSelectedResources(currentSelection);
      Object selection = selectedResources.isEmpty() ? currentSelection.getFirstElement() : selectedResources.get(0);
      IEditorPart editorPart = null;

      if (currentSelection.getFirstElement() instanceof IEditorPart)
      {
         editorPart = (IEditorPart) currentSelection.getFirstElement();
      }
      if (editorPart instanceof WorkflowModelEditor)
      {
         WorkflowModelEditor wme = (WorkflowModelEditor) editorPart;
         ModelType model_ = wme.getWorkflowModel();
         URI modelURI = model_.eResource().getURI();
         if (modelURI.isPlatformResource())
         {
            Path path = new Path(modelURI.toPlatformString(true));
            IResource modelResource = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
            if (selectedResources.isEmpty() || modelResource.equals(selectedResources.get(0)))
            {
               selection = wme.getModelManager();
               selectedResources = Collections.singletonList(modelResource);
            }
         }
      }

      if (selectedResources.isEmpty())
      {
         MessageDialog.openInformation(getShell(), Import_Messages.MSG_InvalidSel,
               Import_Messages.MSG_SelectModel);
      }
      else
      {
         importModelElementsWizardPage = new ImportModelElementsWizardPage(
               Import_Messages.LB_ElementsImport, selection);
         mergeModelElementsWizardPage = new MergeModelElementsWizardPage("merge",  //$NON-NLS-1$
               importModelElementsWizardPage);

         final WorkflowModelManager target = importModelElementsWizardPage.getTarget();
         if (target != null)
         {
            if (target.getModel() == null)
            {
               try
               {
                  ProgressMonitorDialog dialog = new ProgressMonitorDialog(getShell());
                  IRunnableWithProgress loader = new IRunnableWithProgress()
                  {
                     public void run(IProgressMonitor monitor)
                           throws InvocationTargetException, InterruptedException
                     {
                        MergeEditorInput.load(monitor, target, target.toString());
                     }
                  };
                  dialog.run(false, true, loader);
               }
               catch (InvocationTargetException e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               catch (InterruptedException e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }
         }
      }
   }

   public void addPages()
   {
      addPage(importModelElementsWizardPage);
      addPage(mergeModelElementsWizardPage);
   }
}