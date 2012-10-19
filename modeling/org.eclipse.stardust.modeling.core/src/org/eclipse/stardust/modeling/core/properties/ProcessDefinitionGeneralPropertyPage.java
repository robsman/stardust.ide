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

import java.util.ArrayList;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.Model_Messages;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;

public class ProcessDefinitionGeneralPropertyPage extends IdentifiablePropertyPage
{
   private static final int DEFAULT_PRIORITY = 1;

   private LabeledText priorityText;

   private Button auditTrailPersistenceCheckBox;

   private String auditTrailPersistence = null;

   private ComboViewer comboViewer;

   private boolean isAuditTrailPersistent;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      ProcessDefinitionType pd = (ProcessDefinitionType) element;
      priorityText.getText().setText(Integer.toString(pd.getDefaultPriority()));

      AttributeType auditTrailPersistenceAttribute = AttributeUtil.getAttribute(
            (IExtensibleElement) getModelElement(),
            "carnot:engine:auditTrailPersistence"); //$NON-NLS-1$

      if (auditTrailPersistenceAttribute != null)
      {
         auditTrailPersistence = AttributeUtil.getAttributeValue(
               (IExtensibleElement) getModelElement(),
               "carnot:engine:auditTrailPersistence"); //$NON-NLS-1$
         auditTrailPersistenceCheckBox.setSelection(true);
         isAuditTrailPersistent = true;
         comboViewer.setSelection(new StructuredSelection(auditTrailPersistence));
      }
      else
      {
         comboViewer.getCombo().select(0);
      }

      comboViewer.getCombo().setEnabled(isAuditTrailPersistent);
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
      ProcessDefinitionType pd = (ProcessDefinitionType) element;
      try
      {
         String priority = priorityText.getText().getText().trim();
         if (StringUtils.isEmpty(priority))
         {
            pd.setDefaultPriority(DEFAULT_PRIORITY);
         }
         else
         {
            pd.setDefaultPriority(Integer.parseInt(priority));
         }
      }
      catch (Exception ex)
      {
         // nothing to do here, maybe log an error
      }

      if ( !isAuditTrailPersistent)
      {
         AttributeUtil.setAttribute((IExtensibleElement) modelElement,
               "carnot:engine:auditTrailPersistence", null); //$NON-NLS-1$

      }
   }

   public void contributeExtraControls(Composite composite)
   {
      priorityText = FormBuilder.createLabeledText(composite,
            Diagram_Messages.LBL_TXT_DEFAULT_PRIORITY);

      auditTrailPersistenceCheckBox = FormBuilder.createCheckBox(composite,
            Diagram_Messages.LBL_AUDITTRAIL_PERSISTENCE, 1); //$NON-NLS-1$

      ArrayList<String> list = ModelUtils.getPersistenceOptions((ProcessDefinitionType) getModelElement());

      comboViewer = FormBuilder.createComboViewer(composite, list);
      comboViewer.getCombo().addSelectionListener(new SelectionAdapter()
      {

         public void widgetSelected(SelectionEvent e)
         {

            IStructuredSelection value = (IStructuredSelection) comboViewer.getSelection();
            AttributeUtil.setAttribute((IExtensibleElement) modelElement,
                  "carnot:engine:auditTrailPersistence", value.getFirstElement() //$NON-NLS-1$
                        .toString());
         }
      });

      comboViewer.setLabelProvider(new LabelProvider()
      {

         public String getText(Object element)
         {
            return ModelUtils.getPersistenceOptionsText(element.toString());
         }

      });

      auditTrailPersistenceCheckBox.addSelectionListener(new SelectionAdapter()
      {

         public void widgetSelected(SelectionEvent e)
         {

            isAuditTrailPersistent = !isAuditTrailPersistent;
            comboViewer.getCombo().setEnabled(isAuditTrailPersistent);
         }
      });

   }


}
