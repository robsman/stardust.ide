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
 * A representation of the model object '<em><b>Refers To Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getFrom <em>From</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRefersToConnectionType()
 * @model extendedMetaData="name='refersToConnection_._type' kind='empty'"
 * @generated
 */
public interface RefersToConnectionType extends IConnectionSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>From</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getReferingFromConnections <em>Refering From Connections</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the first element symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>From</em>' reference.
    * @see #isSetFrom()
    * @see #unsetFrom()
    * @see #setFrom(IGraphicalObject)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRefersToConnectionType_From()
    * @see org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getReferingFromConnections
    * @model opposite="referingFromConnections" resolveProxies="false" unsettable="true" required="true"
    *        extendedMetaData="kind='attribute' name='from'"
    * @generated
    */
   IGraphicalObject getFrom();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getFrom <em>From</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>From</em>' reference.
    * @see #isSetFrom()
    * @see #unsetFrom()
    * @see #getFrom()
    * @generated
    */
   void setFrom(IGraphicalObject value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getFrom <em>From</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetFrom()
    * @see #getFrom()
    * @see #setFrom(IGraphicalObject)
    * @generated
    */
   void unsetFrom();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getFrom <em>From</em>}' reference is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>From</em>' reference is set.
    * @see #unsetFrom()
    * @see #getFrom()
    * @see #setFrom(IGraphicalObject)
    * @generated
    */
   boolean isSetFrom();

   /**
    * Returns the value of the '<em><b>To</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getReferingToConnections <em>Refering To Connections</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the second element symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>To</em>' reference.
    * @see #isSetTo()
    * @see #unsetTo()
    * @see #setTo(IGraphicalObject)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getRefersToConnectionType_To()
    * @see org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject#getReferingToConnections
    * @model opposite="referingToConnections" resolveProxies="false" unsettable="true" required="true"
    *        extendedMetaData="kind='attribute' name='to'"
    * @generated
    */
   IGraphicalObject getTo();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getTo <em>To</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>To</em>' reference.
    * @see #isSetTo()
    * @see #unsetTo()
    * @see #getTo()
    * @generated
    */
   void setTo(IGraphicalObject value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getTo <em>To</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetTo()
    * @see #getTo()
    * @see #setTo(IGraphicalObject)
    * @generated
    */
   void unsetTo();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType#getTo <em>To</em>}' reference is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>To</em>' reference is set.
    * @see #unsetTo()
    * @see #getTo()
    * @see #setTo(IGraphicalObject)
    * @generated
    */
   boolean isSetTo();

} // RefersToConnectionType
