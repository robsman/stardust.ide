/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Process Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType#getStardustAttributes <em>Stardust Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType#isSupportsProcessAttachments <em>Supports Process Attachments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustProcessType()
 * @model extendedMetaData="name='StardustProcess_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustProcessType extends EObject {
	/**
	 * Returns the value of the '<em><b>Stardust Attributes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stardust Attributes</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stardust Attributes</em>' containment reference.
	 * @see #setStardustAttributes(StardustAttributesType)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustProcessType_StardustAttributes()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='StardustAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustAttributesType getStardustAttributes();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType#getStardustAttributes <em>Stardust Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Attributes</em>' containment reference.
	 * @see #getStardustAttributes()
	 * @generated
	 */
	void setStardustAttributes(StardustAttributesType value);

	/**
	 * Returns the value of the '<em><b>Supports Process Attachments</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supports Process Attachments</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports Process Attachments</em>' attribute.
	 * @see #isSetSupportsProcessAttachments()
	 * @see #unsetSupportsProcessAttachments()
	 * @see #setSupportsProcessAttachments(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustProcessType_SupportsProcessAttachments()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='supportsProcessAttachments'"
	 * @generated
	 */
	boolean isSupportsProcessAttachments();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType#isSupportsProcessAttachments <em>Supports Process Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Supports Process Attachments</em>' attribute.
	 * @see #isSetSupportsProcessAttachments()
	 * @see #unsetSupportsProcessAttachments()
	 * @see #isSupportsProcessAttachments()
	 * @generated
	 */
	void setSupportsProcessAttachments(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType#isSupportsProcessAttachments <em>Supports Process Attachments</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSupportsProcessAttachments()
	 * @see #isSupportsProcessAttachments()
	 * @see #setSupportsProcessAttachments(boolean)
	 * @generated
	 */
	void unsetSupportsProcessAttachments();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustProcessType#isSupportsProcessAttachments <em>Supports Process Attachments</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Supports Process Attachments</em>' attribute is set.
	 * @see #unsetSupportsProcessAttachments()
	 * @see #isSupportsProcessAttachments()
	 * @see #setSupportsProcessAttachments(boolean)
	 * @generated
	 */
	boolean isSetSupportsProcessAttachments();

} // StardustProcessType
