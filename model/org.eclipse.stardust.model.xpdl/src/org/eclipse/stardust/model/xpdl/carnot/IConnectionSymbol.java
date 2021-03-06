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
 * A representation of the model object '<em><b>IConnection Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getSourceAnchor <em>Source Anchor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getTargetAnchor <em>Target Anchor</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getRouting <em>Routing</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getCoordinates <em>Coordinates</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIConnectionSymbol()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='connectionSymbol_._type' kind='empty'"
 * @generated
 */
public interface IConnectionSymbol extends IGraphicalObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Source Anchor</b></em>' attribute.
    * The default value is <code>"center"</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The source node anchor, if existent. Defaults to "center".
    * <!-- end-model-doc -->
    * @return the value of the '<em>Source Anchor</em>' attribute.
    * @see #isSetSourceAnchor()
    * @see #unsetSourceAnchor()
    * @see #setSourceAnchor(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIConnectionSymbol_SourceAnchor()
    * @model default="center" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='sourceAnchor'"
    * @generated
    */
   String getSourceAnchor();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getSourceAnchor <em>Source Anchor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Source Anchor</em>' attribute.
    * @see #isSetSourceAnchor()
    * @see #unsetSourceAnchor()
    * @see #getSourceAnchor()
    * @generated
    */
   void setSourceAnchor(String value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getSourceAnchor <em>Source Anchor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetSourceAnchor()
    * @see #getSourceAnchor()
    * @see #setSourceAnchor(String)
    * @generated
    */
   void unsetSourceAnchor();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getSourceAnchor <em>Source Anchor</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Source Anchor</em>' attribute is set.
    * @see #unsetSourceAnchor()
    * @see #getSourceAnchor()
    * @see #setSourceAnchor(String)
    * @generated
    */
   boolean isSetSourceAnchor();

   /**
    * Returns the value of the '<em><b>Target Anchor</b></em>' attribute.
    * The default value is <code>"center"</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The target node anchor, if existent. Defaults to "center".
    * <!-- end-model-doc -->
    * @return the value of the '<em>Target Anchor</em>' attribute.
    * @see #isSetTargetAnchor()
    * @see #unsetTargetAnchor()
    * @see #setTargetAnchor(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIConnectionSymbol_TargetAnchor()
    * @model default="center" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='targetAnchor'"
    * @generated
    */
   String getTargetAnchor();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getTargetAnchor <em>Target Anchor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Target Anchor</em>' attribute.
    * @see #isSetTargetAnchor()
    * @see #unsetTargetAnchor()
    * @see #getTargetAnchor()
    * @generated
    */
   void setTargetAnchor(String value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getTargetAnchor <em>Target Anchor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetTargetAnchor()
    * @see #getTargetAnchor()
    * @see #setTargetAnchor(String)
    * @generated
    */
   void unsetTargetAnchor();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getTargetAnchor <em>Target Anchor</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Target Anchor</em>' attribute is set.
    * @see #unsetTargetAnchor()
    * @see #getTargetAnchor()
    * @see #setTargetAnchor(String)
    * @generated
    */
   boolean isSetTargetAnchor();

   /**
    * Returns the value of the '<em><b>Routing</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.carnot.RoutingType}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The routing style, if applicable.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Routing</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.RoutingType
    * @see #isSetRouting()
    * @see #unsetRouting()
    * @see #setRouting(RoutingType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIConnectionSymbol_Routing()
    * @model unsettable="true"
    *        extendedMetaData="kind='attribute' name='routing'"
    * @generated
    */
   RoutingType getRouting();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getRouting <em>Routing</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Routing</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.carnot.RoutingType
    * @see #isSetRouting()
    * @see #unsetRouting()
    * @see #getRouting()
    * @generated
    */
   void setRouting(RoutingType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getRouting <em>Routing</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetRouting()
    * @see #getRouting()
    * @see #setRouting(RoutingType)
    * @generated
    */
   void unsetRouting();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol#getRouting <em>Routing</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Routing</em>' attribute is set.
    * @see #unsetRouting()
    * @see #getRouting()
    * @see #setRouting(RoutingType)
    * @generated
    */
   boolean isSetRouting();

   /**
    * Returns the value of the '<em><b>Coordinates</b></em>' containment reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.Coordinates}.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The routing coordinates of the connection symbol.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Coordinates</em>' containment reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIConnectionSymbol_Coordinates()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='coordinate' namespace='##targetNamespace'"
    * @generated
    */
   EList<Coordinates> getCoordinates();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
   INodeSymbol getSourceNode();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model nodeSymbolRequired="true"
    * @generated
    */
   void setSourceNode(INodeSymbol nodeSymbol);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation"
    * @generated
    */
   INodeSymbol getTargetNode();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model nodeSymbolRequired="true"
    * @generated
    */
   void setTargetNode(INodeSymbol nodeSymbol);

} // IConnectionSymbol
