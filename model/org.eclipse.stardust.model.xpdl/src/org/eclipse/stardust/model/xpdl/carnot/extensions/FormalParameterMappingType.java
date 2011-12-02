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
package org.eclipse.stardust.model.xpdl.carnot.extensions;



import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameter Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage#getFormalParameterMappingType()
 * @model extendedMetaData="name='FormalParameterMapping_._type' kind='empty'"
 * @generated
 */
public interface FormalParameterMappingType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

   /**
    * Returns the value of the '<em><b>Data</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Data</em>' reference.
    * @see #setData(DataType)
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage#getFormalParameterMappingType_Data()
    * @model resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='Data'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   DataType getData();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getData <em>Data</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Data</em>' reference.
    * @see #getData()
    * @generated
    */
   void setData(DataType value);

   /**
    * Returns the value of the '<em><b>Parameter</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parameter</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Parameter</em>' reference.
    * @see #setParameter(FormalParameterType)
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage#getFormalParameterMappingType_Parameter()
    * @model resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='FormalParameter'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
    * @generated
    */
   FormalParameterType getParameter();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getParameter <em>Parameter</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Parameter</em>' reference.
    * @see #getParameter()
    * @generated
    */
   void setParameter(FormalParameterType value);

} // FormalParameterMappingType
