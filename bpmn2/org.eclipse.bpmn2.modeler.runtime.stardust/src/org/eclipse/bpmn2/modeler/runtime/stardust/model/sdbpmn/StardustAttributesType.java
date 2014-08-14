/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Attributes Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.StardustAttributesType#getAttributeType <em>Attribute Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustAttributesType()
 * @model extendedMetaData="name='StardustAttributes_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustAttributesType extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Type</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getStardustAttributesType_AttributeType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='AttributeType' namespace='http://www.carnot.ag/workflowmodel/3.1'"
	 * @generated
	 */
	EList<AttributeType> getAttributeType();

} // StardustAttributesType
