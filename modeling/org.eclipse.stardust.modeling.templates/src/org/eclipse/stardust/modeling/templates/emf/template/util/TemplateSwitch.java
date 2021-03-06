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
package org.eclipse.stardust.modeling.templates.emf.template.util;


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.modeling.templates.emf.template.DocumentationType;
import org.eclipse.stardust.modeling.templates.emf.template.FeatureType;
import org.eclipse.stardust.modeling.templates.emf.template.ParameterType;
import org.eclipse.stardust.modeling.templates.emf.template.ReferenceType;
import org.eclipse.stardust.modeling.templates.emf.template.RootsType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage;
import org.eclipse.stardust.modeling.templates.emf.template.TemplateType;
import org.eclipse.stardust.modeling.templates.emf.template.TemplatesType;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage
 * @generated
 */
public class TemplateSwitch<T> {
	/**
    * The cached model package
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected static TemplatePackage modelPackage;

	/**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateSwitch() {
      if (modelPackage == null)
      {
         modelPackage = TemplatePackage.eINSTANCE;
      }
   }

	/**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
	public T doSwitch(EObject theEObject) {
      return doSwitch(theEObject.eClass(), theEObject);
   }

	/**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
      if (theEClass.eContainer() == modelPackage)
      {
         return doSwitch(theEClass.getClassifierID(), theEObject);
      }
      else
      {
         List<EClass> eSuperTypes = theEClass.getESuperTypes();
         return
            eSuperTypes.isEmpty() ?
               defaultCase(theEObject) :
               doSwitch(eSuperTypes.get(0), theEObject);
      }
   }

	/**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
	protected T doSwitch(int classifierID, EObject theEObject) {
      switch (classifierID)
      {
         case TemplatePackage.DOCUMENTATION_TYPE:
         {
            DocumentationType documentationType = (DocumentationType)theEObject;
            T result = caseDocumentationType(documentationType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TemplatePackage.FEATURE_TYPE:
         {
            FeatureType featureType = (FeatureType)theEObject;
            T result = caseFeatureType(featureType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TemplatePackage.PARAMETER_TYPE:
         {
            ParameterType parameterType = (ParameterType)theEObject;
            T result = caseParameterType(parameterType);
            if (result == null) result = caseReferenceType(parameterType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TemplatePackage.REFERENCE_TYPE:
         {
            ReferenceType referenceType = (ReferenceType)theEObject;
            T result = caseReferenceType(referenceType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TemplatePackage.ROOTS_TYPE:
         {
            RootsType rootsType = (RootsType)theEObject;
            T result = caseRootsType(rootsType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TemplatePackage.TEMPLATE_TYPE:
         {
            TemplateType templateType = (TemplateType)theEObject;
            T result = caseTemplateType(templateType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TemplatePackage.TEMPLATES_TYPE:
         {
            TemplatesType templatesType = (TemplatesType)theEObject;
            T result = caseTemplatesType(templatesType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case TemplatePackage.TEMPLATE_LIBRARY_TYPE:
         {
            TemplateLibraryType templateLibraryType = (TemplateLibraryType)theEObject;
            T result = caseTemplateLibraryType(templateLibraryType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Documentation Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Documentation Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseDocumentationType(DocumentationType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Feature Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Feature Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseFeatureType(FeatureType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Parameter Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Parameter Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseParameterType(ParameterType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Reference Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Reference Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseReferenceType(ReferenceType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Roots Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Roots Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseRootsType(RootsType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseTemplateType(TemplateType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Templates Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Templates Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseTemplatesType(TemplatesType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>Library Type</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Library Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
	public T caseTemplateLibraryType(TemplateLibraryType object) {
      return null;
   }

	/**
    * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
    * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject)
    * @generated
    */
	public T defaultCase(EObject object) {
      return null;
   }

} //TemplateSwitch
