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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.base.StringUtils;
import ag.carnot.workflow.model.PredefinedConstants;

public class MailTriggerValidator implements IModelElementValidator
{
   private static final String[] messages = {
         Validation_Messages.MSG_TIMERTRIGGER_UnspecifiedUsername,
         Validation_Messages.MSG_TIMERTRIGGER_UnspecifiedPassword,
         Validation_Messages.MSG_TIMERTRIGGER_UnspecifiedServerName,
         Validation_Messages.MSG_TIMERTRIGGER_UnspecifiedProtocol};

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, PredefinedConstants.MAIL_TRIGGER_USER_ATT)))
      {
         result.add(Issue.error(element, messages[0]));
      }
      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, PredefinedConstants.MAIL_TRIGGER_PASSWORD_ATT)))
      {
         result.add(Issue.error(element, messages[1]));
      }
      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, PredefinedConstants.MAIL_TRIGGER_SERVER_ATT)))
      {
         result.add(Issue.error(element, messages[2]));
      }
      if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
            (IExtensibleElement) element, PredefinedConstants.MAIL_TRIGGER_PROTOCOL_ATT)))
      {
         result.add(Issue.error(element, messages[3]));
      }
      // todo (fh): check access points

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}
