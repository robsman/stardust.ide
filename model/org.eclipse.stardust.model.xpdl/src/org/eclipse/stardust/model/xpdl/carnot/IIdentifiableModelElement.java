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
 * A representation of the model object '<em><b>IIdentifiable Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIIdentifiableModelElement()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='identifiableModelElement_._type' kind='empty'"
 * @generated
 */
public interface IIdentifiableModelElement extends IModelElement, IIdentifiableElement, IExtensibleElement
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Description</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * An optional description of the model element.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Description</em>' containment reference.
    * @see #setDescription(DescriptionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIIdentifiableModelElement_Description()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
    * @generated
    */
   DescriptionType getDescription();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement#getDescription <em>Description</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Description</em>' containment reference.
    * @see #getDescription()
    * @generated
    */
   void setDescription(DescriptionType value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" many="false"
    * @generated
    */
   EList<INodeSymbol> getSymbols();

} // IIdentifiableModelElement
