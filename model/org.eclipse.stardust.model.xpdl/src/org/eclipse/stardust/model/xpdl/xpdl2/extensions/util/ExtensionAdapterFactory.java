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


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.*;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDRedefineContent;
import org.eclipse.xsd.XSDSchemaContent;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage
 * @generated
 */
public class ExtensionAdapterFactory extends AdapterFactoryImpl {
	/**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

	/**
    * The cached model package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected static ExtensionPackage modelPackage;

	/**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public ExtensionAdapterFactory() {
      if (modelPackage == null)
      {
         modelPackage = ExtensionPackage.eINSTANCE;
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
	protected ExtensionSwitch<Adapter> modelSwitch =
		new ExtensionSwitch<Adapter>()
      {
         @Override
         public Adapter caseExtendedAnnotationType(ExtendedAnnotationType object)
         {
            return createExtendedAnnotationTypeAdapter();
         }
         @Override
         public Adapter caseLoopDataRefType(LoopDataRefType object)
         {
            return createLoopDataRefTypeAdapter();
         }
         @Override
         public Adapter caseXSDConcreteComponent(XSDConcreteComponent object)
         {
            return createXSDConcreteComponentAdapter();
         }
         @Override
         public Adapter caseXSDComponent(XSDComponent object)
         {
            return createXSDComponentAdapter();
         }
         @Override
         public Adapter caseXSDSchemaContent(XSDSchemaContent object)
         {
            return createXSDSchemaContentAdapter();
         }
         @Override
         public Adapter caseXSDRedefineContent(XSDRedefineContent object)
         {
            return createXSDRedefineContentAdapter();
         }
         @Override
         public Adapter caseXSDAnnotation(XSDAnnotation object)
         {
            return createXSDAnnotationAdapter();
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
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType <em>Extended Annotation Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtendedAnnotationType
    * @generated
    */
	public Adapter createExtendedAnnotationTypeAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType <em>Loop Data Ref Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType
    * @generated
    */
   public Adapter createLoopDataRefTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDConcreteComponent <em>Concrete Component</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.xsd.XSDConcreteComponent
    * @generated
    */
	public Adapter createXSDConcreteComponentAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDComponent <em>Component</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.xsd.XSDComponent
    * @generated
    */
	public Adapter createXSDComponentAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDSchemaContent <em>Schema Content</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.xsd.XSDSchemaContent
    * @generated
    */
	public Adapter createXSDSchemaContentAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDRedefineContent <em>Redefine Content</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.xsd.XSDRedefineContent
    * @generated
    */
	public Adapter createXSDRedefineContentAdapter() {
      return null;
   }

	/**
    * Creates a new adapter for an object of class '{@link org.eclipse.xsd.XSDAnnotation <em>Annotation</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.xsd.XSDAnnotation
    * @generated
    */
	public Adapter createXSDAnnotationAdapter() {
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

} //ExtensionAdapterFactory
