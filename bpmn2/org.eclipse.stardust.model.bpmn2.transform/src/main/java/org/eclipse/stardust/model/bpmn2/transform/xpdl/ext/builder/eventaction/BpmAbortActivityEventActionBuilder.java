/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ITpearls AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction;

import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.engine.core.runtime.beans.AbortScope;
import org.eclipse.stardust.model.xpdl.builder.eventaction.AbstractEventActionBuilder;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;


public class BpmAbortActivityEventActionBuilder
      extends AbstractEventActionBuilder<EventActionType, BpmAbortActivityEventActionBuilder>
{
   public BpmAbortActivityEventActionBuilder(EventHandlerType handler)
   {
      super(handler, F_CWM.createEventActionType());
   }

   @Override
   protected EventActionType finalizeElement()
   {
	  forActionType(PredefinedConstants.ABORT_ACTIVITY_ACTION);
      return super.finalizeElement();
   }

   public static BpmAbortActivityEventActionBuilder newAbortActivityAction(EventHandlerType handler)
   {
      return new BpmAbortActivityEventActionBuilder(handler);
   }

   public BpmAbortActivityEventActionBuilder withScopeRootHierarchy() {
	   AttributeUtil.setAttribute(element, PredefinedConstants.ABORT_ACTION_SCOPE_ATT, AbortScope.ROOT_HIERARCHY);
	   return this;
   }

   public BpmAbortActivityEventActionBuilder withScopeSubHierarchy() {
	   AttributeUtil.setAttribute(element, PredefinedConstants.ABORT_ACTION_SCOPE_ATT, AbortScope.SUB_HIERARCHY);
	   return this;
   }

}
