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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;

public class XSDSelectSingleFilePage extends SelectSingleFilePage
{
   private final ImportFromSchemaWizard importFromSchemaWizard;

   public XSDSelectSingleFilePage(ImportFromSchemaWizard importFromSchemaWizard,
         IWorkbench workbench, IStructuredSelection selection, boolean isFileMandatory)
   {
      super(workbench, selection, isFileMandatory, importFromSchemaWizard);
      this.importFromSchemaWizard = importFromSchemaWizard;
   }

   private boolean openExternalSchema()
   {
      // Get the fully-qualified file name
      IFile file = getFile();
      if (file == null)
      {
         return false;
      }

      setErrorMessage(null);

      String xsdModelFile = file.getLocationURI().toString();
      String xsdFileName = file.getName();
      String errorMessage = this.importFromSchemaWizard.doLoadExternalModel(
            new NullProgressMonitor(), xsdModelFile, xsdFileName);

      if (errorMessage != null)
      {
         setErrorMessage(errorMessage);
         return false;
      }
      else
      {
         return true;
      }
   }

   public boolean isPageComplete()
   {
      if (super.isPageComplete())
      {
         return openExternalSchema();
      }

      return super.isPageComplete();
   }
}