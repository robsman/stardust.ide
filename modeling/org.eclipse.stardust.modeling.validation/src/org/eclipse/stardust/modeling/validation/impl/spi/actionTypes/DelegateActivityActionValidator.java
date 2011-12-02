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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.base.StringUtils;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.spi.providers.actions.delegate.TargetWorklist;

/**
 * @author rsauer
 * @version $Revision$
 */
public class DelegateActivityActionValidator implements IModelElementValidator
{

   public Issue[] validate(IModelElement element) throws ValidationException
   {
      // TODO Auto-generated method stub
      List result = new ArrayList();

      if (element instanceof EventActionType)
      {
         EventActionType action = (EventActionType) element;

         String targetWorklist = AttributeUtil.getAttributeValue(action,
               PredefinedConstants.TARGET_WORKLIST_ATT);
         if (TargetWorklist.Participant.getId().equals(targetWorklist))
         {
            String participantId = AttributeUtil.getAttributeValue(action,
                  PredefinedConstants.TARGET_PARTICIPANT_ATT);
            if (StringUtils.isEmpty(participantId))
            {
               result.add(Issue.error(action,
                     Validation_Messages.DelegateActivity_MissingParticipantId,
                     PredefinedConstants.TARGET_PARTICIPANT_ATT));
            }
            else
            {
               ModelType model = ModelUtils.findContainingModel(action);
               if (null != model)
               {
                  IModelParticipant participant = (IModelParticipant) ModelUtils.findIdentifiableElement(
                        model.getRole(), participantId);
                  if (null == participant)
                  {
                     participant = (IModelParticipant) ModelUtils.findIdentifiableElement(
                           model.getOrganization(), participantId);
                  }
                  if (null == participant)
                  {
                     participant = (IModelParticipant) ModelUtils.findIdentifiableElement(
                           model.getConditionalPerformer(), participantId);
                  }
                  if (null == participant)
                  {
                     result.add(Issue.error(action, MessageFormat.format(
                           Validation_Messages.DelegateActivity_InvalidParticipantId,
                           new Object[] {participantId}),
                           PredefinedConstants.TARGET_PARTICIPANT_ATT));
                  }
               }
            }
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}
