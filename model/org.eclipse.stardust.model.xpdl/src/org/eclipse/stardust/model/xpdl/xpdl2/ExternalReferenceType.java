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


import org.eclipse.xsd.XSDSchema;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Reference Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getXref <em>Xref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getUuid <em>Uuid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalReferenceType()
 * @model extendedMetaData="name='ExternalReference_._type' kind='empty'"
 * @generated
 */
public interface ExternalReferenceType extends XpdlTypeType {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * Returns the value of the '<em><b>Location</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Location</em>' attribute.
    * @see #setLocation(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalReferenceType_Location()
    * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI" required="true"
    *        extendedMetaData="kind='attribute' name='location'"
    * @generated
    */
	String getLocation();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getLocation <em>Location</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Location</em>' attribute.
    * @see #getLocation()
    * @generated
    */
	void setLocation(String value);

	/**
    * Returns the value of the '<em><b>Namespace</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Namespace</em>' attribute.
    * @see #setNamespace(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalReferenceType_Namespace()
    * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
    *        extendedMetaData="kind='attribute' name='namespace'"
    * @generated
    */
	String getNamespace();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getNamespace <em>Namespace</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Namespace</em>' attribute.
    * @see #getNamespace()
    * @generated
    */
	void setNamespace(String value);

	/**
    * Returns the value of the '<em><b>Xref</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Xref</em>' attribute.
    * @see #setXref(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalReferenceType_Xref()
    * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN"
    *        extendedMetaData="kind='attribute' name='xref'"
    * @generated
    */
	String getXref();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getXref <em>Xref</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Xref</em>' attribute.
    * @see #getXref()
    * @generated
    */
	void setXref(String value);

	/**
    * Returns the value of the '<em><b>Uuid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Uuid</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Uuid</em>' attribute.
    * @see #setUuid(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalReferenceType_Uuid()
    * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
    *        extendedMetaData="kind='attribute' name='uuid' namespace='http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions'"
    * @generated
    */
   String getUuid();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getUuid <em>Uuid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Uuid</em>' attribute.
    * @see #getUuid()
    * @generated
    */
   void setUuid(String value);

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
	XSDSchema getSchema();

} // ExternalReferenceType