package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data;

public enum DataTypeEnum {
	DOCUMENT("dmsDocument"),
	DOCUMENT_LIST("dmsDocumentList"),
	DOCUMENT_FOLDER("dmsFolder"),
	DOCUMENT_FOLDER_LIST("dmsFolderList"),
	ENTITY_BEAN("entity");
	
	private String key;
	private DataTypeEnum(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
	public static DataTypeEnum forKey(String key) {
		if (null == key) return null;
		for (DataTypeEnum t : values()) {
			if (key.equals(t.getKey())) return t;
		}
		return null;
	}
}
