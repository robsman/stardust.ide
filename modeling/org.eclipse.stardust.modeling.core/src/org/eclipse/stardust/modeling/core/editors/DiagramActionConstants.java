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

import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.ui.actions.ActionFactory;

public interface DiagramActionConstants
{
   final String CLEANUP_MODEL = "org.eclipse.stardust.modeling.core.cleanupModelAction"; //$NON-NLS-1$

   final String SHRINK_TO_FIT = "org.eclipse.stardust.modeling.core.shrinkToFitAction"; //$NON-NLS-1$

   final String SHOW_IN_DIAGRAM = "org.eclipse.stardust.modeling.core.showInDiagramAction"; //$NON-NLS-1$

   final String SHOW_IN_OUTLINE = "org.eclipse.stardust.modeling.core.showInOutlineAction"; //$NON-NLS-1$

   final String COPYSYMBOL = "org.eclipse.stardust.modeling.core.copySymbolAction"; //$NON-NLS-1$

   final String PASTESYMBOL = "org.eclipse.stardust.modeling.core.pasteSymbolAction"; //$NON-NLS-1$

   final String MODEL_VALIDATE = "org.eclipse.stardust.modeling.core.validateModel"; //$NON-NLS-1$

   final String IMPORT_MODEL_ELEMENTS = "org.eclipse.stardust.modeling.modelimport.modelElements"; //$NON-NLS-1$

   final String DEPLOY_MODEL = "org.eclipse.stardust.modeling.core.deployModel"; //$NON-NLS-1$

   final String DIAGRAM_OPTIMIZE = "org.eclipse.stardust.modeling.core.optimize"; //$NON-NLS-1$

   final String DIAGRAM_UPDATE = "org.eclipse.stardust.modeling.core.update"; //$NON-NLS-1$

   final String DIAGRAM_CLOSE = "org.eclipse.stardust.modeling.core.close"; //$NON-NLS-1$

   final String DIAGRAM_OPEN = "org.eclipse.stardust.modeling.core.open"; //$NON-NLS-1$

   final String DEFAULT_DIAGRAM_OPEN = "org.eclipse.stardust.modeling.core.process.open"; //$NON-NLS-1$

   final String SUBPROCESS_DIAGRAM_OPEN = "org.eclipse.stardust.modeling.core.subprocess.open"; //$NON-NLS-1$

   final String DELETE_SYMBOL = "org.eclipse.stardust.modeling.core.deleteSymbol"; //$NON-NLS-1$

   final String CREATE_GENERIC_ACTIVITY = "org.eclipse.stardust.modeling.core.createGenericActivity"; //$NON-NLS-1$

   final String CREATE_MANUAL_ACTIVITY = "org.eclipse.stardust.modeling.core.createManualActivity"; //$NON-NLS-1$

   final String CREATE_ROUTE_ACTIVITY = "org.eclipse.stardust.modeling.core.createRouteActivity"; //$NON-NLS-1$

   final String CREATE_SUBPROCESS_ACTIVITY = "org.eclipse.stardust.modeling.core.createSubprocessActivity"; //$NON-NLS-1$

   final String CREATE_APPLICATION_ACTIVITY = "org.eclipse.stardust.modeling.core.createApplicationActivity"; //$NON-NLS-1$

   final String CREATE_TRIGGER = "org.eclipse.stardust.modeling.core.createTrigger."; //$NON-NLS-1$

   final String CREATE_PROCESS_DEFINITION = "org.eclipse.stardust.modeling.core.createProcessDefinition"; //$NON-NLS-1$

   final String CREATE_APPLICATION = "org.eclipse.stardust.modeling.core.createApplication."; //$NON-NLS-1$

   final String CREATE_INTERACTIVE_APPLICATION = "org.eclipse.stardust.modeling.core.createInteractiveApplication."; //$NON-NLS-1$

   final String CREATE_GENERIC_APPLICATION = "org.eclipse.stardust.modeling.core.createGenericApplication"; //$NON-NLS-1$

   final String CREATE_DIAGRAM = "org.eclipse.stardust.modeling.core.createDiagram"; //$NON-NLS-1$

   final String CREATE_DATA = "org.eclipse.stardust.modeling.core.createData."; //$NON-NLS-1$

   final String CREATE_GENERIC_DATA = "org.eclipse.stardust.modeling.core.createGenericData"; //$NON-NLS-1$

   final String CREATE_MODELER = "org.eclipse.stardust.modeling.core.createModeler"; //$NON-NLS-1$

   final String CREATE_ROLE = "org.eclipse.stardust.modeling.core.createRole"; //$NON-NLS-1$

   final String CREATE_CONDITIONAL_PERFORMER = "org.eclipse.stardust.modeling.core.createConditionalPerformer"; //$NON-NLS-1$

   final String CREATE_ORGANIZATION = "org.eclipse.stardust.modeling.core.createOrganization"; //$NON-NLS-1$

   final String CREATE_LINK_TYPE = "org.eclipse.stardust.modeling.core.createLinkType"; //$NON-NLS-1$

   final String SET_PARTICIPANT = "org.eclipse.stardust.modeling.core.setParticipant"; //$NON-NLS-1$

