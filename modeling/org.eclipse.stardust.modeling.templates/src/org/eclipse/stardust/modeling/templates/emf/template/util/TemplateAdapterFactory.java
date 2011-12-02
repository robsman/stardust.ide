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


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.modeling.templates.emf.template.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatePackage
 * @generated
 */
public class TemplateAdapterFactory extends AdapterFactoryImpl {
	/**
    * The cached model package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected static TemplatePackage modelPackage;

	/**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public TemplateAdapterFactory() {
      if (modelPackage == null)
      {
         modelPackage = TemplatePackage.eINSTANCE;
      }
   }

	/**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
	@Override
   public boolean isFactoryForType(Object object) {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject)object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

	/**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected TemplateSwitch<Adapter> modelSwitch =
		new TemplateSwitch<Adapter>()
      {
         @Override
         public Adapter caseDocumentationType(DocumentationType object)
         {
            return createDocumentationTypeAdapter();
         }
         @Override
         public Adapter caseFeatureType(FeatureType object)
         {
            return createFeatureTypeAdapter();
         }
         @Override
         public Adapter caseParameterType(ParameterType object)
         {
            return createParameterTypeAdapter();
         }
         @Override
         public Adapter caseReferenceType(ReferenceType object)
         {
            return createReferenceTypeAdapter();
         }
         @Override
         public Adapter caseRootsType(RootsType object)
         {
            return createRootsTypeAdapter();
         }
         @Override
         public Adapter caseTemplateType(TemplateType object)
         {
            return createTemplateTypeAdapter();
         }
         @Override
         public Adapter caseTemplatesType(TemplatesType object)
         {
            return createTemplatesTypeAdapter();
         }
         @Override
         public Adapter caseTemplateLibraryType(TemplateLibraryType object)
         {
            return createTemplateLibraryTypeAdapter();
         }
         @Override
         public Adapter defaultCase(EObject object)
         {
            return createEObjectAdapter();
         }
      };

	/**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
	@Override
   public Adapter createAdapter(Notifier target) {
      return modelSwitch.doSwitch((EObject)target);
   }


	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.DocumentationType <em>Documentation Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.DocumentationType
    * @generated
    */
	public Adapter createDocumentationTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.FeatureType <em>Feature Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.FeatureType
    * @generated
    */
	public Adapter createFeatureTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.ParameterType <em>Parameter Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ParameterType
    * @generated
    */
	public Adapter createParameterTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.ReferenceType <em>Reference Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.ReferenceType
    * @generated
    */
	public Adapter createReferenceTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.RootsType <em>Roots Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.RootsType
    * @generated
    */
	public Adapter createRootsTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateType <em>Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateType
    * @generated
    */
	public Adapter createTemplateTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplatesType <em>Templates Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplatesType
    * @generated
    */
	public Adapter createTemplatesTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType <em>Library Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.modeling.templates.emf.template.TemplateLibraryType
    * @generated
    */
	public Adapter createTemplateLibraryTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @generated
    */
	public Adapter createEObjectAdapter() {
      return null;
   }

} //TemplateAdapterFactory
