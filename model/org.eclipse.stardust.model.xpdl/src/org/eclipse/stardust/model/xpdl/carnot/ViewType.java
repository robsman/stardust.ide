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
 * A representation of the model object '<em><b>View Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ViewType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ViewType#getView <em>View</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ViewType#getViewable <em>Viewable</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ViewType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getViewType()
 * @model extendedMetaData="name='view_._type' kind='elementOnly'"
 * @generated
 */
public interface ViewType extends IModelElement, IExtensibleElement
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
    * 
    *                      An optional description of the view.
    *                   
    * <!-- end-model-doc -->
    * @return the value of the '<em>Description</em>' containment reference.
    * @see #setDescription(DescriptionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getViewType_Description()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
    * @generated
    */
   DescriptionType getDescription();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ViewType#getDescription <em>Description</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Description</em>' containment reference.
    * @see #getDescription()
    * @generated
    */
   void setDescription(DescriptionType value);

   /**
    * Returns the value of the '<em><b>View</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ViewType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                      The list of further dependent views.
    *                   
    * <!-- end-model-doc -->
    * @return the value of the '<em>View</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getViewType_View()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='view' namespace='##targetNamespace'"
    * @generated
    */
   EList<ViewType> getView();

   /**
    * Returns the value of the '<em><b>Viewable</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ViewableType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The list of viewables.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Viewable</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getViewType_Viewable()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='viewable' namespace='##targetNamespace'"
    * @generated
    */
   EList<ViewableType> getViewable();

   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The human friendly name of the view.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getViewType_Name()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    *        extendedMetaData="kind='attribute' name='name'"
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ViewType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

} // ViewType
