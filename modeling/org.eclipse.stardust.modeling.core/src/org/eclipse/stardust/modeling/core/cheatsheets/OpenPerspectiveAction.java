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
package org.eclipse.stardust.modeling.core.cheatsheets;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

public class OpenPerspectiveAction extends Action
      implements ICheatSheetAction
{
   public void run(String[] params, ICheatSheetManager manager)
   {
      IPerspectiveDescriptor perspective = PlatformUI.getWorkbench().
         getPerspectiveRegistry().findPerspectiveWithId(params[0]);
      if (perspective != null)
      {
         PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
               .setPerspective(perspective);
      }
      // #5319 temporarely removed cheat sheets since they contain the old style (JFC)
      // support case example
      // if (params[1] != null)
      // {
      // new OpenCheatSheetAction(params[1]).run();
      //      }
   }
}
