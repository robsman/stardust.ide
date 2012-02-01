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

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class ImportCarnotModelRepositoryWizard extends Wizard
   implements IImportWizard
{
   private IStructuredSelection selection;

   private ImportRepositoryWizardPage page;
   private IWorkbench workbench;
   private static final String WIZARD_NAME = Import_Messages.NAME_ImportWiz;

   public ImportCarnotModelRepositoryWizard()
   {
      ImportPlugin plugin = ImportPlugin.getDefault();
      IDialogSettings workbenchSettings = plugin.getDialogSettings();
      IDialogSettings section = workbenchSettings
              .getSection(WIZARD_NAME);
      if (section == null)
          section = workbenchSettings.addNewSection(WIZARD_NAME);
      setDialogSettings(section);
   }

   public boolean performFinish()
   {
      return page.performFinish();
   }

   public void init(IWorkbench workbench, IStructuredSelection selection)
   {
      this.workbench = workbench;
      this.selection = selection;
      this.setWindowTitle(Import_Messages.TITLE_ImportWiz);
   }

   public void addPages()
   {
      page = new ImportRepositoryWizardPage(WIZARD_NAME, workbench, selection);
      addPage(page);
   }
}
