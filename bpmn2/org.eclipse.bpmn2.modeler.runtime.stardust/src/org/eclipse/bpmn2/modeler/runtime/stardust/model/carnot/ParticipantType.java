/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType#getParticipant <em>Participant</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getParticipantType()
 * @model extendedMetaData="name='participant_._type' kind='empty'"
 * @generated
 */
public interface ParticipantType extends EObject {
	/**
	 * Returns the value of the '<em><b>Participant</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getParticipantAssociations <em>Participant Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of either an organization or role.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participant</em>' reference.
	 * @see #setParticipant(IModelParticipant)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getParticipantType_Participant()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getParticipantAssociations
	 * @model opposite="participantAssociations" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='participant'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	IModelParticipant getParticipant();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType#getParticipant <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant</em>' reference.
	 * @see #getParticipant()
	 * @generated
	 */
	void setParticipant(IModelParticipant value);

} // ParticipantType
