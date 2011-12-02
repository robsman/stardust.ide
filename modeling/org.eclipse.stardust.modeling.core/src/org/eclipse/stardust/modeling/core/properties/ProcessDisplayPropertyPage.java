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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import ag.carnot.workflow.model.PredefinedConstants;

public class ProcessDisplayPropertyPage extends AbstractModelElementPropertyPage
{
   private ProcessDefinitionType process;

   private Button auxiliary;

   private boolean defaultSelection;

   private AttributeType auxiliaryAttr;

   protected void performDefaults()
   {
      super.performDefaults();
      auxiliary.setSelection(defaultSelection);
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      process = this.getProcess();
      auxiliaryAttr = AttributeUtil.getAttribute(process,
            PredefinedConstants.PROCESS_IS_AUXILIARY_ATT);
      if (auxiliaryAttr == null)
      {
         defaultSelection = false;
         auxiliary.setSelection(false);
      }
      else
      {
         defaultSelection = true;
         auxiliary.setSelection(true);
      }

   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public void apply()
   {
      super.apply();
      auxiliaryAttr = AttributeUtil.getAttribute(process,
            PredefinedConstants.PROCESS_IS_AUXILIARY_ATT);
      if (auxiliaryAttr == null)
      {
         defaultSelection = false;
      }
      else
      {
         defaultSelection = true;
      }
   }

   private ProcessDefinitionType getProcess()
   {
      return (ProcessDefinitionType) getModelElement();
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);

      auxiliary = FormBuilder.createCheckBox(composite,
            Diagram_Messages.LB_PROCESS_IS_AUXILIARY, 1);
      auxiliary.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            boolean currentSelection = auxiliary.getSelection();
            if (currentSelection == false)
            {
               if (auxiliaryAttr != null)
               {
                  MergeUtils.deleteElement(auxiliaryAttr, null);
               }
            }
            else
            {
               AttributeUtil.setBooleanAttribute((IExtensibleElement) process,
                     PredefinedConstants.PROCESS_IS_AUXILIARY_ATT, true);
               auxiliaryAttr = AttributeUtil.getAttribute(process,
                     PredefinedConstants.PROCESS_IS_AUXILIARY_ATT);
            }
         }

      });
      return composite;
   }
}