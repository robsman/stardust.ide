/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ITpearls - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.stardust.engine.api.model.PredefinedConstants;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.TaskDataFlow2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessModeType;

/**
 * @author Simon Nikles
 *
 */
public class CallActivity2Stardust extends AbstractElement2Stardust {

//	public CallActivity2Stardust(ModelType carnotModel, List<String> failures) {
//		super(carnotModel, failures);
//	}

	private Map<String, ModelType> transformedRelatedModelsByDefinitionsId;

	public CallActivity2Stardust(ModelType carnotModel, Map<String, ModelType> transformedRelatedModelsByDefinitionsId, List<String> failures) {
		super(carnotModel, failures);
		this.transformedRelatedModelsByDefinitionsId = transformedRelatedModelsByDefinitionsId;
	}

	public void addCallActivity(CallActivity caller, FlowElementsContainer container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(caller, container);
		List<Documentation> docs = caller.getDocumentation();
		String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);
		newSubProcessActivity(processDef)
				.withIdAndName(caller.getId(), caller.getName())
				.withDescription(processDescription)
				.usingMode(SubProcessModeType.SYNC_SEPARATE_LITERAL)
				.build();

	}

	public void addGlobalCall(CallActivity caller, FlowElementsContainer container) {
		if (caller.getCalledElementRef() == null) {
			failures.add("Missing called element for call activity (" + caller.getId() + ")");
			return;
		}
		CallableElement called = caller.getCalledElementRef();
		if (called.eIsProxy()) {
			Definitions defs = BpmnModelQuery.getModelDefinitions(container);
			called = Bpmn2ProxyResolver.resolveCallableElementProxy(called, defs);
		}
		setCalledElement(caller, container, called);
		mapIOSpec(caller, container, called);
	}

	private void mapIOSpec(CallActivity caller, FlowElementsContainer container, CallableElement called) {
		if (null == called) return;
		InputOutputSpecification callerIO = caller.getIoSpecification();
		InputOutputSpecification calledIO = called.getIoSpecification();
		Map<DataInput, DataInput> calledInputPerCallerInput = new HashMap<DataInput, DataInput>();
		Map<DataOutput, DataOutput> calledOutputPerCallerOutput = new HashMap<DataOutput, DataOutput>();

		if ((callerIO == null || calledIO == null) && callerIO != calledIO) {
			failures.add("Unresolved data mapping from call activity to called element (" + caller.getId() + " to " + called.getId() + ")");
			return;
		}
		if (callerIO != null && calledIO != null) {
			for (DataInput input : callerIO.getDataInputs()) {
				DataInput calledInput = findMatchingInput(input, calledIO.getDataInputs());
				if (calledInput == null) {
					failures.add("Unresolved data mapping from call activity to called element (" + caller.getId() + " to " + called.getId() + ")");
					return;
				}
				calledInputPerCallerInput.put(input, calledInput);
			}
			for (DataOutput output : callerIO.getDataOutputs()) {
				DataOutput calledOutput = findMatchingOutput(output, calledIO.getDataOutputs());
				if (calledOutput == null) {
					failures.add("Unresolved data mapping from call activity to called element (" + caller.getId() + " to " + called.getId() + ")");
					return;
				}
				calledOutputPerCallerOutput.put(output, calledOutput);
			}
		}

		addProcessCallDataMapping(caller, container, calledInputPerCallerInput, calledOutputPerCallerOutput);
	}

	private void addProcessCallDataMapping(CallActivity caller, FlowElementsContainer container, Map<DataInput, DataInput> calledInputPerCallerInput, Map<DataOutput, DataOutput> calledOutputPerCallerOutput) {
		ActivityType callActivity = query.findActivity(caller, container);
		for (DataInput callerInput : calledInputPerCallerInput.keySet()) {
			DataInput calledInput = calledInputPerCallerInput.get(callerInput);
			//callActivity.
	        DataType calledVariable = query.findVariable(calledInput.getId());
	        DataInputAssociation inputAssoc = BpmnModelQuery.findDataInputAssociationTo(callerInput, caller);
	        if (inputAssoc != null && TaskDataFlow2Stardust.hasAssignment(inputAssoc)) {
	        	failures.add("Assignments on DataInputAssociations are not supported for call activities (" + caller.getId() + ")");
	        	return;
	        }
	        String mappingId = TaskDataFlow2Stardust.getInDataMappingId(callerInput, inputAssoc);
	        DataMappingType dataMapping = CarnotModelQuery.getDataMapping(callActivity, mappingId);
	        if (dataMapping == null) {
	        	failures.add("Datamapping (IN) for call activity (" + caller.getId() + ") not found");
	        	return;
	        }
	        dataMapping.setApplicationAccessPoint(calledVariable.getId());
	        dataMapping.setContext(PredefinedConstants.ENGINE_CONTEXT);
		}
		for (DataOutput callerOutput : calledOutputPerCallerOutput.keySet()) {
			DataOutput calledOutput = calledOutputPerCallerOutput.get(callerOutput);
	        DataType calledVariable = query.findVariable(calledOutput.getId());
	        DataOutputAssociation outputAssoc = BpmnModelQuery.findDataOutputAssociationFrom(callerOutput, caller);
	        if (outputAssoc != null && TaskDataFlow2Stardust.hasAssignment(outputAssoc)) {
	        	failures.add("Assignments on DataOutputAssociations are not supported for call activities (" + caller.getId() + ")");
	        	return;
	        }
	        String mappingId = TaskDataFlow2Stardust.getOutDataMappingId(callerOutput, outputAssoc);
	        DataMappingType dataMapping = CarnotModelQuery.getDataMapping(callActivity, mappingId);
	        if (dataMapping == null) {
	        	failures.add("Datamapping (OUT) for call activity (" + caller.getId() + ") not found");
	        	return;
	        }
	        dataMapping.setApplicationAccessPoint(calledVariable.getId());
	        dataMapping.setContext(PredefinedConstants.ENGINE_CONTEXT);
		}
	}

	private DataOutput findMatchingOutput(DataOutput output, List<DataOutput> calledOutputs) {
		String name = output.getName();
		ItemDefinition itemDef = output.getItemSubjectRef();
		boolean isCollection = output.isIsCollection();

		for (DataOutput calledOutput : calledOutputs) {
			if ((name == null && calledOutput.getName() == null) || (name != null && name.equals(calledOutput.getName()))) {
				if ((itemDef == null && calledOutput.getItemSubjectRef() == null) || itemDef.equals(calledOutput.getItemSubjectRef())) {
					if (isCollection == calledOutput.isIsCollection()) {
						return calledOutput;
					}
				}
			}
		}
		return null;
	}

	private DataInput findMatchingInput(DataInput input, List<DataInput> calledInputs) {
		String name = input.getName();
		ItemDefinition itemDef = input.getItemSubjectRef();
		boolean isCollection = input.isIsCollection();

		for (DataInput calledInput : calledInputs) {
			if ((name == null && calledInput.getName() == null) || (name != null && name.equals(calledInput.getName()))) {
				if ((itemDef == null && calledInput.getItemSubjectRef() == null) || itemDef.equals(calledInput.getItemSubjectRef())) {
					if (isCollection == calledInput.isIsCollection()) {
						return calledInput;
					}
				}
			}
		}
		return null;
	}

	private void setCalledElement(CallActivity caller, FlowElementsContainer container, CallableElement called) {
		if (null == called) {
			failures.add("Called Element not Set - CallActivity: " + caller + " FlowElementsContainer: " + container);
			return;
		}
		ProcessDefinitionType calledProcess = query.findProcessDefinition(called.getId());
		ActivityType callingActivity = query.findActivity(caller, container);
		callingActivity.setImplementationProcess(calledProcess);
	}

}

