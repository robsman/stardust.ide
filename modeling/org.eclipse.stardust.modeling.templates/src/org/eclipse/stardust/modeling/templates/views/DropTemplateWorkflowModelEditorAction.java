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
package org.eclipse.stardust.modeling.templates.views;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.ActivityImplementationType;
import org.eclipse.stardust.model.xpdl.carnot.ActivitySymbolType;
import org.eclipse.stardust.model.xpdl.carnot.ActivityType;
import org.eclipse.stardust.model.xpdl.carnot.DiagramType;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.ProcessDefinitionType;
import org.eclipse.stardust.model.xpdl.util.ModelOidUtil;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.IDiagramChangeListener;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegatingCommand;
import org.eclipse.stardust.modeling.core.editors.parts.dialog.ApplyUpdatesCommand;
import org.eclipse.stardust.modeling.core.modelserver.ModelServerUtils;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.stardust.modeling.repository.common.ImportCancelledException;
import org.eclipse.stardust.modeling.templates.adapters.TemplateContentAdapter;
import org.eclipse.stardust.modeling.templates.spi.ITemplate;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;



public class DropTemplateWorkflowModelEditorAction extends WorkflowModelEditorAction implements IDiagramChangeListener
{
   private Map dropListeners = new HashMap();

   public static boolean isValidDndSelection(ISelection selection)
   {
      if (selection instanceof IStructuredSelection)
      {
         IStructuredSelection structuredSelection = (IStructuredSelection) selection;
         if (structuredSelection.size() == 1 && structuredSelection.getFirstElement() instanceof ITemplate)
         {
            return true;
         }
      }
      return false;
   }

   protected void register(WorkflowModelEditor part)
   {
      part.addDiagramChangeListener(this);
      DiagramEditorPage[] editors = part.getEditors();
      for (int i = 0; i < editors.length; i++)
      {
         diagramPageOpened(editors[i]);
      }
   }

   protected void unregister(WorkflowModelEditor part)
   {
      part.removeDiagramChangeListener(this);
      DiagramEditorPage[] editors = part.getEditors();
      for (int i = 0; i < editors.length; i++)
      {
         diagramPageClosed(editors[i]);
      }
   }

   public void diagramPageChanged(DiagramEditorPage page)
   {
      // (fh) ignore
   }

   public void diagramPageClosed(DiagramEditorPage page)
   {
      TransferDropTargetListener dropTargetListener = (TransferDropTargetListener) dropListeners.get(page);
      if (dropTargetListener != null)
      {
         page.getGraphicalViewer().removeDropTargetListener(dropTargetListener);
      }
   }

