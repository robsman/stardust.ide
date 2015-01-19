/*******************************************************************************
 * Copyright (c) 2014 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.carnot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Id Ref Owner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IdRefOwner#getExternalRef <em>External Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdRefOwner()
 * @model abstract="true"
 * @generated
 */
public interface IdRefOwner extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

   /**
    * Returns the value of the '<em><b>External Ref</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>External Ref</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>External Ref</em>' containment reference.
    * @see #setExternalRef(IdRef)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdRefOwner_ExternalRef()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='externalReference' namespace='##targetNamespace'"
    * @generated
    */
   IdRef getExternalRef();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IdRefOwner#getExternalRef <em>External Ref</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>External Ref</em>' containment reference.
    * @see #getExternalRef()
    * @generated
    */
   void setExternalRef(IdRef value);

} // IdRefOwner
