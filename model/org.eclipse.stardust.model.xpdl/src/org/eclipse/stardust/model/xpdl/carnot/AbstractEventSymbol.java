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
 * A representation of the model object '<em><b>Abstract Event Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAbstractEventSymbol()
 * @model abstract="true"
 *        extendedMetaData="name='abstractEventSymbol_._type' kind='empty'"
 * @generated
 */
public interface AbstractEventSymbol extends IFlowObjectSymbol, IModelElementNodeSymbol {
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Label</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Label</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Label</em>' attribute.
    * @see #setLabel(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getAbstractEventSymbol_Label()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    * @generated
    */
   String getLabel();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol#getLabel <em>Label</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Label</em>' attribute.
    * @see #getLabel()
    * @generated
    */
   void setLabel(String value);

} // AbstractEventSymbol
