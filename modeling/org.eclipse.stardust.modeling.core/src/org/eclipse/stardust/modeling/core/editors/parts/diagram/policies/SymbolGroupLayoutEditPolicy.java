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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractNodeSymbolEditPart;


/**
 * @author rsauer
 * @version $Revision$
 */
public class SymbolGroupLayoutEditPolicy extends XYLayoutEditPolicy
{
   protected EditPolicy createChildEditPolicy(EditPart child)
   {
      return (child instanceof AbstractNodeSymbolEditPart)
            ? new SymbolSelectionEditPolicy()
            : super.createChildEditPolicy(child);
   }

   protected Command createAddCommand(EditPart child, Object constraint)
   {
      return UnexecutableCommand.INSTANCE;
   }

   protected Command createChangeConstraintCommand(EditPart target, Object constraint)
   {
      return UnexecutableCommand.INSTANCE;
   }

   protected Command getCreateCommand(CreateRequest request)
   {
      return UnexecutableCommand.INSTANCE;
   }

   protected Command getDeleteDependantCommand(Request request)
   {
      return UnexecutableCommand.INSTANCE;
   }
}