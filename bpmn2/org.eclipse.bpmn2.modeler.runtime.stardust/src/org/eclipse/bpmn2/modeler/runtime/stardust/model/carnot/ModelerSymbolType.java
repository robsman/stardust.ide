/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modeler Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType#getModeler <em>Modeler</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelerSymbolType()
 * @model extendedMetaData="name='modelerSymbol_._type' kind='empty'"
 * @generated
 */
public interface ModelerSymbolType extends IModelElementNodeSymbol {
	/**
	 * Returns the value of the '<em><b>Modeler</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getModelerSymbols <em>Modeler Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The id of the corresponding activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modeler</em>' reference.
	 * @see #setModeler(ModelerType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelerSymbolType_Modeler()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType#getModelerSymbols
	 * @model opposite="modelerSymbols" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='refer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	ModelerType getModeler();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType#getModeler <em>Modeler</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modeler</em>' reference.
	 * @see #getModeler()
	 * @generated
	 */
	void setModeler(ModelerType value);

} // ModelerSymbolType
