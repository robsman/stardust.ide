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
package org.eclipse.stardust.model.xpdl.xpdl2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage
 * @generated
 */
public interface XpdlFactory extends EFactory
{
   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	XpdlFactory eINSTANCE = org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Basic Type Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Basic Type Type</em>'.
    * @generated
    */
	BasicTypeType createBasicTypeType();

   /**
    * Returns a new object of class '<em>Data Type Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Data Type Type</em>'.
    * @generated
    */
   DataTypeType createDataTypeType();

   /**
    * Returns a new object of class '<em>Declared Type Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Declared Type Type</em>'.
    * @generated
    */
	DeclaredTypeType createDeclaredTypeType();

   /**
    * Returns a new object of class '<em>Expression Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Expression Type</em>'.
    * @generated
    */
   ExpressionType createExpressionType();

   /**
    * Returns a new object of class '<em>Extended Attributes Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Extended Attributes Type</em>'.
    * @generated
    */
	ExtendedAttributesType createExtendedAttributesType();

   /**
    * Returns a new object of class '<em>Extended Attribute Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Extended Attribute Type</em>'.
    * @generated
    */
	ExtendedAttributeType createExtendedAttributeType();

   /**
    * Returns a new object of class '<em>External Packages</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>External Packages</em>'.
    * @generated
    */
   ExternalPackages createExternalPackages();

   /**
    * Returns a new object of class '<em>External Package</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>External Package</em>'.
    * @generated
    */
   ExternalPackage createExternalPackage();

   /**
    * Returns a new object of class '<em>External Reference Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>External Reference Type</em>'.
    * @generated
    */
	ExternalReferenceType createExternalReferenceType();

   /**
    * Returns a new object of class '<em>Formal Parameter Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Formal Parameter Type</em>'.
    * @generated
    */
   FormalParameterType createFormalParameterType();

   /**
    * Returns a new object of class '<em>Loop Multi Instance Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Loop Multi Instance Type</em>'.
    * @generated
    */
   LoopMultiInstanceType createLoopMultiInstanceType();

   /**
    * Returns a new object of class '<em>Loop Standard Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Loop Standard Type</em>'.
    * @generated
    */
   LoopStandardType createLoopStandardType();

   /**
    * Returns a new object of class '<em>Loop Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Loop Type</em>'.
    * @generated
    */
   LoopType createLoopType();

   /**
    * Returns a new object of class '<em>Formal Parameters Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Formal Parameters Type</em>'.
    * @generated
    */
   FormalParametersType createFormalParametersType();

   /**
    * Returns a new object of class '<em>Schema Type Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Schema Type Type</em>'.
    * @generated
    */
	SchemaTypeType createSchemaTypeType();

   /**
    * Returns a new object of class '<em>Script Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Script Type</em>'.
    * @generated
    */
	ScriptType createScriptType();

   /**
    * Returns a new object of class '<em>Type Declarations Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Type Declarations Type</em>'.
    * @generated
    */
	TypeDeclarationsType createTypeDeclarationsType();

   /**
    * Returns a new object of class '<em>Type Declaration Type</em>'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return a new object of class '<em>Type Declaration Type</em>'.
    * @generated
    */
	TypeDeclarationType createTypeDeclarationType();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
	XpdlPackage getXpdlPackage();

} //XpdlFactory
