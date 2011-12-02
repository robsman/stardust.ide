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
 * A representation of the model object '<em><b>Performs Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getActivitySymbol <em>Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getParticipantSymbol <em>Participant Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getPerformsConnectionType()
 * @model extendedMetaData="name='performsConnection_._type' kind='empty'"
 * @generated
 */
public interface PerformsConnectionType extends IConnectionSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Activity Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getPerformsConnections <em>Performs Connections</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The model oid of the activity symbol.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Activity Symbol</em>' reference.
    * @see #setActivitySymbol(ActivitySymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getPerformsConnectionType_ActivitySymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getPerformsConnections
    * @model opposite="performsConnections" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='activitySymbol'"
    * @generated
    */
   ActivitySymbolType getActivitySymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getActivitySymbol <em>Activity Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Activity Symbol</em>' reference.
    * @see #getActivitySymbol()
    * @generated
    */
   void setActivitySymbol(ActivitySymbolType value);

   /**
    * Returns the value of the '<em><b>Participant Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol#getPerformedActivities <em>Performed Activities</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the participant symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Participant Symbol</em>' reference.
    * @see #setParticipantSymbol(IModelParticipantSymbol)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getPerformsConnectionType_ParticipantSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol#getPerformedActivities
    * @model opposite="performedActivities" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='participantSymbol'"
    * @generated
    */
   IModelParticipantSymbol getParticipantSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getParticipantSymbol <em>Participant Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Participant Symbol</em>' reference.
    * @see #getParticipantSymbol()
    * @generated
    */
   void setParticipantSymbol(IModelParticipantSymbol value);

} // PerformsConnectionType
