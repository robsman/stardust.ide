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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getCarnotType <em>Carnot Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getDataTypeType()
 * @model extendedMetaData="name='DataType_._type' kind='elementOnly'"
 * @generated
 */
public interface DataTypeType extends EObject
{
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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getDataTypeType_BasicType()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='BasicType' namespace='##targetNamespace'"
    * @generated
    */
   BasicTypeType getBasicType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getBasicType <em>Basic Type</em>}' containment reference.
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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getDataTypeType_DeclaredType()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='DeclaredType' namespace='##targetNamespace'"
    * @generated
    */
   DeclaredTypeType getDeclaredType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getDeclaredType <em>Declared Type</em>}' containment reference.
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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getDataTypeType_SchemaType()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='SchemaType' namespace='##targetNamespace'"
    * @generated
    */
   SchemaTypeType getSchemaType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getSchemaType <em>Schema Type</em>}' containment reference.
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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getDataTypeType_ExternalReference()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='ExternalReference' namespace='##targetNamespace'"
    * @generated
    */
   ExternalReferenceType getExternalReference();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getExternalReference <em>External Reference</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>External Reference</em>' containment reference.
    * @see #getExternalReference()
    * @generated
    */
   void setExternalReference(ExternalReferenceType value);

   /**
    * Returns the value of the '<em><b>Carnot Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Carnot Type</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Carnot Type</em>' attribute.
    * @see #setCarnotType(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getDataTypeType_CarnotType()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='carnotType' namespace='http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions'"
    * @generated
    */
   String getCarnotType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getCarnotType <em>Carnot Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Carnot Type</em>' attribute.
    * @see #getCarnotType()
    * @generated
    */
   void setCarnotType(String value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
   XpdlTypeType getDataType();

} // DataTypeType
