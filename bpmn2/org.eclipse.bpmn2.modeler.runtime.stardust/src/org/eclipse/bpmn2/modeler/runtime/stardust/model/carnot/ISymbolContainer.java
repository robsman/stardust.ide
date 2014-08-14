/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ISymbol Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getActivitySymbol <em>Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getAnnotationSymbol <em>Annotation Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getApplicationSymbol <em>Application Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConditionalPerformerSymbol <em>Conditional Performer Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getDataSymbol <em>Data Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getEndEventSymbols <em>End Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGatewaySymbol <em>Gateway Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGroupSymbol <em>Group Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getIntermediateEventSymbols <em>Intermediate Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getModelerSymbol <em>Modeler Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getOrganizationSymbol <em>Organization Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getProcessSymbol <em>Process Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getProcessInterfaceSymbols <em>Process Interface Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getRoleSymbol <em>Role Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getStartEventSymbols <em>Start Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTextSymbol <em>Text Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getDataMappingConnection <em>Data Mapping Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getExecutedByConnection <em>Executed By Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getGenericLinkConnection <em>Generic Link Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getPartOfConnection <em>Part Of Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getPerformsConnection <em>Performs Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTriggersConnection <em>Triggers Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getRefersToConnection <em>Refers To Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getSubProcessOfConnection <em>Sub Process Of Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTransitionConnection <em>Transition Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getWorksForConnection <em>Works For Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer#getTeamLeadConnection <em>Team Lead Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer()
 * @model abstract="true"
 *        extendedMetaData="name='nodeSymbolContainer_._type' kind='empty'"
 * @generated
 */
public interface ISymbolContainer extends IExtensibleElement {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' attribute list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_Nodes()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:nodes'"
	 * @generated
	 */
	FeatureMap getNodes();

	/**
	 * Returns the value of the '<em><b>Activity Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of activity symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_ActivitySymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='activitySymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<ActivitySymbolType> getActivitySymbol();

	/**
	 * Returns the value of the '<em><b>Annotation Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of annotation symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Annotation Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_AnnotationSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='annotationSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<AnnotationSymbolType> getAnnotationSymbol();

	/**
	 * Returns the value of the '<em><b>Application Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of application symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Application Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_ApplicationSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='applicationSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<ApplicationSymbolType> getApplicationSymbol();

	/**
	 * Returns the value of the '<em><b>Conditional Performer Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of conditional performer symbols.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Conditional Performer Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_ConditionalPerformerSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conditionalPerformerSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<ConditionalPerformerSymbolType> getConditionalPerformerSymbol();

	/**
	 * Returns the value of the '<em><b>Data Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of data symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_DataSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<DataSymbolType> getDataSymbol();

	/**
	 * Returns the value of the '<em><b>End Event Symbols</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EndEventSymbol}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of end event symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>End Event Symbols</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_EndEventSymbols()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='endEventSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<EndEventSymbol> getEndEventSymbols();

	/**
	 * Returns the value of the '<em><b>Gateway Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of gateway symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Gateway Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_GatewaySymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='gatewaySymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<GatewaySymbol> getGatewaySymbol();

	/**
	 * Returns the value of the '<em><b>Group Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GroupSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of group symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Group Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_GroupSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='groupSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<GroupSymbolType> getGroupSymbol();

	/**
	 * Returns the value of the '<em><b>Intermediate Event Symbols</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IntermediateEventSymbol}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of intermediate event symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Intermediate Event Symbols</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_IntermediateEventSymbols()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='intermediateEventSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<IntermediateEventSymbol> getIntermediateEventSymbols();

	/**
	 * Returns the value of the '<em><b>Modeler Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of modeler symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modeler Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_ModelerSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='modelerSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<ModelerSymbolType> getModelerSymbol();

	/**
	 * Returns the value of the '<em><b>Organization Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of organization symbols.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Organization Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_OrganizationSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='organizationSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<OrganizationSymbolType> getOrganizationSymbol();

	/**
	 * Returns the value of the '<em><b>Process Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of workflow process symbols. Only possible for diagrams
	 *                         in workflow model scope.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Process Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_ProcessSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='processSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<ProcessSymbolType> getProcessSymbol();

	/**
	 * Returns the value of the '<em><b>Process Interface Symbols</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PublicInterfaceSymbol}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of public interface symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Process Interface Symbols</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_ProcessInterfaceSymbols()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='publicInterfaceSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<PublicInterfaceSymbol> getProcessInterfaceSymbols();

	/**
	 * Returns the value of the '<em><b>Role Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of role symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Role Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_RoleSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='roleSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<RoleSymbolType> getRoleSymbol();

	/**
	 * Returns the value of the '<em><b>Start Event Symbols</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of start event symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Event Symbols</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_StartEventSymbols()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='startEventSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<StartEventSymbol> getStartEventSymbols();

	/**
	 * Returns the value of the '<em><b>Text Symbol</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextSymbolType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of text symbols.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Text Symbol</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_TextSymbol()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='textSymbol' namespace='##targetNamespace' group='#group:nodes'"
	 * @generated
	 */
	EList<TextSymbolType> getTextSymbol();

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' attribute list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_Connections()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:connections'"
	 * @generated
	 */
	FeatureMap getConnections();

