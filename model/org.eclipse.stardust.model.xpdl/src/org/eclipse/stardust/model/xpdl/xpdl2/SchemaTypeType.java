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
 * A representation of the model object '<em><b>Schema Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getSchemaTypeType()
 * @model extendedMetaData="name='SchemaType_._type' kind='elementOnly'"
 * @generated
 */
public interface SchemaTypeType extends XpdlTypeType {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * Returns the value of the '<em><b>Schema</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Schema</em>' containment reference.
    * @see #setSchema(XSDSchema)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getSchemaTypeType_Schema()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='schema' namespace='http://www.w3.org/2001/XMLSchema'"
    * @generated
    */
	XSDSchema getSchema();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType#getSchema <em>Schema</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Schema</em>' containment reference.
    * @see #getSchema()
    * @generated
    */
	void setSchema(XSDSchema value);

} // SchemaTypeType