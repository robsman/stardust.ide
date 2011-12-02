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
 * A representation of the model object '<em><b>IModel Participant Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol#getTriggeredEvents <em>Triggered Events</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelParticipantSymbol()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='modelParticipantSymbol_._type' kind='empty'"
 * @generated
 */
public interface IModelParticipantSymbol extends IModelElementNodeSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Performed Activities</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Performed Activities</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Performed Activities</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelParticipantSymbol_PerformedActivities()
    * @see org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getParticipantSymbol
    * @model opposite="participantSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<PerformsConnectionType> getPerformedActivities();

   /**
    * Returns the value of the '<em><b>Triggered Events</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getParticipantSymbol <em>Participant Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Triggered Events</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Triggered Events</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelParticipantSymbol_TriggeredEvents()
    * @see org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType#getParticipantSymbol
    * @model opposite="participantSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<TriggersConnectionType> getTriggeredEvents();

} // IModelParticipantSymbol
