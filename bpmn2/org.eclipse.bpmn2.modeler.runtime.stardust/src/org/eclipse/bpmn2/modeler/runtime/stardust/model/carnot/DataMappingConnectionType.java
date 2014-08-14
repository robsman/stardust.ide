/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Mapping Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getActivitySymbol <em>Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getDataSymbol <em>Data Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataMappingConnectionType()
 * @model extendedMetaData="name='dataMappingConnection_._type' kind='empty'"
 * @generated
 */
public interface DataMappingConnectionType extends IConnectionSymbol {
	/**
	 * Returns the value of the '<em><b>Activity Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getDataMappings <em>Data Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the corresponding activity symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity Symbol</em>' reference.
	 * @see #setActivitySymbol(ActivitySymbolType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataMappingConnectionType_ActivitySymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType#getDataMappings
	 * @model opposite="dataMappings" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='activitySymbol'"
	 * @generated
	 */
	ActivitySymbolType getActivitySymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getActivitySymbol <em>Activity Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity Symbol</em>' reference.
	 * @see #getActivitySymbol()
	 * @generated
	 */
	void setActivitySymbol(ActivitySymbolType value);

	/**
	 * Returns the value of the '<em><b>Data Symbol</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getDataMappings <em>Data Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model id of the corresponding data symbol.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Symbol</em>' reference.
	 * @see #setDataSymbol(DataSymbolType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataMappingConnectionType_DataSymbol()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getDataMappings
	 * @model opposite="dataMappings" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='dataSymbol'"
	 * @generated
	 */
	DataSymbolType getDataSymbol();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType#getDataSymbol <em>Data Symbol</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Symbol</em>' reference.
	 * @see #getDataSymbol()
	 * @generated
	 */
	void setDataSymbol(DataSymbolType value);

} // DataMappingConnectionType
