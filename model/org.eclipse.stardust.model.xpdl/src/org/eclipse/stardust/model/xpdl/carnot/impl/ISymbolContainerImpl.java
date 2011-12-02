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
package org.eclipse.stardust.model.xpdl.carnot.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.model.xpdl.carnot.IntermediateEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.PublicInterfaceSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ISymbol Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getActivitySymbol <em>Activity Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getAnnotationSymbol <em>Annotation Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getApplicationSymbol <em>Application Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getConditionalPerformerSymbol <em>Conditional Performer Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getDataSymbol <em>Data Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getEndEventSymbols <em>End Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getGatewaySymbol <em>Gateway Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getGroupSymbol <em>Group Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getIntermediateEventSymbols <em>Intermediate Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getModelerSymbol <em>Modeler Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getOrganizationSymbol <em>Organization Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getProcessSymbol <em>Process Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getProcessInterfaceSymbols <em>Process Interface Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getRoleSymbol <em>Role Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getStartEventSymbols <em>Start Event Symbols</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getTextSymbol <em>Text Symbol</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getDataMappingConnection <em>Data Mapping Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getExecutedByConnection <em>Executed By Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getGenericLinkConnection <em>Generic Link Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getPartOfConnection <em>Part Of Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getPerformsConnection <em>Performs Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getTriggersConnection <em>Triggers Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getRefersToConnection <em>Refers To Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getSubProcessOfConnection <em>Sub Process Of Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getTransitionConnection <em>Transition Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getWorksForConnection <em>Works For Connection</em>}</li>
 *   <li>{@link org.eclipse.stardust.model.xpdl.carnot.impl.ISymbolContainerImpl#getTeamLeadConnection <em>Team Lead Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ISymbolContainerImpl extends EObjectImpl implements ISymbolContainer
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

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
    * The cached value of the '{@link #getNodeContainingFeatures()}' operation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getNodeContainingFeatures()
    * @generated NOT
    * @ordered
    */
   private List nodeContainingFeatures = null;

   /**
    * The cached value of the '{@link #getConnectionContainingFeatures()}' operation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getConnectionContainingFeatures()
    * @generated NOT
    * @ordered
    */
   private List connectionContainingFeatures = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ISymbolContainerImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getNodes()
   {
      if (nodes == null)
      {
         nodes = new BasicFeatureMap(this, CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__NODES);
      }
      return nodes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ActivitySymbolType> getActivitySymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<AnnotationSymbolType> getAnnotationSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ApplicationSymbolType> getApplicationSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__APPLICATION_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ConditionalPerformerSymbolType> getConditionalPerformerSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataSymbolType> getDataSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__DATA_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EndEventSymbol> getEndEventSymbols()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<GatewaySymbol> getGatewaySymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__GATEWAY_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<GroupSymbolType> getGroupSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__GROUP_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<IntermediateEventSymbol> getIntermediateEventSymbols()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ModelerSymbolType> getModelerSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__MODELER_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<OrganizationSymbolType> getOrganizationSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ProcessSymbolType> getProcessSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PROCESS_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PublicInterfaceSymbol> getProcessInterfaceSymbols()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<RoleSymbolType> getRoleSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__ROLE_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<StartEventSymbol> getStartEventSymbols()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TextSymbolType> getTextSymbol()
   {
      return getNodes().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TEXT_SYMBOL);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public FeatureMap getConnections()
   {
      if (connections == null)
      {
         connections = new BasicFeatureMap(this, CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONNECTIONS);
      }
      return connections;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<DataMappingConnectionType> getDataMappingConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ExecutedByConnectionType> getExecutedByConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<GenericLinkConnectionType> getGenericLinkConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PartOfConnectionType> getPartOfConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PART_OF_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<PerformsConnectionType> getPerformsConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__PERFORMS_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TriggersConnectionType> getTriggersConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<RefersToConnectionType> getRefersToConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<SubProcessOfConnectionType> getSubProcessOfConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TransitionConnectionType> getTransitionConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TRANSITION_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<WorksForConnectionType> getWorksForConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<TeamLeadConnectionType> getTeamLeadConnection()
   {
      return getConnections().list(CarnotWorkflowModelPackage.Literals.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getNodeContainingFeatures()
   {
      if (null == nodeContainingFeatures)
      {
         // no need to synchronize on init as effectively a constant is initialized
         this.nodeContainingFeatures = Arrays.asList(new EReference[] {
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_AnnotationSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ApplicationSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ConditionalPerformerSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_DataSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_EndEventSymbols(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GatewaySymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GroupSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_IntermediateEventSymbols(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ModelerSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_OrganizationSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ProcessSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ProcessInterfaceSymbols(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_RoleSymbol(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_StartEventSymbols(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_TextSymbol()
         });
      }
      return nodeContainingFeatures;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated NOT
    */
   public List getConnectionContainingFeatures()
   {
      if (null == connectionContainingFeatures)
      {
         // no need to synchronize on init as effectively a constant is initialized
         this.connectionContainingFeatures = Arrays.asList(new EReference[] {
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_DataMappingConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ExecutedByConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_GenericLinkConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_PartOfConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_PerformsConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_RefersToConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_SubProcessOfConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_TransitionConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_WorksForConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_TeamLeadConnection(),
               CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_TriggersConnection()
         });
      }
      return connectionContainingFeatures;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__NODES:
            return ((InternalEList<?>)getNodes()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
            return ((InternalEList<?>)getActivitySymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
            return ((InternalEList<?>)getAnnotationSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
            return ((InternalEList<?>)getApplicationSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
            return ((InternalEList<?>)getConditionalPerformerSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
            return ((InternalEList<?>)getDataSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
            return ((InternalEList<?>)getEndEventSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
            return ((InternalEList<?>)getGatewaySymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
            return ((InternalEList<?>)getGroupSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
            return ((InternalEList<?>)getIntermediateEventSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
            return ((InternalEList<?>)getModelerSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
            return ((InternalEList<?>)getOrganizationSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
            return ((InternalEList<?>)getProcessSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
            return ((InternalEList<?>)getProcessInterfaceSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
            return ((InternalEList<?>)getRoleSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
            return ((InternalEList<?>)getStartEventSymbols()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
            return ((InternalEList<?>)getTextSymbol()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONNECTIONS:
            return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
            return ((InternalEList<?>)getDataMappingConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
            return ((InternalEList<?>)getExecutedByConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
            return ((InternalEList<?>)getGenericLinkConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
            return ((InternalEList<?>)getPartOfConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
            return ((InternalEList<?>)getPerformsConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
            return ((InternalEList<?>)getTriggersConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
            return ((InternalEList<?>)getRefersToConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
            return ((InternalEList<?>)getSubProcessOfConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
            return ((InternalEList<?>)getTransitionConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
            return ((InternalEList<?>)getWorksForConnection()).basicRemove(otherEnd, msgs);
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
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
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__NODES:
            if (coreType) return getNodes();
            return ((FeatureMap.Internal)getNodes()).getWrapper();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
            return getActivitySymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
            return getAnnotationSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
            return getApplicationSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
            return getConditionalPerformerSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
            return getDataSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
            return getEndEventSymbols();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
            return getGatewaySymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
            return getGroupSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
            return getIntermediateEventSymbols();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
            return getModelerSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
            return getOrganizationSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
            return getProcessSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
            return getProcessInterfaceSymbols();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
            return getRoleSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
            return getStartEventSymbols();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
            return getTextSymbol();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONNECTIONS:
            if (coreType) return getConnections();
            return ((FeatureMap.Internal)getConnections()).getWrapper();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
            return getDataMappingConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
            return getExecutedByConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
            return getGenericLinkConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
            return getPartOfConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
            return getPerformsConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
            return getTriggersConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
            return getRefersToConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
            return getSubProcessOfConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
            return getTransitionConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
            return getWorksForConnection();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
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
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__NODES:
            ((FeatureMap.Internal)getNodes()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
            getActivitySymbol().clear();
            getActivitySymbol().addAll((Collection<? extends ActivitySymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
            getAnnotationSymbol().clear();
            getAnnotationSymbol().addAll((Collection<? extends AnnotationSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
            getApplicationSymbol().clear();
            getApplicationSymbol().addAll((Collection<? extends ApplicationSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
            getConditionalPerformerSymbol().clear();
            getConditionalPerformerSymbol().addAll((Collection<? extends ConditionalPerformerSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
            getDataSymbol().clear();
            getDataSymbol().addAll((Collection<? extends DataSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
            getEndEventSymbols().clear();
            getEndEventSymbols().addAll((Collection<? extends EndEventSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
            getGatewaySymbol().clear();
            getGatewaySymbol().addAll((Collection<? extends GatewaySymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
            getGroupSymbol().clear();
            getGroupSymbol().addAll((Collection<? extends GroupSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
            getIntermediateEventSymbols().clear();
            getIntermediateEventSymbols().addAll((Collection<? extends IntermediateEventSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
            getModelerSymbol().clear();
            getModelerSymbol().addAll((Collection<? extends ModelerSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
            getOrganizationSymbol().clear();
            getOrganizationSymbol().addAll((Collection<? extends OrganizationSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
            getProcessSymbol().clear();
            getProcessSymbol().addAll((Collection<? extends ProcessSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
            getProcessInterfaceSymbols().clear();
            getProcessInterfaceSymbols().addAll((Collection<? extends PublicInterfaceSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
            getRoleSymbol().clear();
            getRoleSymbol().addAll((Collection<? extends RoleSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
            getStartEventSymbols().clear();
            getStartEventSymbols().addAll((Collection<? extends StartEventSymbol>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
            getTextSymbol().clear();
            getTextSymbol().addAll((Collection<? extends TextSymbolType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONNECTIONS:
            ((FeatureMap.Internal)getConnections()).set(newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
            getDataMappingConnection().clear();
            getDataMappingConnection().addAll((Collection<? extends DataMappingConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
            getExecutedByConnection().clear();
            getExecutedByConnection().addAll((Collection<? extends ExecutedByConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
            getGenericLinkConnection().clear();
            getGenericLinkConnection().addAll((Collection<? extends GenericLinkConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
            getPartOfConnection().clear();
            getPartOfConnection().addAll((Collection<? extends PartOfConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
            getPerformsConnection().clear();
            getPerformsConnection().addAll((Collection<? extends PerformsConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
            getTriggersConnection().clear();
            getTriggersConnection().addAll((Collection<? extends TriggersConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
            getRefersToConnection().clear();
            getRefersToConnection().addAll((Collection<? extends RefersToConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
            getSubProcessOfConnection().clear();
            getSubProcessOfConnection().addAll((Collection<? extends SubProcessOfConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
            getTransitionConnection().clear();
            getTransitionConnection().addAll((Collection<? extends TransitionConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
            getWorksForConnection().clear();
            getWorksForConnection().addAll((Collection<? extends WorksForConnectionType>)newValue);
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
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
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__NODES:
            getNodes().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
            getActivitySymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
            getAnnotationSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
            getApplicationSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
            getConditionalPerformerSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
            getDataSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
            getEndEventSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
            getGatewaySymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
            getGroupSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
            getIntermediateEventSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
            getModelerSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
            getOrganizationSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
            getProcessSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
            getProcessInterfaceSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
            getRoleSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
            getStartEventSymbols().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
            getTextSymbol().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONNECTIONS:
            getConnections().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
            getDataMappingConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
            getExecutedByConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
            getGenericLinkConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
            getPartOfConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
            getPerformsConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
            getTriggersConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
            getRefersToConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
            getSubProcessOfConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
            getTransitionConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
            getWorksForConnection().clear();
            return;
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
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
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__NODES:
            return nodes != null && !nodes.isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ACTIVITY_SYMBOL:
            return !getActivitySymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ANNOTATION_SYMBOL:
            return !getAnnotationSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__APPLICATION_SYMBOL:
            return !getApplicationSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONDITIONAL_PERFORMER_SYMBOL:
            return !getConditionalPerformerSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_SYMBOL:
            return !getDataSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__END_EVENT_SYMBOLS:
            return !getEndEventSymbols().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GATEWAY_SYMBOL:
            return !getGatewaySymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GROUP_SYMBOL:
            return !getGroupSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__INTERMEDIATE_EVENT_SYMBOLS:
            return !getIntermediateEventSymbols().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__MODELER_SYMBOL:
            return !getModelerSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ORGANIZATION_SYMBOL:
            return !getOrganizationSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_SYMBOL:
            return !getProcessSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PROCESS_INTERFACE_SYMBOLS:
            return !getProcessInterfaceSymbols().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__ROLE_SYMBOL:
            return !getRoleSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__START_EVENT_SYMBOLS:
            return !getStartEventSymbols().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEXT_SYMBOL:
            return !getTextSymbol().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__CONNECTIONS:
            return connections != null && !connections.isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__DATA_MAPPING_CONNECTION:
            return !getDataMappingConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__EXECUTED_BY_CONNECTION:
            return !getExecutedByConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__GENERIC_LINK_CONNECTION:
            return !getGenericLinkConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PART_OF_CONNECTION:
            return !getPartOfConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__PERFORMS_CONNECTION:
            return !getPerformsConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRIGGERS_CONNECTION:
            return !getTriggersConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__REFERS_TO_CONNECTION:
            return !getRefersToConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__SUB_PROCESS_OF_CONNECTION:
            return !getSubProcessOfConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TRANSITION_CONNECTION:
            return !getTransitionConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__WORKS_FOR_CONNECTION:
            return !getWorksForConnection().isEmpty();
         case CarnotWorkflowModelPackage.ISYMBOL_CONTAINER__TEAM_LEAD_CONNECTION:
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
   public String toString()
   {
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
