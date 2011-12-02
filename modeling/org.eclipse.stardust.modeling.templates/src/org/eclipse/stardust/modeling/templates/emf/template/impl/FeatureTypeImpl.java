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
package org.eclipse.stardust.modeling.templates.emf.template.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureType;
import org.eclipse.stardust.modeling.templates.emf.template.ScopeType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureTypeImpl extends EObjectImpl implements FeatureType {
	/**
    * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getLabel()
    * @generated
    * @ordered
    */
	protected static final String LABEL_EDEFAULT = null;

	/**
    * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getLabel()
    * @generated
    * @ordered
    */
	protected String label = LABEL_EDEFAULT;

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
    * The default value of the '{@link #getType() <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
	protected static final FeatureStyleType TYPE_EDEFAULT = FeatureStyleType.TEXT;

	/**
    * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getType()
    * @generated
    * @ordered
    */
	protected FeatureStyleType type = TYPE_EDEFAULT;

	/**
    * The default value of the '{@link #getScope() <em>Scope</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getScope()
    * @generated
    * @ordered
    */
	protected static final ScopeType SCOPE_EDEFAULT = ScopeType.MODEL;

	/**
    * The cached value of the '{@link #getScope() <em>Scope</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getScope()
    * @generated
    * @ordered
    */
	protected ScopeType scope = SCOPE_EDEFAULT;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected FeatureTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return TemplatePackage.Literals.FEATURE_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String getLabel() {
      return label;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setLabel(String newLabel) {
      String oldLabel = label;
      label = newLabel;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.FEATURE_TYPE__LABEL, oldLabel, label));
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
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.FEATURE_TYPE__NAME, oldName, name));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureStyleType getType() {
      return type;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setType(FeatureStyleType newType) {
      FeatureStyleType oldType = type;
      type = newType == null ? TYPE_EDEFAULT : newType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.FEATURE_TYPE__TYPE, oldType, type));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ScopeType getScope() {
      return scope;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setScope(ScopeType newScope) {
      ScopeType oldScope = scope;
      scope = newScope == null ? SCOPE_EDEFAULT : newScope;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.FEATURE_TYPE__SCOPE, oldScope, scope));
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
         case TemplatePackage.FEATURE_TYPE__LABEL:
            return getLabel();
         case TemplatePackage.FEATURE_TYPE__NAME:
            return getName();
         case TemplatePackage.FEATURE_TYPE__TYPE:
            return getType();
         case TemplatePackage.FEATURE_TYPE__SCOPE:
            return getScope();
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
         case TemplatePackage.FEATURE_TYPE__LABEL:
            setLabel((String)newValue);
            return;
         case TemplatePackage.FEATURE_TYPE__NAME:
            setName((String)newValue);
            return;
         case TemplatePackage.FEATURE_TYPE__TYPE:
            setType((FeatureStyleType)newValue);
            return;
         case TemplatePackage.FEATURE_TYPE__SCOPE:
            setScope((ScopeType)newValue);
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
         case TemplatePackage.FEATURE_TYPE__LABEL:
            setLabel(LABEL_EDEFAULT);
            return;
         case TemplatePackage.FEATURE_TYPE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case TemplatePackage.FEATURE_TYPE__TYPE:
            setType(TYPE_EDEFAULT);
            return;
         case TemplatePackage.FEATURE_TYPE__SCOPE:
            setScope(SCOPE_EDEFAULT);
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
         case TemplatePackage.FEATURE_TYPE__LABEL:
            return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
         case TemplatePackage.FEATURE_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case TemplatePackage.FEATURE_TYPE__TYPE:
            return type != TYPE_EDEFAULT;
         case TemplatePackage.FEATURE_TYPE__SCOPE:
            return scope != SCOPE_EDEFAULT;
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
      result.append(" (label: ");
      result.append(label);
      result.append(", name: ");
      result.append(name);
      result.append(", type: ");
      result.append(type);
      result.append(", scope: ");
      result.append(scope);
      result.append(')');
      return result.toString();
   }

} //FeatureTypeImpl
