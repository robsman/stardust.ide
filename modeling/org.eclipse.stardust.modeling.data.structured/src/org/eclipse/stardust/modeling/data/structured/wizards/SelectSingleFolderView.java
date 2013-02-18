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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
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
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.data.structured.Structured_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class SelectSingleFolderView
{
   protected Composite composite;

   protected IStructuredSelection selection;

   protected boolean isFolderMandatory;

   protected TreeViewer sourceFileViewer;

   protected IFolder selectedFolder;

   protected ISelection defaultSelection;

   protected Listener listener;

   private IJavaProject javaProject;

   public static interface Listener
   {
      public void setControlComplete(boolean isComplete);
   }

   public SelectSingleFolderView(IStructuredSelection selection, boolean isFileMandatory, IProject project)
   {
      this.selection = selection;
      this.isFolderMandatory = isFileMandatory;
      this.selectedFolder = null;
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
      composite = new Composite(parent, SWT.NONE);
      composite.setLayout(new GridLayout());
      composite.setLayoutData(new GridData(GridData.FILL_BOTH));

      Composite smallComposite = new Composite(composite, SWT.NONE);
      smallComposite.setLayoutData(new GridData(GridData.FILL,
            GridData.HORIZONTAL_ALIGN_FILL, true, false));
      GridLayout gridLayout = new GridLayout(2, false);
      gridLayout.marginHeight = 0;
      smallComposite.setLayout(gridLayout);

      Label label = new Label(smallComposite, SWT.NONE);
      label.setText(Structured_Messages.SelectSaveLocationLabel);
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
      toolItem.addSelectionListener(new SelectionAdapter()
      {
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
      toolItem.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            sourceFileViewer.collapseAll();
         }
      });

      createSourceViewer(composite);
      sourceFileViewer.getTree().setFocus();
      return composite;
   }
   
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
            if (selection instanceof IStructuredSelection)
            {
               @SuppressWarnings("unchecked")
               List<IResource> list = ((IStructuredSelection) selection).toList();
               for (IResource resource : list)
               {
                  if (resource instanceof IFolder)
                  {
                     selectedFolder = (IFolder) resource;
                     if (isFolderMandatory)
                     {
                        isComplete = true;
                        break;
                     }
                  }
                  else
                  {
                     selectedFolder = null;
                     if (isFolderMandatory)
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
      sourceFileViewer.addFilter(new ViewerFilter()
      {
         public boolean select(Viewer viewer, Object parentElement, Object element)
         {
            return element instanceof IContainer;
         }
      });
      Control treeWidget = sourceFileViewer.getTree();
      GridData gd = new GridData(GridData.FILL_BOTH);
      treeWidget.setLayoutData(gd);
   }

   public IFolder getFolder()
   {
      return selectedFolder;
   }

   public void setDefaultSelection(ISelection selection)
   {
      this.defaultSelection = selection;
   }

   // this method should be called by a Wizard page or Dialog when it becomes visible
   public void setVisibleHelper(boolean visible)
   {
      if (visible == true)
      {
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
            if (isFolderMandatory && listener != null)
            {
               listener.setControlComplete(false);
            }
         }
      }
      composite.setVisible(visible);
   }

   public void addSelectionChangedTreeListener(ISelectionChangedListener treeListener)
   {
      sourceFileViewer.addSelectionChangedListener(treeListener);
   }

   public String getClasspathResourceName(IFile file)
   {
      String fileName = file.toString().substring(1); // strip resource type identifier
      try
      {
         //also search in required project of the javaproject
         List<IJavaProject> javaProjectsToScan = new ArrayList<IJavaProject>();
         javaProjectsToScan.add(javaProject);
         javaProjectsToScan.addAll(GenericUtils.getRequiredProjects(javaProject));
         
         for(IJavaProject javaProject: javaProjectsToScan)
         {
            IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
            for (int i = 0; i < roots.length; i++)
            {
               IResource resource = roots[i].getCorrespondingResource();
               if (resource instanceof IFolder)
               {
                  String parent = resource.toString().substring(1); // strip resource type identifier
                  if (fileName.startsWith(parent))
                  {
                     return fileName.substring(parent.length());
                  }
               }
            }
         }
      }
      catch (JavaModelException e)
      {
         e.printStackTrace();
      }
      return fileName;
   }
}
