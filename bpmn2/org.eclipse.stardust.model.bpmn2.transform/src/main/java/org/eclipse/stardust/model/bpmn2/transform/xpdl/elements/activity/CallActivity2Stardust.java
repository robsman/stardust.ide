package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newSubProcessActivity;

import java.util.List;
import java.util.Map;

import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.stardust.model.bpmn2.reader.ModelInfo;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class CallActivity2Stardust extends AbstractElement2Stardust {

	private Map<String, ModelType> transformedRelatedModelsByDefinitionsId;
	
		public CallActivity2Stardust(ModelType carnotModel, Map<String, ModelType> transformedRelatedModelsByDefinitionsId, List<String> failures) {
			super(carnotModel, failures);
			this.transformedRelatedModelsByDefinitionsId = transformedRelatedModelsByDefinitionsId;
		}

		public void addCallActivity(CallActivity activity, FlowElementsContainer container) {
			CallableElement calledElementRef = activity.getCalledElementRef();
			ProcessDefinitionType calledProcessDef = null;
			ProcessDefinitionType callingProcessDef = getProcessAndReportFailure(activity, container);
			if (null != calledElementRef) {
				if (calledElementRef.eIsProxy()) calledElementRef = Bpmn2ProxyResolver.resolveProxy(calledElementRef, ModelInfo.getDefinitions(container));
				if (null != calledElementRef)
					calledProcessDef = getProcessAndReportFailure(calledElementRef.getId());
				//if (processDef == null) return;
			}
			if (null == calledProcessDef) {
				failures.add("Could not resolve called process " + activity.getCalledElementRef());
				return;
			}
			List<Documentation> docs = activity.getDocumentation();
			String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);
			ActivityType callActivity = newSubProcessActivity(callingProcessDef)
					.withIdAndName(activity.getId(), activity.getName())
					.withDescription(processDescription)
					.build();
			if (null != calledProcessDef) callActivity.setImplementationProcess(calledProcessDef);
		}

//		private void tempImport() {
//			ModelType t;
//			t.getExternalPackages().g
//		}
}
