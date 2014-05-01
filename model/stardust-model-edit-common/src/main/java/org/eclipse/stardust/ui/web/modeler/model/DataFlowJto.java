package org.eclipse.stardust.ui.web.modeler.model;



public class DataFlowJto extends ModelElementJto {

	public DataFlowJto() {
		super();
//		this.type = ModelerConstants.dat DATA_FLOWS_LITERAL;
	}

//	public Map<String, DataMappingJto> inputDataMapping = new LinkedHashMap<String, DataMappingJto>();
//	public Map<String, DataMappingJto> outputDataMapping = new LinkedHashMap<String, DataMappingJto>();
	public DataMappingJto inputDataMapping = null;
	public DataMappingJto outputDataMapping = null;

}
