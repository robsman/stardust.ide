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
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;

import org.eclipse.stardust.modeling.common.ui.BpmUiConstants;

public class DebuggingPerspective implements IPerspectiveFactory, BpmUiConstants
{
   public void createInitialLayout(IPageLayout layout)
   {
      layout.setEditorAreaVisible(true);
      layout.setFixed(false);

      IFolderLayout fldDetails = layout.createFolder("details", IPageLayout.BOTTOM, //$NON-NLS-1$
         0.75f, IPageLayout.ID_EDITOR_AREA);
      fldDetails.addView(IPageLayout.ID_PROBLEM_VIEW);
      fldDetails.addView(IConsoleConstants.ID_CONSOLE_VIEW);
      fldDetails.addView(IPageLayout.ID_PROP_SHEET);

      IFolderLayout fldDbg = layout.createFolder("control", IPageLayout.TOP, 0.25f, //$NON-NLS-1$
         IPageLayout.ID_EDITOR_AREA);
      fldDbg.addView(IDebugUIConstants.ID_DEBUG_VIEW);

      IFolderLayout fldWork = layout.createFolder("worklist", IPageLayout.RIGHT, 0.5f, //$NON-NLS-1$
         "control"); //$NON-NLS-1$
      fldWork.addView(ID_WORKLIST_VIEW);

      IFolderLayout fldInsp = layout.createFolder("inspection", IPageLayout.RIGHT, 0.5f, //$NON-NLS-1$
         "worklist"); //$NON-NLS-1$
      fldInsp.addView(IDebugUIConstants.ID_VARIABLE_VIEW);

      IFolderLayout fldNav = layout.createFolder("navigation", IPageLayout.RIGHT, 0.75f, //$NON-NLS-1$
         IPageLayout.ID_EDITOR_AREA);
      fldNav.addView(IPageLayout.ID_OUTLINE);

      layout.addPerspectiveShortcut(CWM_PERSPECTIVE_ID);

      layout.addShowViewShortcut(BpmUiConstants.ID_REPOSITORY_VIEW);
      layout.addShowViewShortcut(BpmUiConstants.ID_BOOKMARK_VIEW);

      layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
      layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
   }
}
