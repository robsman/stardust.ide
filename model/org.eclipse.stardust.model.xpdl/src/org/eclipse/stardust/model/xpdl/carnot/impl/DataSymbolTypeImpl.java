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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataSymbolTypeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DataSymbolTypeImpl#getDataMappings <em>Data Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataSymbolTypeImpl extends IModelElementNodeSymbolImpl implements DataSymbolType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

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
    * The cached value of the '{@link #getDataMappings() <em>Data Mappings</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDataMappings()
    * @generated
    * @ordered
    */
   protected EList<DataMappingConnectionType> dataMappings;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DataSymbolTypeImpl()
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
      return CarnotWorkflowModelPackage.Literals.DATA_SYMBOL_TYPE;
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
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA, oldData, newData);
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
            msgs = ((InternalEObject)data).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS, DataType.class, msgs);
         if (newData != null)
            msgs = ((InternalEObject)newData).eInverseAdd(this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS, DataType.class, msgs);
         msgs = basicSetData(newData, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA, newData, newData));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataMappingConnectionType> getDataMappings()
   {
      if (dataMappings == null)
      {
         dataMappings = new EObjectWithInverseEList.Unsettable<DataMappingConnectionType>(DataMappingConnectionType.class, this, CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA_MAPPINGS, CarnotWorkflowModelPackage.DATA_MAPPING_CONNECTION_TYPE__DATA_SYMBOL);
      }
      return dataMappings;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void unsetDataMappings()
   {
      if (dataMappings != null) ((InternalEList.Unsettable<?>)dataMappings).unset();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean isSetDataMappings()
   {
      return dataMappings != null && ((InternalEList.Unsettable<?>)dataMappings).isSet();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA:
            if (data != null)
               msgs = ((InternalEObject)data).eInverseRemove(this, CarnotWorkflowModelPackage.DATA_TYPE__DATA_SYMBOLS, DataType.class, msgs);
            return basicSetData((DataType)otherEnd, msgs);
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA_MAPPINGS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getDataMappings()).basicAdd(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA:
            return basicSetData(null, msgs);
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA_MAPPINGS:
            return ((InternalEList<?>)getDataMappings()).basicRemove(otherEnd, msgs);
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
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA:
            return getData();
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA_MAPPINGS:
            return getDataMappings();
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
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA:
            setData((DataType)newValue);
            return;
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA_MAPPINGS:
            getDataMappings().clear();
            getDataMappings().addAll((Collection<? extends DataMappingConnectionType>)newValue);
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
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA:
            setData((DataType)null);
            return;
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA_MAPPINGS:
            unsetDataMappings();
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
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA:
            return data != null;
         case CarnotWorkflowModelPackage.DATA_SYMBOL_TYPE__DATA_MAPPINGS:
            return isSetDataMappings();
      }
      return super.eIsSet(featureID);
   }

   /**
    * @generated NOT
    */
   public IIdentifiableModelElement getModelElement()
   {
      return getData();
   }

   /**
    * @generated NOT
    */
   public void setModelElement(IIdentifiableModelElement element)
   {
      setData((DataType) element);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getInConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getIGraphicalObject_ReferingToConnections(),
            CarnotWorkflowModelPackage.eINSTANCE.getDataSymbolType_DataMappings()
      });
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getOutConnectionFeatures()
   {
      return Arrays.asList(new EStructuralFeature[] {
            CarnotWorkflowModelPackage.eINSTANCE.getDataSymbolType_DataMappings()
      });
   }

} //DataSymbolTypeImpl
