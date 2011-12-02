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
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelFactory;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.ViewType;
import org.eclipse.stardust.model.xpdl.carnot.ViewableType;


public class CreateViewableTypeCommand extends Command
{
   private IModelElement element;

   private ViewType view;

   private ViewableType viewable;

   private TreeViewer viewer;

   public CreateViewableTypeCommand(ViewType view, IModelElement element,
         TreeViewer viewer)
   {
      this.view = view;
      this.element = element;
      this.viewer = viewer;
   }

   public void execute()
   {
      CarnotWorkflowModelFactory factory = CarnotWorkflowModelFactory.eINSTANCE;
      viewable = factory.createViewableType();
      viewable.setViewable(element);
      view.getViewable().add(viewable);
   }

   public void redo()
   {
      super.redo();
      viewer.refresh();
   }

   public void undo()
   {
      view.getViewable().remove(viewable);
      viewer.refresh();
   }

}
