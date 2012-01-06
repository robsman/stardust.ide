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

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.ScopeUtils;
import org.eclipse.stardust.modeling.validation.IModelElementValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;
import org.eclipse.stardust.modeling.validation.ValidationService;
import org.eclipse.stardust.modeling.validation.Validation_Messages;
import org.eclipse.stardust.modeling.validation.util.IModelParticipantUtils;

public class OrganizationValidator implements IModelElementValidator
{
   public Issue[] validate(IModelElement element) throws ValidationException
   {
      List<Issue> result = new ArrayList<Issue>();
      OrganizationType organization = (OrganizationType) element;

      if (IModelParticipantUtils.isDuplicateId(organization))
      {
         result.add(Issue.error(organization, Validation_Messages.ERR_ORGANIZATION_DuplicateId,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));
      }
      
      if(RoleValidator.worksForAnyOrganization(organization) > 1)
      {
         result.add(Issue.error(organization, Validation_Messages.ERR_ORGANIZATION_PartOf,
               ValidationService.PKG_CWM.getIIdentifiableElement_Id()));         
      }      

      // check if scope data is correct
      if(AttributeUtil.getBooleanValue((IExtensibleElement) organization, PredefinedConstants.BINDING_ATT))
      {
         if(!ScopeUtils.isValidScopeData(organization, null, null))
         {            
            result.add(Issue.error(organization, Validation_Messages.ERR_ORGANIZATION_InvalidScopeData,
                  PredefinedConstants.BINDING_DATA_ID_ATT));                     
         }
      }
      
      return (Issue[]) result.toArray(Issue.ISSUE_ARRAY);
   }
}