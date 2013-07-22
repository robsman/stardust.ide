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
package org.eclipse.stardust.modeling.core.spi.triggerTypes.jms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.extensions.jms.app.JMSLocation;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.util.NameIdUtils;
import org.eclipse.stardust.modeling.common.ui.jface.utils.FormBuilder;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledCombo;
import org.eclipse.stardust.modeling.common.ui.jface.utils.LabeledText;
import org.eclipse.stardust.modeling.common.ui.jface.widgets.LabelWithStatus;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.ui.TypeSelectionComposite;
import org.eclipse.stardust.modeling.core.properties.AbstractModelElementPropertyPage;
import org.eclipse.stardust.modeling.core.properties.IButtonManager;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.WidgetBindingManager;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class JMSTriggerAccessPointPropertyPage extends AbstractModelElementPropertyPage
{
   private static final String[][] LOCATION = {
         {JMSLocation.HEADER.getId(), "Header"}, {JMSLocation.BODY.getId(), "Body"}}; //$NON-NLS-1$ //$NON-NLS-2$

   private LabeledCombo locationCombo;

   private LabeledText idText;
   private LabeledText nameText;

   private AccessPointType accessPointType;

   private LabelWithStatus lblTypeName;

   private TypeSelectionComposite classTypeBrowser;
   private Button[] buttons;

   private ModifyListener idSyncListener = new ModifyListener()
   {
      public void modifyText(ModifyEvent e)
      {
         if (GenericUtils.getAutoIdValue())
         {
            String computedId = NameIdUtils.createIdFromName(null, getModelElement());            
            idText.getText().setText(computedId);
         }
      }
   };
   
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      nameText.getText().removeModifyListener(idSyncListener);

      WidgetBindingManager binding = getWidgetBindingManager();

      accessPointType = (AccessPointType) element;

      binding.bind(idText, accessPointType, CarnotWorkflowModelPackage.eINSTANCE
            .getIIdentifiableElement_Id());
      binding.bind(nameText, accessPointType, CarnotWorkflowModelPackage.eINSTANCE
            .getIIdentifiableElement_Name());

      binding.getValidationBindingManager().bind(accessPointType,
            PredefinedConstants.JMS_LOCATION_PROPERTY, locationCombo.getLabel());

      String location = AttributeUtil.getAttributeValue(accessPointType,
            PredefinedConstants.JMS_LOCATION_PROPERTY);
      if (location != null)
      {
         for (int i = 0; i < LOCATION.length; i++)
         {
            if (LOCATION[i][0].equals(location))
            {
               locationCombo.getCombo().select(i);
               break;
            }
         }
      }

      binding.bind(new LabeledText(classTypeBrowser.getText(), lblTypeName),
            accessPointType, PredefinedConstants.CLASS_NAME_ATT);

      nameText.getText().addModifyListener(idSyncListener);

      bindParameterMapping();
   }

   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
      AccessPointType accessPointType = (AccessPointType) element;
      int locationIndex = locationCombo.getCombo().getSelectionIndex();

      if (locationIndex < 0)
      {
         AttributeUtil.setAttribute(accessPointType,
               PredefinedConstants.JMS_LOCATION_PROPERTY, null);
      }
      else
      {
         AttributeUtil.setAttribute(accessPointType,
               PredefinedConstants.JMS_LOCATION_PROPERTY, JMSLocation.class.getName(),
               LOCATION[locationIndex][0]);
      }
   }

   private void bindParameterMapping()
   {
      if (!StringUtils.isEmpty(idText.getText().getText()))
      {
         for (Iterator iter = getParameter(idText.getText().getText(),
               (TriggerType) getModelElement().eContainer()).iterator(); iter.hasNext();)
         {
            getWidgetBindingManager().bind(idText, (ParameterMappingType) iter.next(),
                  PKG_CWM.getParameterMappingType_Parameter());
         }
      }
   }

   private List getParameter(String id, TriggerType trigger)
   {
      List parameters = new ArrayList();

      for (Iterator iter = trigger.getParameterMapping().iterator(); iter.hasNext();)
      {
         ParameterMappingType mapping = (ParameterMappingType) iter.next();
         if ((!StringUtils.isEmpty(id)) && (!StringUtils.isEmpty(mapping.getParameter())))
         {
            if (id.equals(mapping.getParameter()))
            {
               parameters.add(mapping);
            }
         }
      }
      return parameters;
   }

   public Control createBody(Composite parent)
   {
      Composite composite = FormBuilder.createComposite(parent, 2);
      this.locationCombo = FormBuilder.createLabeledCombo(composite,
            Diagram_Messages.LB_Location);
      for (int i = 0; i < LOCATION.length; i++)
      {
         locationCombo.getCombo().add(LOCATION[i][1]);
      }
      locationCombo.getCombo().addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            int locationIndex = locationCombo.getCombo().getSelectionIndex();
            if (locationIndex > -1)
            {
               AttributeUtil.setAttribute(accessPointType,
                     PredefinedConstants.JMS_LOCATION_PROPERTY, JMSLocation.class
                           .getName(), LOCATION[locationIndex][0]);
            }
         }
      });

      this.nameText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_Name);
      this.idText = FormBuilder.createLabeledText(composite, Diagram_Messages.LB_ID);
      
      boolean autoIdButtonValue = GenericUtils.getAutoIdValue();
      if(autoIdButtonValue)
      {
         idText.getText().setEditable(false);
      }
      
      this.lblTypeName = FormBuilder.createLabelWithRightAlignedStatus(composite,
            Diagram_Messages.LB_SPI_Type);
      this.classTypeBrowser = new TypeSelectionComposite(composite, Diagram_Messages.LB_SPI_Type);

      return composite;
   }

   public void setVisible(boolean visible)
   {
      if (visible)
      {
         bindParameterMapping();
         IButtonManager manager = (IButtonManager) getElement().getAdapter(
               IButtonManager.class);
         if (manager != null)
         {
            manager.updateButtons(getModelElement(), buttons);
         }
      }
      super.setVisible(visible);
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
}