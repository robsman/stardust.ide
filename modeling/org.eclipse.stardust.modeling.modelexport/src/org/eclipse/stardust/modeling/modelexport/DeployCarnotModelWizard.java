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

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.ide.IDE;

public class DeployCarnotModelWizard extends Wizard implements IExportWizard
{
   private static final String WIZARD_NAME = "DeployCarnotModelWizard"; //$NON-NLS-1$

   private IStructuredSelection selection;

   private SelectCarnotModelWizardPage modelSelectionPage;

   public DeployCarnotModelWizard()
   {
      ExportPlugin plugin = ExportPlugin.getDefault();
      IDialogSettings workbenchSettings = plugin.getDialogSettings();
      IDialogSettings section = workbenchSettings.getSection(WIZARD_NAME);
      if (section == null)
      {
         section = workbenchSettings.addNewSection(WIZARD_NAME);
      }
      setDialogSettings(section);
      setWindowTitle(ExportMessages.TITLE_DeployModelWiz);
   }

   public boolean performFinish()
   {
      modelSelectionPage.saveWidgetValues();

      return modelSelectionPage.deployModel();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.selection = selection;

      List<?> selectedResources = IDE.computeSelectedResources(selection);
      if (!selectedResources.isEmpty())
      {
         this.selection = new StructuredSelection(selectedResources);
      }
	}

   public void addPages()
   {
      modelSelectionPage = new SelectCarnotModelWizardPage(
         "SelectCarnotWorkflowModel", selection); //$NON-NLS-1$

      addPage(modelSelectionPage);
   }
}
