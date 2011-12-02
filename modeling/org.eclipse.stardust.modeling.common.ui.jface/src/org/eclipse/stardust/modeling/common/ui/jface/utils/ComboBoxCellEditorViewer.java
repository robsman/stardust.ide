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
package org.eclipse.stardust.modeling.common.ui.jface.utils;

import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Control;

/**
 * Viewer for the ComboBoxCellEditor.
 * 
 * @author herinean
 * @version $Revision$
 */
public class ComboBoxCellEditorViewer extends ContentViewer
{
   private ComboBoxCellEditor editor;
   
   public ComboBoxCellEditorViewer(ComboBoxCellEditor editor)
   {
      this.editor = editor;
   }

   public Control getControl()
   {
      return editor.getControl();
   }

   public ISelection getSelection()
   {
      IStructuredContentProvider cp = (IStructuredContentProvider) getContentProvider();
      Integer value = (Integer) editor.getValue();
      return value == null || value.intValue() == -1
         ? new StructuredSelection()
         : new StructuredSelection(cp.getElements(getInput())[value.intValue()]);
   }

   protected void inputChanged(Object input, Object oldInput)
   {
      refresh();
   }

   public void refresh()
   {
      IStructuredContentProvider cp = (IStructuredContentProvider) getContentProvider();
      ILabelProvider lp = (ILabelProvider) getLabelProvider();
      Object[] elements = cp.getElements(getInput());
      String[] items = new String[elements.length];
      for (int i = 0; i < items.length; i++)
      {
         items[i] = lp.getText(elements[i]);
      }
      editor.setItems(items);
   }

   public void setSelection(ISelection selection, boolean reveal)
   {
      if (selection == null || selection.isEmpty()
            || !(selection instanceof IStructuredSelection))
      {
         editor.setValue(new Integer(-1));
      }
      IStructuredContentProvider cp = (IStructuredContentProvider) getContentProvider();
      Object value = ((IStructuredSelection) selection).getFirstElement();
      Integer index = find(cp.getElements(getInput()), value);
      editor.setValue(index);
   }

   public Object findObject(Integer index)
   {
      IStructuredContentProvider cp = (IStructuredContentProvider) getContentProvider();
      return index == null || index.intValue() == -1
         ? null : cp.getElements(getInput())[index.intValue()];
   }

   public Integer findIndex(Object value)
   {
      IStructuredContentProvider cp = (IStructuredContentProvider) getContentProvider();
      return find(cp.getElements(getInput()), value);
   }

   private Integer find(Object[] elements, Object value)
   {
      for (int i = 0; i < elements.length; i++)
      {
         if (elements[i].equals(value))
         {
            return new Integer(i);
         }
      }
      return new Integer(-1);
   }
}