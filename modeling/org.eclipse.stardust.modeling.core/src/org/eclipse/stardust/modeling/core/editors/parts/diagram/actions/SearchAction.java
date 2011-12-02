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

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.window.Window;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.stardust.model.xpdl.carnot.ModelType;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.core.search.SearchDialog;
import org.eclipse.stardust.modeling.core.search.WorkflowModelSearchQuery;
import org.eclipse.stardust.modeling.core.ui.StringUtils;
import org.eclipse.ui.IWorkbenchPart;


public class SearchAction extends SelectionAction
{
   private ModelType model;

   public SearchAction(IWorkbenchPart part)
   {
      super(part);
      setId(DiagramActionConstants.SEARCH);
      setText(Diagram_Messages.LB_SearchAction);
      setImageDescriptor(DiagramPlugin.getImageDescriptor("icons/search.gif")); //$NON-NLS-1$
      setToolTipText(Diagram_Messages.TOOL_TIP_TXT_Search);
      model = (ModelType) ((WorkflowModelEditor) part).getModel();
   }

   protected boolean calculateEnabled()
   {
      return true;
   }

   public void run()
   {
      SearchDialog dialog = new SearchDialog(getWorkbenchPart().getSite().getShell());
      if (dialog.open() == Window.OK)
      {
         if (!StringUtils.isEmpty(dialog.getSearchString()))
         {
            String searchString = dialog.getSearchString();
            dialog.close();
            NewSearchUI.activateSearchResultView();
            NewSearchUI.runQueryInBackground(new WorkflowModelSearchQuery(searchString,
                  model));
         }
      }
   }
}
