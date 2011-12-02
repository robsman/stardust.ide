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

import java.text.ParseException;
import java.util.Date;

import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtWidgetAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import com.gface.date.DatePickerCombo;
import com.gface.date.DateSelectedEvent;
import com.gface.date.DateSelectionListener;


public class SwtDatePickerAdapter extends SwtWidgetAdapter
{
   private DateSelectionListener listener;
   private Button resetBtn;

   public SwtDatePickerAdapter(DatePickerCombo datePickerCombo)
   {
      super(datePickerCombo);
   }
   
   public SwtDatePickerAdapter(DatePickerCombo datePickerCombo, Button resetBtn)
   {
      super(datePickerCombo);
      this.resetBtn = resetBtn;
   }

   public void updateControl(Object value)
   {
      if (value != null)
      {
         Date date;
         try
         {
            date = DateUtils.getNonInteractiveDateFormat().parse((String) value);
            ((DatePickerCombo) getWidget()).setDate(date);
         }
         catch (ParseException e)
         {
            e.printStackTrace();
         }
      }
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);

      final DatePickerCombo datePickerCombo = (DatePickerCombo) getWidget();
      datePickerCombo.addDateSelectionListener(listener = new DateSelectionListener()
      {
         public void dateSelected(DateSelectedEvent e)
         {
            Date date = ((DatePickerCombo) getWidget()).getDate();
            String value = date == null
                  ? "" : DateUtils.getNonInteractiveDateFormat().format(date); //$NON-NLS-1$
            updateModel(value);
         }
      });
      if (resetBtn != null)
      {
         resetBtn.addSelectionListener(new SelectionListener()
         {
            public void widgetSelected(SelectionEvent e)
            {
               datePickerCombo.setDate(null);
               updateModel(null);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {}
         });
      }
   }

   public void unbind()
   {
      if (null != listener)
      {
         if (!getWidget().isDisposed())
         {
            ((DatePickerCombo) getWidget()).removeDateSelectionListener(listener);
         }
         listener = null;
      }

      super.unbind();
   }
}
