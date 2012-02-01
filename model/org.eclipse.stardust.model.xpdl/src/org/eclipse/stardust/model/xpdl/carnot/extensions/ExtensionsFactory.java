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
package org.eclipse.stardust.model.xpdl.carnot.extensions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.extensions.ExtensionsPackage
 * @generated
 */
public interface ExtensionsFactory extends EFactory
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   ExtensionsFactory eINSTANCE = org.eclipse.stardust.model.xpdl.carnot.extensions.impl.ExtensionsFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Formal Parameter Mapping Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Formal Parameter Mapping Type</em>'.
    * @generated
    */
   FormalParameterMappingType createFormalParameterMappingType();

   /**
    * Returns a new object of class '<em>Formal Parameter Mappings Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Formal Parameter Mappings Type</em>'.
    * @generated
    */
   FormalParameterMappingsType createFormalParameterMappingsType();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   ExtensionsPackage getExtensionsPackage();

} //ExtensionsFactory
