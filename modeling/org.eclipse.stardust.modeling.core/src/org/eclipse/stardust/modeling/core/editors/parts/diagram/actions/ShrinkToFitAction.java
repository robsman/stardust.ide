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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.ISwimlaneSymbol;
import org.eclipse.stardust.model.xpdl.carnot.util.ModelUtils;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.DiagramActionConstants;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractSwimlaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.utils.PoolLaneUtils;
import org.eclipse.ui.IWorkbenchPart;


public class ShrinkToFitAction extends SelectionAction
{
   public ShrinkToFitAction(IWorkbenchPart part)
   {
      super(part);
   }

   protected void init()
   {
      super.init();
      setText(Diagram_Messages.LB_SHRINK_TO_FIT);
      setId(DiagramActionConstants.SHRINK_TO_FIT);
   }

   protected boolean calculateEnabled()
   {
      return getSelectedObjects().size() == 1
            && getSelectedObjects().get(0) instanceof AbstractSwimlaneEditPart
            && canChange((AbstractSwimlaneEditPart) getSelectedObjects().get(0));
   }

   private boolean canChange(AbstractSwimlaneEditPart editPart)
   {
      if (editPart == null)
      {
         return false;
      }

      // if container contains only collapsed lanes
      if(!PoolLaneUtils.canChange(editPart))
      {
         return false;
      }
      // no action on a collapsed lane
      if(editPart instanceof LaneEditPart
            && ((LaneEditPart) editPart).getLaneFigure().isCollapsed())
      {
         return false;
      }
      // must contain symbols
      if(!PoolLaneUtils.containsSymbols(editPart))
      {
         return false;
      }

      // is there enough space to shrink the container?
      int[] currentSpace = new int[] {-1, -1, -1, -1};
      if(!PoolLaneUtils.canShrink(editPart, currentSpace))
      {
         return false;
      }
      return true;
   }

   // execute as command
   public void run()
   {
      CompoundCommand cmd = new CompoundCommand();
      AbstractSwimlaneEditPart editPart = (AbstractSwimlaneEditPart) getSelectedObjects().get(0);
      cmd.add(PoolLaneUtils.shrinkToFit(editPart));
      execute(cmd);
   }
}
