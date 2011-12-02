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

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.DiagramEditorPage;
import org.eclipse.stardust.modeling.core.editors.DynamicConnectionCommand;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.tools.CarnotConnectionCreationTool;


public class ConnectAction extends SelectionAction
{
   public static final String CONNECT_LABEL = Diagram_Messages.ConnectAction_Label;
   public static final String CONNECT_TOOLTIP = Diagram_Messages.ConnectAction_ToolTip;

   public ConnectAction(WorkflowModelEditor editor)
   {
      super(editor);
      initUI();
   }

   protected boolean calculateEnabled()
   {
      INodeSymbol source = extractSymbol(0);
      if (source != null)
      {
         INodeSymbol target = extractSymbol(1);
         DynamicConnectionCommand cmd = new DynamicConnectionCommand(null);
         cmd.setSourceSymbol(source);
         cmd.setTargetSymbol(target);
         return cmd.canExecute();
      }
      return false;
   }

   private INodeSymbol extractSymbol(int i)
   {
      List selection = getSelectedObjects();
      if (selection.size() > 0 && selection.size() < 3 && i < selection.size())
      {
         Object item = selection.get(i);
         if (item instanceof EditPart)
         {
            EditPart part = (EditPart) item;
            Object model = part.getModel();
            if (model instanceof INodeSymbol)
            {
               return (INodeSymbol) model;
            }
         }
      }
      return null;
   }

   public void run()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();

      INodeSymbol source = extractSymbol(0);
      INodeSymbol target = extractSymbol(1);
      if (target == null)
      {
         DiagramEditorPage page = (DiagramEditorPage) editor.getCurrentPage();
         PaletteRoot palette = page.getPaletteRoot();
         ConnectionCreationToolEntry entry = (ConnectionCreationToolEntry)
            findToolEntry(palette, DiagramActionConstants.CONNECT);
         if (entry != null)
         {
            page.getPaletteViewer().setActiveTool(entry);
            Tool tool = page.getEditDomain().getActiveTool();
            if (tool instanceof CarnotConnectionCreationTool)
            {
               CarnotConnectionCreationTool cct = (CarnotConnectionCreationTool) tool;
               List selection = getSelectedObjects();
               AbstractNodeSymbolEditPart part = (AbstractNodeSymbolEditPart) selection.get(0);
               cct.startConnection(part);
            }
         }
      }
      else
      {
         DynamicConnectionCommand cmd = new DynamicConnectionCommand(editor);
         cmd.setSourceSymbol(source);
         cmd.setTargetSymbol(target);
         execute(cmd);
      }
   }

   private ConnectionCreationToolEntry findToolEntry(PaletteContainer palette, String name)
   {
      for (Iterator i = palette.getChildren().iterator(); i.hasNext();)
      {
         PaletteEntry entry = (PaletteEntry) i.next();
         if (entry instanceof ConnectionCreationToolEntry
               && DiagramActionConstants.CONNECT.equals(entry.getId()))
         {
            return (ConnectionCreationToolEntry) entry;
         }
         if (entry instanceof PaletteContainer)
         {
            ConnectionCreationToolEntry toolEntry = findToolEntry((PaletteContainer) entry, name); 
            if (toolEntry != null)
            {
               return toolEntry;
            }
         }
      }
      return null;
   }

   protected void initUI()
   {
      setId(DiagramActionConstants.CONNECT);
      setText(CONNECT_LABEL);
      setToolTipText(CONNECT_TOOLTIP);
      setImageDescriptor(DiagramPlugin.getImageDescriptor(
            "icons/full/obj16/connection.gif")); //$NON-NLS-1$
      setAccelerator('C');
   }
}