   final String CONNECT = "org.eclipse.stardust.modeling.core.connect"; //$NON-NLS-1$

   final String RELOAD_CONNECTIONS = "org.eclipse.stardust.modeling.core.reloadConnections"; //$NON-NLS-1$

   final String DISTRIBUTE_HORIZONTAL = "org.eclipse.stardust.modeling.core.distributeHorizontal"; //$NON-NLS-1$

   final String DISTRIBUTE_VERTICAL = "org.eclipse.stardust.modeling.core.distributeVertical"; //$NON-NLS-1$

   final String SET_DEFAULT_SIZE = "org.eclipse.stardust.modeling.core.setDefaultSize"; //$NON-NLS-1$

   final String GROUP_SYMBOLS = "org.eclipse.stardust.modeling.core.groupSymbols"; //$NON-NLS-1$

   final String UNGROUP_SYMBOLS = "org.eclipse.stardust.modeling.core.ungroupSymbols"; //$NON-NLS-1$

   final String CREATE_ACTIVITY_GRAPH = "org.eclipse.stardust.modeling.core.createActivityGraph"; //$NON-NLS-1$

   final String CREATE_ORGANIZATION_HIERARCHY = "org.eclipse.stardust.modeling.core.createOrganizationHierarchy"; //$NON-NLS-1$

   final String EXPORT_DIAGRAM = "org.eclipse.stardust.modeling.core.export.diagram"; //$NON-NLS-1$

   final String SEARCH = "org.eclipse.stardust.modeling.core.search"; //$NON-NLS-1$
   final String SEARCH_CONNECTION = "org.eclipse.stardust.modeling.repository.common.search"; //$NON-NLS-1$

   final String REFERENCES_SEARCH = "org.eclipse.stardust.modeling.core.referencesSearch"; //$NON-NLS-1$

   final String CREATE_CONNECTION = "org.eclipse.stardust.modeling.core.connection"; //$NON-NLS-1$

   final String SNAP_TO_GRID = "org.eclipse.stardust.modeling.core.snapToGrid"; //$NON-NLS-1$

   final String CONVERT_GATEWAYS = "org.eclipse.stardust.modeling.core.convertGatewaysAction"; //$NON-NLS-1$

   final String[] distributeActions = {
      DISTRIBUTE_HORIZONTAL,
      DISTRIBUTE_VERTICAL
   };

   final String[] horizontalAlignActions = {
      GEFActionConstants.ALIGN_LEFT,
      GEFActionConstants.ALIGN_CENTER,
      GEFActionConstants.ALIGN_RIGHT
   };

   final String[] verticalAlignAction = {
      GEFActionConstants.ALIGN_TOP,
      GEFActionConstants.ALIGN_MIDDLE,
      GEFActionConstants.ALIGN_BOTTOM
   };

   final String[] saveActions = {
      ActionFactory.PRINT.getId(),
      ActionFactory.SAVE.getId(),
      ActionFactory.PROPERTIES.getId()
   };

   final String viewActions[] = {
      DIAGRAM_OPEN,
      DEFAULT_DIAGRAM_OPEN,
      SUBPROCESS_DIAGRAM_OPEN,
      DIAGRAM_OPTIMIZE,
      DIAGRAM_UPDATE,
      DIAGRAM_CLOSE,
      RELOAD_CONNECTIONS,
      MODEL_VALIDATE,
      IMPORT_MODEL_ELEMENTS
   };

   final String[] participantActions = {
      CREATE_ROLE,
      CREATE_ORGANIZATION,
      CREATE_CONDITIONAL_PERFORMER,
      CREATE_MODELER
   };

   final String[] createActivityActions = {
      CREATE_MANUAL_ACTIVITY,
      CREATE_ROUTE_ACTIVITY,
      CREATE_APPLICATION_ACTIVITY,
      CREATE_SUBPROCESS_ACTIVITY
   };

   final String CREATE_SUBPROCESS = "org.eclipse.stardust.modeling.core.createSubprocessDefinition"; //$NON-NLS-1$;

   final String CREATE_SUBPROCESS_FROM_SELECTION = "org.eclipse.stardust.modeling.core.createSubprocessFromSelection"; //$NON-NLS-1$;

   final String RESET_SUBPROCESS = "org.eclipse.stardust.modeling.core.resetSubprocessDefinition"; //$NON-NLS-1$;

   final String MODEL_UPGRADE = "org.eclipse.stardust.model.xpdl.upgradeModel"; //$NON-NLS-1$

   final String MODEL_DIAGRAM_UPGRADE = "org.eclipse.stardust.model.xpdl.upgradeModelDiagram"; //$NON-NLS-1$

   final String DATA_UPGRADE = "org.eclipse.stardust.model.xpdl.upgradeData"; //$NON-NLS-1$

   final String FORWARD_DELETE = "org.eclipse.stardust.modeling.core.forwardDelete"; //$NON-NLS-1$

   final String PROCESS_DIAGRAM_UPDATE = "org.eclipse.stardust.model.xpdl.updateProcessDiagram"; //$NON-NLS-1$
}