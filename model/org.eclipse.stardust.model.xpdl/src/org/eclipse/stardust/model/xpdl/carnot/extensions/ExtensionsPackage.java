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
package org.eclipse.stardust.model.xpdl.carnot.extensions;

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
 * @see org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsFactory
 * @model kind="package"
 * @generated
 */
public interface ExtensionsPackage extends EPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH";

   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "extensions";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://www.carnot.ag/xpdl/3.1";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "carnot";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   ExtensionsPackage eINSTANCE = org.eclipse.stardust.model.xpdl.carnot.extensions.impl.ExtensionsPackageImpl.init();

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingTypeImpl <em>Formal Parameter Mapping Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingTypeImpl
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.ExtensionsPackageImpl#getFormalParameterMappingType()
    * @generated
    */
   int FORMAL_PARAMETER_MAPPING_TYPE = 0;

   /**
    * The feature id for the '<em><b>Data</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_MAPPING_TYPE__DATA = 0;

   /**
    * The feature id for the '<em><b>Parameter</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_MAPPING_TYPE__PARAMETER = 1;

   /**
    * The number of structural features of the '<em>Formal Parameter Mapping Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_MAPPING_TYPE_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingsTypeImpl <em>Formal Parameter Mappings Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingsTypeImpl
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.ExtensionsPackageImpl#getFormalParameterMappingsType()
    * @generated
    */
   int FORMAL_PARAMETER_MAPPINGS_TYPE = 1;

   /**
    * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING = 0;

   /**
    * The number of structural features of the '<em>Formal Parameter Mappings Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_MAPPINGS_TYPE_FEATURE_COUNT = 1;


   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType <em>Formal Parameter Mapping Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Formal Parameter Mapping Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType
    * @generated
    */
   EClass getFormalParameterMappingType();

   /**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Data</em>'.
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getData()
    * @see #getFormalParameterMappingType()
    * @generated
    */
   EReference getFormalParameterMappingType_Data();

   /**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getParameter <em>Parameter</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Parameter</em>'.
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingType#getParameter()
    * @see #getFormalParameterMappingType()
    * @generated
    */
   EReference getFormalParameterMappingType_Parameter();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType <em>Formal Parameter Mappings Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Formal Parameter Mappings Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType
    * @generated
    */
   EClass getFormalParameterMappingsType();

   /**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType#getMapping <em>Mapping</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Mapping</em>'.
    * @see org.eclipse.stardust.model.xpdl.carnot.extensions.FormalParameterMappingsType#getMapping()
    * @see #getFormalParameterMappingsType()
    * @generated
    */
   EReference getFormalParameterMappingsType_Mapping();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   ExtensionsFactory getExtensionsFactory();

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
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingTypeImpl <em>Formal Parameter Mapping Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingTypeImpl
       * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.ExtensionsPackageImpl#getFormalParameterMappingType()
       * @generated
       */
      EClass FORMAL_PARAMETER_MAPPING_TYPE = eINSTANCE.getFormalParameterMappingType();

      /**
       * The meta object literal for the '<em><b>Data</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference FORMAL_PARAMETER_MAPPING_TYPE__DATA = eINSTANCE.getFormalParameterMappingType_Data();

      /**
       * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference FORMAL_PARAMETER_MAPPING_TYPE__PARAMETER = eINSTANCE.getFormalParameterMappingType_Parameter();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingsTypeImpl <em>Formal Parameter Mappings Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.FormalParameterMappingsTypeImpl
       * @see org.eclipse.stardust.model.xpdl.carnot.extensions.impl.ExtensionsPackageImpl#getFormalParameterMappingsType()
       * @generated
       */
      EClass FORMAL_PARAMETER_MAPPINGS_TYPE = eINSTANCE.getFormalParameterMappingsType();

      /**
       * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference FORMAL_PARAMETER_MAPPINGS_TYPE__MAPPING = eINSTANCE.getFormalParameterMappingsType_Mapping();

   }

} //ExtensionsPackage
