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

import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.stardust.common.CompareHelper;
import org.eclipse.stardust.common.Unknown;
import org.eclipse.stardust.engine.api.model.IModelParticipant;
import org.eclipse.stardust.engine.api.model.IModeler;
import org.eclipse.stardust.engine.api.model.IOrganization;
import org.eclipse.stardust.engine.core.runtime.beans.IUser;
import org.eclipse.stardust.engine.core.runtime.beans.IUserGroup;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.carnot.ModelerType;


public class IModelerAdapter extends AbstractIdentifiableModelElementAdapter
      implements IModeler
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof ModelerType) ? new IModelerAdapter(
               (ModelerType) adaptee) : null;
      }
   };

   protected final ModelerType mDelegate;

   public IModelerAdapter(ModelerType target)
   {
      super(target);

      this.mDelegate = target;
   }

   public String getPassword()
   {
      return mDelegate.getPassword();
   }

   public boolean checkPassword(String password)
   {
      return CompareHelper.areEqual(mDelegate.getPassword(), password);
   }

   public String getEMail()
   {
      return mDelegate.getEmail();
   }

   public Iterator getAllParticipants()
   {
      return Collections.EMPTY_LIST.iterator();
   }

   public Iterator getAllOrganizations()
   {
      return Collections.EMPTY_LIST.iterator();
   }

   public Iterator getAllTopLevelOrganizations()
   {
      return Collections.EMPTY_LIST.iterator();
   }

   public IOrganization findOrganization(String id)
   {
      return null;
   }

   public int getCardinality()
   {
      return Unknown.INT;
   }

public void setEMail(String arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setPassword(String arg0) {
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
