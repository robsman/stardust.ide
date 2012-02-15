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

import org.eclipse.stardust.engine.api.model.EventType;
import org.eclipse.stardust.engine.api.model.IEventConditionType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType;



public class IEventConditionTypeAdapter extends AbstractIdentifiableModelElementAdapter
      implements IEventConditionType
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof EventConditionTypeType)
               ? new IEventConditionTypeAdapter((EventConditionTypeType) adaptee)
               : null;
      }
   };

   protected final EventConditionTypeType ectDelegate;

   public IEventConditionTypeAdapter(EventConditionTypeType target)
   {
      super(target);

      this.ectDelegate = target;
   }

   public boolean hasProcessInstanceScope()
   {
      return ectDelegate.isProcessCondition();
   }

   public boolean hasActivityInstanceScope()
   {
      return ectDelegate.isActivityCondition();
   }

   public boolean isAutoBinding()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public boolean isDisableOnMatch()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public EventType getImplementation()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

public void setActivityInstanceScope(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setAutoBinding(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setDisableOnMatch(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setProcessInstanceScope(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
