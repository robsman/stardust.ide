/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pool Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#isBoundaryVisible <em>Boundary Visible</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getLanes <em>Lanes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPoolSymbol()
 * @model extendedMetaData="name='poolSymbol_._type' kind='empty'"
 * @generated
 */
public interface PoolSymbol extends ISymbolContainer, ISwimlaneSymbol {
	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getPoolSymbols <em>Pool Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPoolSymbol_Diagram()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType#getPoolSymbols
	 * @model opposite="poolSymbols" resolveProxies="false" required="true" changeable="false"
	 * @generated
	 */
	DiagramType getDiagram();

	/**
	 * Returns the value of the '<em><b>Boundary Visible</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates if the pool boundary is drawn or not.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Boundary Visible</em>' attribute.
	 * @see #setBoundaryVisible(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPoolSymbol_BoundaryVisible()
	 * @model default="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
	 *        extendedMetaData="kind='attribute' name='boundaryVisible'"
	 * @generated
	 */
	boolean isBoundaryVisible();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#isBoundaryVisible <em>Boundary Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Boundary Visible</em>' attribute.
	 * @see #isBoundaryVisible()
	 * @generated
	 */
	void setBoundaryVisible(boolean value);

	/**
	 * Returns the value of the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A reference to the associated process, if existent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Process</em>' reference.
	 * @see #setProcess(ProcessDefinitionType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPoolSymbol_Process()
	 * @model resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='process'"
	 * @generated
	 */
	ProcessDefinitionType getProcess();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PoolSymbol#getProcess <em>Process</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(ProcessDefinitionType value);

	/**
	 * Returns the value of the '<em><b>Lanes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentPool <em>Parent Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of lanes contained in this pool.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lanes</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getPoolSymbol_Lanes()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentPool
	 * @model opposite="parentPool" containment="true"
	 *        extendedMetaData="kind='element' name='laneSymbol' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<LaneSymbol> getLanes();

} // PoolSymbol
