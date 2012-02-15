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

import org.eclipse.stardust.common.Unknown;
import org.eclipse.stardust.engine.api.model.IOrganization;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelParticipantUtils;


public abstract class AbstractModelParticipantAdapter
      extends AbstractIdentifiableModelElementAdapter
      implements org.eclipse.stardust.engine.api.model.IModelParticipant
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         Object result = null;
         if (adaptee instanceof OrganizationType)
         {
            result = IOrganizationAdapter.FACTORY.createAdapter(adaptee);
         }
         else if (adaptee instanceof RoleType)
         {
            result = IRoleAdapter.FACTORY.createAdapter(adaptee);
         }
         else if (adaptee instanceof ConditionalPerformerType)
         {
            result = IConditionalPerformerAdapter.FACTORY.createAdapter(adaptee);
         }
         return result;
      }
   };

   protected final org.eclipse.stardust.model.xpdl.carnot.IModelParticipant mpDelegate;

   public AbstractModelParticipantAdapter(IModelParticipant target)
   {
      super(target);

      this.mpDelegate = target;
   }

   public Iterator getAllOrganizations()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            ModelParticipantUtils.getAssociatedOrganizations(mpDelegate),
            IOrganizationAdapter.FACTORY).iterator();
   }

   public IOrganization findOrganization(String id)
   {
      return (IOrganization) ModelApiPlugin.getAdapterRegistry().getAdapter(
            ModelParticipantUtils.findAssociatedOrganization(mpDelegate, id),
            IOrganizationAdapter.FACTORY);
   }

   public Iterator getAllTopLevelOrganizations()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(
            ModelParticipantUtils.findAssociatedRootOrganizations(mpDelegate),
            IOrganizationAdapter.FACTORY).iterator();
   }

   public Iterator getAllParticipants()
   {
      return Collections.EMPTY_LIST.iterator();
   }

   public int getCardinality()
   {
      return Unknown.INT;
   }
}
