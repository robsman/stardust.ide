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

import org.eclipse.stardust.engine.api.model.IBindAction;
import org.eclipse.stardust.engine.api.model.IEventAction;
import org.eclipse.stardust.engine.api.model.IEventActionType;
import org.eclipse.stardust.engine.api.model.IEventConditionType;
import org.eclipse.stardust.engine.api.model.IEventHandler;
import org.eclipse.stardust.engine.api.model.IUnbindAction;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.spi.AccessPoint;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;


public class IEventHandlerAdapter extends AbstractIdentifiableModelElementAdapter
      implements IEventHandler
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof EventHandlerType) ? new IEventHandlerAdapter(
               (EventHandlerType) adaptee) : null;
      }
   };

   protected final EventHandlerType ehDelegate;

   protected final AccessPointOwnerAdapter apoAdapter;

   public IEventHandlerAdapter(EventHandlerType target)
   {
      super(target);

      this.ehDelegate = target;

      this.apoAdapter = new AccessPointOwnerAdapter(target);
   }

   public boolean hasBindActions()
   {
      return !ehDelegate.getBindAction().isEmpty();
   }

   public Iterator getAllBindActions()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            ehDelegate.getBindAction(), IEventActionAdapter.FACTORY).iterator();
   }

   public Iterator getAllEventActions()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            ehDelegate.getEventAction(), IEventActionAdapter.FACTORY).iterator();
   }

   public boolean hasUnbindActions()
   {
      return !ehDelegate.getUnbindAction().isEmpty();
   }

   public Iterator getAllUnbindActions()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            ehDelegate.getUnbindAction(), IEventActionAdapter.FACTORY).iterator();
   }

   public boolean isAutoBind()
   {
      return ehDelegate.isAutoBind();
   }

   public boolean isLogHandler()
   {
      return ehDelegate.isLogHandler();
   }

   public boolean isConsumeOnMatch()
   {
      return ehDelegate.isConsumeOnMatch();
   }

   public boolean isUnbindOnMatch()
   {
      return ehDelegate.isUnbindOnMatch();
   }

   public PluggableType getType()
   {
      return (IEventConditionType) ModelApiPlugin.getAdapterRegistry()
            .getAdapter(ehDelegate.getMetaType(), IEventConditionTypeAdapter.FACTORY);
   }

   public AccessPoint findAccessPoint(String id)
   {
      return apoAdapter.findAccessPoint(id);
   }

   public Iterator getAllAccessPoints()
   {
      return apoAdapter.getAllAccessPoints();
   }

   public Iterator getAllInAccessPoints()
   {
      return apoAdapter.getAllInAccessPoints();
   }

   public Iterator getAllOutAccessPoints()
   {
      return apoAdapter.getAllOutAccessPoints();
   }

   public Iterator getAllPersistentAccessPoints()
   {
      return apoAdapter.getAllPersistentAccessPoints();
   }

   public String getProviderClass()
   {
      return apoAdapter.getProviderClass();
   }

public void addToBindActions(IBindAction arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void addToEventActions(IEventAction arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void addToUnbindActions(IUnbindAction arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public IBindAction createBindAction(String arg0, String arg1, IEventActionType arg2, int arg3) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public IEventAction createEventAction(String arg0, String arg1, IEventActionType arg2, int arg3) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public IUnbindAction createUnbindAction(String arg0, String arg1, IEventActionType arg2, int arg3) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public void removeFromBindActions(IBindAction arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void removeFromEventActions(IEventAction arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void removeFromUnbindActions(IUnbindAction arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setAutoBind(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setConditionType(IEventConditionType arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setConsumeOnMatch(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setLogHandler(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setUnbindOnMatch(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
