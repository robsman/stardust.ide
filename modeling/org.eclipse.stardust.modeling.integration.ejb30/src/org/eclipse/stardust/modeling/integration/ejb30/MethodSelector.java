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
package org.eclipse.stardust.modeling.integration.ejb30;

import java.util.List;

import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;


public class MethodSelector
{
   private MethodInfo method;
   private Combo methodCombo;
   private List<MethodInfo> methods;

   public MethodSelector(Composite parent)
   {
      methodCombo = FormBuilder.createCombo(parent, SWT.READ_ONLY, 1);
      methodCombo.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {}

         public void widgetSelected(SelectionEvent e)
         {
            int index = methodCombo.getSelectionIndex();
            method = index < 1 ? null : methods.get(index - 1);
         }
      });
   }

   public MethodInfo getMethod()
   {
      return method;
   }

   public Combo getMethodCombo()
   {
      return methodCombo;
   }

   public void setMethodsList(List<MethodInfo> methods)
   {
      this.methods = methods;
      methodCombo.removeAll();
      methodCombo.add(""); //$NON-NLS-1$
      for (MethodInfo methodInfo : methods)
      {
         methodCombo.add(methodInfo.getLabel());
      }
   }

   public void setMethodName(String methodName)
   {
      if (methods != null)
      {
         for (int i = 0; i < methods.size(); i++)
         {
            MethodInfo method = methods.get(i);
            if (method.getEncoded().equals(methodName))
            {
               methodCombo.select(i + 1);
               this.method = method;
               break;
            }
         }
      }
   }

   public String getMethodName()
   {
      return method == null ? null : method.getEncoded();
   }
}
