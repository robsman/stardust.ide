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
package org.eclipse.stardust.modeling.core.editors;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DiagramModeType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiExtensionRegistry;
import org.eclipse.stardust.model.xpdl.carnot.util.ActivityUtil;
import org.eclipse.stardust.model.xpdl.carnot.util.CarnotConstants;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.tools.CarnotConnectionCreationToolEntry;
import org.eclipse.stardust.modeling.core.editors.tools.CarnotCreationToolEntry;
import org.eclipse.stardust.modeling.core.editors.tools.CarnotSelectionTool;
import org.eclipse.stardust.modeling.core.editors.tools.PaletteFlyout;


public class WorkflowModelEditorPaletteFactory
{
   public static final int CONNECTION_CONTAINER = 1;

   public static final int NODE_CONTAINER = 2;

   public static final int TRANSITION_CONNECTION = 0;

   public static final int DATA_MAPPING_CONNECTION = 1;

   public static final int EXECUTED_BY_CONNECTION = 2;

   public static final int PERFORMED_BY_CONNECTION = 3;

   public static final int PROCESS_ORGANIZATION_CONTAINER = 4;

   public static final int MODEL_ORGANIZATION_CONTAINER = 0;

   public static final int WORKS_FOR_CONNECTION = 0;

   public static final int PART_OF_CONNECTION = 1;

   public static final CarnotWorkflowModelPackage PKG_CWM = CarnotWorkflowModelPackage.eINSTANCE;

   public static final int GENERIC_DIAGRAM = 0;

   public static final int MODEL_DIAGRAM = 1;

   public static final int PROCESS_DEFINITION_DIAGRAM = 2;

   public static DiagramModeType diagramModeType = null;

//   private static final String LINK_TOOL_DESCRIPTION = Editor_Messages.LINK_TOOL_DESCRIPTION;

   public static FlyoutPreferences createPalettePreferences()
   {
      return new PalettePreferences();
   }

   public static void setDiagramModeType(DiagramModeType mode)
   {
      WorkflowModelEditorPaletteFactory.diagramModeType = mode;
   }

   public static PaletteRoot createPaletteForDiagram(DiagramEditorPage editor)
   {
      boolean isModelDiagram = MODEL_DIAGRAM == getDiagramType(editor.getDiagram());

      PaletteRoot palette = new PaletteRoot();

      PaletteContainer controlGroup = createControlGroup(editor, isModelDiagram);
      palette.add(controlGroup);
      palette.setDefaultEntry((ToolEntry) controlGroup.getChildren().get(0));

      if (isModelDiagram)
      {
         palette.add(CONNECTION_CONTAINER, createConnectionsContainer(editor, false));
         palette.add(NODE_CONTAINER, createModelNodesContainer());
      }
      else
      {
         palette.add(CONNECTION_CONTAINER, createConnectionsContainer(editor, true));
         palette.add(NODE_CONTAINER, createProcessNodesContainer(editor));
      }

      return palette;
   }

   public static void updatePalette(DiagramEditorPage editor)
   {
      boolean isBusinessPerspective = false;
      PaletteRoot palette = editor.getPaletteRoot();
      PaletteContainer rootNodes = (PaletteContainer) palette.getChildren().get(2);
      List children = rootNodes.getChildren();
      int start = 1;
      int last = children.size() - 1;
      if (getDiagramType(editor.getDiagram()) == PROCESS_DEFINITION_DIAGRAM)
      {
         start--;
         last--;
         PaletteContainer events = (PaletteContainer) children.get(last);
         List eventsChildren = events.getChildren();
         for (int i = 2; i < eventsChildren.size(); i++)
         {
            ((PaletteEntry) eventsChildren.get(i)).setVisible(!isBusinessPerspective);
         }
      }
      for (int i = start; i < last; i++)
      {
         ((PaletteEntry) children.get(i)).setVisible(i < 3
               ? isBusinessPerspective
               : !isBusinessPerspective);
      }
      if (getDiagramType(editor.getDiagram()) == PROCESS_DEFINITION_DIAGRAM
            && WorkflowModelEditorPaletteFactory.diagramModeType != null)
      {
         rootNodes = (PaletteContainer) palette.getChildren().get(0);
         children = rootNodes.getChildren();
         CarnotCreationToolEntry entry = (CarnotCreationToolEntry) children.get(2);
         if(diagramModeType.equals(DiagramModeType.MODE_450_LITERAL))
         {
            entry.setVisible(true);
         }
         else
         {
            entry.setVisible(false);
         }
      }
   }

