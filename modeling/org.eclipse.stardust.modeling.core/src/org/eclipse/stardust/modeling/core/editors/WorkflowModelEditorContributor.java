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

import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;


/**
 * Manages the installation/deinstallation of global actions for multi-page editors.
 * Responsible for the redirection of global actions to the active editor. Multi-page
 * contributor replaces the contributors for the individual editors in the multi-page
 * editor.
 */
public class WorkflowModelEditorContributor
      extends AbstractMultiPageEditorActionBarContributor
{
   protected void buildActions()
   {
      addRetargetAction(new UndoRetargetAction());
      addRetargetAction(new RedoRetargetAction());
      addRetargetAction(new DeleteRetargetAction());

      addRetargetAction(new ZoomInRetargetAction());
      addRetargetAction(new ZoomOutRetargetAction());
      addRetargetAction((RetargetAction) ActionFactory.COPY.create(PlatformUI
            .getWorkbench().getActiveWorkbenchWindow()));
      addRetargetAction((RetargetAction) ActionFactory.PASTE.create(PlatformUI
            .getWorkbench().getActiveWorkbenchWindow()));
      addRetargetAction((RetargetAction) ActionFactory.CUT.create(PlatformUI
            .getWorkbench().getActiveWorkbenchWindow()));
   }

   protected void declareGlobalActionKeys()
   {
      addGlobalActionKey(ActionFactory.PRINT.getId());
   }

   public void contributeToMenu(IMenuManager manager)
   {
      super.contributeToMenu(manager);

      MenuManager viewMenu = new MenuManager(Diagram_Messages.MENU_Manager_View);
      viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
      viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));

      manager.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
   }

   public void contributeToToolBar(IToolBarManager manager)
   {
      super.contributeToToolBar(manager);

      manager.add(getAction(ActionFactory.UNDO.getId()));
      manager.add(getAction(ActionFactory.REDO.getId()));

      manager.add(new Separator());
      manager.add(new ZoomComboContributionItem(getPage()));
   }
}
