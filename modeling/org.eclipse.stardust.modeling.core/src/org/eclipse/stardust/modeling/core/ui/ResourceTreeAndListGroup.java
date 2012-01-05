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
package org.eclipse.stardust.modeling.core.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class ResourceTreeAndListGroup
      implements ICheckStateListener, ISelectionChangedListener, ITreeViewerListener
{
   private Object root;

   private Object currentTreeSelection;

   private Collection expandedTreeNodes = new HashSet();

   private ITreeContentProvider treeContentProvider;

   private IStructuredContentProvider listContentProvider;

   private ILabelProvider treeLabelProvider;

   private ILabelProvider listLabelProvider;

   private CheckboxTreeViewer treeViewer;

   private CheckboxTableViewer listViewer;

   private ListenerList listeners = new ListenerList();

   private static int PREFERRED_HEIGHT = 150;

   public ResourceTreeAndListGroup(Composite parent, Object rootObject,
         ITreeContentProvider treeContentProvider, ILabelProvider treeLabelProvider,
         IStructuredContentProvider listContentProvider,
         ILabelProvider listLabelProvider, int style, boolean useHeightHint)
   {
      root = rootObject;
      this.treeContentProvider = treeContentProvider;
      this.listContentProvider = listContentProvider;
      this.treeLabelProvider = treeLabelProvider;
      this.listLabelProvider = listLabelProvider;
      createContents(parent, style, useHeightHint);
   }

   public void addCheckStateListener(ICheckStateListener listener)
   {
      listeners.add(listener);
   }

   public void removeCheckStateListener(ICheckStateListener listener)
   {
      listeners.remove(listener);
   }

   public void checkStateChanged(final CheckStateChangedEvent event)
   {
      BusyIndicator.showWhile(treeViewer.getControl().getDisplay(), new Runnable()
      {
         public void run()
         {
            if (event.getCheckable().equals(treeViewer))
            {
               listViewer.setAllChecked(false);
               setTreeViewerAllChecked(false, treeViewer.getTree().getItems());
            }
            else
            {
               listViewer.setAllChecked(false);
               setTreeViewerAllChecked(false, treeViewer.getTree().getItems());
               treeViewer.setChecked(currentTreeSelection, true);
            }
            notifyCheckStateChangeListeners(event);
         }

         private void setTreeViewerAllChecked(boolean state, TreeItem[] items)
         {
            for (int i = 0; i < items.length; i++)
            {
               items[i].setChecked(state);
               TreeItem[] children = items[i].getItems();
               setTreeViewerAllChecked(state, children);
            }
         }
      });
   }

   protected void createContents(Composite parent, int style, boolean useHeightHint)
   {
      Composite composite = new Composite(parent, style);
      composite.setFont(parent.getFont());
      GridLayout layout = new GridLayout();
      layout.numColumns = 2;
      layout.makeColumnsEqualWidth = true;
      layout.marginHeight = 0;
      layout.marginWidth = 0;
      composite.setLayout(layout);
      composite.setLayoutData(new GridData(GridData.FILL_BOTH));

      createTreeViewer(composite, useHeightHint);
      createListViewer(composite, useHeightHint);

      initialize();
   }

   protected void createListViewer(Composite parent, boolean useHeightHint)
   {
      listViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER);
      GridData data = new GridData(GridData.FILL_BOTH);
      if (useHeightHint)
      {
         data.heightHint = PREFERRED_HEIGHT;
      }
      listViewer.getTable().setLayoutData(data);
      listViewer.getTable().setFont(parent.getFont());
      listViewer.setContentProvider(listContentProvider);
      listViewer.setLabelProvider(listLabelProvider);
      listViewer.addCheckStateListener(this);
   }

   protected void createTreeViewer(Composite parent, boolean useHeightHint)
   {
      Tree tree = new Tree(parent, SWT.CHECK | SWT.BORDER);
      GridData data = new GridData(GridData.FILL_BOTH);
      if (useHeightHint)
      {
         data.heightHint = PREFERRED_HEIGHT;
      }
      tree.setLayoutData(data);
      tree.setFont(parent.getFont());

      treeViewer = new CheckboxTreeViewer(tree);
      treeViewer.setContentProvider(treeContentProvider);
      treeViewer.setLabelProvider(treeLabelProvider);
      treeViewer.addTreeListener(this);
      treeViewer.addCheckStateListener(this);
      treeViewer.addSelectionChangedListener(this);
   }

   public void expandAll()
   {
      treeViewer.expandAll();
   }

   public List getAllCheckedListItems()
   {
      List checkedElements = new ArrayList();
      for (int j = 0; j < listViewer.getCheckedElements().length; j++)
      {
         if (listViewer.getCheckedElements()[j] instanceof IFile)
         {
            checkedElements.add(listViewer.getCheckedElements()[j]);
         }
      }
      return checkedElements;
   }

   public void initialCheckListItem(Object element)
   {
      Object parent = treeContentProvider.getParent(element);
      selectAndReveal(parent);
      listViewer.setChecked(element, true);
   }

   public void initialCheckTreeItem(Object element)
   {
      if (element.equals(currentTreeSelection))
      {
         listViewer.setAllChecked(false);
      }
      treeViewer.setChecked(element, true);
      selectAndReveal(element);
   }

   private void selectAndReveal(Object treeElement)
   {
      treeViewer.reveal(treeElement);
      IStructuredSelection selection = new StructuredSelection(treeElement);
      treeViewer.setSelection(selection);
   }

   protected void initialize()
   {
      treeViewer.setInput(root);
      this.expandedTreeNodes = new ArrayList();
      this.expandedTreeNodes.add(root);
   }

   protected void notifyCheckStateChangeListeners(final CheckStateChangedEvent event)
   {
      Object[] array = listeners.getListeners();
      for (int i = 0; i < array.length; i++)
      {
         final ICheckStateListener l = (ICheckStateListener) array[i];
         Platform.run(new SafeRunnable()
         {
            public void run()
            {
               l.checkStateChanged(event);
            }
         });
      }
   }

   protected void populateListViewer(final Object treeElement)
   {
      listViewer.setInput(treeElement);

      if (!(expandedTreeNodes.contains(treeElement)))
      {
         BusyIndicator.showWhile(treeViewer.getControl().getDisplay(), new Runnable()
         {
            public void run()
            {
               listViewer.setAllChecked(false);
            }
         });
      }
   }

   public void selectionChanged(SelectionChangedEvent event)
   {
      IStructuredSelection selection = (IStructuredSelection) event.getSelection();
      Object selectedElement = selection.getFirstElement();
      if (selectedElement == null)
      {
         currentTreeSelection = null;
         listViewer.setInput(currentTreeSelection);
         return;
      }
      if (selectedElement != currentTreeSelection)
      {
         populateListViewer(selectedElement);
      }
      currentTreeSelection = selectedElement;
   }

   public void treeCollapsed(TreeExpansionEvent event)
   {}

   public void treeExpanded(TreeExpansionEvent event)
   {}

   public void setFocus()
   {
      this.treeViewer.getTree().setFocus();
   }
}
