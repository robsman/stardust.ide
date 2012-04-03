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

import java.util.Iterator;

import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ElUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.xpdl2.ScriptType;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.common.ui.jface.databinding.EObjectAdapter;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

public class DataPropertyPage extends IdentifiablePropertyPage
{
   private Button publicCheckBox;

   private boolean publicType;

   private IModelElement modelElement;

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol,
         final IModelElement element)
   {
      setEditableText(((DataType) element).isPredefined());

      super.loadFieldsFromElement(symbol, element);

      ModelType model = ModelUtils.findContainingModel(element);
      modelElement = element;
      final String dataId = ((DataType) element).getId();

      if (dataId != null)
      {
         bindTransitionConditions(model, dataId);
      }      
      setupVisibility();
      refreshTree();
   }


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

   private void bindTransitionConditions(ModelType model, final String dataId)
   {      
      final ModelType modelType = model;
      for (Iterator iter = model.getProcessDefinition().iterator(); iter.hasNext();)
      {
         for (Iterator iterator = ((ProcessDefinitionType) iter.next()).getTransition()
               .iterator(); iterator.hasNext();)
         {
            final TransitionType transition = (TransitionType) iterator.next();
            final String expression = transition.getExpression() == null
                  ? null
                  : ModelUtils.getCDataString(transition.getExpression().getMixed());
            if (expression != null && expression.indexOf(dataId) >= 0)
            {
               getWidgetBindingManager().bind(
                     txtId,
                     new EObjectAdapter(transition, PKG_CWM
                           .getTransitionType_Expression())
                     {
                        private String oldValue = dataId;

                        public void updateModel(Object value)
                        {
                           String newValue = value.toString();
                           String oldExpression = ModelUtils.getCDataString(transition
                                 .getExpression().getMixed());
                           if (oldExpression != null)
                           {
                              try
                              {                                                                  
                                 String newExpression = ""; //$NON-NLS-1$
                                 ScriptType script = modelType.getScript();
                                 if (script == null || StringUtils.isEmpty(script.getType())
                                       || script.getType().equals("text/carnotEL")) //$NON-NLS-1$
                                 {
                                    newExpression = ElUtils.patchExpressions(oldExpression, oldValue, newValue);
                                 } else {
                                    newExpression = ElUtils.patchJsExpressions(oldExpression, oldValue, newValue);  
                                 }                                
                                 ModelUtils.setCDataString(transition.getExpression()
                                       .getMixed(), newExpression);
                                 oldValue = newValue;
                              }
                              catch (Throwable e)
                              {
                                 // TODO (fh) display error message: unable to update transition
                                 // expression due to syntax errors
                                 // e.printStackTrace();
                              }
                           }
                        }

                        public Object getValue()
                        {
                           return oldValue;
                        }
                     });
            }
         }
      }
   }

   private void setEditableText(boolean isPredefined)
   {
      if(isPredefined)
      {
         txtId.getText().setEditable(!isPredefined);
         txtName.getText().setEditable(!isPredefined);
         autoIdButton.setEnabled(false);
         txtDescription.getText().setEditable(!isPredefined);
      }
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