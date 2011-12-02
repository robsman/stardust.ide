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
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

public class NodeSymbolXYLayoutEditPolicy extends XYLayoutEditPolicy
{
   protected Command createAddCommand(EditPart child, Object constraint)
   {
      // not used in this example
      return null;
   }

   protected Command createChangeConstraintCommand(ChangeBoundsRequest request,
         EditPart child, Object constraint)
   {
      // TODO
      return super.createChangeConstraintCommand(request, child, constraint);
   }

   protected Command createChangeConstraintCommand(EditPart child, Object constraint)
   {
      // not used in this example
      return null;
   }

   protected Command getCreateCommand(CreateRequest request)
   {
      // TODO
      return null;
   }

   protected Command getDeleteDependantCommand(Request request)
   {
      // not used in this example
      return null;
   }
}