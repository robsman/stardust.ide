/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.event;

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;

public class BoundaryEvent2Stardust {

	public static final String BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX = "_BSER";
	public static final String BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX = "_BSHR";

	public static String getBoundaryEventHappyPathRouteId(ActivityType eventHolder) {
		return eventHolder.getId() + BOUNDARY_SPLIT_HAPPY_ROUTE_POSTFIX;
	}

	public static String getBoundaryEventEventPathRouteId(BoundaryEvent event) {
		return event.getId() + BOUNDARY_SPLIT_EVENT_ROUTE_POSTFIX;
	}
}
