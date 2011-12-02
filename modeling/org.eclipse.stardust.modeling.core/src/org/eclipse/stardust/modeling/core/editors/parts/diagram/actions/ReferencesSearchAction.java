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
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.stardust.model.xpdl.carnot.DataPathType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IIdentifiableModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TriggerType;
import org.eclipse.stardust.model.xpdl.xpdl2.ExternalPackage;
import org.eclipse.stardust.model.xpdl.xpdl2.TypeDeclarationType;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractModelElementNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.AbstractEObjectTreeEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ChildCategoryNode;
import org.eclipse.stardust.modeling.core.editors.parts.tree.IdentifiableModelElementTreeEditPart;
import org.eclipse.stardust.modeling.core.search.ElementReferenceSearcher;
import org.eclipse.stardust.modeling.core.search.ReferenceSearchQuery;
import org.eclipse.stardust.modeling.core.search.ReferencesSearchResult;
import org.eclipse.ui.IWorkbenchPart;

import ag.carnot.workflow.model.ExternalReference;

public class ReferencesSearchAction extends SelectionAction
{
   public ReferencesSearchAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.REFERENCES_SEARCH);
      setText(Diagram_Messages.LB_EDITOR_References);
   }

   protected boolean calculateEnabled()
   {
      if(getModelElement() == null)
      {
         return false;
      }
      return true;
   }

   private EObject getModelElement()
   {
      EObject modelElement = null;
      List selection = getSelectedObjects();
      if(selection == null || selection.isEmpty() || selection.size() != 1)
      {
         return modelElement;
      }
      else if(!(selection.get(0) instanceof AbstractEditPart))
      {
         return modelElement;
      }
      AbstractEditPart editPart = (AbstractEditPart) selection.get(0);
      if(editPart instanceof IdentifiableModelElementTreeEditPart)
      {
         modelElement = (EObject) editPart.getModel();
      }
      else if(editPart instanceof AbstractConnectionSymbolEditPart)
      {
         IConnectionSymbol connectionSymbol = (IConnectionSymbol) ((AbstractConnectionSymbolEditPart) editPart).getModel();
         if(connectionSymbol instanceof TransitionConnectionType)
         {
            modelElement = ((TransitionConnectionType) connectionSymbol).getTransition();
         }         
      }
      else if(editPart instanceof AbstractModelElementNodeSymbolEditPart)
      {
         modelElement = ((IModelElementNodeSymbol) 
               ((AbstractModelElementNodeSymbolEditPart) editPart).getModel()).getModelElement();
      }
      else if(editPart instanceof AbstractEObjectTreeEditPart)
      {
         Object data = ((AbstractEObjectTreeEditPart) editPart).getModel();
         if(data instanceof ChildCategoryNode.Spec)
         {
            return null;
         }
         modelElement = (EObject) ((AbstractEObjectTreeEditPart) editPart).getModel();
         if (modelElement instanceof ExternalPackage)
         {
            return modelElement;
         }
         if(!(modelElement instanceof TypeDeclarationType))
         {
            modelElement = null;
         }
      }
      if(modelElement instanceof DataPathType)
      {
         return null;
      }
      if(modelElement instanceof TriggerType)
      {
         return null;
      }
      return modelElement;
   }
   
   public void run()
   {
      final EObject modelElement = getModelElement();
                  
      NewSearchUI.activateSearchResultView();
      NewSearchUI.runQueryInBackground(new ReferenceSearchQuery()
      {
         public IStatus run(IProgressMonitor monitor) throws OperationCanceledException
         {
            super.run(monitor);
            ElementReferenceSearcher searcher = new ElementReferenceSearcher();
            Map matchedElements = searcher.search(modelElement);
            ((ReferencesSearchResult) getSearchResult()).setLabel(getLabel());
            ((ReferencesSearchResult) getSearchResult()).getMatchedElements().putAll(
                  matchedElements);
            return Status.OK_STATUS;
         }

         public String getLabel()
         {
            if(modelElement instanceof IIdentifiableModelElement)
            {
               return ((IIdentifiableModelElement) modelElement).getId();
            }
            else if(modelElement instanceof TypeDeclarationType)
            {
               return ((TypeDeclarationType) modelElement).getId();
            }
            else if (modelElement instanceof ExternalPackage)
            {
               return ((ExternalPackage) modelElement).getId();
            }
            return ""; //$NON-NLS-1$
         }
      });
   }
}