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
package org.eclipse.stardust.modeling.core.createUtils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.common.projectnature.BpmProjectNature;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelOutlinePage;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.utils.GenericUtils;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;


public class CreationUtils
{
   // open editor defined in properties
   public static void showInDiagramAndEdit(INodeSymbol symbol)
   {      
      if(symbol instanceof IModelElementNodeSymbol)
      {
         IIdentifiableModelElement model = ((IModelElementNodeSymbol) symbol).getModelElement();
         if(model != null)
         {            
            WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.
               getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

            refreshTreeItem(model);

            String focusMode = PlatformUI.getPreferenceStore().getString(
                  BpmProjectNature.PREFERENCE_FOCUS_MODE);
            // do nothing, default (will select the new element)
            if(focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_ELEMENT))
            {
               return;
            }
            else if(focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_DIALOG))
            {
               editor.selectSymbol(symbol);    
               ActionRegistry registry = editor.getActionRegistry();
               IAction action = registry.getAction(ActionFactory.PROPERTIES.getId());
               if(action != null)
               {            
                  action.run();
               }         
            }
            else if(focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_EDITOR))
            {
               showInOutlineAndEdit(model);
            }
            return;
         }
      }      
   }
   
   // show new element in outline and open editor defined in properties
   public static void showInOutlineAndEdit(EObject element)
   {
      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.
         getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      WorkflowModelOutlinePage outlinePage = (WorkflowModelOutlinePage) editor.getOutlinePage();
      
      String focusMode = PlatformUI.getPreferenceStore().getString(
            BpmProjectNature.PREFERENCE_FOCUS_MODE);
      // do nothing, default (will select the new element)
      if(focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_ELEMENT))
      {
         editor.selectInOutline(element);    
         return;
      }
      else if(focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_DIALOG))
      {
         editor.selectInOutline(element);    
         ActionRegistry registry = outlinePage.getActionRegistry();
         IAction action = registry.getAction(ActionFactory.PROPERTIES.getId());
         if(action != null)
         {            
            action.run();
         }         
      }
      else if(focusMode.equals(BpmProjectNature.PREFERENCE_FOCUS_MODE_EDITOR))
      {
         editor.selectInOutline(element);    
         AbstractEObjectTreeEditPart treeEditPart = (AbstractEObjectTreeEditPart) outlinePage.getEditPart(element);
         treeEditPart.performRequest(new DirectEditRequest());                  
      }
   }
   
   public static void refreshTreeItem(EObject element)
   {
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel(element));
      if (editor == null)
      {
         return;
      }
      WorkflowModelOutlinePage outlinePage = (WorkflowModelOutlinePage) editor.getOutlinePage();
         
      if (outlinePage != null)
      {
         // because of SVN creates file after the label was refreshed, we refresh again
         AbstractEObjectTreeEditPart treeEditPart = (AbstractEObjectTreeEditPart) outlinePage.getEditPart(element);
         if (treeEditPart != null)
         {
            Widget widget = treeEditPart.getWidget();
            if (widget != null && !widget.isDisposed())
            {
               treeEditPart.refresh();
            }
         }   
      }      
   }   

   public static void refreshTreeItemVisuals(EObject element)
   {
      WorkflowModelEditor editor = GenericUtils.getWorkflowModelEditor(ModelUtils.findContainingModel(element));
      if(editor == null)
      {
         return;
      }
      WorkflowModelOutlinePage outlinePage = (WorkflowModelOutlinePage) editor.getOutlinePage();
         
      if (outlinePage != null)
      {
         // because of SVN creates file after the label was refreshed, we refresh again
         AbstractEObjectTreeEditPart treeEditPart = (AbstractEObjectTreeEditPart) outlinePage.getEditPart(element);
         if (treeEditPart != null)
         {
            Widget widget = treeEditPart.getWidget();
            if (widget != null && !widget.isDisposed())
            {
               treeEditPart.refreshTextAndIcon();
            }
         }   
      }      
   }   
}