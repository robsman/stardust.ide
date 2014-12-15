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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon Nikles
 *
 */
public enum InterfaceTypeEnum {

	APPLICATION("Application"),
	TRIGGER("Process Trigger");

	private String key;
	private String label;

	private InterfaceTypeEnum(String label) {
		this.key = name();
		this.label = label;
	}
	public String getKey() {
		return key;
	}

	public String getLabel() {
		return label;
	}

	public static Map<String, String> getChoices() {
		final Map<String, String> choices = new HashMap<String, String>();
		for (InterfaceTypeEnum v : values()) {
			choices.put(v.label, v.key);
		}
		return choices;
	}

	public static InterfaceTypeEnum forKey(String value) {
		for (InterfaceTypeEnum v : values()) {
			if (v.key.equals(value)) return v;
		}
		return null;
	}

}