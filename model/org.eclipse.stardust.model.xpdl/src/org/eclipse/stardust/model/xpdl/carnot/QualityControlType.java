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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quality Control Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.QualityControlType#getCode <em>Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getQualityControlType()
 * @model extendedMetaData="name='qualityControl_._type' kind='elementOnly'"
 * @generated
 */
public interface QualityControlType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

   /**
    * Returns the value of the '<em><b>Code</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.Code}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The Quality Code.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Code</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getQualityControlType_Code()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='code' namespace='http://www.carnot.ag/xpdl/3.1'"
    * @generated
    */
   EList<Code> getCode();

} // QualityControlType
