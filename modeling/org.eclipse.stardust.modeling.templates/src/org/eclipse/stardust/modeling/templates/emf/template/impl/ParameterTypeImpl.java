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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureType;
import org.eclipse.stardust.modeling.templates.emf.template.ParameterType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ParameterTypeImpl#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ParameterTypeImpl#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterTypeImpl extends ReferenceTypeImpl implements ParameterType {
	/**
    * The cached value of the '{@link #getActivity() <em>Activity</em>}' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getActivity()
    * @generated
    * @ordered
    */
	protected ActivityType activity;

	/**
    * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getFeatures()
    * @generated
    * @ordered
    */
	protected EList<FeatureType> features;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected ParameterTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return TemplatePackage.Literals.PARAMETER_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ActivityType getActivity() {
      if (activity != null && activity.eIsProxy())
      {
         InternalEObject oldActivity = (InternalEObject)activity;
         activity = (ActivityType)eResolveProxy(oldActivity);
         if (activity != oldActivity)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.PARAMETER_TYPE__ACTIVITY, oldActivity, activity));
         }
      }
      return activity;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ActivityType basicGetActivity() {
      return activity;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setActivity(ActivityType newActivity) {
      ActivityType oldActivity = activity;
      activity = newActivity;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.PARAMETER_TYPE__ACTIVITY, oldActivity, activity));
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EList<FeatureType> getFeatures() {
      if (features == null)
      {
         features = new EObjectContainmentEList<FeatureType>(FeatureType.class, this, TemplatePackage.PARAMETER_TYPE__FEATURES);
      }
      return features;
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
         case TemplatePackage.PARAMETER_TYPE__FEATURES:
            return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
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
         case TemplatePackage.PARAMETER_TYPE__ACTIVITY:
            if (resolve) return getActivity();
            return basicGetActivity();
         case TemplatePackage.PARAMETER_TYPE__FEATURES:
            return getFeatures();
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
         case TemplatePackage.PARAMETER_TYPE__ACTIVITY:
            setActivity((ActivityType)newValue);
            return;
         case TemplatePackage.PARAMETER_TYPE__FEATURES:
            getFeatures().clear();
            getFeatures().addAll((Collection<? extends FeatureType>)newValue);
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
         case TemplatePackage.PARAMETER_TYPE__ACTIVITY:
            setActivity((ActivityType)null);
            return;
         case TemplatePackage.PARAMETER_TYPE__FEATURES:
            getFeatures().clear();
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
         case TemplatePackage.PARAMETER_TYPE__ACTIVITY:
            return activity != null;
         case TemplatePackage.PARAMETER_TYPE__FEATURES:
            return features != null && !features.isEmpty();
      }
      return super.eIsSet(featureID);
   }

	/**
	 * @generated NOT
	 */
    public EObject getReference()
    {
        if (activity != null)
        {
           return activity;
        }
        return super.getReference();
    }

} //ParameterTypeImpl
