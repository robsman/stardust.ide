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
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.model.xpdl.carnot.util.UnusedModelElementsSearcher;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.tree.ModelTreeEditPart;
import org.eclipse.stardust.modeling.core.search.CleanupModelSearchQuery;
import org.eclipse.stardust.modeling.core.search.CleanupModelSearchResult;
import org.eclipse.ui.IWorkbenchPart;

public class CleanupModelAction extends SelectionAction
{
   private ModelType model;
   
   public CleanupModelAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.CLEANUP_MODEL);
      setText(Diagram_Messages.LB_CleanupModel);
      setToolTipText(Diagram_Messages.LB_CleanupModel);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/full/obj16/model.gif")); //$NON-NLS-1$
   }

   protected boolean calculateEnabled()
   {
      List selection = getSelectedObjects();
      if(selection != null && selection.size() == 1)
      {
         Object object = selection.get(0);
         // we do not check if we have unused model elements in advance (to expensive)
         if(object instanceof ModelTreeEditPart)
         {
            model = (ModelType) ((ModelTreeEditPart) object).getModel();
            return true;
         }
      }
      return false;
   }

   public void run()
   {
      NewSearchUI.activateSearchResultView();
      NewSearchUI.runQueryInBackground(new CleanupModelSearchQuery()
      {
         public IStatus run(IProgressMonitor monitor) throws OperationCanceledException
         {
            super.run(monitor);
            UnusedModelElementsSearcher searcher = new UnusedModelElementsSearcher();
            // search the whole model for unused model elements
            Map matchedElements = searcher.search(model);
            // set the result (CleanupModelSearchResult as defined in plugin.xml)
            ((CleanupModelSearchResult) getSearchResult()).getMatchedElements().putAll(
                  matchedElements);
            return Status.OK_STATUS;
         }
      });
   }
}