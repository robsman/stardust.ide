/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getOrganizationMemberships <em>Organization Memberships</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getTeams <em>Teams</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getRoleSymbolType()
 * @model extendedMetaData="name='roleSymbol_._type' kind='empty'"
 * @generated
 */
public interface RoleSymbolType extends IModelParticipantSymbol {
	/**
	 * Returns the value of the '<em><b>Role</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getRoleSymbols <em>Role Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The id of the corresponding activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Role</em>' reference.
	 * @see #setRole(RoleType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getRoleSymbolType_Role()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType#getRoleSymbols
	 * @model opposite="roleSymbols" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='refer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	RoleType getRole();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getRole <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' reference.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(RoleType value);

	/**
	 * Returns the value of the '<em><b>Organization Memberships</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Organization Memberships</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Organization Memberships</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getRoleSymbolType_OrganizationMemberships()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType#getParticipantSymbol
	 * @model opposite="participantSymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<WorksForConnectionType> getOrganizationMemberships();

	/**
	 * Returns the value of the '<em><b>Teams</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamLeadSymbol <em>Team Lead Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Teams</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Teams</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getRoleSymbolType_Teams()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamLeadSymbol
	 * @model opposite="teamLeadSymbol" transient="true"
	 * @generated
	 */
	EList<TeamLeadConnectionType> getTeams();

} // RoleSymbolType
