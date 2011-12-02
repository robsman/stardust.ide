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
 * A representation of the model object '<em><b>Transition Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getPoints <em>Points</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getSourceActivitySymbol <em>Source Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getTargetActivitySymbol <em>Target Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getTransition <em>Transition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTransitionConnectionType()
 * @model extendedMetaData="name='transitionConnection_._type' kind='empty'"
 * @generated
 */
public interface TransitionConnectionType extends IConnectionSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Points</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   A comma separated list of routing coordinates.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Points</em>' attribute.
    * @see #setPoints(String)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTransitionConnectionType_Points()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='points'"
    * @generated
    */
   String getPoints();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getPoints <em>Points</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Points</em>' attribute.
    * @see #getPoints()
    * @generated
    */
   void setPoints(String value);

   /**
    * Returns the value of the '<em><b>Source Activity Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol#getOutTransitions <em>Out Transitions</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the first activity symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Source Activity Symbol</em>' reference.
    * @see #setSourceActivitySymbol(IFlowObjectSymbol)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTransitionConnectionType_SourceActivitySymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol#getOutTransitions
    * @model opposite="outTransitions" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='sourceActivitySymbol'"
    * @generated
    */
   IFlowObjectSymbol getSourceActivitySymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getSourceActivitySymbol <em>Source Activity Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Source Activity Symbol</em>' reference.
    * @see #getSourceActivitySymbol()
    * @generated
    */
   void setSourceActivitySymbol(IFlowObjectSymbol value);

   /**
    * Returns the value of the '<em><b>Target Activity Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol#getInTransitions <em>In Transitions</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the second activity symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Target Activity Symbol</em>' reference.
    * @see #setTargetActivitySymbol(IFlowObjectSymbol)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTransitionConnectionType_TargetActivitySymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol#getInTransitions
    * @model opposite="inTransitions" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='targetActivitySymbol'"
    * @generated
    */
   IFlowObjectSymbol getTargetActivitySymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getTargetActivitySymbol <em>Target Activity Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Target Activity Symbol</em>' reference.
    * @see #getTargetActivitySymbol()
    * @generated
    */
   void setTargetActivitySymbol(IFlowObjectSymbol value);

   /**
    * Returns the value of the '<em><b>Transition</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionType#getTransitionConnections <em>Transition Connections</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model id of the corresponding transition.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Transition</em>' reference.
    * @see #setTransition(TransitionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getTransitionConnectionType_Transition()
    * @see org.eclipse.stardust.model.xpdl.carnot.TransitionType#getTransitionConnections
    * @model opposite="transitionConnections" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='transition'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
    * @generated
    */
   TransitionType getTransition();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType#getTransition <em>Transition</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Transition</em>' reference.
    * @see #getTransition()
    * @generated
    */
   void setTransition(TransitionType value);

} // TransitionConnectionType
