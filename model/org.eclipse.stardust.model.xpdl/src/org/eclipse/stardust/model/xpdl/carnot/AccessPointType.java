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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Access Point Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AccessPointType#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AccessPointType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAccessPointType()
 * @model extendedMetaData="name='accessPoint_._type' kind='elementOnly'"
 * @generated
 */
public interface AccessPointType extends IIdentifiableModelElement, ITypedElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Direction</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.DirectionType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The direction of the access point. Valid values are "IN",
    *  "OUT" or "INOUT"
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Direction</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DirectionType
    * @see #setDirection(DirectionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAccessPointType_Direction()
    * @model unique="false"
    *        extendedMetaData="kind='attribute' name='direction'"
    * @generated
    */
   DirectionType getDirection();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AccessPointType#getDirection <em>Direction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Direction</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DirectionType
    * @see #getDirection()
    * @generated
    */
   void setDirection(DirectionType value);

   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    * The model id of one of the previously defined dataType elements.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(DataTypeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAccessPointType_Type()
    * @model resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='type'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   DataTypeType getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AccessPointType#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(DataTypeType value);

} // AccessPointType
