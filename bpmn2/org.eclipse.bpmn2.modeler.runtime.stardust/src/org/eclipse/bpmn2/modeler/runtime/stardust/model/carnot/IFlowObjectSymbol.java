/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IFlow Object Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol#getInTransitions <em>In Transitions</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IFlowObjectSymbol#getOutTransitions <em>Out Transitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIFlowObjectSymbol()
 * @model abstract="true"
 *        extendedMetaData="name='flowObjectSymbol_._type' kind='empty'"
 * @generated
 */
public interface IFlowObjectSymbol extends INodeSymbol {
	/**
	 * Returns the value of the '<em><b>In Transitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTargetActivitySymbol <em>Target Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Transitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Transitions</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIFlowObjectSymbol_InTransitions()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getTargetActivitySymbol
	 * @model opposite="targetActivitySymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<TransitionConnectionType> getInTransitions();

	/**
	 * Returns the value of the '<em><b>Out Transitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getSourceActivitySymbol <em>Source Activity Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Transitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Transitions</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIFlowObjectSymbol_OutTransitions()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType#getSourceActivitySymbol
	 * @model opposite="sourceActivitySymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<TransitionConnectionType> getOutTransitions();

} // IFlowObjectSymbol
