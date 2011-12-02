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

import java.util.*;

import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;


public final class ModelParticipantUtils
{
   public static List getAssociatedOrganizations(IModelParticipant participant)
   {
      List result = null;

      List associations = participant.getParticipantAssociations();
      for (int i = 0; i < associations.size(); ++i)
      {
         ParticipantType p = (ParticipantType) associations.get(i);
         if (p.eContainer() instanceof OrganizationType)
         {
            if (null == result)
            {
               result = new ArrayList(associations.size());
            }
            result.add(p.eContainer());
         }
      }

      return (null != result) ? result : Collections.EMPTY_LIST;
   }

   public static OrganizationType findAssociatedOrganization(
         IModelParticipant participant, String id)
   {
      OrganizationType result = null;

      List associations = participant.getParticipantAssociations();
      for (int i = 0; i < associations.size(); ++i)
      {
         ParticipantType p = (ParticipantType) associations.get(i);
         if (p.eContainer() instanceof OrganizationType)
         {
            OrganizationType org = (OrganizationType) p.eContainer();

            if (((null != id) && id.equals(org.getId())) || (id == org.getId()))
            {
               result = org;
               break;
            }
         }
      }

      return result;
   }

   public static List findAssociatedRootOrganizations(IModelParticipant participant)
   {
      List result = new ArrayList();

      // TODO
      List pendingParticipants = new LinkedList();
      pendingParticipants.add(participant);
      Set resolvedParticipants = new HashSet();
      while ( !pendingParticipants.isEmpty())
      {
         IModelParticipant candidate = (IModelParticipant) pendingParticipants.remove(0);
         if ( !resolvedParticipants.contains(candidate))
         {
            List orgs = getAssociatedOrganizations(candidate);
            if (orgs.isEmpty())
            {
               result.add(candidate);
            }
            else
            {
               pendingParticipants.addAll(orgs);
            }
            resolvedParticipants.add(candidate);
         }
      }

      return result;
   }
   
   public static List getSubOrganizations(OrganizationType parent)
   {
      List result = null;
      
      List pList = parent.getParticipant();
      for (int i = 0; i < pList.size(); ++i)
      {
         ParticipantType p = (ParticipantType) pList.get(i);
         if (p.getParticipant() instanceof OrganizationType)
         {
            if (null == result)
            {
               result = new ArrayList(pList.size());
            }
            result.add(p.getParticipant());
         }
      }
      
      return (null != result) ? result : Collections.EMPTY_LIST;
   }

   public static List getSubParticipants(OrganizationType parent)
   {
      List pList = parent.getParticipant();
      List result = new ArrayList(pList.size());
      
      for (int i = 0; i < pList.size(); ++i)
      {
         ParticipantType p = (ParticipantType) pList.get(i);
         if (null != p.getParticipant())
         {
            result.add(p.getParticipant());
         }
      }
      
      return result;
   }

   public static IModelParticipant findSubParticipant(OrganizationType parent, String id)
   {
      IModelParticipant result = null;
      
      List pList = parent.getParticipant();
      for (int i = 0; i < pList.size(); ++i)
      {
         IModelParticipant candidate = ((ParticipantType) pList.get(i)).getParticipant();
         if ((null != candidate)
               && (((null != id) && id.equals(candidate.getId())) || (id == candidate.getId())))
         {
            result = candidate;
            break;
         }
      }
      
      return result;
   }
   
   public static boolean isSubOrganizationOf(OrganizationType parent, OrganizationType child, boolean deep)
   {
      boolean result = false;
      
      List pendingOrgs = new LinkedList();
      pendingOrgs.add(parent);
      Set resolvedOrgs = new HashSet();
      while ( !pendingOrgs.isEmpty())
      {
         OrganizationType org = (OrganizationType) pendingOrgs.remove(0);
         if ( !resolvedOrgs.contains(org))
         {
            if (org == child)
            {
               result = true;
               break;
            }
            else if (deep || (org == parent))
            {
               pendingOrgs.addAll(getSubOrganizations(org));
               resolvedOrgs.add(org);
            }
         }
      }
      
      return result;
   }

   private ModelParticipantUtils()
   {
      // utility class
   }

   public static IModelParticipant findById(ModelType model, String id)
   {
      // TODO Auto-generated method stub
      IModelParticipant result = (IModelParticipant) ModelUtils.findIdentifiableElement(
            model.getOrganization(), id);
      if (null == result)
      {
         result = (IModelParticipant) ModelUtils.findIdentifiableElement(model.getRole(),
               id);
      }
      if (null == result)
      {
         result = (IModelParticipant) ModelUtils.findIdentifiableElement(
               model.getConditionalPerformer(), id);
      }
      return result;
   }
}
