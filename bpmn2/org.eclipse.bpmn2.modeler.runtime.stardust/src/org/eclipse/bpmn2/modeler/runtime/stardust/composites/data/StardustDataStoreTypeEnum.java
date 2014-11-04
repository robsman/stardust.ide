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
package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

/**
 * @author Simon Nikles
 *
 */
public enum StardustDataStoreTypeEnum {

	DOCUMENT(true, "dmsDocument", "DMS Document", "org.eclipse.stardust.engine.api.runtime.Document"),
	DOCUMENT_LIST(true, "dmsDocumentList", "DMS Document List", "java.util.List"),
	DOCUMENT_FOLDER(true, "dmsFolder", "DMS Folder", "org.eclipse.stardust.engine.api.runtime.Folder"),
	DOCUMENT_FOLDER_LIST(true, "dmsFolderList", "DMS Folder List", "java.util.List"),
	ENTITY_BEAN(false, "entity", "Entity Bean", "");

	private boolean active;
	private String key;
	private String displayName;
	private String defaultClass;

	private StardustDataStoreTypeEnum(boolean active, String key, String displayName, String defaultClass) {
		this.active = active;
		this.key = key;
		this.displayName = displayName;
		this.defaultClass = defaultClass;
	}

	public boolean isActive() {
		return active;
	}

	public String getKey() {
		return key;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDefaultClass() {
		return defaultClass;
	}

	public static StardustDataStoreTypeEnum forKey(String string) {
		for (StardustDataStoreTypeEnum t : values()) {
			if (t.key.equals(string)) return t;
		}
		return null;
	}

}
