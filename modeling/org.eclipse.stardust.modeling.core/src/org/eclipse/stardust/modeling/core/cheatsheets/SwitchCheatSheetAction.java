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
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;
import org.eclipse.ui.cheatsheets.OpenCheatSheetAction;

public class SwitchCheatSheetAction extends Action
      implements ICheatSheetAction
{

   /**
    * Switches to another cheatSheet.
    * 
    * @param params
    *           field containing the ID of the cheatSheet to switch to.
    */
   private String cheatSheetID = null;

   public void run(String[] params, ICheatSheetManager manager)
   {
      cheatSheetID = params[0];
      if (params[0] != null)
      {
         // use asyncExec to execute the runnable in the UI thread at appropriate time.
         Display.getDefault().asyncExec(new Runnable() {
        	 
            public void run() 
            {
               new OpenCheatSheetAction(cheatSheetID).run();
            }
            
         });
      }
   }
}