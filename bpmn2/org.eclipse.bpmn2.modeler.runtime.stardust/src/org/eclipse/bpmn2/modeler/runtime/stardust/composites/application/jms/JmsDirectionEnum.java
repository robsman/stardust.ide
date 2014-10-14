package org.eclipse.bpmn2.modeler.runtime.stardust.composites.application.jms;

public enum JmsDirectionEnum {
	
	IN("in", "Receive"),
	OUT("out", "Send"),
	INOUT("inout", "Send & Receive")
	;
	
	private String key;
	private String label;
	
	private JmsDirectionEnum(String key, String label) {
		this.key = key;
		this.label = label;
	}

	public String getKey() {
		return key;
	}

	public String getLabel() {
		return label;
	}

}
