/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2.extensions;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Loop Data Ref Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getInputItemRef <em>Input Item Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getOutputItemRef <em>Output Item Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getLoopCounterRef <em>Loop Counter Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage#getLoopDataRefType()
 * @model extendedMetaData="name='LoopDataRef_._type' kind='elementOnly'"
 * @generated
 */
public interface LoopDataRefType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard";

   /**
    * Returns the value of the '<em><b>Input Item Ref</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Input Item Ref</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Input Item Ref</em>' attribute.
    * @see #setInputItemRef(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage#getLoopDataRefType_InputItemRef()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='element' name='InputItemRef' namespace='##targetNamespace'"
    * @generated
    */
   String getInputItemRef();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getInputItemRef <em>Input Item Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Input Item Ref</em>' attribute.
    * @see #getInputItemRef()
    * @generated
    */
   void setInputItemRef(String value);

   /**
    * Returns the value of the '<em><b>Output Item Ref</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Output Item Ref</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Output Item Ref</em>' attribute.
    * @see #setOutputItemRef(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage#getLoopDataRefType_OutputItemRef()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='element' name='OutputItemRef' namespace='##targetNamespace'"
    * @generated
    */
   String getOutputItemRef();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getOutputItemRef <em>Output Item Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Output Item Ref</em>' attribute.
    * @see #getOutputItemRef()
    * @generated
    */
   void setOutputItemRef(String value);

   /**
    * Returns the value of the '<em><b>Loop Counter Ref</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Loop Counter Ref</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Loop Counter Ref</em>' attribute.
    * @see #setLoopCounterRef(String)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.extensions.ExtensionPackage#getLoopDataRefType_LoopCounterRef()
    * @model dataType="org.eclipse.emf.ecore.xml.type.String"
    *        extendedMetaData="kind='element' name='LoopCounterRef' namespace='##targetNamespace'"
    * @generated
    */
   String getLoopCounterRef();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType#getLoopCounterRef <em>Loop Counter Ref</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Loop Counter Ref</em>' attribute.
    * @see #getLoopCounterRef()
    * @generated
    */
   void setLoopCounterRef(String value);

} // LoopDataRefType
