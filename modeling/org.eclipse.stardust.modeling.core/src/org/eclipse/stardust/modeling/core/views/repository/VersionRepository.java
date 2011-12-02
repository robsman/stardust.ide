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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.action.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.*;
import org.eclipse.ui.part.ViewPart;


public class VersionRepository extends ViewPart
{
   private TreeViewer viewer;

   private ISelectionListener selectionListener;

   ProjectContentProvider contentProvider = new ProjectContentProvider();

   private Menu contextMenu;

   private SwitchProjectAction switchProjectAction;

   private IProject stickyProject;

   private Action refreshAction;

   public VersionRepository()
   {
      super();
   }

   public void dispose()
   {
      if (selectionListener != null)
      {
         getViewSite().getPage().removeSelectionListener(selectionListener);
      }
      if (contextMenu != null && !contextMenu.isDisposed())
      {
         contextMenu.dispose();
      }
      switchProjectAction.dispose();
      super.dispose();
   }

   /*
    * (non-Javadoc) Method declared on IViewPart.
    */
   public void saveState(IMemento memento)
   {
      contentProvider.saveState(memento);
   }

   /*
    * (non-Javadoc) Initializes this view with the given view site. A memento is passed to
    * the view which contains a snapshot of the views state from a previous session. Where
    * possible, the view should try to recreate that state within the part controls. <p>
    * This implementation will ignore the memento and initialize the view in a fresh
    * state. Subclasses may override the implementation to perform any state restoration
    * as needed.
    */
   public void init(IViewSite site, IMemento memento) throws PartInitException
   {
      super.init(site, memento);
      contentProvider.loadState(memento);
   }

   private ISelectionListener getSelectionListener()
   {
      if (selectionListener == null)
      {
         selectionListener = new ISelectionListener()
         {
            public void selectionChanged(IWorkbenchPart part, ISelection selection)
            {
               if (!selection.isEmpty())
               {
                  if (selection instanceof IStructuredSelection)
                  {
                     IProject project = null;
                     IStructuredSelection sel = (IStructuredSelection) selection;
                     Object o = sel.getFirstElement();
                     if (o instanceof IProject)
                     {
                        project = (IProject) o;
                     }
                     else
                     {
                        if (o instanceof IAdaptable)
                        {
                           IResource resource = (IResource) ((IAdaptable) o)
                                 .getAdapter(IResource.class);
                           if (resource != null)
                           {
                              o = resource;
                           }
                        }
                        if (o instanceof IResource)
                        {
                           // System.out.println("Resource");
                           project = ((IResource) o).getProject();
                        }
                        else if (o instanceof IJavaElement)
                        {
                           // System.out.println("Java Element");
                           project = ((IJavaElement) o).getJavaProject().getProject();
                        }
                        else if (o instanceof EditPart)
                        {
                           o = ((EditPart) o).getModel();
                           if (o instanceof EObject)
                           {
                              // System.out.println("EObject");
                              project = ModelUtils.getProjectFromEObject((EObject) o);
                           }
                           /*
                            * else { System.out.println("Edit Part"); }
                            */
                        }
                        /*
                         * else { System.out.println("o.getClass() = " + o.getClass()); }
                         */
                     }
                     setProject(project);
                  }
               }
            }
         };
      }
      return selectionListener;
   }

   void setProject(IProject project)
   {
      if (stickyProject != null)
      {
         project = stickyProject;
         setActionText(project, Diagram_Messages.LB_VersionRepository_StickyProject);
      }
      else
      {
         setActionText(project, Diagram_Messages.LB_VersionRepository_Project);
      }

      if (project != null && project != viewer.getInput())
      {
         viewer.setInput(project);
      }
   }

   private void setActionText(IProject project, String label)
   {
      if (switchProjectAction != null)
      {
         if (project == null)
         {
            switchProjectAction.setText(label);
         }
         else
         {
            switchProjectAction.setText(label + project.getName());
         }
         IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
         tbm.update(true);
      }
   }

