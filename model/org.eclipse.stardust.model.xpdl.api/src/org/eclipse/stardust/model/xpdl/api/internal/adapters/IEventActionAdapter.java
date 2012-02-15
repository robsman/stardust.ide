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

import java.util.Vector;

import org.eclipse.stardust.engine.api.model.EventActionContext;
import org.eclipse.stardust.engine.api.model.IEventAction;
import org.eclipse.stardust.engine.api.model.IEventActionType;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;


public class IEventActionAdapter extends AbstractIdentifiableModelElementAdapter
      implements IEventAction
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof EventActionType) ? new IEventActionAdapter(
               (EventActionType) adaptee) : null;
      }
   };

   protected final EventActionType eaDelegate;

   public IEventActionAdapter(EventActionType target)
   {
      super(target);

      this.eaDelegate = target;
   }

   public PluggableType getType()
   {
      return (IEventActionType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            eaDelegate.getMetaType(), IEventActionTypeAdapter.FACTORY);
   }

   public EventActionContext getContext()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setActionType(IEventActionType arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
