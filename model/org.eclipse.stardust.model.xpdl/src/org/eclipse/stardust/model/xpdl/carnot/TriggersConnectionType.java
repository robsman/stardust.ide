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
 * A representation of the model object '<em><b>Triggers Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getStartEventSymbol <em>Start Event Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getParticipantSymbol <em>Participant Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTriggersConnectionType()
 * @model extendedMetaData="name='triggersConnection_._type' kind='empty'"
 * @generated
 */
public interface TriggersConnectionType extends IConnectionSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Start Event Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getTriggersConnections <em>Triggers Connections</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The model oid of the start symbol.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Start Event Symbol</em>' reference.
    * @see #setStartEventSymbol(StartEventSymbol)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTriggersConnectionType_StartEventSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol#getTriggersConnections
    * @model opposite="triggersConnections" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='startEventSymbol'"
    * @generated
    */
   StartEventSymbol getStartEventSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getStartEventSymbol <em>Start Event Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Start Event Symbol</em>' reference.
    * @see #getStartEventSymbol()
    * @generated
    */
   void setStartEventSymbol(StartEventSymbol value);

   /**
    * Returns the value of the '<em><b>Participant Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol#getTriggeredEvents <em>Triggered Events</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the participant symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Participant Symbol</em>' reference.
    * @see #setParticipantSymbol(IModelParticipantSymbol)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTriggersConnectionType_ParticipantSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol#getTriggeredEvents
    * @model opposite="triggeredEvents" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='participantSymbol'"
    * @generated
    */
   IModelParticipantSymbol getParticipantSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getParticipantSymbol <em>Participant Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Participant Symbol</em>' reference.
    * @see #getParticipantSymbol()
    * @generated
    */
   void setParticipantSymbol(IModelParticipantSymbol value);

} // TriggersConnectionType