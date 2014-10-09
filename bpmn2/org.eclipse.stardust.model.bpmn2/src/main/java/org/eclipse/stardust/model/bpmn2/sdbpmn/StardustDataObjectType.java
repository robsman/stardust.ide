/**
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust Data Object Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getStardustAttributes <em>Stardust Attributes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getPredefined <em>Predefined</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustDataObjectType()
 * @model extendedMetaData="name='StardustDataObject_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustDataObjectType extends EObject {
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
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustDataObjectType_StardustAttributes()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='StardustAttributes' namespace='##targetNamespace'"
	 * @generated
	 */
	StardustAttributesType getStardustAttributes();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getStardustAttributes <em>Stardust Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stardust Attributes</em>' containment reference.
	 * @see #getStardustAttributes()
	 * @generated
	 */
	void setStardustAttributes(StardustAttributesType value);

	/**
	 * Returns the value of the '<em><b>Predefined</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predefined</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predefined</em>' attribute.
	 * @see #isSetPredefined()
	 * @see #unsetPredefined()
	 * @see #setPredefined(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustDataObjectType_Predefined()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='predefined'"
	 * @generated
	 */
	String getPredefined();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getPredefined <em>Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predefined</em>' attribute.
	 * @see #isSetPredefined()
	 * @see #unsetPredefined()
	 * @see #getPredefined()
	 * @generated
	 */
	void setPredefined(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getPredefined <em>Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPredefined()
	 * @see #getPredefined()
	 * @see #setPredefined(String)
	 * @generated
	 */
	void unsetPredefined();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getPredefined <em>Predefined</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Predefined</em>' attribute is set.
	 * @see #unsetPredefined()
	 * @see #getPredefined()
	 * @see #setPredefined(String)
	 * @generated
	 */
	boolean isSetPredefined();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustDataObjectType_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustDataObjectType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // StardustDataObjectType
