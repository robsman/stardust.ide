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
package org.eclipse.stardust.modeling.templates.emf.template;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateFactory
 * @model kind="package"
 * @generated
 */
public interface TemplatePackage extends EPackage {
	/**
    * The package name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNAME = "template"; //$NON-NLS-1$

	/**
    * The package namespace URI.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_URI = "http://www.carnot.ag/workflowmodel/templates"; //$NON-NLS-1$

	/**
    * The package namespace name.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String eNS_PREFIX = "tmplt"; //$NON-NLS-1$

	/**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	TemplatePackage eINSTANCE = org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl.init();

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.DocumentationTypeImpl <em>Documentation Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.DocumentationTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getDocumentationType()
    * @generated
    */
	int DOCUMENTATION_TYPE = 0;

	/**
    * The feature id for the '<em><b>Mixed</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int DOCUMENTATION_TYPE__MIXED = 0;

	/**
    * The feature id for the '<em><b>Group</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int DOCUMENTATION_TYPE__GROUP = 1;

	/**
    * The feature id for the '<em><b>Any</b></em>' attribute list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int DOCUMENTATION_TYPE__ANY = 2;

	/**
    * The number of structural features of the '<em>Documentation Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int DOCUMENTATION_TYPE_FEATURE_COUNT = 3;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl <em>Feature Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getFeatureType()
    * @generated
    */
	int FEATURE_TYPE = 1;

	/**
    * The feature id for the '<em><b>Label</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int FEATURE_TYPE__LABEL = 0;

	/**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int FEATURE_TYPE__NAME = 1;

	/**
    * The feature id for the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int FEATURE_TYPE__TYPE = 2;

	/**
    * The feature id for the '<em><b>Scope</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int FEATURE_TYPE__SCOPE = 3;

	/**
    * The number of structural features of the '<em>Feature Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int FEATURE_TYPE_FEATURE_COUNT = 4;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl <em>Reference Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getReferenceType()
    * @generated
    */
	int REFERENCE_TYPE = 3;

	/**
    * The feature id for the '<em><b>Application Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__APPLICATION_TYPE = 0;

	/**
    * The feature id for the '<em><b>Application Context Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE = 1;

	/**
    * The feature id for the '<em><b>Data Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__DATA_TYPE = 2;

	/**
    * The feature id for the '<em><b>Event Action Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__EVENT_ACTION_TYPE = 3;

	/**
    * The feature id for the '<em><b>Event Condition Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__EVENT_CONDITION_TYPE = 4;

	/**
    * The feature id for the '<em><b>Trigger Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__TRIGGER_TYPE = 5;

	/**
    * The feature id for the '<em><b>Application</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__APPLICATION = 6;

	/**
    * The feature id for the '<em><b>Data</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__DATA = 7;

	/**
    * The feature id for the '<em><b>Conditional Performer</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__CONDITIONAL_PERFORMER = 8;

	/**
    * The feature id for the '<em><b>Organization</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__ORGANIZATION = 9;

	/**
    * The feature id for the '<em><b>Role</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__ROLE = 10;

	/**
    * The feature id for the '<em><b>Process Definition</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__PROCESS_DEFINITION = 11;

	/**
    * The feature id for the '<em><b>Type Declaration</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__TYPE_DECLARATION = 12;

	/**
    * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE__PARAMETERS = 13;

	/**
    * The number of structural features of the '<em>Reference Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int REFERENCE_TYPE_FEATURE_COUNT = 14;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ParameterTypeImpl <em>Parameter Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.ParameterTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getParameterType()
    * @generated
    */
	int PARAMETER_TYPE = 2;

