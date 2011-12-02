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
 * A representation of the model object '<em><b>IIdentifiable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIIdentifiableElement()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='identifiableElement_._type' kind='empty'"
 * @generated
 */
public interface IIdentifiableElement extends EObject{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The human usable identifier of the element. This identifier will usually be used to refer to the model element neutrally to any model version.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Id</em>' attribute.
    * @see #isSetId()
    * @see #unsetId()
    * @see #setId(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIIdentifiableElement_Id()
    * @model unique="false" unsettable="true" dataType="org.eclipse.stardust.model.xpdl.carnot.ElementId" required="true"
    *        extendedMetaData="kind='attribute' name='id'"
    * @generated
    */
   String getId();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Id</em>' attribute.
    * @see #isSetId()
    * @see #unsetId()
    * @see #getId()
    * @generated
    */
   void setId(String value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getId <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetId()
    * @see #getId()
    * @see #setId(String)
    * @generated
    */
   void unsetId();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getId <em>Id</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Id</em>' attribute is set.
    * @see #unsetId()
    * @see #getId()
    * @see #setId(String)
    * @generated
    */
   boolean isSetId();

   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The human friendly name of this element. Usually used as label.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #isSetName()
    * @see #unsetName()
    * @see #setName(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIIdentifiableElement_Name()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='name'"
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #isSetName()
    * @see #unsetName()
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetName()
    * @see #getName()
    * @see #setName(String)
    * @generated
    */
   void unsetName();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement#getName <em>Name</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Name</em>' attribute is set.
    * @see #unsetName()
    * @see #getName()
    * @see #setName(String)
    * @generated
    */
   boolean isSetName();

} // IIdentifiableElement
