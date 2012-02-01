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
package org.eclipse.stardust.model.xpdl.xpdl2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getGrammar <em>Grammar</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getScriptType()
 * @model extendedMetaData="name='Script_._type' kind='empty'"
 * @generated
 */
public interface ScriptType extends EObject
{
   /**
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @generated
    */
	String copyright = "Copyright 2008 by SunGard"; //$NON-NLS-1$

   /**
    * Returns the value of the '<em><b>Grammar</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grammar</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Grammar</em>' attribute.
    * @see #setGrammar(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getScriptType_Grammar()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
    *        extendedMetaData="kind='attribute' name='Grammar'"
    * @generated
    */
	String getGrammar();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getGrammar <em>Grammar</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Grammar</em>' attribute.
    * @see #getGrammar()
    * @generated
    */
	void setGrammar(String value);

   /**
    * Returns the value of the '<em><b>Type</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Type</em>' attribute.
    * @see #setType(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getScriptType_Type()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
    *        extendedMetaData="kind='attribute' name='Type'"
    * @generated
    */
	String getType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getType <em>Type</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Type</em>' attribute.
    * @see #getType()
    * @generated
    */
	void setType(String value);

   /**
    * Returns the value of the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
    * @return the value of the '<em>Version</em>' attribute.
    * @see #setVersion(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getScriptType_Version()
    * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='Version'"
    * @generated
    */
	String getVersion();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ScriptType#getVersion <em>Version</em>}' attribute.
    * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
    * @param value the new value of the '<em>Version</em>' attribute.
    * @see #getVersion()
    * @generated
    */
	void setVersion(String value);

} // ScriptType