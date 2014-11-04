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
package org.eclipse.bpmn2.modeler.runtime.stardust.adapters;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.adapters.common.PropertyAdapterCommons;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.trigger.TriggerAppTypeEnum;
import org.eclipse.bpmn2.modeler.runtime.stardust.utils.StardustApplicationConfigurationCleaner;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnFactory;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTriggerType;

/**
 * @author Simon Nikles
 *
 */
public class StardustTriggerInterfaceExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustInterfaceType> {
	private static long triggerId = 1;
	private static Hashtable<String, Object> choices = null;

	public StardustTriggerInterfaceExtendedPropertiesAdapter(AdapterFactory adapterFactory, StardustInterfaceType object) {
		super(adapterFactory, object);
	}

	public void internalSetApplicationType(StardustInterfaceType sdInterface, EStructuralFeature feature, Object value, int index) {
		if (null == value) {
			removeTriggerModel(sdInterface);
			return;
		}
		TriggerAppTypeEnum appType = TriggerAppTypeEnum.forKey(value.toString());
		switch(appType) {
		case JMS:
			createJmsTriggerModel(sdInterface);
			break;
		default:
			removeTriggerModel(sdInterface);
			break;
		}
	}

	public Hashtable<String, Object> getChoiceOfValues() {
		if (choices==null) {
			choices = new Hashtable<String, Object>();
			for (TriggerAppTypeEnum type : TriggerAppTypeEnum.values()) {
				choices.put(type.getDisplayName(), type.getKey());
			}
		}
		return choices;
	}

	private void createJmsTriggerModel(StardustInterfaceType sdInterface) {
		removeTriggerModel(sdInterface);
		StardustTriggerType trigger = SdbpmnFactory.eINSTANCE.createStardustTriggerType();
		long triggerId = generateTriggerId();
		trigger.setId("JmsTrigger_" + triggerId);
		trigger.setName("JmsTrigger " + triggerId);
		trigger.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:messageAcceptor", "org.eclipse.stardust.engine.extensions.jms.app.DefaultMessageAcceptor", null));
		trigger.getAttribute().add(PropertyAdapterCommons.createAttributeType("carnot:engine:messageType", "Map", "org.eclipse.stardust.engine.extensions.jms.app.MessageType"));

		sdInterface.setStardustTrigger(trigger);
	}

	private static void removeTriggerModel(StardustInterfaceType sdInterface) {
		boolean recreateEmptyTrigger = null != sdInterface.getStardustTrigger();
		StardustApplicationConfigurationCleaner.INSTANCE.performResetExistingApp(sdInterface);
		if (recreateEmptyTrigger) {
			sdInterface.setStardustTrigger(SdbpmnFactory.eINSTANCE.createStardustTriggerType());
		} else {
			sdInterface.setStardustTrigger(null);
		}
	}

	private static long generateTriggerId() {
		return triggerId++;
	}

}
