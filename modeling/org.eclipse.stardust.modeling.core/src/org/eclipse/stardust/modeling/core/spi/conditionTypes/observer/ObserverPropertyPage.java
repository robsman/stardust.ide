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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.observer;

import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.spi.IConditionPropertyPage;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.spi.DefaultModelElementPropertyPage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


public class ObserverPropertyPage extends DefaultModelElementPropertyPage
      implements IConditionPropertyPage
{
   private Button completedEventButton;

   private Button stateChangeEventButton;

   private AttributeType stateChangeAttribute;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      EventHandlerType eventHandler = (EventHandlerType) element;
      stateChangeAttribute = AttributeUtil.getAttribute(eventHandler,
            CarnotConstants.STATE_CHANGE_ATT);
      if ((completedEventButton != null) && (stateChangeAttribute != null))
      {
         completedEventButton.setSelection(stateChangeAttribute.getValue()
               .equals("false")); //$NON-NLS-1$
         stateChangeEventButton.setSelection(stateChangeAttribute.getValue().equals(
               "true")); //$NON-NLS-1$
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      EventHandlerType eventHandler = (EventHandlerType) element;
      if (stateChangeEventButton != null)
      {
         AttributeUtil.setAttribute(eventHandler, CarnotConstants.STATE_CHANGE_ATT,
               "boolean", String.valueOf(stateChangeEventButton.getSelection())); //$NON-NLS-1$
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2, 3);
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      completedEventButton = FormBuilder
            .createRadioButton(composite, Diagram_Messages.B_RADIO_CompletedEvent); 
      stateChangeEventButton = FormBuilder.createRadioButton(composite,
            Diagram_Messages.B_RADIO_StateChangeEvent); 
      completedEventButton.setSelection(true);
      return composite;
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }   
}