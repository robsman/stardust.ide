/**
 * Copyright 2008 by SunGard
 */
package org.eclipse.stardust.model.xpdl.xpdl2;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.xpdl2.extensions.LoopDataRefType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Loop Multi Instance Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMICondition <em>MI Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getComplexMIFlowCondition <em>Complex MI Flow Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getLoopDataRef <em>Loop Data Ref</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIFlowCondition <em>MI Flow Condition</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIOrdering <em>MI Ordering</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopMultiInstanceType()
 * @model extendedMetaData="name='LoopMultiInstance_._type' kind='elementOnly'"
 * @generated
 */
public interface LoopMultiInstanceType extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2008 by SunGard";

   /**
    * Returns the value of the '<em><b>MI Condition</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>MI Condition</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>MI Condition</em>' containment reference.
    * @see #setMICondition(ExpressionType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopMultiInstanceType_MICondition()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='MI_Condition' namespace='##targetNamespace'"
    * @generated
    */
   ExpressionType getMICondition();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMICondition <em>MI Condition</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>MI Condition</em>' containment reference.
    * @see #getMICondition()
    * @generated
    */
   void setMICondition(ExpressionType value);

   /**
    * Returns the value of the '<em><b>Complex MI Flow Condition</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Complex MI Flow Condition</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Complex MI Flow Condition</em>' containment reference.
    * @see #setComplexMIFlowCondition(ExpressionType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopMultiInstanceType_ComplexMIFlowCondition()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='ComplexMI_FlowCondition' namespace='##targetNamespace'"
    * @generated
    */
   ExpressionType getComplexMIFlowCondition();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getComplexMIFlowCondition <em>Complex MI Flow Condition</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Complex MI Flow Condition</em>' containment reference.
    * @see #getComplexMIFlowCondition()
    * @generated
    */
   void setComplexMIFlowCondition(ExpressionType value);

   /**
    * Returns the value of the '<em><b>Loop Data Ref</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Loop Data Ref</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Loop Data Ref</em>' containment reference.
    * @see #setLoopDataRef(LoopDataRefType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopMultiInstanceType_LoopDataRef()
    * @model containment="true"
    *        extendedMetaData="kind='element' name='LoopDataRef' namespace='http://www.carnot.ag/workflowmodel/3.1/xpdl/extensions'"
    * @generated
    */
   LoopDataRefType getLoopDataRef();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getLoopDataRef <em>Loop Data Ref</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Loop Data Ref</em>' containment reference.
    * @see #getLoopDataRef()
    * @generated
    */
   void setLoopDataRef(LoopDataRefType value);

   /**
    * Returns the value of the '<em><b>MI Flow Condition</b></em>' attribute.
    * The default value is <code>"All"</code>.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>MI Flow Condition</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>MI Flow Condition</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
    * @see #isSetMIFlowCondition()
    * @see #unsetMIFlowCondition()
    * @see #setMIFlowCondition(MIFlowConditionType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopMultiInstanceType_MIFlowCondition()
    * @model default="All" unsettable="true"
    *        extendedMetaData="kind='attribute' name='MI_FlowCondition'"
    * @generated
    */
   MIFlowConditionType getMIFlowCondition();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIFlowCondition <em>MI Flow Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>MI Flow Condition</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIFlowConditionType
    * @see #isSetMIFlowCondition()
    * @see #unsetMIFlowCondition()
    * @see #getMIFlowCondition()
    * @generated
    */
   void setMIFlowCondition(MIFlowConditionType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIFlowCondition <em>MI Flow Condition</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetMIFlowCondition()
    * @see #getMIFlowCondition()
    * @see #setMIFlowCondition(MIFlowConditionType)
    * @generated
    */
   void unsetMIFlowCondition();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIFlowCondition <em>MI Flow Condition</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>MI Flow Condition</em>' attribute is set.
    * @see #unsetMIFlowCondition()
    * @see #getMIFlowCondition()
    * @see #setMIFlowCondition(MIFlowConditionType)
    * @generated
    */
   boolean isSetMIFlowCondition();

   /**
    * Returns the value of the '<em><b>MI Ordering</b></em>' attribute.
    * The literals are from the enumeration {@link org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>MI Ordering</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>MI Ordering</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
    * @see #isSetMIOrdering()
    * @see #unsetMIOrdering()
    * @see #setMIOrdering(MIOrderingType)
    * @see org.eclipse.stardust.model.xpdl.xpdl2.XpdlPackage#getLoopMultiInstanceType_MIOrdering()
    * @model unsettable="true" required="true"
    *        extendedMetaData="kind='attribute' name='MI_Ordering'"
    * @generated
    */
   MIOrderingType getMIOrdering();

   /**
    * Sets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIOrdering <em>MI Ordering</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>MI Ordering</em>' attribute.
    * @see org.eclipse.stardust.model.xpdl.xpdl2.MIOrderingType
    * @see #isSetMIOrdering()
    * @see #unsetMIOrdering()
    * @see #getMIOrdering()
    * @generated
    */
   void setMIOrdering(MIOrderingType value);

   /**
    * Unsets the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIOrdering <em>MI Ordering</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #isSetMIOrdering()
    * @see #getMIOrdering()
    * @see #setMIOrdering(MIOrderingType)
    * @generated
    */
   void unsetMIOrdering();

   /**
    * Returns whether the value of the '{@link org.eclipse.stardust.model.xpdl.xpdl2.LoopMultiInstanceType#getMIOrdering <em>MI Ordering</em>}' attribute is set.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return whether the value of the '<em>MI Ordering</em>' attribute is set.
    * @see #unsetMIOrdering()
    * @see #getMIOrdering()
    * @see #setMIOrdering(MIOrderingType)
    * @generated
    */
   boolean isSetMIOrdering();

} // LoopMultiInstanceType
