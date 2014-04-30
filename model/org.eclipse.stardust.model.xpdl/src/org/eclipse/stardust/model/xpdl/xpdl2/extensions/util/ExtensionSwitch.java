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
package org.eclipse.stardust.model.xpdl.xpdl2.extensions.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.*;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDRedefineContent;
import org.eclipse.xsd.XSDSchemaContent;

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
 * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage
 * @generated
 */
public class ExtensionSwitch<T> extends Switch<T> {

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static ExtensionPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ExtensionSwitch() {
      if (modelPackage == null)
      {
         modelPackage = ExtensionPackage.eINSTANCE;
      }
   }

   /**
    * Checks whether this is a switch for the given package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @parameter ePackage the package in question.
    * @return whether this is a switch for the given package.
    * @generated
    */
   @Override
   protected boolean isSwitchFor(EPackage ePackage) {
      return ePackage == modelPackage;
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   @Override
   protected T doSwitch(int classifierID, EObject theEObject) {
      switch (classifierID)
      {
         case ExtensionPackage.EXTENDED_ANNOTATION_TYPE:
         {
            ExtendedAnnotationType extendedAnnotationType = (ExtendedAnnotationType)theEObject;
            T result = caseExtendedAnnotationType(extendedAnnotationType);
            if (result == null) result = caseXSDAnnotation(extendedAnnotationType);
            if (result == null) result = caseXSDComponent(extendedAnnotationType);
            if (result == null) result = caseXSDRedefineContent(extendedAnnotationType);
            if (result == null) result = caseXSDSchemaContent(extendedAnnotationType);
            if (result == null) result = caseXSDConcreteComponent(extendedAnnotationType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case ExtensionPackage.LOOP_DATA_REF_TYPE:
         {
            LoopDataRefType loopDataRefType = (LoopDataRefType)theEObject;
            T result = caseLoopDataRefType(loopDataRefType);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Extended Annotation Type</em>'.
    * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Extended Annotation Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseExtendedAnnotationType(ExtendedAnnotationType object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Loop Data Ref Type</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Loop Data Ref Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseLoopDataRefType(LoopDataRefType object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Concrete Component</em>'.
    * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Concrete Component</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseXSDConcreteComponent(XSDConcreteComponent object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
    * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Component</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseXSDComponent(XSDComponent object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Schema Content</em>'.
    * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Schema Content</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseXSDSchemaContent(XSDSchemaContent object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Redefine Content</em>'.
    * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Redefine Content</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseXSDRedefineContent(XSDRedefineContent object) {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
    * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseXSDAnnotation(XSDAnnotation object) {
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
   @Override
   public T defaultCase(EObject object) {
      return null;
   }

} //ExtensionSwitch
