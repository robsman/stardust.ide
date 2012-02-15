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

import org.eclipse.stardust.engine.api.model.IApplicationContext;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.spi.AccessPoint;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.ContextType;


public class IApplicationContextAdapter extends AbstractIdentifiableModelElementAdapter
      implements IApplicationContext
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ContextType) ? new IApplicationContextAdapter(
               (ContextType) adaptee) : null;
      }
   };

   protected final ContextType acDelegate;

   public IApplicationContextAdapter(ContextType target)
   {
      super(target);

      this.acDelegate = target;
   }

   public PluggableType getType()
   {
      return (PluggableType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            acDelegate.getType(), IApplicationContextTypeAdapter.FACTORY);
   }

   public Iterator getAllAccessPoints()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }
   
   public Iterator getAllInAccessPoints()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }
   
   public Iterator getAllOutAccessPoints()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }
   
   public Iterator getAllPersistentAccessPoints()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }
   
   public AccessPoint findAccessPoint(String id)
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public String getProviderClass()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }
}
