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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modeler Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType#getModeler <em>Modeler</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getModelerSymbolType()
 * @model extendedMetaData="name='modelerSymbol_._type' kind='empty'"
 * @generated
 */
public interface ModelerSymbolType extends IModelElementNodeSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Modeler</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ModelerType#getModelerSymbols <em>Modeler Symbols</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the corresponding activity.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Modeler</em>' reference.
    * @see #setModeler(ModelerType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getModelerSymbolType_Modeler()
    * @see org.eclipse.stardust.model.xpdl.carnot.ModelerType#getModelerSymbols
    * @model opposite="modelerSymbols" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='refer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   ModelerType getModeler();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType#getModeler <em>Modeler</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Modeler</em>' reference.
    * @see #getModeler()
    * @generated
    */
   void setModeler(ModelerType value);

} // ModelerSymbolType
