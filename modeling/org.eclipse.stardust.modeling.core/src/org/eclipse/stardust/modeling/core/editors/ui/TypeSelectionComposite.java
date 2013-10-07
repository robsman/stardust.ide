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

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SelectionDialog;

// TODO: replace with TypeSelector
public class TypeSelectionComposite
{
   private Text typeText;

   private TypeInfo type;

   private int style = IJavaElementSearchConstants.CONSIDER_ALL_TYPES;

   private DereferencePathBrowserComposite[] browser;

   private Button browseButton;

   private TypeInfo filter;

   private TypeFinder finder;

   private ModelType model;

   public TypeSelectionComposite(Composite parent, String title)
   {
      init(parent, title, 1);
   }

   public TypeSelectionComposite(Composite parent, String title, int span)
   {
      init(parent, title, span);
   }

   private void init(Composite parent, final String title, int span)
   {
      final Composite composite = FormBuilder.createComposite(parent, 2);
      GridData gd = FormBuilder.createDefaultSingleLineWidgetGridData(span);
      ((GridLayout) composite.getLayout()).marginHeight = 0;
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      composite.setLayoutData(gd);

      typeText = FormBuilder.createText(composite);
      typeText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            String newType = typeText.getText();
            if (model != null)
            {
               newType = VariableContextHelper.getInstance().getContext(model)
                     .replaceAllVariablesByDefaultValue(newType);
            }
            if ((null == type) || !CompareHelper.areEqual(type.getFullName(), newType))
            {
               if (finder != null)
               {
                  type = finder.findType(newType);
               }
               else if ((browser != null) && (browser.length > 0))
               {
                  type = browser[0].getTypeFinder().findType(newType);
               }
               updateType();
            }
         }
      });
      browseButton = FormBuilder.createButton(composite, Diagram_Messages.Btn_Browse,
            new SelectionListener()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  IRunnableContext context = new ApplicationWindow(composite.getShell());
                  IJavaSearchScope scope = null;
                  if (filter != null)
                  {
                     try
                     {
                        scope = SearchEngine.createHierarchyScope(filter.getType());
                     }
                     catch (JavaModelException ex)
                     {
                        // TODO log
                        ex.printStackTrace();
                     }
                  }
                  if (scope == null)
                  {
                     IJavaProject project = finder == null ? null : finder
                           .getJavaProject();
                     if (project != null)
                     {
                        scope = SearchEngine
                              .createJavaSearchScope(new IJavaElement[] {project});
                     }
                     else
                     {
                        scope = SearchEngine.createWorkspaceScope();
                     }
                  }
                  try
                  {
                     SelectionDialog dialog = JavaUI.createTypeDialog(composite
                           .getShell(), context, scope, style, false);
                     dialog.setTitle(title);
                     if (dialog.open() == Window.OK)
                     {
                        type = new TypeInfo(finder, (IType) dialog.getResult()[0], null);
                        typeText.setText(type.getFullName());
                        updateType();
                     }
                  }
                  catch (JavaModelException e1)
                  {
                     e1.printStackTrace();
                  }
               }

               public void widgetDefaultSelected(SelectionEvent e)
               {}

            });
   }

   public void setStyle(int style)
   {
      this.style = style;
   }

   protected void updateType()
   {
      if (browser != null)
      {
         for (int i = 0; i < browser.length; i++)
         {
            browser[i].setType(type);
         }
      }
   }

   public String getTypeText()
   {
      return typeText.getText();
   }

   public void setTypeText(String typeText)
   {
      this.typeText.setText(typeText);
   }

   public Text getText()
   {
      return typeText;
   }

   public TypeInfo getType()
   {
      return type;
   }

   public void setDereferencePathBrowser(DereferencePathBrowserComposite[] browser)
   {
      this.browser = browser;
   }

   public void setTypeFinder(TypeFinder finder)
   {
      this.finder = finder;
   }

   public void setEnabled(boolean enabled)
   {
      browseButton.setEnabled(enabled);
      typeText.setEditable(enabled);
   }

   public void setType(TypeInfo type)
   {
      this.type = type;
      typeText.setText(type == null ? "" : type.getFullName()); //$NON-NLS-1$
   }

   public void setFilter(Class<?> clazz)
   {
      filter = finder.findType(clazz.getName());
   }

   public void setModel(ModelType model)
   {
      this.model = model;
   }
}
