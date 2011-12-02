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
 * A representation of the model object '<em><b>Text Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TextSymbolType#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTextSymbolType()
 * @model extendedMetaData="name='textSymbol_._type' kind='empty'"
 * @generated
 */
public interface TextSymbolType extends INodeSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Text</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model id of the corresponding text.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Text</em>' attribute.
    * @see #setText(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTextSymbolType_Text()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    *        extendedMetaData="kind='attribute' name='text'"
    * @generated
    */
   String getText();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TextSymbolType#getText <em>Text</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Text</em>' attribute.
    * @see #getText()
    * @generated
    */
   void setText(String value);

} // TextSymbolType
