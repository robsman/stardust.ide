package org.eclipse.bpmn2.modeler.runtime.stardust.adapters.performer;

import java.util.HashMap;
import java.util.Map;

public enum ConditionalPerformerResultEnum {

	USER("user", "User"),
	ORGANISATION_OR_ROLE("modelParticipant", "Organisation/Role"),
	USER_GROUP("userGroup", "User Group"),
	ORGANISATION_OR_ROLE_OR_USER_GROUP("modelParticipantOrUserGroup", "Organisation/Role or User Group");

	private String key;
	private String label;

	private ConditionalPerformerResultEnum(String key, String label) {
		this.key = key;
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
		for (ConditionalPerformerResultEnum v : values()) {
			choices.put(v.label, v.key);
		}
		return choices;
	}

	public static ConditionalPerformerResultEnum forKey(String value) {
		for (ConditionalPerformerResultEnum v : values()) {
			if (v.key.equals(value)) return v;
		}
		return null;
	}

}
