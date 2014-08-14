/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPanelClass <em>Panel Class</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#isPullTrigger <em>Pull Trigger</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPullTriggerEvaluator <em>Pull Trigger Evaluator</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getRule <em>Rule</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getTriggers <em>Triggers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggerTypeType()
 * @model extendedMetaData="name='triggerType_._type' kind='elementOnly'"
 * @generated
 */
public interface TriggerTypeType extends IMetaType {
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
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggerTypeType_PanelClass()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='panelClass'"
	 * @generated
	 */
	String getPanelClass();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPanelClass <em>Panel Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Panel Class</em>' attribute.
	 * @see #getPanelClass()
	 * @generated
	 */
	void setPanelClass(String value);

	/**
	 * Returns the value of the '<em><b>Pull Trigger</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   A boolean that indicates whether the trigger type describes a
	 *                   pulltrigger. A pull trigger (e.g. mail) has its own trigger daemon.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pull Trigger</em>' attribute.
	 * @see #isSetPullTrigger()
	 * @see #unsetPullTrigger()
	 * @see #setPullTrigger(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggerTypeType_PullTrigger()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='pullTrigger'"
	 * @generated
	 */
	boolean isPullTrigger();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#isPullTrigger <em>Pull Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pull Trigger</em>' attribute.
	 * @see #isSetPullTrigger()
	 * @see #unsetPullTrigger()
	 * @see #isPullTrigger()
	 * @generated
	 */
	void setPullTrigger(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#isPullTrigger <em>Pull Trigger</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPullTrigger()
	 * @see #isPullTrigger()
	 * @see #setPullTrigger(boolean)
	 * @generated
	 */
	void unsetPullTrigger();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#isPullTrigger <em>Pull Trigger</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Pull Trigger</em>' attribute is set.
	 * @see #unsetPullTrigger()
	 * @see #isPullTrigger()
	 * @see #setPullTrigger(boolean)
	 * @generated
	 */
	boolean isSetPullTrigger();

	/**
	 * Returns the value of the '<em><b>Pull Trigger Evaluator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pull Trigger Evaluator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pull Trigger Evaluator</em>' attribute.
	 * @see #setPullTriggerEvaluator(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggerTypeType_PullTriggerEvaluator()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='pullTriggerEvaluator'"
	 * @generated
	 */
	String getPullTriggerEvaluator();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getPullTriggerEvaluator <em>Pull Trigger Evaluator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pull Trigger Evaluator</em>' attribute.
	 * @see #getPullTriggerEvaluator()
	 * @generated
	 */
	void setPullTriggerEvaluator(String value);

	/**
	 * Returns the value of the '<em><b>Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rule</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rule</em>' attribute.
	 * @see #setRule(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggerTypeType_Rule()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='rule'"
	 * @generated
	 */
	String getRule();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType#getRule <em>Rule</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rule</em>' attribute.
	 * @see #getRule()
	 * @generated
	 */
	void setRule(String value);

	/**
	 * Returns the value of the '<em><b>Triggers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Triggers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Triggers</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getTriggerTypeType_Triggers()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType#getType
	 * @model opposite="type" transient="true"
	 * @generated
	 */
	EList<TriggerType> getTriggers();

} // TriggerTypeType
