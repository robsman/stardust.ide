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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SwtListAdapter extends SwtWidgetAdapter
{
   private SelectionListener listener;

   public SwtListAdapter(List control)
   {
      super(control);
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);

      List combo = ((List) getWidget());
      combo.addSelectionListener(listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            List list = (List) getWidget();
            updateModel(list.getItem(list.getSelectionIndex()));
         }
      });
   }

   public void unbind()
   {
      if (null != listener)
      {
         if ( !getWidget().isDisposed())
         {
            ((Combo) getWidget()).removeSelectionListener(listener);
         }
         listener = null;
      }

      super.unbind();
   }

   public void updateControl(Object value)
   {
      List list = (List) getWidget();

      if (null == value)
      {
         value = ""; //$NON-NLS-1$
      }

      final int index = list.indexOf(value.toString());
      if (index != list.getSelectionIndex())
      {
         list.select(index);
      }
   }
}
