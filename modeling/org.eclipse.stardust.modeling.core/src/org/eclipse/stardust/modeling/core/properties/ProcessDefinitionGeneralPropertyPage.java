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

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.widgets.Composite;

public class ProcessDefinitionGeneralPropertyPage extends IdentifiablePropertyPage
{
   private static final int DEFAULT_PRIORITY = 1;
   
   private LabeledText priorityText;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      ProcessDefinitionType pd = (ProcessDefinitionType) element;
      priorityText.getText().setText(Integer.toString(pd.getDefaultPriority()));
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
   }

   public void contributeExtraControls(Composite composite)
   {
      priorityText = FormBuilder.createLabeledText(composite,
            Diagram_Messages.LBL_TXT_DEFAULT_PRIORITY);
   }
}
