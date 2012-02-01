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


import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.stardust.modeling.templates.emf.template.DocumentationType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Documentation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.DocumentationTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.DocumentationTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.DocumentationTypeImpl#getAny <em>Any</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentationTypeImpl extends EObjectImpl implements DocumentationType {
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
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected DocumentationTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return TemplatePackage.Literals.DOCUMENTATION_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureMap getMixed() {
      if (mixed == null)
      {
         mixed = new BasicFeatureMap(this, TemplatePackage.DOCUMENTATION_TYPE__MIXED);
      }
      return mixed;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureMap getGroup() {
      return (FeatureMap)getMixed().<FeatureMap.Entry>list(TemplatePackage.Literals.DOCUMENTATION_TYPE__GROUP);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureMap getAny() {
      return (FeatureMap)getGroup().<FeatureMap.Entry>list(TemplatePackage.Literals.DOCUMENTATION_TYPE__ANY);
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
         case TemplatePackage.DOCUMENTATION_TYPE__MIXED:
            return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
         case TemplatePackage.DOCUMENTATION_TYPE__GROUP:
            return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
         case TemplatePackage.DOCUMENTATION_TYPE__ANY:
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
         case TemplatePackage.DOCUMENTATION_TYPE__MIXED:
            if (coreType) return getMixed();
            return ((FeatureMap.Internal)getMixed()).getWrapper();
         case TemplatePackage.DOCUMENTATION_TYPE__GROUP:
            if (coreType) return getGroup();
            return ((FeatureMap.Internal)getGroup()).getWrapper();
         case TemplatePackage.DOCUMENTATION_TYPE__ANY:
            if (coreType) return getAny();
            return ((FeatureMap.Internal)getAny()).getWrapper();
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
         case TemplatePackage.DOCUMENTATION_TYPE__MIXED:
            ((FeatureMap.Internal)getMixed()).set(newValue);
            return;
         case TemplatePackage.DOCUMENTATION_TYPE__GROUP:
            ((FeatureMap.Internal)getGroup()).set(newValue);
            return;
         case TemplatePackage.DOCUMENTATION_TYPE__ANY:
            ((FeatureMap.Internal)getAny()).set(newValue);
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
         case TemplatePackage.DOCUMENTATION_TYPE__MIXED:
            getMixed().clear();
            return;
         case TemplatePackage.DOCUMENTATION_TYPE__GROUP:
            getGroup().clear();
            return;
         case TemplatePackage.DOCUMENTATION_TYPE__ANY:
            getAny().clear();
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
         case TemplatePackage.DOCUMENTATION_TYPE__MIXED:
            return mixed != null && !mixed.isEmpty();
         case TemplatePackage.DOCUMENTATION_TYPE__GROUP:
            return !getGroup().isEmpty();
         case TemplatePackage.DOCUMENTATION_TYPE__ANY:
            return !getAny().isEmpty();
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
      result.append(" (mixed: "); //$NON-NLS-1$
      result.append(mixed);
      result.append(')');
      return result.toString();
   }

   public String getAsText()
   {
      StringBuffer sb = new StringBuffer();
      appendContent(sb, this.getMixed());
      return sb.toString();
   }

   private void appendContent(StringBuffer sb, FeatureMap mixed)
   {
      if (mixed != null)
      {
         for (int i = 0; i < mixed.size(); i++)
         {
            Object value = mixed.getValue(i);
            if (value instanceof AnyType)
            {
               AnyType any = (AnyType) value;
               EStructuralFeature feature = mixed.getEStructuralFeature(i);
               // TODO: handle namespaces
               sb.append('<');
               sb.append(feature.getName());
               appendAttributes(sb, any.getAnyAttribute());
               FeatureMap anyMixed = any.getMixed();
               if (mixed == null || mixed.isEmpty())
               {
                  sb.append('/');
                  sb.append('>');
               }
               else
               {
                  sb.append('>');
                  appendContent(sb, anyMixed);
                  sb.append('<');
                  sb.append('/');
                  sb.append(feature.getName());
                  sb.append('>');
               }
            }
            else
            {
               sb.append(value);
            }
         }
      }
   }

   private void appendAttributes(StringBuffer sb, FeatureMap attributes)
   {
      if (attributes != null)
      {
         for (int i = 0; i < attributes.size(); i++)
         {
            Object value = attributes.getValue(i);
            EStructuralFeature feature = attributes.getEStructuralFeature(i);
            sb.append(' ');
            sb.append(feature.getName());
            sb.append('=');
            String stringValue = value == null ? "" : value.toString(); //$NON-NLS-1$
            char c = '\"';
            int x = stringValue.indexOf('\"');
            if (x >= 0)
            {
               int y = stringValue.indexOf('\'');
               if (y < 0 || x < y)
               {
                  c = '\'';
               }
            }
            sb.append(c);
            sb.append(stringValue);
            sb.append(c);
         }
      }
   }
} //DocumentationTypeImpl
