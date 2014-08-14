/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TStardust Common</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.TStardustCommon#getElementOid <em>Element Oid</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getTStardustCommon()
 * @model extendedMetaData="name='tStardustCommon' kind='empty'"
 * @generated
 */
public interface TStardustCommon extends EObject {
	/**
	 * Returns the value of the '<em><b>Element Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Oid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Oid</em>' attribute.
	 * @see #setElementOid(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.SdbpmnPackage#getTStardustCommon_ElementOid()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='elementOid'"
	 * @generated
	 */
	String getElementOid();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.sdbpmn.TStardustCommon#getElementOid <em>Element Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Oid</em>' attribute.
	 * @see #getElementOid()
	 * @generated
	 */
	void setElementOid(String value);

} // TStardustCommon
