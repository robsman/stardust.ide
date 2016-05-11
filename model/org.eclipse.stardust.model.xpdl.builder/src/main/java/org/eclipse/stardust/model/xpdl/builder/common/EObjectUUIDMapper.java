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

package org.eclipse.stardust.model.xpdl.builder.common;

import java.util.*;

import org.eclipse.emf.ecore.EObject;

/**
 * @author Shrikant.Gangal
 *
 */
public class EObjectUUIDMapper
{
   /**
    *
    */
   protected Map<UUID, EObject> uuidEObjectMap = new HashMap<UUID, EObject>();
   protected List<EObject> unmappedObjects = new ArrayList<EObject>();

   /**
    * @param obj
    */
   public String map(EObject obj)
   {
      UUID uuid = UUID.randomUUID();
      uuidEObjectMap.put(uuid, obj);
      //In case method call originated from Undo operation - make sure object no longer marked as "unmapped" - otherwise
      //cleanup operation will delete it!
      unmappedObjects.remove(obj);
      return uuid.toString();
   }
   
   /**
    * @param obj
    */
   public void unmap(EObject obj, boolean checkForExistence)
   {
      if (checkForExistence)
      {
         UUID uuid = getRealUUID(obj);
         if (uuid == null)
         {
            return;
         }
      }
      unmappedObjects.add(obj);
   }
   
   public void cleanup()
   {
      for (Iterator<EObject> i = unmappedObjects.iterator(); i.hasNext();)
      {
         EObject element = i.next();
         UUID uuid = getRealUUID(element);         
         uuidEObjectMap.remove(uuid);
      }
      unmappedObjects.clear();
   }

   /**
    * @param uuid
    * @return
    */
   public EObject getEObject(UUID uuid)
   {

      return uuidEObjectMap.get(uuid);
   }

   /**
    * @param uuid
    * @return
    */
   public EObject getEObject(String uuid)
   {

      return uuidEObjectMap.get(UUID.fromString(uuid));
   }

   /**
    * @param obj
    * @return
    */
   public String getUUID(EObject obj)
   {
      if (null != obj)
      {
         Set<Map.Entry<UUID, EObject>> entrySet = uuidEObjectMap.entrySet();
         for (Map.Entry<UUID, EObject> e : entrySet)
         {
            if (obj.equals(e.getValue()))
            {
               return e.getKey().toString();
            }
         }
      }

      return null;
   }
   
   private UUID getRealUUID(EObject obj)
   {
      if (null != obj)
      {
         Set<Map.Entry<UUID, EObject>> entrySet = uuidEObjectMap.entrySet();
         for (Map.Entry<UUID, EObject> e : entrySet)
         {
            if (obj.equals(e.getValue()))
            {
               return e.getKey();
            }
         }
      }

      return null;
   }

   public void empty()
   {
      uuidEObjectMap = new HashMap<UUID, EObject>();
   }
}
