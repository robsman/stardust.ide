/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Timer Start Event Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType#getStardustAttributes <em>Stardust Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustTimerStartEventType()
 * @model extendedMetaData="name='StardustTimerStartEvent_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustTimerStartEventType extends EObject {
	/**
	 * Returns the value of the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Attributes</em>' containment reference.
	 * @see #setStardustAttributes(StardustAttributesType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustTimerStartEventType_StardustAttributes()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='StardustAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustAttributesType getStardustAttributes();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustTimerStartEventType#getStardustAttributes <em>Stardust Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Attributes</em>' containment reference.
	 * @see #getStardustAttributes()
	 * @generated
	 */
	void setStardustAttributes(StardustAttributesType value);

} // StardustTimerStartEventType