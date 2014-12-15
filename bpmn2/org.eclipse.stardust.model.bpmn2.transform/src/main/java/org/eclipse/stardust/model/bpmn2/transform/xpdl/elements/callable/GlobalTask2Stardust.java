package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.callable;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newModelDiagram;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;

import java.util.List;

import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.GlobalBusinessRuleTask;
import org.eclipse.bpmn2.GlobalManualTask;
import org.eclipse.bpmn2.GlobalScriptTask;
import org.eclipse.bpmn2.GlobalTask;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.task.UserTask2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

public class GlobalTask2Stardust extends AbstractElement2Stardust {

	public GlobalTask2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addGlobalTask(GlobalTask globalTask) {
		List<Documentation> docs = globalTask.getDocumentation();
		String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);

		ProcessDefinitionType def =
				newProcessDefinition(carnotModel)
				.withIdAndName(globalTask.getId(), globalTask.getName())
				.withDescription(processDescription)
				.build();

		newModelDiagram(carnotModel).forProcess(def).build();
		
		addGlobalTaskContent(def, globalTask);
	}

	private void addGlobalTaskContent(ProcessDefinitionType def, GlobalTask globalTask) {
		if (globalTask instanceof GlobalUserTask) {
			UserTask2Stardust userTask2Stardust = new UserTask2Stardust(carnotModel, failures);
			userTask2Stardust.addGlobalUserTask((GlobalUserTask)globalTask, org.eclipse.stardust.model.bpmn2.reader.ModelInfo.getDefinitions(globalTask));
		} else if (globalTask instanceof GlobalScriptTask) {
			failures.add("GlobalScriptTask not yet supported!");
		} else if (globalTask instanceof GlobalBusinessRuleTask) {
			failures.add("GlobalBusinessRuleTask not yet supported!");
		} else if (globalTask instanceof GlobalManualTask) {
			UserTask2Stardust userTask2Stardust = new UserTask2Stardust(carnotModel, failures);
			userTask2Stardust.addGlobalManualTask((GlobalManualTask)globalTask, org.eclipse.stardust.model.bpmn2.reader.ModelInfo.getDefinitions(globalTask));
		}  else {
			failures.add("GlobalTask must declare a specific subclass (e.g. GlobalUserTask) for execution: " + globalTask);
		}
	}
	
	

}
