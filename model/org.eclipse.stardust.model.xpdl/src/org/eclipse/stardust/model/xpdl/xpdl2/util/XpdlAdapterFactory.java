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
package org.eclipse.stardust.model.xpdl.xpdl2.util;


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType;
import org.eclipse.stardust.model.xpdl.xpdl2.ScriptType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage
 * @generated
 */
public class XpdlAdapterFactory extends AdapterFactoryImpl 
{
   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public static final String copyright = "Copyright 2008 by SunGard";

   /**
    * The cached model package.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	protected static XpdlPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	public XpdlAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = XpdlPackage.eINSTANCE;
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
   public boolean isFactoryForType(Object object)
   {
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
	protected XpdlSwitch<Adapter> modelSwitch =
      new XpdlSwitch<Adapter>()
      {
         @Override
         public Adapter caseBasicTypeType(BasicTypeType object)
         {
            return createBasicTypeTypeAdapter();
         }
         @Override
         public Adapter caseDataTypeType(DataTypeType object)
         {
            return createDataTypeTypeAdapter();
         }
         @Override
         public Adapter caseDeclaredTypeType(DeclaredTypeType object)
         {
            return createDeclaredTypeTypeAdapter();
         }
         @Override
         public Adapter caseExtendedAttributesType(ExtendedAttributesType object)
         {
            return createExtendedAttributesTypeAdapter();
         }
         @Override
         public Adapter caseExtendedAttributeType(ExtendedAttributeType object)
         {
            return createExtendedAttributeTypeAdapter();
         }
         @Override
         public Adapter caseExtensible(Extensible object)
         {
            return createExtensibleAdapter();
         }
         @Override
         public Adapter caseExternalPackages(ExternalPackages object)
         {
            return createExternalPackagesAdapter();
         }
         @Override
         public Adapter caseExternalPackage(ExternalPackage object)
         {
            return createExternalPackageAdapter();
         }
         @Override
         public Adapter caseExternalReferenceType(ExternalReferenceType object)
         {
            return createExternalReferenceTypeAdapter();
         }
         @Override
         public Adapter caseFormalParametersType(FormalParametersType object)
         {
            return createFormalParametersTypeAdapter();
         }
         @Override
         public Adapter caseFormalParameterType(FormalParameterType object)
         {
            return createFormalParameterTypeAdapter();
         }
         @Override
         public Adapter caseSchemaTypeType(SchemaTypeType object)
         {
            return createSchemaTypeTypeAdapter();
         }
         @Override
         public Adapter caseScriptType(ScriptType object)
         {
            return createScriptTypeAdapter();
         }
         @Override
         public Adapter caseTypeDeclarationsType(TypeDeclarationsType object)
         {
            return createTypeDeclarationsTypeAdapter();
         }
         @Override
         public Adapter caseTypeDeclarationType(TypeDeclarationType object)
         {
            return createTypeDeclarationTypeAdapter();
         }
         @Override
         public Adapter caseXpdlTypeType(XpdlTypeType object)
         {
            return createXpdlTypeTypeAdapter();
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
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject)target);
   }


   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType <em>Basic Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.BasicTypeType
    * @generated
    */
	public Adapter createBasicTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType <em>Data Type Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DataTypeType
    * @generated
    */
   public Adapter createDataTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType <em>Declared Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.DeclaredTypeType
    * @generated
    */
	public Adapter createDeclaredTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType <em>Extended Attributes Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributesType
    * @generated
    */
	public Adapter createExtendedAttributesTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType <em>Extended Attribute Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExtendedAttributeType
    * @generated
    */
	public Adapter createExtendedAttributeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.Extensible <em>Extensible</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.Extensible
    * @generated
    */
   public Adapter createExtensibleAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages <em>External Packages</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackages
    * @generated
    */
   public Adapter createExternalPackagesAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage <em>External Package</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage
    * @generated
    */
   public Adapter createExternalPackageAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType <em>External Reference Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ExternalReferenceType
    * @generated
    */
	public Adapter createExternalReferenceTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType <em>Formal Parameter Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParameterType
    * @generated
    */
   public Adapter createFormalParameterTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType <em>Formal Parameters Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.FormalParametersType
    * @generated
    */
   public Adapter createFormalParametersTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType <em>Schema Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.SchemaTypeType
    * @generated
    */
	public Adapter createSchemaTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType <em>Script Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.ScriptType
    * @generated
    */
	public Adapter createScriptTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType <em>Type Declarations Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationsType
    * @generated
    */
	public Adapter createTypeDeclarationsTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType <em>Type Declaration Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType
    * @generated
    */
	public Adapter createTypeDeclarationTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType <em>Type Type</em>}'.
    * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlTypeType
    * @generated
    */
	public Adapter createXpdlTypeTypeAdapter()
   {
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
	public Adapter createEObjectAdapter()
   {
      return null;
   }

} //XpdlAdapterFactory
