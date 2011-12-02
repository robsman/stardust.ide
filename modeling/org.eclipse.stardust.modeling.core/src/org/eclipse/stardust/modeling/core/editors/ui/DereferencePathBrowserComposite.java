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

import org.eclipse.jface.window.Window;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.ui.DereferencePathBrowser;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.validation.util.MethodFilter;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;


public class DereferencePathBrowserComposite
{
   private TypeFinder typeFinder;

   private TypeInfo type;

   private Text methodText;

   private DirectionType direction = DirectionType.OUT_LITERAL;

   private boolean isConstructor = false;

   private boolean deep = true;

   private boolean includeVoidIn = false;

   public DereferencePathBrowserComposite(Composite parent, String title)
   {
      init(parent, title, 1);
   }

   public DereferencePathBrowserComposite(Composite parent, String title, int span)
   {
      init(parent, title, span);
   }

   private void init(Composite parent, final String title, int span)
   {
      final Composite composite = FormBuilder.createComposite(parent, 2);
      GridData gd = FormBuilder.createDefaultSingleLineWidgetGridData();
      gd.horizontalSpan = span;
      ((GridLayout) composite.getLayout()).marginHeight = 0;
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      composite.setLayoutData(gd);

      methodText = FormBuilder.createText(composite);
      FormBuilder.createButton(composite, Diagram_Messages.Btn_Browse,
            new SelectionListener()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  typeFinder.setMethodFilter(createFilter());
                  DereferencePathBrowser browser = new DereferencePathBrowser(composite
                        .getShell(), type, typeFinder, title);
                  browser.setConstructor(isConstructor);
                  browser.setDeep(deep);
                  if (browser.open() == Window.OK)
                  {
                     if (!StringUtils.isEmpty(browser.getSelectedMethod()))
                     {
                        methodText.setText(browser.getSelectedMethod());
                     }
                  }
               }

               public void widgetDefaultSelected(SelectionEvent e)
               {}

            });
   }

   private MethodFilter createFilter()
   {
      return new MethodFilter()
      {
         public boolean accept(MethodInfo info)
         {
            if (info.isAccessible())
            {
               if (isConstructor)
               {
                  return true;
               }
               else if (DirectionType.OUT_LITERAL.equals(direction))
               {
                  return info.hasReturn() && info.getParameterCount() == 0;
               }
               else if (DirectionType.IN_LITERAL.equals(direction))
               {
                  return info.getParameterCount() > 0 || !info.hasReturn() && includeVoidIn;
               }
               else if (DirectionType.INOUT_LITERAL.equals(direction))
               {
                  return !info.isConstructor();
               }
            }
            return false;
         }
      };
   }

   public void setType(TypeInfo type)
   {
      this.type = type;
   }

   public void setTypeFinder(TypeFinder typeFinder)
   {
      this.typeFinder = typeFinder;
   }

   public TypeFinder getTypeFinder()
   {
      return typeFinder;
   }

   public String getMethod()
   {
      return methodText.getText().trim();
   }

   public void setMethod(String methodName)
   {
      methodText.setText(methodName);
   }

   public Text getMethodText()
   {
      return methodText;
   }

   public void setDirection(DirectionType directionType)
   {
      direction = directionType;
   }

   public void setConstructor(boolean isConstructor)
   {
      this.isConstructor = isConstructor;
   }

   public void setDeep(boolean isDeep)
   {
      this.deep = isDeep;
   }

   public void setIncludeVoidIn(boolean includeVoidIn)
   {
      this.includeVoidIn = includeVoidIn;
   }
}
