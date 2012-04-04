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
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.utils.CwmFeatureAdapter;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


public class IdentifiablePropertyPage extends AbstractModelElementPropertyPage
{
   private static final String EMPTY = ""; //$NON-NLS-1$

   private Label stxtOid;

   protected LabeledText txtId;
   protected LabeledText txtName;

   protected LabeledText txtDescription;

   protected Button autoIdButton;
   
   protected Button publicCheckBox;

   protected boolean publicType;

   protected IModelElement modelElement;

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
      txtName.getText().removeModifyListener(listener);
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.getModelBindingManager().bind(element,
            PKG_CWM.getIModelElement_ElementOid(), stxtOid);
      wBndMgr.bind(txtId, element, PKG_CWM.getIIdentifiableElement_Id());
      wBndMgr.bind(txtName, element, PKG_CWM.getIIdentifiableElement_Name());
      
      if (element instanceof IIdentifiableModelElement)
      {
         wBndMgr.bind(txtDescription, element, (element instanceof ModelType) ? PKG_CWM
            .getModelType_Description() : PKG_CWM
            .getIIdentifiableModelElement_Description(), CwmFeatureAdapter.INSTANCE);
      }
      
      wBndMgr.getModelBindingManager().updateWidgets(element);
      txtName.getText().addModifyListener(listener);
      
      txtName.getText().selectAll();
      txtName.getText().setFocus();
      
      modelElement = element;
      setupVisibility();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      GenericUtils.setAutoIdValue(getModelElement(), autoIdButton.getSelection());
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);

      FormBuilder.createLabelWithRightAlignedStatus(composite, getOidLabel());
      this.stxtOid = FormBuilder.createLabel(composite, EMPTY);

      this.txtName = FormBuilder
            .createLabeledText(composite, Diagram_Messages.LB_Name);
      txtName.setTextLimit(80);
      this.txtId = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);
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
      
      contributeExtraControls(composite);
      if (getModelElement() instanceof IIdentifiableModelElement)
      {
         FormBuilder.createHorizontalSeparator(composite, 2);
         this.txtDescription = FormBuilder.createLabeledTextArea(composite,
            Diagram_Messages.TA_Description);
      }

      return composite;
   }

   protected String getOidLabel()
   {
      return Diagram_Messages.LB_ElementOID;
   }

   protected void contributeExtraControls(Composite composite)
   {
      publicCheckBox = FormBuilder.createCheckBox(composite,
            Diagram_Messages.CHECKBOX_Visibility, 2);
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
   
   protected void setupVisibility()
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

   protected String getId()
   {
      return txtId.getText().getText();
   }

   protected String getName()
   {
      return txtName.getText().getText();
   }
}