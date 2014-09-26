package org.eclipse.bpmn2.modeler.runtime.stardust.composites.camel.accesspoint;

public enum CamelAcessPointDataTypes {
	PRIMITIVE_TYPE("primitive", "carnot:engine:type", "Primitive Data"),
	STRUCT_TYPE("struct", "carnot:engine:dataType", "Structured Data"),
	SERIALIZABLE_TYPE("serializable", "carnot:engine:className", "Serializable");
	
	private String key;
	private String type;
	public String displayName;

	private CamelAcessPointDataTypes(String key, String type, String displayName) {
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

	public static CamelAcessPointDataTypes forKey(String key) {
		if (null == key) return null;
		for (CamelAcessPointDataTypes t : values()) {
			if (key.equals(t.key)) return t;
		}
		return null;
	}	
}
