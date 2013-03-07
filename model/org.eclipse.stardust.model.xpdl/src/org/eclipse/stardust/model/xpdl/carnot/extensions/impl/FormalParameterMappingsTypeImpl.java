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
package org.eclipse.stardust.model.xpdl.carnot.extensions.impl;




import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Formal Parameter Mappings Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingsTypeImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormalParameterMappingsTypeImpl extends EObjectImpl implements FormalParameterMappingsType
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getMapping()
    * @generated
    * @ordered
    */
   protected EList<FormalParameterMappingType> mapping;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected FormalParameterMappingsTypeImpl()
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
      return ExtensionsPackage.Literals.FORMAL_PARAMETER_MAPPINGS_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<FormalParameterMappingType> getMapping()
   {
      if (mapping == null)
      {
         mapping = new EObjectContainmentEList<FormalParameterMappingType>(FormalParameterMappingType.class, this, ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING);
      }
      return mapping;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public DataType getMappedData(FormalParameterType formalParameter)
   {
      if (formalParameter != null && formalParameter.getId() != null)
      {
         for (FormalParameterMappingType parameterMapping : getMapping())
         {
            FormalParameterType parameter = parameterMapping.getParameter();
            if (parameter != null && formalParameter.getId().equals(parameter.getId()))
            {
                return parameterMapping.getData(); 
            }       
         }
      }
      return null;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    */
   public void setMappedData(FormalParameterType formalParameter, DataType data)
   {
      if (formalParameter == null)
      {
         return;
      }
      for (FormalParameterMappingType parameterMapping : getMapping())
      {
         if (formalParameter.getId().equals(parameterMapping.getParameter().getId()))
         {
            if (data == null)
            {
               getMapping().remove(parameterMapping);
            }
            else
            {
               parameterMapping.setData(data);
            }
            return;
         }
      }
      
      FormalParameterMappingType mapping = ExtensionsFactory.eINSTANCE.createFormalParameterMappingType();
      mapping.setData(data);
      mapping.setParameter(formalParameter);
      getMapping().add(mapping);
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
         case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
            return ((InternalEList<?>)getMapping()).basicRemove(otherEnd, msgs);
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
         case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
            return getMapping();
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
         case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
            getMapping().clear();
            getMapping().addAll((Collection<? extends FormalParameterMappingType>)newValue);
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
         case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
            getMapping().clear();
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
         case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING:
            return mapping != null && !mapping.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} //FormalParameterMappingsTypeImpl
