/*******************************************************************************
 * Copyright (c) 2011 SunGard CSA LLC and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SunGard CSA LLC - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.stardust.model.xpdl.carnot;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method
 * for each non-abstract class of the model. <!-- end-user-doc -->
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage
 * @generated
 */
public interface CarnotWorkflowModelFactory extends EFactory
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   CarnotWorkflowModelFactory eINSTANCE = org.eclipse.stardust.model.xpdl.carnot.impl.CarnotWorkflowModelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Coordinates</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Coordinates</em>'.
    * @generated
    */
   Coordinates createCoordinates();

   /**
    * Returns a new object of class '<em>IExtensible Element</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>IExtensible Element</em>'.
    * @generated
    */
   IExtensibleElement createIExtensibleElement();

   /**
    * Returns a new object of class '<em>Identifiable Reference</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Identifiable Reference</em>'.
    * @generated
    */
   IdentifiableReference createIdentifiableReference();

   /**
    * Returns a new object of class '<em>Access Point Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Access Point Type</em>'.
    * @generated
    */
   AccessPointType createAccessPointType();

   /**
    * Returns a new object of class '<em>Activity Symbol Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Activity Symbol Type</em>'.
    * @generated
    */
   ActivitySymbolType createActivitySymbolType();

   /**
    * Returns a new object of class '<em>Activity Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Activity Type</em>'.
    * @generated
    */
   ActivityType createActivityType();

   /**
    * Returns a new object of class '<em>Annotation Symbol Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Annotation Symbol Type</em>'.
    * @generated
    */
   AnnotationSymbolType createAnnotationSymbolType();

   /**
    * Returns a new object of class '<em>Application Context Type Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Application Context Type Type</em>'.
    * @generated
    */
   ApplicationContextTypeType createApplicationContextTypeType();

   /**
    * Returns a new object of class '<em>Application Symbol Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Application Symbol Type</em>'.
    * @generated
    */
   ApplicationSymbolType createApplicationSymbolType();

   /**
    * Returns a new object of class '<em>Application Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Application Type</em>'.
    * @generated
    */
   ApplicationType createApplicationType();

   /**
    * Returns a new object of class '<em>Application Type Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Application Type Type</em>'.
    * @generated
    */
   ApplicationTypeType createApplicationTypeType();

   /**
    * Returns a new object of class '<em>Attribute Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Attribute Type</em>'.
    * @generated
    */
   AttributeType createAttributeType();

   /**
    * Returns a new object of class '<em>Bind Action Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Bind Action Type</em>'.
    * @generated
    */
   BindActionType createBindActionType();

   /**
    * Returns a new object of class '<em>Code</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Code</em>'.
    * @generated
    */
   Code createCode();

   /**
    * Returns a new object of class '<em>Conditional Performer Symbol Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Conditional Performer Symbol Type</em>'.
    * @generated
    */
   ConditionalPerformerSymbolType createConditionalPerformerSymbolType();

   /**
    * Returns a new object of class '<em>Conditional Performer Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Conditional Performer Type</em>'.
    * @generated
    */
   ConditionalPerformerType createConditionalPerformerType();

   /**
    * Returns a new object of class '<em>Context Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Context Type</em>'.
    * @generated
    */
   ContextType createContextType();

   /**
    * Returns a new object of class '<em>Data Mapping Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Data Mapping Connection Type</em>'.
    * @generated
    */
   DataMappingConnectionType createDataMappingConnectionType();

   /**
    * Returns a new object of class '<em>Data Mapping Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Data Mapping Type</em>'.
    * @generated
    */
   DataMappingType createDataMappingType();

   /**
    * Returns a new object of class '<em>Data Path Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Data Path Type</em>'.
    * @generated
    */
   DataPathType createDataPathType();

   /**
    * Returns a new object of class '<em>Data Symbol Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Data Symbol Type</em>'.
    * @generated
    */
   DataSymbolType createDataSymbolType();

   /**
    * Returns a new object of class '<em>Data Type</em>'.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @return a new object of class '<em>Data Type</em>'.
    * @generated
    */
   DataType createDataType();

   /**
    * Returns a new object of class '<em>Data Type Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Data Type Type</em>'.
    * @generated
    */
   DataTypeType createDataTypeType();

   /**
    * Returns a new object of class '<em>Description Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Description Type</em>'.
    * @generated
    */
   DescriptionType createDescriptionType();

   /**
    * Returns a new object of class '<em>Diagram Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Diagram Type</em>'.
    * @generated
    */
   DiagramType createDiagramType();

   /**
    * Returns a new object of class '<em>Document Root</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Document Root</em>'.
    * @generated
    */
   DocumentRoot createDocumentRoot();

   /**
    * Returns a new object of class '<em>End Event Symbol</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>End Event Symbol</em>'.
    * @generated
    */
   EndEventSymbol createEndEventSymbol();

   /**
    * Returns a new object of class '<em>Event Action Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Event Action Type</em>'.
    * @generated
    */
   EventActionType createEventActionType();

   /**
    * Returns a new object of class '<em>Event Action Type Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Event Action Type Type</em>'.
    * @generated
    */
   EventActionTypeType createEventActionTypeType();

   /**
    * Returns a new object of class '<em>Event Condition Type Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Event Condition Type Type</em>'.
    * @generated
    */
   EventConditionTypeType createEventConditionTypeType();

   /**
    * Returns a new object of class '<em>Event Handler Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Event Handler Type</em>'.
    * @generated
    */
   EventHandlerType createEventHandlerType();

   /**
    * Returns a new object of class '<em>Executed By Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Executed By Connection Type</em>'.
    * @generated
    */
   ExecutedByConnectionType createExecutedByConnectionType();

   /**
    * Returns a new object of class '<em>Id Ref</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Id Ref</em>'.
    * @generated
    */
   IdRef createIdRef();

   /**
    * Returns a new object of class '<em>Gateway Symbol</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Gateway Symbol</em>'.
    * @generated
    */
   GatewaySymbol createGatewaySymbol();

   /**
    * Returns a new object of class '<em>Generic Link Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Generic Link Connection Type</em>'.
    * @generated
    */
   GenericLinkConnectionType createGenericLinkConnectionType();

   /**
    * Returns a new object of class '<em>Group Symbol Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Group Symbol Type</em>'.
    * @generated
    */
   GroupSymbolType createGroupSymbolType();

   /**
    * Returns a new object of class '<em>Intermediate Event Symbol</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Intermediate Event Symbol</em>'.
    * @generated
    */
   IntermediateEventSymbol createIntermediateEventSymbol();

   /**
    * Returns a new object of class '<em>Lane Symbol</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Lane Symbol</em>'.
    * @generated
    */
   LaneSymbol createLaneSymbol();

   /**
    * Returns a new object of class '<em>Link Type Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Link Type Type</em>'.
    * @generated
    */
   LinkTypeType createLinkTypeType();

   /**
    * Returns a new object of class '<em>Modeler Symbol Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Modeler Symbol Type</em>'.
    * @generated
    */
   ModelerSymbolType createModelerSymbolType();

   /**
    * Returns a new object of class '<em>Modeler Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Modeler Type</em>'.
    * @generated
    */
   ModelerType createModelerType();

   /**
    * Returns a new object of class '<em>Model Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Model Type</em>'.
    * @generated
    */
   ModelType createModelType();

   /**
    * Returns a new object of class '<em>Organization Symbol Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Organization Symbol Type</em>'.
    * @generated
    */
   OrganizationSymbolType createOrganizationSymbolType();

   /**
    * Returns a new object of class '<em>Organization Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Organization Type</em>'.
    * @generated
    */
   OrganizationType createOrganizationType();

   /**
    * Returns a new object of class '<em>Parameter Mapping Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Parameter Mapping Type</em>'.
    * @generated
    */
   ParameterMappingType createParameterMappingType();

   /**
    * Returns a new object of class '<em>Participant Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Participant Type</em>'.
    * @generated
    */
   ParticipantType createParticipantType();

   /**
    * Returns a new object of class '<em>Part Of Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Part Of Connection Type</em>'.
    * @generated
    */
   PartOfConnectionType createPartOfConnectionType();

   /**
    * Returns a new object of class '<em>Performs Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Performs Connection Type</em>'.
    * @generated
    */
   PerformsConnectionType createPerformsConnectionType();

   /**
    * Returns a new object of class '<em>Pool Symbol</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Pool Symbol</em>'.
    * @generated
    */
   PoolSymbol createPoolSymbol();

   /**
    * Returns a new object of class '<em>Process Definition Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Process Definition Type</em>'.
    * @generated
    */
   ProcessDefinitionType createProcessDefinitionType();

   /**
    * Returns a new object of class '<em>Process Symbol Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Process Symbol Type</em>'.
    * @generated
    */
   ProcessSymbolType createProcessSymbolType();

   /**
    * Returns a new object of class '<em>Public Interface Symbol</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Public Interface Symbol</em>'.
    * @generated
    */
   PublicInterfaceSymbol createPublicInterfaceSymbol();

   /**
    * Returns a new object of class '<em>Quality Control Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Quality Control Type</em>'.
    * @generated
    */
   QualityControlType createQualityControlType();

   /**
    * Returns a new object of class '<em>Refers To Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Refers To Connection Type</em>'.
    * @generated
    */
   RefersToConnectionType createRefersToConnectionType();

   /**
    * Returns a new object of class '<em>Role Symbol Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Role Symbol Type</em>'.
    * @generated
    */
   RoleSymbolType createRoleSymbolType();

   /**
    * Returns a new object of class '<em>Role Type</em>'.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @return a new object of class '<em>Role Type</em>'.
    * @generated
    */
   RoleType createRoleType();

   /**
    * Returns a new object of class '<em>Start Event Symbol</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Start Event Symbol</em>'.
    * @generated
    */
   StartEventSymbol createStartEventSymbol();

   /**
    * Returns a new object of class '<em>Sub Process Of Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Sub Process Of Connection Type</em>'.
    * @generated
    */
   SubProcessOfConnectionType createSubProcessOfConnectionType();

   /**
    * Returns a new object of class '<em>Text Symbol Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Text Symbol Type</em>'.
    * @generated
    */
   TextSymbolType createTextSymbolType();

   /**
    * Returns a new object of class '<em>Text Type</em>'.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @return a new object of class '<em>Text Type</em>'.
    * @generated
    */
   TextType createTextType();

   /**
    * Returns a new object of class '<em>Transition Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Transition Connection Type</em>'.
    * @generated
    */
   TransitionConnectionType createTransitionConnectionType();

   /**
    * Returns a new object of class '<em>Transition Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Transition Type</em>'.
    * @generated
    */
   TransitionType createTransitionType();

   /**
    * Returns a new object of class '<em>Triggers Connection Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Triggers Connection Type</em>'.
    * @generated
    */
   TriggersConnectionType createTriggersConnectionType();

   /**
    * Returns a new object of class '<em>Trigger Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Trigger Type</em>'.
    * @generated
    */
   TriggerType createTriggerType();

   /**
    * Returns a new object of class '<em>Trigger Type Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Trigger Type Type</em>'.
    * @generated
    */
   TriggerTypeType createTriggerTypeType();

   /**
    * Returns a new object of class '<em>Unbind Action Type</em>'.
    * <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * @return a new object of class '<em>Unbind Action Type</em>'.
    * @generated
    */
   UnbindActionType createUnbindActionType();

   /**
    * Returns a new object of class '<em>Viewable Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Viewable Type</em>'.
    * @generated
    */
   ViewableType createViewableType();

   /**
    * Returns a new object of class '<em>View Type</em>'.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @return a new object of class '<em>View Type</em>'.
    * @generated
    */
   ViewType createViewType();

   /**
    * Returns a new object of class '<em>Works For Connection Type</em>'. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Works For Connection Type</em>'.
    * @generated
    */
   WorksForConnectionType createWorksForConnectionType();

   /**
    * Returns a new object of class '<em>Xml Text Node</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Xml Text Node</em>'.
    * @generated
    */
   XmlTextNode createXmlTextNode();

   /**
    * Returns a new object of class '<em>Team Lead Connection Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Team Lead Connection Type</em>'.
    * @generated
    */
   TeamLeadConnectionType createTeamLeadConnectionType();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   CarnotWorkflowModelPackage getCarnotWorkflowModelPackage();

} //CarnotWorkflowModelFactory
