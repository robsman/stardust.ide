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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.modeling.core.DiagramPlugin;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ObjectRepositoryActivator;
import org.eclipse.stardust.modeling.repository.common.SearchConnectionHandler;


public class SearchConnectionAction extends SelectionAction
{
   private IConfigurationElement config;
   private SearchConnectionHandler handler;
   private Connection connection;
   
   public SearchConnectionAction(IConfigurationElement config, WorkflowModelEditor part)
   {
      super(part);
      this.config = config;
      setId(ObjectRepositoryActivator.SEARCH_ACTION + config.getAttribute(SpiConstants.ID));      
      
      setText(config.getAttribute(SpiConstants.NAME));
      setImageDescriptor(DiagramPlugin.getImageDescriptor(config));
   }
   
   protected boolean calculateEnabled()
   {
      // if it is the connection and only one, and supports search      
      if(getSelectedObjects().size() == 1)
      {         
         if(supportsSearch())
         {
            return true;
         }
      }
      return false;      
   }

   // is it a connection
   public boolean supportsSearch()
   {
      Object entry = getSelectedObjects().get(0);
      if (entry instanceof EditPart)
      {
         Object model = ((EditPart) entry).getModel();
         if (model instanceof Connection)
         {
            // 
            String type = ((Connection) model).getType();      
            if (config.getAttribute(SpiConstants.ID).equals(type))
            {
               if(supportSearch())
               {
                  connection = (Connection) model;
                  return true;                     
               }
            }
         }
      }      
      return false;
   }
   
   private boolean supportSearch()
   {
      try
      {
         handler = (SearchConnectionHandler) config.createExecutableExtension("handler"); //$NON-NLS-1$
         if(handler.supportsSearch())
         {
            return true;
         }
      }
      catch (CoreException e)
      {
         // e.printStackTrace();
      }
      return false;
   }

   public void run()
   {
      // we need a connection to search
      handler.performSearch(connection);
   }
}