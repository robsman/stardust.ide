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
package org.eclipse.stardust.modeling.common.ui.jface.databinding;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SwtButtonAdapter extends SwtWidgetAdapter
{
   private SelectionListener listener;

   public SwtButtonAdapter(Button button)
   {
      super(button);
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);

      Button button = (Button) getWidget();
      button.addSelectionListener(listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            Button button = (Button) getWidget();
            updateModel(button.getSelection() ? Boolean.TRUE : Boolean.FALSE);
         }
      });
   }

   public void unbind()
   {
      if (null != listener)
      {
         if ( !getWidget().isDisposed())
         {
            ((Button) getWidget()).removeSelectionListener(listener);
         }
         listener = null;
      }

      super.unbind();
   }

   public void updateControl(Object value)
   {
      Button button = (Button) getWidget();

      final boolean newValue = value != null ? ((Boolean) value).booleanValue() : false;
      if (newValue != button.getSelection())
      {
         button.setSelection(newValue);
      }
   }
}
