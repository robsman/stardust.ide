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
package org.eclipse.stardust.modeling.repository.common.impl;

import java.text.MessageFormat;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.stardust.modeling.repository.common.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RepositoryFactoryImpl extends EFactoryImpl implements RepositoryFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static RepositoryFactory init()
   {
      try
      {
         RepositoryFactory theRepositoryFactory = (RepositoryFactory) EPackage.Registry.INSTANCE
               .getEFactory("http://www.carnot.ag/repository/4.3"); //$NON-NLS-1$
         if (theRepositoryFactory != null)
         {
            return theRepositoryFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new RepositoryFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public RepositoryFactoryImpl()
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
      case RepositoryPackage.REPOSITORY:
         return createRepository();
      case RepositoryPackage.CONNECTION:
         return createConnection();
      case RepositoryPackage.ATTRIBUTE:
         return createAttribute();
      default:
			throw new IllegalArgumentException(MessageFormat.format(
					Repository_Messages.EXC_IS_NOT_VALID_CLASSIFIER,
					new Object[] { eClass.getName() }));
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Connection createConnection()
   {
      ConnectionImpl connection = new ConnectionImpl();
      return connection;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Attribute createAttribute()
   {
      AttributeImpl attribute = new AttributeImpl();
      return attribute;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Repository createRepository()
   {
      RepositoryImpl repository = new RepositoryImpl();
      return repository;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public RepositoryPackage getRepositoryPackage()
   {
      return (RepositoryPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static RepositoryPackage getPackage()
   {
      return RepositoryPackage.eINSTANCE;
   }
} //RepositoryFactoryImpl
