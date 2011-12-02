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
package org.eclipse.stardust.model.xpdl.carnot.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.*;


/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an
 * adapter <code>createXXX</code> method for each class of the model. <!-- end-user-doc
 * -->
 * 
 * @see org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage
 * @generated
 */
public class CarnotWorkflowModelAdapterFactory extends AdapterFactoryImpl
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public static final String copyright = "Copyright 2000-2009 by SunGard Systeme GmbH"; //$NON-NLS-1$

   /**
    * The cached model package.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   protected static CarnotWorkflowModelPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @generated
    */
   public CarnotWorkflowModelAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = CarnotWorkflowModelPackage.eINSTANCE;
      }
   }

   /**
    * Returns whether this factory is applicable for the type of the object. <!--
    * begin-user-doc --> This implementation returns <code>true</code> if the object is
    * either the model's package or is an instance object of the model. <!-- end-user-doc
    * -->
    * 
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object object)
   {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject)object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

   /**
    * The switch the delegates to the <code>createXXX</code> methods. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @generated
    */
   protected CarnotWorkflowModelSwitch<Adapter> modelSwitch =
      new CarnotWorkflowModelSwitch<Adapter>()
      {
         @Override
         public Adapter caseCoordinates(Coordinates object)
         {
            return createCoordinatesAdapter();
         }
         @Override
         public Adapter caseIIdentifiableElement(IIdentifiableElement object)
         {
            return createIIdentifiableElementAdapter();
         }
         @Override
         public Adapter caseIExtensibleElement(IExtensibleElement object)
         {
            return createIExtensibleElementAdapter();
         }
         @Override
         public Adapter caseIdentifiableReference(IdentifiableReference object)
         {
            return createIdentifiableReferenceAdapter();
         }
         @Override
         public Adapter caseIModelElement(IModelElement object)
         {
            return createIModelElementAdapter();
         }
         @Override
         public Adapter caseIIdentifiableModelElement(IIdentifiableModelElement object)
         {
            return createIIdentifiableModelElementAdapter();
         }
         @Override
         public Adapter caseIEventHandlerOwner(IEventHandlerOwner object)
         {
            return createIEventHandlerOwnerAdapter();
         }
         @Override
         public Adapter caseIAccessPointOwner(IAccessPointOwner object)
         {
            return createIAccessPointOwnerAdapter();
         }
         @Override
         public Adapter caseIMetaType(IMetaType object)
         {
            return createIMetaTypeAdapter();
         }
         @Override
         public Adapter caseITypedElement(ITypedElement object)
         {
            return createITypedElementAdapter();
         }
         @Override
         public Adapter caseISymbolContainer(ISymbolContainer object)
         {
            return createISymbolContainerAdapter();
         }
         @Override
         public Adapter caseIGraphicalObject(IGraphicalObject object)
         {
            return createIGraphicalObjectAdapter();
         }
         @Override
         public Adapter caseINodeSymbol(INodeSymbol object)
         {
            return createINodeSymbolAdapter();
         }
         @Override
         public Adapter caseISwimlaneSymbol(ISwimlaneSymbol object)
         {
            return createISwimlaneSymbolAdapter();
         }
         @Override
         public Adapter caseIModelElementNodeSymbol(IModelElementNodeSymbol object)
         {
            return createIModelElementNodeSymbolAdapter();
         }
         @Override
         public Adapter caseIFlowObjectSymbol(IFlowObjectSymbol object)
         {
            return createIFlowObjectSymbolAdapter();
         }
         @Override
         public Adapter caseIConnectionSymbol(IConnectionSymbol object)
         {
            return createIConnectionSymbolAdapter();
         }
         @Override
         public Adapter caseIModelParticipant(IModelParticipant object)
         {
            return createIModelParticipantAdapter();
         }
         @Override
         public Adapter caseIModelParticipantSymbol(IModelParticipantSymbol object)
         {
            return createIModelParticipantSymbolAdapter();
         }
         @Override
         public Adapter caseAbstractEventAction(AbstractEventAction object)
         {
            return createAbstractEventActionAdapter();
         }
         @Override
         public Adapter caseAbstractEventSymbol(AbstractEventSymbol object)
         {
            return createAbstractEventSymbolAdapter();
         }
         @Override
         public Adapter caseAccessPointType(AccessPointType object)
         {
            return createAccessPointTypeAdapter();
         }
         @Override
         public Adapter caseActivitySymbolType(ActivitySymbolType object)
         {
            return createActivitySymbolTypeAdapter();
         }
         @Override
         public Adapter caseActivityType(ActivityType object)
         {
            return createActivityTypeAdapter();
         }
         @Override
         public Adapter caseAnnotationSymbolType(AnnotationSymbolType object)
         {
            return createAnnotationSymbolTypeAdapter();
         }
         @Override
         public Adapter caseApplicationContextTypeType(ApplicationContextTypeType object)
         {
            return createApplicationContextTypeTypeAdapter();
         }
         @Override
         public Adapter caseApplicationSymbolType(ApplicationSymbolType object)
         {
            return createApplicationSymbolTypeAdapter();
         }
         @Override
         public Adapter caseApplicationType(ApplicationType object)
         {
            return createApplicationTypeAdapter();
         }
         @Override
         public Adapter caseApplicationTypeType(ApplicationTypeType object)
         {
            return createApplicationTypeTypeAdapter();
         }
         @Override
         public Adapter caseAttributeType(AttributeType object)
         {
            return createAttributeTypeAdapter();
         }
         @Override
         public Adapter caseBindActionType(BindActionType object)
         {
            return createBindActionTypeAdapter();
         }
         @Override
         public Adapter caseCode(Code object)
         {
            return createCodeAdapter();
         }
         @Override
         public Adapter caseConditionalPerformerSymbolType(ConditionalPerformerSymbolType object)
         {
            return createConditionalPerformerSymbolTypeAdapter();
         }
         @Override
         public Adapter caseConditionalPerformerType(ConditionalPerformerType object)
         {
            return createConditionalPerformerTypeAdapter();
         }
         @Override
         public Adapter caseContextType(ContextType object)
         {
            return createContextTypeAdapter();
         }
         @Override
         public Adapter caseDataMappingConnectionType(DataMappingConnectionType object)
         {
            return createDataMappingConnectionTypeAdapter();
         }
         @Override
         public Adapter caseDataMappingType(DataMappingType object)
         {
            return createDataMappingTypeAdapter();
         }
         @Override
         public Adapter caseDataPathType(DataPathType object)
         {
            return createDataPathTypeAdapter();
         }
         @Override
         public Adapter caseDataSymbolType(DataSymbolType object)
         {
            return createDataSymbolTypeAdapter();
         }
         @Override
         public Adapter caseDataType(DataType object)
         {
            return createDataTypeAdapter();
         }
         @Override
         public Adapter caseDataTypeType(DataTypeType object)
         {
            return createDataTypeTypeAdapter();
         }
         @Override
         public Adapter caseDescriptionType(DescriptionType object)
         {
            return createDescriptionTypeAdapter();
         }
         @Override
         public Adapter caseDiagramType(DiagramType object)
         {
            return createDiagramTypeAdapter();
         }
         @Override
         public Adapter caseDocumentRoot(DocumentRoot object)
         {
            return createDocumentRootAdapter();
         }
         @Override
         public Adapter caseEndEventSymbol(EndEventSymbol object)
         {
            return createEndEventSymbolAdapter();
         }
         @Override
         public Adapter caseEventActionType(EventActionType object)
         {
            return createEventActionTypeAdapter();
         }
         @Override
         public Adapter caseEventActionTypeType(EventActionTypeType object)
         {
            return createEventActionTypeTypeAdapter();
         }
         @Override
         public Adapter caseEventConditionTypeType(EventConditionTypeType object)
         {
            return createEventConditionTypeTypeAdapter();
         }
         @Override
         public Adapter caseEventHandlerType(EventHandlerType object)
         {
            return createEventHandlerTypeAdapter();
         }
         @Override
         public Adapter caseExecutedByConnectionType(ExecutedByConnectionType object)
         {
            return createExecutedByConnectionTypeAdapter();
         }
         @Override
         public Adapter caseIdRef(IdRef object)
         {
            return createIdRefAdapter();
         }
         @Override
         public Adapter caseGatewaySymbol(GatewaySymbol object)
         {
            return createGatewaySymbolAdapter();
         }
         @Override
         public Adapter caseGenericLinkConnectionType(GenericLinkConnectionType object)
         {
            return createGenericLinkConnectionTypeAdapter();
         }
         @Override
         public Adapter caseGroupSymbolType(GroupSymbolType object)
         {
            return createGroupSymbolTypeAdapter();
         }
         @Override
         public Adapter caseIntermediateEventSymbol(IntermediateEventSymbol object)
         {
            return createIntermediateEventSymbolAdapter();
         }
         @Override
         public Adapter caseLaneSymbol(LaneSymbol object)
         {
            return createLaneSymbolAdapter();
         }
         @Override
         public Adapter caseLinkTypeType(LinkTypeType object)
         {
            return createLinkTypeTypeAdapter();
         }
         @Override
         public Adapter caseModelerSymbolType(ModelerSymbolType object)
         {
            return createModelerSymbolTypeAdapter();
         }
         @Override
         public Adapter caseModelerType(ModelerType object)
         {
            return createModelerTypeAdapter();
         }
         @Override
         public Adapter caseModelType(ModelType object)
         {
            return createModelTypeAdapter();
         }
         @Override
         public Adapter caseOrganizationSymbolType(OrganizationSymbolType object)
         {
            return createOrganizationSymbolTypeAdapter();
         }
         @Override
         public Adapter caseOrganizationType(OrganizationType object)
         {
            return createOrganizationTypeAdapter();
         }
         @Override
         public Adapter caseParameterMappingType(ParameterMappingType object)
         {
            return createParameterMappingTypeAdapter();
         }
         @Override
         public Adapter caseParticipantType(ParticipantType object)
         {
            return createParticipantTypeAdapter();
         }
         @Override
         public Adapter casePartOfConnectionType(PartOfConnectionType object)
         {
            return createPartOfConnectionTypeAdapter();
         }
         @Override
         public Adapter casePerformsConnectionType(PerformsConnectionType object)
         {
            return createPerformsConnectionTypeAdapter();
         }
         @Override
         public Adapter casePoolSymbol(PoolSymbol object)
         {
            return createPoolSymbolAdapter();
         }
         @Override
         public Adapter caseProcessDefinitionType(ProcessDefinitionType object)
         {
            return createProcessDefinitionTypeAdapter();
         }
         @Override
         public Adapter caseProcessSymbolType(ProcessSymbolType object)
         {
            return createProcessSymbolTypeAdapter();
         }
         @Override
         public Adapter casePublicInterfaceSymbol(PublicInterfaceSymbol object)
         {
            return createPublicInterfaceSymbolAdapter();
         }
         @Override
         public Adapter caseQualityControlType(QualityControlType object)
         {
            return createQualityControlTypeAdapter();
         }
         @Override
         public Adapter caseRefersToConnectionType(RefersToConnectionType object)
         {
            return createRefersToConnectionTypeAdapter();
         }
         @Override
         public Adapter caseRoleSymbolType(RoleSymbolType object)
         {
            return createRoleSymbolTypeAdapter();
         }
         @Override
         public Adapter caseRoleType(RoleType object)
         {
            return createRoleTypeAdapter();
         }
         @Override
         public Adapter caseStartEventSymbol(StartEventSymbol object)
         {
            return createStartEventSymbolAdapter();
         }
         @Override
         public Adapter caseSubProcessOfConnectionType(SubProcessOfConnectionType object)
         {
            return createSubProcessOfConnectionTypeAdapter();
         }
         @Override
         public Adapter caseTeamLeadConnectionType(TeamLeadConnectionType object)
         {
            return createTeamLeadConnectionTypeAdapter();
         }
         @Override
         public Adapter caseTextSymbolType(TextSymbolType object)
         {
            return createTextSymbolTypeAdapter();
         }
         @Override
         public Adapter caseTextType(TextType object)
         {
            return createTextTypeAdapter();
         }
         @Override
         public Adapter caseTransitionConnectionType(TransitionConnectionType object)
         {
            return createTransitionConnectionTypeAdapter();
         }
         @Override
         public Adapter caseTransitionType(TransitionType object)
         {
            return createTransitionTypeAdapter();
         }
         @Override
         public Adapter caseTriggersConnectionType(TriggersConnectionType object)
         {
            return createTriggersConnectionTypeAdapter();
         }
         @Override
         public Adapter caseTriggerType(TriggerType object)
         {
            return createTriggerTypeAdapter();
         }
         @Override
         public Adapter caseTriggerTypeType(TriggerTypeType object)
         {
            return createTriggerTypeTypeAdapter();
         }
         @Override
         public Adapter caseUnbindActionType(UnbindActionType object)
         {
            return createUnbindActionTypeAdapter();
         }
         @Override
         public Adapter caseViewableType(ViewableType object)
         {
            return createViewableTypeAdapter();
         }
         @Override
         public Adapter caseViewType(ViewType object)
         {
            return createViewTypeAdapter();
         }
         @Override
         public Adapter caseWorksForConnectionType(WorksForConnectionType object)
         {
            return createWorksForConnectionTypeAdapter();
         }
         @Override
         public Adapter caseXmlTextNode(XmlTextNode object)
         {
            return createXmlTextNodeAdapter();
         }
         @Override
         public Adapter defaultCase(EObject object)
         {
            return createEObjectAdapter();
         }
      };

   /**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc --> <!--
    * end-user-doc -->
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
   @Override
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject)target);
   }


   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.Coordinates <em>Coordinates</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.Coordinates
    * @generated
    */
   public Adapter createCoordinatesAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.AccessPointType <em>Access Point Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.AccessPointType
    * @generated
    */
   public Adapter createAccessPointTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType <em>Activity Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType
    * @generated
    */
   public Adapter createActivitySymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ActivityType <em>Activity Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ActivityType
    * @generated
    */
   public Adapter createActivityTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType <em>Annotation Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType
    * @generated
    */
   public Adapter createAnnotationSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType <em>Application Context Type Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationContextTypeType
    * @generated
    */
   public Adapter createApplicationContextTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType <em>Application Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType
    * @generated
    */
   public Adapter createApplicationSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationType <em>Application Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationType
    * @generated
    */
   public Adapter createApplicationTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType <em>Application Type Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ApplicationTypeType
    * @generated
    */
   public Adapter createApplicationTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.AttributeType <em>Attribute Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.AttributeType
    * @generated
    */
   public Adapter createAttributeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.BindActionType <em>Bind Action Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.BindActionType
    * @generated
    */
   public Adapter createBindActionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.Code <em>Code</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.Code
    * @generated
    */
   public Adapter createCodeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType <em>Conditional Performer Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerSymbolType
    * @generated
    */
   public Adapter createConditionalPerformerSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType <em>Conditional Performer Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ConditionalPerformerType
    * @generated
    */
   public Adapter createConditionalPerformerTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ContextType <em>Context Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ContextType
    * @generated
    */
   public Adapter createContextTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType <em>Data Mapping Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DataMappingConnectionType
    * @generated
    */
   public Adapter createDataMappingConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DataMappingType <em>Data Mapping Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DataMappingType
    * @generated
    */
   public Adapter createDataMappingTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DataPathType <em>Data Path Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DataPathType
    * @generated
    */
   public Adapter createDataPathTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DataSymbolType <em>Data Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DataSymbolType
    * @generated
    */
   public Adapter createDataSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DataType <em>Data Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DataType
    * @generated
    */
   public Adapter createDataTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DataTypeType <em>Data Type Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DataTypeType
    * @generated
    */
   public Adapter createDataTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DescriptionType <em>Description Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DescriptionType
    * @generated
    */
   public Adapter createDescriptionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DiagramType <em>Diagram Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DiagramType
    * @generated
    */
   public Adapter createDiagramTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.DocumentRoot <em>Document Root</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.DocumentRoot
    * @generated
    */
   public Adapter createDocumentRootAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol <em>End Event Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol
    * @generated
    */
   public Adapter createEndEventSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionType <em>Event Action Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.EventActionType
    * @generated
    */
   public Adapter createEventActionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType <em>Event Action Type Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.EventActionTypeType
    * @generated
    */
   public Adapter createEventActionTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType <em>Event Condition Type Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.EventConditionTypeType
    * @generated
    */
   public Adapter createEventConditionTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.EventHandlerType <em>Event Handler Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.EventHandlerType
    * @generated
    */
   public Adapter createEventHandlerTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType <em>Executed By Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType
    * @generated
    */
   public Adapter createExecutedByConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IdRef <em>Id Ref</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IdRef
    * @generated
    */
   public Adapter createIdRefAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol <em>Gateway Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol
    * @generated
    */
   public Adapter createGatewaySymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType <em>Generic Link Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType
    * @generated
    */
   public Adapter createGenericLinkConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType <em>Group Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.GroupSymbolType
    * @generated
    */
   public Adapter createGroupSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IntermediateEventSymbol <em>Intermediate Event Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IntermediateEventSymbol
    * @generated
    */
   public Adapter createIntermediateEventSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.LaneSymbol <em>Lane Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.LaneSymbol
    * @generated
    */
   public Adapter createLaneSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement <em>IIdentifiable Element</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IIdentifiableElement
    * @generated
    */
   public Adapter createIIdentifiableElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement <em>IExtensible Element</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IExtensibleElement
    * @generated
    */
   public Adapter createIExtensibleElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference <em>Identifiable Reference</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IdentifiableReference
    * @generated
    */
   public Adapter createIdentifiableReferenceAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement <em>IIdentifiable Model Element</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement
    * @generated
    */
   public Adapter createIIdentifiableModelElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner <em>IEvent Handler Owner</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IEventHandlerOwner
    * @generated
    */
   public Adapter createIEventHandlerOwnerAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner <em>IAccess Point Owner</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IAccessPointOwner
    * @generated
    */
   public Adapter createIAccessPointOwnerAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IMetaType <em>IMeta Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IMetaType
    * @generated
    */
   public Adapter createIMetaTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ITypedElement <em>ITyped Element</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ITypedElement
    * @generated
    */
   public Adapter createITypedElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer <em>ISymbol Container</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer
    * @generated
    */
   public Adapter createISymbolContainerAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject <em>IGraphical Object</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject
    * @generated
    */
   public Adapter createIGraphicalObjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.INodeSymbol <em>INode Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.INodeSymbol
    * @generated
    */
   public Adapter createINodeSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol <em>ISwimlane Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol
    * @generated
    */
   public Adapter createISwimlaneSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol <em>IModel Element Node Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol
    * @generated
    */
   public Adapter createIModelElementNodeSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol <em>IFlow Object Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IFlowObjectSymbol
    * @generated
    */
   public Adapter createIFlowObjectSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol <em>IConnection Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol
    * @generated
    */
   public Adapter createIConnectionSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IModelElement <em>IModel Element</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelElement
    * @generated
    */
   public Adapter createIModelElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.LinkTypeType <em>Link Type Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.LinkTypeType
    * @generated
    */
   public Adapter createLinkTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType <em>Modeler Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ModelerSymbolType
    * @generated
    */
   public Adapter createModelerSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ModelerType <em>Modeler Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ModelerType
    * @generated
    */
   public Adapter createModelerTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ModelType <em>Model Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ModelType
    * @generated
    */
   public Adapter createModelTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType <em>Organization Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType
    * @generated
    */
   public Adapter createOrganizationSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.OrganizationType <em>Organization Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.OrganizationType
    * @generated
    */
   public Adapter createOrganizationTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType <em>Parameter Mapping Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ParameterMappingType
    * @generated
    */
   public Adapter createParameterMappingTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ParticipantType <em>Participant Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ParticipantType
    * @generated
    */
   public Adapter createParticipantTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType <em>Part Of Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.PartOfConnectionType
    * @generated
    */
   public Adapter createPartOfConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType <em>Performs Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType
    * @generated
    */
   public Adapter createPerformsConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.PoolSymbol <em>Pool Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.PoolSymbol
    * @generated
    */
   public Adapter createPoolSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType <em>Process Definition Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType
    * @generated
    */
   public Adapter createProcessDefinitionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType <em>Process Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ProcessSymbolType
    * @generated
    */
   public Adapter createProcessSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.PublicInterfaceSymbol <em>Public Interface Symbol</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.PublicInterfaceSymbol
    * @generated
    */
   public Adapter createPublicInterfaceSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.QualityControlType <em>Quality Control Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.QualityControlType
    * @generated
    */
   public Adapter createQualityControlTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType <em>Refers To Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.RefersToConnectionType
    * @generated
    */
   public Adapter createRefersToConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType <em>Role Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType
    * @generated
    */
   public Adapter createRoleSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.RoleType <em>Role Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.RoleType
    * @generated
    */
   public Adapter createRoleTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol <em>Start Event Symbol</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol
    * @generated
    */
   public Adapter createStartEventSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType <em>Sub Process Of Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.SubProcessOfConnectionType
    * @generated
    */
   public Adapter createSubProcessOfConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TextSymbolType <em>Text Symbol Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TextSymbolType
    * @generated
    */
   public Adapter createTextSymbolTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TextType <em>Text Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TextType
    * @generated
    */
   public Adapter createTextTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType <em>Transition Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType
    * @generated
    */
   public Adapter createTransitionConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TransitionType <em>Transition Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TransitionType
    * @generated
    */
   public Adapter createTransitionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType <em>Triggers Connection Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TriggersConnectionType
    * @generated
    */
   public Adapter createTriggersConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TriggerType <em>Trigger Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TriggerType
    * @generated
    */
   public Adapter createTriggerTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType <em>Trigger Type Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TriggerTypeType
    * @generated
    */
   public Adapter createTriggerTypeTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.UnbindActionType <em>Unbind Action Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.UnbindActionType
    * @generated
    */
   public Adapter createUnbindActionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ViewableType <em>Viewable Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ViewableType
    * @generated
    */
   public Adapter createViewableTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.ViewType <em>View Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.ViewType
    * @generated
    */
   public Adapter createViewTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType <em>Works For Connection Type</em>}'.
    * <!-- begin-user-doc --> This default implementation returns null so that we can
    * easily ignore cases; it's useful to ignore a case when inheritance will catch all
    * the cases anyway. <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.WorksForConnectionType
    * @generated
    */
   public Adapter createWorksForConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.XmlTextNode <em>Xml Text Node</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.XmlTextNode
    * @generated
    */
   public Adapter createXmlTextNodeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType <em>Team Lead Connection Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.TeamLeadConnectionType
    * @generated
    */
   public Adapter createTeamLeadConnectionTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction <em>Abstract Event Action</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.AbstractEventAction
    * @generated
    */
   public Adapter createAbstractEventActionAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipant <em>IModel Participant</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelParticipant
    * @generated
    */
   public Adapter createIModelParticipantAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol <em>IModel Participant Symbol</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol
    * @generated
    */
   public Adapter createIModelParticipantSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol <em>Abstract Event Symbol</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see org.eclipse.stardust.model.xpdl.carnot.AbstractEventSymbol
    * @generated
    */
   public Adapter createAbstractEventSymbolAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @generated
    */
   public Adapter createEObjectAdapter()
   {
      return null;
   }

} //CarnotWorkflowModelAdapterFactory
