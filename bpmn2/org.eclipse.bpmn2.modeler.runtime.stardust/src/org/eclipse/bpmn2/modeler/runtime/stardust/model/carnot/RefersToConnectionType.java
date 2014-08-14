/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Refers To Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getRefersToConnectionType()
 * @model extendedMetaData="name='refersToConnection_._type' kind='empty'"
 * @generated
 */
public interface RefersToConnectionType extends IConnectionSymbol {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingFromConnections <em>Refering From Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the first element symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #isSetFrom()
	 * @see #unsetFrom()
	 * @see #setFrom(IGraphicalObject)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getRefersToConnectionType_From()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingFromConnections
	 * @model opposite="referingFromConnections" resolveProxies="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='from'"
	 * @generated
	 */
	IGraphicalObject getFrom();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #isSetFrom()
	 * @see #unsetFrom()
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(IGraphicalObject value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFrom()
	 * @see #getFrom()
	 * @see #setFrom(IGraphicalObject)
	 * @generated
	 */
	void unsetFrom();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getFrom <em>From</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>From</em>' reference is set.
	 * @see #unsetFrom()
	 * @see #getFrom()
	 * @see #setFrom(IGraphicalObject)
	 * @generated
	 */
	boolean isSetFrom();

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingToConnections <em>Refering To Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the second element symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #isSetTo()
	 * @see #unsetTo()
	 * @see #setTo(IGraphicalObject)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getRefersToConnectionType_To()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IGraphicalObject#getReferingToConnections
	 * @model opposite="referingToConnections" resolveProxies="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='to'"
	 * @generated
	 */
	IGraphicalObject getTo();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #isSetTo()
	 * @see #unsetTo()
	 * @see #getTo()
	 * @generated
	 */
	void setTo(IGraphicalObject value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTo()
	 * @see #getTo()
	 * @see #setTo(IGraphicalObject)
	 * @generated
	 */
	void unsetTo();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType#getTo <em>To</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>To</em>' reference is set.
	 * @see #unsetTo()
	 * @see #getTo()
	 * @see #setTo(IGraphicalObject)
	 * @generated
	 */
	boolean isSetTo();

} // RefersToConnectionType
