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

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.core.merrimac.clad.AbstractListComposite;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.ui.property.events.CommonEventDetailComposite;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustStartEventType;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Simon Nikles
 *
 */
public class StardustCommonEventDetailComposite extends CommonEventDetailComposite {

	private final EStructuralFeature throwEventDef = Bpmn2Package.eINSTANCE.getThrowEvent_EventDefinitions();
	private final EStructuralFeature throwEventDefRef = Bpmn2Package.eINSTANCE.getThrowEvent_EventDefinitionRefs();
	private final EStructuralFeature catchEventDef = Bpmn2Package.eINSTANCE.getCatchEvent_EventDefinitions();
	private final EStructuralFeature catchEventDefRef = Bpmn2Package.eINSTANCE.getCatchEvent_EventDefinitionRefs();

	public StardustCommonEventDetailComposite(Composite parent, int style) {
		super(parent, style);
	}
	public StardustCommonEventDetailComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public void createBindings(EObject be) {
		super.createBindings(be);
		if (be instanceof StartEvent) {
			final StartEvent startEvent = (StartEvent)be;
			if (startEvent.getEventDefinitions().size() > 0) {
				final List<StardustStartEventType> startExtensions = ModelDecorator.getAllExtensionAttributeValues(be, StardustStartEventType.class);
				if (startExtensions.size() > 0) {
					RecordingCommand command = new RecordingCommand(editingDomain) {
						@Override
						protected void doExecute() {
							for (StardustStartEventType eventExt : startExtensions) {
								EcoreUtil.remove(eventExt);
							}
						}
					};
					editingDomain.getCommandStack().execute(command);
				}
			}
		}
	}

	@Override
	protected AbstractListComposite bindList(final EObject object, EStructuralFeature feature, EClass listItemClass) {
		if (throwEventDef.equals(feature) || catchEventDef.equals(feature)) {
			eventsTable = new StardustEventDefinitionListComposite(this, (Event)object);
			eventsTable.bindList(object, feature);
			return eventsTable;
		} else if (throwEventDefRef.equals(feature) || catchEventDefRef.equals(feature)) {
			return null;
		}
		return super.bindList(object, feature, listItemClass);
	}

}
