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
package org.eclipse.stardust.modeling.common.ui.perspectives;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.stardust.modeling.common.ui.BpmUiConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.cheatsheets.OpenCheatSheetAction;


public class BusinessModelingPerspective implements IPerspectiveFactory, BpmUiConstants
{
   public void createInitialLayout(IPageLayout layout)
   {
      layout.setEditorAreaVisible(true);
      layout.setFixed(false);

      IFolderLayout fldNav = layout.createFolder("navigation", IPageLayout.LEFT, //$NON-NLS-1$
         IPageLayout.DEFAULT_FASTVIEW_RATIO, IPageLayout.ID_EDITOR_AREA);
      fldNav.addView(IPageLayout.ID_OUTLINE);

      IFolderLayout fldDetails = layout.createFolder("details", IPageLayout.BOTTOM, //$NON-NLS-1$
         1.0f - IPageLayout.DEFAULT_FASTVIEW_RATIO, IPageLayout.ID_EDITOR_AREA);
      fldDetails.addView(BpmUiConstants.ID_REPOSITORY_VIEW);
      fldDetails.addView(IPageLayout.ID_PROBLEM_VIEW);

      layout.addPerspectiveShortcut(CWM_PERSPECTIVE_ID);
      layout.addPerspectiveShortcut(CWD_PERSPECTIVE_ID);
      
      layout.addNewWizardShortcut(BpmUiConstants.ID_NEW_MODEL_WIZARD);
      
      layout.addShowViewShortcut(BpmUiConstants.ID_REPOSITORY_VIEW);
      layout.addShowViewShortcut(BpmUiConstants.ID_BOOKMARK_VIEW);
      
      layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
      
      /* open cheatsheets for analyst perspective */
    layout.addShowViewShortcut("org.eclipse.ui.cheatsheets.views.CheatSheetView");  //$NON-NLS-1$
      /* catch Exception while not working when opening a new workspace */
      
 //      #5319 temporarely removed cheat sheets since they contain the old style (JFC)
       try {
       new
       OpenCheatSheetAction("org.eclipse.stardust.modeling.core.cheatsheetAnalystComp").run(); //$NON-NLS-1$
       }
       catch (RuntimeException e)
            {
            }
   }
}
