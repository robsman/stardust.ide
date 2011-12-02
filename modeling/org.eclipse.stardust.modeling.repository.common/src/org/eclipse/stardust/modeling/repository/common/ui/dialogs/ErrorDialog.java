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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


public class ErrorDialog extends Dialog
{
   private final static int WIDGET_HEIGHT = 200;
   private final static int WIDGET_WIDTH = 450;
   
   private EObject eObject;
   private EStructuralFeature feature;

   public ErrorDialog(EObject eObject,
         EStructuralFeature feature)
   {
      super((Shell) null);
      this.eObject = eObject;
      this.feature = feature;
   }

   protected Control createDialogArea(Composite parent)
   {
      getShell().setText(Repository_Messages.TXT_ERROR + ":"+ Repository_Messages.TXT_INVALID_CONTAINING_FEATURE); //$NON-NLS-2$
      
      Composite composite = (Composite) super.createDialogArea(parent);
      GridData data = (GridData) composite.getLayoutData();
      data.heightHint = WIDGET_HEIGHT;
      data.widthHint = WIDGET_WIDTH;

      Composite content = FormBuilder.createComposite(composite, 2);
      FormBuilder.createLabel(content, Repository_Messages.LBL_ID); //$NON-NLS-2$
      FormBuilder.applyDefaultTextControlWidth(
            FormBuilder.createLabel(content, createIdLabel(MergeUtils.getId(eObject), eObject.eClass())));
      FormBuilder.createLabel(content, Repository_Messages.LBL_NAME); //$NON-NLS-2$
      String name = MergeUtils.getName(eObject);
      FormBuilder.createLabel(content, name == null ? "<" +Repository_Messages.LBL_NAME_KLEIN_GESCHRIEBEN + ">" : name); //$NON-NLS-1$ //$NON-NLS-3$

      FormBuilder.createLabel(content, Repository_Messages.LBL_CONTAINER); //$NON-NLS-2$
      EObject container = eObject.eContainer();
      String containerLabel = Repository_Messages.LBL_NULL;
      if (container != null)
      {
         if (container instanceof IIdentifiableElement)
         {
            containerLabel = createIdLabel(((IIdentifiableElement) container).getId(), container.eClass());
         }
         else
         {
            containerLabel = "(" + container.eClass().getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
         }
      }
      FormBuilder.createLabel(content, containerLabel);
      
      if (feature != null)
      {
         FormBuilder.createLabel(content, Repository_Messages.LBL_FEATURE); //$NON-NLS-2$
         FormBuilder.createLabel(content, feature.getName() == null ? "<name>" : feature.getName()); //$NON-NLS-1$ //$NON-NLS-3$
      }
      
      return composite;
   }

   private static String createIdLabel(String id, EClass eClass)
   {
      if (id == null)
      {
         id = "<id>"; //$NON-NLS-1$ //$NON-NLS-3$
      }
      return id + " (" + eClass.getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
   }
}