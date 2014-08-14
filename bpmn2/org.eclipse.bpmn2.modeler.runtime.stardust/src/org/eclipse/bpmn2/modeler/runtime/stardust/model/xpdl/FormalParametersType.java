/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameters Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParametersType#getFormalParameter <em>Formal Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getFormalParametersType()
 * @model extendedMetaData="name='FormalParameters_._type' kind='elementOnly'"
 * @generated
 */
public interface FormalParametersType extends EObject {
	/**
	 * Returns the value of the '<em><b>Formal Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParameterType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameter</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getFormalParametersType_FormalParameter()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FormalParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<FormalParameterType> getFormalParameter();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void addFormalParameter(FormalParameterType parameter);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model parameterIdDataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	FormalParameterType getFormalParameter(String parameterId);

} // FormalParametersType
