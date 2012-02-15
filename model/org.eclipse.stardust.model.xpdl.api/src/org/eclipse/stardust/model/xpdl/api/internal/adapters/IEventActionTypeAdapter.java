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

import org.eclipse.stardust.engine.api.model.EventActionContext;
import org.eclipse.stardust.engine.api.model.IEventActionType;
import org.eclipse.stardust.engine.api.model.IEventConditionType;
import org.eclipse.stardust.model.API_Messages;
import org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType;



public class IEventActionTypeAdapter extends AbstractIdentifiableModelElementAdapter
      implements IEventActionType
{
   public static final IAdapterFactory FACTORY = new IAdapterFactory()
   {
      public Object createAdapter(Object adaptee)
      {
         return (adaptee instanceof EventActionTypeType) ? new IEventActionTypeAdapter(
               (EventActionTypeType) adaptee) : null;
      }
   };

   protected final EventActionTypeType eatDelegate;

   public IEventActionTypeAdapter(EventActionTypeType target)
   {
      super(target);

      this.eatDelegate = target;
   }

   public boolean isProcessAction()
   {
      return eatDelegate.isProcessAction();
   }

   public boolean isActivityAction()
   {
      return eatDelegate.isActivityAction();
   }

   public Iterator getSupportedConditionTypes()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public Iterator getUnsupportedContexts()
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public boolean supports(EventActionContext type)
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

   public boolean supports(IEventConditionType type)
   {
       // TODO implement
       throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);   

   }

public void addSupportedConditionType(IEventConditionType arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void addUnsupportedContext(EventActionContext arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void removeAllSupportedConditionTypes() {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void removeAllUnsupportedContexts() {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setActivityAction(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}

public void setProcessAction(boolean arg0) {
    // TODO implement this method!
    throw new RuntimeException(API_Messages.EXC_NOT_IMPLEMENTED_YET);
    
}
}
