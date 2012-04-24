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

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ConflictDialog extends Dialog
{
   private EObject eObject;
   private IconFactory iconFactory;
   public static MergeAction usage = null;

   public static MergeAction getUsage()
   {
      return usage;
   }

   public static void setUsage(MergeAction usage)
   {
      UsageDisplayDialog.usage = usage;
   }   

   private ConflictDialog(Shell shell, IconFactory iconFactory, EObject eObject)
   {
      super(shell);
      this.eObject = eObject;
      this.iconFactory = iconFactory;
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite composite = (Composite) super.createDialogArea(parent);
      getShell().setText(Repository_Messages.TXT_CONFLICT_Replace);
      GridLayout layout = (GridLayout) composite.getLayout();
      layout.numColumns = 2;
      Label label = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      label.setImage(ImportUtils.getImage(iconFactory, eObject));
      FormBuilder.createLabel(composite, ImportUtils.getLabel(eObject));
      String message = Repository_Messages.LBL_ANOTHER_TYPE_WITH_ID_ALREADY_EXISTS_IN_MODEL;
      message = MessageFormat.format(message, new Object[]{getType(eObject), MergeUtils.getId(eObject)});
      FormBuilder.createLabel(composite, message, 2);
                  
      return composite;
   }

   private String getType(EObject eObject)
   {
      return eObject.eContainingFeature().getName();
   }

   public static MergeAction acceptClosure(Shell shell, IconFactory iconFactory, EObject element, EObject original)
   {
      if (original instanceof IMetaType)
      {
         // types always reuse
         return MergeAction.REUSE;
      }
      if (original instanceof DataType)
      {
         DataType data = (DataType) original;
         if (data.isPredefined())
         {
            // always reuse predefined data
            return MergeAction.REUSE;
         }
      }
      ModelType model = ModelUtils.findContainingModel(original);
      if (model != null)
      {
         IWorkflowModelEditor editor = BpmUiActivator.findWorkflowModelEditor(model);
         if (editor != null && editor.requireLock(original))
         {
            return MergeAction.REUSE;
         }
      }
      
      MergeAction usage = UsageDisplayDialog.getUsage();
      if(usage != null)
      {
         return usage;
      }
      
      ConflictDialog dialog = new ConflictDialog(shell, iconFactory, element);
      
      int result = dialog.open();
      if (result == Window.OK)
      {
         return MergeAction.REPLACE;
      }
      return null;
   }
}