   private static int getDiagramType(DiagramType diagram)
   {
      if (diagram.eContainer() instanceof ModelType)
      {
         return WorkflowModelEditorPaletteFactory.MODEL_DIAGRAM;
      }
      else if (diagram.eContainer() instanceof ProcessDefinitionType)
      {
         return WorkflowModelEditorPaletteFactory.PROCESS_DEFINITION_DIAGRAM;
      }
      else
      {
         return WorkflowModelEditorPaletteFactory.GENERIC_DIAGRAM;
      }
   }

   private static PaletteContainer createControlGroup(EditPartRegistry registry,
         boolean isModelDiagram)
   {
      PaletteGroup controls = new PaletteGroup(Diagram_Messages.LB_PALETTEGROUP_Controls);

      ToolEntry selectionEntry = new PanningSelectionToolEntry();
      selectionEntry.setToolClass(CarnotSelectionTool.class);
      selectionEntry.setLabel(Diagram_Messages.LBL_SELECT);

      controls.add(selectionEntry);
//      controls.add(new MarqueeToolEntry());

      PaletteSeparator separator = new PaletteSeparator();
      separator.setUserModificationPermission(PaletteEntry.PERMISSION_NO_MODIFICATION);
      controls.add(separator);

      controls.add(createSwimlaneTools(isModelDiagram, (DiagramEditorPage) registry));
      controls.add(createTextTools(registry));

      return controls;
   }

