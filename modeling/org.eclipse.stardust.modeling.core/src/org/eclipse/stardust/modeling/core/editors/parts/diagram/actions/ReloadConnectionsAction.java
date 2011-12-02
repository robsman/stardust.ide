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

import java.util.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ISymbolContainer;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SymbolGroupEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DelegateCommand;
import org.eclipse.stardust.modeling.core.editors.ui.TreeNode;
import org.eclipse.stardust.modeling.core.editors.ui.TreeNodeContentProvider;
import org.eclipse.stardust.modeling.core.modelserver.ModelServer;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;


/**
 * @author fherinean
 * @version $Revision$
 */
public class ReloadConnectionsAction extends SelectionAction
{
   public static final String REQ_RELOAD_CONNECTIONS = DiagramActionConstants.RELOAD_CONNECTIONS;
   public static final String RELOAD_CONNECTIONS_LABEL = Diagram_Messages.RELOAD_CONNECTIONS_LABEL;

   public ReloadConnectionsAction(WorkflowModelEditor editor)
   {
      super(editor);
      setId(DiagramActionConstants.RELOAD_CONNECTIONS);
      setText(RELOAD_CONNECTIONS_LABEL);
   }

   public boolean isEnabled()
   {
      update();
      return super.isEnabled();
   }

   protected boolean calculateEnabled()
   {
      Command cmd = createReloadCommand();
      if (cmd == null)
      {
         return false;
      }
      return cmd.canExecute();
   }

   public void run()
   {
      CompoundCommand cmd = createReloadCommand();
      if (!cmd.isEmpty())
      {
         Map<Object, Boolean> selection = new HashMap<Object, Boolean>();
         Dialog dialog = createDialog(cmd, selection);
         if (dialog.open() == Dialog.OK && filter(cmd, selection))
         {
            execute(cmd);
         }
         PoolLaneUtils.refreshLaneContent();         
      }
   }

   public void runEmbedded(DelegateCommand compound)
   {
      CompoundCommand cmd = createReloadCommand();
      if (!cmd.isEmpty())
      {
         Map<Object, Boolean> selection = new HashMap<Object, Boolean>();
         Dialog dialog = createDialog(cmd, selection);
         if (dialog.open() == Dialog.OK && filter(cmd, selection))
         {
            compound.setDelegate(cmd);
         }
         PoolLaneUtils.refreshLaneContent();
      }
   }

   /**
    * removes all commands disabled via checkbox 
    * 
    * @param command The current command.
    * @param selection The checkbox selection from the Dialog.
    * 
    * @return
    */
   private boolean filter(CompoundCommand command, Map<Object, Boolean> selection)
   {
      List<Command> commands = command.getCommands();
      Iterator<Command> itr = commands.iterator();
      while (itr.hasNext())
      {
         Command cmd = itr.next();
         Boolean status = (Boolean) selection.get(cmd);
         if (cmd instanceof CompoundCommand)
         {
            CompoundCommand compound = (CompoundCommand) cmd;
            filter(compound, selection);
            if (compound.isEmpty())
            {
               itr.remove();
            }
         }
         else if (status != null && !status.booleanValue())
         {
            itr.remove();            
         }
      }
      return !command.isEmpty();
   }
   
   private Dialog createDialog(final Command cmd, final Map<Object, Boolean> selection)
   {
      return new Dialog(getWorkbenchPart().getSite().getShell())
      {
         protected Control createDialogArea(Composite parent)
         {
            getShell().setText(Diagram_Messages.TXT_AvailableConnections);
            Composite composite = (Composite) super.createDialogArea(parent);
            Tree tree = new Tree(composite, SWT.CHECK | SWT.BORDER);
            GridData gd = new GridData(GridData.FILL_BOTH);
            gd.minimumWidth = 450;
            gd.minimumHeight = 250;
            tree.setLayoutData(gd);
            TreeViewer viewer = new TreeViewer(tree);
            viewer.setContentProvider(new TreeNodeContentProvider());
            viewer.setLabelProvider(new LabelProvider()
            {
               public String getText(Object element)
               {
                  TreeNode node = (TreeNode) element;
                  Command cmd = (Command) node.getValue();
                  if(cmd == null)
                  {
                     return Diagram_Messages.TXT_DIAGRAM;
                  }
                  return cmd.getLabel();
               }
            });
            viewer.setInput(getNode(cmd, 0).getChildren());
            viewer.expandAll();
            checkAll(tree.getItems(), selection, true);
            tree.addSelectionListener(new SelectionAdapter()
            {
               public void widgetSelected(SelectionEvent e)
               {
                  if (e.detail == SWT.CHECK)
                  {
                     TreeItem item = (TreeItem) e.item;
                     boolean checked = item.getChecked();
                     setState(item, selection, checked);
                     validate();
                  }
               }
            });
            validate();
            return composite;
         }

         /**
          * enable OK button only if we have a valid selection
          */
         private void validate()
         {            
            boolean isValid = filter(createDuplicate((CompoundCommand) cmd), selection);
            
            Button okButton = getButton(IDialogConstants.OK_ID);
            if (okButton != null)
            {
               okButton.setEnabled(isValid);
            }
         }   
         
         /**
          * create a copy of this command because filter modifies the command
          * 
          * @param cmd
          * @return
          */
         private CompoundCommand createDuplicate(CompoundCommand cmd)
         {
            CompoundCommand command = new CompoundCommand();
                        
            List<Command> commands = cmd.getCommands();
            Iterator<Command> itr = commands.iterator();
            while (itr.hasNext())
            {
               Command cmd_ = itr.next();
               if (cmd_ instanceof CompoundCommand)
               {
                  command.add(createDuplicate((CompoundCommand) cmd_));
               }
               else
               {
                  command.add(cmd_);
               }
            }            
            return command;
         }         
      };
   }

