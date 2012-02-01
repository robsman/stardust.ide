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
 * A representation of the model object '<em><b>Extensible</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.Extensible#getExtendedAttributes <em>Extended Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtensible()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Extensible extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Extended Attributes</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Extended Attributes</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Extended Attributes</em>' containment reference.
    * @see #setExtendedAttributes(ExtendedAttributesType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExtensible_ExtendedAttributes()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='ExtendedAttributes' namespace='##targetNamespace'"
    * @generated
    */
   ExtendedAttributesType getExtendedAttributes();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.Extensible#getExtendedAttributes <em>Extended Attributes</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Extended Attributes</em>' containment reference.
    * @see #getExtendedAttributes()
    * @generated
    */
   void setExtendedAttributes(ExtendedAttributesType value);

} // Extensible
