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
 * A representation of the model object '<em><b>Formal Parameters Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType#getFormalParameter <em>Formal Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParametersType()
 * @model extendedMetaData="name='FormalParameters_._type' kind='elementOnly'"
 * @generated
 */
public interface FormalParametersType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Formal Parameter</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Formal Parameter</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Formal Parameter</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getFormalParametersType_FormalParameter()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='FormalParameter' namespace='##targetNamespace'"
    * @generated
    */
   EList<FormalParameterType> getFormalParameter();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   void addFormalParameter(FormalParameterType parameter);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model parameterIdDataType="org.eclipse.emf.ecore.xml.type.String"
    * @generated
    */
   FormalParameterType getFormalParameter(String parameterId);

} // FormalParametersType
