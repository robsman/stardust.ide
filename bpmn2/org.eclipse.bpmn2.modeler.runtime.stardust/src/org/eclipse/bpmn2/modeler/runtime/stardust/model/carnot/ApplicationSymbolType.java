/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application Symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType#getExecutingActivities <em>Executing Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType#getApplication <em>Application</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationSymbolType()
 * @model extendedMetaData="name='applicationSymbol_._type' kind='empty'"
 * @generated
 */
public interface ApplicationSymbolType extends IModelElementNodeSymbol {
	/**
	 * Returns the value of the '<em><b>Executing Activities</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType#getApplicationSymbol <em>Application Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executing Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executing Activities</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationSymbolType_ExecutingActivities()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType#getApplicationSymbol
	 * @model opposite="applicationSymbol" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<ExecutedByConnectionType> getExecutingActivities();

	/**
	 * Returns the value of the '<em><b>Application</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getApplicationSymbols <em>Application Symbols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The id of the corresponding activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application</em>' reference.
	 * @see #setApplication(ApplicationType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getApplicationSymbolType_Application()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType#getApplicationSymbols
	 * @model opposite="applicationSymbols" resolveProxies="false" required="true"
	 *        extendedMetaData="kind='attribute' name='refer'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	ApplicationType getApplication();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType#getApplication <em>Application</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application</em>' reference.
	 * @see #getApplication()
	 * @generated
	 */
	void setApplication(ApplicationType value);

} // ApplicationSymbolType
