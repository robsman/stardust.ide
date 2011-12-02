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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Activity Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivitySymbolTypeImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivitySymbolTypeImpl#getPerformsConnections <em>Performs Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivitySymbolTypeImpl#getExecutedByConnections <em>Executed By Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivitySymbolTypeImpl#getDataMappings <em>Data Mappings</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ActivitySymbolTypeImpl#getGatewaySymbols <em>Gateway Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivitySymbolTypeImpl extends IFlowObjectSymbolImpl
      implements ActivitySymbolType
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getActivity() <em>Activity</em>}' reference. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #getActivity()
    * @generated
    * @ordered
    */
   protected ActivityType activity;

   /**
    * The cached value of the '{@link #getPerformsConnections() <em>Performs Connections</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getPerformsConnections()
    * @generated
    * @ordered
    */
   protected EList<PerformsConnectionType> performsConnections;

   /**
    * The cached value of the '{@link #getExecutedByConnections() <em>Executed By Connections</em>}' reference list.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getExecutedByConnections()
    * @generated
    * @ordered
    */
   protected EList<ExecutedByConnectionType> executedByConnections;

   /**
    * The cached value of the '{@link #getDataMappings() <em>Data Mappings</em>}' reference list.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getDataMappings()
    * @generated
    * @ordered
    */
   protected EList<DataMappingConnectionType> dataMappings;

   /**
    * The cached value of the '{@link #getGatewaySymbols() <em>Gateway Symbols</em>}' reference list.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #getGatewaySymbols()
    * @generated
    * @ordered
    */
   protected EList<GatewaySymbol> gatewaySymbols;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   protected ActivitySymbolTypeImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.ACTIVITY_SYMBOL_TYPE;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public ActivityType getActivity()
   {
      return activity;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetActivity(ActivityType newActivity, NotificationChain msgs)
   {
      ActivityType oldActivity = activity;
      activity = newActivity;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY, oldActivity, newActivity);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void setActivity(ActivityType newActivity)
   {
      if (newActivity != activity)
      {
         NotificationChain msgs = null;
         if (activity != null)
            msgs = ((InternalEObject)activity).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS, ActivityType.class, msgs);
         if (newActivity != null)
            msgs = ((InternalEObject)newActivity).eInverseAdd(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS, ActivityType.class, msgs);
         msgs = basicSetActivity(newActivity, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY, newActivity, newActivity));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PerformsConnectionType> getPerformsConnections()
   {
      if (performsConnections == null)
      {
         performsConnections = new EObjectWithInverseEList<PerformsConnectionType>(PerformsConnectionType.class, this, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS, CarnotWorkflowModelPackage.PERFORMS_CONNECTION_TYPE__ACTIVITY_SYMBOL);
      }
      return performsConnections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ExecutedByConnectionType> getExecutedByConnections()
   {
      if (executedByConnections == null)
      {
         executedByConnections = new EObjectWithInverseEList<ExecutedByConnectionType>(ExecutedByConnectionType.class, this, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS, CarnotWorkflowModelPackage.EXECUTED_BY_CONNECTION_TYPE__ACTIVITY_SYMBOL);
      }
      return executedByConnections;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EList<DataMappingConnectionType> getDataMappings()
   {
      if (dataMappings == null)
      {
         dataMappings = new EObjectWithInverseEList<DataMappingConnectionType>(DataMappingConnectionType.class, this, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS, CarnotWorkflowModelPackage.DATA_MAPPING_CONNECTION_TYPE__ACTIVITY_SYMBOL);
      }
      return dataMappings;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EList<GatewaySymbol> getGatewaySymbols()
   {
      if (gatewaySymbols == null)
      {
         gatewaySymbols = new EObjectWithInverseResolvingEList<GatewaySymbol>(GatewaySymbol.class, this, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS, CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL);
      }
      return gatewaySymbols;
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EReference[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIFlowObjectSymbol_InTransitions(),
            CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType_DataMappings(),
            CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType_ExecutedByConnections(),
            CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType_PerformsConnections(),
            CarnotWorkflowModelPackage.eINSTANCE.getIGraphicalObject_ReferingToConnections()
      });
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public List getOutConnectionFeatures()
   {
      return Arrays.asList(new EReference[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIFlowObjectSymbol_OutTransitions(),
            CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType_DataMappings()
      });
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated NOT
    */
   public IIdentifiableModelElement getModelElement()
   {
      return getActivity();
   }

   /**
    * @generated NOT
    */
   public void setModelElement(IIdentifiableModelElement element)
   {
      setActivity((ActivityType) element);
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
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY:
            if (activity != null)
               msgs = ((InternalEObject)activity).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_TYPE__ACTIVITY_SYMBOLS, ActivityType.class, msgs);
            return basicSetActivity((ActivityType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getPerformsConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutedByConnections()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getDataMappings()).basicAdd(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getGatewaySymbols()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY:
            return basicSetActivity(null, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS:
            return ((InternalEList<?>)getPerformsConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS:
            return ((InternalEList<?>)getExecutedByConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS:
            return ((InternalEList<?>)getDataMappings()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS:
            return ((InternalEList<?>)getGatewaySymbols()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY:
            return getActivity();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS:
            return getPerformsConnections();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS:
            return getExecutedByConnections();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS:
            return getDataMappings();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS:
            return getGatewaySymbols();
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
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY:
            setActivity((ActivityType)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS:
            getPerformsConnections().clear();
            getPerformsConnections().addAll((Collection<? extends PerformsConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS:
            getExecutedByConnections().clear();
            getExecutedByConnections().addAll((Collection<? extends ExecutedByConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS:
            getDataMappings().clear();
            getDataMappings().addAll((Collection<? extends DataMappingConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS:
            getGatewaySymbols().clear();
            getGatewaySymbols().addAll((Collection<? extends GatewaySymbol>)newValue);
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
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY:
            setActivity((ActivityType)null);
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS:
            getPerformsConnections().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS:
            getExecutedByConnections().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS:
            getDataMappings().clear();
            return;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS:
            getGatewaySymbols().clear();
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
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__ACTIVITY:
            return activity != null;
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__PERFORMS_CONNECTIONS:
            return performsConnections != null && !performsConnections.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__EXECUTED_BY_CONNECTIONS:
            return executedByConnections != null && !executedByConnections.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__DATA_MAPPINGS:
            return dataMappings != null && !dataMappings.isEmpty();
         case CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS:
            return gatewaySymbols != null && !gatewaySymbols.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // ActivitySymbolTypeImpl
