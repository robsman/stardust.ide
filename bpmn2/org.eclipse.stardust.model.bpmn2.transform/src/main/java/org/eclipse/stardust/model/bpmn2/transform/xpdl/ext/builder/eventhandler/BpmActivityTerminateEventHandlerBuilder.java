/*******************************************************************************
 * Copyright (c) 2012 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SunGard CSA LLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventhandler;

import org.eclipse.stardust.common.Period;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.ext.builder.eventaction.AbortProcessEventActionBuilder;
import org.eclipse.stardust.model.xpdl.builder.eventhandler.AbstractEventHandlerBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.EventHandlerType;
import org.eclipse.stardust.model.xpdl.carnot.util.AttributeUtil;

public class BpmActivityTerminateEventHandlerBuilder extends AbstractEventHandlerBuilder<BpmActivityTerminateEventHandlerBuilder>
{

	private static final Period oneSecond = new Period((short)0, (short)0, (short)0, (short)0, (short)0, (short)1);

	public BpmActivityTerminateEventHandlerBuilder() {}

	@Override
	protected EventHandlerType finalizeElement()
	{
		forConditionType(PredefinedConstants.TIMER_CONDITION);
		element.setAutoBind(true);
		element.setConsumeOnMatch(true); 
		createTerminateAction();
		AttributeUtil.setBooleanAttribute(element, PredefinedConstants.TIMER_CONDITION_USE_DATA_ATT, false);
		AttributeUtil.setAttribute(element, PredefinedConstants.TIMER_PERIOD_ATT, "Period", oneSecond.toString());
		
		return super.finalizeElement();
	}

	private static BpmActivityTerminateEventHandlerBuilder newActivityTerminateEventHandler()
	{
		return new BpmActivityTerminateEventHandlerBuilder();
	}

	public static BpmActivityTerminateEventHandlerBuilder newActivityTimerEventHandler(ActivityType activity)
	{
		activity.setHibernateOnCreation(true);
		return newActivityTerminateEventHandler().forActivity(activity);
	}

	private void createTerminateAction() {
		AbortProcessEventActionBuilder.newAbortProcessAction(element).build();
	}
	
}