   public void diagramPageOpened(final DiagramEditorPage page)
   {
      TransferDropTargetListener dropTargetListener = new TransferDropTargetListener()
      {
         public Transfer getTransfer()
         {
            return LocalSelectionTransfer.getTransfer();
         }

         public boolean isEnabled(DropTargetEvent event)
         {
            Point location = getAbsoluteLocation(page.getGraphicalViewer().getControl());
            WorkflowModelEditor editor = page.getWorkflowModelEditor();
            EditPart editPart = page.getGraphicalViewer().findObjectAt(new org.eclipse.draw2d.geometry.Point(event.x - location.x,event.y - location.y));
            EditPart targetEditPart = GenericUtils.isValidTargetEditPart(editPart);
            if (targetEditPart == null) {
               return false;
            }
            ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
            boolean enabled = isValidDndSelection(selection);
            if (enabled)
            {
               event.detail = DND.DROP_COPY;
            }
            DiagramType targetDiagram = page.getDiagram();
            if (targetDiagram == null || !(targetDiagram.eContainer() instanceof ProcessDefinitionType)) {
               return false;
            }
            return enabled;
         }

         public void dragEnter(DropTargetEvent event)
         {
            // (fh) ignore
         }

         public void dragLeave(DropTargetEvent event)
         {
            // (fh) ignore
         }

         public void dragOperationChanged(DropTargetEvent event)
         {
            // (fh) ignore
         }

         public void dragOver(DropTargetEvent event)
         {
            // (fh) ignore
         }

         public void drop(DropTargetEvent event)
         {
            WorkflowModelEditor editor = page.getWorkflowModelEditor();
            DiagramType targetDiagram = page.getDiagram();

            if (editor.getModelServer().requireLock(targetDiagram))
            {
               ModelServerUtils.showMessageBox(Diagram_Messages.MSG_LOCK_NEEDED);
               return;
            }

            ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
            ITemplate template = (ITemplate) ((IStructuredSelection) selection).getFirstElement();
            Point location = getAbsoluteLocation(page.getGraphicalViewer().getControl());
            final EditPart editPart = page.getGraphicalViewer().findObjectAt(new org.eclipse.draw2d.geometry.Point(event.x - location.x,event.y - location.y));
            ModelType targetModel = editor.getWorkflowModel();
            TemplateContentAdapter contentAdapter = new TemplateContentAdapter(targetModel, template);
            targetModel.eAdapters().add(contentAdapter);
            ChangeRecorder recorder = new ChangeRecorder(targetModel);
            ModelOidUtil modelOidUtil = editor.getModelManager().getModelOidUtil();

            try
            {
               template.applyTemplate(editor, targetModel, targetDiagram, editPart, event.x - location.x, event.y - location.y);
               ApplyUpdatesCommand command = new ApplyUpdatesCommand(recorder.endRecording());
               editor.getEditDomain().getCommandStack().execute(command);
               CompoundCommand reorderCmd = null;
               if (editPart instanceof AbstractSwimlaneEditPart) {

                  LaneEditPart laneEditPart = (LaneEditPart) editPart;
                  if (!laneEditPart.getLaneModel().getActivitySymbol().isEmpty())
                  {
                     for (Iterator<ActivitySymbolType> i = laneEditPart.getLaneModel()
                           .getActivitySymbol().iterator(); i.hasNext();)
                     {
                        ActivitySymbolType activitySymbolType = i.next();
                        ActivityType activityType = (ActivityType) activitySymbolType
                              .getModelElement();
                        if (activityType.getImplementation().equals(
                              ActivityImplementationType.MANUAL_LITERAL))
                        {
                           activityType.setPerformer(laneEditPart.getLaneModel()
                                 .getParticipantReference());
                        }
                     }
                  }

                  reorderCmd = new CompoundCommand();
                  reorderCmd.add(new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        return PoolLaneUtils.resizeLane((AbstractSwimlaneEditPart) editPart);                     }
                     });
                  reorderCmd.add(new DelegatingCommand()
                  {
                     public Command createDelegate()
                     {
                        return PoolLaneUtils.reorderLanes((AbstractSwimlaneEditPart) editPart, new Integer(PoolLaneUtils.CHILD_LANES_MAXSIZE));
                     }
                  });
                  editor.getEditDomain().getCommandStack().execute(reorderCmd);
               }
               editor.selectSymbols(contentAdapter.getAddedSymbols(), targetDiagram);
               DiagramEditorPage diagramEditorPage = (DiagramEditorPage) editor.getCurrentPage();
               diagramEditorPage.setFocus();
               INodeSymbol lastSymbol = (INodeSymbol) contentAdapter.getAddedSymbols().get(0);
               diagramEditorPage.setMouseLocation(new org.eclipse.draw2d.geometry.Point(lastSymbol.getXPos(), lastSymbol.getYPos()));
            }
            catch (ImportCancelledException ice)
            {
               ApplyUpdatesCommand command = new ApplyUpdatesCommand(recorder.endRecording());
               editor.getEditDomain().getCommandStack().execute(command);
               editor.getEditDomain().getCommandStack().undo();
            }
            finally
            {
               targetModel.eAdapters().remove(contentAdapter);
               recorder.dispose();

            }
         }

         private Point getAbsoluteLocation(Control control)
         {
            Point location = control.getLocation();
            if (control.getParent() != null)
            {
               Point parentLocation = getAbsoluteLocation(control.getParent());
               location.x += parentLocation.x;
               location.y += parentLocation.y;
            }
            return location;
         }

         public void dropAccept(DropTargetEvent event)
         {
            // (fh) ignore
         }
      };
      dropListeners.put(page, dropTargetListener);
      page.getGraphicalViewer().addDropTargetListener(dropTargetListener);
   }
}