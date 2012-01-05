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
package org.eclipse.stardust.modeling.core.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.validation.util.MethodInfo;
import org.eclipse.stardust.modeling.validation.util.TypeFinder;
import org.eclipse.stardust.modeling.validation.util.TypeInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


public class DereferencePathBrowser extends Dialog
{
   private TypeInfo type;

   private String selectedMethod = ""; //$NON-NLS-1$

   private TypeFinder finder;

   private String title;

   private boolean isConstructor = false;

   private boolean isDeep = true;

   public DereferencePathBrowser(Shell parentShell, TypeInfo type, TypeFinder finder,
         String title)
   {
      super(parentShell);
      this.type = type;
      this.finder = finder;
      this.title = title;
   }

   protected Control createDialogArea(Composite parent)
   {
      getShell().setText(title);
      Composite composite = (Composite) super.createDialogArea(parent);
      GridData gd = (GridData) composite.getLayoutData();
      gd.minimumHeight = 300;
      gd.minimumWidth = 320;
      gd.heightHint = 300;
      gd.widthHint = 320;

      Tree tree = FormBuilder.createTree(composite, SWT.V_SCROLL | SWT.H_SCROLL
            | SWT.BORDER);
      final TreeViewer viewer = new TreeViewer(tree);
      viewer.addSelectionChangedListener(new ISelectionChangedListener()
      {
         public void selectionChanged(SelectionChangedEvent event)
         {
            TreeItem methodItem = ((TreeViewer) event.getSource()).getTree()
                  .getSelection()[0];

            selectedMethod = getMethodPath(methodItem);
         }

      });
      viewer.addDoubleClickListener(new IDoubleClickListener()
      {

         public void doubleClick(DoubleClickEvent event)
         {
            TreeItem methodItem = viewer.getTree().getSelection()[0];
            if (methodItem.getItems().length < 1)
            {
               selectedMethod = getMethodPath(methodItem).substring(0,
                     getMethodPath(methodItem).length());
               close();
            }
            else
            {
               Object obj = ((IStructuredSelection) event.getSelection())
                     .getFirstElement();

               if (viewer.getExpandedState(obj))
               {
                  viewer.collapseToLevel(obj, 1);
               }
               else
               {
                  viewer.expandToLevel(obj, 1);
               }
            }
         }

      });
      viewer.setContentProvider(new DereferencePathBrowserContentProvider(finder,
            isConstructor, isDeep));
      viewer.setLabelProvider(new DereferencePathBrowserLabelProvider());
      viewer.setInput(type);
      viewer.setSorter(new ViewerSorter());
      return composite;
   }

   private String getMethodPath(TreeItem methodItem)
   {
      String methodPath = null; //$NON-NLS-1$
      if (methodItem.getParentItem() != null)
      {
         methodPath = getMethodPath(methodItem.getParentItem());
      }
      MethodInfo info = (MethodInfo) methodItem.getData();
      String methodName = info.getEncoded();
      methodPath = methodPath == null ? methodName : methodPath + "." + methodName; //$NON-NLS-1$
      return methodPath;
   }

   public String getSelectedMethod()
   {
      return selectedMethod;
   }

   public void setConstructor(boolean b)
   {
      this.isConstructor = b;
   }

   public void setDeep(boolean b)
   {
      this.isDeep = b;
   }
}
