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
package org.eclipse.stardust.modeling.core.editors.tools;

import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This action sets a Tool to be the active entry in the PaletteViewer.
 */
public class SetActiveFlyoutToolAction extends Action
{

   private PaletteViewer viewer;

   private ToolEntry entry;

   /**
    * Creates a new SetActivePaletteToolAction with the given entry to set, as well as a
    * label, icon, and isChecked to be used in a menu.
    * 
    * @param viewer
    *           the PaletteViewer
    * @param label
    *           the label to show in the menu for this entry.
    * @param icon
    *           the icon to show in the menu for this entry.
    * @param entry
    *           the entry to set if this action is invoked.
    */
   public SetActiveFlyoutToolAction(PaletteViewer viewer, String label,
         ImageDescriptor icon, ToolEntry entry)
   {
      super(label, icon);
      this.viewer = viewer;
      this.entry = entry;
   }

   /**
    * @see org.eclipse.jface.action.IAction#run()
    */
   public void run()
   {
      if (viewer != null)
         viewer.setActiveTool(entry);
   }

}
