/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IExtensible Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IExtensibleElement#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIExtensibleElement()
 * @model extendedMetaData="name='extensibleElement_._type' kind='empty'"
 * @generated
 */
public interface IExtensibleElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AttributeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of extension attributes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attribute</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIExtensibleElement_Attribute()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='attribute' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<AttributeType> getAttribute();

} // IExtensibleElement
