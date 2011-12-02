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
package org.eclipse.stardust.modeling.repository.file;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.window.Window;
import org.eclipse.stardust.modeling.core.editors.WorkflowModelEditor;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.SearchConnectionHandler;
import org.eclipse.stardust.modeling.repository.common.descriptors.CategoryDescriptor;
import org.eclipse.stardust.modeling.repository.file.search.SearchDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import ag.carnot.base.StringUtils;

public class FileSearchConnectionHandler implements SearchConnectionHandler
{
   public void performSearch(Connection connection)
   {      
      final URI uri = ConnectionManager.makeURI(connection);

      WorkflowModelEditor editor = (WorkflowModelEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
      ConnectionManager cm = editor.getConnectionManager();
      Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
      SearchDialog dialog = new SearchDialog(shell);
      
      if (dialog.open() == Window.OK)
      {
         if (dialog.getSearchString() != null && dialog.getSearchString().length() != 0)
         {
            if (!StringUtils.isEmpty(dialog.getSearchString()))
            {               
               // the dialog can search
               try
               {
                  List<IObjectDescriptor> descriptors = cm.select(connection, null);
                  final List<IObjectDescriptor> result = dialog.searchEntries(descriptors);
                  // we take the selection and update the cache 
                  if(result != null)
                  {                  
                     IObjectDescriptor searchDescriptor = new CategoryDescriptor(uri, "search", //$NON-NLS-1$
                           "Search Result", result.toArray(new IObjectDescriptor[result.size()]), //$NON-NLS-1$
                           FileObjectRepositoryActivator.PLUGIN_ID,
                           "icons/search.gif"); //$NON-NLS-1$
                     connection.setProperty("search.result", searchDescriptor); //$NON-NLS-1$
                  }  
                  else
                  {
                     connection.removeProperty("search.result");                   //$NON-NLS-1$
                  }
               }
               catch (CoreException e)
               {
                  // TODO error message?
                  connection.removeProperty("search.result");                   //$NON-NLS-1$
               }
               connection.eNotify(new NotificationImpl(Notification.ADD_MANY, null, null));               
            }
            dialog.close();
         }
      }
   }    

   public boolean supportsSearch()
   {
      return true;
   }
}
