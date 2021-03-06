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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getPoolSymbols <em>Pool Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getMode <em>Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDiagramType()
 * @model extendedMetaData="name='diagram_._type' kind='elementOnly'"
 * @generated
 */
public interface DiagramType extends ISymbolContainer, IModelElement, IExtensibleElement
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The human friendly name of the diagram.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDiagramType_Name()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    *        extendedMetaData="kind='attribute' name='name'"
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Returns the value of the '<em><b>Pool Symbols</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.PoolSymbol}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.PoolSymbol#getDiagram <em>Diagram</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The list of contained pool symbols.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Pool Symbols</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDiagramType_PoolSymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.PoolSymbol#getDiagram
    * @model opposite="diagram" containment="true"
    *        extendedMetaData="kind='element' name='poolSymbol' namespace='##targetNamespace'"
    * @generated
    */
   EList<PoolSymbol> getPoolSymbols();

   /**
    * Returns the value of the '<em><b>Orientation</b></em>' attribute.
    * The default value is <code>"Vertical"</code>.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.OrientationType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The default orientation of the diagram.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Orientation</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.OrientationType
    * @see #isSetOrientation()
    * @see #unsetOrientation()
    * @see #setOrientation(OrientationType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDiagramType_Orientation()
    * @model default="Vertical" unique="false" unsettable="true"
    *        extendedMetaData="kind='attribute' name='orientation'"
    * @generated
    */
   OrientationType getOrientation();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getOrientation <em>Orientation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Orientation</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.OrientationType
    * @see #isSetOrientation()
    * @see #unsetOrientation()
    * @see #getOrientation()
    * @generated
    */
   void setOrientation(OrientationType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getOrientation <em>Orientation</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetOrientation()
    * @see #getOrientation()
    * @see #setOrientation(OrientationType)
    * @generated
    */
   void unsetOrientation();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getOrientation <em>Orientation</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Orientation</em>' attribute is set.
    * @see #unsetOrientation()
    * @see #getOrientation()
    * @see #setOrientation(OrientationType)
    * @generated
    */
   boolean isSetOrientation();

   /**
    * Returns the value of the '<em><b>Mode</b></em>' attribute.
    * The default value is <code>"MODE_4_0_0"</code>.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.DiagramModeType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The type of activity join. Valid values are: "None",
    *                   "XOR" or "AND".
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Mode</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DiagramModeType
    * @see #isSetMode()
    * @see #unsetMode()
    * @see #setMode(DiagramModeType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getDiagramType_Mode()
    * @model default="MODE_4_0_0" unique="false" unsettable="true"
    *        extendedMetaData="kind='attribute' name='mode'"
    * @generated
    */
   DiagramModeType getMode();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getMode <em>Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Mode</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.DiagramModeType
    * @see #isSetMode()
    * @see #unsetMode()
    * @see #getMode()
    * @generated
    */
   void setMode(DiagramModeType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getMode <em>Mode</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetMode()
    * @see #getMode()
    * @see #setMode(DiagramModeType)
    * @generated
    */
   void unsetMode();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType#getMode <em>Mode</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Mode</em>' attribute is set.
    * @see #unsetMode()
    * @see #getMode()
    * @see #setMode(DiagramModeType)
    * @generated
    */
   boolean isSetMode();

} // DiagramType
