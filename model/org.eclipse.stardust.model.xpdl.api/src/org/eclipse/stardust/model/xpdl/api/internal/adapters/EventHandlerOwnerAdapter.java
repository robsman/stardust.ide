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

import org.eclipse.stardust.engine.api.model.EventHandlerOwner;
import org.eclipse.stardust.engine.api.model.IEventHandler;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;


public class EventHandlerOwnerAdapter extends AbstractModelElementAdapter
      implements EventHandlerOwner
{
   protected IEventHandlerOwner iehoDelegate;

   public EventHandlerOwnerAdapter(IEventHandlerOwner delegate)
   {
      super((delegate instanceof IModelElement) ? (IModelElement) delegate : null);

      this.iehoDelegate = delegate;
   }

   public Iterator getAllEventHandlers()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            iehoDelegate.getEventHandler(), IEventHandlerAdapter.FACTORY).iterator();
   }

   public IEventHandler findHandlerById(String id)
   {
      return (IEventHandler) ModelApiPlugin.getAdapterRegistry().getAdapter(
            iehoDelegate.getEventHandler(), id, IEventHandlerAdapter.FACTORY);
   }
}
