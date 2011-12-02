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

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.modeling.repository.common.Repository#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#getRepository()
 * @model
 * @generated
 */
public interface Repository extends EObject
{
   /**
    * Returns the value of the '<em><b>Connection</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.modeling.repository.common.Connection}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Connection</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Connection</em>' containment reference list.
    * @see org.eclipse.stardust.modeling.repository.common.RepositoryPackage#getRepository_Connection()
    * @model containment="true"
    * @generated
    */
   List<Connection> getConnection();

} // Repository