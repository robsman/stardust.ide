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
import org.eclipse.stardust.model.xpdl.builder.eventaction.AbstractEventActionBuilder;
import org.eclipse.stardust.model.xpdl.carnot.EventActionType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;


public class AbortProcessEventActionBuilder
      extends AbstractEventActionBuilder<EventActionType, AbortProcessEventActionBuilder>
{
   public AbortProcessEventActionBuilder(EventHandlerType handler)
   {
      super(handler, F_CWM.createEventActionType());
   }

   @Override
   protected EventActionType finalizeElement()
   {
	  forActionType(PredefinedConstants.ABORT_PROCESS_ACTION);
      return super.finalizeElement();
   }

   public static AbortProcessEventActionBuilder newAbortProcessAction(EventHandlerType handler)
   {
      return new AbortProcessEventActionBuilder(handler);
   }

}
