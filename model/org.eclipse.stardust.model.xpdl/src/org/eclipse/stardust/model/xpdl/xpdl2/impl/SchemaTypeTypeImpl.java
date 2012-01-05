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
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.xsd.XSDSchema;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schema Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.SchemaTypeTypeImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SchemaTypeTypeImpl extends EObjectImpl implements SchemaTypeType {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard";

	/**
    * The cached value of the '{@link #getSchema() <em>Schema</em>}' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #getSchema()
    * @generated
    * @ordered
    */
	protected XSDSchema schema;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected SchemaTypeTypeImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   protected EClass eStaticClass() {
      return XpdlPackage.Literals.SCHEMA_TYPE_TYPE;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public XSDSchema getSchema() {
      return schema;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public NotificationChain basicSetSchema(XSDSchema newSchema, NotificationChain msgs) {
      XSDSchema oldSchema = schema;
      schema = newSchema;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA, oldSchema, newSchema);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public void setSchema(XSDSchema newSchema) {
      if (newSchema != schema)
      {
         NotificationChain msgs = null;
         if (schema != null)
            msgs = ((InternalEObject)schema).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA, null, msgs);
         if (newSchema != null)
            msgs = ((InternalEObject)newSchema).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA, null, msgs);
         msgs = basicSetSchema(newSchema, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA, newSchema, newSchema));
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
         case XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA:
            return basicSetSchema(null, msgs);
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
         case XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA:
            return getSchema();
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
         case XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA:
            setSchema((XSDSchema)newValue);
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
         case XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA:
            setSchema((XSDSchema)null);
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
         case XpdlPackage.SCHEMA_TYPE_TYPE__SCHEMA:
            return schema != null;
      }
      return super.eIsSet(featureID);
   }

} //SchemaTypeTypeImpl