/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IModel Participant Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getTriggeredEvents <em>Triggered Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIModelParticipantSymbol()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='modelParticipantSymbol_._type' kind='empty'"
 * @generated
 */
public interface IModelParticipantSymbol extends IModelElementNodeSymbol {
	/**
	 * Returns the value of the '<em><b>Performed Activities</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed Activities</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIModelParticipantSymbol_PerformedActivities()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType#getParticipantSymbol
	 * @model opposite="participantSymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<PerformsConnectionType> getPerformedActivities();

	/**
	 * Returns the value of the '<em><b>Triggered Events</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Triggered Events</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggered Events</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIModelParticipantSymbol_TriggeredEvents()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getParticipantSymbol
	 * @model opposite="participantSymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<TriggersConnectionType> getTriggeredEvents();

} // IModelParticipantSymbol
