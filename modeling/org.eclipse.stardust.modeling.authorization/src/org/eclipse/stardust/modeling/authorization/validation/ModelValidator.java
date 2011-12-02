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
package org.eclipse.stardust.modeling.authorization.validation;

import java.util.HashSet;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ScopeUtils;
import org.eclipse.stardust.modeling.authorization.AuthorizationUtils;
import org.eclipse.stardust.modeling.authorization.Authorization_Messages;
import org.eclipse.stardust.modeling.authorization.Permission;
import org.eclipse.stardust.modeling.validation.IModelValidator;
import org.eclipse.stardust.modeling.validation.Issue;
import org.eclipse.stardust.modeling.validation.ValidationException;

import ag.carnot.base.CollectionUtils;

public class ModelValidator implements IModelValidator
{
   private static final Issue[] ISSUE_ARRAY = new Issue[0];

   // validate permissions (no scoped participants)
   public Issue[] validate(ModelType model) throws ValidationException
   {
      List<Issue> result = CollectionUtils.newList();
      List<Permission> permissions = AuthorizationUtils.getPermissions((IExtensibleElement) model);
      
      HashSet<IModelParticipant> scoped = ScopeUtils.findScopedParticipants(model);
      
      // check all model permissions
      boolean match = false;
      for(Permission permission : permissions)
      {      
         for(IModelParticipant participant : scoped)
         {
            if(permission.contains(participant))
            {
               match = true;
               break;
            }            
         }
      }
      
      if(match)
      {
         result.add(Issue.error(model, Authorization_Messages.ERR_MSG_SCOPED_PARTICIPANTS_ARE_NOT_ALLOWED_FOR_MODEL_LEVEL_GRANTS, model.getId()));         
      }
            
      return result.toArray(ISSUE_ARRAY);
   }
}