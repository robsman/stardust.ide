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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.stardust.model.xpdl.carnot.GatewaySymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.INodeSymbol;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteAllCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteSymbolCommandFactory;

public class NodeSymbolComponentEditPolicy extends ComponentEditPolicy
{
   protected Command createDeleteCommand(GroupRequest deleteRequest)
   {
      CompoundCommand cmd = new CompoundCommand();
      if (!(getHost().getModel() instanceof GatewaySymbol))
      {
         IModelElement element = (IModelElement) getHost().getModel();

         cmd.add(DeleteSymbolCommandFactory
               .createDeleteSymbolCommand((INodeSymbol) getHost().getModel()));
         cmd.add(DeleteAllCommandFactory.createDeleteAllCommand(element));
         return cmd;
      }

      return null;
   }
}