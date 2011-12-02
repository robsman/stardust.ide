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
package org.eclipse.stardust.modeling.validation.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.IModelParticipantUtils;

import ag.carnot.workflow.model.PredefinedConstants;

public class RoleValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      RoleType role = (RoleType) element;

      if (IModelParticipantUtils.isDuplicateId(role))
      {
         result.add(Issue.error(role, Validation_Messages.ERR_ROLE_DuplicateId, ValidationService.PKG_CWM
               .getIIdentifiableElement_Id()));
      }
      
      if(role.getId().equals(PredefinedConstants.ADMINISTRATOR_ROLE))
      {
         if(isAdministratorConnectedToOrganization(role))
         {
            result.add(Issue.error(role, Validation_Messages.ERR_ROLE_Administrator, 
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));            
         }         
      }        
      else 
      {
         if(worksForAnyOrganization(role) > 0
            && managerOfAnyOrganization(role) > 0)
         {
            result.add(Issue.error(role, Validation_Messages.ERR_ROLE_WorksForManagerOf, 
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));            
         }                  
         if(worksForAnyOrganization(role) > 1)
         {
            result.add(Issue.error(role, Validation_Messages.ERR_ROLE_WorksFor, 
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));                     
         }         
         if(managerOfAnyOrganization(role) > 1)
         {
            result.add(Issue.error(role, Validation_Messages.ERR_ROLE_ManagerOf, 
                  ValidationService.PKG_CWM.getIIdentifiableElement_Id()));                     
         }
      }
      
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
   
   public static int worksForAnyOrganization(IModelParticipant role)
   {
      int cnt = 0;
      for (OrganizationType organization : ModelUtils.findContainingModel(role).getOrganization())
      {
         for(ParticipantType participant : organization.getParticipant())
         {
            IModelParticipant childParticipant = participant.getParticipant();
            if(childParticipant.equals(role))
            {
               cnt++;
            }
         }         
      }
      return cnt;
   }   
      
   public int managerOfAnyOrganization(RoleType role)
   {
      int cnt = 0;
      for (OrganizationType organization : ModelUtils.findContainingModel(role).getOrganization())
      {
         RoleType teamLead = organization.getTeamLead();
         if(teamLead != null && teamLead.equals(role))
         {
            cnt++;
         }
      }
      return cnt;
   }   
   
   public boolean isAdministratorConnectedToOrganization(RoleType administrator)
   {
      for (OrganizationType organization : ModelUtils.findContainingModel(administrator).getOrganization())
      {
         RoleType teamLead = organization.getTeamLead();
         if(teamLead != null && teamLead.equals(administrator))
         {
            return true;
         }
         
         for(ParticipantType participant : organization.getParticipant())
         {
            IModelParticipant childParticipant = participant.getParticipant();
            if(childParticipant.equals(administrator))
            {
               return true;
            }
         }         
      }
      return false;
   }   
}