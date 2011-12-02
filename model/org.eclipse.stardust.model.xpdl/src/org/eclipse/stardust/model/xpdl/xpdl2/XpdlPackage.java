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
package org.eclipse.stardust.model.xpdl.xpdl2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory
 * @model kind="package"
 * @generated
 */
public interface XpdlPackage extends EPackage
{
   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard";

   /**
    * The package name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNAME = "xpdl";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_URI = "http://www.wfmc.org/2008/XPDL2.1";

   /**
    * The XPDL 1.0 namespace URI.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    */
    String eNS_URI1 = "http://www.wfmc.org/2002/XPDL1.0";

   /**
    * The XPDL 2.0 namespace URI.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    */
    String eNS_URI2 = "http://www.wfmc.org/2004/XPDL2.0alpha";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_PREFIX = "xpdl";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	XpdlPackage eINSTANCE = org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl.init();

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType <em>Type Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getXpdlTypeType()
    * @generated
    */
	int XPDL_TYPE_TYPE = 15;

   /**
    * The number of structural features of the '<em>Type Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int XPDL_TYPE_TYPE_FEATURE_COUNT = 0;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.BasicTypeTypeImpl <em>Basic Type Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.BasicTypeTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getBasicTypeType()
    * @generated
    */
	int BASIC_TYPE_TYPE = 0;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int BASIC_TYPE_TYPE__TYPE = XPDL_TYPE_TYPE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Basic Type Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int BASIC_TYPE_TYPE_FEATURE_COUNT = XPDL_TYPE_TYPE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl <em>Data Type Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getDataTypeType()
    * @generated
    */
   int DATA_TYPE_TYPE = 1;

   /**
    * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DATA_TYPE_TYPE__BASIC_TYPE = 0;

   /**
    * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DATA_TYPE_TYPE__DECLARED_TYPE = 1;

   /**
    * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DATA_TYPE_TYPE__SCHEMA_TYPE = 2;

   /**
    * The feature id for the '<em><b>External Reference</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DATA_TYPE_TYPE__EXTERNAL_REFERENCE = 3;

   /**
    * The feature id for the '<em><b>Carnot Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DATA_TYPE_TYPE__CARNOT_TYPE = 4;

   /**
    * The number of structural features of the '<em>Data Type Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DATA_TYPE_TYPE_FEATURE_COUNT = 5;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DeclaredTypeTypeImpl <em>Declared Type Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.DeclaredTypeTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getDeclaredTypeType()
    * @generated
    */
	int DECLARED_TYPE_TYPE = 2;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int DECLARED_TYPE_TYPE__ID = XPDL_TYPE_TYPE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Declared Type Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int DECLARED_TYPE_TYPE_FEATURE_COUNT = XPDL_TYPE_TYPE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributesTypeImpl <em>Extended Attributes Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributesTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExtendedAttributesType()
    * @generated
    */
	int EXTENDED_ATTRIBUTES_TYPE = 3;

   /**
    * The feature id for the '<em><b>Extended Attribute</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE = 0;

   /**
    * The number of structural features of the '<em>Extended Attributes Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTES_TYPE_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl <em>Extended Attribute Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExtendedAttributeType()
    * @generated
    */
	int EXTENDED_ATTRIBUTE_TYPE = 4;

   /**
    * The feature id for the '<em><b>Extended Annotation</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION = 0;

   /**
    * The feature id for the '<em><b>Mixed</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTE_TYPE__MIXED = 1;

   /**
    * The feature id for the '<em><b>Group</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTE_TYPE__GROUP = 2;

   /**
    * The feature id for the '<em><b>Any</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTE_TYPE__ANY = 3;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTE_TYPE__NAME = 4;

   /**
    * The feature id for the '<em><b>Value</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTE_TYPE__VALUE = 5;

   /**
    * The number of structural features of the '<em>Extended Attribute Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTENDED_ATTRIBUTE_TYPE_FEATURE_COUNT = 6;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.Extensible <em>Extensible</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.Extensible
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExtensible()
    * @generated
    */
   int EXTENSIBLE = 5;

