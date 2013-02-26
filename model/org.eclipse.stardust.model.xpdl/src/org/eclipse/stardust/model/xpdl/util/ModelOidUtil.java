/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.util.EContentAdapter;

import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class ModelOidUtil extends EContentAdapter
{
   private long lastOID = 0;
   private HashMap<Long, IModelElement> unsets = new HashMap<Long, IModelElement>();
   private Set<Long> oids = new HashSet<Long>();
   private boolean enabled = true;   
   private boolean valid = false;
   
   public void setEnabled(boolean enabled)
   {
      this.enabled = enabled;
   }

   @Override
   public void notifyChanged(Notification notification)
   {      
      Object notifier = notification.getNotifier();
      if (notifier instanceof IModelElement 
            && notification.getEventType() == Notification.SET
            && notification.getFeature().equals(CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid()))
      {
         if(valid == false && enabled == true)
         {
            throw new DuplicateOidException("invalid setting of oid!");
         }         
      }
      super.notifyChanged(notification);
   }

   @Override
   protected synchronized void addAdapter(Notifier notifier)
   {      
      super.addAdapter(notifier);
      if (notifier instanceof IModelElement)
      {
         valid = true;         
         if (((IModelElement) notifier).eIsSet(CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid()))
         {
            // check for duplicates.
            long elementOid = ((IModelElement) notifier).getElementOid();
            if(unsets == null)
            {
               if(elementOid <= lastOID && elementOid > 0)
               {
                  ((IModelElement) notifier).setElementOid(++lastOID);                  
                  elementOid = ((IModelElement) notifier).getElementOid(); 
               }            
            }   
            else
            {
               if (unsets.containsKey(elementOid))
               {
                  IModelElement me = unsets.remove(elementOid);
                  me.setElementOid(++lastOID);
                  unsets.put(lastOID, me);
                  oids.add(lastOID);                                 
               }
               else
               {
                  if(!oids.contains(elementOid))
                  {
                     oids.add(elementOid);
                     lastOID = Math.max(lastOID, elementOid);                     
                  }
                  else
                  {               
                     ((IModelElement) notifier).setElementOid(++lastOID);
                     elementOid = ((IModelElement) notifier).getElementOid(); 
                     oids.add(elementOid);               
                  }
               }
            }
         }
         else
         {
            ((IModelElement) notifier).setElementOid(++lastOID);
            if(unsets != null)
            {
               unsets.put(lastOID, (IModelElement) notifier);
               oids.add(lastOID);                                                
            }
         }
         valid = false;
      }
   }

   public static ModelOidUtil register(ModelType model)
   {
      ModelOidUtil m = new ModelOidUtil();
      model.eAdapters().add(m);
      m.unsets = null;
      m.oids = null;
      
      return m;
   }
}