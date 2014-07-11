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

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl#getScriptGrammar <em>Script Grammar</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl#getScriptType <em>Script Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl#getScriptVersion <em>Script Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpressionTypeImpl extends EObjectImpl implements ExpressionType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMixed()
    * @generated
    * @ordered
    */
   protected FeatureMap mixed;

   /**
    * The default value of the '{@link #getScriptGrammar() <em>Script Grammar</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getScriptGrammar()
    * @generated
    * @ordered
    */
   protected static final String SCRIPT_GRAMMAR_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getScriptGrammar() <em>Script Grammar</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getScriptGrammar()
    * @generated
    * @ordered
    */
   protected String scriptGrammar = SCRIPT_GRAMMAR_EDEFAULT;

   /**
    * The default value of the '{@link #getScriptType() <em>Script Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getScriptType()
    * @generated
    * @ordered
    */
   protected static final String SCRIPT_TYPE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getScriptType() <em>Script Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getScriptType()
    * @generated
    * @ordered
    */
   protected String scriptType = SCRIPT_TYPE_EDEFAULT;

   /**
    * The default value of the '{@link #getScriptVersion() <em>Script Version</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getScriptVersion()
    * @generated
    * @ordered
    */
   protected static final String SCRIPT_VERSION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getScriptVersion() <em>Script Version</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getScriptVersion()
    * @generated
    * @ordered
    */
   protected String scriptVersion = SCRIPT_VERSION_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ExpressionTypeImpl()
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
      return XpdlPackage.Literals.EXPRESSION_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getMixed()
   {
      if (mixed == null)
      {
         mixed = new BasicFeatureMap(this, XpdlPackage.EXPRESSION_TYPE__MIXED);
      }
      return mixed;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getGroup()
   {
      return (FeatureMap)getMixed().<FeatureMap.Entry>list(XpdlPackage.Literals.EXPRESSION_TYPE__GROUP);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getAny()
   {
      return (FeatureMap)getGroup().<FeatureMap.Entry>list(XpdlPackage.Literals.EXPRESSION_TYPE__ANY);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getScriptGrammar()
   {
      return scriptGrammar;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setScriptGrammar(String newScriptGrammar)
   {
      String oldScriptGrammar = scriptGrammar;
      scriptGrammar = newScriptGrammar;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXPRESSION_TYPE__SCRIPT_GRAMMAR, oldScriptGrammar, scriptGrammar));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getScriptType()
   {
      return scriptType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setScriptType(String newScriptType)
   {
      String oldScriptType = scriptType;
      scriptType = newScriptType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXPRESSION_TYPE__SCRIPT_TYPE, oldScriptType, scriptType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getScriptVersion()
   {
      return scriptVersion;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setScriptVersion(String newScriptVersion)
   {
      String oldScriptVersion = scriptVersion;
      scriptVersion = newScriptVersion;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXPRESSION_TYPE__SCRIPT_VERSION, oldScriptVersion, scriptVersion));
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
         case XpdlPackage.EXPRESSION_TYPE__MIXED:
            return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
         case XpdlPackage.EXPRESSION_TYPE__GROUP:
            return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
         case XpdlPackage.EXPRESSION_TYPE__ANY:
            return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
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
         case XpdlPackage.EXPRESSION_TYPE__MIXED:
            if (coreType) return getMixed();
            return ((FeatureMap.Internal)getMixed()).getWrapper();
         case XpdlPackage.EXPRESSION_TYPE__GROUP:
            if (coreType) return getGroup();
            return ((FeatureMap.Internal)getGroup()).getWrapper();
         case XpdlPackage.EXPRESSION_TYPE__ANY:
            if (coreType) return getAny();
            return ((FeatureMap.Internal)getAny()).getWrapper();
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_GRAMMAR:
            return getScriptGrammar();
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_TYPE:
            return getScriptType();
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_VERSION:
            return getScriptVersion();
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
         case XpdlPackage.EXPRESSION_TYPE__MIXED:
            ((FeatureMap.Internal)getMixed()).set(newValue);
            return;
         case XpdlPackage.EXPRESSION_TYPE__GROUP:
            ((FeatureMap.Internal)getGroup()).set(newValue);
            return;
         case XpdlPackage.EXPRESSION_TYPE__ANY:
            ((FeatureMap.Internal)getAny()).set(newValue);
            return;
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_GRAMMAR:
            setScriptGrammar((String)newValue);
            return;
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_TYPE:
            setScriptType((String)newValue);
            return;
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_VERSION:
            setScriptVersion((String)newValue);
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
         case XpdlPackage.EXPRESSION_TYPE__MIXED:
            getMixed().clear();
            return;
         case XpdlPackage.EXPRESSION_TYPE__GROUP:
            getGroup().clear();
            return;
         case XpdlPackage.EXPRESSION_TYPE__ANY:
            getAny().clear();
            return;
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_GRAMMAR:
            setScriptGrammar(SCRIPT_GRAMMAR_EDEFAULT);
            return;
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_TYPE:
            setScriptType(SCRIPT_TYPE_EDEFAULT);
            return;
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_VERSION:
            setScriptVersion(SCRIPT_VERSION_EDEFAULT);
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
         case XpdlPackage.EXPRESSION_TYPE__MIXED:
            return mixed != null && !mixed.isEmpty();
         case XpdlPackage.EXPRESSION_TYPE__GROUP:
            return !getGroup().isEmpty();
         case XpdlPackage.EXPRESSION_TYPE__ANY:
            return !getAny().isEmpty();
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_GRAMMAR:
            return SCRIPT_GRAMMAR_EDEFAULT == null ? scriptGrammar != null : !SCRIPT_GRAMMAR_EDEFAULT.equals(scriptGrammar);
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_TYPE:
            return SCRIPT_TYPE_EDEFAULT == null ? scriptType != null : !SCRIPT_TYPE_EDEFAULT.equals(scriptType);
         case XpdlPackage.EXPRESSION_TYPE__SCRIPT_VERSION:
            return SCRIPT_VERSION_EDEFAULT == null ? scriptVersion != null : !SCRIPT_VERSION_EDEFAULT.equals(scriptVersion);
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
      result.append(" (mixed: ");
      result.append(mixed);
      result.append(", scriptGrammar: ");
      result.append(scriptGrammar);
      result.append(", scriptType: ");
      result.append(scriptType);
      result.append(", scriptVersion: ");
      result.append(scriptVersion);
      result.append(')');
      return result.toString();
   }

} //ExpressionTypeImpl
