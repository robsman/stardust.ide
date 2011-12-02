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
package org.eclipse.stardust.modeling.core.properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.stardust.model.xpdl.carnot.IModelElement;
import org.eclipse.stardust.model.xpdl.carnot.IModelElementNodeSymbol;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPropertyPage;


public abstract class AbstractConnectionPropertyPage extends AbstractModelElementPropertyPage
      implements IWorkbenchPropertyPage
{
   public void loadElementFromFields(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   public void loadFieldsFromElement(IModelElementNodeSymbol symbol, IModelElement element)
   {
   }

   protected EObject getModelElement()
   {
      return (EObject) getEditor().getModel();
   }

   public void elementChanged()
   {
   }

   protected Connection getConnection()
   {
      return (Connection) getElement().getAdapter(EObject.class);
   }

   protected Object getProperty(String name)
   {
      return getConnection().getProperty(name);
   }

   protected Object getProperty(String name, Object defaultValue)
   {
      Object property = getProperty(name);
      return property == null ? defaultValue : property;
   }

   protected void setProperty(String name, Object value)
   {
      getConnection().setProperty(name, value);
   }

   protected String getAttribute(String name)
   {
      Connection connection = getConnection();
      return connection.getAttribute(name);
   }
   
   protected void removeAttribute(String name)
   {
      Connection connection = getConnection();
      connection.removeAttribute(name);
   }

   protected void setAttribute(String name, String value)
   {
      Connection connection = getConnection();
      connection.setAttribute(name, value);
   }

   protected void connectionPropertiesChanged()
   {
      Connection connection = getConnection();
      ConnectionManager manager = ConnectionManager.getConnectionManager(connection);
      if (manager != null)
      {
         try
         {
            manager.close(connection);
         }
         catch (CoreException e)
         {
            // ignore
            // e.printStackTrace();
         }
         connection.eNotify(new NotificationImpl(Notification.REMOVE, null, null, 0));
      }
   }
}