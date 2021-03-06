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
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils.EObjectInvocationHandler;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
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
   
   private Button publicCheckBox;

   private boolean publicType;

   protected IIdentifiableModelElement modelElement;
   
   private Boolean providesVisibility = false;

   private ModifyListener listener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         onNameUpdate(GenericUtils.getAutoIdValue(), ((Text) e.widget).getText());
      }
   };

   protected void onNameUpdate(boolean autoFillEnabled, String name)
   {
      if (autoFillEnabled)
      {
         txtId.getText().setEditable(false);
         
         String computedId = NameIdUtils.createIdFromName(null, getModelElement());
         txtId.getText().setText(computedId);            
      }
      else
      {
         txtId.getText().setEditable(true);            
      }
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      EObject bindElement = element;
      if (bindElement instanceof Proxy && bindElement instanceof ModelType)
      {
         Proxy proxy = (Proxy) bindElement;
         InvocationHandler ih = Proxy.getInvocationHandler(proxy);
         if (ih instanceof EObjectInvocationHandler)
         {
            EObject model = ((EObjectInvocationHandler) ih).getModel();
            if(model instanceof ModelType)
            {
               bindElement = model;
            }                     
         }
      }
      
      txtName.getText().removeModifyListener(listener);
      WidgetBindingManager wBndMgr = getWidgetBindingManager();

      wBndMgr.getModelBindingManager().bind(bindElement,
            PKG_CWM.getIModelElement_ElementOid(), stxtOid);
      wBndMgr.bind(txtId, bindElement, PKG_CWM.getIIdentifiableElement_Id());
      wBndMgr.bind(txtName, bindElement, PKG_CWM.getIIdentifiableElement_Name());
      
      if (element instanceof IIdentifiableModelElement)
      {
         wBndMgr.bind(txtDescription, bindElement, (element instanceof ModelType) ? PKG_CWM
            .getModelType_Description() : PKG_CWM
            .getIIdentifiableModelElement_Description(), CwmFeatureAdapter.INSTANCE);
      }
      
      wBndMgr.getModelBindingManager().updateWidgets(bindElement);
      txtName.getText().addModifyListener(listener);
      
      txtName.getText().selectAll();
      txtName.getText().setFocus();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
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
      
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue();
      
      if(autoIdButtonValue)
      {
         txtId.getText().setEditable(false);
      }
      
      contributeExtraControls(composite);
      if (getModelElement() instanceof IIdentifiableModelElement)
      {
         FormBuilder.createHorizontalSeparator(composite, 2);
         this.txtDescription = FormBuilder.createLabeledTextArea(composite,
            Diagram_Messages.TA_Description);
         
         modelElement = (IIdentifiableModelElement) getModelElement();
      }
      
      if (providesVisibility)
      {
         setupVisibility();
      }

      return composite;
   }

   protected String getOidLabel()
   {
      return Diagram_Messages.LB_ElementOID;
   }

   protected void contributeExtraControls(Composite composite)
   {
      if (providesVisibility)
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
   }

   protected String getId()
   {
      return txtId.getText().getText();
   }

   protected String getName()
   {
      return txtName.getText().getText();
   }
   
   protected void setupVisibility()
   {
      AttributeType visibility = AttributeUtil.getAttribute(
            (IExtensibleElement) getModelElement(),
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
   
   protected void setProvidesVisibility(boolean flag)
   {
      providesVisibility = flag;
   }
}