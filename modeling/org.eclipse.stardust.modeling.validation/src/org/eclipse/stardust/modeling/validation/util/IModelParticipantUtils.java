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
package org.eclipse.stardust.modeling.validation.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class IModelParticipantUtils
{   
   public static boolean isDuplicateId(IModelParticipant element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      List<IModelParticipant> participants = new ArrayList<IModelParticipant>();
      
      if(!model.getConditionalPerformer().isEmpty())
      {
         participants.addAll(model.getConditionalPerformer());
      }
      if(!model.getRole().isEmpty())
      {
         participants.addAll(model.getRole());         
      }
      if(!model.getOrganization().isEmpty())
      {
         participants.addAll(model.getOrganization());         
      }
    
      for(IModelParticipant participant : participants)
      {
         if(!participant.equals(element) && participant.getId().equals(element.getId()))
         {
            return true;
         }
      }      
      
      return false;
   }
}