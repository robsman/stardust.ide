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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.trigger;


/**
 * @author Simon Nikles
 *
 */
public enum TriggerAppTypeEnum {

	JMS("jms", "JMS Trigger")
	;

	private String key;
	public String displayName;

	private TriggerAppTypeEnum(String key, String displayName) {
		this.setKey(key);
		this.displayName = displayName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static TriggerAppTypeEnum forKey(String key) {
		if (null == key) return null;
		for (TriggerAppTypeEnum t : values()) {
			if (key.equals(t.key)) return t;
		}
		return null;
	}

}
