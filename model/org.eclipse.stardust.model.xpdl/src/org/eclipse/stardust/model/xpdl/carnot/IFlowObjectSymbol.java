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
 * A representation of the model object '<em><b>IFlow Object Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol#getInTransitions <em>In Transitions</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol#getOutTransitions <em>Out Transitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIFlowObjectSymbol()
 * @model abstract="true"
 *        extendedMetaData="name='flowObjectSymbol_._type' kind='empty'"
 * @generated
 */
public interface IFlowObjectSymbol extends INodeSymbol {
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>In Transitions</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getTargetActivitySymbol <em>Target Activity Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>In Transitions</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>In Transitions</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIFlowObjectSymbol_InTransitions()
    * @see org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getTargetActivitySymbol
    * @model opposite="targetActivitySymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<TransitionConnectionType> getInTransitions();

   /**
    * Returns the value of the '<em><b>Out Transitions</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getSourceActivitySymbol <em>Source Activity Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Out Transitions</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Out Transitions</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getIFlowObjectSymbol_OutTransitions()
    * @see org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getSourceActivitySymbol
    * @model opposite="sourceActivitySymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<TransitionConnectionType> getOutTransitions();

} // IFlowObjectSymbol
