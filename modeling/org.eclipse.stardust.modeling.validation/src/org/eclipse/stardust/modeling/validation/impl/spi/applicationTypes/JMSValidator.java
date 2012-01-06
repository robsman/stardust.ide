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
package org.eclipse.stardust.modeling.validation.impl.spi.applicationTypes;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.JavaDataTypeUtils;

public class JMSValidator implements IModelElementValidator
{

   private static final String IN = "in"; //$NON-NLS-1$

   private static final String INOUT = "inout"; //$NON-NLS-1$

   private static final String OUT = "out"; //$NON-NLS-1$

   private static final String[] messages = {
         Validation_Messages.MSG_PropertyNotSet, Validation_Messages.MSG_ParameterHasNoId,
         Validation_Messages.MSG_NoLocationForParameter,
         Validation_Messages.MSG_NoValidTypeForParameter,
         Validation_Messages.MSG_DuplicateIdUsed, Validation_Messages.MSG_PropertyNotSet};

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();
      String key = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.TYPE_ATT);
      if (key == null)
      {
         result.add(Issue.error(element, MessageFormat.format(messages[0],
               new String[] {PredefinedConstants.TYPE_ATT}), PredefinedConstants.TYPE_ATT));
      }
      else if (key.equalsIgnoreCase(OUT) || key.equalsIgnoreCase(INOUT))
      {
         result.addAll(checkProperty(element,
               PredefinedConstants.QUEUE_CONNECTION_FACTORY_NAME_PROPERTY));
         result.addAll(checkProperty(element, PredefinedConstants.QUEUE_NAME_PROPERTY));
         result.addAll(checkProperty(element,
               PredefinedConstants.MESSAGE_PROVIDER_PROPERTY));
         result.addAll(checkProperty(element,
               PredefinedConstants.REQUEST_MESSAGE_TYPE_PROPERTY));
         // @todo (ub)
         /*
          * if (getAllInAccessPoints().hasNext() == false) { inconsistencies.add(new
          * Inconsistency("Application '" + application.getId() + "' doesnt have any
          * parameters set for JMS Request type." , application, Inconsistency.WARNING)); }
          */
      }
      else if (key.equalsIgnoreCase(IN) || key.equalsIgnoreCase(INOUT))
      {
         result.addAll(checkProperty(element,
               PredefinedConstants.MESSAGE_ACCEPTOR_PROPERTY));
         result.addAll(checkProperty(element,
               PredefinedConstants.RESPONSE_MESSAGE_TYPE_PROPERTY));
         // @todo (ub)
         /*
          * if (application.getAllOutAccessPoints().hasNext() == false) {
          * inconsistencies.add(new Inconsistency("Application '" + application.getId() + "'
          * doesnt have any parameters set for JMS Response type." , application,
          * Inconsistency.WARNING)); }
          */
      }
      ArrayList ids = new ArrayList();
      ApplicationType application = (ApplicationType) element;
      EList accessPoints = application.getAccessPoint();
      for (Iterator iter = accessPoints.iterator(); iter.hasNext();)
      {
         AccessPointType ap = (AccessPointType) iter.next();
         
         if (StringUtils.isEmpty(ap.getId()))
         {
            result.add(Issue.error(ap, messages[1],
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }
         else if(!ModelUtils.isValidId(ap.getId()))
         {
            result.add(Issue.warning(ap,
                  MessageFormat.format(Validation_Messages.ERR_ELEMENT_InvalidId,
                        new Object[] {ap.getId()}),      
                        ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
         }         
         else
         {
            String idKey = ap.getDirection().getName() + ":" + ap.getId(); //$NON-NLS-1$
            if (ids.contains(idKey))
            {
               result.add(Issue.error(ap, MessageFormat.format(messages[4],
                     new String[] {ap.getName()}),
                     ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
            }
            ids.add(idKey);
         }

         if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(ap,
               PredefinedConstants.JMS_LOCATION_PROPERTY)))
         {
            result.add(Issue.error(ap, MessageFormat.format(
                  messages[2], new String[] {ap
                        .getName()}), PredefinedConstants.JMS_LOCATION_PROPERTY));
         }
         String className = AttributeUtil.getAttributeValue(ap,
               PredefinedConstants.CLASS_NAME_ATT);
         
         if (JavaDataTypeUtils.getTypeFromCurrentProject(className) == null)
         {
            result.add(Issue.error(ap, MessageFormat.format(messages[3], new String[] {
                  ap.getName(), className}), PredefinedConstants.CLASS_NAME_ATT));
         }
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private List checkProperty(IModelElement element, String name)
   {
      List result = new ArrayList();
      Object property = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            name);
      if (property == null || property.toString().trim().length() == 0)
      {
         result.add(Issue.error(element, MessageFormat.format(
               messages[5], new String[] {name}), name));
      }
      return result;
   }
}