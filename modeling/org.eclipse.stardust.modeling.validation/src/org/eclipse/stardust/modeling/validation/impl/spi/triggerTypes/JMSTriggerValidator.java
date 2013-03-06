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
package org.eclipse.stardust.modeling.validation.impl.spi.triggerTypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;

public class JMSTriggerValidator implements IModelElementValidator
{
   private static final String[] messages = {
         Validation_Messages.MSG_JMSTRIGGER_UnspecifiedMessageType,
         Validation_Messages.MSG_JMSTRIGGER_ParameterNoId,
         Validation_Messages.MSG_JMSTRIGGER_NoLocationSpecified,
         Validation_Messages.MSG_JMSTRIGGER_NoValidTypeForParameter};

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, PredefinedConstants.MESSAGE_TYPE_ATT)))
      {
         result.add(Issue.error(element, messages[0], PredefinedConstants.MESSAGE_TYPE_ATT));
      }
      TriggerType trigger = (TriggerType) element;
      EList accessPointList = trigger.getAccessPoint();
      for (Iterator i = accessPointList.iterator(); i.hasNext();)
      {
         AccessPointType accessPoint = (AccessPointType) i.next();
         if (StringUtils.isEmpty(accessPoint.getId()))
         {
            result.add(Issue.warning(accessPoint, messages[1],
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }
         else if(!ModelUtils.isValidId(accessPoint.getId()))
         {
            result.add(Issue.warning(accessPoint,
                  MessageFormat.format(Validation_Messages.ERR_ELEMENT_InvalidId,
                        new Object[] {accessPoint.getId()}),      
                        ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }         
         
         if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(accessPoint,
               PredefinedConstants.JMS_LOCATION_PROPERTY)))
         {
            result.add(Issue.warning(accessPoint, MessageFormat.format(messages[2],
                  new String[] {accessPoint.getId()}),
                  PredefinedConstants.JMS_LOCATION_PROPERTY));
         }

         String clazz = AttributeUtil.getAttributeValue(accessPoint,
               PredefinedConstants.CLASS_NAME_ATT);
         clazz = VariableContextHelper.getInstance().getContext(
               (ModelType) element.eContainer().eContainer()).replaceAllVariablesByDefaultValue(clazz);
         if (JavaDataTypeUtils.getTypeFromCurrentProject(clazz) == null)
         {
            result.add(Issue.warning(accessPoint, MessageFormat.format(messages[3],
                  new String[] {accessPoint.getName(), clazz}),
                  PredefinedConstants.CLASS_NAME_ATT));
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}