package org.eclipse.stardust.model.bpmn2.sdbpmn;

public class StardustModelParticipantExt extends ModelElementExt {

	/**
	 * "organizationParticipant" or "roleParticipant
	 */
	public String participantType;

	/**
	 * UUID of the parent, if this is a sub-participant
	 */
	public String parentUUID;

	/**
	 * model qualified UUID of the team lead, if available (<modelUuid> ":"
	 * <roleUuid>)
	 */
	public String teamLeadFullId;

}
