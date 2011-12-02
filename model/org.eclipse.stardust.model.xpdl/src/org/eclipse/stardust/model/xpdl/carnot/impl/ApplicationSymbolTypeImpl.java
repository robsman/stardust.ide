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
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationSymbolTypeImpl#getExecutingActivities <em>Executing Activities</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ApplicationSymbolTypeImpl#getApplication <em>Application</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ApplicationSymbolTypeImpl extends IModelElementNodeSymbolImpl implements ApplicationSymbolType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getExecutingActivities() <em>Executing Activities</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExecutingActivities()
    * @generated
    * @ordered
    */
   protected EList<ExecutedByConnectionType> executingActivities;

   /**
    * The cached value of the '{@link #getApplication() <em>Application</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplication()
    * @generated
    * @ordered
    */
   protected ApplicationType application;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ApplicationSymbolTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.APPLICATION_SYMBOL_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ExecutedByConnectionType> getExecutingActivities()
   {
      if (executingActivities == null)
      {
         executingActivities = new EObjectWithInverseEList<ExecutedByConnectionType>(ExecutedByConnectionType.class, this, CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES, CarnotWorkflowModelPackage.EXECUTED_BY_CONNECTION_TYPE__APPLICATION_SYMBOL);
      }
      return executingActivities;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ApplicationType getApplication()
   {
      return application;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetApplication(ApplicationType newApplication, NotificationChain msgs)
   {
      ApplicationType oldApplication = application;
      application = newApplication;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION, oldApplication, newApplication);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setApplication(ApplicationType newApplication)
   {
      if (newApplication != application)
      {
         NotificationChain msgs = null;
         if (application != null)
            msgs = ((InternalEObject)application).eInverseRemove(this, CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS, ApplicationType.class, msgs);
         if (newApplication != null)
            msgs = ((InternalEObject)newApplication).eInverseAdd(this, CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS, ApplicationType.class, msgs);
         msgs = basicSetApplication(newApplication, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION, newApplication, newApplication));
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
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutingActivities()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION:
            if (application != null)
               msgs = ((InternalEObject)application).eInverseRemove(this, CarnotWorkflowModelPackage.APPLICATION_TYPE__APPLICATION_SYMBOLS, ApplicationType.class, msgs);
            return basicSetApplication((ApplicationType)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES:
            return ((InternalEList<?>)getExecutingActivities()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION:
            return basicSetApplication(null, msgs);
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
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES:
            return getExecutingActivities();
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION:
            return getApplication();
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
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES:
            getExecutingActivities().clear();
            getExecutingActivities().addAll((Collection<? extends ExecutedByConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION:
            setApplication((ApplicationType)newValue);
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
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES:
            getExecutingActivities().clear();
            return;
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION:
            setApplication((ApplicationType)null);
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
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__EXECUTING_ACTIVITIES:
            return executingActivities != null && !executingActivities.isEmpty();
         case CarnotWorkflowModelPackage.APPLICATION_SYMBOL_TYPE__APPLICATION:
            return application != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * @generated NOT
    */
   public IIdentifiableModelElement getModelElement()
   {
      return getApplication();
   }

   /**
    * @generated NOT
    */
   public void setModelElement(IIdentifiableModelElement element)
   {
      setApplication((ApplicationType) element);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIGraphicalObject_ReferingToConnections()
      });
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getOutConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getApplicationSymbolType_ExecutingActivities()
      });
   }

} //ApplicationSymbolTypeImpl
