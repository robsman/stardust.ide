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
package org.eclipse.stardust.model.xpdl.xpdl2.extensions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xsd.XSDPackage;

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
 * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionFactory
 * @model kind="package"
 * @generated
 */
public interface ExtensionPackage extends EPackage {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * The package name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNAME = "extensions"; //$NON-NLS-1$

	/**
    * The package namespace URI.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_URI = "http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions"; //$NON-NLS-1$

	/**
    * The package namespace name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_PREFIX = "ext"; //$NON-NLS-1$

	/**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	ExtensionPackage eINSTANCE = org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtensionPackageImpl.init();

	/**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtendedAnnotationTypeImpl <em>Extended Annotation Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtendedAnnotationTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtensionPackageImpl#getExtendedAnnotationType()
    * @generated
    */
	int EXTENDED_ANNOTATION_TYPE = 0;

	/**
    * The feature id for the '<em><b>Element</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__ELEMENT = XSDPackage.XSD_ANNOTATION__ELEMENT;

	/**
    * The feature id for the '<em><b>Container</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__CONTAINER = XSDPackage.XSD_ANNOTATION__CONTAINER;

	/**
    * The feature id for the '<em><b>Root Container</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__ROOT_CONTAINER = XSDPackage.XSD_ANNOTATION__ROOT_CONTAINER;

	/**
    * The feature id for the '<em><b>Schema</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__SCHEMA = XSDPackage.XSD_ANNOTATION__SCHEMA;

	/**
    * The feature id for the '<em><b>Diagnostics</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__DIAGNOSTICS = XSDPackage.XSD_ANNOTATION__DIAGNOSTICS;

	/**
    * The feature id for the '<em><b>Application Information</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__APPLICATION_INFORMATION = XSDPackage.XSD_ANNOTATION__APPLICATION_INFORMATION;

	/**
    * The feature id for the '<em><b>User Information</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__USER_INFORMATION = XSDPackage.XSD_ANNOTATION__USER_INFORMATION;

	/**
    * The feature id for the '<em><b>Attributes</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE__ATTRIBUTES = XSDPackage.XSD_ANNOTATION__ATTRIBUTES;

	/**
    * The number of structural features of the '<em>Extended Annotation Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ANNOTATION_TYPE_FEATURE_COUNT = XSDPackage.XSD_ANNOTATION_FEATURE_COUNT + 0;


	/**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.LoopDataRefTypeImpl <em>Loop Data Ref Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.LoopDataRefTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtensionPackageImpl#getLoopDataRefType()
    * @generated
    */
   int LOOP_DATA_REF_TYPE = 1;

   /**
    * The feature id for the '<em><b>Input Item Ref</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_DATA_REF_TYPE__INPUT_ITEM_REF = 0;

   /**
    * The feature id for the '<em><b>Output Item Ref</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF = 1;

   /**
    * The feature id for the '<em><b>Loop Counter Ref</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF = 2;

   /**
    * The number of structural features of the '<em>Loop Data Ref Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_DATA_REF_TYPE_FEATURE_COUNT = 3;


   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType <em>Extended Annotation Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Extended Annotation Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType
    * @generated
    */
	EClass getExtendedAnnotationType();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType <em>Loop Data Ref Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Loop Data Ref Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType
    * @generated
    */
   EClass getLoopDataRefType();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getInputItemRef <em>Input Item Ref</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Input Item Ref</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getInputItemRef()
    * @see #getLoopDataRefType()
    * @generated
    */
   EAttribute getLoopDataRefType_InputItemRef();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getOutputItemRef <em>Output Item Ref</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Output Item Ref</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getOutputItemRef()
    * @see #getLoopDataRefType()
    * @generated
    */
   EAttribute getLoopDataRefType_OutputItemRef();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getLoopCounterRef <em>Loop Counter Ref</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Loop Counter Ref</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getLoopCounterRef()
    * @see #getLoopDataRefType()
    * @generated
    */
   EAttribute getLoopDataRefType_LoopCounterRef();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
	ExtensionFactory getExtensionFactory();

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
	interface Literals  {
		/**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtendedAnnotationTypeImpl <em>Extended Annotation Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtendedAnnotationTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtensionPackageImpl#getExtendedAnnotationType()
       * @generated
       */
		EClass EXTENDED_ANNOTATION_TYPE = eINSTANCE.getExtendedAnnotationType();
      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.LoopDataRefTypeImpl <em>Loop Data Ref Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.LoopDataRefTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtensionPackageImpl#getLoopDataRefType()
       * @generated
       */
      EClass LOOP_DATA_REF_TYPE = eINSTANCE.getLoopDataRefType();
      /**
       * The meta object literal for the '<em><b>Input Item Ref</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_DATA_REF_TYPE__INPUT_ITEM_REF = eINSTANCE.getLoopDataRefType_InputItemRef();
      /**
       * The meta object literal for the '<em><b>Output Item Ref</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_DATA_REF_TYPE__OUTPUT_ITEM_REF = eINSTANCE.getLoopDataRefType_OutputItemRef();
      /**
       * The meta object literal for the '<em><b>Loop Counter Ref</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_DATA_REF_TYPE__LOOP_COUNTER_REF = eINSTANCE.getLoopDataRefType_LoopCounterRef();

}

} //ExtensionPackage
