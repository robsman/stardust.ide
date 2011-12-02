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
 * A representation of the model object '<em><b>Application Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getAccessPointProviderClass <em>Access Point Provider Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getInstanceClass <em>Instance Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#isSynchronous <em>Synchronous</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getApplications <em>Applications</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationTypeType()
 * @model extendedMetaData="name='applicationType_._type' kind='elementOnly'"
 * @generated
 */
public interface ApplicationTypeType extends IMetaType{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Access Point Provider Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Access Point Provider Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Access Point Provider Class</em>' attribute.
    * @see #setAccessPointProviderClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationTypeType_AccessPointProviderClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='accessPointProviderClass'"
    * @generated
    */
   String getAccessPointProviderClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getAccessPointProviderClass <em>Access Point Provider Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Access Point Provider Class</em>' attribute.
    * @see #getAccessPointProviderClass()
    * @generated
    */
   void setAccessPointProviderClass(String value);

   /**
    * Returns the value of the '<em><b>Instance Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Instance Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Instance Class</em>' attribute.
    * @see #setInstanceClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationTypeType_InstanceClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='instanceClass'"
    * @generated
    */
   String getInstanceClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getInstanceClass <em>Instance Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Instance Class</em>' attribute.
    * @see #getInstanceClass()
    * @generated
    */
   void setInstanceClass(String value);

   /**
    * Returns the value of the '<em><b>Panel Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Panel Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Panel Class</em>' attribute.
    * @see #setPanelClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationTypeType_PanelClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='panelClass'"
    * @generated
    */
   String getPanelClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getPanelClass <em>Panel Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Panel Class</em>' attribute.
    * @see #getPanelClass()
    * @generated
    */
   void setPanelClass(String value);

   /**
    * Returns the value of the '<em><b>Synchronous</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether the application type element describes
    *                   a synchronously executed application or not.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Synchronous</em>' attribute.
    * @see #isSetSynchronous()
    * @see #unsetSynchronous()
    * @see #setSynchronous(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationTypeType_Synchronous()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='synchronous'"
    * @generated
    */
   boolean isSynchronous();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#isSynchronous <em>Synchronous</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Synchronous</em>' attribute.
    * @see #isSetSynchronous()
    * @see #unsetSynchronous()
    * @see #isSynchronous()
    * @generated
    */
   void setSynchronous(boolean value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#isSynchronous <em>Synchronous</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetSynchronous()
    * @see #isSynchronous()
    * @see #setSynchronous(boolean)
    * @generated
    */
   void unsetSynchronous();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#isSynchronous <em>Synchronous</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Synchronous</em>' attribute is set.
    * @see #unsetSynchronous()
    * @see #isSynchronous()
    * @see #setSynchronous(boolean)
    * @generated
    */
   boolean isSetSynchronous();

   /**
    * Returns the value of the '<em><b>Validator Class</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Validator Class</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Validator Class</em>' attribute.
    * @see #setValidatorClass(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationTypeType_ValidatorClass()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='validatorClass'"
    * @generated
    */
   String getValidatorClass();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getValidatorClass <em>Validator Class</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Validator Class</em>' attribute.
    * @see #getValidatorClass()
    * @generated
    */
   void setValidatorClass(String value);

   /**
    * Returns the value of the '<em><b>Applications</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Applications</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Applications</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationTypeType_Applications()
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationType#getType
    * @model opposite="type" transient="true"
    * @generated
    */
   EList<ApplicationType> getApplications();

} // ApplicationTypeType
