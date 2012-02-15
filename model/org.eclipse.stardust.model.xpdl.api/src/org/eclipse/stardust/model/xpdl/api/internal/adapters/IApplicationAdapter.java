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
import org.eclipse.stardust.engine.api.model.IApplication;
import org.eclipse.stardust.engine.api.model.IApplicationContext;
import org.eclipse.stardust.engine.api.model.IApplicationType;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.spi.AccessPoint;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;


public class IApplicationAdapter extends AbstractIdentifiableModelElementAdapter
      implements IApplication
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ApplicationType) ? new IApplicationAdapter(
               (ApplicationType) adaptee) : null;
      }
   };

   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   protected final ApplicationType aDelegate;

   protected final AccessPointOwnerAdapter apoAdapter;

   public IApplicationAdapter(ApplicationType delegate)
   {
      super(delegate);

      this.aDelegate = delegate;

      this.apoAdapter = new AccessPointOwnerAdapter(delegate);
   }

   public PluggableType getType()
   {
      return (IApplicationType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            aDelegate.getMetaType(), IApplicationTypeAdapter.FACTORY);
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
   public Iterator getAllPersistentAccessPoints()
   {
      return apoAdapter.getAllPersistentAccessPoints();
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
   public String getProviderClass()
   {
      return apoAdapter.getProviderClass();
   }

   public Iterator getAllActivities()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(aDelegate, PKG_CWM.getApplicationType_ExecutedActivities(),
                  IActivityAdapter.FACTORY)
            .iterator();
   }

   public Iterator getAllContexts()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            aDelegate.getContext(), IApplicationContextAdapter.FACTORY).iterator();
   }

   public IApplicationContext findContext(String id)
   {
      return (IApplicationContext) ModelApiPlugin.getAdapterRegistry()
            .getAdapter(aDelegate.getContext(), id, IApplicationContextAdapter.FACTORY);
   }

   public boolean isInteractive()
   {
      return aDelegate.isInteractive();
   }

   public boolean isSynchronous()
   {
      // TODO no corresponding method in EMF, what should be returned here 
      return false;
   }

public void addToPersistentAccessPoints(IAccessPoint arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public IApplicationContext createContext(String arg0, int arg1) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public void removeAllContexts() {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void removeContext(String arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setApplicationType(IApplicationType arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setInteractive(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
