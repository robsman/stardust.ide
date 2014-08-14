/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Packages</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ExternalPackages#getExternalPackage <em>External Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getExternalPackages()
 * @model extendedMetaData="name='ExternalPackages_._type' kind='elementOnly'"
 * @generated
 */
public interface ExternalPackages extends EObject {
	/**
	 * Returns the value of the '<em><b>External Package</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ExternalPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Package</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Package</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getExternalPackages_ExternalPackage()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExternalPackage' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ExternalPackage> getExternalPackage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model packageIdDataType="org.eclipse.emf.ecore.xml.type.String"
	 * @generated
	 */
	ExternalPackage getExternalPackage(String packageId);

} // ExternalPackages
