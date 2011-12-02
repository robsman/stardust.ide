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
 * A representation of the model object '<em><b>IMeta Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIMetaType()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='metaType_._type' kind='empty'"
 * @generated
 */
public interface IMetaType extends IIdentifiableModelElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Is Predefined</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * A boolean that indicates whether this meta type is predefined by CARNOT or model specific.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Is Predefined</em>' attribute.
    * @see #isSetIsPredefined()
    * @see #unsetIsPredefined()
    * @see #setIsPredefined(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIMetaType_IsPredefined()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='predefined'"
    * @generated
    */
   boolean isIsPredefined();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Is Predefined</em>' attribute.
    * @see #isSetIsPredefined()
    * @see #unsetIsPredefined()
    * @see #isIsPredefined()
    * @generated
    */
   void setIsPredefined(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetIsPredefined()
    * @see #isIsPredefined()
    * @see #setIsPredefined(boolean)
    * @generated
    */
   void unsetIsPredefined();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Is Predefined</em>' attribute is set.
    * @see #unsetIsPredefined()
    * @see #isIsPredefined()
    * @see #setIsPredefined(boolean)
    * @generated
    */
   boolean isSetIsPredefined();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    * @generated
    */
   String getExtensionPointId();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" many="false"
    * @generated
    */
   EList<ITypedElement> getTypedElements();

} // IMetaType