//package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity;
//
//import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.eclipse.bpmn2.CallActivity;
//import org.eclipse.bpmn2.CallableElement;
//import org.eclipse.bpmn2.DataInput;
//import org.eclipse.bpmn2.DataInputAssociation;
//import org.eclipse.bpmn2.DataOutput;
//import org.eclipse.bpmn2.DataOutputAssociation;
//import org.eclipse.bpmn2.Definitions;
//import org.eclipse.bpmn2.Documentation;
//import org.eclipse.bpmn2.FlowElementsContainer;
//import org.eclipse.bpmn2.InputOutputSpecification;
//import org.eclipse.bpmn2.ItemDefinition;
//import org.eclipse.stardust.model.bpmn2.reader.ModelInfo;
//import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.data.TaskDataFlow2Stardust;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
//import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
//import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
//import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
//import org.eclipse.stardust.model.xpdl.carnot.DataType;
//import org.eclipse.stardust.model.xpdl.carnot.ModelType;
//import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
//
//public class CallActivity2Stardust extends AbstractElement2Stardust {
//
//	private Map<String, ModelType> transformedRelatedModelsByDefinitionsId;
//
//		public CallActivity2Stardust(ModelType carnotModel, Map<String, ModelType> transformedRelatedModelsByDefinitionsId, List<String> failures) {
//			super(carnotModel, failures);
//			this.transformedRelatedModelsByDefinitionsId = transformedRelatedModelsByDefinitionsId;
//		}
//
//		public void addCallActivity(CallActivity activity, FlowElementsContainer container) {
//			CallableElement calledElementRef = activity.getCalledElementRef();
//			ProcessDefinitionType calledProcessDef = null;
//			ProcessDefinitionType callingProcessDef = getProcessAndReportFailure(activity, container);
//			if (null != calledElementRef) {
//				if (calledElementRef.eIsProxy()) calledElementRef = Bpmn2ProxyResolver.resolveProxy(calledElementRef, ModelInfo.getDefinitions(container));
//				if (null != calledElementRef)
//					calledProcessDef = getProcessAndReportFailure(calledElementRef.getId());
//				//if (processDef == null) return;
//			}
//			if (null == calledProcessDef) {
//				failures.add("Could not resolve called process " + activity.getCalledElementRef());
//				return;
//			}
//			List<Documentation> docs = activity.getDocumentation();
//			String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);
//			ActivityType callActivity = newSubProcessActivity(callingProcessDef)
//					.withIdAndName(activity.getId(), activity.getName())
//					.withDescription(processDescription)
//					.build();
//			if (null != calledProcessDef) {
//				callActivity.setImplementationProcess(calledProcessDef);
//				mapIOSpec(activity, container, calledElementRef);
//			}
//		}
//
//		public void addGlobalCall(CallActivity caller, FlowElementsContainer container) {
//			if (caller.getCalledElementRef() == null) {
//				failures.add("Missing called element for call activity (" + caller.getId() + ")");
//				return;
//			}
//			CallableElement called = caller.getCalledElementRef();
//			if (called.eIsProxy()) {
//				Definitions defs = BpmnModelQuery.getModelDefinitions(container);
//				called = Bpmn2ProxyResolver.resolveCallableElementProxy(called, defs);
//			}
//			setCalledElement(caller, container, called);
//			mapIOSpec(caller, container, called);
//		}
//
//		private void setCalledElement(CallActivity caller, FlowElementsContainer container, CallableElement called) {
//			if (null == called) {
//				failures.add("Stardust Called Element not found " + called);
//				return;
//			}
//			ProcessDefinitionType calledProcess = query.findProcessDefinition(called.getId());
//			ActivityType callingActivity = query.findActivity(caller, container);
//			if (null != callingActivity) {
//				callingActivity.setImplementationProcess(calledProcess);
//			} else {
//				failures.add("Stardust Call Activity not found " + caller);
//			}
//		}
//
//		private void mapIOSpec(CallActivity caller, FlowElementsContainer container, CallableElement called) {
//			if (null == caller || null == called) return;
//			InputOutputSpecification callerIO = caller.getIoSpecification();
//			InputOutputSpecification calledIO = called.getIoSpecification();
//			Map<DataInput, DataInput> calledInputPerCallerInput = new HashMap<DataInput, DataInput>();
//			Map<DataOutput, DataOutput> calledOutputPerCallerOutput = new HashMap<DataOutput, DataOutput>();
//
//			if ((callerIO == null || calledIO == null) && callerIO != calledIO) {
//				failures.add("Unresolved data mapping from call activity to called element (" + caller.getId() + " to " + called.getId() + ")");
//				return;
//			}
//			if (callerIO != null && calledIO != null) {
//				for (DataInput input : callerIO.getDataInputs()) {
//					DataInput calledInput = findMatchingInput(input, calledIO.getDataInputs());
//					if (calledInput == null) {
//						failures.add("Unresolved data mapping from call activity to called element (" + caller.getId() + " to " + called.getId() + ")");
//						return;
//					}
//					calledInputPerCallerInput.put(input, calledInput);
//				}
//				for (DataOutput output : callerIO.getDataOutputs()) {
//					DataOutput calledOutput = findMatchingOutput(output, calledIO.getDataOutputs());
//					if (calledOutput == null) {
//						failures.add("Unresolved data mapping from call activity to called element (" + caller.getId() + " to " + called.getId() + ")");
//						return;
//					}
//					calledOutputPerCallerOutput.put(output, calledOutput);
//				}
//			}
//
//			addProcessCallDataMapping(caller, container, calledInputPerCallerInput, calledOutputPerCallerOutput);
//		}
//
//		private DataOutput findMatchingOutput(DataOutput output, List<DataOutput> calledOutputs) {
//			String name = output.getName();
//			ItemDefinition itemDef = output.getItemSubjectRef();
//			boolean isCollection = output.isIsCollection();
//
//			for (DataOutput calledOutput : calledOutputs) {
//				if ((name == null && calledOutput.getName() == null) || (name != null && name.equals(calledOutput.getName()))) {
//					if ((itemDef == null && calledOutput.getItemSubjectRef() == null) || itemDef.equals(calledOutput.getItemSubjectRef())) {
//						if (isCollection == calledOutput.isIsCollection()) {
//							return calledOutput;
//						}
//					}
//				}
//			}
//			return null;
//		}
//
//		private DataInput findMatchingInput(DataInput input, List<DataInput> calledInputs) {
//			String name = input.getName();
//			ItemDefinition itemDef = input.getItemSubjectRef();
//			boolean isCollection = input.isIsCollection();
//
//			for (DataInput calledInput : calledInputs) {
//				if ((name == null && calledInput.getName() == null) || (name != null && name.equals(calledInput.getName()))) {
//					if ((itemDef == null && calledInput.getItemSubjectRef() == null) || itemDef.equals(calledInput.getItemSubjectRef())) {
//						if (isCollection == calledInput.isIsCollection()) {
//							return calledInput;
//						}
//					}
//				}
//			}
//			return null;
//		}
//
//		private void addProcessCallDataMapping(CallActivity caller, FlowElementsContainer container, Map<DataInput, DataInput> calledInputPerCallerInput, Map<DataOutput, DataOutput> calledOutputPerCallerOutput) {
//			ActivityType callActivity = query.findActivity(caller, container);
//			for (DataInput callerInput : calledInputPerCallerInput.keySet()) {
//				DataInput calledInput = calledInputPerCallerInput.get(callerInput);
//				//callActivity.
//		        DataType calledVariable = query.findVariable(calledInput.getId());
//		        DataInputAssociation inputAssoc = BpmnModelQuery.findDataInputAssociationTo(callerInput, caller);
//		        if (inputAssoc != null && TaskDataFlow2Stardust.hasAssignment(inputAssoc)) {
//		        	failures.add("Assignments on DataInputAssociations are not supported for call activities (" + caller.getId() + ")");
//		        	return;
//		        }
//		        String mappingId = TaskDataFlow2Stardust.getInDataMappingId(callerInput, inputAssoc);
//		        logger.debug("Search datamapping for call activity: activity id (caller) " + callActivity + " mapping: " + mappingId);
//		        DataMappingType dataMapping = CarnotModelQuery.getDataMapping(callActivity, mappingId);
//		        if (dataMapping == null) {
//		        	failures.add("Datamapping (IN) for call activity (" + caller.getId() + ") not found");
//		        	return;
//		        }
//		        dataMapping.setApplicationAccessPoint(calledVariable.getId());
//			}
//			for (DataOutput callerOutput : calledOutputPerCallerOutput.keySet()) {
//				DataOutput calledOutput = calledOutputPerCallerOutput.get(callerOutput);
//		        DataType calledVariable = query.findVariable(calledOutput.getId());
//		        DataOutputAssociation outputAssoc = BpmnModelQuery.findDataOutputAssociationFrom(callerOutput, caller);
//		        if (outputAssoc != null && TaskDataFlow2Stardust.hasAssignment(outputAssoc)) {
//		        	failures.add("Assignments on DataOutputAssociations are not supported for call activities (" + caller.getId() + ")");
//		        	return;
//		        }
//		        String mappingId = TaskDataFlow2Stardust.getOutDataMappingId(callerOutput, outputAssoc);
//		        DataMappingType dataMapping = CarnotModelQuery.getDataMapping(callActivity, mappingId);
//		        if (dataMapping == null) {
//		        	failures.add("Datamapping (OUT) for call activity (" + caller.getId() + ") not found");
//		        	return;
//		        }
//		        dataMapping.setApplicationAccessPoint(calledVariable.getId());
//			}
//		}
//
////		private void tempImport() {
////			ModelType t;
////			t.getExternalPackages().g
////		}
//}
