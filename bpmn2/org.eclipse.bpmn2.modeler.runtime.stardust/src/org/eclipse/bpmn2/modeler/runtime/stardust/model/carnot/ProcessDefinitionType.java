/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.extensions.FormalParameterMappingsType;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.FormalParametersType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Definition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getTransition <em>Transition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExecutingActivities <em>Executing Activities</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getProcessSymbols <em>Process Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDefaultPriority <em>Default Priority</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameterMappings <em>Formal Parameter Mappings</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExternalRef <em>External Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType()
 * @model extendedMetaData="name='processDefinition_._type' kind='elementOnly'"
 * @generated
 */
public interface ProcessDefinitionType extends IIdentifiableModelElement, IEventHandlerOwner {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of activity definitions of the workflow process definition.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_Activity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='activity' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ActivityType> getActivity();

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of transitions between the activity definitions of the
	 *                      workflow process definition.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transition</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_Transition()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='transition' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TransitionType> getTransition();

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of triggers of the workflow process definition.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_Trigger()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='trigger' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TriggerType> getTrigger();

	/**
	 * Returns the value of the '<em><b>Data Path</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataPathType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of data paths of the workflow process definition.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Path</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_DataPath()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataPath' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DataPathType> getDataPath();

	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of diagrams of the workflow process definition.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Diagram</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_Diagram()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='diagram' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DiagramType> getDiagram();

	/**
	 * Returns the value of the '<em><b>Executing Activities</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementationProcess <em>Implementation Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executing Activities</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executing Activities</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_ExecutingActivities()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivityType#getImplementationProcess
	 * @model opposite="implementationProcess" transient="true"
	 * @generated
	 */
	EList<ActivityType> getExecutingActivities();

	/**
	 * Returns the value of the '<em><b>Process Symbols</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Symbols</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process Symbols</em>' reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_ProcessSymbols()
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType#getProcess
	 * @model opposite="process" transient="true"
	 * @generated
	 */
	EList<ProcessSymbolType> getProcessSymbols();

	/**
	 * Returns the value of the '<em><b>Default Priority</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Default priority assigned to a process instance at startup.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Priority</em>' attribute.
	 * @see #setDefaultPriority(int)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_DefaultPriority()
	 * @model default="0" dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="kind='attribute' name='defaultPriority'"
	 * @generated
	 */
	int getDefaultPriority();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getDefaultPriority <em>Default Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Priority</em>' attribute.
	 * @see #getDefaultPriority()
	 * @generated
	 */
	void setDefaultPriority(int value);

	/**
	 * Returns the value of the '<em><b>Formal Parameters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the formal parameters container.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Formal Parameters</em>' containment reference.
	 * @see #setFormalParameters(FormalParametersType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_FormalParameters()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FormalParameters' namespace='http://www.wfmc.org/2008/XPDL2.1'"
	 * @generated
	 */
	FormalParametersType getFormalParameters();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameters <em>Formal Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formal Parameters</em>' containment reference.
	 * @see #getFormalParameters()
	 * @generated
	 */
	void setFormalParameters(FormalParametersType value);

	/**
	 * Returns the value of the '<em><b>Formal Parameter Mappings</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the formal parameter mappings container.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Formal Parameter Mappings</em>' containment reference.
	 * @see #setFormalParameterMappings(FormalParameterMappingsType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_FormalParameterMappings()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FormalParameterMappings' namespace='http://www.carnot.ag/xpdl/3.1'"
	 * @generated
	 */
	FormalParameterMappingsType getFormalParameterMappings();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getFormalParameterMappings <em>Formal Parameter Mappings</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formal Parameter Mappings</em>' containment reference.
	 * @see #getFormalParameterMappings()
	 * @generated
	 */
	void setFormalParameterMappings(FormalParameterMappingsType value);

	/**
	 * Returns the value of the '<em><b>External Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Ref</em>' containment reference.
	 * @see #setExternalRef(IdRef)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getProcessDefinitionType_ExternalRef()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='externalReference' namespace='##targetNamespace'"
	 * @generated
	 */
	IdRef getExternalRef();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType#getExternalRef <em>External Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Ref</em>' containment reference.
	 * @see #getExternalRef()
	 * @generated
	 */
	void setExternalRef(IdRef value);

} // ProcessDefinitionType
