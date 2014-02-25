package org.eclipse.stardust.ui.web.modeler.bpmn2.model.extensions;

import static java.util.Collections.emptyList;

import java.util.List;

public class StardustApplicationExt extends ModelElementExt {

	public boolean interactive;

	public List<StardustAccessPointExt> accessPoints = emptyList();

	public List<StardustContextExt> contexts = emptyList();

}
