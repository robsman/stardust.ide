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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Activity Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getPerformsConnections <em>Performs Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getExecutedByConnections <em>Executed By Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getDataMappings <em>Data Mappings</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getGatewaySymbols <em>Gateway Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getActivitySymbolType()
 * @model extendedMetaData="name='activitySymbol_._type' kind='empty'"
 * @generated
 */
public interface ActivitySymbolType extends IFlowObjectSymbol, IModelElementNodeSymbol{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Activity</b></em>' reference. It is
    * bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ActivityType#getActivitySymbols <em>Activity Symbols</em>}'.
    * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The id of the
    * corresponding activity. <!-- end-model-doc -->
    * 
    * @return the value of the '<em>Activity</em>' reference.
    * @see #setActivity(ActivityType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getActivitySymbolType_Activity()
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivityType#getActivitySymbols
    * @model opposite="activitySymbols" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='refer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='process'"
    * @generated
    */
   ActivityType getActivity();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType#getActivity <em>Activity</em>}' reference.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @param value the new value of the '<em>Activity</em>' reference.
    * @see #getActivity()
    * @generated
    */
   void setActivity(ActivityType value);

   /**
    * Returns the value of the '<em><b>Performs Connections</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getActivitySymbol <em>Activity Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Performs Connections</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Performs Connections</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getActivitySymbolType_PerformsConnections()
    * @see org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType#getActivitySymbol
    * @model opposite="activitySymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<PerformsConnectionType> getPerformsConnections();

   /**
    * Returns the value of the '<em><b>Executed By Connections</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType#getActivitySymbol <em>Activity Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Executed Application</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Executed By Connections</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getActivitySymbolType_ExecutedByConnections()
    * @see org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType#getActivitySymbol
    * @model opposite="activitySymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<ExecutedByConnectionType> getExecutedByConnections();

   /**
    * Returns the value of the '<em><b>Data Mappings</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType#getActivitySymbol <em>Activity Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data Mappings</em>' reference list isn't clear, there
    * really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Data Mappings</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getActivitySymbolType_DataMappings()
    * @see org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType#getActivitySymbol
    * @model opposite="activitySymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<DataMappingConnectionType> getDataMappings();

   /**
    * Returns the value of the '<em><b>Gateway Symbols</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol#getActivitySymbol <em>Activity Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Gateway Symbols</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Gateway Symbols</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getActivitySymbolType_GatewaySymbols()
    * @see org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol#getActivitySymbol
    * @model opposite="activitySymbol" transient="true"
    * @generated
    */
   EList<GatewaySymbol> getGatewaySymbols();

} // ActivitySymbolType
