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

import java.util.List;

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IDataType;
import org.eclipse.stardust.engine.api.model.PluggableType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.DataType;


public class IDataAdapter extends AbstractIdentifiableModelElementAdapter
      implements IData
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof DataType)
               ? new IDataAdapter((DataType) adaptee)
               : null;
      }
   };

   protected final DataType dDelegate;

   public IDataAdapter(DataType target)
   {
      super(target);

      this.dDelegate = target;
   }

   /**
    * @category IData
    */
   public PluggableType getType()
   {
      return (IDataType) ModelApiPlugin.getAdapterRegistry().getAdapter(
            dDelegate.getMetaType(), IDataTypeAdapter.FACTORY);
   }

   /**
    * @category IData
    */
   public Direction getDirection()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

public void checkConsistency(List arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setDataType(IDataType arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
