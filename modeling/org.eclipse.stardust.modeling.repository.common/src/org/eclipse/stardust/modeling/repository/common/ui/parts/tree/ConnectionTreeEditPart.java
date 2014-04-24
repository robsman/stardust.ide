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
package org.eclipse.stardust.modeling.repository.common.ui.parts.tree;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.stardust.model.xpdl.carnot.spi.SpiConstants;
import org.eclipse.stardust.model.xpdl.util.IConnectionManager;
import org.eclipse.stardust.modeling.repository.common.Connection;
import org.eclipse.stardust.modeling.repository.common.ConnectionManager;
import org.eclipse.stardust.modeling.repository.common.IFilter;
import org.eclipse.stardust.modeling.repository.common.IObjectDescriptor;
import org.eclipse.stardust.modeling.repository.common.RepositoryPackage;
import org.eclipse.stardust.modeling.repository.common.Repository_Messages;
import org.eclipse.stardust.modeling.repository.common.ui.ConnectionQueryUtils;
import org.eclipse.stardust.modeling.repository.common.ui.DeleteValueCmd;
import org.eclipse.stardust.modeling.repository.common.ui.ImageUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;


public class ConnectionTreeEditPart extends LazyLoadingTreeEditPart implements DisposeListener
{
   private Font alternateFont;
   
   public ConnectionTreeEditPart(Connection model)
   {
      super(model);
   }

   protected List<IObjectDescriptor> doGetModelChildren()
   {
      Connection connection = (Connection) getModel();
      if ("true".equals(connection.getProperty( //$NON-NLS-1$
            IConnectionManager.CONNECTION_MANAGER_CREATED)))
      {
         return Collections.emptyList();
      }
      try
      {
         ConnectionManager manager = ConnectionManager.getConnectionManager(connection);
         manager.open(connection);
         // can be categories but also solutions, etc.
         return ConnectionQueryUtils.select(connection, manager,
               (IFilter[]) connection.getProperty("filters")); //$NON-NLS-1$
      }
      catch (CoreException e)
      {
         ErrorDialog.openError(getViewer().getControl().getShell(),
               Repository_Messages.DIA_ERROR, Repository_Messages.DIA_ERROR_OPENING_CONNECTION, e.getStatus());
         return Collections.emptyList();
      }
      catch (Exception e)
      {
         return Collections.emptyList();
      }
   }

   @Override
   public void setWidget(final Widget widget)
   {
      super.setWidget(widget);
      if (checkTreeItem())
      {
         widget.addDisposeListener(this);
         TreeItem item = (TreeItem) widget;
         setAlternativeFont(item);
      }
   }

   private void setAlternativeFont(TreeItem item)
   {
      Connection connection = (Connection) getModel();
      if ("true".equals(connection.getAttribute(IConnectionManager.BY_REFERENCE))) //$NON-NLS-1$
      {
         if (alternateFont == null)
         {
            Font font = item.getFont();
            FontData[] data = font.getFontData();
            int alternateStyle = SWT.ITALIC;
            for (int i = 0; i < data.length; i++)
            {
               int style = data[i].getStyle();
               if ((style & alternateStyle) == alternateStyle)
               {
                  style -= alternateStyle;
               }
               else
               {
                  style += alternateStyle;
               }
               data[i].setStyle(style);
            }
            alternateFont = new Font(font.getDevice(), data);
         }
         item.setFont(alternateFont);
      }
      else
      {
         item.setFont(null);
      }
   }

   protected String doGetText()
   {
      Connection connection = (Connection) getModel();
      String name = connection.getName();
      return name == null ? "" : name; //$NON-NLS-1$
   }
   
   protected Image doGetImage()
   {
      Connection connection = (Connection) getModel();
      if ("true".equals(connection.getProperty( //$NON-NLS-1$
            IConnectionManager.CONNECTION_MANAGER_CREATED)))
      {
         return null;
      }
      
      ConnectionManager manager = ConnectionManager.getConnectionManager(connection);
      try
      {
         IConfigurationElement config = manager.getConfigurationElement(connection.getType());
         String icon = config.getAttribute(SpiConstants.ICON);
         return icon == null ? null
               : ImageUtil.getImageManager(config.getContributor().getName()).getPlainIcon(icon);
      }
      catch (CoreException e)
      {
         return null;
      }
   }

   protected void createEditPolicies()
   {
      installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy()
      {
         protected Command createDeleteCommand(GroupRequest deleteRequest)
         {
            Connection connection = (Connection) getHost().getModel();
            return new DeleteValueCmd(connection.eContainer(),
                  RepositoryPackage.eINSTANCE.getRepository_Connection(), connection);
         }
      });
   }

   public void widgetDisposed(DisposeEvent e)
   {
      if (alternateFont != null)
      {
         Font x = alternateFont;
         alternateFont = null;
         x.dispose();
      }
   }
   
   public void refresh()
   {
      TreeItem item = (TreeItem) widget;
      setAlternativeFont(item);
      super.refresh();
   }
}
