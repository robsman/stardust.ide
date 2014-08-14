/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Handler Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getBindAction <em>Bind Action</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getEventAction <em>Event Action</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getUnbindAction <em>Unbind Action</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isAutoBind <em>Auto Bind</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isConsumeOnMatch <em>Consume On Match</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isLogHandler <em>Log Handler</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isUnbindOnMatch <em>Unbind On Match</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType()
 * @model extendedMetaData="name='eventHandler_._type' kind='elementOnly'"
 * @generated
 */
public interface EventHandlerType extends IIdentifiableModelElement, ITypedElement, IAccessPointOwner {
	/**
	 * Returns the value of the '<em><b>Bind Action</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.BindActionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of bind actions which are associated with this event
	 *                      handler.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bind Action</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_BindAction()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='bindAction' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<BindActionType> getBindAction();

	/**
	 * Returns the value of the '<em><b>Event Action</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of event actions which are associated with this event
	 *                      handler.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Action</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_EventAction()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='eventAction' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<EventActionType> getEventAction();

	/**
	 * Returns the value of the '<em><b>Unbind Action</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.UnbindActionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of unbind actions which are associated with this event
	 *                      handler.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unbind Action</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_UnbindAction()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='unbindAction' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<UnbindActionType> getUnbindAction();

	/**
	 * Returns the value of the '<em><b>Auto Bind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   A boolean that indicates whether the event handler is automatically
	 *                   bound on activity or workflow process startup.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Auto Bind</em>' attribute.
	 * @see #isSetAutoBind()
	 * @see #unsetAutoBind()
	 * @see #setAutoBind(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_AutoBind()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='autoBind'"
	 * @generated
	 */
	boolean isAutoBind();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isAutoBind <em>Auto Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Bind</em>' attribute.
	 * @see #isSetAutoBind()
	 * @see #unsetAutoBind()
	 * @see #isAutoBind()
	 * @generated
	 */
	void setAutoBind(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isAutoBind <em>Auto Bind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAutoBind()
	 * @see #isAutoBind()
	 * @see #setAutoBind(boolean)
	 * @generated
	 */
	void unsetAutoBind();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isAutoBind <em>Auto Bind</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Auto Bind</em>' attribute is set.
	 * @see #unsetAutoBind()
	 * @see #isAutoBind()
	 * @see #setAutoBind(boolean)
	 * @generated
	 */
	boolean isSetAutoBind();

	/**
	 * Returns the value of the '<em><b>Consume On Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Consume On Match</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Consume On Match</em>' attribute.
	 * @see #isSetConsumeOnMatch()
	 * @see #unsetConsumeOnMatch()
	 * @see #setConsumeOnMatch(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_ConsumeOnMatch()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='consumeOnMatch'"
	 * @generated
	 */
	boolean isConsumeOnMatch();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isConsumeOnMatch <em>Consume On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Consume On Match</em>' attribute.
	 * @see #isSetConsumeOnMatch()
	 * @see #unsetConsumeOnMatch()
	 * @see #isConsumeOnMatch()
	 * @generated
	 */
	void setConsumeOnMatch(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isConsumeOnMatch <em>Consume On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConsumeOnMatch()
	 * @see #isConsumeOnMatch()
	 * @see #setConsumeOnMatch(boolean)
	 * @generated
	 */
	void unsetConsumeOnMatch();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isConsumeOnMatch <em>Consume On Match</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Consume On Match</em>' attribute is set.
	 * @see #unsetConsumeOnMatch()
	 * @see #isConsumeOnMatch()
	 * @see #setConsumeOnMatch(boolean)
	 * @generated
	 */
	boolean isSetConsumeOnMatch();

	/**
	 * Returns the value of the '<em><b>Log Handler</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Log Handler</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Log Handler</em>' attribute.
	 * @see #isSetLogHandler()
	 * @see #unsetLogHandler()
	 * @see #setLogHandler(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_LogHandler()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='logHandler'"
	 * @generated
	 */
	boolean isLogHandler();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isLogHandler <em>Log Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Log Handler</em>' attribute.
	 * @see #isSetLogHandler()
	 * @see #unsetLogHandler()
	 * @see #isLogHandler()
	 * @generated
	 */
	void setLogHandler(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isLogHandler <em>Log Handler</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetLogHandler()
	 * @see #isLogHandler()
	 * @see #setLogHandler(boolean)
	 * @generated
	 */
	void unsetLogHandler();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isLogHandler <em>Log Handler</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Log Handler</em>' attribute is set.
	 * @see #unsetLogHandler()
	 * @see #isLogHandler()
	 * @see #setLogHandler(boolean)
	 * @generated
	 */
	boolean isSetLogHandler();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getEventHandlers <em>Event Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The type of the event handler. Valid values are the model id's of
	 *                   previously defined eventConditionType elements.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(EventConditionTypeType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_Type()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType#getEventHandlers
	 * @model opposite="eventHandlers" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='type'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	EventConditionTypeType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EventConditionTypeType value);

	/**
	 * Returns the value of the '<em><b>Unbind On Match</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   A boolean that indicates whether the event handler is automatically
	 *                   unbound on matching an event.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unbind On Match</em>' attribute.
	 * @see #isSetUnbindOnMatch()
	 * @see #unsetUnbindOnMatch()
	 * @see #setUnbindOnMatch(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getEventHandlerType_UnbindOnMatch()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='unbindOnMatch'"
	 * @generated
	 */
	boolean isUnbindOnMatch();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isUnbindOnMatch <em>Unbind On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unbind On Match</em>' attribute.
	 * @see #isSetUnbindOnMatch()
	 * @see #unsetUnbindOnMatch()
	 * @see #isUnbindOnMatch()
	 * @generated
	 */
	void setUnbindOnMatch(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isUnbindOnMatch <em>Unbind On Match</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnbindOnMatch()
	 * @see #isUnbindOnMatch()
	 * @see #setUnbindOnMatch(boolean)
	 * @generated
	 */
	void unsetUnbindOnMatch();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventHandlerType#isUnbindOnMatch <em>Unbind On Match</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unbind On Match</em>' attribute is set.
	 * @see #unsetUnbindOnMatch()
	 * @see #isUnbindOnMatch()
	 * @see #setUnbindOnMatch(boolean)
	 * @generated
	 */
	boolean isSetUnbindOnMatch();

} // EventHandlerType