   /**
    * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTENSIBLE__EXTENDED_ATTRIBUTES = 0;

   /**
    * The number of structural features of the '<em>Extensible</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTENSIBLE_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackagesImpl <em>External Packages</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackagesImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExternalPackages()
    * @generated
    */
   int EXTERNAL_PACKAGES = 6;

   /**
    * The feature id for the '<em><b>External Package</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTERNAL_PACKAGES__EXTERNAL_PACKAGE = 0;

   /**
    * The number of structural features of the '<em>External Packages</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTERNAL_PACKAGES_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl <em>External Package</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExternalPackage()
    * @generated
    */
   int EXTERNAL_PACKAGE = 7;

   /**
    * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTERNAL_PACKAGE__EXTENDED_ATTRIBUTES = EXTENSIBLE__EXTENDED_ATTRIBUTES;

   /**
    * The feature id for the '<em><b>Href</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTERNAL_PACKAGE__HREF = EXTENSIBLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTERNAL_PACKAGE__ID = EXTENSIBLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTERNAL_PACKAGE__NAME = EXTENSIBLE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>External Package</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXTERNAL_PACKAGE_FEATURE_COUNT = EXTENSIBLE_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalReferenceTypeImpl <em>External Reference Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalReferenceTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExternalReferenceType()
    * @generated
    */
	int EXTERNAL_REFERENCE_TYPE = 8;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.SchemaTypeTypeImpl <em>Schema Type Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.SchemaTypeTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getSchemaTypeType()
    * @generated
    */
	int SCHEMA_TYPE_TYPE = 11;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationsTypeImpl <em>Type Declarations Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationsTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeDeclarationsType()
    * @generated
    */
	int TYPE_DECLARATIONS_TYPE = 13;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl <em>Type Declaration Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeDeclarationType()
    * @generated
    */
	int TYPE_DECLARATION_TYPE = 14;

   /**
    * The feature id for the '<em><b>Location</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTERNAL_REFERENCE_TYPE__LOCATION = XPDL_TYPE_TYPE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Namespace</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTERNAL_REFERENCE_TYPE__NAMESPACE = XPDL_TYPE_TYPE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Xref</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTERNAL_REFERENCE_TYPE__XREF = XPDL_TYPE_TYPE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>External Reference Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int EXTERNAL_REFERENCE_TYPE_FEATURE_COUNT = XPDL_TYPE_TYPE_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParameterTypeImpl <em>Formal Parameter Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParameterTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getFormalParameterType()
    * @generated
    */
   int FORMAL_PARAMETER_TYPE = 10;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParametersTypeImpl <em>Formal Parameters Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParametersTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getFormalParametersType()
    * @generated
    */
   int FORMAL_PARAMETERS_TYPE = 9;

   /**
    * The feature id for the '<em><b>Formal Parameter</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER = 0;

   /**
    * The number of structural features of the '<em>Formal Parameters Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETERS_TYPE_FEATURE_COUNT = 1;

   /**
    * The feature id for the '<em><b>Data Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_TYPE__DATA_TYPE = 0;

   /**
    * The feature id for the '<em><b>Description</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_TYPE__DESCRIPTION = 1;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_TYPE__ID = 2;

   /**
    * The feature id for the '<em><b>Mode</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_TYPE__MODE = 3;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_TYPE__NAME = 4;

   /**
    * The number of structural features of the '<em>Formal Parameter Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FORMAL_PARAMETER_TYPE_FEATURE_COUNT = 5;

   /**
    * The feature id for the '<em><b>Schema</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int SCHEMA_TYPE_TYPE__SCHEMA = XPDL_TYPE_TYPE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Schema Type Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int SCHEMA_TYPE_TYPE_FEATURE_COUNT = XPDL_TYPE_TYPE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ScriptTypeImpl <em>Script Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ScriptTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getScriptType()
    * @generated
    */
	int SCRIPT_TYPE = 12;

