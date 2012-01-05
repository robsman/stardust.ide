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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.stardust.model.xpdl.carnot.ExecutedByConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.IConnectionSymbol;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.PerformsConnectionType;
import org.eclipse.stardust.model.xpdl.carnot.TransitionConnectionType;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.AbstractConnectionSymbolEditPart;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteAllCommandFactory;
import org.eclipse.stardust.modeling.core.editors.parts.diagram.commands.DeleteConnectionSymbolCmd;


public class ConnectionSymbolComponentEditPolicy extends ComponentEditPolicy
{

   private Object connection;

   protected Command createDeleteCommand(GroupRequest deleteRequest)
   {
      CompoundCommand cmd = new CompoundCommand();
      connection = getHost().getModel();
      if (getHost() instanceof AbstractConnectionSymbolEditPart)
      {
         for (Iterator iter = getAllConnectionSymbols().iterator(); iter.hasNext();)
         {
            cmd.add(new DeleteConnectionSymbolCmd((IConnectionSymbol) iter.next()));
         }
         if (connection instanceof IModelElement)
         {
            boolean checkPerformsConn = true;
            boolean checkExecutedConn = true;
            if (connection instanceof PerformsConnectionType)
            {
               checkPerformsConn = (((PerformsConnectionType) connection)
                     .getActivitySymbol() != null);
            }
            else if (connection instanceof ExecutedByConnectionType)
            {
               checkExecutedConn = (((ExecutedByConnectionType) connection)
                     .getActivitySymbol() != null);
            }
            if (checkExecutedConn && checkPerformsConn)
            {
               delete(cmd);
            }
         }
         return cmd;
      }
      return null;
   }

   private void delete(CompoundCommand cmd)
   {
      cmd.add(DeleteAllCommandFactory.createDeleteAllCommand((IModelElement) connection));
   }

   private List getAllConnectionSymbols()
   {
      List list = new ArrayList();
      if (connection instanceof TransitionConnectionType)
      {
         if (((TransitionConnectionType) connection).getTransition() != null)
         {
            return ((TransitionConnectionType) connection).getTransition()
                  .getTransitionConnections();
         }
      }
      list.add(connection);
      return list;
   }
}
