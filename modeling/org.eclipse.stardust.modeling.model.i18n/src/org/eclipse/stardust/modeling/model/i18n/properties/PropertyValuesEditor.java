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
package org.eclipse.stardust.modeling.model.i18n.properties;

import java.util.Locale;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.model.i18n.I18N_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;


public class PropertyValuesEditor implements IPropertyModelListener
{
   private static final String[] columnProperties = new String[] {I18N_Messages.PropertyValuesEditor_NLS, I18N_Messages.PropertyValuesEditor_Value};
   private static final String[] updateProperties = new String[] {columnProperties[1]};
   
   private TableViewer viewer;
   private ScopedPropertyModel model;
   private String propertyName;
   
   private TextCellEditor textEditor;
   
   public void setModel(ScopedPropertyModel model)
   {
      this.model = model;
      model.getPropertyModel().addPropertyModelListener(this);
      localesChanged();
   }

   public void createBody(Composite composite, int span)
   {
      final Table table = FormBuilder.createTable(composite,
            SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
            columnProperties, new int[] {25, 75}, span);
      viewer = new TableViewer(table);
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(new TableLabelProvider());
      viewer.setColumnProperties(columnProperties);
      viewer.setCellModifier(new CellModifier());
      textEditor = new TextCellEditor(viewer.getTable());
      viewer.setCellEditors(new CellEditor[] {null, textEditor});
   }
   
   private class TableLabelProvider extends LabelProvider implements ITableLabelProvider
   {
      public Image getColumnImage(Object element, int columnIndex)
      {
         return null;
      }

      public String getColumnText(Object element, int columnIndex)
      {
         String result = null;
         switch (columnIndex)
         {
            case 0 :
               result = element instanceof Locale
                  ? ((Locale) element).getDisplayName() : element.toString();
               break;
            case 1 :
               result = model.getProperty(element, propertyName);
         }
         return result == null ? "" : result; //$NON-NLS-1$
      }
   }
   
   private class CellModifier implements ICellModifier
   {
      public boolean canModify(Object element, String property)
      {
         return property.equals(columnProperties[1]) && propertyName != null;
      }

      public Object getValue(Object element, String property)
      {
         Object result = null;
         Object locale = element instanceof TableItem
            ? ((TableItem) element).getData() : element;
         if (property.equals(columnProperties[0]))
         {
            result = locale;
         }
         if (property.equals(columnProperties[1]))
         {
            result = model.getProperty(locale, propertyName);
         }
         return result == null ? "" : result; //$NON-NLS-1$
      }

      public void modify(Object element, String property, Object value)
      {
         Object locale = element instanceof TableItem
            ? ((TableItem) element).getData() : element;
         if (property.equals(columnProperties[1]))
         {
            model.setProperty(locale, propertyName, value.toString());
            viewer.update(locale, updateProperties);
         }
      }
   }

   public void localesChanged()
   {
      viewer.setInput(propertyName == null ? null : model.getPropertyModel().getLocales());
   }

   public void setPropertyName(String propertyName)
   {
      this.propertyName = propertyName;
      localesChanged();
   }

   public String getPropertyName()
   {
      return propertyName;
   }
}
