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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.stardust.model.xpdl.carnot.AnnotationSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.EndEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.StartEventSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TextSymbolType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.SearchAction;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.actions.ShowPropertiesAction;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.WorkflowModelTreeEditPartFactory;
import org.eclipse.ui.actions.ActionFactory;


public class WorkflowModelOutlinePage extends AbstractMultiPageGraphicalEditorOutlinePage
      implements IValidationEventListener
{
   public WorkflowModelOutlinePage(WorkflowModelEditor editor)
   {
      super(editor, new TreeViewer());
   }

   public ActionRegistry getActionRegistry()
   {
      return super.getActionRegistry();
   }

   protected EditPartFactory createOutlineEditPartFactory()
   {
      return new WorkflowModelTreeEditPartFactory((WorkflowModelEditor) getEditor());
   }

   protected void contributeToolBarActions(IToolBarManager tbm)
   {
      // todo: (fh) add here filters ?
      IAction searchAction = getActionRegistry().getAction(DiagramActionConstants.SEARCH);
      tbm.add(searchAction);
   }

   protected void createActions()
   {
      ShowPropertiesAction propDlgAction = new ShowPropertiesAction(
            (WorkflowModelEditor) getEditor(), getSite().getSelectionProvider());
      propDlgAction.setId(ActionFactory.PROPERTIES.getId());
      getActionRegistry().registerAction(propDlgAction);
      SearchAction searchAction = new SearchAction(getEditor());
      getActionRegistry().registerAction(searchAction);
      /*
      ReferencesSearchAction referencesSearchAction = new ReferencesSearchAction(
            (WorkflowModelEditor) getEditor());
      getActionRegistry().registerAction(referencesSearchAction);
      */
   }

   protected EditPartViewer getViewer()
   {
      return super.getViewer();
   }

   public void onIssuesUpdated(EObject element, IValidationStatus validationStatus)
   {
      Object editPart = getViewer().getEditPartRegistry().get(element);
      if (editPart instanceof AbstractEObjectTreeEditPart)
      {
         boolean hasWarnings = !validationStatus.getWarnings().isEmpty()
               || !validationStatus.getChildrenWithWarnings().isEmpty();
         boolean hasErrors = !validationStatus.getErrors().isEmpty()
               || !validationStatus.getChildrenWithErrors().isEmpty();

         AbstractEObjectTreeEditPart treePart = (AbstractEObjectTreeEditPart) editPart;
         treePart.setState(hasErrors
               ? AbstractEObjectTreeEditPart.STATE_ERRORS
               : hasWarnings
                     ? AbstractEObjectTreeEditPart.STATE_WARNINGS
                     : AbstractEObjectTreeEditPart.STATE_OK);
      }
   }

   public void selectElement(EObject modelElement)
   {
      Object editPart = getEditPart(modelElement);
      if (editPart instanceof AbstractEObjectTreeEditPart)
      {
         selectEditPart(editPart);
      }
      else
      {
         editPart = getEditPart(modelElement.eContainer());
         if (editPart instanceof AbstractEObjectTreeEditPart)
         {
            selectEditPart(editPart);
         }
      }
   }

   public EditPart getEditPart(EObject modelElement)
   {
      return (EditPart) getViewer().getEditPartRegistry().get(modelElement);
   }
   
   private void selectEditPart(Object editPart)
   {
      getSite().getSelectionProvider().setSelection(new StructuredSelection(editPart));
      setFocus();
   }

   protected boolean canDelete(ISelection selection)
   {
      Object selObj = null;
      if (selection instanceof IStructuredSelection)
      {
         selObj = ((IStructuredSelection) selection).getFirstElement();

         if (selObj instanceof EditPart)
         {
            Object element = ((EditPart) selObj).getModel();
            boolean isTransition = !(element instanceof TransitionConnectionType && ((TransitionConnectionType) element)
                  .getTransition() == null);
            boolean isNoGatewaySymbol = !(element instanceof GatewaySymbol);
            boolean isNoTextSymbol = !(element instanceof TextSymbolType);
            boolean isNoAnnotationSymbol = !(element instanceof AnnotationSymbolType);
            boolean isNoStartEventSymbol = !(element instanceof StartEventSymbol)
                  || ((StartEventSymbol) element).getTrigger() != null;
            boolean isNoEndEventSymbol = !(element instanceof EndEventSymbol);

            return isTransition && isNoGatewaySymbol && isNoTextSymbol
                  && isNoAnnotationSymbol && isNoStartEventSymbol && isNoEndEventSymbol;
         }
      }
      return false;
   }
}