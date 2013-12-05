/*******************************************************************************
 * Copyright (c) 2012 ITpearls AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ITpearls AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Resource;
import org.eclipse.stardust.common.StringUtils;
import org.eclipse.stardust.model.bpmn2.extension.ExtensionHelper2;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustConditionalPerformerExt;
import org.eclipse.stardust.model.bpmn2.sdbpmn.StardustModelParticipantExt;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.Bpmn2StardustXPDLExtension;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.elements.AbstractElement2Stardust;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.BpmnModelQuery;
import org.eclipse.stardust.model.bpmn2.transform.xpdl.helper.CarnotModelQuery;
import org.eclipse.stardust.model.xpdl.builder.BpmModelBuilder;
import org.eclipse.stardust.model.xpdl.builder.common.AbstractElementBuilder;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationType;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;
import org.eclipse.stardust.model.xpdl.carnot.RoleType;


/**
 * @author Simon Nikles
 *
 * TODO (multi-)model-management / handling of uuid
 */
public class Resource2Stardust extends AbstractElement2Stardust {

	protected static final Logger logger = Logger.getLogger(Bpmn2StardustXPDLExtension.class);

	public Resource2Stardust(ModelType carnotModel, List<String> failures) {
		super(carnotModel, failures);
	}

	public void addResource(Resource resource) {
		/* In BPMN2, a Resource is assigned to activities (e.g. as ResourceRole 'Performer').
		 * Runtime data used for assignment is defined in a resource assignment expression in BPMN2.
		 * Stardust stores this information (incl. variables) in the reusable participant element.
		 * Therefore, we expect the whole definition (participant) to be in an extension element of the resource,
		 * while ommiting the BPMN2-assignment expressions.
		 * */
    	StardustModelParticipantExt ext = ExtensionHelper2.getInstance().getModelParticipantExtension(resource);
    	if (null != ext)  {
    		if ("organizationParticipant".equals(ext.participantType)) {
    			OrganizationType performer =
	    			BpmModelBuilder.newOrganization(carnotModel)
	    							.withIdAndName(resource.getId(), resource.getName())
	    							.build();
    			Bpmn2StardustXPDLExtension.addAttributes(ext, performer);
    			carnotModel.getOrganization().add(performer);

    		} else if ("roleParticipant".equals(ext.participantType)) {
    			RoleType performer =
	    			BpmModelBuilder.newRole(carnotModel)
	    							.withIdAndName(resource.getId(), resource.getName())
	    							.build();
    			Bpmn2StardustXPDLExtension.addAttributes(ext, performer);
    			carnotModel.getRole().add(performer);
    		} else if ("conditionalPerformerParticipant".equals(ext.participantType)) {
    			ConditionalPerformerType performer =
	    			BpmModelBuilder.newConditionalPerformer(carnotModel)
	    							.withIdAndName(resource.getId(), resource.getName())
	    							.build();
    			Bpmn2StardustXPDLExtension.addAttributes(ext, performer);
    			carnotModel.getConditionalPerformer().add(performer);
    		}
    	}
	}

	public void setConditionalPerformerData(Definitions defs) {
		// During load and transformation of the bpmn model, the references to data cannot be resolved.
		// Thus, the id of the data element is stored and resolved finally
		List<ConditionalPerformerType> conditionalPerformers = carnotModel.getConditionalPerformer();
		for (int i = 0; i < conditionalPerformers.size(); i++) {
			ConditionalPerformerType performer = conditionalPerformers.get(i);
			if (null != performer.getData()) continue;
			Resource res = BpmnModelQuery.findResource(defs, performer.getId());
			if (null == res) continue;
			StardustConditionalPerformerExt ext = ExtensionHelper2.getInstance().getCoreExtension(StardustConditionalPerformerExt.class, res);
			if (null == ext) continue;
			String dataId =  Bpmn2StardustXPDLExtension.stripFullId(ext.dataFullId);
			if (StringUtils.isEmpty(dataId)) continue;
			DataType data = query.findVariable(dataId);
			if (null != data) performer.setData(data);
		}
	}

	/**
	 * Expects all resources to be available
	 * @param resource
	 */
	public void addResourceRelations(Resource resource) {
		StardustModelParticipantExt ext = ExtensionHelper2.getInstance().getModelParticipantExtension(resource);
		Definitions defs = (Definitions)resource.eContainer();
		BpmnModelQuery bpmnquery = new BpmnModelQuery(logger);
		if (null != ext) {
			if ("organizationParticipant".equals(ext.participantType)) {
				OrganizationType organisation = (OrganizationType) CarnotModelQuery.findParticipant(carnotModel, resource.getId());
				if (StringUtils.isNotEmpty(ext.teamLeadFullId)) {
					String teamLeadId = Bpmn2StardustXPDLExtension.stripFullId(ext.teamLeadFullId);
					RoleType teamLead = (RoleType) CarnotModelQuery.findParticipant(carnotModel, teamLeadId);
					organisation.setTeamLead(teamLead);
				}
				if (StringUtils.isNotEmpty(ext.parentUUID)) {
					Resource parentResource = bpmnquery.findResourceByUUID(defs, ext.parentUUID);
					if (null == parentResource) {
						logger.info("Parent for Participant uuid " + ext.parentUUID + " not found.");
						return;
					}
					OrganizationType parent = (OrganizationType) CarnotModelQuery.findParticipant(carnotModel, parentResource.getId());
					ParticipantType participantType = AbstractElementBuilder.F_CWM.createParticipantType();
					participantType.setParticipant(organisation);
					parent.getParticipant().add(participantType);
				}
			} else if ("roleParticipant".equals(ext.participantType)) {
				RoleType role = (RoleType) CarnotModelQuery.findParticipant(carnotModel, resource.getId());
				if (StringUtils.isNotEmpty(ext.parentUUID)) {
					Resource parentResource = bpmnquery.findResourceByUUID(defs, ext.parentUUID);
					if (null == parentResource) {
						logger.info("Parent for Participant uuid " + ext.parentUUID + " not found.");
						return;
					}
					OrganizationType parent = (OrganizationType) CarnotModelQuery.findParticipant(carnotModel, parentResource.getId());
					if (!parent.getTeamLead().getId().equals(role.getId())) { // ommit relation, if its the teamleader role
						ParticipantType participantType = AbstractElementBuilder.F_CWM.createParticipantType();
						participantType.setParticipant(role);
						parent.getParticipant().add(participantType);
					}
				}
			}
		}
	}

}
