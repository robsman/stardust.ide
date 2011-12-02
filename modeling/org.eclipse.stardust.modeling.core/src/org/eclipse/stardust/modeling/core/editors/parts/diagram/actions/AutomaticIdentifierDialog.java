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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.stardust.modeling.common.ui.IdFactory;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class AutomaticIdentifierDialog extends Dialog
{
   private Text idText;
   private Button performCheckBox;
   private Button autoPerformCheckBox;
   
   private String id;
   private String iconPath;
   private String type;
   
   private boolean perform = false;
   private boolean autoPerform = false;
   
   private Object container;
   private Map counters = new HashMap();
   
   private boolean canAutoPerform;
   
   protected AutomaticIdentifierDialog(Shell parentShell, boolean canAutoPerform)
   {
      super(parentShell);
      this.canAutoPerform = canAutoPerform;
      perform = !canAutoPerform;
   }

   protected Control createDialogArea(Composite parent)
   {
      getShell().setText(Diagram_Messages.TXT_WR);
      Composite composite = (Composite) super.createDialogArea(parent);
      GridLayout layout = (GridLayout) composite.getLayout();
      layout.numColumns = 3;
      Image image = DiagramPlugin.getImage(iconPath);
      Label iconLabel = FormBuilder.createLabel(composite, ""); //$NON-NLS-1$
      iconLabel.setImage(image);
      FormBuilder.createLabel(composite, Diagram_Messages.LBL_NULL, 2);
      FormBuilder.createLabel(composite, MessageFormat.format(Diagram_Messages.LBL_HAS_NO_ID,new Object[]{type}), 3);
      FormBuilder.createLabel(composite, Diagram_Messages.LB_ID);
      idText = FormBuilder.createText(composite, 2);
      idText.addModifyListener(new ModifyListener()
      {
         public void modifyText(ModifyEvent e)
         {
            id = idText.getText();
         }
      });
      performCheckBox = FormBuilder.createCheckBox(composite, Diagram_Messages.BOX_GENERATE_AUTOMATIC_ID, 2);
      performCheckBox.setSelection(perform);
      performCheckBox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {/* (fh) ignore */}

         public void widgetSelected(SelectionEvent e)
         {
            perform = performCheckBox.getSelection();
            autoPerformCheckBox.setSelection(false);
            autoPerformCheckBox.setVisible(perform && canAutoPerform);
            updateId();
         }
      });
      autoPerformCheckBox = FormBuilder.createCheckBox(composite, getAutoPerformCheckBoxText(), 3);
      autoPerformCheckBox.setSelection(autoPerform);
      autoPerformCheckBox.setVisible(perform && canAutoPerform);
      autoPerformCheckBox.addSelectionListener(new SelectionListener()
      {
         public void widgetDefaultSelected(SelectionEvent e) {/* (fh) ignore */}

         public void widgetSelected(SelectionEvent e)
         {
            autoPerform = autoPerformCheckBox.getSelection();
         }
      });
      updateId();
      return composite;
   }

   private String getAutoPerformCheckBoxText()
   {
      return canAutoPerform ? Diagram_Messages.BOX_TXT_APPLY_TO_ALL_MISSING_ID_S : Diagram_Messages.BOX_TXT_APPLY_TO_ALL_SIMILAR_ISSUES_IN_THIS_MD;
   }

   public void updateId()
   {
      if (perform)
      {
         int[] count = (int[]) counters.get(container);
         if (count == null)
         {
            if (container instanceof List)
            {
               IdFactory factory = new IdFactory(type, null);
               factory.computeNames((List) container);
               id = factory.getId();
               if (!type.equals(id))
               {
                  try
                  {
                     count = new int[] {Integer.parseInt(id.substring(id.lastIndexOf('_') + 1)) - 1};
                  }
                  catch (NumberFormatException nfe)
                  {
                     // (fh) fall through
                  }
               }
            }
            if (count == null)
            {
               count = new int[] {0};
            }
            counters.put(container, count);
         }
         id = type + '_' + ++count[0];
         if (!idText.isDisposed())
         {
            idText.setText(id);
         }
      }
   }

   public void initialize(Object container, String iconPath, String type, boolean autoReplace)
   {
      this.container = container;
      this.iconPath = iconPath;
      this.type = type;
      this.autoPerform = autoReplace;
   }

   public String getId()
   {
      return id;
   }

   public boolean getAutoPerform()
   {
      return autoPerform;
   }
}
