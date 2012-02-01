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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.model.i18n.Activator;
import org.eclipse.stardust.modeling.model.i18n.I18N_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class LocalesList implements IPropertyModelListener
{
   private static final String NEW_LOCALE_PLACEHOLDER = ""; //$NON-NLS-1$

   private static final String[] columnProperties = new String[] {I18N_Messages.PropertyValuesEditor_NLS};
   
   private TableViewer viewer;
   private PropertyModel model;
   
   private ComboBoxCellEditor comboEditor;
   
   public void setModel(PropertyModel model)
   {
      this.model = model;
      model.addPropertyModelListener(this);
      localesChanged();
   }

   public void createBody(Composite composite, int span)
   {
      final Table table = FormBuilder.createTable(composite,
            SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
            columnProperties, new int[] {100}, span);
      viewer = new TableViewer(table);
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(new TableLabelProvider());
      viewer.setColumnProperties(columnProperties);
      viewer.setCellModifier(new CellModifier());
      comboEditor = new ComboBoxCellEditor(viewer.getTable(), new String[] {});
      viewer.setCellEditors(new CellEditor[] {comboEditor});
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
         if (element != NEW_LOCALE_PLACEHOLDER)
         {
            switch (columnIndex)
            {
               case 0 :
                  result = element instanceof Locale
                     ? ((Locale) element).getDisplayName() : element.toString();
            }
         }
         return result == null ? "" : result; //$NON-NLS-1$
      }
   }
   
   private class CellModifier implements ICellModifier
   {
      private List<Locale> cache;
      
      private boolean modifying = false;

      public boolean canModify(Object element, String property)
      {
         return model.hasSourceFolders() && property.equals(columnProperties[0])
            && element == NEW_LOCALE_PLACEHOLDER;
      }
      
      public Object getValue(Object element, String property)
      {
         Object result = new Integer(-1);
         if (property.equals(columnProperties[0]))
         {
            cache = CollectionUtils.newList();
 
            // collect not used locales and find platform locale
            Set<?> existing = model.getLocales();
            Locale[] all = Locale.getAvailableLocales();
            for (int i = 0; i < all.length; i++)
            {
               if (!existing.contains(all[i].toString()) && !existing.contains(all[i]))
               {
                  cache.add(all[i]);
               }
            }
            
            // sort locales
            sortLocales(cache);
            
            // collect strings and set editor input
            List<String> strings = CollectionUtils.newList();
            for (int i = 0; i < cache.size(); i++)
            {
               strings.add(cache.get(i).getDisplayName(
                     Activator.getPlatformLocale()));
            }
            comboEditor.setItems(strings.toArray(new String[strings.size()]));

            // the result is the index of the element in the input strings
            result = new Integer(strings.indexOf(element));
         }
         return result;
      }

      public void modify(Object element, String property, Object value)
      {
         if (!modifying)    // (fh) to prevent infinite loops #CRNT-7262
         {
            try
            {
               modifying = true;
               if (property.equals(columnProperties[0]))
               {
                  int index = ((Integer) value).intValue();
                  if (index >= 0)
                  {
                     Locale newLocale = cache.get(index);
                     model.addLocale(newLocale, null);
                  }
               }
            }
            finally
            {
               modifying = false;
            }
         }
      }
   }

   public void localesChanged()
   {
      List<Object> locales = CollectionUtils.newList();
      locales.addAll(model.getLocales());
      sortLocales(locales);
      viewer.setInput(locales);
      viewer.add(NEW_LOCALE_PLACEHOLDER);
   }

   public Locale getSelectedNLS()
   {
      IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
      Locale result = null;
      if (!selection.isEmpty() && selection.getFirstElement() instanceof Locale)
      {
         result = (Locale) selection.getFirstElement();
      }
      return result;
   }

   public void addSelectionChangedListener(ISelectionChangedListener listener)
   {
      viewer.addSelectionChangedListener(listener);
   }

   private void sortLocales(List<? extends Object> locales)
   {
      Collections.sort(locales, new Comparator<Object>()
      {
         public int compare(Object o1, Object o2)
         {
            String c1 = o1 instanceof Locale
               ? ((Locale) o1).getDisplayName(Activator.getPlatformLocale()) : o1.toString();
            String c2 = o2 instanceof Locale
               ? ((Locale) o2).getDisplayName(Activator.getPlatformLocale()) : o2.toString();
            return c1.compareTo(c2);
         }
      });
   }
}
