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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.actions;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ApplicationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipantSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.OrganizationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.carnot.RoleSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditorPaletteFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPart;


public class CreateConnectionAction extends SelectionAction
{
   private static final int MODEL_ORGANIZATION_CONTAINER = WorkflowModelEditorPaletteFactory.MODEL_ORGANIZATION_CONTAINER;

   private static final int PROCESS_ORGANIZATION_CONTAINER = WorkflowModelEditorPaletteFactory.PROCESS_ORGANIZATION_CONTAINER;

   private static final CarnotWorkflowModelPackage CWM_PKG = CarnotWorkflowModelPackage.eINSTANCE;

   public static final int TRANSITION = WorkflowModelEditorPaletteFactory.TRANSITION_CONNECTION;

   public static final int DATA_MAPPING = WorkflowModelEditorPaletteFactory.DATA_MAPPING_CONNECTION;

   public static final int EXECUTED_BY = WorkflowModelEditorPaletteFactory.EXECUTED_BY_CONNECTION;

   public static final int PERFORMED_BY = WorkflowModelEditorPaletteFactory.PERFORMED_BY_CONNECTION;

   public static final int PART_OF = WorkflowModelEditorPaletteFactory.PART_OF_CONNECTION;

   public static final int WORKS_FOR = WorkflowModelEditorPaletteFactory.WORKS_FOR_CONNECTION;

   private WorkflowModelEditor editor;

   private int type;

   private Object selectedObject;

   private boolean isEnabled = false;

   private boolean isOrganizationType = false;

   public CreateConnectionAction(IWorkbenchPart part, int type, String label,
         Object selectedObject)
   {
      this(part, type, false, label, selectedObject);
   }

   public CreateConnectionAction(IWorkbenchPart part, int type,
         boolean isOrganizationType, String label, Object selectedObject)
   {
      super(part);
      this.editor = (WorkflowModelEditor) part;
      setId(DiagramActionConstants.CREATE_CONNECTION);
      setText(label);
      this.type = type;
      this.selectedObject = selectedObject;
      this.isOrganizationType = isOrganizationType;
      initAction();
   }

   private void initAction()
   {
      EClass eClass = null;
      boolean isModelElementSymbolEditPart = selectedObject instanceof AbstractModelElementNodeSymbolEditPart;

      if (isModelElementSymbolEditPart)
      {
         Object model = ((AbstractNodeSymbolEditPart) selectedObject).getModel();
         if (!isOrganizationType)
         {
            if (((INodeSymbol) model).eContainer().eContainer() instanceof ProcessDefinitionType)
            {
               if (type == TRANSITION)
               {
                  eClass = CWM_PKG.getTransitionConnectionType();
                  isEnabled = model instanceof ActivitySymbolType
                        || model instanceof StartEventSymbol;
               }
               else if (type == DATA_MAPPING)
               {
                  eClass = CWM_PKG.getDataMappingConnectionType();
                  isEnabled = model instanceof ActivitySymbolType
                        || model instanceof DataSymbolType;
               }
               else if (type == EXECUTED_BY)
               {
                  eClass = CWM_PKG.getExecutedByConnectionType();
                  isEnabled = model instanceof ApplicationSymbolType;
               }
               else if (type == PERFORMED_BY)
               {
                  eClass = CWM_PKG.getPerformsConnectionType();
                  isEnabled = model instanceof IModelParticipantSymbol;
               }
            }
         }
         else
         {
            if (type == WORKS_FOR)
            {
               eClass = CWM_PKG.getWorksForConnectionType();
               isEnabled = isModelElementSymbolEditPart
                     && model instanceof RoleSymbolType;
            }
            else if (type == PART_OF)
            {
               eClass = CWM_PKG.getPartOfConnectionType();
               isEnabled = isModelElementSymbolEditPart
                     && model instanceof OrganizationSymbolType;
            }
         }
      }
      setImageDescriptor(DiagramPlugin.getImageDescriptor(editor.getIconFactory().getIconFor(eClass)));
   }

   protected boolean calculateEnabled()
   {
      return isEnabled;
   }

   public void run()
   {
      List paletteContainer = ((DiagramEditorPage) editor.getCurrentPage())
            .getPaletteRoot().getChildren();
      PaletteViewer paletteViewer = ((DiagramEditorPage) editor.getCurrentPage())
            .getPaletteViewer();
      PaletteDrawer drawer = (PaletteDrawer) paletteContainer
            .get(WorkflowModelEditorPaletteFactory.CONNECTION_CONTAINER);
      List drawerEntries = drawer.getChildren();
      Object obj = isOrganizationType ? ((PaletteContainer) drawerEntries.get(drawer
            .getChildren().size() > PROCESS_ORGANIZATION_CONTAINER
            ? PROCESS_ORGANIZATION_CONTAINER
            : MODEL_ORGANIZATION_CONTAINER)).getChildren().get(type) : drawerEntries
            .get(type);
      paletteViewer.setActiveTool((ToolEntry) obj);
      performMouseDownEvent();
   }

   private void performMouseDownEvent()
   {
      DiagramEditorPage diagramPage = (DiagramEditorPage) editor.getCurrentPage();
      Canvas canvas = (Canvas) (diagramPage).getGraphicalViewer().getControl();
      Event event = new Event();
      event.button = 1;
      event.count = 0;
      event.detail = 0;
      event.end = 0;
      event.height = 0;
      event.keyCode = 0;
      event.start = 0;
      event.stateMask = 0;
      event.time = 9516624;
      event.type = 3;
      event.widget = canvas;
      event.width = 0;
      event.x = (diagramPage).getMouseLocation().x;
      event.y = (diagramPage).getMouseLocation().y;
      canvas.notifyListeners(3, event);
   }

}