	/**
    * The feature id for the '<em><b>Application Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__APPLICATION_TYPE = REFERENCE_TYPE__APPLICATION_TYPE;

	/**
    * The feature id for the '<em><b>Application Context Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__APPLICATION_CONTEXT_TYPE = REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE;

	/**
    * The feature id for the '<em><b>Data Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__DATA_TYPE = REFERENCE_TYPE__DATA_TYPE;

	/**
    * The feature id for the '<em><b>Event Action Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__EVENT_ACTION_TYPE = REFERENCE_TYPE__EVENT_ACTION_TYPE;

	/**
    * The feature id for the '<em><b>Event Condition Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__EVENT_CONDITION_TYPE = REFERENCE_TYPE__EVENT_CONDITION_TYPE;

	/**
    * The feature id for the '<em><b>Trigger Type</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__TRIGGER_TYPE = REFERENCE_TYPE__TRIGGER_TYPE;

	/**
    * The feature id for the '<em><b>Application</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__APPLICATION = REFERENCE_TYPE__APPLICATION;

	/**
    * The feature id for the '<em><b>Data</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__DATA = REFERENCE_TYPE__DATA;

	/**
    * The feature id for the '<em><b>Conditional Performer</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__CONDITIONAL_PERFORMER = REFERENCE_TYPE__CONDITIONAL_PERFORMER;

	/**
    * The feature id for the '<em><b>Organization</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__ORGANIZATION = REFERENCE_TYPE__ORGANIZATION;

	/**
    * The feature id for the '<em><b>Role</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__ROLE = REFERENCE_TYPE__ROLE;

	/**
    * The feature id for the '<em><b>Process Definition</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__PROCESS_DEFINITION = REFERENCE_TYPE__PROCESS_DEFINITION;

	/**
    * The feature id for the '<em><b>Type Declaration</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__TYPE_DECLARATION = REFERENCE_TYPE__TYPE_DECLARATION;

	/**
    * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__PARAMETERS = REFERENCE_TYPE__PARAMETERS;

	/**
    * The feature id for the '<em><b>Activity</b></em>' reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__ACTIVITY = REFERENCE_TYPE_FEATURE_COUNT + 0;

	/**
    * The feature id for the '<em><b>Features</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE__FEATURES = REFERENCE_TYPE_FEATURE_COUNT + 1;

	/**
    * The number of structural features of the '<em>Parameter Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int PARAMETER_TYPE_FEATURE_COUNT = REFERENCE_TYPE_FEATURE_COUNT + 2;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.RootsTypeImpl <em>Roots Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.RootsTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getRootsType()
    * @generated
    */
	int ROOTS_TYPE = 4;

	/**
    * The feature id for the '<em><b>Root</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int ROOTS_TYPE__ROOT = 0;

	/**
    * The number of structural features of the '<em>Roots Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int ROOTS_TYPE_FEATURE_COUNT = 1;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl <em>Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getTemplateType()
    * @generated
    */
	int TEMPLATE_TYPE = 5;

	/**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_TYPE__ID = 0;

	/**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_TYPE__NAME = 1;

	/**
    * The feature id for the '<em><b>Documentation</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_TYPE__DOCUMENTATION = 2;

	/**
    * The feature id for the '<em><b>Roots</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_TYPE__ROOTS = 3;

	/**
    * The feature id for the '<em><b>Style</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_TYPE__STYLE = 4;

	/**
    * The feature id for the '<em><b>Category</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_TYPE__CATEGORY = 5;

	/**
    * The number of structural features of the '<em>Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_TYPE_FEATURE_COUNT = 6;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatesTypeImpl <em>Templates Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatesTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getTemplatesType()
    * @generated
    */
	int TEMPLATES_TYPE = 6;

	/**
    * The feature id for the '<em><b>Template</b></em>' containment reference list.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATES_TYPE__TEMPLATE = 0;

	/**
    * The number of structural features of the '<em>Templates Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATES_TYPE_FEATURE_COUNT = 1;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl <em>Library Type</em>}' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getTemplateLibraryType()
    * @generated
    */
	int TEMPLATE_LIBRARY_TYPE = 7;

