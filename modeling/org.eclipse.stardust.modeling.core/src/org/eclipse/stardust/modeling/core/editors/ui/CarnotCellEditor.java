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
package org.eclipse.stardust.modeling.core.editors.ui;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public abstract class CarnotCellEditor
{
   private ArrayList<TreeEditor> editors = new ArrayList<TreeEditor>();
   private int column;
   
   public abstract boolean canEdit(Object item);
   public abstract Object getValue(Object item);
   public abstract void setValue(Object item, Object value);
   public abstract Control createControl(Composite parent, Object item);
   public abstract void refresh(TreeEditor editor);
   
   public CarnotCellEditor(int column)
   {
      this.column = column;
   }
   
   public void setTree(Tree tree)
   {
      ArrayList<TreeEditor> free = new ArrayList<TreeEditor>();
      HashSet<TreeItem> used = new HashSet<TreeItem>();
      for (TreeEditor editor : editors)
      {
         TreeItem treeItem = editor.getItem();
         if (treeItem.isDisposed())
         {
            free.add(editor);
         }
         else
         {
            used.add(treeItem);
            editor.layout();
         }
      }
      for (int i = free.size() - 1; i >= 0; i--)
      {
         TreeEditor editor = free.get(i);
         editors.remove(editor);
         editor.getEditor().dispose();
         editor.dispose();
      }
      addItems(tree, tree.getItems(), used);
   }
   
   private void addItems(Tree tree, TreeItem[] items, HashSet<TreeItem> used)
   {
      for (int i = 0; i < items.length; i++)
      {
         TreeItem treeItem = items[i];
         if (!used.contains(treeItem))
         {
            Object data = treeItem.getData();
            if (canEdit(data))
            {
               Control control = createControl(tree, data);
               TreeEditor editor = new TreeEditor(tree);
               editor.grabHorizontal = true;
               control.setBackground(tree.getBackground());
               control.setForeground(tree.getForeground());
               editor.setEditor(control, treeItem, column);
               editors.add(editor);
            }
         }
         addItems(tree, treeItem.getItems(), used);
      }
   }
   
   public void dispose()
   {
      for (TreeEditor editor : editors)
      {
         editor.getEditor().dispose();
         editor.dispose();
      }
      editors.clear();
   }
   
   public void refresh()
   {
      for (TreeEditor editor : editors)
      {
         refresh(editor);
      }
   }
}
