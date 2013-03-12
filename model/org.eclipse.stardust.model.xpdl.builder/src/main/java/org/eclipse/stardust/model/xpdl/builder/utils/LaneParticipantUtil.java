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

package org.eclipse.stardust.model.xpdl.builder.utils;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.PoolSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;

/**
 * @author Barry.Grotjahn
 *
 */
public class LaneParticipantUtil
{
   public static boolean isUsedInLane(IModelParticipant participant)
   {
      ModelType model = ModelUtils.findContainingModel(participant);
      for(ProcessDefinitionType process : model.getProcessDefinition())
      {
         List diagrams = process.getDiagram();
         for (Iterator it = diagrams.iterator(); it.hasNext();)
         {
            DiagramType diagram = (DiagramType) it.next();   
            List pools = ((DiagramType) diagram).getPoolSymbols();
            for (Iterator p = pools.iterator(); p.hasNext();)
            {
               PoolSymbol pool = (PoolSymbol) p.next();   
               if(!pool.getLanes().isEmpty())
               {
                  return isParticipantUsedInLanes(pool, participant);
               }
            }            
         }         
      }      
      
      return false;
   }
   
   private static boolean isParticipantUsedInLanes(ISwimlaneSymbol container, EObject element)
   {
      for(Iterator iter = container.getChildLanes().iterator(); iter.hasNext();)
      {
         LaneSymbol lane = (LaneSymbol) iter.next();             
         IModelParticipant participant = getParticipant(lane);         
         if(participant != null && participant.equals(element))
         {
            return true;
         }                  
         if(!lane.getChildLanes().isEmpty())
         {
            return isParticipantUsedInLanes(lane, element);
         }                        
      }
      
      return false;
   }   
   
   public static void deleteLane(LaneSymbol lane)
   {
      setParticipant(lane, null);
   }
   
   public static void setParticipant(LaneSymbol lane, IModelParticipant participant)
   {
      lane.setParticipant(null);    
      lane.setParticipantReference(participant);
   }
   
   public static IModelParticipant getParticipant(LaneSymbol lane)
   {      
      IModelParticipant participant = lane.getParticipantReference();
      if(participant == null)
      {      
         participant = lane.getParticipant();
         if(participant != null)
         {
            setParticipant(lane, participant);
         }         
      }
      
      if(lane.getParticipantReference() != null)
      {
         return participant;
      }
      
      return null;
   }
}