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
package org.eclipse.stardust.modeling.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;


public class HierarchyUtils
{
   public static List getTopLevelChildParticipants(LaneSymbol laneSymbol)
   {
      List participants = new ArrayList();
      if(!laneSymbol.getChildLanes().isEmpty())
      {
         List children = laneSymbol.getChildLanes();
         for(int i = 0; i < children.size(); i++)
         {
            LaneSymbol childSymbol = (LaneSymbol) children.get(i);
            IModelParticipant laneParticipant = childSymbol.getParticipant();
            if(laneParticipant != null)
            {
               participants.add(laneParticipant);
            }
            else
            {
               List childParticipants = getTopLevelChildParticipants(childSymbol);
               if(childParticipants != null)
               {
                  participants.addAll(childParticipants);
               }
            }            
         }
      }
      if(participants.isEmpty())
      {
         return null;
      }      
      return participants;
   }
   
   // create a tree with all children of the organizations
   public static Map createHierarchy(ModelType model)
   {
      Map allOrganizationsSource = new HashMap();      
      Map allOrganizations = null;      
      
      EList organizations = model.getOrganization();
      for(int i = 0; i < organizations.size(); i++)
      {
         OrganizationType organization = (OrganizationType) organizations.get(i);         
         EList contentObjects = organization.eContents();
         if(contentObjects.size() == 0)
         {
            allOrganizationsSource.put(organization, null);            
         }
         else
         {
            List childParticipants = new ArrayList();
            for(int c = 0; c < contentObjects.size(); c++)
            {
               EObject content = (EObject) contentObjects.get(c);
               if(content instanceof ParticipantType)
               {
                  IModelParticipant childParticipant = ((ParticipantType) content).getParticipant();
                  childParticipants.add(childParticipant);                  
               }               
            }
            allOrganizationsSource.put(organization, childParticipants);                        
         }         
      }      
      allOrganizations = new HashMap(allOrganizationsSource);
    
      Iterator it = allOrganizationsSource.entrySet().iterator(); 
      while (it.hasNext()) 
      {
         Map.Entry entry = (Map.Entry) it.next();
         OrganizationType organization = (OrganizationType) entry.getKey();
         Object children = entry.getValue();
         
         if(allOrganizations.containsKey(organization) && children != null)
         {
            List organizationTree = createTree(allOrganizations, organization);
            allOrganizations.put(organization, organizationTree);
         }      
      }
      return allOrganizations;
   }
   
   // helper
   public static List createTree(Map allOrganizations, OrganizationType organization)
   {
      Object children = allOrganizations.get(organization);
      allOrganizations.remove(organization);
      
      List newChildren = null;      
      if(children != null)
      {
         newChildren = new ArrayList();       
         Map organizationContainer = new HashMap();
         for(int c = 0; c < ((List) children).size(); c++)
         {            
            Object childParticipant = ((List) children).get(c);
            if(childParticipant instanceof OrganizationType)
            {               
               List organizationTree = createTree(allOrganizations, (OrganizationType) childParticipant);
               if(organizationTree != null)
               {
                  organizationContainer.put(childParticipant, organizationTree);                  
               }
               else
               {
                  newChildren.add(childParticipant);                  
               }
            }
            else
            {
               newChildren.add(childParticipant);
            }            
         }
         if(organizationContainer.size() > 0)
         {
            newChildren.add(organizationContainer);            
         }         
      }      
      return newChildren;
   }

   // get child hierarchy of a spezific IModelParticipant
   public static List getChildHierarchy(Map organizationTree, OrganizationType laneParticipant)
   {
      Iterator it = organizationTree.entrySet().iterator(); 
      while (it.hasNext()) 
      {
         Map.Entry entry = (Map.Entry) it.next();
         OrganizationType organization = (OrganizationType) entry.getKey();
         Object children = entry.getValue();
         if(organization.equals(laneParticipant))
         {
            return (List) children;
         }
         else
         {
            if(children != null)
            {
               for(int c = 0; c < ((List) children).size(); c++)
               {
                  Object child = ((List) children).get(c);
                  if(child instanceof Map)
                  {
                     return getChildHierarchy((Map) child, laneParticipant);                 
                  }                  
                  else
                  {
                     // leave node
                     if(child.equals(laneParticipant))
                     {
                        return null;
                     }                     
                  }                  
               }
            }
            else
            {
               return null;
            }
         }         
      }
      return null;
   }
   
