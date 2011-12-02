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
 * A representation of the model object '<em><b>Application Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#isInteractive <em>Interactive</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#getExecutedActivities <em>Executed Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#getApplicationSymbols <em>Application Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationType()
 * @model extendedMetaData="name='application_._type' kind='elementOnly'"
 * @generated
 */
public interface ApplicationType extends IIdentifiableModelElement, ITypedElement, IAccessPointOwner
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Context</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ContextType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The list of application contexts.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Context</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationType_Context()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='context' namespace='##targetNamespace'"
    * @generated
    */
   EList<ContextType> getContext();

   /**
    * Returns the value of the '<em><b>Interactive</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A boolean that indicates whether this application is interactive or not.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Interactive</em>' attribute.
    * @see #setInteractive(boolean)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationType_Interactive()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
    *        extendedMetaData="kind='attribute' name='interactive'"
    * @generated
    */
   boolean isInteractive();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#isInteractive <em>Interactive</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Interactive</em>' attribute.
    * @see #isInteractive()
    * @generated
    */
   void setInteractive(boolean value);

   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getApplications <em>Applications</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the applicationType.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(ApplicationTypeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationType_Type()
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType#getApplications
    * @model opposite="applications" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='type'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   ApplicationTypeType getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(ApplicationTypeType value);

   /**
    * Returns the value of the '<em><b>Executed Activities</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ActivityType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ActivityType#getApplication <em>Application</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Executed Activities</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Executed Activities</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationType_ExecutedActivities()
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivityType#getApplication
    * @model opposite="application" transient="true"
    * @generated
    */
   EList<ActivityType> getExecutedActivities();

   /**
    * Returns the value of the '<em><b>Application Symbols</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType#getApplication <em>Application</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Application Symbols</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Application Symbols</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getApplicationType_ApplicationSymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType#getApplication
    * @model opposite="application" transient="true"
    * @generated
    */
   EList<ApplicationSymbolType> getApplicationSymbols();

} // ApplicationType
