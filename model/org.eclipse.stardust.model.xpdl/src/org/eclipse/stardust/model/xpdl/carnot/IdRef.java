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
package org.eclipse.stardust.model.xpdl.carnot;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Id Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IdRef#getPackageRef <em>Package Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IdRef#getRef <em>Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdRef()
 * @model extendedMetaData="name='IdRef_._type' kind='empty'"
 * @generated
 */
public interface IdRef extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Package Ref</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Package Ref</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Package Ref</em>' reference.
    * @see #setPackageRef(ExternalPackage)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdRef_PackageRef()
    * @model resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='PackageRef'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   ExternalPackage getPackageRef();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IdRef#getPackageRef <em>Package Ref</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Package Ref</em>' reference.
    * @see #getPackageRef()
    * @generated
    */
   void setPackageRef(ExternalPackage value);

   /**
    * Returns the value of the '<em><b>Ref</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Ref</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Ref</em>' attribute.
    * @see #setRef(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIdRef_Ref()
    * @model required="true"
    *        extendedMetaData="kind='attribute' name='ref'"
    * @generated
    */
   String getRef();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IdRef#getRef <em>Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Ref</em>' attribute.
    * @see #getRef()
    * @generated
    */
   void setRef(String value);

   <T extends IIdentifiableModelElement> T get(Class<T> clz);

   <T extends IIdentifiableModelElement> void set(T identifiable);

} // IdRef
