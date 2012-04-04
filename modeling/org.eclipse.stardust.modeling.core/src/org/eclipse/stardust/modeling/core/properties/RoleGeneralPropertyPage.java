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
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.VerifierFactory;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class RoleGeneralPropertyPage extends IdentifiablePropertyPage
{
   private Text cardinalityText;

   private Button publicCheckBox;

   private boolean publicType;

   private IModelElement modelElement;

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
      
      modelElement = element;
      setupVisibility();
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
      FormBuilder.createLabel(composite, Diagram_Messages.LB_Cardinality);
      cardinalityText = FormBuilder.createText(composite);
      cardinalityText.addVerifyListener(VerifierFactory.intVerifier);
      
      publicCheckBox = FormBuilder.createCheckBox(composite,
            Diagram_Messages.CHECKBOX_Visibility);
      publicCheckBox.addSelectionListener(new SelectionAdapter()
      {

         public void widgetSelected(SelectionEvent e)
         {

            publicType = !publicType;
            if (publicType)
            {
               AttributeUtil.setAttribute((IExtensibleElement) modelElement,
                     PredefinedConstants.MODELELEMENT_VISIBILITY, "Public"); //$NON-NLS-1$
            }
            else
            {
               AttributeUtil.setAttribute((IExtensibleElement) modelElement,
                     PredefinedConstants.MODELELEMENT_VISIBILITY, "Private"); //$NON-NLS-1$
            }
         }
      });
   }
   
   private void setupVisibility()
   {
      AttributeType visibility = AttributeUtil.getAttribute(
            (IExtensibleElement) modelElement,
            PredefinedConstants.MODELELEMENT_VISIBILITY);
      if (visibility == null)
      {
         String visibilityDefault = PlatformUI.getPreferenceStore().getString(
               BpmProjectNature.PREFERENCE_MULTIPACKAGEMODELING_VISIBILITY);
         if (visibilityDefault == null || visibilityDefault == "" //$NON-NLS-1$
               || visibilityDefault.equalsIgnoreCase("Public")) //$NON-NLS-1$
         {
            AttributeUtil.setAttribute((IExtensibleElement) modelElement,
                  PredefinedConstants.MODELELEMENT_VISIBILITY, "Public"); //$NON-NLS-1$
            publicType = true;
         }
      }
      else
      {
         if (visibility.getValue().equalsIgnoreCase("Public")) //$NON-NLS-1$
         {
            publicType = true;
         }
         else
         {
            publicType = false;
         }
      }
      publicCheckBox.setSelection(publicType);
   }
}


