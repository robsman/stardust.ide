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

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;


public class ForwardDeleteAction extends SelectionAction
{

   private ActionRegistry registry;

   public void dispose()
   {
      registry = null;
      super.dispose();
   }

   public ForwardDeleteAction(IWorkbenchPart part, ActionRegistry registry)
   {
      super(part);
      this.registry = registry;
      setId(DiagramActionConstants.FORWARD_DELETE);
      setText(Diagram_Messages.TXT_ForwardAction);
   }

   protected boolean calculateEnabled()
   {
      return registry.getAction(ActionFactory.DELETE.getId()).isEnabled()
            || registry.getAction(DiagramActionConstants.DELETE_SYMBOL).isEnabled();
   }

   public void run()
   {
      IAction deleteAction = registry.getAction(ActionFactory.DELETE.getId());
      if (!deleteAction.isEnabled())
      {
         deleteAction = registry.getAction(DiagramActionConstants.DELETE_SYMBOL);
      }
      deleteAction.run();
   }

}
