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
package org.eclipse.stardust.modeling.core.editors.parts.diagram.policies;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.stardust.model.xpdl.carnot.CarnotWorkflowModelPackage;
import org.eclipse.stardust.model.xpdl.carnot.IGraphicalObject;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.LaneEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.OrphanSymbolCommand;


public class LaneContentContainerEditPolicy extends SymbolContainerEditPolicy
{
   protected Command getOrphanChildrenCommand(GroupRequest request)
   {
      List parts = request.getEditParts();
      CompoundCommand result = new CompoundCommand();
      for (int i = 0; i < parts.size(); i++)
      {
         Object part = parts.get(i);
         // TODO add missing types
         if (part instanceof AbstractNodeSymbolEditPart)
         {
            OrphanSymbolCommand addCmd = new OrphanSymbolCommand();
            // TODO add missing types
            addCmd.setSymbolSymbol(
                  (IGraphicalObject) ((AbstractNodeSymbolEditPart) part).getModel(),
                  CarnotWorkflowModelPackage.eINSTANCE.getISymbolContainer_ActivitySymbol());
            addCmd.setContainer(((LaneEditPart) getHost()).getLaneModel());
            //orphan.setLabel();
            result.add(addCmd);
         }
         else
         {
            result.add(UnexecutableCommand.INSTANCE);
         }
      }
      return result.unwrap();
   }
}