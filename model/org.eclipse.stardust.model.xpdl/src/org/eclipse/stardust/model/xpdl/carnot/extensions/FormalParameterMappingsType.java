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



import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameter Mappings Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage#getFormalParameterMappingsType()
 * @model extendedMetaData="name='FormalParameterMappings_._type' kind='elementOnly'"
 * @generated
 */
public interface FormalParameterMappingsType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Mapping</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Mapping</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Mapping</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage#getFormalParameterMappingsType_Mapping()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='FormalParameterMapping' namespace='##targetNamespace'"
    * @generated
    */
   EList<FormalParameterMappingType> getMapping();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   DataType getMappedData(FormalParameterType formalParameter);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model
    * @generated
    */
   void setMappedData(FormalParameterType formalParameter, DataType data);

} // FormalParameterMappingsType
