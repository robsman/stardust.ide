/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ExternalReferenceType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataMappings <em>Data Mappings</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#isPredefined <em>Predefined</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataSymbols <em>Data Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getConditionalPerformers <em>Conditional Performers</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getDataPaths <em>Data Paths</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getParameterMappings <em>Parameter Mappings</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getExternalReference <em>External Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType()
 * @model extendedMetaData="name='data_._type' kind='elementOnly'"
 * @generated
 */
public interface DataType extends IIdentifiableModelElement, ITypedElement {
	/**
	 * Returns the value of the '<em><b>Data Mappings</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Mappings</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_DataMappings()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingType#getData
	 * @model opposite="data" transient="true"
	 * @generated
	 */
	EList<DataMappingType> getDataMappings();

	/**
	 * Returns the value of the '<em><b>Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   A boolean that indicates whether the data element is predefined or
	 *                   created by modeling.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predefined</em>' attribute.
	 * @see #isSetPredefined()
	 * @see #unsetPredefined()
	 * @see #setPredefined(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_Predefined()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='predefined'"
	 * @generated
	 */
	boolean isPredefined();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#isPredefined <em>Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predefined</em>' attribute.
	 * @see #isSetPredefined()
	 * @see #unsetPredefined()
	 * @see #isPredefined()
	 * @generated
	 */
	void setPredefined(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#isPredefined <em>Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPredefined()
	 * @see #isPredefined()
	 * @see #setPredefined(boolean)
	 * @generated
	 */
	void unsetPredefined();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#isPredefined <em>Predefined</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Predefined</em>' attribute is set.
	 * @see #unsetPredefined()
	 * @see #isPredefined()
	 * @see #setPredefined(boolean)
	 * @generated
	 */
	boolean isSetPredefined();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   Abstract description of the data type of the data element (e.g.
	 *                   primitive, entity). Depending on that type class names are given as
	 *                   attribute elements.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(DataTypeType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_Type()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType#getData
	 * @model opposite="data" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='type'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	DataTypeType getType();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(DataTypeType value);

	/**
	 * Returns the value of the '<em><b>Data Symbols</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Symbols</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Symbols</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_DataSymbols()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType#getData
	 * @model opposite="data" transient="true"
	 * @generated
	 */
	EList<DataSymbolType> getDataSymbols();

	/**
	 * Returns the value of the '<em><b>Conditional Performers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conditional Performers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conditional Performers</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_ConditionalPerformers()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType#getData
	 * @model opposite="data" transient="true"
	 * @generated
	 */
	EList<ConditionalPerformerType> getConditionalPerformers();

	/**
	 * Returns the value of the '<em><b>Data Paths</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Paths</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Paths</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_DataPaths()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType#getData
	 * @model opposite="data" transient="true"
	 * @generated
	 */
	EList<DataPathType> getDataPaths();

	/**
	 * Returns the value of the '<em><b>Parameter Mappings</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Mappings</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_ParameterMappings()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ParameterMappingType#getData
	 * @model opposite="data" transient="true"
	 * @generated
	 */
	EList<ParameterMappingType> getParameterMappings();

	/**
	 * Returns the value of the '<em><b>External Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Reference</em>' containment reference.
	 * @see #setExternalReference(ExternalReferenceType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getDataType_ExternalReference()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExternalReference' namespace='http://www.wfmc.org/2008/XPDL2.1'"
	 * @generated
	 */
	ExternalReferenceType getExternalReference();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType#getExternalReference <em>External Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Reference</em>' containment reference.
	 * @see #getExternalReference()
	 * @generated
	 */
	void setExternalReference(ExternalReferenceType value);

} // DataType
