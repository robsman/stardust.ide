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

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.xmi.XMLResource;

import org.eclipse.stardust.common.CollectionUtils;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class ModelOidUtil extends EContentAdapter
{
   private long lastOID = 0;
   private Map<Long, IModelElement> unsets = CollectionUtils.newMap();
   private Set<Long> oids = CollectionUtils.newSet();
   private boolean enabled = true;  
   private boolean copyPaste = false;     
   private boolean valid = false;
   private XMLResource resource;
   
   public void setEnabled(boolean enabled)
   {
      this.enabled = enabled;
   }

   public void setCopyPaste(boolean copyPaste)
   {
      this.copyPaste = copyPaste;
   }   
   
   @Override
   public void notifyChanged(Notification notification)
   {      
      Object notifier = notification.getNotifier();
      if (notifier instanceof IModelElement 
            && notification.getEventType() == Notification.SET
            && notification.getFeature().equals(CarnotWorkflowModelPackage.eINSTANCE.getIModelElement_ElementOid()))
      {
         if (!valid && enabled)
         {
            throw new DuplicateOidException("invalid setting of oid!"); //$NON-NLS-1$
         }         
         else
         {
            if(!copyPaste)
            {
               oids.remove(notification.getOldLongValue());
            }
            oids.add(notification.getNewLongValue());
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
         IModelElement element = (IModelElement) notifier;
         if (element.isSetElementOid())
         {
            // check for duplicates.
            final long elementOid = element.getElementOid();
            if (unsets != null && unsets.containsKey(elementOid))
            {
               setElementOid(unsets.remove(elementOid));
            }
            else
            {
               if (oids.contains(elementOid))
               {
                  element.setElementOid(++lastOID);
                  oids.add(lastOID); 
                  if(resource != null)
                  {
                     resource.setID(element, new Long(lastOID).toString());
                  }                  
               }
               else
               {               
                  lastOID = Math.max(lastOID, elementOid);                     
                  oids.add(elementOid);
               }
            }
         }
         else
         {
            setElementOid(element);                                                
         }
         valid = false;
      }
   }

   @Override
   protected synchronized void removeAdapter(Notifier notifier)
   {
      super.removeAdapter(notifier);
      if (notifier instanceof IModelElement)
      {
         oids.remove(((IModelElement) notifier).getElementOid());
      }
   }

   private void setElementOid(IModelElement element)
   {
      element.setElementOid(++lastOID);
      if (unsets != null)
      {
         unsets.put(lastOID, element);
      }
      oids.add(lastOID);
   }

   public static ModelOidUtil register(ModelType model, long maxUsedOid, Resource resource)
   {
      ModelOidUtil m = new ModelOidUtil();
      m.lastOID = maxUsedOid;
      if(resource != null && resource instanceof XMLResource)
      {      
         m.resource = (XMLResource) resource;
      }      
      model.eAdapters().add(m);
      m.unsets = null;
      return m;
   }
}