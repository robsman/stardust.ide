/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.common.util.EList;

import org.eclipse.stardust.model.xpdl.carnot.ContextType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Context Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType#getAccessPoint1 <em>Access Point1</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType#getTypeRef <em>Type Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustContextType()
 * @model extendedMetaData="name='StardustContextType' kind='elementOnly'"
 * @generated
 */
public interface StardustContextType extends ContextType {
	/**
	 * Returns the value of the '<em><b>Access Point1</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Point1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access Point1</em>' containment reference list.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustContextType_AccessPoint1()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='accessPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<StardustAccessPointType> getAccessPoint1();

	/**
	 * Returns the value of the '<em><b>Type Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Ref</em>' attribute.
	 * @see #setTypeRef(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustContextType_TypeRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='typeRef'"
	 * @generated
	 */
	String getTypeRef();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType#getTypeRef <em>Type Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Ref</em>' attribute.
	 * @see #getTypeRef()
	 * @generated
	 */
	void setTypeRef(String value);

} // StardustContextType
