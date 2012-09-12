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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.helper;

import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.EventDefinition;

/**
 * @author Simon Nikles
 *
 */
public class BpmnModelQuery {

	public BpmnModelQuery() {
	
	}

	public int countEventDefinitions(CatchEvent event) {
		if (event.getEventDefinitions() != null) return event.getEventDefinitions().size();
		return 0;
	}

	public EventDefinition getFirstEventDefinition(CatchEvent event) {
		if (countEventDefinitions(event) > 0) {
			return event.getEventDefinitions().get(0);
		}
		return null;
	}
}
