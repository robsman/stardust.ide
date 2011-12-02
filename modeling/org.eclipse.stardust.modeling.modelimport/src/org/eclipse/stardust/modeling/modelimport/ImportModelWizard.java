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
package org.eclipse.stardust.modeling.modelimport;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

public class ImportModelWizard extends Wizard implements IImportWizard
{
   protected IStructuredSelection selection;

   protected IWorkbench workbench;

   private ImportModelWizardPage importModelWizardPage;

   public ImportModelWizard()
   {
      setWindowTitle(ImportMessages.DESC_ImportFile);
      String WIZARD_NAME = ImportMessages.NAME_ImportCarnotProcessModelWiz;

      ImportPlugin plugin = ImportPlugin.getDefault();
      IDialogSettings workbenchSettings = plugin.getDialogSettings();
      IDialogSettings section = workbenchSettings.getSection(WIZARD_NAME);
      if (section == null)
      {
         section = workbenchSettings.addNewSection(WIZARD_NAME);
      }
      setDialogSettings(section);
   }

   public boolean performFinish()
   {
      return ((IImportModelWizardPage) getContainer().getCurrentPage()).performFinish();
   }

   public void init(IWorkbench workbench, IStructuredSelection currentSelection)
   {
      this.workbench = workbench;
      this.selection = currentSelection;
      
      List selectedResources = IDE.computeSelectedResources(currentSelection);
      if (!selectedResources.isEmpty())
      {
         this.selection = new StructuredSelection(selectedResources);
      }
   }

   public void addPages()
   {
      importModelWizardPage = new ImportModelWizardPage(ImportMessages.NAME_CarnotWorkflowImportPage,
            selection);
      addPage(importModelWizardPage);
   }

   public ImportModelWizardPage getModelImportPage()
   {
      return importModelWizardPage;
   }

   public void openEditor(IProgressMonitor monitor, final IFile file, final boolean reopen)
   {
      monitor.setTaskName(ImportMessages.TASK_OpenFile);
      getShell().getDisplay().asyncExec(new Runnable()
      {
         public void run()
         {
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                  .getActivePage();

            try
            {
               IEditorPart part = page.findEditor(new FileEditorInput(file));

               // editor is open
               if (part != null && reopen)
               {
                  page.closeEditor(part, true);
               }

               IDE.openEditor(page, file, true);

            }
            catch (PartInitException e)
            {
            }
         }
      });
      monitor.worked(2);
   }
}