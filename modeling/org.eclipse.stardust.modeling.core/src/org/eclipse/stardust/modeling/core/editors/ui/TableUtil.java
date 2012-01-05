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

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * @author fherinean
 * @version $Revision$
 */
public class TableUtil
{
   public static void createColumns(Tree tree, String[] columnNames)
   {
      for (int i = 0; i < columnNames.length; i++)
      {
         TreeColumn column = new TreeColumn(tree, SWT.NONE);
         column.setText(columnNames[i]);
      }
   }

   public static void createColumns(Table table, String[] columnNames)
   {
      for (int i = 0; i < columnNames.length; i++)
      {
         TableColumn column = new TableColumn(table, SWT.NONE);
         column.setText(columnNames[i]);
      }
   }

   public static void setInitialColumnSizes(final Table table, final int[] columnSizes)
   {
      internalSetInitialColumnSizes(table, columnSizes);
   }

   public static void setInitialColumnSizes(final Tree tree, final int[] columnSizes)
   {
      internalSetInitialColumnSizes(tree, columnSizes);
   }
   
   public static void setInitialColumnSizesDirect(final Control control, final int[] columnSizes)
   {
      internalSetInitialColumnSizesDirect(control, columnSizes);
   }

   public static void internalSetInitialColumnSizesDirect(final Control control,
         final int[] columnSizes)
   {
      int columnCount = 0;
      if (!control.isDisposed() && columnSizes != null
            && (columnCount = getColumnCount(control)) > 0)
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
            if (i == columnCount)
            {
               break;
            }
            percentage += columnSizes[i];
            int cs = percentage == 100 ? size - cumulated : size * columnSizes[i] / 100;
            Item column = getColumn(control, i);
            setWidth(column, cs);
            cumulated += cs;
         }
      }
   }
   
   public static void internalSetInitialColumnSizes(final Control control, final int[] columnSizes)
   {
      control.addControlListener(new ControlListener()
      {
         public void controlMoved(ControlEvent e)
         {
         }

         public void controlResized(ControlEvent e)
         {
            setInitialColumnSizes();
         }

         private void setInitialColumnSizes()
         {
            int columnCount = 0;
            if (!control.isDisposed()
                  && columnSizes != null
                  && (columnCount = getColumnCount(control)) > 0)
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
                  if (i == columnCount)
                  {
                     break;
                  }
                  percentage += columnSizes[i];
                  int cs = percentage == 100 ? size - cumulated : size * columnSizes[i] / 100;
                  Item column = getColumn(control, i);
                  setWidth(column, cs);
                  cumulated += cs;
               }
            }
//            control.removeControlListener(this);
         }
      });
   }
   
   private static void setWidth(Item column, int width)
   {
      if (column instanceof TableColumn)
      {
         ((TableColumn) column).setWidth(width);
      }
      else if (column instanceof TreeColumn)
      {
         ((TreeColumn) column).setWidth(width);
      }
   }

   private static Item getColumn(Control control, int index)
   {
      return control instanceof Table
            ? (Item) ((Table) control).getColumn(index)
            : control instanceof Tree ? (Item) ((Tree) control).getColumn(index) : null;
   }

   private static int getColumnCount(Control control)
   {
      return control instanceof Table
            ? ((Table) control).getColumnCount()
            : control instanceof Tree ? ((Tree) control).getColumnCount() : 0;
   }   

   public static void setLabelProvider(TreeViewer viewer,
         final TableLabelProvider provider, final String[] attributes)
   {
      internalSetLabelProvider(viewer, provider, attributes);
   }

   public static void setLabelProvider(TableViewer viewer,
         final TableLabelProvider provider, final String[] attributes)
   {
      internalSetLabelProvider(viewer, provider, attributes);
   }

   private static void internalSetLabelProvider(StructuredViewer viewer,
         final TableLabelProvider provider, final String[] attributes)
   {
      viewer.setLabelProvider(new ITableLabelProvider()
      {
         public Image getColumnImage(Object element, int columnIndex)
         {
            if (columnIndex == 0)
            {
               return provider.getImage(element);
            }
            return null;
         }

         public String getColumnText(Object element, int columnIndex)
         {
            if (provider.isNamed() && columnIndex < attributes.length)
            {
               return provider.getText(attributes[columnIndex], element);
            }
            return provider.getText(columnIndex, element);
         }

         public void addListener(ILabelProviderListener listener)
         {
         }

         public void dispose()
         {
         }

         public boolean isLabelProperty(Object element, String property)
         {
            for (int i = 0; i < attributes.length; i++)
            {
               if (property.equals(attributes[i]))
               {
                  return true;
               }
            }
            return false;
         }

         public void removeListener(ILabelProviderListener listener)
         {
         }
      });
   }
}
