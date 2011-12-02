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


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.stardust.modeling.templates.emf.template.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TemplateFactoryImpl extends EFactoryImpl implements TemplateFactory {
	/**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static TemplateFactory init() {
      try
      {
         TemplateFactory theTemplateFactory = (TemplateFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.carnot.ag/workflowmodel/templates"); 
         if (theTemplateFactory != null)
         {
            return theTemplateFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new TemplateFactoryImpl();
   }

	/**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateFactoryImpl() {
      super();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	@Override
   public EObject create(EClass eClass) {
      switch (eClass.getClassifierID())
      {
         case TemplatePackage.DOCUMENTATION_TYPE: return createDocumentationType();
         case TemplatePackage.FEATURE_TYPE: return createFeatureType();
         case TemplatePackage.PARAMETER_TYPE: return createParameterType();
         case TemplatePackage.REFERENCE_TYPE: return createReferenceType();
         case TemplatePackage.ROOTS_TYPE: return createRootsType();
         case TemplatePackage.TEMPLATE_TYPE: return createTemplateType();
         case TemplatePackage.TEMPLATES_TYPE: return createTemplatesType();
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE: return createTemplateLibraryType();
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
   public Object createFromString(EDataType eDataType, String initialValue) {
      switch (eDataType.getClassifierID())
      {
         case TemplatePackage.FEATURE_STYLE_TYPE:
            return createFeatureStyleTypeFromString(eDataType, initialValue);
         case TemplatePackage.SCOPE_TYPE:
            return createScopeTypeFromString(eDataType, initialValue);
         case TemplatePackage.STYLE_TYPE:
            return createStyleTypeFromString(eDataType, initialValue);
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
   public String convertToString(EDataType eDataType, Object instanceValue) {
      switch (eDataType.getClassifierID())
      {
         case TemplatePackage.FEATURE_STYLE_TYPE:
            return convertFeatureStyleTypeToString(eDataType, instanceValue);
         case TemplatePackage.SCOPE_TYPE:
            return convertScopeTypeToString(eDataType, instanceValue);
         case TemplatePackage.STYLE_TYPE:
            return convertStyleTypeToString(eDataType, instanceValue);
         default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public DocumentationType createDocumentationType() {
      DocumentationTypeImpl documentationType = new DocumentationTypeImpl();
      return documentationType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureType createFeatureType() {
      FeatureTypeImpl featureType = new FeatureTypeImpl();
      return featureType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ParameterType createParameterType() {
      ParameterTypeImpl parameterType = new ParameterTypeImpl();
      return parameterType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ReferenceType createReferenceType() {
      ReferenceTypeImpl referenceType = new ReferenceTypeImpl();
      return referenceType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public RootsType createRootsType() {
      RootsTypeImpl rootsType = new RootsTypeImpl();
      return rootsType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateType createTemplateType() {
      TemplateTypeImpl templateType = new TemplateTypeImpl();
      return templateType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplatesType createTemplatesType() {
      TemplatesTypeImpl templatesType = new TemplatesTypeImpl();
      return templatesType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateLibraryType createTemplateLibraryType() {
      TemplateLibraryTypeImpl templateLibraryType = new TemplateLibraryTypeImpl();
      return templateLibraryType;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public FeatureStyleType createFeatureStyleTypeFromString(EDataType eDataType, String initialValue) {
      FeatureStyleType result = FeatureStyleType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String convertFeatureStyleTypeToString(EDataType eDataType, Object instanceValue) {
      return instanceValue == null ? null : instanceValue.toString();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ScopeType createScopeTypeFromString(EDataType eDataType, String initialValue) {
      ScopeType result = ScopeType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String convertScopeTypeToString(EDataType eDataType, Object instanceValue) {
      return instanceValue == null ? null : instanceValue.toString();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public StyleType createStyleTypeFromString(EDataType eDataType, String initialValue) {
      StyleType result = StyleType.get(initialValue);
      if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
      return result;
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public String convertStyleTypeToString(EDataType eDataType, Object instanceValue) {
      return instanceValue == null ? null : instanceValue.toString();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplatePackage getTemplatePackage() {
      return (TemplatePackage)getEPackage();
   }

	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
	@Deprecated
   public static TemplatePackage getPackage() {
      return TemplatePackage.eINSTANCE;
   }

} //TemplateFactoryImpl
