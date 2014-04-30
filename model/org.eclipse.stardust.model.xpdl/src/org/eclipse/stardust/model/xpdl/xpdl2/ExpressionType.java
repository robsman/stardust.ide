/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptGrammar <em>Script Grammar</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptType <em>Script Type</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptVersion <em>Script Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExpressionType()
 * @model extendedMetaData="name='ExpressionType' kind='mixed'"
 * @generated
 */
public interface ExpressionType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard";

   /**
    * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
    * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Mixed</em>' attribute list.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExpressionType_Mixed()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
    *        extendedMetaData="kind='elementWildcard' name=':mixed'"
    * @generated
    */
   FeatureMap getMixed();

   /**
    * Returns the value of the '<em><b>Group</b></em>' attribute list.
    * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Group</em>' attribute list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Group</em>' attribute list.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExpressionType_Group()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
    *        extendedMetaData="kind='group' name='group:1'"
    * @generated
    */
   FeatureMap getGroup();

   /**
    * Returns the value of the '<em><b>Any</b></em>' attribute list.
    * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Any</em>' attribute list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Any</em>' attribute list.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExpressionType_Any()
    * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
    *        extendedMetaData="kind='elementWildcard' wildcards='##any' name=':2' processing='lax' group='#group:1'"
    * @generated
    */
   FeatureMap getAny();

   /**
    * Returns the value of the '<em><b>Script Grammar</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Script Grammar</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Script Grammar</em>' attribute.
    * @see #setScriptGrammar(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExpressionType_ScriptGrammar()
    * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
    *        extendedMetaData="kind='attribute' name='ScriptGrammar'"
    * @generated
    */
   String getScriptGrammar();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptGrammar <em>Script Grammar</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Script Grammar</em>' attribute.
    * @see #getScriptGrammar()
    * @generated
    */
   void setScriptGrammar(String value);

   /**
    * Returns the value of the '<em><b>Script Type</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Script Type</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Script Type</em>' attribute.
    * @see #setScriptType(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExpressionType_ScriptType()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='ScriptType'"
    * @generated
    */
   String getScriptType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptType <em>Script Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Script Type</em>' attribute.
    * @see #getScriptType()
    * @generated
    */
   void setScriptType(String value);

   /**
    * Returns the value of the '<em><b>Script Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Script Version</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Script Version</em>' attribute.
    * @see #setScriptVersion(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getExpressionType_ScriptVersion()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='attribute' name='ScriptVersion'"
    * @generated
    */
   String getScriptVersion();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.ExpressionType#getScriptVersion <em>Script Version</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Script Version</em>' attribute.
    * @see #getScriptVersion()
    * @generated
    */
   void setScriptVersion(String value);

} // ExpressionType
