/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Declarations Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.TypeDeclarationsType#getTypeDeclaration <em>Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getTypeDeclarationsType()
 * @model extendedMetaData="name='TypeDeclarations_._type' kind='elementOnly'"
 * @generated
 */
public interface TypeDeclarationsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Declaration</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.TypeDeclarationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Declaration</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Declaration</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getTypeDeclarationsType_TypeDeclaration()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TypeDeclaration' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TypeDeclarationType> getTypeDeclaration();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model typeIdDataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	TypeDeclarationType getTypeDeclaration(String typeId);

} // TypeDeclarationsType
