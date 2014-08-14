/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.ExtensionsPackage
 * @generated
 */
public interface ExtensionsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExtensionsFactory eINSTANCE = org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.impl.ExtensionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Formal Parameter Mapping Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formal Parameter Mapping Type</em>'.
	 * @generated
	 */
	FormalParameterMappingType createFormalParameterMappingType();

	/**
	 * Returns a new object of class '<em>Formal Parameter Mappings Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formal Parameter Mappings Type</em>'.
	 * @generated
	 */
	FormalParameterMappingsType createFormalParameterMappingsType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExtensionsPackage getExtensionsPackage();

} //ExtensionsFactory
