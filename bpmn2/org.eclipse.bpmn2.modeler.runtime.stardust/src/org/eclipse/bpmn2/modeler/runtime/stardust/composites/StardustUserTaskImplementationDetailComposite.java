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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites;

import java.util.List;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.DefaultDetailComposite;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustUserTaskImplementationDetailComposite extends DefaultDetailComposite {

	StardustUserTaskType sdUserTask = null;

	/**
	 * @param parent
	 * @param style
	 */
	public StardustUserTaskImplementationDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public StardustUserTaskImplementationDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {"interactiveApplicationRef", "allowsAbortByPerformer"};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}

	@Override
	public void createBindings(EObject be) {
		List<StardustUserTaskType> list = ModelDecorator.getAllExtensionAttributeValues(be, StardustUserTaskType.class);
		if (list.size() > 0) {
			sdUserTask = list.get(0);
		} else {
			sdUserTask= SdbpmnFactory.eINSTANCE.createStardustUserTaskType();
			EStructuralFeature feature = SdbpmnPackage.eINSTANCE.getDocumentRoot_StardustUserTask();
			ModelDecorator.addExtensionAttributeValue(be, feature, sdUserTask, true);
		}
		super.createBindings(sdUserTask);
	}

}
