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
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl#getHref <em>Href</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalPackageImpl extends EObjectImpl implements ExternalPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The cached value of the '{@link #getExtendedAttributes() <em>Extended Attributes</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExtendedAttributes()
    * @generated
    * @ordered
    */
   protected ExtendedAttributesType extendedAttributes;

   /**
    * The default value of the '{@link #getHref() <em>Href</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getHref()
    * @generated
    * @ordered
    */
   protected static final String HREF_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getHref() <em>Href</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getHref()
    * @generated
    * @ordered
    */
   protected String href = HREF_EDEFAULT;

   /**
    * The default value of the '{@link #getId() <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getId()
    * @generated
    * @ordered
    */
   protected static final String ID_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getId()
    * @generated
    * @ordered
    */
   protected String id = ID_EDEFAULT;

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
   protected ExternalPackageImpl()
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
      return XpdlPackage.Literals.EXTERNAL_PACKAGE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExtendedAttributesType getExtendedAttributes()
   {
      return extendedAttributes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetExtendedAttributes(ExtendedAttributesType newExtendedAttributes, NotificationChain msgs)
   {
      ExtendedAttributesType oldExtendedAttributes = extendedAttributes;
      extendedAttributes = newExtendedAttributes;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES, oldExtendedAttributes, newExtendedAttributes);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setExtendedAttributes(ExtendedAttributesType newExtendedAttributes)
   {
      if (newExtendedAttributes != extendedAttributes)
      {
         NotificationChain msgs = null;
         if (extendedAttributes != null)
            msgs = ((InternalEObject)extendedAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES, null, msgs);
         if (newExtendedAttributes != null)
            msgs = ((InternalEObject)newExtendedAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES, null, msgs);
         msgs = basicSetExtendedAttributes(newExtendedAttributes, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES, newExtendedAttributes, newExtendedAttributes));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getHref()
   {
      return href;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setHref(String newHref)
   {
      String oldHref = href;
      href = newHref;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_PACKAGE__HREF, oldHref, href));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getId()
   {
      return id;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setId(String newId)
   {
      String oldId = id;
      id = newId;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_PACKAGE__ID, oldId, id));
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
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.EXTERNAL_PACKAGE__NAME, oldName, name));
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
         case XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES:
            return basicSetExtendedAttributes(null, msgs);
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
         case XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES:
            return getExtendedAttributes();
         case XpdlPackage.EXTERNAL_PACKAGE__HREF:
            return getHref();
         case XpdlPackage.EXTERNAL_PACKAGE__ID:
            return getId();
         case XpdlPackage.EXTERNAL_PACKAGE__NAME:
            return getName();
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
         case XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES:
            setExtendedAttributes((ExtendedAttributesType)newValue);
            return;
         case XpdlPackage.EXTERNAL_PACKAGE__HREF:
            setHref((String)newValue);
            return;
         case XpdlPackage.EXTERNAL_PACKAGE__ID:
            setId((String)newValue);
            return;
         case XpdlPackage.EXTERNAL_PACKAGE__NAME:
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
         case XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES:
            setExtendedAttributes((ExtendedAttributesType)null);
            return;
         case XpdlPackage.EXTERNAL_PACKAGE__HREF:
            setHref(HREF_EDEFAULT);
            return;
         case XpdlPackage.EXTERNAL_PACKAGE__ID:
            setId(ID_EDEFAULT);
            return;
         case XpdlPackage.EXTERNAL_PACKAGE__NAME:
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
         case XpdlPackage.EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES:
            return extendedAttributes != null;
         case XpdlPackage.EXTERNAL_PACKAGE__HREF:
            return HREF_EDEFAULT == null ? href != null : !HREF_EDEFAULT.equals(href);
         case XpdlPackage.EXTERNAL_PACKAGE__ID:
            return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
         case XpdlPackage.EXTERNAL_PACKAGE__NAME:
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
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (href: ");
      result.append(href);
      result.append(", id: ");
      result.append(id);
      result.append(", name: ");
      result.append(name);
      result.append(')');
      return result.toString();
   }

} //ExternalPackageImpl
