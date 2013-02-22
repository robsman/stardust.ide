/*
 * $Id$
 * (C) 2000 - 2013 CARNOT AG
 */
package org.eclipse.stardust.model.xpdl.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
   
   @Override
   protected synchronized void addAdapter(Notifier notifier)
   {      
      super.addAdapter(notifier);
      if (notifier instanceof IModelElement)
      {
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
      }
   }

   public static void register(ModelType model)
   {
      ModelOidUtil m = new ModelOidUtil();
      model.eAdapters().add(m);
      m.unsets = null;
      m.oids = null;
   }
}