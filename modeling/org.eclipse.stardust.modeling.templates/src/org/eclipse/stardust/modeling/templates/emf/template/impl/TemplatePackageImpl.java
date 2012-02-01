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
package org.eclipse.stardust.modeling.templates.emf.template.impl;




import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.modeling.templates.emf.template.DocumentationType;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureStyleType;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureType;
import org.eclipse.stardust.modeling.templates.emf.template.ParameterType;
import org.eclipse.stardust.modeling.templates.emf.template.ReferenceType;
import org.eclipse.stardust.modeling.templates.emf.template.RootsType;
import org.eclipse.stardust.modeling.templates.emf.template.ScopeType;
import org.eclipse.stardust.modeling.templates.emf.template.StyleType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateFactory;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatesType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TemplatePackageImpl extends EPackageImpl implements TemplatePackage {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass documentationTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass featureTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass parameterTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass referenceTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass rootsTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass templateTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass templatesTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass templateLibraryTypeEClass = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EEnum featureStyleTypeEEnum = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EEnum scopeTypeEEnum = null;

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EEnum styleTypeEEnum = null;

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
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage#eNS_URI
    * @see #init()
    * @generated
    */
	private TemplatePackageImpl() {
      super(eNS_URI, TemplateFactory.eINSTANCE);
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
    * <p>This method is used to initialize {@link TemplatePackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
	public static TemplatePackage init() {
      if (isInited) return (TemplatePackage)EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI);

      // Obtain or create and register package
      TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TemplatePackageImpl());

      isInited = true;

      // Initialize simple dependencies
      CarnotWorkflowModelPackage.eINSTANCE.eClass();
      XMLTypePackage.eINSTANCE.eClass();

      // Create package meta-data objects
      theTemplatePackage.createPackageContents();

      // Initialize created meta-data
      theTemplatePackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theTemplatePackage.freeze();

  
      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(TemplatePackage.eNS_URI, theTemplatePackage);
      return theTemplatePackage;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getDocumentationType() {
      return documentationTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getDocumentationType_Mixed() {
      return (EAttribute)documentationTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getDocumentationType_Group() {
      return (EAttribute)documentationTypeEClass.getEStructuralFeatures().get(1);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getDocumentationType_Any() {
      return (EAttribute)documentationTypeEClass.getEStructuralFeatures().get(2);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getFeatureType() {
      return featureTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getFeatureType_Label() {
      return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getFeatureType_Name() {
      return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(1);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getFeatureType_Type() {
      return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(2);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getFeatureType_Scope() {
      return (EAttribute)featureTypeEClass.getEStructuralFeatures().get(3);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getParameterType() {
      return parameterTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getParameterType_Activity() {
      return (EReference)parameterTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getParameterType_Features() {
      return (EReference)parameterTypeEClass.getEStructuralFeatures().get(1);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getReferenceType() {
      return referenceTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_ApplicationType() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_ApplicationContextType() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(1);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_DataType() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(2);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_EventActionType() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(3);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_EventConditionType() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(4);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_TriggerType() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(5);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_Application() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(6);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_Data() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(7);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_ConditionalPerformer() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(8);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_Organization() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(9);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_Role() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(10);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_ProcessDefinition() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(11);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_TypeDeclaration() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(12);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getReferenceType_Parameters() {
      return (EReference)referenceTypeEClass.getEStructuralFeatures().get(13);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getRootsType() {
      return rootsTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getRootsType_Root() {
      return (EReference)rootsTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getTemplateType() {
      return templateTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTemplateType_Id() {
      return (EAttribute)templateTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTemplateType_Name() {
      return (EAttribute)templateTypeEClass.getEStructuralFeatures().get(1);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTemplateType_Documentation() {
      return (EReference)templateTypeEClass.getEStructuralFeatures().get(2);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTemplateType_Roots() {
      return (EReference)templateTypeEClass.getEStructuralFeatures().get(3);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTemplateType_Style() {
      return (EAttribute)templateTypeEClass.getEStructuralFeatures().get(4);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTemplateType_Category() {
      return (EAttribute)templateTypeEClass.getEStructuralFeatures().get(5);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getTemplatesType() {
      return templatesTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTemplatesType_Template() {
      return (EReference)templatesTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getTemplateLibraryType() {
      return templateLibraryTypeEClass;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTemplateLibraryType_Id() {
      return (EAttribute)templateLibraryTypeEClass.getEStructuralFeatures().get(0);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTemplateLibraryType_Name() {
      return (EAttribute)templateLibraryTypeEClass.getEStructuralFeatures().get(1);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTemplateLibraryType_Documentation() {
      return (EReference)templateLibraryTypeEClass.getEStructuralFeatures().get(2);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTemplateLibraryType_Templates() {
      return (EReference)templateLibraryTypeEClass.getEStructuralFeatures().get(3);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTemplateLibraryType_Model() {
      return (EReference)templateLibraryTypeEClass.getEStructuralFeatures().get(4);
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EEnum getFeatureStyleType() {
      return featureStyleTypeEEnum;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EEnum getScopeType() {
      return scopeTypeEEnum;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EEnum getStyleType() {
      return styleTypeEEnum;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateFactory getTemplateFactory() {
      return (TemplateFactory)getEFactoryInstance();
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
      documentationTypeEClass = createEClass(DOCUMENTATION_TYPE);
      createEAttribute(documentationTypeEClass, DOCUMENTATION_TYPE__MIXED);
      createEAttribute(documentationTypeEClass, DOCUMENTATION_TYPE__GROUP);
      createEAttribute(documentationTypeEClass, DOCUMENTATION_TYPE__ANY);

      featureTypeEClass = createEClass(FEATURE_TYPE);
      createEAttribute(featureTypeEClass, FEATURE_TYPE__LABEL);
      createEAttribute(featureTypeEClass, FEATURE_TYPE__NAME);
      createEAttribute(featureTypeEClass, FEATURE_TYPE__TYPE);
      createEAttribute(featureTypeEClass, FEATURE_TYPE__SCOPE);

      parameterTypeEClass = createEClass(PARAMETER_TYPE);
      createEReference(parameterTypeEClass, PARAMETER_TYPE__ACTIVITY);
      createEReference(parameterTypeEClass, PARAMETER_TYPE__FEATURES);

      referenceTypeEClass = createEClass(REFERENCE_TYPE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__APPLICATION_TYPE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__APPLICATION_CONTEXT_TYPE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__DATA_TYPE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__EVENT_ACTION_TYPE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__EVENT_CONDITION_TYPE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__TRIGGER_TYPE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__APPLICATION);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__DATA);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__CONDITIONAL_PERFORMER);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__ORGANIZATION);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__ROLE);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__PROCESS_DEFINITION);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__TYPE_DECLARATION);
      createEReference(referenceTypeEClass, REFERENCE_TYPE__PARAMETERS);

      rootsTypeEClass = createEClass(ROOTS_TYPE);
      createEReference(rootsTypeEClass, ROOTS_TYPE__ROOT);

      templateTypeEClass = createEClass(TEMPLATE_TYPE);
      createEAttribute(templateTypeEClass, TEMPLATE_TYPE__ID);
      createEAttribute(templateTypeEClass, TEMPLATE_TYPE__NAME);
      createEReference(templateTypeEClass, TEMPLATE_TYPE__DOCUMENTATION);
      createEReference(templateTypeEClass, TEMPLATE_TYPE__ROOTS);
      createEAttribute(templateTypeEClass, TEMPLATE_TYPE__STYLE);
      createEAttribute(templateTypeEClass, TEMPLATE_TYPE__CATEGORY);

      templatesTypeEClass = createEClass(TEMPLATES_TYPE);
      createEReference(templatesTypeEClass, TEMPLATES_TYPE__TEMPLATE);

      templateLibraryTypeEClass = createEClass(TEMPLATE_LIBRARY_TYPE);
      createEAttribute(templateLibraryTypeEClass, TEMPLATE_LIBRARY_TYPE__ID);
      createEAttribute(templateLibraryTypeEClass, TEMPLATE_LIBRARY_TYPE__NAME);
      createEReference(templateLibraryTypeEClass, TEMPLATE_LIBRARY_TYPE__DOCUMENTATION);
      createEReference(templateLibraryTypeEClass, TEMPLATE_LIBRARY_TYPE__TEMPLATES);
      createEReference(templateLibraryTypeEClass, TEMPLATE_LIBRARY_TYPE__MODEL);

      // Create enums
      featureStyleTypeEEnum = createEEnum(FEATURE_STYLE_TYPE);
      scopeTypeEEnum = createEEnum(SCOPE_TYPE);
      styleTypeEEnum = createEEnum(STYLE_TYPE);
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
      XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
      CarnotWorkflowModelPackage theCarnotWorkflowModelPackage = (CarnotWorkflowModelPackage)EPackage.Registry.INSTANCE.getEPackage(CarnotWorkflowModelPackage.eNS_URI);
      XpdlPackage theXpdlPackage = (XpdlPackage)EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      parameterTypeEClass.getESuperTypes().add(this.getReferenceType());

      // Initialize classes and features; add operations and parameters
      initEClass(documentationTypeEClass, DocumentationType.class, "DocumentationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEAttribute(getDocumentationType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, DocumentationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getDocumentationType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, DocumentationType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getDocumentationType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, DocumentationType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      initEClass(featureTypeEClass, FeatureType.class, "FeatureType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEAttribute(getFeatureType_Label(), theXMLTypePackage.getString(), "label", null, 0, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getFeatureType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getFeatureType_Type(), this.getFeatureStyleType(), "type", null, 0, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getFeatureType_Scope(), this.getScopeType(), "scope", null, 0, 1, FeatureType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      initEClass(parameterTypeEClass, ParameterType.class, "ParameterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEReference(getParameterType_Activity(), theCarnotWorkflowModelPackage.getActivityType(), null, "activity", null, 0, 1, ParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getParameterType_Features(), this.getFeatureType(), null, "features", null, 0, -1, ParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      initEClass(referenceTypeEClass, ReferenceType.class, "ReferenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEReference(getReferenceType_ApplicationType(), theCarnotWorkflowModelPackage.getApplicationTypeType(), null, "applicationType", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_ApplicationContextType(), theCarnotWorkflowModelPackage.getApplicationContextTypeType(), null, "applicationContextType", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_DataType(), theCarnotWorkflowModelPackage.getDataTypeType(), null, "dataType", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_EventActionType(), theCarnotWorkflowModelPackage.getEventActionTypeType(), null, "eventActionType", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_EventConditionType(), theCarnotWorkflowModelPackage.getEventConditionTypeType(), null, "eventConditionType", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_TriggerType(), theCarnotWorkflowModelPackage.getTriggerTypeType(), null, "triggerType", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_Application(), theCarnotWorkflowModelPackage.getApplicationType(), null, "application", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_Data(), theCarnotWorkflowModelPackage.getDataType(), null, "data", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_ConditionalPerformer(), theCarnotWorkflowModelPackage.getConditionalPerformerType(), null, "conditionalPerformer", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_Organization(), theCarnotWorkflowModelPackage.getOrganizationType(), null, "organization", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_Role(), theCarnotWorkflowModelPackage.getRoleType(), null, "role", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_ProcessDefinition(), theCarnotWorkflowModelPackage.getProcessDefinitionType(), null, "processDefinition", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_TypeDeclaration(), theXpdlPackage.getTypeDeclarationType(), null, "typeDeclaration", null, 0, 1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getReferenceType_Parameters(), this.getParameterType(), null, "parameters", null, 0, -1, ReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      addEOperation(referenceTypeEClass, ecorePackage.getEObject(), "getReference", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      EOperation op = addEOperation(referenceTypeEClass, null, "setReference", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
      addEParameter(op, ecorePackage.getEObject(), "reference", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      initEClass(rootsTypeEClass, RootsType.class, "RootsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEReference(getRootsType_Root(), this.getReferenceType(), null, "root", null, 0, -1, RootsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      initEClass(templateTypeEClass, TemplateType.class, "TemplateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEAttribute(getTemplateType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getTemplateType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getTemplateType_Documentation(), this.getDocumentationType(), null, "documentation", null, 0, 1, TemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getTemplateType_Roots(), this.getRootsType(), null, "roots", null, 0, 1, TemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getTemplateType_Style(), this.getStyleType(), "style", null, 0, 1, TemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getTemplateType_Category(), theXMLTypePackage.getString(), "category", null, 0, 1, TemplateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      initEClass(templatesTypeEClass, TemplatesType.class, "TemplatesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEReference(getTemplatesType_Template(), this.getTemplateType(), null, "template", null, 0, -1, TemplatesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      op = addEOperation(templatesTypeEClass, this.getTemplateType(), "getTemplate", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
      addEParameter(op, theXMLTypePackage.getString(), "templateId", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

      initEClass(templateLibraryTypeEClass, TemplateLibraryType.class, "TemplateLibraryType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
      initEAttribute(getTemplateLibraryType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TemplateLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEAttribute(getTemplateLibraryType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TemplateLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getTemplateLibraryType_Documentation(), this.getDocumentationType(), null, "documentation", null, 0, 1, TemplateLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getTemplateLibraryType_Templates(), this.getTemplatesType(), null, "templates", null, 0, 1, TemplateLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
      initEReference(getTemplateLibraryType_Model(), theCarnotWorkflowModelPackage.getModelType(), null, "model", null, 0, 1, TemplateLibraryType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

      // Initialize enums and add enum literals
      initEEnum(featureStyleTypeEEnum, FeatureStyleType.class, "FeatureStyleType"); //$NON-NLS-1$
      addEEnumLiteral(featureStyleTypeEEnum, FeatureStyleType.TEXT);
      addEEnumLiteral(featureStyleTypeEEnum, FeatureStyleType.SELECTION);

      initEEnum(scopeTypeEEnum, ScopeType.class, "ScopeType"); //$NON-NLS-1$
      addEEnumLiteral(scopeTypeEEnum, ScopeType.MODEL);
      addEEnumLiteral(scopeTypeEEnum, ScopeType.PROCESS);

      initEEnum(styleTypeEEnum, StyleType.class, "StyleType"); //$NON-NLS-1$
      addEEnumLiteral(styleTypeEEnum, StyleType.STANDALONE);
      addEEnumLiteral(styleTypeEEnum, StyleType.EMBEDDED);

      // Create resource
      createResource(eNS_URI);

      // Create annotations
      // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
      createExtendedMetaDataAnnotations();
      // http://www.carnot.ag/workflow/model/ElementIdRef
      createElementIdRefAnnotations();
   }

	/**
    * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected void createExtendedMetaDataAnnotations() {
      String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		 //$NON-NLS-1$
      addAnnotation
        (documentationTypeEClass, 
         source, 
         new String[] 
         {
          "name", "documentation", //$NON-NLS-1$ //$NON-NLS-2$
          "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getDocumentationType_Mixed(), 
         source, 
         new String[] 
         {
          "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
          "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getDocumentationType_Group(), 
         source, 
         new String[] 
         {
          "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "group:1" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getDocumentationType_Any(), 
         source, 
         new String[] 
         {
          "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
          "wildcards", "##any", //$NON-NLS-1$ //$NON-NLS-2$
          "name", ":2", //$NON-NLS-1$ //$NON-NLS-2$
          "processing", "lax", //$NON-NLS-1$ //$NON-NLS-2$
          "group", "#group:1" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (featureTypeEClass, 
         source, 
         new String[] 
         {
          "name", "feature", //$NON-NLS-1$ //$NON-NLS-2$
          "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getFeatureType_Label(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "label" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getFeatureType_Name(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getFeatureType_Type(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getFeatureType_Scope(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "scope" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (parameterTypeEClass, 
         source, 
         new String[] 
         {
          "name", "parameter", //$NON-NLS-1$ //$NON-NLS-2$
          "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getParameterType_Activity(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "activity" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getParameterType_Features(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "feature", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (referenceTypeEClass, 
         source, 
         new String[] 
         {
          "name", "reference", //$NON-NLS-1$ //$NON-NLS-2$
          "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getReferenceType_ApplicationType(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "applicationType" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_ApplicationContextType(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "applicationContextType" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_DataType(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "dataType" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_EventActionType(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "eventActionType" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_EventConditionType(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "eventConditionType" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_TriggerType(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "triggerType" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Application(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "application" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Data(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "data" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_ConditionalPerformer(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "conditionalPerformer" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Organization(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "organization" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Role(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "role" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_ProcessDefinition(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "process" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_TypeDeclaration(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "typeDeclaration" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Parameters(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "parameter", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (rootsTypeEClass, 
         source, 
         new String[] 
         {
          "name", "roots", //$NON-NLS-1$ //$NON-NLS-2$
          "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getRootsType_Root(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "root", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (templateTypeEClass, 
         source, 
         new String[] 
         {
          "name", "template", //$NON-NLS-1$ //$NON-NLS-2$
          "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateType_Id(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateType_Name(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateType_Documentation(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "documentation", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateType_Roots(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "roots", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateType_Style(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "style" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateType_Category(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "category" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (templatesTypeEClass, 
         source, 
         new String[] 
         {
          "name", "templates", //$NON-NLS-1$ //$NON-NLS-2$
          "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplatesType_Template(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "template", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (templateLibraryTypeEClass, 
         source, 
         new String[] 
         {
          "kind", "elementOnly", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "TemplateLibrary" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateLibraryType_Id(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateLibraryType_Name(), 
         source, 
         new String[] 
         {
          "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateLibraryType_Documentation(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "documentation", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateLibraryType_Templates(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "templates", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (getTemplateLibraryType_Model(), 
         source, 
         new String[] 
         {
          "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
          "name", "model", //$NON-NLS-1$ //$NON-NLS-2$
          "namespace", "http://www.carnot.ag/workflowmodel/3.1" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (featureStyleTypeEEnum, 
         source, 
         new String[] 
         {
          "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (scopeTypeEEnum, 
         source, 
         new String[] 
         {
          "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
         });		
      addAnnotation
        (styleTypeEEnum, 
         source, 
         new String[] 
         {
          "name", "style" //$NON-NLS-1$ //$NON-NLS-2$
         });
   }

	/**
    * Initializes the annotations for <b>http://www.carnot.ag/workflow/model/ElementIdRef</b>.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected void createElementIdRefAnnotations() {
      String source = "http://www.carnot.ag/workflow/model/ElementIdRef";													 //$NON-NLS-1$
      addAnnotation
        (getParameterType_Activity(), 
         source, 
         new String[] 
         {
          "scope", "process" //$NON-NLS-1$ //$NON-NLS-2$
         });					
      addAnnotation
        (getReferenceType_ApplicationType(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_ApplicationContextType(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_DataType(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_EventActionType(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_EventConditionType(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_TriggerType(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Application(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Data(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_ConditionalPerformer(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Organization(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_Role(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_ProcessDefinition(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });			
      addAnnotation
        (getReferenceType_TypeDeclaration(), 
         source, 
         new String[] 
         {
          "scope", "model" //$NON-NLS-1$ //$NON-NLS-2$
         });																					
   }

} //TemplatePackageImpl
