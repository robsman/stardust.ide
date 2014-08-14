/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl;

import org.eclipse.xsd.XSDSchema;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schema Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.SchemaTypeType#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getSchemaTypeType()
 * @model extendedMetaData="name='SchemaType_._type' kind='elementOnly'"
 * @generated
 */
public interface SchemaTypeType extends XpdlTypeType {
	/**
	 * Returns the value of the '<em><b>Schema</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' containment reference.
	 * @see #setSchema(XSDSchema)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.XpdlPackage#getSchemaTypeType_Schema()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='schema' namespace='http://www.w3.org/2001/XMLSchema'"
	 * @generated
	 */
	XSDSchema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.SchemaTypeType#getSchema <em>Schema</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' containment reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(XSDSchema value);

} // SchemaTypeType
