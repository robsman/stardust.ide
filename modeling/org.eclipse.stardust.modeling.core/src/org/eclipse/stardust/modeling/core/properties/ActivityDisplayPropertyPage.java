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
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.merge.MergeUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ActivityDisplayPropertyPage extends AbstractModelElementPropertyPage
{
   private ActivityType activity;
   
   private Button auxiliary;
   private boolean defaultSelection;
   private AttributeType auxiliaryAttr;
   
   protected void performDefaults()
   {
      super.performDefaults();
      auxiliary.setSelection(defaultSelection);
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      activity = getActivity();
      auxiliaryAttr = AttributeUtil.getAttribute(activity, PredefinedConstants.ACTIVITY_IS_AUXILIARY_ATT);
      if(activity.getImplementation().equals(ActivityImplementationType.ROUTE_LITERAL))
      {
         if(auxiliaryAttr == null)
         {
            defaultSelection = true;
            auxiliary.setSelection(true);            
         }
         else
         {
            defaultSelection = false;
            auxiliary.setSelection(false);            
         }
      }
      else
      {
         if(auxiliaryAttr == null)
         {
            defaultSelection = false;
            auxiliary.setSelection(false);
         }
         else
         {
            defaultSelection = true;
            auxiliary.setSelection(true);            
         }         
      }
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {}

   public void apply()
   {
      super.apply();
      auxiliaryAttr = AttributeUtil.getAttribute(activity, PredefinedConstants.ACTIVITY_IS_AUXILIARY_ATT);
      if(activity.getImplementation().equals(ActivityImplementationType.ROUTE_LITERAL))
      {
         if(auxiliaryAttr == null)
         {
            defaultSelection = true;
         }
         else
         {
            defaultSelection = false;
         }
      }
      else
      {
         if(auxiliaryAttr == null)
         {
            defaultSelection = false;
         }
         else
         {
            defaultSelection = true;
         }         
      }
   }

   private ActivityType getActivity()
   {
      return (ActivityType) getModelElement();
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createLabeledControlsComposite(parent);
      
      auxiliary = FormBuilder.createCheckBox(composite, Diagram_Messages.LB_ACTIVITY_IS_AUXILIARY, 1);
      auxiliary.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            boolean currentSelection = auxiliary.getSelection();
            if(activity.getImplementation().equals(ActivityImplementationType.ROUTE_LITERAL))
            {
               // delete
               if(currentSelection == true)
               {                  
                  if(auxiliaryAttr != null)
                  {
                     MergeUtils.deleteElement(auxiliaryAttr, null);                     
                  }
               }
               else
               {
                  AttributeUtil.setBooleanAttribute((IExtensibleElement) activity, PredefinedConstants.ACTIVITY_IS_AUXILIARY_ATT, false);
                  auxiliaryAttr = AttributeUtil.getAttribute(activity, PredefinedConstants.ACTIVITY_IS_AUXILIARY_ATT);
               }
            }
            else
            {
               // create
               if(currentSelection == true)
               {                  
                  AttributeUtil.setBooleanAttribute((IExtensibleElement) activity, PredefinedConstants.ACTIVITY_IS_AUXILIARY_ATT, true);
                  auxiliaryAttr = AttributeUtil.getAttribute(activity, PredefinedConstants.ACTIVITY_IS_AUXILIARY_ATT);
               }
               else
               {
                  if(auxiliaryAttr != null)
                  {
                     MergeUtils.deleteElement(auxiliaryAttr, null);                     
                  }
               }
            }
         }
      });            
      return composite;
   }
}