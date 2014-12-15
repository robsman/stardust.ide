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

import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.JMSTriggerModelProvider.createJmsTriggerModel;
import static org.eclipse.bpmn2.modeler.runtime.stardust.adapters.application.TriggerModelMaintainer.removeTriggerModel;

import java.util.Hashtable;

import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.trigger.TriggerAppTypeEnum;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustInterfaceType;

/**
 * @author Simon Nikles
 *
 */
public class StardustTriggerInterfaceExtendedPropertiesAdapter extends ExtendedPropertiesAdapter<StardustInterfaceType> {

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

}
