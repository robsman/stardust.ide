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
import java.util.Vector;

import org.eclipse.stardust.engine.api.model.IModelParticipant;
import org.eclipse.stardust.engine.api.model.IOrganization;
import org.eclipse.stardust.engine.core.runtime.beans.IUser;
import org.eclipse.stardust.engine.core.runtime.beans.IUserGroup;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelParticipantUtils;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;


public class IOrganizationAdapter extends AbstractModelParticipantAdapter
      implements IOrganization
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof OrganizationType) ? new IOrganizationAdapter(
               (OrganizationType) adaptee) : null;
      }
   };

   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   protected final OrganizationType oDelegate;

   public IOrganizationAdapter(OrganizationType target)
   {
      super(target);

      this.oDelegate = target;
   }

   public Iterator getAllParticipants()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            ModelParticipantUtils.getSubParticipants(oDelegate),
            AbstractModelParticipantAdapter.FACTORY).iterator();
   }

   public Iterator getSubOrganizations()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            ModelParticipantUtils.getSubOrganizations(oDelegate),
            IOrganizationAdapter.FACTORY).iterator();
   }

   public IModelParticipant findParticipant(String id)
   {
      return (IModelParticipant) ModelApiPlugin.getAdapterRegistry()
            .getAdapter(ModelParticipantUtils.findSubParticipant(oDelegate, id),
                  AbstractModelParticipantAdapter.FACTORY);
   }

   public boolean isDirectOrIndirectSubOrganizationOf(IOrganization testOrganization)
   {
      boolean result = false;

      ModelType model = ModelUtils.findContainingModel(oDelegate);
      if (null != model)
      {
         OrganizationType cwmOrg = (OrganizationType) ModelUtils.findIdentifiableElement(
               model, PKG_CWM.getModelType_Organization(), testOrganization.getId());

         if (null != cwmOrg)
         {
            result = ModelParticipantUtils.isSubOrganizationOf(oDelegate, cwmOrg, true);
         }
      }

      return result;
   }

public void addToParticipants(IModelParticipant arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void addToSubOrganizations(IOrganization arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void removeFromParticipants(IModelParticipant arg0) {
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
