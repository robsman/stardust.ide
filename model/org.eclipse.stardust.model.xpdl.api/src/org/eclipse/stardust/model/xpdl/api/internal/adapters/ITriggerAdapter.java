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
package org.eclipse.stardust.model.xpdl.api.internal.adapters;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.stardust.engine.api.model.IAccessPoint;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IParameterMapping;
import org.eclipse.stardust.engine.api.model.ITrigger;
import org.eclipse.stardust.engine.api.model.ITriggerType;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.spi.AccessPoint;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;


public class ITriggerAdapter extends AbstractIdentifiableModelElementAdapter
      implements ITrigger
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof TriggerType) ? new ITriggerAdapter(
               (TriggerType) adaptee) : null;
      }
   };

   protected final TriggerType tDelegate;

   protected final AccessPointOwnerAdapter apoAdapter;

   public ITriggerAdapter(TriggerType target)
   {
      super(target);

      this.tDelegate = target;

      this.apoAdapter = new AccessPointOwnerAdapter(target);
   }

   /**
    * @category ITrigger
    */
   public PluggableType getType()
   {
      return (ITriggerType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            tDelegate.getMetaType(), ITriggerTypeAdapter.FACTORY);
   }

   public boolean isSynchronous()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public Iterator getAllParameterMappings()
   {
      // TODO implement
      throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
   }

   /**
    * @category AccessPointOwner
    */
   public Iterator getAllAccessPoints()
   {
      return apoAdapter.getAllAccessPoints();
   }

   /**
    * @category AccessPointOwner
    */
   public Iterator getAllInAccessPoints()
   {
      return apoAdapter.getAllInAccessPoints();
   }

   /**
    * @category AccessPointOwner
    */
   public Iterator getAllOutAccessPoints()
   {
      return apoAdapter.getAllOutAccessPoints();
   }

   /**
    * @category AccessPointOwner
    */
   public AccessPoint findAccessPoint(String id)
   {
      return apoAdapter.findAccessPoint(id);
   }

   /**
    * @category AccessPointOwner
    */
   public Iterator getAllPersistentAccessPoints()
   {
      return apoAdapter.getAllPersistentAccessPoints();
   }

   /**
    * @category AccessPointOwner
    */
   public String getProviderClass()
   {
      return apoAdapter.getProviderClass();
   }

public void addToParameterMappings(IParameterMapping arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void addToPersistentAccessPoints(IAccessPoint arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public IParameterMapping createParameterMapping(IData arg0, String arg1, String arg2, int arg3) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public void removeFromParameterMappings(IParameterMapping arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setAllParameterMappings(Iterator arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setType(ITriggerType arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
