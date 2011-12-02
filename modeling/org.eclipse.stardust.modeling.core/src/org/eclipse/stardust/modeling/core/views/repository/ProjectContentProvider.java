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
package org.eclipse.stardust.modeling.core.views.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.WorkflowModelManager;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.progress.UIJob;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ProjectContentProvider implements ITreeContentProvider
{
   private static final String MEMENTO_PROJECT_TYPE = CarnotConstants.DIAGRAM_PLUGIN_ID
         + ".projectInfo"; //$NON-NLS-1$

   private IResourceChangeListener resourceListener;

   private Job runningJob;

   private ResourceInfo root;

   private Map cache = new HashMap();

   public Object[] getChildren(Object parentElement)
   {
      return ((ResourceInfo) parentElement).getChildren();
   }

   public Object getParent(Object element)
   {
      return ((ResourceInfo) element).getParent();
   }

   public boolean hasChildren(Object element)
   {
      return ((ResourceInfo) element).hasChildren();
   }

   public Object[] getElements(Object inputElement)
   {
      return root.getChildren();
   }

   public void dispose()
   {
      if (runningJob != null)
      {
         runningJob.cancel();
      }
      if (resourceListener != null)
      {
         getWorkspace().removeResourceChangeListener(resourceListener);
      }
   }

   public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
   {
      if (oldInput != newInput)
      {
         if (oldInput != null && resourceListener != null)
         {
            getWorkspace().removeResourceChangeListener(resourceListener);
         }
         if (newInput != null)
         {
            getWorkspace().addResourceChangeListener(
                  getResourceChangeListener((TreeViewer) viewer));
            root = (ResourceInfo) cache.get(((IProject) newInput).getName());
            if (root == null)
            {
               root = new ResourceInfo(null, null, ((IProject) newInput).getName(), null,
                     null);
               cache.put(((IProject) newInput).getName(), root);
               updateView((TreeViewer) viewer, (IProject) newInput);
            }
         }
      }
   }

   private void updateView(final TreeViewer viewer, final IProject project)
   {
      if (runningJob != null)
      {
         // todo: this may render the cache invalid, if we don't have a fully executed job
         runningJob.cancel();
         runningJob = null;
      }
      if (project != null)
      {
         runningJob = new Job(Diagram_Messages.JOB_RepositoryUpdate)
         {
            protected IStatus run(IProgressMonitor monitor)
            {
               try
               {
                  project.accept(new IResourceVisitor()
                  {
                     public boolean visit(IResource resource) throws CoreException
                     {
                        if (resource instanceof IFolder || resource == project)
                        {
                           return true;
                        }
                        if (resource instanceof IFile)
                        {
                           addFileResource(viewer, (IFile) resource);
                        }
                        return false;
                     }
                  });
                  runningJob = null;
                  return Status.OK_STATUS;
               }
               catch (CoreException e)
               {
                  return new Status(Status.WARNING, CarnotConstants.DIAGRAM_PLUGIN_ID, 0,
                        Diagram_Messages.MSG_ErrorUpdateRepository, e);
               }
            }
         };
         runningJob.schedule();
      }
   }

   private void updateViewer(final TreeViewer viewer)
   {
      new UIJob(Diagram_Messages.JOB_RepositoryUpdate)
      {
         public IStatus runInUIThread(IProgressMonitor monitor)
         {
            viewer.refresh();
            return Status.OK_STATUS;
         }
      }.schedule();
   }

   private void addFileResource(final TreeViewer viewer, final IFile file)
         throws CoreException
   {
      if ("cwm".equalsIgnoreCase(file.getFileExtension()) || "xpdl".equalsIgnoreCase(file.getFileExtension())) //$NON-NLS-1$ //$NON-NLS-2$
      {
         final ModelType model = create(file);
         if (model != null)
         {
            new UIJob(Diagram_Messages.JOB_RepositoryUpdate)
            {
               public IStatus runInUIThread(IProgressMonitor monitor)
               {
                  String version = AttributeUtil.getAttributeValue(model,
                        "carnot:engine:version"); //$NON-NLS-1$
                  ResourceInfo child = new ResourceInfo(file, model.getId(), model
                        .getName(), version, ResourceInfo.getVersionNumbers(version));
                  traverse(child, child.getVersionNumbers(), true);
                  if (!viewer.getControl().isDisposed())
                  {
                     viewer.refresh();
                  }
                  return Status.OK_STATUS;
               }
            }.schedule();
         }
      }
   }

   private ResourceInfo traverse(ResourceInfo child, int[] version, boolean set)
   {
      int[] parentVersion = null;
      int ix = version.length - 1;
      if (ix > 0)
      {
         if (version[ix - 1] != 0)
         {
            parentVersion = new int[version.length - 1];
            System.arraycopy(version, 0, parentVersion, 0, version.length - 1);
         }
         else
         {
            int iy = ix - 1;
            while (iy > 0 && version[iy] == 0)
            {
               iy--;
            }
            if (version[iy] != 0)
            {
               parentVersion = new int[++iy];
               System.arraycopy(version, 0, parentVersion, 0, iy);
            }
         }
      }
      ResourceInfo parent = null;
      if (parentVersion != null)
      {
         parent = traverse(child, parentVersion, false);
         ResourceInfo savedParent = parent;
         for (int i = parent.getVersionNumbers().length; i <= ix; i++)
         {
            parent = getCreatePseudoParent(savedParent, version, i);
         }
      }
      else
      {
         parent = root.getPseudoChild(child.getId(), child.getName(), child.getFile(),
               child.getVersion());
      }
      ResourceInfo info = parent.findChild(version);
      if (info == null)
      {
         if (set)
         {
            info = child;
         }
         else
         {
            info = new ResourceInfo(null, child.getId(), child.getId(), ResourceInfo
                  .toString(version), version);
         }
         parent.add(info);
         int[] previousVersion = new int[version.length];
         System.arraycopy(version, 0, previousVersion, 0, version.length);
         while (previousVersion[ix] > 1)
         {
            previousVersion[ix]--;
            if (parent.findChild(previousVersion) == null)
            {
               parent.add(new ResourceInfo(null, child.getId(), null, ResourceInfo
                     .toString(previousVersion), previousVersion));
            }
         }
      }
      else
      {
         if (set)
         {
            info.set(child);
         }
      }
      return info;
   }

   private ResourceInfo getCreatePseudoParent(ResourceInfo parent, int[] version, int iy)
   {
      int[] parentVersion;
      parentVersion = new int[iy];
      System.arraycopy(version, 0, parentVersion, 0, version[iy - 1] == 0 ? iy - 1 : iy);
      parent = parent.getPseudoChild(parentVersion);
      return parent;
   }

   private ModelType create(IFile file) throws CoreException
   {
      ModelType model = null;
      WorkflowModelManager modelManager = new WorkflowModelManager();

      if (file.exists())
      {
         try
         {
            modelManager.load(URI
                  .createPlatformResourceURI(file.getFullPath().toString()));
         }
         catch (Exception e)
         {
            throw new PartInitException(Diagram_Messages.MSG_FailedLoadingModel, e);
         }

         model = modelManager.getModel();
         if (null == model)
         {
            throw new CoreException(new Status(IStatus.ERROR,
                  CarnotConstants.DIAGRAM_PLUGIN_ID, IStatus.OK,
                  Diagram_Messages.MSG_LoadingModelFailed, null));
         }
      }
      return model;
   }

   private IResourceChangeListener getResourceChangeListener(final TreeViewer viewer)
   {
      if (resourceListener == null)
      {
         resourceListener = new IResourceChangeListener()
         {
            public void resourceChanged(IResourceChangeEvent event)
            {
               if (event.getResource() instanceof IProject)
               {
                  switch (event.getType())
                  {
                  case IResourceChangeEvent.PRE_DELETE:
                  case IResourceChangeEvent.PRE_CLOSE:
                     System.out.println(Diagram_Messages.MSG_ClearingProject
                           + event.getResource());
                     boolean needClear = root == cache.get(event.getResource());
                     cache.remove(event.getResource());
                     if (needClear)
                     {
                        updateView(viewer, null);
                     }
                     break;
                  }
               }
               else
               {
                  IResourceDelta delta = event.getDelta();
                  try
                  {
                     delta.accept(new IResourceDeltaVisitor()
                     {
                        public boolean visit(IResourceDelta delta) throws CoreException
                        {
                           IResource resource = delta.getResource();
                           if (resource instanceof IFile)
                           {
                              // todo: (fh) process resource changes outside current
                              // project
                              if (delta.getKind() == IResourceDelta.ADDED)
                              {
                                 addFileResource(viewer, (IFile) resource);
                              }
                              if (delta.getKind() == IResourceDelta.REMOVED)
                              {
                                 ResourceInfo info = new ResourceInfo((IFile) resource,
                                       null, null, null, null);
                                 removeDeep(getComparator(viewer), root, info);
                                 updateViewer(viewer);
                              }
                              if (delta.getKind() == IResourceDelta.CHANGED)
                              {
                                 ResourceInfo info = new ResourceInfo((IFile) resource,
                                       null, null, null, null);
                                 removeDeep(getComparator(viewer), root, info);
                                 addFileResource(viewer, (IFile) resource);
                              }
                           }
                           return true;
                        }
                     });
                  }
                  catch (CoreException e)
                  {
                     // todo: something meaningfull
                     // e.printStackTrace();
                  }
               }
            }
         };
      }
      return resourceListener;
   }

   private boolean removeDeep(Comparator comparator, ResourceInfo parent,
         ResourceInfo info)
   {
      boolean result = false;
      ResourceInfo[] children = parent.getChildren();
      for (int i = 0; i < children.length; i++)
      {
         ResourceInfo child = children[i];
         if (child.equals(info))
         {
            child.setVirtual();
            result = true;
         }
         else
         {
            result = removeDeep(comparator, child, info);
         }
         if (result)
         {
            parent.cleanUp(comparator);
            break;
         }
      }
      return result;
   }

   private Comparator getComparator(final StructuredViewer viewer)
   {
      return new Comparator()
      {
         public int compare(Object o1, Object o2)
         {
            return viewer.getSorter().compare(viewer, o1, o2);
         }
      };
   }

   public void saveState(IMemento memento)
   {
      IWorkspace workspace = getWorkspace();
      for (Iterator projects = cache.keySet().iterator(); projects.hasNext();)
      {
         String name = (String) projects.next();
         IProject project = workspace.getRoot().getProject(name);
         if (project != null && project.isOpen())
         {
            ResourceInfo info = (ResourceInfo) cache.get(name);
            IMemento m = memento.createChild(MEMENTO_PROJECT_TYPE, name);
            info.saveState(m);
            memento.putMemento(m);
         }
      }
   }

   public void loadState(IMemento memento)
   {
      if (memento != null)
      {
         IMemento[] children = memento.getChildren(MEMENTO_PROJECT_TYPE);
         if (children != null)
         {
            IWorkspace workspace = getWorkspace();
            for (int i = 0; i < children.length; i++)
            {
               IMemento child = children[i];
               ResourceInfo info = new ResourceInfo(child);
               IProject project = workspace.getRoot().getProject(child.getID());
               if (project != null && project.isOpen())
               {
                  cache.put(child.getID(), info);
               }
            }
         }
      }
   }

   private IWorkspace getWorkspace()
   {
      return ResourcesPlugin.getWorkspace();
   }

   public ResourceInfo findResource(IResource resource)
   {
      return root.findResource(resource);
   }

   public ResourceInfo getRoot()
   {
      return root;
   }

   public IProject[] getProjects()
   {
      return getWorkspace().getRoot().getProjects();
   }

   public void clearCaches()
   {
      cache.clear();
   }
}