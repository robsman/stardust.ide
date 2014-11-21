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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms;

import java.util.HashMap;
import java.util.Map;

public enum JmsMessageType {

	MAP("Map", "Map message"),
	STREAM("Stream", "Stream message"),
	TEXT("Text", "Text message"),
	OBJECT("Object", "Object message");

	private String key;
	private String label;

	private JmsMessageType(String key, String label) {
		this.key = key;
		this.label = label;
	}

	public String getKey() {
		return key;
	}

	public String getLabel() {
		return label;
	}

	public static JmsMessageType getDefault() {
		return MAP;
	}

	public static Map<String, String> getChoices() {
		final Map<String, String> choices = new HashMap<String, String>();
		for (JmsMessageType v : values()) {
			choices.put(v.label, v.key);
		}
		return choices;
	}

}
