/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Resource Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getStardustConditionalPerformer <em>Stardust Conditional Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getStardustRole <em>Stardust Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getStardustOrganization <em>Stardust Organization</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getDataId <em>Data Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustResourceType()
 * @model extendedMetaData="name='StardustResource_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustResourceType extends EObject {
	/**
	 * Returns the value of the '<em><b>Stardust Conditional Performer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Conditional Performer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Conditional Performer</em>' containment reference.
	 * @see #setStardustConditionalPerformer(ConditionalPerformerType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustResourceType_StardustConditionalPerformer()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='StardustConditionalPerformer' namespace='##targetNamespace'"
	 * @generated
	 */
	ConditionalPerformerType getStardustConditionalPerformer();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getStardustConditionalPerformer <em>Stardust Conditional Performer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Conditional Performer</em>' containment reference.
	 * @see #getStardustConditionalPerformer()
	 * @generated
	 */
	void setStardustConditionalPerformer(ConditionalPerformerType value);

	/**
	 * Returns the value of the '<em><b>Stardust Role</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Role</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Role</em>' containment reference.
	 * @see #setStardustRole(RoleType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustResourceType_StardustRole()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='StardustRole' namespace='##targetNamespace'"
	 * @generated
	 */
	RoleType getStardustRole();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getStardustRole <em>Stardust Role</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Role</em>' containment reference.
	 * @see #getStardustRole()
	 * @generated
	 */
	void setStardustRole(RoleType value);

	/**
	 * Returns the value of the '<em><b>Stardust Organization</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Organization</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Organization</em>' containment reference.
	 * @see #setStardustOrganization(OrganizationType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustResourceType_StardustOrganization()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='StardustOrganization' namespace='##targetNamespace'"
	 * @generated
	 */
	OrganizationType getStardustOrganization();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getStardustOrganization <em>Stardust Organization</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Organization</em>' containment reference.
	 * @see #getStardustOrganization()
	 * @generated
	 */
	void setStardustOrganization(OrganizationType value);

	/**
	 * Returns the value of the '<em><b>Data Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Id</em>' attribute.
	 * @see #setDataId(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustResourceType_DataId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='dataId'"
	 * @generated
	 */
	String getDataId();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustResourceType#getDataId <em>Data Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Id</em>' attribute.
	 * @see #getDataId()
	 * @generated
	 */
	void setDataId(String value);

} // StardustResourceType
