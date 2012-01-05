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
package org.eclipse.stardust.modeling.core.views.bookmark;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.dnd.ModelElementTransfer;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.AddViewAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.DeleteViewAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.RenameViewAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ShowPropertiesAction;
import org.eclipse.stardust.modeling.core.editors.parts.tree.WorkflowModelTreeEditPartFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;


public class WorkflowModelBookmarkView extends ViewPart
{
   private static final String[] PROPERTIES = new String[] {"viewTypes"}; //$NON-NLS-1$
   
   public final static String VIEW_ID = "org.eclipse.stardust.modeling.core.views.bookmark"; //$NON-NLS-1$

   private WorkflowModelEditor editor;

   private TreeViewer treeViewer;

   private boolean isDndInit = false;

   private EditDomain editDomain;

   private ModelType model;

   private Tree tree;

   private IAction[] actions = new IAction[4];

   public WorkflowModelBookmarkView()
   {
      super();
   }

   public void createPartControl(Composite parent)
   {
      init(parent);

      editor = getActiveEditor();
      if (editor != null)
      {
         initTree();
      }
      else
      {
         tree.setVisible(false);
      }
      addSelectionListener();
   }

   private void initTree()
   {
      editDomain = editor.getEditDomain();
      model = editor.getWorkflowModel();
      if (model != null)
      {
         treeViewer.setInput(model.eContainer());
         treeViewer.setColumnProperties(PROPERTIES);
         treeViewer.setCellEditors(new CellEditor[] {new TextCellEditor(treeViewer
               .getTree())});
         createActions();
         createCtxMenu();
         addDnd();
      }
   }

   private void init(Composite parent)
   {
      tree = new Tree(parent, SWT.NONE);
      treeViewer = new TreeViewer(tree);
      treeViewer.setContentProvider(new BookmarkViewContentProvider());
      treeViewer.setLabelProvider(new BookmarkViewLabelProvider());
      treeViewer.setSorter(new ViewerSorter());
      treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            Object element = ((IStructuredSelection) event.getSelection())
                  .getFirstElement();
            if (element instanceof ViewableType)
            {
               EditPart part = new WorkflowModelTreeEditPartFactory(editor).createEditPart(
                     null, ((ViewableType) element).getViewable());
               getSite().getSelectionProvider().setSelection(
                     new StructuredSelection(part));
            }
            else if (element instanceof ViewType)
            {
               getSite().getSelectionProvider().setSelection(
                     new StructuredSelection(element));
            }
            else if (element instanceof ModelType)
            {
               getSite().getSelectionProvider().setSelection(
                     new StructuredSelection(new WorkflowModelTreeEditPartFactory(editor)
                           .createEditPart(null, element)));
            }
            actions[0]
                  .setEnabled(!((element instanceof ModelType) || (element instanceof ViewableType)));
            actions[1].setEnabled(!(element instanceof ModelType));
            actions[2].setEnabled(!(element instanceof ViewType));
            createCtxMenu();
         }
      });
      treeViewer.addDoubleClickListener(new IDoubleClickListener()
      {
         public void doubleClick(DoubleClickEvent event)
         {
            Object element = ((IStructuredSelection) event.getSelection())
                  .getFirstElement();
            if (element instanceof ViewType)
            {
               if (treeViewer.getExpandedState(element))
               {
                  treeViewer.collapseToLevel(element, AbstractTreeViewer.ALL_LEVELS);
               }
               else
               {
                  treeViewer.expandToLevel(element, AbstractTreeViewer.ALL_LEVELS);
               }
            }
            else
            {
               new ShowPropertiesAction(editor, getSite().getSelectionProvider()).run();
            }
         }
      });

      getSite().setSelectionProvider(new ISelectionProvider()
      {
         private ISelection selection;

         public void addSelectionChangedListener(ISelectionChangedListener listener)
         {}

         public ISelection getSelection()
         {
            return selection;
         }

         public void removeSelectionChangedListener(ISelectionChangedListener listener)
         {}

         public void setSelection(ISelection selection)
         {
            this.selection = selection;
         }
      });
   }

   private void addDnd()
   {
      if (!isDndInit)
      {
         treeViewer.addDragSupport(DND.DROP_COPY, new Transfer[] {ModelElementTransfer
               .getInstance()}, new BookmarkDragSourceListener(treeViewer));
         treeViewer.addDropSupport(DND.DROP_COPY, new Transfer[] {ModelElementTransfer
               .getInstance()}, new BookmarkDropTargetListener(treeViewer, editDomain));
         isDndInit = true;
      }
   }

   private void createCtxMenu()
   {
      MenuManager menuManager = new MenuManager();
      for (int i = 0; i < actions.length; i++)
      {
         if (actions[i].isEnabled())
         {
            menuManager.add(actions[i]);
         }
      }
      Menu contextMenu = menuManager.createContextMenu(treeViewer.getTree());
      treeViewer.getTree().setMenu(contextMenu);
   }

   private void createActions()
   {
      actions[0] = new RenameViewAction(editDomain, treeViewer);
      actions[1] = new DeleteViewAction(editDomain, treeViewer);
      actions[2] = new ShowPropertiesAction(editor, getSite().getSelectionProvider());
      actions[3] = new AddViewAction(model, editDomain, treeViewer);
   }

   private WorkflowModelEditor getActiveEditor()
   {
      WorkflowModelEditor editor = null;
      IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
            .getActivePage();
      if (page != null)
      {
         IEditorPart editorPart = page.getActiveEditor();
         if (editorPart instanceof WorkflowModelEditor)
         {
            editor = (WorkflowModelEditor) editorPart;
         }
      }
      return editor;
   }

   public void setFocus()
   {}

   public void updateViewer()
   {
      treeViewer.refresh();
   }

   private void addSelectionListener()
   {
      getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(
            new ISelectionListener()
            {
               public void selectionChanged(IWorkbenchPart part, ISelection selection)
               {
                  if (getActiveEditor() != null)
                  {
                     if (!getActiveEditor().equals(editor))
                     {
                        tree.setVisible(true);
                        editor = getActiveEditor();
                        initTree();
                        updateViewer();
                     }
                  }
                  else if (getActiveEditor() == null)
                  {
                     tree.setVisible(false);
                     editor = null;
                  }
               }
            });
   }
}
