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

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xsd.XSDSchema;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Declaration Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType()
 * @model extendedMetaData="name='TypeDeclaration_._type' kind='elementOnly'"
 * @generated
 */
public interface TypeDeclarationType extends Extensible {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard";

	/**
    * Returns the value of the '<em><b>Basic Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Basic Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Basic Type</em>' containment reference.
    * @see #setBasicType(BasicTypeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType_BasicType()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='BasicType' namespace='##targetNamespace'"
    * @generated
    */
	BasicTypeType getBasicType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getBasicType <em>Basic Type</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Basic Type</em>' containment reference.
    * @see #getBasicType()
    * @generated
    */
	void setBasicType(BasicTypeType value);

	/**
    * Returns the value of the '<em><b>Declared Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Declared Type</em>' containment reference.
    * @see #setDeclaredType(DeclaredTypeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType_DeclaredType()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='DeclaredType' namespace='##targetNamespace'"
    * @generated
    */
	DeclaredTypeType getDeclaredType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDeclaredType <em>Declared Type</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Declared Type</em>' containment reference.
    * @see #getDeclaredType()
    * @generated
    */
	void setDeclaredType(DeclaredTypeType value);

	/**
    * Returns the value of the '<em><b>Schema Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Schema Type</em>' containment reference.
    * @see #setSchemaType(SchemaTypeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType_SchemaType()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='SchemaType' namespace='##targetNamespace'"
    * @generated
    */
	SchemaTypeType getSchemaType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getSchemaType <em>Schema Type</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Schema Type</em>' containment reference.
    * @see #getSchemaType()
    * @generated
    */
	void setSchemaType(SchemaTypeType value);

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType_ExternalReference()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='ExternalReference' namespace='##targetNamespace'"
    * @generated
    */
	ExternalReferenceType getExternalReference();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getExternalReference <em>External Reference</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>External Reference</em>' containment reference.
    * @see #getExternalReference()
    * @generated
    */
	void setExternalReference(ExternalReferenceType value);

	/**
    * Returns the value of the '<em><b>Description</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Description</em>' attribute.
    * @see #setDescription(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType_Description()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='element' name='Description' namespace='##targetNamespace'"
    * @generated
    */
	String getDescription();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDescription <em>Description</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Description</em>' attribute.
    * @see #getDescription()
    * @generated
    */
	void setDescription(String value);

	/**
    * Returns the value of the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Id</em>' attribute.
    * @see #setId(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType_Id()
    * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
    *        extendedMetaData="kind='attribute' name='Id'"
    * @generated
    */
	String getId();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #getId()
    * @generated
    */
	void setId(String value);

	/**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationType_Name()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='Name'"
    * @generated
    */
	String getName();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
	void setName(String value);

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
	XpdlTypeType getDataType();

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
	XSDSchema getSchema();

} // TypeDeclarationType