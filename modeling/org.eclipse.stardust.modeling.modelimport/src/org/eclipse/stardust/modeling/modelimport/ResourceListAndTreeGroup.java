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
package org.eclipse.stardust.modeling.modelimport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

public class ResourceListAndTreeGroup implements ICheckStateListener
{
   private FileSystemStore root;

   private Map checkedStateStore = new HashMap(9);
   private ListenerList listeners = new ListenerList();

   // widgets
   private CheckboxTreeViewer treeViewer;
   private CheckboxTableViewer listViewer;

   //height hint for viewers
   private static int PREFERRED_HEIGHT = 150;

   public ResourceListAndTreeGroup(Composite parent, FileSystemStore rootObject,
                                   int style, boolean useHeightHint)
   {
      root = rootObject;
      createContents(parent, style, useHeightHint);
   }

   public void checkStateChanged(final CheckStateChangedEvent event)
   {

      //Potentially long operation - show a busy cursor
      BusyIndicator.showWhile(treeViewer.getControl().getDisplay(),
         new Runnable()
         {
            public void run()
            {
               if (event.getCheckable().equals(treeViewer))
               {
                  treeItemChecked((ModelNode) event.getElement(), event.getChecked());
               }
               else
               {
                  listItemChecked((ModelNode) event.getElement(), event.getChecked());
               }
               notifyCheckStateChangeListeners(event);
            }
         });
   }

   protected void createContents(Composite parent, int style,
                                 boolean useHeightHint)
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

      createListViewer(composite, useHeightHint);
      createTreeViewer(composite, useHeightHint);

