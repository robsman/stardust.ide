/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Organization Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getOrganizationSymbols <em>Organization Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getTeamLead <em>Team Lead</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getOrganizationType()
 * @model extendedMetaData="name='organization_._type' kind='elementOnly'"
 * @generated
 */
public interface OrganizationType extends IModelParticipant {
	/**
	 * Returns the value of the '<em><b>Participant</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of participants for the organization.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participant</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getOrganizationType_Participant()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='participant' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ParticipantType> getParticipant();

	/**
	 * Returns the value of the '<em><b>Organization Symbols</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getOrganization <em>Organization</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Organization Symbols</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Organization Symbols</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getOrganizationType_OrganizationSymbols()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getOrganization
	 * @model opposite="organization" transient="true"
	 * @generated
	 */
	EList<OrganizationSymbolType> getOrganizationSymbols();

	/**
	 * Returns the value of the '<em><b>Team Lead</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getTeams <em>Teams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The lead role for the organization, if existent.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Team Lead</em>' reference.
	 * @see #setTeamLead(RoleType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getOrganizationType_TeamLead()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getTeams
	 * @model opposite="teams" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='teamLead'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	RoleType getTeamLead();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType#getTeamLead <em>Team Lead</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Team Lead</em>' reference.
	 * @see #getTeamLead()
	 * @generated
	 */
	void setTeamLead(RoleType value);

} // OrganizationType
