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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.commands;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;


public class RenameViewCommand extends Command
{

   private ViewType view;

   private String oldName;

   private String newName;

   private TreeViewer viewer;

   public RenameViewCommand(ViewType view, String newName, TreeViewer viewer)
   {
      this.view = view;
      this.newName = newName;
      this.viewer = viewer;
   }

   public void execute()
   {
      oldName = view.getName();
      redo();
   }

   public void redo()
   {
      view.setName(newName);
      viewer.refresh();
   }

   public void undo()
   {
      view.setName(oldName);
      viewer.refresh();
   }

}
