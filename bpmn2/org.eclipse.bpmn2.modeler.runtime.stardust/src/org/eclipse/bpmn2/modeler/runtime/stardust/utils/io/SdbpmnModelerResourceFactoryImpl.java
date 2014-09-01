package org.eclipse.bpmn2.modeler.runtime.stardust.utils.io;

import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

public class SdbpmnModelerResourceFactoryImpl extends Bpmn2ModelerResourceFactoryImpl {

	@Override
	public Resource createResource(URI uri) {
		SdbpmnModelerResourceImpl resource = new SdbpmnModelerResourceImpl(uri);
		return resource;
	}

}
