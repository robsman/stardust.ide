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

import org.eclipse.stardust.engine.api.model.IApplicationContextType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType;



public class IApplicationContextTypeAdapter
      extends AbstractIdentifiableModelElementAdapter implements IApplicationContextType
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ApplicationContextTypeType)
               ? new IApplicationContextTypeAdapter((ApplicationContextTypeType) adaptee)
               : null;
      }
   };

   protected final ApplicationContextTypeType actDelegate;

   public IApplicationContextTypeAdapter(ApplicationContextTypeType target)
   {
      super(target);

      this.actDelegate = target;
   }

   public boolean hasApplicationPath()
   {
      return actDelegate.isHasApplicationPath();
   }

   public boolean hasMappingId()
   {
      return actDelegate.isHasMappingId();
   }

public void setHasApplicationPath(boolean arg0) {
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setHasMappingId(boolean arg0) {
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
