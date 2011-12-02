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
 * A representation of the model object '<em><b>Context Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ContextType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ContextType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getContextType()
 * @model extendedMetaData="name='context_._type' kind='elementOnly'"
 * @generated
 */
public interface ContextType extends IModelElement, IExtensibleElement, ITypedElement, IAccessPointOwner
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
    *                      An optional description of the context.
    *                   
    * <!-- end-model-doc -->
    * @return the value of the '<em>Description</em>' containment reference.
    * @see #setDescription(DescriptionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getContextType_Description()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
    * @generated
    */
   DescriptionType getDescription();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ContextType#getDescription <em>Description</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Description</em>' containment reference.
    * @see #getDescription()
    * @generated
    */
   void setDescription(DescriptionType value);

   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType#getContexts <em>Contexts</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The type of the context for interactive applications. Valid values are
    *                   the following model id's of previously defined applicationContextType
    *                   elements: "jfc" | "jsp".
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(ApplicationContextTypeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getContextType_Type()
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType#getContexts
    * @model opposite="contexts" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='type'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   ApplicationContextTypeType getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ContextType#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(ApplicationContextTypeType value);

} // ContextType
