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
 * A representation of the model object '<em><b>External Packages</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages#getExternalPackage <em>External Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalPackages()
 * @model extendedMetaData="name='ExternalPackages_._type' kind='elementOnly'"
 * @generated
 */
public interface ExternalPackages extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard";

   /**
    * Returns the value of the '<em><b>External Package</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>External Package</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>External Package</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExternalPackages_ExternalPackage()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='ExternalPackage' namespace='##targetNamespace'"
    * @generated
    */
   EList<ExternalPackage> getExternalPackage();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model packageIdDataType="org.eclipse.emf.ecore.xml.type.String"
    * @generated
    */
   ExternalPackage getExternalPackage(String packageId);

} // ExternalPackages
