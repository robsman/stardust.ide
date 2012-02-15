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

import org.eclipse.stardust.common.Unknown;
import org.eclipse.stardust.engine.api.model.IModelParticipant;
import org.eclipse.stardust.engine.api.model.IRole;
import org.eclipse.stardust.engine.core.runtime.beans.IUser;
import org.eclipse.stardust.engine.core.runtime.beans.IUserGroup;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;


public class IRoleAdapter extends AbstractModelParticipantAdapter implements IRole
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof RoleType)
               ? new IRoleAdapter((RoleType) adaptee)
               : null;
      }
   };

   protected final RoleType rDelegate;

   public IRoleAdapter(RoleType target)
   {
      super(target);

      this.rDelegate = target;
   }

   public int getCardinality()
   {
      return (0 != rDelegate.getCardinality()) ? rDelegate.getCardinality() : Unknown.INT;
   }

public void setCardinality(int arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void checkConsistency(Vector arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public boolean isAuthorized(IModelParticipant arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public boolean isAuthorized(IUser arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public boolean isAuthorized(IUserGroup arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
}

public void setDescription(String arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
