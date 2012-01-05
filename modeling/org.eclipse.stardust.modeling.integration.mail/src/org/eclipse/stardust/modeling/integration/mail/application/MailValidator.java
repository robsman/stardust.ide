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
package org.eclipse.stardust.modeling.integration.mail.application;

import java.util.Iterator;
import java.util.List;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.engine.extensions.mail.utils.MailValidationUtils;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.integration.mail.MailConstants;
import org.eclipse.stardust.modeling.integration.mail.Mail_Messages;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

import ag.carnot.workflow.model.PredefinedConstants;

/**
 * 
 * @author mgille
 */
public class MailValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();

      if (element instanceof ApplicationType)
      {
         ApplicationType application = (ApplicationType) element;

         checkProperty(application, PredefinedConstants.FROM_ADDRESS,
               MailConstants.DEFAULT_MAIL_FROM, Mail_Messages.TXT_NO_SENDER_MAIL_ADDRESS_SPECIFIED,
               result);
         checkProperty(application, PredefinedConstants.TO_ADDRESS,
               MailConstants.DEFAULT_MAIL_TO, Mail_Messages.TXT_NO_RECEIVER_MAIL_ADDRESS_SPECIFIED,
               result);
         checkProperty(application, PredefinedConstants.MAIL_SERVER,
               MailConstants.DEFAULT_MAIL_SERVER, Mail_Messages.TXT_NO_MAIL_SERVER_SPECIFIED,
               result);
         
         validateMailAddress(application, MailConstants.DEFAULT_MAIL_FROM, result);
         validateMailAddress(application, MailConstants.DEFAULT_MAIL_TO, result);
         validateMailAddress(application, MailConstants.DEFAULT_MAIL_BCC, result);
         validateMailAddress(application, MailConstants.DEFAULT_MAIL_CC, result);
      }

      /*
       * String defaultTextTemplate = AttributeUtil.getAttributeValue(
       * (IExtensibleElement) element, MailConstants.PLAIN_TEXT_TEMPLATE); if
       * (StringUtils.isEmpty(defaultTextTemplate)) { result.add(Issue.warning(element,
       * MailMessages.MSG_FailedLoadingSQLDef,
       * MailConstants.DEFAULT_PLAIN_TEXT_TEMPLATE)); }
       */
      return result.toArray(Issue.ISSUE_ARRAY);
   }

   private void checkProperty(ApplicationType application, String accessPoint,
         String att, String msg, List<Issue> result)
   {
      List<ActivityType> executedActivities = application.getExecutedActivities();
      boolean hasAccessPoints = false;
      for (ActivityType activity : executedActivities)
      {
         hasAccessPoints = false;
         if (ActivityUtil.isApplicationActivity(activity))
         {
            List<DataMappingType> dataMappings = ActivityUtil.getDataMappings(activity, true,
                  PredefinedConstants.APPLICATION_CONTEXT);
            hasAccessPoints = dataMappings.isEmpty() ? false : hasAccessPoints;
            for (DataMappingType dataMapping : dataMappings)
            {
               if (accessPoint.equals(dataMapping.getApplicationAccessPoint()))
               {
                  hasAccessPoints = true;
               }
            }
            if (!hasAccessPoints)
            {
               break;
            }
         }
      }

      if (!hasAccessPoints
            && StringUtils.isEmpty(AttributeUtil.getAttributeValue(application, att)))
      {
         result.add(Issue.error(application, msg, att));
      }
   }
   
   private void validateMailAddress(ApplicationType application, String att, List<Issue> result)
   {
      boolean isValid = true;
      String mailList = AttributeUtil.getAttributeValue(application, att);
      if (mailList != null)
      {
         mailList = VariableContextHelper.getInstance().getContext(
               (ModelType) application.eContainer()).replaceAllVariablesByDefaultValue(
               mailList);
      }

      for (Iterator<String> i = StringUtils.split(mailList, ';'); i.hasNext();)
      {
         String mail = i.next();
         if (!StringUtils.isEmpty(mail) && !MailValidationUtils.isValidEMail(mail))
         {
            isValid = false;
         }
      }
      if (!isValid)
      {
         result.add(Issue.warning(application, Mail_Messages.TXT_INVALID_MAIL_ADRESS, att));
      }
   }
}