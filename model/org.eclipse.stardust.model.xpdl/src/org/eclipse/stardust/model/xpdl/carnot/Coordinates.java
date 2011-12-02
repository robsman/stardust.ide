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
 * A representation of the model object '<em><b>Coordinates</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getXPos <em>XPos</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getYPos <em>YPos</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getCoordinates()
 * @model extendedMetaData="name='coordinates_._type' kind='elementOnly'"
 * @generated
 */
public interface Coordinates extends EObject{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>XPos</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The location on the x-axis.
    * <!-- end-model-doc -->
    * @return the value of the '<em>XPos</em>' attribute.
    * @see #isSetXPos()
    * @see #unsetXPos()
    * @see #setXPos(double)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getCoordinates_XPos()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
    *        extendedMetaData="kind='attribute' name='x'"
    * @generated
    */
   double getXPos();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getXPos <em>XPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>XPos</em>' attribute.
    * @see #isSetXPos()
    * @see #unsetXPos()
    * @see #getXPos()
    * @generated
    */
   void setXPos(double value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getXPos <em>XPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetXPos()
    * @see #getXPos()
    * @see #setXPos(double)
    * @generated
    */
   void unsetXPos();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getXPos <em>XPos</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>XPos</em>' attribute is set.
    * @see #unsetXPos()
    * @see #getXPos()
    * @see #setXPos(double)
    * @generated
    */
   boolean isSetXPos();

   /**
    * Returns the value of the '<em><b>YPos</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The location on the y-axis.
    * <!-- end-model-doc -->
    * @return the value of the '<em>YPos</em>' attribute.
    * @see #isSetYPos()
    * @see #unsetYPos()
    * @see #setYPos(double)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getCoordinates_YPos()
    * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
    *        extendedMetaData="kind='attribute' name='y'"
    * @generated
    */
   double getYPos();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getYPos <em>YPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>YPos</em>' attribute.
    * @see #isSetYPos()
    * @see #unsetYPos()
    * @see #getYPos()
    * @generated
    */
   void setYPos(double value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getYPos <em>YPos</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetYPos()
    * @see #getYPos()
    * @see #setYPos(double)
    * @generated
    */
   void unsetYPos();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates#getYPos <em>YPos</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>YPos</em>' attribute is set.
    * @see #unsetYPos()
    * @see #getYPos()
    * @see #setYPos(double)
    * @generated
    */
   boolean isSetYPos();

} // Coordinates
