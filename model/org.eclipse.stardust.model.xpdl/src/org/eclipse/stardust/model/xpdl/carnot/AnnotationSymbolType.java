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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Annotation Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAnnotationSymbolType()
 * @model extendedMetaData="name='annotationSymbol_._type' kind='elementOnly'"
 * @generated
 */
public interface AnnotationSymbolType extends INodeSymbol{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Text</b></em>' containment reference. <!--
    * begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Text</em>' containment reference isn't clear, there
    * really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Text</em>' containment reference.
    * @see #setText(TextType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAnnotationSymbolType_Text()
    * @model containment="true" resolveProxies="false" extendedMetaData="kind='element'
    *        name='text' namespace='##targetNamespace'"
    * @generated
    */
   TextType getText();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType#getText <em>Text</em>}' containment reference.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @param value the new value of the '<em>Text</em>' containment reference.
    * @see #getText()
    * @generated
    */
   void setText(TextType value);

} // AnnotationSymbolType
