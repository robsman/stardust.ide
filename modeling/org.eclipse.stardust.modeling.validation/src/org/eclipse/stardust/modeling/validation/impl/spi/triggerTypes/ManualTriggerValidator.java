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
import java.util.HashSet;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ScopeUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.Validation_Messages;

import ag.carnot.workflow.model.PredefinedConstants;

public class ManualTriggerValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();

      ModelType model = ModelUtils.findContainingModel(element);
      String participantId = AttributeUtil.getAttributeValue((IExtensibleElement) element,
            PredefinedConstants.PARTICIPANT_ATT);

      String typeId = null;
      if (element instanceof TriggerType)
      {
         IMetaType metaType = ((TriggerType) element).getMetaType();
         if (metaType != null)
         {
            typeId = metaType.getId();
         }
      }

      if (participantId == null)
      {
         result.add(Issue.error(element, MessageFormat.format(Validation_Messages.MSG_Trigger_UnspecifiedParticipant,
               typeId), PredefinedConstants.PARTICIPANT_ATT));
      }
      else if (null != model)
      {
         @SuppressWarnings("unchecked")
         IModelParticipant participant = ModelUtils.findParticipant(participantId, model.getRole(), model.getOrganization());
         if (participant == null)
         {
            result.add(Issue.error(element, MessageFormat.format(Validation_Messages.MSG_Trigger_InvalidParticipant,
                  participantId, typeId), PredefinedConstants.PARTICIPANT_ATT));
         }
         else
         {
            HashSet<IModelParticipant> scoped = ScopeUtils.findScopedParticipants(model);            
            // is scoped participant?
            if(scoped.contains(participant))
            {
               if(!ScopeUtils.isValidScopedParticipantForManualTrigger(participant))
               {
                  result.add(Issue.warning(element, Validation_Messages.ERR_Trigger_InvalidScopedParticipant, 
                        PredefinedConstants.PARTICIPANT_ATT));    
               }
            }            
         }
      }

      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}