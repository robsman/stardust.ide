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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.GenericLinkConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.ui.IWorkbenchPart;


public class ShowInOutlineAction extends SelectionAction
{
   private WorkflowModelEditor editor;

   public ShowInOutlineAction(IWorkbenchPart part)
   {
      super(part);
      this.editor = (WorkflowModelEditor) part;
      setText(Diagram_Messages.LB_ShowInOutline);
      setToolTipText(Diagram_Messages.LB_ShowInOutline);
      setId(DiagramActionConstants.SHOW_IN_OUTLINE);
   }

   protected boolean calculateEnabled()
   {      
      if(editor.getOutlinePage().getControl() == null)
      {
         return false;
      }
      List selection = getSelectedObjects();
      if(selection.size() != 1)
      {
         return false;
      }
      Object element = getSelectedObjects().get(0);
      // must be diagram
      if(element instanceof AbstractModelElementNodeSymbolEditPart
            || element instanceof AbstractConnectionSymbolEditPart)
      {
         if(element instanceof AbstractConnectionSymbolEditPart)
         {
            IConnectionSymbol symbol = (IConnectionSymbol) ((AbstractConnectionSymbolEditPart) element).getModel();
            if(symbol instanceof TransitionConnectionType
                  && ((TransitionConnectionType) symbol).getTransition() != null)
            {
                  return true;
            }
            else if(symbol instanceof GenericLinkConnectionType
                  && ((GenericLinkConnectionType) symbol).getLinkType() != null)
            {
               return true;
            }
         }
         else
         {
            INodeSymbol symbol = (INodeSymbol) ((AbstractModelElementNodeSymbolEditPart) element).getModel();            
            if(symbol instanceof IModelElementNodeSymbol
                  && ((IModelElementNodeSymbol) symbol).getModelElement() != null)
            {
               return true;
            }
         }
      }
      return false;
   }
   
   public void run()
   {
      Object element = getSelectedObjects().get(0);      
      EObject select = null;
      if(element instanceof AbstractConnectionSymbolEditPart)
      {
         IConnectionSymbol symbol = (IConnectionSymbol) ((AbstractConnectionSymbolEditPart) element).getModel();
         if(symbol instanceof TransitionConnectionType)
         {
            select = ((TransitionConnectionType) symbol).getTransition();
         }
         else if(symbol instanceof GenericLinkConnectionType)
         {
            select = ((GenericLinkConnectionType) symbol).getLinkType();
         }
      }
      else
      {
         INodeSymbol symbol = (INodeSymbol) ((AbstractModelElementNodeSymbolEditPart) element).getModel();            
         if(symbol instanceof IModelElementNodeSymbol)
         {
            select = ((IModelElementNodeSymbol) symbol).getModelElement();
         }
      }
      editor.selectInOutline(select);
   }   
}