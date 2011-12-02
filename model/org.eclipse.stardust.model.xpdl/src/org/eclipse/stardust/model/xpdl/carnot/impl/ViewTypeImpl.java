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
package org.eclipse.stardust.model.xpdl.carnot.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.AttributeType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DescriptionType;
import org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ViewTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ViewTypeImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ViewTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ViewTypeImpl#getView <em>View</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ViewTypeImpl#getViewable <em>Viewable</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ViewTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewTypeImpl extends EObjectImpl implements ViewType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The default value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getElementOid()
    * @generated
    * @ordered
    */
   protected static final long ELEMENT_OID_EDEFAULT = 0L;

   /**
    * The cached value of the '{@link #getElementOid() <em>Element Oid</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getElementOid()
    * @generated
    * @ordered
    */
   protected long elementOid = ELEMENT_OID_EDEFAULT;

   /**
    * This is true if the Element Oid attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean elementOidESet;

   /**
    * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAttribute()
    * @generated
    * @ordered
    */
   protected EList<AttributeType> attribute;

   /**
    * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDescription()
    * @generated
    * @ordered
    */
   protected DescriptionType description;

   /**
    * The cached value of the '{@link #getView() <em>View</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getView()
    * @generated
    * @ordered
    */
   protected EList<ViewType> view;

   /**
    * The cached value of the '{@link #getViewable() <em>Viewable</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getViewable()
    * @generated
    * @ordered
    */
   protected EList<ViewableType> viewable;

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
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ViewTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.VIEW_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public long getElementOid()
   {
      return elementOid;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setElementOid(long newElementOid)
   {
      long oldElementOid = elementOid;
      elementOid = newElementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOidESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.VIEW_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetElementOid()
   {
      long oldElementOid = elementOid;
      boolean oldElementOidESet = elementOidESet;
      elementOid = ELEMENT_OID_EDEFAULT;
      elementOidESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.VIEW_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetElementOid()
   {
      return elementOidESet;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DescriptionType getDescription()
   {
      return description;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetDescription(DescriptionType newDescription, NotificationChain msgs)
   {
      DescriptionType oldDescription = description;
      description = newDescription;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION, oldDescription, newDescription);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setDescription(DescriptionType newDescription)
   {
      if (newDescription != description)
      {
         NotificationChain msgs = null;
         if (description != null)
            msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION, null, msgs);
         if (newDescription != null)
            msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION, null, msgs);
         msgs = basicSetDescription(newDescription, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION, newDescription, newDescription));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<AttributeType> getAttribute()
   {
      if (attribute == null)
      {
         attribute = new EObjectContainmentEList<AttributeType>(AttributeType.class, this, CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE);
      }
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ViewType> getView()
   {
      if (view == null)
      {
         view = new EObjectContainmentEList<ViewType>(ViewType.class, this, CarnotWorkflowModelPackage.VIEW_TYPE__VIEW);
      }
      return view;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ViewableType> getViewable()
   {
      if (viewable == null)
      {
         viewable = new EObjectContainmentEList<ViewableType>(ViewableType.class, this, CarnotWorkflowModelPackage.VIEW_TYPE__VIEWABLE);
      }
      return viewable;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setName(String newName)
   {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.VIEW_TYPE__NAME, oldName, name));
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
         case CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE:
            return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION:
            return basicSetDescription(null, msgs);
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEW:
            return ((InternalEList<?>)getView()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEWABLE:
            return ((InternalEList<?>)getViewable()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.VIEW_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE:
            return getAttribute();
         case CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION:
            return getDescription();
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEW:
            return getView();
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEWABLE:
            return getViewable();
         case CarnotWorkflowModelPackage.VIEW_TYPE__NAME:
            return getName();
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
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.VIEW_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE:
            getAttribute().clear();
            getAttribute().addAll((Collection<? extends AttributeType>)newValue);
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION:
            setDescription((DescriptionType)newValue);
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEW:
            getView().clear();
            getView().addAll((Collection<? extends ViewType>)newValue);
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEWABLE:
            getViewable().clear();
            getViewable().addAll((Collection<? extends ViewableType>)newValue);
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__NAME:
            setName((String)newValue);
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
         case CarnotWorkflowModelPackage.VIEW_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE:
            getAttribute().clear();
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION:
            setDescription((DescriptionType)null);
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEW:
            getView().clear();
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEWABLE:
            getViewable().clear();
            return;
         case CarnotWorkflowModelPackage.VIEW_TYPE__NAME:
            setName(NAME_EDEFAULT);
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
         case CarnotWorkflowModelPackage.VIEW_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE:
            return attribute != null && !attribute.isEmpty();
         case CarnotWorkflowModelPackage.VIEW_TYPE__DESCRIPTION:
            return description != null;
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEW:
            return view != null && !view.isEmpty();
         case CarnotWorkflowModelPackage.VIEW_TYPE__VIEWABLE:
            return viewable != null && !viewable.isEmpty();
         case CarnotWorkflowModelPackage.VIEW_TYPE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == IExtensibleElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE: return CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE;
            default: return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == IExtensibleElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IEXTENSIBLE_ELEMENT__ATTRIBUTE: return CarnotWorkflowModelPackage.VIEW_TYPE__ATTRIBUTE;
            default: return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
      result.append(" (elementOid: ");
      if (elementOidESet) result.append(elementOid); else result.append("<unset>");
      result.append(", name: ");
      result.append(name);
      result.append(')');
      return result.toString();
   }

} //ViewTypeImpl
