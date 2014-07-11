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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.cap.CopyPasteUtil;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteSymbolCommandFactory;
import org.eclipse.swt.SWT;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

public class CutAction extends DeleteAction
{
   private Clipboard clipboard;
   private Integer isValid;

   public CutAction(IWorkbenchPart part)
   {
      super(part);
      setId(ActionFactory.CUT.getId());
      setText(Diagram_Messages.LB_CutElement);
      ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
      setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
      setToolTipText(Diagram_Messages.LB_CutElement);
      setAccelerator(SWT.CTRL | 'X');
      clipboard = Clipboard.getDefault();
   }

   protected boolean calculateEnabled()
   {
      // our selection comes from diagram OR outline
      List selection = getSelectedObjects();
      if(CopyPasteUtil.containsGateway(selection))
      {
         return false;
      }

      isValid = CopyPasteUtil.validateSelection(selection, true);
      if(isValid == null)
      {
         return false;
      }
      return true;
   }

   public void run()
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      List contentList = CopyPasteUtil.createCopySet(isValid, getSelectedObjects(), editor, false);
      clipboard.setContents(contentList);

      List symbols = new ArrayList();
      List modelSymbols = new ArrayList();
      filterSelection(symbols, modelSymbols, getSelectedObjects());

      CompoundCommand cmd = new CompoundCommand();
      if(symbols.size() == 0 && modelSymbols.size() == 0)
      {
         cmd.add(super.createDeleteCommand(getSelectedObjects()));
      }
      else
      {
         cmd.add(super.createDeleteCommand(modelSymbols));
         cmd.add(createDeleteSymbolsCommand(symbols));
      }
      execute(cmd);
   }

   public void filterSelection(List symbols, List modelSymbols, List selectionList)
   {
      for(int i = 0; i < selectionList.size(); i++)
      {
         Object entry = selectionList.get(i);
         EObject symbol = (EObject) ((AbstractEditPart) entry).getModel();
         IIdentifiableModelElement modelElement = null;

         if(symbol instanceof AnnotationSymbolType
               || symbol instanceof TextSymbolType)
         {
            symbols.add(entry);
         }
         else if(symbol instanceof IModelElementNodeSymbol)
         {
            modelElement = ((IModelElementNodeSymbol) symbol).getModelElement();
            if(modelElement != null)
            {
               modelSymbols.add(entry);
            }
            else
            {
               symbols.add(entry);
            }
         }
      }
   }

   public Command createDeleteSymbolsCommand(List objects)
   {
      CompoundCommand cmd = new CompoundCommand();
      if (objects.isEmpty())
      {
         return null;
      }
      for (Iterator iter = objects.iterator(); iter.hasNext();)
      {
         EditPart editPart = (EditPart) iter.next();
         if (editPart instanceof AbstractConnectionEditPart)
         {
            cmd.add(new DeleteConnectionSymbolCmd((IConnectionSymbol) editPart.getModel()));
         }
         else if (editPart instanceof AbstractNodeSymbolEditPart)
         {
            cmd.add(DeleteSymbolCommandFactory.createDeleteSymbolCommand((INodeSymbol) editPart.getModel()));
         }
      }
      return cmd;
   }
}