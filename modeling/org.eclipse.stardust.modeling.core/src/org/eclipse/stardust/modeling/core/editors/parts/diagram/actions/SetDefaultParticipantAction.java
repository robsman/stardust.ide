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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IModelParticipant;
import org.eclipse.stardust.model.xpdl.carnot.LaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.properties.DefaultPropSheetCmdFactory;
import org.eclipse.stardust.modeling.core.utils.HierarchyUtils;
import org.eclipse.stardust.modeling.core.utils.IdentifiableViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SetDefaultParticipantAction extends SelectionAction
{
   public SetDefaultParticipantAction(WorkflowModelEditor editor)
   {
      super(editor);
   }

   protected boolean calculateEnabled()
   {
      if (getSelectedObjects().size() == 1)
      {
         LaneSymbol lane = getLane();
         if(lane != null && HierarchyUtils.hasChildLanesParticipant(lane)) return false;
         return lane != null && getParticipants() != null;
      }
      return false;
   }

   public void run()
   {
      IModelParticipant[] participant = new IModelParticipant[1];
      Dialog dialog = createDialog(participant);
      if (dialog.open() == Dialog.OK)
      {
         WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
         DiagramEditorPage page = (editor.getCurrentPage() instanceof DiagramEditorPage)
               ? (DiagramEditorPage) editor.getCurrentPage()
               : null;
         if (null != page)
         {
            Command cmd = DefaultPropSheetCmdFactory.INSTANCE.getSetCommand(page
                  .findEditPart(getLane()), getLane(),
                  CarnotWorkflowModelPackage.eINSTANCE.getISwimlaneSymbol_Participant(),
                  participant[0]);
            execute(cmd);
         }
      }
   }

   private Dialog createDialog(final IModelParticipant[] participant)
   {
      return new Dialog(getWorkbenchPart().getSite().getShell())
      {
         protected Control createDialogArea(Composite parent)
         {
            getShell().setText(Diagram_Messages.TXT_DefaultLaneParticipant);
            Composite composite = (Composite) super.createDialogArea(parent);
            createContent(composite, participant);
            return composite;
         }
      };
   }

   private void createContent(Composite composite, final IModelParticipant[] participant)
   {
      Table table = new Table(composite, SWT.BORDER);
      GridData gd = new GridData(GridData.FILL_BOTH);
      gd.minimumWidth = 350;
      gd.minimumHeight = 250;
      table.setLayoutData(gd);
      final TableViewer viewer = new TableViewer(table);
      viewer.setContentProvider(new ArrayContentProvider());
      viewer.setLabelProvider(new LabelProvider()
      {
         public String getText(Object element)
         {
            IModelParticipant participant = (IModelParticipant) element;
            return participant.getName();
         }
         
         public Image getImage(Object element)
         {
            return DiagramPlugin.getImage(((WorkflowModelEditor) getWorkbenchPart()).getIconFactory().getIconFor((IModelParticipant) element));
         }
      });
      viewer.setSorter(new IdentifiableViewerSorter());
      viewer.setInput(getParticipants());
      table.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            participant[0] = (IModelParticipant)
               ((IStructuredSelection) viewer.getSelection()).getFirstElement();
         }
      });
      LaneSymbol lane = getLane();
      if (lane.getParticipant() != null)
      {
         viewer.setSelection(new StructuredSelection(lane.getParticipant()));
         participant[0] = lane.getParticipant();
      }
   }

   private ArrayList getParticipants()
   {
      LaneSymbol lane = getLane();
      ModelType model = ModelUtils.findContainingModel(lane);
      List participants = HierarchyUtils.getParticipants(lane, model);
      if(participants != null && !participants.isEmpty())
      {
         return (ArrayList) participants;
      }
      return null; 
   }

   private LaneSymbol getLane()
   {
      Object selection = getSelectedObjects().get(0);
      if (selection instanceof LaneEditPart)
      {
         return (LaneSymbol) ((LaneEditPart) selection).getModel();
      }
      return null;
   }
   
   protected void init()
   {
      super.init();
      setId(DiagramActionConstants.SET_PARTICIPANT);
      setText(Diagram_Messages.TXT_SetDefaultParticipant);
   }
}