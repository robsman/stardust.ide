/*******************************************************************************
 * Copyright (c) 2014 ITpearls, AG
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * ITpearls AG - Stardust Runtime Extension
 *
 ******************************************************************************/
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
