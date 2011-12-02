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
 * A representation of the model object '<em><b>Abstract Event Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAbstractEventAction()
 * @model abstract="true"
 *        extendedMetaData="name='abstractEventAction_._type' kind='empty'"
 * @generated
 */
public interface AbstractEventAction extends IIdentifiableModelElement, ITypedElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Type</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getActionInstances <em>Action Instances</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The type of the bind action. Valid values are the model id's of previously defined eventActionType elements whose
    *  unsupportedContexts-attribute does not contain "bind".
    *  
    * <!-- end-model-doc -->
    * @return the value of the '<em>Type</em>' reference.
    * @see #setType(EventActionTypeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAbstractEventAction_Type()
    * @see org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType#getActionInstances
    * @model opposite="actionInstances" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='type'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   EventActionTypeType getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction#getType <em>Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' reference.
    * @see #getType()
    * @generated
    */
   void setType(EventActionTypeType value);

} // AbstractEventAction
