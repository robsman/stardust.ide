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
 * A representation of the model object '<em><b>Sub Process Of Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getProcessSymbol <em>Process Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getSubprocessSymbol <em>Subprocess Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getSubProcessOfConnectionType()
 * @model extendedMetaData="name='subprocessOfConnection_._type' kind='empty'"
 * @generated
 */
public interface SubProcessOfConnectionType extends IConnectionSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Process Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getSubProcesses <em>Sub Processes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The model oid of the process symbol.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Process Symbol</em>' reference.
    * @see #setProcessSymbol(ProcessSymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getSubProcessOfConnectionType_ProcessSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getSubProcesses
    * @model opposite="subProcesses" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='processSymbol'"
    * @generated
    */
   ProcessSymbolType getProcessSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getProcessSymbol <em>Process Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Process Symbol</em>' reference.
    * @see #getProcessSymbol()
    * @generated
    */
   void setProcessSymbol(ProcessSymbolType value);

   /**
    * Returns the value of the '<em><b>Subprocess Symbol</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getParentProcesses <em>Parent Processes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * 
    *                   The model oid of the subprocess symbol.
    *                
    * <!-- end-model-doc -->
    * @return the value of the '<em>Subprocess Symbol</em>' reference.
    * @see #setSubprocessSymbol(ProcessSymbolType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getSubProcessOfConnectionType_SubprocessSymbol()
    * @see org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getParentProcesses
    * @model opposite="parentProcesses" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='subprocessSymbol'"
    * @generated
    */
   ProcessSymbolType getSubprocessSymbol();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getSubprocessSymbol <em>Subprocess Symbol</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Subprocess Symbol</em>' reference.
    * @see #getSubprocessSymbol()
    * @generated
    */
   void setSubprocessSymbol(ProcessSymbolType value);

} // SubProcessOfConnectionType
