/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2;

import java.math.BigInteger;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Loop Standard Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getTestTime <em>Test Time</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopStandardType()
 * @model extendedMetaData="name='LoopStandard_._type' kind='elementOnly'"
 * @generated
 */
public interface LoopStandardType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard";

   /**
    * Returns the value of the '<em><b>Loop Condition</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Loop Condition</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Loop Condition</em>' containment reference.
    * @see #setLoopCondition(ExpressionType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopStandardType_LoopCondition()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='LoopCondition' namespace='##targetNamespace'"
    * @generated
    */
   ExpressionType getLoopCondition();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopCondition <em>Loop Condition</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Loop Condition</em>' containment reference.
    * @see #getLoopCondition()
    * @generated
    */
   void setLoopCondition(ExpressionType value);

   /**
    * Returns the value of the '<em><b>Loop Maximum</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Loop Maximum</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Loop Maximum</em>' attribute.
    * @see #setLoopMaximum(BigInteger)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopStandardType_LoopMaximum()
    * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
    *        extendedMetaData="kind='attribute' name='LoopMaximum'"
    * @generated
    */
   BigInteger getLoopMaximum();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getLoopMaximum <em>Loop Maximum</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Loop Maximum</em>' attribute.
    * @see #getLoopMaximum()
    * @generated
    */
   void setLoopMaximum(BigInteger value);

   /**
    * Returns the value of the '<em><b>Test Time</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Test Time</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Test Time</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
    * @see #isSetTestTime()
    * @see #unsetTestTime()
    * @see #setTestTime(TestTimeType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopStandardType_TestTime()
    * @model unsettable="true" required="true"
    *        extendedMetaData="kind='attribute' name='TestTime'"
    * @generated
    */
   TestTimeType getTestTime();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getTestTime <em>Test Time</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Test Time</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.TestTimeType
    * @see #isSetTestTime()
    * @see #unsetTestTime()
    * @see #getTestTime()
    * @generated
    */
   void setTestTime(TestTimeType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getTestTime <em>Test Time</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetTestTime()
    * @see #getTestTime()
    * @see #setTestTime(TestTimeType)
    * @generated
    */
   void unsetTestTime();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopStandardType#getTestTime <em>Test Time</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>Test Time</em>' attribute is set.
    * @see #unsetTestTime()
    * @see #getTestTime()
    * @see #setTestTime(TestTimeType)
    * @generated
    */
   boolean isSetTestTime();

} // LoopStandardType
