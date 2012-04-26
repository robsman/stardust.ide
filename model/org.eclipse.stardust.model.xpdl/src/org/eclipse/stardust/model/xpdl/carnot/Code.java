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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Code</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.Code#getCode <em>Code</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.Code#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.Code#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getCode()
 * @model extendedMetaData="name='code_._type' kind='elementOnly'"
 * @generated
 */
public interface Code extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Code</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * Quality Control Code. 
    * <!-- end-model-doc -->
    * @return the value of the '<em>Code</em>' attribute.
    * @see #setCode(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getCode_Code()
    * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='code'"
    * @generated
    */
   String getCode();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Code#getCode <em>Code</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Code</em>' attribute.
    * @see #getCode()
    * @generated
    */
   void setCode(String value);

   /**
    * Returns the value of the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * Quality Control Code Description.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Value</em>' attribute.
    * @see #setValue(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getCode_Value()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='value'"
    * @generated
    */
   String getValue();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Code#getValue <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Value</em>' attribute.
    * @see #getValue()
    * @generated
    */
   void setValue(String value);

   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * Quality Control Code Name.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getCode_Name()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='name'"
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Code#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

} // Code
