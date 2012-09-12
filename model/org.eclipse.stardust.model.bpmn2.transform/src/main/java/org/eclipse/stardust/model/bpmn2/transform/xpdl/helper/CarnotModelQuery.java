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
package org.eclipse.stardust.model.bpmn2.transform.xpdl.helper;

import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.FlowNode;
import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;

/**
 * @author Simon Nikles
 *
 */
public class CarnotModelQuery {
	
	private ModelType carnotModel;

	public CarnotModelQuery(ModelType carnotModel) {
		this.carnotModel = carnotModel; 
	}

	public ActivityType findActivity(FlowNode node, FlowElementsContainer container) {
		String nodeId = node != null ? node.getId() : null;
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
		if (processDef != null && nodeId != null) {
			return findActivity(processDef, nodeId);
		}
		return null;
	}

	public IModelParticipant findResourceType(String id) {
		EList<RoleType> roles = carnotModel.getRole();
		EList<OrganizationType> orgs = carnotModel.getOrganization();
		for (RoleType role : roles) {
			if (role.getId().equals(id)) return role;
		}
		for (OrganizationType org : orgs) {
			if (org.getId().equals(id)) return org;
		}
		return null;
	}

	public ProcessDefinitionType findProcessDefinition(String id) {
		return findProcessDefinition(carnotModel, id);
	}

	public TransitionType findTransition(String id, FlowElementsContainer container) {
		ProcessDefinitionType processDef = findProcessDefinition(container.getId());
		return findTransition(processDef, id);
	}

	public static IModelParticipant findParticipant(ModelType model, String id) {
		for (RoleType role : model.getRole()) {
			if (role.getId().equals(id)) return role;
		}
		for (OrganizationType org : model.getOrganization()) {
			if (org.getId().equals(id)) return org;
		}
		return null;
	}

	public static TriggerType findTrigger(ProcessDefinitionType processDef, String id) {
		for (TriggerType trigger : processDef.getTrigger()) {
			if (trigger.getId().equals(id)) return trigger;
		}
		return null;
	}

	public static ActivityType findActivity(ProcessDefinitionType processDef, String id) {
		for (ActivityType activity : processDef.getActivity()) {
			if (activity.getId().equals(id)) return activity; 
		}
		return null;
	}

	public static ProcessDefinitionType findProcessDefinition(ModelType model, String id) {
		for (ProcessDefinitionType processDef : model.getProcessDefinition()) {
			if (processDef.getId().equals(id)) return processDef;
		}
		return null;
	}

	public static TransitionType findTransition(ProcessDefinitionType processDef, String id) {
		for (TransitionType transition : processDef.getTransition()) {
			if (transition.getId().equals(id)) return transition; 
		}
		return null;
	}
	
	
	
}
