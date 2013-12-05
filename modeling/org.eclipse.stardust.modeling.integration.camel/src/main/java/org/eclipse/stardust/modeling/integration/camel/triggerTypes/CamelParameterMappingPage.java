/*******************************************************************************
* Copyright (c) 2012 SunGard CSA LLC and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    SunGard CSA LLC - initial API and implementation and/or initial documentation
*******************************************************************************/
package org.eclipse.stardust.modeling.integration.camel.triggerTypes;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdaptee;
import org.eclipse.stardust.modeling.core.editors.parts.NotificationAdapter;
import org.eclipse.stardust.modeling.core.spi.triggerTypes.ParameterMappingPage;

/**
* 
 * @author Florin.Herinean
* @version $Revision: $
*/
public class CamelParameterMappingPage extends ParameterMappingPage implements NotificationAdaptee
{
   private static final String BODY_ACCESS_POINT_ID = "message";
   
   private NotificationAdapter adapter = new NotificationAdapter(this);
   
   @Override
   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
      super.loadFieldsFromElement(symbol, element);
      if (!element.eAdapters().contains(adapter))
      {
         // (fh) we want to be notified when data is changed.
         element.eAdapters().add(adapter);
      }
      enablePathBrowser();
   }

   @Override
   public void dispose()
   {
      // (fh) remove the notifier when the dialog is disposed.
      getModelElement().eAdapters().remove(adapter);
      super.dispose();
   }
   
   /*****************************************
    * Implementation of NotificationAdaptee *
    *****************************************/

   @Override
   public Object getModel()
   {
      return getModelElement();
   }

   @Override
   public void handleNotification(Notification notification)
   {
      if (PKG_CWM.getParameterMappingType_Data().equals(notification.getFeature()))
      {
         // (fh) Data has changed
         ParameterMappingType mapping = (ParameterMappingType) getModelElement();
         TriggerType trigger = (TriggerType) mapping.eContainer();
         DataType data = mapping.getData();
         
         AccessPointType message = createBodyAccessPoint(trigger, data.getType());
         List<AttributeType> attributes = message.getAttribute();
         for (AttributeType attribute : data.getAttribute())
         {
            if (!PredefinedConstants.BROWSABLE_ATT.equals(attribute.getName()))
            {
               attributes.add((AttributeType) EcoreUtil.copy(attribute));
            }
         }
         
         // (fh) refresh page to consider the modified access point
         getWidgetBindingManager().dispose();
         elementChanged();

         // controls were reset
         enablePathBrowser();
      }
      else if (PKG_CWM.getParameterMappingType_Parameter().equals(notification.getFeature()))
      {
         // parameter has changed
         enablePathBrowser();
      }
   }

   private void enablePathBrowser()
   {
      ParameterMappingType mapping = (ParameterMappingType) getModelElement();
      String parameterId = mapping.getParameter();
      boolean enabled = parameterId != null && !parameterId.isEmpty() && !BODY_ACCESS_POINT_ID.equals(parameterId);
      dataPathBrowser.setEnabled(enabled);
      parameterPathBrowser.setEnabled(enabled);
   }

   static AccessPointType createBodyAccessPoint(TriggerType trigger, DataTypeType type)
   {
      List<AccessPointType> accessPoints = trigger.getAccessPoint();
      
      AccessPointType message = ModelUtils.findElementById(accessPoints, BODY_ACCESS_POINT_ID);
      if (message == null)
      {
         // (fh) create new access point
         message = AccessPointUtil.createAccessPoint(BODY_ACCESS_POINT_ID, BODY_ACCESS_POINT_ID,
               DirectionType.OUT_LITERAL, type);
         accessPoints.add(message);
      }
      else
      {
         // (fh) update existing access point
         message.setType(type);
         message.getAttribute().clear();
      }
      AttributeUtil.setBooleanAttribute(message, PredefinedConstants.BROWSABLE_ATT, Boolean.FALSE);
      
      return message;
   }
}
