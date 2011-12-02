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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Event Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.StartEventSymbolImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.StartEventSymbolImpl#getTriggersConnections <em>Triggers Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.StartEventSymbolImpl#getStartActivity <em>Start Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartEventSymbolImpl extends AbstractEventSymbolImpl implements StartEventSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTrigger()
    * @generated
    * @ordered
    */
   protected TriggerType trigger;

   /**
    * The cached value of the '{@link #getTriggersConnections() <em>Triggers Connections</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTriggersConnections()
    * @generated
    * @ordered
    */
   protected EList<TriggersConnectionType> triggersConnections;

   /**
    * The cached value of the '{@link #getStartActivity() <em>Start Activity</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStartActivity()
    * @generated
    * @ordered
    */
   protected ActivityType startActivity;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected StartEventSymbolImpl()
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
      return CarnotWorkflowModelPackage.Literals.START_EVENT_SYMBOL;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TriggerType getTrigger()
   {
      return trigger;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetTrigger(TriggerType newTrigger, NotificationChain msgs)
   {
      TriggerType oldTrigger = trigger;
      trigger = newTrigger;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER, oldTrigger, newTrigger);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTrigger(TriggerType newTrigger)
   {
      if (newTrigger != trigger)
      {
         NotificationChain msgs = null;
         if (trigger != null)
            msgs = ((InternalEObject)trigger).eInverseRemove(this, CarnotWorkflowModelPackage.TRIGGER_TYPE__STARTING_EVENT_SYMBOLS, TriggerType.class, msgs);
         if (newTrigger != null)
            msgs = ((InternalEObject)newTrigger).eInverseAdd(this, CarnotWorkflowModelPackage.TRIGGER_TYPE__STARTING_EVENT_SYMBOLS, TriggerType.class, msgs);
         msgs = basicSetTrigger(newTrigger, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER, newTrigger, newTrigger));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TriggersConnectionType> getTriggersConnections()
   {
      if (triggersConnections == null)
      {
         triggersConnections = new EObjectWithInverseEList<TriggersConnectionType>(TriggersConnectionType.class, this, CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS, CarnotWorkflowModelPackage.TRIGGERS_CONNECTION_TYPE__START_EVENT_SYMBOL);
      }
      return triggersConnections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ActivityType getStartActivity()
   {
      return startActivity;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetStartActivity(ActivityType newStartActivity, NotificationChain msgs)
   {
      ActivityType oldStartActivity = startActivity;
      startActivity = newStartActivity;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY, oldStartActivity, newStartActivity);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setStartActivity(ActivityType newStartActivity)
   {
      if (newStartActivity != startActivity)
      {
         NotificationChain msgs = null;
         if (startActivity != null)
            msgs = ((InternalEObject)startActivity).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS, ActivityType.class, msgs);
         if (newStartActivity != null)
            msgs = ((InternalEObject)newStartActivity).eInverseAdd(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS, ActivityType.class, msgs);
         msgs = basicSetStartActivity(newStartActivity, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY, newStartActivity, newStartActivity));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER:
            if (trigger != null)
               msgs = ((InternalEObject)trigger).eInverseRemove(this, CarnotWorkflowModelPackage.TRIGGER_TYPE__STARTING_EVENT_SYMBOLS, TriggerType.class, msgs);
            return basicSetTrigger((TriggerType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getTriggersConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY:
            if (startActivity != null)
               msgs = ((InternalEObject)startActivity).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__STARTING_EVENT_SYMBOLS, ActivityType.class, msgs);
            return basicSetStartActivity((ActivityType)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER:
            return basicSetTrigger(null, msgs);
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS:
            return ((InternalEList<?>)getTriggersConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY:
            return basicSetStartActivity(null, msgs);
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
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER:
            return getTrigger();
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS:
            return getTriggersConnections();
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY:
            return getStartActivity();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER:
            setTrigger((TriggerType)newValue);
            return;
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS:
            getTriggersConnections().clear();
            getTriggersConnections().addAll((Collection<? extends TriggersConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY:
            setStartActivity((ActivityType)newValue);
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
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER:
            setTrigger((TriggerType)null);
            return;
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS:
            getTriggersConnections().clear();
            return;
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY:
            setStartActivity((ActivityType)null);
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
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGER:
            return trigger != null;
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__TRIGGERS_CONNECTIONS:
            return triggersConnections != null && !triggersConnections.isEmpty();
         case CarnotWorkflowModelPackage.START_EVENT_SYMBOL__START_ACTIVITY:
            return startActivity != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * @generated NOT
    */
   public IIdentifiableModelElement getModelElement()
   {
      return getTrigger();
   }

   /**
    * @generated NOT
    */
   public void setModelElement(IIdentifiableModelElement element)
   {
      setTrigger((TriggerType) element);
   }

   /**
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIGraphicalObject_ReferingToConnections(),
            CarnotWorkflowModelPackage.eINSTANCE.getStartEventSymbol_TriggersConnections()
      });
   }

} //StartEventSymbolImpl
