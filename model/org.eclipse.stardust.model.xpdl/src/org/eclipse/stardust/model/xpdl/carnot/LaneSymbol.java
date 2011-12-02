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
 * A representation of the model object '<em><b>Lane Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.LaneSymbol#getParentPool <em>Parent Pool</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.LaneSymbol#getParentLane <em>Parent Lane</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLaneSymbol()
 * @model extendedMetaData="name='laneSymbol_._type' kind='empty'"
 * @generated
 */
public interface LaneSymbol extends ISymbolContainer, ISwimlaneSymbol
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Parent Pool</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.PoolSymbol#getLanes <em>Lanes</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parent Pool</em>' container reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Parent Pool</em>' container reference.
    * @see #setParentPool(PoolSymbol)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLaneSymbol_ParentPool()
    * @see org.eclipse.stardust.model.xpdl.carnot.PoolSymbol#getLanes
    * @model opposite="lanes" resolveProxies="false" required="true"
    * @generated
    */
   PoolSymbol getParentPool();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.LaneSymbol#getParentPool <em>Parent Pool</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Parent Pool</em>' container reference.
    * @see #getParentPool()
    * @generated
    */
   void setParentPool(PoolSymbol value);

   /**
    * Returns the value of the '<em><b>Parent Lane</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getChildLanes <em>Child Lanes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * A reference to the associated parent swimlane from the same pool, either a lane or the pool itself.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Parent Lane</em>' reference.
    * @see #setParentLane(ISwimlaneSymbol)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getLaneSymbol_ParentLane()
    * @see org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol#getChildLanes
    * @model opposite="childLanes" resolveProxies="false"
    *        extendedMetaData="kind='attribute' name='parentLane'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='pool' reftype='oid'"
    * @generated
    */
   ISwimlaneSymbol getParentLane();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.LaneSymbol#getParentLane <em>Parent Lane</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Parent Lane</em>' reference.
    * @see #getParentLane()
    * @generated
    */
   void setParentLane(ISwimlaneSymbol value);

} // LaneSymbol
