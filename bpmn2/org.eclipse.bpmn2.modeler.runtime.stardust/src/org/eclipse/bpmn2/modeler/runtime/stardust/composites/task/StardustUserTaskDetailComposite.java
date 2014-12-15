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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.task;

import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractPropertiesProvider;
import org.eclipse.bpmn2.modeler.ui.property.tasks.ActivityDetailComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustUserTaskDetailComposite extends ActivityDetailComposite {

	public StardustUserTaskDetailComposite(Composite parent, int style) {
		super(parent, style);
	}

	public StardustUserTaskDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider==null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"anyAttribute", //$NON-NLS-1$
						"calledElementRef", // only used in CallActivity //$NON-NLS-1$
						"calledChoreographyRef", // only used in CallChoreography //$NON-NLS-1$
						"calledCollaborationRef", // only used in CallConversation //$NON-NLS-1$
						"implementation", // used by BusinessRuleTask, SendTask, ReceiveTask, UserTask and ServiceTask //$NON-NLS-1$
						"operationRef", // SendTask, ReceiveTask, ServiceTask //$NON-NLS-1$
						"messageRef", // SendTask, ReceiveTask //$NON-NLS-1$
						"scriptFormat", "script", // ScriptTask //$NON-NLS-1$ //$NON-NLS-2$
						"instantiate", // ReceiveTask //$NON-NLS-1$
						//"startQuantity", // these are "MultipleAssignments" features and should be used
						//"completionQuantity", // with caution, according to the BPMN 2.0 spec
						"triggeredByEvent", //$NON-NLS-1$
						"isForCompensation", //$NON-NLS-1$
						"ordering", //$NON-NLS-1$
						"cancelRemainingInstances", //$NON-NLS-1$
						"completionCondition", //$NON-NLS-1$
						"method", //$NON-NLS-1$
						"protocol", //$NON-NLS-1$
						"loopCharacteristics", //$NON-NLS-1$
						"resources", //$NON-NLS-1$
						"properties", //$NON-NLS-1$
				};

				@Override
				public String[] getProperties() {
					return properties;
				}
			};
		}
		return propertiesProvider;
	}

}
