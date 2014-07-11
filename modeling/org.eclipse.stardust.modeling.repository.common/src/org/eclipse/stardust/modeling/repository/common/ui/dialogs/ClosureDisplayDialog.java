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
package org.eclipse.stardust.modeling.repository.common.ui.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.ui.ImageUtil;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;


public class ClosureDisplayDialog extends Dialog
{
   private final static int WIDGET_HEIGHT = 250;
   private final static int WIDGET_WIDTH = 300;

   private List closure;
   private EObject eObject;
   private IconFactory iconFactory;

   private ClosureDisplayDialog(Shell shell, IconFactory iconFactory, EObject eObject, List closure)
   {
      super(shell);
      this.iconFactory = iconFactory;
      this.eObject = eObject;
      this.closure = closure;
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText(Repository_Messages.TXT_REQUIRED_ELEMENTS);
   }

   private static ArrayList filter(EObject eObject, List elements)
   {
      ArrayList closure = new ArrayList();
      for (int i = 0; i < elements.size(); i++)
      {
         EObject element = (EObject) elements.get(i);
         if (element != eObject && !(element instanceof IMetaType))
         {
            closure.add(element);
         }
      }
      Collections.sort(closure, new Comparator()
      {
         public int compare(Object o1, Object o2)
         {
            EObject e1 = (EObject) o1;
            EObject e2 = (EObject) o2;
            String s1 = e1.eClass().getName() + ":" + ImportUtils.getLabel(e1); //$NON-NLS-1$
            String s2 = e2.eClass().getName() + ":" + ImportUtils.getLabel(e2); //$NON-NLS-1$
            // group participants together
            if (e1 instanceof IModelParticipant)
            {
               s1 = "participant:" + s1; //$NON-NLS-1$
            }
            if (e2 instanceof IModelParticipant)
            {
               s2 = "participant:" + s2; //$NON-NLS-1$
            }
            return s1.compareTo(s2);
         }
      });
      return closure;
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite composite = (Composite) super.createDialogArea(parent);
      GridLayout layout = (GridLayout) composite.getLayout();
      layout.numColumns = 2;
      Label label = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      label.setImage(ImageUtil.getImage(iconFactory, eObject));
      FormBuilder.createLabel(composite, ImportUtils.getLabel(eObject));
      FormBuilder.createLabel(composite, Repository_Messages.LBL_REQUIRES_THE_ELEMENTS_LISTED_BELOW, 2);
      Table table = FormBuilder.createTable(composite, SWT.BORDER, null, null, 2);
      GridData data = (GridData) table.getLayoutData();
      data.heightHint = WIDGET_HEIGHT;
      data.widthHint = WIDGET_WIDTH;
      table.setLayoutData(data);
      TableViewer viewer = new TableViewer(table);
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(new ILabelProvider()
      {
         public Image getImage(Object element)
         {
            return ImageUtil.getImage(ClosureDisplayDialog.this.iconFactory, (EObject) element);
         }

         public String getText(Object element)
         {
            return ImportUtils.getLabel((EObject) element);
         }

         public void addListener(ILabelProviderListener listener)
         {
         }

         public void dispose()
         {
         }

         public boolean isLabelProperty(Object element, String property)
         {
            return false;
         }

         public void removeListener(ILabelProviderListener listener)
         {
         }
      });
      viewer.setInput(closure);
      return composite;
   }

   public static boolean acceptClosure(Shell shell, IconFactory iconFactory, EObject eObject, List closure)
   {
      List filtered = filter(eObject, closure);
      if (!filtered.isEmpty())
      {
         ClosureDisplayDialog dialog = new ClosureDisplayDialog(shell, iconFactory, eObject, filtered);
         return dialog.open() == Window.OK;
      }
      return true;
   }
}