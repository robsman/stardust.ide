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
package org.eclipse.stardust.modeling.debug.engine;

import java.lang.reflect.Field;
import java.util.Set;

import org.eclipse.stardust.common.error.InternalException;

import ag.carnot.db.DefaultPersistentVector;
import ag.carnot.db.IdentifiablePersistent;
import ag.carnot.db.PersistenceController;
import ag.carnot.db.Persistent;
import ag.carnot.db.Session;
import ag.carnot.db.jdbc.SessionFactory;

/**
 * This class is adapted from 
 * {@link ag.carnot.workflow.tools.defdesk.debugger.TransientPersistenceController}.
 * 
 * @author sborn
 * @version $Revision$
 */
public class TransientPersistenceController implements PersistenceController
{
   private Persistent persistent;

   public TransientPersistenceController(Persistent persistent)
   {
      this.persistent = persistent;
   }

   public void fetch()
   {
   }

   public void fetchLink(String linkName)
   {
   }

   public void fetchVector(String vectorName)
   {
      try
      {
         Field field = persistent.getClass().getDeclaredField(vectorName);
         Object value = field.get(persistent);
         if (value == null)
         {
            field.set(persistent, new DefaultPersistentVector());
         }
      }
      catch (Exception e)
      {
         throw new InternalException(e);
      }

   }

   public Persistent getPersistent()
   {
      return persistent;
   }

   public void markDeleted()
   {
      markDeleted(false);
   }
   
   /**
    * This implementation deletes the persistent object administrated by this 
    * PersistenceController with no regard to the parameter writeThrough.
    * 
    * @param writeThrough not yet evaluated
    */
   public void markDeleted(boolean writeThrough)
   {
      ((DebugSession) SessionFactory.getSession(SessionFactory.AUDIT_TRAIL))
            .deleteObjectByOID(((IdentifiablePersistent) persistent).getOID());
   }

   public boolean isLocked()
   {
      return false;
   }

   public void markLocked()
   {
      // ignore
   }

   public void markModified()
   {
   }

   public void markModified(String fieldName)
   {
      // ignore hint on modified fields
      markModified();
   }
   
   public void markCreated()
   {
   }

   public void reload()
   {
   }

   public void reloadAttribute(String name)
   {
   }

   public boolean isModified()
   {
      return false;
   }

   public Set getModifiedFields()
   {
      // not tracking modified fields
      return null;
   }

   public boolean isCreated()
   {
      return false;
   }

   public void close()
   {
   }

   public Session getSession()
   {      
      return SessionFactory.getSession(SessionFactory.AUDIT_TRAIL);
   }

   public void getTypeManager()
   {

   }
}
