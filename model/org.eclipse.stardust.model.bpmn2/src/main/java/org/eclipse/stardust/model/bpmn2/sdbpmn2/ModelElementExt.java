package org.eclipse.stardust.model.bpmn2.sdbpmn2;

import com.google.gson.JsonObject;

public class ModelElementExt {
	public Long elementOid;
	public String id;
	public String name;

	/**
	 * key-value list of extended attributes
	 */
	public JsonObject attributes;
}
