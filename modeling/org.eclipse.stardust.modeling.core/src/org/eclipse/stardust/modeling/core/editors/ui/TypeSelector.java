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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;

import ag.carnot.base.CompareHelper;

public abstract class TypeSelector
{
   protected TypeInfo type;
   private ArrayList listeners = new ArrayList();

   private TypeSelector(final TypeFinder finder,
         final Composite parent, final String title, final int style)
   {
      final Composite composite = FormBuilder.createComposite(parent, 2);
      GridData gd = FormBuilder.createDefaultSingleLineWidgetGridData();
      ((GridLayout) composite.getLayout()).marginHeight = 0;
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      composite.setLayoutData(gd);

      createControl(composite);
      
      addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent event)
         {
            TypeInfo old = type;
            String newType = getText();
            if (newType.length() == 0)
            {
               type = null;
               if (old != null)
               {
                  notifyListeners(old);
               }
            }
            else if (old == null
                  || !CompareHelper.areEqual(old.getFullName(), newType))
            {
               type = finder.findType(newType);
               notifyListeners(old);
            }
         }
      });
      
      FormBuilder.createButton(composite, Diagram_Messages.Btn_Browse, new SelectionListener()
      {
         public void widgetSelected(SelectionEvent event)
         {
            IRunnableContext context = new ApplicationWindow(composite.getShell());
            IJavaSearchScope scope = SearchEngine.createJavaSearchScope(
                  new IJavaElement[] {finder.getJavaProject()});
            try
            {
               SelectionDialog dialog = JavaUI.createTypeDialog(
                     composite.getShell(), context, scope, style, false);
               dialog.setTitle(title);
               if (dialog.open() == Window.OK)
               {
                  IType result = (IType) dialog.getResult()[0];
                  TypeInfo old = type;
                  type = new TypeInfo(finder, result, null);
                  setText(type.getFullName());
                  notifyListeners(old);
               }
            }
            catch (JavaModelException exception)
            {
               exception.printStackTrace();
            }
         }

         public void widgetDefaultSelected(SelectionEvent event) {}
      });
   }

   protected void notifyListeners(TypeInfo old)
   {
      if (!CompareHelper.areEqual(old, type))
      {
         for (int i = 0; i < listeners.size(); i++)
         {
            TypeListener listener = (TypeListener) listeners.get(i);
            listener.typeChanged(type);
         }
      }
   }

   public TypeInfo getType()
   {
      return type;
   }
   
   public void addListener(TypeListener listener)
   {
      listeners.add(listener);
   }

   protected abstract void setText(String fullName);

   protected abstract String getText();

   protected abstract void addModifyListener(ModifyListener modifyListener);

   protected abstract void createControl(Composite composite);
   
   public static interface TypeListener
   {
      void typeChanged(TypeInfo type);
   }
   
   public static class TextSelector extends TypeSelector
   {
      private Text typeText;

      public TextSelector(TypeFinder finder, Composite parent, String title, int style)
      {
         super(finder, parent, title, style);
      }

      protected void addModifyListener(ModifyListener listener)
      {
         typeText.addModifyListener(listener);
      }

      protected void createControl(Composite composite)
      {
         typeText = FormBuilder.createText(composite);
      }

      protected String getText()
      {
         return typeText.getText().trim();
      }

      protected void setText(String text)
      {
         typeText.setText(text);
      }
      
      public Text getTextControl()
      {
         return typeText;
      }
   }
   
   public static class ComboSelector extends TypeSelector
   {
      private Combo typeCombo;
      private List classes;

      public ComboSelector(TypeFinder finder, Composite parent, String title, int style)
      {
         super(finder, parent, title, style);
      }

      protected void addModifyListener(ModifyListener listener)
      {
         typeCombo.addModifyListener(listener);
      }

      protected void createControl(Composite composite)
      {
         typeCombo = FormBuilder.createCombo(composite, SWT.DROP_DOWN, 1);
         typeCombo.addSelectionListener(new SelectionListener()
         {
            public void widgetDefaultSelected(SelectionEvent e) {}

            public void widgetSelected(SelectionEvent e)
            {
               int index = typeCombo.getSelectionIndex();
               type = (TypeInfo) (index < 0 ? null : classes.get(index));
            }
         });
      }

      protected String getText()
      {
         return typeCombo.getText().trim();
      }

      protected void setText(String text)
      {
         typeCombo.setText(text);
      }

      public Combo getComboControl()
      {
         return typeCombo;
      }

      public void setTypesList(List classes)
      {
         this.classes = classes;
         typeCombo.removeAll();
         for (int i = 0; i < classes.size(); i++)
         {
            TypeInfo typeInfo = (TypeInfo) classes.get(i);
            typeCombo.add(typeInfo.getFullName());
         }
      }
   }
}
