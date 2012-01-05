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
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Attribute Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl#getExtendedAnnotation <em>Extended Annotation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendedAttributeTypeImpl extends EObjectImpl implements ExtendedAttributeType {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard";

	/**
    * The cached value of the '{@link #getExtendedAnnotation() <em>Extended Annotation</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getExtendedAnnotation()
    * @generated
    * @ordered
    */
	protected ExtendedAnnotationType extendedAnnotation;

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
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
	protected static final String NAME_EDEFAULT = null;

	/**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
	protected String name = NAME_EDEFAULT;

	/**
    * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getValue()
    * @generated
    * @ordered
    */
	protected static final String VALUE_EDEFAULT = null;

	/**
    * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getValue()
    * @generated
    * @ordered
    */
	protected String value = VALUE_EDEFAULT;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected ExtendedAttributeTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return XpdlPackage.Literals.EXTENDED_ATTRIBUTE_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ExtendedAnnotationType getExtendedAnnotation() {
      return extendedAnnotation;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetExtendedAnnotation(ExtendedAnnotationType newExtendedAnnotation, NotificationChain msgs) {
      ExtendedAnnotationType oldExtendedAnnotation = extendedAnnotation;
      extendedAnnotation = newExtendedAnnotation;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION, oldExtendedAnnotation, newExtendedAnnotation);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setExtendedAnnotation(ExtendedAnnotationType newExtendedAnnotation) {
      if (newExtendedAnnotation != extendedAnnotation)
      {
         NotificationChain msgs = null;
         if (extendedAnnotation != null)
            msgs = ((InternalEObject)extendedAnnotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION, null, msgs);
         if (newExtendedAnnotation != null)
            msgs = ((InternalEObject)newExtendedAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION, null, msgs);
         msgs = basicSetExtendedAnnotation(newExtendedAnnotation, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION, newExtendedAnnotation, newExtendedAnnotation));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureMap getMixed() {
      if (mixed == null)
      {
         mixed = new BasicFeatureMap(this, XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__MIXED);
      }
      return mixed;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureMap getGroup() {
      return (FeatureMap)getMixed().<FeatureMap.Entry>list(XpdlPackage.Literals.EXTENDED_ATTRIBUTE_TYPE__GROUP);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureMap getAny() {
      return (FeatureMap)getGroup().<FeatureMap.Entry>list(XpdlPackage.Literals.EXTENDED_ATTRIBUTE_TYPE__ANY);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String getName() {
      return name;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setName(String newName) {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__NAME, oldName, name));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String getValue() {
      return value;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setValue(String newValue) {
      String oldValue = value;
      value = newValue;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__VALUE, oldValue, value));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID)
      {
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION:
            return basicSetExtendedAnnotation(null, msgs);
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__MIXED:
            return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__GROUP:
            return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__ANY:
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
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID)
      {
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION:
            return getExtendedAnnotation();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__MIXED:
            if (coreType) return getMixed();
            return ((FeatureMap.Internal)getMixed()).getWrapper();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__GROUP:
            if (coreType) return getGroup();
            return ((FeatureMap.Internal)getGroup()).getWrapper();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__ANY:
            if (coreType) return getAny();
            return ((FeatureMap.Internal)getAny()).getWrapper();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__NAME:
            return getName();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__VALUE:
            return getValue();
      }
      return super.eGet(featureID, resolve, coreType);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID)
      {
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION:
            setExtendedAnnotation((ExtendedAnnotationType)newValue);
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__MIXED:
            ((FeatureMap.Internal)getMixed()).set(newValue);
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__GROUP:
            ((FeatureMap.Internal)getGroup()).set(newValue);
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__ANY:
            ((FeatureMap.Internal)getAny()).set(newValue);
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__NAME:
            setName((String)newValue);
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__VALUE:
            setValue((String)newValue);
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
   public void eUnset(int featureID) {
      switch (featureID)
      {
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION:
            setExtendedAnnotation((ExtendedAnnotationType)null);
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__MIXED:
            getMixed().clear();
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__GROUP:
            getGroup().clear();
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__ANY:
            getAny().clear();
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__VALUE:
            setValue(VALUE_EDEFAULT);
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
   public boolean eIsSet(int featureID) {
      switch (featureID)
      {
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION:
            return extendedAnnotation != null;
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__MIXED:
            return mixed != null && !mixed.isEmpty();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__GROUP:
            return !getGroup().isEmpty();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__ANY:
            return !getAny().isEmpty();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE__VALUE:
            return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      }
      return super.eIsSet(featureID);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   public String toString() {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (mixed: ");
      result.append(mixed);
      result.append(", name: ");
      result.append(name);
      result.append(", value: ");
      result.append(value);
      result.append(')');
      return result.toString();
   }

} //ExtendedAttributeTypeImpl