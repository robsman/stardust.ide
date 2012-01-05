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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FileSystemElement;
import org.eclipse.ui.internal.wizards.datatransfer.MinimizedFileSystemElement;
import org.eclipse.ui.internal.wizards.datatransfer.WizardFileSystemResourceImportPage1;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.wizards.datatransfer.FileSystemImportWizard;
import org.eclipse.ui.wizards.datatransfer.FileSystemStructureProvider;

// TODO: (fh) cleanup: the filters, the file system import wizard
public class SelectSingleFileView
{
   protected Composite composite;

   protected IStructuredSelection selection;

   protected boolean isFileMandatory;

   protected TreeViewer sourceFileViewer;

   protected Button importButton;

   protected Vector<ViewerFilter> fFilters;

   protected IFile selectedFile;

   protected ISelection defaultSelection;

   protected Listener listener;

   private IJavaProject javaProject;

   public static interface Listener
   {
      public void setControlComplete(boolean isComplete);
   }

   public SelectSingleFileView(IStructuredSelection selection, boolean isFileMandatory, IProject project)
   {
      this.selection = selection;
      this.isFileMandatory = isFileMandatory;
      this.selectedFile = null;
      this.defaultSelection = null;
      try
      {
         if (project != null && project.hasNature(JavaCore.NATURE_ID))
         {
            javaProject = JavaCore.create(project);
         }
      }
      catch (CoreException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public Composite createControl(Composite parent)
   {
      Composite composite = new Composite(parent, SWT.NONE);
      composite.setLayout(new GridLayout());
      composite.setLayoutData(new GridData(GridData.FILL_BOTH));

      Composite smallComposite = new Composite(composite, SWT.NONE);
      smallComposite.setLayoutData(new GridData(GridData.FILL,
            GridData.HORIZONTAL_ALIGN_FILL, true, false));
      GridLayout gridLayout = new GridLayout(2, false);
      gridLayout.marginHeight = 0;
      smallComposite.setLayout(gridLayout);

      Label label = new Label(smallComposite, SWT.NONE);
      label.setText(Structured_Messages.WorkbenchFilesLabel);
      label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING, GridData.END,
            false, false));

      // Collapse and Expand all buttons
      ToolBar toolBar = new ToolBar(smallComposite, SWT.FLAT);
      toolBar.setLayoutData(new GridData(GridData.END, GridData.END, true, false));

      ToolItem toolItem = new ToolItem(toolBar, SWT.NONE);
      ImageDescriptor imageDescriptor = DiagramPlugin.getImageDescriptor(
            "{org.eclipse.wst.common.ui}icons/expandAll.gif"); //$NON-NLS-1$
      Image image = imageDescriptor.createImage();
      toolItem.setImage(image);
      toolItem.setToolTipText(Structured_Messages.ExpandAllLabel);
      toolItem.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            sourceFileViewer.expandAll();

         }
      });

      toolItem = new ToolItem(toolBar, SWT.NONE);
      imageDescriptor = DiagramPlugin.getImageDescriptor(
            "{org.eclipse.wst.common.ui}icons/collapseAll.gif"); //$NON-NLS-1$
      image = imageDescriptor.createImage();
      toolItem.setImage(image);
      toolItem.setToolTipText(Structured_Messages.CollapseAllLabel);
      toolItem.addSelectionListener(new SelectionListener()
      {

         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            sourceFileViewer.collapseAll();

         }
      });

      createSourceViewer(composite);
      createFilterControl(composite);
      createImportButton(composite);
      sourceFileViewer.getTree().setFocus();
      return composite;
   }

   protected void createFilterControl(Composite composite)
   {}

   public void setListener(Listener listener)
   {
      this.listener = listener;
   }

   protected void createSourceViewer(Composite parent)
   {
      sourceFileViewer = new TreeViewer(new Tree(parent, SWT.SINGLE | SWT.BORDER));
      sourceFileViewer.setContentProvider(new WorkbenchContentProvider()
      {
         public Object[] getChildren(Object element)
         {
            if (element instanceof IJavaProject)
            {
               List<IResource> result = CollectionUtils.newList();
               try
               {
                  IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
                  for (int i = 0; i < roots.length; i++)
                  {
                     IResource resource = roots[i].getCorrespondingResource();
                     if (resource instanceof IFolder)
                     {
                        result.add(resource);
                     }
                  }
               }
               catch (JavaModelException e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               return result.toArray();
            }
            return super.getChildren(element);
         }
      });
      sourceFileViewer.setLabelProvider(new WorkbenchLabelProvider());
      sourceFileViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            boolean isComplete = true;
            ISelection selection = event.getSelection();
            importButton.setEnabled(selection != null && !selection.isEmpty());
            if (selection instanceof IStructuredSelection)
            {
               @SuppressWarnings("unchecked")
               List<IResource> list = ((IStructuredSelection) selection).toList();
               for (IResource resource : list)
               {
                  if (resource instanceof IFile)
                  {
                     selectedFile = (IFile) resource;
                     if (isFileMandatory)
                     {
                        isComplete = true;
                        break;
                     }
                  }
                  else
                  {
                     selectedFile = null;
                     if (isFileMandatory)
                     {
                        isComplete = false;
                     }
                  }
               }

               if (listener != null)
               {
                  listener.setControlComplete(isComplete);
               }
            }
         }
      });
      Control treeWidget = sourceFileViewer.getTree();
      GridData gd = new GridData(GridData.FILL_BOTH);
      treeWidget.setLayoutData(gd);
   }

   protected void createImportButton(Composite parent)
   {
      importButton = new Button(parent, SWT.NONE);
      importButton.setEnabled(false);
      importButton.setText(Structured_Messages.ImportFilesButtonLabel);

      GridData gridData = new GridData();
      gridData.horizontalAlignment = GridData.CENTER;
      importButton.setLayoutData(gridData);
      importButton.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {}

         public void widgetSelected(SelectionEvent e)
         {
            // This listener is if the Import Wizard adds a new few, we want
            // it to be selected when we come back from it
            ImportAddResourceListener importAddResourceListener = new ImportAddResourceListener();

            ResourcesPlugin.getWorkspace().addResourceChangeListener(
                  importAddResourceListener);

            FileSystemImportWizard importWizard = new FileSystemImportWizard()
            {
               private IWorkbench workbench;
               private IStructuredSelection selection;
               private ModifiedWizardFileSystemResourceImportPage1 mainPage;

               public void init(IWorkbench workbench, IStructuredSelection currentSelection)
               {
                  super.init(workbench, currentSelection);
                  this.workbench = workbench;
                  this.selection = currentSelection;
               }

               public void addPages()
               {
                  mainPage = new ModifiedWizardFileSystemResourceImportPage1(this.workbench, this.selection);
                  addPage(mainPage);
               }

               public boolean performFinish()
               {
                  return mainPage.finish();
               }
            };
            IWorkbench workbench = PlatformUI.getWorkbench();
            selection = (IStructuredSelection) sourceFileViewer.getSelection();
            importWizard.init(workbench, selection != null
                  ? selection
                  : new StructuredSelection());
            Shell shell = Display.getCurrent().getActiveShell();
            WizardDialog wizardDialog = new WizardDialog(shell, importWizard);
            wizardDialog.create();
            wizardDialog.open();
            sourceFileViewer.refresh();
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(
                  importAddResourceListener);
            IFile importedFile = importAddResourceListener.getImportedFile();
            if (importedFile != null)
            {
               StructuredSelection structuredSelection = new StructuredSelection(
                     importedFile);
               sourceFileViewer.setSelection(structuredSelection);
            }
         }
      });
      importButton.setToolTipText(Structured_Messages.ImportFilesButtonToolTip);
   }

   public IFile getFile()
   {
      return selectedFile;
   }

   public void setDefaultSelection(ISelection selection)
   {
      this.defaultSelection = selection;
   }

   public void resetFilters()
   {
      fFilters = null;
   }

   public void addFilter(ViewerFilter filter)
   {
      if (fFilters == null)
      {
         fFilters = new Vector<ViewerFilter>();
      }
      fFilters.add(filter);
   }

   // This is a convenience method that allows filtering of the given file
   // exensions. It internally creates a ResourceFilter so that users of this
   // class don't have to construct one.
   // If the extensions provided don't have '.', one will be added.
   public void addFilterExtensions(String[] filterExtensions)
   {
      // First add the '.' to the filterExtensions if they don't already have one
      String[] correctedFilterExtensions = new String[filterExtensions.length];
      for (int i = 0; i < filterExtensions.length; i++)
      {
         // If the extension doesn't start with a '.', then add one.
         if (filterExtensions[i].startsWith(".")) //$NON-NLS-1$
         {
            correctedFilterExtensions[i] = filterExtensions[i];
         }
         else
         {
            correctedFilterExtensions[i] = "." + filterExtensions[i]; //$NON-NLS-1$
         }
      }

      addFilter(new ResourceFilter(correctedFilterExtensions));
   }

   // This is a convenience method that allows filtering of the given file
   // exensions. It internally creates a ResourceFilter so that users of this
   // class don't have to construct one.
   // If the extensions provided don't have '.', one will be added.
   public void setFilterExtensions(String[] filterExtensions)
   {
      // First add the '.' to the filterExtensions if they don't already have one
      String[] correctedFilterExtensions = new String[filterExtensions.length];
      for (int i = 0; i < filterExtensions.length; i++)
      {
         // If the extension doesn't start with a '.', then add one.
         if (filterExtensions[i].startsWith(".")) //$NON-NLS-1$
         {
            correctedFilterExtensions[i] = filterExtensions[i];
         }
         else
         {
            correctedFilterExtensions[i] = "." + filterExtensions[i]; //$NON-NLS-1$
         }
      }
      ViewerFilter filter = new ResourceFilter(correctedFilterExtensions);
      fFilters = new Vector<ViewerFilter>();
      fFilters.add(filter);
      if (sourceFileViewer != null)
      {
         sourceFileViewer.getTree().setRedraw(false);
         sourceFileViewer.resetFilters();
         for (Iterator<ViewerFilter> i = fFilters.iterator(); i.hasNext();)
         {
            sourceFileViewer.addFilter((ViewerFilter) i.next());
         }
         sourceFileViewer.getTree().setRedraw(true);
         sourceFileViewer.getTree().redraw();
      }
   }

   // this method should be called by a Wizard page or Dialog when it becomes visible
   public void setVisibleHelper(boolean visible)
   {
      if (visible == true)
      {
         if (fFilters != null)
         {
            sourceFileViewer.resetFilters();
            for (Iterator<ViewerFilter> i = fFilters.iterator(); i.hasNext();)
            {
               sourceFileViewer.addFilter((ViewerFilter) i.next());
            }
         }
         sourceFileViewer.setInput(javaProject == null
               ? (Object) ResourcesPlugin.getWorkspace().getRoot() : javaProject);
         sourceFileViewer.expandToLevel(1);

         if (defaultSelection != null)
         {
            sourceFileViewer.setSelection(defaultSelection, true);
         }
         else if (!sourceFileViewer.getSelection().isEmpty())
         {
            sourceFileViewer.setSelection(sourceFileViewer.getSelection());
         }
         else
         {
            if (isFileMandatory && listener != null)
            {
               listener.setControlComplete(false);
            }
         }
      }
   }

   class ImportAddResourceListener
         implements IResourceChangeListener, IResourceDeltaVisitor
   {
      Vector<IResource> importedFiles;

      ImportAddResourceListener()
      {
         importedFiles = new Vector<IResource>();
      }

      public void resourceChanged(IResourceChangeEvent event)
      {
         IResourceDelta resourceDelta = event.getDelta();

         try
         {
            if (resourceDelta != null)
            {
               resourceDelta.accept(this);
            }
         }
         catch (Exception e)
         {
            // TODO... log exception
            // UIPlugin.getMsgLogger().write("Exception caught during resource change" +
            // e);
            // UIPlugin.getMsgLogger().writeCurrentThread();
         }
      }

      public boolean visit(IResourceDelta delta)
      {
         if (delta.getKind() == IResourceDelta.ADDED)
         {
            if (delta.getResource() instanceof IFile)
               importedFiles.add(delta.getResource());
         }
         return true;
      }

      public Collection<IResource> getImportedFiles()
      {
         return importedFiles;
      }

      // This returns the first imported file in the list of imported files
      public IFile getImportedFile()
      {
         if (importedFiles.isEmpty() == false)
            return (IFile) importedFiles.firstElement();

         return null;
      }
   }

   public void addSelectionChangedTreeListener(ISelectionChangedListener treeListener)
   {
      sourceFileViewer.addSelectionChangedListener(treeListener);
   }
   
   private class ModifiedWizardFileSystemResourceImportPage1
      extends WizardFileSystemResourceImportPage1
   {
      public ModifiedWizardFileSystemResourceImportPage1(IWorkbench workbench,
            IStructuredSelection selection)
      {
         super(workbench, selection);
      }

      protected void enableButtonGroup(boolean enable)
      {
         super.enableButtonGroup(enable);
         selectTypesButton.setEnabled(false);
      }

      /**
       * Returns a content provider for <code>FileSystemElement</code>s that returns 
       * only files as children.
       */
      protected ITreeContentProvider getFileProvider()
      {
          return new WorkbenchContentProvider()
          {
              public Object[] getChildren(Object o)
              {
                  if (o instanceof FileSystemElement)
                  {
                      FileSystemElement element = (FileSystemElement) o;
                      Object[] result = ((MinimizedFileSystemElement) element).getFiles(FileSystemStructureProvider.INSTANCE).getChildren(element);
                      List<FileSystemElement> filtered = CollectionUtils.newList();
                      for (int i = 0; i < result.length; i++)
                      {
                         FileSystemElement child = (FileSystemElement) result[i];
                         if (select(element, child))
                         {
                            filtered.add(child);
                         }
                      }
                      return filtered.toArray();
                  }
                  return new Object[0];
              }
          };
      }

      protected boolean select(FileSystemElement parent, FileSystemElement child)
      {
         if (fFilters == null)
         {
            return true;
         }
         for (int i = 0; i < fFilters.size(); i++)
         {
            ViewerFilter filter = (ViewerFilter) fFilters.get(i);
            if (filter.select(sourceFileViewer, parent, child))
            {
               return true;
            }
         }
         return false;
      }
   }
}
