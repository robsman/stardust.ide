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
package org.eclipse.stardust.modeling.repository.common;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.repository.common.RepositoryFactory
 * @model kind="package"
 * @generated
 */
public interface RepositoryPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "repository"; //$NON-NLS-1$

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://www.carnot.ag/repository/4.3"; //$NON-NLS-1$

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "cnx"; //$NON-NLS-1$

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   RepositoryPackage eINSTANCE = org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl
         .init();

   /**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl <em>Connection</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl
    * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getConnection()
    * @generated
    */
   int CONNECTION = 1;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.repository.common.impl.AttributeImpl <em>Attribute</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.repository.common.impl.AttributeImpl
    * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getAttribute()
    * @generated
    */
   int ATTRIBUTE = 2;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.repository.common.impl.RepositoryImpl <em>Repository</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryImpl
    * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getRepository()
    * @generated
    */
   int REPOSITORY = 0;

   /**
    * The feature id for the '<em><b>Connection</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int REPOSITORY__CONNECTION = 0;

   /**
    * The number of structural features of the '<em>Repository</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int REPOSITORY_FEATURE_COUNT = 1;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int CONNECTION__ID = 0;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int CONNECTION__NAME = 1;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int CONNECTION__TYPE = 2;

   /**
    * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int CONNECTION__ATTRIBUTES = 3;

   /**
    * The number of structural features of the '<em>Connection</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int CONNECTION_FEATURE_COUNT = 4;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ATTRIBUTE__NAME = 0;

   /**
    * The feature id for the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ATTRIBUTE__VALUE = 1;

   /**
    * The number of structural features of the '<em>Attribute</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int ATTRIBUTE_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.repository.common.IObjectDescriptor <em>IObject Descriptor</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.repository.common.IObjectDescriptor
    * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getIObjectDescriptor()
    * @generated
    */
   int IOBJECT_DESCRIPTOR = 3;

   /**
    * The number of structural features of the '<em>IObject Descriptor</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int IOBJECT_DESCRIPTOR_FEATURE_COUNT = 0;

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.repository.common.Connection <em>Connection</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Connection</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Connection
    * @generated
    */
   EClass getConnection();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.repository.common.Connection#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Connection#getId()
    * @see #getConnection()
    * @generated
    */
   EAttribute getConnection_Id();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.repository.common.Connection#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Connection#getName()
    * @see #getConnection()
    * @generated
    */
   EAttribute getConnection_Name();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.repository.common.Connection#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Type</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Connection#getType()
    * @see #getConnection()
    * @generated
    */
   EAttribute getConnection_Type();

   /**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.modeling.repository.common.Connection#getAttributes <em>Attributes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Attributes</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Connection#getAttributes()
    * @see #getConnection()
    * @generated
    */
   EReference getConnection_Attributes();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.repository.common.Attribute <em>Attribute</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Attribute</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Attribute
    * @generated
    */
   EClass getAttribute();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.repository.common.Attribute#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Attribute#getName()
    * @see #getAttribute()
    * @generated
    */
   EAttribute getAttribute_Name();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.repository.common.Attribute#getValue <em>Value</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Value</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Attribute#getValue()
    * @see #getAttribute()
    * @generated
    */
   EAttribute getAttribute_Value();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.repository.common.Repository <em>Repository</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Repository</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Repository
    * @generated
    */
   EClass getRepository();

   /**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.modeling.repository.common.Repository#getConnection <em>Connection</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Connection</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.Repository#getConnection()
    * @see #getRepository()
    * @generated
    */
   EReference getRepository_Connection();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.repository.common.IObjectDescriptor <em>IObject Descriptor</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>IObject Descriptor</em>'.
    * @see org.eclipse.stardust.modeling.repository.common.IObjectDescriptor
    * @generated
    */
   EClass getIObjectDescriptor();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   RepositoryFactory getRepositoryFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * @generated
    */
   interface Literals
   {
      /**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.repository.common.impl.RepositoryImpl <em>Repository</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryImpl
       * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getRepository()
       * @generated
       */
      EClass REPOSITORY = eINSTANCE.getRepository();

      /**
       * The meta object literal for the '<em><b>Connection</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference REPOSITORY__CONNECTION = eINSTANCE.getRepository_Connection();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl <em>Connection</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.repository.common.impl.ConnectionImpl
       * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getConnection()
       * @generated
       */
      EClass CONNECTION = eINSTANCE.getConnection();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute CONNECTION__ID = eINSTANCE.getConnection_Id();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute CONNECTION__NAME = eINSTANCE.getConnection_Name();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute CONNECTION__TYPE = eINSTANCE.getConnection_Type();

      /**
       * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference CONNECTION__ATTRIBUTES = eINSTANCE.getConnection_Attributes();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.repository.common.impl.AttributeImpl <em>Attribute</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.repository.common.impl.AttributeImpl
       * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getAttribute()
       * @generated
       */
      EClass ATTRIBUTE = eINSTANCE.getAttribute();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute ATTRIBUTE__NAME = eINSTANCE.getAttribute_Name();

      /**
       * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute ATTRIBUTE__VALUE = eINSTANCE.getAttribute_Value();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.repository.common.IObjectDescriptor <em>IObject Descriptor</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.repository.common.IObjectDescriptor
       * @see org.eclipse.stardust.modeling.repository.common.impl.RepositoryPackageImpl#getIObjectDescriptor()
       * @generated
       */
      EClass IOBJECT_DESCRIPTOR = eINSTANCE.getIObjectDescriptor();

   }

} //RepositoryPackage
