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
	String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * The package name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNAME = "xpdl2"; //$NON-NLS-1$

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_URI = "http://www.wfmc.org/2008/XPDL2.1"; //$NON-NLS-1$

   /**
    * The XPDL 1.0 namespace URI.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    */
    String eNS_URI1 = "http://www.wfmc.org/2002/XPDL1.0"; //$NON-NLS-1$

   /**
    * The XPDL 2.0 namespace URI.
    * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
    */
    String eNS_URI2 = "http://www.wfmc.org/2004/XPDL2.0alpha"; //$NON-NLS-1$

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_PREFIX = "xpdl"; //$NON-NLS-1$

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
	int XPDL_TYPE_TYPE = 19;

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
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl <em>Expression Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExpressionType()
    * @generated
    */
   int EXPRESSION_TYPE = 3;

   /**
    * The feature id for the '<em><b>Mixed</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXPRESSION_TYPE__MIXED = 0;

   /**
    * The feature id for the '<em><b>Group</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXPRESSION_TYPE__GROUP = 1;

   /**
    * The feature id for the '<em><b>Any</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXPRESSION_TYPE__ANY = 2;

   /**
    * The feature id for the '<em><b>Script Grammar</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXPRESSION_TYPE__SCRIPT_GRAMMAR = 3;

   /**
    * The feature id for the '<em><b>Script Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXPRESSION_TYPE__SCRIPT_TYPE = 4;

   /**
    * The feature id for the '<em><b>Script Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXPRESSION_TYPE__SCRIPT_VERSION = 5;

   /**
    * The number of structural features of the '<em>Expression Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int EXPRESSION_TYPE_FEATURE_COUNT = 6;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributesTypeImpl <em>Extended Attributes Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExtendedAttributesTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExtendedAttributesType()
    * @generated
    */
	int EXTENDED_ATTRIBUTES_TYPE = 4;

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
	int EXTENDED_ATTRIBUTE_TYPE = 5;

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
   int EXTENSIBLE = 6;

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
   int EXTERNAL_PACKAGES = 7;

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
   int EXTERNAL_PACKAGE = 8;

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
	int EXTERNAL_REFERENCE_TYPE = 9;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.SchemaTypeTypeImpl <em>Schema Type Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.SchemaTypeTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getSchemaTypeType()
    * @generated
    */
	int SCHEMA_TYPE_TYPE = 15;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationsTypeImpl <em>Type Declarations Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationsTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeDeclarationsType()
    * @generated
    */
	int TYPE_DECLARATIONS_TYPE = 17;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl <em>Type Declaration Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.TypeDeclarationTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeDeclarationType()
    * @generated
    */
	int TYPE_DECLARATION_TYPE = 18;

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
   int FORMAL_PARAMETER_TYPE = 11;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParametersTypeImpl <em>Formal Parameters Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.FormalParametersTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getFormalParametersType()
    * @generated
    */
   int FORMAL_PARAMETERS_TYPE = 10;

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
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl <em>Loop Multi Instance Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopMultiInstanceType()
    * @generated
    */
   int LOOP_MULTI_INSTANCE_TYPE = 12;

   /**
    * The feature id for the '<em><b>MI Condition</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION = 0;

   /**
    * The feature id for the '<em><b>Complex MI Flow Condition</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION = 1;

   /**
    * The feature id for the '<em><b>Loop Data Ref</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF = 2;

   /**
    * The feature id for the '<em><b>MI Flow Condition</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION = 3;

   /**
    * The feature id for the '<em><b>MI Ordering</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING = 4;

   /**
    * The number of structural features of the '<em>Loop Multi Instance Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_MULTI_INSTANCE_TYPE_FEATURE_COUNT = 5;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopStandardTypeImpl <em>Loop Standard Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopStandardTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopStandardType()
    * @generated
    */
   int LOOP_STANDARD_TYPE = 13;

   /**
    * The feature id for the '<em><b>Loop Condition</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_STANDARD_TYPE__LOOP_CONDITION = 0;

   /**
    * The feature id for the '<em><b>Loop Maximum</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_STANDARD_TYPE__LOOP_MAXIMUM = 1;

   /**
    * The feature id for the '<em><b>Test Time</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_STANDARD_TYPE__TEST_TIME = 2;

   /**
    * The number of structural features of the '<em>Loop Standard Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_STANDARD_TYPE_FEATURE_COUNT = 3;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopTypeImpl <em>Loop Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopTypeImpl
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopType()
    * @generated
    */
   int LOOP_TYPE = 14;

   /**
    * The feature id for the '<em><b>Loop Standard</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_TYPE__LOOP_STANDARD = 0;

   /**
    * The feature id for the '<em><b>Loop Multi Instance</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_TYPE__LOOP_MULTI_INSTANCE = 1;

   /**
    * The feature id for the '<em><b>Loop Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_TYPE__LOOP_TYPE = 2;

   /**
    * The number of structural features of the '<em>Loop Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int LOOP_TYPE_FEATURE_COUNT = 3;

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
	int SCRIPT_TYPE = 16;

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
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType <em>Loop Type Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopTypeType()
    * @generated
    */
   int LOOP_TYPE_TYPE = 20;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType <em>MI Flow Condition Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIFlowConditionType()
    * @generated
    */
   int MI_FLOW_CONDITION_TYPE = 21;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType <em>MI Ordering Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIOrderingType()
    * @generated
    */
   int MI_ORDERING_TYPE = 22;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ModeType <em>Mode Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getModeType()
    * @generated
    */
   int MODE_TYPE = 23;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType <em>Test Time Type</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTestTimeType()
    * @generated
    */
   int TEST_TIME_TYPE = 24;

   /**
    * The meta object id for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeType <em>Type Type</em>}' enum.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeType()
    * @generated
    */
	int TYPE_TYPE = 25;

   /**
    * The meta object id for the '<em>Loop Type Type Object</em>' data type.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopTypeTypeObject()
    * @generated
    */
   int LOOP_TYPE_TYPE_OBJECT = 26;

   /**
    * The meta object id for the '<em>MI Flow Condition Type Object</em>' data type.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIFlowConditionTypeObject()
    * @generated
    */
   int MI_FLOW_CONDITION_TYPE_OBJECT = 27;

   /**
    * The meta object id for the '<em>MI Ordering Type Object</em>' data type.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIOrderingTypeObject()
    * @generated
    */
   int MI_ORDERING_TYPE_OBJECT = 28;

   /**
    * The meta object id for the '<em>Mode Type Object</em>' data type.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ModeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getModeTypeObject()
    * @generated
    */
   int MODE_TYPE_OBJECT = 29;

   /**
    * The meta object id for the '<em>Test Time Type Object</em>' data type.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTestTimeTypeObject()
    * @generated
    */
   int TEST_TIME_TYPE_OBJECT = 30;

   /**
    * The meta object id for the '<em>Type Type Object</em>' data type.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeType
    * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTypeTypeObject()
    * @generated
    */
	int TYPE_TYPE_OBJECT = 31;


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
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType <em>Expression Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Expression Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType
    * @generated
    */
   EClass getExpressionType();

   /**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getMixed <em>Mixed</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Mixed</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getMixed()
    * @see #getExpressionType()
    * @generated
    */
   EAttribute getExpressionType_Mixed();

   /**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getGroup <em>Group</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Group</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getGroup()
    * @see #getExpressionType()
    * @generated
    */
   EAttribute getExpressionType_Group();

   /**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getAny <em>Any</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Any</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getAny()
    * @see #getExpressionType()
    * @generated
    */
   EAttribute getExpressionType_Any();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptGrammar <em>Script Grammar</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Script Grammar</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptGrammar()
    * @see #getExpressionType()
    * @generated
    */
   EAttribute getExpressionType_ScriptGrammar();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptType <em>Script Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Script Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptType()
    * @see #getExpressionType()
    * @generated
    */
   EAttribute getExpressionType_ScriptType();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptVersion <em>Script Version</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Script Version</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptVersion()
    * @see #getExpressionType()
    * @generated
    */
   EAttribute getExpressionType_ScriptVersion();

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
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType <em>Loop Multi Instance Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Loop Multi Instance Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType
    * @generated
    */
   EClass getLoopMultiInstanceType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMICondition <em>MI Condition</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>MI Condition</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMICondition()
    * @see #getLoopMultiInstanceType()
    * @generated
    */
   EReference getLoopMultiInstanceType_MICondition();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getComplexMIFlowCondition <em>Complex MI Flow Condition</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Complex MI Flow Condition</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getComplexMIFlowCondition()
    * @see #getLoopMultiInstanceType()
    * @generated
    */
   EReference getLoopMultiInstanceType_ComplexMIFlowCondition();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getLoopDataRef <em>Loop Data Ref</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Loop Data Ref</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getLoopDataRef()
    * @see #getLoopMultiInstanceType()
    * @generated
    */
   EReference getLoopMultiInstanceType_LoopDataRef();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIFlowCondition <em>MI Flow Condition</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>MI Flow Condition</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIFlowCondition()
    * @see #getLoopMultiInstanceType()
    * @generated
    */
   EAttribute getLoopMultiInstanceType_MIFlowCondition();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIOrdering <em>MI Ordering</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>MI Ordering</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIOrdering()
    * @see #getLoopMultiInstanceType()
    * @generated
    */
   EAttribute getLoopMultiInstanceType_MIOrdering();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType <em>Loop Standard Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Loop Standard Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType
    * @generated
    */
   EClass getLoopStandardType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopCondition <em>Loop Condition</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Loop Condition</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopCondition()
    * @see #getLoopStandardType()
    * @generated
    */
   EReference getLoopStandardType_LoopCondition();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopMaximum <em>Loop Maximum</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Loop Maximum</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopMaximum()
    * @see #getLoopStandardType()
    * @generated
    */
   EAttribute getLoopStandardType_LoopMaximum();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getTestTime <em>Test Time</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Test Time</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getTestTime()
    * @see #getLoopStandardType()
    * @generated
    */
   EAttribute getLoopStandardType_TestTime();

   /**
    * Returns the meta object for class '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType <em>Loop Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Loop Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopType
    * @generated
    */
   EClass getLoopType();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopStandard <em>Loop Standard</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Loop Standard</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopStandard()
    * @see #getLoopType()
    * @generated
    */
   EReference getLoopType_LoopStandard();

   /**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopMultiInstance <em>Loop Multi Instance</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Loop Multi Instance</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopMultiInstance()
    * @see #getLoopType()
    * @generated
    */
   EReference getLoopType_LoopMultiInstance();

   /**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopType <em>Loop Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Loop Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopType()
    * @see #getLoopType()
    * @generated
    */
   EAttribute getLoopType_LoopType();

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
    * Returns the meta object for enum '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType <em>Loop Type Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Loop Type Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
    * @generated
    */
   EEnum getLoopTypeType();

   /**
    * Returns the meta object for enum '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType <em>MI Flow Condition Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>MI Flow Condition Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
    * @generated
    */
   EEnum getMIFlowConditionType();

   /**
    * Returns the meta object for enum '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType <em>MI Ordering Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>MI Ordering Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
    * @generated
    */
   EEnum getMIOrderingType();

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
    * Returns the meta object for enum '{@link org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType <em>Test Time Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Test Time Type</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
    * @generated
    */
   EEnum getTestTimeType();

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
    * Returns the meta object for data type '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType <em>Loop Type Type Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for data type '<em>Loop Type Type Object</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
    * @model instanceClass="org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType"
    *        extendedMetaData="name='LoopType_._type:Object' baseType='LoopType_._type'"
    * @generated
    */
   EDataType getLoopTypeTypeObject();

   /**
    * Returns the meta object for data type '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType <em>MI Flow Condition Type Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for data type '<em>MI Flow Condition Type Object</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
    * @model instanceClass="org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType"
    *        extendedMetaData="name='MI_FlowCondition_._type:Object' baseType='MI_FlowCondition_._type'"
    * @generated
    */
   EDataType getMIFlowConditionTypeObject();

   /**
    * Returns the meta object for data type '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType <em>MI Ordering Type Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for data type '<em>MI Ordering Type Object</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
    * @model instanceClass="org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType"
    *        extendedMetaData="name='MI_Ordering_._type:Object' baseType='MI_Ordering_._type'"
    * @generated
    */
   EDataType getMIOrderingTypeObject();

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
    * Returns the meta object for data type '{@link org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType <em>Test Time Type Object</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for data type '<em>Test Time Type Object</em>'.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
    * @model instanceClass="org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType"
    *        extendedMetaData="name='TestTime_._type:Object' baseType='TestTime_._type'"
    * @generated
    */
   EDataType getTestTimeTypeObject();

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
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl <em>Expression Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.ExpressionTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getExpressionType()
       * @generated
       */
      EClass EXPRESSION_TYPE = eINSTANCE.getExpressionType();

      /**
       * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXPRESSION_TYPE__MIXED = eINSTANCE.getExpressionType_Mixed();

      /**
       * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXPRESSION_TYPE__GROUP = eINSTANCE.getExpressionType_Group();

      /**
       * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXPRESSION_TYPE__ANY = eINSTANCE.getExpressionType_Any();

      /**
       * The meta object literal for the '<em><b>Script Grammar</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXPRESSION_TYPE__SCRIPT_GRAMMAR = eINSTANCE.getExpressionType_ScriptGrammar();

      /**
       * The meta object literal for the '<em><b>Script Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXPRESSION_TYPE__SCRIPT_TYPE = eINSTANCE.getExpressionType_ScriptType();

      /**
       * The meta object literal for the '<em><b>Script Version</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute EXPRESSION_TYPE__SCRIPT_VERSION = eINSTANCE.getExpressionType_ScriptVersion();

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
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl <em>Loop Multi Instance Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopMultiInstanceTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopMultiInstanceType()
       * @generated
       */
      EClass LOOP_MULTI_INSTANCE_TYPE = eINSTANCE.getLoopMultiInstanceType();

      /**
       * The meta object literal for the '<em><b>MI Condition</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION = eINSTANCE.getLoopMultiInstanceType_MICondition();

      /**
       * The meta object literal for the '<em><b>Complex MI Flow Condition</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION = eINSTANCE.getLoopMultiInstanceType_ComplexMIFlowCondition();

      /**
       * The meta object literal for the '<em><b>Loop Data Ref</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF = eINSTANCE.getLoopMultiInstanceType_LoopDataRef();

      /**
       * The meta object literal for the '<em><b>MI Flow Condition</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION = eINSTANCE.getLoopMultiInstanceType_MIFlowCondition();

      /**
       * The meta object literal for the '<em><b>MI Ordering</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING = eINSTANCE.getLoopMultiInstanceType_MIOrdering();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopStandardTypeImpl <em>Loop Standard Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopStandardTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopStandardType()
       * @generated
       */
      EClass LOOP_STANDARD_TYPE = eINSTANCE.getLoopStandardType();

      /**
       * The meta object literal for the '<em><b>Loop Condition</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference LOOP_STANDARD_TYPE__LOOP_CONDITION = eINSTANCE.getLoopStandardType_LoopCondition();

      /**
       * The meta object literal for the '<em><b>Loop Maximum</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_STANDARD_TYPE__LOOP_MAXIMUM = eINSTANCE.getLoopStandardType_LoopMaximum();

      /**
       * The meta object literal for the '<em><b>Test Time</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_STANDARD_TYPE__TEST_TIME = eINSTANCE.getLoopStandardType_TestTime();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopTypeImpl <em>Loop Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.LoopTypeImpl
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopType()
       * @generated
       */
      EClass LOOP_TYPE = eINSTANCE.getLoopType();

      /**
       * The meta object literal for the '<em><b>Loop Standard</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference LOOP_TYPE__LOOP_STANDARD = eINSTANCE.getLoopType_LoopStandard();

      /**
       * The meta object literal for the '<em><b>Loop Multi Instance</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference LOOP_TYPE__LOOP_MULTI_INSTANCE = eINSTANCE.getLoopType_LoopMultiInstance();

      /**
       * The meta object literal for the '<em><b>Loop Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute LOOP_TYPE__LOOP_TYPE = eINSTANCE.getLoopType_LoopType();

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
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType <em>Loop Type Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopTypeType()
       * @generated
       */
      EEnum LOOP_TYPE_TYPE = eINSTANCE.getLoopTypeType();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType <em>MI Flow Condition Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIFlowConditionType()
       * @generated
       */
      EEnum MI_FLOW_CONDITION_TYPE = eINSTANCE.getMIFlowConditionType();

      /**
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType <em>MI Ordering Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIOrderingType()
       * @generated
       */
      EEnum MI_ORDERING_TYPE = eINSTANCE.getMIOrderingType();

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
       * The meta object literal for the '{@link org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType <em>Test Time Type</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTestTimeType()
       * @generated
       */
      EEnum TEST_TIME_TYPE = eINSTANCE.getTestTimeType();

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
       * The meta object literal for the '<em>Loop Type Type Object</em>' data type.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getLoopTypeTypeObject()
       * @generated
       */
      EDataType LOOP_TYPE_TYPE_OBJECT = eINSTANCE.getLoopTypeTypeObject();

      /**
       * The meta object literal for the '<em>MI Flow Condition Type Object</em>' data type.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIFlowConditionTypeObject()
       * @generated
       */
      EDataType MI_FLOW_CONDITION_TYPE_OBJECT = eINSTANCE.getMIFlowConditionTypeObject();

      /**
       * The meta object literal for the '<em>MI Ordering Type Object</em>' data type.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getMIOrderingTypeObject()
       * @generated
       */
      EDataType MI_ORDERING_TYPE_OBJECT = eINSTANCE.getMIOrderingTypeObject();

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
       * The meta object literal for the '<em>Test Time Type Object</em>' data type.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
       * @see org.eclipse.stardust.model.xpdl.xpdl2.impl.XpdlPackageImpl#getTestTimeTypeObject()
       * @generated
       */
      EDataType TEST_TIME_TYPE_OBJECT = eINSTANCE.getTestTimeTypeObject();

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
