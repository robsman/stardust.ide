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
package org.eclipse.stardust.model.xpdl.carnot.util;

import java.util.HashSet;

import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;

import ag.carnot.base.StringUtils;
import ag.carnot.bpm.rt.data.structured.IXPathMap;
import ag.carnot.bpm.rt.data.structured.StructuredDataXPathUtils;
import ag.carnot.workflow.model.PredefinedConstants;
import ag.carnot.workflow.runtime.beans.BigData;
import ag.carnot.workflow.spi.providers.data.java.Type;

public class ScopeUtils
{  
   public static boolean isValidScopeData(IExtensibleElement extensible, String dataPath, DataType data)
   {          
      boolean valid = true;

      ModelType model = ModelUtils.findContainingModel(extensible);
      
      String dataId = AttributeUtil.getAttributeValue(extensible, PredefinedConstants.BINDING_DATA_ID_ATT);               
      if (StringUtils.isEmpty(dataId) && data == null)
      {
         valid = false;
      }
      else
      {
         if (data == null)
         {
            data = (DataType) ModelUtils.findElementById(model.getData(), dataId);
         }
         
         if (data != null)
         {         
            if (data.getType().getId().equals(PredefinedConstants.STRUCTURED_DATA))
            {               
               if (dataPath == null)
               {
                  dataPath = AttributeUtil.getAttributeValue(extensible, PredefinedConstants.BINDING_DATA_PATH_ATT);
               }
               
               if (StringUtils.isEmpty(dataPath))
               {
                  valid = false;
               }
               else
               {
                  TypeDeclarationType type = StructuredTypeUtils.getTypeDeclaration(data);
                  if (type == null)
                  {
                     valid = false;
                  }
                  else
                  {
                     IXPathMap xPathMap = StructuredTypeUtils.getXPathMap(data); 
                     if (StructuredDataXPathUtils.returnSinglePrimitiveType(dataPath, xPathMap) != BigData.STRING)
                     {
                        valid = false;     
                     }
                  }                  
               }
            }
            else if (data.getType().getId().equals(PredefinedConstants.PRIMITIVE_DATA))
            {
               String type = AttributeUtil.getAttributeValue((IExtensibleElement) data, CarnotConstants.TYPE_ATT);
               if (Type.String.equals(type))
               {                  
                  if (dataPath == null)
                  {                  
                     dataPath = AttributeUtil.getAttributeValue(extensible, PredefinedConstants.BINDING_DATA_PATH_ATT);
                  }
                  
                  if (!StringUtils.isEmpty(dataPath))
                  {
                     valid = false;
                  }
               }
               else
               {
                  valid = false;
               }
            }
            else
            {
               valid = false;
            }
         }
         else
         {
            valid = false;
         }
      }
      return valid;
   }   
   
   public static boolean isValidScopedParticipantForManualTrigger(IModelParticipant participant)
   {          
      OrganizationType organization = ScopeUtils.findScopedOrganizationForParticipant(participant);
      return isValidScopeData(organization, null, null);
   }
   
   public static HashSet<IModelParticipant> findScopedParticipants(ModelType model)
   {
      HashSet<IModelParticipant> scoped = new HashSet<IModelParticipant>();
      
      for(OrganizationType organization : model.getOrganization())
      {
         if(AttributeUtil.getBooleanValue((IExtensibleElement) organization, PredefinedConstants.BINDING_ATT))
         {
            collectChildren(organization, scoped);            
         }
      }      
      return scoped;
   }
   
   public static OrganizationType findScopedOrganizationForParticipant(IModelParticipant element)
   {          
      if(element instanceof OrganizationType)
      {
         if(AttributeUtil.getBooleanValue((IExtensibleElement) element, PredefinedConstants.BINDING_ATT))
         {
            return (OrganizationType) element;
         }         
      }
      
      ModelType model = ModelUtils.findContainingModel(element);
      
      OrganizationType parent = null;      
      for(OrganizationType organization : model.getOrganization())
      {
         RoleType teamLead = organization.getTeamLead();
         if(teamLead != null && teamLead.equals(element))
         {
            parent = organization;
         }

         if(parent == null)
         {
            for(ParticipantType participant : organization.getParticipant())
            {
               IModelParticipant childParticipant = participant.getParticipant();
               if(childParticipant.equals(element))
               {
                  parent = organization;
                  break;
               }               
            }
         }            
      }               
      
      if(parent == null)
      {
         return null;
      }
      
      if(AttributeUtil.getBooleanValue((IExtensibleElement) parent, PredefinedConstants.BINDING_ATT))
      {
         return parent;
      }         
      
      return findScopedParent(parent);
   }   
   
   private static OrganizationType findScopedParent(OrganizationType element)
   {
      ModelType model = ModelUtils.findContainingModel(element);
      
      for(OrganizationType organization : model.getOrganization())
      {
         for(ParticipantType participant : organization.getParticipant())
         {
            IModelParticipant childParticipant = participant.getParticipant();
            if(childParticipant instanceof OrganizationType)
            {
               if(childParticipant.equals(element))
               {
                  
            	  if(AttributeUtil.getBooleanValue((IExtensibleElement) organization, PredefinedConstants.BINDING_ATT))
                  {
                     return organization;
                  }
                  return findScopedParent(organization);                  
               }
            }         
         }         
      }      
      return null;
   }

   private static void collectChildren(OrganizationType organization, HashSet<IModelParticipant> scoped)
   {      
      scoped.add(organization);
      
      RoleType teamLead = organization.getTeamLead();
      if(teamLead != null)
      {
         scoped.add(teamLead);
      }
      
      for(ParticipantType participant : organization.getParticipant())
      {
         IModelParticipant childParticipant = participant.getParticipant();
         if(childParticipant instanceof OrganizationType)
         {
            if(!scoped.contains(childParticipant))
            {
               collectChildren((OrganizationType) childParticipant, scoped);
            }            
         }
         scoped.add(childParticipant);
      }
   }
}