	/**
	 * Returns the value of the '<em><b>Data Mapping Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of data mapping connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Mapping Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_DataMappingConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataMappingConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<DataMappingConnectionType> getDataMappingConnection();

	/**
	 * Returns the value of the '<em><b>Executed By Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of "executed by"-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Executed By Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_ExecutedByConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='executedByConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<ExecutedByConnectionType> getExecutedByConnection();

	/**
	 * Returns the value of the '<em><b>Generic Link Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of generic link connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Generic Link Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_GenericLinkConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='genericLinkConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<GenericLinkConnectionType> getGenericLinkConnection();

	/**
	 * Returns the value of the '<em><b>Part Of Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of "part of"-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Part Of Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_PartOfConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='partOfConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<PartOfConnectionType> getPartOfConnection();

	/**
	 * Returns the value of the '<em><b>Performs Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of performs-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Performs Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_PerformsConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='performsConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<PerformsConnectionType> getPerformsConnection();

	/**
	 * Returns the value of the '<em><b>Triggers Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of performs-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Triggers Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_TriggersConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='triggersConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<TriggersConnectionType> getTriggersConnection();

	/**
	 * Returns the value of the '<em><b>Refers To Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of "refers to"-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Refers To Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_RefersToConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='refersToConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<RefersToConnectionType> getRefersToConnection();

	/**
	 * Returns the value of the '<em><b>Sub Process Of Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of "subprocess of"-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sub Process Of Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_SubProcessOfConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subprocessOfConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<SubProcessOfConnectionType> getSubProcessOfConnection();

	/**
	 * Returns the value of the '<em><b>Transition Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of transition connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transition Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_TransitionConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='transitionConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<TransitionConnectionType> getTransitionConnection();

	/**
	 * Returns the value of the '<em><b>Works For Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of "works for"-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Works For Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_WorksForConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='worksForConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<WorksForConnectionType> getWorksForConnection();

	/**
	 * Returns the value of the '<em><b>Team Lead Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *                         The list of "team lead"-connections.
	 *                      
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Team Lead Connection</em>' containment reference list.
	 * @see org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage#getISymbolContainer_TeamLeadConnection()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='teamLeadConnection' namespace='##targetNamespace' group='#group:connections'"
	 * @generated
	 */
	EList<TeamLeadConnectionType> getTeamLeadConnection();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FeatureList" required="true" many="false"
	 * @generated
	 */
	List getNodeContainingFeatures();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.FeatureList" required="true" many="false"
	 * @generated
	 */
	List getConnectionContainingFeatures();

} // ISymbolContainer
