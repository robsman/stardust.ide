package org.eclipse.stardust.model.bpmn2.sdbpmn;

public class StardustDataObjectOrStoreExt extends ModelElementExt {
	/**
	 * "primitive" or "struct"
	 */
	public String dataType;

	// only used if dataType is 'primitive'
	public String primitiveDataType;

	// only used if dataType is 'struct' (<modelId> ":" <itemDefinitionId>)
	public String structuredDataTypeFullId;
}
