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
import org.eclipse.stardust.model.xpdl.carnot.DataMappingType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.DirectionType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getElementOid <em>Element Oid</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getApplicationAccessPoint <em>Application Access Point</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getApplicationPath <em>Application Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataMappingTypeImpl#getDirection <em>Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataMappingTypeImpl extends EObjectImpl implements DataMappingType
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
    * This is true if the Id attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean idESet;

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
    * This is true if the Name attribute has been set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   protected boolean nameESet;

   /**
    * The default value of the '{@link #getApplicationAccessPoint() <em>Application Access Point</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplicationAccessPoint()
    * @generated
    * @ordered
    */
   protected static final String APPLICATION_ACCESS_POINT_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getApplicationAccessPoint() <em>Application Access Point</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplicationAccessPoint()
    * @generated
    * @ordered
    */
   protected String applicationAccessPoint = APPLICATION_ACCESS_POINT_EDEFAULT;

   /**
    * The default value of the '{@link #getApplicationPath() <em>Application Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplicationPath()
    * @generated
    * @ordered
    */
   protected static final String APPLICATION_PATH_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getApplicationPath() <em>Application Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getApplicationPath()
    * @generated
    * @ordered
    */
   protected String applicationPath = APPLICATION_PATH_EDEFAULT;

   /**
    * The default value of the '{@link #getContext() <em>Context</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getContext()
    * @generated
    * @ordered
    */
   protected static final String CONTEXT_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getContext() <em>Context</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getContext()
    * @generated
    * @ordered
    */
   protected String context = CONTEXT_EDEFAULT;

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
    * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDirection()
    * @generated NOT
    * @ordered
    */
   protected static final DirectionType DIRECTION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDirection()
    * @generated
    * @ordered
    */
   protected DirectionType direction = DIRECTION_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DataMappingTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.DATA_MAPPING_TYPE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ELEMENT_OID, oldElementOid, elementOid, !oldElementOidESet));
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
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ELEMENT_OID, oldElementOid, ELEMENT_OID_EDEFAULT, oldElementOidESet));
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
   public String getApplicationAccessPoint()
   {
      return applicationAccessPoint;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setApplicationAccessPoint(String newApplicationAccessPoint)
   {
      String oldApplicationAccessPoint = applicationAccessPoint;
      applicationAccessPoint = newApplicationAccessPoint;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_ACCESS_POINT, oldApplicationAccessPoint, applicationAccessPoint));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getApplicationPath()
   {
      return applicationPath;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setApplicationPath(String newApplicationPath)
   {
      String oldApplicationPath = applicationPath;
      applicationPath = newApplicationPath;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_PATH, oldApplicationPath, applicationPath));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getContext()
   {
      return context;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setContext(String newContext)
   {
      String oldContext = context;
      context = newContext;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__CONTEXT, oldContext, context));
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA, oldData, newData);
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
            msgs = ((InternalEObject)data).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS, DataType.class, msgs);
         if (newData != null)
            msgs = ((InternalEObject)newData).eInverseAdd(this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS, DataType.class, msgs);
         msgs = basicSetData(newData, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA, newData, newData));
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
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA_PATH, oldDataPath, dataPath));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DirectionType getDirection()
   {
      return direction;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setDirection(DirectionType newDirection)
   {
      DirectionType oldDirection = direction;
      direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DIRECTION, oldDirection, direction));
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
      boolean oldIdESet = idESet;
      idESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID, oldId, id, !oldIdESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetId()
   {
      String oldId = id;
      boolean oldIdESet = idESet;
      id = ID_EDEFAULT;
      idESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID, oldId, ID_EDEFAULT, oldIdESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetId()
   {
      return idESet;
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
      boolean oldNameESet = nameESet;
      nameESet = true;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME, oldName, name, !oldNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetName()
   {
      String oldName = name;
      boolean oldNameESet = nameESet;
      name = NAME_EDEFAULT;
      nameESet = false;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.UNSET, CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME, oldName, NAME_EDEFAULT, oldNameESet));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetName()
   {
      return nameESet;
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
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA:
            if (data != null)
               msgs = ((InternalEObject)data).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_MAPPINGS, DataType.class, msgs);
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
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA:
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
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ELEMENT_OID:
            return getElementOid();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID:
            return getId();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME:
            return getName();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_ACCESS_POINT:
            return getApplicationAccessPoint();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_PATH:
            return getApplicationPath();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__CONTEXT:
            return getContext();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA:
            return getData();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA_PATH:
            return getDataPath();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DIRECTION:
            return getDirection();
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
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ELEMENT_OID:
            setElementOid((Long)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID:
            setId((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME:
            setName((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_ACCESS_POINT:
            setApplicationAccessPoint((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_PATH:
            setApplicationPath((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__CONTEXT:
            setContext((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA:
            setData((DataType)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA_PATH:
            setDataPath((String)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DIRECTION:
            setDirection((DirectionType)newValue);
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
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ELEMENT_OID:
            unsetElementOid();
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID:
            unsetId();
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME:
            unsetName();
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_ACCESS_POINT:
            setApplicationAccessPoint(APPLICATION_ACCESS_POINT_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_PATH:
            setApplicationPath(APPLICATION_PATH_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__CONTEXT:
            setContext(CONTEXT_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA:
            setData((DataType)null);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA_PATH:
            setDataPath(DATA_PATH_EDEFAULT);
            return;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DIRECTION:
            setDirection(DIRECTION_EDEFAULT);
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
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ELEMENT_OID:
            return isSetElementOid();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID:
            return isSetId();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME:
            return isSetName();
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_ACCESS_POINT:
            return APPLICATION_ACCESS_POINT_EDEFAULT == null ? applicationAccessPoint != null : !APPLICATION_ACCESS_POINT_EDEFAULT.equals(applicationAccessPoint);
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__APPLICATION_PATH:
            return APPLICATION_PATH_EDEFAULT == null ? applicationPath != null : !APPLICATION_PATH_EDEFAULT.equals(applicationPath);
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__CONTEXT:
            return CONTEXT_EDEFAULT == null ? context != null : !CONTEXT_EDEFAULT.equals(context);
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA:
            return data != null;
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DATA_PATH:
            return DATA_PATH_EDEFAULT == null ? dataPath != null : !DATA_PATH_EDEFAULT.equals(dataPath);
         case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__DIRECTION:
            return direction != DIRECTION_EDEFAULT;
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
      if (baseClass == IIdentifiableElement.class)
      {
         switch (derivedFeatureID)
         {
            case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID;
            case CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME: return CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME;
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
      if (baseClass == IIdentifiableElement.class)
      {
         switch (baseFeatureID)
         {
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__ID: return CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__ID;
            case CarnotWorkflowModelPackage.IIDENTIFIABLE_ELEMENT__NAME: return CarnotWorkflowModelPackage.DATA_MAPPING_TYPE__NAME;
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
      result.append(", id: ");
      if (idESet) result.append(id); else result.append("<unset>");
      result.append(", name: ");
      if (nameESet) result.append(name); else result.append("<unset>");
      result.append(", applicationAccessPoint: ");
      result.append(applicationAccessPoint);
      result.append(", applicationPath: ");
      result.append(applicationPath);
      result.append(", context: ");
      result.append(context);
      result.append(", dataPath: ");
      result.append(dataPath);
      result.append(", direction: ");
      result.append(direction);
      result.append(')');
      return result.toString();
   }

} //DataMappingTypeImpl
