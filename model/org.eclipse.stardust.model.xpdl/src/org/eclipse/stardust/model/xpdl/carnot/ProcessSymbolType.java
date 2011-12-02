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
 * A representation of the model object '<em><b>Process Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getProcess <em>Process</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getSubProcesses <em>Sub Processes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getParentProcesses <em>Parent Processes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getProcessSymbolType()
 * @model extendedMetaData="name='processSymbol_._type' kind='empty'"
 * @generated
 */
public interface ProcessSymbolType extends IModelElementNodeSymbol{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Process</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType#getProcessSymbols <em>Process Symbols</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * <!-- begin-model-doc -->
    * The id of the corresponding activity.
    * <!-- end-model-doc -->
    * @return the value of the '<em>Process</em>' reference.
    * @see #setProcess(ProcessDefinitionType)
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getProcessSymbolType_Process()
    * @see org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType#getProcessSymbols
    * @model opposite="processSymbols" resolveProxies="false" required="true"
    *        extendedMetaData="kind='attribute' name='refer'"
    *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
    * @generated
    */
   ProcessDefinitionType getProcess();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType#getProcess <em>Process</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Process</em>' reference.
    * @see #getProcess()
    * @generated
    */
   void setProcess(ProcessDefinitionType value);

   /**
    * Returns the value of the '<em><b>Sub Processes</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getProcessSymbol <em>Process Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Sub Processes</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Sub Processes</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getProcessSymbolType_SubProcesses()
    * @see org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getProcessSymbol
    * @model opposite="processSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<SubProcessOfConnectionType> getSubProcesses();

   /**
    * Returns the value of the '<em><b>Parent Processes</b></em>' reference list.
    * The list contents are of type {@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType}.
    * It is bidirectional and its opposite is '{@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getSubprocessSymbol <em>Subprocess Symbol</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parent Processes</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Parent Processes</em>' reference list.
    * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage#getProcessSymbolType_ParentProcesses()
    * @see org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType#getSubprocessSymbol
    * @model opposite="subprocessSymbol" resolveProxies="false" transient="true"
    * @generated
    */
   EList<SubProcessOfConnectionType> getParentProcesses();

} // ProcessSymbolType
