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

import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.SERIALIZABLE_CLASS_NAME;
import static org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes.STRUCTURED_DATA_TYPE;

import org.eclipse.bpmn2.modeler.runtime.stardust.common.attributes.apps.AccessPointAttributes;
import org.eclipse.bpmn2.modeler.runtime.stardust.composites.Messages;

public enum AcessPointDataTypes {

	PRIMITIVE_TYPE("primitive", AccessPointAttributes.PRIMITIVE_TYPE.attributeName(), Messages.composite_application_section_AccessPoint_select_primitiveData),
	STRUCT_TYPE("struct", STRUCTURED_DATA_TYPE.attributeName(), Messages.composite_application_section_AccessPoint_select_structData),
	SERIALIZABLE_TYPE("serializable", SERIALIZABLE_CLASS_NAME.attributeName(), Messages.composite_application_section_AccessPoint_select_serializableData);

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
