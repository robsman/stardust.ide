package org.eclipse.stardust.model.bpmn2.sdbpmn;

public class StardustConditionalPerformerExt extends ModelElementExt {

	/**
	 * Must be "conditionalPerformerParticipant"
	 */
	public String participantType;

	/**
	 * model qualified UUID of the condition data
	 */
	public String dataFullId;

}
