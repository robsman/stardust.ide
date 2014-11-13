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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.activity.task;

import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newInteractiveApplicationActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newManualActivity;
import static org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder.newRole;

import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.GlobalManualTask;
import org.eclipse.bpmn2.GlobalTask;
import org.eclipse.bpmn2.GlobalUserTask;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.Performer;
import org.eclipse.bpmn2.Resource;
import org.eclipse.bpmn2.ResourceRole;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
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

public class UserTask2Stardust extends AbstractElement2Stardust {

	public static String TASK_IMPLEMENTATION_UNSPECIFIED = "##unspecified";
	public static String TASK_IMPLEMENTATION_UNSPECIFIED_ALT = "unspecified";
	public static String TASK_IMPLEMENTATION_UNSPECIFIED_NULL = null;
	public static String TASK_IMPLEMENTATION_UNSPECIFIED_EMPTY = "";

	public UserTask2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addUserTask(UserTask task, FlowElementsContainer container) {
		logger.debug("Add user task: " + task);
		ProcessDefinitionType processDef = getProcessAndReportFailure(task, container);
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());

		ActivityType activity =
				//taskWithoutImplementationSpec(task)
				taskWithoutAppRef(task)
				? buildManualActivity(processDef, task, descr)
				: buildInteractiveActivity(processDef, task, descr);

		Bpmn2StardustXPDLExtension.addUserTaskExtensions(query, task, activity);

