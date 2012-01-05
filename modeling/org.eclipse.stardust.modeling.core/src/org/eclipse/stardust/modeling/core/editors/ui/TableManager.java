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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.BindingManager;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtWidgetAdapter;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;


/**
 * @author fherinean
 * @version $Revision$
 */
public class TableManager implements ControlListener
{
   private String[] attributeNames;

   private int[] columnSizes;

   private String defaultIcon;

   private Control control;
   private Map editors;

   BindingManager binding = new BindingManager();

   private ArrayList providers = new ArrayList();

   public TableManager(Table table)
   {
      setControl(table);
   }

   public TableManager(Tree tree)
   {
      setControl(tree);
   }
   
   public boolean isDisposed()
   {
      return (null == control) || control.isDisposed();
   }

   private void setControl(Control control)
   {
      this.control = control;
      control.addControlListener(this);
      control.addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            binding.dispose();
         }
      });
   }

   public void addLabelProvider(TableLabelProvider labelProvider)
   {
      providers.add(labelProvider);
   }

   public int getRowCount()
   {
      if (control instanceof Table)
      {
         return ((Table) control).getItemCount();
      }
      else if (control instanceof Tree)
      {
         return ((Tree) control).getItemCount();
      }
      return 1;
   }

   public void controlMoved(ControlEvent e)
   {}

   public void controlResized(ControlEvent e)
   {
      resize();
   }

   public void setColumnSizes(int[] columnSizes)
   {
      this.columnSizes = columnSizes;
      resize();
   }

   private void resize()
   {
      if (!control.isDisposed() && getColumnCount() > 0 && columnSizes != null)
      {
         int cumulated = 0;
         int percentage = 0;
         int size = control.getSize().x - control.getBorderWidth() * 2;
         if (control instanceof Scrollable)
         {
            Rectangle clientArea = ((Scrollable) control).getClientArea();
            size = clientArea.width;
         }
         for (int i = 0; i < columnSizes.length; i++)
         {
            if (i == getColumnCount())
            {
               break;
            }
            percentage += columnSizes[i];
            int cs = percentage == 100 ? size - cumulated : size * columnSizes[i] / 100;
            setColumnWidth(i, cs);
            cumulated += cs;
         }
      }
   }

   private int getColumnCount()
   {
      if (control instanceof Table)
      {
         return ((Table) control).getColumnCount();
      }
      else if (control instanceof Tree)
      {
         return ((Tree) control).getColumnCount();
      }
      return 1;
   }

   private void setColumnWidth(int columnIndex, int size)
   {
      if (control instanceof Table)
      {
         TableColumn column = ((Table) control).getColumn(columnIndex);
         column.setWidth(size);
      }
      else if (control instanceof Tree)
      {
         TreeColumn column = ((Tree) control).getColumn(columnIndex);
         column.setWidth(size);
      }
   }

   public Image[] getColumnImage(Object element)
   {
      TableLabelProvider provider = getProvider(element);
      Image image = provider.getImage(element);
      if (image == null && defaultIcon != null)
      {
         image = DiagramPlugin.getImage(defaultIcon);
      }
      return image == null ? new Image[0] : new Image[] {image};
   }

   public String[] getColumnText(Object element)
   {
      String[] text = new String[getColumnCount()];
      TableLabelProvider provider = getProvider(element);
      for (int i = 0; i < text.length; i++)
      {
         if (attributeNames != null && i < attributeNames.length && provider.isNamed())
         {
            text[i] = provider.getText(attributeNames[i], element);
         }
         else
         {
            text[i] = provider.getText(i, element);
         }
      }
      return text;
   }

   private TableLabelProvider getProvider(Object element)
   {
      for (int i = 0; i < providers.size(); i++)
      {
         TableLabelProvider provider = (TableLabelProvider) providers.get(i);
         if (provider.accept(element))
         {
            return provider;
         }
      }
      return DefaultTableLabelProvider.instance();
   }

   public void setColumnNames(String[] columnNames)
   {
      if (!control.isDisposed())
      {
         for (int i = 0; i < columnNames.length; i++)
         {
            if (control instanceof Table)
            {
               TableColumn column = i == getColumnCount() ? new TableColumn(
                     (Table) control, SWT.NONE) : ((Table) control).getColumn(i);
               column.setText(columnNames[i]);
            }
            else if (control instanceof Tree)
            {
               TreeColumn column = i == getColumnCount() ? new TreeColumn((Tree) control,
                     SWT.NONE) : ((Tree) control).getColumn(i);
               column.setText(columnNames[i]);
            }
         }
      }
   }

   public void setAttributeNames(String[] attributeNames)
   {
      this.attributeNames = attributeNames;
   }

   public void removeAll()
   {
      binding.dispose();
      if (control instanceof Table)
      {
         ((Table) control).removeAll();
      }
      else if (control instanceof Tree)
      {
         ((Tree) control).removeAll();
      }
   }

   public void removeAll(Object root)
   {
      Item item = findItem(root);
      if (item instanceof TreeItem)
      {
         TreeItem treeItem = (TreeItem) item;
         for (int i = 0; i < treeItem.getItemCount(); i++)
         {
            binding.unbind(treeItem.getItem(i).getData());
         }
         treeItem.removeAll();
      }
   }

   public Object getElementAt(int[] index)
   {
      Item item = getItem(index);
      return item == null ? null : item.getData();
   }

   public void addElement(Object element)
   {
      addElement(null, element);
   }

   public Item addElement(Object parent, Object element)
   {
      Item newItem = null;
      if (!control.isDisposed())
      {
         if (control instanceof Table)
         {
            newItem = createTableItem(element);
         }
         else if (control instanceof Tree)
         {
            TreeItem parentItem = (TreeItem) (parent == null ? null : findItem(parent));
            newItem = createTreeItem(parentItem, element);
         }
      }
      return newItem;
   }

   private TableItem createTableItem(Object element)
   {
      return createTableItem(getRowCount(), element);
   }

   private TableItem createTableItem(int index, Object element)
   {
      TableItem item = new TableItem((Table) control, SWT.NONE, index);
      item.setData(element);
      if (element instanceof EObject)
      {
         binding.bind((EObject) element, getBinder((EObject) element, item));
      }
      else
      {
         item.setText(getColumnText(element));
         item.setImage(getColumnImage(element));
      }
      return item;
   }

   private IWidgetAdapter getBinder(final EObject element, Item item)
   {
      return new SwtWidgetAdapter(item)
      {
         public void updateControl(Object value)
         {
            Widget item = getWidget();
            if (item instanceof TableItem)
            {
               ((TableItem) item).setText(getColumnText(element));
               ((TableItem) item).setImage(getColumnImage(element));
            }
            else if (item instanceof TreeItem)
            {
               ((TreeItem) item).setText(getColumnText(element));
               ((TreeItem) item).setImage(getColumnImage(element));
            }
         }
      };
   }

   private TreeItem createTreeItem(TreeItem parentItem, Object element)
   {
      TreeItem item = parentItem == null
            ? new TreeItem((Tree) control, SWT.NONE)
            : new TreeItem(parentItem, SWT.NONE);
      item.setData(element);
      if (element instanceof EObject)
      {
         binding.bind((EObject) element, getBinder((EObject) element, item));
      }
      else
      {
         item.setText(getColumnText(element));
         item.setImage(getColumnImage(element));
      }
      if (parentItem != null)
      {
         parentItem.setExpanded(true);
      }
      return item;
   }

   public void setGrayed(Object element, boolean grayed)
   {
      Item item = findItem(element);
      if (item instanceof TableItem)
      {
         ((TableItem) item).setGrayed(grayed);
         ((TableItem) item).setForeground(grayed ? ColorConstants.gray : ColorConstants.listForeground);
      }
      else if (item instanceof TreeItem)
      {
         ((TreeItem) item).setGrayed(grayed);
         ((TreeItem) item).setForeground(grayed ? ColorConstants.gray : ColorConstants.listForeground);
      }
   }

   public boolean getGrayed(Object element)
   {
      Item item = findItem(element);
      if (item instanceof TableItem)
      {
         return ((TableItem) item).getGrayed();
      }
      else if (item instanceof TreeItem)
      {
         return ((TreeItem) item).getGrayed();
      }
      return false;
   }

   public void setChecked(Object element, boolean checked)
   {
      Item item = findItem(element);
      if (item instanceof TableItem)
      {
         ((TableItem) item).setChecked(checked);
      }
      else if (item instanceof TreeItem)
      {
         ((TreeItem) item).setChecked(checked);
      }
   }

   public boolean getChecked(int index)
   {
      return isItemChecked(getItem(new int[] {index}));
   }

   public boolean getChecked(Object element)
   {
      return isItemChecked(findItem(element));
   }

   private boolean isItemChecked(Item item)
   {
      if (item instanceof TableItem)
      {
         return ((TableItem) item).getChecked();
      }
      else if (item instanceof TreeItem)
      {
         ((TreeItem) item).getChecked();
      }
      return false;
   }

   private Item getItem(int[] index)
   {
      if (control instanceof Table)
      {
         return ((Table) control).getItem(index[0]);
      }
      else if (control instanceof Tree)
      {
         return ((Tree) control).getItem(index[0]);
      }
      return null;
   }

   private Item findItem(Object element)
   {
      int[] i = new int[1];
      int rowCount = getRowCount();
      for (i[0] = 0; i[0] < rowCount; i[0]++)
      {
         Item item = findItem(getItem(i), element);
         if (item != null)
         {
            return item;
         }
      }
      return null;
   }

   private Item findItem(Item item, Object element)
   {
      if (item != null && element.equals(item.getData()))
      {
         return item;
      }
      if (item instanceof TreeItem)
      {
         TreeItem treeItem = (TreeItem) item;
         int itemCount = treeItem.getItemCount();
         for (int i = 0; i < itemCount; i++)
         {
            item = findItem(treeItem.getItem(i), element);
            if (item != null)
            {
               return item;
            }
         }
      }
      return null;
   }

   public void setDefaultIcon(String defaultIcon)
   {
      this.defaultIcon = defaultIcon;
   }

   public void setTopIndex(int[] index)
   {
      if (control instanceof Table)
      {
         ((Table) control).setTopIndex(index[0]);
      }
      else if (control instanceof Tree)
      {
         TreeItem item = (TreeItem) getItem(index);
         ((Tree) control).setTopItem(item);
      }
   }

   public void selectByText(String text)
   {
      if (control instanceof Table)
      {
//         ((Table) control).setTopIndex(index[0]);
      }
      else if (control instanceof Tree)
      {
         //todo
/*         TreeItem items = getTreeItems(index);
         if (!items.isEmpty())
         {
            ((Tree) control).setTopItem((TreeItem) items.get(items.size() - 1));
         }*/
      }
   }

   public void setSelection(int[] index)
   {
      if (control instanceof Table)
      {
         ((Table) control).setSelection((0 < index.length) ? index[0] : 0);
      }
      else if (control instanceof Tree)
      {
         ArrayList items = getTreeItems(index);
         ((Tree) control).setSelection((TreeItem[]) items.toArray(new TreeItem[items.size()]));
      }
   }

   private ArrayList getTreeItems(int[] index)
   {
      ArrayList items = new ArrayList();
      TreeItem top = ((Tree) control).getItem(index[0]);
      if (top != null)
      {
         items.add(top);
         for (int i = 1; i < index.length; i++)
         {
            TreeItem item = top.getItem(index[i]);
            if (item != null)
            {
               top = item;
               items.add(top);
            }
         }
      }
      return items;
   }

   public int[] getSelectionIndex()
   {
      int[] selection = null;
      if (control instanceof Table)
      {
         selection = new int[] {((Table) control).getSelectionIndex()};
      }
      else if (control instanceof Tree)
      {
         TreeItem[] items = ((Tree) control).getSelection();
         if (items.length > 0)
         {
            selection = new int[items.length];
            selection[0] = ((Tree) control).indexOf(items[0]);
            for (int i = 1; i < items.length; i++)
            {
               selection[i] = items[i - 1].indexOf(items[i]);
            }
         }
      }
      if (selection == null)
      {
         selection = new int[] {-1};
      }
      return selection;
   }

   public void showSelection()
   {
      if (control instanceof Table)
      {
         ((Table) control).showSelection();
      }
      else if (control instanceof Tree)
      {
         ((Tree) control).showSelection();
      }
   }

   public void deselect(int index)
   {
      if (control instanceof Table)
      {
         ((Table) control).deselect(index);
      }
      else if (control instanceof Tree)
      {
         ((Tree) control).deselectAll();
      }
   }

   public void deselectAll()
   {
      if (control instanceof Table)
      {
         ((Table) control).deselectAll();
      }
      else if (control instanceof Tree)
      {
         ((Tree) control).deselectAll();
      }
   }

   public Object getSelectedItem()
   {
      Item[] selection = null;
      if (control instanceof Table)
      {
         selection = ((Table) control).getSelection();
      }
      else if (control instanceof Tree)
      {
         selection = ((Tree) control).getSelection();
      }
      return selection.length > 0 ? selection[selection.length - 1].getData() : null;
   }

   public TreeItem getSelectedTreeItem() {
      if(control instanceof Tree) {
         return ((Tree) control).getSelection()[0];
      }
      return null;
   }

   public void setHeaderVisible(boolean visible)
   {
      if (control instanceof Table)
      {
         ((Table) control).setHeaderVisible(visible);
      }
      else if (control instanceof Tree)
      {
         ((Tree) control).setHeaderVisible(visible);
      }
   }

   public Point computeSize(int wHint, int itemCount, boolean changed)
   {
      int itemHeight = SWT.DEFAULT;
      if (itemCount != SWT.DEFAULT)
      {
         if (control instanceof Table)
         {
            itemHeight = ((Table) control).getItemHeight() * itemCount;
         }
         else if (control instanceof Tree)
         {
            itemHeight = ((Tree) control).getItemHeight() * itemCount;
         }
      }
      return control.computeSize(SWT.DEFAULT, itemHeight, changed);
   }

   public void expandAll()
   {
      if (control instanceof Tree)
      {
         TreeItem [] items = ((Tree) control).getItems();
         for (int i = 0; i < items.length; i++)
         {
            items[i].setExpanded(true);
         }
      }
   }

   public void setInPlaceEditor(Object element, Control cellEditor, int column)
   {
      Item item = findItem(element);
      if (item != null)
      {
         if (control instanceof Tree)
         {
            TreeEditor editor = (TreeEditor) getEditor(element);
            Control oldEditor = editor.getEditor();
            if (oldEditor != null)
            {
               oldEditor.dispose();
            }
            cellEditor.setBackground(control.getBackground());
            cellEditor.setForeground(control.getForeground());
            editor.setEditor(cellEditor, (TreeItem) item, column);
         }
      }
   }

   private ControlEditor getEditor(Object element)
   {
      if (editors == null)
      {
         editors = new HashMap();
      }
      ControlEditor editor = (ControlEditor) editors.get(element);
      if (editor == null)
      {
         if (control instanceof Tree)
         {
            editor = new TreeEditor((Tree) control);
         }
         editor.grabHorizontal = true;
         editors.put(element, editor);
      }
      return editor;
   }

   public Control getInPlaceEditor(Object element)
   {
      ControlEditor editor = (ControlEditor)
         (editors == null ? null : editors.get(element));
      return editor == null ? null : editor.getEditor();
   }

   public void updateItem(Object o)
   {
      Item item = findItem(o);
      if (item instanceof TreeItem)
      {
         ((TreeItem) item).setText(getColumnText(o));
         ((TreeItem) item).setImage(getColumnImage(o));
      }
      else if (item instanceof TableItem)
      {
         ((TableItem) item).setText(getColumnText(o));
         ((TableItem) item).setImage(getColumnImage(o));
      }
   }

   public List getChildren(Object parent)
   {
      if (control instanceof Tree)
      {
         TreeItem[] items = null;
         if (parent == null)
         {
            items = ((Tree) control).getItems();
         }
         else
         {
            TreeItem item = (TreeItem) findItem(parent);
            if (item != null)
            {
               items = item.getItems();
            }
         }
         if (items != null)
         {
            ArrayList result = new ArrayList(items.length);
            for (int i = 0; i < items.length; i++)
            {
               result.add(items[i].getData());
            }
            return result;
         }
      }
      else if (control instanceof Table)
      {
         if (parent == null)
         {
            TableItem[] items = ((Table) control).getItems();
            ArrayList result = new ArrayList(items.length);
            for (int i = 0; i < items.length; i++)
            {
               result.add(items[i].getData());
            }
            return result;
         }
      }
      return Collections.EMPTY_LIST;
   }

   public void select(Object object)
   {
      if (control instanceof Table)
      {
         ((Table) control).setSelection(new TableItem[] {(TableItem) findItem(object)});
      }
      else if (control instanceof Tree)
      {
         ((Tree) control).setSelection(new TreeItem[] {(TreeItem) findItem(object)});
      }
   }

   public void setFocus()
   {
      control.setFocus();
   }

   public void setRedraw(boolean redraw)
   {
      control.setRedraw(redraw);
   }

   public boolean isExpanded(Object o)
   {
      Item item = findItem(o);
      return item instanceof TreeItem ? ((TreeItem) item).getExpanded() : false;
   }

   public void setExpanded(Object o, boolean expanded)
   {
      Item item = findItem(o);
      if (item instanceof TreeItem)
      {
         ((TreeItem) item).setExpanded(expanded);
      }
   }

   public Object getExpandedSnapshot()
   {
      return getExpandedSnapshot(null);
   }

   public Object getExpandedSnapshot(List subset)
   {
      Map states = new HashMap();
      int[] i = new int[1];
      int rowCount = getRowCount();
      for (i[0] = 0; i[0] < rowCount; i[0]++)
      {
         Item item = getItem(i);
         getExpandedSnapshot(states, item, subset);
      }
      return states;
   }

   private void getExpandedSnapshot(Map states, Item item, List subset)
   {
      Object o = item.getData();
      if (item instanceof TableItem)
      {
         if (subset == null || subset.contains(o))
         {
            states.put(o, Boolean.FALSE);
         }
      }
      else if (item instanceof TreeItem)
      {
         TreeItem treeItem = (TreeItem) item;
         if (subset == null || subset.contains(o))
         {
            states.put(o, treeItem.getExpanded() ? Boolean.TRUE : Boolean.FALSE);
         }
         int itemCount = treeItem.getItemCount();
         for (int i = 0; i < itemCount; i++)
         {
            getExpandedSnapshot(states, treeItem.getItem(i), subset);
         }
      }
   }

   public void restoreFromExpandedSnapshot(Object snapshot)
   {
      if (snapshot != null)
      {
         Map states = (Map) snapshot;
         for (Iterator i = states.keySet().iterator(); i.hasNext();)
         {
            Object o = i.next();
            setExpanded(o, ((Boolean) states.get(o)).booleanValue());
         }
      }
   }

   public void remove(Object object)
   {
      Item item = findItem(object);
      if (item != null)
      {
         if (control instanceof Table)
         {
            ((Table) control).remove(((Table) control).indexOf((TableItem) item));
         }
         else if (control instanceof Tree)
         {
            TreeItem parent = ((TreeItem) item).getParentItem();
            if (parent == null)
            {
               // todo:
            }
            else
            {
            }
         }
      }
   }

   public void insert(Object parent, Object object, int index)
   {
      // todo:
   }

   public boolean canMoveUp(Object object, boolean canChangeParent)
   {
      Item item = findItem(object);
      if (item != null)
      {
         if (control instanceof Table)
         {
            return ((Table) control).indexOf((TableItem) item) > 0;
         }
         else if (control instanceof Tree)
         {
            TreeItem treeItem = (TreeItem) item;
            if (treeItem.getParentItem() == null)
            {
               return ((Tree) control).indexOf(treeItem) > 0;
            }
            else
            {
               TreeItem parent = treeItem.getParentItem();
               if (parent.indexOf(treeItem) > 0)
               {
                  return true;
               }
               if (canChangeParent)
               {
                  if (parent.getParentItem() == null)
                  {
                     return ((Tree) control).indexOf(parent) > 0;
                  }
                  else
                  {
                     return parent.getParentItem().indexOf(parent) > 0;
                  }
               }
            }
         }
      }
      return false;
   }

   public boolean canMoveDown(Object object, boolean canChangeParent)
   {
      Item item = findItem(object);
      if (item != null)
      {
         if (control instanceof Table)
         {
            return ((Table) control).indexOf((TableItem) item)
               < ((Table) control).getItemCount() - 1;
         }
         else if (control instanceof Tree)
         {
            TreeItem treeItem = (TreeItem) item;
            if (treeItem.getParentItem() == null)
            {
               return ((Tree) control).indexOf(treeItem)
                  < ((Tree) control).getItemCount() - 1;
            }
            else
            {
               TreeItem parent = treeItem.getParentItem();
               if (parent.indexOf(treeItem) < parent.getItemCount() - 1)
               {
                  return true;
               }
               if (canChangeParent)
               {
                  if (parent.getParentItem() == null)
                  {
                     return ((Tree) control).indexOf(parent)
                        < ((Tree) control).getItemCount() - 1;
                  }
                  else
                  {
                     return parent.getParentItem().indexOf(parent)
                        < parent.getParentItem().getItemCount() - 1;
                  }
               }
            }
         }
      }
      return false;
   }

   public void moveUp(Object object, boolean canChangeParent)
   {
      Item item = findItem(object);
      if (item != null)
      {
         control.setRedraw(false);
         if (control instanceof Table)
         {
            int index = ((Table) control).indexOf((TableItem) item);
            if (index > 0)
            {
               TableItem theOther = ((Table) control).getItem(index - 1);
               switchItems(item, theOther);
            }
         }
         else if (control instanceof Tree)
         {
            if (((TreeItem) item).getParentItem() == null)
            {
               int index = ((Tree) control).indexOf((TreeItem) item);
               if (index > 0)
               {
                  TreeItem theOther = ((Tree) control).getItem(index - 1);
                  switchItems(item, theOther);
               }
            }
            else
            {
               TreeItem parent = ((TreeItem) item).getParentItem();
               int index = parent.indexOf((TreeItem) item);
               if (index > 0)
               {
                  TreeItem theOther = parent.getItem(index - 1);
                  switchItems(item, theOther);
               }
               else if (canChangeParent)
               {
                  SnapshotNode snParent = takeSnapshot(parent);
                  SnapshotNode snItem = (SnapshotNode) snParent.children.remove(0);
                  restoreFromSnapshot(parent, snParent, true);

                  TreeItem newItem;
                  if (parent.getParentItem() == null)
                  {
                     parent = ((Tree) control).getItem(
                        ((Tree) control).indexOf(parent) - 1);
                  }
                  else
                  {
                     parent = parent.getParentItem().getItem(
                        parent.getParentItem().indexOf(parent) - 1);
                  }
                  newItem = new TreeItem(parent, SWT.NONE);
                  restoreFromSnapshot(newItem, snItem);
               }
            }
         }
         control.setRedraw(true);
      }
   }

   public void moveDown(Object object, boolean canChangeParent)
   {
      Item item = findItem(object);
      if (item != null)
      {
         control.setRedraw(false);
         if (control instanceof Table)
         {
            int index = ((Table) control).indexOf((TableItem) item);
            if (index < ((Table) control).getItemCount() - 1)
            {
               TableItem theOther = ((Table) control).getItem(index + 1);
               switchItems(item, theOther);
            }
         }
         else if (control instanceof Tree)
         {
            if (((TreeItem) item).getParentItem() == null)
            {
               int index = ((Tree) control).indexOf((TreeItem) item);
               if (index < ((Tree) control).getItemCount() - 1)
               {
                  TreeItem theOther = ((Tree) control).getItem(index + 1);
                  switchItems(item, theOther);
               }
            }
            else
            {
               TreeItem parent = ((TreeItem) item).getParentItem();
               int index = parent.indexOf((TreeItem) item);
               if (index < parent.getItemCount() - 1)
               {
                  TreeItem theOther = parent.getItem(index + 1);
                  switchItems(item, theOther);
               }
               else if (canChangeParent)
               {
                  SnapshotNode snParent = takeSnapshot(parent);
                  SnapshotNode snItem = (SnapshotNode) snParent.children.remove(
                     snParent.children.size() - 1);
                  restoreFromSnapshot(parent, snParent, true);

                  TreeItem newItem;
                  if (parent.getParentItem() == null)
                  {
                     parent = ((Tree) control).getItem(
                        ((Tree) control).indexOf(parent) + 1);
                  }
                  else
                  {
                     parent = parent.getParentItem().getItem(
                        parent.getParentItem().indexOf(parent) + 1);
                  }
                  newItem = new TreeItem(parent, SWT.NONE, 0);
                  restoreFromSnapshot(newItem, snItem);
               }
            }
         }
         control.setRedraw(true);
      }
   }

   private void switchItems(Item item, Item other)
   {
      if (control instanceof Table)
      {
         Object o1 = item.getData();
         Object o2 = other.getData();
         setTableData((TableItem) item, o2);
         setTableData((TableItem) other, o1);
      }
      else if (control instanceof Tree)
      {
         SnapshotNode n1 = takeSnapshot((TreeItem) item);
         SnapshotNode n2 = takeSnapshot((TreeItem) other);
         restoreFromSnapshot((TreeItem) item, n2);
         restoreFromSnapshot((TreeItem) other, n1);
      }
   }

   private void setTableData(TableItem root, Object o)
   {
      Object data = root.getData();
      if (data != o)
      {
         if (data instanceof EObject)
         {
            binding.unbind(data);
         }
         root.setData(o);
         if (o instanceof EObject)
         {
            binding.bind((EObject) o, getBinder((EObject) o, root));
         }
         else
         {
            root.setText(getColumnText(o));
            root.setImage(getColumnImage(o));
         }
      }
   }

   private SnapshotNode takeSnapshot(TreeItem root)
   {
      SnapshotNode node = new SnapshotNode();
      node.expanded = root.getExpanded();
      node.data = root.getData();
      int size = root.getItemCount();
      node.children = new ArrayList(size);
      for (int i = 0; i < size; i++)
      {
         node.children.add(takeSnapshot(root.getItem(i)));
      }
      return node;
   }

   private void restoreFromSnapshot(TreeItem root, SnapshotNode node)
   {
      restoreFromSnapshot(root, node, false);
   }

   private void restoreFromSnapshot(TreeItem root, SnapshotNode node, boolean force)
   {
      Object data = root.getData();
      if (force || data != node.data)
      {
         if (data instanceof EObject)
         {
            binding.unbind(data);
         }
         root.setData(node.data);
         if (node.data instanceof EObject)
         {
            binding.bind((EObject) node.data, getBinder((EObject) node.data, root));
         }
         else
         {
            root.setText(getColumnText(node.data));
            root.setImage(getColumnImage(node.data));
         }
         int size = root.getItemCount();
         if (size > node.children.size())
         {
            root.removeAll();
            size = 0;
         }
         for (int i = 0; i < node.children.size(); i++)
         {
            TreeItem child = i < size ? root.getItem(i) : new TreeItem(root, SWT.NONE);
            restoreFromSnapshot(child, (SnapshotNode) node.children.get(i));
         }
         root.setExpanded(node.expanded);
      }
   }
}
