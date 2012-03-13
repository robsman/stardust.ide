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
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IMetaType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeAction;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.IconFactory;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.common.ui.IWorkflowModelEditor;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.util.ImportUtils;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;


public class UsageDisplayDialog extends Dialog
{
   private boolean isReferenced = false;
   private EObject eObject;
   private MergeAction action = MergeAction.REPLACE;
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

   private UsageDisplayDialog(Shell shell, IconFactory iconFactory, EObject eObject)
   {
      super(shell);
      this.eObject = eObject;
      this.iconFactory = iconFactory;
      if (AttributeUtil.getAttributeValue((IExtensibleElement) eObject,
            IConnectionManager.URI_ATTRIBUTE_NAME) != null)
      {
         isReferenced = true;
      }
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite composite = (Composite) super.createDialogArea(parent);
      getShell().setText(Repository_Messages.TXT_CONFLICT);
      GridLayout layout = (GridLayout) composite.getLayout();
      layout.numColumns = 2;
      Label label = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      label.setImage(ImportUtils.getImage(iconFactory, eObject));
      FormBuilder.createLabel(composite, ImportUtils.getLabel(eObject));
      /*FormBuilder.createLabel(composite, Repository_Messages.LBL_ANOTHER + getType(eObject)
            + Repository_Messages.LBL_WITH_ID + MergeUtils.getId(eObject)
            + Repository_Messages.LBL_ALREADY_EXISTS_MD, 2);*/
      String message = Repository_Messages.LBL_ANOTHER_TYPE_WITH_ID_ALREADY_EXISTS_IN_MODEL;
      message = MessageFormat.format(message, new Object[]{getType(eObject), MergeUtils.getId(eObject)});
      FormBuilder.createLabel(composite, message, 2);
      
      final Button usageCheckBox = FormBuilder.createCheckBox(composite, Repository_Messages.BUT_USE_OPTION_FOR_ALL_DUPLICATE_ELEMENTS, 2);
      usageCheckBox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            if(usageCheckBox.getSelection())
            {
               setUsage(action);
            }
            else
            {
               setUsage(null);
            }
         }         
      });      
      usageCheckBox.setSelection(false);
      
      Group group = FormBuilder.createGroup(composite, " Action: ", 1, 2); //$NON-NLS-1$
      Button replaceButton = FormBuilder.createRadioButton(group,
            Repository_Messages.BUT_REPLACE_EXISTING_ELEMENT_WITH_NEW_ONE);
      replaceButton.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }

         public void widgetSelected(SelectionEvent e)
         {
            action = MergeAction.REPLACE;
            if(usageCheckBox.getSelection())
            {
               setUsage(action);
            }            
         }
      });
      Button reuseButton = FormBuilder.createRadioButton(group,
            Repository_Messages.BUT_REUSE_ELEMENT_IN_THE_MD);
      reuseButton.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e)
         {
         }
      
         public void widgetSelected(SelectionEvent e)
         {
            action = MergeAction.REUSE;
            if(usageCheckBox.getSelection())
            {
               setUsage(action);
            }            
         }
      });
      replaceButton.setSelection(true);
      
      if(isReferenced)
      {
         reuseButton.setEnabled(false);
      }
      
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
      
      UsageDisplayDialog dialog = new UsageDisplayDialog(shell, iconFactory, element);
      
      int result = dialog.open();
      if (result == Window.OK)
      {
         return dialog.action;
      }
      return null;
   }
}