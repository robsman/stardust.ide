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

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import ag.carnot.workflow.model.PredefinedConstants;

public class ApplicationGeneralPropertyPage extends IdentifiablePropertyPage
{

   private Button publicCheckBox;

   private boolean publicType;

   private IModelElement modelElement;

   @Override
   protected void contributeExtraControls(Composite composite)
   {
      super.contributeExtraControls(composite);
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

   @Override
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadElementFromFields(symbol, element);
   }

   @Override
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      modelElement = element;
      setupVisibility();
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
