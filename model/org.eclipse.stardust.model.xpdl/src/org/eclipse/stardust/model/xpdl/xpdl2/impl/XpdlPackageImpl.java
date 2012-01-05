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
package org.eclipse.stardust.model.xpdl.xpdl2.impl;




import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType;
import org.eclipse.stardust.model.xpdl.xpdl2.Extensible;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType;
import org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType;
import org.eclipse.stardust.model.xpdl.xpdl2.ModeType;
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ScriptType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlFactory;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.impl.ExtensionPackageImpl;
import org.eclipse.xsd.XSDPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XpdlPackageImpl extends EPackageImpl implements XpdlPackage 
{
   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass basicTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass dataTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass declaredTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass extendedAttributesTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass extendedAttributeTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass extensibleEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass externalPackagesEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass externalPackageEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass externalReferenceTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass formalParameterTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass formalParametersTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass schemaTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass scriptTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass typeDeclarationsTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass typeDeclarationTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EClass xpdlTypeTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EEnum modeTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EEnum typeTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EDataType modeTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	private EDataType typeTypeObjectEDataType = null;

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
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#eNS_URI
    * @see #init()
    * @generated
    */
	private XpdlPackageImpl()
   {
      super(eNS_URI, XpdlFactory.eINSTANCE);
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
	public static XpdlPackage init()
   {
      if (isInited) return (XpdlPackage)EPackage.Registry.INSTANCE.getEPackage(XpdlPackage.eNS_URI);

      // Obtain or create and register package
      XpdlPackageImpl theXpdlPackage = (XpdlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof XpdlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new XpdlPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      XSDPackage.eINSTANCE.eClass();
      XMLTypePackage.eINSTANCE.eClass();

      // Obtain or create and register interdependencies
      ExtensionPackageImpl theExtensionPackage = (ExtensionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExtensionPackage.eNS_URI) instanceof ExtensionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExtensionPackage.eNS_URI) : ExtensionPackage.eINSTANCE);

      // Create package meta-data objects
      theXpdlPackage.createPackageContents();
      theExtensionPackage.createPackageContents();

      // Initialize created meta-data
      theXpdlPackage.initializePackageContents();
      theExtensionPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theXpdlPackage.freeze();

      return theXpdlPackage;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getBasicTypeType()
   {
      return basicTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getBasicTypeType_Type()
   {
      return (EAttribute)basicTypeTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getDataTypeType()
   {
      return dataTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataTypeType_BasicType()
   {
      return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataTypeType_DeclaredType()
   {
      return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataTypeType_SchemaType()
   {
      return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getDataTypeType_ExternalReference()
   {
      return (EReference)dataTypeTypeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDataTypeType_CarnotType()
   {
      return (EAttribute)dataTypeTypeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getDeclaredTypeType()
   {
      return declaredTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getDeclaredTypeType_Id()
   {
      return (EAttribute)declaredTypeTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getExtendedAttributesType()
   {
      return extendedAttributesTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getExtendedAttributesType_ExtendedAttribute()
   {
      return (EReference)extendedAttributesTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getExtendedAttributeType()
   {
      return extendedAttributeTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getExtendedAttributeType_ExtendedAnnotation()
   {
      return (EReference)extendedAttributeTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExtendedAttributeType_Mixed()
   {
      return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExtendedAttributeType_Group()
   {
      return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExtendedAttributeType_Any()
   {
      return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExtendedAttributeType_Name()
   {
      return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExtendedAttributeType_Value()
   {
      return (EAttribute)extendedAttributeTypeEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getExtensible()
   {
      return extensibleEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getExtensible_ExtendedAttributes()
   {
      return (EReference)extensibleEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getExternalPackages()
   {
      return externalPackagesEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getExternalPackages_ExternalPackage()
   {
      return (EReference)externalPackagesEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getExternalPackage()
   {
      return externalPackageEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExternalPackage_Href()
   {
      return (EAttribute)externalPackageEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExternalPackage_Id()
   {
      return (EAttribute)externalPackageEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExternalPackage_Name()
   {
      return (EAttribute)externalPackageEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getExternalReferenceType()
   {
      return externalReferenceTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExternalReferenceType_Location()
   {
      return (EAttribute)externalReferenceTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExternalReferenceType_Namespace()
   {
      return (EAttribute)externalReferenceTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getExternalReferenceType_Xref()
   {
      return (EAttribute)externalReferenceTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getFormalParameterType()
   {
      return formalParameterTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getFormalParameterType_DataType()
   {
      return (EReference)formalParameterTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getFormalParameterType_Description()
   {
      return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getFormalParameterType_Id()
   {
      return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getFormalParameterType_Mode()
   {
      return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getFormalParameterType_Name()
   {
      return (EAttribute)formalParameterTypeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getFormalParametersType()
   {
      return formalParametersTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getFormalParametersType_FormalParameter()
   {
      return (EReference)formalParametersTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getSchemaTypeType()
   {
      return schemaTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getSchemaTypeType_Schema()
   {
      return (EReference)schemaTypeTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getScriptType()
   {
      return scriptTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getScriptType_Grammar()
   {
      return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getScriptType_Type()
   {
      return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getScriptType_Version()
   {
      return (EAttribute)scriptTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getTypeDeclarationsType()
   {
      return typeDeclarationsTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTypeDeclarationsType_TypeDeclaration()
   {
      return (EReference)typeDeclarationsTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getTypeDeclarationType()
   {
      return typeDeclarationTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTypeDeclarationType_BasicType()
   {
      return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTypeDeclarationType_DeclaredType()
   {
      return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTypeDeclarationType_SchemaType()
   {
      return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EReference getTypeDeclarationType_ExternalReference()
   {
      return (EReference)typeDeclarationTypeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTypeDeclarationType_Description()
   {
      return (EAttribute)typeDeclarationTypeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTypeDeclarationType_Id()
   {
      return (EAttribute)typeDeclarationTypeEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EAttribute getTypeDeclarationType_Name()
   {
      return (EAttribute)typeDeclarationTypeEClass.getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EClass getXpdlTypeType()
   {
      return xpdlTypeTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EEnum getModeType()
   {
      return modeTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EEnum getTypeType()
   {
      return typeTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getModeTypeObject()
   {
      return modeTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public EDataType getTypeTypeObject()
   {
      return typeTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public XpdlFactory getXpdlFactory()
   {
      return (XpdlFactory)getEFactoryInstance();
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
      if (isCreated) return;
      isCreated = true;

      // Create classes and their features
      basicTypeTypeEClass = createEClass(BASIC_TYPE_TYPE);
      createEAttribute(basicTypeTypeEClass, BASIC_TYPE_TYPE__TYPE);

      dataTypeTypeEClass = createEClass(DATA_TYPE_TYPE);
      createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__BASIC_TYPE);
      createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__DECLARED_TYPE);
      createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__SCHEMA_TYPE);
      createEReference(dataTypeTypeEClass, DATA_TYPE_TYPE__EXTERNAL_REFERENCE);
      createEAttribute(dataTypeTypeEClass, DATA_TYPE_TYPE__CARNOT_TYPE);

      declaredTypeTypeEClass = createEClass(DECLARED_TYPE_TYPE);
      createEAttribute(declaredTypeTypeEClass, DECLARED_TYPE_TYPE__ID);

      extendedAttributesTypeEClass = createEClass(EXTENDED_ATTRIBUTES_TYPE);
      createEReference(extendedAttributesTypeEClass, EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE);

      extendedAttributeTypeEClass = createEClass(EXTENDED_ATTRIBUTE_TYPE);
      createEReference(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__EXTENDED_ANNOTATION);
      createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__MIXED);
      createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__GROUP);
      createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__ANY);
      createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__NAME);
      createEAttribute(extendedAttributeTypeEClass, EXTENDED_ATTRIBUTE_TYPE__VALUE);

      extensibleEClass = createEClass(EXTENSIBLE);
      createEReference(extensibleEClass, EXTENSIBLE__EXTENDED_ATTRIBUTES);

      externalPackagesEClass = createEClass(EXTERNAL_PACKAGES);
      createEReference(externalPackagesEClass, EXTERNAL_PACKAGES__EXTERNAL_PACKAGE);

      externalPackageEClass = createEClass(EXTERNAL_PACKAGE);
      createEAttribute(externalPackageEClass, EXTERNAL_PACKAGE__HREF);
      createEAttribute(externalPackageEClass, EXTERNAL_PACKAGE__ID);
      createEAttribute(externalPackageEClass, EXTERNAL_PACKAGE__NAME);

      externalReferenceTypeEClass = createEClass(EXTERNAL_REFERENCE_TYPE);
      createEAttribute(externalReferenceTypeEClass, EXTERNAL_REFERENCE_TYPE__LOCATION);
      createEAttribute(externalReferenceTypeEClass, EXTERNAL_REFERENCE_TYPE__NAMESPACE);
      createEAttribute(externalReferenceTypeEClass, EXTERNAL_REFERENCE_TYPE__XREF);

      formalParametersTypeEClass = createEClass(FORMAL_PARAMETERS_TYPE);
      createEReference(formalParametersTypeEClass, FORMAL_PARAMETERS_TYPE__FORMAL_PARAMETER);

      formalParameterTypeEClass = createEClass(FORMAL_PARAMETER_TYPE);
      createEReference(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__DATA_TYPE);
      createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__DESCRIPTION);
      createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__ID);
      createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__MODE);
      createEAttribute(formalParameterTypeEClass, FORMAL_PARAMETER_TYPE__NAME);

      schemaTypeTypeEClass = createEClass(SCHEMA_TYPE_TYPE);
      createEReference(schemaTypeTypeEClass, SCHEMA_TYPE_TYPE__SCHEMA);

      scriptTypeEClass = createEClass(SCRIPT_TYPE);
      createEAttribute(scriptTypeEClass, SCRIPT_TYPE__GRAMMAR);
      createEAttribute(scriptTypeEClass, SCRIPT_TYPE__TYPE);
      createEAttribute(scriptTypeEClass, SCRIPT_TYPE__VERSION);

      typeDeclarationsTypeEClass = createEClass(TYPE_DECLARATIONS_TYPE);
      createEReference(typeDeclarationsTypeEClass, TYPE_DECLARATIONS_TYPE__TYPE_DECLARATION);

      typeDeclarationTypeEClass = createEClass(TYPE_DECLARATION_TYPE);
      createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__BASIC_TYPE);
      createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__DECLARED_TYPE);
      createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__SCHEMA_TYPE);
      createEReference(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__EXTERNAL_REFERENCE);
      createEAttribute(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__DESCRIPTION);
      createEAttribute(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__ID);
      createEAttribute(typeDeclarationTypeEClass, TYPE_DECLARATION_TYPE__NAME);

      xpdlTypeTypeEClass = createEClass(XPDL_TYPE_TYPE);

      // Create enums
      modeTypeEEnum = createEEnum(MODE_TYPE);
      typeTypeEEnum = createEEnum(TYPE_TYPE);

      // Create data types
      modeTypeObjectEDataType = createEDataType(MODE_TYPE_OBJECT);
      typeTypeObjectEDataType = createEDataType(TYPE_TYPE_OBJECT);
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
      if (isInitialized) return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Obtain other dependent packages
      ExtensionPackage theExtensionPackage = (ExtensionPackage)EPackage.Registry.INSTANCE.getEPackage(ExtensionPackage.eNS_URI);
      XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
      XSDPackage theXSDPackage = (XSDPackage)EPackage.Registry.INSTANCE.getEPackage(XSDPackage.eNS_URI);

      // Add subpackages
      getESubpackages().add(theExtensionPackage);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      basicTypeTypeEClass.getESuperTypes().add(this.getXpdlTypeType());
      declaredTypeTypeEClass.getESuperTypes().add(this.getXpdlTypeType());
      externalPackageEClass.getESuperTypes().add(this.getExtensible());
      externalReferenceTypeEClass.getESuperTypes().add(this.getXpdlTypeType());
      schemaTypeTypeEClass.getESuperTypes().add(this.getXpdlTypeType());
      typeDeclarationTypeEClass.getESuperTypes().add(this.getExtensible());

      // Initialize classes and features; add operations and parameters
      initEClass(basicTypeTypeEClass, BasicTypeType.class, "BasicTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getBasicTypeType_Type(), this.getTypeType(), "type", "STRING", 1, 1, BasicTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(dataTypeTypeEClass, DataTypeType.class, "DataTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getDataTypeType_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getDataTypeType_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getDataTypeType_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getDataTypeType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getDataTypeType_CarnotType(), theXMLTypePackage.getString(), "carnotType", null, 0, 1, DataTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      addEOperation(dataTypeTypeEClass, this.getXpdlTypeType(), "getDataType", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(declaredTypeTypeEClass, DeclaredTypeType.class, "DeclaredTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getDeclaredTypeType_Id(), theXMLTypePackage.getIDREF(), "id", null, 1, 1, DeclaredTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(extendedAttributesTypeEClass, ExtendedAttributesType.class, "ExtendedAttributesType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getExtendedAttributesType_ExtendedAttribute(), this.getExtendedAttributeType(), null, "extendedAttribute", null, 0, -1, ExtendedAttributesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(extendedAttributeTypeEClass, ExtendedAttributeType.class, "ExtendedAttributeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getExtendedAttributeType_ExtendedAnnotation(), theExtensionPackage.getExtendedAnnotationType(), null, "extendedAnnotation", null, 0, 1, ExtendedAttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExtendedAttributeType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ExtendedAttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExtendedAttributeType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExtendedAttributeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
      initEAttribute(getExtendedAttributeType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ExtendedAttributeType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
      initEAttribute(getExtendedAttributeType_Name(), theXMLTypePackage.getNMTOKEN(), "name", null, 1, 1, ExtendedAttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExtendedAttributeType_Value(), theXMLTypePackage.getString(), "value", null, 0, 1, ExtendedAttributeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(extensibleEClass, Extensible.class, "Extensible", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getExtensible_ExtendedAttributes(), this.getExtendedAttributesType(), null, "extendedAttributes", null, 0, 1, Extensible.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(externalPackagesEClass, ExternalPackages.class, "ExternalPackages", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getExternalPackages_ExternalPackage(), this.getExternalPackage(), null, "externalPackage", null, 0, -1, ExternalPackages.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      EOperation op = addEOperation(externalPackagesEClass, this.getExternalPackage(), "getExternalPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, theXMLTypePackage.getString(), "packageId", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(externalPackageEClass, ExternalPackage.class, "ExternalPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getExternalPackage_Href(), theXMLTypePackage.getString(), "href", null, 1, 1, ExternalPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExternalPackage_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, ExternalPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExternalPackage_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, ExternalPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(externalReferenceTypeEClass, ExternalReferenceType.class, "ExternalReferenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getExternalReferenceType_Location(), theXMLTypePackage.getAnyURI(), "location", null, 1, 1, ExternalReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExternalReferenceType_Namespace(), theXMLTypePackage.getAnyURI(), "namespace", null, 0, 1, ExternalReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExternalReferenceType_Xref(), theXMLTypePackage.getNMTOKEN(), "xref", null, 0, 1, ExternalReferenceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      addEOperation(externalReferenceTypeEClass, theXSDPackage.getXSDSchema(), "getSchema", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(formalParametersTypeEClass, FormalParametersType.class, "FormalParametersType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getFormalParametersType_FormalParameter(), this.getFormalParameterType(), null, "formalParameter", null, 0, -1, FormalParametersType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      op = addEOperation(formalParametersTypeEClass, null, "addFormalParameter", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, this.getFormalParameterType(), "parameter", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(formalParametersTypeEClass, this.getFormalParameterType(), "getFormalParameter", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, theXMLTypePackage.getString(), "parameterId", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(formalParameterTypeEClass, FormalParameterType.class, "FormalParameterType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getFormalParameterType_DataType(), this.getDataTypeType(), null, "dataType", null, 0, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getFormalParameterType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getFormalParameterType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getFormalParameterType_Mode(), this.getModeType(), "mode", "IN", 0, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getFormalParameterType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, FormalParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(schemaTypeTypeEClass, SchemaTypeType.class, "SchemaTypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getSchemaTypeType_Schema(), theXSDPackage.getXSDSchema(), null, "schema", null, 0, 1, SchemaTypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(scriptTypeEClass, ScriptType.class, "ScriptType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getScriptType_Grammar(), theXMLTypePackage.getAnyURI(), "grammar", null, 0, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getScriptType_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getScriptType_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, ScriptType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(typeDeclarationsTypeEClass, TypeDeclarationsType.class, "TypeDeclarationsType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getTypeDeclarationsType_TypeDeclaration(), this.getTypeDeclarationType(), null, "typeDeclaration", null, 0, -1, TypeDeclarationsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      op = addEOperation(typeDeclarationsTypeEClass, this.getTypeDeclarationType(), "getTypeDeclaration", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, theXMLTypePackage.getString(), "typeId", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(typeDeclarationTypeEClass, TypeDeclarationType.class, "TypeDeclarationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getTypeDeclarationType_BasicType(), this.getBasicTypeType(), null, "basicType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTypeDeclarationType_DeclaredType(), this.getDeclaredTypeType(), null, "declaredType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTypeDeclarationType_SchemaType(), this.getSchemaTypeType(), null, "schemaType", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getTypeDeclarationType_ExternalReference(), this.getExternalReferenceType(), null, "externalReference", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTypeDeclarationType_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTypeDeclarationType_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTypeDeclarationType_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, TypeDeclarationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      addEOperation(typeDeclarationTypeEClass, this.getXpdlTypeType(), "getDataType", 0, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(typeDeclarationTypeEClass, theXSDPackage.getXSDSchema(), "getSchema", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(xpdlTypeTypeEClass, XpdlTypeType.class, "XpdlTypeType", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      // Initialize enums and add enum literals
      initEEnum(modeTypeEEnum, ModeType.class, "ModeType");
      addEEnumLiteral(modeTypeEEnum, ModeType.IN);
      addEEnumLiteral(modeTypeEEnum, ModeType.OUT);
      addEEnumLiteral(modeTypeEEnum, ModeType.INOUT);

      initEEnum(typeTypeEEnum, TypeType.class, "TypeType");
      addEEnumLiteral(typeTypeEEnum, TypeType.STRING_LITERAL);
      addEEnumLiteral(typeTypeEEnum, TypeType.FLOAT_LITERAL);
      addEEnumLiteral(typeTypeEEnum, TypeType.INTEGER_LITERAL);
      addEEnumLiteral(typeTypeEEnum, TypeType.REFERENCE_LITERAL);
      addEEnumLiteral(typeTypeEEnum, TypeType.DATETIME_LITERAL);
      addEEnumLiteral(typeTypeEEnum, TypeType.BOOLEAN_LITERAL);
      addEEnumLiteral(typeTypeEEnum, TypeType.PERFORMER_LITERAL);

      // Initialize data types
      initEDataType(modeTypeObjectEDataType, ModeType.class, "ModeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
      initEDataType(typeTypeObjectEDataType, TypeType.class, "TypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

      // Create resource
      createResource(eNS_URI);

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
	protected void createExtendedMetaDataAnnotations()
   {
      String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
      addAnnotation
        (basicTypeTypeEClass, 
         source, 
         new String[] 
         {
          "name", "BasicType_._type",
          "kind", "empty"
         });		
      addAnnotation
        (getBasicTypeType_Type(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Type"
         });		
      addAnnotation
        (dataTypeTypeEClass, 
         source, 
         new String[] 
         {
          "name", "DataType_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getDataTypeType_BasicType(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "BasicType",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getDataTypeType_DeclaredType(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "DeclaredType",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getDataTypeType_SchemaType(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "SchemaType",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getDataTypeType_ExternalReference(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "ExternalReference",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getDataTypeType_CarnotType(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "carnotType",
          "namespace", "http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions"
         });		
      addAnnotation
        (declaredTypeTypeEClass, 
         source, 
         new String[] 
         {
          "name", "DeclaredType_._type",
          "kind", "empty"
         });		
      addAnnotation
        (getDeclaredTypeType_Id(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Id"
         });		
      addAnnotation
        (extendedAttributesTypeEClass, 
         source, 
         new String[] 
         {
          "name", "ExtendedAttributes_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getExtendedAttributesType_ExtendedAttribute(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "ExtendedAttribute",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (extendedAttributeTypeEClass, 
         source, 
         new String[] 
         {
          "name", "ExtendedAttribute_._type",
          "kind", "mixed"
         });		
      addAnnotation
        (getExtendedAttributeType_ExtendedAnnotation(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "ExtendedAnnotation",
          "namespace", "http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions"
         });		
      addAnnotation
        (getExtendedAttributeType_Mixed(), 
         source, 
         new String[] 
         {
          "kind", "elementWildcard",
          "name", ":mixed"
         });		
      addAnnotation
        (getExtendedAttributeType_Group(), 
         source, 
         new String[] 
         {
          "kind", "group",
          "name", "group:1"
         });		
      addAnnotation
        (getExtendedAttributeType_Any(), 
         source, 
         new String[] 
         {
          "kind", "elementWildcard",
          "wildcards", "##any",
          "name", ":2",
          "processing", "lax",
          "group", "#group:1"
         });		
      addAnnotation
        (getExtendedAttributeType_Name(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Name"
         });		
      addAnnotation
        (getExtendedAttributeType_Value(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Value"
         });		
      addAnnotation
        (getExtensible_ExtendedAttributes(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "ExtendedAttributes",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (externalPackagesEClass, 
         source, 
         new String[] 
         {
          "name", "ExternalPackages_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getExternalPackages_ExternalPackage(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "ExternalPackage",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (externalPackageEClass, 
         source, 
         new String[] 
         {
          "name", "ExternalPackage_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getExternalPackage_Href(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "href"
         });		
      addAnnotation
        (getExternalPackage_Id(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Id"
         });		
      addAnnotation
        (getExternalPackage_Name(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Name"
         });		
      addAnnotation
        (externalReferenceTypeEClass, 
         source, 
         new String[] 
         {
          "name", "ExternalReference_._type",
          "kind", "empty"
         });		
      addAnnotation
        (getExternalReferenceType_Location(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "location"
         });		
      addAnnotation
        (getExternalReferenceType_Namespace(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "namespace"
         });		
      addAnnotation
        (getExternalReferenceType_Xref(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "xref"
         });		
      addAnnotation
        (formalParametersTypeEClass, 
         source, 
         new String[] 
         {
          "name", "FormalParameters_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getFormalParametersType_FormalParameter(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "FormalParameter",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (formalParameterTypeEClass, 
         source, 
         new String[] 
         {
          "name", "FormalParameter_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getFormalParameterType_DataType(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "DataType",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getFormalParameterType_Description(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "Description",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getFormalParameterType_Id(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Id"
         });		
      addAnnotation
        (getFormalParameterType_Mode(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Mode"
         });		
      addAnnotation
        (getFormalParameterType_Name(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Name"
         });		
      addAnnotation
        (schemaTypeTypeEClass, 
         source, 
         new String[] 
         {
          "name", "SchemaType_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getSchemaTypeType_Schema(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "schema",
          "namespace", "http://www.w3.org/2001/XMLSchema"
         });		
      addAnnotation
        (scriptTypeEClass, 
         source, 
         new String[] 
         {
          "name", "Script_._type",
          "kind", "empty"
         });		
      addAnnotation
        (getScriptType_Grammar(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Grammar"
         });		
      addAnnotation
        (getScriptType_Type(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Type"
         });		
      addAnnotation
        (getScriptType_Version(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Version"
         });		
      addAnnotation
        (typeDeclarationsTypeEClass, 
         source, 
         new String[] 
         {
          "name", "TypeDeclarations_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getTypeDeclarationsType_TypeDeclaration(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "TypeDeclaration",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (typeDeclarationTypeEClass, 
         source, 
         new String[] 
         {
          "name", "TypeDeclaration_._type",
          "kind", "elementOnly"
         });		
      addAnnotation
        (getTypeDeclarationType_BasicType(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "BasicType",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getTypeDeclarationType_DeclaredType(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "DeclaredType",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getTypeDeclarationType_SchemaType(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "SchemaType",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getTypeDeclarationType_ExternalReference(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "ExternalReference",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getTypeDeclarationType_Description(), 
         source, 
         new String[] 
         {
          "kind", "element",
          "name", "Description",
          "namespace", "##targetNamespace"
         });		
      addAnnotation
        (getTypeDeclarationType_Id(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Id"
         });		
      addAnnotation
        (getTypeDeclarationType_Name(), 
         source, 
         new String[] 
         {
          "kind", "attribute",
          "name", "Name"
         });		
      addAnnotation
        (modeTypeEEnum, 
         source, 
         new String[] 
         {
          "name", "Mode_._type"
         });		
      addAnnotation
        (typeTypeEEnum, 
         source, 
         new String[] 
         {
          "name", "Type_._type"
         });		
      addAnnotation
        (modeTypeObjectEDataType, 
         source, 
         new String[] 
         {
          "name", "Mode_._type:Object",
          "baseType", "Mode_._type"
         });		
      addAnnotation
        (typeTypeObjectEDataType, 
         source, 
         new String[] 
         {
          "name", "Type_._type:Object",
          "baseType", "Type_._type"
         });
   }

} //XpdlPackageImpl
