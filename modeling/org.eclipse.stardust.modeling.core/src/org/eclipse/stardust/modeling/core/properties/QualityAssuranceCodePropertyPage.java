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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.common.reflect.Reflect;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.utils.CwmFeatureAdapter;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class QualityAssuranceCodePropertyPage extends AbstractModelElementPropertyPage
{
   protected LabeledText txtId;
   protected LabeledText txtName;

   protected LabeledText txtDescription;

   protected Button autoIdButton;

   private Button[] buttons;
   
   public void setVisible(boolean visible)
   {
      if (visible)
      {
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         if (manager != null)
         {
            manager.updateButtons(getModelElement(), buttons);
         }
      }
      super.setVisible(visible);
   }   
   
   protected EObject getModelElement()
   {
      EObject modelElement = super.getModelElement();
      if (modelElement instanceof Proxy)
      {
         Proxy proxy = (Proxy) modelElement;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         
         Object value = Reflect.getFieldValue(ih, "val$element"); //$NON-NLS-1$
         if (value != null)
         {
            modelElement = (Code) value;
         }
      }
      return modelElement;      
   }

   public void contributeVerticalButtons(Composite parent)
   {
      IButtonManager manager = (IButtonManager) getElement().getAdapter(
            IButtonManager.class);
      if (manager != null)
      {
         buttons = manager.createButtons(parent);
      }
   }

   private SelectionListener autoIdListener = new SelectionListener()
   {
      public void widgetDefaultSelected(SelectionEvent e)
      {
      }

      public void widgetSelected(SelectionEvent e)
      {
         onNameUpdate(((Button) e.widget).getSelection(), txtName.getText().getText());         
      }
   };         
   
   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         onNameUpdate(autoIdButton.getSelection(), ((Text) e.widget).getText());
      }
   };

   protected void onNameUpdate(boolean autoFillEnabled, String name)
   {
      if (autoFillEnabled)
      {
         txtId.getText().setEditable(false);
         String computedId = ModelUtils.computeId(name);
         txtId.getText().setText(computedId);            
      }
      else
      {
         txtId.getText().setEditable(true);            
      }
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      Code code = (Code) element;
      
      txtName.getText().removeModifyListener(listener);
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      if (code instanceof Proxy)
      {
         Proxy proxy = (Proxy) code;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         
         Object value = Reflect.getFieldValue(ih, "val$element"); //$NON-NLS-1$
         if (value != null)
         {
            code = (Code) value;
         }
      }
      
      wBndMgr.bind(txtId, code, PKG_CWM.getCode_Code());
      wBndMgr.bind(txtName, code, PKG_CWM.getCode_Name());
      
      wBndMgr.bind(txtDescription, code, 
            PKG_CWM.getCode_Value(), CwmFeatureAdapter.INSTANCE);
      
      wBndMgr.getModelBindingManager().updateWidgets(code);
      txtName.getText().addModifyListener(listener);
      
      txtName.getText().selectAll();
      txtName.getText().setFocus();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      GenericUtils.setAutoIdValue(getModelElement(), autoIdButton.getSelection());
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);

      this.txtName = FormBuilder
            .createLabeledText(composite, Diagram_Messages.LB_Name);
      txtName.setTextLimit(80);
      this.txtId = FormBuilder.createLabeledText(composite, Diagram_Messages.QUALITY_CONTROL_CODE);
      txtId.setTextLimit(80);      
      
      autoIdButton = FormBuilder.createCheckBox(composite,
            Diagram_Messages.BTN_AutoId, 2);
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue(getModelElement());
      
      autoIdButton.setSelection(autoIdButtonValue);
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }
      autoIdButton.addSelectionListener(autoIdListener);
      
      FormBuilder.createHorizontalSeparator(composite, 2);
      this.txtDescription = FormBuilder.createLabeledTextArea(composite,
         Diagram_Messages.TA_Description);
      
      return composite;
   }

   protected String getId()
   {
      return txtId.getText().getText();
   }

   protected String getName()
   {
      return txtName.getText().getText();
   }
}