/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.stardust.model.xpdl.carnot.AccessPointType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Access Point Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType#getTypeRef <em>Type Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustAccessPointType()
 * @model extendedMetaData="name='StardustAccessPointType' kind='elementOnly'"
 * @generated
 */
public interface StardustAccessPointType extends AccessPointType {
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
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustAccessPointType_TypeRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='typeRef'"
	 * @generated
	 */
	String getTypeRef();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustAccessPointType#getTypeRef <em>Type Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Ref</em>' attribute.
	 * @see #getTypeRef()
	 * @generated
	 */
	void setTypeRef(String value);

} // StardustAccessPointType