   /**
    * The feature id for the '<em><b>Grammar</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int SCRIPT_TYPE__GRAMMAR = 0;

   /**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int SCRIPT_TYPE__TYPE = 1;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int SCRIPT_TYPE__VERSION = 2;

   /**
    * The number of structural features of the '<em>Script Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int SCRIPT_TYPE_FEATURE_COUNT = 3;

   /**
    * The feature id for the '<em><b>Type Declaration</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION = 0;

   /**
    * The number of structural features of the '<em>Type Declarations Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATIONS_TYPE_FEATURE_COUNT = 1;

   /**
    * The feature id for the '<em><b>Extended Attributes</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__EXTENDED_ATTRIBUTES = EXTENSIBLE__EXTENDED_ATTRIBUTES;

   /**
    * The feature id for the '<em><b>Basic Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__BASIC_TYPE = EXTENSIBLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Declared Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__DECLARED_TYPE = EXTENSIBLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Schema Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__SCHEMA_TYPE = EXTENSIBLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>External Reference</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE = EXTENSIBLE_FEATURE_COUNT + 3;

   /**
    * The feature id for the '<em><b>Description</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__DESCRIPTION = EXTENSIBLE_FEATURE_COUNT + 4;

   /**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__ID = EXTENSIBLE_FEATURE_COUNT + 5;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE__NAME = EXTENSIBLE_FEATURE_COUNT + 6;

   /**
    * The number of structural features of the '<em>Type Declaration Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TYPE_DECLARATION_TYPE_FEATURE_COUNT = EXTENSIBLE_FEATURE_COUNT + 7;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ModeType <em>Mode Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getModeType()
    * @generated
    */
   int MODE_TYPE = 16;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeType <em>Type Type</em>}' enum.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeType()
    * @generated
    */
	int TYPE_TYPE = 17;

   /**
    * The meta object id for the '<em>Mode Type Object</em>' data type.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getModeTypeObject()
    * @generated
    */
   int MODE_TYPE_OBJECT = 18;

   /**
    * The meta object id for the '<em>Type Type Object</em>' data type.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeTypeObject()
    * @generated
    */
	int TYPE_TYPE_OBJECT = 19;


   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType <em>Basic Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Basic Type Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType
    * @generated
    */
	EClass getBasicTypeType();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType#getType()
    * @see #getBasicTypeType()
    * @generated
    */
	EAttribute getBasicTypeType_Type();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType <em>Data Type Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Data Type Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType
    * @generated
    */
   EClass getDataTypeType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getBasicType <em>Basic Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Basic Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getBasicType()
    * @see #getDataTypeType()
    * @generated
    */
   EReference getDataTypeType_BasicType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getDeclaredType <em>Declared Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Declared Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getDeclaredType()
    * @see #getDataTypeType()
    * @generated
    */
   EReference getDataTypeType_DeclaredType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getSchemaType <em>Schema Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Schema Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getSchemaType()
    * @see #getDataTypeType()
    * @generated
    */
   EReference getDataTypeType_SchemaType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getExternalReference <em>External Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>External Reference</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getExternalReference()
    * @see #getDataTypeType()
    * @generated
    */
   EReference getDataTypeType_ExternalReference();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getCarnotType <em>Carnot Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Carnot Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType#getCarnotType()
    * @see #getDataTypeType()
    * @generated
    */
   EAttribute getDataTypeType_CarnotType();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType <em>Declared Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Declared Type Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType
    * @generated
    */
	EClass getDeclaredTypeType();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType#getId()
    * @see #getDeclaredTypeType()
    * @generated
    */
	EAttribute getDeclaredTypeType_Id();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType <em>Extended Attributes Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Extended Attributes Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType
    * @generated
    */
	EClass getExtendedAttributesType();

   /**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType#getExtendedAttribute <em>Extended Attribute</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Extended Attribute</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType#getExtendedAttribute()
    * @see #getExtendedAttributesType()
    * @generated
    */
	EReference getExtendedAttributesType_ExtendedAttribute();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType <em>Extended Attribute Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Extended Attribute Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType
    * @generated
    */
	EClass getExtendedAttributeType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getExtendedAnnotation <em>Extended Annotation</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Extended Annotation</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getExtendedAnnotation()
    * @see #getExtendedAttributeType()
    * @generated
    */
	EReference getExtendedAttributeType_ExtendedAnnotation();

   /**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getMixed <em>Mixed</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Mixed</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getMixed()
    * @see #getExtendedAttributeType()
    * @generated
    */
	EAttribute getExtendedAttributeType_Mixed();

   /**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getGroup <em>Group</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Group</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getGroup()
    * @see #getExtendedAttributeType()
    * @generated
    */
	EAttribute getExtendedAttributeType_Group();

   /**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getAny <em>Any</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Any</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getAny()
    * @see #getExtendedAttributeType()
    * @generated
    */
	EAttribute getExtendedAttributeType_Any();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getName()
    * @see #getExtendedAttributeType()
    * @generated
    */
	EAttribute getExtendedAttributeType_Name();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getValue <em>Value</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Value</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType#getValue()
    * @see #getExtendedAttributeType()
    * @generated
    */
	EAttribute getExtendedAttributeType_Value();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.Extensible <em>Extensible</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Extensible</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.Extensible
    * @generated
    */
   EClass getExtensible();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.Extensible#getExtendedAttributes <em>Extended Attributes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Extended Attributes</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.Extensible#getExtendedAttributes()
    * @see #getExtensible()
    * @generated
    */
   EReference getExtensible_ExtendedAttributes();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages <em>External Packages</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>External Packages</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages
    * @generated
    */
   EClass getExternalPackages();

   /**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages#getExternalPackage <em>External Package</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>External Package</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages#getExternalPackage()
    * @see #getExternalPackages()
    * @generated
    */
   EReference getExternalPackages_ExternalPackage();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage <em>External Package</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>External Package</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage
    * @generated
    */
   EClass getExternalPackage();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getHref <em>Href</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Href</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getHref()
    * @see #getExternalPackage()
    * @generated
    */
   EAttribute getExternalPackage_Href();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getId()
    * @see #getExternalPackage()
    * @generated
    */
   EAttribute getExternalPackage_Id();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage#getName()
    * @see #getExternalPackage()
    * @generated
    */
   EAttribute getExternalPackage_Name();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType <em>External Reference Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>External Reference Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType
    * @generated
    */
	EClass getExternalReferenceType();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getLocation <em>Location</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Location</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getLocation()
    * @see #getExternalReferenceType()
    * @generated
    */
	EAttribute getExternalReferenceType_Location();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getNamespace <em>Namespace</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Namespace</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getNamespace()
    * @see #getExternalReferenceType()
    * @generated
    */
	EAttribute getExternalReferenceType_Namespace();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getXref <em>Xref</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Xref</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType#getXref()
    * @see #getExternalReferenceType()
    * @generated
    */
	EAttribute getExternalReferenceType_Xref();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType <em>Formal Parameter Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Formal Parameter Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType
    * @generated
    */
   EClass getFormalParameterType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDataType <em>Data Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Data Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDataType()
    * @see #getFormalParameterType()
    * @generated
    */
   EReference getFormalParameterType_DataType();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDescription <em>Description</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Description</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getDescription()
    * @see #getFormalParameterType()
    * @generated
    */
   EAttribute getFormalParameterType_Description();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getId()
    * @see #getFormalParameterType()
    * @generated
    */
   EAttribute getFormalParameterType_Id();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getMode <em>Mode</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Mode</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getMode()
    * @see #getFormalParameterType()
    * @generated
    */
   EAttribute getFormalParameterType_Mode();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType#getName()
    * @see #getFormalParameterType()
    * @generated
    */
   EAttribute getFormalParameterType_Name();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType <em>Formal Parameters Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Formal Parameters Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType
    * @generated
    */
   EClass getFormalParametersType();

   /**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType#getFormalParameter <em>Formal Parameter</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Formal Parameter</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType#getFormalParameter()
    * @see #getFormalParametersType()
    * @generated
    */
   EReference getFormalParametersType_FormalParameter();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType <em>Schema Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Schema Type Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType
    * @generated
    */
	EClass getSchemaTypeType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType#getSchema <em>Schema</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Schema</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType#getSchema()
    * @see #getSchemaTypeType()
    * @generated
    */
	EReference getSchemaTypeType_Schema();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType <em>Script Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Script Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ScriptType
    * @generated
    */
	EClass getScriptType();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getGrammar <em>Grammar</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Grammar</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getGrammar()
    * @see #getScriptType()
    * @generated
    */
	EAttribute getScriptType_Grammar();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getType()
    * @see #getScriptType()
    * @generated
    */
	EAttribute getScriptType_Type();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getVersion <em>Version</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Version</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getVersion()
    * @see #getScriptType()
    * @generated
    */
	EAttribute getScriptType_Version();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType <em>Type Declarations Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Type Declarations Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType
    * @generated
    */
	EClass getTypeDeclarationsType();

   /**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType#getTypeDeclaration <em>Type Declaration</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Type Declaration</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType#getTypeDeclaration()
    * @see #getTypeDeclarationsType()
    * @generated
    */
	EReference getTypeDeclarationsType_TypeDeclaration();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType <em>Type Declaration Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Type Declaration Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType
    * @generated
    */
	EClass getTypeDeclarationType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getBasicType <em>Basic Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Basic Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getBasicType()
    * @see #getTypeDeclarationType()
    * @generated
    */
	EReference getTypeDeclarationType_BasicType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDeclaredType <em>Declared Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Declared Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDeclaredType()
    * @see #getTypeDeclarationType()
    * @generated
    */
	EReference getTypeDeclarationType_DeclaredType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getSchemaType <em>Schema Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Schema Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getSchemaType()
    * @see #getTypeDeclarationType()
    * @generated
    */
	EReference getTypeDeclarationType_SchemaType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getExternalReference <em>External Reference</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>External Reference</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getExternalReference()
    * @see #getTypeDeclarationType()
    * @generated
    */
	EReference getTypeDeclarationType_ExternalReference();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDescription <em>Description</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Description</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getDescription()
    * @see #getTypeDeclarationType()
    * @generated
    */
	EAttribute getTypeDeclarationType_Description();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getId()
    * @see #getTypeDeclarationType()
    * @generated
    */
	EAttribute getTypeDeclarationType_Id();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType#getName()
    * @see #getTypeDeclarationType()
    * @generated
    */
	EAttribute getTypeDeclarationType_Name();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType <em>Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Type Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType
    * @generated
    */
	EClass getXpdlTypeType();

   /**
    * Returns the meta object for enum '{@link org.eclipse.stardust.model.xpdl.xpdl2.ModeType <em>Mode Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Mode Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @generated
    */
   EEnum getModeType();

   /**
    * Returns the meta object for enum '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeType <em>Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Type Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @generated
    */
	EEnum getTypeType();

   /**
    * Returns the meta object for data type '{@link org.eclipse.stardust.model.xpdl.xpdl2.ModeType <em>Mode Type Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for data type '<em>Mode Type Object</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @model instanceClass="org.eclipse.stardust.model.xpdl.xpdl2.ModeType"
    *        extendedMetaData="name='Mode_._type:Object' baseType='Mode_._type'"
    * @generated
    */
   EDataType getModeTypeObject();

   /**
    * Returns the meta object for data type '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeType <em>Type Type Object</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for data type '<em>Type Type Object</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @model instanceClass="org.eclipse.stardust.model.xpdl.xpdl2.TypeType"
    *        extendedMetaData="name='Type_._type:Object' baseType='Type_._type'"
    * @generated
    */
	EDataType getTypeTypeObject();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
	XpdlFactory getXpdlFactory();

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
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.BasicTypeTypeImpl <em>Basic Type Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.BasicTypeTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getBasicTypeType()
       * @generated
       */
		EClass BASIC_TYPE_TYPE = eINSTANCE.getBasicTypeType();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute BASIC_TYPE_TYPE__TYPE = eINSTANCE.getBasicTypeType_Type();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl <em>Data Type Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.DataTypeTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getDataTypeType()
       * @generated
       */
      EClass DATA_TYPE_TYPE = eINSTANCE.getDataTypeType();

      /**
       * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DATA_TYPE_TYPE__BASIC_TYPE = eINSTANCE.getDataTypeType_BasicType();

      /**
       * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DATA_TYPE_TYPE__DECLARED_TYPE = eINSTANCE.getDataTypeType_DeclaredType();

      /**
       * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DATA_TYPE_TYPE__SCHEMA_TYPE = eINSTANCE.getDataTypeType_SchemaType();

      /**
       * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DATA_TYPE_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getDataTypeType_ExternalReference();

      /**
       * The meta object literal for the '<em><b>Carnot Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute DATA_TYPE_TYPE__CARNOT_TYPE = eINSTANCE.getDataTypeType_CarnotType();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.DeclaredTypeTypeImpl <em>Declared Type Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.DeclaredTypeTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getDeclaredTypeType()
       * @generated
       */
		EClass DECLARED_TYPE_TYPE = eINSTANCE.getDeclaredTypeType();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute DECLARED_TYPE_TYPE__ID = eINSTANCE.getDeclaredTypeType_Id();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributesTypeImpl <em>Extended Attributes Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributesTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExtendedAttributesType()
       * @generated
       */
		EClass EXTENDED_ATTRIBUTES_TYPE = eINSTANCE.getExtendedAttributesType();

      /**
       * The meta object literal for the '<em><b>Extended Attribute</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE = eINSTANCE.getExtendedAttributesType_ExtendedAttribute();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl <em>Extended Attribute Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributeTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExtendedAttributeType()
       * @generated
       */
		EClass EXTENDED_ATTRIBUTE_TYPE = eINSTANCE.getExtendedAttributeType();

      /**
       * The meta object literal for the '<em><b>Extended Annotation</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION = eINSTANCE.getExtendedAttributeType_ExtendedAnnotation();

      /**
       * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__MIXED = eINSTANCE.getExtendedAttributeType_Mixed();

      /**
       * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__GROUP = eINSTANCE.getExtendedAttributeType_Group();

      /**
       * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__ANY = eINSTANCE.getExtendedAttributeType_Any();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__NAME = eINSTANCE.getExtendedAttributeType_Name();

      /**
       * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTENDED_ATTRIBUTE_TYPE__VALUE = eINSTANCE.getExtendedAttributeType_Value();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.Extensible <em>Extensible</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.Extensible
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExtensible()
       * @generated
       */
      EClass EXTENSIBLE = eINSTANCE.getExtensible();

      /**
       * The meta object literal for the '<em><b>Extended Attributes</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference EXTENSIBLE__EXTENDED_ATTRIBUTES = eINSTANCE.getExtensible_ExtendedAttributes();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackagesImpl <em>External Packages</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackagesImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExternalPackages()
       * @generated
       */
      EClass EXTERNAL_PACKAGES = eINSTANCE.getExternalPackages();

      /**
       * The meta object literal for the '<em><b>External Package</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference EXTERNAL_PACKAGES__EXTERNAL_PACKAGE = eINSTANCE.getExternalPackages_ExternalPackage();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl <em>External Package</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalPackageImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExternalPackage()
       * @generated
       */
      EClass EXTERNAL_PACKAGE = eINSTANCE.getExternalPackage();

      /**
       * The meta object literal for the '<em><b>Href</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXTERNAL_PACKAGE__HREF = eINSTANCE.getExternalPackage_Href();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXTERNAL_PACKAGE__ID = eINSTANCE.getExternalPackage_Id();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXTERNAL_PACKAGE__NAME = eINSTANCE.getExternalPackage_Name();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalReferenceTypeImpl <em>External Reference Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExternalReferenceTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExternalReferenceType()
       * @generated
       */
		EClass EXTERNAL_REFERENCE_TYPE = eINSTANCE.getExternalReferenceType();

      /**
       * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTERNAL_REFERENCE_TYPE__LOCATION = eINSTANCE.getExternalReferenceType_Location();

      /**
       * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTERNAL_REFERENCE_TYPE__NAMESPACE = eINSTANCE.getExternalReferenceType_Namespace();

      /**
       * The meta object literal for the '<em><b>Xref</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute EXTERNAL_REFERENCE_TYPE__XREF = eINSTANCE.getExternalReferenceType_Xref();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParameterTypeImpl <em>Formal Parameter Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParameterTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getFormalParameterType()
       * @generated
       */
      EClass FORMAL_PARAMETER_TYPE = eINSTANCE.getFormalParameterType();

      /**
       * The meta object literal for the '<em><b>Data Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference FORMAL_PARAMETER_TYPE__DATA_TYPE = eINSTANCE.getFormalParameterType_DataType();

      /**
       * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute FORMAL_PARAMETER_TYPE__DESCRIPTION = eINSTANCE.getFormalParameterType_Description();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute FORMAL_PARAMETER_TYPE__ID = eINSTANCE.getFormalParameterType_Id();

      /**
       * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute FORMAL_PARAMETER_TYPE__MODE = eINSTANCE.getFormalParameterType_Mode();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute FORMAL_PARAMETER_TYPE__NAME = eINSTANCE.getFormalParameterType_Name();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParametersTypeImpl <em>Formal Parameters Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParametersTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getFormalParametersType()
       * @generated
       */
      EClass FORMAL_PARAMETERS_TYPE = eINSTANCE.getFormalParametersType();

      /**
       * The meta object literal for the '<em><b>Formal Parameter</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER = eINSTANCE.getFormalParametersType_FormalParameter();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.SchemaTypeTypeImpl <em>Schema Type Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.SchemaTypeTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getSchemaTypeType()
       * @generated
       */
		EClass SCHEMA_TYPE_TYPE = eINSTANCE.getSchemaTypeType();

      /**
       * The meta object literal for the '<em><b>Schema</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference SCHEMA_TYPE_TYPE__SCHEMA = eINSTANCE.getSchemaTypeType_Schema();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ScriptTypeImpl <em>Script Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ScriptTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getScriptType()
       * @generated
       */
		EClass SCRIPT_TYPE = eINSTANCE.getScriptType();

      /**
       * The meta object literal for the '<em><b>Grammar</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute SCRIPT_TYPE__GRAMMAR = eINSTANCE.getScriptType_Grammar();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute SCRIPT_TYPE__TYPE = eINSTANCE.getScriptType_Type();

      /**
       * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute SCRIPT_TYPE__VERSION = eINSTANCE.getScriptType_Version();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationsTypeImpl <em>Type Declarations Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationsTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeDeclarationsType()
       * @generated
       */
		EClass TYPE_DECLARATIONS_TYPE = eINSTANCE.getTypeDeclarationsType();

      /**
       * The meta object literal for the '<em><b>Type Declaration</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION = eINSTANCE.getTypeDeclarationsType_TypeDeclaration();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl <em>Type Declaration Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeDeclarationType()
       * @generated
       */
		EClass TYPE_DECLARATION_TYPE = eINSTANCE.getTypeDeclarationType();

      /**
       * The meta object literal for the '<em><b>Basic Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TYPE_DECLARATION_TYPE__BASIC_TYPE = eINSTANCE.getTypeDeclarationType_BasicType();

      /**
       * The meta object literal for the '<em><b>Declared Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TYPE_DECLARATION_TYPE__DECLARED_TYPE = eINSTANCE.getTypeDeclarationType_DeclaredType();

      /**
       * The meta object literal for the '<em><b>Schema Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TYPE_DECLARATION_TYPE__SCHEMA_TYPE = eINSTANCE.getTypeDeclarationType_SchemaType();

      /**
       * The meta object literal for the '<em><b>External Reference</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE = eINSTANCE.getTypeDeclarationType_ExternalReference();

      /**
       * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TYPE_DECLARATION_TYPE__DESCRIPTION = eINSTANCE.getTypeDeclarationType_Description();

      /**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TYPE_DECLARATION_TYPE__ID = eINSTANCE.getTypeDeclarationType_Id();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TYPE_DECLARATION_TYPE__NAME = eINSTANCE.getTypeDeclarationType_Name();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType <em>Type Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getXpdlTypeType()
       * @generated
       */
		EClass XPDL_TYPE_TYPE = eINSTANCE.getXpdlTypeType();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ModeType <em>Mode Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getModeType()
       * @generated
       */
      EEnum MODE_TYPE = eINSTANCE.getModeType();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeType <em>Type Type</em>}' enum.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeType()
       * @generated
       */
		EEnum TYPE_TYPE = eINSTANCE.getTypeType();

      /**
       * The meta object literal for the '<em>Mode Type Object</em>' data type.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getModeTypeObject()
       * @generated
       */
      EDataType MODE_TYPE_OBJECT = eINSTANCE.getModeTypeObject();

      /**
       * The meta object literal for the '<em>Type Type Object</em>' data type.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeTypeObject()
       * @generated
       */
		EDataType TYPE_TYPE_OBJECT = eINSTANCE.getTypeTypeObject();

	}

} //XpdlPackage
