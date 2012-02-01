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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Basic Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getBasicTypeType()
 * @model extendedMetaData="name='BasicType_._type' kind='empty'"
 * @generated
 */
public interface BasicTypeType extends XpdlTypeType {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * The default value is <code>"STRING"</code>.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.xpdl2.TypeType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @see #isSetType()
    * @see #unsetType()
    * @see #setType(TypeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getBasicTypeType_Type()
    * @model default="STRING" unsettable="true" required="true"
    *        extendedMetaData="kind='attribute' name='Type'"
    * @generated
    */
	TypeType getType();

	/**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @see #isSetType()
    * @see #unsetType()
    * @see #getType()
    * @generated
    */
	void setType(TypeType value);

	/**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #isSetType()
    * @see #getType()
    * @see #setType(TypeType)
    * @generated
    */
	void unsetType();

	/**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType#getType <em>Type</em>}' attribute is set.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return whether the value of the '<em>Type</em>' attribute is set.
    * @see #unsetType()
    * @see #getType()
    * @see #setType(TypeType)
    * @generated
    */
	boolean isSetType();

} // BasicTypeType