/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Triggers Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getStartEventSymbol <em>Start Event Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getParticipantSymbol <em>Participant Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggersConnectionType()
 * @model extendedMetaData="name='triggersConnection_._type' kind='empty'"
 * @generated
 */
public interface TriggersConnectionType extends IConnectionSymbol {
	/**
	 * Returns the value of the '<em><b>Start Event Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTriggersConnections <em>Triggers Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The model oid of the start symbol.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Event Symbol</em>' reference.
	 * @see #setStartEventSymbol(StartEventSymbol)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggersConnectionType_StartEventSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTriggersConnections
	 * @model opposite="triggersConnections" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='startEventSymbol'"
	 * @generated
	 */
	StartEventSymbol getStartEventSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getStartEventSymbol <em>Start Event Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Event Symbol</em>' reference.
	 * @see #getStartEventSymbol()
	 * @generated
	 */
	void setStartEventSymbol(StartEventSymbol value);

	/**
	 * Returns the value of the '<em><b>Participant Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getTriggeredEvents <em>Triggered Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the participant symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participant Symbol</em>' reference.
	 * @see #setParticipantSymbol(IModelParticipantSymbol)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggersConnectionType_ParticipantSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipantSymbol#getTriggeredEvents
	 * @model opposite="triggeredEvents" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='participantSymbol'"
	 * @generated
	 */
	IModelParticipantSymbol getParticipantSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getParticipantSymbol <em>Participant Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant Symbol</em>' reference.
	 * @see #getParticipantSymbol()
	 * @generated
	 */
	void setParticipantSymbol(IModelParticipantSymbol value);

} // TriggersConnectionType
