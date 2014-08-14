/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IMeta Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIMetaType()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='metaType_._type' kind='empty'"
 * @generated
 */
public interface IMetaType extends IIdentifiableModelElement {
	/**
	 * Returns the value of the '<em><b>Is Predefined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A boolean that indicates whether this meta type is predefined by CARNOT or model specific.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Predefined</em>' attribute.
	 * @see #isSetIsPredefined()
	 * @see #unsetIsPredefined()
	 * @see #setIsPredefined(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getIMetaType_IsPredefined()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='predefined'"
	 * @generated
	 */
	boolean isIsPredefined();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Predefined</em>' attribute.
	 * @see #isSetIsPredefined()
	 * @see #unsetIsPredefined()
	 * @see #isIsPredefined()
	 * @generated
	 */
	void setIsPredefined(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsPredefined()
	 * @see #isIsPredefined()
	 * @see #setIsPredefined(boolean)
	 * @generated
	 */
	void unsetIsPredefined();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IMetaType#isIsPredefined <em>Is Predefined</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Predefined</em>' attribute is set.
	 * @see #unsetIsPredefined()
	 * @see #isIsPredefined()
	 * @see #setIsPredefined(boolean)
	 * @generated
	 */
	boolean isSetIsPredefined();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 * @generated
	 */
	String getExtensionPointId();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<ITypedElement> getTypedElements();

} // IMetaType
