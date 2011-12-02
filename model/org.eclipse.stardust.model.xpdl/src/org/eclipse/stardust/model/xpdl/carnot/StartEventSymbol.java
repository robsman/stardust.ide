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
 * A representation of the model object '<em><b>Start Event Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getTriggersConnections <em>Triggers Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getStartActivity <em>Start Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getStartEventSymbol()
 * @model extendedMetaData="name='startEventSymbol_._type' kind='empty'"
 * @generated
 */
public interface StartEventSymbol extends AbstractEventSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Trigger</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TriggerType#getStartingEventSymbols <em>Starting Event Symbols</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the corresponding activity.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Trigger</em>' reference.
    * @see #setTrigger(TriggerType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getStartEventSymbol_Trigger()
    * @see org.eclipse.stardust.model.xpdl.carnot.TriggerType#getStartingEventSymbols
    * @model opposite="startingEventSymbols" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='refer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
    * @generated
    */
   TriggerType getTrigger();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getTrigger <em>Trigger</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Trigger</em>' reference.
    * @see #getTrigger()
    * @generated
    */
   void setTrigger(TriggerType value);

   /**
    * Returns the value of the '<em><b>Triggers Connections</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getStartEventSymbol <em>Start Event Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Triggers Connections</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Triggers Connections</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getStartEventSymbol_TriggersConnections()
    * @see org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getStartEventSymbol
    * @model opposite="startEventSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<TriggersConnectionType> getTriggersConnections();

   /**
    * Returns the value of the '<em><b>Start Activity</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ActivityType#getStartingEventSymbols <em>Starting Event Symbols</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the activity started by this event.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Start Activity</em>' reference.
    * @see #setStartActivity(ActivityType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getStartEventSymbol_StartActivity()
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivityType#getStartingEventSymbols
    * @model opposite="startingEventSymbols" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='startActivity'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
    * @generated
    */
   ActivityType getStartActivity();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getStartActivity <em>Start Activity</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Start Activity</em>' reference.
    * @see #getStartActivity()
    * @generated
    */
   void setStartActivity(ActivityType value);

} // StartEventSymbol
