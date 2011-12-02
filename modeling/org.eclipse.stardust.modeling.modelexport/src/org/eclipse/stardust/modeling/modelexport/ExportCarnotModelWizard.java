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

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;

public class ExportCarnotModelWizard extends Wizard implements IExportWizard
{
   private ExportCarnotModelWizardPage modelExportPage;   
   protected IStructuredSelection selection;
   protected IWorkbench workbench;
   
   public ExportCarnotModelWizard()
   {
      super();
      setWindowTitle(ExportMessages.DESC_ExportModel);
   }

   public boolean performFinish()
   {
      return modelExportPage.performFinish();
   }

   @SuppressWarnings("unchecked")
   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.workbench = workbench;
      this.selection = selection;

      List<IResource> selectedResources = IDE.computeSelectedResources(selection);
      if (!selectedResources.isEmpty())
      {
         this.selection = new StructuredSelection(selectedResources);
      }
   }
   
   public void addPages()
   {
      modelExportPage = new ExportCarnotModelWizardPage("CarnotWorkflowModelExport", //$NON-NLS-1$
            selection);
      addPage(modelExportPage);
   } 
}