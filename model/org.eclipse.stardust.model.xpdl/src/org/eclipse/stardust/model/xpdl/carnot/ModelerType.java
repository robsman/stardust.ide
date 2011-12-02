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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modeler Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ModelerType#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ModelerType#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ModelerType#getModelerSymbols <em>Modeler Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getModelerType()
 * @model extendedMetaData="name='modeler_._type' kind='elementOnly'"
 * @generated
 */
public interface ModelerType extends IIdentifiableModelElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Email</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The e-mail address of the modeler.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Email</em>' attribute.
    * @see #setEmail(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getModelerType_Email()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='email'"
    * @generated
    */
   String getEmail();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ModelerType#getEmail <em>Email</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Email</em>' attribute.
    * @see #getEmail()
    * @generated
    */
   void setEmail(String value);

   /**
    * Returns the value of the '<em><b>Password</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The password for the modeler.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Password</em>' attribute.
    * @see #setPassword(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getModelerType_Password()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='password'"
    * @generated
    */
   String getPassword();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ModelerType#getPassword <em>Password</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Password</em>' attribute.
    * @see #getPassword()
    * @generated
    */
   void setPassword(String value);

   /**
    * Returns the value of the '<em><b>Modeler Symbols</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType#getModeler <em>Modeler</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Modeler Symbols</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Modeler Symbols</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getModelerType_ModelerSymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType#getModeler
    * @model opposite="modeler" transient="true"
    * @generated
    */
   EList<ModelerSymbolType> getModelerSymbols();

} // ModelerType
