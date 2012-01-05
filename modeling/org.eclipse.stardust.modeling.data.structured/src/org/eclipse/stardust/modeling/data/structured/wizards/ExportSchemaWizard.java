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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.util.XSDResourceImpl;

import ag.carnot.bpm.rt.data.structured.StructuredDataConstants;

/**
 * Extend the base wizard to select a file from the project or outside the workbench and
 * add error handling
 */
public class ExportSchemaWizard extends Wizard implements INewWizard
{
   private static final String XSD_FILE_SUFFIX = ".xsd"; //$NON-NLS-1$
   private TypeDeclarationsSelectionPage typesPage;
   private ExportLocationPage locationPage;

   public ExportSchemaWizard(TypeDeclarationType typeDeclaration)
   {
      String title = Structured_Messages.ExportSchemaWizardTitle;
      setWindowTitle(title);
      setDefaultPageImageDescriptor(DiagramPlugin.imageDescriptorFromPlugin(
            "org.eclipse.xsd.editor", "icons/full/wizban/NewXSD.gif")); //$NON-NLS-1$ //$NON-NLS-2$

      setNeedsProgressMonitor(true);
      
      typesPage = new TypeDeclarationsSelectionPage(typeDeclaration);
      typesPage.setTitle(title);
      typesPage.setDescription(Structured_Messages.TypesPageDescription);

      locationPage = new ExportLocationPage();
      locationPage.setTitle(title);
      locationPage.setDescription(Structured_Messages.LocationPageDescription);
   }

   public void init(IWorkbench aWorkbench, IStructuredSelection aSelection)
   {}

   public void addPages()
   {
      if (typesPage.getTypes2Save().size() > 1)
      {
         addPage(typesPage);
      }
      addPage(locationPage);
   }

   public boolean canFinish()
   {
      return locationPage.isPageComplete();
   }

   public boolean performFinish()
   {
      final IFolder location = locationPage.getSaveFolder();
      final List<TypeDeclarationType> types = typesPage.getTypes2Save();
      
      try
      {
         getContainer().run(false, true, new IRunnableWithProgress()
         {
            public void run(IProgressMonitor monitor) throws InvocationTargetException,
                  InterruptedException
            {
               monitor.beginTask(Structured_Messages.ExportingTaskName, types.size() * 3);
               for (int i = 0; i < types.size() && !monitor.isCanceled(); i++)
               {
                  TypeDeclarationType declaration = (TypeDeclarationType) types.get(i);
                  monitor.subTask(declaration.getName());
                  XSDSchema schema = declaration.getSchemaType().getSchema();
                  // fix references to internal schemas
                  List<XSDImport> changedImports = CollectionUtils.newList();
                  List<XSDSchemaContent> contents = schema.getContents();
                  for (XSDSchemaContent item : contents)
                  {
                     if (item instanceof XSDImport)
                     {
                        XSDImport xsdImport = (XSDImport) item;
                        String schemaLocation = xsdImport.getSchemaLocation();
                        if (schemaLocation != null && schemaLocation.startsWith(StructuredDataConstants.URN_INTERNAL_PREFIX))
                        {
                           xsdImport.setSchemaLocation(schemaLocation.substring(
                                 StructuredDataConstants.URN_INTERNAL_PREFIX.length()) + XSD_FILE_SUFFIX);
                           changedImports.add(xsdImport);
                        }
                     }
                  }
                  monitor.worked(1);
                  try
                  {
                     doSaveSchema(declaration.getId() + XSD_FILE_SUFFIX, schema, location, monitor);
                  }
                  finally
                  {
                     // revert changes made for saving
                     for (XSDImport xsdImport : changedImports)
                     {
                        String schemaLocation = xsdImport.getSchemaLocation();
                        xsdImport.setSchemaLocation(StructuredDataConstants.URN_INTERNAL_PREFIX
                              + schemaLocation.substring(0, schemaLocation.length() - XSD_FILE_SUFFIX.length()));
                     }
                  }
               }
               monitor.done();
            }
         });
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
      
      return true;
   }

   private void doSaveSchema(String xsdFileName, final XSDSchema schema, IFolder location,
         final IProgressMonitor monitor)
   {
      if (monitor.isCanceled())
      {
         return;
      }
      final IFile file = location.getFile(xsdFileName);
      schema.updateElement();
      if (file.exists())
      {
         String[] buttons = new String[] {
               IDialogConstants.YES_LABEL,
               IDialogConstants.SKIP_LABEL,
               IDialogConstants.CANCEL_LABEL };
         String question = NLS.bind(IDEWorkbenchMessages.SaveAsDialog_overwriteQuestion,
               file.getFullPath().toOSString());
         MessageDialog dialog = new MessageDialog(getShell(),
               IDEWorkbenchMessages.Question, null, question,
               MessageDialog.QUESTION, buttons, 0);
         switch (dialog.open())
         {
         case 0: // Yes
            break;
         case 1: // Skip
            return;
         case 2: // Cancel
         default:
            monitor.setCanceled(true);
            return;
         }
      }
      try
      {
         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
         XSDResourceImpl.serialize(outputStream, schema.getDocument(), "UTF-8"); //$NON-NLS-1$
         monitor.worked(1);
         ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
         if (file.exists())
         {
            file.setContents(inputStream, IResource.KEEP_HISTORY, null);
         }
         else
         {
            file.create(inputStream, IResource.KEEP_HISTORY, null);
         }
         monitor.worked(1);
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   private static class IDEWorkbenchMessages extends NLS
   {
      private static final String BUNDLE_NAME = "org.eclipse.ui.internal.ide.messages"; //$NON-NLS-1$
      
      public static String SaveAsDialog_overwriteQuestion;
      public static String Question;
      
      static
      {
         // load message values from bundle file
         NLS.initializeMessages(BUNDLE_NAME, IDEWorkbenchMessages.class);
      }
   }
}
