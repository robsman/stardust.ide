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
package org.eclipse.stardust.modeling.repository.common;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage
 * @generated
 */
public interface RepositoryFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   RepositoryFactory eINSTANCE = org.eclipse.stardust.modeling.repository.common.impl.RepositoryFactoryImpl
         .init();

   /**
    * Returns a new object of class '<em>Connection</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Connection</em>'.
    * @generated
    */
   Connection createConnection();

   /**
    * Returns a new object of class '<em>Attribute</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Attribute</em>'.
    * @generated
    */
   Attribute createAttribute();

   /**
    * Returns a new object of class '<em>Repository</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Repository</em>'.
    * @generated
    */
   Repository createRepository();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   RepositoryPackage getRepositoryPackage();

} //RepositoryFactory
