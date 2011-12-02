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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Participant Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ParticipantType#getParticipant <em>Participant</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getParticipantType()
 * @model extendedMetaData="name='participant_._type' kind='empty'"
 * @generated
 */
public interface ParticipantType extends EObject{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Participant</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipant#getParticipantAssociations <em>Participant Associations</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model id of either an organization or role.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Participant</em>' reference.
    * @see #setParticipant(IModelParticipant)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getParticipantType_Participant()
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelParticipant#getParticipantAssociations
    * @model opposite="participantAssociations" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='participant'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   IModelParticipant getParticipant();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ParticipantType#getParticipant <em>Participant</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Participant</em>' reference.
    * @see #getParticipant()
    * @generated
    */
   void setParticipant(IModelParticipant value);

} // ParticipantType
