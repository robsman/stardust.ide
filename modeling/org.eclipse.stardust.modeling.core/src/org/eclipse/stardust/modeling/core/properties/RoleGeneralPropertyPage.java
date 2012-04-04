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

import java.text.NumberFormat;
import java.text.ParseException;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class RoleGeneralPropertyPage extends IdentifiablePropertyPage
{
   private Text cardinalityText;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      RoleType role = (RoleType) element;
      String cardinality = NumberFormat.getIntegerInstance()
            .format(role.getCardinality());
      if (cardinality != null)
      {
         cardinalityText.setText(cardinality);
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
      RoleType role = (RoleType) element;
      String cardinalityString = cardinalityText.getText().trim();
      int cardinality = 0;
      if (!StringUtils.isEmpty(cardinalityString))
      {
         try
         {
            cardinality = NumberFormat.getIntegerInstance().parse(cardinalityString)
                  .intValue();
         }
         catch (ParseException e)
         {
         }
      }

      if (0 != cardinality)
      {
         role.setCardinality(cardinality);
      }
      else
      {
         role.eUnset(CarnotWorkflowModelPackage.eINSTANCE.getRoleType_Cardinality());
      }
   }

   public void contributeExtraControls(Composite composite)
   {            
      super.contributeExtraControls(composite);
      FormBuilder.createLabel(composite, Diagram_Messages.LB_Cardinality);
      cardinalityText = FormBuilder.createText(composite);
      cardinalityText.addVerifyListener(VerifierFactory.intVerifier);      
   }
}


