package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

/**
 * @author Simon Nikles
 *
 */
public enum StardustDataStoreTypeEnum {

	DOCUMENT(true, "dmsDocument", "DMS Document"),
	DOCUMENT_LIST(true, "dmsDocumentList", "DMS Document List"),
	DOCUMENT_FOLDER(true, "dmsFolder", "DMS Folder"),
	DOCUMENT_FOLDER_LIST(true, "dmsFolderList", "DMS Folder List"),
	ENTITY_BEAN(false, "entity", "Entity Bean");
	
	private boolean active;
	private String key;
	private String displayName;
	
	private StardustDataStoreTypeEnum(boolean active, String key, String displayName) {
		this.active = active;
		this.key = key;
		this.displayName = displayName;
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

	public static StardustDataStoreTypeEnum forKey(String string) {
		for (StardustDataStoreTypeEnum t : values()) {
			if (t.key.equals(string)) return t;
		}
		return null;
	}
	
}
