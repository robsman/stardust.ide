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
package org.eclipse.stardust.modeling.core.spi.conditionTypes.statechange;

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


public class StateChangePropertyPage extends DefaultModelElementPropertyPage
      implements IConditionPropertyPage
{
   private static final String HIBERNATED = "7"; //$NON-NLS-1$

   private static final String ABORTED = "6"; //$NON-NLS-1$

   private static final String SUSPENDED = "5"; //$NON-NLS-1$

   private static final String INTERRUPTED = "4"; //$NON-NLS-1$

   private static final String COMPLETED = "2"; //$NON-NLS-1$

   private static final String APPLICATION = "1"; //$NON-NLS-1$

   private static final String CREATED = "0"; //$NON-NLS-1$

   private AttributeType sourceStateAttribute;

   private AttributeType targetStateAttribute;

   private Button naSourceStateButton;

   private Button applicationSourceStateButton;

   private Button suspendedSourceStateButton;

   private Button hibernatedSourceStateButton;

   private Button interruptedSourceStateButton;

   private Button createdSourceStateButton;

   private Button naTargetStateButton;

   private Button applicationTargetStateButton;

   private Button suspendedTargetStateButton;

   private Button hibernatedTargetStateButton;

   private Button interruptedTargetStateButton;

   private Button createdTargetStateButton;

   private Button completedTargetStateButton;

   private Button abortedTargetStateButton;

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

   private void init(EventHandlerType type)
   {
      sourceStateAttribute = AttributeUtil.getAttribute(type,
            CarnotConstants.SOURCE_STATE_ATT);
      targetStateAttribute = AttributeUtil.getAttribute(type,
            CarnotConstants.TARGET_STATE_ATT);
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 1, 3);
      ((GridLayout) composite.getLayout()).horizontalSpacing = 0;
      ((GridLayout) composite.getLayout()).marginWidth = 0;
      ((GridLayout) composite.getLayout()).marginHeight = 0;
      createSourceStateGroup(composite);
      createTargetStateGroup(composite);
      return composite;
   }

   private void createTargetStateGroup(Composite composite)
   {
      Group targetStateGroup = FormBuilder.createGroup(composite,
            Diagram_Messages.GROUP_TargetState, 4);
      naTargetStateButton = FormBuilder.createRadioButton(targetStateGroup, "N/A", 4); //$NON-NLS-1$
      applicationTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            Diagram_Messages.B_RADIO_application);
      suspendedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            Diagram_Messages.B_RADIO_suspended);
      hibernatedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            Diagram_Messages.B_RADIO_hibernated);
      interruptedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            Diagram_Messages.B_RADIO_interrupted);
      createdTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            Diagram_Messages.B_RADIO_created);
      completedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            Diagram_Messages.B_RADIO_completed);
      abortedTargetStateButton = FormBuilder.createRadioButton(targetStateGroup,
            Diagram_Messages.B_RADIO_aborted);
   }

   private void createSourceStateGroup(Composite composite)
   {
      Group sourceStateGroup = FormBuilder.createGroup(composite,
            Diagram_Messages.GROUP_SourceState, 4);
      naSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup, "N/A", 4); //$NON-NLS-1$
      applicationSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup,
            Diagram_Messages.B_RADIO_application);
      suspendedSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup,
            Diagram_Messages.B_RADIO_suspended);
      hibernatedSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup,
            Diagram_Messages.B_RADIO_hibernated);
      interruptedSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup,
            Diagram_Messages.B_RADIO_interrupted);
      createdSourceStateButton = FormBuilder.createRadioButton(sourceStateGroup,
            Diagram_Messages.B_RADIO_created);
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
            createdTargetStateButton.setSelection(true);
            break;
         case 1:
            applicationTargetStateButton.setSelection(true);
            break;
         case 2:
            completedTargetStateButton.setSelection(true);
            break;

         case 4:
            interruptedTargetStateButton.setSelection(true);
            break;
         case 5:
            suspendedTargetStateButton.setSelection(true);
            break;
         case 6:
            abortedTargetStateButton.setSelection(true);
            break;
         case 7:
            hibernatedTargetStateButton.setSelection(true);
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
            createdSourceStateButton.setSelection(true);
            break;
         case 1:
            applicationSourceStateButton.setSelection(true);
            break;
         case 4:
            interruptedSourceStateButton.setSelection(true);
            break;
         case 5:
            suspendedSourceStateButton.setSelection(true);
            break;
         case 7:
            hibernatedSourceStateButton.setSelection(true);
            break;
         default:
            naSourceStateButton.setSelection(true);
            break;
         }
      }
   }

   private void setTargetStateAttribute(EventHandlerType eventHandler)
   {
      if ((targetStateAttribute == null) && (!naTargetStateButton.getSelection()))
      {
         AttributeUtil.setAttribute(eventHandler, CarnotConstants.TARGET_STATE_ATT,
               CarnotConstants.ACTIVITY_INSTANCE_STATE_ATT, CREATED);
         targetStateAttribute = AttributeUtil.getAttribute(eventHandler,
               CarnotConstants.TARGET_STATE_ATT);
      }
      if (createdTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(CREATED);
      }
      else if (applicationTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(APPLICATION);
      }
      else if (completedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(COMPLETED);
      }
      else if (interruptedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(INTERRUPTED);
      }
      else if (suspendedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(SUSPENDED);
      }
      else if (abortedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(ABORTED);
      }
      else if (hibernatedTargetStateButton.getSelection())
      {
         targetStateAttribute.setValue(HIBERNATED);
      }
      else if ((naTargetStateButton.getSelection()) && (targetStateAttribute != null))
      {
         AttributeUtil.setAttribute(eventHandler, CarnotConstants.TARGET_STATE_ATT, "");//$NON-NLS-1$
      }
   }

   private void setSourceStateAttribute(EventHandlerType eventHandler)
   {
      if ((sourceStateAttribute == null) && (!naSourceStateButton.getSelection()))
      {
         AttributeUtil.setAttribute(eventHandler, CarnotConstants.SOURCE_STATE_ATT,
               CarnotConstants.ACTIVITY_INSTANCE_STATE_ATT, CREATED);
         sourceStateAttribute = AttributeUtil.getAttribute(eventHandler,
               CarnotConstants.SOURCE_STATE_ATT);
      }
      if (createdSourceStateButton.getSelection())
      {
         sourceStateAttribute.setValue(CREATED);
      }
      else if (applicationSourceStateButton.getSelection())
      {
         sourceStateAttribute.setValue(APPLICATION);
      }
      else if (interruptedSourceStateButton.getSelection())
      {
         sourceStateAttribute.setValue(INTERRUPTED);
      }
      else if (suspendedSourceStateButton.getSelection())
      {
         sourceStateAttribute.setValue(SUSPENDED);
      }
      else if (hibernatedSourceStateButton.getSelection())
      {
         sourceStateAttribute.setValue(HIBERNATED);
      }
      else if ((naSourceStateButton.getSelection()) && (sourceStateAttribute != null))
      {
         AttributeUtil.setAttribute(eventHandler, CarnotConstants.SOURCE_STATE_ATT, "");//$NON-NLS-1$
      }
   }
   
   public void setDelegateContainer(AbstractModelElementPropertyPage page)
   {
   }   
}