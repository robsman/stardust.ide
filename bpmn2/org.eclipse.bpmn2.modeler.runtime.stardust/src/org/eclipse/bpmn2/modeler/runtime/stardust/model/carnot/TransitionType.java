/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#isForkOnTraversal <em>Fork On Traversal</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTo <em>To</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTransitionConnections <em>Transition Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTransitionType()
 * @model extendedMetaData="name='transition_._type' kind='elementOnly'"
 * @generated
 */
public interface TransitionType extends IIdentifiableModelElement {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An optional expression for transition condition. Only valid if no condition attribute is existing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(XmlTextNode)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTransitionType_Expression()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='expression' namespace='##targetNamespace'"
	 * @generated
	 */
	XmlTextNode getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(XmlTextNode value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The condition used to verify transition, default value is
	 *                   "TRUE". Now deprecated for conditions beside TRUE and FALSE,
	 *                   more complex conditions should be put into an expression element.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTransitionType_Condition()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='condition'"
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Fork On Traversal</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fork On Traversal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fork On Traversal</em>' attribute.
	 * @see #isSetForkOnTraversal()
	 * @see #unsetForkOnTraversal()
	 * @see #setForkOnTraversal(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTransitionType_ForkOnTraversal()
	 * @model default="false" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='forkOnTraversal'"
	 * @generated
	 */
	boolean isForkOnTraversal();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#isForkOnTraversal <em>Fork On Traversal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fork On Traversal</em>' attribute.
	 * @see #isSetForkOnTraversal()
	 * @see #unsetForkOnTraversal()
	 * @see #isForkOnTraversal()
	 * @generated
	 */
	void setForkOnTraversal(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#isForkOnTraversal <em>Fork On Traversal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetForkOnTraversal()
	 * @see #isForkOnTraversal()
	 * @see #setForkOnTraversal(boolean)
	 * @generated
	 */
	void unsetForkOnTraversal();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#isForkOnTraversal <em>Fork On Traversal</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fork On Traversal</em>' attribute is set.
	 * @see #unsetForkOnTraversal()
	 * @see #isForkOnTraversal()
	 * @see #setForkOnTraversal(boolean)
	 * @generated
	 */
	boolean isSetForkOnTraversal();

	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getOutTransitions <em>Out Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique model id of the starting activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(ActivityType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTransitionType_From()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getOutTransitions
	 * @model opposite="outTransitions" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='from'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
	 * @generated
	 */
	ActivityType getFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(ActivityType value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getInTransitions <em>In Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unique model id of the ending activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(ActivityType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTransitionType_To()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getInTransitions
	 * @model opposite="inTransitions" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='to'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
	 * @generated
	 */
	ActivityType getTo();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(ActivityType value);

	/**
	 * Returns the value of the '<em><b>Transition Connections</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Connections</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTransitionType_TransitionConnections()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTransition
	 * @model opposite="transition" transient="true"
	 * @generated
	 */
	EList<TransitionConnectionType> getTransitionConnections();

} // TransitionType
