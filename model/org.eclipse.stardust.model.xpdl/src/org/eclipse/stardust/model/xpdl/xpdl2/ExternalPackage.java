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
 * A representation of the model object '<em><b>External Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getHref <em>Href</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalPackage()
 * @model extendedMetaData="name='ExternalPackage_._type' kind='elementOnly'"
 * @generated
 */
public interface ExternalPackage extends Extensible
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Href</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Href</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Href</em>' attribute.
    * @see #setHref(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalPackage_Href()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    *        extendedMetaData="kind='attribute' name='href'"
    * @generated
    */
   String getHref();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getHref <em>Href</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Href</em>' attribute.
    * @see #getHref()
    * @generated
    */
   void setHref(String value);

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalPackage_Id()
    * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
    *        extendedMetaData="kind='attribute' name='Id'"
    * @generated
    */
   String getId();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getId <em>Id</em>}' attribute.
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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalPackage_Name()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='Name'"
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

} // ExternalPackage
