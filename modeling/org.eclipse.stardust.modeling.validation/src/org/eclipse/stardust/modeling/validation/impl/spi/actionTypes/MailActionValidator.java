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
package org.eclipse.stardust.modeling.validation.impl.spi.actionTypes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.model.xpdl.carnot.util.VariableContextHelper;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.base.StringUtils;

public class MailActionValidator implements IModelElementValidator
{
   private static final String MAIL_TYPE = "email"; //$NON-NLS-1$

   private static final String PARTICIPANT_TYPE = "participant"; //$NON-NLS-1$

   private static final String MAIL_ACTION_RECEIVER_TYPE_ATT = CarnotConstants.ENGINE_SCOPE
         + "receiverType"; //$NON-NLS-1$

   private static final String MAIL_ACTION_RECEIVER_ATT = CarnotConstants.ENGINE_SCOPE
         + "receiver"; //$NON-NLS-1$

   private static final String MAIL_ACTION_ADDRESS_ATT = CarnotConstants.ENGINE_SCOPE
         + "emailAddress"; //$NON-NLS-1$

   private static final String[] messages = {
         Validation_Messages.MSG_NoReceiverType,
         Validation_Messages.MSG_NoReceivingParticipant,
         Validation_Messages.MSG_NoEmailAddress,
         Validation_Messages.MailActionValidator_MSG_NoCorrectEmailAddress};

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List result = new ArrayList();

      String receiverType = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            MAIL_ACTION_RECEIVER_TYPE_ATT);

      if (StringUtils.isEmpty(receiverType))
      {
         result.add(Issue.warning(element, messages[0]));
      }
      else
      {
         if (receiverType.equals(PARTICIPANT_TYPE))
         {
            if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
                  (IExtensibleElement) element, MAIL_ACTION_RECEIVER_ATT)))
            {
               result.add(Issue.warning(element, messages[1]));
            }
         }
         else if (receiverType.equals(MAIL_TYPE))
         {
            if (StringUtils.isEmpty(AttributeUtil.getAttributeValue(
                  (IExtensibleElement) element, MAIL_ACTION_ADDRESS_ATT)))
            {
               result.add(Issue.warning(element, messages[2]));
            }
            else
            {
               String mailAddress = AttributeUtil.getAttributeValue(
                     (IExtensibleElement) element, MAIL_ACTION_ADDRESS_ATT);
               mailAddress = VariableContextHelper.getInstance().getContext(element)
                     .replaceAllVariablesByDefaultValue(mailAddress);
               if (!(checkFormat(mailAddress)))
               {
                  result.add(Issue.warning(element, messages[3]));
               }
            }
         }
      }
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }

   private boolean checkFormat(String mailAdress)
   {
      int atSignIdx = mailAdress.indexOf('@');
      int atSignLastIdx = mailAdress.lastIndexOf('@');
      int dotLastIdx = mailAdress.lastIndexOf("."); //$NON-NLS-1$

      boolean isCorrectAtSign = ((atSignIdx > 0) && (atSignIdx == atSignLastIdx));
      boolean isCorrectDot = atSignIdx > 0 ? (dotLastIdx > atSignIdx + 1)
            && (mailAdress.charAt(atSignIdx + 1) != '.' && mailAdress
                  .charAt(atSignIdx - 1) != '.') : false;
      boolean isCorrectSuffix = (dotLastIdx > 0 && dotLastIdx < mailAdress.length() - 1)
            ? (mailAdress.substring(dotLastIdx + 1).length() > 1 && mailAdress.substring(
                  dotLastIdx + 1).length() < 5)
            : false;

      return (!mailAdress.startsWith(".")) && isCorrectAtSign && isCorrectDot //$NON-NLS-1$
            && isCorrectSuffix;
   }

}
