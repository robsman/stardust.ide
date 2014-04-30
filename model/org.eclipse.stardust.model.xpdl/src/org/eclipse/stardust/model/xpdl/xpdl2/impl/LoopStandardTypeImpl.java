/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType;
import org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType;
import org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loop Standard Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopStandardTypeImpl#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopStandardTypeImpl#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopStandardTypeImpl#getTestTime <em>Test Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoopStandardTypeImpl extends EObjectImpl implements LoopStandardType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The cached value of the '{@link #getLoopCondition() <em>Loop Condition</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopCondition()
    * @generated
    * @ordered
    */
   protected ExpressionType loopCondition;

   /**
    * The default value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopMaximum()
    * @generated
    * @ordered
    */
   protected static final BigInteger LOOP_MAXIMUM_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getLoopMaximum() <em>Loop Maximum</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopMaximum()
    * @generated
    * @ordered
    */
   protected BigInteger loopMaximum = LOOP_MAXIMUM_EDEFAULT;

   /**
    * The default value of the '{@link #getTestTime() <em>Test Time</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTestTime()
    * @generated
    * @ordered
    */
   protected static final TestTimeType TEST_TIME_EDEFAULT = TestTimeType.BEFORE;

   /**
    * The cached value of the '{@link #getTestTime() <em>Test Time</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getTestTime()
    * @generated
    * @ordered
    */
   protected TestTimeType testTime = TEST_TIME_EDEFAULT;

   /**
    * This is true if the Test Time attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean testTimeESet;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected LoopStandardTypeImpl()
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
      return XpdlPackage.Literals.LOOP_STANDARD_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExpressionType getLoopCondition()
   {
      return loopCondition;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetLoopCondition(ExpressionType newLoopCondition, NotificationChain msgs)
   {
      ExpressionType oldLoopCondition = loopCondition;
      loopCondition = newLoopCondition;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION, oldLoopCondition, newLoopCondition);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopCondition(ExpressionType newLoopCondition)
   {
      if (newLoopCondition != loopCondition)
      {
         NotificationChain msgs = null;
         if (loopCondition != null)
            msgs = ((InternalEObject)loopCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION, null, msgs);
         if (newLoopCondition != null)
            msgs = ((InternalEObject)newLoopCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION, null, msgs);
         msgs = basicSetLoopCondition(newLoopCondition, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION, newLoopCondition, newLoopCondition));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BigInteger getLoopMaximum()
   {
      return loopMaximum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopMaximum(BigInteger newLoopMaximum)
   {
      BigInteger oldLoopMaximum = loopMaximum;
      loopMaximum = newLoopMaximum;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_STANDARD_TYPE__LOOP_MAXIMUM, oldLoopMaximum, loopMaximum));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TestTimeType getTestTime()
   {
      return testTime;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setTestTime(TestTimeType newTestTime)
   {
      TestTimeType oldTestTime = testTime;
      testTime = newTestTime == null ? TEST_TIME_EDEFAULT : newTestTime;
      boolean oldTestTimeESet = testTimeESet;
      testTimeESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.LOOP_STANDARD_TYPE__TEST_TIME, oldTestTime, testTime, !oldTestTimeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetTestTime()
   {
      TestTimeType oldTestTime = testTime;
      boolean oldTestTimeESet = testTimeESet;
      testTime = TEST_TIME_EDEFAULT;
      testTimeESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, XpdlPackage.LOOP_STANDARD_TYPE__TEST_TIME, oldTestTime, TEST_TIME_EDEFAULT, oldTestTimeESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetTestTime()
   {
      return testTimeESet;
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
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION:
            return basicSetLoopCondition(null, msgs);
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
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION:
            return getLoopCondition();
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_MAXIMUM:
            return getLoopMaximum();
         case XpdlPackage.LOOP_STANDARD_TYPE__TEST_TIME:
            return getTestTime();
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
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION:
            setLoopCondition((ExpressionType)newValue);
            return;
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_MAXIMUM:
            setLoopMaximum((BigInteger)newValue);
            return;
         case XpdlPackage.LOOP_STANDARD_TYPE__TEST_TIME:
            setTestTime((TestTimeType)newValue);
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
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION:
            setLoopCondition((ExpressionType)null);
            return;
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_MAXIMUM:
            setLoopMaximum(LOOP_MAXIMUM_EDEFAULT);
            return;
         case XpdlPackage.LOOP_STANDARD_TYPE__TEST_TIME:
            unsetTestTime();
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
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_CONDITION:
            return loopCondition != null;
         case XpdlPackage.LOOP_STANDARD_TYPE__LOOP_MAXIMUM:
            return LOOP_MAXIMUM_EDEFAULT == null ? loopMaximum != null : !LOOP_MAXIMUM_EDEFAULT.equals(loopMaximum);
         case XpdlPackage.LOOP_STANDARD_TYPE__TEST_TIME:
            return isSetTestTime();
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
      result.append(" (loopMaximum: ");
      result.append(loopMaximum);
      result.append(", testTime: ");
      if (testTimeESet) result.append(testTime); else result.append("<unset>");
      result.append(')');
      return result.toString();
   }

} //LoopStandardTypeImpl
