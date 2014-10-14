package org.eclipse.bpmn2.modeler.runtime.stardust.composites.data;

/**
 * @author Simon Nikles
 *
 */
public enum StardustDataObjectTypeEnum {

	PRIMITIVE(true, "primitive", "Primitive Type"),
	STRUCTURED(true, "struct", "Structured Type"),
	SERIALIZABLE(true, "serializable", "Serializable")
	;
	
	private boolean active;
	private String key;
	private String displayName;
	
	private StardustDataObjectTypeEnum(boolean active, String key, String displayName) {
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

	public static StardustDataObjectTypeEnum forKey(String string) {
		for (StardustDataObjectTypeEnum t : values()) {
			if (t.key.equals(string)) return t;
		}
		return null;
	}
	
}
