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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DocumentRoot;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.DocumentRootImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

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
    * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getXMLNSPrefixMap()
    * @generated
    * @ordered
    */
   protected EMap<String, String> xMLNSPrefixMap;

   /**
    * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getXSISchemaLocation()
    * @generated
    * @ordered
    */
   protected EMap<String, String> xSISchemaLocation;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DocumentRootImpl()
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
      return CarnotWorkflowModelPackage.Literals.DOCUMENT_ROOT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getMixed()
   {
      if (mixed == null)
      {
         mixed = new BasicFeatureMap(this, CarnotWorkflowModelPackage.DOCUMENT_ROOT__MIXED);
      }
      return mixed;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EMap<String, String> getXMLNSPrefixMap()
   {
      if (xMLNSPrefixMap == null)
      {
         xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, CarnotWorkflowModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
      }
      return xMLNSPrefixMap;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EMap<String, String> getXSISchemaLocation()
   {
      if (xSISchemaLocation == null)
      {
         xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, CarnotWorkflowModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
      }
      return xSISchemaLocation;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ModelType getModel()
   {
      return (ModelType)getMixed().get(CarnotWorkflowModelPackage.Literals.DOCUMENT_ROOT__MODEL, true);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetModel(ModelType newModel, NotificationChain msgs)
   {
      return ((FeatureMap.Internal)getMixed()).basicAdd(CarnotWorkflowModelPackage.Literals.DOCUMENT_ROOT__MODEL, newModel, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setModel(ModelType newModel)
   {
      ((FeatureMap.Internal)getMixed()).set(CarnotWorkflowModelPackage.Literals.DOCUMENT_ROOT__MODEL, newModel);
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
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MIXED:
            return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MODEL:
            return basicSetModel(null, msgs);
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
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MIXED:
            if (coreType) return getMixed();
            return ((FeatureMap.Internal)getMixed()).getWrapper();
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            if (coreType) return getXMLNSPrefixMap();
            else return getXMLNSPrefixMap().map();
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            if (coreType) return getXSISchemaLocation();
            else return getXSISchemaLocation().map();
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MODEL:
            return getModel();
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
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MIXED:
            ((FeatureMap.Internal)getMixed()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MODEL:
            setModel((ModelType)newValue);
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
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MIXED:
            getMixed().clear();
            return;
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            getXMLNSPrefixMap().clear();
            return;
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            getXSISchemaLocation().clear();
            return;
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MODEL:
            setModel((ModelType)null);
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
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MIXED:
            return mixed != null && !mixed.isEmpty();
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
            return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
            return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
         case CarnotWorkflowModelPackage.DOCUMENT_ROOT__MODEL:
            return getModel() != null;
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
      result.append(" (mixed: "); //$NON-NLS-1$
      result.append(mixed);
      result.append(')');
      return result.toString();
   }

} //DocumentRootImpl
