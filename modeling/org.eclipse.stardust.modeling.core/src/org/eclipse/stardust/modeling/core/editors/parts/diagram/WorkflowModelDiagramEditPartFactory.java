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
package org.eclipse.stardust.modeling.core.editors.parts.diagram;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.stardust.model.xpdl.carnot.*;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.figures.ApplicationSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.ConditionalPerformerSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.EventFigure;
import org.eclipse.stardust.modeling.core.editors.figures.ModelerSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.OrganizationSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.ProcessInterfaceFigure;
import org.eclipse.stardust.modeling.core.editors.figures.ProcessSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.RoleSymbolFigure;
import org.eclipse.stardust.modeling.core.editors.figures.TransitionConnectionFigure;


public class WorkflowModelDiagramEditPartFactory implements EditPartFactory
{
   private static final EStructuralFeature[] activitySourceConnections = {
         CarnotWorkflowModelPackage.eINSTANCE.getIFlowObjectSymbol_OutTransitions(),
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_OutLinks()};

   private static final EStructuralFeature[] applicationSourceConnections = {CarnotWorkflowModelPackage.eINSTANCE
         .getApplicationSymbolType_ExecutingActivities()};

   private static final EStructuralFeature[] conditionalPerformerSourceConnections = {
         CarnotWorkflowModelPackage.eINSTANCE
               .getIModelParticipantSymbol_PerformedActivities(),
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_OutLinks()};

   private static final EStructuralFeature[] dataSourceConnections = {
         CarnotWorkflowModelPackage.eINSTANCE.getDataSymbolType_DataMappings(),
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_OutLinks()};

   private static final EStructuralFeature[] organizationSourceConnections = {
         CarnotWorkflowModelPackage.eINSTANCE
               .getOrganizationSymbolType_SuperOrganizations(),
         CarnotWorkflowModelPackage.eINSTANCE
               .getIModelParticipantSymbol_PerformedActivities(),
         CarnotWorkflowModelPackage.eINSTANCE.getIModelParticipantSymbol_TriggeredEvents(),                              
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_OutLinks()};

   private static final EStructuralFeature[] processSourceConnections = {
         CarnotWorkflowModelPackage.eINSTANCE.getProcessSymbolType_ParentProcesses(),
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_OutLinks()};

   private static final EStructuralFeature[] roleSourceConnections = {
         CarnotWorkflowModelPackage.eINSTANCE.getRoleSymbolType_OrganizationMemberships(),
         CarnotWorkflowModelPackage.eINSTANCE.getRoleSymbolType_Teams(),
         CarnotWorkflowModelPackage.eINSTANCE
               .getIModelParticipantSymbol_PerformedActivities(),
         CarnotWorkflowModelPackage.eINSTANCE.getIModelParticipantSymbol_TriggeredEvents(),               
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_OutLinks()};

   private static final EStructuralFeature[] startEventSourceConnections = {
      CarnotWorkflowModelPackage.eINSTANCE
         .getIFlowObjectSymbol_OutTransitions()};

   private static final EStructuralFeature[] startEventTargetConnections = {
      CarnotWorkflowModelPackage.eINSTANCE.getStartEventSymbol_TriggersConnections()};
   
   private static final EStructuralFeature[] activityTargetConnections = {
         CarnotWorkflowModelPackage.eINSTANCE.getIFlowObjectSymbol_InTransitions(),
         CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType_DataMappings(),
         CarnotWorkflowModelPackage.eINSTANCE.getActivitySymbolType_PerformsConnections(),
         CarnotWorkflowModelPackage.eINSTANCE
               .getActivitySymbolType_ExecutedByConnections(),
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_InLinks()};

   private static final EStructuralFeature[] dataTargetConnections = {CarnotWorkflowModelPackage.eINSTANCE
         .getINodeSymbol_InLinks()};

   private static final EStructuralFeature[] organizationTargetConnections = {
         CarnotWorkflowModelPackage.eINSTANCE
               .getOrganizationSymbolType_SubOrganizations(),
         CarnotWorkflowModelPackage.eINSTANCE.getOrganizationSymbolType_MemberRoles(),
         CarnotWorkflowModelPackage.eINSTANCE.getOrganizationSymbolType_TeamLead(),
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_InLinks()};

   private static final EStructuralFeature[] roleTargetConnections = {CarnotWorkflowModelPackage.eINSTANCE
         .getINodeSymbol_InLinks()};

   private static final EStructuralFeature[] conditionalPerformerTargetConnections = {CarnotWorkflowModelPackage.eINSTANCE
         .getINodeSymbol_InLinks()};

   private static final EStructuralFeature[] processTargetConnections = {
         CarnotWorkflowModelPackage.eINSTANCE.getProcessSymbolType_SubProcesses(),
         CarnotWorkflowModelPackage.eINSTANCE.getINodeSymbol_InLinks()};

   private static final EStructuralFeature[] endEventTargetConnections = {CarnotWorkflowModelPackage.eINSTANCE
         .getIFlowObjectSymbol_InTransitions()};

   private WorkflowModelEditor editor;

