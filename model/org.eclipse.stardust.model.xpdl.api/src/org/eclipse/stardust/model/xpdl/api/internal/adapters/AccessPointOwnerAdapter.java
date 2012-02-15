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

import org.eclipse.stardust.common.Predicate;
import org.eclipse.stardust.engine.api.model.AccessPointOwner;
import org.eclipse.stardust.model.spi.AccessPoint;
import org.eclipse.stardust.model.xpdl.api.ModelApiPlugin;
import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner;
import org.eclipse.stardust.model.xpdl.carnot.util.AccessPointUtil;


public class AccessPointOwnerAdapter implements AccessPointOwner
{
   public static final Predicate PRED_IN_ACCESS_POINT = new Predicate()
   {
      public boolean accept(Object arg)
      {
         return (arg instanceof AccessPointType)
               && AccessPointUtil.isIn(((AccessPointType) arg).getDirection());
      }
   };

   public static final Predicate PRED_OUT_ACCESS_POINT = new Predicate()
   {
      public boolean accept(Object arg)
      {
         return (arg instanceof AccessPointType)
               && AccessPointUtil.isOut(((AccessPointType) arg).getDirection());
      }
   };

   private static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   protected final IAccessPointOwner iapoDelegate;

   public AccessPointOwnerAdapter(IAccessPointOwner delegate)
   {
      this.iapoDelegate = delegate;
   }

   public Iterator getAllAccessPoints()
   {
      return ModelApiPlugin.getAdapterRegistry()
            .getAdapters(iapoDelegate, PKG_CWM.getIAccessPointOwner_AccessPoint(),
                  IAccessPointAdapter.FACTORY)
            .iterator();
   }

   public Iterator getAllInAccessPoints()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(iapoDelegate,
            PKG_CWM.getIAccessPointOwner_AccessPoint(), PRED_IN_ACCESS_POINT,
            IAccessPointAdapter.FACTORY).iterator();
   }

   public Iterator getAllOutAccessPoints()
   {
      return ModelApiPlugin.getAdapterRegistry().getAdapters(iapoDelegate,
            PKG_CWM.getIAccessPointOwner_AccessPoint(), PRED_OUT_ACCESS_POINT,
            IAccessPointAdapter.FACTORY).iterator();
   }

   public AccessPoint findAccessPoint(String id)
   {
      return (AccessPoint) ModelApiPlugin.getAdapterRegistry().getAdapter(
            iapoDelegate, PKG_CWM.getIAccessPointOwner_AccessPoint(), id,
            IAccessPointAdapter.FACTORY);
   }

   public Iterator getAllPersistentAccessPoints()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public String getProviderClass()
   {
      // TODO Auto-generated method stub
      return null;
   }
}
