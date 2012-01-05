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


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatesType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Templates Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatesTypeImpl#getTemplate <em>Template</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemplatesTypeImpl extends EObjectImpl implements TemplatesType {
	/**
    * The cached value of the '{@link #getTemplate() <em>Template</em>}' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getTemplate()
    * @generated
    * @ordered
    */
	protected EList<TemplateType> template;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TemplatesTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return TemplatePackage.Literals.TEMPLATES_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EList<TemplateType> getTemplate() {
      if (template == null)
      {
         template = new EObjectContainmentEList<TemplateType>(TemplateType.class, this, TemplatePackage.TEMPLATES_TYPE__TEMPLATE);
      }
      return template;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateType getTemplate(String templateId) {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
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
         case TemplatePackage.TEMPLATES_TYPE__TEMPLATE:
            return ((InternalEList<?>)getTemplate()).basicRemove(otherEnd, msgs);
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
         case TemplatePackage.TEMPLATES_TYPE__TEMPLATE:
            return getTemplate();
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
   public void eSet(int featureID, Object newValue) {
      switch (featureID)
      {
         case TemplatePackage.TEMPLATES_TYPE__TEMPLATE:
            getTemplate().clear();
            getTemplate().addAll((Collection<? extends TemplateType>)newValue);
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
         case TemplatePackage.TEMPLATES_TYPE__TEMPLATE:
            getTemplate().clear();
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
         case TemplatePackage.TEMPLATES_TYPE__TEMPLATE:
            return template != null && !template.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} //TemplatesTypeImpl
