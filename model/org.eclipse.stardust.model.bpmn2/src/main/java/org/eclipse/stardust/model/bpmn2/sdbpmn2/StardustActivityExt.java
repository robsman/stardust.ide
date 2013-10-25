package org.eclipse.stardust.model.bpmn2.sdbpmn2;

import static java.util.Collections.emptyList;

import java.util.List;

public class StardustActivityExt extends ModelElementExt {

	public boolean hibernateOnCreation;

	public List<StardustEventHandlerExt> eventHandlers = emptyList();

}
