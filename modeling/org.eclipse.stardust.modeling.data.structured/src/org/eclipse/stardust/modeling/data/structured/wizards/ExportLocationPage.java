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

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * Export location page
 */
public class ExportLocationPage extends WizardPage
{
   private SelectSingleFolderView folderView;

   protected boolean folderSelected;

   public ExportLocationPage()
   {
      super("LocationPage"); //$NON-NLS-1$
      setPageComplete(false);
   }

   public void createControl(Composite parent)
   {
      Composite client = FormBuilder.createComposite(parent, 1);

      folderView = new SelectSingleFolderView(null, true, null);
      SelectSingleFolderView.Listener listener = new SelectSingleFolderView.Listener()
      {
         public void setControlComplete(boolean isComplete)
         {
            folderSelected = isComplete;
            setPageComplete(folderSelected);
         }
      };
      folderView.setListener(listener);
      final Control control = folderView.createControl(client);
      control.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData(1));
      folderView.setVisibleHelper(true);
      
      setControl(client);
   }

   public IFolder getSaveFolder()
   {
      return folderView.getFolder();
   }
}