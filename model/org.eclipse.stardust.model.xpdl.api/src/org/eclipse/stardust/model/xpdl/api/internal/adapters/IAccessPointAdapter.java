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

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.engine.api.model.IAccessPoint;
import org.eclipse.stardust.engine.api.model.IDataType;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;


public class IAccessPointAdapter extends AbstractIdentifiableModelElementAdapter
      implements IAccessPoint
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof AccessPointType) ? new IAccessPointAdapter(
               (AccessPointType) adaptee) : null;
      }
   };

   protected final AccessPointType apDelegate;

   public IAccessPointAdapter(AccessPointType delegate)
   {
      super(delegate);

      this.apDelegate = delegate;
   }

   /**
    * @category AccessPoint
    */
   public Direction getDirection()
   {
      return ConversionUtils.convert(apDelegate.getDirection());
   }

   /**
    * @category AccessPoint
    */
   public PluggableType getType()
   {
      return (IDataType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            apDelegate.getMetaType(), IDataTypeAdapter.FACTORY);
   }

public void setDataType(IDataType arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
