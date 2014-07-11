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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.stardust.model.xpdl.carnot.DataSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.DataType;
import org.eclipse.stardust.model.xpdl.carnot.util.DiagramUtil;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AnnotationSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.DiagramRootEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.GenericLinkConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.PoolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.RefersToConnectionEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.SymbolGroupEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.TextSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.core.utils.CheckDeleteConnections;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.ui.IWorkbenchPart;

public class DeleteAllAction extends DeleteAction
{
   public DeleteAllAction(IWorkbenchPart part)
   {
      super(part);
      setText(Diagram_Messages.TXT_DeleteAll);
      setToolTipText(Diagram_Messages.TXT_DeleteAll);
      setLazyEnablementCalculation(true);
   }

   @SuppressWarnings("unchecked")
   protected boolean calculateEnabled()
   {
      return super.calculateEnabled()
         && !getSelectedObjects().isEmpty()
         // check all selected objects
         && isValidSelection(getSelectedObjects());
   }

   // should be enhanced
   private boolean isValidSelection(List<Object> selectedObjects)
   {
      for(Object object : selectedObjects)
      {
         if(object instanceof AnnotationSymbolEditPart
               || object instanceof TextSymbolEditPart
               || object instanceof SymbolGroupEditPart
               || object instanceof ChildCategoryNode
               || object instanceof RefersToConnectionEditPart
               || object instanceof GenericLinkConnectionEditPart
               || object instanceof ModelTreeEditPart
               || object instanceof DiagramRootEditPart
               || object instanceof DiagramEditPart
               || isPredefinedData(object)
               || object instanceof EditPart && ((EditPart) object).getModel() instanceof IObjectDescriptor)
         {
            return false;
         }
         else if(object instanceof PoolEditPart)
         {
            if(DiagramUtil.isDefaultPool(((PoolEditPart) object).getPoolModel()))
            {
               return false;
            }
         }
      }
      return true;
   }

   private boolean isPredefinedData(Object obj)
   {
      if (obj instanceof EditPart)
      {
         if (((EditPart) obj).getModel() instanceof DataType)
         {
            return ((DataType) ((EditPart) obj).getModel()).isPredefined();
         }
         else if (((EditPart) obj).getModel() instanceof DataSymbolType)
         {
            DataType data = ((DataSymbolType) ((EditPart) obj).getModel()).getData();
            return data != null
                  && ((DataSymbolType) ((EditPart) obj).getModel()).getData()
                        .isPredefined();
         }
      }
      return false;
   }

   public void run()
   {
      // 1st check for connections and show a dialog to select from (or cancel)
      // if there is more than one connection
      if(getSelectedObjects().size() == 1)
      {
         // check and maybe set (delete) another connection
         // if user pressed CANCEL when having more than one connection (dialog opens) then return
         WorkflowModelEditor editor = (WorkflowModelEditor) getWorkbenchPart();
         if(!CheckDeleteConnections.checkConnections(getSelectedObjects(), editor, false))
         {
            return;
         }
      }
      if (MessageDialog.openConfirm(null, Diagram_Messages.MSG_ConfirmDelete,
            Diagram_Messages.MSG_SureToDeleteElement))
      {
         super.run();
      }
   }
}