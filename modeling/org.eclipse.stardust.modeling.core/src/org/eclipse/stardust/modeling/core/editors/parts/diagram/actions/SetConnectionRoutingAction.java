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

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.RoutingType;
import org.eclipse.stardust.modeling.core.editors.parts.properties.ConnectionRoutingCommandFactory;


/**
 * @author fherinean
 * @version $Revision$
 */
public class SetConnectionRoutingAction extends Action
{
   private IConnectionSymbol connection;
   private EditDomain domain;
   private RoutingType implType;

   public SetConnectionRoutingAction(RoutingType implType,
         IConnectionSymbol connection, EditDomain domain)
   {
      super(implType.getName());
      this.implType = implType;
      this.connection = connection;
      this.domain = domain;
   }

   public void run()
   {
      Command command = ConnectionRoutingCommandFactory.getSetRoutingCommand(
         implType, connection);
      domain.getCommandStack().execute(command);
   }

   public RoutingType getImplType()
   {
      return implType;
   }
}
