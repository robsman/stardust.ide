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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

/**
 * @author fherinean
 * @version $Revision$
 */
public class SwtComboAdapter extends SwtWidgetAdapter
{
   private SelectionListener listener;

   public SwtComboAdapter(Combo control)
   {
      super(control);
   }

   public void bind(IBindingMediator manager)
   {
      super.bind(manager);

      final Combo combo = ((Combo) getWidget());
      combo.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateModel(combo.getText());
         }         
      });            
      combo.addSelectionListener(listener = new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            Combo combo = (Combo) getWidget();
            int selection = combo.getSelectionIndex();
            updateModel(selection < 0 ? combo.getText()
                  : combo.getItem(combo.getSelectionIndex()));
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
      Combo combo = (Combo) getWidget();

      if (null == value)
      {
         value = ""; //$NON-NLS-1$
      }

      final int index = combo.indexOf(value.toString());
      if (index != combo.getSelectionIndex())
      {
         combo.select(index);
      }
      if (index == -1 && (combo.getStyle() & SWT.READ_ONLY) == 0)
      {
         combo.setText(value.toString());
      }
   }
}
