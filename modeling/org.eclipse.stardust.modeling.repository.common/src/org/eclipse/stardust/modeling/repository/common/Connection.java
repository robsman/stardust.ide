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
package org.eclipse.stardust.modeling.repository.common;

import java.util.List;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.util.IConnection;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.Connection#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.Connection#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.Connection#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.Connection#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends EObject, IConnection
{
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
    * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#getConnection_Id()
    * @model
    * @generated
    */
   String getId();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.repository.common.Connection#getId <em>Id</em>}' attribute.
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
    * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#getConnection_Name()
    * @model
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.repository.common.Connection#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see #setType(String)
    * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#getConnection_Type()
    * @model
    * @generated
    */
   String getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.modeling.repository.common.Connection#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see #getType()
    * @generated
    */
   void setType(String value);

   /**
    * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.modeling.repository.common.Attribute}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Attributes</em>' containment reference list.
    * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#getConnection_Attributes()
    * @model containment="true"
    * @generated
    */
   List<Attribute> getAttributes();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   String getAttribute(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   void removeAttribute(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   void setAttribute(String name, String value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   Object getProperty(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   void removeProperty(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   void setProperty(String name, Object value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
   Map<String, Object> getProperties();
} // Connection