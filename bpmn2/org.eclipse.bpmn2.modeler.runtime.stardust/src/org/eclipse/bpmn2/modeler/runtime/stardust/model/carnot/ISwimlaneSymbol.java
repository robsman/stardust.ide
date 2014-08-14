/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ISwimlane Symbol</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getChildLanes <em>Child Lanes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipantReference <em>Participant Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISwimlaneSymbol()
 * @model interface="true" abstract="true"
 *        extendedMetaData="name='compartmentSymbol_._type' kind='empty'"
 * @generated
 */
public interface ISwimlaneSymbol extends INodeSymbol, IIdentifiableElement {
	/**
	 * Returns the value of the '<em><b>Orientation</b></em>' attribute.
	 * The default value is <code>"Vertical"</code>.
	 * The literals are from the enumeration {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The orientation of the compartment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Orientation</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType
	 * @see #isSetOrientation()
	 * @see #unsetOrientation()
	 * @see #setOrientation(OrientationType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISwimlaneSymbol_Orientation()
	 * @model default="Vertical" unique="false" unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='orientation'"
	 * @generated
	 */
	OrientationType getOrientation();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation</em>' attribute.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrientationType
	 * @see #isSetOrientation()
	 * @see #unsetOrientation()
	 * @see #getOrientation()
	 * @generated
	 */
	void setOrientation(OrientationType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOrientation()
	 * @see #getOrientation()
	 * @see #setOrientation(OrientationType)
	 * @generated
	 */
	void unsetOrientation();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getOrientation <em>Orientation</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Orientation</em>' attribute is set.
	 * @see #unsetOrientation()
	 * @see #getOrientation()
	 * @see #setOrientation(OrientationType)
	 * @generated
	 */
	boolean isSetOrientation();

	/**
	 * Returns the value of the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The collapsed state of the compartment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Collapsed</em>' attribute.
	 * @see #setCollapsed(boolean)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISwimlaneSymbol_Collapsed()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='collapsed'"
	 * @generated
	 */
	boolean isCollapsed();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#isCollapsed <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collapsed</em>' attribute.
	 * @see #isCollapsed()
	 * @generated
	 */
	void setCollapsed(boolean value);

	/**
	 * Returns the value of the '<em><b>Participant</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedSwimlanes <em>Performed Swimlanes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A reference to the associated participant, if existent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participant</em>' reference.
	 * @see #setParticipant(IModelParticipant)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISwimlaneSymbol_Participant()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IModelParticipant#getPerformedSwimlanes
	 * @model opposite="performedSwimlanes" resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='participant'"
	 * @generated
	 */
	IModelParticipant getParticipant();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipant <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant</em>' reference.
	 * @see #getParticipant()
	 * @generated
	 */
	void setParticipant(IModelParticipant value);

	/**
	 * Returns the value of the '<em><b>Child Lanes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentLane <em>Parent Lane</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Lanes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Lanes</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISwimlaneSymbol_ChildLanes()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LaneSymbol#getParentLane
	 * @model opposite="parentLane" resolveProxies="false" transient="true"
	 * @generated
	 */
	EList<LaneSymbol> getChildLanes();

	/**
	 * Returns the value of the '<em><b>Participant Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A reference to the associated participant, if existent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participant Reference</em>' reference.
	 * @see #setParticipantReference(IModelParticipant)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISwimlaneSymbol_ParticipantReference()
	 * @model resolveProxies="false"
	 *        extendedMetaData="kind='attribute' name='participantReference'"
	 *        annotation="http://www.carnot.ag/workflow/model/ElementIdRef scope='model'"
	 * @generated
	 */
	IModelParticipant getParticipantReference();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISwimlaneSymbol#getParticipantReference <em>Participant Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant Reference</em>' reference.
	 * @see #getParticipantReference()
	 * @generated
	 */
	void setParticipantReference(IModelParticipant value);

} // ISwimlaneSymbol
