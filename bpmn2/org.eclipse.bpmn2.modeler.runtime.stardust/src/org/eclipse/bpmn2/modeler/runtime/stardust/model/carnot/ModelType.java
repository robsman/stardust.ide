/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ExternalPackages;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.ScriptType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.xpdl.TypeDeclarationsType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplicationType <em>Application Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplicationContextType <em>Application Context Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getTriggerType <em>Trigger Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getEventConditionType <em>Event Condition Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getEventActionType <em>Event Action Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModeler <em>Modeler</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getQualityControl <em>Quality Control</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getRole <em>Role</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOrganization <em>Organization</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getConditionalPerformer <em>Conditional Performer</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getProcessDefinition <em>Process Definition</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getExternalPackages <em>External Packages</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getScript <em>Script</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getTypeDeclarations <em>Type Declarations</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getLinkType <em>Link Type</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getView <em>View</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCarnotVersion <em>Carnot Version</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCreated <em>Created</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModelOID <em>Model OID</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOid <em>Oid</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getVendor <em>Vendor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType()
 * @model extendedMetaData="name='model_._type' kind='elementOnly'"
 * @generated
 */
public interface ModelType extends IIdentifiableElement, IExtensibleElement {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      An optional description of this model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(DescriptionType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Description()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='description' namespace='##targetNamespace'"
	 * @generated
	 */
	DescriptionType getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getDescription <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' containment reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(DescriptionType value);

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of data types registered for use with this model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_DataType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='dataType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DataTypeType> getDataType();

	/**
	 * Returns the value of the '<em><b>Application Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of application types registered for use with this model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_ApplicationType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='applicationType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ApplicationTypeType> getApplicationType();

	/**
	 * Returns the value of the '<em><b>Application Context Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationContextTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of aplication context types regisred for use with this
	 *                      model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application Context Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_ApplicationContextType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='applicationContextType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ApplicationContextTypeType> getApplicationContextType();

	/**
	 * Returns the value of the '<em><b>Trigger Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggerTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of process trigger types registered for use with this model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trigger Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_TriggerType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='triggerType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TriggerTypeType> getTriggerType();

	/**
	 * Returns the value of the '<em><b>Event Condition Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventConditionTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of event condition types registered for use with this model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Condition Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_EventConditionType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='eventConditionType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<EventConditionTypeType> getEventConditionType();

	/**
	 * Returns the value of the '<em><b>Event Action Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EventActionTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of event action types registered for use with this model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Action Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_EventActionType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='eventActionType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<EventActionTypeType> getEventActionType();

	/**
	 * Returns the value of the '<em><b>Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of workflow data defined at model scope.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Data()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='data' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DataType> getData();

	/**
	 * Returns the value of the '<em><b>Application</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of workflow applications defined at model scope.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Application()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='application' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ApplicationType> getApplication();

	/**
	 * Returns the value of the '<em><b>Modeler</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of users having granted rights to check out this model from
	 *                      the model repository.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modeler</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Modeler()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='modeler' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ModelerType> getModeler();

	/**
	 * Returns the value of the '<em><b>Quality Control</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Quality Control of the Model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Quality Control</em>' containment reference.
	 * @see #setQualityControl(QualityControlType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_QualityControl()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='qualityControl' namespace='http://www.carnot.ag/xpdl/3.1'"
	 * @generated
	 */
	QualityControlType getQualityControl();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getQualityControl <em>Quality Control</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quality Control</em>' containment reference.
	 * @see #getQualityControl()
	 * @generated
	 */
	void setQualityControl(QualityControlType value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of workflow participant roles defined at model scope.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Role</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Role()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='role' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<RoleType> getRole();

	/**
	 * Returns the value of the '<em><b>Organization</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of workflow participant organizations defined at model
	 *                      scope.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Organization</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Organization()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='organization' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<OrganizationType> getOrganization();

	/**
	 * Returns the value of the '<em><b>Conditional Performer</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of conditional (late bound) workflow participants defined at
	 *                      model scope.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conditional Performer</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_ConditionalPerformer()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='conditionalPerformer' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ConditionalPerformerType> getConditionalPerformer();

	/**
	 * Returns the value of the '<em><b>Process Definition</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessDefinitionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of workflow process definitions.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Process Definition</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_ProcessDefinition()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='processDefinition' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ProcessDefinitionType> getProcessDefinition();

	/**
	 * Returns the value of the '<em><b>External Packages</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the type declarations container.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>External Packages</em>' containment reference.
	 * @see #setExternalPackages(ExternalPackages)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_ExternalPackages()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExternalPackages' namespace='http://www.wfmc.org/2008/XPDL2.1'"
	 * @generated
	 */
	ExternalPackages getExternalPackages();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getExternalPackages <em>External Packages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Packages</em>' containment reference.
	 * @see #getExternalPackages()
	 * @generated
	 */
	void setExternalPackages(ExternalPackages value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the scripting language information.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(ScriptType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Script()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Script' namespace='http://www.wfmc.org/2008/XPDL2.1'"
	 * @generated
	 */
	ScriptType getScript();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(ScriptType value);

	/**
	 * Returns the value of the '<em><b>Type Declarations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the type declarations container.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Declarations</em>' containment reference.
	 * @see #setTypeDeclarations(TypeDeclarationsType)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_TypeDeclarations()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TypeDeclarations' namespace='http://www.wfmc.org/2008/XPDL2.1'"
	 * @generated
	 */
	TypeDeclarationsType getTypeDeclarations();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getTypeDeclarations <em>Type Declarations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Declarations</em>' containment reference.
	 * @see #getTypeDeclarations()
	 * @generated
	 */
	void setTypeDeclarations(TypeDeclarationsType value);

	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DiagramType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of diagrams defined at model scope.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Diagram</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Diagram()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='diagram' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DiagramType> getDiagram();

	/**
	 * Returns the value of the '<em><b>Link Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.LinkTypeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of user defined link types registered for use with this
	 *                      model.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link Type</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_LinkType()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='linkType' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<LinkTypeType> getLinkType();

	/**
	 * Returns the value of the '<em><b>View</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ViewType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                      The list of user defined views on the model structure.
	 *                   
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>View</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_View()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='view' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ViewType> getView();

	/**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The name of the user editing this model last.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Author()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='author'"
	 * @generated
	 */
	String getAuthor();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(String value);

	/**
	 * Returns the value of the '<em><b>Carnot Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The software version used to create and edit this model. Opening this
	 *                   model in another version of the software may require an explicit
	 *                   conversion.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Carnot Version</em>' attribute.
	 * @see #setCarnotVersion(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_CarnotVersion()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='carnotVersion'"
	 * @generated
	 */
	String getCarnotVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCarnotVersion <em>Carnot Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Carnot Version</em>' attribute.
	 * @see #getCarnotVersion()
	 * @generated
	 */
	void setCarnotVersion(String value);

	/**
	 * Returns the value of the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The date this model was created.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Created</em>' attribute.
	 * @see #setCreated(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Created()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='created'"
	 * @generated
	 */
	String getCreated();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getCreated <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created</em>' attribute.
	 * @see #getCreated()
	 * @generated
	 */
	void setCreated(String value);

	/**
	 * Returns the value of the '<em><b>Model OID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   A 32-bit number assigned to the model during deployment to an audit
	 *                   trail. This number may be changed if the model is deployed to different
	 *                   audit trails or even to the same audit trail multiple times.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model OID</em>' attribute.
	 * @see #isSetModelOID()
	 * @see #unsetModelOID()
	 * @see #setModelOID(int)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_ModelOID()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
	 *        extendedMetaData="kind='attribute' name='modelOID'"
	 * @generated
	 */
	int getModelOID();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModelOID <em>Model OID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model OID</em>' attribute.
	 * @see #isSetModelOID()
	 * @see #unsetModelOID()
	 * @see #getModelOID()
	 * @generated
	 */
	void setModelOID(int value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModelOID <em>Model OID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetModelOID()
	 * @see #getModelOID()
	 * @see #setModelOID(int)
	 * @generated
	 */
	void unsetModelOID();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getModelOID <em>Model OID</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Model OID</em>' attribute is set.
	 * @see #unsetModelOID()
	 * @see #getModelOID()
	 * @see #setModelOID(int)
	 * @generated
	 */
	boolean isSetModelOID();

	/**
	 * Returns the value of the '<em><b>Oid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   A 64-bit number uniquely identifying the model in the model repository.
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Oid</em>' attribute.
	 * @see #isSetOid()
	 * @see #unsetOid()
	 * @see #setOid(long)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Oid()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Long" required="true"
	 *        extendedMetaData="kind='attribute' name='oid'"
	 * @generated
	 */
	long getOid();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOid <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Oid</em>' attribute.
	 * @see #isSetOid()
	 * @see #unsetOid()
	 * @see #getOid()
	 * @generated
	 */
	void setOid(long value);

	/**
	 * Unsets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOid <em>Oid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetOid()
	 * @see #getOid()
	 * @see #setOid(long)
	 * @generated
	 */
	void unsetOid();

	/**
	 * Returns whether the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getOid <em>Oid</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Oid</em>' attribute is set.
	 * @see #unsetOid()
	 * @see #getOid()
	 * @see #setOid(long)
	 * @generated
	 */
	boolean isSetOid();

	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                   The vendor of the software used to create and edit this model. Usually
	 *                   this will be "carnot".
	 *                
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getModelType_Vendor()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='vendor'"
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelType#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

} // ModelType
