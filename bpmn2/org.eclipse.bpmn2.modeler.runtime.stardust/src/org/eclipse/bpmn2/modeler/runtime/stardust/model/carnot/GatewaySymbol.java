/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gateway Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getFlowKind <em>Flow Kind</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getActivitySymbol <em>Activity Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getGatewaySymbol()
 * @model extendedMetaData="name='gatewaySymbol_._type' kind='empty'"
 * @generated
 */
public interface GatewaySymbol extends IFlowObjectSymbol {
	/**
	 * Returns the value of the '<em><b>Flow Kind</b></em>' attribute.
	 * The default value is <code>"none"</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of flow control. Valid values are: "none", "join" or "split".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Flow Kind</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType
	 * @see #isSetFlowKind()
	 * @see #unsetFlowKind()
	 * @see #setFlowKind(FlowControlType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getGatewaySymbol_FlowKind()
	 * @model default="none" unique="false" unsettable="true"
	 *        extendedMetaData="kind='attribute' name='flowKind'"
	 * @generated
	 */
	FlowControlType getFlowKind();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getFlowKind <em>Flow Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flow Kind</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FlowControlType
	 * @see #isSetFlowKind()
	 * @see #unsetFlowKind()
	 * @see #getFlowKind()
	 * @generated
	 */
	void setFlowKind(FlowControlType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getFlowKind <em>Flow Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFlowKind()
	 * @see #getFlowKind()
	 * @see #setFlowKind(FlowControlType)
	 * @generated
	 */
	void unsetFlowKind();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getFlowKind <em>Flow Kind</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Flow Kind</em>' attribute is set.
	 * @see #unsetFlowKind()
	 * @see #getFlowKind()
	 * @see #setFlowKind(FlowControlType)
	 * @generated
	 */
	boolean isSetFlowKind();

	/**
	 * Returns the value of the '<em><b>Activity Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getGatewaySymbols <em>Gateway Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The oid of the corresponding activity symbol.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity Symbol</em>' reference.
	 * @see #setActivitySymbol(ActivitySymbolType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getGatewaySymbol_ActivitySymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getGatewaySymbols
	 * @model opposite="gatewaySymbols" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='refer'"
	 * @generated
	 */
	ActivitySymbolType getActivitySymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol#getActivitySymbol <em>Activity Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Symbol</em>' reference.
	 * @see #getActivitySymbol()
	 * @generated
	 */
	void setActivitySymbol(ActivitySymbolType value);

} // GatewaySymbol