   public void createPartControl(Composite parent)
   {
      parent.setLayout(new GridLayout());
      Tree tree = new Tree(parent, SWT.BORDER | SWT.MULTI);
      tree.setLinesVisible(true);
      tree.setLayoutData(FormBuilder.createDefaultMultiLineWidgetGridData());

      viewer = new TreeViewer(tree);
      viewer.setSorter(new ViewerSorter()
      {
         public int compare(Viewer viewer, Object e1, Object e2)
         {
            ResourceInfo r1 = (ResourceInfo) e1;
            ResourceInfo r2 = (ResourceInfo) e2;
            int result = getCollator().compare(r1.getId(), r2.getId());
            if (result == 0)
            {
               if(r1.getVersionNumbers() == null || r2.getVersionNumbers() == null) {
                  return 1;
                }
               int[] v1 = r1.getVersionNumbers();
               int[] v2 = r2.getVersionNumbers();
               result = v1.length - v2.length;
               if (result == 0)
               {
                  for (int i = 0; i < v1.length; i++)
                  {
                     result = v2[i] - v1[i];
                     if (result != 0)
                     {
                        break;
                     }
                  }
               }
            }
            return result;
         }
      });
      viewer.setContentProvider(contentProvider);
      viewer.setLabelProvider(new ResourceInfoLabelProvider());

      WorkflowModelEditor editor = getActiveEditor();
      if (editor != null)
      {
         setProject(ModelUtils.getProjectFromEObject(editor.getWorkflowModel()));
      }
      else
      {
         IProject[] projects = contentProvider.getProjects();
         if (projects != null && projects.length == 1)
         {
            setProject(projects[0]);
         }
      }
      getViewSite().getPage().addSelectionListener(getSelectionListener());
      addActionBars();
      refreshAction.run();
   }

   private void addActionBars()
   {
      final RepositoryActionSet actionSet = new RepositoryActionSet(this, viewer);

      MenuManager menuMgr = new MenuManager(Diagram_Messages.MENU_Repository_PopUp);
      menuMgr.setRemoveAllWhenShown(true);
      menuMgr.addMenuListener(new IMenuListener()
      {
         public void menuAboutToShow(IMenuManager manager)
         {
            GEFActionConstants.addStandardActionGroups(manager);
            actionSet.addActions(manager);
         }
      });
      contextMenu = menuMgr.createContextMenu(viewer.getTree());
      viewer.getTree().setMenu(contextMenu);

      // Register viewer with site. This must be done before making the actions.
      IWorkbenchPartSite site = getSite();
      ResourceSelectionProvider provider = new ResourceSelectionProvider(viewer);
      actionSet.setSelectionProvider(provider);
      site.registerContextMenu(menuMgr, provider);

      // Add actions to the local tool bar
      IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
      refreshAction = new Action(Diagram_Messages.LB_VersionRepository_Refresh)
            {
               public void run()
               {
                  IProject currentProject = (IProject) viewer.getInput();
                  viewer.setInput(null);
                  contentProvider.clearCaches();
                  viewer.setInput(currentProject);
               }
            };
      tbm.add(refreshAction);
      tbm.add(switchProjectAction = new SwitchProjectAction(this));
      setActionText((IProject) viewer.getInput(),
            Diagram_Messages.LB_VersionRepository_Project); //$NON-NLS-1$

      viewer.addOpenListener(new IOpenListener()
      {
         public void open(OpenEvent event)
         {
            actionSet.open(event);
         }
      });
   }

   private WorkflowModelEditor getActiveEditor()
   {
      WorkflowModelEditor editor = null;
      IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
            .getActivePage();
      if (page != null)
      {
         IEditorPart editorPart = page.getActiveEditor();
         // todo: accept any editor part
         if (editorPart instanceof WorkflowModelEditor)
         {
            editor = (WorkflowModelEditor) editorPart;
         }
      }
      return editor;
   }

   public void setFocus()
   {
   // nothing to do here
   }

   public ProjectContentProvider getContentProvider()
   {
      return contentProvider;
   }

   public void setStickyProject(IProject project)
   {
      stickyProject = project;
      setProject(project == null ? (IProject) viewer.getInput() : project);
   }
}
