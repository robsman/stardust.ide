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
 * A representation of the model object '<em><b>IModel Participant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipant#getPerformedActivities <em>Performed Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipant#getPerformedSwimlanes <em>Performed Swimlanes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipant#getParticipantAssociations <em>Participant Associations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelParticipant()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='modelParticipant_._type' kind='empty'"
 * @generated
 */
public interface IModelParticipant extends IIdentifiableModelElement{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Performed Activities</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ActivityType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ActivityType#getPerformer <em>Performer</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Performed Activities</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Performed Activities</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelParticipant_PerformedActivities()
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivityType#getPerformer
    * @model opposite="performer" transient="true"
    * @generated
    */
   EList<ActivityType> getPerformedActivities();

   /**
    * Returns the value of the '<em><b>Performed Swimlanes</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getParticipant <em>Participant</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Performed Swimlanes</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Performed Swimlanes</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelParticipant_PerformedSwimlanes()
    * @see org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getParticipant
    * @model opposite="participant" transient="true"
    * @generated
    */
   EList<ISwimlaneSymbol> getPerformedSwimlanes();

   /**
    * Returns the value of the '<em><b>Participant Associations</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ParticipantType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ParticipantType#getParticipant <em>Participant</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Participant Associations</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Participant Associations</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIModelParticipant_ParticipantAssociations()
    * @see org.eclipse.stardust.model.xpdl.carnot.ParticipantType#getParticipant
    * @model opposite="participant" transient="true" changeable="false"
    * @generated
    */
   EList<ParticipantType> getParticipantAssociations();

} // IModelParticipant
