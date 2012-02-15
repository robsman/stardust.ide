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

import org.eclipse.stardust.engine.api.model.ITriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType;


public class ITriggerTypeAdapter extends AbstractIdentifiableModelElementAdapter
      implements ITriggerType
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof TriggerTypeType) ? new ITriggerTypeAdapter(
               (TriggerTypeType) adaptee) : null;
      }
   };

   protected final TriggerTypeType ttDelegate;

   public ITriggerTypeAdapter(TriggerTypeType target)
   {
      super(target);

      this.ttDelegate = target;
   }

   public boolean isPullTrigger()
   {
      return ttDelegate.isPullTrigger();
   }
}
