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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.FlowControlType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gateway Symbol</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GatewaySymbolImpl#getFlowKind <em>Flow Kind</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.GatewaySymbolImpl#getActivitySymbol <em>Activity Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GatewaySymbolImpl extends IFlowObjectSymbolImpl implements GatewaySymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The default value of the '{@link #getFlowKind() <em>Flow Kind</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFlowKind()
    * @generated
    * @ordered
    */
   protected static final FlowControlType FLOW_KIND_EDEFAULT = FlowControlType.NONE_LITERAL;

   /**
    * The cached value of the '{@link #getFlowKind() <em>Flow Kind</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getFlowKind()
    * @generated
    * @ordered
    */
   protected FlowControlType flowKind = FLOW_KIND_EDEFAULT;

   /**
    * This is true if the Flow Kind attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean flowKindESet;

   /**
    * The cached value of the '{@link #getActivitySymbol() <em>Activity Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getActivitySymbol()
    * @generated
    * @ordered
    */
   protected ActivitySymbolType activitySymbol;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected GatewaySymbolImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIFlowObjectSymbol_InTransitions(),
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
            CarnotWorkflowModelPackage.eINSTANCE.getIFlowObjectSymbol_OutTransitions()
      });
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.GATEWAY_SYMBOL;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FlowControlType getFlowKind()
   {
      return flowKind;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setFlowKind(FlowControlType newFlowKind)
   {
      FlowControlType oldFlowKind = flowKind;
      flowKind = newFlowKind == null ? FLOW_KIND_EDEFAULT : newFlowKind;
      boolean oldFlowKindESet = flowKindESet;
      flowKindESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GATEWAY_SYMBOL__FLOW_KIND, oldFlowKind, flowKind, !oldFlowKindESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetFlowKind()
   {
      FlowControlType oldFlowKind = flowKind;
      boolean oldFlowKindESet = flowKindESet;
      flowKind = FLOW_KIND_EDEFAULT;
      flowKindESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.GATEWAY_SYMBOL__FLOW_KIND, oldFlowKind, FLOW_KIND_EDEFAULT, oldFlowKindESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetFlowKind()
   {
      return flowKindESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ActivitySymbolType getActivitySymbol()
   {
      return activitySymbol;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetActivitySymbol(ActivitySymbolType newActivitySymbol, NotificationChain msgs)
   {
      ActivitySymbolType oldActivitySymbol = activitySymbol;
      activitySymbol = newActivitySymbol;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL, oldActivitySymbol, newActivitySymbol);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setActivitySymbol(ActivitySymbolType newActivitySymbol)
   {
      if (newActivitySymbol != activitySymbol)
      {
         NotificationChain msgs = null;
         if (activitySymbol != null)
            msgs = ((InternalEObject)activitySymbol).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS, ActivitySymbolType.class, msgs);
         if (newActivitySymbol != null)
            msgs = ((InternalEObject)newActivitySymbol).eInverseAdd(this, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS, ActivitySymbolType.class, msgs);
         msgs = basicSetActivitySymbol(newActivitySymbol, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL, newActivitySymbol, newActivitySymbol));
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
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
            if (activitySymbol != null)
               msgs = ((InternalEObject)activitySymbol).eInverseRemove(this, CarnotWorkflowModelPackage.ACTIVITY_SYMBOL_TYPE__GATEWAY_SYMBOLS, ActivitySymbolType.class, msgs);
            return basicSetActivitySymbol((ActivitySymbolType)otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
            return basicSetActivitySymbol(null, msgs);
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
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__FLOW_KIND:
            return getFlowKind();
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
            return getActivitySymbol();
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
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__FLOW_KIND:
            setFlowKind((FlowControlType)newValue);
            return;
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
            setActivitySymbol((ActivitySymbolType)newValue);
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
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__FLOW_KIND:
            unsetFlowKind();
            return;
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
            setActivitySymbol((ActivitySymbolType)null);
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
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__FLOW_KIND:
            return isSetFlowKind();
         case CarnotWorkflowModelPackage.GATEWAY_SYMBOL__ACTIVITY_SYMBOL:
            return activitySymbol != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (flowKind: ");
      if (flowKindESet) result.append(flowKind); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //GatewaySymbolImpl
