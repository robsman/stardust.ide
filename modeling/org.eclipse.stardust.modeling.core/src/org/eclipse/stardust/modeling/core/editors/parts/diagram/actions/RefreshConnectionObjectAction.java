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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;


public class RefreshConnectionObjectAction extends SelectionAction
{
   private List<Connection> selectedConnections;

   public RefreshConnectionObjectAction(WorkflowModelEditor part)
   {
      super(part);
      setId(ObjectRepositoryActivator.REFRESH_CONNECTION_OBJECT_ACTION);
      setText(Diagram_Messages.TXT_REFRESH);
   }

   protected boolean calculateEnabled()
   {
      selectedConnections = getSelectedConnections();
      if (selectedConnections != null)
      {
         return true;
      }
      return false;
   }

   @SuppressWarnings("unchecked")
   private List<Connection> getSelectedConnections()
   {
      if (getSelectedObjects().size() == 0)
         return null;
      List<Object> selection = getSelectedObjects();
      Object entry;
      List<Connection> result = new ArrayList<Connection>();
      for (int i = 0; i < selection.size(); i++)
      {
         entry = getSelectedObjects().get(i);
         if (entry instanceof EditPart)
         {
            Object model = ((EditPart) entry).getModel();
            if (model instanceof Connection)
            {
               result.add((Connection)model);
            }
            else
            {
               return null;
            }
         }
      }
      return result.size() == 0 ? null : result;
   }

   public void run()
   {
      for (Iterator<Connection> i = selectedConnections.iterator(); i.hasNext();)
      {
         Connection connection = i.next();
         connectionPropertiesChanged(connection);
      }
   }

   protected void connectionPropertiesChanged(Connection connection)
   {
      ConnectionManager manager = ConnectionManager.getConnectionManager(connection);
      if (manager != null)
      {
         try
         {
            manager.close(connection);
         }
         catch (CoreException e)
         {

         }
         connection.eNotify(new NotificationImpl(Notification.REMOVE, null, null, 0));
      }
   }
}
