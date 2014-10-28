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
import org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl#getBasicType <em>Basic Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl#getSchemaType <em>Schema Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl#getExternalReference <em>External Reference</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl#getCarnotType <em>Carnot Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataTypeTypeImpl extends EObjectImpl implements DataTypeType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getBasicType() <em>Basic Type</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getBasicType()
    * @generated
    * @ordered
    */
   protected BasicTypeType basicType;

   /**
    * The cached value of the '{@link #getDeclaredType() <em>Declared Type</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDeclaredType()
    * @generated
    * @ordered
    */
   protected DeclaredTypeType declaredType;

   /**
    * The cached value of the '{@link #getSchemaType() <em>Schema Type</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSchemaType()
    * @generated
    * @ordered
    */
   protected SchemaTypeType schemaType;

   /**
    * The cached value of the '{@link #getExternalReference() <em>External Reference</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExternalReference()
    * @generated
    * @ordered
    */
   protected ExternalReferenceType externalReference;

   /**
    * The default value of the '{@link #getCarnotType() <em>Carnot Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCarnotType()
    * @generated
    * @ordered
    */
   protected static final String CARNOT_TYPE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getCarnotType() <em>Carnot Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getCarnotType()
    * @generated
    * @ordered
    */
   protected String carnotType = CARNOT_TYPE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DataTypeTypeImpl()
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
      return XpdlPackage.Literals.DATA_TYPE_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public BasicTypeType getBasicType()
   {
      return basicType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetBasicType(BasicTypeType newBasicType, NotificationChain msgs)
   {
      BasicTypeType oldBasicType = basicType;
      basicType = newBasicType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE, oldBasicType, newBasicType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setBasicType(BasicTypeType newBasicType)
   {
      if (newBasicType != basicType)
      {
         NotificationChain msgs = null;
         if (basicType != null)
            msgs = ((InternalEObject)basicType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE, null, msgs);
         if (newBasicType != null)
            msgs = ((InternalEObject)newBasicType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE, null, msgs);
         msgs = basicSetBasicType(newBasicType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE, newBasicType, newBasicType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DeclaredTypeType getDeclaredType()
   {
      return declaredType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetDeclaredType(DeclaredTypeType newDeclaredType, NotificationChain msgs)
   {
      DeclaredTypeType oldDeclaredType = declaredType;
      declaredType = newDeclaredType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE, oldDeclaredType, newDeclaredType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setDeclaredType(DeclaredTypeType newDeclaredType)
   {
      if (newDeclaredType != declaredType)
      {
         NotificationChain msgs = null;
         if (declaredType != null)
            msgs = ((InternalEObject)declaredType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE, null, msgs);
         if (newDeclaredType != null)
            msgs = ((InternalEObject)newDeclaredType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE, null, msgs);
         msgs = basicSetDeclaredType(newDeclaredType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE, newDeclaredType, newDeclaredType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public SchemaTypeType getSchemaType()
   {
      return schemaType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetSchemaType(SchemaTypeType newSchemaType, NotificationChain msgs)
   {
      SchemaTypeType oldSchemaType = schemaType;
      schemaType = newSchemaType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE, oldSchemaType, newSchemaType);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSchemaType(SchemaTypeType newSchemaType)
   {
      if (newSchemaType != schemaType)
      {
         NotificationChain msgs = null;
         if (schemaType != null)
            msgs = ((InternalEObject)schemaType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE, null, msgs);
         if (newSchemaType != null)
            msgs = ((InternalEObject)newSchemaType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE, null, msgs);
         msgs = basicSetSchemaType(newSchemaType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE, newSchemaType, newSchemaType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExternalReferenceType getExternalReference()
   {
      return externalReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetExternalReference(ExternalReferenceType newExternalReference, NotificationChain msgs)
   {
      ExternalReferenceType oldExternalReference = externalReference;
      externalReference = newExternalReference;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE, oldExternalReference, newExternalReference);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setExternalReference(ExternalReferenceType newExternalReference)
   {
      if (newExternalReference != externalReference)
      {
         NotificationChain msgs = null;
         if (externalReference != null)
            msgs = ((InternalEObject)externalReference).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE, null, msgs);
         if (newExternalReference != null)
            msgs = ((InternalEObject)newExternalReference).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE, null, msgs);
         msgs = basicSetExternalReference(newExternalReference, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE, newExternalReference, newExternalReference));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getCarnotType()
   {
      return carnotType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setCarnotType(String newCarnotType)
   {
      String oldCarnotType = carnotType;
      carnotType = newCarnotType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.DATA_TYPE_TYPE__CARNOT_TYPE, oldCarnotType, carnotType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public XpdlTypeType getDataType()
   {
      if (schemaType != null)
      {
         return schemaType;
      }
      else if (declaredType != null)
      {
         return declaredType;
      }
      else if (externalReference != null)
      {
         return externalReference;
      }
      return basicType;
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
         case XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE:
            return basicSetBasicType(null, msgs);
         case XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE:
            return basicSetDeclaredType(null, msgs);
         case XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE:
            return basicSetSchemaType(null, msgs);
         case XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE:
            return basicSetExternalReference(null, msgs);
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
         case XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE:
            return getBasicType();
         case XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE:
            return getDeclaredType();
         case XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE:
            return getSchemaType();
         case XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE:
            return getExternalReference();
         case XpdlPackage.DATA_TYPE_TYPE__CARNOT_TYPE:
            return getCarnotType();
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
         case XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE:
            setBasicType((BasicTypeType)newValue);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE:
            setDeclaredType((DeclaredTypeType)newValue);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE:
            setSchemaType((SchemaTypeType)newValue);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE:
            setExternalReference((ExternalReferenceType)newValue);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__CARNOT_TYPE:
            setCarnotType((String)newValue);
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
         case XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE:
            setBasicType((BasicTypeType)null);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE:
            setDeclaredType((DeclaredTypeType)null);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE:
            setSchemaType((SchemaTypeType)null);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE:
            setExternalReference((ExternalReferenceType)null);
            return;
         case XpdlPackage.DATA_TYPE_TYPE__CARNOT_TYPE:
            setCarnotType(CARNOT_TYPE_EDEFAULT);
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
         case XpdlPackage.DATA_TYPE_TYPE__BASIC_TYPE:
            return basicType != null;
         case XpdlPackage.DATA_TYPE_TYPE__DECLARED_TYPE:
            return declaredType != null;
         case XpdlPackage.DATA_TYPE_TYPE__SCHEMA_TYPE:
            return schemaType != null;
         case XpdlPackage.DATA_TYPE_TYPE__EXTERNAL_REFERENCE:
            return externalReference != null;
         case XpdlPackage.DATA_TYPE_TYPE__CARNOT_TYPE:
            return CARNOT_TYPE_EDEFAULT == null ? carnotType != null : !CARNOT_TYPE_EDEFAULT.equals(carnotType);
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
      result.append(" (carnotType: ");
      result.append(carnotType);
      result.append(')');
      return result.toString();
   }

} //DataTypeTypeImpl
