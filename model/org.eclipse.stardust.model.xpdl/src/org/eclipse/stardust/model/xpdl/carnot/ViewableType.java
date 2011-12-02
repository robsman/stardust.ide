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
package org.eclipse.stardust.model.xpdl.carnot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Viewable Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ViewableType#getViewable <em>Viewable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getViewableType()
 * @model extendedMetaData="name='viewable_._type' kind='empty'"
 * @generated
 */
public interface ViewableType extends EObject{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Viewable</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the viewable. Valid viewable model elements are
    *                   "activity", "application", "data",
    *                   "participant", "workflow process".
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Viewable</em>' reference.
    * @see #setViewable(IModelElement)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getViewableType_Viewable()
    * @model resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='viewable'"
    * @generated
    */
   IModelElement getViewable();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ViewableType#getViewable <em>Viewable</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Viewable</em>' reference.
    * @see #getViewable()
    * @generated
    */
   void setViewable(IModelElement value);

} // ViewableType
