/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Loop Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopStandard <em>Loop Standard</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopMultiInstance <em>Loop Multi Instance</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopType <em>Loop Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopType()
 * @model extendedMetaData="name='Loop_._type' kind='elementOnly'"
 * @generated
 */
public interface LoopType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard";

   /**
    * Returns the value of the '<em><b>Loop Standard</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Loop Standard</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Loop Standard</em>' containment reference.
    * @see #setLoopStandard(LoopStandardType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopType_LoopStandard()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='LoopStandard' namespace='##targetNamespace'"
    * @generated
    */
   LoopStandardType getLoopStandard();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopStandard <em>Loop Standard</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Loop Standard</em>' containment reference.
    * @see #getLoopStandard()
    * @generated
    */
   void setLoopStandard(LoopStandardType value);

   /**
    * Returns the value of the '<em><b>Loop Multi Instance</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Loop Multi Instance</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Loop Multi Instance</em>' containment reference.
    * @see #setLoopMultiInstance(LoopMultiInstanceType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopType_LoopMultiInstance()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='LoopMultiInstance' namespace='##targetNamespace'"
    * @generated
    */
   LoopMultiInstanceType getLoopMultiInstance();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopMultiInstance <em>Loop Multi Instance</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Loop Multi Instance</em>' containment reference.
    * @see #getLoopMultiInstance()
    * @generated
    */
   void setLoopMultiInstance(LoopMultiInstanceType value);

   /**
    * Returns the value of the '<em><b>Loop Type</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Loop Type</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Loop Type</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
    * @see #isSetLoopType()
    * @see #unsetLoopType()
    * @see #setLoopType(LoopTypeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopType_LoopType()
    * @model unsettable="true" required="true"
    *        extendedMetaData="kind='attribute' name='LoopType'"
    * @generated
    */
   LoopTypeType getLoopType();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopType <em>Loop Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Loop Type</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.LoopTypeType
    * @see #isSetLoopType()
    * @see #unsetLoopType()
    * @see #getLoopType()
    * @generated
    */
   void setLoopType(LoopTypeType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopType <em>Loop Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetLoopType()
    * @see #getLoopType()
    * @see #setLoopType(LoopTypeType)
    * @generated
    */
   void unsetLoopType();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopType#getLoopType <em>Loop Type</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Loop Type</em>' attribute is set.
    * @see #unsetLoopType()
    * @see #getLoopType()
    * @see #setLoopType(LoopTypeType)
    * @generated
    */
   boolean isSetLoopType();

} // LoopType
