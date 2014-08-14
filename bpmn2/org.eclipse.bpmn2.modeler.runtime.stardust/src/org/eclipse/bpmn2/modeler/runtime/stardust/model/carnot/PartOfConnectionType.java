/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Part Of Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getSuborganizationSymbol <em>Suborganization Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPartOfConnectionType()
 * @model extendedMetaData="name='partOfConnection_._type' kind='empty'"
 * @generated
 */
public interface PartOfConnectionType extends IConnectionSymbol {
	/**
	 * Returns the value of the '<em><b>Organization Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSubOrganizations <em>Sub Organizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the organization symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Organization Symbol</em>' reference.
	 * @see #setOrganizationSymbol(OrganizationSymbolType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPartOfConnectionType_OrganizationSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSubOrganizations
	 * @model opposite="subOrganizations" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='organizationSymbol'"
	 * @generated
	 */
	OrganizationSymbolType getOrganizationSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getOrganizationSymbol <em>Organization Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Organization Symbol</em>' reference.
	 * @see #getOrganizationSymbol()
	 * @generated
	 */
	void setOrganizationSymbol(OrganizationSymbolType value);

	/**
	 * Returns the value of the '<em><b>Suborganization Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSuperOrganizations <em>Super Organizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the sub organization symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Suborganization Symbol</em>' reference.
	 * @see #setSuborganizationSymbol(OrganizationSymbolType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPartOfConnectionType_SuborganizationSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType#getSuperOrganizations
	 * @model opposite="superOrganizations" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='suborganizationSymbol'"
	 * @generated
	 */
	OrganizationSymbolType getSuborganizationSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType#getSuborganizationSymbol <em>Suborganization Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suborganization Symbol</em>' reference.
	 * @see #getSuborganizationSymbol()
	 * @generated
	 */
	void setSuborganizationSymbol(OrganizationSymbolType value);

} // PartOfConnectionType
