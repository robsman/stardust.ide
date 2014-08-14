/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IEvent Handler Owner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IEventHandlerOwner#getEventHandler <em>Event Handler</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIEventHandlerOwner()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='eventAware_._type' kind='empty'"
 * @generated
 */
public interface IEventHandlerOwner extends EObject {
	/**
	 * Returns the value of the '<em><b>Event Handler</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of event handlers defined for this model element.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Handler</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIEventHandlerOwner_EventHandler()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='eventHandler' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<EventHandlerType> getEventHandler();

} // IEventHandlerOwner
