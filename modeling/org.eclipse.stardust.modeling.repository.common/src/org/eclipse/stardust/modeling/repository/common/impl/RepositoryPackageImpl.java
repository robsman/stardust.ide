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


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.stardust.modeling.repository.common.Attribute;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.Repository;
import org.eclipse.stardust.modeling.repository.common.RepositoryFactory;
import org.eclipse.stardust.modeling.repository.common.RepositoryPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RepositoryPackageImpl extends EPackageImpl implements RepositoryPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass connectionEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass attributeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass repositoryEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass iObjectDescriptorEClass = null;

   /**
    * Creates an instance of the model <b>Package</b>, registered with
    * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
    * package URI value.
    * <p>Note: the correct way to create the package is via the static
    * factory method {@link #init init()}, which also performs
    * initialization of the package, or returns the registered package,
    * if one already exists.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private RepositoryPackageImpl()
   {
      super(eNS_URI, RepositoryFactory.eINSTANCE);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static boolean isInited = false;

   /**
    * Creates, registers, and initializes the <b>Package</b> for this
    * model, and for any others upon which it depends.  Simple
    * dependencies are satisfied by calling this method on all
    * dependent packages before doing anything else.  This method drives
    * initialization for interdependent packages directly, in parallel
    * with this package, itself.
    * <p>Of this package and its interdependencies, all packages which
    * have not yet been registered by their URI values are first created
    * and registered.  The packages are then initialized in two steps:
    * meta-model objects for all of the packages are created before any
    * are initialized, since one package's meta-model objects may refer to
    * those of another.
    * <p>Invocation of this method will not affect any packages that have
    * already been initialized.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static RepositoryPackage init()
   {
      if (isInited)
         return (RepositoryPackage) EPackage.Registry.INSTANCE
               .getEPackage(RepositoryPackage.eNS_URI);

      // Obtain or create and register package
      RepositoryPackageImpl theRepositoryPackage = (RepositoryPackageImpl) (EPackage.Registry.INSTANCE
            .getEPackage(eNS_URI) instanceof RepositoryPackageImpl
            ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI)
            : new RepositoryPackageImpl());

      isInited = true;

      // Create package meta-data objects
      theRepositoryPackage.createPackageContents();

      // Initialize created meta-data
      theRepositoryPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theRepositoryPackage.freeze();

      return theRepositoryPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getConnection()
   {
      return connectionEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getConnection_Id()
   {
      return (EAttribute) connectionEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getConnection_Name()
   {
      return (EAttribute) connectionEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getConnection_Type()
   {
      return (EAttribute) connectionEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getConnection_Attributes()
   {
      return (EReference) connectionEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getAttribute()
   {
      return attributeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttribute_Name()
   {
      return (EAttribute) attributeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getAttribute_Value()
   {
      return (EAttribute) attributeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getRepository()
   {
      return repositoryEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getRepository_Connection()
   {
      return (EReference) repositoryEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getIObjectDescriptor()
   {
      return iObjectDescriptorEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public RepositoryFactory getRepositoryFactory()
   {
      return (RepositoryFactory) getEFactoryInstance();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isCreated = false;

   /**
    * Creates the meta-model objects for the package.  This method is
    * guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void createPackageContents()
   {
      if (isCreated)
         return;
      isCreated = true;

      // Create classes and their features
      repositoryEClass = createEClass(REPOSITORY);
      createEReference(repositoryEClass, REPOSITORY__CONNECTION);

      connectionEClass = createEClass(CONNECTION);
      createEAttribute(connectionEClass, CONNECTION__ID);
      createEAttribute(connectionEClass, CONNECTION__NAME);
      createEAttribute(connectionEClass, CONNECTION__TYPE);
      createEReference(connectionEClass, CONNECTION__ATTRIBUTES);

      attributeEClass = createEClass(ATTRIBUTE);
      createEAttribute(attributeEClass, ATTRIBUTE__NAME);
      createEAttribute(attributeEClass, ATTRIBUTE__VALUE);

      iObjectDescriptorEClass = createEClass(IOBJECT_DESCRIPTOR);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isInitialized = false;

   /**
    * Complete the initialization of the package and its meta-model.  This
    * method is guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void initializePackageContents()
   {
      if (isInitialized)
         return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes

      // Initialize classes and features; add operations and parameters
      initEClass(repositoryEClass, Repository.class, "Repository", !IS_ABSTRACT, //$NON-NLS-1$
            !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getRepository_Connection(), this.getConnection(), null,
            "connection", null, 0, -1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
            IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
            !IS_DERIVED, IS_ORDERED);

      initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, //$NON-NLS-1$
            !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getConnection_Id(), ecorePackage.getEString(), "id", null, 0, 1, //$NON-NLS-1$
            Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
            !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getConnection_Name(), ecorePackage.getEString(), "name", null, 0, 1, //$NON-NLS-1$
            Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
            !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getConnection_Type(), ecorePackage.getEString(), "type", null, 0, 1, //$NON-NLS-1$
            Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
            !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getConnection_Attributes(), this.getAttribute(), null, "attributes", //$NON-NLS-1$
            null, 0, -1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
            IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
            IS_ORDERED);

      EOperation op = addEOperation(connectionEClass, ecorePackage.getEString(),
            "getAttribute", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      op = addEOperation(connectionEClass, null, "removeAttribute", 0, 1, IS_UNIQUE, //$NON-NLS-1$
            IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      op = addEOperation(connectionEClass, null, "setAttribute", 0, 1, IS_UNIQUE, //$NON-NLS-1$
            IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
      addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      op = addEOperation(connectionEClass, ecorePackage.getEJavaObject(), "getProperty", //$NON-NLS-1$
            0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      op = addEOperation(connectionEClass, null, "removeProperty", 0, 1, IS_UNIQUE, //$NON-NLS-1$
            IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      op = addEOperation(connectionEClass, null, "setProperty", 0, 1, IS_UNIQUE, //$NON-NLS-1$
            IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
      addEParameter(op, ecorePackage.getEJavaObject(), "value", 0, 1, IS_UNIQUE, //$NON-NLS-1$
            IS_ORDERED);

      op = addEOperation(connectionEClass, null, "getProperties", 0, 1, IS_UNIQUE, //$NON-NLS-1$
            IS_ORDERED);
      EGenericType g1 = createEGenericType(ecorePackage.getEMap());
      EGenericType g2 = createEGenericType(ecorePackage.getEString());
      g1.getETypeArguments().add(g2);
      g2 = createEGenericType(ecorePackage.getEJavaObject());
      g1.getETypeArguments().add(g2);
      initEOperation(op, g1);

      initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, //$NON-NLS-1$
            !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, //$NON-NLS-1$
            Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
            !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getAttribute_Value(), ecorePackage.getEString(), "value", null, 0, //$NON-NLS-1$
            1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
            !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(iObjectDescriptorEClass, IObjectDescriptor.class, "IObjectDescriptor", //$NON-NLS-1$
            IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEJavaObject(), "getURI", 0, //$NON-NLS-1$
            1, IS_UNIQUE, IS_ORDERED);

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEJavaObject(), "getType", 0, //$NON-NLS-1$
            1, IS_UNIQUE, IS_ORDERED);

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEJavaObject(), "getIcon", 0, //$NON-NLS-1$
            1, IS_UNIQUE, IS_ORDERED);

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEString(), "getLabel", 0, 1, //$NON-NLS-1$
            IS_UNIQUE, IS_ORDERED);

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEString(), "getDescription", //$NON-NLS-1$
            0, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEBooleanObject(),
            "hasChildren", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEJavaObject(),
            "getChildren", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      addEOperation(iObjectDescriptorEClass, ecorePackage.getEBoolean(), "isLazyLoading", //$NON-NLS-1$
            0, 1, IS_UNIQUE, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} //RepositoryPackageImpl
