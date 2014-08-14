/**
 */
package org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;
import java.util.List;

import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ActivitySymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.AnnotationSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ApplicationSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.CarnotPackage;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ConditionalPerformerSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataMappingConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.DataSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.EndEventSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ExecutedByConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GatewaySymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GenericLinkConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.GroupSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ISymbolContainer;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.IntermediateEventSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ModelerSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.OrganizationSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PartOfConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PerformsConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.ProcessSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.PublicInterfaceSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RefersToConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.RoleSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.StartEventSymbol;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.SubProcessOfConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TeamLeadConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TextSymbolType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TransitionConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.TriggersConnectionType;
import org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.WorksForConnectionType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ISymbol Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getActivitySymbol <em>Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getAnnotationSymbol <em>Annotation Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getApplicationSymbol <em>Application Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getConditionalPerformerSymbol <em>Conditional Performer Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getDataSymbol <em>Data Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getEndEventSymbols <em>End Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getGatewaySymbol <em>Gateway Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getGroupSymbol <em>Group Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getIntermediateEventSymbols <em>Intermediate Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getModelerSymbol <em>Modeler Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getOrganizationSymbol <em>Organization Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getProcessSymbol <em>Process Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getProcessInterfaceSymbols <em>Process Interface Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getRoleSymbol <em>Role Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getStartEventSymbols <em>Start Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getTextSymbol <em>Text Symbol</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getDataMappingConnection <em>Data Mapping Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getExecutedByConnection <em>Executed By Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getGenericLinkConnection <em>Generic Link Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getPartOfConnection <em>Part Of Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getPerformsConnection <em>Performs Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getTriggersConnection <em>Triggers Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getRefersToConnection <em>Refers To Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getSubProcessOfConnection <em>Sub Process Of Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getTransitionConnection <em>Transition Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getWorksForConnection <em>Works For Connection</em>}</li>
 *   <li>{@link org.eclipse.bpmn2.modeler.runtime.stardust.model.carnot.impl.ISymbolContainerImpl#getTeamLeadConnection <em>Team Lead Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ISymbolContainerImpl extends IExtensibleElementImpl implements ISymbolContainer {
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap nodes;

	/**
	 * The cached value of the '{@link #getConnections() <em>Connections</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap connections;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ISymbolContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CarnotPackage.eINSTANCE.getISymbolContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getNodes() {
		if (nodes == null) {
			nodes = new BasicFeatureMap(this, CarnotPackage.ISYMBOL_CONTAINER__NODES);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivitySymbolType> getActivitySymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_ActivitySymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AnnotationSymbolType> getAnnotationSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_AnnotationSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ApplicationSymbolType> getApplicationSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_ApplicationSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConditionalPerformerSymbolType> getConditionalPerformerSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_ConditionalPerformerSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataSymbolType> getDataSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_DataSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EndEventSymbol> getEndEventSymbols() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_EndEventSymbols());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GatewaySymbol> getGatewaySymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_GatewaySymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GroupSymbolType> getGroupSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_GroupSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IntermediateEventSymbol> getIntermediateEventSymbols() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_IntermediateEventSymbols());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelerSymbolType> getModelerSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_ModelerSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OrganizationSymbolType> getOrganizationSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_OrganizationSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProcessSymbolType> getProcessSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_ProcessSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PublicInterfaceSymbol> getProcessInterfaceSymbols() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_ProcessInterfaceSymbols());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RoleSymbolType> getRoleSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_RoleSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StartEventSymbol> getStartEventSymbols() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_StartEventSymbols());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TextSymbolType> getTextSymbol() {
		return getNodes().list(CarnotPackage.eINSTANCE.getISymbolContainer_TextSymbol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getConnections() {
		if (connections == null) {
			connections = new BasicFeatureMap(this, CarnotPackage.ISYMBOL_CONTAINER__CONNECTIONS);
		}
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataMappingConnectionType> getDataMappingConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_DataMappingConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutedByConnectionType> getExecutedByConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_ExecutedByConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericLinkConnectionType> getGenericLinkConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_GenericLinkConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PartOfConnectionType> getPartOfConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_PartOfConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PerformsConnectionType> getPerformsConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_PerformsConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TriggersConnectionType> getTriggersConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_TriggersConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RefersToConnectionType> getRefersToConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_RefersToConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubProcessOfConnectionType> getSubProcessOfConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_SubProcessOfConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransitionConnectionType> getTransitionConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_TransitionConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorksForConnectionType> getWorksForConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_WorksForConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TeamLeadConnectionType> getTeamLeadConnection() {
		return getConnections().list(CarnotPackage.eINSTANCE.getISymbolContainer_TeamLeadConnection());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getNodeContainingFeatures() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getConnectionContainingFeatures() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CarnotPackage.ISYMBOL_CONTAINER__NODES:
				return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
				return ((InternalEList<?>)getActivitySymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
				return ((InternalEList<?>)getAnnotationSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
				return ((InternalEList<?>)getApplicationSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
				return ((InternalEList<?>)getConditionalPerformerSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
				return ((InternalEList<?>)getDataSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
				return ((InternalEList<?>)getEndEventSymbols()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
				return ((InternalEList<?>)getGatewaySymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
				return ((InternalEList<?>)getGroupSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
				return ((InternalEList<?>)getIntermediateEventSymbols()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
				return ((InternalEList<?>)getModelerSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
				return ((InternalEList<?>)getOrganizationSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
				return ((InternalEList<?>)getProcessSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
				return ((InternalEList<?>)getProcessInterfaceSymbols()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
				return ((InternalEList<?>)getRoleSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
				return ((InternalEList<?>)getStartEventSymbols()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
				return ((InternalEList<?>)getTextSymbol()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
				return ((InternalEList<?>)getDataMappingConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
				return ((InternalEList<?>)getExecutedByConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
				return ((InternalEList<?>)getGenericLinkConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
				return ((InternalEList<?>)getPartOfConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
				return ((InternalEList<?>)getPerformsConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
				return ((InternalEList<?>)getTriggersConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
				return ((InternalEList<?>)getRefersToConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
				return ((InternalEList<?>)getSubProcessOfConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
				return ((InternalEList<?>)getTransitionConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
				return ((InternalEList<?>)getWorksForConnection()).basicRemove(otherEnd, msgs);
			case CarnotPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
				return ((InternalEList<?>)getTeamLeadConnection()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CarnotPackage.ISYMBOL_CONTAINER__NODES:
				if (coreType) return getNodes();
				return ((FeatureMap.Internal)getNodes()).getWrapper();
			case CarnotPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
				return getActivitySymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
				return getAnnotationSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
				return getApplicationSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
				return getConditionalPerformerSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
				return getDataSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
				return getEndEventSymbols();
			case CarnotPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
				return getGatewaySymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
				return getGroupSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
				return getIntermediateEventSymbols();
			case CarnotPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
				return getModelerSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
				return getOrganizationSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
				return getProcessSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
				return getProcessInterfaceSymbols();
			case CarnotPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
				return getRoleSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
				return getStartEventSymbols();
			case CarnotPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
				return getTextSymbol();
			case CarnotPackage.ISYMBOL_CONTAINER__CONNECTIONS:
				if (coreType) return getConnections();
				return ((FeatureMap.Internal)getConnections()).getWrapper();
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
				return getDataMappingConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
				return getExecutedByConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
				return getGenericLinkConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
				return getPartOfConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
				return getPerformsConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
				return getTriggersConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
				return getRefersToConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
				return getSubProcessOfConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
				return getTransitionConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
				return getWorksForConnection();
			case CarnotPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
				return getTeamLeadConnection();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CarnotPackage.ISYMBOL_CONTAINER__NODES:
				((FeatureMap.Internal)getNodes()).set(newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
				getActivitySymbol().clear();
				getActivitySymbol().addAll((Collection<? extends ActivitySymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
				getAnnotationSymbol().clear();
				getAnnotationSymbol().addAll((Collection<? extends AnnotationSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
				getApplicationSymbol().clear();
				getApplicationSymbol().addAll((Collection<? extends ApplicationSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
				getConditionalPerformerSymbol().clear();
				getConditionalPerformerSymbol().addAll((Collection<? extends ConditionalPerformerSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
				getDataSymbol().clear();
				getDataSymbol().addAll((Collection<? extends DataSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
				getEndEventSymbols().clear();
				getEndEventSymbols().addAll((Collection<? extends EndEventSymbol>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
				getGatewaySymbol().clear();
				getGatewaySymbol().addAll((Collection<? extends GatewaySymbol>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
				getGroupSymbol().clear();
				getGroupSymbol().addAll((Collection<? extends GroupSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
				getIntermediateEventSymbols().clear();
				getIntermediateEventSymbols().addAll((Collection<? extends IntermediateEventSymbol>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
				getModelerSymbol().clear();
				getModelerSymbol().addAll((Collection<? extends ModelerSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
				getOrganizationSymbol().clear();
				getOrganizationSymbol().addAll((Collection<? extends OrganizationSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
				getProcessSymbol().clear();
				getProcessSymbol().addAll((Collection<? extends ProcessSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
				getProcessInterfaceSymbols().clear();
				getProcessInterfaceSymbols().addAll((Collection<? extends PublicInterfaceSymbol>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
				getRoleSymbol().clear();
				getRoleSymbol().addAll((Collection<? extends RoleSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
				getStartEventSymbols().clear();
				getStartEventSymbols().addAll((Collection<? extends StartEventSymbol>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
				getTextSymbol().clear();
				getTextSymbol().addAll((Collection<? extends TextSymbolType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__CONNECTIONS:
				((FeatureMap.Internal)getConnections()).set(newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
				getDataMappingConnection().clear();
				getDataMappingConnection().addAll((Collection<? extends DataMappingConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
				getExecutedByConnection().clear();
				getExecutedByConnection().addAll((Collection<? extends ExecutedByConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
				getGenericLinkConnection().clear();
				getGenericLinkConnection().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
				getPartOfConnection().clear();
				getPartOfConnection().addAll((Collection<? extends PartOfConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
				getPerformsConnection().clear();
				getPerformsConnection().addAll((Collection<? extends PerformsConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
				getTriggersConnection().clear();
				getTriggersConnection().addAll((Collection<? extends TriggersConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
				getRefersToConnection().clear();
				getRefersToConnection().addAll((Collection<? extends RefersToConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
				getSubProcessOfConnection().clear();
				getSubProcessOfConnection().addAll((Collection<? extends SubProcessOfConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
				getTransitionConnection().clear();
				getTransitionConnection().addAll((Collection<? extends TransitionConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
				getWorksForConnection().clear();
				getWorksForConnection().addAll((Collection<? extends WorksForConnectionType>)newValue);
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
				getTeamLeadConnection().clear();
				getTeamLeadConnection().addAll((Collection<? extends TeamLeadConnectionType>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CarnotPackage.ISYMBOL_CONTAINER__NODES:
				getNodes().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
				getActivitySymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
				getAnnotationSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
				getApplicationSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
				getConditionalPerformerSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
				getDataSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
				getEndEventSymbols().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
				getGatewaySymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
				getGroupSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
				getIntermediateEventSymbols().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
				getModelerSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
				getOrganizationSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
				getProcessSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
				getProcessInterfaceSymbols().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
				getRoleSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
				getStartEventSymbols().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
				getTextSymbol().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__CONNECTIONS:
				getConnections().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
				getDataMappingConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
				getExecutedByConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
				getGenericLinkConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
				getPartOfConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
				getPerformsConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
				getTriggersConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
				getRefersToConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
				getSubProcessOfConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
				getTransitionConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
				getWorksForConnection().clear();
				return;
			case CarnotPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
				getTeamLeadConnection().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CarnotPackage.ISYMBOL_CONTAINER__NODES:
				return nodes != null && !nodes.isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
				return !getActivitySymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
				return !getAnnotationSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
				return !getApplicationSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
				return !getConditionalPerformerSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
				return !getDataSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
				return !getEndEventSymbols().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
				return !getGatewaySymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
				return !getGroupSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
				return !getIntermediateEventSymbols().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
				return !getModelerSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
				return !getOrganizationSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
				return !getProcessSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
				return !getProcessInterfaceSymbols().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
				return !getRoleSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
				return !getStartEventSymbols().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
				return !getTextSymbol().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__CONNECTIONS:
				return connections != null && !connections.isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
				return !getDataMappingConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
				return !getExecutedByConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
				return !getGenericLinkConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
				return !getPartOfConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
				return !getPerformsConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
				return !getTriggersConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
				return !getRefersToConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
				return !getSubProcessOfConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
				return !getTransitionConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
				return !getWorksForConnection().isEmpty();
			case CarnotPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
				return !getTeamLeadConnection().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case CarnotPackage.ISYMBOL_CONTAINER___GET_NODE_CONTAINING_FEATURES:
				return getNodeContainingFeatures();
			case CarnotPackage.ISYMBOL_CONTAINER___GET_CONNECTION_CONTAINING_FEATURES:
				return getConnectionContainingFeatures();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (nodes: ");
		result.append(nodes);
		result.append(", connections: ");
		result.append(connections);
		result.append(')');
		return result.toString();
	}

} //ISymbolContainerImpl
