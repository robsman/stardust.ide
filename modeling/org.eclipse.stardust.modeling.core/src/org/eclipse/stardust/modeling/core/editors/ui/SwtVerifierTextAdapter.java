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

import org.eclipse.stardust.modeling.common.ui.jface.databinding.IBindingMediator;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.SwtTextAdapter;
import org.eclipse.stardust.modeling.core.Verifier;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import ag.carnot.workflow.spi.providers.data.java.Type;

public class SwtVerifierTextAdapter extends SwtTextAdapter
{

   private ModifyListener listener;

   public SwtVerifierTextAdapter(Text control)
   {
      super(control);
   }

   public void bind(IBindingMediator manager)
   {
      ((Text) getWidget()).addModifyListener(listener = new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String value = ((Text) getWidget()).getText();
            Verifier verifier = VerifierFactory.getVerifier(Type.String);
            if (verifier != null)
            {
               value = verifier.getInternalValue(value);
            }
            updateModel(value);
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
            ((Text) getWidget()).removeModifyListener(listener);
         }
         listener = null;
      }
   }
}
