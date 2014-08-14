/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Team Lead Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamSymbol <em>Team Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamLeadSymbol <em>Team Lead Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTeamLeadConnectionType()
 * @model extendedMetaData="name='teamLeadConnection_._type' kind='empty'"
 * @generated
 */
public interface TeamLeadConnectionType extends IConnectionSymbol {
	/**
	 * Returns the value of the '<em><b>Team Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getTeamLead <em>Team Lead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the organization symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Team Symbol</em>' reference.
	 * @see #setTeamSymbol(OrganizationSymbolType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTeamLeadConnectionType_TeamSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getTeamLead
	 * @model opposite="teamLead" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='teamSymbol'"
	 * @generated
	 */
	OrganizationSymbolType getTeamSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamSymbol <em>Team Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Team Symbol</em>' reference.
	 * @see #getTeamSymbol()
	 * @generated
	 */
	void setTeamSymbol(OrganizationSymbolType value);

	/**
	 * Returns the value of the '<em><b>Team Lead Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getTeams <em>Teams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the participant symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Team Lead Symbol</em>' reference.
	 * @see #setTeamLeadSymbol(RoleSymbolType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTeamLeadConnectionType_TeamLeadSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType#getTeams
	 * @model opposite="teams" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='teamLeadSymbol'"
	 * @generated
	 */
	RoleSymbolType getTeamLeadSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType#getTeamLeadSymbol <em>Team Lead Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Team Lead Symbol</em>' reference.
	 * @see #getTeamLeadSymbol()
	 * @generated
	 */
	void setTeamLeadSymbol(RoleSymbolType value);

} // TeamLeadConnectionType
