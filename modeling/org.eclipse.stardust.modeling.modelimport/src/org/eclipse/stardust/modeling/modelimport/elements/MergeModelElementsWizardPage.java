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

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.modelimport.IImportModelWizardPage;
import org.eclipse.stardust.modeling.modelimport.ImportMessages;
import org.eclipse.stardust.modeling.modelimport.ImportPlugin;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;


public class MergeModelElementsWizardPage extends WizardPage
      implements IImportModelWizardPage
{
   private final static String IMG_WIZBAN_IMPORT_WIZ = "IMG_WIZBAN_IMPORT_WIZ"; //$NON-NLS-1$

   private ImportModelElementsWizardPage sourcePage;
   private MergeEditorInput input;
   private Composite composite;

   protected MergeModelElementsWizardPage(String pageName, ImportModelElementsWizardPage sourcePage)
   {
      super(pageName);
      setTitle(ImportMessages.MergeModelElementsWizardPage_Title); // TODO
      setDescription(ImportMessages.MergeModelElementsWizardPage_Comment);
      // TODO: replace with CARNOT EXP specific image
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(IMG_WIZBAN_IMPORT_WIZ));
      this.sourcePage = sourcePage;
   }

   public IProject getProjectContext()
   {
      return sourcePage.getProjectContext();
   }

   public IDialogSettings getWizardSettings()
   {
      return getDialogSettings();
   }

   // when user presses finish
   public boolean performFinish()
   {
      boolean result = true;
      try
      {
         input.saveChanges(null);
      }
      catch (CoreException e)
      {
         ErrorDialog.openError(getShell(), ImportMessages.MergeModelElementsWizardPage_ERROR_DIALOG_TITLE, e.getMessage(), e.getStatus());
         result = false;
      }
      return result;
   }

   public void updateButtons()
   {
      getWizard().getContainer().updateButtons();
   }

   // change size (layout)
   public void createControl(Composite parent)
   {
      composite = FormBuilder.createComposite(parent, 1);
      setControl(composite);      
      
      IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
      IDialogSettings wizardSettings = workbenchSettings.getSection("ShowViewDialog"); //$NON-NLS-1$
      Point loc = null;
      Point size = null;
      if (wizardSettings == null)
      {
          loc = getShell().getLocation();
          size = getShell().getSize();
      }
      else
      {      
	      int height = wizardSettings.getInt("DIALOG_HEIGHT"); //$NON-NLS-1$
	      int width = wizardSettings.getInt("DIALOG_WIDTH"); //$NON-NLS-1$
	      size = new Point(width * 2, height * 2);
	      
	      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	      loc = new Point(new Integer((int) dim.getWidth()).intValue(), 
	            new Integer((int) dim.getHeight()).intValue());
	      loc.x -= size.x;
	      loc.y -= size.y;
	      loc.x /= 2;
	      loc.y /= 2;
      }
            
      getShell().setLocation(loc);
      getShell().setSize(size);
   }

   // called by eclipse?
   public void setVisible(boolean visible)
   {      
      if (visible)
      {
         boolean isNew = input == null;
         // get input - MergeEditorInput - (also sets source and target)
         // MergeEditorInput opens a dialog
         input = sourcePage.getInput();
  
         if (isNew)
         {
            Control control = input.createContents(composite);
            GridData gd = new GridData(GridData.FILL_BOTH);
            control.setLayoutData(gd);
            composite.layout(true, true);
         }
         else
         {
            input.getDiffViewer().setInput(input.getCompareResult());
            input.contentChanged();
         }
      }      
      super.setVisible(visible);
   }
   
   public boolean isPageComplete()
   {
      return input != null && input.isDirty();
   }

   /**
    * we overwrite this method, in this case only to view a message when there is nothing to merge
    */
   public boolean canFlipToNextPage()
   {
      boolean canFlip = super.canFlipToNextPage();
      // a message should appear, when there is nothing to merge
      if(input != null && input.getCompareResult() == null && input.getDiffViewer() == null)
      {
         WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
         Shell shell = editor.getSite().getShell();
         MessageDialog.openInformation(shell, ImportMessages.MergeModelElementsWizardPage_InfoDialogTitle, ImportMessages.MergeModelElementsWizardPage_InfoDialogText);            
      }
      return canFlip;
   }
}