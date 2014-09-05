package org.eclipse.stardust.test.model.transformation.bpmn.playground;

public class JavaAppWithVariousConstructors {

	private String localParam1;
	private String localParam2;
	private String localParam3;

	public JavaAppWithVariousConstructors() {
		localParam1 = "String1";
		localParam1 = "String2";
		localParam1 = "String3";
	}
	
	public JavaAppWithVariousConstructors(String param1) {
		localParam1 = param1;
		localParam1 = "String2";
		localParam1 = "String3";
	}

	public JavaAppWithVariousConstructors(String param1, String param2) {
		localParam1 = param1;
		localParam2 = param2;
		localParam1 = "String3";		
	}
	
	public JavaAppWithVariousConstructors(String param1, String param2, String param3) {
		localParam1 = param1;
		localParam2 = param2;
		localParam3 = param3;
	}
	
	public String returnSimpleString(String param1) {
		return param1; 
	}
	
	public String returnConcatString(String param1, String param2) {
		return param1.concat(param2); 
	}
	
	public String returnSimpleString(String param1, String param2, String param3) {
		return param1.concat(param2).concat(param3); 
	}
	
	public String returnLocalString() {
		return localParam1.concat(localParam2).concat(localParam3); 
	}
	
	public void noReturnNoParamMetho() {
	}
	
	public void noReturnOverWriteLocalString(String param1) {
		localParam1 = param1;
	}
}
