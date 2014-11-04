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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.accesspoint;

public enum AcessPointDataTypes {
	PRIMITIVE_TYPE("primitive", "carnot:engine:type", "Primitive Data"),
	STRUCT_TYPE("struct", "carnot:engine:dataType", "Structured Data"),
	SERIALIZABLE_TYPE("serializable", "carnot:engine:className", "Serializable");

	private String key;
	private String type;
	public String displayName;

	private AcessPointDataTypes(String key, String type, String displayName) {
		this.setKey(key);
		this.displayName = displayName;
		this.key = key;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public static AcessPointDataTypes forKey(String key) {
		if (null == key) return null;
		for (AcessPointDataTypes t : values()) {
			if (key.equals(t.key)) return t;
		}
		return null;
	}
}
