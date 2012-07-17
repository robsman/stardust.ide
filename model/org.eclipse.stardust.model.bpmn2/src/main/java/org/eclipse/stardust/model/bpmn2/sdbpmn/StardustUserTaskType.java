/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.stardust.model.bpmn2.sdbpmn;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stardust User Task Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustUserTaskType()
 * @model extendedMetaData="name='StardustUserTask_._type' kind='elementOnly'"
 * @generated
 */
public interface StardustUserTaskType extends TStardustActivity {
	/**
	 * Returns the value of the '<em><b>Allows Abort By Performer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines whether the activity is allowed to be aborted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Allows Abort By Performer</em>' attribute.
	 * @see #isSetAllowsAbortByPerformer()
	 * @see #unsetAllowsAbortByPerformer()
	 * @see #setAllowsAbortByPerformer(boolean)
	 * @see org.eclipse.stardust.model.bpmn2.sdbpmn.SdbpmnPackage#getStardustUserTaskType_AllowsAbortByPerformer()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='allowsAbortByPerformer'"
	 * @generated
	 */
	boolean isAllowsAbortByPerformer();

	/**
	 * Sets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allows Abort By Performer</em>' attribute.
	 * @see #isSetAllowsAbortByPerformer()
	 * @see #unsetAllowsAbortByPerformer()
	 * @see #isAllowsAbortByPerformer()
	 * @generated
	 */
	void setAllowsAbortByPerformer(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAllowsAbortByPerformer()
	 * @see #isAllowsAbortByPerformer()
	 * @see #setAllowsAbortByPerformer(boolean)
	 * @generated
	 */
	void unsetAllowsAbortByPerformer();

	/**
	 * Returns whether the value of the '{@link org.eclipse.stardust.model.bpmn2.sdbpmn.StardustUserTaskType#isAllowsAbortByPerformer <em>Allows Abort By Performer</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Allows Abort By Performer</em>' attribute is set.
	 * @see #unsetAllowsAbortByPerformer()
	 * @see #isAllowsAbortByPerformer()
	 * @see #setAllowsAbortByPerformer(boolean)
	 * @generated
	 */
	boolean isSetAllowsAbortByPerformer();

} // StardustUserTaskType
