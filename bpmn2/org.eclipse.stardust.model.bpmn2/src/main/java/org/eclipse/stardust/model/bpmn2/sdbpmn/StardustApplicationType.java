/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.common.util.EList;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Application Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType#getAccessPoint1 <em>Access Point1</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustApplicationType#getContext1 <em>Context1</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustApplicationType()
 * @model extendedMetaData="name='StardustApplicationType' kind='elementOnly'"
 * @generated
 */
public interface StardustApplicationType extends ApplicationType {
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
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustApplicationType_AccessPoint1()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='accessPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<StardustAccessPointType> getAccessPoint1();

	/**
	 * Returns the value of the '<em><b>Context1</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustContextType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context1</em>' containment reference list.
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustApplicationType_Context1()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='context' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<StardustContextType> getContext1();

} // StardustApplicationType
