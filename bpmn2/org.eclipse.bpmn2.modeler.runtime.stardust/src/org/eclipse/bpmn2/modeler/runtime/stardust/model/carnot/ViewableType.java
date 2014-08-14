/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Viewable Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewableType#getViewable <em>Viewable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getViewableType()
 * @model extendedMetaData="name='viewable_._type' kind='empty'"
 * @generated
 */
public interface ViewableType extends EObject {
	/**
	 * Returns the value of the '<em><b>Viewable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The model oid of the viewable. Valid viewable model elements are
	 *                   "activity", "application", "data",
	 *                   "participant", "workflow process".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Viewable</em>' reference.
	 * @see #setViewable(IModelElement)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getViewableType_Viewable()
	 * @model resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='viewable'"
	 * @generated
	 */
	IModelElement getViewable();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewableType#getViewable <em>Viewable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Viewable</em>' reference.
	 * @see #getViewable()
	 * @generated
	 */
	void setViewable(IModelElement value);

} // ViewableType
