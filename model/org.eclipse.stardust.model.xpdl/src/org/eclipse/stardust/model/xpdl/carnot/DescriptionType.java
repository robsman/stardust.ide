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
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Description Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DescriptionType#getMixed <em>Mixed</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDescriptionType()
 * @model extendedMetaData="name='description_._type' kind='mixed'"
 * @generated
 */
public interface DescriptionType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
    * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Mixed</em>' attribute list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDescriptionType_Mixed()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
    *        extendedMetaData="kind='elementWildcard' name=':mixed'"
    * @generated
    */
   FeatureMap getMixed();

} // DescriptionType
