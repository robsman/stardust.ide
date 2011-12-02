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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.processStatechange;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.swt.widgets.Group;


public class ProcessStateChangePropertyPage extends DefaultModelElementPropertyPage
      implements IConditionPropertyPage

{
   private static final String SOURCE_STATE = Diagram_Messages.SOURCE_STATE; 

   private static final String INTERRUPTED = Diagram_Messages.INTERRUPTED; 

   private static final String ABORTED = Diagram_Messages.ABORTED; 

   private static final String COMPLETED = Diagram_Messages.COMPLETED; 

   private static final String ACTIVE = Diagram_Messages.ACTIVE; 

   private static final String N_A = "N/A"; //$NON-NLS-1$

   private static final String TARGET_STATE = Diagram_Messages.TARGET_STATE; 

   private static final String INTERRUPTED_VAL = "3"; //$NON-NLS-1$

   private static final String ABORTED_VAL = "1"; //$NON-NLS-1$

   private static final String COMPLETED_VAL = "2"; //$NON-NLS-1$

   private static final String ACTIVE_VAL = "0"; //$NON-NLS-1$

   private Button interruptedSourceStateButton;

   private Button activeSourceStateButton;

   private Button naSourceStateButton;

   private Button naTargetStateButton;

   private Button activeTargetStateButton;

   private Button completedTargetStateButton;

   private Button abortedTargetStateButton;

   private Button interruptedTargetStateButton;

   private AttributeType sourceStateAttribute;

   private AttributeType targetStateAttribute;

   private EList attributes;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      init((EventHandlerType) element);
      setSourceStateValue();
      setTargetStateValue();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      EventHandlerType eventHandler = (EventHandlerType) element;
      if (naSourceStateButton != null)
      {
         setSourceStateAttribute(eventHandler);
         setTargetStateAttribute(eventHandler);
      }
   }

   private void init(EventHandlerType eventHandler)
   {
      attributes = eventHandler.getAttribute();
      for (Iterator iter = attributes.iterator(); iter.hasNext();)
      {
         AttributeType handlerAttribute = (AttributeType) iter.next();
         if (handlerAttribute.getName().equals(CarnotConstants.TARGET_STATE_ATT))
         {
            targetStateAttribute = handlerAttribute;
         }
         else if (handlerAttribute.getName().equals(CarnotConstants.SOURCE_STATE_ATT))
         {
            sourceStateAttribute = handlerAttribute;
         }
      }
   }

   private void setTargetStateValue()
   {
      if (targetStateAttribute == null)
      {
         naTargetStateButton.setSelection(true);
      }
      else
      {
         switch (Integer.parseInt(targetStateAttribute.getValue()))
         {
         case 0:
            activeTargetStateButton.setSelection(true);
            break;
         case 1:
            abortedTargetStateButton.setSelection(true);
            break;
         case 2:
            completedTargetStateButton.setSelection(true);
            break;
         case 3:
            interruptedTargetStateButton.setSelection(true);
            break;
         default:
            naTargetStateButton.setSelection(true);
            break;
         }
      }
   }

   private void setSourceStateValue()
   {
      if (sourceStateAttribute == null)
      {
         naSourceStateButton.setSelection(true);
      }
      else
      {
         switch (Integer.parseInt(sourceStateAttribute.getValue()))
         {
         case 0:
            activeSourceStateButton.setSelection(true);
            break;
         case 3:
            interruptedSourceStateButton.setSelection(true);
            break;
         default:
            naSourceStateButton.setSelection(true);
            break;
         }
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1, 3);
      ((GridLayout) composite.getLayout()).marginHeight = 0;
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      createSourceStateGroup(composite);

      createTargetStateGroup(composite);

      return composite;
   }

   private void createTargetStateGroup(Composite composite)
   {
      Group targetStateGroup = FormBuilder.createGroup(composite, TARGET_STATE, 4);

      naTargetStateButton = FormBuilder.createRadioButton(targetStateGroup, N_A, 4);

      activeTargetStateButton = FormBuilder.createRadioButton(targetStateGroup, ACTIVE);

      completedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            COMPLETED);

      abortedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup, ABORTED);

      interruptedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            INTERRUPTED);
   }

   private void createSourceStateGroup(Composite composite)
   {
      Group sourceStateGroup = FormBuilder.createGroup(composite, SOURCE_STATE, 2);

      naSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup, N_A);

      FormBuilder.createLabel(sourceStateGroup, ""); //$NON-NLS-1$

      activeSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup, ACTIVE);

      interruptedSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup,
            INTERRUPTED);
   }

   private void setTargetStateAttribute(EventHandlerType eventHandler)
   {
      if ((targetStateAttribute == null) && (!naTargetStateButton.getSelection()))
      {
         AttributeUtil.setAttribute(eventHandler, CarnotConstants.TARGET_STATE_ATT,
               CarnotConstants.PROCESS_INSTANCE_STATE_ATT, ACTIVE_VAL);
         targetStateAttribute = AttributeUtil.getAttribute(eventHandler,
               CarnotConstants.TARGET_STATE_ATT);
      }
      if (activeTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(ACTIVE_VAL);
      }
      if (completedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(COMPLETED_VAL);
      }
      else if (abortedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(ABORTED_VAL);
      }
      else if (interruptedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(INTERRUPTED_VAL);
      }
      else if (naTargetStateButton.getSelection())
      {
         attributes.remove(targetStateAttribute);
      }
   }

   private void setSourceStateAttribute(EventHandlerType eventHandler)
   {
      if ((sourceStateAttribute == null) && (naSourceStateButton != null)
            && (!naSourceStateButton.getSelection()))
      {
         AttributeUtil.setAttribute(eventHandler, CarnotConstants.SOURCE_STATE_ATT,
               CarnotConstants.PROCESS_INSTANCE_STATE_ATT, ACTIVE_VAL);
         sourceStateAttribute = AttributeUtil.getAttribute(eventHandler,
               CarnotConstants.SOURCE_STATE_ATT);
      }
      if (interruptedSourceStateButton.getSelection())
      {
         sourceStateAttribute.setValue(INTERRUPTED_VAL);
      }
      else if (activeSourceStateButton.getSelection())
      {
         sourceStateAttribute.setValue(ACTIVE_VAL);
      }
      else if ((naSourceStateButton.getSelection()) && (sourceStateAttribute != null))
      {
         attributes.remove(sourceStateAttribute);
      }
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }   
}