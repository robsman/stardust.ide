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

import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EscalationEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.IntermediateAndEndEventDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;

public class EndEvent2Stardust extends NativeIntermediateEvent2Stardust {

	private BpmnModelQuery bpmnquery;

	public EndEvent2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
		bpmnquery = new BpmnModelQuery(logger);
	}

	public void addEndEvent(EndEvent event, FlowElementsContainer container, Map<String, String> predefinedDataForId) {
		logger.debug("addEndEvent " + event);

		EventDefinition def = bpmnquery.getFirstEventDefinition(event);
		if (!checkAndReportElementSupport(event, def, container)) return;
		addEndEvent(event, container);
//		new IntermediateAndEndEventDataFlow2Stardust(carnotModel, failures).addDataFlows(event, container, predefinedDataForId);
	}

    private boolean checkAndReportElementSupport(EndEvent event, EventDefinition def, FlowElementsContainer container) {
		int eventDefCount = bpmnquery.countEventDefinitions(event);
		if (eventDefCount > 1) {
			failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "EndEvent - Multiple Event definitions " + event.getId());
			return false;
		}
		if (def== null) {
			// route element - just to simplify handling of conditional flows
			return true;
		} else if (def instanceof MessageEventDefinition) {
			return true;
		} else if (def instanceof TerminateEventDefinition) {
			return true;
    	} else if (def instanceof ErrorEventDefinition) {
			return true;
    	}  else if (def instanceof EscalationEventDefinition) {
			return true;
    	}  else {
			failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "EndEvent " + event.getId()
					+ " EventDefinition " + def.getClass().getName());
			return false;
		}
	}
}
