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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.ui.StringUtils;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ApplicationRetrySynchronousPropertyPage extends AbstractModelElementPropertyPage
{
   public static final String CAMEL_PRODUCER_APPLICATION_TYPE = "camelSpringProducerApplication";
   
   protected LabeledText number;
   protected LabeledText time;
   protected Button retryApplicationButton;
   protected Button retryButton;
      
   private SelectionListener retryListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {
      }

      public void widgetSelected(SelectionEvent e)
      {
         boolean selection = ((Button) e.widget).getSelection();
         if(selection)
         {
            retryApplicationButton.setEnabled(true);
            retryApplicationButton.setGrayed(false);
            number.getText().setEditable(true);
            time.getText().setEditable(true);
            
            ApplicationTypeType type = ((ApplicationType) getModel()).getType();
            if(type != null)
            {
               if(type.getId().equals(CAMEL_PRODUCER_APPLICATION_TYPE))
               {
                  retryApplicationButton.setEnabled(false);            
                  retryApplicationButton.setSelection(true);
               }            
            }         
         }
         else
         {
            retryApplicationButton.setEnabled(false);            
            retryApplicationButton.setGrayed(true);            
            retryApplicationButton.setSelection(false);
            number.getText().setEditable(false);
            number.getText().setText(""); //$NON-NLS-1$
            time.getText().setEditable(false);
            time.getText().setText(""); //$NON-NLS-1$            
         } 
      }
   };            
   
   private ModifyListener numberListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         boolean isValid = validateNumber();         
         if(!isValid)
         {         
            setErrorMessage(Diagram_Messages.ERROR_MSG_VALUE_MUST_BE_NUMERIC_BETWEEN_1_AND_10);
            setValid(false);
         }
         else
         {
            if(validate())
            {
               setValid(true);
               setErrorMessage(null);
            }
         }
      }
   };

   private ModifyListener timeListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         boolean isValid = validateTime();         
         if(!isValid)
         {         
            setErrorMessage(Diagram_Messages.ERROR_MSG_VALUE_MUST_BE_NUMERIC_BETWEEN_1_AND_60);            
            setValid(false);
         }
         else
         {
            if(validate())
            {
               setValid(true);
               setErrorMessage(null);
            }
         }
      }
   };
   
   private boolean validate()
   {      
      return validateNumber() && validateTime();
   }

   private boolean validateNumber()
   {
      String numberValue = number.getText().getText();
      if(StringUtils.isEmpty(numberValue))
      {
         return true;
      }
      
      try
      {
         int value = Integer.parseInt(numberValue);
         if(value >= 1 && value <= 10)
         {
            return true;
         }
      }
      catch (NumberFormatException e)
      {
      }      
      return false;
   }
   
   private boolean validateTime()
   {
      String timeValue = time.getText().getText();
      if(StringUtils.isEmpty(timeValue))
      {
         return true;
      }
      
      try
      {
         int value = Integer.parseInt(timeValue);
         if(value >= 1 && value <= 60)
         {
            return true;
         }
      }
      catch (NumberFormatException e)
      {
      }      
      return false;
   }
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      number.getText().removeModifyListener(numberListener);
      time.getText().removeModifyListener(timeListener);
      retryButton.removeSelectionListener(retryListener);
      
      AttributeType numberAttribute = AttributeUtil.getAttribute((ApplicationType) element,
            PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_NUMBER);
      if(numberAttribute != null)
      {
         number.getText().setText(numberAttribute.getValue());
      }
      
      AttributeType timeAttribute = AttributeUtil.getAttribute((ApplicationType) element,
            PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_TIME);
      if(timeAttribute != null)
      {
         time.getText().setText(timeAttribute.getValue());
      }

      AttributeType retryApplicationAttribute = null;
      if(retryApplicationAttribute != null && retryApplicationAttribute.getValue().equals(PredefinedConstants.APPLICATION_CONTEXT))
      {
         retryApplicationButton.setSelection(true);
      }      

      AttributeType retryAttribute = AttributeUtil.getAttribute((ApplicationType) element,
            PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_ENABLE);
      if(retryAttribute != null && AttributeUtil.getBooleanValue(retryAttribute))
      {
         retryButton.setSelection(AttributeUtil.getBooleanValue(retryAttribute));
      }      
      else
      {
         retryApplicationButton.setEnabled(false);            
         retryApplicationButton.setGrayed(true);            
         number.getText().setEditable(false);
         time.getText().setEditable(false);
      }
      
      number.getText().addModifyListener(numberListener);
      time.getText().addModifyListener(timeListener);
      retryButton.addSelectionListener(retryListener);      
      
      ApplicationTypeType type = ((ApplicationType) element).getType();
      if(type != null)
      {
         if(type.getId().equals(CAMEL_PRODUCER_APPLICATION_TYPE)
               && retryAttribute != null && AttributeUtil.getBooleanValue(retryAttribute))
         {
            retryApplicationButton.setEnabled(false);            
            retryApplicationButton.setSelection(true);
         }            
      }               
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      // in case apply button is pressed with invalid values
      if(validate())
      {
         AttributeUtil.setAttribute((IExtensibleElement) element, PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_NUMBER, number.getText().getText());
         AttributeUtil.setAttribute((IExtensibleElement) element, PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_TIME, time.getText().getText());
         AttributeUtil.setBooleanAttribute((IExtensibleElement) element, PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_ENABLE, retryButton.getSelection());
         if(retryApplicationButton.getSelection())
         {
            //AttributeUtil.setAttribute((IExtensibleElement) element, PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_RESPONSIBILITY, PredefinedConstants.APPLICATION_CONTEXT);         
         }
         else
         {
            //AttributeUtil.setAttribute((IExtensibleElement) element, PredefinedConstants.SYNCHRONOUS_APPLICATION_RETRY_RESPONSIBILITY, null);
         }
      }
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);

      retryButton = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_APPLICATION_RETRY_ENABLE, 2);            
      retryApplicationButton = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_APPLICATION_RETRY_APPLICATION, 2);                  
      number = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_APPLICATION_RETRY_NUMBER);
      number.setTextLimit(2);
      time = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_APPLICATION_RETRY_TIME);
      time.setTextLimit(2);      

      return composite;
   }
   
   private IModelElement getModel()
   {
      IModelElement element = null;
      IAdaptable adaptable = getElement();
      if (adaptable != null)
      {
         element = (IModelElement) adaptable.getAdapter(IModelElement.class);
         if (element instanceof IModelElementNodeSymbol)
         {
            element = ((IModelElementNodeSymbol) element).getModelElement();
         }
      }
      return element;
   }   
}