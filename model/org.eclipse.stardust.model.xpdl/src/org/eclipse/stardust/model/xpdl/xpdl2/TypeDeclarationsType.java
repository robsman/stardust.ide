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
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType#getTypeDeclaration <em>Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationsType()
 * @model extendedMetaData="name='TypeDeclarations_._type' kind='elementOnly'"
 * @generated
 */
public interface TypeDeclarationsType extends EObject {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * Returns the value of the '<em><b>Type Declaration</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType}.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Declaration</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Type Declaration</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getTypeDeclarationsType_TypeDeclaration()
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