   private static PaletteContainer createConnectionsContainer(DiagramEditorPage editor,
         boolean isProcess)
   {
/*      PaletteDrawer drawer = new PaletteDrawer(
            Editor_Messages.LB_PALETTEDRAWER_Connections, DiagramPlugin
                  .getImageDescriptor("icons/blank16.gif")); //$NON-NLS-1$*/

      PaletteGroup drawer = new PaletteGroup(Diagram_Messages.LB_PALETTEDRAWER_Connections);
      PaletteEntry connectionTool = new CarnotConnectionCreationToolEntry(
            Diagram_Messages.WorkflowModelEditorPaletteFactory_ConnectToolLabel, // label
            Diagram_Messages.WorkflowModelEditorPaletteFactory_ConnectToolDescription, // description
            new DynamicConnectionFactory(editor.getWorkflowModelEditor()), // CreationFactory
            DiagramPlugin.getImageDescriptor("icons/full/obj16/connection.gif"), // small icon //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/full/obj16/connection.gif")); // large icon //$NON-NLS-1$
      connectionTool.setId(DiagramActionConstants.CONNECT);
      drawer.add(connectionTool);
      return drawer;
   }

   private static PaletteContainer createModelNodesContainer()
   {
      PaletteDrawer drawer = new PaletteDrawer(Diagram_Messages.LB_PALETTEDRAWER_Items,
            DiagramPlugin.getImageDescriptor("icons/blank16.gif")); //$NON-NLS-1$

      drawer.add(new CarnotCreationToolEntry(
            Diagram_Messages.LB_TOOLENTRY_ProcessDefintion,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewWorkflow, NodeCreationFactory
                  .getProcessDefinitionFactory(), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/process.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/full/obj16/process.gif"))); //$NON-NLS-1$

      CarnotCreationToolEntry tool = new CarnotCreationToolEntry(
            Diagram_Messages.WorkflowModelEditorPaletteFactory_LB_TOOLENTRY_Data,
            Diagram_Messages.WorkflowModelEditorPaletteFactory_DESC_TOOLENTRY_CreateData,
            NodeCreationFactory.getDataFactory(null), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/data.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/full/obj16/data.gif")); //$NON-NLS-1$
      drawer.add(tool);
      tool = new CarnotCreationToolEntry(
            Diagram_Messages.WorkflowModelEditorPaletteFactory_LB_TOOLENTRY_Application,
            Diagram_Messages.WorkflowModelEditorPaletteFactory_DESC_TOOLENTRY_CreateApplication,
            NodeCreationFactory.getApplicationFactory(null), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/application.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/full/obj16/application.gif")); //$NON-NLS-1$
      drawer.add(tool);

      drawer.add(createDataTools());
      drawer.add(createApplicationTools());
      drawer.add(createContextTools());
      drawer.add(createParticipantContainer());

      return drawer;
   }

   private static PaletteContainer createActivityTools(DiagramEditorPage editor)
   {
      PaletteContainer container = new PaletteFlyout(
            Diagram_Messages.LB_PALETTEDRAWER_Activities,
            Diagram_Messages.DESC_PALETTEDRAWER_Activities, DiagramPlugin
                  .getImageDescriptor(editor.getWorkflowModelEditor().getIconFactory().getIconFor(PKG_CWM.getActivityType())));
      container.add(new CarnotCreationToolEntry(Diagram_Messages.LB_TOOLENTRY_Route,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewRouteActivity, NodeCreationFactory
                  .getActivityFactory(ActivityImplementationType.ROUTE_LITERAL),
            DiagramPlugin.getImageDescriptor("icons/full/obj16/activity_route.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      container.add(new CarnotCreationToolEntry(Diagram_Messages.LB_TOOLENTRY_Manual,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewManualActivity, NodeCreationFactory
                  .getActivityFactory(ActivityImplementationType.MANUAL_LITERAL),
            DiagramPlugin.getImageDescriptor("icons/full/obj16/activity_manual.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      container
            .add(new CarnotCreationToolEntry(
                  Diagram_Messages.LB_TOOLENTRY_Application,
                  Diagram_Messages.DESC_TOOLENTRY_CreatesNewApplicationActivity,
                  NodeCreationFactory
                        .getActivityFactory(ActivityImplementationType.APPLICATION_LITERAL),
                  DiagramPlugin
                        .getImageDescriptor("icons/full/obj16/activity_application.gif"), //$NON-NLS-1$
                  DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      container.add(new CarnotCreationToolEntry(Diagram_Messages.LB_TOOLENTRY_Subprocess,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewSubprocessActivity,
            NodeCreationFactory
                  .getActivityFactory(ActivityImplementationType.SUBPROCESS_LITERAL),
            DiagramPlugin.getImageDescriptor("icons/full/obj16/activity_subprocess.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      return container;
   }

   private static PaletteContainer createProcessNodesContainer(DiagramEditorPage editor)
   {
      PaletteDrawer drawer = new PaletteDrawer(Diagram_Messages.LB_PALETTEDRAWER_Items,
            DiagramPlugin.getImageDescriptor("icons/blank16.gif")); //$NON-NLS-1$

      CarnotCreationToolEntry tool = new CarnotCreationToolEntry(Diagram_Messages.LB_Activity,
            Diagram_Messages.LB_CreateActivity, NodeCreationFactory.getActivityFactory(null),
            DiagramPlugin.getImageDescriptor("icons/full/obj16/activity.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/full/obj16/activity.gif")); //$NON-NLS-1$
      drawer.add(tool);
      tool = new CarnotCreationToolEntry(
            Diagram_Messages.WorkflowModelEditorPaletteFactory_LB_TOOLENTRY_Data,
            Diagram_Messages.WorkflowModelEditorPaletteFactory_DESC_TOOLENTRY_CreateData,
            NodeCreationFactory.getDataFactory(null), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/data.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/full/obj16/data.gif")); //$NON-NLS-1$
      drawer.add(tool);
      tool = new CarnotCreationToolEntry(
            Diagram_Messages.WorkflowModelEditorPaletteFactory_LB_TOOLENTRY_Application,
            Diagram_Messages.WorkflowModelEditorPaletteFactory_DESC_TOOLENTRY_CreateApplication,
            NodeCreationFactory.getApplicationFactory(null), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/application.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/full/obj16/application.gif")); //$NON-NLS-1$
      drawer.add(tool);

      drawer.add(createActivityTools(editor));
      drawer.add(createDataTools());
      drawer.add(createApplicationTools());
      drawer.add(createContextTools());
      drawer.add(createStartingTools());
      drawer.add(createParticipantContainer());

      return drawer;
   }

   private static ToolEntry createSwimlaneTools(boolean isModelDiagram, DiagramEditorPage editor)
   {
/*      PaletteContainer swimlaneTools = new PaletteFlyout(
            Editor_Messages.LB_PALETTESTACK_Swimlane,
            Editor_Messages.DESC_PALETTESTACK_CreateSwimlane, DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/pool.gif")); //$NON-NLS-1$*/
      if (isModelDiagram)
      {
         CarnotCreationToolEntry toolEntry =
            new CarnotCreationToolEntry(Diagram_Messages.LB_TOOLENTRY_Pool,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewPool, NodeCreationFactory
                  .getPoolFactory(), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/pool.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/pool24.gif")); //$NON-NLS-1$
         toolEntry.setVisible(false);

         return toolEntry;
      }
      else
      {
         CarnotCreationToolEntry toolEntry =
            new CarnotCreationToolEntry(Diagram_Messages.LB_TOOLENTRY_Lane,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewLane, NodeCreationFactory
                  .getLaneFactory(), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/lane.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/lane24.gif")); //$NON-NLS-1$

         if(editor.getDiagram().getMode().equals(DiagramModeType.MODE_450_LITERAL))
         {
            toolEntry.setVisible(true);
         }
         else
         {
            toolEntry.setVisible(false);
         }

         return toolEntry;
      }
   }

   private static PaletteContainer createTextTools(EditPartRegistry registry)
   {
      PaletteContainer textTools = new PaletteFlyout(
            Diagram_Messages.LB_PALETTESTACK_Annotations,
            Diagram_Messages.DESC_PALETTESTACK_CreateAnnotation, DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/annotation.gif")); //$NON-NLS-1$
      textTools.add(new CarnotCreationToolEntry(Diagram_Messages.LB_TOOLENTRY_Annotation,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewAnnotation, NodeCreationFactory
                  .getAnnotationFactory(registry), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/annotation.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      textTools.add(new CarnotCreationToolEntry(Diagram_Messages.LB_TOOLENTRY_Text,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewTextField, NodeCreationFactory
                  .getTextFactory(registry), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/text.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      return textTools;
   }

   private static PaletteContainer createContextTools()
   {
      PaletteContainer applicationTools = new PaletteFlyout(
            Diagram_Messages.LB_PALETTESTACK_Contexts,
            Diagram_Messages.DESC_PALETTESTACK_CreateInteractiveApplications,
            // DiagramPlugin.getImageDescriptor(IconFactory.getIconFor(PKG_CWM.getApplicationType())));
            DiagramPlugin.getImageDescriptor("icons/full/obj16/contexts.gif")); //$NON-NLS-1$
      // add an entry for each application type
      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      Map extensions = registry
            .getExtensions(CarnotConstants.CONTEXT_TYPES_EXTENSION_POINT_ID);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         String id = config.getAttribute(SpiConstants.ID);
         if (!ActivityUtil.isImplicitContext(id))
         {
            CarnotCreationToolEntry tool = new CarnotCreationToolEntry(config
                  .getAttribute(SpiConstants.NAME),
                  Diagram_Messages.DESC_TOOLENTRY_P1_CreatesNew + id
                        + Diagram_Messages.DESC_TOOLENTRY_P2_interactiveApplication,
                  NodeCreationFactory.getContextFactory(config),
                  DiagramPlugin.getImageDescriptor(config),
                  DiagramPlugin.getImageDescriptor("icons/connection24.gif")); //$NON-NLS-1$
            applicationTools.add(tool);
         }
      }
      return applicationTools;
   }

   private static PaletteContainer createApplicationTools()
   {
      PaletteFlyout applicationTools = new PaletteFlyout(
            Diagram_Messages.LB_PALETTESTACK_Applications,
            Diagram_Messages.DESC_PALETTESTACK_CreateNoninteractiveApplications,
            DiagramPlugin.getImageDescriptor("icons/full/obj16/applications.gif")); //$NON-NLS-1$
      // add an entry for each application type
      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      Map extensions = registry
            .getExtensions(CarnotConstants.APPLICATION_TYPES_EXTENSION_POINT_ID);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         CarnotCreationToolEntry tool = new CarnotCreationToolEntry(
               config.getAttribute(SpiConstants.NAME),
               Diagram_Messages.LB_TOOLENTRY_P1_CreatesNew
                     + config.getAttribute(SpiConstants.NAME) + Diagram_Messages.LB_TOOLENTRY_P2_application,
               NodeCreationFactory.getApplicationFactory(config),
               DiagramPlugin.getImageDescriptor(config),
               DiagramPlugin.getImageDescriptor("icons/connection24.gif")); //$NON-NLS-1$
         applicationTools.add(tool);
      }
      return applicationTools;
   }

   private static PaletteContainer createStartingTools()
   {
      PaletteFlyout startingTools = new PaletteFlyout(
            Diagram_Messages.LB_PALETTEDRAWER_ProcessLifecycle,
            Diagram_Messages.DESC_PALETTEDRAWER_ProcessLifecycle, DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/trigger.gif")); //$NON-NLS-1$
      CarnotCreationToolEntry startTool = new CarnotCreationToolEntry(
            Diagram_Messages.LB_TOOLENTRY_StartEvent,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewStartEvent, NodeCreationFactory
                  .getEventFactory(null, false), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/start_event.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif")); //$NON-NLS-1$
      CarnotCreationToolEntry endTool = new CarnotCreationToolEntry(
            Diagram_Messages.LB_TOOLENTRY_EndEvent,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewEndEvent, NodeCreationFactory
                  .getEventFactory(null, true), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/end_event.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif")); //$NON-NLS-1$
      startingTools.add(startTool);
      startingTools.add(endTool);
      // add an entry for each trigger type
      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      Map extensions = registry
            .getExtensions(CarnotConstants.TRIGGER_TYPES_EXTENSION_POINT_ID);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         CarnotCreationToolEntry tool = new CarnotCreationToolEntry(
               config.getAttribute(SpiConstants.NAME),
               Diagram_Messages.LB_TOOLENTRY_P1_CreatesNew
                     + config.getAttribute(SpiConstants.ID) + Diagram_Messages.LB_TOOLENTRY_P2_trigger,
               NodeCreationFactory.getEventFactory(config, false),
               DiagramPlugin.getImageDescriptor(config),
               DiagramPlugin.getImageDescriptor("icons/connection24.gif")); //$NON-NLS-1$
         startingTools.add(tool);
      }
      return startingTools;
   }

   private static PaletteContainer createDataTools()
   {
      PaletteFlyout dataTools = new PaletteFlyout(Diagram_Messages.LB_PALETTESTACK_Data,
            Diagram_Messages.DESC_PALETTESTACK_CreateData, DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/data.gif")); //$NON-NLS-1$
      // add an entry for each
      SpiExtensionRegistry registry = SpiExtensionRegistry.instance();
      Map extensions = registry
            .getExtensions(CarnotConstants.DATA_TYPES_EXTENSION_POINT_ID);
      for (Iterator i = extensions.values().iterator(); i.hasNext();)
      {
         IConfigurationElement config = (IConfigurationElement) i.next();
         CarnotCreationToolEntry tool = new CarnotCreationToolEntry(
               config.getAttribute(SpiConstants.NAME),
               Diagram_Messages.LB_TOOLENTRY_P1_CreatesNew
                     + config.getAttribute(SpiConstants.ID) + Diagram_Messages.LB_TOOLENTRY_P2_workflowData,
               NodeCreationFactory.getDataFactory(config),
               DiagramPlugin.getImageDescriptor(config),
               DiagramPlugin.getImageDescriptor("icons/connection24.gif")); //$NON-NLS-1$
         dataTools.add(tool);
         if ("primitive".equals(config.getAttribute(SpiConstants.ID))) //$NON-NLS-1$
         {
            dataTools.setActiveEntry(tool);
         }
      }
      return dataTools;
   }

   private static PaletteContainer createParticipantContainer()
   {
      PaletteContainer participantsTools = new PaletteFlyout(
            Diagram_Messages.LB_PALETTESTACK_Participants,
            Diagram_Messages.DESC_PALETTESTACK_Participants, DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/participants.gif")); //$NON-NLS-1$
      participantsTools.add(new CarnotCreationToolEntry(
            Diagram_Messages.LB_TOOLENTRY_Role,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewWorkflowRole, NodeCreationFactory
                  .getRoleFactory(), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/role.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      participantsTools.add(new CarnotCreationToolEntry(
            Diagram_Messages.LB_TOOLENTRY_Organization,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewWorkflowOrganization,
            NodeCreationFactory.getOrganizationFactory(), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/organization.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$
      participantsTools.add(new CarnotCreationToolEntry(
            Diagram_Messages.LB_TOOLENTRY_ConditionalPerformer,
            Diagram_Messages.DESC_TOOLENTRY_CreatesNewWorkflowCondPerformer,
            NodeCreationFactory.getConditionalPerformerFactory(), DiagramPlugin
                  .getImageDescriptor("icons/full/obj16/conditional.gif"), //$NON-NLS-1$
            DiagramPlugin.getImageDescriptor("icons/connection24.gif"))); //$NON-NLS-1$

      return participantsTools;
   }

   private static final class PalettePreferences implements FlyoutPreferences
   {
      private IPreferenceStore getPreferenceStore()
      {
         return DiagramPlugin.getDefault().getPreferenceStore();
      }

      public int getDockLocation()
      {
         return getPreferenceStore().getInt(
               "WorkflowDiagramEditorPaletteFactory.Location"); //$NON-NLS-1$
      }

      public int getPaletteState()
      {
         return getPreferenceStore().getInt("WorkflowDiagramEditorPaletteFactory.State"); //$NON-NLS-1$
      }

      public int getPaletteWidth()
      {
         return getPreferenceStore().getInt("WorkflowDiagramEditorPaletteFactory.Size"); //$NON-NLS-1$
      }

      public void setDockLocation(int location)
      {
         getPreferenceStore().setValue(
               "WorkflowDiagramEditorPaletteFactory.Location", location); //$NON-NLS-1$
      }

      public void setPaletteState(int state)
      {
         getPreferenceStore()
               .setValue("WorkflowDiagramEditorPaletteFactory.State", state); //$NON-NLS-1$
      }

      public void setPaletteWidth(int width)
      {
         getPreferenceStore().setValue("WorkflowDiagramEditorPaletteFactory.Size", width); //$NON-NLS-1$
      }
   }
}