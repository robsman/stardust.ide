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
 * A representation of the model object '<em><b>IModel Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IModelElement#getElementOid <em>Element Oid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelElement()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='modelElement_._type' kind='empty'"
 * @generated
 */
public interface IModelElement extends EObject{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Element Oid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A 32-bit number assigned to the group symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Element Oid</em>' attribute.
    * @see #isSetElementOid()
    * @see #unsetElementOid()
    * @see #setElementOid(long)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelElement_ElementOid()
    * @model unique="false" unsettable="true" id="true" dataType="org.eclipse.emf.ecore.xml.type.Long" required="true"
    *        extendedMetaData="kind='attribute' name='oid'"
    * @generated
    */
   long getElementOid();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IModelElement#getElementOid <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Element Oid</em>' attribute.
    * @see #isSetElementOid()
    * @see #unsetElementOid()
    * @see #getElementOid()
    * @generated
    */
   void setElementOid(long value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IModelElement#getElementOid <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetElementOid()
    * @see #getElementOid()
    * @see #setElementOid(long)
    * @generated
    */
   void unsetElementOid();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IModelElement#getElementOid <em>Element Oid</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Element Oid</em>' attribute is set.
    * @see #unsetElementOid()
    * @see #getElementOid()
    * @see #setElementOid(long)
    * @generated
    */
   boolean isSetElementOid();

} // IModelElement
