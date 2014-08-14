/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Context Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getAccessPointProviderClass <em>Access Point Provider Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasApplicationPath <em>Has Application Path</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasMappingId <em>Has Mapping Id</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getValidatorClass <em>Validator Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getContexts <em>Contexts</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationContextTypeType()
 * @model extendedMetaData="name='applicationContextType_._type' kind='elementOnly'"
 * @generated
 */
public interface ApplicationContextTypeType extends IMetaType {
	/**
	 * Returns the value of the '<em><b>Access Point Provider Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Access Point Provider Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Access Point Provider Class</em>' attribute.
	 * @see #setAccessPointProviderClass(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationContextTypeType_AccessPointProviderClass()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='accessPointProviderClass'"
	 * @generated
	 */
	String getAccessPointProviderClass();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getAccessPointProviderClass <em>Access Point Provider Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Access Point Provider Class</em>' attribute.
	 * @see #getAccessPointProviderClass()
	 * @generated
	 */
	void setAccessPointProviderClass(String value);

	/**
	 * Returns the value of the '<em><b>Has Application Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Application Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Application Path</em>' attribute.
	 * @see #isSetHasApplicationPath()
	 * @see #unsetHasApplicationPath()
	 * @see #setHasApplicationPath(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationContextTypeType_HasApplicationPath()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='hasApplicationPath'"
	 * @generated
	 */
	boolean isHasApplicationPath();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasApplicationPath <em>Has Application Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Application Path</em>' attribute.
	 * @see #isSetHasApplicationPath()
	 * @see #unsetHasApplicationPath()
	 * @see #isHasApplicationPath()
	 * @generated
	 */
	void setHasApplicationPath(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasApplicationPath <em>Has Application Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHasApplicationPath()
	 * @see #isHasApplicationPath()
	 * @see #setHasApplicationPath(boolean)
	 * @generated
	 */
	void unsetHasApplicationPath();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasApplicationPath <em>Has Application Path</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Has Application Path</em>' attribute is set.
	 * @see #unsetHasApplicationPath()
	 * @see #isHasApplicationPath()
	 * @see #setHasApplicationPath(boolean)
	 * @generated
	 */
	boolean isSetHasApplicationPath();

	/**
	 * Returns the value of the '<em><b>Has Mapping Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Mapping Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Mapping Id</em>' attribute.
	 * @see #isSetHasMappingId()
	 * @see #unsetHasMappingId()
	 * @see #setHasMappingId(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationContextTypeType_HasMappingId()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='hasMappingId'"
	 * @generated
	 */
	boolean isHasMappingId();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasMappingId <em>Has Mapping Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Mapping Id</em>' attribute.
	 * @see #isSetHasMappingId()
	 * @see #unsetHasMappingId()
	 * @see #isHasMappingId()
	 * @generated
	 */
	void setHasMappingId(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasMappingId <em>Has Mapping Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHasMappingId()
	 * @see #isHasMappingId()
	 * @see #setHasMappingId(boolean)
	 * @generated
	 */
	void unsetHasMappingId();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#isHasMappingId <em>Has Mapping Id</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Has Mapping Id</em>' attribute is set.
	 * @see #unsetHasMappingId()
	 * @see #isHasMappingId()
	 * @see #setHasMappingId(boolean)
	 * @generated
	 */
	boolean isSetHasMappingId();

	/**
	 * Returns the value of the '<em><b>Panel Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Panel Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Panel Class</em>' attribute.
	 * @see #setPanelClass(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationContextTypeType_PanelClass()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='panelClass'"
	 * @generated
	 */
	String getPanelClass();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getPanelClass <em>Panel Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panel Class</em>' attribute.
	 * @see #getPanelClass()
	 * @generated
	 */
	void setPanelClass(String value);

	/**
	 * Returns the value of the '<em><b>Validator Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validator Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validator Class</em>' attribute.
	 * @see #setValidatorClass(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationContextTypeType_ValidatorClass()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='validatorClass'"
	 * @generated
	 */
	String getValidatorClass();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType#getValidatorClass <em>Validator Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Validator Class</em>' attribute.
	 * @see #getValidatorClass()
	 * @generated
	 */
	void setValidatorClass(String value);

	/**
	 * Returns the value of the '<em><b>Contexts</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contexts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contexts</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationContextTypeType_Contexts()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ContextType#getType
	 * @model opposite="type" transient="true"
	 * @generated
	 */
	EList<ContextType> getContexts();

} // ApplicationContextTypeType
