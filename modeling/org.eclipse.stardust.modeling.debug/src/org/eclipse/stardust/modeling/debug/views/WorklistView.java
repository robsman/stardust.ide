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
package org.eclipse.stardust.modeling.debug.views;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchListener;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.modeling.debug.DebugPlugin;
import org.eclipse.stardust.modeling.debug.Debug_Messages;
import org.eclipse.stardust.modeling.debug.debugger.UiAccessor;
import org.eclipse.stardust.modeling.debug.debugger.types.ActivityInstanceDigest;
import org.eclipse.stardust.modeling.debug.model.CWMDebugTarget;
import org.eclipse.stardust.modeling.debug.model.CWMDebugTarget.WorklistManager;
import org.eclipse.stardust.modeling.debug.model.CWMThread;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;


public class WorklistView extends ViewPart
{
   private TreeViewer viewer;
   private DrillDownAdapter drillDownAdapter;

   private Action resetAction;
   private Action removeEmptyPerformersAction;
   private Action activateAction;
   private Action doubleClickAction;
   
   public static final String VIEW_ID = "org.eclipse.stardust.modeling.debug.views.WorklistView"; //$NON-NLS-1$

   /**
    * The constructor.
    */
   public WorklistView()
   {
   }

   private void registerLaunchListener()
   {
      ILaunchManager launchManager = org.eclipse.debug.core.DebugPlugin.getDefault().getLaunchManager();
      if (null != launchManager)
      {
         launchManager.addLaunchListener(new ILaunchListener()
         {
            public void launchAdded(ILaunch launch)
            {
               notifyWorklistManager(launch.getDebugTargets(), true);
            }

            public void launchChanged(ILaunch launch)
            {
               notifyWorklistManager(launch.getDebugTargets(), true);
            }

            public void launchRemoved(ILaunch launch)
            {
               notifyWorklistManager(launch.getDebugTargets(), false);
            }
         });
         notifyWorklistManager(launchManager.getDebugTargets(), true);
      }
   }
   
   private void notifyWorklistManager(IDebugTarget[] targets, boolean register)
   {
      for (int idx = 0; idx < targets.length; ++idx)
      {
         if (targets[idx] instanceof CWMDebugTarget)
         {
            WorklistManager manager = ((CWMDebugTarget) targets[idx]).getWorklistManager();
            if (register)
            {
               manager.addWorklistView(this);
            }
            else
            {
               manager.removeWorklistView(this);
               reset();
            }
            break;
         }
      }
   }

