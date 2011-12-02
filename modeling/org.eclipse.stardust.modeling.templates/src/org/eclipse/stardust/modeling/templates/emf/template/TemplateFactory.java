/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.modeling.templates.emf.template;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage
 * @generated
 */
public interface TemplateFactory extends EFactory {
	/**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	TemplateFactory eINSTANCE = org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateFactoryImpl.init();

	/**
    * Returns a new object of class '<em>Documentation Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Documentation Type</em>'.
    * @generated
    */
	DocumentationType createDocumentationType();

	/**
    * Returns a new object of class '<em>Feature Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Feature Type</em>'.
    * @generated
    */
	FeatureType createFeatureType();

	/**
    * Returns a new object of class '<em>Parameter Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Parameter Type</em>'.
    * @generated
    */
	ParameterType createParameterType();

	/**
    * Returns a new object of class '<em>Reference Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Reference Type</em>'.
    * @generated
    */
	ReferenceType createReferenceType();

	/**
    * Returns a new object of class '<em>Roots Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Roots Type</em>'.
    * @generated
    */
	RootsType createRootsType();

	/**
    * Returns a new object of class '<em>Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Type</em>'.
    * @generated
    */
	TemplateType createTemplateType();

	/**
    * Returns a new object of class '<em>Templates Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Templates Type</em>'.
    * @generated
    */
	TemplatesType createTemplatesType();

	/**
    * Returns a new object of class '<em>Library Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Library Type</em>'.
    * @generated
    */
	TemplateLibraryType createTemplateLibraryType();

	/**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
	TemplatePackage getTemplatePackage();

} //TemplateFactory
