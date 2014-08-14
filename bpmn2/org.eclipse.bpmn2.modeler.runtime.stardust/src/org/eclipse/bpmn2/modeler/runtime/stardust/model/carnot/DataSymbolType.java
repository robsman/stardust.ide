/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getDataMappings <em>Data Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataSymbolType()
 * @model extendedMetaData="name='dataSymbol_._type' kind='empty'"
 * @generated
 */
public interface DataSymbolType extends IModelElementNodeSymbol {
	/**
	 * Returns the value of the '<em><b>Data</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataSymbols <em>Data Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The id of the corresponding activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data</em>' reference.
	 * @see #setData(DataType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataSymbolType_Data()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataSymbols
	 * @model opposite="dataSymbols" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='refer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	DataType getData();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getData <em>Data</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' reference.
	 * @see #getData()
	 * @generated
	 */
	void setData(DataType value);

	/**
	 * Returns the value of the '<em><b>Data Mappings</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getDataSymbol <em>Data Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Mappings</em>' reference list.
	 * @see #isSetDataMappings()
	 * @see #unsetDataMappings()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataSymbolType_DataMappings()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getDataSymbol
	 * @model opposite="dataSymbol" resolveProxies="false" unsettable="true" transient="true"
	 * @generated
	 */
	EList<DataMappingConnectionType> getDataMappings();

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getDataMappings <em>Data Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDataMappings()
	 * @see #getDataMappings()
	 * @generated
	 */
	void unsetDataMappings();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getDataMappings <em>Data Mappings</em>}' reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Data Mappings</em>' reference list is set.
	 * @see #unsetDataMappings()
	 * @see #getDataMappings()
	 * @generated
	 */
	boolean isSetDataMappings();

} // DataSymbolType
