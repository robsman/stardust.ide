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
package org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl;




import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType;
import org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl;
import org.eclipse.xsd.XSDPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtensionPackageImpl extends EPackageImpl implements ExtensionPackage {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass extendedAnnotationTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass loopDataRefTypeEClass = null;

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage#eNS_URI
    * @see #init()
    * @generated
    */
	private ExtensionPackageImpl() {
      super(eNS_URI, ExtensionFactory.eINSTANCE);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private static boolean isInited = false;

	/**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    *
    * <p>This method is used to initialize {@link ExtensionPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc -->
	* <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
	public static ExtensionPackage init() {
      if (isInited) return (ExtensionPackage)EPackage.Registry.INSTANCE.getEPackage(ExtensionPackage.eNS_URI);

      // Obtain or create and register package
      ExtensionPackageImpl theExtensionPackage = (ExtensionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExtensionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExtensionPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      XSDPackage.eINSTANCE.eClass();
      XMLTypePackage.eINSTANCE.eClass();

      // Obtain or create and register interdependencies
      XpdlPackageImpl theXpdlPackage = (XpdlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI) instanceof XpdlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI) : XpdlPackage.eINSTANCE);

      // Create package meta-data objects
      theExtensionPackage.createPackageContents();
      theXpdlPackage.createPackageContents();

      // Initialize created meta-data
      theExtensionPackage.initializePackageContents();
      theXpdlPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theExtensionPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(ExtensionPackage.eNS_URI, theExtensionPackage);
      return theExtensionPackage;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getExtendedAnnotationType() {
      return extendedAnnotationTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getLoopDataRefType()
   {
      return loopDataRefTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopDataRefType_InputItemRef()
   {
      return (EAttribute)loopDataRefTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopDataRefType_OutputItemRef()
   {
      return (EAttribute)loopDataRefTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopDataRefType_LoopCounterRef()
   {
      return (EAttribute)loopDataRefTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ExtensionFactory getExtensionFactory() {
      return (ExtensionFactory)getEFactoryInstance();
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
	public void createPackageContents() {
      if (isCreated) return;
      isCreated = true;

      // Create classes and their features
      extendedAnnotationTypeEClass = createEClass(EXTENDED_ANNOTATION_TYPE);

      loopDataRefTypeEClass = createEClass(LOOP_DATA_REF_TYPE);
      createEAttribute(loopDataRefTypeEClass, LOOP_DATA_REF_TYPE__INPUT_ITEM_REF);
      createEAttribute(loopDataRefTypeEClass, LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF);
      createEAttribute(loopDataRefTypeEClass, LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF);
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
	public void initializePackageContents() {
      if (isInitialized) return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Obtain other dependent packages
      XSDPackage theXSDPackage = (XSDPackage)EPackage.Registry.INSTANCE.getEPackage(XSDPackage.eNS_URI);
      XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      extendedAnnotationTypeEClass.getESuperTypes().add(theXSDPackage.getXSDAnnotation());

      // Initialize classes and features; add operations and parameters
      initEClass(extendedAnnotationTypeEClass, ExtendedAnnotationType.class, "ExtendedAnnotationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(loopDataRefTypeEClass, LoopDataRefType.class, "LoopDataRefType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getLoopDataRefType_InputItemRef(), theXMLTypePackage.getString(), "inputItemRef", null, 0, 1, LoopDataRefType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLoopDataRefType_OutputItemRef(), theXMLTypePackage.getString(), "outputItemRef", null, 0, 1, LoopDataRefType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLoopDataRefType_LoopCounterRef(), theXMLTypePackage.getString(), "loopCounterRef", null, 0, 1, LoopDataRefType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create annotations
      // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
      createExtendedMetaDataAnnotations();
   }

	/**
    * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected void createExtendedMetaDataAnnotations() {
      String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
      addAnnotation
        (extendedAnnotationTypeEClass, 
         source, 
         new String[] 
         {
          "name", "ExternalAnnotation_._type",
          "kind", "empty"
         });
      addAnnotation
        (loopDataRefTypeEClass,
         source,
         new String[]
         {
          "name", "LoopDataRef_._type",
          "kind", "elementOnly"
         });
      addAnnotation
        (getLoopDataRefType_InputItemRef(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "InputItemRef",
          "namespace", "##targetNamespace"
         });
      addAnnotation
        (getLoopDataRefType_OutputItemRef(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "OutputItemRef",
          "namespace", "##targetNamespace"
         });
      addAnnotation
        (getLoopDataRefType_LoopCounterRef(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "LoopCounterRef",
          "namespace", "##targetNamespace"
         });
   }

} //ExtensionPackageImpl