   /**
    * This is a callback that will allow us
    * to create the viewer and initialize it.
    */
   public void createPartControl(Composite parent)
   {
      viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
      viewer.setAutoExpandLevel(2);
      drillDownAdapter = new DrillDownAdapter(viewer);
      viewer.setContentProvider(new ViewContentProvider());
      viewer.setLabelProvider(new /*EObjectLabelProvider()*/ViewLabelProvider());
      viewer.setSorter(new ViewerSorter());
      viewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event.getSelection();
            if (selection != null && !selection.isEmpty() &&
                selection.getFirstElement() instanceof ActivityInstanceDigest)
            {
               ActivityInstanceDigest item = (ActivityInstanceDigest) selection.getFirstElement();
               ViewContentProvider cp = (ViewContentProvider) viewer.getContentProvider();
               if (cp.root != null)
               {
                  UiAccessor.activateDiagramForProcess(cp.root.getTarget(),
                        item.getProcessDefinitionId());
               }
            }
         }
      });
      makeActions();
      hookContextMenu();
      hookDoubleClickAction();
      contributeToActionBars();
      registerLaunchListener();
   }

   /**
    * Passing the focus request to the viewer's control.
    */
   public void setFocus()
   {
      viewer.getControl().setFocus();
   }

   private void hookContextMenu()
   {
      MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
      menuMgr.setRemoveAllWhenShown(true);
      menuMgr.addMenuListener(new IMenuListener()
      {
         public void menuAboutToShow(IMenuManager manager)
         {
            WorklistView.this.fillContextMenu(manager);
         }
      });
      Menu menu = menuMgr.createContextMenu(viewer.getControl());
      viewer.getControl().setMenu(menu);
      getSite().registerContextMenu(menuMgr, viewer);
   }

   private void contributeToActionBars()
   {
      IActionBars bars = getViewSite().getActionBars();
      fillLocalPullDown(bars.getMenuManager());
      fillLocalToolBar(bars.getToolBarManager());
   }

   private void fillLocalPullDown(IMenuManager manager)
   {
      manager.add(activateAction);
      /*manager.add(new Separator());
      manager.add(resetAction);
      manager.add(removeEmptyPerformersAction);*/
   }

   private void fillContextMenu(IMenuManager manager)
   {
      manager.add(activateAction);
      manager.add(new Separator());
      drillDownAdapter.addNavigationActions(manager);
      // Other plug-ins can contribute there actions here
      manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
   }

   private void fillLocalToolBar(IToolBarManager manager)
   {
      manager.add(activateAction);
      manager.add(new Separator());
      /*manager.add(resetAction);
      manager.add(removeEmptyPerformersAction);*/
      drillDownAdapter.addNavigationActions(manager);
   }

   private void makeActions()
   {
      resetAction = new Action()
      {
         public void run()
         {
            viewer.setInput(null);
         }
      };
      
      resetAction.setText(Debug_Messages.BUTTON_ClearContent);
      resetAction.setToolTipText(Debug_Messages.TOOLTIP_ClearContent);
      resetAction.setImageDescriptor(DebugPlugin
            .getImageDescriptor("icons/full/obj16/excludeUser_action_icon.gif")); //$NON-NLS-1$;
      
      removeEmptyPerformersAction = new Action()
      {
         public void run()
         {
            viewer.refresh();
         };
      };
      
      removeEmptyPerformersAction.setText(Debug_Messages.BUTTON_ClearEmptyPerformers);
      removeEmptyPerformersAction.setToolTipText(Debug_Messages.TOOLTIP_ClearEmptyPerformers);
      removeEmptyPerformersAction.setImageDescriptor(DebugPlugin
            .getImageDescriptor("icons/full/obj16/excludeUser_action_icon.gif")); //$NON-NLS-1$;
      
      activateAction = new Action()
      {
         public void run()
         {
            ISelection selection = viewer.getSelection();
            Object obj = ((IStructuredSelection) selection).getFirstElement();
            
            if (obj instanceof ActivityInstanceDigest)
            {
               ActivityInstanceDigest activityInstance = (ActivityInstanceDigest) obj;
               IThread thread = activityInstance.getThread();
               if (null == thread || !(thread instanceof CWMThread))
               {
                  showMessage(MessageFormat.format(
                              Debug_Messages.MSG_ActivityInstanceCannotBeResumedFromWorklist,
                              new String[] { obj.toString() }));
                  return;
               }
               
               try
               {
                  ((CWMThread)thread).resume();
               }
               catch (DebugException e)
               {
                  e.printStackTrace();
               }
            }
         }
      };
      
      activateAction.setText(Debug_Messages.BUTTON_ActivateFromWorklist);
      activateAction.setToolTipText(Debug_Messages.TOOLTIP_ActivateFromWorklist);
      activateAction.setImageDescriptor(DebugPlugin
            .getImageDescriptor("icons/full/obj16/activateActivity_action_icon.gif")); //$NON-NLS-1$;

      doubleClickAction = new Action()
      {
         public void run()
         {
            activateAction.run();
         }
      };
   }

   private void hookDoubleClickAction()
   {
      viewer.addDoubleClickListener(new IDoubleClickListener()
      {
         public void doubleClick(DoubleClickEvent event)
         {
            doubleClickAction.run();
         }
      });
   }

   private void showMessage(String message)
   {
      MessageDialog.openInformation(viewer.getControl().getShell(), Debug_Messages.TITLE_CARNOTWorklist,
            message);
   }

   public void refresh(final WorklistManager manager)
   {
      // syncExec() will block the current thread as long as Runnable.run()
      // has not been executed.
      PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
      {
         public void run()
         {
            if (viewer.getInput() != manager)
            {
               viewer.setInput(manager);
               viewer.expandToLevel(2);
            }
            else
            {
               viewer.refresh();
               viewer.expandToLevel(2);
            }
         }
      });
   }

   public void reset()
   {
      refresh(null);
   }

   private static class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider
   {
      private CWMDebugTarget.WorklistManager root = null;
      
      public void inputChanged(Viewer v, Object oldInput, Object newInput)
      {
         root = null;
         if (newInput instanceof CWMDebugTarget.WorklistManager)
         {
            root = (WorklistManager) newInput;
         }
      }

      public void dispose()
      {
         root = null;
      }

      public Object[] getElements(Object parent)
      {
         return getChildren(parent);
      }

      public Object getParent(Object child)
      {
         Object parent = root;
         if (child instanceof ActivityInstanceDigest)
         {
            String performer = ((ActivityInstanceDigest) child).getPerformerName();
            if (performer != null)
            {
               parent = performer;
            }
         }
         return parent;
      }

      public Object[] getChildren(Object parent)
      {
         HashSet children = new HashSet();
         for (Iterator i = root.getWorklist(); i.hasNext();)
         {
            ActivityInstanceDigest digest = (ActivityInstanceDigest) i.next();
            if (parent == root)
            {
               String performer = digest.getPerformerName();
               children.add(performer == null ? (Object) digest : performer);
            }
            else if (parent instanceof String && parent.equals(digest.getPerformerName()))
            {
               children.add(digest);
            }
         }
         return children.toArray();
      }

      public boolean hasChildren(Object parent)
      {
         // TODO (fh) is it worth optimizing?
         return getChildren(parent).length > 0;
      }
   }

   private static class ViewLabelProvider extends LabelProvider
   {
      public String getText(Object obj)
      {
         if (obj instanceof ActivityInstanceDigest)
         {
            return MessageFormat.format(Debug_Messages.WorklistContentFormat,
               new String[] {((ActivityInstanceDigest)obj).getActivityName()});
         }
         return obj.toString();
      }

      public Image getImage(Object obj)
      {
         String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
         if (obj instanceof ActivityInstanceDigest)
         {
            imageKey = "icons/full/obj16/activity.gif"; //$NON-NLS-1$;
         }
         else if (obj instanceof String) 
         {
            imageKey = "icons/full/obj16/role.gif"; //$NON-NLS-1$;
         }

         return DebugPlugin.getImage(imageKey);  
      }
   }
}