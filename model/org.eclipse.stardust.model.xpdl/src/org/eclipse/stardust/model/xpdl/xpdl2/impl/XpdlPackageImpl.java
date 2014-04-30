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

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.stardust.model.xpdl.xpdl2.*;
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
   public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

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
   private EClass expressionTypeEClass = null;

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
   private EClass loopMultiInstanceTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass loopStandardTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass loopTypeEClass = null;

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
   private EEnum loopTypeTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EEnum miFlowConditionTypeEEnum = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EEnum miOrderingTypeEEnum = null;

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
   private EEnum testTimeTypeEEnum = null;

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
   private EDataType loopTypeTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EDataType miFlowConditionTypeObjectEDataType = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EDataType miOrderingTypeObjectEDataType = null;

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
   private EDataType testTimeTypeObjectEDataType = null;

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
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    *
    * <p>This method is used to initialize {@link XpdlPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
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
      XpdlPackageImpl theXpdlPackage = (XpdlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof XpdlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XpdlPackageImpl());

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


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(XpdlPackage.eNS_URI, theXpdlPackage);
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
   public EClass getExpressionType()
   {
      return expressionTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExpressionType_Mixed()
   {
      return (EAttribute)expressionTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExpressionType_Group()
   {
      return (EAttribute)expressionTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExpressionType_Any()
   {
      return (EAttribute)expressionTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExpressionType_ScriptGrammar()
   {
      return (EAttribute)expressionTypeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExpressionType_ScriptType()
   {
      return (EAttribute)expressionTypeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getExpressionType_ScriptVersion()
   {
      return (EAttribute)expressionTypeEClass.getEStructuralFeatures().get(5);
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
   public EClass getLoopMultiInstanceType()
   {
      return loopMultiInstanceTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getLoopMultiInstanceType_MICondition()
   {
      return (EReference)loopMultiInstanceTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getLoopMultiInstanceType_ComplexMIFlowCondition()
   {
      return (EReference)loopMultiInstanceTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getLoopMultiInstanceType_LoopDataRef()
   {
      return (EReference)loopMultiInstanceTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopMultiInstanceType_MIFlowCondition()
   {
      return (EAttribute)loopMultiInstanceTypeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopMultiInstanceType_MIOrdering()
   {
      return (EAttribute)loopMultiInstanceTypeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getLoopStandardType()
   {
      return loopStandardTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getLoopStandardType_LoopCondition()
   {
      return (EReference)loopStandardTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopStandardType_LoopMaximum()
   {
      return (EAttribute)loopStandardTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopStandardType_TestTime()
   {
      return (EAttribute)loopStandardTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getLoopType()
   {
      return loopTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getLoopType_LoopStandard()
   {
      return (EReference)loopTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getLoopType_LoopMultiInstance()
   {
      return (EReference)loopTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getLoopType_LoopType()
   {
      return (EAttribute)loopTypeEClass.getEStructuralFeatures().get(2);
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
   public EEnum getLoopTypeType()
   {
      return loopTypeTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EEnum getMIFlowConditionType()
   {
      return miFlowConditionTypeEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EEnum getMIOrderingType()
   {
      return miOrderingTypeEEnum;
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
   public EEnum getTestTimeType()
   {
      return testTimeTypeEEnum;
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
   public EDataType getLoopTypeTypeObject()
   {
      return loopTypeTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getMIFlowConditionTypeObject()
   {
      return miFlowConditionTypeObjectEDataType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EDataType getMIOrderingTypeObject()
   {
      return miOrderingTypeObjectEDataType;
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
   public EDataType getTestTimeTypeObject()
   {
      return testTimeTypeObjectEDataType;
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

      expressionTypeEClass = createEClass(EXPRESSION_TYPE);
      createEAttribute(expressionTypeEClass, EXPRESSION_TYPE__MIXED);
      createEAttribute(expressionTypeEClass, EXPRESSION_TYPE__GROUP);
      createEAttribute(expressionTypeEClass, EXPRESSION_TYPE__ANY);
      createEAttribute(expressionTypeEClass, EXPRESSION_TYPE__SCRIPT_GRAMMAR);
      createEAttribute(expressionTypeEClass, EXPRESSION_TYPE__SCRIPT_TYPE);
      createEAttribute(expressionTypeEClass, EXPRESSION_TYPE__SCRIPT_VERSION);

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

      loopMultiInstanceTypeEClass = createEClass(LOOP_MULTI_INSTANCE_TYPE);
      createEReference(loopMultiInstanceTypeEClass, LOOP_MULTI_INSTANCE_TYPE__MI_CONDITION);
      createEReference(loopMultiInstanceTypeEClass, LOOP_MULTI_INSTANCE_TYPE__COMPLEX_MI_FLOW_CONDITION);
      createEReference(loopMultiInstanceTypeEClass, LOOP_MULTI_INSTANCE_TYPE__LOOP_DATA_REF);
      createEAttribute(loopMultiInstanceTypeEClass, LOOP_MULTI_INSTANCE_TYPE__MI_FLOW_CONDITION);
      createEAttribute(loopMultiInstanceTypeEClass, LOOP_MULTI_INSTANCE_TYPE__MI_ORDERING);

      loopStandardTypeEClass = createEClass(LOOP_STANDARD_TYPE);
      createEReference(loopStandardTypeEClass, LOOP_STANDARD_TYPE__LOOP_CONDITION);
      createEAttribute(loopStandardTypeEClass, LOOP_STANDARD_TYPE__LOOP_MAXIMUM);
      createEAttribute(loopStandardTypeEClass, LOOP_STANDARD_TYPE__TEST_TIME);

      loopTypeEClass = createEClass(LOOP_TYPE);
      createEReference(loopTypeEClass, LOOP_TYPE__LOOP_STANDARD);
      createEReference(loopTypeEClass, LOOP_TYPE__LOOP_MULTI_INSTANCE);
      createEAttribute(loopTypeEClass, LOOP_TYPE__LOOP_TYPE);

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
      loopTypeTypeEEnum = createEEnum(LOOP_TYPE_TYPE);
      miFlowConditionTypeEEnum = createEEnum(MI_FLOW_CONDITION_TYPE);
      miOrderingTypeEEnum = createEEnum(MI_ORDERING_TYPE);
      modeTypeEEnum = createEEnum(MODE_TYPE);
      testTimeTypeEEnum = createEEnum(TEST_TIME_TYPE);
      typeTypeEEnum = createEEnum(TYPE_TYPE);

      // Create data types
      loopTypeTypeObjectEDataType = createEDataType(LOOP_TYPE_TYPE_OBJECT);
      miFlowConditionTypeObjectEDataType = createEDataType(MI_FLOW_CONDITION_TYPE_OBJECT);
      miOrderingTypeObjectEDataType = createEDataType(MI_ORDERING_TYPE_OBJECT);
      modeTypeObjectEDataType = createEDataType(MODE_TYPE_OBJECT);
      testTimeTypeObjectEDataType = createEDataType(TEST_TIME_TYPE_OBJECT);
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

      initEClass(expressionTypeEClass, ExpressionType.class, "ExpressionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getExpressionType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, ExpressionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExpressionType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, ExpressionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
      initEAttribute(getExpressionType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, ExpressionType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
      initEAttribute(getExpressionType_ScriptGrammar(), theXMLTypePackage.getAnyURI(), "scriptGrammar", null, 0, 1, ExpressionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExpressionType_ScriptType(), theXMLTypePackage.getString(), "scriptType", null, 0, 1, ExpressionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExpressionType_ScriptVersion(), theXMLTypePackage.getString(), "scriptVersion", null, 0, 1, ExpressionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

      initEClass(loopMultiInstanceTypeEClass, LoopMultiInstanceType.class, "LoopMultiInstanceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getLoopMultiInstanceType_MICondition(), this.getExpressionType(), null, "mICondition", null, 0, 1, LoopMultiInstanceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getLoopMultiInstanceType_ComplexMIFlowCondition(), this.getExpressionType(), null, "complexMIFlowCondition", null, 0, 1, LoopMultiInstanceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getLoopMultiInstanceType_LoopDataRef(), theExtensionPackage.getLoopDataRefType(), null, "loopDataRef", null, 0, 1, LoopMultiInstanceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLoopMultiInstanceType_MIFlowCondition(), this.getMIFlowConditionType(), "mIFlowCondition", "All", 0, 1, LoopMultiInstanceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLoopMultiInstanceType_MIOrdering(), this.getMIOrderingType(), "mIOrdering", null, 1, 1, LoopMultiInstanceType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(loopStandardTypeEClass, LoopStandardType.class, "LoopStandardType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getLoopStandardType_LoopCondition(), this.getExpressionType(), null, "loopCondition", null, 0, 1, LoopStandardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLoopStandardType_LoopMaximum(), theXMLTypePackage.getInteger(), "loopMaximum", null, 0, 1, LoopStandardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLoopStandardType_TestTime(), this.getTestTimeType(), "testTime", null, 1, 1, LoopStandardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(loopTypeEClass, LoopType.class, "LoopType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getLoopType_LoopStandard(), this.getLoopStandardType(), null, "loopStandard", null, 0, 1, LoopType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getLoopType_LoopMultiInstance(), this.getLoopMultiInstanceType(), null, "loopMultiInstance", null, 0, 1, LoopType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getLoopType_LoopType(), this.getLoopTypeType(), "loopType", null, 1, 1, LoopType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
      initEEnum(loopTypeTypeEEnum, LoopTypeType.class, "LoopTypeType");
      addEEnumLiteral(loopTypeTypeEEnum, LoopTypeType.STANDARD);
      addEEnumLiteral(loopTypeTypeEEnum, LoopTypeType.MULTI_INSTANCE);

      initEEnum(miFlowConditionTypeEEnum, MIFlowConditionType.class, "MIFlowConditionType");
      addEEnumLiteral(miFlowConditionTypeEEnum, MIFlowConditionType.NONE);
      addEEnumLiteral(miFlowConditionTypeEEnum, MIFlowConditionType.ONE);
      addEEnumLiteral(miFlowConditionTypeEEnum, MIFlowConditionType.ALL);
      addEEnumLiteral(miFlowConditionTypeEEnum, MIFlowConditionType.COMPLEX);

      initEEnum(miOrderingTypeEEnum, MIOrderingType.class, "MIOrderingType");
      addEEnumLiteral(miOrderingTypeEEnum, MIOrderingType.SEQUENTIAL);
      addEEnumLiteral(miOrderingTypeEEnum, MIOrderingType.PARALLEL);

      initEEnum(modeTypeEEnum, ModeType.class, "ModeType");
      addEEnumLiteral(modeTypeEEnum, ModeType.IN);
      addEEnumLiteral(modeTypeEEnum, ModeType.OUT);
      addEEnumLiteral(modeTypeEEnum, ModeType.INOUT);

      initEEnum(testTimeTypeEEnum, TestTimeType.class, "TestTimeType");
      addEEnumLiteral(testTimeTypeEEnum, TestTimeType.BEFORE);
      addEEnumLiteral(testTimeTypeEEnum, TestTimeType.AFTER);

      initEEnum(typeTypeEEnum, TypeType.class, "TypeType");
      addEEnumLiteral(typeTypeEEnum, TypeType.STRING);
      addEEnumLiteral(typeTypeEEnum, TypeType.FLOAT);
      addEEnumLiteral(typeTypeEEnum, TypeType.INTEGER);
      addEEnumLiteral(typeTypeEEnum, TypeType.REFERENCE);
      addEEnumLiteral(typeTypeEEnum, TypeType.DATETIME);
      addEEnumLiteral(typeTypeEEnum, TypeType.BOOLEAN);
      addEEnumLiteral(typeTypeEEnum, TypeType.PERFORMER);

      // Initialize data types
      initEDataType(loopTypeTypeObjectEDataType, LoopTypeType.class, "LoopTypeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
      initEDataType(miFlowConditionTypeObjectEDataType, MIFlowConditionType.class, "MIFlowConditionTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
      initEDataType(miOrderingTypeObjectEDataType, MIOrderingType.class, "MIOrderingTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
      initEDataType(modeTypeObjectEDataType, ModeType.class, "ModeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
      initEDataType(testTimeTypeObjectEDataType, TestTimeType.class, "TestTimeTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
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
        (expressionTypeEClass,
         source,
         new String[]
         {
          "name", "ExpressionType",
          "kind", "mixed"
         });
      addAnnotation
        (getExpressionType_Mixed(),
         source,
         new String[]
         {
          "kind", "elementWildcard",
          "name", ":mixed"
         });
      addAnnotation
        (getExpressionType_Group(),
         source,
         new String[]
         {
          "kind", "group",
          "name", "group:1"
         });
      addAnnotation
        (getExpressionType_Any(),
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
        (getExpressionType_ScriptGrammar(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "ScriptGrammar"
         });
      addAnnotation
        (getExpressionType_ScriptType(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "ScriptType"
         });
      addAnnotation
        (getExpressionType_ScriptVersion(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "ScriptVersion"
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
        (loopMultiInstanceTypeEClass,
         source,
         new String[]
         {
          "name", "LoopMultiInstance_._type",
          "kind", "elementOnly"
         });
      addAnnotation
        (getLoopMultiInstanceType_MICondition(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "MI_Condition",
          "namespace", "##targetNamespace"
         });
      addAnnotation
        (getLoopMultiInstanceType_ComplexMIFlowCondition(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "ComplexMI_FlowCondition",
          "namespace", "##targetNamespace"
         });
      addAnnotation
        (getLoopMultiInstanceType_LoopDataRef(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "LoopDataRef",
          "namespace", "http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions"
         });
      addAnnotation
        (getLoopMultiInstanceType_MIFlowCondition(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "MI_FlowCondition"
         });
      addAnnotation
        (getLoopMultiInstanceType_MIOrdering(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "MI_Ordering"
         });
      addAnnotation
        (loopStandardTypeEClass,
         source,
         new String[]
         {
          "name", "LoopStandard_._type",
          "kind", "elementOnly"
         });
      addAnnotation
        (getLoopStandardType_LoopCondition(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "LoopCondition",
          "namespace", "##targetNamespace"
         });
      addAnnotation
        (getLoopStandardType_LoopMaximum(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "LoopMaximum"
         });
      addAnnotation
        (getLoopStandardType_TestTime(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "TestTime"
         });
      addAnnotation
        (loopTypeEClass,
         source,
         new String[]
         {
          "name", "Loop_._type",
          "kind", "elementOnly"
         });
      addAnnotation
        (getLoopType_LoopStandard(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "LoopStandard",
          "namespace", "##targetNamespace"
         });
      addAnnotation
        (getLoopType_LoopMultiInstance(),
         source,
         new String[]
         {
          "kind", "element",
          "name", "LoopMultiInstance",
          "namespace", "##targetNamespace"
         });
      addAnnotation
        (getLoopType_LoopType(),
         source,
         new String[]
         {
          "kind", "attribute",
          "name", "LoopType"
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
        (loopTypeTypeEEnum,
         source,
         new String[]
         {
          "name", "LoopType_._type"
         });
      addAnnotation
        (miFlowConditionTypeEEnum,
         source,
         new String[]
         {
          "name", "MI_FlowCondition_._type"
         });
      addAnnotation
        (miOrderingTypeEEnum,
         source,
         new String[]
         {
          "name", "MI_Ordering_._type"
         });
      addAnnotation
        (modeTypeEEnum,
         source,
         new String[]
         {
          "name", "Mode_._type"
         });
      addAnnotation
        (testTimeTypeEEnum,
         source,
         new String[]
         {
          "name", "TestTime_._type"
         });
      addAnnotation
        (typeTypeEEnum,
         source,
         new String[]
         {
          "name", "Type_._type"
         });
      addAnnotation
        (loopTypeTypeObjectEDataType,
         source,
         new String[]
         {
          "name", "LoopType_._type:Object",
          "baseType", "LoopType_._type"
         });
      addAnnotation
        (miFlowConditionTypeObjectEDataType,
         source,
         new String[]
         {
          "name", "MI_FlowCondition_._type:Object",
          "baseType", "MI_FlowCondition_._type"
         });
      addAnnotation
        (miOrderingTypeObjectEDataType,
         source,
         new String[]
         {
          "name", "MI_Ordering_._type:Object",
          "baseType", "MI_Ordering_._type"
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
        (testTimeTypeObjectEDataType,
         source,
         new String[]
         {
          "name", "TestTime_._type:Object",
          "baseType", "TestTime_._type"
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
