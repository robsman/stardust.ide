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

import org.eclipse.stardust.common.Direction;
import org.eclipse.stardust.engine.api.model.IData;
import org.eclipse.stardust.engine.api.model.IDataPath;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;


public class IDataPathAdapter extends AbstractIdentifiableModelElementAdapter
      implements IDataPath
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof DataPathType) ? new IDataPathAdapter(
               (DataPathType) adaptee) : null;
      }
   };

   protected final DataPathType dpDelegate;

   public IDataPathAdapter(DataPathType target)
   {
      super(target);

      this.dpDelegate = target;
   }

   /**
    * @category IDataPath
    */
   public boolean isDescriptor()
   {
      return dpDelegate.isDescriptor();
   }

   /**
    * @category IDataPath
    */
   public Direction getDirection()
   {
      return ConversionUtils.convert(dpDelegate.getDirection());
   }

   /**
    * @category IDataPath
    */
   public IData getData()
   {
      return (IData) ModelApiPlugin.getAdapterRegistry().getAdapter(
            dpDelegate.getData(), IDataAdapter.FACTORY);
   }

   /**
    * @category IDataPath
    */
   public String getAccessPath()
   {
      return dpDelegate.getDataPath();
   }

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setAccessPath(String arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setData(IData arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setDescriptor(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setDirection(Direction arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