   public WorkflowModelDiagramEditPartFactory(WorkflowModelEditor editor)
   {
      this.editor = editor;
   }

   public EditPart createEditPart(EditPart context, Object model)
   {
      if (model instanceof ModelType)
      {
         // TODO
         List diagrams = ((ModelType) model).getDiagram();
         if (!diagrams.isEmpty())
         {
            return new DiagramEditPart(editor, (DiagramType) diagrams.get(0));
         }
         else
         {
            return new DiagramEditPart(editor, CarnotWorkflowModelFactory.eINSTANCE
                  .createDiagramType());
         }
      }
      else if (model instanceof DiagramType)
      {
         return new DiagramEditPart(editor, (DiagramType) model);
      }
      else if (model instanceof PoolSymbol)
      {
         return new PoolEditPart(editor, (PoolSymbol) model);
      }
      else if (model instanceof LaneSymbol)
      {
         return new LaneEditPart(editor, (LaneSymbol) model);
      }
      else if (model instanceof ActivitySymbolType)
      {
         return new ActivitySymbolNodeEditPart(editor, (ActivitySymbolType) model,
               activitySourceConnections, activityTargetConnections);
      }
      else if (model instanceof AnnotationSymbolType)
      {
         return new AnnotationSymbolEditPart(editor, (AnnotationSymbolType) model);
      }
      else if (model instanceof ApplicationSymbolType)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (ApplicationSymbolType) model, ApplicationSymbolFigure.class,
               applicationSourceConnections, null);
      }
      else if (model instanceof ConditionalPerformerSymbolType)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (ConditionalPerformerSymbolType) model,
               ConditionalPerformerSymbolFigure.class,
               conditionalPerformerSourceConnections,
               conditionalPerformerTargetConnections);
      }
      else if (model instanceof DataSymbolType)
      {
         return new DataSymbolNodeEditEditPart(editor, (DataSymbolType) model,
               dataSourceConnections, dataTargetConnections);
      }
      else if (model instanceof GatewaySymbol)
      {
         return new GatewaySymbolEditPart(editor, (GatewaySymbol) model);
      }
      else if (model instanceof ModelerSymbolType)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (ModelerSymbolType) model, ModelerSymbolFigure.class, null, null);
      }
      else if (model instanceof OrganizationSymbolType)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (OrganizationSymbolType) model, OrganizationSymbolFigure.class,
               organizationSourceConnections, organizationTargetConnections);
      }
      else if (model instanceof ProcessSymbolType)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (ProcessSymbolType) model, ProcessSymbolFigure.class,
               processSourceConnections, processTargetConnections);
      }
      else if (model instanceof RoleSymbolType)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (RoleSymbolType) model, RoleSymbolFigure.class, roleSourceConnections,
               roleTargetConnections);
      }
      else if (model instanceof StartEventSymbol)
      {
//         TriggerType trigger = ((StartEventSymbol) model).getTrigger();
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (StartEventSymbol) model,  /*trigger == null ? null :*/ EventFigure.class,
               startEventSourceConnections, startEventTargetConnections)
         {
            protected IFigure createFigure()
            {
               StartEventSymbol symbol = (StartEventSymbol) getModel();
               EventFigure f = new EventFigure(EventFigure.EVENT_FLOW_START,
                     getIconFactory().getIconFor(symbol));
               // f.setTypeIndicator(EventFigure.EVENT_TYPE_MESSAGE);
               f.setLocation(new Point(symbol.getXPos(), symbol.getYPos()));
               TriggerType trigger = symbol.getTrigger();
               f.setText(trigger == null ? null/* "Start Event" */: //$NON-NLS-1$ 
                     trigger.getName() == null ? trigger.getId() : trigger.getName());               
               return f;
            }
         };
      }
      else if (model instanceof EndEventSymbol)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (EndEventSymbol) model, EventFigure.class, null, endEventTargetConnections)
         {
            protected IFigure createFigure()
            {
               EndEventSymbol symbolModel = (EndEventSymbol) getModel();
               EventFigure f = new EventFigure(EventFigure.EVENT_FLOW_END,
                     getIconFactory().getIconFor(symbolModel));
               // f.setTypeIndicator(EventFigure.EVENT_TYPE_MESSAGE);
               f.setLocation(new Point(symbolModel.getXPos(), symbolModel.getYPos()));
               f.setText(null/* "End Event" */); //$NON-NLS-1$
               return f;
            }
         };
      }
      else if (model instanceof PublicInterfaceSymbol)
      {
         return new AbstractModelElementNodeSymbolEditPart(editor,
               (PublicInterfaceSymbol) model, ProcessInterfaceFigure.class, null, endEventTargetConnections)
         {
            protected IFigure createFigure()
            {
               PublicInterfaceSymbol symbolModel = (PublicInterfaceSymbol) getModel();
               ProcessInterfaceFigure f = new ProcessInterfaceFigure(ProcessInterfaceFigure.EVENT_FLOW_END,
                     getIconFactory().getIconFor(symbolModel));
               // f.setTypeIndicator(EventFigure.EVENT_TYPE_MESSAGE);
               f.setLocation(new Point(symbolModel.getXPos(), symbolModel.getYPos()));
               f.setText(null/* "End Event" */); //$NON-NLS-1$
               return f;
            }
         };
      }
      else if (model instanceof TextSymbolType)
      {
         return new TextSymbolEditPart(editor, (TextSymbolType) model);
      }
      else if (model instanceof GroupSymbolType)
      {
         return new SymbolGroupEditPart(editor, (GroupSymbolType) model);
      }
      else if (model instanceof DataMappingConnectionType)
      {
         return new AbstractConnectionSymbolEditPart((DataMappingConnectionType) model)
         {
            protected void refreshVisuals()
            {
               super.refreshVisuals();

               boolean hasIn = false;
               boolean hasOut = false;

               DataMappingConnectionType connection = (DataMappingConnectionType) getModel();
               ActivitySymbolType activitySymbol = connection.getActivitySymbol();
               DataSymbolType dataSymbol = connection.getDataSymbol();
               // refresh events may be fired before both symbols are set !
               if (activitySymbol != null && dataSymbol != null)
               {
                  ActivityType activity = (ActivityType) activitySymbol.getModelElement();
                  if (activity != null)
                  {
                     List dataMappings = activity.getDataMapping();
                     for (Iterator i = dataMappings.iterator(); i.hasNext();)
                     {
                        DataMappingType dataMapping = (DataMappingType) i.next();
                        if ((null != dataSymbol.getModelElement())
                              && (null != dataMapping)
                              && dataSymbol.getModelElement().equals(
                                    dataMapping.getData()))
                        {
                           if (DirectionType.IN_LITERAL
                                 .equals(dataMapping.getDirection()))
                           {
                              hasIn = true;
                           }
                           if (DirectionType.OUT_LITERAL.equals(dataMapping
                                 .getDirection()))
                           {
                              hasOut = true;
                           }
                        }
                     }
                  }
                  // workaround for #4523: if no direction found get the correct data
                  // mapping for the connection which has an eContainer of type
                  // ChangeDescription and get its direction
                  if (!hasIn && !hasOut)
                  {
                     if (dataSymbol.getData() != null)
                     {
                        DirectionType direction = null;
                        for (Iterator iter = dataSymbol.getData().getDataMappings()
                              .iterator(); iter.hasNext();)
                        {
                           DataMappingType dataMappingType = (DataMappingType) iter
                                 .next();

                           if (dataSymbol.getData().getDataMappings().size() == 1)
                           {
                              direction = dataMappingType.getDirection();

                           }
                           else if (dataMappingType.eContainer() instanceof ChangeDescription)
                           {
                              direction = dataMappingType.getDirection();
                           }
                        }
                        if (DirectionType.IN_LITERAL.equals(direction))
                        {
                           hasIn = true;
                        }
                        if (DirectionType.OUT_LITERAL.equals(direction))
                        {
                           hasOut = true;
                        }
                     }
                  }
               }

               PolylineConnection pLine = (PolylineConnection) getFigure();

               pLine.setForegroundColor(ColorConstants.lightGray);
               // arrow at source endpoint
               pLine.setSourceDecoration(hasOut ? new PolygonDecoration() : null);
               // arrow at target endpoint
               pLine.setTargetDecoration(hasIn ? new PolygonDecoration() : null);
            }
         };
      }
      else if (model instanceof ExecutedByConnectionType)
      {
         return new ExecutedByConnectionEditPart((ExecutedByConnectionType) model);
      }
      else if (model instanceof PartOfConnectionType)
      {
         return new PartOfConnectionEditPart((PartOfConnectionType) model);
      }
      else if (model instanceof TriggersConnectionType)
      {
         return new TriggersConnectionEditPart((TriggersConnectionType) model);
      }
      else if (model instanceof PerformsConnectionType)
      {
         return new PerformsConnectionEditPart((PerformsConnectionType) model);
      }
      else if (model instanceof SubProcessOfConnectionType)
      {
         return new SubProcessOfConnectionEditPart((SubProcessOfConnectionType) model);
      }
      else if (model instanceof TransitionConnectionType)
      {
         return new AbstractConnectionSymbolEditPart((TransitionConnectionType) model)
         {
            protected IFigure createFigure()
            {
               return new TransitionConnectionFigure((TransitionConnectionType) getModel());
            }

            public Object getAdapter(Class key)
            {
               if (IModelElement.class == key)
               {
                  return ((TransitionConnectionType) getModel()).getTransition();
               }
               return super.getAdapter(key);
            }
         };
      }
      else if (model instanceof WorksForConnectionType)
      {
         return new WorksForConnectionEditPart((WorksForConnectionType) model);
      }
      else if (model instanceof TeamLeadConnectionType)
      {
         return new TeamLeadConnectionEditPart((TeamLeadConnectionType) model);
      }
      else if (model instanceof RefersToConnectionType)
      {
         return new RefersToConnectionEditPart((RefersToConnectionType) model);
      }
      else if (model instanceof GenericLinkConnectionType)
      {
         return new GenericLinkConnectionEditPart((GenericLinkConnectionType) model);
      }
      return null;
   }
}