	/**
    * The feature id for the '<em><b>Id</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_LIBRARY_TYPE__ID = 0;

	/**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_LIBRARY_TYPE__NAME = 1;

	/**
    * The feature id for the '<em><b>Documentation</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_LIBRARY_TYPE__DOCUMENTATION = 2;

	/**
    * The feature id for the '<em><b>Templates</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_LIBRARY_TYPE__TEMPLATES = 3;

	/**
    * The feature id for the '<em><b>Model</b></em>' containment reference.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_LIBRARY_TYPE__MODEL = 4;

	/**
    * The number of structural features of the '<em>Library Type</em>' class.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
	int TEMPLATE_LIBRARY_TYPE_FEATURE_COUNT = 5;


	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType <em>Feature Style Type</em>}' enum.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getFeatureStyleType()
    * @generated
    */
	int FEATURE_STYLE_TYPE = 8;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.ScopeType <em>Scope Type</em>}' enum.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.ScopeType
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getScopeType()
    * @generated
    */
	int SCOPE_TYPE = 9;

	/**
    * The meta object id for the '{@link org.eclipse.stardust.modeling.templates.emf.template.StyleType <em>Style Type</em>}' enum.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see org.eclipse.stardust.modeling.templates.emf.template.StyleType
    * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getStyleType()
    * @generated
    */
	int STYLE_TYPE = 10;


	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType <em>Documentation Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Documentation Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.DocumentationType
    * @generated
    */
	EClass getDocumentationType();

	/**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getMixed <em>Mixed</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Mixed</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getMixed()
    * @see #getDocumentationType()
    * @generated
    */
	EAttribute getDocumentationType_Mixed();

	/**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getGroup <em>Group</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Group</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getGroup()
    * @see #getDocumentationType()
    * @generated
    */
	EAttribute getDocumentationType_Group();

	/**
    * Returns the meta object for the attribute list '{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getAny <em>Any</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute list '<em>Any</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.DocumentationType#getAny()
    * @see #getDocumentationType()
    * @generated
    */
	EAttribute getDocumentationType_Any();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType <em>Feature Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Feature Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureType
    * @generated
    */
	EClass getFeatureType();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getLabel <em>Label</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Label</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getLabel()
    * @see #getFeatureType()
    * @generated
    */
	EAttribute getFeatureType_Label();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getName()
    * @see #getFeatureType()
    * @generated
    */
	EAttribute getFeatureType_Name();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getType()
    * @see #getFeatureType()
    * @generated
    */
	EAttribute getFeatureType_Type();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getScope <em>Scope</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Scope</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureType#getScope()
    * @see #getFeatureType()
    * @generated
    */
	EAttribute getFeatureType_Scope();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType <em>Parameter Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Parameter Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ParameterType
    * @generated
    */
	EClass getParameterType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType#getActivity <em>Activity</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Activity</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ParameterType#getActivity()
    * @see #getParameterType()
    * @generated
    */
	EReference getParameterType_Activity();

	/**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType#getFeatures <em>Features</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Features</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ParameterType#getFeatures()
    * @see #getParameterType()
    * @generated
    */
	EReference getParameterType_Features();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType <em>Reference Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Reference Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType
    * @generated
    */
	EClass getReferenceType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationType <em>Application Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Application Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationType()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_ApplicationType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationContextType <em>Application Context Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Application Context Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplicationContextType()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_ApplicationContextType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getDataType <em>Data Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Data Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getDataType()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_DataType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventActionType <em>Event Action Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Event Action Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventActionType()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_EventActionType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventConditionType <em>Event Condition Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Event Condition Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getEventConditionType()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_EventConditionType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTriggerType <em>Trigger Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Trigger Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTriggerType()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_TriggerType();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplication <em>Application</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Application</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getApplication()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_Application();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Data</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getData()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_Data();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getConditionalPerformer <em>Conditional Performer</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Conditional Performer</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getConditionalPerformer()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_ConditionalPerformer();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getOrganization <em>Organization</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Organization</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getOrganization()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_Organization();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getRole <em>Role</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Role</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getRole()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_Role();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getProcessDefinition <em>Process Definition</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Process Definition</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getProcessDefinition()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_ProcessDefinition();

	/**
    * Returns the meta object for the reference '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTypeDeclaration <em>Type Declaration</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Type Declaration</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getTypeDeclaration()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_TypeDeclaration();

	/**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getParameters <em>Parameters</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Parameters</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType#getParameters()
    * @see #getReferenceType()
    * @generated
    */
	EReference getReferenceType_Parameters();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.RootsType <em>Roots Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Roots Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.RootsType
    * @generated
    */
	EClass getRootsType();

	/**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.modeling.templates.emf.template.RootsType#getRoot <em>Root</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Root</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.RootsType#getRoot()
    * @see #getRootsType()
    * @generated
    */
	EReference getRootsType_Root();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType <em>Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType
    * @generated
    */
	EClass getTemplateType();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getId()
    * @see #getTemplateType()
    * @generated
    */
	EAttribute getTemplateType_Id();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getName()
    * @see #getTemplateType()
    * @generated
    */
	EAttribute getTemplateType_Name();

	/**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getDocumentation <em>Documentation</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Documentation</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getDocumentation()
    * @see #getTemplateType()
    * @generated
    */
	EReference getTemplateType_Documentation();

	/**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getRoots <em>Roots</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Roots</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getRoots()
    * @see #getTemplateType()
    * @generated
    */
	EReference getTemplateType_Roots();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getStyle <em>Style</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Style</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getStyle()
    * @see #getTemplateType()
    * @generated
    */
	EAttribute getTemplateType_Style();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getCategory <em>Category</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Category</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType#getCategory()
    * @see #getTemplateType()
    * @generated
    */
	EAttribute getTemplateType_Category();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplatesType <em>Templates Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Templates Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatesType
    * @generated
    */
	EClass getTemplatesType();

	/**
    * Returns the meta object for the containment reference list '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplatesType#getTemplate <em>Template</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Template</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatesType#getTemplate()
    * @see #getTemplatesType()
    * @generated
    */
	EReference getTemplatesType_Template();

	/**
    * Returns the meta object for class '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType <em>Library Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for class '<em>Library Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType
    * @generated
    */
	EClass getTemplateLibraryType();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getId <em>Id</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Id</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getId()
    * @see #getTemplateLibraryType()
    * @generated
    */
	EAttribute getTemplateLibraryType_Id();

	/**
    * Returns the meta object for the attribute '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getName()
    * @see #getTemplateLibraryType()
    * @generated
    */
	EAttribute getTemplateLibraryType_Name();

	/**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getDocumentation <em>Documentation</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Documentation</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getDocumentation()
    * @see #getTemplateLibraryType()
    * @generated
    */
	EReference getTemplateLibraryType_Documentation();

	/**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getTemplates <em>Templates</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Templates</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getTemplates()
    * @see #getTemplateLibraryType()
    * @generated
    */
	EReference getTemplateLibraryType_Templates();

	/**
    * Returns the meta object for the containment reference '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getModel <em>Model</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Model</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType#getModel()
    * @see #getTemplateLibraryType()
    * @generated
    */
	EReference getTemplateLibraryType_Model();

	/**
    * Returns the meta object for enum '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType <em>Feature Style Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Feature Style Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType
    * @generated
    */
	EEnum getFeatureStyleType();

	/**
    * Returns the meta object for enum '{@link org.eclipse.stardust.modeling.templates.emf.template.ScopeType <em>Scope Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Scope Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ScopeType
    * @generated
    */
	EEnum getScopeType();

	/**
    * Returns the meta object for enum '{@link org.eclipse.stardust.modeling.templates.emf.template.StyleType <em>Style Type</em>}'.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Style Type</em>'.
    * @see org.eclipse.stardust.modeling.templates.emf.template.StyleType
    * @generated
    */
	EEnum getStyleType();

	/**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
	TemplateFactory getTemplateFactory();

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
	interface Literals {
		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.DocumentationTypeImpl <em>Documentation Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.DocumentationTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getDocumentationType()
       * @generated
       */
		EClass DOCUMENTATION_TYPE = eINSTANCE.getDocumentationType();

		/**
       * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute DOCUMENTATION_TYPE__MIXED = eINSTANCE.getDocumentationType_Mixed();

		/**
       * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute DOCUMENTATION_TYPE__GROUP = eINSTANCE.getDocumentationType_Group();

		/**
       * The meta object literal for the '<em><b>Any</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute DOCUMENTATION_TYPE__ANY = eINSTANCE.getDocumentationType_Any();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl <em>Feature Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.FeatureTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getFeatureType()
       * @generated
       */
		EClass FEATURE_TYPE = eINSTANCE.getFeatureType();

		/**
       * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute FEATURE_TYPE__LABEL = eINSTANCE.getFeatureType_Label();

		/**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute FEATURE_TYPE__NAME = eINSTANCE.getFeatureType_Name();

		/**
       * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute FEATURE_TYPE__TYPE = eINSTANCE.getFeatureType_Type();

		/**
       * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute FEATURE_TYPE__SCOPE = eINSTANCE.getFeatureType_Scope();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ParameterTypeImpl <em>Parameter Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.ParameterTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getParameterType()
       * @generated
       */
		EClass PARAMETER_TYPE = eINSTANCE.getParameterType();

		/**
       * The meta object literal for the '<em><b>Activity</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference PARAMETER_TYPE__ACTIVITY = eINSTANCE.getParameterType_Activity();

		/**
       * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference PARAMETER_TYPE__FEATURES = eINSTANCE.getParameterType_Features();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl <em>Reference Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.ReferenceTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getReferenceType()
       * @generated
       */
		EClass REFERENCE_TYPE = eINSTANCE.getReferenceType();

		/**
       * The meta object literal for the '<em><b>Application Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__APPLICATION_TYPE = eINSTANCE.getReferenceType_ApplicationType();

		/**
       * The meta object literal for the '<em><b>Application Context Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE = eINSTANCE.getReferenceType_ApplicationContextType();

		/**
       * The meta object literal for the '<em><b>Data Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__DATA_TYPE = eINSTANCE.getReferenceType_DataType();

		/**
       * The meta object literal for the '<em><b>Event Action Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__EVENT_ACTION_TYPE = eINSTANCE.getReferenceType_EventActionType();

		/**
       * The meta object literal for the '<em><b>Event Condition Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__EVENT_CONDITION_TYPE = eINSTANCE.getReferenceType_EventConditionType();

		/**
       * The meta object literal for the '<em><b>Trigger Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__TRIGGER_TYPE = eINSTANCE.getReferenceType_TriggerType();

		/**
       * The meta object literal for the '<em><b>Application</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__APPLICATION = eINSTANCE.getReferenceType_Application();

		/**
       * The meta object literal for the '<em><b>Data</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__DATA = eINSTANCE.getReferenceType_Data();

		/**
       * The meta object literal for the '<em><b>Conditional Performer</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__CONDITIONAL_PERFORMER = eINSTANCE.getReferenceType_ConditionalPerformer();

		/**
       * The meta object literal for the '<em><b>Organization</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__ORGANIZATION = eINSTANCE.getReferenceType_Organization();

		/**
       * The meta object literal for the '<em><b>Role</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__ROLE = eINSTANCE.getReferenceType_Role();

		/**
       * The meta object literal for the '<em><b>Process Definition</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__PROCESS_DEFINITION = eINSTANCE.getReferenceType_ProcessDefinition();

		/**
       * The meta object literal for the '<em><b>Type Declaration</b></em>' reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__TYPE_DECLARATION = eINSTANCE.getReferenceType_TypeDeclaration();

		/**
       * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference REFERENCE_TYPE__PARAMETERS = eINSTANCE.getReferenceType_Parameters();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.RootsTypeImpl <em>Roots Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.RootsTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getRootsType()
       * @generated
       */
		EClass ROOTS_TYPE = eINSTANCE.getRootsType();

		/**
       * The meta object literal for the '<em><b>Root</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference ROOTS_TYPE__ROOT = eINSTANCE.getRootsType_Root();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl <em>Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getTemplateType()
       * @generated
       */
		EClass TEMPLATE_TYPE = eINSTANCE.getTemplateType();

		/**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TEMPLATE_TYPE__ID = eINSTANCE.getTemplateType_Id();

		/**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TEMPLATE_TYPE__NAME = eINSTANCE.getTemplateType_Name();

		/**
       * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TEMPLATE_TYPE__DOCUMENTATION = eINSTANCE.getTemplateType_Documentation();

		/**
       * The meta object literal for the '<em><b>Roots</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TEMPLATE_TYPE__ROOTS = eINSTANCE.getTemplateType_Roots();

		/**
       * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TEMPLATE_TYPE__STYLE = eINSTANCE.getTemplateType_Style();

		/**
       * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TEMPLATE_TYPE__CATEGORY = eINSTANCE.getTemplateType_Category();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatesTypeImpl <em>Templates Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatesTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getTemplatesType()
       * @generated
       */
		EClass TEMPLATES_TYPE = eINSTANCE.getTemplatesType();

		/**
       * The meta object literal for the '<em><b>Template</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TEMPLATES_TYPE__TEMPLATE = eINSTANCE.getTemplatesType_Template();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl <em>Library Type</em>}' class.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplateLibraryTypeImpl
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getTemplateLibraryType()
       * @generated
       */
		EClass TEMPLATE_LIBRARY_TYPE = eINSTANCE.getTemplateLibraryType();

		/**
       * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TEMPLATE_LIBRARY_TYPE__ID = eINSTANCE.getTemplateLibraryType_Id();

		/**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EAttribute TEMPLATE_LIBRARY_TYPE__NAME = eINSTANCE.getTemplateLibraryType_Name();

		/**
       * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TEMPLATE_LIBRARY_TYPE__DOCUMENTATION = eINSTANCE.getTemplateLibraryType_Documentation();

		/**
       * The meta object literal for the '<em><b>Templates</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TEMPLATE_LIBRARY_TYPE__TEMPLATES = eINSTANCE.getTemplateLibraryType_Templates();

		/**
       * The meta object literal for the '<em><b>Model</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @generated
       */
		EReference TEMPLATE_LIBRARY_TYPE__MODEL = eINSTANCE.getTemplateLibraryType_Model();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType <em>Feature Style Type</em>}' enum.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getFeatureStyleType()
       * @generated
       */
		EEnum FEATURE_STYLE_TYPE = eINSTANCE.getFeatureStyleType();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.ScopeType <em>Scope Type</em>}' enum.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.ScopeType
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getScopeType()
       * @generated
       */
		EEnum SCOPE_TYPE = eINSTANCE.getScopeType();

		/**
       * The meta object literal for the '{@link org.eclipse.stardust.modeling.templates.emf.template.StyleType <em>Style Type</em>}' enum.
       * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
       * @see org.eclipse.stardust.modeling.templates.emf.template.StyleType
       * @see org.eclipse.stardust.modeling.templates.emf.template.impl.TemplatePackageImpl#getStyleType()
       * @generated
       */
		EEnum STYLE_TYPE = eINSTANCE.getStyleType();

	}

} //TemplatePackage