      initialize();
   }

   protected void createListViewer(Composite parent, boolean useHeightHint)
   {
      listViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER);
      GridData data = new GridData(GridData.FILL_BOTH);
      if (useHeightHint)
         data.heightHint = PREFERRED_HEIGHT;
      listViewer.getTable().setLayoutData(data);
      listViewer.getTable().setFont(parent.getFont());
      listViewer.setContentProvider(new IStructuredContentProvider()
      {
         public Object[] getElements(Object inputElement)
         {
            return ((FileSystemStore) inputElement).getRootModels();
         }

         public void dispose()
         {
         }

         public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
         {
         }
      });
      listViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            ModelNode node = (ModelNode) element;
            return node.getId();
         }
      });
      listViewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            IStructuredSelection selection = (IStructuredSelection) event
               .getSelection();
            ModelNode node = (ModelNode) selection.getFirstElement();
            if (node == null)
            {
               treeViewer.setInput(new ModelNode[0]);
            }
            else
            {
               treeViewer.setInput(new ModelNode[] {node});
               treeViewer.expandAll();
               Set checked = (Set) checkedStateStore.get(node.getId());
               if (checked != null)
               {
                  treeViewer.setCheckedElements(checked.toArray());
               }
            }
         }
      });
      listViewer.addCheckStateListener(this);
   }

   protected void createTreeViewer(Composite parent, boolean useHeightHint)
   {
      Tree tree = new Tree(parent, SWT.CHECK | SWT.BORDER);
      GridData data = new GridData(GridData.FILL_BOTH);
      if (useHeightHint)
         data.heightHint = PREFERRED_HEIGHT;
      tree.setLayoutData(data);
      tree.setFont(parent.getFont());

      treeViewer = new CheckboxTreeViewer(tree);
      treeViewer.setContentProvider(new ITreeContentProvider()
      {
         public Object[] getChildren(Object parentElement)
         {
            ModelNode node = (ModelNode) parentElement;
            Object[] versions = node.getVersions();
            return versions;
         }

         public Object getParent(Object element)
         {
            ModelNode node = (ModelNode) element;
            return node.getParent();
         }

         public boolean hasChildren(Object element)
         {
            ModelNode node = (ModelNode) element;
            return node.hasChildren();
         }

         public Object[] getElements(Object inputElement)
         {
            ModelNode[] root = (ModelNode[]) inputElement;
            return root;
         }

         public void dispose()
         {
         }

         public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
         {
         }
      });
      treeViewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            ModelNode node = (ModelNode) element;
            return node.getFullName();
         }
      });
      treeViewer.addCheckStateListener(this);
   }

   protected void initialize()
   {
      listViewer.setInput(root);
   }

   protected void listItemChecked(ModelNode listElement, boolean state)
   {
      listViewer.setGrayed(listElement, false);
      if (state)
      {
         Set set = (Set) checkedStateStore.get(listElement.getId());
         if (set == null)
         {
            set = new HashSet(9);
            checkedStateStore.put(listElement.getId(), set);
         }
         addAll(set, listElement);
      }
      else
      {
         checkedStateStore.remove(listElement.getId());
      }
      ModelNode node = (ModelNode) ((IStructuredSelection)
         listViewer.getSelection()).getFirstElement();
      if (listElement == node)
      {
         treeViewer.expandAll();
         treeViewer.setSubtreeChecked(node, state);
      }
   }

   protected void treeItemChecked(ModelNode listElement, boolean state)
   {
      Set set = (Set) checkedStateStore.get(listElement.getId());
      ModelNode node = root.findRootModel(listElement.getId());
      if (state)
      {
         if (set == null)
         {
            set = new HashSet(9);
            checkedStateStore.put(listElement.getId(), set);
         }
         set.add(listElement);
         if (set.size() == countVersions(node))
         {
            setChecked(node);
         }
         else
         {
            setGrayChecked(node);
         }
      }
      else
      {
         if (set != null)
         {
            set.remove(listElement);
            if (set.isEmpty())
            {
               checkedStateStore.remove(listElement.getId());
               set = null;
            }
         }
         if (set == null)
         {
            setUnchecked(node);
         }
         else
         {
            setGrayChecked(node);
         }
      }
   }

   private void setGrayChecked(ModelNode node)
   {
      listViewer.setChecked(node, true);
      listViewer.setGrayed(node, true);
   }

   private void setUnchecked(ModelNode node)
   {
      listViewer.setChecked(node, false);
      listViewer.setGrayed(node, false);
   }

   private void setChecked(ModelNode node)
   {
      listViewer.setChecked(node, true);
      listViewer.setGrayed(node, false);
   }

   private int countVersions(ModelNode node)
   {
      int count = 1;
      Object[] versions = node.getVersions();
      for (int i = 0; i < versions.length; i++)
      {
         count += countVersions((ModelNode) versions[i]);
      }
      return count;
   }

   private void addAll(Set set, ModelNode node)
   {
      set.add(node);
      for (Iterator i = node.publicVersions(); i.hasNext();)
      {
         addAll(set, (ModelNode) i.next());
      }
   }

   protected void notifyCheckStateChangeListeners(
      final CheckStateChangedEvent event)
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

   public void addCheckStateListener(ICheckStateListener listener)
   {
      listeners.add(listener);
   }

   public void removeCheckStateListener(ICheckStateListener listener)
   {
      listeners.remove(listener);
   }

   public void setAllSelections(boolean selection)
   {
      //If there is no root there is nothing to select
      if (root == null)
      {
         return;
      }
      listViewer.setAllChecked(selection);
      listViewer.setAllGrayed(false);
      Object[] models = root.getRootModels();
      for (int i = 0; i < models.length; i++)
      {
         listItemChecked((ModelNode) models[i], selection);
      }
   }

   public void setRoot(FileSystemStore newRoot)
   {
      this.root = newRoot;
      initialize();
   }

   public void setFocus()
   {
      listViewer.getTable().setFocus();
   }

   public List getAllCheckedListItems()
   {
      List result = new ArrayList();
      for (Iterator i = checkedStateStore.values().iterator(); i.hasNext();)
      {
         Set set = (Set) i.next();
         result.addAll(set);
      }
      return result;
   }
}

