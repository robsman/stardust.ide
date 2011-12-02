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

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.DateUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.gface.date.DatePickerCombo;
import com.gface.date.DatePickerStyle;

import ag.carnot.workflow.model.PredefinedConstants;

public class ModelPropertyPage extends IdentifiablePropertyPage
{
   private static final String EMPTY = ""; //$NON-NLS-1$
   private static final String TIMESTAMP_TYPE = "Timestamp"; //$NON-NLS-1$

   private DatePickerCombo validFrom;

   protected String getOidLabel()
   {
      return Diagram_Messages.LB_OID;
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
      AttributeUtil.setAttribute((IExtensibleElement) element, PredefinedConstants.VALID_FROM_ATT, TIMESTAMP_TYPE, getDate(validFrom));
      AttributeUtil.setAttribute((IExtensibleElement) element, PredefinedConstants.VALID_TO_ATT, null);
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      setDate(AttributeUtil.getAttributeValue((IExtensibleElement) element, PredefinedConstants.VALID_FROM_ATT), validFrom);
   }

   private String getDate(DatePickerCombo control)
   {
      Date date = control.getDate();
      return date == null ? EMPTY : DateUtils.getNonInteractiveDateFormat().format(date);
   }

   private void setDate(String textValue, DatePickerCombo control)
   {
      if (textValue != null)
      {
         try
         {
            Date date = DateUtils.getNonInteractiveDateFormat().parse(textValue);
            control.setDate(date);
         }
         catch (ParseException e)
         {
            control.setDate(null);
         }
      }
      else
      {
         control.setDate(null);
      }
   }

   protected void contributeExtraControls(Composite composite)
   {
      int dpStyle = DatePickerStyle.HIDE_WHEN_NOT_IN_FOCUS|
         DatePickerStyle.SINGLE_CLICK_SELECTION |
         DatePickerStyle.WEEKS_STARTS_ON_MONDAY;

      FormBuilder.createLabel(composite, Diagram_Messages.LB_ValidFrom);
      validFrom = new DatePickerCombo(composite, SWT.BORDER, dpStyle);
      validFrom.setDateFormat(DateFormat.getDateInstance(DateFormat.SHORT));
      validFrom.setLayoutData(FormBuilder.createDefaultSingleLineWidgetGridData());
   }
}
