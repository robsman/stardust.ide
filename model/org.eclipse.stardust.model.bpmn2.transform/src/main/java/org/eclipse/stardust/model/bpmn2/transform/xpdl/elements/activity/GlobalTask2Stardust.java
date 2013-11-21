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

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newInteractiveApplicationActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newModelDiagram;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newProcessDefinition;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRole;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Documentation;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.stardust.model.bpmn2.transform.util.Bpmn2ProxyResolver;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDL;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.DocumentationTool;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;

/**
 * @author Simon Nikles
 *
 */
public class GlobalTask2Stardust extends AbstractElement2Stardust {

	public GlobalTask2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addGlobalUserTask(GlobalUserTask task, Definitions defs) {

		logger.debug("Add global user task: " + task);
		ProcessDefinitionType processDef = addProcess(task);
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());

		ActivityType activity =
				taskWithoutImplementationSpec(task)
				? buildManualActivity(processDef, task, descr)
				: buildInteractiveActivity(processDef, task, descr);

		Bpmn2StardustXPDLExtension.addGlobalUserTaskExtensions(query, task, activity);

		List<ResourceRole> resources = task.getResources();
		for (ResourceRole role : resources) {
			if (role instanceof Performer) {
				setTaskPerformer(activity, role, task, defs);
			}
		}
	}

	public ProcessDefinitionType addProcess(GlobalUserTask task) {
		List<Documentation> docs = task.getDocumentation();
		String processDescription = DocumentationTool.getDescriptionFromDocumentation(docs);

		ProcessDefinitionType def =
				newProcessDefinition(carnotModel)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(processDescription)
				.build();

		newModelDiagram(carnotModel).forProcess(def).build();
		return def;
	}

    private boolean taskWithoutImplementationSpec(GlobalUserTask task) {
    	return
    			null == task.getImplementation()
    		||  task.getImplementation().equals(UserTask2Stardust.TASK_IMPLEMENTATION_UNSPECIFIED)
    		|| 	task.getImplementation().equals(UserTask2Stardust.TASK_IMPLEMENTATION_UNSPECIFIED_NULL)
    		|| 	task.getImplementation().equals(UserTask2Stardust.TASK_IMPLEMENTATION_UNSPECIFIED_ALT)
    		|| 	task.getImplementation().equals(UserTask2Stardust.TASK_IMPLEMENTATION_UNSPECIFIED_EMPTY);
	}

	private ActivityType buildManualActivity(ProcessDefinitionType processDef, GlobalUserTask task, String descr) {
		ActivityType activity = newManualActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr)
				.build();
		return activity;
	}

	private ActivityType buildInteractiveActivity(ProcessDefinitionType processDef, GlobalUserTask task, String descr) {
		ActivityType activity = newInteractiveApplicationActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr)
				// usingApplication is set by through extension-attribute (reference id)
				.build();
		// TODO REVIEW - currently, the interactiveApplicationBuilder sets 'route'
		activity.setImplementation(ActivityImplementationType.APPLICATION_LITERAL);
		return activity;
	}

	private void setTaskPerformer(ActivityType activity, ResourceRole role, GlobalUserTask task, Definitions defs) {
		logger.debug("Set Task Performer task: " + task + ", performer: " + role);
        if (role.eIsProxy()) role = Bpmn2ProxyResolver.resolveRoleProxy(role, defs);
        validateResource(role);
        if (role.getResourceRef() != null) {
            Resource resource = role.getResourceRef();
            if (resource.eIsProxy()) resource = Bpmn2ProxyResolver.resolveResourceProxy(resource, defs);
            if (resource != null) {
                IModelParticipant resourceType = query.findParticipant(resource.getId());
                if (resourceType==null) {
                	String descr = DocumentationTool.getDescriptionFromDocumentation(resource.getDocumentation());
                	resourceType = newRole(carnotModel).withIdAndName(resource.getId(), resource.getName()).withDescription(descr).build();
                }
                activity.setPerformer(resourceType);
            }
        }
    }

	private void validateResource(ResourceRole role) {
        if (role.getResourceAssignmentExpression() != null)
        	failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "(RESOURCE ASSIGNMENT EXPRESSION NOT IMPLEMENTED) " + role);
        if (role.getResourceParameterBindings() != null
        	&& role.getResourceParameterBindings().size() > 0)
        	failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "RESOURCE PARAMETER BINDINGS NOT IMPLEMENTED " + role);
	}

}