   /**
    * modify selection map so it reflects the state of the checkboxes  
    * 
    * @param item
    * @param selection
    * @param checked
    */
   private void setState(TreeItem item, final Map<Object, Boolean> selection, boolean checked)
   {
      TreeNode node = (TreeNode) item.getData();
      if(node.getValue() != null)
      {
         selection.put(node.getValue(), checked ? Boolean.TRUE : Boolean.FALSE);
      }
      checkAll(item.getItems(), selection, checked);
   }

   /**
    * check all children of this tree item and update selection map
    * 
    * @param items
    * @param selection
    * @param checked
    */
   private void checkAll(TreeItem[] items, Map<Object, Boolean> selection, boolean checked)
   {
      if (items != null)
      {
         for (int i = 0; i < items.length; i++)
         {
            TreeItem item = items[i];
            item.setChecked(checked);
            setState(item, selection, checked);
         }
      }
   }

   /**
    * create tree hierarchy for dialog
    * 
    * @param cmd
    * @param rootCnt
    * @return
    */
   private TreeNode getNode(Command cmd, int rootCnt)
   {
      rootCnt++;
      
      // 1st node will not be shown
      TreeNode node = new TreeNode(rootCnt <= 2 && cmd instanceof CompoundCommand ? null : cmd);
      if (cmd instanceof CompoundCommand)
      {
         CompoundCommand comp = (CompoundCommand) cmd;
         List<Command> cmds = comp.getCommands();         
         TreeNode[] children = new TreeNode[cmds.size()];
         for (int i = 0; i < cmds.size(); i++)
         {
            Command command = (Command) cmds.get(i);
            children[i] = getNode(command, rootCnt);
            children[i].setParent(node);
         }
         node.setChildren(children);
      }
      return node;
   }

   // create command and filter out commands not necessary
   
   public CompoundCommand createReloadCommand()
   {
      ReloadConnectionsRequest request = new ReloadConnectionsRequest();
      List<EditPart> editparts = getOperationSet(request);
      if (editparts.isEmpty())
      {
         return null;
      }

      WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
      ModelServer server = editor.getModelServer();
      
      CompoundCommand command = new CompoundCommand();
      command.setDebugLabel(getText());
      for (int i = 0; i < editparts.size(); i++)
      {
         EditPart part = (EditPart) editparts.get(i);
         Object model = part.getModel();
         if (server != null && model instanceof EObject && server.requireLock((EObject) model))
         {
            return null;
         }
         command.add(part.getCommand(request));
      }
      filterEmptyCommandsAndDuplicates(new HashSet<Command>(), command);
      return command;
   }

   private void filterEmptyCommandsAndDuplicates(Set<Command> existingCommands,
         CompoundCommand command)
   {
      List<Command> commands = command.getCommands();
      Iterator<Command> itr = commands.iterator();
      while (itr.hasNext())
      {
         Command cmd = itr.next();
         if (cmd instanceof CompoundCommand)
         {
            CompoundCommand compound = (CompoundCommand) cmd;
            filterEmptyCommandsAndDuplicates(existingCommands, compound);
            if (compound.isEmpty())
            {
               itr.remove();
            }
         }
         else if (existingCommands.contains(cmd))
         {
            itr.remove();
         }
         else
         {
            existingCommands.add(cmd);
         }
      }
   }

   protected List<EditPart> getOperationSet(Request request)
   {
      List<EditPart> editparts = new ArrayList<EditPart>(getSelectedObjects());
      filterNonGraphicalEditParts(editparts);
      List<EditPart> containers;
      
      while (!(containers = filterContainers(editparts)).isEmpty())
      {
         for (int i = 0; i < containers.size(); i++)
         {
            editparts.addAll(((EditPart) containers.get(i)).getChildren());
         }
      }
      DiagramRootEditPart rootEditPart = null;
      for (Iterator<EditPart> iter = editparts.iterator(); iter.hasNext();)
      {
         EditPart editPart = (EditPart) iter.next();
         if (editPart instanceof DiagramRootEditPart)
         {
            rootEditPart = (DiagramRootEditPart) editPart;
         }
      }
      ToolUtilities.filterEditPartsUnderstanding(editparts, request);
      if (rootEditPart != null)
      {
         editparts.add(rootEditPart);
      }
      return editparts;
   }

   private List<EditPart> filterContainers(List<EditPart> editparts)
   {
      List<EditPart> containers = new ArrayList<EditPart>();
      Iterator<EditPart> iter = editparts.iterator();
      while (iter.hasNext())
      {
         EditPart ep = (EditPart) iter.next();
         if (ep.getModel() instanceof ISymbolContainer)
         {
            iter.remove();
            containers.add(ep);
         }
      }
      return containers;
   }

   private void filterNonGraphicalEditParts(List<EditPart> editparts)
   {
      Iterator<EditPart> iter = editparts.iterator();
      while (iter.hasNext())
      {
         Object next = iter.next();
         if (!(next instanceof GraphicalEditPart)
               || next instanceof SymbolGroupEditPart)
         {
            iter.remove();
         }
      }
   }
}