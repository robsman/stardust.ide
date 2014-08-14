/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParameterType;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameter Mappings Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingsType#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage#getFormalParameterMappingsType()
 * @model extendedMetaData="name='FormalParameterMappings_._type' kind='elementOnly'"
 * @generated
 */
public interface FormalParameterMappingsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Mapping</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapping</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapping</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage#getFormalParameterMappingsType_Mapping()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FormalParameterMapping' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<FormalParameterMappingType> getMapping();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	DataType getMappedData(FormalParameterType formalParameter);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void setMappedData(FormalParameterType formalParameter, DataType data);

} // FormalParameterMappingsType
