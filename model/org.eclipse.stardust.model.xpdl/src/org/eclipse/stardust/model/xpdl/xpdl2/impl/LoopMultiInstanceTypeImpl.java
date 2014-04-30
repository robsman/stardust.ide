/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType;
import org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType;
import org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loop Multi Instance Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl#getMICondition <em>MI Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl#getComplexMIFlowCondition <em>Complex MI Flow Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl#getLoopDataRef <em>Loop Data Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl#getMIFlowCondition <em>MI Flow Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl#getMIOrdering <em>MI Ordering</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoopMultiInstanceTypeImpl extends EObjectImpl implements LoopMultiInstanceType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The cached value of the '{@link #getMICondition() <em>MI Condition</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMICondition()
    * @generated
    * @ordered
    */
   protected ExpressionType mICondition;

   /**
    * The cached value of the '{@link #getComplexMIFlowCondition() <em>Complex MI Flow Condition</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getComplexMIFlowCondition()
    * @generated
    * @ordered
    */
   protected ExpressionType complexMIFlowCondition;

   /**
    * The cached value of the '{@link #getLoopDataRef() <em>Loop Data Ref</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopDataRef()
    * @generated
    * @ordered
    */
   protected LoopDataRefType loopDataRef;

   /**
    * The default value of the '{@link #getMIFlowCondition() <em>MI Flow Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMIFlowCondition()
    * @generated
    * @ordered
    */
   protected static final MIFlowConditionType MI_FLOW_CONDITION_EDEFAULT = MIFlowConditionType.ALL;

   /**
    * The cached value of the '{@link #getMIFlowCondition() <em>MI Flow Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMIFlowCondition()
    * @generated
    * @ordered
    */
   protected MIFlowConditionType mIFlowCondition = MI_FLOW_CONDITION_EDEFAULT;

   /**
    * This is true if the MI Flow Condition attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean mIFlowConditionESet;

   /**
    * The default value of the '{@link #getMIOrdering() <em>MI Ordering</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMIOrdering()
    * @generated
    * @ordered
    */
   protected static final MIOrderingType MI_ORDERING_EDEFAULT = MIOrderingType.SEQUENTIAL;

   /**
    * The cached value of the '{@link #getMIOrdering() <em>MI Ordering</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMIOrdering()
    * @generated
    * @ordered
    */
   protected MIOrderingType mIOrdering = MI_ORDERING_EDEFAULT;

   /**
    * This is true if the MI Ordering attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean mIOrderingESet;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected LoopMultiInstanceTypeImpl()
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
      return XpdlPackage.Literals.LOOP_MULTI_INSTANCE_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExpressionType getMICondition()
   {
      return mICondition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetMICondition(ExpressionType newMICondition, NotificationChain msgs)
   {
      ExpressionType oldMICondition = mICondition;
      mICondition = newMICondition;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION, oldMICondition, newMICondition);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMICondition(ExpressionType newMICondition)
   {
      if (newMICondition != mICondition)
      {
         NotificationChain msgs = null;
         if (mICondition != null)
            msgs = ((InternalEObject)mICondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION, null, msgs);
         if (newMICondition != null)
            msgs = ((InternalEObject)newMICondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION, null, msgs);
         msgs = basicSetMICondition(newMICondition, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION, newMICondition, newMICondition));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExpressionType getComplexMIFlowCondition()
   {
      return complexMIFlowCondition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetComplexMIFlowCondition(ExpressionType newComplexMIFlowCondition, NotificationChain msgs)
   {
      ExpressionType oldComplexMIFlowCondition = complexMIFlowCondition;
      complexMIFlowCondition = newComplexMIFlowCondition;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION, oldComplexMIFlowCondition, newComplexMIFlowCondition);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setComplexMIFlowCondition(ExpressionType newComplexMIFlowCondition)
   {
      if (newComplexMIFlowCondition != complexMIFlowCondition)
      {
         NotificationChain msgs = null;
         if (complexMIFlowCondition != null)
            msgs = ((InternalEObject)complexMIFlowCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION, null, msgs);
         if (newComplexMIFlowCondition != null)
            msgs = ((InternalEObject)newComplexMIFlowCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION, null, msgs);
         msgs = basicSetComplexMIFlowCondition(newComplexMIFlowCondition, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION, newComplexMIFlowCondition, newComplexMIFlowCondition));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopDataRefType getLoopDataRef()
   {
      return loopDataRef;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetLoopDataRef(LoopDataRefType newLoopDataRef, NotificationChain msgs)
   {
      LoopDataRefType oldLoopDataRef = loopDataRef;
      loopDataRef = newLoopDataRef;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF, oldLoopDataRef, newLoopDataRef);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopDataRef(LoopDataRefType newLoopDataRef)
   {
      if (newLoopDataRef != loopDataRef)
      {
         NotificationChain msgs = null;
         if (loopDataRef != null)
            msgs = ((InternalEObject)loopDataRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF, null, msgs);
         if (newLoopDataRef != null)
            msgs = ((InternalEObject)newLoopDataRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF, null, msgs);
         msgs = basicSetLoopDataRef(newLoopDataRef, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF, newLoopDataRef, newLoopDataRef));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MIFlowConditionType getMIFlowCondition()
   {
      return mIFlowCondition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMIFlowCondition(MIFlowConditionType newMIFlowCondition)
   {
      MIFlowConditionType oldMIFlowCondition = mIFlowCondition;
      mIFlowCondition = newMIFlowCondition == null ? MI_FLOW_CONDITION_EDEFAULT : newMIFlowCondition;
      boolean oldMIFlowConditionESet = mIFlowConditionESet;
      mIFlowConditionESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION, oldMIFlowCondition, mIFlowCondition, !oldMIFlowConditionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetMIFlowCondition()
   {
      MIFlowConditionType oldMIFlowCondition = mIFlowCondition;
      boolean oldMIFlowConditionESet = mIFlowConditionESet;
      mIFlowCondition = MI_FLOW_CONDITION_EDEFAULT;
      mIFlowConditionESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION, oldMIFlowCondition, MI_FLOW_CONDITION_EDEFAULT, oldMIFlowConditionESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetMIFlowCondition()
   {
      return mIFlowConditionESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MIOrderingType getMIOrdering()
   {
      return mIOrdering;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setMIOrdering(MIOrderingType newMIOrdering)
   {
      MIOrderingType oldMIOrdering = mIOrdering;
      mIOrdering = newMIOrdering == null ? MI_ORDERING_EDEFAULT : newMIOrdering;
      boolean oldMIOrderingESet = mIOrderingESet;
      mIOrderingESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING, oldMIOrdering, mIOrdering, !oldMIOrderingESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetMIOrdering()
   {
      MIOrderingType oldMIOrdering = mIOrdering;
      boolean oldMIOrderingESet = mIOrderingESet;
      mIOrdering = MI_ORDERING_EDEFAULT;
      mIOrderingESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING, oldMIOrdering, MI_ORDERING_EDEFAULT, oldMIOrderingESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetMIOrdering()
   {
      return mIOrderingESet;
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
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION:
            return basicSetMICondition(null, msgs);
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION:
            return basicSetComplexMIFlowCondition(null, msgs);
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF:
            return basicSetLoopDataRef(null, msgs);
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
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION:
            return getMICondition();
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION:
            return getComplexMIFlowCondition();
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF:
            return getLoopDataRef();
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION:
            return getMIFlowCondition();
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING:
            return getMIOrdering();
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
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION:
            setMICondition((ExpressionType)newValue);
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION:
            setComplexMIFlowCondition((ExpressionType)newValue);
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF:
            setLoopDataRef((LoopDataRefType)newValue);
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION:
            setMIFlowCondition((MIFlowConditionType)newValue);
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING:
            setMIOrdering((MIOrderingType)newValue);
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
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION:
            setMICondition((ExpressionType)null);
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION:
            setComplexMIFlowCondition((ExpressionType)null);
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF:
            setLoopDataRef((LoopDataRefType)null);
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION:
            unsetMIFlowCondition();
            return;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING:
            unsetMIOrdering();
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
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION:
            return mICondition != null;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION:
            return complexMIFlowCondition != null;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF:
            return loopDataRef != null;
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION:
            return isSetMIFlowCondition();
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING:
            return isSetMIOrdering();
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
      result.append(" (mIFlowCondition: ");
      if (mIFlowConditionESet) result.append(mIFlowCondition); else result.append("<unset>");
      result.append(", mIOrdering: ");
      if (mIOrderingESet) result.append(mIOrdering); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //LoopMultiInstanceTypeImpl