		List<ResourceRole> resources = task.getResources();
		for (ResourceRole role : resources) {
			if (role instanceof Performer) {
				setTaskPerformer(activity, role, org.eclipse.stardust.model.bpmn2.reader.ModelInfo.getDefinitions(container));
			}
		}
	}

	public void addUserTaskForManualTask(ManualTask task, FlowElementsContainer container) {
		logger.debug("Add user task: " + task);
		ProcessDefinitionType processDef = getProcessAndReportFailure(task, container);
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(task.getDocumentation());

		ActivityType activity = buildManualActivity(processDef, task, descr);

		List<ResourceRole> resources = task.getResources();
		for (ResourceRole role : resources) {
			if (role instanceof Performer) {
				setTaskPerformer(activity, role, org.eclipse.stardust.model.bpmn2.reader.ModelInfo.getDefinitions(container));
			}
		}
	}

	private boolean taskWithoutAppRef(UserTask task) {
		String ref = Bpmn2StardustXPDLExtension.getUserTaskApplicationRef(task);
		return null == ref || ref.trim().isEmpty();
	}

	public void addGlobalUserTask(GlobalUserTask globalTask, Definitions container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(globalTask.getId());
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(globalTask.getDocumentation());

		ActivityType activity =
				taskWithoutImplementationSpec(globalTask)
				? buildManualActivity(processDef, globalTask, descr)
				: buildInteractiveActivity(processDef, globalTask, descr);

		Bpmn2StardustXPDLExtension.addGlobalUserTaskExtensions(query, globalTask, activity);

		List<ResourceRole> resources = globalTask.getResources();
		for (ResourceRole role : resources) {
			if (role instanceof Performer) {
				setTaskPerformer(activity, role, globalTask, org.eclipse.stardust.model.bpmn2.reader.ModelInfo.getDefinitions(container));
			}
		}
	}

	public void addGlobalManualTask(GlobalManualTask globalTask, Definitions container) {
		ProcessDefinitionType processDef = getProcessAndReportFailure(globalTask.getId());
		if (processDef == null) return;
		String descr = DocumentationTool.getDescriptionFromDocumentation(globalTask.getDocumentation());

		ActivityType activity = buildManualActivity(processDef, globalTask, descr);

		List<ResourceRole> resources = globalTask.getResources();
		for (ResourceRole role : resources) {
			if (role instanceof Performer) {
				setTaskPerformer(activity, role, globalTask, org.eclipse.stardust.model.bpmn2.reader.ModelInfo.getDefinitions(container));
			}
		}
	}

    private boolean taskWithoutImplementationSpec(UserTask task) {
    	if (null == task.getImplementation()) return true;
    	return
    			task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED)
    		|| 	task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED_NULL)
    		|| 	task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED_ALT)
    		|| 	task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED_EMPTY);
	}

    private boolean taskWithoutImplementationSpec(GlobalUserTask task) {
    	return
    			task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED)
    		|| 	task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED_NULL)
    		|| 	task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED_ALT)
    		|| 	task.getImplementation().equals(TASK_IMPLEMENTATION_UNSPECIFIED_EMPTY);
	}

	private ActivityType buildManualActivity(ProcessDefinitionType processDef, Task task, String descr) {
		ActivityType activity = newManualActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr)
				.build();
		return activity;
	}

	private ActivityType buildManualActivity(ProcessDefinitionType processDef, GlobalTask task, String descr) {
		ActivityType activity = newManualActivity(processDef)
				.withIdAndName("globalTask_"+task.getId(), task.getName())
				.withDescription(descr)
				.build();
		return activity;
	}

	private ActivityType buildInteractiveActivity(ProcessDefinitionType processDef, UserTask task, String descr) {
		ActivityType activity = newInteractiveApplicationActivity(processDef)
				.withIdAndName(task.getId(), task.getName())
				.withDescription(descr)
				// usingApplication is set by through extension-attribute (reference id)
				.build();
		// TODO REVIEW - currently, the interactiveApplicationBuilder sets 'route'
		activity.setImplementation(ActivityImplementationType.APPLICATION_LITERAL);
		return activity;
	}

	private ActivityType buildInteractiveActivity(ProcessDefinitionType processDef, GlobalUserTask task, String descr) {
		ActivityType activity = newInteractiveApplicationActivity(processDef)
				.withIdAndName("globalTask_"+task.getId(), task.getName())
				.withDescription(descr)
				// usingApplication is set by through extension-attribute (reference id)
				.build();
		// TODO REVIEW - currently, the interactiveApplicationBuilder sets 'route'
		activity.setImplementation(ActivityImplementationType.APPLICATION_LITERAL);
		return activity;
	}

	private void setTaskPerformer(ActivityType activity, ResourceRole role, Definitions container) {
		logger.debug("Set Task Performer task: " + activity + ", performer: " + role);
        if (role.eIsProxy()) role = Bpmn2ProxyResolver.resolveRoleProxy(role, container);
        validateResource(role);
        if (role.getResourceRef() != null) {
            Resource resource = role.getResourceRef();
            if (resource.eIsProxy()) resource = Bpmn2ProxyResolver.resolveResourceProxy(resource, container);
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

	private void setTaskPerformer(ActivityType activity, ResourceRole role, GlobalTask task, Definitions container) {
		logger.debug("Set Task Performer task: " + task + ", performer: " + role);
        if (role.eIsProxy()) role = Bpmn2ProxyResolver.resolveRoleProxy(role, container);
        validateResource(role);
        if (role.getResourceRef() != null) {
            Resource resource = role.getResourceRef();
            if (resource.eIsProxy()) resource = Bpmn2ProxyResolver.resolveResourceProxy(resource, container);
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

//	private void setTaskPerformer(ActivityType activity, ResourceRole role, UserTask task, FlowElementsContainer container) {
//		logger.debug("Set Task Performer task: " + task + ", performer: " + role);
//        if (role.eIsProxy()) role = Bpmn2ProxyResolver.resolveRoleProxy(role, container);
//        validateResource(role);
//        if (role.getResourceRef() != null) {
//            Resource resource = role.getResourceRef();
//            if (resource.eIsProxy()) resource = Bpmn2ProxyResolver.resolveResourceProxy(resource, container);
//            if (resource != null) {
//                IModelParticipant resourceType = query.findParticipant(resource.getId());
//                if (resourceType==null) {
//                	String descr = DocumentationTool.getDescriptionFromDocumentation(resource.getDocumentation());
//                	resourceType = newRole(carnotModel).withIdAndName(resource.getId(), resource.getName()).withDescription(descr).build();
//                }
//                activity.setPerformer(resourceType);
//            }
//        }
//    }

	private void validateResource(ResourceRole role) {
        if (role.getResourceAssignmentExpression() != null)
        	failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "(RESOURCE ASSIGNMENT EXPRESSION NOT IMPLEMENTED) " + role);
        if (role.getResourceParameterBindings() != null
        	&& role.getResourceParameterBindings().size() > 0)
        	failures.add(Bpmn2StardustXPDL.FAIL_ELEMENT_UNSUPPORTED_FEATURE + "RESOURCE PARAMETER BINDINGS NOT IMPLEMENTED " + role);
	}

}
