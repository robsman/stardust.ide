package org.eclipse.stardust.model.bpmn2.reader;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.PartnerEntity;
import org.eclipse.bpmn2.PartnerRole;
import org.eclipse.bpmn2.RootElement;

/**
 * @author Simon Nikles
 *
 */
public class ModelInfo {

	public ModelInfo() {
	}
	
	public static List<PartnerRole> getParnterRolesOfParticipant(Definitions definitions, Participant participant) {
		List<PartnerRole> roles = new ArrayList<PartnerRole>();
		for (RootElement root : definitions.getRootElements()) {
			if (root instanceof PartnerRole && ((PartnerRole)root).getParticipantRef().contains(participant)) {
				roles.add((PartnerRole)root);
			}
		}
		return roles;		
	}
	
	public static List<PartnerEntity> getParnterEntitiesOfParticipant(Definitions definitions, Participant participant) {
		List<PartnerEntity> entities = new ArrayList<PartnerEntity>();
		for (RootElement root : definitions.getRootElements()) {
			if (root instanceof PartnerEntity && ((PartnerEntity)root).getParticipantRef().contains(participant)) {
				entities.add((PartnerEntity)root);
			}
		}
		return entities;		
	}
	
	
	
}
