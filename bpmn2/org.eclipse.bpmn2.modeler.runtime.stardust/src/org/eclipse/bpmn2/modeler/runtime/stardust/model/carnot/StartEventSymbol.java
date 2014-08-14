/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Event Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTriggersConnections <em>Triggers Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getStartActivity <em>Start Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getStartEventSymbol()
 * @model extendedMetaData="name='startEventSymbol_._type' kind='empty'"
 * @generated
 */
public interface StartEventSymbol extends AbstractEventSymbol {
	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getStartingEventSymbols <em>Starting Event Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The id of the corresponding activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trigger</em>' reference.
	 * @see #setTrigger(TriggerType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getStartEventSymbol_Trigger()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getStartingEventSymbols
	 * @model opposite="startingEventSymbols" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='refer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
	 * @generated
	 */
	TriggerType getTrigger();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getTrigger <em>Trigger</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(TriggerType value);

	/**
	 * Returns the value of the '<em><b>Triggers Connections</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getStartEventSymbol <em>Start Event Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Triggers Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers Connections</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getStartEventSymbol_TriggersConnections()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType#getStartEventSymbol
	 * @model opposite="startEventSymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<TriggersConnectionType> getTriggersConnections();

	/**
	 * Returns the value of the '<em><b>Start Activity</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getStartingEventSymbols <em>Starting Event Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The id of the activity started by this event.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Activity</em>' reference.
	 * @see #setStartActivity(ActivityType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getStartEventSymbol_StartActivity()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getStartingEventSymbols
	 * @model opposite="startingEventSymbols" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='startActivity'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
	 * @generated
	 */
	ActivityType getStartActivity();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol#getStartActivity <em>Start Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Activity</em>' reference.
	 * @see #getStartActivity()
	 * @generated
	 */
	void setStartActivity(ActivityType value);

} // StartEventSymbol
