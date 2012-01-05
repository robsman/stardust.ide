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

import java.util.Map;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.stardust.engine.core.pojo.data.Type;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.AbstractWidgetAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.core.spi.dataTypes.primitive.PrimitivePropertyPage.DatePickerComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class PrimitiveDataWidgetAdapter extends AbstractWidgetAdapter
{
   private final ComboViewer typeViewer;

   private ISelectionChangedListener dataListener;

   private final Composite valueComposite;

   private final Map/* <Type, Control> */valueControlsMap;

   public PrimitiveDataWidgetAdapter(ComboViewer typeViewer, Composite valueComposite,
         Map/* <Type, Control> */valueControlsMap)
   {
      this.typeViewer = typeViewer;
      this.valueComposite = valueComposite;
      this.valueControlsMap = valueControlsMap;
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);
      this.dataListener = createDataListener();
      typeViewer.addSelectionChangedListener(dataListener);
      typeViewer.getControl().addDisposeListener(new DisposeListener()
      {
         public void widgetDisposed(DisposeEvent e)
         {
            unbind();
         }
      });
   }

   private ISelectionChangedListener createDataListener()
   {
      return new ISelectionChangedListener()
      {

         public void selectionChanged(SelectionChangedEvent event)
         {
            Type data = getData(event.getSelection());
            if (data != null)
            {
               updatePrimitiveDataValue(data);
               updateModel(data);
            }
         }

      };
   }

   private void updatePrimitiveDataValue(Type data)
   {
      data = (data == null) ? Type.String : data;
      Object object = valueControlsMap.get(data);
      ((StackLayout) valueComposite.getLayout()).topControl = object instanceof DatePickerComposite
            ? ((DatePickerComposite) object).getCalendarComposite()
            : (Control) object;

      valueComposite.layout();
   }

   public void updateVisuals(Object types)
   {
      ISelection selection = typeViewer.getSelection();
      typeViewer.setInput(types);
      typeViewer.setSelection(selection);
      updatePrimitiveDataValue(getData(selection));
   }

   public void unbind()
   {
      if (null != dataListener)
      {
         if (!typeViewer.getControl().isDisposed())
         {
            typeViewer.removeSelectionChangedListener(dataListener);
         }
         dataListener = null;
      }

      super.unbind();
   }

   private Type getData(ISelection selection)
   {
      Type data = null;
      if (selection instanceof IStructuredSelection)
      {
         IStructuredSelection structuredSelection = (IStructuredSelection) selection;
         if (!structuredSelection.isEmpty())
         {
            data = (Type) structuredSelection.getFirstElement();
         }
      }
      return data;
   }
}
