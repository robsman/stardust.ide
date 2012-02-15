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

import org.eclipse.stardust.engine.api.model.IApplicationType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType;



public class IApplicationTypeAdapter extends AbstractIdentifiableModelElementAdapter
      implements IApplicationType
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ApplicationTypeType) ? new IApplicationTypeAdapter(
               (ApplicationTypeType) adaptee) : null;
      }
   };

   protected final ApplicationTypeType atDelegate;

   public IApplicationTypeAdapter(ApplicationTypeType target)
   {
      super(target);

      this.atDelegate = target;
   }

   public boolean isSynchronous()
   {
      return atDelegate.isSynchronous();
   }

public void setSynchronous(boolean arg0) {
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
