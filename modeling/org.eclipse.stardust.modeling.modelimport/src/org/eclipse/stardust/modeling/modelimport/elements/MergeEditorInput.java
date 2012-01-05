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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.ITypedElement;
import org.eclipse.compare.structuremergeviewer.Differencer;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.modelimport.IImportModelWizardPage;
import org.eclipse.stardust.modeling.modelimport.ImportMessages;
import org.eclipse.stardust.modeling.modelimport.ImportPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.progress.WorkbenchJob;

public class MergeEditorInput extends CompareEditorInput
{
   private WorkflowModelManager source;
   private WorkflowModelManager target;

   private boolean dirty = false;
   
   private DifferencesViewer diffViewer;
   
   private IImportModelWizardPage page;
   
   private Viewer cmpViewer;
   private String sourceName;
//   private String targetName;

   public MergeEditorInput(IImportModelWizardPage page)
   {
      super(new CompareConfiguration());
      this.page = page;
   }

   // called by eclipse (when object is created)? called by run
   protected Object prepareInput(final IProgressMonitor monitor)
         throws InvocationTargetException, InterruptedException
   {
      if (monitor.isCanceled())
      {
         return null;
      }
      monitor.beginTask(ImportMessages.MergeEditorInput_OpenCompareViewerTaskName, 4);

      load(monitor, source, sourceName);
      if (monitor.isCanceled())
      {
         return null;
      }
      monitor.worked(1);

      // should be already loaded
//      load(monitor, target, targetName);
      if (monitor.isCanceled())
      {
         return null;
      }
      monitor.worked(1);

      Root sourceRoot = new Root(source);
      Root targetRoot = new Root(target);

      boolean returnCode = false;      
      
      final ITypedElement nodeOne = new RootComparator(sourceRoot);
      final ITypedElement nodeTwo = new RootComparator(targetRoot);

      new UIJob("") //$NON-NLS-1$
      {
         public IStatus runInUIThread(IProgressMonitor monitor)
         {
            // must be run in ui thread because it wants to load some icons
            initializeCompareConfiguration(nodeOne, nodeTwo);
            return Status.OK_STATUS;
         }
      }.schedule();

      monitor.subTask(ImportMessages.MergeEditorInput_CheckingDuplicateOidsTaskName);
      returnCode = MergeUtil.initialize(sourceRoot.getModel(), targetRoot.getModel(), this);
      if (returnCode == true)
      {
         monitor.setCanceled(true);
         return null;
      }
      if (monitor.isCanceled())
      {
         return null;
      }
      monitor.worked(1);
      
      // nodeTwo is local
      monitor.subTask(ImportMessages.MergeEditorInput_ComparingTaskName);
      Differencer differencer = new Differencer();
      Object input = differencer.findDifferences(false, null, null,
            null, nodeOne, nodeTwo);
      if (monitor.isCanceled())
      {
         return null;
      }
      monitor.worked(1);

      return input;
   }

   private void initializeCompareConfiguration(ITypedElement one, ITypedElement two)
   {
      CompareConfiguration config = getCompareConfiguration();
      config.setRightEditable(false);
      config.setLeftEditable(false);
      config.setLeftLabel(one.getName());
      config.setLeftImage(one.getImage());
      config.setRightLabel(two.getName());
      config.setRightImage(two.getImage());
   }

   public static void load(IProgressMonitor monitor, WorkflowModelManager resource, String name)
      throws InvocationTargetException
   {
      monitor.subTask(MessageFormat.format(ImportMessages.MergeEditorInput_LoadingTaskName,
            new Object[] {name}));
      try
      {
         resource.load((URI) null);
      }
      catch (IOException e)
      {
         throw new InvocationTargetException(e);
      }
   }

   public void setSource(WorkflowModelManager source)
   {
      this.source = source;
      sourceName = source.toString();
   }

   public void setTarget(WorkflowModelManager target)
   {
      this.target = target;
//      targetName = target.toString();
   }

   public DifferencesViewer getDiffViewer()
   {
      return diffViewer;
   }

   public Viewer createDiffViewer(Composite parent)
   {
      return diffViewer = new DifferencesViewer(parent, this, getCompareConfiguration());
   }

   public Viewer findContentViewer(Viewer oldViewer, ICompareInput input, Composite parent)
   {
      return cmpViewer = oldViewer instanceof ContentCompareViewer
         ? oldViewer : new ContentCompareViewer(parent, getCompareConfiguration());
   }

   public void saveChanges(IProgressMonitor pm) throws CoreException
   {
      try
      {
         target.save(target.getModel().eResource().getURI());
         
         IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

         String uri = target.getModel().eResource().getURI().path();
         if (uri.startsWith("/resource")) //$NON-NLS-1$
         {
            uri = uri.substring("/resource".length()); //$NON-NLS-1$
         }

         final IFile file = (IFile) root.findMember(new Path(uri));
         
         IWorkbenchPage workbenchPage = ImportPlugin.getDefault().getWorkbench()
               .getActiveWorkbenchWindow().getActivePage();
         IEditorReference[] references = workbenchPage.getEditorReferences();
         for (int i = 0; i < references.length; i++)
         {
            IEditorPart editor = references[i].getEditor(true);
            if (editor instanceof WorkflowModelEditor)
            {
               ModelType model = ((WorkflowModelEditor) editor).getWorkflowModel();
               if (model != null && CompareHelper.areEqual(
                     target.getModel().eResource().getURI(), model.eResource().getURI()))
               {
                  workbenchPage.closeEditor(editor, false);
               }
            }
         }

         new WorkbenchJob(ImportMessages.MergeEditorInput_OpenEditorJobName)
         {
            public IStatus runInUIThread(IProgressMonitor monitor)
            {
               IWorkbenchPage page = ImportPlugin.getDefault().getWorkbench()
                  .getActiveWorkbenchWindow().getActivePage();
               try
               {
                  IDE.openEditor(page, file);
               }
               catch (PartInitException e)
               {
                  return new Status(Status.WARNING, ImportPlugin.PLUGIN_ID,
                        0, e.getMessage(), e);
               }
               return Status.OK_STATUS;
            }
         }.schedule();
      }
      catch (IOException e)
      {
         throw new CoreException(new Status(Status.ERROR, ImportPlugin.PLUGIN_ID,
               0, e.getMessage(), e));
      }
   }

   public boolean isDirty()
   {
      return dirty;
   }

   public void setDirty(boolean dirty)
   {
      this.dirty = dirty;
      page.updateButtons();
   }

   public void contentChanged()
   {
      if(cmpViewer != null)
      {
         cmpViewer.refresh();
      }
   }

   public boolean showErrors(final String[] errors)
   {
      final boolean[] result = new boolean[1];
      ((WizardPage) page).getShell().getDisplay().syncExec(new Runnable()
      {
         public void run()
         {
            StringBuffer message = new StringBuffer();
            message.append(ImportMessages.MergeEditorInput_ERROR_MESSAGE_HEADLINE);
            for (int i = 0; i < errors.length; i++)
            {
               message.append("\n  - ").append(errors[i]).append(';'); //$NON-NLS-1$
            }
            message.append(ImportMessages.MergeEditorInput_ERROR_MESSAGE_NEWOIDS);
            message.append(ImportMessages.MergeEditorInput_ERROR_MESSAGE_CONTINUE);
            result[0] = MessageDialog.openQuestion(((WizardPage) page).getShell(), ImportMessages.MergeEditorInput_ERROR_MESSAGE_TITLE, message.toString());
         }
      });
      return result[0];
   }
}