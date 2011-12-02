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

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SwtTextAdapter extends SwtWidgetAdapter
{
//   private FocusListener listener;
   private ModifyListener listener;

   public SwtTextAdapter(Text control)
   {
      super(control);
   }

   public void bind(IBindingMediator manager)
   {
/*      ((Text) getWidget()).addFocusListener(listener = new FocusAdapter()
      {
         public void focusLost(FocusEvent e)
         {
            updateModel(((Text) getWidget()).getText());
         }
      });*/
      ((Text) getWidget()).addModifyListener(listener = new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            updateModel(((Text) getWidget()).getText());
         }
      });
      super.bind(manager);
   }

   public void unbind()
   {
      super.unbind();
      if (listener != null)
      {
         if (!getWidget().isDisposed())
         {
//            ((Text) getWidget()).removeFocusListener(listener);
            ((Text) getWidget()).removeModifyListener(listener);
         }
         listener = null;
      }
   }

   public void updateControl(Object value)
   {
      Text text= (Text) getWidget();
      if (!text.getText().equals(value))
      {
         text.setText(value == null ? "" : value.toString()); //$NON-NLS-1$
      }
   }
}
