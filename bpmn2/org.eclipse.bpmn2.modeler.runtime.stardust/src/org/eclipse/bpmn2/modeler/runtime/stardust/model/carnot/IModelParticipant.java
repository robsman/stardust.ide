/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IModel Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedSwimlanes <em>Performed Swimlanes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getParticipantAssociations <em>Participant Associations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIModelParticipant()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='modelParticipant_._type' kind='empty'"
 * @generated
 */
public interface IModelParticipant extends IIdentifiableModelElement {
	/**
	 * Returns the value of the '<em><b>Performed Activities</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getPerformer <em>Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed Activities</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIModelParticipant_PerformedActivities()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getPerformer
	 * @model opposite="performer" transient="true"
	 * @generated
	 */
	EList<ActivityType> getPerformedActivities();

	/**
	 * Returns the value of the '<em><b>Performed Swimlanes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed Swimlanes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed Swimlanes</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIModelParticipant_PerformedSwimlanes()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipant
	 * @model opposite="participant" transient="true"
	 * @generated
	 */
	EList<ISwimlaneSymbol> getPerformedSwimlanes();

	/**
	 * Returns the value of the '<em><b>Participant Associations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Associations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Associations</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIModelParticipant_ParticipantAssociations()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParticipantType#getParticipant
	 * @model opposite="participant" transient="true" changeable="false"
	 * @generated
	 */
	EList<ParticipantType> getParticipantAssociations();

} // IModelParticipant
