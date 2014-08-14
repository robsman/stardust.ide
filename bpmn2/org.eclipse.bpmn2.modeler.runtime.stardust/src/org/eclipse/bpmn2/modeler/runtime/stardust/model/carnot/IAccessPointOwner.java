/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IAccess Point Owner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IAccessPointOwner#getAccessPoint <em>Access Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIAccessPointOwner()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='accessPointOwner_._type' kind='empty'"
 * @generated
 */
public interface IAccessPointOwner extends EObject {
	/**
	 * Returns the value of the '<em><b>Access Point</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AccessPointType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of access points.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Access Point</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIAccessPointOwner_AccessPoint()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='accessPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<AccessPointType> getAccessPoint();

} // IAccessPointOwner
