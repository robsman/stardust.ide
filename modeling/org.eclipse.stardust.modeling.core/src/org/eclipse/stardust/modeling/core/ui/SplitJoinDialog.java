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

import java.text.MessageFormat;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.JoinSplitType;
import org.eclipse.stardust.modeling.common.projectnature.ModelingCoreActivator;
import org.eclipse.stardust.modeling.common.ui.BpmUiActivator;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author rsauer
 * @version $Revision: 31000 $
 */
public class SplitJoinDialog extends Dialog
{
   private Button[] buttons;

   private Button saveButton;
   private FlowControlType flow;

   private JoinSplitType selectedType;

   public JoinSplitType getSelectedType()
   {
      return selectedType;
   }

   protected void okPressed()
   {
      boolean save = saveButton.getSelection();
      if (save)
      {
         PlatformUI.getPreferenceStore().setValue(
               ModelingCoreActivator.PLUGIN_ID + flow.getLiteral() + "Prompt", false);
      }
      int i = 0;
      for (JoinSplitType type : JoinSplitType.values())
      {
         if (type != JoinSplitType.NONE_LITERAL)
         {
            boolean selected = buttons[i++].getSelection();
            if (save)
            {
               PlatformUI.getPreferenceStore().setValue(
                     ModelingCoreActivator.PLUGIN_ID + flow.getLiteral() + type.getLiteral(), selected);
            }
            if (selected)
            {
               selectedType = type;
            }
         }
      }
      super.okPressed();
   }

   public SplitJoinDialog(Shell parentShell, FlowControlType flow)
   {
      super(parentShell);
      this.flow = flow;
   }

   protected void configureShell(Shell shell)
   {
      super.configureShell(shell);
      shell.setText(MessageFormat.format(Diagram_Messages.SplitJoinDialog_Title, BpmUiActivator.i18n(flow)));
   }

   protected Control createDialogArea(Composite parent)
   {
      Composite panel = (Composite) super.createDialogArea(parent);

      ((GridLayout) panel.getLayout()).numColumns = JoinSplitType.values().length;

      buttons = new Button[JoinSplitType.values().length - 1];

      int i = 0;
      for (JoinSplitType type : JoinSplitType.values())
      {
         if (type != JoinSplitType.NONE_LITERAL)
         {
            buttons[i++] = FormBuilder.createRadioButton(panel, BpmUiActivator.i18n(type));
         }
      }

      saveButton = FormBuilder.createCheckBox(panel, Diagram_Messages.SplitJoinDialog_Save);
      saveButton.setSelection(false);

      return panel;
   }
}