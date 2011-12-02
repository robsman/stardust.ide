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
package org.eclipse.stardust.model.xpdl.carnot.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.ParticipantType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Participant Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ParticipantTypeImpl#getParticipant <em>Participant</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParticipantTypeImpl extends EObjectImpl implements ParticipantType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getParticipant() <em>Participant</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParticipant()
    * @generated
    * @ordered
    */
   protected IModelParticipant participant;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ParticipantTypeImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.PARTICIPANT_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public IModelParticipant getParticipant()
   {
      return participant;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetParticipant(IModelParticipant newParticipant, NotificationChain msgs)
   {
      IModelParticipant oldParticipant = participant;
      participant = newParticipant;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT, oldParticipant, newParticipant);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParticipant(IModelParticipant newParticipant)
   {
      if (newParticipant != participant)
      {
         NotificationChain msgs = null;
         if (participant != null)
            msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PARTICIPANT_ASSOCIATIONS, IModelParticipant.class, msgs);
         if (newParticipant != null)
            msgs = ((InternalEObject)newParticipant).eInverseAdd(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PARTICIPANT_ASSOCIATIONS, IModelParticipant.class, msgs);
         msgs = basicSetParticipant(newParticipant, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT, newParticipant, newParticipant));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT:
            if (participant != null)
               msgs = ((InternalEObject)participant).eInverseRemove(this, CarnotWorkflowModelPackage.IMODEL_PARTICIPANT__PARTICIPANT_ASSOCIATIONS, IModelParticipant.class, msgs);
            return basicSetParticipant((IModelParticipant)otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT:
            return basicSetParticipant(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT:
            return getParticipant();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT:
            setParticipant((IModelParticipant)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT:
            setParticipant((IModelParticipant)null);
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.PARTICIPANT_TYPE__PARTICIPANT:
            return participant != null;
      }
      return super.eIsSet(featureID);
   }

} //ParticipantTypeImpl
