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

import org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loop Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopTypeImpl#getLoopStandard <em>Loop Standard</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopTypeImpl#getLoopMultiInstance <em>Loop Multi Instance</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopTypeImpl#getLoopType <em>Loop Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoopTypeImpl extends EObjectImpl implements LoopType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The cached value of the '{@link #getLoopStandard() <em>Loop Standard</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopStandard()
    * @generated
    * @ordered
    */
   protected LoopStandardType loopStandard;

   /**
    * The cached value of the '{@link #getLoopMultiInstance() <em>Loop Multi Instance</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopMultiInstance()
    * @generated
    * @ordered
    */
   protected LoopMultiInstanceType loopMultiInstance;

   /**
    * The default value of the '{@link #getLoopType() <em>Loop Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopType()
    * @generated
    * @ordered
    */
   protected static final LoopTypeType LOOP_TYPE_EDEFAULT = LoopTypeType.STANDARD;

   /**
    * The cached value of the '{@link #getLoopType() <em>Loop Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopType()
    * @generated
    * @ordered
    */
   protected LoopTypeType loopType = LOOP_TYPE_EDEFAULT;

   /**
    * This is true if the Loop Type attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean loopTypeESet;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected LoopTypeImpl()
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
      return XpdlPackage.Literals.LOOP_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopStandardType getLoopStandard()
   {
      return loopStandard;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetLoopStandard(LoopStandardType newLoopStandard, NotificationChain msgs)
   {
      LoopStandardType oldLoopStandard = loopStandard;
      loopStandard = newLoopStandard;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_TYPE__LOOP_STANDARD, oldLoopStandard, newLoopStandard);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopStandard(LoopStandardType newLoopStandard)
   {
      if (newLoopStandard != loopStandard)
      {
         NotificationChain msgs = null;
         if (loopStandard != null)
            msgs = ((InternalEObject)loopStandard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_TYPE__LOOP_STANDARD, null, msgs);
         if (newLoopStandard != null)
            msgs = ((InternalEObject)newLoopStandard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_TYPE__LOOP_STANDARD, null, msgs);
         msgs = basicSetLoopStandard(newLoopStandard, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_TYPE__LOOP_STANDARD, newLoopStandard, newLoopStandard));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopMultiInstanceType getLoopMultiInstance()
   {
      return loopMultiInstance;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetLoopMultiInstance(LoopMultiInstanceType newLoopMultiInstance, NotificationChain msgs)
   {
      LoopMultiInstanceType oldLoopMultiInstance = loopMultiInstance;
      loopMultiInstance = newLoopMultiInstance;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE, oldLoopMultiInstance, newLoopMultiInstance);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopMultiInstance(LoopMultiInstanceType newLoopMultiInstance)
   {
      if (newLoopMultiInstance != loopMultiInstance)
      {
         NotificationChain msgs = null;
         if (loopMultiInstance != null)
            msgs = ((InternalEObject)loopMultiInstance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE, null, msgs);
         if (newLoopMultiInstance != null)
            msgs = ((InternalEObject)newLoopMultiInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE, null, msgs);
         msgs = basicSetLoopMultiInstance(newLoopMultiInstance, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE, newLoopMultiInstance, newLoopMultiInstance));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopTypeType getLoopType()
   {
      return loopType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopType(LoopTypeType newLoopType)
   {
      LoopTypeType oldLoopType = loopType;
      loopType = newLoopType == null ? LOOP_TYPE_EDEFAULT : newLoopType;
      boolean oldLoopTypeESet = loopTypeESet;
      loopTypeESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_TYPE__LOOP_TYPE, oldLoopType, loopType, !oldLoopTypeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetLoopType()
   {
      LoopTypeType oldLoopType = loopType;
      boolean oldLoopTypeESet = loopTypeESet;
      loopType = LOOP_TYPE_EDEFAULT;
      loopTypeESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, XpdlPackage.LOOP_TYPE__LOOP_TYPE, oldLoopType, LOOP_TYPE_EDEFAULT, oldLoopTypeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetLoopType()
   {
      return loopTypeESet;
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
         case XpdlPackage.LOOP_TYPE__LOOP_STANDARD:
            return basicSetLoopStandard(null, msgs);
         case XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE:
            return basicSetLoopMultiInstance(null, msgs);
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
         case XpdlPackage.LOOP_TYPE__LOOP_STANDARD:
            return getLoopStandard();
         case XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE:
            return getLoopMultiInstance();
         case XpdlPackage.LOOP_TYPE__LOOP_TYPE:
            return getLoopType();
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
         case XpdlPackage.LOOP_TYPE__LOOP_STANDARD:
            setLoopStandard((LoopStandardType)newValue);
            return;
         case XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE:
            setLoopMultiInstance((LoopMultiInstanceType)newValue);
            return;
         case XpdlPackage.LOOP_TYPE__LOOP_TYPE:
            setLoopType((LoopTypeType)newValue);
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
         case XpdlPackage.LOOP_TYPE__LOOP_STANDARD:
            setLoopStandard((LoopStandardType)null);
            return;
         case XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE:
            setLoopMultiInstance((LoopMultiInstanceType)null);
            return;
         case XpdlPackage.LOOP_TYPE__LOOP_TYPE:
            unsetLoopType();
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
         case XpdlPackage.LOOP_TYPE__LOOP_STANDARD:
            return loopStandard != null;
         case XpdlPackage.LOOP_TYPE__LOOP_MULTI_INSTANCE:
            return loopMultiInstance != null;
         case XpdlPackage.LOOP_TYPE__LOOP_TYPE:
            return isSetLoopType();
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
      result.append(" (loopType: ");
      if (loopTypeESet) result.append(loopType); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //LoopTypeImpl
