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
 * A representation of the model object '<em><b>Executed By Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType#getActivitySymbol <em>Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType#getApplicationSymbol <em>Application Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getExecutedByConnectionType()
 * @model extendedMetaData="name='executedByConnection_._type' kind='empty'"
 * @generated
 */
public interface ExecutedByConnectionType extends IConnectionSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Activity Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getExecutedByConnections <em>Executed By Connections</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model id of the corresponding activity symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Activity Symbol</em>' reference.
    * @see #setActivitySymbol(ActivitySymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getExecutedByConnectionType_ActivitySymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getExecutedByConnections
    * @model opposite="executedByConnections" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='activitySymbol'"
    * @generated
    */
   ActivitySymbolType getActivitySymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType#getActivitySymbol <em>Activity Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Activity Symbol</em>' reference.
    * @see #getActivitySymbol()
    * @generated
    */
   void setActivitySymbol(ActivitySymbolType value);

   /**
    * Returns the value of the '<em><b>Application Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType#getExecutingActivities <em>Executing Activities</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model id of the corresponding application symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Application Symbol</em>' reference.
    * @see #setApplicationSymbol(ApplicationSymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getExecutedByConnectionType_ApplicationSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType#getExecutingActivities
    * @model opposite="executingActivities" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='applicationSymbol'"
    * @generated
    */
   ApplicationSymbolType getApplicationSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType#getApplicationSymbol <em>Application Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Application Symbol</em>' reference.
    * @see #getApplicationSymbol()
    * @generated
    */
   void setApplicationSymbol(ApplicationSymbolType value);

} // ExecutedByConnectionType
