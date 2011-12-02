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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;

public class TableEditorTraverseManager implements TraverseListener
{
   public static final int NONE = -1;
   public static final int NEXT_ROW = -2;
   public static final int PREVIOUS_ROW = -3;
   
   private StructuredViewer viewer;
   private int nextColumn;
   private int previousColumn;
   private int firstColumn;
   private int lastColumn;

   public TableEditorTraverseManager(TreeViewer treeViewer, int previousColumn, int nextColumn,
         int firstColumn, int lastColumn)
   {
      this.viewer = treeViewer;
      this.nextColumn = nextColumn;
      this.previousColumn = previousColumn;
      this.firstColumn = firstColumn;
      this.lastColumn = lastColumn;
   }

   public TableEditorTraverseManager(TableViewer tableViewer, int previousColumn, int nextColumn,
         int firstColumn, int lastColumn)
   {
      this.viewer = tableViewer;
      this.nextColumn = nextColumn;
      this.previousColumn = previousColumn;
      this.firstColumn = firstColumn;
      this.lastColumn = lastColumn;
   }

   public void keyTraversed(TraverseEvent e)
   {
      if (e.detail == SWT.TRAVERSE_TAB_NEXT && nextColumn != NONE)
      {
         Object selection = getSelection(nextColumn);
         if (selection != null)
         {
            e.doit = false;
            if (viewer instanceof TreeViewer)
            {
               ((TreeViewer) viewer).editElement(selection, nextColumn == NEXT_ROW ? firstColumn : nextColumn);
            }
            else if (viewer instanceof TableViewer)
            {
               ((TableViewer) viewer).editElement(selection, nextColumn == NEXT_ROW ? firstColumn : nextColumn);
            }
         }
      }
      else if (e.detail == SWT.TRAVERSE_TAB_PREVIOUS && previousColumn != NONE)
      {
         Object selection = getSelection(previousColumn);
         if (selection != null)
         {
            e.doit = false;
            if (viewer instanceof TreeViewer)
            {
               ((TreeViewer) viewer).editElement(selection, previousColumn == PREVIOUS_ROW ? lastColumn : previousColumn);
            }
            else if (viewer instanceof TableViewer)
            {
               ((TableViewer) viewer).editElement(selection, previousColumn == PREVIOUS_ROW ? lastColumn : previousColumn);
            }
         }
      }
   }

   private Object getSelection(int column)
   {
      Object selection = ((IStructuredSelection) viewer.getSelection()).getFirstElement();
      switch (column)
      {
         case NONE:
            selection = null;
            break;
         case NEXT_ROW:
            selection = getNextElement(selection);
            break;
         case PREVIOUS_ROW:
            selection = getPreviousElement(selection);
      }
      return selection;
   }

   public Object getPreviousElement(Object selection)
   {
      return null;
   }

   public Object getNextElement(Object selection)
   {
      return null;
   }
}
