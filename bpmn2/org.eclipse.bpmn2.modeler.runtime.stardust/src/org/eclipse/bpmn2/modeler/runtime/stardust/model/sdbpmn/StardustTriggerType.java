/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Trigger Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustTriggerType#getAccessPoint1 <em>Access Point1</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustTriggerType#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustTriggerType()
 * @model extendedMetaData="name='StardustTriggerType' kind='elementOnly'"
 * @generated
 */
public interface StardustTriggerType extends TriggerType {
	/**
	 * Returns the value of the '<em><b>Access Point1</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustAccessPointType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Point1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access Point1</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustTriggerType_AccessPoint1()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='accessPoint' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<StardustAccessPointType> getAccessPoint1();

	/**
	 * Returns the value of the '<em><b>Context</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustContextType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustTriggerType_Context()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='context' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<StardustContextType> getContext();

} // StardustTriggerType
