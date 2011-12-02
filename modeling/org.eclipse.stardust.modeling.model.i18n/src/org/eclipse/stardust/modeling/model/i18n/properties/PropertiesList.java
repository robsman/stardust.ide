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

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.model.i18n.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;


public class PropertiesList implements IPropertyModelListener
{
   private static final String[] columnProperties = new String[] {
      Messages.PropertiesList_PropertyName};
   
   private TableViewer viewer;
   private ScopedPropertyModel model;

   public void setModel(ScopedPropertyModel model)
   {
      this.model = model;
      model.getPropertyModel().addPropertyModelListener(this);
      localesChanged();
   }

   public void createBody(Composite composite, int span)
   {
      Table table = FormBuilder.createTable(composite,
            SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER,
            columnProperties, new int[] {100}, span);
      viewer = new TableViewer(table);
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(new LabelProvider());
      viewer.setColumnProperties(columnProperties);
   }
   
   public TableViewer getViewer()
   {
      return viewer;
   }
   
   public void localesChanged()
   {
      viewer.setInput(model.getProperties());
   }

   public void select(String name)
   {
      viewer.setSelection(new StructuredSelection(name));
   }
}
