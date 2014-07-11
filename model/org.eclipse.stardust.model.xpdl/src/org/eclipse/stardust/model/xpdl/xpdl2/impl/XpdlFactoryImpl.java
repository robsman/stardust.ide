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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.stardust.model.xpdl.xpdl2.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XpdlFactoryImpl extends EFactoryImpl implements XpdlFactory
{
   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public static XpdlFactory init()
   {
      try
      {
         XpdlFactory theXpdlFactory = (XpdlFactory)EPackage.Registry.INSTANCE.getEFactory(XpdlPackage.eNS_URI);
         if (theXpdlFactory != null)
         {
            return theXpdlFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new XpdlFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public XpdlFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case XpdlPackage.BASIC_TYPE_TYPE: return createBasicTypeType();
         case XpdlPackage.DATA_TYPE_TYPE: return createDataTypeType();
         case XpdlPackage.DECLARED_TYPE_TYPE: return createDeclaredTypeType();
         case XpdlPackage.EXPRESSION_TYPE: return createExpressionType();
         case XpdlPackage.EXTENDED_ATTRIBUTES_TYPE: return createExtendedAttributesType();
         case XpdlPackage.EXTENDED_ATTRIBUTE_TYPE: return createExtendedAttributeType();
         case XpdlPackage.EXTERNAL_PACKAGES: return createExternalPackages();
         case XpdlPackage.EXTERNAL_PACKAGE: return createExternalPackage();
         case XpdlPackage.EXTERNAL_REFERENCE_TYPE: return createExternalReferenceType();
         case XpdlPackage.FORMAL_PARAMETERS_TYPE: return createFormalParametersType();
         case XpdlPackage.FORMAL_PARAMETER_TYPE: return createFormalParameterType();
         case XpdlPackage.LOOP_MULTI_INSTANCE_TYPE: return createLoopMultiInstanceType();
         case XpdlPackage.LOOP_STANDARD_TYPE: return createLoopStandardType();
         case XpdlPackage.LOOP_TYPE: return createLoopType();
         case XpdlPackage.SCHEMA_TYPE_TYPE: return createSchemaTypeType();
         case XpdlPackage.SCRIPT_TYPE: return createScriptType();
         case XpdlPackage.TYPE_DECLARATIONS_TYPE: return createTypeDeclarationsType();
         case XpdlPackage.TYPE_DECLARATION_TYPE: return createTypeDeclarationType();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object createFromString(EDataType eDataType, String initialValue)
   {
      switch (eDataType.getClassifierID())
      {
         case XpdlPackage.LOOP_TYPE_TYPE:
            return createLoopTypeTypeFromString(eDataType, initialValue);
         case XpdlPackage.MI_FLOW_CONDITION_TYPE:
            return createMIFlowConditionTypeFromString(eDataType, initialValue);
         case XpdlPackage.MI_ORDERING_TYPE:
            return createMIOrderingTypeFromString(eDataType, initialValue);
         case XpdlPackage.MODE_TYPE:
            return createModeTypeFromString(eDataType, initialValue);
         case XpdlPackage.TEST_TIME_TYPE:
            return createTestTimeTypeFromString(eDataType, initialValue);
         case XpdlPackage.TYPE_TYPE:
            return createTypeTypeFromString(eDataType, initialValue);
         case XpdlPackage.LOOP_TYPE_TYPE_OBJECT:
            return createLoopTypeTypeObjectFromString(eDataType, initialValue);
         case XpdlPackage.MI_FLOW_CONDITION_TYPE_OBJECT:
            return createMIFlowConditionTypeObjectFromString(eDataType, initialValue);
         case XpdlPackage.MI_ORDERING_TYPE_OBJECT:
            return createMIOrderingTypeObjectFromString(eDataType, initialValue);
         case XpdlPackage.MODE_TYPE_OBJECT:
            return createModeTypeObjectFromString(eDataType, initialValue);
         case XpdlPackage.TEST_TIME_TYPE_OBJECT:
            return createTestTimeTypeObjectFromString(eDataType, initialValue);
         case XpdlPackage.TYPE_TYPE_OBJECT:
            return createTypeTypeObjectFromString(eDataType, initialValue);
         default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String convertToString(EDataType eDataType, Object instanceValue)
   {
      switch (eDataType.getClassifierID())
      {
         case XpdlPackage.LOOP_TYPE_TYPE:
            return convertLoopTypeTypeToString(eDataType, instanceValue);
         case XpdlPackage.MI_FLOW_CONDITION_TYPE:
            return convertMIFlowConditionTypeToString(eDataType, instanceValue);
         case XpdlPackage.MI_ORDERING_TYPE:
            return convertMIOrderingTypeToString(eDataType, instanceValue);
         case XpdlPackage.MODE_TYPE:
            return convertModeTypeToString(eDataType, instanceValue);
         case XpdlPackage.TEST_TIME_TYPE:
            return convertTestTimeTypeToString(eDataType, instanceValue);
         case XpdlPackage.TYPE_TYPE:
            return convertTypeTypeToString(eDataType, instanceValue);
         case XpdlPackage.LOOP_TYPE_TYPE_OBJECT:
            return convertLoopTypeTypeObjectToString(eDataType, instanceValue);
         case XpdlPackage.MI_FLOW_CONDITION_TYPE_OBJECT:
            return convertMIFlowConditionTypeObjectToString(eDataType, instanceValue);
         case XpdlPackage.MI_ORDERING_TYPE_OBJECT:
            return convertMIOrderingTypeObjectToString(eDataType, instanceValue);
         case XpdlPackage.MODE_TYPE_OBJECT:
            return convertModeTypeObjectToString(eDataType, instanceValue);
         case XpdlPackage.TEST_TIME_TYPE_OBJECT:
            return convertTestTimeTypeObjectToString(eDataType, instanceValue);
         case XpdlPackage.TYPE_TYPE_OBJECT:
            return convertTypeTypeObjectToString(eDataType, instanceValue);
         default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public BasicTypeType createBasicTypeType()
   {
      BasicTypeTypeImpl basicTypeType = new BasicTypeTypeImpl();
      return basicTypeType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public DataTypeType createDataTypeType()
   {
      DataTypeTypeImpl dataTypeType = new DataTypeTypeImpl();
      return dataTypeType;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public DeclaredTypeType createDeclaredTypeType()
   {
      DeclaredTypeTypeImpl declaredTypeType = new DeclaredTypeTypeImpl();
      return declaredTypeType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExpressionType createExpressionType()
   {
      ExpressionTypeImpl expressionType = new ExpressionTypeImpl();
      return expressionType;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public ExtendedAttributesType createExtendedAttributesType()
   {
      ExtendedAttributesTypeImpl extendedAttributesType = new ExtendedAttributesTypeImpl();
      return extendedAttributesType;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public ExtendedAttributeType createExtendedAttributeType()
   {
      ExtendedAttributeTypeImpl extendedAttributeType = new ExtendedAttributeTypeImpl();
      return extendedAttributeType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExternalPackages createExternalPackages()
   {
      ExternalPackagesImpl externalPackages = new ExternalPackagesImpl();
      return externalPackages;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExternalPackage createExternalPackage()
   {
      ExternalPackageImpl externalPackage = new ExternalPackageImpl();
      return externalPackage;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public ExternalReferenceType createExternalReferenceType()
   {
      ExternalReferenceTypeImpl externalReferenceType = new ExternalReferenceTypeImpl();
      return externalReferenceType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FormalParameterType createFormalParameterType()
   {
      FormalParameterTypeImpl formalParameterType = new FormalParameterTypeImpl();
      return formalParameterType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopMultiInstanceType createLoopMultiInstanceType()
   {
      LoopMultiInstanceTypeImpl loopMultiInstanceType = new LoopMultiInstanceTypeImpl();
      return loopMultiInstanceType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopStandardType createLoopStandardType()
   {
      LoopStandardTypeImpl loopStandardType = new LoopStandardTypeImpl();
      return loopStandardType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopType createLoopType()
   {
      LoopTypeImpl loopType = new LoopTypeImpl();
      return loopType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FormalParametersType createFormalParametersType()
   {
      FormalParametersTypeImpl formalParametersType = new FormalParametersTypeImpl();
      return formalParametersType;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public SchemaTypeType createSchemaTypeType()
   {
      SchemaTypeTypeImpl schemaTypeType = new SchemaTypeTypeImpl();
      return schemaTypeType;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public ScriptType createScriptType()
   {
      ScriptTypeImpl scriptType = new ScriptTypeImpl();
      return scriptType;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public TypeDeclarationsType createTypeDeclarationsType()
   {
      TypeDeclarationsTypeImpl typeDeclarationsType = new TypeDeclarationsTypeImpl();
      return typeDeclarationsType;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public TypeDeclarationType createTypeDeclarationType()
   {
      TypeDeclarationTypeImpl typeDeclarationType = new TypeDeclarationTypeImpl();
      return typeDeclarationType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopTypeType createLoopTypeTypeFromString(EDataType eDataType, String initialValue)
   {
      LoopTypeType result = LoopTypeType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertLoopTypeTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MIFlowConditionType createMIFlowConditionTypeFromString(EDataType eDataType, String initialValue)
   {
      MIFlowConditionType result = MIFlowConditionType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertMIFlowConditionTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MIOrderingType createMIOrderingTypeFromString(EDataType eDataType, String initialValue)
   {
      MIOrderingType result = MIOrderingType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertMIOrderingTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ModeType createModeTypeFromString(EDataType eDataType, String initialValue)
   {
      ModeType result = ModeType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertModeTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TestTimeType createTestTimeTypeFromString(EDataType eDataType, String initialValue)
   {
      TestTimeType result = TestTimeType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertTestTimeTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public TypeType createTypeTypeFromString(EDataType eDataType, String initialValue)
   {
      TypeType result = TypeType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public String convertTypeTypeToString(EDataType eDataType, Object instanceValue)
   {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public LoopTypeType createLoopTypeTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createLoopTypeTypeFromString(XpdlPackage.Literals.LOOP_TYPE_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertLoopTypeTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertLoopTypeTypeToString(XpdlPackage.Literals.LOOP_TYPE_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MIFlowConditionType createMIFlowConditionTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createMIFlowConditionTypeFromString(XpdlPackage.Literals.MI_FLOW_CONDITION_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertMIFlowConditionTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertMIFlowConditionTypeToString(XpdlPackage.Literals.MI_FLOW_CONDITION_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public MIOrderingType createMIOrderingTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createMIOrderingTypeFromString(XpdlPackage.Literals.MI_ORDERING_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertMIOrderingTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertMIOrderingTypeToString(XpdlPackage.Literals.MI_ORDERING_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ModeType createModeTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createModeTypeFromString(XpdlPackage.Literals.MODE_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertModeTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertModeTypeToString(XpdlPackage.Literals.MODE_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public TestTimeType createTestTimeTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createTestTimeTypeFromString(XpdlPackage.Literals.TEST_TIME_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String convertTestTimeTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertTestTimeTypeToString(XpdlPackage.Literals.TEST_TIME_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public TypeType createTypeTypeObjectFromString(EDataType eDataType, String initialValue)
   {
      return createTypeTypeFromString(XpdlPackage.Literals.TYPE_TYPE, initialValue);
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public String convertTypeTypeObjectToString(EDataType eDataType, Object instanceValue)
   {
      return convertTypeTypeToString(XpdlPackage.Literals.TYPE_TYPE, instanceValue);
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @generated
    */
   public XpdlPackage getXpdlPackage()
   {
      return (XpdlPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   @Deprecated
   public static XpdlPackage getPackage()
   {
      return XpdlPackage.eINSTANCE;
   }

} //XpdlFactoryImpl
