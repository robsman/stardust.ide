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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IEvent Handler Owner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner#getEventHandler <em>Event Handler</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIEventHandlerOwner()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='eventAware_._type' kind='empty'"
 * @generated
 */
public interface IEventHandlerOwner extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Event Handler</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.EventHandlerType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The list of event handlers defined for this model element.
    * 
    * <!-- end-model-doc -->
    * @return the value of the '<em>Event Handler</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIEventHandlerOwner_EventHandler()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='eventHandler' namespace='##targetNamespace'"
    * @generated
    */
   EList<EventHandlerType> getEventHandler();

} // IEventHandlerOwner
