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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ParameterMappingTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ParameterMappingTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ParameterMappingTypeImpl#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ParameterMappingTypeImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ParameterMappingTypeImpl#getParameterPath <em>Parameter Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterMappingTypeImpl extends EObjectImpl implements ParameterMappingType
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
    * The cached value of the '{@link #getData() <em>Data</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getData()
    * @generated
    * @ordered
    */
   protected DataType data;

   /**
    * The default value of the '{@link #getDataPath() <em>Data Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataPath()
    * @generated
    * @ordered
    */
   protected static final String DATA_PATH_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getDataPath() <em>Data Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataPath()
    * @generated
    * @ordered
    */
   protected String dataPath = DATA_PATH_EDEFAULT;

   /**
    * The default value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParameter()
    * @generated
    * @ordered
    */
   protected static final String PARAMETER_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParameter()
    * @generated
    * @ordered
    */
   protected String parameter = PARAMETER_EDEFAULT;

   /**
    * The default value of the '{@link #getParameterPath() <em>Parameter Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParameterPath()
    * @generated
    * @ordered
    */
   protected static final String PARAMETER_PATH_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getParameterPath() <em>Parameter Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getParameterPath()
    * @generated
    * @ordered
    */
   protected String parameterPath = PARAMETER_PATH_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ParameterMappingTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.PARAMETER_MAPPING_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
   public DataType getData()
   {
      return data;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetData(DataType newData, NotificationChain msgs)
   {
      DataType oldData = data;
      data = newData;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA, oldData, newData);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setData(DataType newData)
   {
      if (newData != data)
      {
         NotificationChain msgs = null;
         if (data != null)
            msgs = ((InternalEObject)data).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS, DataType.class, msgs);
         if (newData != null)
            msgs = ((InternalEObject)newData).eInverseAdd(this, CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS, DataType.class, msgs);
         msgs = basicSetData(newData, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA, newData, newData));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getDataPath()
   {
      return dataPath;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setDataPath(String newDataPath)
   {
      String oldDataPath = dataPath;
      dataPath = newDataPath;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA_PATH, oldDataPath, dataPath));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getParameter()
   {
      return parameter;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParameter(String newParameter)
   {
      String oldParameter = parameter;
      parameter = newParameter;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER, oldParameter, parameter));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getParameterPath()
   {
      return parameterPath;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setParameterPath(String newParameterPath)
   {
      String oldParameterPath = parameterPath;
      parameterPath = newParameterPath;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER_PATH, oldParameterPath, parameterPath));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA:
            if (data != null)
               msgs = ((InternalEObject)data).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE__PARAMETER_MAPPINGS, DataType.class, msgs);
            return basicSetData((DataType)otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
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
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA:
            return basicSetData(null, msgs);
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
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA:
            return getData();
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA_PATH:
            return getDataPath();
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER:
            return getParameter();
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER_PATH:
            return getParameterPath();
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
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA:
            setData((DataType)newValue);
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA_PATH:
            setDataPath((String)newValue);
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER:
            setParameter((String)newValue);
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER_PATH:
            setParameterPath((String)newValue);
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
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA:
            setData((DataType)null);
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA_PATH:
            setDataPath(DATA_PATH_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER:
            setParameter(PARAMETER_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER_PATH:
            setParameterPath(PARAMETER_PATH_EDEFAULT);
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
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA:
            return data != null;
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__DATA_PATH:
            return DATA_PATH_EDEFAULT == null ? dataPath != null : !DATA_PATH_EDEFAULT.equals(dataPath);
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER:
            return PARAMETER_EDEFAULT == null ? parameter != null : !PARAMETER_EDEFAULT.equals(parameter);
         case CarnotWorkflowModelPackage.PARAMETER_MAPPING_TYPE__PARAMETER_PATH:
            return PARAMETER_PATH_EDEFAULT == null ? parameterPath != null : !PARAMETER_PATH_EDEFAULT.equals(parameterPath);
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
      result.append(" (elementOid: "); //$NON-NLS-1$
      if (elementOidESet) result.append(elementOid); else result.append("<unset>"); //$NON-NLS-1$
      result.append(", dataPath: "); //$NON-NLS-1$
      result.append(dataPath);
      result.append(", parameter: "); //$NON-NLS-1$
      result.append(parameter);
      result.append(", parameterPath: "); //$NON-NLS-1$
      result.append(parameterPath);
      result.append(')');
      return result.toString();
   }

} //ParameterMappingTypeImpl