   // get all participants of this list
   public static List getParticipants(List organizationChildren)
   {
      List allParticipants = new ArrayList();
      
      for(int c = 0; c < organizationChildren.size(); c++)
      {
         Object child = organizationChildren.get(c);
         if(child instanceof Map)
         {
            Iterator it = ((Map) child).entrySet().iterator(); 
            while (it.hasNext()) 
            {
               Map.Entry entry = (Map.Entry) it.next();
               OrganizationType organization = (OrganizationType) entry.getKey();
               allParticipants.add(organization);               
               Object children = entry.getValue();               
               if(children != null)
               {
                  List childOrganizations = getParticipants((List) children);
                  if(childOrganizations != null)
                  {
                     allParticipants.addAll(childOrganizations);
                  }
               }
            }
         }                  
         else
         {
            if(child instanceof IModelParticipant)
            {
               allParticipants.add(child);
            }                     
         }                  
      }
      if(allParticipants.size() == 0)
      {
         allParticipants = null;
      }
      return allParticipants;
   }  
   
   // collect all participants in the model
   public static List getAllParticipants(ModelType model)
   {
      List allParticipants = new ArrayList();
      allParticipants.addAll(model.getRole());
      allParticipants.addAll(model.getConditionalPerformer());
      allParticipants.addAll(model.getOrganization());
      return allParticipants;
   }  
   
   // only show participants below parent organizations (if any)
   public static List getParticipants(LaneSymbol lane, ModelType model)
   {
      if(lane != null)
      {         
         IModelParticipant laneParticipant = null;         
         Map organizationTree = HierarchyUtils.createHierarchy(model); 
         ISwimlaneSymbol parent = (ISwimlaneSymbol) lane.getParentLane();
         IModelParticipant parentParticipant = null;   
         while (parent != null)
         {
            parentParticipant = parent.getParticipant();
            if(parentParticipant != null)
            {
               laneParticipant = parentParticipant;   
               break;
            }
            parent = parent instanceof LaneSymbol ? ((LaneSymbol) parent).getParentLane() : null;
         }    
         
         // no parent participant, show all
         if(laneParticipant == null)
         {
            return HierarchyUtils.getAllParticipants(model);
         }    
         // a role has no children
         if(!(parentParticipant instanceof OrganizationType))
         {
            return Collections.EMPTY_LIST;
         }         
         
         List organizationChildren = HierarchyUtils.getChildHierarchy(organizationTree, (OrganizationType) laneParticipant);
         List participants = new ArrayList();
         if(organizationChildren != null)
         {
            if(HierarchyUtils.getParticipants(organizationChildren) != null)
            {
               participants = HierarchyUtils.getParticipants(organizationChildren);               
            }
         }
         return participants;         
      }
      else
      {
         return HierarchyUtils.getAllParticipants(model);
      }
   }
   
   // check if a lane has child lanes with participants assigned
   public static boolean hasChildLanesParticipant(LaneSymbol lane)
   {
      EList lanes = lane.getChildLanes();
      if(!lanes.isEmpty())
      {
         for(int i = 0; i < lanes.size(); i++)
         {
            LaneSymbol childLane = (LaneSymbol) lanes.get(i);
            if(childLane.getParticipant() != null)
            {
               return true;
            }
            if(hasChildLanesParticipant(childLane))
            {
               return true;
            }
         }
      }
      return false;
   }
   
   // has lane or parent lane participant
   public static IModelParticipant hasParentLanesParticipant(LaneSymbol lane)
   {
      if(lane.getParticipant() != null)
      {
         return lane.getParticipant();
      }
      ISwimlaneSymbol parent = lane.getParentLane();
      if(parent instanceof PoolSymbol)
      {
         return null;
      }
      return hasParentLanesParticipant((LaneSymbol) parent); 
   }   
}