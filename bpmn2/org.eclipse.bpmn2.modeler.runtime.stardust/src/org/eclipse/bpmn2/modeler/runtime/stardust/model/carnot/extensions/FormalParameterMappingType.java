/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParameterType;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameter Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingType#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage#getFormalParameterMappingType()
 * @model extendedMetaData="name='FormalParameterMapping_._type' kind='empty'"
 * @generated
 */
public interface FormalParameterMappingType extends EObject {
	/**
	 * Returns the value of the '<em><b>Data</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' reference.
	 * @see #setData(DataType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage#getFormalParameterMappingType_Data()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='Data'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	DataType getData();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingType#getData <em>Data</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' reference.
	 * @see #getData()
	 * @generated
	 */
	void setData(DataType value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(FormalParameterType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage#getFormalParameterMappingType_Parameter()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='FormalParameter'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
	 * @generated
	 */
	FormalParameterType getParameter();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingType#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(FormalParameterType value);

} // FormalParameterMappingType
