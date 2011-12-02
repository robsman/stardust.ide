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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.ITypedElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.ui.EObjectLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


public class AccessPathBrowser extends Dialog
{
   private String selectedMethod = ""; //$NON-NLS-1$
   private String title;
   private ITypedElement accessPoint;
   private DirectionType direction;
   private boolean updateSelection;
   private AccessPathBrowserContentProvider provider;
   private WorkflowModelEditor editor;

   public AccessPathBrowser(WorkflowModelEditor editor, String title, ITypedElement accessPoint,
                            DirectionType direction)
   {
      super(editor.getSite().getShell());
      setShellStyle(getShellStyle() | SWT.RESIZE);
      this.editor = editor;
      this.title = title;
      this.accessPoint = accessPoint;
      this.direction = direction;
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
            if (updateSelection)
            {
               updateSelection = false;
               ISelection selection = provider.parseSelection(selectedMethod);
               viewer.setSelection(selection, true);
            }
            else
            {
               TreeItem[] selection = ((TreeViewer) event.getSource()).getTree().getSelection();
               TreeItem methodItem = selection == null || selection.length == 0
                  ? null : selection[0];
               checkCanClose(methodItem);
               selectedMethod = getMethodPath(methodItem);
            }
         }
      });
      viewer.addDoubleClickListener(new IDoubleClickListener()
      {

         public void doubleClick(DoubleClickEvent event)
         {
            TreeItem methodItem = viewer.getTree().getSelection()[0];
            if (methodItem.getItems().length < 1)
            {
               checkCanClose(methodItem);
               selectedMethod = getMethodPath(methodItem);
               if (getButton(IDialogConstants.OK_ID).isEnabled())
               {
                  close();
               }
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
      provider = new AccessPathBrowserContentProvider(direction);
      viewer.setContentProvider(provider);
      viewer.setLabelProvider(new EObjectLabelProvider(editor));
      viewer.setSorter(new ViewerSorter());
      viewer.setInput(accessPoint);
      updateSelection = true;
      return composite;
   }

   private void checkCanClose(TreeItem methodItem)
   {
      boolean canClose = false;
      if (methodItem != null)
      {
         Object data = methodItem.getData();
         if (data instanceof AccessPointType)
         {
            canClose = direction.equals(((AccessPointType) data).getDirection());
         }
      }
      Button okButton = getButton(IDialogConstants.OK_ID);
      if (okButton != null)
      {
         okButton.setEnabled(canClose);
      }
   }

   protected Control createButtonBar(Composite parent)
   {
      Control buttonBar = super.createButtonBar(parent);
      getButton(IDialogConstants.OK_ID).setEnabled(false);
      return buttonBar;
   }

   private String getMethodPath(TreeItem methodItem)
   {
      String methodPath = null;
      if (methodItem != null)
      {
         if (methodItem.getParentItem() != null)
         {
            methodPath = getMethodPath(methodItem.getParentItem());
         }
         IIdentifiableElement info = (IIdentifiableElement) methodItem.getData();
         String methodName = info.getId();
         String separator = null;
         if (info instanceof IExtensibleElement)
         {
            separator = AttributeUtil.getAttributeValue((IExtensibleElement) info,
                  "carnot:engine:path:separator"); //$NON-NLS-1$
         }
         methodPath = methodPath == null ? methodName : methodPath +
               (separator == null ? "." : separator) + methodName; //$NON-NLS-1$
      }
      return methodPath;
   }

   public String getSelectedMethod()
   {
      return selectedMethod;
   }

   public void setMethod(String method)
   {
      selectedMethod = method;
   }
}
