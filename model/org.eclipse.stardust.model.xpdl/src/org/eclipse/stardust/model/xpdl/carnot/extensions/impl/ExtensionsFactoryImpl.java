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


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory;
import org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType;
import org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtensionsFactoryImpl extends EFactoryImpl implements ExtensionsFactory
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static ExtensionsFactory init()
   {
      try
      {
         ExtensionsFactory theExtensionsFactory = (ExtensionsFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.carnot.ag/xpdl/3.1"); 
         if (theExtensionsFactory != null)
         {
            return theExtensionsFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new ExtensionsFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExtensionsFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case ExtensionsPackage.FORMAL_PARAMETER_MAPPING_TYPE: return createFormalParameterMappingType();
         case ExtensionsPackage.FORMAL_PARAMETER_MAPPINGS_TYPE: return createFormalParameterMappingsType();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FormalParameterMappingType createFormalParameterMappingType()
   {
      FormalParameterMappingTypeImpl formalParameterMappingType = new FormalParameterMappingTypeImpl();
      return formalParameterMappingType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FormalParameterMappingsType createFormalParameterMappingsType()
   {
      FormalParameterMappingsTypeImpl formalParameterMappingsType = new FormalParameterMappingsTypeImpl();
      return formalParameterMappingsType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExtensionsPackage getExtensionsPackage()
   {
      return (ExtensionsPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static ExtensionsPackage getPackage()
   {
      return ExtensionsPackage.eINSTANCE;
   }

} //ExtensionsFactoryImpl
