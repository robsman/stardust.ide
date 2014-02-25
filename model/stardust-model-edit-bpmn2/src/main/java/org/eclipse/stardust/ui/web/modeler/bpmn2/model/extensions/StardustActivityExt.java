package org.eclipse.stardust.ui.web.modeler.bpmn2.model.extensions;

import static java.util.Collections.emptyList;

import java.util.List;

public class StardustActivityExt extends ModelElementExt {

	public boolean hibernateOnCreation;

	public List<StardustEventHandlerExt> eventHandlers = emptyList();

}
