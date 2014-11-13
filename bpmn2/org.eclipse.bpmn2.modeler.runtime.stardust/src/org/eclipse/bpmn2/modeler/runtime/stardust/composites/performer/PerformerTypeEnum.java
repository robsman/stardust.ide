package org.eclipse.bpmn2.modeler.runtime.stardust.composites.performer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Simon Nikles
 *
 */
public enum PerformerTypeEnum {

	ROLE("Role"),
	ORGANISATION("Organisation"),
	CONDITIONAL("Conditional Performer");

	private String key;
	private String label;

	private PerformerTypeEnum(String label) {
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
		for (PerformerTypeEnum v : values()) {
			choices.put(v.label, v.key);
		}
		return choices;
	}

	public static PerformerTypeEnum forKey(String value) {
		for (PerformerTypeEnum v : values()) {
			if (v.key.equals(value)) return v;
		}
		return null;
	}
}
