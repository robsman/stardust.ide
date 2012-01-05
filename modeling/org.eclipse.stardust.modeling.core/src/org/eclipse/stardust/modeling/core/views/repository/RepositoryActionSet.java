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
package org.eclipse.stardust.modeling.core.views.repository;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * @author fherinean
 * @version $Revision$
 */
public class RepositoryActionSet implements ISelectionChangedListener
{
   private RepositoryAction[] actions;

   public RepositoryActionSet(VersionRepository repository, TreeViewer viewer)
   {
      actions = new RepositoryAction[] {
         new OpenVersionAction(repository),
         new DeleteVersionAction(repository),
         new CreateVersionAction(repository, CreateVersionAction.CREATE_INITIAL_VERSION),
         new CreateVersionAction(repository, CreateVersionAction.CREATE_SUCCESOR_VERSION),
         new CopyVersionAction(repository)
      };
      viewer.addSelectionChangedListener(this);
   }

   public void selectionChanged(SelectionChangedEvent event)
   {
      for (int i = 0; i < actions.length; i++)
      {
         RepositoryAction action = actions[i];
         action.update();
      }
   }

   public void addActions(IMenuManager manager)
   {
      for (int i = 0; i < actions.length; i++)
      {
         RepositoryAction action = actions[i];
         if (action.isEnabled())
         {
            manager.add(action);
         }
      }
   }

   public void setSelectionProvider(ISelectionProvider provider)
   {
      for (int i = 0; i < actions.length; i++)
      {
         RepositoryAction action = actions[i];
         action.setSelectionProvider(provider);
      }
   }

   public void open(OpenEvent event)
   {
      if (actions[0].isEnabled())
      {
         actions[0].run();
      }
   }
}
