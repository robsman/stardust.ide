/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loop Data Ref Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.LoopDataRefTypeImpl#getInputItemRef <em>Input Item Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.LoopDataRefTypeImpl#getOutputItemRef <em>Output Item Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.LoopDataRefTypeImpl#getLoopCounterRef <em>Loop Counter Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoopDataRefTypeImpl extends EObjectImpl implements LoopDataRefType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The default value of the '{@link #getInputItemRef() <em>Input Item Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInputItemRef()
    * @generated
    * @ordered
    */
   protected static final String INPUT_ITEM_REF_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getInputItemRef() <em>Input Item Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInputItemRef()
    * @generated
    * @ordered
    */
   protected String inputItemRef = INPUT_ITEM_REF_EDEFAULT;

   /**
    * The default value of the '{@link #getOutputItemRef() <em>Output Item Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOutputItemRef()
    * @generated
    * @ordered
    */
   protected static final String OUTPUT_ITEM_REF_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getOutputItemRef() <em>Output Item Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getOutputItemRef()
    * @generated
    * @ordered
    */
   protected String outputItemRef = OUTPUT_ITEM_REF_EDEFAULT;

   /**
    * The default value of the '{@link #getLoopCounterRef() <em>Loop Counter Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopCounterRef()
    * @generated
    * @ordered
    */
   protected static final String LOOP_COUNTER_REF_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getLoopCounterRef() <em>Loop Counter Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getLoopCounterRef()
    * @generated
    * @ordered
    */
   protected String loopCounterRef = LOOP_COUNTER_REF_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected LoopDataRefTypeImpl()
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
      return ExtensionPackage.Literals.LOOP_DATA_REF_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getInputItemRef()
   {
      return inputItemRef;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setInputItemRef(String newInputItemRef)
   {
      String oldInputItemRef = inputItemRef;
      inputItemRef = newInputItemRef;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ExtensionPackage.LOOP_DATA_REF_TYPE__INPUT_ITEM_REF, oldInputItemRef, inputItemRef));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getOutputItemRef()
   {
      return outputItemRef;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setOutputItemRef(String newOutputItemRef)
   {
      String oldOutputItemRef = outputItemRef;
      outputItemRef = newOutputItemRef;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ExtensionPackage.LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF, oldOutputItemRef, outputItemRef));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getLoopCounterRef()
   {
      return loopCounterRef;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setLoopCounterRef(String newLoopCounterRef)
   {
      String oldLoopCounterRef = loopCounterRef;
      loopCounterRef = newLoopCounterRef;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ExtensionPackage.LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF, oldLoopCounterRef, loopCounterRef));
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
         case ExtensionPackage.LOOP_DATA_REF_TYPE__INPUT_ITEM_REF:
            return getInputItemRef();
         case ExtensionPackage.LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF:
            return getOutputItemRef();
         case ExtensionPackage.LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF:
            return getLoopCounterRef();
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
         case ExtensionPackage.LOOP_DATA_REF_TYPE__INPUT_ITEM_REF:
            setInputItemRef((String)newValue);
            return;
         case ExtensionPackage.LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF:
            setOutputItemRef((String)newValue);
            return;
         case ExtensionPackage.LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF:
            setLoopCounterRef((String)newValue);
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
         case ExtensionPackage.LOOP_DATA_REF_TYPE__INPUT_ITEM_REF:
            setInputItemRef(INPUT_ITEM_REF_EDEFAULT);
            return;
         case ExtensionPackage.LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF:
            setOutputItemRef(OUTPUT_ITEM_REF_EDEFAULT);
            return;
         case ExtensionPackage.LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF:
            setLoopCounterRef(LOOP_COUNTER_REF_EDEFAULT);
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
         case ExtensionPackage.LOOP_DATA_REF_TYPE__INPUT_ITEM_REF:
            return INPUT_ITEM_REF_EDEFAULT == null ? inputItemRef != null : !INPUT_ITEM_REF_EDEFAULT.equals(inputItemRef);
         case ExtensionPackage.LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF:
            return OUTPUT_ITEM_REF_EDEFAULT == null ? outputItemRef != null : !OUTPUT_ITEM_REF_EDEFAULT.equals(outputItemRef);
         case ExtensionPackage.LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF:
            return LOOP_COUNTER_REF_EDEFAULT == null ? loopCounterRef != null : !LOOP_COUNTER_REF_EDEFAULT.equals(loopCounterRef);
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
      result.append(" (inputItemRef: ");
      result.append(inputItemRef);
      result.append(", outputItemRef: ");
      result.append(outputItemRef);
      result.append(", loopCounterRef: ");
      result.append(loopCounterRef);
      result.append(')');
      return result.toString();
   }

} //LoopDataRefTypeImpl
