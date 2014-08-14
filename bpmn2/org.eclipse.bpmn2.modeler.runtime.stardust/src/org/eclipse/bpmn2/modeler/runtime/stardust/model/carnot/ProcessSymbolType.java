/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getSubProcesses <em>Sub Processes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getParentProcesses <em>Parent Processes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessSymbolType()
 * @model extendedMetaData="name='processSymbol_._type' kind='empty'"
 * @generated
 */
public interface ProcessSymbolType extends IModelElementNodeSymbol {
	/**
	 * Returns the value of the '<em><b>Process</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getProcessSymbols <em>Process Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The id of the corresponding activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Process</em>' reference.
	 * @see #setProcess(ProcessDefinitionType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessSymbolType_Process()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getProcessSymbols
	 * @model opposite="processSymbols" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='refer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	ProcessDefinitionType getProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getProcess <em>Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(ProcessDefinitionType value);

	/**
	 * Returns the value of the '<em><b>Sub Processes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getProcessSymbol <em>Process Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Processes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Processes</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessSymbolType_SubProcesses()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getProcessSymbol
	 * @model opposite="processSymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<SubProcessOfConnectionType> getSubProcesses();

	/**
	 * Returns the value of the '<em><b>Parent Processes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getSubprocessSymbol <em>Subprocess Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Processes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Processes</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessSymbolType_ParentProcesses()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType#getSubprocessSymbol
	 * @model opposite="subprocessSymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<SubProcessOfConnectionType> getParentProcesses();

} // ProcessSymbolType
