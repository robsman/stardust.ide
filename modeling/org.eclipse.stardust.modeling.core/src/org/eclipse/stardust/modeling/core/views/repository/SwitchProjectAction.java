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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.stardust.modeling.core.Diagram_Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.model.IWorkbenchAdapter;


public class SwitchProjectAction extends Action
{
   private VersionRepository repository;
   private Map imageCache;

   public SwitchProjectAction(VersionRepository repository)
   {
      super(Diagram_Messages.LB_Repository_Project);
      this.repository = repository;
      imageCache = new HashMap();
      setMenuCreator(new IMenuCreator()
      {
         private Menu projectsMenu;

         public void dispose()
         {
            if (projectsMenu != null)
            {
               for (int i = 0; i < projectsMenu.getItemCount(); i++)
               {
                  MenuItem menuItem = projectsMenu.getItem(i);
                  menuItem.setData(null);
                  menuItem.setImage(null);
               }
               projectsMenu.dispose();
               projectsMenu = null;
            }
         }

         public Menu getMenu(Control parent)
         {
            dispose();
            projectsMenu = new Menu(parent);
            populateMenu(projectsMenu);
            return projectsMenu;
         }

         public Menu getMenu(Menu parent)
         {
            dispose();
            projectsMenu = new Menu(parent);
            populateMenu(projectsMenu);
            return projectsMenu;
         }
      });
   }

   private Menu populateMenu(Menu projectsMenu)
   {
      IProject[] projects = repository.getContentProvider().getProjects();
      addMenuItem(projectsMenu, null, projects != null && projects.length > 0);
      if (projects != null)
      {
         for (int i = 0; i < projects.length; i++)
         {
            if (projects[i].isAccessible() && projects[i].isOpen())
            {
               addMenuItem(projectsMenu, projects[i], false);
            }
         }
      }
      return projectsMenu;
   }

   private void addMenuItem(Menu parent, IProject value, boolean addSeparator)
   {
      MenuItem item = new MenuItem(parent, SWT.NONE);
      item.setData(value);
      if (value == null)
      {
         item.setText(Diagram_Messages.LB_Repository_SelectionProject);
      }
      else
      {
         IWorkbenchAdapter adapter = (IWorkbenchAdapter) value.getAdapter(IWorkbenchAdapter.class);
         if (adapter == null)
         {
            item.setText(value.getName());
         }
         else
         {
            item.setText(adapter.getLabel(value));
            item.setImage(getImage(adapter.getImageDescriptor(value)));
         }
      }
      item.addSelectionListener(new SelectionAdapter()
      {
         public void widgetSelected(SelectionEvent e)
         {
            MenuItem item = (MenuItem) e.widget;
            IProject project = (IProject) item.getData();
            repository.setStickyProject(project);
         }
      });
      if (addSeparator)
      {
         new MenuItem(parent, SWT.SEPARATOR);
      }
   }

   private Image getImage(ImageDescriptor imageDescriptor)
   {
      Image image = (Image) imageCache.get(imageDescriptor);
      if (image == null)
      {
         image = imageDescriptor.createImage();
         imageCache.put(imageDescriptor, image);
      }
      return image;
   }

   public void dispose()
   {
      Collection values = imageCache.values();
      for (Iterator i = values.iterator(); i.hasNext();)
      {
         Image image = (Image) i.next();
         image.dispose();
      }
      imageCache.clear();
      imageCache = null;
      repository = null;
   }
}
